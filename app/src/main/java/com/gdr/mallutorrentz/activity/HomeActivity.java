package com.gdr.mallutorrentz.activity;

/**
 * Coded by Irshad
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gdr.mallutorrentz.MainInterface;
import com.gdr.mallutorrentz.R;
import com.gdr.mallutorrentz.adapter.HomeUpdates;
import com.gdr.mallutorrentz.adapter.LatestAdapter;
import com.gdr.mallutorrentz.adapter.MalayalamAdapter;
import com.gdr.mallutorrentz.model.HomeUpdateModel;
import com.gdr.mallutorrentz.model.LatestModel;
import com.gdr.mallutorrentz.model.MalayalamModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HomeActivity extends AppCompatActivity {

    LinearLayout ad_gone;
    RecyclerView recyclerView;
    ArrayList < HomeUpdateModel > arrayList = new ArrayList < >();
    HomeUpdates dataAdapter;
    int page = 1, limit = 10;
    ProgressBar progressBar;
    RecyclerView latestRecycler;
    ArrayList < LatestModel > latestModels = new ArrayList < >();
    LatestAdapter latestAdapter;
    RecyclerView malayalamRv;
    ArrayList < MalayalamModel > malayalamModels = new ArrayList < >();
    MalayalamAdapter malayalamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler);
        dataAdapter = new HomeUpdates(arrayList, HomeActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dataAdapter);

        progressBar = findViewById(R.id.progressbar);
        recyclerView.setPadding(6, 0, 0, 0);

        latestRecycler = findViewById(R.id.latest_movie);
        latestAdapter = new LatestAdapter(latestModels, HomeActivity.this);
        LinearLayoutManager latestlayoutmanager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
        latestRecycler.setLayoutManager(latestlayoutmanager);
        latestRecycler.setAdapter(latestAdapter);
        getLatest(page, limit);
        latestRecycler.setPadding(6, 0, 0, 0);

        malayalamRv = findViewById(R.id.malayalam_movie_rv);
        malayalamAdapter = new MalayalamAdapter(malayalamModels, this);
        LinearLayoutManager mlm = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
        malayalamRv.setLayoutManager(mlm);
        malayalamRv.setAdapter(malayalamAdapter);

        getData(page, limit);
        homeAd1(page, limit);
        getMalayalam(page, limit);

    }

    private void getMalayalam(int page, int limit) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/irshad1998/torrentz/latest_movie/").addConverterFactory(ScalarsConverterFactory.create()).build();

        MainInterface mainInterface = retrofit.create(MainInterface.class);
        Call < String > call = mainInterface.MALAYALAM_CALL(page, limit);
        call.enqueue(new Callback < String > () {@Override
        public void onResponse(Call < String > call, Response < String > response) {
            if (response.isSuccessful() && response.body() != null) {

                try {
                    JSONArray jsonArray = new JSONArray(response.body());
                    malayalamResult(jsonArray);
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        }@Override
        public void onFailure(Call < String > call, Throwable t) {

        }
        });

    }

    private void malayalamResult(JSONArray jsonArray) {

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                MalayalamModel mModel = new MalayalamModel();

                mModel.setAThumbnaill(object.getString("thumbnaill"));

                malayalamModels.add(mModel);
            } catch(JSONException e) {
                e.printStackTrace();
            }
            malayalamAdapter = new MalayalamAdapter(malayalamModels, HomeActivity.this);
            malayalamRv.setAdapter(malayalamAdapter);
        }

    }

    private void getLatest(int page, int limit) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/irshad1998/torrentz/latest_movie/").addConverterFactory(ScalarsConverterFactory.create()).build();

        MainInterface mainInterface = retrofit.create(MainInterface.class);
        Call < String > call = mainInterface.LATEST_CALL(page, limit);
        call.enqueue(new Callback < String > () {@Override
        public void onResponse(Call < String > call, Response < String > response) {
            if (response.isSuccessful() && response.body() != null) {

                try {
                    JSONArray jsonArray = new JSONArray(response.body());
                    resultLatest(jsonArray);
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        }@Override
        public void onFailure(Call < String > call, Throwable t) {

        }
        });

    }

    private void resultLatest(JSONArray jsonArray) {

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                LatestModel latestModel = new LatestModel();

                latestModel.setThumbnaill(object.getString("thumbnaill"));

                latestModels.add(latestModel);
            } catch(JSONException e) {
                e.printStackTrace();
            }
            latestAdapter = new LatestAdapter(latestModels, HomeActivity.this);
            latestRecycler.setAdapter(latestAdapter);
        }
    }

    private void homeAd1(int page, int limit) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/irshad1998/torrentz/home_ad_1/").addConverterFactory(ScalarsConverterFactory.create()).build();

        MainInterface mainInterface = retrofit.create(MainInterface.class);
        Call < String > call = mainInterface.FETCH_CALL(page, limit);
        call.enqueue(new Callback < String > () {@Override
        public void onResponse(Call < String > call, Response < String > response) {
            if (response.isSuccessful() && response.body() != null) {
                /* ad 1 section */

                try {
                    JSONArray jsonArray = new JSONArray(response.body());
                    resultFetch(jsonArray);
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        }@Override
        public void onFailure(Call < String > call, Throwable t) {

        }
        });

    }
    private void resultFetch(JSONArray jsonArray) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/irshad1998/torrentz/home_ad_1/").addConverterFactory(ScalarsConverterFactory.create()).build();

        MainInterface mainInterface = retrofit.create(MainInterface.class);
        Call < String > call = mainInterface.FETCH_CALL(page, limit);
        call.enqueue(new Callback < String > () {@Override
        public void onResponse(Call < String > call, Response < String > response) {
            if (response.isSuccessful() && response.body() != null) {

                try {
                    JSONArray jsonArray = new JSONArray(response.body());
                    fetchResult(jsonArray);
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        }@Override
        public void onFailure(Call < String > call, Throwable t) {

        }
        });
    }

    private void fetchResult(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                String thumbnail_large = object.getString("thumbnail_large_url");
                String thubnail_small = object.getString("thumbnail_small_url");
                String ad_logo = object.getString("logo_image_url");
                String ad_tittle = object.getString("ad_tittle_text");
                String ad_text = object.getString("ad_message_text");
                String action_text = object.getString("action_text");
                String action_url = object.getString("action_url");

                ImageView banner = (ImageView) findViewById(R.id.home_ad_1);
                ImageView logo = (ImageView) findViewById(R.id.home_ad_1_logo);
                TextView tittle = (TextView) findViewById(R.id.home_ad_1_tittle);
                tittle.setText(ad_tittle);
                TextView message = (TextView) findViewById(R.id.home_ad_1_message);
                message.setText(ad_text);
                TextView action_txt = (TextView) findViewById(R.id.home_ad_1_action_text);
                action_txt.setText(action_text);
                action_txt.setOnClickListener(new View.OnClickListener() {@Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, CommonWeb.class);
                    intent.putExtra("url", action_url);
                    startActivity(intent);
                }
                });

                Glide.with(HomeActivity.this).load(thumbnail_large).diskCacheStrategy(DiskCacheStrategy.ALL).into(banner);

                Glide.with(HomeActivity.this).load(ad_logo).diskCacheStrategy(DiskCacheStrategy.ALL).into(logo);

            } catch(JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void getData(int page, int limit) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/irshad1998/torrentz/api/").addConverterFactory(ScalarsConverterFactory.create()).build();

        MainInterface mainInterface = retrofit.create(MainInterface.class);
        Call < String > call = mainInterface.STRING_CALL(page, limit);
        call.enqueue(new Callback < String > () {@Override
        public void onResponse(Call < String > call, Response < String > response) {
            if (response.isSuccessful() && response.body() != null) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray = new JSONArray(response.body());
                    parseResult(jsonArray);
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        }@Override
        public void onFailure(Call < String > call, Throwable t) {}
        });
    }

    private void parseResult(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                HomeUpdateModel data = new HomeUpdateModel();

                data.setName(object.getString("name"));
                data.setLanguage(object.getString("language"));
                data.setDescription(object.getString("description"));
                data.setThumbnail(object.getString("thumbnail"));
                data.setDuration(object.getString("duration"));
                data.setTrailerUrl(object.getString("trailer_url"));
                data.setDownloadurl1(object.getString("downloadurl_1"));
                data.setDownloadurl1Magnet(object.getString("downloadurl_1_magnet"));
                data.setDownloadurl1Size(object.getString("downloadurl_1_size"));
                data.setDownloadurl2(object.getString("downloadurl_2"));
                data.setDownloadurl2Magnet(object.getString("downloadurl_2_magnet"));
                data.setDownloadurl2Size(object.getString("downloadurl_2_size"));
                data.setDownloadurl3(object.getString("downloadurl_3"));
                data.setDownloadurl3Magnet(object.getString("downloadurl_3_magnet"));
                data.setDownloadurl3Size(object.getString("downloadurl_3_size"));
                data.setDownloadurl4(object.getString("downloadurl_4"));
                data.setDownloadurl4Magnet(object.getString("downloadurl_4_magnet"));
                data.setDownloadurl4Size(object.getString("downloadurl_4_size"));
                data.setDownloadurl5(object.getString("downloadurl_5"));
                data.setDownloadurl5Magnet(object.getString("downloadurl_5_magnet"));
                data.setDownloadurl5Size(object.getString("downloadurl_5_size"));
                data.setDownloadurl6(object.getString("downloadurl_6"));
                data.setDownloadurl6Magnet(object.getString("downloadurl_6_magnet"));
                data.setDownloadurl6Size(object.getString("downloadurl_6_size"));
                data.setDownloadurl7(object.getString("downloadurl_7"));
                data.setDownloadurl7Magnet(object.getString("downloadurl_7_magnet"));
                data.setDownloadurl7Size(object.getString("downloadurl_7_size"));
                data.setDownloadurl8(object.getString("downloadurl_8"));
                data.setDownloadurl8Magnet(object.getString("downloadurl_8_magnet"));
                data.setDownloadurl8Size(object.getString("downloadurl_8_size"));
                data.setDownloadurl9(object.getString("downloadurl_9"));
                data.setDownloadurl9Magnet(object.getString("downloadurl_9_magnet"));
                data.setDownloadurl9Size(object.getString("downloadurl_9_size"));
                data.setDownloadurl10(object.getString("downloadurl_10"));
                data.setDownloadurl10Magnet(object.getString("downloadurl_10_magnet"));
                data.setDownloadurl10Size(object.getString("downloadurl_10_size"));

                arrayList.add(data);
            } catch(JSONException e) {
                e.printStackTrace();
            }
            dataAdapter = new HomeUpdates(arrayList, HomeActivity.this);
            recyclerView.setAdapter(dataAdapter);
        }
    }

}