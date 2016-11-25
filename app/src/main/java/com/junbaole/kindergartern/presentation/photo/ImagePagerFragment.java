package com.junbaole.kindergartern.presentation.photo;

import android.databinding.DataBindingUtil;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.ImageInfo;
import com.junbaole.kindergartern.databinding.PhotoPageFragmentBinding;
import com.junbaole.kindergartern.presentation.base.BaseFragment;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donglua on 15/6/21.
 */
public class ImagePagerFragment extends BaseFragment {

    public final static String ARG_PATH = "PATHS";
    public final static String ARG_CURRENT_ITEM = "ARG_CURRENT_ITEM";
    public final static String MESSAGE = "message";
    private int isVisibleTitlebar = View.VISIBLE;

    private ArrayList<ImageInfo> paths;
    private ViewPager mViewPager;
    private PhotoPagerAdapter mPagerAdapter;
    private onDismissListener mOnDismissListener;

    public final static long ANIM_DURATION = 200L;

    public final static String ARG_THUMBNAIL_TOP = "THUMBNAIL_TOP";
    public final static String ARG_THUMBNAIL_LEFT = "THUMBNAIL_LEFT";
    public final static String ARG_THUMBNAIL_WIDTH = "THUMBNAIL_WIDTH";
    public final static String ARG_THUMBNAIL_HEIGHT = "THUMBNAIL_HEIGHT";
    public final static String ARG_HAS_ANIM = "HAS_ANIM";
    public final static String ARG_TITLE_VISIBLE = "title_visible";

    private int thumbnailTop = 0;
    private int thumbnailLeft = 0;
    private int thumbnailWidth = 0;
    private int thumbnailHeight = 0;
    private String message = "";

    private boolean hasAnim = false;

    private final ColorMatrix colorizerMatrix = new ColorMatrix();

    private int currentItem = 0;
    private PhotoPageFragmentBinding pageFragmentBinding;

    public static ImagePagerFragment newInstance(ArrayList<ImageInfo> paths, int currentItem) {

        ImagePagerFragment f = new ImagePagerFragment();

        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PATH, paths);
        args.putInt(ARG_CURRENT_ITEM, currentItem);
        args.putBoolean(ARG_HAS_ANIM, false);

        f.setArguments(args);

        return f;
    }

    public void setmOnDismissListener(onDismissListener dismissListener) {
        this.mOnDismissListener = dismissListener;
        if(mPagerAdapter!=null)
            mPagerAdapter.setmOnDismissListener(dismissListener);
    }

    public static ImagePagerFragment newInstance(String message, ArrayList<ImageInfo> paths, int currentItem, int titleVisible) {

        ImagePagerFragment f = new ImagePagerFragment();

        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PATH, paths);
        args.putInt(ARG_CURRENT_ITEM, currentItem);
        args.putBoolean(ARG_HAS_ANIM, false);
        args.putString(MESSAGE, message);
        args.putInt(ARG_TITLE_VISIBLE, titleVisible);
        f.setArguments(args);

        return f;
    }

    public static ImagePagerFragment newInstance(ArrayList<ImageInfo> paths, int currentItem, int[] screenLocation, int thumbnailWidth, int thumbnailHeight) {

        ImagePagerFragment f = newInstance(paths, currentItem);

        f.getArguments().putInt(ARG_THUMBNAIL_LEFT, screenLocation[0]);
        f.getArguments().putInt(ARG_THUMBNAIL_TOP, screenLocation[1]);
        f.getArguments().putInt(ARG_THUMBNAIL_WIDTH, thumbnailWidth);
        f.getArguments().putInt(ARG_THUMBNAIL_HEIGHT, thumbnailHeight);
        f.getArguments().putBoolean(ARG_HAS_ANIM, true);

        return f;
    }

    public void setPhotos(List<ImageInfo> paths, int currentItem) {
        this.paths.clear();
        this.paths.addAll(paths);
        this.currentItem = currentItem;

        mViewPager.setCurrentItem(currentItem);
        mViewPager.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        paths = new ArrayList<>();

        Bundle bundle = getArguments();

        if (bundle != null) {
            paths.clear();
            paths = bundle.getParcelableArrayList(ARG_PATH);
            hasAnim = bundle.getBoolean(ARG_HAS_ANIM);
            currentItem = bundle.getInt(ARG_CURRENT_ITEM);
            thumbnailTop = bundle.getInt(ARG_THUMBNAIL_TOP);
            thumbnailLeft = bundle.getInt(ARG_THUMBNAIL_LEFT);
            thumbnailWidth = bundle.getInt(ARG_THUMBNAIL_WIDTH);
            thumbnailHeight = bundle.getInt(ARG_THUMBNAIL_HEIGHT);
            message = bundle.getString(MESSAGE);
            isVisibleTitlebar = bundle.getInt(ARG_TITLE_VISIBLE);
        }

        mPagerAdapter = new PhotoPagerAdapter(Glide.with(this), paths);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        pageFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.photo_page_fragment, container, false);
        View rootView = pageFragmentBinding.getRoot();
        mViewPager = (ViewPager)rootView.findViewById(R.id.vp_photos);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(currentItem);
        mViewPager.setOffscreenPageLimit(5);
        pageFragmentBinding.setMessage(message);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                hasAnim = currentItem == position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitleVisible();
    }


    /**
     * This is called by the colorizing animator. It sets a saturation factor that is then
     * passed onto a filter on the picture's drawable.
     *
     * @param value saturation
     */
    public void setSaturation(float value) {
        colorizerMatrix.setSaturation(value);
        ColorMatrixColorFilter colorizerFilter = new ColorMatrixColorFilter(colorizerMatrix);
        mViewPager.getBackground().setColorFilter(colorizerFilter);
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public ArrayList<ImageInfo> getPaths() {
        return paths;
    }

    public int getCurrentItem() {
        return mViewPager.getCurrentItem();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        paths.clear();
        paths = null;

        if (mViewPager != null) {
            mViewPager.setAdapter(null);
        }
    }

    public void setTitleBuilder(TitleBuilder titleBuilder) {
        new TitleBuilder(pageFragmentBinding.titleBar).setTitleBuilder(titleBuilder);
    }

    public void setTitleVisible() {
        pageFragmentBinding.titleBar.getRoot().setVisibility(isVisibleTitlebar);
    }

    public void switchPage(int position){
        mViewPager.setCurrentItem(position,true);
    }
    public interface  onDismissListener{
        void onDismiss();
    }
}
