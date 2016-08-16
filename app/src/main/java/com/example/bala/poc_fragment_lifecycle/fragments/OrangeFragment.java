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
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.bala.poc_fragment_lifecycle.R;
import com.example.bala.poc_fragment_lifecycle.common_inetface.BackPressHandlingInterface;
import com.example.bala.poc_fragment_lifecycle.ui.DetailActivity;
import com.example.bala.poc_fragment_lifecycle.ui.MainActivity;


public class OrangeFragment extends Fragment {

    private String TAG = TomatoFragment.class.getSimpleName();
    private TextView mTextView = null;
    private Bundle mBundle = null;
    private Button mBtnBack, mBtnNext;
    private String SELECTED_VALUE = "selected_fruits";
    private String TOMATO_FRAGMENT = "com.example.bala.poc_fragment_lifecycle.fragments.TOMATO_FRAGMENT";
    boolean DUAL_PANE = false;
    private BackPressHandlingInterface mBackPressHandlingListener;

    public static OrangeFragment newInstance(int columnCount) {
        OrangeFragment fragment = new OrangeFragment();
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
        intViews(mView);
        Log.d(TAG, "onCreateView");
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
    }

    private void intViews(View oView) {
        try {
            mTextView = (TextView) oView.findViewById(R.id.contentTv);
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
            mBundle.putString(SELECTED_VALUE, "Hello Tomato");
            TomatoFragment mTomatoFragment = (TomatoFragment) mFragmentManager.findFragmentByTag(TOMATO_FRAGMENT);
            if (mTomatoFragment == null) {
                mTomatoFragment = TomatoFragment.newInstance(1);
            }
            mTomatoFragment.setArguments(mBundle);
            if (DUAL_PANE) {
                mFragmentTransaction.replace(R.id.detailscontainer, mTomatoFragment);
            } else {
                mFragmentTransaction.replace(R.id.listcontainer, mTomatoFragment, TOMATO_FRAGMENT);
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
