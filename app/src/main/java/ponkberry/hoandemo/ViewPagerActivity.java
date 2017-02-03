package ponkberry.hoandemo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import ponkberry.hoandemo.adapter.ViewPagerAdapter;
import ponkberry.hoandemo.fragment.HistoryFragment;
import ponkberry.hoandemo.fragment.ContentFragment;
import ponkberry.hoandemo.fragment.LoginFragment;

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
}
