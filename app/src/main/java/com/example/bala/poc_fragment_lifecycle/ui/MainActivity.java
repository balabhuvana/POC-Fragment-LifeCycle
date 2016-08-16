package com.example.bala.poc_fragment_lifecycle.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.bala.poc_fragment_lifecycle.R;
import com.example.bala.poc_fragment_lifecycle.fragments.AppleFragment;
import com.example.bala.poc_fragment_lifecycle.fragments.BananaFragment;
import com.example.bala.poc_fragment_lifecycle.fragments.BrinjalFragment;
import com.example.bala.poc_fragment_lifecycle.fragments.MyListFragment;
import com.example.bala.poc_fragment_lifecycle.fragments.OrangeFragment;
import com.example.bala.poc_fragment_lifecycle.fragments.TomatoFragment;

public class MainActivity extends AppCompatActivity implements MyListFragment.MyListItemSelectedInterface {
    private String TAG = MainActivity.class.getSimpleName();
    private boolean DUAL_PANE = false;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private String SELECTED_VALUE = "selected_fruits";
    private String APPLE_FRAGMENT = "com.example.bala.poc_fragment_lifecycle.fragments.APPLE_FRAGMENT";
    private String BANANA_FRAGMENT = "com.example.bala.poc_fragment_lifecycle.fragments.BANANA_FRAGMENT";
    private String BRINJAL_FRAGMENT = "com.example.bala.poc_fragment_lifecycle.fragments.BRINJAL_FRAGMENT";
    private String TOMATO_FRAGMENT = "com.example.bala.poc_fragment_lifecycle.fragments.TOMATO_FRAGMENT";
    private String ORANGE_FRAGMENT = "com.example.bala.poc_fragment_lifecycle.fragments.ORANGE_FRAGMENT";
    private String MY_LIST_FRAGMENT = "com.example.bala.poc_fragment_lifecycle.fragments.MY_LIST_FRAGMENT";
    private String SCREEN_NAME = "screen_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        FrameLayout mFrameLayout = (FrameLayout) findViewById(R.id.detailscontainer);

        if (mFrameLayout != null && mFrameLayout.getVisibility() == View.VISIBLE) {
            DUAL_PANE = true;

            Fragment mCheckListFragment = (Fragment) mFragmentManager.findFragmentByTag(MY_LIST_FRAGMENT);
            if (mCheckListFragment == null) {
                mFragmentTransaction.add(R.id.listcontainer, MyListFragment.newInstance(1), MY_LIST_FRAGMENT);
            }
            Bundle mBundle = new Bundle();
            mBundle.putString(SELECTED_VALUE, "Apple");
            Fragment mCheckAppleFragment = (Fragment) mFragmentManager.findFragmentByTag(APPLE_FRAGMENT);
            if (mCheckAppleFragment == null) {
                AppleFragment mAppleFragment = AppleFragment.newInstance(1);
                mAppleFragment.setArguments(mBundle);
                mFragmentTransaction.add(R.id.detailscontainer, mAppleFragment, APPLE_FRAGMENT);
            }

            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();


        } else {
            Fragment mFragment = (Fragment) mFragmentManager.findFragmentByTag(MY_LIST_FRAGMENT);
            if (mFragment == null) {
                mFragmentTransaction.add(R.id.listcontainer, MyListFragment.newInstance(1), MY_LIST_FRAGMENT);
                Fragment mAppleFragment = mFragmentManager.findFragmentByTag(APPLE_FRAGMENT);
                if (mAppleFragment != null) {
                    mFragmentTransaction.remove(mFragmentManager.findFragmentByTag(APPLE_FRAGMENT));
                }
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
            } else {
                Log.d(TAG, "Single else");
            }
        }

    }

    @Override
    public void onTitleSelected(int position, String selectedTitle) {
        Toast.makeText(MainActivity.this, TAG + " - " + selectedTitle, Toast.LENGTH_SHORT).show();
        transactionProcess(position, selectedTitle);
    }

    public void transactionProcess(int position, String selectedTitle) {
        try {
            FragmentManager mFragmentManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            Bundle mBundle = new Bundle();
            mBundle.putString(SELECTED_VALUE, selectedTitle);

            if (position == 0) {
                AppleFragment mAppleFragment = AppleFragment.newInstance(1);
                mAppleFragment.setArguments(mBundle);
                if (DUAL_PANE) {
                    startDetailFragment(mAppleFragment);
                } else {
                    startDetailActivity(position, selectedTitle);
                }

            } else if (position == 1) {
                BananaFragment mBananaFragment = BananaFragment.newInstance(1);
                mBananaFragment.setArguments(mBundle);
                if (DUAL_PANE) {
                    startDetailFragment(mBananaFragment);
                } else {
                    startDetailActivity(position, selectedTitle);
                }
            } else if (position == 2) {
                OrangeFragment mOrangeFragment = OrangeFragment.newInstance(1);
                mOrangeFragment.setArguments(mBundle);
                if (DUAL_PANE) {
                    startDetailFragment(mOrangeFragment);
                } else {
                    startDetailActivity(position, selectedTitle);
                }
            } else if (position == 3) {
                TomatoFragment mTomatoFragment = TomatoFragment.newInstance(1);
                mTomatoFragment.setArguments(mBundle);
                if (DUAL_PANE) {
                    startDetailFragment(mTomatoFragment);
                } else {
                    startDetailActivity(position, selectedTitle);
                }
            } else if (position == 4) {
                BrinjalFragment mBrinjalFragment = BrinjalFragment.newInstance(1);
                mBrinjalFragment.setArguments(mBundle);

                if (DUAL_PANE) {
                    startDetailFragment(mBrinjalFragment);
                } else {
                    startDetailActivity(position, selectedTitle);
                }
            }

        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void startDetailFragment(Fragment mFragment) {
        try {
            mFragmentTransaction.replace(R.id.detailscontainer, mFragment);
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void startDetailActivity(int position, String selectedTitle) {
        try {
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra(SCREEN_NAME, String.valueOf(position));
            intent.putExtra(SELECTED_VALUE, selectedTitle);
            startActivity(intent);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

}
