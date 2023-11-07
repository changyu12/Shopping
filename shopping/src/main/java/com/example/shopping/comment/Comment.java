package com.example.shopping.comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.shopping.member.Member;
import com.example.shopping.notice.Notice;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String content;

	private LocalDateTime createDate;
	
	@ManyToOne
	private Notice notice;

	@ManyToMany
	Set<Member> voter;
	
	
}
