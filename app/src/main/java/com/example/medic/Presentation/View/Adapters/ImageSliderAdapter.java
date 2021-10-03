package com.example.medic.Presentation.View.Adapters;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.MainActivity;
import com.example.medic.R;

import java.io.FileNotFoundException;
import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder> {

    List<String> images;
    MainActivity mActivity;
    ImageView imageView;

    public ImageSliderAdapter(List<String> images, boolean adding, MainActivity activity) {
        this.images = images;
        mActivity = activity;

        if (adding){
            this.images.add(null);
        }
    }

    @NonNull
    @Override
    public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO:
        return new ImageSliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_element, parent, false));
        //return new ImageSliderViewHolder(ImageElementBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderViewHolder holder, int position) {
        if (images.get(position) == null){
            holder.view.findViewById(R.id.imageContent).setVisibility(View.GONE);
            holder.view.findViewById(R.id.addButtonImage).setVisibility(View.VISIBLE);
            holder.view.findViewById(R.id.addButtonImage).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mActivity != null){
                        mActivity.getActivityResultRegistry().register("key", 
                                new ActivityResultContracts.OpenDocument(),
                                new ActivityResultCallback<Uri>() {
                                    @Override
                                    public void onActivityResult(Uri result) {
                                        mActivity.getApplicationContext().getContentResolver()
                                                .takePersistableUriPermission(result,
                                                        Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                        images.add(images.size() - 1, result.toString());
                                        notifyDataSetChanged();
                                    }
                                }).launch(new String[]{"image/*"});
                    }
                }
            });
        } else {
            holder.view.findViewById(R.id.addButtonImage).setVisibility(View.GONE);
            holder.view.findViewById(R.id.imageContent).setVisibility(View.VISIBLE);

            if (mActivity != null){
                try {
                    imageView = holder.view.findViewById(R.id.imageContent);
                    imageView.setImageBitmap(BitmapFactory.decodeFileDescriptor(
                                    mActivity.getApplicationContext().getContentResolver().openFileDescriptor(
                                            Uri.parse(images.get(position)), "r").getFileDescriptor()
                                    )
                            );
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class ImageSliderViewHolder extends RecyclerView.ViewHolder{

        View view;

        public ImageSliderViewHolder(View itemView) {
            super(itemView);

            view = itemView;
        }
    }
}
