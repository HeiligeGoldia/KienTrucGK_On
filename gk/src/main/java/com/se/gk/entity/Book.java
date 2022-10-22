package com.se.gk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "book2")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SubId;
	
	private String name;
	
	private String credit;

	public int getSubId() {
		return SubId;
	}

	public void setSubId(int subId) {
		SubId = subId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public Book(int subId, String name, String credit) {
		super();
		SubId = subId;
		this.name = name;
		this.credit = credit;
	}

	public Book() {
		super();
	}

	@Override
	public String toString() {
		return "Book [SubId=" + SubId + ", name=" + name + ", credit=" + credit + "]";
	}
	
	
	
}
