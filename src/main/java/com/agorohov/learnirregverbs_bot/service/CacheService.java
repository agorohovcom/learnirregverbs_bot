package com.agorohov.learnirregverbs_bot.service;

public interface CacheService {

    void printCacheContent(String cacheName);

    int getCacheSize(String cacheName);

    void clearCache(String cacheName);
}
