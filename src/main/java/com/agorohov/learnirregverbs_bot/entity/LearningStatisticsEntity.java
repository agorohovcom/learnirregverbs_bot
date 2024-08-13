package com.agorohov.learnirregverbs_bot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "learning_statistics")
@Data
@Accessors(chain = true)
public class LearningStatisticsEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "attempts")
    private Integer attempts = 0;
        
    @Column(name = "correct_series")
    private Integer correctSeries = 0;
    
    @Column(name = "rank")
    private Short rank = 0;
    
    @ManyToOne
    @JoinColumn(name = "user_chat_id", nullable = false)
    private UserEntity user;
    
    @ManyToOne
    @JoinColumn(name = "verb_id", nullable = false)
    private VerbEntity verb;
}
