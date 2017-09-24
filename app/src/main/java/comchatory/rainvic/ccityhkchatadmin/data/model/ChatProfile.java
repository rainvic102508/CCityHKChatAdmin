package comchatory.rainvic.ccityhkchatadmin.data.model;

/**
 * Created by rainvic on 9/22/17.
 */

public class ChatProfile {

    String uid;
    Long resentTimeStamp;
    String displayUserName;
    String lastMessage;

    public ChatProfile() {
    }

    public ChatProfile(String uid, Long resentTimeStamp, String displayUserName, String lastMessage) {
        this.uid = uid;
        this.resentTimeStamp = resentTimeStamp;
        this.displayUserName = displayUserName;
        this.lastMessage = lastMessage;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getResentTimeStamp() {
        return resentTimeStamp;
    }

    public void setResentTimeStamp(Long resentTimeStamp) {
        this.resentTimeStamp = resentTimeStamp;
    }

    public String getDisplayUserName() {
        return displayUserName;
    }

    public void setDisplayUserName(String displayUserName) {
        this.displayUserName = displayUserName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
