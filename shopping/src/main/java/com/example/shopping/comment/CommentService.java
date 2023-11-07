package com.example.shopping.comment;

public interface CommentService {
    
    void create(Integer id, String content);
    
    Comment readdetail(Integer id);
}
