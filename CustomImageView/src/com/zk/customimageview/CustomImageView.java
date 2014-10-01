package com.zk.customimageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CustomImageView extends ImageView {

	public static final int TYPE_OVAL = 0;
	public static final int TYPE_CIRCLE = 1;
	public static final int TYPE_ROUND_RECT = 2;
	public static final int TYPE_SQUARE = 3;
	
	private Bitmap mBitmap;
	private Canvas bitmapCanvas;
	private Paint mBitmapPaint;
	
	private static final Xfermode sXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
	
	private int defaultType = TYPE_OVAL;
	
	public CustomImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomImageView(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas){
		Drawable drawable = getDrawable();
		if(drawable != null){
			if(mBitmap == null){
				mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
				bitmapCanvas = new Canvas(mBitmap);
			}
			drawable.draw(bitmapCanvas);
			
			mBitmapPaint.setXfermode(sXfermode);
			bitmapCanvas.drawBitmap(getBitmap(getWidth(), getHeight()), 0.0f, 0.0f, mBitmapPaint);
		}
		
		if(mBitmap != null){
			mBitmapPaint.setXfermode(null);
			canvas.drawBitmap(mBitmap, 0.0f, 0.0f, mBitmapPaint);
		}
	}
	
	/**
	 * set Image type
	 * @param type
	 */
	public void setType(int type){
		defaultType = type;
		this.invalidate();
	}
	
	private Bitmap getBitmap(int width, int height){
		Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        
        switch(defaultType){
        case TYPE_OVAL:
        	canvas.drawOval(new RectF(0.0f, 0.0f, width, height), paint);
        	break;
        	
        case TYPE_CIRCLE:
        	float radius = Math.min((float)width / 2, (float)height / 2);
        	canvas.drawCircle((float) width / 2, (float) height / 2, radius, paint);
        	break;
        	
        case TYPE_ROUND_RECT:
        	canvas.drawRoundRect(new RectF(0, 0, width, height), 20.0f, 20.0f, paint);
        	break;
        	
        case TYPE_SQUARE:
        	final boolean heightBigger = width <= height;
        	if(heightBigger){
        		float top = (float)(height - width) / 2;
        		canvas.drawRect(new RectF(0.0f, top, width, top + width), paint);
        	}else{
        		float left = (float)(width - height) / 2;
        		canvas.drawRect(new RectF(left, 0.0f, left + height, height), paint);
        	}
        	break;
        	
        default:
        	break;
        }
        
        return bitmap;
	}
}
