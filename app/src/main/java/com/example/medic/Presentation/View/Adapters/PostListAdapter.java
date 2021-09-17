package com.example.medic.Presentation.View.Adapters;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Domain.Model.Post;
import com.example.medic.R;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {
    private Post[] localDataSet;

    public PostListAdapter(Post[] localDataSet){
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_layer, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(localDataSet[position].getTitle());
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);
        }

        public TextView getTextView(){
            return  textView;
        }
    }
}
