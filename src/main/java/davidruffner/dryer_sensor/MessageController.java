package davidruffner.dryer_sensor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MessageController {
    @PostMapping("/dryer")
    String acceptMessage(@RequestBody Message message, HttpServletResponse response) {
        Message returnMessage;

        switch (message.getMessageType()) {
            case "event":
                switch (message.getMessage()) {
                    case "dryer_finished":
                        // Check token
                        if (message.getToken() == null || !message.getToken().equals("oxXLrop4W3wyGOa9JuOe")) {
                            returnMessage = new Message("response", "unauthorized");
                            response.setStatus(401);
                        }
                        else {
                            // Send push notification
                            returnMessage = new Message("response", "confirmed");
                            response.setStatus(200);
                        }
                        break;

                    default:
                        returnMessage = new Message("response", "invalid_event_name");
                        response.setStatus(400);
                        break;
                }
                break;

            default:
                returnMessage = new Message("response", "invalid_message_type");
                response.setStatus(400);
                break;
        }

        return returnMessage.toString();
    }
}
