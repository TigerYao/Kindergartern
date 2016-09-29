package com.junbaole.kindergartern.presentation.photo;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.ImageInfo;
import com.junbaole.kindergartern.data.utils.ScreenUtils;
import com.junbaole.kindergartern.data.utils.StringUtils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by donglua on 15/6/21.
 */
public class PhotoPagerAdapter extends PagerAdapter {

    private List<ImageInfo> paths = new ArrayList<>();
    private RequestManager mGlide;

    public PhotoPagerAdapter(RequestManager glide, List<ImageInfo> paths) {
        this.paths = paths;
        this.mGlide = glide;
    }

    public Uri getImgUri(int position) {
        if (paths == null || paths.get(position) == null) {
            return null;
        }
        Uri uri = paths.get(position).getImgUri();
        try {
            if (uri == null || StringUtils.isBlank(uri.getPath())) {
                uri = Uri.parse(paths.get(position).original_uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uri;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Context context = container.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.photo_pager_item, container, false);

        final ImageView imageView = (ImageView)itemView.findViewById(R.id.iv_pager);
        int swidth = ScreenUtils.width - (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, imageView.getResources().getDisplayMetrics());
        imageView.setMaxWidth(swidth);
        final Uri uri = getImgUri(position);
        if (uri != null){
            Glide.with(context).load(uri).into(imageView);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context instanceof Activity) {
                    if (!((Activity)context).isFinishing()) {
                        ((Activity)context).onBackPressed();
                    }
                }
            }
        });

        container.addView(itemView);

        return itemView;
    }

    @Override
    public int getCount() {
        return paths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
        Glide.clear((View)object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
