package ponkberry.hoandemo;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ponkberry.hoandemo.adapter.NavMenuAdapter;
import ponkberry.hoandemo.bean.Book;
import ponkberry.hoandemo.dialog.Quiz4Dialog;
import ponkberry.hoandemo.util.UtilLog;

// The R object is based on the resource folder. If you have an error with R, then you have errors
// within your resource folder. The errors most likely  lies within the .xml files you created
// You must fix those prior to fixing the ones in the java files

public class MainActivity extends BaseActivity implements View.OnTouchListener, AdapterView.OnItemClickListener {
// In order to use gestures in an activity, you must implement View.OnTouchListener

    private ImageButton bt1;
    private ImageButton bt3;
    private ImageButton launch_mode;
    private GestureDetector mGestureDetector;
    private GestureDetector nGestureDetector;

    private DrawerLayout mNavMenu;
    private ListView mNavList;
    private ArrayList<String> listResult;
    private boolean navMenuVisible;
    private static final String[] navMenuList = new String[] { "MainActivity", "ViewPagerActivity", "ListViewActivity", "Activity A", "Activity B", "Activity C", "Activity D", "TimerActivity", "AnimationActivity", "AnimatorActivity", "LoginActivity", "Test1", "Test2", "Test3", "Test4",  };

    @BindView(R.id.main_fl)
    FrameLayout fl;

    @BindView(R.id.gesture_nav_menu)
    View nav_menu;

    @OnClick(R.id.logout)
    public void logOut() {
        toActivity(LoginActivity.class);
    }

    @OnClick(R.id.clearPreferences)
    public void clearPref() {
        SharedPreferences mSharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        //mSharedPreferences.getString("loginPref","");
//        String getSPName = mSharedPreferences.getString("username","");
//        UtilLog.logD("mainPref",getSPName);
//        toastShort(""+getSPName);
        SharedPreferences.Editor mSPEditor = mSharedPreferences.edit();
        mSPEditor.clear();
        mSPEditor.commit();
    }

    @OnClick(R.id.nav_menu_bt)
    public void navMenu() {
//        if (mNavMenu.isDrawerOpen(Gravity.START)) {
//            mNavMenu.closeDrawer(Gravity.START);
//        } else {
//            mNavMenu.openDrawer(Gravity.START);
//        }
        if (!navMenuVisible) {
            //Open Animation
            navOpen();
        } else {
            //Close Animation
            navClose();
        }
    }

    @OnClick(R.id.main_custom_dialog_bt)
    public void toQuiz() {
            final Quiz4Dialog dialog = new Quiz4Dialog(this, new Quiz4Dialog.ICustomDialogEventListener() {
                @Override
                public void onCancelListener() { toViewPagerActivity(); }

                @Override
                public void onToDialogListener() {
                    toActivity(DialogActivity.class);
                }

                @Override
                public void onToListViewListener() {
                    toActivity(ListViewActivity.class);
                }
            });
            dialog.show();
    }

    @OnClick(R.id.main_animator_bt)
    public void toAnimator() {
        toActivity(AnimatorActivity.class);
    }

    @OnClick(R.id.main_timer_bt)
    public void toTimer() {
        toActivity(TimerActivity.class);
    }

    @OnClick(R.id.main_animation_bt)
    public void toAnimation() {
        toActivity(AnimationActivity.class);
    }

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
        // Must have ButterKnife dependency within build gradle
        ButterKnife.bind(this);
        initialView();
        initialListener();

        mGestureDetector = new GestureDetector(this,new simpleGestureListener());
        fl.setOnTouchListener(this);
        fl.setLongClickable(true);
        nav_menu.setOnTouchListener(this);
        nav_menu.setLongClickable(true);

        //navMenu = (DrawerLayout) findViewById(R.id.main_nav_menu);
        listResult = new ArrayList<String>();
        for (int i = 0; i < navMenuList.length; i++) {
            listResult.add(navMenuList[i]);
        }
        mNavList = (ListView) findViewById(R.id.nav_menu_list);
        NavMenuAdapter listViewAdapter = new NavMenuAdapter(this, listResult);
        mNavList.setAdapter(listViewAdapter);
        mNavList.setOnItemClickListener(this);
        navMenuVisible = false;
    }

    // This method must have an override
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this,"ListView as clicked at position: "+ position, Toast.LENGTH_LONG).show();
        Class[] classList = new Class[] { MainActivity.class, ViewPagerActivity.class, ListViewActivity.class, ActivityA.class, ActivityB.class, ActivityC.class, ActivityD.class, TimerActivity.class, AnimationActivity.class, AnimatorActivity.class, LoginActivity.class };
        if (position == 1 || position % classList.length == 1) {
            toViewPagerActivity();
        } else {
            toActivity(classList[position % classList.length]);
        }

        //Log.d("NavMenu",String.valueOf(position));
    }

    private void initialView() {
        bt1 = (ImageButton) findViewById(R.id.bt1);
        bt3 = (ImageButton) findViewById(R.id.bt3);
        launch_mode = (ImageButton) findViewById(R.id.launch_mode);
    }

    private void toViewPagerActivity() {
        Intent intent = new Intent(getApplicationContext(), ViewPagerActivity.class);
        intent.putExtra("key","value");
        Bundle bundle = new Bundle();
        bundle.putInt("Integer", 12345);
        Book book = new Book();
        book.setName("Android");
        book.setAuthor("Hoan");
        bundle.putSerializable("book",book);
        intent.putExtras(bundle);
        startActivityForResult(intent,1); // 1 is the request code
    }

    private void initialListener() {
        // Methods should start with lower case letters
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Button1 was clicked",Toast.LENGTH_LONG).show();
                toViewPagerActivity();
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListViewActivity.class);
                startActivityForResult(intent, 3);
//                toActivity(ListViewActivity.class);
//                    Intent intent = new Intent(v.getContext(), ListViewActivity.class);
//                    startActivity(intent);
            }
        }
        );

        launch_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(ActivityA.class);
            }
        });
    }

    public void navOpen() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(nav_menu, "translationX", 0, 400);
        animator.setDuration(1000);
        animator.start();
        navMenuVisible = true;
    }

    public void navClose() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(nav_menu, "translationX", 400, 0);
        animator.setDuration(1000);
        animator.start();
        navMenuVisible = false;
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        UtilLog.logD("MultiTouch", ""+v.getId());
        return mGestureDetector.onTouchEvent(event);
        // return false;
    }

    private class simpleGestureListener extends GestureDetector.SimpleOnGestureListener {

        // System invokes these methods in this order: onDown -> onShowPress -> onLongPRess
        public boolean onDown(MotionEvent e) {
            UtilLog.logD("MyGesture", "onDown: "+e.toString());
            //toastShort("onDown");

//            if(navMenuVisible) {
//                navClose();
//            }

            return false;
        }

        public void onShowPress(MotionEvent e) {
            UtilLog.logD("MyGesture", "onShowPress");
            //toastShort("onShowPress");
        }

        public void onLongPress(MotionEvent e) {
            UtilLog.logD("MyGesture", "onLongPress");
            //toastShort("onLongPress");
        }

        public boolean onSingleTapUp(MotionEvent e) {
            UtilLog.logD("MyGesture", "onSingleTapUp");
            //toastShort("onSingleTapUp");
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent e) {
            UtilLog.logD("MyGesture", "onSingleTapConfirmed");
            //toastShort("onSingleTapConfirmed");
            return true;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            UtilLog.logD("MyGesture", "onScroll: " + (e2.getX() - e1.getX() + "   " +distanceX));
            //toastShort("onScroll");
            //scroll_menu_v.startAnimation(transAnimation);
            // Must do this programmatically, XML animation is not sufficient

            if ((e1.getX() < e2.getX()) && distanceX < 10 && navMenuVisible != true) {
                //toastShort(""+e1.getX());
                navOpen();
            } else if (e2.getX() < e1.getX() && navMenuVisible && distanceX > 50) {
                //toastShort("Left");
                navClose();
            }

            return true;
        }

        public boolean onFling (MotionEvent e1, MotionEvent e2, float veloxityX, float velocityY) {
            UtilLog.logD("MyGesture", "onFling");

            //toastShort("onFling");
            return true;
        }

        public boolean onDoubleTap (MotionEvent e) {
            UtilLog.logD("MyGesture", "onDoubleTap");
            //toastShort("onDoubleTap");
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent e) {
            UtilLog.logD("MyGesture", "onDoubleTapEvent");
            //toastShort("onDoubleTapEvent");
            return true;
        }
    }
}