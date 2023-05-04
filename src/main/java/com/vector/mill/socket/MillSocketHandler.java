package com.vector.mill.socket;

import com.google.gson.Gson;
import com.vector.mill.bean.MillService;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MillSocketHandler extends TextWebSocketHandler {

    private final MillService millService;

    private final Gson gson = new Gson();

    public MillSocketHandler(MillService millService) {
        this.millService = millService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        TextMessage msg = new TextMessage(gson.toJson(millService.getState()));
        session.sendMessage(msg);
    }
}
