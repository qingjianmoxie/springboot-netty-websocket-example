package com.example.websocket;

import com.github.netty.springboot.EnableNettyServletEmbedded;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * websocket服务端
 * @author wangzihao
 */
@EnableNettyServletEmbedded//这个注解可以启用netty的Servlet容器，默认是tomcat的
@SpringBootApplication
public class SpringbootNettyWebsocketExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootNettyWebsocketExampleApplication.class, args);
	}

}
