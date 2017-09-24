package comchatory.rainvic.ccityhkchatadmin.messenger;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import comchatory.rainvic.ccityhkchatadmin.R;
import comchatory.rainvic.ccityhkchatadmin.data.model.Message;
import comchatory.rainvic.ccityhkchatadmin.databinding.ItemStoryLineLeftBinding;
import comchatory.rainvic.ccityhkchatadmin.databinding.ItemStoryLineRightBinding;
import comchatory.rainvic.ccityhkchatadmin.viewUtil.HeaderRecyclerViewAdapterV2;

/**
 * Created by rainvic on 9/14/17.
 */

public class MessengerAdapter extends HeaderRecyclerViewAdapterV2<MessengerAdapter.BindingHolder> {


    private List<Message> messageList;
    private Context context;

    private final static int TYPE_USER = Integer.MIN_VALUE;
    private final static int TYPE_ADMIN = Integer.MIN_VALUE + 1;


    public MessengerAdapter(List<Message> messageList, Context context) {
        this.messageList = new ArrayList<>();
        if(messageList != null && !messageList.isEmpty()) {
            this.messageList.addAll(messageList);
        }
        this.context = context;
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

        if(viewType == TYPE_USER) {
            ItemStoryLineRightBinding itemStoryLineRightBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_story_line_right,
                    parent,
                    false
            );

            return new BindingHolder(itemStoryLineRightBinding);
        }else {

            ItemStoryLineLeftBinding itemStoryLineLeftBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_story_line_left,
                    parent,
                    false
            );

            return new BindingHolder(itemStoryLineLeftBinding);
        }
    }

    @Override
    public void onBindBasicItemView(BindingHolder holder, int position) {


        final Message message = (Message) getBasicItem(position);
        if(message != null){
            if(getBasicItemType(position) == TYPE_USER) {
                ItemStoryLineRightBinding binding = holder.itemStoryLineRightBinding;
                binding.setViewModel(new StoryItemViewModel(context, message));
            }else{
                ItemStoryLineLeftBinding binding = holder.itemStoryLineLeftBinding;
                binding.setViewModel(new StoryItemViewModel(context, message));
            }
        }
    }

    @Override
    public int getBasicItemCount() {
        if(messageList == null)
            return 0;

        return messageList.size();
    }


    @Override
    public int getBasicItemType(int position) {
        if(messageList == null)
            return TYPE_ADMIN;
        return messageList.get(position).isUser()?TYPE_USER:TYPE_ADMIN;
    }

    @Override
    public Object getBasicItem(int position) {
        if(messageList == null)
            return null;
        return messageList.get(position);
    }

    public void addMoreMessages(List<Message> moreMessages){
        if(messageList == null)
            messageList = new ArrayList<>();

        messageList.addAll(moreMessages);
        notifyItemInserted(messageList.size()-1);
    }

    /********************************************************
    Private
    *********************************************************/


    /**************************************************************************
     * // VIEW HOLDER
     **************************************************************************/

    static class BindingHolder extends RecyclerView.ViewHolder {
        private ItemStoryLineLeftBinding itemStoryLineLeftBinding;
        private ItemStoryLineRightBinding itemStoryLineRightBinding;

        public BindingHolder(ItemStoryLineLeftBinding binding) {
            super(binding.getRoot());
            this.itemStoryLineLeftBinding = binding;

        }

        public BindingHolder(ItemStoryLineRightBinding binding) {
            super(binding.getRoot());
            this.itemStoryLineRightBinding = binding;

        }
    }
}
