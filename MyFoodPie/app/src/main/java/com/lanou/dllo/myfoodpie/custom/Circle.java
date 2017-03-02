package com.lanou.dllo.myfoodpie.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.lanou.dllo.myfoodpie.R;

/*
         |              |
         | \            | \
         |   | | | | | |    | | | | |||||\
         |                          |||||||\
         |         ( )              ||||||||
         |                           |||||/
         |                  | | | | | |||/
         |    |             |          |
         |    |             |          |
       / |   | |            |          |\
      |      |/             |          \|
       \ |                  |
         |                  |
           \ | | | | | | | /
             |       |            <-----弱鸡
             |       |
             |       |
*/
public class Circle extends ImageView {
    private boolean isCircle;
    private String name;

    public Circle(Context context) {
        super(context);
    }

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleHead);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int res = typedArray.getIndex(i);
            switch (res) {
                case R.styleable.CircleHead_isCircle:
                    isCircle = typedArray.getBoolean(res, false);
                    break;
                case R.styleable.CircleHead_myName:
                    name = typedArray.getString(res);
                    break;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isCircle) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                Bitmap out = getCircleBitmap(bitmap);
                Paint paint = new Paint();
                canvas.drawBitmap(out, 0, 0, paint);
            } else {
                super.onDraw(canvas);
            }
        } else {
            super.onDraw(canvas);
        }
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        Paint paint = new Paint();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap circle = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(circle);
        int radio = (width >= height ? height : width) / 2;
        canvas.drawCircle(width / 2, height / 2, radio, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return circle;
    }
}
