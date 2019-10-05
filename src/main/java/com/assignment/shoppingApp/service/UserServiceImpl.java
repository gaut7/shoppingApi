package com.assignment.shoppingApp.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.assignment.shoppingApp.dto.UserDTO;
import com.assignment.shoppingApp.exception.AssignmentException;
import com.assignment.shoppingApp.model.UserModel;
import com.assignment.shoppingApp.repository.UserRepository;
import com.assignment.shoppingApp.utils.TokenGeneratorUtil;


@Service
@PropertySources(value = { @PropertySource("classpath:messages.properties"),
		@PropertySource("classpath:exception.properties"),
		@PropertySource("classpath:application.properties") })
public class UserServiceImpl implements UserService, EnvironmentAware {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
	private Mapper mapper = new DozerBeanMapper();
	
	
	@Autowired
	private UserRepository userRepository;
	
	private static Environment environment;
	
	public String getProperty(String key) {
		return environment.getProperty(key);
	}

	@SuppressWarnings("static-access")
	@Override
	public void setEnvironment(final Environment environment) {
		this.environment = environment;

	}

	@Override
	public UserModel createUser(UserModel user) throws AssignmentException {
		try {
			UserDTO userDTO = userRepository.findByEmail(user.getEmail());
			if(userDTO!=null)
			{
				throw new AssignmentException(environment.getProperty("email.already.registered"));
			}
			userDTO = mapper.map(user, UserDTO.class);
			String authToken = TokenGeneratorUtil.generateToken(user.getEmail());
			userDTO.setAuthtoken(authToken);
	        userRepository.save(userDTO);
		} catch (AssignmentException assignmentException) {
			throw assignmentException;
		}catch (Exception exception) {
			LOGGER.error(exception);
			throw new AssignmentException("Something went wrong");
		}
		return login(user);
	}

	@Override
	public UserModel login(UserModel user) throws AssignmentException {
		
		try {
			UserDTO userDTO = userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());
			if(userDTO != null)
			{
				user = mapper.map(userDTO, UserModel.class);
				user.setPassword(null);
			}else {
				throw new AssignmentException(environment.getProperty(""));
			}	
		} catch (Exception e) {
			LOGGER.error(e);
			throw new AssignmentException("Something went wrong");
		}
		return user;
	}

	@Override
	public void logout(HttpServletRequest request) throws AssignmentException {
		try {
			UserModel user = (UserModel) request.getAttribute("user");
			UserDTO userDTO = userRepository.findByAuthtoken(user.getAuthtoken());
			if(userDTO != null)
			{
				userDTO.setAuthtoken(TokenGeneratorUtil.generateToken("ahssdf346254rfvewrge"));
			}else {
				throw new AssignmentException("Unauthenticated User");
			}
		} catch (AssignmentException assignmentException) {
			throw assignmentException;
		}catch (Exception exception) {
			LOGGER.error(exception);
			throw new AssignmentException("Something went wrong");
		}
		
	}

	@Override
	public UserModel getUserByAuthToken(String authToken) throws AssignmentException {
		UserModel user;
		try {
			UserDTO userDTO = userRepository.findByAuthtoken(authToken);
			if(userDTO != null)
			{
				user = mapper.map(userDTO, UserModel.class);
			}else {
				throw new AssignmentException("Unauthenticated User");
			}
		} catch (AssignmentException assignmentException) {
			throw assignmentException;
		}catch (Exception exception) {
			LOGGER.error(exception);
			throw new AssignmentException("Something went wrong");
		}
		return user;
	}	
	
}
