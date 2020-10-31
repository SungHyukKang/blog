package com.ksh.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ksh.blog.model.RoleType;
import com.ksh.blog.model.User;
import com.ksh.blog.repository.UserRepository;


@RestController
public class DummyControllerTest {
	
	@Autowired //이미 메모리에 떠있음 -> 의존성 주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id ) {
		try {
			userRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return id+  " 삭제완료";
	}
	
	//save 함수는 id를 전달하지 않으면 insert를 해주고
	//id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	//id에 대한 데이터가 없으면 insert를 해준다.
	@Transactional//함수가 시작될 때 트랜잭션 시작 , 종료 시 트랜잭션 종료와 동시에 commit
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id ,@RequestBody User requestUser) {
		System.out.println("id " +id);
		System.out.println("password " +requestUser.getPassword());
		System.out.println("email " +requestUser.getEmail());
		
		User user =userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		user.setUsername(requestUser.getUsername());
		//userRepository.save(user);
		//더티 체킹
		return user;
		
	}
	
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll(); 
	}
	
	//한 페이지당 2건의 데이터 리턴
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2,sort="id",direction=Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser =userRepository.findAll(pageable);
		//분기처리는 알아서 .
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	//{id} 주소로 파라미터 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/ 4를 찾으면 내가 db에서 못 찾는다 
		//그럼 return null -> 프로그램에 문제 발생
		//따라서 Optional로 user객체를 감싸서 가져옴. 
		//null인지 아닌지 판단.
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다 . id:"+id);
			}
		});
		//요청 : 웹 브라우저
		//user 객체 = 자바 오브젝트 
		//변환 ( 웹 브라우저가 이해할 수 있는 데이터 -> json(Gson 라이브러리)
		//스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		//만약 자바 obj를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출
		//User 오브젝트를 json으로 변환하여 웹 브라우저로 전달.
		return user;
	}
	
	
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
