package comchatory.rainvic.ccityhkchatadmin;

import android.support.annotation.NonNull;

/**
 * Created by Rainvic on 29/12/2016.
 */

public interface BaseView<T> {

    boolean isActive();
    void setPresenter(T presenter);
    void showSnackBar(int title, @NonNull int mainTxt, int actionTxt);
    void setLoadingIndicator(boolean active);
    void closeThisPageWithResult(int result);

}