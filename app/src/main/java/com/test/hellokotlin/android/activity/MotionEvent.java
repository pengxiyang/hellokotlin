package com.test.hellokotlin.android.activity;

/**
 * @author pengxy
 * @date 2024/4/15 001511:19
 * @des
 */
public class MotionEvent{
    public static final int ACTION_DOWN             = 0;
    public static final int ACTION_UP               = 1;
    public static final int ACTION_MOVE             = 2;
    public static final int ACTION_CANCEL           = 3;
    private int actionMasked;

    private int x;
    private int y;
    public MotionEvent() {
    }

    public MotionEvent(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setActionMasked(int actionMasked) {
        this.actionMasked = actionMasked;
    }
}