package com.agorohov.learnirregverbs_bot.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    // работает и без этого
//    @Bean
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager("verbs");
//    }
}
