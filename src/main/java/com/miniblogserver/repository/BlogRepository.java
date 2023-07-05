package com.miniblogserver.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.miniblogserver.model.Blog;

import jakarta.transaction.Transactional;

public interface BlogRepository extends JpaRepository<Blog, Long> {

	@Query(value = "SELECT b FROM blog b WHERE b.state=?1 AND (?2 = '' OR b.title LIKE %?2%) AND (?3 = -1 OR b.user.id = ?3)")
	Page<Blog> getBlog(Integer state, String keywords, Long userId, Pageable pageable);

	@Transactional
	@Modifying
	@Query(value = "UPDATE blog SET state=?2 WHERE id IN ?1")
	int updateBlogStateByIds(List<Long> ids, Integer state);
}
