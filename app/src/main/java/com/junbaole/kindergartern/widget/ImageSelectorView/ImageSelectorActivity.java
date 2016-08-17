package com.junbaole.kindergartern.widget.ImageSelectorView;

import java.util.ArrayList;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.ImageInfo;
import com.junbaole.kindergartern.databinding.ActivitySelectorBinding;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;
import com.junbaole.kindergartern.presentation.photo.ImagePagerFragment;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.GridView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ImageSelectorActivity extends BaseActivity {
    private GridView mGirdView;
    protected MyAdapter mAdapter;
    ActivitySelectorBinding selectorBinding;
    SelectClickHandler clickHandler;
    ArrayList<ImageInfo> mImgUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectorBinding = DataBindingUtil.setContentView(this, R.layout.activity_selector);
        clickHandler = new SelectClickHandler(this);
        selectorBinding.setClickHandler(clickHandler);
        new TitleBuilder(selectorBinding.titleBar).TitleBuilderLable("选择照片", "", "取消").TitleBuilderLayout(true, true).TitleBuilderLeftItem(true, false).TitleBuilderRightItem(false, true).build();
        initView();
        getImgs();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mGirdView = (GridView)findViewById(R.id.id_gridView);
    }

    public void getImgs() {
        Observable.OnSubscribe<ArrayList<ImageInfo>> subscribe = new Observable.OnSubscribe<ArrayList<ImageInfo>>() {
            @Override
            public void call(Subscriber<? super ArrayList<ImageInfo>> subscriber) {
                Uri baseUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                Cursor c = MediaStore.Images.Media.query(getContentResolver(), baseUri,
                        new String[] { MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME});
                if (c != null && c.getCount() > 0) {
                    int columnIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
                    int columnDataIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    int columnNameIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
                    ArrayList<ImageInfo> imgsPath = new ArrayList<>();
                    imgsPath.add(new ImageInfo());
                    while (c.moveToNext()) {
                        ImageInfo image = new ImageInfo();
                        long origid = c.getLong(columnIndex);
                        image.setUri(Uri.withAppendedPath(baseUri, origid + ""));
                        image.setRealpath(c.getString(columnDataIndex));
                        image.client_id = c.getString(columnDataIndex);
                        imgsPath.add(image);
                    }
                    subscriber.onNext(imgsPath);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new Throwable("找不到图片"));
                }

            }
        };
        Subscriber<ArrayList<ImageInfo>> subscribers = new Subscriber<ArrayList<ImageInfo>>() {

            @Override
            public void onCompleted() {
                mAdapter = new MyAdapter(ImageSelectorActivity.this, mImgUrls,
                        R.layout.grid_item);
                mGirdView.setAdapter(mAdapter);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ArrayList<ImageInfo> uris) {
                mImgUrls = uris;

            }
        };

        Observable.create(subscribe).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscribers);
    }

    public void scranPics(int postion) {
        selectorBinding.displayImgs.setVisibility(View.VISIBLE);
        ImagePagerFragment imagePagerFragment = ImagePagerFragment.newInstance(mImgUrls, postion);
        getSupportFragmentManager().beginTransaction().replace(R.id.display_imgs, imagePagerFragment).commit();
    }

}
