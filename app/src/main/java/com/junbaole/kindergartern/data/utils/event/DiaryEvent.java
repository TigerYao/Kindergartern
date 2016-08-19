package com.junbaole.kindergartern.data.utils.event;

import com.junbaole.kindergartern.data.model.DiaryInfo;

/**
 * Created by yaohu on 16/8/17.
 */
public class DiaryEvent {

    public boolean isOk;
    public boolean isDiary;
    public DiaryInfo diaryInfo;

    public DiaryEvent(boolean isOk) {
        this.isOk = isOk;
    }

    public DiaryEvent(boolean isOk, boolean isDiary, DiaryInfo diaryInfo) {
        this.isOk = isOk;
        this.isDiary = isDiary;
        this.diaryInfo = diaryInfo;
    }
}
