package ru.kpfu.itis.kevlinsky.websockets;

import java.io.*;
import java.util.Scanner;

import com.google.gson.Gson;
import com.neovisionaries.ws.client.*;
import ru.kpfu.itis.kevlinsky.websockets.dto.Message;


public class JavaWebSocketClient{
    private static final String SERVER = "ws://localhost:8080/chat";
    private static final Gson gson = new Gson();

    public static void main(String[] args) throws Exception {
        Message message = new Message();
        WebSocket ws = connect();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type in text:");
        String text;
        while ((text = scanner.nextLine()) != null){
            if (text.equals("exit")) {
                break;
            }
            message.setText(text);
            ws.sendText(gson.toJson(message));
        }
        ws.disconnect();
    }

    private static WebSocket connect() throws IOException, WebSocketException {
        return new WebSocketFactory()
                .createSocket(SERVER)
                .addListener(new WebSocketAdapter() {
                    public void onTextMessage(WebSocket websocket, String message) {
                        Message msg = gson.fromJson(message, Message.class);
                        System.out.println(msg.getText());
                    }
                })
                .addExtension(WebSocketExtension.PERMESSAGE_DEFLATE)
                .connect();
    }
}