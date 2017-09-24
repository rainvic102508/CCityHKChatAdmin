package comchatory.rainvic.ccityhkchatadmin.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rainvic on 9/22/17.
 */
@IgnoreExtraProperties
public class Message {

    Boolean isUser;
    String message;
    Long timestamp;

    public Message(boolean isUser, String message, long timestamp) {
        this.isUser = isUser;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Message() {
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("isUser", isUser);
        result.put("message", message);
        result.put("timestamp", timestamp);

        return result;
    }
}
