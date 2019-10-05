package com.assignment.shoppingApp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.assignment.shoppingApp.dto.UserDTO;
import java.lang.String;


@Repository
public interface UserRepository extends JpaRepository<UserDTO, Integer> {
	
	UserDTO findByEmailAndPassword(String email,String password);
	UserDTO findByEmail(String email);
	UserDTO findByAuthtoken(String authtoken);
}
