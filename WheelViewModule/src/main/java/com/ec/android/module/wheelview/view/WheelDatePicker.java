package com.ec.android.module.wheelview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ec.android.module.wheelview.sourse.LoopView;
import com.ec.android.module.wheelview.sourse.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 日期选择控件
 *
 * @author EC
 *         date by 2016-05-10
 */
public class WheelDatePicker extends RelativeLayout {
    private int mYear = -1;
    private int mMonth = -1;
    private int mDay = -1;
    //
    private LoopView mYearLv;
    private LoopView mMonthLv;
    private LoopView mDayLv;
    //
    private List<String> mYearList = new ArrayList<>();
    private List<String> mMonthList = new ArrayList<>();
    private List<String> mDayList = new ArrayList<>();

    public WheelDatePicker(Context context) {
        this(context, null);
    }

    public WheelDatePicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WheelDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void init(int year, int month, int day) {
        this.mYear = year;
        this.mMonth = month;
        this.mDay = day;
        //
        initYearLv();
        initMonthLv();
    }

    private void initView() {
        final LinearLayout linearLayout = new LinearLayout(getContext());

        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        //
        final LayoutParams lyLP = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.addView(linearLayout, lyLP);
        //
        mYearLv = new LoopView(getContext());
        mMonthLv = new LoopView(getContext());
        mDayLv = new LoopView(getContext());
        //
        mYearLv.setTextSize(25);
        mYearLv.setViewPadding(6, 0, 6, 0);

        mMonthLv.setTextSize(25);
        mMonthLv.setViewPadding(6, 0, 6, 0);

        mDayLv.setTextSize(25);
        mDayLv.setViewPadding(6, 0, 6, 0);
        //
        final LinearLayout.LayoutParams monthLP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        monthLP.setMargins(30, 0, 30, 0);

        linearLayout.addView(mYearLv);
        linearLayout.addView(mMonthLv, monthLP);
        linearLayout.addView(mDayLv);
        //
        if (mYear == -1 || mMonth == -1 || mDay == -1) {
            Calendar calendar = Calendar.getInstance();

            mYear = calendar.get(Calendar.YEAR);
            //加一才是真正的月数
            mMonth = calendar.get(Calendar.MONTH) + 1;
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
        }
        initYearLv();
        initMonthLv();
    }

    private void initYearLv() {
        //默认选中的
        int selectPosition = 0;

        mYearList.clear();

        for (int i = 2000; i <= 2100; i++) {
            mYearList.add(Integer.toString(i));
            //
            if (i == mYear) {
                selectPosition = i - 2000;
            }
        }
        //
        mYearLv.setItems(mYearList);
        mYearLv.setInitPosition(selectPosition);
    }

    private void initMonthLv() {
        //默认选中的
        int selectPosition = 0;

        mMonthList.clear();

        for (int i = 1; i <= 12; i++) {
            mMonthList.add(Integer.toString(i));
            //
            if (i == mMonth) {
                selectPosition = i - 1;
            }
        }
        //
        mMonthLv.setItems(mMonthList);
        mMonthLv.setInitPosition(selectPosition);

        mMonthLv.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                String monthStr = mMonthList.get(index);
                //
                int month = Integer.parseInt(monthStr);

                updateDayLv(month);
            }
        });
        ///
        updateDayLv(mMonth);
    }

    private void updateDayLv(int updateMonth) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(mYear, updateMonth - 1, mDay);
        //当前选择月的实际天数
        int dayNum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        //默认选中的
        int selectPosition = 0;

        mDayList.clear();

        for (int i = 1; i <= dayNum; i++) {
            mDayList.add(Integer.toString(i));
            //
            if (i == mDay) {
                selectPosition = i - 1;
            }
        }
        //
        mDayLv.setItems(mDayList);
        mDayLv.setInitPosition(selectPosition);
    }

    public int getYear() {
        int selectedItem = mYearLv.getSelectedItem();
        String year = mYearList.get(selectedItem);

        return Integer.parseInt(year);
    }

    public int getMonth() {
        int selectedItem = mMonthLv.getSelectedItem();
        String month = mMonthList.get(selectedItem);

        return Integer.parseInt(month);
    }

    public int getDay() {
        int selectedItem = mDayLv.getSelectedItem();
        String day = mDayList.get(selectedItem);

        return Integer.parseInt(day);
    }

    /**
     * 设置显示的条目，推荐奇数，默认5个
     *
     * @param i
     * @deprecated 整个LoopView有问题，不支持用户自定义的height，所以显示的条目也不准确
     */
    public void setVisibleItems(int i) {
        mYearLv.setVisibleItems(i);
        mMonthLv.setVisibleItems(i);
        mDayLv.setVisibleItems(i);
    }


}
