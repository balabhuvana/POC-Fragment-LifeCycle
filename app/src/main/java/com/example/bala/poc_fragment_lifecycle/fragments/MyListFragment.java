package com.example.bala.poc_fragment_lifecycle.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bala.poc_fragment_lifecycle.R;


public class MyListFragment extends Fragment {

    private String TAG = MyListFragment.class.getSimpleName();
    private MyListItemSelectedInterface myListItemSelectedListener;
    private String title[];
    private ListView mListView;
    private ArrayAdapter mArrayAdapter;
    private static View view;

    public static MyListFragment newInstance(int columnCount) {
        MyListFragment fragment = new MyListFragment();
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

        if (context instanceof MyListItemSelectedInterface) {
            myListItemSelectedListener = (MyListItemSelectedInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_list_view, container, false);
        mListView = (ListView) mView.findViewById(R.id.myListView);

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
        title = getActivity().getResources().getStringArray(R.array.fruits);
        mArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_activated_1, title);
        mListView.setAdapter(mArrayAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                myListItemSelectedListener.onTitleSelected(i, title[i]);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        myListItemSelectedListener = null;
        mArrayAdapter = null;
        mListView = null;
    }

    public interface MyListItemSelectedInterface {

        public void onTitleSelected(int position, String selectedTitle);

    }
}
