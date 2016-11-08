package com.ecar.ecarskeyboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.ecar.mylibrary.KeyboardTouchListener;
import com.ecar.mylibrary.KeyboardUtil;

import java.util.ArrayList;
import java.util.List;

import urils.ecaray.com.ecarutils.Utils.ContlorKeyboard;
import urils.ecaray.com.ecarutils.Utils.TagUtil;


public class MainActivity extends AppCompatActivity {

    private RelativeLayout rootView;
    private ScrollView scrollView;
    private EditText normalEd1; //普通键盘
    private EditText specialEd1, specialEd2, specialEd3, specialEd4, specialEd5, specialEd6, specialEd7, specialEd8; //特殊键盘

    private KeyboardUtil keyboardUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rela);
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewPager);
         List<Fragment> mFragList=new ArrayList<>();
        BlankFragment blankFragment=new BlankFragment();
        BlankFragment2 blankFragment2=new BlankFragment2();

        mFragList.add(blankFragment);
        mFragList.add(blankFragment2);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),mFragList));


//        initMoveKeyBoard();

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragList;
        public ViewPagerAdapter(FragmentManager fm, List<Fragment> mFragList) {
            super(fm);
            this.mFragList = mFragList;
        }


        @Override
        public Fragment getItem(int position) {
            return mFragList.get(position);
        }

        @Override
        public int getCount() {
            return mFragList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }

    private void initMoveKeyBoard() {
        rootView = (RelativeLayout) findViewById(R.id.root_view);
        scrollView = (ScrollView) findViewById(R.id.sv_main);

        normalEd1 = (EditText) findViewById(R.id.normal_ed1);

        specialEd1 = (EditText) findViewById(R.id.special_ed1);
        specialEd2 = (EditText) findViewById(R.id.special_ed2);
        specialEd3 = (EditText) findViewById(R.id.special_ed3);
        specialEd4 = (EditText) findViewById(R.id.special_ed4);
        specialEd5 = (EditText) findViewById(R.id.special_ed5);
        specialEd6 = (EditText) findViewById(R.id.special_ed6);
        specialEd7 = (EditText) findViewById(R.id.special_ed7);
        specialEd8 = (EditText) findViewById(R.id.special_ed8);

        keyboardUtil = new KeyboardUtil(this, rootView, scrollView)
                .setRandom(true)//设置是否为随机键盘
                .setOtherEdittext(normalEd1) //不需要自定义的的键盘
                .setKeyBoardStateChangeListener(new KeyBoardStateListener())//监听键盘切换
                .setInputOverListener(new inputOverListener()).doneOnclick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                               Toast.makeText(MainActivity.this,"键盘",Toast.LENGTH_SHORT).show();
                    }
                });//监听输入事件

        keyboardUtil.hideKeyboardLayout(); //隐藏键盘

        specialEd1.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM, -1)); // 设置自定义键盘的ontouth事件
        specialEd2.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_FINISH, -1)); // 设置自定义键盘的ontouth事件
        specialEd3.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_POINT, -1)); // 设置自定义键盘的ontouth事件
        specialEd4.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_X, -1)); // 设置自定义键盘的ontouth事件
        specialEd5.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_NEXT, -1)); // 设置自定义键盘的ontouth事件
        specialEd6.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM_ABC, -1)); // 设置自定义键盘的ontouth事件
        specialEd7.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_ABC, -1)); // 设置自定义键盘的ontouth事件
        specialEd8.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_SYMBOL, -1)); // 设置自定义键盘的ontouth事件

//        new ContlorKeyboard().controlKeyboardLayout(findViewById(R.id.root_view), specialEd3);

    }

    //监听键盘切换
    class KeyBoardStateListener implements KeyboardUtil.KeyBoardStateChangeListener {

        @Override
        public void KeyBoardStateChange(int state, EditText editText) {
//            System.out.println("state" + state);
//            System.out.println("editText" + editText.getText().toString());
        }
    }

    //监听输入事件
    class inputOverListener implements KeyboardUtil.InputFinishListener {

        @Override
        public void inputHasOver(int onclickType, EditText editText) {
//            System.out.println("onclickType" + onclickType);
//            System.out.println("editText" + editText.getText().toString());
        }
    }

}