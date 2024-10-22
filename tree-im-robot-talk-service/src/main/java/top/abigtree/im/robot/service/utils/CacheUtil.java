package top.abigtree.im.robot.service.utils;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/10/22
 */
public class CacheUtil {

    public static <K, V> Cache<K, V> buildCacheWithCapacity(int capacity) {
        return Caffeine.newBuilder()
                .maximumSize(capacity)
                .build();
    }
}
