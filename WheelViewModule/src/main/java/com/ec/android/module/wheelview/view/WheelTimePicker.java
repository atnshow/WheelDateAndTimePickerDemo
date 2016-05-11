package com.ec.android.module.wheelview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ec.android.module.wheelview.sourse.LoopView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 时间选择控件（24小时制）,可选择是否显示秒的控件
 *
 * @author EC
 *         date by 2016-05-10
 */
public class WheelTimePicker extends RelativeLayout {
    private int mHour = -1;
    private int mMinute = -1;
    private int mSecond = -1;
    //是否显示秒
    private boolean isShowSecond = true;
    //
    private LoopView mHourLv;
    private LoopView mMinuteLv;
    private LoopView mSecondLv;
    //
    private List<String> mHourList = new ArrayList<>();
    private List<String> mMinuteList = new ArrayList<>();
    private List<String> mSecondList = new ArrayList<>();

    public WheelTimePicker(Context context) {
        this(context, null);
    }

    public WheelTimePicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WheelTimePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化 时、分
     *
     * @param hour
     * @param minute
     */
    public void init(int hour, int minute) {
        init(hour, minute, 0);
    }

    /**
     * 初始化 时、分、秒
     *
     * @param hour
     * @param minute
     * @param second
     */
    public void init(int hour, int minute, int second) {
        this.mHour = hour;
        this.mMinute = minute;
        this.mSecond = second;
        //
        initHourLv();
        initMinuteLv();
        initSecondLv();
    }

    private void initView() {
        final LinearLayout linearLayout = new LinearLayout(getContext());

        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        //
        final LayoutParams lyLP = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.addView(linearLayout, lyLP);
        //
        mHourLv = new LoopView(getContext());
        mMinuteLv = new LoopView(getContext());
        mSecondLv = new LoopView(getContext());
        //
        mHourLv.setTextSize(25);
        mHourLv.setViewPadding(6, 0, 6, 0);

        mMinuteLv.setTextSize(25);
        mMinuteLv.setViewPadding(6, 0, 6, 0);

        mSecondLv.setTextSize(25);
        mSecondLv.setViewPadding(6, 0, 6, 0);
        ////
        final LinearLayout.LayoutParams monthLP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        monthLP.setMargins(30, 0, 30, 0);

        linearLayout.addView(mHourLv);
        linearLayout.addView(mMinuteLv, monthLP);
        linearLayout.addView(mSecondLv);
        //
        if (mHour == -1 || mMinute == -1 || mSecond == -1) {
            Calendar calendar = Calendar.getInstance();

            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinute = calendar.get(Calendar.MINUTE);
            mSecond = calendar.get(Calendar.SECOND);
        }
        initHourLv();
        initMinuteLv();
        initSecondLv();
    }

    private void initHourLv() {
        //默认选中的
        int selectPosition = 0;

        mHourList.clear();

        for (int i = 0; i <= 23; i++) {
            mHourList.add(Integer.toString(i));
            //
            if (i == mHour) {
                selectPosition = i;
            }
        }
        //
        mHourLv.setItems(mHourList);
        mHourLv.setInitPosition(selectPosition);
    }

    private void initMinuteLv() {
        //默认选中的
        int selectPosition = 0;

        mMinuteList.clear();

        for (int i = 0; i <= 59; i++) {
            mMinuteList.add(Integer.toString(i));
            //
            if (i == mMinute) {
                selectPosition = i;
            }
        }
        //
        mMinuteLv.setItems(mMinuteList);
        mMinuteLv.setInitPosition(selectPosition);
    }

    private void initSecondLv() {
        //默认选中的
        int selectPosition = 0;

        mSecondList.clear();

        for (int i = 0; i <= 59; i++) {
            mSecondList.add(Integer.toString(i));
            //
            if (i == mSecond) {
                selectPosition = i;
            }
        }
        //
        mSecondLv.setItems(mSecondList);
        mSecondLv.setInitPosition(selectPosition);
    }

    public int getHour() {
        int selectedItem = mHourLv.getSelectedItem();
        String hour = mHourList.get(selectedItem);

        return Integer.parseInt(hour);
    }

    public int getMinute() {
        int selectedItem = mMinuteLv.getSelectedItem();
        String minute = mMinuteList.get(selectedItem);

        return Integer.parseInt(minute);
    }

    public int getSecond() {
        int selectedItem = mSecondLv.getSelectedItem();
        String second = mSecondList.get(selectedItem);

        return Integer.parseInt(second);
    }

    /**
     * 设置显示的条目，推荐奇数，默认5个
     *
     * @param i
     * @deprecated 整个LoopView有问题，不支持用户自定义的height，所以显示的条目也不准确
     */
    public void setVisibleItems(int i) {
        mHourLv.setVisibleItems(i);
        mMinuteLv.setVisibleItems(i);
        mSecondLv.setVisibleItems(i);
    }

    /**
     * 是否显示秒
     *
     * @param flag
     */
    public void setShowSecond(boolean flag) {
        if (mSecondLv != null) {
            if (flag) {
                mSecondLv.setVisibility(VISIBLE);
            } else {
                mSecondLv.setVisibility(GONE);
            }
        }
    }
}
