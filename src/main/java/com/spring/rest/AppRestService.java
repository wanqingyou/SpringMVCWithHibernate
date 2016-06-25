package com.spring.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spring.model.Customers;
import com.spring.model.OrderItem;
import com.spring.model.Orders;
import com.spring.model.Product;
import com.spring.service.AppService;
import com.spring.vo.ProductToBeItem;

@Path("/")
@Component
public class AppRestService {

	@Autowired
	private AppService appService;

	ObjectMapper mapper = new ObjectMapper();

	@GET
	@Path("/getOrderDetails/{custName}")
	@Produces({ MediaType.APPLICATION_JSON })
	public String getOrderDetailsByCustomer(@PathParam(value = "custName") String custName) throws JsonProcessingException {
		if (custName == null || custName.equals("")) {
			throw new IllegalArgumentException("custName cannot be null.");
		}
		List<Orders> orders = new ArrayList<Orders>();
		Customers c = appService.getCustomerByName(custName);
		if (c != null) {
			int id = c.getCustomerID();
			orders = appService.listOrdersByCustomer(id);
		}

		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		return mapper.writeValueAsString(orders);
	}

	@GET
	@Path("/getTransactionsOnDate/{date}")
	@Produces({ MediaType.APPLICATION_JSON })
	public String getTransactionsOnDate(@PathParam(value = "date") String date) throws ParseException, JsonProcessingException {
		if (date == null) {
			throw new IllegalArgumentException("date cannot be null.");
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = df.parse(date);
		List<Customers> customers = new ArrayList<Customers>();
		customers = appService.getCustomerByOrderDate(parsedDate);
		for (Customers c : customers) {
			int id = c.getCustomerID();
			List<Orders> orders = appService.listOrdersByCustomer(id, parsedDate);

			c.setOrder(new HashSet<Orders>(orders));
		}
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		return mapper.writeValueAsString(customers);
	}

	@POST
	@Path("/updateProductPrice")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String updateProductPrice(Product product) throws JsonProcessingException {
		if (product == null) {
			throw new IllegalArgumentException("product cannot be null.");
		}

		Product p = appService.getProductByName(product.getName());
		if (p != null) {
			p.setUnitPrice(product.getUnitPrice());
			appService.updateProduct(p, p.getProductId());
			Product productTo = appService.getProductByName(p.getName());
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			return mapper.writeValueAsString(productTo);
		}
		else {
			return String.format("{product : %s not found}", product.getName());
		}
	}

	@PUT
	@Path("/addNewOrdersForCustomer/{custName}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String addNewOrdersForCustomer(@PathParam(value = "custName") String custName, List<ProductToBeItem> items) throws JsonProcessingException {
		if (custName == null || custName.equals("")) {
			throw new IllegalArgumentException("custName cannot be null.");
		}
		if (items == null || items.isEmpty()) {
			throw new IllegalArgumentException("items cannot be null.");
		}

		Customers c = appService.getCustomerByName(custName);
		if (c == null) {
			throw new IllegalArgumentException("customer cannot be null.");
		}
		else {
			Set<OrderItem> orderItems = new HashSet<OrderItem>();
			Orders order = new Orders();
			double total = 0;
			for (ProductToBeItem p : items) {
				OrderItem item = new OrderItem();
				Product product = appService.getProductByName(p.getName());
				item.setProduct(product);
				item.setQuantity(p.getQuantity());
				double s = product.getUnitPrice() * p.getQuantity();
				total += s;
				item.setAmount(s);
				item.setOrder(order);
				orderItems.add(item);
			}
			order.setCustomer(c);
			order.setOrderDate(new Date());
			order.setOrderItem(orderItems);
			order.setAmount(total);
			appService.addOrder(order);
		}
		int id = c.getCustomerID();
		c.setOrder(new HashSet<Orders>(appService.listOrdersByCustomer(id)));
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		return mapper.writeValueAsString(c);
	}
}
