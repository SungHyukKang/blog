package com.ksh.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ksh.blog.model.Board;
import com.ksh.blog.repository.BoardRepository;

@RestController
public class ReplyControllerTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/test/board/{id}")
		public Board getBoard(@PathVariable int id) {
		return boardRepository.findById(id).get();
	}
}
