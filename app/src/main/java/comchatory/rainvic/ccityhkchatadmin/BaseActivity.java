package comchatory.rainvic.ccityhkchatadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by rainvic on 9/20/17.
 */

public class BaseActivity extends AppCompatActivity {

    private boolean isActive = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.isActive = true;
    }

    @Override
    protected void onPause() {
        this.isActive = false;
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean isActivityActive(){
        return isActive;
    }

//    public boolean isUserLoggedIn() {
//        if(MyFirebaseAuth.getInstance().getUser() != null
//            && !MyFirebaseAuth.getInstance().getUser().isAnonymous()){
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//    public void goToLoginPage(){
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivityForResult(intent, LOGIN_ACTIVITY);
//    }
}
