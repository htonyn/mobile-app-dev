package ponkberry.hoandemo;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ponk on 2/27/2017.
 */

public class TimerActivity extends BaseActivity {

    private Button timer_start;
    private int time;

    @BindView(R.id.timer_et)
    EditText editText;

    @BindView(R.id.timer_bt)
    Button timerButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        ButterKnife.bind(this);
        timerButton.setOnClickListener(start);
    }

    Handler mHandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            if(time>0) {
                editText.setText(String.valueOf(time));
                //public final boolena postDelayed(Runnable r, long delayMillis);
                mHandler.postDelayed(this, 1000);
                //mHandler.post(this); not delayed
            }
        }
    };

    final View.OnClickListener start = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editText.setEnabled(false);
            time = Integer.valueOf(editText.getText().toString());
            timerButton.setText("stop");
            timerButton.setOnClickListener(stop);
            mHandler.postDelayed(runnable, 1000);
        }
    };

    final View.OnClickListener stop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            timerButton.setText("reset");
            timerButton.setOnClickListener(reset);
            mHandler.removeCallbacks(runnable);
        }
    };

    final View.OnClickListener reset = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editText.setEnabled(true);
            timerButton.setText("start");
            timerButton.setOnClickListener(start);
            editText.setText(String.valueOf(20));
        }
    };
}
