package com.hui.tally.calendarpackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.hui.tally.R;
import static com.hui.tally.calendarpackage.CalendarUtils.daysInMonthArray;
import static com.hui.tally.calendarpackage.CalendarUtils.monthYearFromDate;


public class Calendarmain extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

//當活動被創建時會執行這個方法。
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main);
        initWidgets();
        //CalendarUtils.selectedDate 被設置為當前日期。
        CalendarUtils.selectedDate = LocalDate.now();
        //setMonthView 方法被調用，它用來顯示當前月份的行事曆
        setMonthView();

    }


    //這個方法初始化了 calendarRecyclerView 和 monthYearText
    // ，它們是 XML 布局文件中的元件。
    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }


    //這個方法用於設置月曆的顯示。
    private void setMonthView()
    {
        //它將 monthYearText 的文字設置為 CalendarUtils.selectedDate 的年份和月份。
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        //使用 daysInMonthArray 方法獲取當前月份的所有日期。
        ArrayList<LocalDate> daysInMonth = daysInMonthArray();
        //創建一個 CalendarAdapter 對象，用於填充 RecyclerView，並將日期傳遞給它。
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        //設置 RecyclerView 的佈局管理器為網格佈局，每行7個項目（7天一週）
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    //這兩個方法分別處理點擊上個月和下個月按鈕的事件。
    //它們修改 CalendarUtils.selectedDate 的值以顯示上一個月或下一個月的行事曆
    // ，然後調用 setMonthView 更新畫面。
    public void previousMonthAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    //這個方法實現了 CalendarAdapter.OnItemListener 接口，處理點擊日期的事件。
    //當使用者點擊日期時，這個方法會更新 CalendarUtils.selectedDate 並重新顯示月曆。
    @Override
    public void onItemClick(int position, LocalDate date)
    {
        if(date != null)
        {
            CalendarUtils.selectedDate = date;
            setMonthView();
        }
    }

    //weeklyAction 方法：
    //這個方法處理點擊"週視圖"按鈕的事件。
    //當按鈕被點擊時，它啟動一個新的活動 WeekViewActivity
    public void weeklyAction(View view)
    {
        startActivity(new Intent(this, WeekViewActivity.class));
    }
}








