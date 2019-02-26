package com.example.websocket.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.messaging.DefaultSimpUserRegistry;

/**
 * Websocket配置
 * @author wangzihao
 */
@Configuration
@EnableWebSocketMessageBroker
@EnableWebSocket
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer{
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        StompWebSocketEndpointRegistration endpoint = registry.addEndpoint("/my-websocket");//添加一个/my-websocket端点，客户端就可以通过这个端点来进行连接；
        endpoint.setHandshakeHandler(new AuthHandshakeHandler());//这里放入一个握手的处理器,可以处理自定义握手期间的事情
        endpoint.setAllowedOrigins("*").withSockJS();//setAllowedOrigins(*)设置跨域.  withSockJS(*)添加SockJS支持
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic/");//定义了一个客户端订阅地址的前缀信息，也就是客户端接收服务端发送消息的前缀信息
        registry.setApplicationDestinationPrefixes("/app"); //api全局的前缀名
        registry.setUserDestinationPrefix("/user/");// 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/ ， 如果设置了全局前缀效果为 /app/user/xxx
    }

    @Bean
    @ConditionalOnMissingBean(SimpUserRegistry.class)
    public SimpUserRegistry userRegistry(){
        return new DefaultSimpUserRegistry();//负责管理在线用户信息
    }

}
