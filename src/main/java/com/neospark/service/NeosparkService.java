package com.neospark.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neospark.data.User;
import com.neospark.data.UserRepo;
import com.neospark.model.UserVO;

import jakarta.transaction.Transactional;

@Component
public class NeosparkService {

	@Autowired
	private UserRepo userRepo;

	public List getListOfUsers() {
		return mapEntityToModel(userRepo.findAll());
	}

	private List mapEntityToModel(List<User> listUser) {
		return listUser.stream().map(s -> UserVO.builder().firstName(s.getFirstName()).lastName(s.getLastName())
				.id(s.getId()).tenant(s.getTenant()).build()).collect(Collectors.toList());

	}

	public User addUser(UserVO userVO) {

		return userRepo.save(User.builder().firstName(userVO.getFirstName()).lastName(userVO.getLastName()).build());
	}

//	public void updateUser(UUID id, UserVO userVO) {
//		User user = userRepo.getReferenceById(id);
//		user.setFirstName(userVO.getFirstName());
//		user.setLastName(userVO.getLastName());
//		userRepo.save(user);
//	}

	@Transactional
	public void updateUser(UUID id, UserVO userVO) {
		userRepo.updateUser(id, userVO.getFirstName(), userVO.getLastName());
	}

}
