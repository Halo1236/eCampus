package com.ayhalo.ecampus.ui.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * com.ayhalo.ecampus.ui.viewpager
 * Created by wuyh on 2018/3/8.
 */

public class TabLayoutView extends LinearLayout {

    private Context context;
    private String[] titles; //要显示的标题
    private int[] imgs; //图标
    private int[] imgsSelected; //图标

    private int imgwidth;
    private int imgheight;
    private int txtsize; //标题大小
    private int txtColor; //标题未选中颜色
    private int txtSelectedColor; //选择颜色

    private List<TextView> textViews; //保存标题
    private List<ImageView> imageViews; //保存图片
    private int currentIndex = 0;

    private OnItemOnclickListener onItemOnclickListener;

    public TabLayoutView(Context context) {
        this(context, null);
    }

    public TabLayoutView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void setOnItemOnclickListener(OnItemOnclickListener onItemOnclickListener) {
        this.onItemOnclickListener = onItemOnclickListener;
    }

    /**
     * 设置标题、图片和当前选中的条目
     *
     * @param tabtxts
     * @param imgs
     * @param currentIndex
     */
    public void setDataSource(String[] tabtxts, int[] imgs, int[] imgsSelected, int currentIndex) {
        this.titles = tabtxts;
        this.imgs = imgs;
        this.currentIndex = currentIndex;
        this.imgsSelected = imgsSelected;
    }

    /**
     * 设置图标大小
     *
     * @param imgwidth  图标的宽度
     * @param imgheight 图标的高度
     */
    public void setImageStyle(int imgwidth, int imgheight) {
        this.imgwidth = dip2px(context, imgwidth);
        this.imgheight = dip2px(context, imgheight);
    }

    /**
     * 设置标题颜色
     *
     * @param txtsize          文字大小
     * @param txtColor         文字未选中颜色
     * @param txtSelectedColor 文字选中时颜色
     */
    public void setTextStyle(int txtsize, int txtColor, int txtSelectedColor) {
        this.txtsize = txtsize;
        this.txtColor = txtColor;
        this.txtSelectedColor = txtSelectedColor;
    }

    /**
     * 动态布局
     * 1、外层为横向线下布局
     * 2、动态添加相对布局，平分父布局，使宽度一致，添加到横向布局中
     * 3、总线布局添加图标和标题，并添加到相对布局中
     * 4、添加圆点到相对布局中，并设置在3的右上角
     */
    public void initDatas() {
        textViews = new ArrayList<>();
        imageViews = new ArrayList<>();

        setOrientation(HORIZONTAL);
        LinearLayout.LayoutParams lp = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.weight = 1;
        lp.gravity = Gravity.CENTER;

        LayoutParams imglp = new LayoutParams(imgwidth, imgheight);
        imglp.gravity = Gravity.CENTER;

        LayoutParams txtlp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txtlp.gravity = Gravity.CENTER;
        txtlp.setMargins(0, dip2px(context, 3), 0, 0);

        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        int size = titles.length;
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(imglp);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            TextView textView = new TextView(context);
            textView.setText(titles[i]);
            textView.setLayoutParams(txtlp);
            textView.setTextSize(txtsize);

            LinearLayout cly = new LinearLayout(context);
            cly.setId(i + 100);
            cly.setGravity(Gravity.CENTER);
            cly.setOrientation(VERTICAL);
            cly.setLayoutParams(imglp);
            cly.addView(imageView);

            RelativeLayout prl = new RelativeLayout(context);

            final int index = i;
            prl.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setSelectStyle(index);
                    if (onItemOnclickListener != null) {
                        onItemOnclickListener.onItemClick(index);
                    }
                }
            });

            cly.addView(textView);
            prl.addView(cly, rlp);
            addView(prl, lp);

            textViews.add(textView);
            imageViews.add(imageView);
        }
        setSelectStyle(currentIndex);
    }

    public void setSelectStyle(int index) {
        int size = titles.length;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                textViews.get(i).setTextColor(context.getResources().getColor(txtSelectedColor));
                imageViews.get(i).setSelected(true);
                imageViews.get(i).setImageResource(imgsSelected[i]);

            } else {
                textViews.get(i).setTextColor(context.getResources().getColor(txtColor));
                imageViews.get(i).setSelected(false);
                imageViews.get(i).setImageResource(imgs[i]);

            }
        }
    }


    public interface OnItemOnclickListener {
        void onItemClick(int index);
    }

    private int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}