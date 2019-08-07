package com.sky.todoodle.Adapter;


import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.sky.todoodle.MainActivity;
import com.sky.todoodle.Model.ToDo;
import com.sky.todoodle.R;

import java.util.List;

class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener
{
    ItemClickListener itemClickListener;
    TextView item_title,item_description;


    public ListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);

        item_title=(TextView)itemView.findViewById(R.id.item_title);
        item_description=(TextView)itemView.findViewById(R.id.item_description);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Select the action");
        contextMenu.add(0,0,getAdapterPosition(),"DELETE");
        //contextMenu.add(0,0,getAdapterPosition(),"COMPLETED");

    }
}

public class ListItemAdapter extends RecyclerView.Adapter<ListItemViewHolder> {

    MainActivity mainActivity;
    List<ToDo> toDoList;

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mainActivity.getBaseContext());
        View view = inflater.inflate(R.layout.item_list,parent,false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {


        holder.item_title.setText(toDoList.get(position).getTitle());
        holder.item_description.setText(toDoList.get(position).getDescription() );

        holder.setItemClickListener(new ItemClickListener(){
            @Override
            public void onClick(View view, int position, boolean isLongClick){
                mainActivity.title.setText(toDoList.get(position).getTitle() );
                mainActivity.description.setText(toDoList.get(position).getDescription() );

                mainActivity.isUpdate=true;
                mainActivity.idUpdate=toDoList.get(position).getId();

            }

        });
    }
    @Override
    public int getItemCount() {
        return toDoList.size();
    }

}
