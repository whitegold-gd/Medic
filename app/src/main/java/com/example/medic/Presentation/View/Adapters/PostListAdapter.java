package com.example.medic.Presentation.View.Adapters;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.DI.ServiceLocator;
import com.example.medic.Domain.Model.Post;
import com.example.medic.MainActivity;
import com.example.medic.R;
import com.example.medic.databinding.PostListElementBinding;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {
    private List<Post> localDataSet;
    private TextView localTextView;
    private MainActivity mainActivity;
    private RecyclerView recyclerView;

    public PostListAdapter(List<Post> localDataSet, MainActivity activity){
        this.localDataSet = localDataSet;
        this.mainActivity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PostListElementBinding binding = PostListElementBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.postCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                String json = ServiceLocator.getInstance().getGson().toJson(localDataSet.get(holder.getAdapterPosition()));

                bundle.putString("Post", json);

                Navigation.findNavController(v).navigate(R.id.action_postList_to_postFragment, bundle);
            }
        });

        holder.binding.title.setText(localDataSet.get(position).getTitle());

        holder.binding.body.setText(localDataSet.get(position).getBody());

        holder.binding.date.setText(localDataSet.get(position).getDate().toString());

        holder.binding.tags.setText(localDataSet.get(position).getTags());

        holder.binding.nameOfAuthor.setText(localDataSet.get(position).getUser().getFirstName());

        if (localDataSet.get(position).getImages() != null && !localDataSet.get(position).getImages().isEmpty()){
            holder.binding.imageSlider
                    .setVisibility(View.VISIBLE);
            holder.binding.imageSlider.setAdapter(new ImageSliderAdapter(localDataSet.get(position).getImages(),
                    false,
                    mainActivity));
        } else {
            holder.binding.imageSlider
                    .setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private PostListElementBinding binding;

        public ViewHolder(@NonNull PostListElementBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
