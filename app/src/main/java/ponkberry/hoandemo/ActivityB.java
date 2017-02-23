package ponkberry.hoandemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Ponk on 2/22/2017.
 */

public class ActivityB extends BaseActivity {
    private ImageButton toA;
    private ImageButton toB;
    private ImageButton toC;
    private ImageButton toD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        initialButtons();
        initialListener();
    }

    private void initialButtons() {
        toA = (ImageButton) findViewById(R.id.toAStandard);
        toB = (ImageButton) findViewById(R.id.toBSingleTop);
        toC = (ImageButton) findViewById(R.id.toCSingleTask);
        toD = (ImageButton) findViewById(R.id.toDSingleInstance);
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
}

