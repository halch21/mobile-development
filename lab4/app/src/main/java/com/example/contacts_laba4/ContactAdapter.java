package com.example.contacts_laba4;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context context;
    List<Contact> list;
    private View selected;

    ContactAdapter(Context context, List<Contact> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Contact contact = list.get(position);
        holder.textID.setText(String.valueOf(contact.id));
        holder.textName.setText(contact.name);
        holder.textPhone.setText(contact.phone);
        holder.line.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (selected != null){
                    selected.setBackgroundColor(0);
                }
                v.setBackgroundColor(context.getColor(R.color.colorAccent));
                selected = v;
                Contact contact = list.get(position);
                ((MainActivity) context).cursor = position;
                ((MainActivity) context).editName.setText(contact.name);
                ((MainActivity) context).editPhone.setText(contact.phone);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textID;
        TextView textName;
        TextView textPhone;
        LinearLayout line;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textID = itemView.findViewById(R.id.textID);
            textName = itemView.findViewById(R.id.textName);
            textPhone = itemView.findViewById(R.id.textPhone);
            line = itemView.findViewById(R.id.line);
        }
    }
}
