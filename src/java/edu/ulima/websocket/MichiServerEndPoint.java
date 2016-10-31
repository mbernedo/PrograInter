/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author mbernedo
 */
@ServerEndpoint(value = "/michi")
@Singleton
public class MichiServerEndPoint {

    private String WaitingUser = null;
    private Session WaitingSession = null;
    Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("New request received. Id: " + userSession.getId());
        userSessions.add(userSession);
    }

    @OnClose
    public void onClose(Session userSession) {
        System.out.println("Connection closed. Id: " + userSession.getId());
        userSessions.remove(userSession);
    }

    @OnMessage
    public void onMessage(String message, Session userSession) throws ParseException {
        System.out.println("Message Received: " + message);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(message);
        System.out.println(obj.get("cod"));
        //0: esperando, 1: en juego
        if (obj.get("cod").toString().equals("1")) {
            if(WaitingUser==null){
                WaitingUser = obj.get("msg").toString();
                WaitingSession = userSession;
                System.out.println("if");
            }else{
                try {
                    WaitingSession.getBasicRemote().sendText("Encontraste huevoncito");
                } catch (IOException ex) {
                    Logger.getLogger(MichiServerEndPoint.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("else");
            }
        }
    }
}
