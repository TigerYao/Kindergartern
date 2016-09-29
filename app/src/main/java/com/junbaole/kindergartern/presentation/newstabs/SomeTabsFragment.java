package com.junbaole.kindergartern.presentation.newstabs;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.databinding.FragmentSomeTabsBinding;
import com.junbaole.kindergartern.presentation.adapter.DiscoverAdapter;
import com.junbaole.kindergartern.presentation.base.BaseFragment;
import com.junbaole.kindergartern.widget.recycleview.DividerGridItemDecoration;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SomeTabsFragment} interface
 * to handle interaction events.
 * Use the {@link SomeTabsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SomeTabsFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentSomeTabsBinding someTabsBinding;


    public SomeTabsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SomeTabsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SomeTabsFragment newInstance(String param1, String param2) {
        SomeTabsFragment fragment = new SomeTabsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        someTabsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_some_tabs,container,false);
        return someTabsBinding.getRoot();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        someTabsBinding.recycleview.setLayoutManager(new GridLayoutManager(getContext(),2));
        someTabsBinding.recycleview.addItemDecoration(new DividerGridItemDecoration(getContext()));
        someTabsBinding.recycleview.setAdapter(new DiscoverAdapter());
    }
}
