package com.junbaole.kindergartern.domain;

/**
 * Created by TigerYao on 16/7/21.
 */
public class SendPhoneEvent {

    public String successMsg;
    public String failMsg;
    public Object obj;
    public int type;
    public int time;
    public boolean isSuccess = false;

    @Override
    public String toString() {
        return "SendPhoneEvent{" +
                "successMsg='" + successMsg + '\'' +
                ", failMsg='" + failMsg + '\'' +
                ", type=" + type +
                ", time=" + time +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
