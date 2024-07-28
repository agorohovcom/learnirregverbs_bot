package com.agorohov.learnirregverbs_bot.service;

import com.agorohov.learnirregverbs_bot.entity.User;
import com.agorohov.learnirregverbs_bot.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    @Override
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
    
    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
