package com.miniblogserver.dto;

import java.sql.Timestamp;

public class CategoryDTO {
	private Long id;
	private String name;
	private Timestamp createTime;
	private Timestamp editTime;

	public CategoryDTO() {
	}

	public CategoryDTO(Long id, String name, Timestamp createTime, Timestamp editTime) {
		this.id = id;
		this.name = name;
		this.createTime = createTime;
		this.editTime = editTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getEditTime() {
		return editTime;
	}

	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}
}
