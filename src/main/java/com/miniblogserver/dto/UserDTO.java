package com.miniblogserver.dto;

import java.sql.Timestamp;

public class UserDTO {

	private Long id;
	private String username;
	private String nickname;
	private boolean enabled;
	private String roleName;
	private String email;
	private String userface;
	private Timestamp regTime;

	public UserDTO() {
	}

	public UserDTO(Long id, String username, String nickname, boolean enabled, String roleName, String email, String userface,
			Timestamp regTime) {
		this.id = id;
		this.username = username;
		this.nickname = nickname;
		this.enabled = enabled;
		this.roleName = roleName;
		this.email = email;
		this.userface = userface;
		this.regTime = regTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserface() {
		return userface;
	}

	public void setUserface(String userface) {
		this.userface = userface;
	}

	public Timestamp getRegTime() {
		return regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}
}
