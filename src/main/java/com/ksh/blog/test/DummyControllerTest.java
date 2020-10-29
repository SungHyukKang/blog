package com.ksh.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ksh.blog.model.RoleType;
import com.ksh.blog.model.User;
import com.ksh.blog.repository.UserRepository;


@RestController
public class DummyControllerTest {
	
	@Autowired //이미 메모리에 떠있음 -> 의존성 주입(DI)
	private UserRepository userRepository;
	
	
	@PostMapping("/dummy/join")
	public String join(@RequestBody User user) {
		System.out.println("username :"+user.getUsername());
		System.out.println("password :"+user.getPassword());
		System.out.println("email :"+user.getEmail());
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
