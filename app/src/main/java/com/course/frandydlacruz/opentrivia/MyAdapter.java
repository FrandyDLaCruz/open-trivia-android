package com.course.frandydlacruz.opentrivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.course.frandydlacruz.opentrivia.entities.Category;
import com.course.frandydlacruz.opentrivia.interfaces.ListItemClickListener;

//import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private Category categories;
    private ListItemClickListener listItemClickListener;

    public MyAdapter(Category categories, ListItemClickListener listItemClickListener) {
        this.categories = categories;
        this.listItemClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.trivia_category_list_items;
        LayoutInflater inflater = LayoutInflater.from(context);

        boolean shouldAttachToParentInmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentInmediately);

        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);

        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(categories.getTriviaCategories().get(position).getName(), categories.getTriviaCategories().get(position).getId(), listItemClickListener);
    }

    @Override
    public int getItemCount() {
        return categories.getTriviaCategories().size();
    }
}
