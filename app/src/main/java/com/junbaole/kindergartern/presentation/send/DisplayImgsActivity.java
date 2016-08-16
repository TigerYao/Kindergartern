package com.junbaole.kindergartern.presentation.send;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.utils.ActionsheetHelper;

import com.junbaole.kindergartern.data.utils.event.ActionSheetEvent;
import com.junbaole.kindergartern.databinding.ActivityDisplayImgsBinding;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;
import com.junbaole.kindergartern.widget.ImageSelectorView.ImageLoader;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;


public class DisplayImgsActivity extends BaseActivity {


    private ActivityDisplayImgsBinding mDisplayImgsBinding;
    private ArrayList<String> imgs;
    private ActionsheetHelper sheetHelper;
    private DispalyImgClickHandler clickHandler;
    private String path = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDisplayImgsBinding = DataBindingUtil.setContentView(this, R.layout.activity_display_imgs);
        new TitleBuilder(mDisplayImgsBinding.titleBar).TitleBuilderLayout(true, true).TitleBuilderLeftItem(true, false).TitleBuilderRightItem(true, true).TitleBuilderImgReasours(R.mipmap.icon_fanhui, R.mipmap.icon_fenxiang).TitleBuilderTextRightDrawable(R.mipmap.icon_caidan, 0, 0, 0);
        clickHandler = new DispalyImgClickHandler(this);
        imgs = getIntent().getStringArrayListExtra("imgs");
        path = getIntent().getStringExtra("path");
        mDisplayImgsBinding.setClickHandler(clickHandler);

        mDisplayImgsBinding.convenientBanner.setPages(new CBViewHolderCreator<ImgViewHolder>() {
            @Override
            public ImgViewHolder createHolder() {
                return new ImgViewHolder();
            }
        }, imgs).setCanLoop(false);
        sheetHelper = new ActionsheetHelper(DisplayImgsActivity.this, "", "转入日记", "下载原图") {
            @Override
            public void showDialog(Object obje) {
                super.showDialog(obje);
            }
        };
    }

    class ImgViewHolder implements Holder<String> {


        ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
//            imageView.setMinimumWidth(ScreenUtils.width);
//            imageView.setMaxHeight(300);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, final String data) {
            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    sheetHelper.showDialog(data);
                    return true;
                }
            });

            ImageLoader.getInstance(3, ImageLoader.Type.LIFO).loadImage(path + "/" + data, imageView);
//            diaplayimgItemBinding.imgview.setImageURI(path+"/"+data);

        }

    }

    @Subscribe
    public void operate(ActionSheetEvent sheetEvent) {
        switch (sheetEvent.style) {
            case "转入日记":
                Toast.makeText(this,sheetEvent.object+"转入日记",Toast.LENGTH_LONG).show();
                break;
            case "下载原图":
                Toast.makeText(this,sheetEvent.object+"下载原图",Toast.LENGTH_LONG).show();

                break;
        }
    }
}
