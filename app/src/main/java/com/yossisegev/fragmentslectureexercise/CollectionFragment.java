package com.yossisegev.fragmentslectureexercise;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yossisegev.fragmentslectureexercise.entities.Collection;

/**
 * Created by Yossi Segev on 09/12/2017.
 */

public class CollectionFragment extends Fragment implements View.OnClickListener{

    private static final String ARGS_COLLECTION_ID = "args_collection_id";
    private CollectionFragmentListener listener;
    private Collection collection;
    private View previewLayout;
    private View listLayout;
    private RecyclerView recyclerView;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private ImageView previewImage;
    private ImageView closeButton;
    private Button visitCollectionButton;

    public static CollectionFragment newInstance(int collectionId) {
        CollectionFragment collectionFragment = new CollectionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_COLLECTION_ID, collectionId);
        collectionFragment.setArguments(bundle);
        return collectionFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CollectionFragmentListener) {
            listener = (CollectionFragmentListener) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int collectionId = getArguments().getInt(ARGS_COLLECTION_ID);
        collection = CollectionsRepository.getInstance().getCollection(collectionId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection, container, false);

        // Init views
        previewLayout = view.findViewById(R.id.fragment_collection_preview_layout);
        previewLayout.setOnClickListener(this);
        listLayout = view.findViewById(R.id.fragment_collection_list_layout);
        closeButton = view.findViewById(R.id.fragment_collection_close_btn);
        closeButton.setOnClickListener(this);
        previewImage = view.findViewById(R.id.fragment_collection_preview_image);
        recyclerView = view.findViewById(R.id.fragment_collection_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        nameTextView = view.findViewById(R.id.fragment_collection_name);
        nameTextView.setText(collection.getTitle());
        descriptionTextView = view.findViewById(R.id.fragment_collection_description);
        descriptionTextView.setText(collection.getDescription());
        visitCollectionButton = view.findViewById(R.id.fragment_collection_visit_btn);
        visitCollectionButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showPreviewLayout();
    }

    private void showListLayout() {
        previewLayout.setVisibility(View.GONE);
        listLayout.setVisibility(View.VISIBLE);
        PhotoListAdapter adapter = new PhotoListAdapter(Picasso.with(getContext()), collection.getPreviewPhotos());
        recyclerView.setAdapter(adapter);
    }

    private void showPreviewLayout() {
        listLayout.setVisibility(View.GONE);
        previewLayout.setVisibility(View.VISIBLE);
        String imageUrl = collection.getPreviewPhotos().get(0).getUrls().getSmall();
        Picasso.with(getContext()).load(imageUrl).into(previewImage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.fragment_collection_preview_layout:
                showListLayout();
                break;

            case R.id.fragment_collection_close_btn:
                showPreviewLayout();
                break;

            case R.id.fragment_collection_visit_btn:
                if (listener != null) listener.onCollectionSelected(collection);
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface CollectionFragmentListener {
        void onCollectionSelected(Collection collection);
    }
}
