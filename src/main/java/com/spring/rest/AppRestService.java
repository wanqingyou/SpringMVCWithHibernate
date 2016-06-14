package com.spring.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.DAO;
import com.spring.model.Customers;
import com.spring.model.OrderItem;
import com.spring.model.Orders;
import com.spring.model.Product;
import com.spring.vo.ProductToBeItem;

@Path("/")
public class AppRestService {

	@Autowired
	private DAO appDao;

	@GET
	@Path("/getOrderDetails/{custName}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public List<Orders> getOrderDetailsByCustomer(@PathParam(value = "custName") String custName) {
		if (custName == null || custName.equals("")) {
			throw new IllegalArgumentException("custName cannot be null.");
		}
		List<Orders> orders = new ArrayList<Orders>();
		Customers c = appDao.getCustomerByName(custName);
		if (c != null) {
			int id = c.getCustomerID();
			orders = appDao.listOrdersByCustomer(id);
			for (Orders order : orders) {
				List<OrderItem> items = appDao.listOrderItemsByOrder(order.getOrderID());
				order.setOrderItem(new HashSet<OrderItem>(items));
			}
		}
		return orders;
	}

	@GET
	@Path("/getTransactionsAtDate/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public List<Customers> getOrderDetailsByCustomer(@PathParam(value = "date") Date date) {
		if (date == null) {
			throw new IllegalArgumentException("date cannot be null.");
		}
		List<Customers> customers = new ArrayList<Customers>();
		customers = appDao.getCustomerByOrderDate(date);
		for (Customers c : customers) {
			int id = c.getCustomerID();
			List<Orders> orders = appDao.listOrdersByCustomer(id);
			for (Orders order : orders) {
				List<OrderItem> items = appDao.listOrderItemsByOrder(order.getOrderID());
				order.setOrderItem(new HashSet<OrderItem>(items));
			}
			c.setOrder(new HashSet<Orders>(orders));
		}
		return customers;
	}

	@POST
	@Path("/updateProductPrice")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	@Transactional
	public Product updateProductPrice(@FormParam("name") String name, @FormParam("unitPrice") double unitPrice) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("name cannot be null.");
		}

		Product product = appDao.getProductByName(name);
		if (product != null) {
			product.setUnitPrice(unitPrice);
			appDao.updateProduct(product);
		}
		else {
			product = new Product();
			product.setName(name);
			product.setUnitPrice(unitPrice);
			appDao.addProduct(product);
		}
		return appDao.getProductByName(name);
	}

	@PUT
	@Path("/addNewOrdersForCustomer/{custName}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String addNewOrdersForCustomer(@PathParam(value = "custName") String custName, List<ProductToBeItem> items) {
		if (custName == null || custName.equals("")) {
			throw new IllegalArgumentException("custName cannot be null.");
		}
		if (items == null || items.isEmpty()) {
			throw new IllegalArgumentException("items cannot be null.");
		}

		Customers c = appDao.getCustomerByName(custName);
		if (c == null) {
			return String.format("Customer %s not found.", custName);
		}
		else {
			Set<OrderItem> orderItems = new HashSet<OrderItem>();
			Orders order = new Orders();
			double total = 0;
			for (ProductToBeItem p : items) {
				OrderItem item = new OrderItem();
				item.setProduct(appDao.getProductById((p.getProductId())));
				item.setQuantity(p.getQuantity());
				double s = p.getUnitPrice() * p.getQuantity();
				total += s;
				item.setAmount(s);
				item.setOrder(order);
				orderItems.add(item);
			}
			order.setCustomer(c);
			order.setOrderDate(new Date());
			order.setOrderItem(orderItems);
			order.setAmount(total);
			appDao.addOrder(order);
		}
		return String.format("Order added for customer %s.", custName);
	}
}
