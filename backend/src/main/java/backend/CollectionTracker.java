package backend;

import backend.api.UserController;
import backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CollectionTracker {
	public static void main(String[] args) {

		SpringApplication.run(CollectionTracker.class, args);
	}

}
