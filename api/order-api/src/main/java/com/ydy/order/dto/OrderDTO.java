package com.ydy.order.dto;

import com.ydy.order.model.Order;

public class OrderDTO extends Order {
	private String word;//

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
