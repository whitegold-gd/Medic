package com.example.medic.Presentation.View.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Domain.Model.Post;
import com.example.medic.R;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {
    private List<Post> localDataSet;
    private TextView localTextView;

    public PostListAdapter(List<Post> localDataSet){
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_list_element, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        localTextView = (TextView)holder.view.findViewById(R.id.title);
        localTextView.setText(localDataSet.get(position).getTitle());

        localTextView = (TextView)holder.view.findViewById(R.id.body);
        localTextView.setText(localDataSet.get(position).getBody());

        localTextView = (TextView)holder.view.findViewById(R.id.date);
        localTextView.setText(localDataSet.get(position).getDate().toString());

        localTextView = (TextView)holder.view.findViewById(R.id.tags);
        localTextView.setText(localDataSet.get(position).getTags());

        localTextView = (TextView)holder.view.findViewById(R.id.nameOfAuthor);
        localTextView.setText(localDataSet.get(position).getNameOfAuthor());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
        }
    }
}
