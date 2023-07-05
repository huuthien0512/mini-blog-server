package com.miniblogserver.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miniblogserver.dto.BlogCreationDTO;
import com.miniblogserver.dto.BlogDTO;
import com.miniblogserver.model.Blog;
import com.miniblogserver.model.Category;
import com.miniblogserver.model.User;
import com.miniblogserver.repository.BlogRepository;
import com.miniblogserver.repository.CategoryRepository;
import com.miniblogserver.repository.UserRepository;
import com.miniblogserver.utils.Util;

@Service
@Transactional
public class BlogService {

	private final BlogRepository blogRepository;

	private final UserRepository userRepository;

	private final CategoryRepository categoryRepository;

	BlogService(BlogRepository blogRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		this.blogRepository = blogRepository;
		this.categoryRepository = categoryRepository;
		this.userRepository = userRepository;
	}

	public BlogDTO saveBlog(BlogCreationDTO blogCreationDTO) {
		Long userId = Util.getCurrentUser().getId();
		blogCreationDTO.setUserId(userId);
		Blog blog = convertToEntity(blogCreationDTO);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (blog.getState() == 1) {
			blog.setPublishDate(timestamp);
		}
		blog.setEditTime(timestamp);
		blog.setUser(Util.getCurrentUser());
		Blog addedBlog = blogRepository.save(blog);
		return convertToDTO(addedBlog);
	}

	public Map<String, Object> getBlogByState(Integer state, Integer page, Integer count, String keywords,
			Long userId) {
		Pageable paging = PageRequest.of(page - 1, count);
		Page<Blog> pageBlogs = null;
		pageBlogs = blogRepository.getBlog(state, keywords, userId, paging);
		List<Blog> blogs = pageBlogs.getContent();
		List<BlogDTO> blogDTOs = new ArrayList<>();
		blogs.forEach(blog -> blogDTOs.add(convertToDTO(blog)));

		Map<String, Object> blogMap = new HashMap<>();
		blogMap.put("totalCount", pageBlogs.getTotalElements());
		blogMap.put("blogs", blogDTOs);
		return blogMap;
	}

	public void deleteBlogs(List<Long> ids) {
		blogRepository.deleteAllById(ids);
	}

	public int updateBlogsState(List<Long> ids, Integer state) {
		return blogRepository.updateBlogStateByIds(ids, 3);
	}

	public int restoreBlogs(List<Long> ids) {
		return blogRepository.updateBlogStateByIds(ids, 1);
	}

	public BlogDTO getBlogById(Long id) {
		Optional<Blog> optionalBlog = blogRepository.findById(id);
		BlogDTO blogDTO = null;
		if (optionalBlog.isPresent()) {
			Blog blog = optionalBlog.get();
			blog.setPageView(blog.getPageView() + 1);
			Blog editedBlog = blogRepository.save(blog);
			blogDTO = convertToDTO(editedBlog);
		}
		return blogDTO;
	}

	public BlogDTO convertToDTO(Blog blog) {
		BlogDTO blogDTO = new BlogDTO();
		blogDTO.setId(blog.getId());
		blogDTO.setTitle(blog.getTitle());
		blogDTO.setHtmlContent(blog.getHtmlContent());
		blogDTO.setCategoryId(blog.getCategory().getId());
		blogDTO.setCategoryName(blog.getCategory().getName());
		blogDTO.setNickname(blog.getUser().getNickname());
		blogDTO.setPublishTime(blog.getPublishTime());
		blogDTO.setEditTime(blog.getEditTime());
		blogDTO.setState(blog.getState());
		blogDTO.setPageView(blog.getPageView());
		return blogDTO;
	}

	public Blog convertToEntity(BlogCreationDTO blogCreationDTO) {
		Blog blog = new Blog();
		blog.setId(blogCreationDTO.getId());
		blog.setTitle(blogCreationDTO.getTitle());
		blog.setHtmlContent(blogCreationDTO.getHtmlContent());
		Optional<Category> optionalCategory = categoryRepository.findById(blogCreationDTO.getCategoryId());
		if (optionalCategory.isPresent()) {
			blog.setCategory(optionalCategory.get());
		}
		Optional<User> optionalUser = userRepository.findById(blogCreationDTO.getUserId());
		if (optionalUser.isPresent()) {
			blog.setUser(optionalUser.get());
		}
		blog.setPageView(blogCreationDTO.getPageView());
		blog.setState(blogCreationDTO.getState());
		return blog;
	}
}
