package com.instagram.member.common.config;

import io.lettuce.core.ReadFrom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.sentinel.master}")
    private String master;

    @Value("${spring.redis.sentinel.password}")
    private String password;

    @Value("#{'${spring.redis.sentinel.nodes}'.split(',')}")
    private List<String> nodes;

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA)
                .build();

        Set<RedisNode> redisNodeSet = new HashSet<>();
        nodes.forEach(x -> redisNodeSet.add(new RedisNode(x.split(":")[0], Integer.parseInt(x.split(":")[1]))));

        RedisSentinelConfiguration serverConfig = new RedisSentinelConfiguration();
        serverConfig.master(master);
        serverConfig.setSentinels(redisNodeSet);
        serverConfig.setPassword(password);

        return new LettuceConnectionFactory(serverConfig,clientConfig);
    }
}
