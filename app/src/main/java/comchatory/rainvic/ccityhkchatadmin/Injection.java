package comchatory.rainvic.ccityhkchatadmin;

import android.content.Context;

import comchatory.rainvic.ccityhkchatadmin.data.FirebaseDB;


public class Injection {

    public static FirebaseDB provideFirebaseDB(Context context){
        return FirebaseDB.getInstance(context);
    }
}