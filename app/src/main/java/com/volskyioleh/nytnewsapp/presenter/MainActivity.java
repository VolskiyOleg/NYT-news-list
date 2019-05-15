package com.volskyioleh.nytnewsapp.presenter;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.volskyioleh.nytnewsapp.R;
import com.volskyioleh.nytnewsapp.presenter.adapters.ViewPagerAdapter;
import com.volskyioleh.nytnewsapp.presenter.favorite.FavoriteListActivity;
import com.volskyioleh.nytnewsapp.presenter.most_emailes.MostEmailedFragment;
import com.volskyioleh.nytnewsapp.presenter.most_shared.MostSharedFragment;
import com.volskyioleh.nytnewsapp.presenter.most_viewed.MostViewedFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favorite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            startActivity(new Intent(this, FavoriteListActivity.class));
          //  Toast.makeText(MainActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MostEmailedFragment(), getString(R.string.most_emailed));
        adapter.addFragment(new MostSharedFragment(), getString(R.string.most_shared));
        adapter.addFragment(new MostViewedFragment(), getString(R.string.most_viewed));
        viewPager.setAdapter(adapter);
    }
}
