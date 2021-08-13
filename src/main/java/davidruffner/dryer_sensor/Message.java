package davidruffner.dryer_sensor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Message {
    private String messageType;
    private String message;
    private String token;

    public String getMessageType() {
        return messageType;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public Message(String messageType, String message) {
        this.messageType = messageType;
        this.message = message;
    }

    public Message(String messageType, String message, String token) {
        this.messageType = messageType;
        this.message = message;
        this.token = token;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonNode = mapper.createObjectNode();

        jsonNode.put("message_type", messageType);
        jsonNode.put("message", message);
        if (token != null) {
            jsonNode.put("token", token);
        }

        return jsonNode.toString();
    }
}
