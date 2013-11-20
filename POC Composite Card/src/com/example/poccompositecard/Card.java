package com.example.poccompositecard;

public class Card {
	private int type;
	private String contentString;

	public Card(int type, String contentString) {
		super();
		this.type = type;
		this.contentString = contentString;
	}

	public int getType() {
		return type;
	}

	public String getContentString() {
		return contentString;
	}

}
