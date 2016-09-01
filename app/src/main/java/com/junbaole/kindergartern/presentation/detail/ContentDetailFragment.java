package com.junbaole.kindergartern.presentation.detail;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.DiaryDetailInfo;
import com.junbaole.kindergartern.databinding.FragmentContentDetailBinding;
import com.junbaole.kindergartern.presentation.adapter.CommentsAdaper;
import com.junbaole.kindergartern.presentation.base.BaseFragment;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContentDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentDetailFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentContentDetailBinding mContentDetailBinding;

    // TODO: Rename and change types of parameters
    private DiaryDetailInfo mParam1;
    private boolean mIsCommentView = false;
    private CommentsAdaper mCommentsAdaper;

    public ContentDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContentDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContentDetailFragment newInstance(DiaryDetailInfo param1, boolean isCommentView) {
        ContentDetailFragment fragment = new ContentDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        args.putBoolean(ARG_PARAM2, isCommentView);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelable(ARG_PARAM1);
            mIsCommentView = getArguments().getBoolean(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_content_detail, container, false);
        mContentDetailBinding.setDiaryInfo(mParam1);
        if (mIsCommentView) {
            mContentDetailBinding.userinfoLayout.setVisibility(View.GONE);
            new TitleBuilder(mContentDetailBinding.titleBar).TitleBuilderLayout(true, true).TitleBuilderLeftItem(true, false).TitleBuilderRightItem(false, false).TitleBuilderLable("评论", "", "")
                    .build();
        } else
            new TitleBuilder(mContentDetailBinding.titleBar).TitleBuilderLayout(true, true).TitleBuilderLeftItem(true, false).TitleBuilderRightItem(false, true).TitleBuilderLable("内容详情", "", "编辑")
                    .build();
        return mContentDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mParam1.comment_num > 0 && mParam1.comments != null) {
            mCommentsAdaper = new CommentsAdaper(mParam1.comments);
            mContentDetailBinding.recycleview.setLayoutManager(new LinearLayoutManager(getContext()));
            mContentDetailBinding.recycleview.setAdapter(mCommentsAdaper);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
