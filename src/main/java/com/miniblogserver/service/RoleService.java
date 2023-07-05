package com.miniblogserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.miniblogserver.dto.RoleDTO;
import com.miniblogserver.model.Role;
import com.miniblogserver.model.RoleUser;
import com.miniblogserver.model.User;
import com.miniblogserver.repository.RoleRepository;
import com.miniblogserver.repository.RoleUserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleService {
	
	private final RoleRepository roleRepository;
	
	private final RoleUserRepository roleUserRepository;
	
	public RoleService(RoleRepository roleRepository, RoleUserRepository roleUserRepository) {
		this.roleRepository = roleRepository;
		this.roleUserRepository = roleUserRepository;
	}
	
	public List<RoleDTO> getRoles() {
		List<Role> roles = roleRepository.findAll();
		List<RoleDTO> roleDTOs = new ArrayList<>(roles.size());
		roles.forEach(role -> roleDTOs.add(convertToDTO(role)));
		return roleDTOs;
	}
	
	public void setUserRole(Long userId, Long roleId) {
		RoleUser roleUser = new RoleUser();
		Role role = new Role();
		role.setId(roleId);
		roleUser.setRole(role);
		User user = new User();
		user.setId(userId);
		roleUser.setUser(user);
		roleUserRepository.save(roleUser);
	}
	
	public RoleDTO convertToDTO(Role role) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(role.getId());
		roleDTO.setName(role.getName());
		return roleDTO;
	}

	public Role convertToEntity(RoleDTO roleDTO) {
		Role role = new Role();
		role.setId(roleDTO.getId());
		role.setName(roleDTO.getName());
		return role;
	}
}
