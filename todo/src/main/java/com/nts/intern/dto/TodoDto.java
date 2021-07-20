package com.nts.intern.dto;

import java.time.LocalDateTime;

public class TodoDto {
	private long id;
	private String title;
	private String name;
	private int sequence;
	private String type;
	private LocalDateTime regDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDateTime(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "TodoDto [id=" + id + ", title=" + title + ", name=" + name + ", sequence=" + sequence + ", type=" + type
				+ ", regDate=" + regDate.getYear() + " " + regDate.getMonth() + " " + regDate.getDayOfMonth() + "]";
	}
}
