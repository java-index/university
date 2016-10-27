package com.smarterama.university.ui;

public class Message {

	private String type;
	private String message;

	public Message(String type, String message) {
		this.message = message;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}
}