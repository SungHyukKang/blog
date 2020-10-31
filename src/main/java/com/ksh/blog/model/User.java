package com.ksh.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity //User 클래스가 MySQL에 테이블이 생성된다.
@DynamicInsert//insert 시 null인 필드를 제외시켜준다.
public class User {
	
	@Id//pk
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전량을 따라간다.
	private int id; //auto_increment
	@Column(nullable = false ,length= 30,unique=true)
	private String username; //아이디
	
	@Column(nullable = false ,length= 100)
	private String password; 
	
	@Column(nullable = false ,length= 50)
	private String email;
	
	@Column(nullable = false ,length= 10)
	private String name;
	//@ColumnDefault("user")
	//DB는 RoleType 이라는 게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. //ADMIN,USER
	
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;
	
}
