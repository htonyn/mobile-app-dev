package ponkberry.hoandemo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ponkberry.hoandemo.util.UtilLog;

public class AnimatorActivity extends BaseActivity {


    private ValueAnimator repeatAnimator;

    @OnClick(R.id.animator_trans)
    public void trans(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv, "translationX", 0, 200, -200,0,100); //translationY
        animator.setDuration(2000);
        animator.start();
    }
    @OnClick(R.id.animator_scale)
    public void scale(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv, "scaleX", 0, 3, 1); //scaleY
        animator.setDuration(2000);
        animator.start();
    }
    @OnClick(R.id.animator_color)
    public void color(){
        ObjectAnimator animator = ObjectAnimator.ofInt(tv, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        // Each color change is given in hexadecimal
        // The first pair of bytes denotes the alpha
        // The last three pairs correspond to RGB
        animator.setDuration(8000);
        // You must create your own Color Evaluator
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();
    }
    @OnClick(R.id.animator_char)
    public void charFAnim(){
        ValueAnimator animator = ValueAnimator.ofObject(new CharEvaluator(),new Character('A'),new Character('Z'));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char text = (char)animation.getAnimatedValue();
                tv.setText(String.valueOf(text));
            }
        });
        animator.setDuration(10000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }
    @OnClick(R.id.animator_alpha)
    public void alpha(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv,"alpha",1,0,1);
        animator.setDuration(2000);
        animator.start();
    }
    @OnClick(R.id.animator_rotation)
    public void rotation(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv,"rotation",0,180,0);//rotationX   rotationY
        animator.setDuration(2000);
        animator.start();
    }



    @OnClick(R.id.animator_bt_start)
    public void startAnimator(){
        repeatAnimator = doAnimatorListener();
//        repeatAnimator.setStartDelay(3000);
        repeatAnimator.start();
    }

    @OnClick(R.id.animator_bt_cancel)
    public void cancelAnimator(){
        repeatAnimator.cancel();
        repeatAnimator.removeAllListeners();
        repeatAnimator.removeAllUpdateListeners();
    }

    @BindView(R.id.animator_tv)
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        ButterKnife.bind(this);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastShort("Click");
            }
        });
    }

    private ValueAnimator doAnimatorListener(){
        // This will create 400 time slots, 1 to 400. It does not start at 0. Each time the program
        // calls the animation, it'll be at the animation value of 1, each time incrementing by 1
        ValueAnimator animator = ValueAnimator.ofInt(0,400);
        // This is the update call for the animation, each time it wants to update the animation
        // the system will go here.
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int)animation.getAnimatedValue();
                // This will get the frame of each animation based on its animation value retrieved
                // above.
                tv.layout(tv.getLeft(),curValue,tv.getRight(),curValue+tv.getHeight());
            }
        });
        // At the start and end of the animation
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                UtilLog.logD("Yan","animation start");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                UtilLog.logD("Yan","animation end");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                UtilLog.logD("Yan","animation cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                UtilLog.logD("Yan","animation repeat");
            }
        });
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new BounceInterpolator());
        animator.setDuration(1000);
        return animator;
    }

    private class CharEvaluator implements TypeEvaluator<Character> {
        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int startInt  = (int)startValue;
            int endInt = (int)endValue;
            int curInt = (int)(startInt + fraction *(endInt - startInt));
            char result = (char)curInt;
            return result;
        }
    }

    public class ArgbEvaluator implements TypeEvaluator {
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            int startInt = (Integer) startValue;
            int startA = (startInt >> 24);
            int startR = (startInt >> 16) & 0xff;
            int startG = (startInt >> 8) & 0xff;
            int startB = startInt & 0xff;

            int endInt = (Integer) endValue;
            int endA = (endInt >> 24);
            int endR = (endInt >> 16) & 0xff;
            int endG = (endInt >> 8) & 0xff;
            int endB = endInt & 0xff;

            return (int)((startA + (int)(fraction * (endA - startA))) << 24) |
                    (int)((startR + (int)(fraction * (endR - startR))) << 16) |
                    (int)((startG + (int)(fraction * (endG - startG))) << 8) |
                    (int)((startB + (int)(fraction * (endB - startB))));
        }
    }
}
