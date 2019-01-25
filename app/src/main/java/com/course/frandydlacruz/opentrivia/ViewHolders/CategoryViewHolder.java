package com.course.frandydlacruz.opentrivia.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.course.frandydlacruz.opentrivia.R;
import com.course.frandydlacruz.opentrivia.interfaces.ListItemClickListener;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private Button categoryItem;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryItem = itemView.findViewById(R.id.btn_category);
    }

    public void bind(String categoryName, final int categoryId, final ListItemClickListener listener) {
        categoryItem.setText(categoryName);

        categoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListItemClick(categoryId);
            }
        });
    }
}
