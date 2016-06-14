package com.spring.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.Customers;
import com.spring.model.OrderItem;
import com.spring.model.Orders;
import com.spring.model.Product;
import com.spring.service.AppService;
import com.spring.vo.ProductToBeItem;

@Controller
@SessionAttributes({ "cart" })
public class AppController {
	@Autowired
	private AppService appService;

	@RequestMapping("/order/list-in-date")
	public ModelAndView custSearchPage() {
		return new ModelAndView("orderindate");
	}

	@RequestMapping(value = "/order/custSearch", method = RequestMethod.POST)
	public ModelAndView custSearch(@RequestParam(value = "orderDate") String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = df.parse(date);

		List<Customers> searchResult = appService.getCustomerByOrderDate(parsedDate);

		ModelAndView model = new ModelAndView("orderindate");
		model.addObject("searchResults", searchResult);
		model.addObject("orderDate", date);
		model.addObject("noData", searchResult.isEmpty());
		return model;
	}

	@RequestMapping(value = { "/", "/customers" }, method = RequestMethod.GET)
	public String listPersons(Model model, HttpServletRequest request) {
		model.addAttribute("listCustomers", appService.listCustomers());
		request.getSession().removeAttribute("cart");
		request.getSession().removeAttribute("customerId");
		model.addAttribute("cart", new ArrayList<ProductToBeItem>());
		return "customer";
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String listProducts(Model model) {
		model.addAttribute("listProducts", appService.listProducts());
		return "product";
	}

	/**
	* This method will provide the medium to add a new customer.
	*/
	@RequestMapping(value = { "/customer/add" }, method = RequestMethod.GET)
	public String newCustomer(ModelMap model) {
		Customers customer = new Customers();
		model.addAttribute("customer", customer);
		return "addcustomer";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving customer in database. It also validates the user input
	 */
	@RequestMapping(value = { "/customer/add" }, method = RequestMethod.POST)
	public String saveCustomer(@Valid Customers user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "addcustomer";
		}
		appService.addCustomer(user);

		model.addAttribute("success", "Customer " + user.getCustomerName() + " added successfully");
		return "addcustsuccess";
	}

	@RequestMapping(value = "orderItemList/{customerId}", method = RequestMethod.GET)
	public ModelAndView getOrderItems(Model model, @ModelAttribute("cart") List<ProductToBeItem> itemList, @PathVariable(value = "customerId") int id,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("customerId", id);
		mv.addObject("customerName", appService.getCustomerById(id).getCustomerName());

		request.getSession().setAttribute("customerId", id);
		System.out.println("The # of items in shopping cart:" + itemList.size());
		if (itemList != null && !itemList.isEmpty())
			mv.addObject("itemList", itemList);
		else
			mv.addObject("itemList", request.getSession().getAttribute("cart"));
		mv.setViewName("cart");
		return mv;
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving order in database. It also validates the user input
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "orderItemList/{customerId}", method = RequestMethod.POST)
	public ModelAndView submitorder(Model model, @PathVariable(value = "customerId") int id, @ModelAttribute("cart") List<ProductToBeItem> itemList,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();


		List<ProductToBeItem> items = new ArrayList<ProductToBeItem>();
		if (itemList != null && !itemList.isEmpty())
			items = itemList;
		else
			items = (List<ProductToBeItem>) request.getSession().getAttribute("cart");
		Orders order = new Orders();
		order.setCustomer(appService.getCustomerById(id));
		order.setOrderDate(new Date());
		Set<OrderItem> orderitems = new HashSet<OrderItem>();
		double total = 0;
		for (ProductToBeItem item : items) {
			OrderItem orderitem = new OrderItem();
			orderitem.setOrder(order);
			orderitem.setProduct(appService.getProductById(item.getProductId()));
			orderitem.setQuantity(item.getQuantity());
			double temp = item.getUnitPrice() * item.getQuantity();
			orderitem.setAmount(temp);
			total += temp;
			orderitems.add(orderitem);
		}
		order.setAmount(total);
		order.setOrderItem(orderitems);
		appService.addOrder(order);

		items.clear();
		request.getSession().removeAttribute("cart");
		request.getSession().removeAttribute("customerId");
		model.addAttribute("success", "Order added successfully");
		model.addAttribute("cart", new ArrayList<ProductToBeItem>());
		mv.setViewName("addordersuccess");
		return mv;
	}

	@RequestMapping(value = "/get-item-to-add/{customerId}", method = RequestMethod.GET)
	public String getOrderItemToAdd(@PathVariable(value = "customerId") int id, ModelMap model, HttpServletRequest request) {
		request.getSession().setAttribute("customerId", id);
		model.addAttribute("customerId", id);
		model.addAttribute("customerName", appService.getCustomerById(id).getCustomerName());
		List<Product> list = appService.listProducts();
		List<ProductToBeItem> items = new ArrayList<ProductToBeItem>();
		for (Product pro : list) {
			ProductToBeItem item = new ProductToBeItem();
			item.setProductId(pro.getProductId());
			item.setName(pro.getName());
			item.setUnitPrice(pro.getUnitPrice());
			item.setQuantity(1);
			items.add(item);
		}

		model.addAttribute("listItems", items);
		List<ProductToBeItem> newOrderItems = new ArrayList<ProductToBeItem>();
		request.getSession().setAttribute("cart", newOrderItems);
		return "listproductforaddingorder";
	}

	@RequestMapping(value = "/get-order-details/{customerId}", method = RequestMethod.GET)
	public String getOrderDetailsByCustomer(@PathVariable(value = "customerId") int id, ModelMap model) {
		model.addAttribute("customerId", id);
		model.addAttribute("customerName", appService.getCustomerById(id).getCustomerName());
		model.addAttribute("listOrders", appService.listOrdersByCustomer(id));
		return "getorderdetails";
	}

	@RequestMapping(value = "/get-order-items/{customerId}/{orderId}", method = RequestMethod.GET)
	public String getOrderItemsByOrder(@PathVariable(value = "customerId") int custId, @PathVariable(value = "orderId") int id, ModelMap model) {
		model.addAttribute("customerId", custId);
		model.addAttribute("customerName", appService.getCustomerById(custId).getCustomerName());
		model.addAttribute("listOrderItems", appService.listOrderItemsByOrderId(id));
		return "getorderitems";
	}

	/**
	* This method will provide the medium to add a new product.
	*/
	@RequestMapping(value = { "/orders/add/{customerId}/{productId}" }, method = RequestMethod.GET)
	public String newOrder(@PathVariable(value = "customerId") int customerId, @PathVariable(value = "productId") int id, ModelMap model,
			@ModelAttribute("cart") List<ProductToBeItem> itemList, HttpServletRequest request) {
		ProductToBeItem product = new ProductToBeItem();
		Product p = appService.getProductById(id);
		product.setName(p.getName());
		product.setProductId(p.getProductId());
		product.setUnitPrice(p.getUnitPrice());
		model.addAttribute("productTo", product);
		model.addAttribute("cart", itemList);
		request.getSession().setAttribute("customerId", customerId);
		return "addtocart";
	}

	/**
	* This method will provide the medium to add a new product.
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/orders/add/{customerId}/{productId}" }, method = RequestMethod.POST)
	public String newOrder(@Valid ProductToBeItem product, BindingResult result, @PathVariable(value = "customerId") int customerId,
			@PathVariable(value = "productId") int id, ModelMap model, @ModelAttribute("cart") List<ProductToBeItem> itemList, HttpServletRequest request) {
		if (result.hasErrors()) {
			return "redirect:/get-item-to-add/" + customerId;
		}
		List<ProductToBeItem> cart = (List<ProductToBeItem>) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<ProductToBeItem>();
		}
		cart.add(product);
		request.getSession().setAttribute("cart", cart);
		model.put("cart", cart);
		return "redirect:/get-item-to-add/" + customerId;
	}

	/**
	* This method will provide the medium to add a new product.
	*/
	@RequestMapping(value = { "/product/add" }, method = RequestMethod.GET)
	public String newProduct(ModelMap model) {
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("edit", false);
		return "addproduct";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving product in database. It also validates the user input
	 */
	@RequestMapping(value = { "/product/add" }, method = RequestMethod.POST)
	public String saveProduct(@Valid Product product, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "addproduct";
		}
		appService.addProduct(product);
		model.addAttribute("success", "Product " + product.getName() + " added successfully");
		return "addprosuccess";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-product-details/{productId}" }, method = RequestMethod.GET)
	public String editProduct(@PathVariable(value = "productId") int id, ModelMap model) {
		Product product = appService.getProductById(id);
		model.addAttribute("product", product);
		model.addAttribute("edit", true);
		return "addproduct";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-product-details/{productId}" }, method = RequestMethod.POST)
	public String updateProduct(@Valid Product product, BindingResult result, ModelMap model, @PathVariable(value = "productId") int id) {
		if (result.hasErrors()) {
			return "addproduct";
		}
		appService.updateProduct(product, id);
		model.addAttribute("success", "Product " + product.getName() + " updated successfully");
		return "addprosuccess";
	}
}
