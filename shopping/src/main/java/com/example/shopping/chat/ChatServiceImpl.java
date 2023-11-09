package com.example.shopping.chat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



@Service
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public void create(Chat chat) {
		
		chat.setCreateDate(LocalDateTime.now());
		chatRepository.save(chat);

	}

	@Override
	public List<Chat> readlist() {
		return chatRepository.findAll();
	}

	@Override
	public Chat readdetailusername(String username) {
		Optional<Chat> on = chatRepository.findByUsername(username);
		
		return on.get();
	}
	
	@Override
	public Chat readdetail(Integer id) {
		Optional<Chat> on = chatRepository.findById(id);
		
		return on.get();
	}

	@Override
	public void update(Chat chat) {
		
		chat.setCreateDate(LocalDateTime.now());
		chatRepository.save(chat);

	}

	@Override
	public void delete(Integer id) {
		Optional<Chat> on = chatRepository.findById(id);
		
		chatRepository.delete(on.get());

	}
	
	public Page<Chat> getList(int page) {
		//게시물의 나열 순서가 최근 자료가 위로 올라오게 즉 역순으로 설정
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return chatRepository.findAll(pageable);
	}

}
