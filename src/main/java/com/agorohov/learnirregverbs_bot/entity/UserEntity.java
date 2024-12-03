package com.agorohov.learnirregverbs_bot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Accessors(chain = true)
public class UserEntity {
    
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
    private Boolean isDeleted = false;
    
    // если orphanRemoval = true, то приходится сразу инициализировать statistics
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL/*, orphanRemoval = true*/)
    private List<LearningStatisticsEntity> statistics/* = new ArrayList<>()*/;
}
