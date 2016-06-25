package com.spring.dao;

import java.util.Date;
import java.util.List;

import com.spring.model.Customers;
import com.spring.model.OrderItem;
import com.spring.model.Orders;
import com.spring.model.Product;

/**
 * Defines DAO operations for the models.
 * @author WYou
 * @since 16.5.0
 */
public interface DAO {

	public List<Customers> getCustomerByOrderDate(Date date);

	public Customers getCustomer(int custID);

	public Customers getCustomerByName(String name);

	public void addCustomer(Customers customer);

	public List<Customers> listCustomers();

	public List<Orders> listOrdersByCustomer(int id);

	public List<Orders> listOrdersByCustomer(int id, Date date);

	public List<OrderItem> listOrderItemsByOrder(int id);

	public void addProduct(Product product);

	public Product getProductById(int id);

	public Product getProductByName(String name);

	public void updateProduct(Product product);

	public List<Product> listProducts();

	public void addOrder(Orders order);

	public void addOrderItem(OrderItem order);
}
