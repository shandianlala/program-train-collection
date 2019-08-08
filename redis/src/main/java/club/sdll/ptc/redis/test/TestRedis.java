package club.sdll.ptc.redis.test;

import club.sdll.ptc.redis.utils.RedisUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * decription
 *
 * @author chengxiwang
 * @version v0.1
 * @data 2018年08月22日 15:04
 */
public class TestRedis {

    public static void main(String[] args) {
        System.out.println("================================hash测试==================================");
        String hashKey = "test_redis_hash";
        Map<String, String> value = new HashMap<String, String>();
        value.put("name", "shandianlala");
        value.put("age", "26");
        value.put("class", "02班");
        value.put("sex", "男");
        String status = RedisUtils.hmset(hashKey, value);
        System.out.println("status==========" + status);
        String name = RedisUtils.hget(hashKey, "name");
        System.out.println("name==========" + name);
        Map<String, String> allValue = RedisUtils.hgetAll(hashKey);
        System.out.println("allValue==========" + allValue);

        System.out.println("================================geo地理位置==================================");
        String geoKey = "test_redis_geo";
//        double longitude = 120.27;
//        double latitude = 30.27;
//        String member = "1";
//
//        double longitude1 = 124.33;
//        double latitude1 = 30.88;
//        String member1 = "2";
//
//        double longitude2 = 116.44;
//        double latitude2 = 31.27;
//        String member2 = "3";
//
//        double longitude3 = 121.67;
//        double latitude3 = 29.27;
//        String member3 = "4";
//
//        long flag = RedisUtils.geoAdd(geoKey, longitude ,latitude ,member);
//        long flag1 = RedisUtils.geoAdd(geoKey, longitude1 ,latitude1 ,member1);
//        long flag2 = RedisUtils.geoAdd(geoKey, longitude2 ,latitude2 ,member2);
//        long flag3 = RedisUtils.geoAdd(geoKey, longitude3 ,latitude3 ,member3);
//        System.out.println("flag=" + flag);

//        List<GeoRadiusResponse> geoList = RedisUtils.geoRadius(geoKey, 120.27, 30.27, 200,
//                GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withCoord().withDist());
//        System.out.println(geoList);

        JSONArray selectOptionsArray = JSONObject.parseArray("[]");
        Integer operator = selectOptionsArray.getInteger(0);

    }





}
