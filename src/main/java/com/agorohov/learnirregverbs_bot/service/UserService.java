package com.agorohov.learnirregverbs_bot.service;

import com.agorohov.learnirregverbs_bot.entity.User;
import com.agorohov.learnirregverbs_bot.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
    
    public void save(User user) {
        userRepository.save(user);
    }
}
