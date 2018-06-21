package com.deneme.caulis.caulis.Group;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.deneme.caulis.caulis.R;
import com.deneme.caulis.caulis.classes.CaulisGroup;

import java.util.ArrayList;

public class GroupAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<CaulisGroup> list = new ArrayList<CaulisGroup>();
    private Context context;
    private int cnt;

    public GroupAdapter(ArrayList<CaulisGroup> list, Context context){
        this.list = list;
        this.context = context;
        cnt = 0;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int i) {
        cnt++;
        return cnt;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.groupelement, null);
        }
        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position).getName());

        //Handle buttons and add onClickListeners
        Button goToGroup = (Button)view.findViewById(R.id.goToGroupButton);

        goToGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(GroupAdapter.this.context,GroupChatActivity.class);
                    i.putExtra("group",list.get(position));
                    context.startActivity(i);//denenmedi
                }catch (Exception e){
                    e.printStackTrace();
                }
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
