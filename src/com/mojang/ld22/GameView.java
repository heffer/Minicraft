package com.mojang.ld22;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by IntelliJ IDEA.
 * User: chris
 * Date: 12/22/11
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameView extends View {
    private static Bitmap gameBitmap;
    public static Canvas gameCanvas;

    private boolean loaded = false;
    private Rect source, dest;
    private int offsetX = 0;
    
    private Paint drawPaint;

    public GameView(Context context) {
        super(context);
        refreshCanvasSize();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        refreshCanvasSize();
    }
    
    public static void refreshCanvasSize() {
        gameBitmap = Bitmap.createBitmap(Game.getWidth(), Game.getHeight(), Bitmap.Config.ARGB_8888);
        gameCanvas = new Canvas(gameBitmap);    	
    }
    
    public void initMeasure()
    {
        drawPaint = new Paint();
        drawPaint.setAntiAlias(false);
        drawPaint.setFilterBitmap(false);

        loaded = true;
        
        source = new Rect(0, 0, Game.getWidth(), Game.getHeight());

        float proportion = (float)Game.getWidth() / (float)Game.getHeight();

        float newWidth = this.getHeight() * proportion;

        offsetX = (int)(this.getWidth() - newWidth) / 2;

        dest = new Rect(offsetX, 0, (int)newWidth+offsetX, this.getHeight());
    }


    @Override 
    protected void onDraw(Canvas canvas) {
        if (gameBitmap != null) {
            
            if(!loaded) initMeasure();

            canvas.drawBitmap(gameBitmap, source, dest, drawPaint);
            //canvas.drawBitmap(gameBitmap, 0, 0, null);
            //mCanvas.drawBitmap(mBitmap, 0,0,null);
        }
    }


}
