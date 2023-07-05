package com.miniblogserver.model;

import java.util.List;

public class BlogIdsState {
	private List<Long> ids;
	private Integer state;

	public BlogIdsState() {
	}

	public BlogIdsState(List<Long> ids, Integer state) {
		this.ids = ids;
		this.state = state;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
