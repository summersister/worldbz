package com.dear.common.cache.redis;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis操作工具类
 * @author Neo
 */
@Slf4j
@Component
public class RedisUtil {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 设置键值对
     * @param key
     * @param value
     * @return 成功返回OK(请以OK为判断基准)
     */
    public String set(String key, String value){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return jedis.set(key, value);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * 设置键值对，值为对象
     * @param key
     * @param obj
     * @return
     */
    public String setObject(String key, Object obj){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            String objJson = JSON.toJSONString(obj);

            return jedis.set(key, objJson);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    public Long hsetObject(String key, Integer filed, Object obj){

        return this.hsetObject(key, String.valueOf(filed), obj);
    }

    /**
     * 设置键值对，值为对象
     * @param key
     * @param obj
     * @return
     */
    public Long hsetObject(String key, String filed, Object obj){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            String objJson = JSON.toJSONString(obj);

            return jedis.hset(key, filed, objJson);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * 设置键值对，值为对象，并设置有效时长
     *
     * @param key
     * @param obj
     * @param t   有效时长（秒）
     * @return
     */
    public String setexObject(String key, Object obj, int t) {

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            String objJson = JSON.toJSONString(obj);

            return jedis.setex(key, t, objJson);

        } catch (Exception e) {

            log.error(e.getMessage());

        } finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * 根据Key获取对象信息
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getObject(String key, Class<T> clazz){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return JSON.parseObject(jedis.get(key), clazz);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * 设置键值对，并赋予有效时长，时长为秒
     * @param key
     * @param value
     * @param t
     * @return 成功返回OK(请以OK为判断基准)
     */
    public String setex(String key, String value, int t){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return jedis.setex(key,t, value);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * 不重复设置键值对
     * @param key
     * @param value
     * @return 成功返回1(请以返回值不等于null且等于1为判断基准)
     */
    public Long setnx(String key, String value){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return jedis.setnx(key, value);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * 储存接口参考 HashMap put
     *
     * Redis 哈希(Hash)
     * Redis hash 是一个string类型的field和value的映射表，hash特别适合用于存储对象。
     *
     * Redis 中每个 hash 可以存储 232 - 1 键值对（40多亿）。
     *
     * @param key
     * @param filed
     * @param value
     * @return
     */
    public Long hset(String key, String filed, String value){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return jedis.hset(key, filed, value);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * Redis hkeys
     *
     * 返回值
     * 包含哈希表中所有字段的列表。当键不存在时，返回一个空列表。
     * @param key
     * @return
     */
    public Set<String> hkeys(String key){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return jedis.hkeys(key);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }


    /**
     *
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * 返回值:
     * 被成功移除的域的数量，不包括被忽略的域
     *
     * @param key
     * @param filed
     * @return
     */
    public Long hdel(String key, String filed){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return jedis.hdel(key, filed);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * 设置键值对，并设置有效时长
     * 可选模式：只有不存在时设置 和 只有存在时覆盖
     * @param key 键值对的key
     * @param value 键值对的value
     * @param nxxx 设置方式，参数固定为NX和XX，NX表示只有不存在时设置，XX表示只有存在时覆盖
     * @param expx 时长单位，参数固定为EX和PX，EX表示秒级，PX表示毫秒级
     * @param time 时长数值
     * @return
     */
    public String set(String key, String value, String nxxx, String expx, int time){

        Jedis jedis = null;

        try {

            if ((!"NX".equals(nxxx) && !"XX".equals(nxxx)) || (!"EX".equals(expx) && !"PX".equals(expx))){

                throw new RuntimeException("使用redis时，参数非法");

            }

            jedis = jedisPool.getResource();

            return jedis.set(key, value, nxxx, expx, time);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * 设置键值对，值为list集合的json串
     *
     * @param key
     * @param list
     * @return 成功返回OK(请以OK为判断基准)
     */
    public String setList(String key, List<?> list) {

        Jedis jedis = null;

        try {

            String str = JSON.toJSONString(list);

            jedis = jedisPool.getResource();

            return jedis.set(key, str);

        } catch (Exception e) {

            log.error(e.getMessage());

        } finally {

            this.closeJedis(jedis);
        }

        return null;
    }


    /**
     * 设置键值对，值为list集合的json串 (hset)
     *
     * @param key
     * @param list
     * @return 成功返回OK(请以OK为判断基准)
     */
    public Long hsetList(String key, String filed, List<?> list) {

        Jedis jedis = null;

        try {

            String str = JSON.toJSONString(list);

            jedis = jedisPool.getResource();

            return jedis.hset(key, filed, str);

        } catch (Exception e) {

            log.error(e.getMessage());

        } finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * 设置键值对，值为list集合的json串
     * @param key
     * @param list
     * @param t 单位秒
     * @return 成功返回OK(请以OK为判断基准)
     */
    public String setexList(String key, List<?> list, int t){

        Jedis jedis = null;

        try {

            String str = JSON.toJSONString(list);

            jedis = jedisPool.getResource();

            return jedis.setex(key, t, str);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * 向队列中头部添加
     *
     * @param key
     * @param value
     * @return
     */
    public long lpush(String key, String value){

        long num = 0;

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            num = jedis.lpush(key, value);

            return num;

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return num;
    }

    /**
     * 向队列中头部添加 (非原子性设置时间)
     *
     * @param key
     * @param value
     * @param t
     * @return
     */
    public long lpush(String key, String value, Integer t){

        long num = 0;

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            num = jedis.lpush(key,value);

            if (t != null){

                jedis.expire(key, t);

            }

            return num;

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return num;
    }

    /**
     * 取出集合一条并删除
     *
     * @param key 集合名
     * @return 成功返回集合长度；失败返回0
     */
    public String rpop(String key){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return jedis.rpop(key);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * 通过键获取值
     * @param key
     * @return 成功返回对应的值，失败返回null(请以null为判断基准)
     */
    public String get(String key){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return jedis.get(key);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * 通过键获取集合
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return 成功返回对应的集合，失败返回null(请以null为判断基准)
     */
    public <T> List<T> getList(String key, Class<T> clazz){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            String str = jedis.get(key);

            if (str != null){

                List<T> list = JSON.parseArray(str, clazz);

                return list;

            }

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }


    /**
     * 通过键获取集合(hget)
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return 成功返回对应的集合，失败返回null(请以null为判断基准)
     */
    public <T> List<T> hgetList(String key, String filed, Class<T> clazz){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            String str = jedis.hget(key, filed);

            if (str != null){

                List<T> list = JSON.parseArray(str, clazz);

                return list;

            }

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    public String hget(String key, String field){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return jedis.hget(key, field);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    public <T> T hgetObject(String key, Integer field, Class<T> l){

        return this.hgetObject(key, String.valueOf(field), l);
    }

    public <T> T hgetObject(String key, String field, Class<T> l){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return JSON.parseObject(jedis.hget(key, field), l);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    public String hmset(String key, Map<String, String> hash){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return jedis.hmset(key, hash);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    public Map<String, String> hgetAll(String key){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return jedis.hgetAll(key);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * Redis Keys 命令用于查找所有符合给定模式 pattern 的 key 。。
     *
     * 查找以 runoob 为开头的 key：
     *
     * redis 127.0.0.1:6379> KEYS runoob*
     * 1) "runoob3"
     * 2) "runoob1"
     * 3) "runoob2"
     * 获取 redis 中所有的 key 可用使用 *。
     *
     * redis 127.0.0.1:6379> KEYS *
     * 1) "runoob3"
     * 2) "runoob1"
     * 3) "runoob2"
     *
     * @param key
     * @return
     */
    public Set<String> keys(String key){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return jedis.keys(key);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * 通过键删除键值对
     *
     * @param key
     * @return 成功返回大于等于0的个数(请以返回值不等于null且大于等于0为判断基准)
     */
    public Long del(String key){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return jedis.del(key);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    /**
     * 获取key的剩余时间
     *
     * @param key
     * @return 成功返回剩余时间（单位秒）(请以返回值不等于null且大于0为判断基准)
     */
    public Long ttl(String key){

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            return jedis.ttl(key);

        }catch (Exception e){

            log.error(e.getMessage());

        }finally {

            this.closeJedis(jedis);
        }

        return null;
    }

    private void closeJedis(Jedis jedis) {

        if (jedis != null){

            jedis.close();

        }
    }

}
