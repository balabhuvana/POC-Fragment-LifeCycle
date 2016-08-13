package com.example.bala.poc_fragment_lifecycle;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.bala.poc_fragment_lifecycle.fragments.AppleFragment;
import com.example.bala.poc_fragment_lifecycle.fragments.BananaFragment;
import com.example.bala.poc_fragment_lifecycle.fragments.BrinjalFragment;
import com.example.bala.poc_fragment_lifecycle.fragments.MyListFragment;
import com.example.bala.poc_fragment_lifecycle.fragments.OrangeFragment;
import com.example.bala.poc_fragment_lifecycle.fragments.TomatoFragment;

public class MainActivity extends AppCompatActivity implements MyListFragment.MyListItemSelectedInterface {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(android.R.id.content, MyListFragment.newInstance(1)).commit();

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
            mBundle.putString(CommonVariables.SELECTED_VALUE, selectedTitle);

            if (position == 0) {
                AppleFragment mAppleFragment = AppleFragment.newInstance(1);
                mAppleFragment.setArguments(mBundle);
                mFragmentTransaction.replace(android.R.id.content, mAppleFragment);
            } else if (position == 1) {
                BananaFragment mBananaFragment = BananaFragment.newInstance(1);
                mBananaFragment.setArguments(mBundle);
                mFragmentTransaction.replace(android.R.id.content, mBananaFragment);
            } else if (position == 2) {
                OrangeFragment mOrangeFragment = OrangeFragment.newInstance(1);
                mOrangeFragment.setArguments(mBundle);
                mFragmentTransaction.replace(android.R.id.content, mOrangeFragment);
            } else if (position == 3) {
                TomatoFragment mTomatoFragment = TomatoFragment.newInstance(1);
                mTomatoFragment.setArguments(mBundle);
                mFragmentTransaction.replace(android.R.id.content, mTomatoFragment);
            } else if (position == 4) {
                BrinjalFragment mBrinjalFragment = BrinjalFragment.newInstance(1);
                mBrinjalFragment.setArguments(mBundle);
                mFragmentTransaction.replace(android.R.id.content, mBrinjalFragment);
            }
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

}
