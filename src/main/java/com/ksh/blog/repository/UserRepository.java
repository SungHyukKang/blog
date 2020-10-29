package com.ksh.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksh.blog.model.User;

// DAO 
// Spring IoC에서 객체를 가지고 있나? 자동으로 Bean으로 등록됨 .
//@Repository 생략 가능 .. . .
public interface UserRepository extends JpaRepository<User, Integer>{

}