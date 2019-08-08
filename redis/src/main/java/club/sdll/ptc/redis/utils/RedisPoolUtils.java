package club.sdll.ptc.redis.utils;

import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.*;

/**
 * decription
 *
 * @author chengxiwang
 * @version v0.1
 * @data 2018年08月22日 15:00
 */
public class RedisPoolUtils {

    private static HashMap<Integer, JedisPool> poolMap = new HashMap<Integer, JedisPool>();

    /**
     * 初始化redisPool池
     * @param database
     * @return
     */
    private synchronized static JedisPool initPool(int database) {
        JedisPool pool = poolMap.get(database);
        if (pool != null) {
            return pool;
        }
        ResourceBundle bundle = ResourceBundle.getBundle("redis");
        if (bundle == null) {
            throw new IllegalArgumentException(
                    "[redis.properties] is not found!");
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.valueOf(bundle.getString("redis.maxIdle")));
        config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.testOnBorrow")));
        config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.testOnReturn")));
        pool = new JedisPool(config, bundle.getString("redis.ip"),
                Integer.valueOf(bundle.getString("redis.port")), 0,
                bundle.getString("redis.password"), database);

        poolMap.put(database, pool);
        return pool;
    }


    /**
     * 获得redisPool池
     * @param database
     * @return
     */
    public static JedisPool getPool(int database) {
        JedisPool pool = poolMap.get(database);
        if (pool != null) {
            return pool;
        }
        return RedisPoolUtils.initPool(database);
    }

    /**
     * 存储键值对到redis
     * @param pool reids池
     * @param key 键
     * @param value 值
     * @param timeOut 存储时间
     */
    public static void set(JedisPool pool, String key, String value, Integer timeOut) {
        Jedis jedis = pool.getResource();
        jedis.set(key, value);
        if (timeOut > 0) {
            jedis.expire(key, timeOut);
        }
        pool.returnResourceObject(jedis);
    }

    /**
     * 根据键获取存储在redis的值
     * @param pool reids池
     * @param key 键
     * @return
     */
    public static String get(JedisPool pool, String key) {
        Jedis jedis = pool.getResource();
        String result = jedis.get(key);
        pool.returnResourceObject(jedis);
        return result;
    }

    public static void del(JedisPool pool, String key) {
        Jedis jedis = pool.getResource();
        Long result = jedis.del(key);
        pool.returnResourceObject(jedis);
    }

    public static void rpush(JedisPool pool, String key, String... string) {
        Jedis jedis = pool.getResource();
        jedis.rpush(key, string);

        pool.returnResourceObject(jedis);
    }

    public static String rpop(JedisPool pool, String key) {
        Jedis jedis = pool.getResource();
        String result = jedis.rpop(key);

        pool.returnResourceObject(jedis);
        return result;
    }

    public static String lpop(JedisPool pool, String key) {
        Jedis jedis = pool.getResource();
        String result = jedis.lpop(key);
        pool.returnResourceObject(jedis);
        return result;
    }

    /**
     * 无序set中添加成员
     * @param pool
     * @param key
     * @param value
     * @param timeOut
     */
    public static void sadd(JedisPool pool, String key, String value, Integer timeOut) {
        Jedis jedis = pool.getResource();
        jedis.sadd(key, value);
        if (timeOut > 0) {
            jedis.expire(key, timeOut);
        }
        pool.returnResourceObject(jedis);
    }

    /**
     * 获取set视图所有成员
     * @param pool
     * @param setKey
     * @return
     */
    public static Set<String> smembers(JedisPool pool, String setKey) {
        Jedis jedis = pool.getResource();
        Set<String> set = jedis.smembers(setKey);
        return set;
    }

    /**
     * 判断 value 是否在 set 视图中
     * @param setKey set视图变量名
     * @param value
     * @return
     */
    public static boolean sismember(JedisPool pool, String setKey, String value) {
        Jedis jedis = pool.getResource();
        boolean flag = jedis.sismember(setKey, value);
        pool.returnResourceObject(jedis);
        jedis.close();
        return flag;
    }

    /**
     * 无序set中 删除成员 value
     * @param pool
     * @param setKey
     * @param value
     */
    public static void srem(JedisPool pool, String setKey, String value) {
        Jedis jedis = pool.getResource();
        jedis.srem(setKey, value);
        pool.returnResourceObject(jedis);
    }

    /**
     * hash散列中添加数据
     * @param pool
     * @param hashKey
     * @param hashValue
     * @return 数据添加状态
     */
    public static String hmset(JedisPool pool, String hashKey, Map<String, String> hashValue) {
        Jedis jedis = pool.getResource();
        String status = jedis.hmset(hashKey, hashValue);
        jedis.close();
        return status;
    }

    /**
     * 获取hash散列 字段field的值
     * @param pool
     * @param hashKey
     * @param field
     */
    public static String hget(JedisPool pool, String hashKey, String field) {
        Jedis jedis = pool.getResource();
        String value = jedis.hget(hashKey, field);
        jedis.close();
        return value;
    }

    /**
     * 获取hash散列 所有字段的值
     * @param pool
     * @param hashKey
     */
    public static Map<String, String> hgetAll(JedisPool pool, String hashKey) {
        Jedis jedis = pool.getResource();
        Map<String, String> hashValue = jedis.hgetAll(hashKey);
        jedis.close();
        return hashValue;
    }

    /**======== redis geo地理位置=======*/

    /**
     * 将指定的地理空间位置（经度、纬度、名称）添加到指定的key中。
     * @param pool
     * @param key
     * @param longitude
     * @param latitude
     * @param member
     * @return
     */
    public static long geoAdd(JedisPool pool, String key, double longitude, double latitude, String member) {
        Jedis jedis = pool.getResource();
        long flag = jedis.geoadd(key, longitude, latitude, member);
        jedis.close();
        return flag;
    }

    /**
     * 以给定的经纬度为中心， 返回键包含的位置元素当中， 与中心的距离不超过给定最大距离的所有位置元素。
     * @param pool
     * @param geoKey
     * @param longitude
     * @param latitude
     * @param radius
     * @return
     */
    public static List<GeoRadiusResponse> geoRadius(JedisPool pool, String geoKey,
                                                 double longitude, double latitude, double radius,
                                                 GeoUnit geoUnit, GeoRadiusParam geoRadiusParam) {
        Jedis jedis = pool.getResource();
        if (geoUnit == null) {
            geoUnit = GeoUnit.KM;
        }
        List<GeoRadiusResponse> list = null;
        if (geoRadiusParam == null) {
           list = jedis.georadius(geoKey, longitude, latitude, radius, geoUnit);
        }else {
            list = jedis.georadius(geoKey, longitude, latitude, radius,
                    geoUnit, geoRadiusParam);
        }
        jedis.close();
        return list;
    }

    public static void publishMessage(JedisPool pool, String channel, String message ) {
        Jedis jedis = pool.getResource();
        jedis.publish(channel, message);



    }




}
