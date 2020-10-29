package com.ksh.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술 
@Entity //User 클래스가 MySQL에 테이블이 생성된다.
public class User {
	
	@Id//pk
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전량을 따라간다.
	private int id; //auto_increment
	@Column(nullable = false ,length= 30)
	private String username; //아이디
	
	@Column(nullable = false ,length= 100)
	private String password; 
	
	@Column(nullable = false ,length= 50)
	private String email;
	
	@Column(nullable = false ,length= 10)
	private String name;
	
	@ColumnDefault("'user'")
	private String role; // Enum을 쓰는게 좋다. //admin,user,manager
	
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;
	
}
