package ponkberry.hoandemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Ponk on 2/22/2017.
 */

public class ActivityD extends BaseActivity {
    private Button toA;
    private Button toB;
    private Button toC;
    private Button toD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);
        initialButtons();
        initialListener();
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
}
