package com.hui.tally.notepackage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.hui.tally.R;

import io.realm.Realm;
//使用 Realm 數據庫進行初始化，這是一個流行的移動端數據庫，用於存儲和管理筆記數據
public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        MaterialButton saveBtn = findViewById(R.id.savebtn);


        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                long createdTime = System.currentTimeMillis();

                //用於確保數據庫操作的一致性的區域
                realm.beginTransaction();
                //創建一個新的 Note 對象，並將標題、描述和創建時間設定為該對象的屬性
                Note note = realm.createObject(Note.class);
                note.setTitle(title);
                note.setDescription(description);
                note.setCreatedTime(createdTime);
                //新創建的筆記保存到數據庫中
                realm.commitTransaction();
                //顯示一個短暫的 Toast 消息，通知用戶筆記已成功儲存。
                Toast.makeText(getApplicationContext(),"儲存成功!!!",Toast.LENGTH_SHORT).show();
                finish();


            }
        });


    }
}