package com.agorohov.learnirregverbs_bot.component.db_agency;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.entity.User;
import com.agorohov.learnirregverbs_bot.service.UserService;
import com.agorohov.learnirregverbs_bot.service.UserServiceImpl;
import java.sql.Timestamp;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;

@Slf4j
public class UserAgent {
    
    private final UserService userService;
    
    private User user;
    
    public UserAgent(UserService userService){
        this.userService = userService;
    }
    
    public void saveOrUpdateUser(UpdateHandler uh){
        Optional<User> optUser = userService.findById(uh.getUserId());
        
        if (optUser.isEmpty()) {
            user = new User()
                    .setChatId(uh.getUserId())
                    .setUserName(uh.getUserName())
                    .setFirstMessageAt(new Timestamp(uh.getUpdateWasReceivedAt()))
                    .setLastMessageAt(new Timestamp(uh.getUpdateWasReceivedAt()));
        } else {
            user = optUser
                    .get()
                    // на случай, если у прользователя поменялся userName
                    .setUserName(uh.getUserName())
                    .setLastMessageAt(new Timestamp(uh.getUpdateWasReceivedAt()));
        }

        try {
            userService.save(user);
            log.info("User with id = " + user.getChatId() + " was saved to DB");
        } catch (DataAccessException e) {
            log.error(e.getMessage());
        }
    }
    
}
