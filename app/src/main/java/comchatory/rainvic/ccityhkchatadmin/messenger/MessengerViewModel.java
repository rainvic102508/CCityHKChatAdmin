package comchatory.rainvic.ccityhkchatadmin.messenger;

import android.content.Context;
import android.databinding.BaseObservable;

/**
 * Created by rainvic on 9/14/17.
 */

public class MessengerViewModel extends BaseObservable {
    private Context context;
    private MessengerContract.Presenter messengerPresenter;

    public MessengerViewModel(Context context, MessengerContract.Presenter messengerPresenter) {
        this.context = context;
        this.messengerPresenter = messengerPresenter;
    }
}
