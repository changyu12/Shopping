package com.example.shopping.notice;

import java.time.LocalDateTime;
import java.util.List;

import com.example.shopping.comment.Comment;
import com.example.shopping.member.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
		
	private String content;
	
	private String writer;
	
	private String category;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
	private List<Comment> commentList;
	
	//추천  조아요
	@ManyToMany
	List<Member> voter;
	
	//비추  시러요
	@ManyToMany
	List<Member> hater;
	
	//게시물 조회수
	@ManyToMany
	List<Member> hitter;
}
