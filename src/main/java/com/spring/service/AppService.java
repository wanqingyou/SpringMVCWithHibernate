package com.spring.service;

import java.util.Date;
import java.util.List;

import com.spring.model.Customers;
import com.spring.model.OrderItem;
import com.spring.model.Orders;
import com.spring.model.Product;

/**
 * Define service interface.
 * @author WYou
 * @Since 16.6.0
 */
public interface AppService {
	public List<Customers> getCustomerByOrderDate(Date orderDate);

	public void addCustomer(Customers customer);

	public Customers getCustomerById(int id);

	public List<Customers> listCustomers();

	public List<Orders> listOrdersByCustomer(int id);

	public List<OrderItem> listOrderItemsByOrderId(int id);

	public List<Product> listProducts();

	public void addProduct(Product product);

	public Product getProductById(int id);

	public void updateProduct(Product product, int id);

	public void addOrder(Orders order);

	public void addOrderItem(OrderItem order);
}
