package comchatory.rainvic.ccityhkchatadmin.messenger;

import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

import comchatory.rainvic.ccityhkchatadmin.data.FirebaseDB;
import comchatory.rainvic.ccityhkchatadmin.data.model.Message;

/**
 * Created by rainvic on 9/14/17.
 */

public class MessengerPresenter implements MessengerContract.Presenter {

    private MessengerContract.View messengerView;

    private FirebaseDB firebaseDB;

    private List<Message> messageList;

    private String username;
    private String uid;

    private FirebaseDB.MessageCallback messageCallback;

    public MessengerPresenter(MessengerContract.View messengerView, String uid, String username, FirebaseDB firebaseDB) {
        this.messengerView = messengerView;
        this.firebaseDB = firebaseDB;
        this.messageList = new ArrayList<>();
        this.uid = uid;
        this.username = username;
    }

    @Override
    public void start() {
        loadMessages(true);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void showSnackBar(int mainTxt) {

    }

    @Override
    public void showSnackBar(int title, int mainTxt, int actionTxt) {

    }

    @Override
    public void clickSendMsgBtn() {
        if(messengerView.isActive()){
            String newMsg = messengerView.getNewMessage();
            if(newMsg == null || newMsg.isEmpty())
                return;

            sendNewMessage(newMsg);
        }
    }

    @Override
    public void loadMessages(boolean force) {
        if(force){
            messageList.clear();
        }

        long from = messageList.size();
        long to = from + 20;
        if(messageCallback == null){
            messageCallback = new FirebaseDB.MessageCallback() {
                @Override
                public void onSuccess(List<Message> messageList) {
                    if(messengerView.isActive()){
                        messengerView.onGetMessageList(messageList);
                    }
                }

                @Override
                public void onFailed(DatabaseError databaseError) {

                }
            };
        }


        firebaseDB.getMessages(uid, from, to, messageCallback);
    }

    @Override
    public void sendNewMessage(String body) {
        firebaseDB.writeNewMessage(
                username,
                uid,
                body);

        if(messengerView.isActive()){
            messengerView.onNewMessageSent();
        }

    }
}
