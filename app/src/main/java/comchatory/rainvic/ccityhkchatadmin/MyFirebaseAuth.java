package comchatory.rainvic.ccityhkchatadmin;

import android.app.Activity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;

import comchatory.rainvic.ccityhkchatadmin.util.PreferenceUtil;

import static comchatory.rainvic.ccityhkchatadmin.util.PreferenceUtil.ANONYMOUS_AUTH;

/**
 * Created by rainvic on 7/29/17.
 */

public class MyFirebaseAuth {

    private final static String TAG  = MyFirebaseAuth.class.getSimpleName();

    private static MyFirebaseAuth _INSTANCE;
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    public static MyFirebaseAuth getInstance(){
        if(_INSTANCE == null){
            _INSTANCE = new MyFirebaseAuth();
        }

        return _INSTANCE;
    }

    public MyFirebaseAuth() {
        this.mAuth = FirebaseAuth.getInstance();
        this.user = mAuth.getCurrentUser();
    }

    public void addAuthStateListener(FirebaseAuth.AuthStateListener mAuthListener){
        if(mAuth != null)
            mAuth.addAuthStateListener(mAuthListener);
    }

    public void removeAuthStateListener(FirebaseAuth.AuthStateListener mAuthListener){
        mAuth.removeAuthStateListener(mAuthListener);
    }

    public FirebaseUser getUser(){
        return this.user;
    }

    public FirebaseAuth getmAuth() {
        return this.mAuth;
    }

    public void signInAnonymously(final Activity activity, OnCompleteListener<AuthResult> listener){
        if(PreferenceUtil.getFireBaseAuthID(activity) == null ||
                PreferenceUtil.getAuthTypeID(activity) == ANONYMOUS_AUTH) {
            mAuth.signInAnonymously()
                    .addOnCompleteListener(activity, listener);
        }
    }


    public void signInWithPhoneAuthCredential(Activity activity, PhoneAuthCredential credential, OnCompleteListener<AuthResult> listener) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, listener);
    }


    public void signInWithAuthCredential(Activity activity, AuthCredential credential, OnCompleteListener<AuthResult> listener) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, listener);
    }


}
