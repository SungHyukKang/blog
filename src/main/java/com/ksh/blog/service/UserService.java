package com.ksh.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksh.blog.model.RoleType;
import com.ksh.blog.model.User;
import com.ksh.blog.repository.UserRepository;




//스프링이 컴포넌트 스캔을 통하여 Bead에 등록해줌 , IoC 해준다.
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setRole(RoleType.USER);
		user.setPassword(encPassword);
		userRepository.save(user);
	}
}
