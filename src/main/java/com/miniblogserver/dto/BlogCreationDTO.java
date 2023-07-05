package com.miniblogserver.dto;

import java.util.List;

import com.miniblogserver.model.Tags;

public class BlogCreationDTO {
	private Long id;
	private String title;
	private String htmlContent;
	private Integer state;
	private Long userId;
	private Long categoryId;
	private Integer pageView;
	private List<Tags> tags;

	public BlogCreationDTO() {
	}

	public BlogCreationDTO(Long id, String title, String htmlContent, Integer state, Long userId, Long categoryId,
			Integer pageView, List<Tags> tags) {
		this.id = id;
		this.title = title;
		this.htmlContent = htmlContent;
		this.state = state;
		this.userId = userId;
		this.categoryId = categoryId;
		this.pageView = pageView;
		this.tags = tags;
	}

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

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getPageView() {
		return pageView;
	}

	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}

	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

}
