package com.agorohov.learnirregverbs_bot.service.implementation;

import com.agorohov.learnirregverbs_bot.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheServiceImpl implements CacheService {

    private final CacheManager cacheManager;

//    public void printCacheContent(String cacheName) {
//        Cache cache = cacheManager.getCache(cacheName);
//        if (cache != null) {
//            Object nativeCache = cache.getNativeCache();
//
//            // Проверяем, является ли кэш ConcurrentMapCache
//            if (nativeCache instanceof Map) {
//                ((Map<?, ?>) nativeCache).forEach((key, value) -> {
//                    System.out.println("Key: " + key + ", Value: " + value);
//                });
//            } else {
//                System.out.println("Кэш не поддерживает итерацию через forEach.");
//            }
//        } else {
//            System.out.println("Кэш с именем " + cacheName + " не найден.");
//        }
//    }

    public int getCacheSize(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            Object nativeCache = cache.getNativeCache();

            // Проверяем, является ли кэш ConcurrentMapCache
            if (nativeCache instanceof Map) {
                return ((Map<?, ?>) nativeCache).size();
            } else {
                throw new UnsupportedOperationException("Кэш не поддерживает получение размера.");
            }
        } else {
            throw new IllegalArgumentException("Кэш с именем " + cacheName + " не найден.");
        }
    }

    public void clearCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.clear();
            log.info("Кэш с именем {} очищен.", cacheName);
        } else {
            throw new IllegalArgumentException("Кэш с именем " + cacheName + " не найден.");
        }
    }
}
