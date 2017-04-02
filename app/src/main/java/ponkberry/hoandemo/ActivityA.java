package ponkberry.hoandemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.BindView;
import ponkberry.hoandemo.util.UtilLog;

/**
 * Created by Ponk on 2/22/2017.
 */

public class ActivityA extends BaseActivity implements View.OnTouchListener {

    private Button toA;
    private Button toB;
    private Button toC;
    private Button toD;
    private GestureDetector mGestureDetector;

    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        initialButtons();
        initialListener();
        mGestureDetector = new GestureDetector(this,new ActivityA.simpleGestureListener());
        rl = (RelativeLayout) this.findViewById(R.id.a_rl);
        rl.setOnTouchListener(this);
        //rl.setLongClickable(true);
    }

    private void initialButtons() {
        toA = (Button) findViewById(R.id.toAStandard);
        toB = (Button) findViewById(R.id.toBSingleTop);
        toC = (Button) findViewById(R.id.toCSingleTask);
        toD = (Button) findViewById(R.id.toDSingleInstance);
    }

    private void initialListener() {
        // Methods should start with lower case letters
        toA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(ActivityA.class);
            }
        });

        toB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(ActivityB.class);
            }
        });

        toC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(ActivityC.class);
            }
        });

        toD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(ActivityD.class);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        this.mGestureDetector.onTouchEvent(e);
        return super.onTouchEvent(e);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
        // return false;
    }

    private class simpleGestureListener implements GestureDetector.OnGestureListener {

        // System invokes these methods in this order: onDown -> onShowPress -> onLongPress
        @Override
        public boolean onDown(MotionEvent e) {
            UtilLog.logD("MyGesture", "onDown");
            toastShort("onDown");
            return false;
        }

        public void onShowPress(MotionEvent e) {
            UtilLog.logD("MyGesture", "onShowPress");
            toastShort("onShowPress");
        }

        public void onLongPress(MotionEvent e) {
            UtilLog.logD("MyGesture", "onLongPress");
            toastShort("onLongPress");
        }

        public boolean onSingleTapUp(MotionEvent e) {
            UtilLog.logD("MyGesture", "onSingleTapUp");
            toastShort("onSingleTapUp");
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent e) {
            UtilLog.logD("MyGesture", "onSingleTapConfirmed");
            toastShort("onSingleTapConfirmed");
            return true;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            UtilLog.logD("MyGesture", "onScroll: " + (e2.getX() - e1.getX() + "   " +distanceX));
            toastShort("onScroll");
            //toastOneSecond("onScroll");
            return true;
        }

        public boolean onFling (MotionEvent e1, MotionEvent e2, float veloxityX, float velocityY) {
            UtilLog.logD("MyGesture", "onFling");
            toastShort("onFling");
            return true;
        }

        public boolean onDoubleTap (MotionEvent e) {
            UtilLog.logD("MyGesture", "onDoubleTap");
            toastShort("onDoubleTap");
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent e) {
            UtilLog.logD("MyGesture", "onDoubleTapEvent");
            toastShort("onDoubleTapEvent");
            return true;
        }
    }
}
