package com.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.DAO;
import com.spring.model.Customers;
import com.spring.model.OrderItem;
import com.spring.model.Orders;
import com.spring.model.Product;

/**
 * Service implementation
 * @author WYou
 * @since 16.6.0
 */

@Service
@Transactional
public class AppServiceImpl implements AppService {
	@Autowired
	private DAO appDao;

	public AppServiceImpl() {

	}

	public AppServiceImpl(DAO appDao) {
		this.appDao = appDao;
	}

	@Override
	public List<Customers> getCustomerByOrderDate(Date orderDate) {
		return appDao.getCustomerByOrderDate(orderDate);
	}

	@Override
	public void addCustomer(Customers customer) {
		appDao.addCustomer(customer);
	}

	@Override
	public List<Customers> listCustomers() {
		return appDao.listCustomers();
	}

	@Override
	public Customers getCustomerById(int id) {
		return appDao.getCustomer(id);
	}

	@Override
	public List<Orders> listOrdersByCustomer(int id) {
		return appDao.listOrdersByCustomer(id);
	}

	@Override
	public List<OrderItem> listOrderItemsByOrderId(int id) {
		return appDao.listOrderItemsByOrder(id);
	}

	@Override
	public void addProduct(Product product) {
		appDao.addProduct(product);

	}

	@Override
	public Product getProductById(int id) {
		return appDao.getProductById(id);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateProduct(Product product, int id) {
		Product entity = appDao.getProductById(id);
		if (entity != null) {
			entity.setName(product.getName());
			entity.setUnitPrice(product.getUnitPrice());
		}
	}

	@Override
	public List<Product> listProducts() {
		return appDao.listProducts();
	}

	@Override
	public void addOrder(Orders order) {
		appDao.addOrder(order);
	}

	@Override
	public void addOrderItem(OrderItem order) {
		appDao.addOrderItem(order);
	}

	@Override
	public Customers getCustomerByName(String name) {
		return appDao.getCustomerByName(name);
	}

	@Override
	public List<Orders> listOrdersByCustomer(int id, Date date) {
		return appDao.listOrdersByCustomer(id, date);
	}

	@Override
	public Product getProductByName(String name) {
		return appDao.getProductByName(name);
	}

}
