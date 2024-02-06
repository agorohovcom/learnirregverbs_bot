package com.agorohov.learnirregverbs_bot.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "users")
@Data
@Accessors(chain = true)
public class User {
    
    @Id
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "user_name")
    private String userName;
    
    @Column(name = "first_message_at")
    private Timestamp firstMessageAt;

    @Column(name = "last_message_at")
    private Timestamp lastMessageAt;
    
    @Column(name = "is_deleted")
    private Boolean isDeleled = false;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<LearningStatistics> statistics;
}
