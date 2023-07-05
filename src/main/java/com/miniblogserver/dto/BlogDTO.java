package com.miniblogserver.dto;

import java.sql.Timestamp;
import java.util.List;

import com.miniblogserver.model.Tags;

public class BlogDTO {

	private Long id;
	private String title;
	private String htmlContent;
	private Long userId;
	private Timestamp publishTime;
	private Integer state;
	private Integer pageView;
	private Timestamp editTime;
	private String[] dynamicTags;
	private String nickname;
	private Long categoryId;
	private String categoryName;
	private List<Tags> tags;

	public BlogDTO() {
	}

	public BlogDTO(Long id, String title, String htmlContent, Long userId, Timestamp publishTime, Integer state,
			Integer pageView, Timestamp editTime, String[] dynamicTags, String nickname, Long categoryId,
			String categoryName, List<Tags> tags) {
		this.id = id;
		this.title = title;
		this.htmlContent = htmlContent;
		this.userId = userId;
		this.publishTime = publishTime;
		this.state = state;
		this.pageView = pageView;
		this.editTime = editTime;
		this.dynamicTags = dynamicTags;
		this.nickname = nickname;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.tags = tags;
	}

	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String[] getDynamicTags() {
		return dynamicTags;
	}

	public void setDynamicTags(String[] dynamicTags) {
		this.dynamicTags = dynamicTags;
	}

	public Timestamp getEditTime() {
		return editTime;
	}

	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Timestamp getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getPageView() {
		return pageView;
	}

	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}
}
