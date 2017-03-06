package ponkberry.hoandemo;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ponk on 3/1/2017.
 */

public class AnimationActivity extends BaseActivity {
    private Animation alphaAnimation;
    private Animation scaleAnimation;
    private Animation rotateAnimation;
    private Animation transAnimation;
    private Animation setAnimation;

    @BindView(R.id.animation_tv)
    TextView tv;

    @OnClick(R.id.anim_alpha)
    public void alphaClick() {
        tv.startAnimation(alphaAnimation);
    }
    @OnClick(R.id.anim_scale)
    public void scaleClick() {
        tv.startAnimation(scaleAnimation);
    }
    @OnClick(R.id.anim_rotate)
    public void rotateClick() {
        tv.startAnimation(rotateAnimation);
    }
    @OnClick(R.id.anim_trans)
    public void transClick() {
        tv.startAnimation(transAnimation);
    }
    @OnClick(R.id.anim_set)
    public void setClick() {
        tv.startAnimation(setAnimation);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        initialAnimation();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastShort("Click");
            }
        });
    }

    private void initialAnimation() {
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        transAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_trans);
        setAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_set);
    }

}
