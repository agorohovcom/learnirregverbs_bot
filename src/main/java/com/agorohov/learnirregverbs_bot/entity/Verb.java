package com.agorohov.learnirregverbs_bot.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "verbs")
@Data
@Accessors(chain = true)
public class Verb {
    
    @Id
    // автоген или вручную? сейчас добавляет из таблицы глаголов
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
//    private Integer id;
    private short id;

    @Column(name = "infinitive")
    private String infinitive;
    
    @Column(name = "past")
    private String past;
    
    @Column(name = "past_participle")
    private String pastParticiple;
    
    @Column(name = "translation")
    private String translation;
    
    @OneToMany(mappedBy = "verb", cascade = CascadeType.ALL)
    List<LearningStatistics> statistics;
}
