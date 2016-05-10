package com.liangwang.dramascriptlibs.UI;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import com.liangwang.dramascriptlibs.Function.Tools.SharedPreferencesUtil;
import com.liangwang.dramascriptlibs.Function.Tools.UITools;

import java.text.SimpleDateFormat;
import java.util.Date;

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
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━
 * <p>
 * 作者：LigthWang
 * <p>
 * 描述：1. 配置属性添加到style.xml里面
 * 2. 设置start和end value可以为小数
 */
public class TimeView extends View {

    private static final String TAG = TimeView.class.getSimpleName();

    private Context mContext;

    /**
     * 短线的高度
     */
    private float mShortLineHeight;
    /**
     * 长线的高度
     */
    private float mHighLineHeight;
    /**
     * 短线的宽度
     */
    private float mShortLineWidth;
    /**
     * 长线的宽度
     */
    private float mHighLineWidth;
    /**
     * 两个长线间间隔数量
     */
    private int mSmallPartitionCount;
    /**
     * 指示器的宽度的一半
     */
    private float mIndicatorHalfWidth;
    /**
     * 指示器数字距离上边的距离
     */
    private float mIndicatorTextTopMargin;
    /**
     * 短线长线的上边距
     */
    private float mLineTopMargin;
    /**
     * 起止数值, 暂定为int
     */
    private int mStartValue;
    private int mEndValue;
    /**
     * 两个长线之间相差多少值 暂定为int
     */
    private int mPartitionValue;
    /**
     * 长线间隔宽度
     */
    private float mPartitionWidth;
    /**
     * 设置的初始值
     */
    private int mOriginValue;
    private int mOriginValueSmall;
    /**
     * 当前值
     */
    private int mCurrentValue;
    /**
     * 刻度的大小
     */
    private int mScaleTextsize;
    /**
     * 最小速度
     */
    protected int mMinVelocity;

    private Paint mBgPaint;
    private Paint mShortLinePaint;
    private Paint mHighLinePaint;
    private Paint mIndicatorTxtPaint;
    private Paint mIndicatorViewPaint;

    //往右边去能偏移的最大值
    private float mRightOffset;
    //往左边去能偏移的最大值
    private float mLeftOffset;
    //移动的距离
    private float mMoveX = 0f;

    private float mWidth, mHeight;

    private Scroller mScroller;
    protected VelocityTracker mVelocityTracker;

    public int flag;
    public int flag2;


    private OnValueChangeListener listener;
    //系统当前的时间  毫秒
    private static long currentTimeMillis = System.currentTimeMillis();
    private static long s;
    private String caculateTime;
    private long val;

    public interface OnValueChangeListener {
        void onValueChange(String intVal, int fltval);
    }

    public void setValueChangeListener(OnValueChangeListener listener) {
        this.listener = listener;
    }

    public TimeView(Context context) {
        this(context, null);
    }

    public TimeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        mScroller = new Scroller(context);

        mMinVelocity = ViewConfiguration.get(getContext())
                .getScaledMinimumFlingVelocity();
        initValue();
        initPaint();

    }


    private void initPaint() {
        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setColor(Color.argb(255, 224, 95, 23));
        flag = SharedPreferencesUtil.getInstance(getContext()).getInt("flag");
//        Log.e("flag", "flag=" + flag+"flag2="+flag2);
        mShortLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /*if (flag>94&&flag<96){
            mShortLinePaint.setColor(Color.RED);
            Log.e("flag","flag="+flag);
        }else {
            mShortLinePaint.setColor(Color.WHITE);
        }*/
        mShortLinePaint.setColor(Color.WHITE);
        mShortLinePaint.setStrokeWidth(mShortLineWidth);

        mHighLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHighLinePaint.setColor(Color.WHITE);
        mHighLinePaint.setStrokeWidth(mHighLineWidth);

        mIndicatorTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIndicatorTxtPaint.setColor(Color.WHITE);
        mIndicatorTxtPaint.setTextSize(mScaleTextsize);

        mIndicatorViewPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIndicatorViewPaint.setColor(Color.WHITE);
    }

    private void initValue() {
        //设置指示器的宽度
        mIndicatorHalfWidth = UITools.convertDpToPixel(mContext, 9);
        //设置长线之间的距离
        mPartitionWidth = UITools.convertDpToPixel(mContext, 140.3f);
        //设置长线的宽度
        mHighLineWidth = UITools.convertDpToPixel(mContext, 1.67f);
        //设置短线的宽度
        mShortLineWidth = UITools.convertDpToPixel(mContext, 0.0f);
        //设置短线距离上边的距离
        mLineTopMargin = UITools.convertDpToPixel(mContext, 0.33f);
        //设置长线的长度
        mHighLineHeight = UITools.convertDpToPixel(mContext, 35.3f);
        //设置短线的高度
        mShortLineHeight = UITools.convertDpToPixel(mContext, 6.0f);
        //设置指示器数字距离上边的距离
        mIndicatorTextTopMargin = UITools.convertDpToPixel(mContext, 15f);
        //两条长线的默认数量
        mSmallPartitionCount = 3;
        //设置默认指示器所在的值
        mOriginValue = 96;
        //设置默认最小值
        mOriginValueSmall = 0;
        //设置默认分割值
        mPartitionValue = 10;
        //设置默认的开始值
        mStartValue = 50;
        //设置默认最后的值
        mEndValue = 250;
        //设置字体的大小
        mScaleTextsize = 44;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);

        drawIndicator(canvas);

        drawLinePartition(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    /**
     * 我是画背景哈
     *
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        canvas.drawRect(0, 0, mWidth, mHeight, mBgPaint);
    }

    /**
     * 我是画指示器哈
     *
     * @param canvas
     */
    private void drawIndicator(Canvas canvas) {
        Path path = new Path();
        path.moveTo(mWidth / 2 - mIndicatorHalfWidth, 0);
        path.lineTo(mWidth / 2, mIndicatorHalfWidth);
        path.lineTo(mWidth / 2 + mIndicatorHalfWidth, 0);
        canvas.drawPath(path, mIndicatorViewPaint);
    }

    private float mOffset = 0f;

    /**
     * 我是画分割的算法哈哈哈
     *
     * @param canvas
     */
    private void drawLinePartition(Canvas canvas) {
        //计算半个屏幕能有多少个partition
        int halfCount = (int) (mWidth / 2 / mPartitionWidth);
        //根据偏移量计算当前应该指向什么值
        mCurrentValue = mOriginValue - (int) (mMoveX / mPartitionWidth) * mPartitionValue;
        //相对偏移量是多少, 相对偏移量就是假设不加入数字来指示位置， 范围是0 ~ mPartitionWidth的偏移量
        mOffset = mMoveX - (int) (mMoveX / mPartitionWidth) * mPartitionWidth;


        // 画长线和短线
        for (int i = -halfCount - 1; i <= halfCount + 1; i++) {
            //表示每个长线的刻度值
            val = mCurrentValue + i * mPartitionValue;
            //只绘出范围内的图形
            if (val >= mStartValue && val <= mEndValue) {
                //画长的刻度
                float startx = mWidth / 2 + mOffset + i * mPartitionWidth;
                if (startx > 0 && startx < mWidth) {
                    canvas.drawLine(mWidth / 2 + mOffset + i * mPartitionWidth, 0 + mLineTopMargin,
                            mWidth / 2 + mOffset + i * mPartitionWidth, 0 + mLineTopMargin + mHighLineHeight, mHighLinePaint);

                    caculateTime = caculateTime(val, mCurrentValue);

                    //画刻度值
                    canvas.drawText(val + "", mWidth / 2 + mOffset + i * mPartitionWidth - mIndicatorTxtPaint.measureText(val + "") / 2,
                            0 + mLineTopMargin + mHighLineHeight + mIndicatorTextTopMargin + UITools.calcTextHeight(mIndicatorTxtPaint, val + ""), mIndicatorTxtPaint);
                }

                //画短的刻度
                if (val != mEndValue) {
                    for (int j = 1; j < mSmallPartitionCount; j++) {
                        float start_x = mWidth / 2 + mOffset + i * mPartitionWidth + j * mPartitionWidth / mSmallPartitionCount;
                        if (start_x > 0 && start_x < mWidth) {
                            if (flag==val || flag ==val) {
                                mShortLinePaint.setColor(Color.RED);
                                Log.e("flag", "flag=" + flag+"flag2"+flag2+"mCurrentValue="+mCurrentValue);
                            }
                            canvas.drawLine(mWidth / 2 + mOffset + i * mPartitionWidth + j * mPartitionWidth / mSmallPartitionCount, 0 + mLineTopMargin,
                                    mWidth / 2 + mOffset + i * mPartitionWidth + j * mPartitionWidth / mSmallPartitionCount, 0 + mLineTopMargin + mShortLineHeight, mShortLinePaint);

                        }
                    }
                }

            }

        }
        if (null != listener) {
//             long s = System.currentTimeMillis() - ((int) (mMoveX / mPartitionWidth) * mPartitionValue + (int) (mOffset / (mPartitionWidth / mSmallPartitionCount))/900);
//            long s = System.currentTimeMillis() - mCurrentValue * 15 * 60 * 1000 - (int) (mOffset / (mPartitionWidth / mSmallPartitionCount));
            s = currentTimeMillis - (int) (mOffset / (mPartitionWidth / mSmallPartitionCount)) * 1000;
            int kong = 96 - mCurrentValue;
            if (kong > 0) {
                s = s - kong * (int) (mOffset / (mPartitionWidth / mSmallPartitionCount)) * 1000;
                Log.e("kong", "间隔数=" + kong + ",减的数目=" + kong * (int) (mOffset / (mPartitionWidth / mSmallPartitionCount)) * 1000);
            }

            /*for (int i =0;i<=kong;i++){
                if (kong==0){
                    s = currentTimeMillis - (int) (mOffset / (mPartitionWidth / mSmallPartitionCount)) * 1000;
                }else {
                    s = currentTimeMillis - kong *(int) (mOffset / (mPartitionWidth / mSmallPartitionCount)) * 1000;
                }

            }*/

//            s = System.currentTimeMillis() - (int) (mOffset / (mPartitionWidth / mSmallPartitionCount)) * 1000 * (96 - mCurrentValue);

            Date date = new Date(s);
            SimpleDateFormat aFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = aFormat.format(date);
//            Log.e("s","指针所指向的时间s="+s);
            listener.onValueChange(format, -(int) (mOffset / (mPartitionWidth / mSmallPartitionCount)));
        }
    }

    /**
     * 计算时间的方法
     *
     * @param val           总值
     * @param mCurrentValue 当前值
     */
    private String caculateTime(long val, long mCurrentValue) {
        //七天的时间   毫秒
        long sevenDayTime = mOriginValue * 15 * 60 * 1000;
        //指针所指向与最后的差距的毫秒 毫秒
        long pointCurrentTimeValue = (mOriginValue - val) * 15 * 60 * 1000;
        //这个就是每个长线的时间值
        long LongLineTime = currentTimeMillis - pointCurrentTimeValue;
        Date date = new Date(LongLineTime);
        SimpleDateFormat aFormat = new SimpleDateFormat("HH:mm:ss ");
//        Log.e("caculateTime","当前的currentTimeMillis="+currentTimeMillis+",七天总共的毫秒sevenDayTime="+sevenDayTime+
//                ",指针所指向与最后的差距的毫秒pointCurrentTimeValue="+pointCurrentTimeValue+",每个长线的时间"+date);
        return aFormat.format(date);
    }


    private boolean isActionUp = false;
    private float mLastX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float xPosition = event.getX();

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isActionUp = false;
                mScroller.forceFinished(true);
                if (null != animator) {
                    animator.cancel();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                isActionUp = false;
                float off = xPosition - mLastX;

                if ((mMoveX <= mRightOffset) && off < 0 || (mMoveX >= mLeftOffset) && off > 0) {

                } else {
                    mMoveX += off;
                    postInvalidate();
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isActionUp = true;
                f = true;
                countVelocityTracker(event);
                return false;
            default:
                break;
        }

        mLastX = xPosition;
        return true;
    }

    //值动画
    private ValueAnimator animator;

    private boolean isCancel = false;

    private void startAnim() {
        isCancel = false;
        float smallWidth = mPartitionWidth / mSmallPartitionCount;
        float neededMoveX;
        if (mMoveX < 0) {
            neededMoveX = (int) (mMoveX / smallWidth - 0.5f) * smallWidth;
        } else {
            neededMoveX = (int) (mMoveX / smallWidth + 0.5f) * smallWidth;
        }
        animator = new ValueAnimator().ofFloat(mMoveX, neededMoveX);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (!isCancel) {
                    mMoveX = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isCancel = true;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    private boolean f = true;

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            float off = mScroller.getFinalX() - mScroller.getCurrX();
            off = off * functionSpeed();
            if ((mMoveX <= mRightOffset) && off < 0) {
                mMoveX = mRightOffset;
            } else if ((mMoveX >= mLeftOffset) && off > 0) {
                mMoveX = mLeftOffset;
            } else {
                mMoveX += off;
                if (mScroller.isFinished()) {
                    startAnim();
                } else {
                    postInvalidate();
                    mLastX = mScroller.getFinalX();
                }
            }

        } else {
            if (isActionUp && f) {
                startAnim();
                f = false;

            }
        }
    }

    /**
     * 控制滑动速度
     *
     * @return
     */
    private float functionSpeed() {
        return 0.2f;
    }

    private void countVelocityTracker(MotionEvent event) {
        mVelocityTracker.computeCurrentVelocity(1000, 3000);
        float xVelocity = mVelocityTracker.getXVelocity();
        if (Math.abs(xVelocity) > mMinVelocity) {
            mScroller.fling(0, 0, (int) xVelocity, 0, Integer.MIN_VALUE,
                    Integer.MAX_VALUE, 0, 0);
        } else {

        }
    }

    public void setStartValue(int mStartValue) {
        this.mStartValue = mStartValue;
        recaculate();
        invalidate();
    }

    public void setEndValue(int mEndValue) {
        this.mEndValue = mEndValue;
        recaculate();
        invalidate();
    }

    public void setPartitionValue(int mPartitionValue) {
        this.mPartitionValue = mPartitionValue;
        recaculate();
        invalidate();
    }

    public void setPartitionWidthInDP(float mPartitionWidth) {
        this.mPartitionWidth = UITools.convertDpToPixel(mContext, mPartitionWidth);
        recaculate();
        invalidate();
    }

    public void setmValue(int mValue) {
        this.mCurrentValue = mValue;
        invalidate();
    }

    public void setSmallPartitionCount(int mSmallPartitionCount) {
        this.mSmallPartitionCount = mSmallPartitionCount;
        recaculate();
        invalidate();
    }

    public void setOriginValue(int mOriginValue) {
        this.mOriginValue = mOriginValue;
        recaculate();
        invalidate();
    }

    public void setOriginValueSmall(int small) {
        this.mOriginValueSmall = small;
        recaculate();
        invalidate();
    }

    private void recaculate() {
        mMoveX = -mOriginValueSmall * (mPartitionWidth / mSmallPartitionCount);
        mRightOffset = -1 * (mEndValue - mOriginValue) * mPartitionWidth / mPartitionValue;
        mLeftOffset = -1 * (mStartValue - mOriginValue) * mPartitionWidth / mPartitionValue;
    }
}

