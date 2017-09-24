package comchatory.rainvic.ccityhkchatadmin.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import comchatory.rainvic.ccityhkchatadmin.constant.DBKeys;
import comchatory.rainvic.ccityhkchatadmin.data.model.ChatProfile;
import comchatory.rainvic.ccityhkchatadmin.data.model.Message;

import static comchatory.rainvic.ccityhkchatadmin.constant.DBKeys.DATA_SET_MESSAGE_KEY;


/**
 * Created by Rainvic on 26/8/2016.
 */
public class FirebaseDB {
    private final static String TAG = FirebaseDB.class.getSimpleName();

    /**
     * this one has to be set -1 after finish downloading/uploading
     */
    private static int processing = 0;

    private ChildEventListener childEventListener;

    static private FirebaseDB _instance;
    Context context;
    // Write a message to the database
    FirebaseDatabase database;
    DatabaseReference itemRef;
    DatabaseReference itemsRef;
    DatabaseReference typeRef;
    DatabaseReference imgRef;
    DatabaseReference profileRef;
    DatabaseReference advertsRef;
    DatabaseReference messageRef;
    DatabaseReference chatProfileRef;


    static {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public static FirebaseDB getInstance(Context context){
        if(_instance == null){
            _instance = new FirebaseDB(context);
        }
        processing ++;
        return _instance;
    }

    private FirebaseDB(Context context){
        this.context = context;
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        itemRef = database.getReference(DBKeys.DATA_SET_ITEM_KEY);
        itemsRef = database.getReference(DBKeys.DATA_SET_ITEMS_KEY);
        typeRef = database.getReference(DBKeys.DATA_SET_ITEM_TYPE_KEY);
        imgRef = database.getReference(DBKeys.DATA_SET_IMAGE_KEY);
        profileRef = database.getReference(DBKeys.DATA_SET_PROFILE_KEY);
        advertsRef = database.getReference(DBKeys.DATA_SET_ADVERT_KEY);
        messageRef = database.getReference(DATA_SET_MESSAGE_KEY);
        chatProfileRef = database.getReference(DBKeys.DATA_SET_CHAT_PROFILE_KEY);
//        depTypeRef = database.getReference("itemTypeOld");
    }




    public void getMessages(String uid, long from, long to, final MessageCallback messageCallback){
        if(childEventListener == null){
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Message message = dataSnapshot.getValue(Message.class);
                    List<Message> messages = new ArrayList<Message>();
                    messages.add(message);
                    messageCallback.onSuccess(messages);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    Message newMessage = dataSnapshot.getValue(Message.class);
                    String messageKey = dataSnapshot.getKey();
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    String messageKey = dataSnapshot.getKey();
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    Message newMessage = dataSnapshot.getValue(Message.class);
                    String messageKey = dataSnapshot.getKey();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    Log.w(TAG, "postMessage:onCancelled", databaseError.toException());
                    Toast.makeText(context, "Failed to load comments.",
                            Toast.LENGTH_SHORT).show();
                }
            };
        }else{
            messageRef.child(uid).removeEventListener(childEventListener);
        }

        messageRef.child(uid).addChildEventListener(childEventListener);
    }

    public void writeNewMessage(String username, String uid, String body) {
//        String key = messageRef.child(uid).push().getKey();
        Long timestamp = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis();
        Message message = new Message(false, body, timestamp);
        Map<String, Object> msgValues = message.toMap();

//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/"+DATA_SET_MESSAGE_KEY+"/" + uid + "/" + key, msgValues);
//
//        database.getReference().updateChildren(childUpdates);

        messageRef.child(uid).push().setValue(message);

        ChatProfile chatProfile = new ChatProfile(uid, timestamp, username, body);

        chatProfileRef.child(uid).setValue(chatProfile);
    }




    /**************************************************************************
     // Callback
     **************************************************************************/
    public interface ProgressCallback{
        void onProgressUpdated(int progress);
        void onFinished(boolean success);
    }

    public interface StringListCallback{
        void onStringListReturned(List<String> stringList);
        void onFailed();
    }

    public interface MessageCallback{
        void onSuccess(List<Message> messageList);
        void onFailed(DatabaseError databaseError);
    }


    /**************************************************************************
     * // STATIC
     **************************************************************************/
    public static boolean isProcessing(){
        return (processing > 0);
    }

}