package com.junbaole.kindergartern.widget.ptr;

public interface PtrTrigger {
    void onPrepare();

    void onMove(int y, boolean isComplete, boolean automatic);

    void onRelease();

    void onComplete();

    void onReset();
}