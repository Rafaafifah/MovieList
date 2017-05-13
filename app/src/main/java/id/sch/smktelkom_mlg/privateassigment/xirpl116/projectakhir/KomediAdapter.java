package id.sch.smktelkom_mlg.privateassigment.xirpl116.projectakhir;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Smktelkom on 5/11/2017.
 */

class KomediAdapter extends RecyclerView.Adapter<KomediAdapter.ViewHolder> {

    private List<KomediItem> komediListItems;
    private Context context;

    public KomediAdapter(List<KomediItem> komediListItems, Context context) {
        this.komediListItems = komediListItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.komedi_item, parent, false);
        return new ViewHolder(v);
    } //menyambungkan ke xmlnya

    @Override
    public void onBindViewHolder(KomediAdapter.ViewHolder holder, final int position) {
        final KomediItem komediItem = komediListItems.get(position);
        holder.tvTitle.setText(komediItem.getTitle());
        Glide
                .with(context)
                .load("http://image.tmdb.org/t/p/w500" + komediItem.getImageUrl())
                .into(holder.ivData);

        holder.rLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, komediItem.getTitle() + " selected", Toast.LENGTH_LONG).show();
                Intent singleBlogIntent = new Intent(context, KomediDetailActivity.class);
                singleBlogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                singleBlogIntent.putExtra("blog_id", position);
                context.startActivity(singleBlogIntent);
            }
        });
    }//mau diapakan viewnya

    @Override
    public int getItemCount() {
        return komediListItems.size();
    }//mengambil data, brp banyak data yg akan ditampilkan


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivData;
        public TextView tvTitle;
        public TextView tvDesc;
        public RelativeLayout rLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            ivData = (ImageView) itemView.findViewById(R.id.imageViewData);
            tvTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            tvDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            rLayout = (RelativeLayout) itemView.findViewById(R.id.LinearLayout);
        }
    }//inisialisasi
}
