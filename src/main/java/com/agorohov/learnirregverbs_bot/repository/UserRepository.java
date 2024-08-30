package com.agorohov.learnirregverbs_bot.repository;

import com.agorohov.learnirregverbs_bot.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Override
    Optional<UserEntity> findById(Long id);

    @Override
    boolean existsById(Long id);

    @Override
    UserEntity saveAndFlush(UserEntity entity);
    
    @Override
    long count();
}
