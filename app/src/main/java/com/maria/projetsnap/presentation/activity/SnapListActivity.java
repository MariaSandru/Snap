package com.maria.projetsnap.presentation.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.maria.projetsnap.R;
import com.maria.projetsnap.model.Snap;
import com.maria.projetsnap.presentation.adapter.SnapAdapter;
import com.maria.projetsnap.presentation.loader.SnapLoader;

import java.util.ArrayList;
import java.util.List;

public class SnapListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Snap>> {

RecyclerView mRecyclerView;
    List<Snap> mSnaps = new ArrayList<>();
    SnapAdapter mAdapter;
    RelativeLayout mSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSpinner = findViewById(R.id.spinner);
        mRecyclerView=findViewById(R.id.recylcerid);
        
        mAdapter = new SnapAdapter(mSnaps,this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        initData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initData(){
        getSupportLoaderManager().initLoader(0,null,this);
        mSpinner.setVisibility(View.VISIBLE);
    }
    @NonNull
    @Override
    public Loader<List<Snap>> onCreateLoader(int i, @Nullable Bundle bundle) {

        return new SnapLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Snap>> loader, List<Snap> snaps) {
        mSnaps.addAll(snaps);
        mAdapter.notifyDataSetChanged();
        mSpinner.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Snap>> loader) {

    }
}
