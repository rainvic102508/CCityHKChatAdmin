package comchatory.rainvic.ccityhkchatadmin.messenger;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import comchatory.rainvic.ccityhkchatadmin.BaseActivity;
import comchatory.rainvic.ccityhkchatadmin.Injection;
import comchatory.rainvic.ccityhkchatadmin.R;
import comchatory.rainvic.ccityhkchatadmin.data.model.Message;
import comchatory.rainvic.ccityhkchatadmin.databinding.ActivityMessengerBinding;
import comchatory.rainvic.ccityhkchatadmin.viewUtil.MyLinearLayoutManager;


public class MessengerActivity extends BaseActivity implements MessengerContract.View {

    private ActivityMessengerBinding binding;
    private MessengerContract.Presenter messengerPresenter;
    private MessengerViewModel messengerViewModel;
    private MessengerAdapter messengerAdapter;

    public static final String UID = "DATA_UID";
    public static final String USER_NAME = "DATA_USER_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_messenger);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        String uid = getIntent().getStringExtra(UID);
        String username = getIntent().getStringExtra(USER_NAME);
        if(uid == null || uid.isEmpty())
            finish();

        messengerPresenter = new MessengerPresenter(this, uid, username, Injection.provideFirebaseDB(this));

        messengerViewModel = new MessengerViewModel(this, messengerPresenter);

        binding.setPresenter(messengerPresenter);
        binding.setViewModel(messengerViewModel);


    }


    @Override
    protected void onResume() {
        super.onResume();
//        if(isUserLoggedIn()) {
            messengerPresenter.start();
//        }else{
//            goToLoginPage();
//        }
    }

    @Override
    public boolean isActive() {
        return isActivityActive();
    }

    @Override
    public void setPresenter(MessengerContract.Presenter presenter) {

    }

    @Override
    public void showSnackBar(int title, @NonNull int mainTxt, int actionTxt) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void closeThisPageWithResult(int result) {

    }

    @Override
    public String getNewMessage() {
        return binding.etNewMsg.getEditableText().toString();
    }

    @Override
    public void onNewMessageSent() {
        binding.etNewMsg.setText("");
    }

    @Override
    public void onGetMessageList(List<Message> messageList) {
        if(messengerAdapter == null) {
            messengerAdapter = new MessengerAdapter(messageList, this);

            binding.rvMessages.setHasFixedSize(false);
            binding.rvMessages.setAdapter(messengerAdapter);
            binding.rvMessages.setLayoutManager(new MyLinearLayoutManager(MessengerActivity.this, LinearLayoutManager.VERTICAL, false));

        }else{
            messengerAdapter.addMoreMessages(messageList);
            binding.rvMessages.smoothScrollToPosition(messengerAdapter.getBasicItemCount());
        }
    }
}
