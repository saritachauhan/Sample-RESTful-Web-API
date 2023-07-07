package com.example.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.example.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;


@RestController
public class UserResourceJpa {
	
	
	private UserRepository userrepository;
	private PostRepository postrepository;

	
	public UserResourceJpa(UserRepository repository, PostRepository postrepository, UserRepository userrepository) {
		
		this.userrepository=userrepository;
		this.postrepository=postrepository;
	}

	// GET /users
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userrepository.findAll();
	}
	
	// GET /users
	@GetMapping("/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		
				Optional<User> user=userrepository.findById(id);
				if(user.isEmpty())
					throw new UserNotFoundException("id"+id);
					return user.get();
	}
	
	

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		userrepository.save(user);
		User savedUser=userrepository.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
		
		
		
	}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
				userrepository.deleteById(id);
					
	}
	
	// Post /users/
		@GetMapping("/jpa/users/{id}/posts")
		public List<Post> retrievePostsForUser(@PathVariable int id) {
			Optional<User> user=userrepository.findById(id);
			if(user.isEmpty())
				throw new UserNotFoundException("id"+id);
			return user.get().getPosts();
		}
	
		// Post /users/posts
				@PostMapping("/jpa/users/{id}/posts")
				public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
					Optional<User> user=userrepository.findById(id);
					if(user.isEmpty())
						throw new UserNotFoundException("id"+id);
					post.setUser(user.get());
					Post savedPost=postrepository.save(post);
					URI location=ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(savedPost.getId())
							.toUri();
					return ResponseEntity.created(location).build();
				}	
		

}
