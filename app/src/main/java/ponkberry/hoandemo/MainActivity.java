package ponkberry.hoandemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ponkberry.hoandemo.util.UtilLog;

// The R object is based on the resource folder. If you have an error with R, then you have errors
// within your resource folder. The errors most likely  lies within the .xml files you created
// You must fix those prior to fixing the ones in the java files

public class MainActivity extends BaseActivity {

    private ImageButton bt1;
    private ImageButton bt3;

    @OnClick(R.id.bt2)
    public void button2Click() {
        toActivity(DialogActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialView();
        initialListener();
        // Must have ButterKnife dependency
        ButterKnife.bind(this);
    }

    private void initialView() {
        bt1 = (ImageButton) findViewById(R.id.bt1);
        bt3 = (ImageButton) findViewById(R.id.bt3);
    }

    private void initialListener() {
        // Methods should start with lower case letters
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Button1 was clicked",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), ViewPagerActivity.class);
                startActivity(intent);
            }
        }
        );

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(ListViewActivity.class);
//                    Intent intent = new Intent(v.getContext(), ListViewActivity.class);
//                    startActivity(intent);
            }
        }
        );
    }
    public void onClick(View v) {
//        Toast.makeText(this,"Button2 was clicked",Toast.LENGTH_LONG).show();
        toastLong("Button2 was clicked.");
        UtilLog.logD("testD","Toast");
        //Log.d("testD","Toast");
        //Log.e("testE","Toast");
    }
}

// If a user clicks a button multiple times, you should use the setOnClickListener so that you
// can save on resources.