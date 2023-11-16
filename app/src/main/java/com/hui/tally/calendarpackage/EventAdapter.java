package com.hui.tally.calendarpackage;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.hui.tally.R;
import java.util.List;


public class EventAdapter extends ArrayAdapter<Event>
{
    private Context context;
    private List<Event> eventList;


    public EventAdapter(@NonNull Context context, List<Event> eventList) {
        super(context, 0, eventList);
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        Event event = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);

        String eventTitle = event.getName();
        eventCellTV.setText(eventTitle);

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu menu = new PopupMenu(context, v);
                menu.getMenu().add("刪除");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("刪除")) {
                            // 在集合中刪除事件
                            Event eventToRemove = eventList.get(position);
                            eventList.remove(eventToRemove);
                            notifyDataSetChanged();
                            Toast.makeText(context, "已刪除事件", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                menu.show();
                return true;
            }
        });

        return convertView;
    }


}
