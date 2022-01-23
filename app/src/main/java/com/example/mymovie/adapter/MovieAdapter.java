package com.example.mymovie.adapter;

import static com.example.mymovie.util.Constant.BASE_IMAGE_URL;
import static com.example.mymovie.util.Constant.BASE_URL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovie.R;
import com.example.mymovie.model.DataRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public Context context;
    public ArrayList<DataRequest> dataRequests;

    public MovieAdapter(Context context, ArrayList<DataRequest> dataRequests) {
        this.context = context;
        this.dataRequests = dataRequests;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        final DataRequest dataRequest = dataRequests.get(position);

        Picasso.get()
                .load(BASE_IMAGE_URL + dataRequest.getPosterPath())
                .placeholder(R.mipmap.ic_placeholder)
                .error(R.mipmap.ic_placeholder)
                .into(holder.iv_movie);

        holder.tv_title.setText(dataRequest.getTitle());
        holder.tv_date.setText(dataRequest.getReleaseDate());
        holder.tv_overview.setText(dataRequest.getOverview());
    }

    @Override
    public int getItemCount() {
        return (dataRequests == null) ? 0 : dataRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_movie;
        private TextView tv_title, tv_date, tv_overview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_movie = itemView.findViewById(R.id.imageView_movie);

            tv_title = itemView.findViewById(R.id.textView_title);
            tv_date = itemView.findViewById(R.id.textView_releaseDate);
            tv_overview = itemView.findViewById(R.id.textView_overview);
        }
    }
}
