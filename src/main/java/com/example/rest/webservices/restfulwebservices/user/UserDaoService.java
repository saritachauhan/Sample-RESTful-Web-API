package com.example.rest.webservices.restfulwebservices.user;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;



@Component
public class UserDaoService {
	
	
	public static int userscount=0;
	
	private static List<User> users= new ArrayList<>();
	
	static {
		users.add(new User(++userscount,"Adam",LocalDate.now().minusYears(250)));
		users.add(new User(++userscount,"Eve",LocalDate.now().minusYears(20)));
		users.add(new User(++userscount,"Nova",LocalDate.now().minusYears(20)));
	}
	
	
	public List<User> findAll(){
		return users;
	}
	
	
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId()==(id); 
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId()==(id); 
		users.removeIf(predicate);
		
	}
	
	public User save(User user) {
		user.setId(++userscount);
		users.add(user);
		return user;
		
	}

}
