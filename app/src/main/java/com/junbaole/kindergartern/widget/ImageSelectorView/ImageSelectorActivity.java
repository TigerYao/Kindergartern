package com.junbaole.kindergartern.widget.ImageSelectorView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.ImageInfo;
import com.junbaole.kindergartern.data.utils.ConstantUtils;
import com.junbaole.kindergartern.databinding.ActivitySelectorBinding;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;
import com.junbaole.kindergartern.presentation.photo.ImagePagerFragment;

import java.io.File;
import java.util.ArrayList;

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
    private static final int AVATAR_WIDTH = 150;
    private static final int AVATAR_HEIGHT = 150;
    ArrayList<ImageInfo> imgsPath = new ArrayList<>();

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
                        new String[] { MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME });

                if (imgsPath.size() > 0)
                    imgsPath.clear();
                imgsPath.add(new ImageInfo());
                if (c != null && c.getCount() > 0) {
                    int columnIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
                    int columnDataIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    int columnNameIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == ConstantUtils.CAMERA_REQUEST_CODE) {
                    if (mAdapter.mCameraTempFile != null && mAdapter.mCameraTempFile.exists()) {
                        Uri photoUri = Uri.fromFile(mAdapter.mCameraTempFile);
                        startPhotoZoom(photoUri);
                    } else {
                        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                    }
                } else if (requestCode == ConstantUtils.RESULT_REQUEST_CODE) {
                    if (mCropTempFile != null && mCropTempFile.exists()) {
                        ImageInfo image = new ImageInfo();
                        Uri photoUri = Uri.fromFile(mCropTempFile);
                        image.setUri(photoUri);
                        image.setRealpath(mCropTempFile.getAbsolutePath());
                        image.client_id = mCropTempFile.getAbsolutePath();
                        imgsPath.add(1,image);
                        mAdapter.notifyDataSetChanged();
                        Uri localUri = Uri.fromFile(mCropTempFile);
                        Intent localIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri);
                        sendBroadcast(localIntent);
                    }
                }
                break;
            case RESULT_CANCELED:
                break;
        }
    }

    File mCropTempFile;

    private void startPhotoZoom(Uri uri) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP", null);
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", AVATAR_WIDTH);
            intent.putExtra("outputY", AVATAR_HEIGHT);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", false);
            mCropTempFile = mAdapter.getAvatorPath(this);
            if (mCropTempFile == null) {
                Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
                return;
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCropTempFile));
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("noFaceDetection", true);
            startActivityForResult(intent, ConstantUtils.RESULT_REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
        } catch (SecurityException e) {
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
        }
    }

}
