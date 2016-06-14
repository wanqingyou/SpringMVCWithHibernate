package com.spring.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.Customers;
import com.spring.model.OrderItem;
import com.spring.model.Orders;
import com.spring.model.Product;

/**
 * An implementation of the DAO interface.
 * @author WYou
 * @since 16.6.0
 */
@Repository
public class DAOImpl implements DAO {
	private static final Logger logger = Logger.getLogger(DAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public DAOImpl() {

	}

	public DAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customers> getCustomerByOrderDate(Date orderDate) {
		String query = "SELECT customers FROM Customers customers"
				+ ", Orders orders WHERE orders.customer.customerID = customers.customerID AND orders.orderDate = :orderDate";
		Session session = this.sessionFactory.getCurrentSession();
		List<Customers> list = session.createQuery(query).setParameter("orderDate", orderDate).list();
		return list;
	}

	@Override
	public Customers getCustomer(int custID) {
		Session session = this.sessionFactory.getCurrentSession();
		Customers c = (Customers) session.load(Customers.class, new Integer(custID));
		logger.info("Customer loaded successfully, Customer details=" + c);
		return c;
	}

	@Override
	public void addCustomer(Customers customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(customer);
		logger.info("Customer saved successfully, Customer Details=" + customer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customers> listCustomers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Customers> list = session.createQuery("from Customers").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> listOrdersByCustomer(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		String query = "SELECT orders FROM Orders as orders" + " JOIN  orders.customer cust WHERE cust.customerID = :id";
		List<Orders> list = session.createQuery(query).setParameter("id", id).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderItem> listOrderItemsByOrder(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		String query = "SELECT items FROM OrderItem as items" + " JOIN  items.order ord WHERE ord.orderID = :id";
		List<OrderItem> list = session.createQuery(query).setParameter("id", id).list();
		return list;
	}

	@Override
	public void addProduct(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(product);
		logger.info("Product saved successfully, Product Details=" + product);
	}

	@Override
	public Product getProductById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Product c = (Product) session.load(Product.class, new Integer(id));
		logger.info("Product loaded successfully, Product details=" + c);
		return c;
	}

	@Override
	public void updateProduct(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(product);
		logger.info("product updated successfully, product Details=" + product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> listProducts() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Product> list = session.createQuery("from Product").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Customers getCustomerByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Customers> customers = session.createQuery("from Customers where lower(customerName) like :name").setParameter("name", "%" + name + "%").list();
		if (!customers.isEmpty()) {
			return customers.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Product getProductByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Product> products = session.createQuery("from Product where name = :name").setParameter("name", name).list();
		if (!products.isEmpty()) {
			return products.get(0);
		}
		return null;
	}

	@Override
	public void addOrder(Orders order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(order);
		logger.info("order saved successfully, order Details=" + order);
	}

	@Override
	public void addOrderItem(OrderItem order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(order);
		logger.info("orderItem saved successfully, order Details=" + order);
	}
}
