package crmapp.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crmapp.app.entities.Post;
import crmapp.app.repositories.PostRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/posts")
public class PostController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<Post>> getAllPosts() {
		List<Post> posts = postRepository.findAll();
		if (posts.size() == 0) {
			return new ResponseEntity<List<Post>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Post> getPostById(@PathVariable(PARAM_ID) int id) {
		Post post = postRepository.findOne(id);
		if (post == null) {
			return new ResponseEntity<Post>(post, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<Post> addPost(@RequestBody Post post) {
		post.setVersion(0);
		post = postRepository.save(post);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Post>(post, header, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> updatePost(@PathVariable(PARAM_ID) int id, @RequestBody Post post) {
		logger.info("<==/////////// Entering to the updatePost() method ... ///////////==>");
		post.setId(id);
		logger.info("<==/////////// Id is setted to " + post.getId() + "///////////==>");
		int actualVersionNumber = postRepository.getOne(id).getVersion();
		post.setVersion(actualVersionNumber);
		logger.info("<==/////////// Printing post: " + post + "///////////==>");
		post = postRepository.save(post);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deletePost(@PathVariable(PARAM_ID) int id) {
		postRepository.delete(id);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
