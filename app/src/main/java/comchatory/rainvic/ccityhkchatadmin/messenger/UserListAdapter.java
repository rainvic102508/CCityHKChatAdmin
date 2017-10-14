package comchatory.rainvic.ccityhkchatadmin.messenger;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import comchatory.rainvic.ccityhkchatadmin.R;
import comchatory.rainvic.ccityhkchatadmin.data.FirebaseDB;
import comchatory.rainvic.ccityhkchatadmin.data.model.ChatProfile;
import comchatory.rainvic.ccityhkchatadmin.data.model.Message;
import comchatory.rainvic.ccityhkchatadmin.databinding.ItemChatProfileBinding;
import comchatory.rainvic.ccityhkchatadmin.databinding.ItemStoryLineLeftBinding;
import comchatory.rainvic.ccityhkchatadmin.databinding.ItemStoryLineRightBinding;
import comchatory.rainvic.ccityhkchatadmin.viewUtil.HeaderRecyclerViewAdapterV2;
import comchatory.rainvic.ccityhkchatadmin.viewUtil.ItemClickedListener;
import comchatory.rainvic.ccityhkchatadmin.viewUtil.ViewHolderListener;

import static android.support.v7.widget.RecyclerView.NO_POSITION;

/**
 * Created by rainvic on 9/14/17.
 */

public class UserListAdapter extends HeaderRecyclerViewAdapterV2<UserListAdapter.BindingHolder> {

    private Context context;
    private ItemClickedListener itemClickedListener;

    private final static int TYPE_USER = Integer.MIN_VALUE;
    private final static int TYPE_ADMIN = Integer.MIN_VALUE + 1;


    public UserListAdapter( Context context, ItemClickedListener itemClickedListener) {
        this.context = context;
        this.itemClickedListener = itemClickedListener;
    }

    /********************************************************
    HEADER
    *********************************************************/
    @Override
    public boolean useHeader() {
        return false;
    }

    @Override
    public BindingHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindHeaderView(BindingHolder holder, int position) {

    }

    /********************************************************
    FOOTER
    *********************************************************/
    @Override
    public boolean useFooter() {
        return false;
    }

    @Override
    public BindingHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindFooterView(BindingHolder holder, int position) {

    }

    /********************************************************
    BASIC
    *********************************************************/
    @Override
    public BindingHolder onCreateBasicItemViewHolder(ViewGroup parent, int viewType) {

            ItemChatProfileBinding itemChatProfileBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_chat_profile,
                    parent,
                    false
            );

            return new BindingHolder(itemChatProfileBinding, new ViewHolderListener() {
                @Override
                public void onViewClicked(View itemView, int position) {
                    if(itemClickedListener != null)
                        itemClickedListener.onItemClicked(itemView, getBasicItem(position));
                }
            });
    }

    @Override
    public void onBindBasicItemView(BindingHolder holder, int position) {
        ChatProfile chatProfile = (ChatProfile) getBasicItem(position);
        if(chatProfile != null){
            ItemChatProfileBinding binding = holder.itemChatProfileBinding;
            binding.tvUserId.setText(chatProfile.getDisplayUserName());
            binding.tvLastMessage.setText(chatProfile.getLastMessage());
            binding.tvTime.setText(String.valueOf(chatProfile.getResentTimeStamp()));
        }
    }

    @Override
    public int getBasicItemCount() {
        List<ChatProfile> list = FirebaseDB.getChatProfiles();

        if(list == null)
            return 0;

        return list.size();
    }


    @Override
    public int getBasicItemType(int position) {
        return 0;
    }

    @Override
    public Object getBasicItem(int position) {
        if(FirebaseDB.getChatProfiles() == null)
            return null;
        return FirebaseDB.getChatProfiles().get(position);
    }

    public void onDateUpdated(){
        notifyDataSetChanged();
    }

    /********************************************************
    Private
    *********************************************************/


    /**************************************************************************
     * // VIEW HOLDER
     **************************************************************************/

    static class BindingHolder extends RecyclerView.ViewHolder {
        private ItemChatProfileBinding itemChatProfileBinding;
        private ViewHolderListener viewHolderListener;

        public BindingHolder(ItemChatProfileBinding binding, ViewHolderListener viewHolderListener) {
            super(binding.getRoot());
            this.itemChatProfileBinding = binding;
            this.viewHolderListener = viewHolderListener;


            BindingHolder.this.itemChatProfileBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos =  getAdapterPosition();
                    if(pos != NO_POSITION) {
                        BindingHolder.this.viewHolderListener.onViewClicked( BindingHolder.this.itemChatProfileBinding.getRoot(), pos);
                    }
                }
            });
        }

    }
}
