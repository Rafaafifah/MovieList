package id.sch.smktelkom_mlg.privateassigment.xirpl116.projectakhir;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Smktelkom on 5/16/2017.
 */

public class MovieDBAdapter extends RecyclerView.Adapter<MovieDBAdapter.ViewHolder> {
    private final Context context;
    ArrayList<MovieDBItem> fItem;


    public MovieDBAdapter(ArrayList<MovieDBItem> movieDBItems, Context context) {
        this.fItem = movieDBItems;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final MovieDBItem movieDBItem = fItem.get(position);
        holder.tvTitle.setText(movieDBItem.title);
        holder.tvYear.setText(movieDBItem.year);
        holder.tvDesc.setText(movieDBItem.desc);

        Glide
                .with(context)
                .load(movieDBItem.imageUrl)
                .into(holder.ivPoster);

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MovieDBItem movieDBItem1 = fItem.get(position);
                fItem.remove(position);
                movieDBItem1.delete();
                MovieDBAdapter.this.notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return fItem.size();
    }

    public interface IMovieDBAdapter {
        void doDelete(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvYear;
        public ImageView ivPoster;
        public Button btDelete;
        public LinearLayout llFavorite;
        public TextView tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            tvYear = (TextView) itemView.findViewById(R.id.textViewYear);
            ivPoster = (ImageView) itemView.findViewById(R.id.imageViewFavorite);
            btDelete = (Button) itemView.findViewById(R.id.buttonDelete);
            llFavorite = (LinearLayout) itemView.findViewById(R.id.LinearLayoutFavorite);
            tvDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
        }

    }

}
