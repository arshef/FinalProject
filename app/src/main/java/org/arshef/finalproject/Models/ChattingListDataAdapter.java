package org.arshef.finalproject.Models;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.arshef.finalproject.Activities.ChattingActivity;
import org.arshef.finalproject.R;

import java.util.List;

public class ChattingListDataAdapter extends ArrayAdapter<Chat> {
    private Context context;

    public ChattingListDataAdapter(Context context, int resource, List<Chat> chats) {
        super(context, resource, chats);
        this.context = context;
    }


    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        final Chat chat = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat_list, parent, false);
        TextView textView = convertView.findViewById(R.id.chatUsrTxt);
        textView.setText(chat.getBUser().Username);
        Button button = convertView.findViewById(R.id.selectChatBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChattingActivity.class);
                intent.putExtra("usr",chat.getBUser().Username);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
