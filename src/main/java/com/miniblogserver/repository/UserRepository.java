package com.miniblogserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.miniblogserver.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	@Query(value = "SELECT u FROM user u WHERE u.username = ?1")
	User findUserByUsername(String username);
	
	@Query(value = "SELECT u FROM user u WHERE (?1 ='' OR CONCAT(u.username, ' ', u.nickname) LIKE %?1%) AND u.id <> ?2")
	List<User> findUsersByUsername(String username, Long userId);
	
	@Modifying
	@Query(value = "UPDATE user SET enabled = ?2 WHERE id = ?1")
	int updateUserEnabledByUserId(Long userId, boolean enable);
}
