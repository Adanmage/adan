package com.hui.tally;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hui.tally.calendarpackage.Calendarmain;
import com.hui.tally.clockpackage.clockmain;
import com.hui.tally.notepackage.Notemain;

public class MainActivityM1 extends AppCompatActivity {
    //鬧鐘按鈕
    private ImageView clockBton;
    //日曆按鈕
    private ImageView clanderBton;
    //記事本按鈕
    private ImageView noteBton;
    //齒輪按鈕
    private ImageView gearBton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_m1);

        //找到鬧鐘圖片控建
        clockBton = findViewById(R.id.clock_icon);
        //找到日曆圖片控建
        clanderBton = findViewById(R.id.clander_icon);
        //找到筆記本圖片控建
        noteBton = findViewById(R.id.note_icon);
        //找到齒輪圖片控建
        gearBton = findViewById(R.id.gear_icon);



        //命令跳轉鬧鐘頁面
        clockBton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clockIntent = new Intent(MainActivityM1.this, clockmain.class);
                startActivity(clockIntent);
            }
        });

        //命令跳轉記帳本頁面
        gearBton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent savemoneyIntent = new Intent(MainActivityM1.this, MainActivity.class);
                startActivity(savemoneyIntent);
            }
        });

        //命令跳轉筆記頁面
        noteBton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noteIntent = new Intent(MainActivityM1.this, Notemain.class);
                startActivity(noteIntent);
            }
        });

        //命令跳轉行事曆頁面
        clanderBton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noteIntent = new Intent(MainActivityM1.this, Calendarmain.class);
                startActivity(noteIntent);
            }
        });
    }
}