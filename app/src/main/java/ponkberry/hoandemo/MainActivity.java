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
import ponkberry.hoandemo.bean.Book;
import ponkberry.hoandemo.util.UtilLog;

// The R object is based on the resource folder. If you have an error with R, then you have errors
// within your resource folder. The errors most likely  lies within the .xml files you created
// You must fix those prior to fixing the ones in the java files

public class MainActivity extends BaseActivity {

    private ImageButton bt1;
    private ImageButton bt3;

    @OnClick(R.id.bt2)
    public void button2Click() {
        Intent intent = new Intent(this,DialogActivity.class);
        startActivityForResult(intent, 2);
    }

    // onStart occurs every time you start the application.
    @Override
    protected void onStart() {
        toastShort("On Start");
        super.onStart();
    }

    // onCreate only starts on the first creation of the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialView();
        initialListener();
        // Must have ButterKnife dependency within build gradle
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
                intent.putExtra("key","value");
                Bundle bundle = new Bundle();
                bundle.putInt("Integer", 12345);
                Book book = new Book();
                book.setName("Android");
                book.setAuthor("Hoan");
                bundle.putSerializable("book",book);
                intent.putExtras(bundle);
                startActivityForResult(intent,1); // 1 is the request code
                //startActivity(intent);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListViewActivity.class);
                startActivityForResult(intent, 3);
//                toActivity(ListViewctivity.class);
//                    Intent intent = new Intent(v.getContext(), ListViewActivity.class);
//                    startActivity(intent);
            }
        }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                String message = data.getStringExtra("message");
                toastShort(message);
                break;
            case 2:
                toastShort("Dialog");
                break;
            case 3:
                toastShort("ListView");
                break;
            default:
        }
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