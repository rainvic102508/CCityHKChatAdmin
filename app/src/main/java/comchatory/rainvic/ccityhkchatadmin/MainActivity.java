package comchatory.rainvic.ccityhkchatadmin;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import comchatory.rainvic.ccityhkchatadmin.databinding.ActivityMainBinding;
import comchatory.rainvic.ccityhkchatadmin.messenger.MessengerActivity;
import comchatory.rainvic.ccityhkchatadmin.util.PreferenceUtil;

import static comchatory.rainvic.ccityhkchatadmin.util.PreferenceUtil.ANONYMOUS_AUTH;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MyFirebaseAuth myFirebaseAuth;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        myFirebaseAuth = MyFirebaseAuth.getInstance();

        if(myFirebaseAuth.getUser() == null) {
            myFirebaseAuth.signInAnonymously(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.e(TAG, "signInAnonymously:onComplete:" + task.isSuccessful());

                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "signInAnonymously", task.getException());
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                    PreferenceUtil.setFireBaseAuthID(MainActivity.this, task.getResult().getUser().getUid());
                    PreferenceUtil.setAuthTypeID(MainActivity.this, ANONYMOUS_AUTH);


                    startMessenger();

                }
            });
        }else{
            startMessenger();
        }
    }

    private void startMessenger(){

        Intent intent = new Intent(this, MessengerActivity.class);
        startActivity(intent);
    }
}
