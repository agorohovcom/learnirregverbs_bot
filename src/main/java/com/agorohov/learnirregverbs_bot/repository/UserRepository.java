package com.agorohov.learnirregverbs_bot.repository;

import com.agorohov.learnirregverbs_bot.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
}
