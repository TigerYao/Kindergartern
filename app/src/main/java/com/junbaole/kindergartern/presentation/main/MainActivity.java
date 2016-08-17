package com.junbaole.kindergartern.presentation.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.databinding.ActivityMainBinding;
import com.junbaole.kindergartern.presentation.base.BaseFragment;
import com.junbaole.kindergartern.presentation.base.BaseFragmentActivity;
import com.junbaole.kindergartern.presentation.contact.ContactListFragment;
import com.junbaole.kindergartern.presentation.home.HomeFragment;
import com.junbaole.kindergartern.presentation.personal.PersonalFragment;


public class MainActivity extends BaseFragmentActivity {

    ActivityMainBinding mainBinding;
    MainClickHandler mainClickHandler;
    private static final int[] ITEM_DRAWABLES = {R.mipmap.icon_videov_tc, R.mipmap.icon_camera_tc, R.mipmap.icon_image_tc};

    @Override
    public int getFragmentContentId() {
        return R.id.fragment_container;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return HomeFragment.newInstance("", "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        onAfterCreate();
        mainClickHandler = new MainClickHandler(this);
        mainBinding.setClickHandler(mainClickHandler);
        mainBinding.mainTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab1:
                        addFragment(getFirstFragment());
                        break;
                    case R.id.tab2:
                        break;
                    case R.id.tab4:
                        addFragment(ContactListFragment.newInstance("", ""));
                        break;
                    case R.id.tab5:
                        addFragment(PersonalFragment.newInstance("", ""));
                        break;
                }
            }
        });

    }

}
