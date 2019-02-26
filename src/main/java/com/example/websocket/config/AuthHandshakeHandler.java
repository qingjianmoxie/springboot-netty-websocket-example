package com.example.websocket.config;

import com.github.netty.protocol.servlet.websocket.NettyRequestUpgradeStrategy;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * 该类可以在 http协议升级到websocket协议 的握手过程中 加业务逻辑 （比如：登录认证）
 * @author wangzihao
 */
public class AuthHandshakeHandler extends DefaultHandshakeHandler {
    public AuthHandshakeHandler() {
        //请求协议升级的策略, 任选其一即可
        super(new NettyRequestUpgradeStrategy());
//        super(new TomcatRequestUpgradeStrategy());
//        super(new JettyRequestUpgradeStrategy());
    }

    //这里获取首次握手的用户身份
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        //这里返回用户身份，controller中就可以接到Principal对象了
        String token = request.getHeaders().getFirst("access_token");
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return token;
            }
        };
        return principal;
    }

}
