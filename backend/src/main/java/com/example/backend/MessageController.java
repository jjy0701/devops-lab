package com.example.backend;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MessageController {

    private final JdbcTemplate jdbcTemplate;

    public MessageController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/messages")
    public List<Map<String, Object>> getMessages() {
        String sql = "SELECT id, content FROM messages";
        return jdbcTemplate.queryForList(sql);
    }
}
