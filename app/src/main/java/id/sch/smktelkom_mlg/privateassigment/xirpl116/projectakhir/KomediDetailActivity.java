package id.sch.smktelkom_mlg.privateassigment.xirpl116.projectakhir;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KomediDetailActivity extends AppCompatActivity {
    private static final String URL_DATA = "https://api.themoviedb.org/3/discover/movie?with_genres=35&with_cast=23659&sort_by=revenue.desc&api_key=07a414c01835fd0e21580fe28c87a19f";
    public TextView textViewTitle;
    public TextView textViewDesc;
    public TextView textViewRelease;
    public TextView textViewAnother;
    public ImageView imageViewPict;
    public String url;
    private Integer mKey = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komedi_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mKey = getIntent().getExtras().getInt("blog_id");
        loadRecyclerViewData();


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewRelease = (TextView) findViewById(R.id.textViewRelease);
        textViewDesc = (TextView) findViewById(R.id.textViewDesc);
        textViewAnother = (TextView) findViewById(R.id.textViewAnother);
        imageViewPict = (ImageView) findViewById(R.id.imageViewDetail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ac) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(ac);
                            JSONArray array = jsonObject.getJSONArray("results");
                            JSONObject o = array.getJSONObject(mKey);

                            setTitle("");
                            textViewTitle.setText(o.getString("title"));
                            textViewRelease.setText("Release Date " + "\n" + o.getString("release_date"));
                            textViewDesc.setText("Overview " + "\n" + o.getString("overview"));
                            textViewAnother.setText("Popularity : " + "\n" + o.getString("popularity"));
                            url = o.getJSONObject("link").getString("url");
                            Glide
                                    .with(KomediDetailActivity.this)
                                    .load("http://image.tmdb.org/t/p/w500" + o.getString("poster_path"))
                                    .into(imageViewPict);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                    }

                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
