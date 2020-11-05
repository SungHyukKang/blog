package com.ksh.blog.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksh.blog.dto.ReplySaveRequestDto;
import com.ksh.blog.model.Board;
import com.ksh.blog.model.Reply;
import com.ksh.blog.model.RoleType;
import com.ksh.blog.model.User;
import com.ksh.blog.repository.BoardRepository;
import com.ksh.blog.repository.ReplyRepository;
import com.ksh.blog.repository.UserRepository;





//스프링이 컴포넌트 스캔을 통하여 Bead에 등록해줌 , IoC 해준다.
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void 글쓰기(Board board,User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly=true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}
	
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
		.orElseThrow(()->{
			return new IllegalArgumentException("글 상세보기 실패 아이디를 찾을 수 없습니다."+id);
		});
		
	}
	
	
	
	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패 아이디를 찾을 수 없습니다."+id);
				}); //영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		//해당 함수로 종료시에 (Service가 종료될 때) 트랜잭션이 종료된다 . 이때 더티체킹이 일어난다-> 자동 업데이트 ,DB쪽으로 flush.
	}
	
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
		int result =replyRepository.mSave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(),replySaveRequestDto.getContent());
		System.out.println("BoardService: "+result);
	}

	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}
	
	
}
