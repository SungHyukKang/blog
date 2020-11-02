<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form>
	<input type="hidden" id ="id" value="${principal.user.id }">
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text"  value ="${principal.user.username }"class="form-control" placeholder="Enter Username" id="username" readonly="readonly">
		</div>

		<div class="form-group">
			<label for="name">Name</label> 
			<input type="text" class="form-control" value="${principal.user.name }"placeholder="Enter name" id="name" readonly="readonly">
		</div>
		
		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password"  class="form-control" placeholder="Enter password" id="password">
		</div>
		
		<div class="form-group">
			<label for="email">Email</label> 
			<input type="email" value="${principal.user.email }"class="form-control" placeholder="Enter email" id="email">
		</div>
		
		<div class="form-group form-check"></div>
	</form>
	
		<button id="btn-update" class="btn btn-primary">회원수정완료</button>

</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
