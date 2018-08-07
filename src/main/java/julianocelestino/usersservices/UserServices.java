package julianocelestino.usersservices;

import julianocelestino.usersservices.domain.User;
import julianocelestino.usersservices.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;

@RestController
@RequestMapping("/users")
public class UserServices {

	@Autowired
	private UserRepository repository;

	@GetMapping
	public ResponseEntity get() {
		return ResponseEntity.status(HttpStatus.OK).body("Aqui Virao os Usuarios");
	}


	@PostMapping
	public ResponseEntity<?> post (@RequestBody User user) {
		user.setEnabled(true);
		user.setRegisterDate(new Date());
		repository.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping
	public ResponseEntity<?> deleteAll() {
		repository.deleteAll();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}
