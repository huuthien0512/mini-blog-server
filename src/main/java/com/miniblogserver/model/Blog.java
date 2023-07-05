package com.miniblogserver.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity(name = "blog")
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "html_content", nullable = false)
	private String htmlContent;

	@OneToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(name = "publish_time")
	private Timestamp publishTime;

	@Column(name = "edit_time")
	private Timestamp editTime;

	@Column(name = "state")
	private Integer state;

	@Column(name = "page_view")
	private Integer pageView;

	public Blog() {

	}

	public Blog(Long id, String title, String htmlContent, Category category, User user, Timestamp publishTime,
			Timestamp editTime, Integer state, Integer pageView) {
		this.id = id;
		this.title = title;
		this.htmlContent = htmlContent;
		this.category = category;
		this.user = user;
		this.publishTime = publishTime;
		this.editTime = editTime;
		this.state = state;
		this.pageView = pageView;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getPublishTime() {
		return publishTime;
	}

	public void setPublishDate(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public Timestamp getEditTime() {
		return editTime;
	}

	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
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
