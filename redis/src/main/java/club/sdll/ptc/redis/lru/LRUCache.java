package club.sdll.ptc.redis.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基于jdk的数据结构实现LRU缓存
 *
 * @author shandianlala@gmail.com
 * @version 1.0
 * @date 2021-04-08 07:39
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private int CACHE_SIZE = 16;

    // 这里是传递进来最多能缓存的数据
    public LRUCache(int initialCapacity) {
        /**
         * 设置hashMap 的初始大小，同时最后一个true指的是让linkedHashMap按照顺序来进行排序，
         * 最近访问的放在头，最老访问的放在尾。
         */
        super((int) Math.ceil(initialCapacity / 0.75), 0.75f, true);
        CACHE_SIZE = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        /**
         * 当Map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据。
         */
        return size() > CACHE_SIZE;
    }
}
