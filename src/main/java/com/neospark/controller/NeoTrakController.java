package com.neospark.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neospark.model.UserVO;
import com.neospark.service.NeosparkService;

@RestController
public class NeoTrakController {

	@Autowired
	private NeosparkService neosparkService;

	@GetMapping("/get-users")
	public List getUsers() {
		return neosparkService.getListOfUsers();
	}

	@PostMapping("/add-user")
	public UUID addUser(@RequestBody UserVO userVO) {
		return neosparkService.addUser(userVO).getId();
	}

	@PutMapping("/update-user/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable UUID userId, @RequestBody UserVO userVO) {
		int count = neosparkService.updateUser(userId, userVO);
		if(count == 0) {
			return new ResponseEntity<String>("Updated Failed as no records found", HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<String>("Updated successfully", HttpStatus.ACCEPTED);
	}

}
