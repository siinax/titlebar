package com.siinax.titlebar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.xml.validation.Validator;


/**
 * Created by Administrator on 2018/10/8. 今天是充满希望的一天!! w(ﾟДﾟ)w (╯°Д°)╯︵ ┻━┻
 */

public class TitleBar extends RelativeLayout implements View.OnClickListener {


    private final String TAG = "TitleBar";
    private RelativeLayout titlebar;
    private TextView left_tv;
    private TextView title_tv;
    private TextView right_tv;
    private TextView left_subtv;
    private TextView right_subtv;
    private long pretime;
    public long clicktime = Constant.CLICK_INTERVAL;
    private int tempview;
    private TitleBarClickListener listener;
    private TitleBarLeftClickListener leftlistener;
    private TitleBarLRClickListener Lrlistener;
    private View bottom_line;

    public void addTitleBarClickListener(TitleBarClickListener listener) {
        this.listener = listener;
    }
    public void addTitleBarClickListener(TitleBarLeftClickListener leftlistener) {
        this.leftlistener = leftlistener;
    }
    public void addTitleBarClickListener(TitleBarLRClickListener Lrlistener) {
        this.Lrlistener = Lrlistener;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void init(Context context, AttributeSet attrs) {

        inflate(getContext(), R.layout.layout_mytitle, this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);

//        int titleText = typedArray.getResourceId(R.styleable.TitleBar_titleText, 0);
//        int leftText = typedArray.getResourceId(R.styleable.TitleBar_leftText, 0);
//        int leftSubText = typedArray.getResourceId(R.styleable.TitleBar_leftSubText, 0);
//        int rightText = typedArray.getResourceId(R.styleable.TitleBar_rightText, 0);
//        int rightSubText = typedArray.getResourceId(R.styleable.TitleBar_rightSubText, 0);

//        int leftIcon = typedArray.getResourceId(R.styleable.TitleBar_leftIcon, 0);
//        int rightIcon = typedArray.getResourceId(R.styleable.TitleBar_rightIcon, 0);


//        int titleIcon = typedArray.getResourceId(R.styleable.TitleBar_titleIcon, 0);
//        int leftSubIcon = typedArray.getResourceId(R.styleable.TitleBar_leftSubIcon, 0);
//        int leftSubIcon = typedArray.getResourceId(R.styleable.TitleBar_leftSubIcon, 0);
//        int rightSubIcon = typedArray.getResourceId(R.styleable.TitleBar_rightSubIcon, 0);


        Drawable leftIcon = typedArray.getDrawable(R.styleable.TitleBar_leftIcon);

        Drawable leftSubIcon = typedArray.getDrawable(R.styleable.TitleBar_leftSubIcon);
        Drawable titleIcon = typedArray.getDrawable(R.styleable.TitleBar_titleIcon);

        Drawable rightIcon = typedArray.getDrawable(R.styleable.TitleBar_rightIcon);
        Drawable rightSubIcon = typedArray.getDrawable(R.styleable.TitleBar_rightSubIcon);

        CharSequence titleText = typedArray.getText(R.styleable.TitleBar_titleText);

        CharSequence leftText = typedArray.getText(R.styleable.TitleBar_leftText);

        CharSequence leftSubText = typedArray.getText(R.styleable.TitleBar_leftSubText);

        CharSequence rightText = typedArray.getText(R.styleable.TitleBar_rightText);
        CharSequence rightSubText = typedArray.getText(R.styleable.TitleBar_rightSubText);

        Drawable backgroundColor = typedArray.getDrawable(R.styleable.TitleBar_backgroundColor);
        boolean showLine = typedArray.getBoolean(R.styleable.TitleBar_showLine,false);

        ColorStateList titletextColor = typedArray.getColorStateList(R.styleable.TitleBar_titletextColor);
        ColorStateList leftTextColor = typedArray.getColorStateList(R.styleable.TitleBar_leftTextColor);
        ColorStateList leftSubTextColor = typedArray.getColorStateList(R.styleable.TitleBar_leftSubTextColor);
        ColorStateList rightTextColor = typedArray.getColorStateList(R.styleable.TitleBar_rightTextColor);
        ColorStateList rightSubTextColor = typedArray.getColorStateList(R.styleable.TitleBar_rightSubTextColor);

        titlebar = (RelativeLayout) findViewById(R.id.titlebar_bg);

        titlebar.setElevation(10f);
        titlebar.setOutlineProvider(ViewOutlineProvider.BOUNDS);

        if (backgroundColor != null) {
            titlebar.setBackground(backgroundColor);
        }

        Loga.d("dd", "rightIcon = " + rightIcon);
        // TODO: 2018/11/30 默认icon
        Drawable defaultdrawable = ContextCompat.getDrawable(context, R.drawable.ic_arrow_back);

        leftIcon = leftIcon == null ? defaultdrawable : leftIcon;

        title_tv = (TextView) findViewById(R.id.title_tv);
        if (titleIcon != null) {
            title_tv.setVisibility(VISIBLE);
            TitleBarUtils.addDrawableText(context, title_tv, titleIcon, TitleBarUtils.left);
        } else if (!TextUtils.isEmpty(titleText)) {
            title_tv.setVisibility(VISIBLE);
            title_tv.setText(titleText);
        }
        title_tv.setTextColor(titletextColor == null ? ColorStateList.valueOf(0xFF000000) : titletextColor);


        left_tv = (TextView) findViewById(R.id.left_tv);
        left_tv.setTextColor(leftTextColor == null ? ColorStateList.valueOf(0xFF000000) : leftTextColor);
        setIconandText(context, left_tv, leftIcon, leftText, TitleBarUtils.left);
        /*if (leftIcon != null) {
            left_tv.setVisibility(VISIBLE);
            TitleBarUtils.addDrawableText(context, left_tv, leftIcon, TitleBarUtils.left);
        }
        if (!TextUtils.isEmpty(leftText)) {
            left_tv.setVisibility(VISIBLE);
            left_tv.setText(leftText);
        }
        if (leftIcon == null && TextUtils.isEmpty(leftText)) {
            left_tv.setVisibility(GONE);
        }*/

        right_tv = (TextView) findViewById(R.id.right_tv);
        right_tv.setTextColor(rightTextColor == null ? ColorStateList.valueOf(0xFF000000) : rightTextColor);
        setIconandText(context, right_tv, rightIcon, rightText, TitleBarUtils.right);
        /*if (rightIcon != null) {
            right_tv.setVisibility(VISIBLE);
            TitleBarUtils.addDrawableText(context, right_tv, rightIcon, TitleBarUtils.right);
        } else if (!TextUtils.isEmpty(rightText)) {
            right_tv.setVisibility(VISIBLE);
            right_tv.setText(rightText);
        } else {
            right_tv.setVisibility(GONE);
        }*/

        left_subtv = (TextView) findViewById(R.id.left_subtv);
        left_subtv.setTextColor(leftSubTextColor == null ? ColorStateList.valueOf(0xFF000000) : leftSubTextColor);
        setIconandText(context, left_subtv, leftSubIcon, leftSubText, TitleBarUtils.left);
        /*if (leftSubIcon != null) {
            left_subtv.setVisibility(VISIBLE);
            TitleBarUtils.addDrawableText(context, left_subtv, leftSubIcon, TitleBarUtils.left);
        } else if (!TextUtils.isEmpty(leftSubText)) {
            left_subtv.setVisibility(VISIBLE);
            left_subtv.setText(leftSubText);
        } else {
            left_subtv.setVisibility(GONE);
        }*/

        right_subtv = (TextView) findViewById(R.id.right_subtv);
        right_subtv.setTextColor(rightSubTextColor == null ? ColorStateList.valueOf(0xFF000000) : rightSubTextColor);
        setIconandText(context, right_subtv, rightSubIcon, rightSubText, TitleBarUtils.left);
        /*if (rightSubIcon != null) {
            right_subtv.setVisibility(VISIBLE);
            TitleBarUtils.addDrawableText(context, right_subtv, rightSubIcon, TitleBarUtils.left);
        } else if (!TextUtils.isEmpty(rightSubText)) {
            right_subtv.setVisibility(VISIBLE);
            right_subtv.setText(rightSubText);
        } else {
            right_subtv.setVisibility(GONE);
        }*/
        bottom_line = findViewById(R.id.bottom_line);
        bottom_line.setVisibility(showLine ? VISIBLE : GONE);


        left_tv.setOnClickListener(this);
        left_subtv.setOnClickListener(this);
        right_tv.setOnClickListener(this);
        right_subtv.setOnClickListener(this);
        typedArray.recycle();
    }

    @Override
    public void onClick(View v) {
        if (listener == null && leftlistener == null && Lrlistener == null) {
//            new Throwable("TitleBarClickListener 不可为空");
            Loga.e(TAG, "TitleBarClickListener 不可为空");
            return;
        }

        if (System.currentTimeMillis() - pretime > clicktime) {
            pretime = System.currentTimeMillis();
        } else if (tempview == v.getId()){
            return;
        }

        tempview = v.getId();
        switch (v.getId()) {
            case R.id.left_tv:
                if (left_tv.getVisibility() == VISIBLE) {
                    if (listener != null) {
                        listener.onLeftclick();
                    }
                    if (leftlistener != null) {
                        leftlistener.onLeftclick();
                    }
                    if (Lrlistener != null) {
                        Lrlistener.onLeftclick();
                    }

                }
                break;
            case R.id.left_subtv:
                if (left_subtv.getVisibility() == VISIBLE) {
                    if (listener != null)
                        listener.onLeftSubclick();
                }
                break;
            case R.id.right_tv:
                if (right_tv.getVisibility() == VISIBLE) {
                    if (listener != null)
                        listener.onRightclick(v);
                    if (Lrlistener != null) {
                        Lrlistener.onRightclick();
                    }
                }
                break;
            case R.id.right_subtv:
                if (right_subtv.getVisibility() == VISIBLE) {
                    if (listener != null)
                        listener.onRightSubclick();
                }
                break;
        }
    }


    private void setIconandText(Context context, TextView textView, Drawable icon, CharSequence text,int type){
        if (icon != null) {
            textView.setVisibility(VISIBLE);
            TitleBarUtils.addDrawableText(context, left_tv, icon, type);
        }
        if (!TextUtils.isEmpty(text)) {
            textView.setVisibility(VISIBLE);
            textView.setText(text);
        }
        if (icon == null && TextUtils.isEmpty(text)) {
            textView.setVisibility(GONE);
        }
    }

    public void setTitleText(int Rid) {
        title_tv.setText(Rid);
    }

    public void setTitletText(CharSequence charSequence) {
        title_tv.setText(charSequence);
    }

    public void setLeftText(int Rid) {
        left_tv.setVisibility(VISIBLE);
        left_tv.setText(Rid);
    }

    public void setLeftText(CharSequence charSequence) {
        left_tv.setVisibility(VISIBLE);
        left_tv.setText(charSequence);
    }

    public void setLeftSubText(int Rid) {
        left_subtv.setVisibility(VISIBLE);
        left_subtv.setText(Rid);
    }

    public TextView getLeft_subtv() {
        left_subtv.setVisibility(VISIBLE);
        return left_subtv;
    }

    public TextView getLeft_tv() {
        return left_tv;
    }

    public TextView getRight_subtv() {
        return right_subtv;
    }

    public TextView getRight_tv() {
        return right_tv;
    }

    public TextView getTitle_tv() {
        return title_tv;
    }

    public RelativeLayout getTitlebar() {
        return titlebar;
    }

    //    get/

    public void setLeftSubText(CharSequence charSequence) {
        left_subtv.setVisibility(VISIBLE);
        left_subtv.setText(charSequence);
    }

    public void setRightText(int Rid) {
        right_tv.setVisibility(VISIBLE);
        right_tv.setText(Rid);
    }

    public void setRightText(CharSequence charSequence) {
        right_tv.setVisibility(VISIBLE);
        right_tv.setText(charSequence);
    }

    public void setRightSubText(int Rid) {
        right_subtv.setVisibility(VISIBLE);
        right_subtv.setText(Rid);
    }

    public void setRightSubText(CharSequence charSequence) {
        right_subtv.setVisibility(VISIBLE);
        right_subtv.setText(charSequence);
    }

    public void setLeftIcon(int Rid) {
        left_tv.setVisibility(VISIBLE);
        TitleBarUtils.addDrawableText(getContext(), left_tv, Rid, TitleBarUtils.left);
    }

    public void setRightIcon(int Rid) {
        right_tv.setVisibility(VISIBLE);
        TitleBarUtils.addDrawableText(getContext(), right_tv, Rid, TitleBarUtils.right);
    }

    public void setLeftSubIcon(int Rid) {
        left_subtv.setVisibility(VISIBLE);
        TitleBarUtils.addDrawableText(getContext(), left_subtv, Rid, TitleBarUtils.left);
    }

    public void setRightSubIcon(int Rid) {
        right_subtv.setVisibility(VISIBLE);
        TitleBarUtils.addDrawableText(getContext(), right_subtv, Rid, TitleBarUtils.right);
    }

    public interface TitleBarClickListener {
        void onLeftclick();
        void onLeftSubclick();
        void onRightclick(View v);
        void onRightSubclick();
    }

    public interface TitleBarLeftClickListener{
        void onLeftclick();
    }

    public interface TitleBarLRClickListener{
        void onLeftclick();
        void onRightclick();
    }



}
