package org.sogrey.lock9view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * 手势密码图案提示
 *
 *  create by Sogrey on 2017/03/09
 */
public class LockIndicator extends View {

    private int      numRow        =3;    // 行
    private int      numColum      =3; // 列
    private int      patternWidth  =40;
    private int      patternHeight =40;
    private int      f             =5;
    private int      g             =5;
    private int      strokeWidth   =3;
    private Paint    paint         =null;
    private Drawable patternNoraml =null;
    private Drawable patternPressed=null;
    private float  nodeSize;
    private String lockPassStr; // 手势密码

    /**
     *
     * @param context
     */
    public LockIndicator(Context context) {
        super(context);
        init(context,null,0,0);
    }

    /**
     *
     * @param context
     * @param attrs
     */
    public LockIndicator(Context context,AttributeSet attrs) {
        super(context,attrs);
        init(context,attrs,0,0);
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public LockIndicator(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        init(context,attrs,defStyleAttr,0);
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    private void init(Context context,AttributeSet attrs,int defStyleAttr,int defStyleRes) {
        // 获取定义的属性
        TypedArray a=context.obtainStyledAttributes(attrs,org.sogrey.lock9view.R.styleable
                .Lock9View_indicator,defStyleAttr,defStyleRes);
        patternNoraml=a.getDrawable(org.sogrey.lock9view.R.styleable
                .Lock9View_indicator_lock9_indicator_normal);
        patternPressed=a.getDrawable(org.sogrey.lock9view.R.styleable
                .Lock9View_indicator_lock9_indicator_active);
        nodeSize=a.getDimension(org.sogrey.lock9view.R.styleable
                .Lock9View_indicator_lock9_indicator_nodeSize,context
                .getResources().getDimension(R.dimen.nodeSize_indicator));
        ckeckNull();
        if (patternNoraml==null||patternPressed==null)
            throw new NullPointerException("The indicator normal or active drawable must noNull");
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        if (patternPressed!=null) {
            patternWidth=nodeSize!=0f ? (int)nodeSize : patternPressed.getIntrinsicWidth();
            patternHeight=nodeSize!=0f ? (int)nodeSize : patternPressed.getIntrinsicHeight();
            this.f=(patternWidth/4);
            this.g=(patternHeight/4);
            patternPressed.setBounds(0,0,patternWidth,patternHeight);
            patternNoraml.setBounds(0,0,patternWidth,patternHeight);
        }
    }

    /**
     *
     */
    private void ckeckNull() {
        if (patternNoraml==null) {
            if (VERSION.SDK_INT>=VERSION_CODES.LOLLIPOP) {
                patternNoraml = getContext().getResources().getDrawable(R.drawable.node_small_normal,null);
            }else {
                patternNoraml=getContext().getResources().getDrawable(R.drawable.node_small_normal);
            }
        }
        if (patternPressed==null) {
            if (VERSION.SDK_INT>=VERSION_CODES.LOLLIPOP) {
                patternPressed = getContext().getResources().getDrawable(R.drawable.node_small_active,
                        null);
            }else {
                patternPressed=getContext().getResources().getDrawable(R.drawable.node_small_active);
            }
        }
    }

    /**
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        if ((patternPressed==null)||(patternNoraml==null)) {
            return;
        }
        // 绘制3*3的图标
        for (int i=0;i<numRow;i++) {
            for (int j=0;j<numColum;j++) {
                paint.setColor(0xff000000);
                int i1=j*patternHeight+j*this.g;
                int i2=i*patternWidth+i*this.f;
                canvas.save();
                canvas.translate(i1,i2);
                String curNum=String.valueOf(numColum*i+(j+1));
                if (!TextUtils.isEmpty(lockPassStr)) {
                    if (!lockPassStr.contains(curNum)/*lockPassStr.indexOf(curNum)==-1*/) {
                        // 未选中
                        patternNoraml.draw(canvas);
                    } else {
                        // 被选中
                        patternPressed.draw(canvas);
                    }
                } else {
                    // 重置状态
                    patternNoraml.draw(canvas);
                }
                canvas.restore();
            }
        }
    }

    /**
     *
     * @param paramInt1
     * @param paramInt2
     */
    @Override
    protected void onMeasure(int paramInt1,int paramInt2) {
        if (patternPressed!=null)
            setMeasuredDimension(numColum*patternHeight+this.g
                    *(-1+numColum),numRow*patternWidth+this.f
                    *
                    (-1+numRow));
    }

    /**
     * 请求重新绘制
     *
     * @param paramString
     *         手势密码字符序列
     */
    public void setPath(String paramString) {
        lockPassStr=paramString;
        invalidate();
    }
}