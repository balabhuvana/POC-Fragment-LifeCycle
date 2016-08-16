package com.example.bala.poc_fragment_lifecycle.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.bala.poc_fragment_lifecycle.R;
import com.example.bala.poc_fragment_lifecycle.common_inetface.BackPressHandlingInterface;
import com.example.bala.poc_fragment_lifecycle.ui.DetailActivity;
import com.example.bala.poc_fragment_lifecycle.ui.MainActivity;


public class AppleFragment extends Fragment {

    private String TAG = AppleFragment.class.getSimpleName();
    private TextView mTextView = null;
    private Bundle mBundle = null;
    private Button mBtnBack, mBtnNext;
    private String SELECTED_VALUE = "selected_fruits";
    private EditText mEditText;
    private String BANANA_FRAGMENT = "com.example.bala.poc_fragment_lifecycle.fragments.BANANA_FRAGMENT";
    boolean DUAL_PANE = false;
    private BackPressHandlingInterface mBackPressHandlingListener;

    public static AppleFragment newInstance(int columnCount) {
        AppleFragment fragment = new AppleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d(TAG, "onAttach");
        super.onAttach(activity);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_content_view, container, false);
        Log.d(TAG, "onCreateView");
        return mView;
    }

    @Override
    public void onViewCreated(View oView, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(oView, savedInstanceState);

        try {
            mTextView = (TextView) oView.findViewById(R.id.contentTv);
            mEditText = (EditText) oView.findViewById(R.id.etName);
            mBtnBack = (Button) oView.findViewById(R.id.btnBack);
            mBtnNext = (Button) oView.findViewById(R.id.btnNext);
            mBtnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    transactionProcess();
                }
            });
            mBtnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    backPressHandling();
                }
            });

        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
        assignValue();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");

    }

    private void assignValue() {
        try {

            if (mBundle != null) {
                mTextView.setText(mBundle.getString(SELECTED_VALUE));
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }


    private void transactionProcess() {
        try {
            FrameLayout mFrameLayout = (FrameLayout) getActivity().findViewById(R.id.detailscontainer);

            if (mFrameLayout != null && mFrameLayout.getVisibility() == View.VISIBLE) {
                DUAL_PANE = true;
            } else {
                DUAL_PANE = false;
            }
            FragmentManager mFragmentManager = getFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            Bundle mBundle = new Bundle();
            mBundle.putString(SELECTED_VALUE, "Hello Banana");
            BananaFragment mBananaFragment = (BananaFragment) mFragmentManager.findFragmentByTag(BANANA_FRAGMENT);
            if (mBananaFragment == null) {
                mBananaFragment = BananaFragment.newInstance(1);
            }
            mBananaFragment.setArguments(mBundle);
            if (DUAL_PANE) {
                mFragmentTransaction.replace(R.id.detailscontainer, mBananaFragment);
            } else {
                mFragmentTransaction.replace(R.id.listcontainer, mBananaFragment, BANANA_FRAGMENT);
            }
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void backPressHandling() {
        try {

            if (getFragmentManager().getBackStackEntryCount() > 1) {
                mBackPressHandlingListener.backPressHandling();
            }


        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

}
