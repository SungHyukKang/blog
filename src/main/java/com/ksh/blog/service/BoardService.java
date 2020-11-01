package com.ksh.blog.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksh.blog.model.Board;
import com.ksh.blog.model.RoleType;
import com.ksh.blog.model.User;
import com.ksh.blog.repository.BoardRepository;
import com.ksh.blog.repository.UserRepository;





//스프링이 컴포넌트 스캔을 통하여 Bead에 등록해줌 , IoC 해준다.
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void 글쓰기(Board board,User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
}
