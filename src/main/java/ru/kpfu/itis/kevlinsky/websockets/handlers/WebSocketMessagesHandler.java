package ru.kpfu.itis.kevlinsky.websockets.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.kpfu.itis.kevlinsky.websockets.dto.Message;
import com.google.gson.Gson;

@Component
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        Gson gson = new Gson();
        Message message1 = gson.fromJson(message.getPayload().toString(), Message.class);
        int message_length = message1.getText().length();
        Message message2 = new Message();
        message2.setText("Количество символов в вашем сообщении: " + message_length);
        session.sendMessage(new TextMessage(gson.toJson(message2)));
    }
}
