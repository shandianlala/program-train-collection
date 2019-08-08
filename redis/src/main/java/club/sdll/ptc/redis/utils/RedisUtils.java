package club.sdll.ptc.redis.utils;

import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * decription
 *
 * @author chengxiwang
 * @version v0.1
 * @data 2018年08月22日 14:59
 */
public class RedisUtils {

    private static JedisPool pool;

    /**
     * 默认保存时间 30天
     */
    private final static Integer DEFAULT_TIME_OUT = 60 * 60 * 24 * 30;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("redis");
        int db = Integer.valueOf(bundle.getString("redis.db"));
        pool = RedisPoolUtils.getPool(db);
    }

    /**
     * 存储键值对到redis，指定失效时间 timeOuts 秒
     * @param key 键
     * @param value 值
     * @param timeOuts 超时时间
     */
    public static void set(String key, String value, Integer timeOuts) {
        RedisPoolUtils.set(pool, key, value, timeOuts);
    }

    /**
     * 存储键值对到redis，默认失效时间30分钟
     * @param key 键
     * @param value 值
     */
    public static void set(String key, String value) {
        RedisPoolUtils.set(pool, key, value, DEFAULT_TIME_OUT);
    }

    /**
     * 根据键获取存储在redis的值
     * @param key
     * @return
     */
    public static String get(String key) {
        return RedisPoolUtils.get(pool, key);
    }

    public static void del(String key) {
        RedisPoolUtils.del(pool, key);
    }

    public static void rpush(String key, String... string) {
        RedisPoolUtils.rpush(pool, key, string);
    }

    public static String rpop(String key) {
        return RedisPoolUtils.rpop(pool, key);
    }


    public static String getRedisCacheKey(String key) {
        return key + "_cache";
    }

    /**
     * 无序set中添加成员，
     * @param key
     * @param value
     */
    public static void sadd(String key, String value) {
        Integer timeOut = DEFAULT_TIME_OUT;
        RedisPoolUtils.sadd(pool, key, value, timeOut);
    }

    /**
     * 无序set中添加成员
     * @param key
     * @param value
     * @param timeOut
     */
    public static void sadd(String key, String value, Integer timeOut) {
        if (timeOut == null || timeOut < 0) {
            return;
        }
        RedisPoolUtils.sadd(pool, key, value, timeOut);
    }

    /**
     * 无序set中删除 成员value
     * @param key
     * @param value
     */
    public static void srem(String key, String value) {
        RedisPoolUtils.srem(pool, key, value);
    }

    /**
     * 获取 setKey 视图所有成员
     * @param setKey
     * @return
     */
    public static Set<String> smembers(String setKey) {
        Set<String> set = RedisPoolUtils.smembers(pool, setKey);
        return set;
    }

    /**
     * 判断value 是否在 set 视图中
     * @param set
     * @param value
     * @return
     */
    public static boolean sismember(String set, String value) {
        boolean flag = RedisPoolUtils.sismember(pool, set, value);
        return flag;
    }

    /**
     * hash散列中添加数据
     * @param hashKey
     * @param hashValue
     * @return 数据添加状态
     */
    public static String hmset(String hashKey, Map<String, String> hashValue) {
        String status = RedisPoolUtils.hmset(pool, hashKey, hashValue);
        return status;
    }

    /**
     * hash散列中添加数据
     * @param hashKey
     * @param field
     */
    public static String hget(String hashKey, String field) {
        String value = RedisPoolUtils.hget(pool, hashKey, field);
        return value;
    }

    /**
     * 获取hash散列 所有字段的值
     * @param hashKey
     */
    public static Map<String, String> hgetAll(String hashKey) {
        Map<String, String> hashValue = RedisPoolUtils.hgetAll(pool, hashKey);
        return hashValue;
    }


    /**
     * 增加地理位置
     * @param key
     * @param longitude
     * @param latitude
     * @param member
     * @return
     */
    public static long geoAdd(String key, double longitude, double latitude, String member) {
        long flag = RedisPoolUtils.geoAdd(pool, key, longitude, latitude, member);
        return flag;
    }

    /**
     * 以给定的经纬度为中心， 返回键包含的位置元素当中， 与中心的距离不超过给定最大距离的所有位置元素。
     * @param geoKey
     * @param longitude
     * @param latitude
     * @param radius
     * @return
     */
    public static List<GeoRadiusResponse> geoRadius(String geoKey,
                                                 double longitude, double latitude, double radius,
                                                 GeoUnit geoUnit, GeoRadiusParam geoRadiusParam) {
        List<GeoRadiusResponse> list = RedisPoolUtils.geoRadius(pool, geoKey, longitude, latitude, radius,
                geoUnit, geoRadiusParam);
        return list;
    }


}
