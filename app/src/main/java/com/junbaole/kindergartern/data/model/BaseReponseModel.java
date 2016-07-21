package com.junbaole.kindergartern.data.model;

import java.util.ArrayList;

/**
 * Created by TigerYao on 16/6/30.
 */
public class BaseReponseModel<T> {
    public int code;
    public T data;
    public ArrayList<String> messages;

    @Override
    public String toString() {
        return "BaseReponseModel{" +
                "code=" + code +
                ", data=" + data +
                ", messages=" + messages +
                '}';
    }
}
