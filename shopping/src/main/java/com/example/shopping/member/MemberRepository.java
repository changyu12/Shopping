package com.example.shopping.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

	Optional<Member>findByUsername(String username);

	Optional<Member> findById(Integer id);
	
}
