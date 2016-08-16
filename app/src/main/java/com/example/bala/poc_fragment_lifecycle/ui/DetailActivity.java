package com.example.bala.poc_fragment_lifecycle.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.bala.poc_fragment_lifecycle.R;
import com.example.bala.poc_fragment_lifecycle.fragments.AppleFragment;
import com.example.bala.poc_fragment_lifecycle.fragments.BananaFragment;
import com.example.bala.poc_fragment_lifecycle.fragments.BrinjalFragment;
import com.example.bala.poc_fragment_lifecycle.fragments.OrangeFragment;
import com.example.bala.poc_fragment_lifecycle.fragments.TomatoFragment;

public class DetailActivity extends AppCompatActivity {
    private String SCREEN_NAME = "screen_name";
    private String SELECTED_VALUE = "selected_fruits";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent mIntent = getIntent();
        if (mIntent != null) {
            String position = mIntent.getStringExtra(SCREEN_NAME);

            String selected_value = mIntent.getStringExtra(SELECTED_VALUE);
            initScreenLoading(Integer.parseInt(position), selected_value);
        }


    }

    private void initScreenLoading(int position, String selectedValue) {
        try {
            Fragment mFragment = createFragment(position);
            Bundle mBundle = new Bundle();
            mBundle.putString(SELECTED_VALUE, selectedValue);
            mFragment.setArguments(mBundle);
            FragmentManager mFragmentManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.add(R.id.detailscontainer, mFragment);
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private Fragment createFragment(int position) {
        if (position == 0) {
            return AppleFragment.newInstance(1);
        } else if (position == 1) {
            return BananaFragment.newInstance(1);
        } else if (position == 2) {
            return OrangeFragment.newInstance(1);
        } else if (position == 3) {
            return TomatoFragment.newInstance(1);
        } else if (position == 4) {
            return BrinjalFragment.newInstance(1);
        }
        return AppleFragment.newInstance(1);
    }


}
