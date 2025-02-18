package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.Nullable;

public class CakeView extends SurfaceView {

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();

    Paint redSquare = new Paint();
    Paint balloonPaint = new Paint();

    private CakeModel cakeModel = new CakeModel();

    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 120.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;

    

    /**
     * Actor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(0xFF000000);  //black
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        redSquare.setColor(0xFFFF0000);
        balloonPaint.setColor(0xFF0000FF); // really angry blue
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);

        setBackgroundColor(Color.WHITE);  //better than black default

    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {


        if (getCakeModel().candlesWick) {
            canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);
            if (getCakeModel().isCandleLit) {
                //draw the outer flame
                float flameCenterX = left + candleWidth / 2;
                float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

                //draw the inner flame
                flameCenterY += outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
            }
            //draw the wick
            float wickLeft = left + candleWidth/2 - wickWidth/2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
        }


    }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.*
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        //Now a candle in the center
        for (int i = 1 ; i <= getCakeModel().numCandles; i++) {
          //  drawCandle(canvas, cakeLeft + cakeWidth / 4 - candleWidth / 2, cakeTop);
             drawCandle(canvas, cakeLeft + cakeWidth / 7*i + cakeLeft - candleWidth / 9*i, cakeTop);

           // drawCandle(canvas, cakeWidth/i, cakeTop);
           // drawCandle(canvas, cakeLeft + cakeWidth/i, cakeTop );
        }

        canvas.drawRect(((float) cakeModel.x), ((float) cakeModel.y),(((float) cakeModel.x) - 30), (((float) cakeModel.y)+45), redSquare);
        canvas.drawRect(((float) cakeModel.x), ((float) cakeModel.y),(((float) cakeModel.x) + 30), (((float) cakeModel.y)-45), redSquare);
        canvas.drawRect(((float) cakeModel.x), ((float) cakeModel.y),(((float) cakeModel.x)-30), (((float) cakeModel.y)-45), candlePaint);
        canvas.drawRect(((float) cakeModel.x), ((float) cakeModel.y),(((float) cakeModel.x) + 30), (((float) cakeModel.y)+45), candlePaint);
        canvas.drawOval(((float) cakeModel.x)-20, ((float) cakeModel.y)-30,(((float) cakeModel.x) + 30), (((float) cakeModel.y)+45), balloonPaint);
        canvas.drawLine(((float) cakeModel.x)+7, ((float) cakeModel.y+40), ((float) cakeModel.x)+7, ((float) cakeModel.y)+80 , cakePaint);

    }//onDraw


    public CakeModel getCakeModel() {
        return cakeModel;
    }


}//class CakeView

