package com.miniblogserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniblogserver.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
}
