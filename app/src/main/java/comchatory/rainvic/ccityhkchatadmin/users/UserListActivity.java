package comchatory.rainvic.ccityhkchatadmin.users;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import comchatory.rainvic.ccityhkchatadmin.R;
import comchatory.rainvic.ccityhkchatadmin.data.FirebaseDB;
import comchatory.rainvic.ccityhkchatadmin.data.model.ChatProfile;
import comchatory.rainvic.ccityhkchatadmin.databinding.ActivityUserListBinding;
import comchatory.rainvic.ccityhkchatadmin.messenger.MessengerActivity;
import comchatory.rainvic.ccityhkchatadmin.messenger.UserListAdapter;
import comchatory.rainvic.ccityhkchatadmin.viewUtil.ItemClickedListener;
import comchatory.rainvic.ccityhkchatadmin.viewUtil.MyLinearLayoutManager;

import static comchatory.rainvic.ccityhkchatadmin.messenger.MessengerActivity.UID;
import static comchatory.rainvic.ccityhkchatadmin.messenger.MessengerActivity.USER_NAME;

public class UserListActivity extends AppCompatActivity {

    private ActivityUserListBinding binding;
    private UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list);



    }

    @Override
    protected void onResume() {
        super.onResume();


        binding.rvUserList.setHasFixedSize(false);

        adapter = new UserListAdapter(this, new ItemClickedListener() {
            @Override
            public void onItemClicked(View itemView, Object object) {
                if(object instanceof ChatProfile){
                    ChatProfile chatProfile = (ChatProfile) object;
                    Intent intent = new Intent(UserListActivity.this, MessengerActivity.class);
                    intent.putExtra(UID, chatProfile.getUid());
                    intent.putExtra(USER_NAME, chatProfile.getDisplayUserName());
                    startActivity(intent);
                }
            }
        });

        binding.rvUserList.setAdapter(adapter);
        binding.rvUserList.setLayoutManager(new MyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        FirebaseDB.getInstance(this).updateChatList(new FirebaseDB.ChatProfileListCallback() {
            @Override
            public void onUpdated() {
                if(adapter != null)
                    adapter.onDateUpdated();
            }
        });

    }
}
