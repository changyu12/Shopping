package com.example.shopping.chatreply;

public interface ChatreplyService {
    
    void create(Integer id, String content);
    
    Chatreply readdetail(Integer id);
}
