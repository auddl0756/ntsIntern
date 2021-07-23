package com.nts.intern.type;

public enum TodoType {
	TODO("TODO"), DOING("DOING"), DONE("DONE");

	private final String type;

	TodoType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
