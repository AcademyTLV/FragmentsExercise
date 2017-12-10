package com.yossisegev.fragmentslectureexercise;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.yossisegev.fragmentslectureexercise.api.UnsplashApiHelper;
import com.yossisegev.fragmentslectureexercise.api.UnsplashService;
import com.yossisegev.fragmentslectureexercise.entities.Collection;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CollectionFragment.CollectionFragmentListener {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.activity_main_view_pager);

        // API call
        UnsplashApiHelper apiHelper = new UnsplashApiHelper();
        apiHelper.service().getFeaturedCollections().enqueue(new Callback<List<Collection>>() {
            @Override
            public void onResponse(@NonNull Call<List<Collection>> call, @NonNull Response<List<Collection>> response) {
                List<Collection> collections = response.body();
                if (collections != null) {
                    for (Collection collection : collections) {
                        CollectionsRepository.getInstance().saveCollection(collection);
                    }
                    initViewPager();
                } else {
                    showErrorMessage();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Collection>> call, @NonNull Throwable t) {
                showErrorMessage();
            }
        });
    }

    // Init the fragments + view pager
    private void initViewPager() {
        List<Collection> collections = CollectionsRepository.getInstance().getCollections();
        List<CollectionFragment> fragments = new ArrayList<>();
        for (Collection collection : collections) {
            fragments.add(CollectionFragment.newInstance(collection.getId()));
        }
        CollectionsPagerAdapter adapter = new CollectionsPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    private void showErrorMessage() {
        Toast.makeText(this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCollectionSelected(Collection collection) {
        Intent browserIntent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(collection.getLinks().getHtml()));
        startActivity(browserIntent);
    }
}
