package com.miniblogserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniblogserver.model.Role;
import com.miniblogserver.model.RoleUser;

public interface RoleUserRepository extends JpaRepository<RoleUser, Long>{
	
}
