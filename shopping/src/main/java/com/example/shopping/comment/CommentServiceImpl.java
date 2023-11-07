package com.example.shopping.comment;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.member.MemberService;
import com.example.shopping.notice.Notice;
import com.example.shopping.notice.NoticeService;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MemberService memberService;

    @Override
    public void create(Integer id, String content) {
        
        Notice notice = noticeService.readdetail(id);

        Comment c = new Comment();
            c.setContent(content);
            c.setNotice(notice);
            c.setCreateDate(LocalDateTime.now());
            commentRepository.save(c);

    }

   
	@Override
	public Comment readdetail(Integer id) {
		Optional<Comment> oc = commentRepository.findById(id);
		return oc.get();
	}
    
}
