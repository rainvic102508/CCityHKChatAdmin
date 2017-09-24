package comchatory.rainvic.ccityhkchatadmin.messenger;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Calendar;

import comchatory.rainvic.ccityhkchatadmin.data.model.Message;

/**
 * Created by rainvic on 6/28/17.
 */

public class StoryItemViewModel extends BaseObservable {
    private Context context;
    private Message message;

    public StoryItemViewModel(Context context, Message message) {
        this.context = context;
        this.message = message;
    }

    @Bindable
    public String getWho(){
        if (message != null)
            return message.isUser()?"me":"CcityHK";

        return "";
    }

    @Bindable
    public String getContent(){
        if(message == null)
            return null;

        return message.getMessage();
    }

    @Bindable
    public String getContentImg(){
        if(message == null)
            return null;

        return message.getMessage();
    }

    @Bindable
    public int getContentImgVisibility(){
//        if(message == null || message.getContentImg() == null || message.getContentImg().isEmpty())
//            return View.GONE;
//
//        return View.VISIBLE;
        return View.GONE;
    }

    @Bindable
    public String getProfileImg(){
        return "";
    }

    @Bindable
    public String getModifiedAt(){
        if(message == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(message.getTimestamp());

        // TODO: 6/28/17
        return "06:23";
    }

    @BindingAdapter("bind:setProfileImg")
    public static void setProfileImg(ImageView imageView, String v) {

//        Glide.with(imageView.getContext())
//                .load(v)
//                .placeholder(R.drawable.default_avatar)
//                .crossFade()
//                .centerCrop()
//                .into(imageView);

//        FirebaseStrg firebaseStrg = FirebaseStrg.getINSTANT(imageView.getContext());



//        Glide.with(imageView.getContext())
//                .load("")
//                .crossFade()
//                .centerCrop()
//                .into(imageView);

    }

    @BindingAdapter("bind:setContentImg")
    public static void setContentImg(ImageView imageView, String v) {

        Glide.with(imageView.getContext())
                .load(v)
                .crossFade()
                .centerCrop()
                .into(imageView);

    }
}
