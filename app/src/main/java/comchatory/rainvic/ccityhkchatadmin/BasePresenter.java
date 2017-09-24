package comchatory.rainvic.ccityhkchatadmin;

/**
 * Created by Rainvic on 29/12/2016.
 */


public interface BasePresenter {

    void start();
    void onStart();
    void onStop();
    void showSnackBar(int mainTxt);
    void showSnackBar(int title, int mainTxt, int actionTxt);

}