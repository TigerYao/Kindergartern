package com.junbaole.kindergartern.data.model;

import java.util.ArrayList;

/**
 * Created by TigerYao on 16/6/30.
 */
public class BaseReponseModel<T> {
    public int code;
    public T data;
    public ArrayList<String> messages;

    public boolean isSuccess(){
        if(code==2000){
            return true;
        }
        return false;
    }

    public String getString(){
        StringBuffer messageBuff = new StringBuffer();
        for(String msg:messages){
            messageBuff.append(msg).append("\n");
        }
        return messageBuff.toString();
    }

    @Override
    public String toString() {
        return "BaseReponseModel{" +
                "code=" + code +
                ", data=" + data +
                ", messages=" + messages +
                '}';
    }
}
