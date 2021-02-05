package com.demo2.backend.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
/**
 * code used from example given in recitation. More modifications to come as needed
 * @author Vamsi Krishna Calpakkam
 *
 */
@Configuration 
public class WebSocketConfig {  
    @Bean  
    public ServerEndpointExporter serverEndpointExporter(){  
        return new ServerEndpointExporter();  
    }  
} 
