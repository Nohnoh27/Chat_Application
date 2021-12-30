package com.example.chat_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter(Context ctx, List<User> userList) extends RecyclerView.Adapter<UserAdapter.ViewHolder>
{
    LayoutInflater inflater;
    List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.user_layout,parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

        holder.txt_nom.setText(userList.get(position).getNom());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_nom;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txt_nom=itemView.findViewById(R.id.txt_nom);
        }
    }
}

