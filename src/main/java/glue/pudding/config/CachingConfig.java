package glue.pudding.config;

import glue.pudding.entity.Menu;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;
import org.springframework.data.redis.cache.RedisCacheManager;

/**
 * Created by GUIXu on 2020/4/22.
 */
@Configuration
@EnableCaching
public class CachingConfig {

    private final int maxIdle = 4;
    private final long maxWaitMills = Long.MAX_VALUE;
    private final int maxTotal = 4;
    private final boolean testOnBorrow = true;

    private final String host = "192.168.133.130";
    //private final String host = "212.64.67.158";
    private final int port = 6379;
    private final String password = "123456";

    @Bean
    public JedisPoolConfig poolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMills);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig config) {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        factory.setPoolConfig(config);
        return factory;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(factory);
        return  template;
    }

    @Bean
    public RedisTemplate<String, Menu> menuRedisTemplate(JedisConnectionFactory factory) {
        RedisTemplate<String, Menu> template = new RedisTemplate<String, Menu>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        //template.setValueSerializer(new Jackson2JsonRedisSerializer<Menu>(Menu.class));
        return template;
    }

    @Bean
    public CacheManager cacheManager(JedisConnectionFactory factory) {
        return RedisCacheManager.create(factory);
    }
}
