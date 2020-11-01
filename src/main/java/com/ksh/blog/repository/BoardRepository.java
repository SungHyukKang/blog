package com.ksh.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksh.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
