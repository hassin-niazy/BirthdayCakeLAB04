package cs301.birthdaycake;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener{

    private CakeView cakeView;
    private CakeModel cakeModel;

    public CakeController (CakeView cake)
    {
        this.cakeView = cake;
        this.cakeModel = cakeView.getCakeModel();
    }

    @Override
    public void onClick(View view) {

        Log.d("cake", "Click!");

        cakeModel.isCandleLit = false;
        cakeView.invalidate();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        cakeModel.candlesWick = b;
        cakeView.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    cakeModel.numCandles = i;
    cakeView.invalidate();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { // not usable
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar){ // empty
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        cakeModel.x = (int) motionEvent.getX();
        cakeModel.y = (int) motionEvent.getY();
        cakeView.invalidate();

        return true;


    }
}
