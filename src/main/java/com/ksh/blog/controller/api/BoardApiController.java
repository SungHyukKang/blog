package com.ksh.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ksh.blog.service.*;
import com.ksh.blog.config.auth.PrincipalDetail;
import com.ksh.blog.dto.ResponseDto;
import com.ksh.blog.model.Board;
import com.ksh.blog.model.RoleType;
import com.ksh.blog.model.User;

@RestController
public class BoardApiController {
	
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board , @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.글쓰기(board,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);//자바 오브젝트를 
	}
	
}


