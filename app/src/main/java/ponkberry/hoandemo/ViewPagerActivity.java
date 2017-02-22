package ponkberry.hoandemo;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import ponkberry.hoandemo.adapter.ViewPagerAdapter;
import ponkberry.hoandemo.bean.Book;
import ponkberry.hoandemo.fragment.HistoryFragment;
import ponkberry.hoandemo.fragment.ContentFragment;
import ponkberry.hoandemo.fragment.LoginFragment;
import ponkberry.hoandemo.util.UtilLog;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    private TabLayout tabLayout;

    // Because the superclass of ViewPager is the Fragment imported from support.v4, the Fragments
    // used here must also be imported from
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String message = intent.getStringExtra("key");
        int number = bundle.getInt("Integer",0);
        // If the key is incorrect, or none of the keys are equal to Integer, then the value 0 is
        // stored into number.
        int fakeNumber = bundle.getInt("fake", 0);
        Book book = (Book) bundle.getSerializable("book");
        UtilLog.logD("ViewPagerAcitivity, value is: ", message);
        UtilLog.logD("ViewPagerAcitivity, number is: ", ""+number);
        UtilLog.logD("ViewPagerAcitivity, fake number is: ", String.valueOf(fakeNumber));

        UtilLog.logD("ViewPagerActivity, book author is: ", book.getAuthor());
        UtilLog.logD("ViewPagerActivity, book name is: ", book.getName());

        initial();
    }


    private void initial() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        fragmentList.add(new HistoryFragment());
        fragmentList.add(new ContentFragment());
        fragmentList.add(new LoginFragment());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        // ViewPagerAdapter must accept a Fragment Manager as a parameter
        viewPagerAdapter.setContent(fragmentList);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("message","ViewPagerActivity");
        setResult(RESULT_OK, intent);
        super.onBackPressed();

    }
}