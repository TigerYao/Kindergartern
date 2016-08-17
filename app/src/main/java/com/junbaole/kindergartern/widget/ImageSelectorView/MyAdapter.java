package com.junbaole.kindergartern.widget.ImageSelectorView;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.ImageInfo;
import com.junbaole.kindergartern.data.utils.StringUtils;

import android.content.Context;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;

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
        if (item == null || StringUtils.isBlank(item.client_id)) {
            helper.getView(R.id.id_item_check).setVisibility(View.GONE);
            return;
        }
        final ImageView mImageView = helper.getView(R.id.id_item_image);
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
}
