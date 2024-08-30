package com.agorohov.learnirregverbs_bot.repository;

import com.agorohov.learnirregverbs_bot.entity.VerbEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface VerbRepository extends JpaRepository<VerbEntity, Integer> {

    @Override
    Optional<VerbEntity> findById(Integer id);

    @Override
    long count();
}
