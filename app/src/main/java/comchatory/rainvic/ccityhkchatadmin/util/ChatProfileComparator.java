package comchatory.rainvic.ccityhkchatadmin.util;

import java.util.Comparator;

import comchatory.rainvic.ccityhkchatadmin.data.model.ChatProfile;

/**
 * Created by rainvic on 10/14/17.
 */

public class ChatProfileComparator implements Comparator<ChatProfile> {
    @Override
    public int compare(ChatProfile chatProfile, ChatProfile t1) {

        if(chatProfile.getResentTimeStamp()>t1.getResentTimeStamp())
            return 1;
        else if(chatProfile.getResentTimeStamp()<t1.getResentTimeStamp())
            return -1;
        else
            return 0;

    }
}
