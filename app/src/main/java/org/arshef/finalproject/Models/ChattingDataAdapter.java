package org.arshef.finalproject.Models;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.arshef.finalproject.R;

import java.util.List;

public class ChattingDataAdapter extends ArrayAdapter<SingleChat> {
    private Context context;

    public ChattingDataAdapter(Context context, int resource, List<SingleChat> chats) {
        super(context, resource, chats);
        this.context = context;
    }


    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        final SingleChat chat = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chatting, parent, false);
        TextView textView = convertView.findViewById(R.id.msg);
        textView.setText(String.format("%s:%s", chat.user, chat.message));
        return convertView;
    }
}
