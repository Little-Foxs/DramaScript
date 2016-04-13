package com.liangwang.dramascriptlibs.Function.Tools;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　　┃　＞　　　＜　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃...　⌒　...　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃   神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┗━━━┓
 * 　　　　　　　　　┃　　　　　　　┣┓
 * 　　　　　　　　　┃　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 * <p/>
 * ━━━━━━感觉萌萌哒━━━━━━
 * <p/>
 * 作者：LigthWang
 * <p/>
 * 描述：
 */
public class UITools {

    private static LayoutInflater inflater;
    public static View inflate(Context context, int res){
        if(inflater==null) {
            inflater = LayoutInflater.from(context);
        }
        return inflater.inflate(res,null);
    }

    /**
     * dp转成px
     * @param context
     * @param dp
     * @return
     */
    public static float convertDpToPixel(Context context, float dp) {
        Resources res = context.getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    /**
     * sp转成px
     * @param context
     * @param spValue
     * @return
     */
    public static float convertSpToPixel(Context context, float spValue) {
        Resources res = context.getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        float fontScale = metrics.scaledDensity;
        return spValue * fontScale + 0.5f;
    }

    /**
     * 计算文字的宽度
     * @param paint
     * @param demoText
     * @return
     */
    public static int calcTextWidth(Paint paint, String demoText) {
        return (int) paint.measureText(demoText);
    }

    /**
     * 计算文字的高度
     * @param paint
     * @param demoText
     * @return
     */
    public static int calcTextHeight(Paint paint, String demoText) {

        Rect r = new Rect();
        paint.getTextBounds(demoText, 0, demoText.length(), r);
        return r.height();
    }

}
