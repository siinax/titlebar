package com.siinax.titlebarlibrary;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * Created by siinax on 2018/12/2.今天是充满希望的一天!! w(ﾟДﾟ)w (╯°Д°)╯︵ ┻━┻
 */

public class TitleBarUtils {

    public static final int left = 0x00000;
    public static final int right = 0x00016;
    public static final int top = 0x00064;
    public static final int bottom = 0x00256;


    public static void addDrawableText(Context context, TextView textView, int Rid, int type) {
        Drawable drawable = context.getResources().getDrawable(Rid);
        addDrawableText(context,textView,drawable,type);
    }

    public static void addDrawableText(Context context, TextView textView, Drawable drawable, int type) {
        addDrawableText(context,textView,drawable,type,drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    public static void addDrawableText(Context context, TextView textView, Drawable drawable, int type, int width, int height) {
        drawable.setBounds(0, 0, width, height);
        switch (type) {
            case left:
                textView.setCompoundDrawables(drawable, null, null, null);
                break;
            case right:
                textView.setCompoundDrawables(null, null, drawable, null);
                break;
            case top:
                textView.setCompoundDrawables(null, drawable, null, null);
                break;
            case bottom:
                textView.setCompoundDrawables(null, null, null, drawable);
                break;

        }
    }
}
