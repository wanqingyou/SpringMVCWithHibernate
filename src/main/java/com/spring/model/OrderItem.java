package com.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author WYou
 * @since 16.6.0
 */
@Entity
@Table(name = "OrderItem")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderItem_id")
	private int orderItemID;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "Amount")
	private double amount;
	@OneToOne(fetch = FetchType.EAGER, targetEntity = Product.class)
	@JoinColumn(name = "product_Id", referencedColumnName = "product_Id")
	@JsonIgnore
	private Product product;
	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonIgnore
	private Orders order;

	public int getOrderItemID() {
		return orderItemID;
	}

	public void setOrderItemID(int orderItemID) {
		this.orderItemID = orderItemID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}


}
