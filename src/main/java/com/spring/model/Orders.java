package com.spring.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;


/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author WYou
 * @since 16.6.0
 */
@Entity
@Table(name = "Orders")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Order_id")
	private int orderID;
	@Temporal(TemporalType.DATE)
	@Column(name = "Order_Date")
	private Date orderDate;
	@Column(name = "Amount")
	private double amount;
	@ManyToOne()
	@JoinColumn(name = "Customer_ID")
	private Customers customer;
	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	private Set<OrderItem> orderItem;

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Set<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(Set<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

}
