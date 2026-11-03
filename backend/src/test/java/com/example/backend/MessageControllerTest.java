package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MessageControllerTest {

    @Test
    void getMessagesReturnsDatabaseResults() {

        // 가짜 JdbcTemplate 생성
        JdbcTemplate jdbc = mock(JdbcTemplate.class);

        // DB가 다음 데이터를 반환한다고 가정
        var rows = List.<Map<String, Object>>of(
            Map.of("id", 1L, "content", "Hello Spring Boot!")
        );

        when(jdbc.queryForList(
            "SELECT id, content FROM messages"
        )).thenReturn(rows);

        // 컨트롤러 실행
        MessageController controller = new MessageController(jdbc);
        var result = controller.getMessages();

        // 결과 검증
        assertEquals(1, result.size());
        assertEquals("Hello Spring Boot!", result.get(0).get("content"));
    }
}
