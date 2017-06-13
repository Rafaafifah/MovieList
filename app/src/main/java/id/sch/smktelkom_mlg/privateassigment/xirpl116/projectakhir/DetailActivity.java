package id.sch.smktelkom_mlg.privateassigment.xirpl116.projectakhir;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class DetailActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://api.nytimes.com/svc/movies/v2/reviews/search.json?api-key=211b58de33064238b255f09b04a38274";
    public TextView textViewHeadet;
    public TextView textViewDescet;
    public TextView textViewReview;
    public ImageView imageViewOtof;
    public String imageURL;
    boolean isPressed = true;
    private Integer mPostkey = null;
    private String jenis = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPostkey = getIntent().getExtras().getInt("blog_id");
        loadRecyclerViewData(); //menampilkan detail pada sesuatu yg dipilih

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.buttonFav);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPressed == true) {
                    String data = "Berhasil ditambahkan ke favorit";
                    doSave();

                    Toast.makeText(DetailActivity.this, data, Toast.LENGTH_SHORT).show();

                    isPressed = false;
                } else {
                    Toast.makeText(DetailActivity.this, "Sudah ditambahkan ke favorit", Toast.LENGTH_LONG).show();
                }

            }
        });
        textViewHeadet = (TextView) findViewById(R.id.textViewHeadet);
        textViewDescet = (TextView) findViewById(R.id.textViewDescet);
        textViewReview = (TextView) findViewById(R.id.textViewReview);
        imageViewOtof = (ImageView) findViewById(R.id.imageViewDetail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void doSave() {
        String jenis = this.jenis;
        String imageurl = "multimedia" + "src";
        String title = textViewHeadet.getText().toString();
        String year = textViewDescet.getText().toString();
        String desc = textViewReview.getText().toString();
        MovieDBItem movieDBItem = new MovieDBItem(imageurl, title, year, jenis, desc);
        movieDBItem.save();
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("results");
                            JSONObject o = array.getJSONObject(mPostkey);

                            setTitle(" ");
                            textViewHeadet.setText(o.getString("display_title"));
                            textViewDescet.setText(" Produser " + "\n" + o.getString("byline"));
                            textViewReview.setText(" Sumary " + "\n" + o.getString("summary_short"));
                            //url = o.getJSONObject("link").getString("url");
                            Glide
                                    .with(DetailActivity.this)
                                    .load(o.getJSONObject("multimedia").getString("src"))
                                    .into(imageViewOtof);


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
