package com.course.frandydlacruz.opentrivia;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private Button categoryItem;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryItem = itemView.findViewById(R.id.btn_category);
    }

    public void bind(String categoryName, final int position, final ListItemClickListener listener) {
        categoryItem.setText(categoryName);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onListItemClick(position);
//            }
//        });
        categoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListItemClick(position);
            }
        });
    }
}
