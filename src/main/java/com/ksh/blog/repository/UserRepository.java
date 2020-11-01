package com.ksh.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ksh.blog.model.User;

// DAO 
// Spring IoC에서 객체를 가지고 있나? 자동으로 Bean으로 등록됨 .
//@Repository 생략 가능 .. . .
public interface UserRepository extends JpaRepository<User, Integer> {
	//SElect *FROM user WHERE username= 1?;
	Optional<User> findByUsername(String username);
}
// JPA Naming 쿼리
// SELECT * FROM user WHERE username =? AND password =?;
//	User findByUsernameAndPassword(String username, String password);

//	@Query(value="SELECT * FROM user WHERE username =?1 AND password =?2",nativeQuery = true)
//	User login(String username ,String password);