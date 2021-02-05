package com.demo2.backend.websocket;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/**
 * code taken from the example given. More modifications will be made in the next couple days as needed for our needs
 * @author Vamsi Krishna Calpakkam
 *
 */
@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {
	private Session session;
    private static Set<WebSocketServer> chatEndpoints = new CopyOnWriteArraySet<>();
    private static Map<String, String> users = new HashMap();
    private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    
    /**
     * Connects a new user and keeps track of their session
     * @param session Session just opened by the user
     * @param username The new user's name
     * @throws IOException If there is an error from connection
     */
    @OnOpen
    public void onOpen(
    	      Session session, 
    	      @PathParam("username") String username) throws IOException {
        logger.info("Entered into Open");
		this.session = session;
        chatEndpoints.add(this);
        users.put(session.getId(), username);
        
        String message="User: " + username +" Has Joined the Chat";
        	//broadcast(message);
		
    }
    /**
     * Broadcasts a message to all connected users
     * @param session The current users session
     * @param message The message to be broadcast
     * @throws IOException throws an error 
     */
    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        // Handle new messages
    	logger.info("Entered into Message: Got Message: "+message);
    	String echo=""+message;
    	//sendMessageToPArticularUser(session,echo);
    	broadcast(message);
    }
 
    /**
     * When a user disconnects, closes that session and logs the disconnect
     * @param session session to be closed
     * @throws IOException If errors in broadcasting
     */
    @OnClose
    public void onClose(Session session) throws IOException{
    	logger.info("Entered into Close");
        chatEndpoints.remove(this);
        String message="Disconnected";
        broadcast(message);
    }
 
    /**
     * If there is an error, logs it
     * @param session The session to log the error in
     * @param throwable The error
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    	logger.info("Entered into Error");
    }
    
    /**
     * Sends a message to a single user
     * @param session The user's session to send the message to
     * @param message The message to send
     */
    private void sendMessageToPArticularUser(Session session,String message) {
    	
    	try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
        	logger.info("Exception: " + e.getMessage().toString());
            e.printStackTrace();
        }
    }
    
    
    /**
     * Sends the given message to all users
     * @param message message to be sent
     * @throws IOException If a socket connection can't be made
     */
    private static void broadcast(String message) 
    	      throws IOException {
    	  
    	        chatEndpoints.forEach(endpoint -> {
    	            synchronized (endpoint) {
    	                try {
    	                    endpoint.session.getBasicRemote().sendText(message);
    	                } catch (IOException e) {
    	                    e.printStackTrace();
    	                }
    	            }
    	        });
    	    }
}
