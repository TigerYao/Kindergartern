package com.junbaole.kindergartern.widget.ImageSelectorView;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.ImageInfo;
import com.junbaole.kindergartern.data.utils.ConstantUtils;
import com.junbaole.kindergartern.data.utils.StringUtils;
import com.junbaole.kindergartern.presentation.base.BaseActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyAdapter extends CommonAdapter<ImageInfo> {

    private Context ctx;
    /**
     * 用户选择的图片，存储为图片的完整路径
     */
    public static ArrayList<ImageInfo> mSelectedImage = new ArrayList<>();

    public MyAdapter(Context context, List<ImageInfo> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        this.ctx = context;
    }

    @Override
    public void convert(final ViewHolder helper, final ImageInfo item) {
        final ImageView mImageView = helper.getView(R.id.id_item_image);
        if (item == null || StringUtils.isBlank(item.client_id)) {
            helper.getView(R.id.id_item_check).setVisibility(View.GONE);
            mImageView.setImageResource(R.mipmap.icon_xiangche_120);
            mImageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    getPhotoFromCamera();
                }
            });
            return;
        }
        Glide.with(ctx).load(item.getImgUri()).into(mImageView);
        final CheckBox mSelect = helper.getView(R.id.id_item_check);
        mSelect.setVisibility(View.VISIBLE);
        mImageView.setColorFilter(null);
        mSelect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSelectedImage.contains(item)) {
                    mSelectedImage.remove(item);
                    mImageView.setColorFilter(null);
                } else {
                    mSelectedImage.add(item);
                    mImageView.setColorFilter(Color.parseColor("#77000000"));
                }
            }
        });

        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ImageSelectorActivity)ctx).scranPics(helper.getPosition());
            }
        });

        /**
         * 已经选择过的图片，显示出选择过的效果
         */
        if (mSelectedImage.contains(item)) {
            mSelect.setChecked(true);
            mImageView.setColorFilter(Color.parseColor("#77000000"));
        } else {
            mSelect.setChecked(false);
            mImageView.setColorFilter(null);
        }

    }

    public File mCameraTempFile;

    private void getPhotoFromCamera() {
        try {
            Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            mCameraTempFile = getAvatorPath(ctx);
            if (mCameraTempFile == null) {
                mCameraTempFile.createNewFile();
            }
            Uri uri = Uri.fromFile(mCameraTempFile);
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            ((BaseActivity)ctx).startActivityForResult(intentFromCapture, ConstantUtils.CAMERA_REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(ctx, "打开相机失败,稍后重试", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static File getAvatorPath(Context context) {
        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED))
            return null;
        return new File(Environment.getExternalStorageDirectory(), UUID.randomUUID().toString() + ".tmp");
    }
}
