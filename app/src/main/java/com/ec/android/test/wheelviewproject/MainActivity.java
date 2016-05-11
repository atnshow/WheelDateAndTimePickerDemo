package com.ec.android.test.wheelviewproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ec.android.module.wheelview.view.WheelDatePicker;
import com.ec.android.module.wheelview.view.WheelTimePicker;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.OnDismissListener;
import com.orhanobut.dialogplus.ViewHolder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void chooseDate(View view) {
        final WheelDatePicker chooseDateView = new WheelDatePicker(this);

        chooseDateView.init(2015, 4, 6);

        Holder viewHolder = new ViewHolder(chooseDateView);

        DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(viewHolder)
                .setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(DialogPlus dialog) {
                        int year = chooseDateView.getYear();
                        int month = chooseDateView.getMonth();
                        int day = chooseDateView.getDay();

                        Toast.makeText(getApplicationContext(), "日期--" + year + month + day, Toast.LENGTH_SHORT).show();
                    }
                })
                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();

        dialog.show();
    }

    public void chooseTime(View view) {
        final WheelTimePicker chooseTimeView = new WheelTimePicker(this);

        chooseTimeView.init(21, 55, 13);

        Holder viewHolder = new ViewHolder(chooseTimeView);

        DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(viewHolder)
                .setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(DialogPlus dialog) {
                        final int hour = chooseTimeView.getHour();
                        final int minute = chooseTimeView.getMinute();
                        final int second = chooseTimeView.getSecond();

                        Toast.makeText(getApplicationContext(), "时间--" + hour + minute + second, Toast.LENGTH_SHORT).show();
                    }
                })
                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();

        dialog.show();
    }
}
