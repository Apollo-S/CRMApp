package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.Post;
import crmapp.app.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController extends BaseController<Post, PostService> {
	
	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<Post>> getAllPosts() {
		return super.getAllEntities();
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Post> getPostById(@PathVariable(PARAM_ID) int id) {
		return super.getEntityBy(id);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<Post> addPost(@RequestBody Post post) {
		return super.addEntity(post);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> updatePost(@PathVariable(PARAM_ID) int id, @RequestBody Post post) {
		return super.updateEntity(post);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deletePost(@PathVariable(PARAM_ID) int id) {
		return super.deleteEntityById(id);
	}

}
