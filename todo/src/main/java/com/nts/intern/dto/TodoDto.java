package com.nts.intern.dto;

import java.time.LocalDateTime;

public class TodoDto {
	private Long id;
	private String title;
	private String name;
	private Integer sequence;
	private String type;
	private LocalDateTime regDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
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
