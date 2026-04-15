package org.petar.shortenit.configuration;

import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.UnifiedJedis;

public class RedisConfig {

    @Value("${spring.data.redis.host")
    private String REDIS_HOST;

    @Value("${spring.data.redis.port")
    private Integer REDIS_PORT;

    @Value("${spring.data.redis.user")
    private String REDIS_USER;

    @Value("${spring.data.redis.host")
    private String REDIS_PASSWORD;

    public void run() {
        JedisClientConfig config = DefaultJedisClientConfig.builder()
                .user(REDIS_USER)
                .password(REDIS_PASSWORD)
                .build();

        UnifiedJedis jedis = new UnifiedJedis(new HostAndPort(REDIS_HOST, REDIS_PORT), config);

        String res1 = jedis.set("foo", "bar");
        System.out.println(res1); // >>> OK

        String res2 = jedis.get("foo");
        System.out.println(res2); // >>> bar

        jedis.close();
    }
}
