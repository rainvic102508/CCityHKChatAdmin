package comchatory.rainvic.ccityhkchatadmin.messenger;


import java.util.List;

import comchatory.rainvic.ccityhkchatadmin.BasePresenter;
import comchatory.rainvic.ccityhkchatadmin.BaseView;
import comchatory.rainvic.ccityhkchatadmin.data.model.Message;

/**
 * Created by rainvic on 9/14/17.
 */

public interface MessengerContract {
    interface View extends BaseView<Presenter> {
        String getNewMessage();
        void onNewMessageSent();
        void onGetMessageList(List<Message> messageList);
    }

    interface Presenter extends BasePresenter {
        void clickSendMsgBtn();

        void loadMessages(boolean force);
        void sendNewMessage(String body);
    }
}
