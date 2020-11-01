package com.ksh.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ksh.blog.service.*;
import com.ksh.blog.dto.ResponseDto;
import com.ksh.blog.model.RoleType;
import com.ksh.blog.model.User;

@RestController
public class UserApiController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController 호출");
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);//자바 오브젝트를 
	}
	
}


