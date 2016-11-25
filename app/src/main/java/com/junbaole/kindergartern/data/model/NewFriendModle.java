package com.junbaole.kindergartern.data.model;

import java.util.ArrayList;

/**
 * Created by yaohu on 16/10/11.
 */

public class NewFriendModle{

public ArrayList<NewFriendInfo> content;

    public class NewFriendInfo {
        public int user_id;
        public int rel_id2;
        public String rel_type;
        public boolean as_confirm;
        public boolean as_sponsor;
        public String msg;
        public String rel_name;
        public String rel_icon;

    }
}
