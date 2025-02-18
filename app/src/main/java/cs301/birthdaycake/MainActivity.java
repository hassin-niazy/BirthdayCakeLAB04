package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.Touch;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        CakeView CakeByID = findViewById(R.id.cakeview);
        CakeController cakeCtrl = new CakeController(CakeByID);
        findViewById(R.id.buttonBlowout).setOnClickListener(cakeCtrl);

        Switch wick = findViewById(R.id.candleSwitch);
        wick.setOnCheckedChangeListener(cakeCtrl);

        SeekBar seek = findViewById(R.id.candleSeekBar);
        seek.setOnSeekBarChangeListener(cakeCtrl);

        CakeByID.setOnTouchListener(cakeCtrl);


    }
    public void goodbye(View button)
    {
        Log.i("button","Goodbye!");
        finishAffinity();
    }

}
