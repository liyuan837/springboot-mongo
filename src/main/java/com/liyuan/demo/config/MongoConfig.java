package com.liyuan.demo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
@Configuration
public class MongoConfig {
    @Value("${spring.data.mongodb.host}")
    private String host;
 
    @Value("${spring.data.mongodb.port}")
    private int port;
 
    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Value("${spring.data.mongodb.credentialdatabase}")
    private String credentialdatabase;

    @Bean(name = "mongoDbFactory")
    public MongoDbFactory mongoDbFactory() {
 
        // 客户端
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectTimeout(10000);
        builder.maxWaitTime(120000);
 
        //数据库连接池其他参数，如最大连接数这些，可以参考着使用部分参数
        //builder.connectionsPerHost();
        //builder.minConnectionsPerHost();
        //builder.requiredReplicaSetName();
        //builder.threadsAllowedToBlockForConnectionMultiplier();
        //builder.serverSelectionTimeout();
        //builder.maxConnectionIdleTime();
        //builder.maxConnectionLifeTime();
        //builder.socketTimeout());
        //builder.socketKeepAlive();
        //builder.sslEnabled());
        //builder.sslInvalidHostNameAllowed();
        //builder.alwaysUseMBeans();
        //builder.heartbeatFrequency();
        //builder.minHeartbeatFrequency();
        //builder.heartbeatConnectTimeout();
        //builder.heartbeatSocketTimeout();
        //builder.localThreshold();
 
        MongoClientOptions mongoClientOptions = builder.build();
 
        // MongoDB地址列表,如果有多个ip地址，那么配置文件里面可以用逗号分隔ip地址，这里再把每一个ip地址和端口号添加进list里面
        List<ServerAddress> serverAddresses = new ArrayList<>();
        ServerAddress serverAddress = new ServerAddress(host, port);
        serverAddresses.add(serverAddress);
 
        // 连接认证，如果设置了用户名和密码的话
        List<MongoCredential> mongoCredentialList = new ArrayList<>();
        mongoCredentialList.add(MongoCredential.createScramSha1Credential(username,credentialdatabase,password.toCharArray()));
 
        // 创建认证客户端(存在用户名和密码)
         MongoClient mongoClient = new MongoClient(serverAddresses,mongoCredentialList, mongoClientOptions);
 
        // 创建非认证客户端(没有设置mongodb数据库的用户名和密码)
        //MongoClient mongoClient = new MongoClient(serverAddresses, mongoClientOptions);
 
        // 创建MongoDbFactory
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, database);
        return mongoDbFactory;
    }
 
    @Bean(name = "mongoTemplate")
    @Autowired
    public MongoTemplate getMongoTemplate(MongoDbFactory mongoDbFactory)
    {
        return new MongoTemplate(mongoDbFactory);
    }
}