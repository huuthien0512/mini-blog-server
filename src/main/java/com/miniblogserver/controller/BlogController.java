package com.miniblogserver.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.miniblogserver.dto.BlogCreationDTO;
import com.miniblogserver.dto.BlogDTO;
import com.miniblogserver.model.BlogIdsState;
import com.miniblogserver.model.ImageData;
import com.miniblogserver.service.BlogService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/blogs")
public class BlogController {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	private final BlogService blogService;

	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@GetMapping()
	public Map<String, Object> getBlogs(@RequestParam(value = "state", defaultValue = "1") Integer state,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "count", defaultValue = "20") Integer count,
			@RequestParam(value = "keywords", defaultValue = "") String keywords,
			@RequestParam(value = "userId", defaultValue = "-1") Long userId) {
		return blogService.getBlogByState(state, page, count, keywords, userId);
	}

	@PostMapping()
	public BlogDTO addNewBlog(@RequestBody BlogCreationDTO blogCreationDTO) {
		return blogService.saveBlog(blogCreationDTO);
	}

	@PostMapping("/upload-image")
	public ImageData uploadImg(HttpServletRequest req, MultipartFile image) throws Exception {
		if (image == null) {
			throw new Exception();
		}
		StringBuilder url = new StringBuilder();
		String filePath = "/blogimg/" + sdf.format(new Date());
		String imgFolderPath = req.getServletContext().getRealPath(filePath);
		File imgFolder = new File(imgFolderPath);
		if (!imgFolder.exists()) {
			imgFolder.mkdirs();
		}
		url.append(req.getScheme()).append("://").append(req.getServerName()).append(":").append(req.getServerPort())
				.append(req.getContextPath()).append(filePath);
		String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");
		IOUtils.copy(image.getInputStream(), new FileOutputStream(new File(imgFolder, imgName)));
		url.append("/").append(imgName);
		return new ImageData(url.toString());
	}

	@GetMapping("/{id}")
	public BlogDTO getBlogById(@PathVariable Long id) {
		return blogService.getBlogById(id);
	}

	@PutMapping()
	public void updateBlogsState(@RequestBody BlogIdsState blogIdsState) {
		List<Long> ids = blogIdsState.getIds();
		Integer state = blogIdsState.getState();
		blogService.updateBlogsState(ids, state);
	}

	@DeleteMapping()
	public void deleteBlogs(@RequestParam List<Long> ids) {
		blogService.deleteBlogs(ids);
	}

	@PutMapping("/restore")
	public void restoreArticle(@RequestBody List<Long> ids) {
		blogService.restoreBlogs(ids);
	}
}
