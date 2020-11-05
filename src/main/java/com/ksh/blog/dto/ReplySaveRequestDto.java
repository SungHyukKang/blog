package com.ksh.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplySaveRequestDto {
	private int userId;
	private int boardId; 
	private String content;
}
