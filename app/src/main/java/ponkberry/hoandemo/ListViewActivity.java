package ponkberry.hoandemo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ponkberry.hoandemo.adapter.ListViewAdapter;
import ponkberry.hoandemo.adapter.ViewPagerAdapter;
import ponkberry.hoandemo.fragment.EighthFragment;
import ponkberry.hoandemo.fragment.FifthFragment;
import ponkberry.hoandemo.fragment.FirstFragment;
import ponkberry.hoandemo.fragment.FourthFragment;
import ponkberry.hoandemo.fragment.SecondFragment;
import ponkberry.hoandemo.fragment.SeventhFragment;
import ponkberry.hoandemo.fragment.SixthFragment;
import ponkberry.hoandemo.fragment.ThirdFragment;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // In order to use on click for the list view, you need to implement the listener for the
    // on click

    private ListView listView;
    private ArrayList<String> listResult;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listResult = new ArrayList<String>();
        createFakeResult();
        initialView();
    }

    public void createFakeResult() {
        listResult.add("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaA");
        listResult.add("BBBBBBBBBBBBBBBBBBBBBBBBB");
        listResult.add("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
        listResult.add("DD");
        listResult.add("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        listResult.add("F");
        listResult.add("GGGGGGGGGG");
        listResult.add("H");
        listResult.add("I");
        listResult.add("J");
        listResult.add("K");
        listResult.add("L");
        listResult.add("M");
        listResult.add("N");
        listResult.add("OOOOOOOOOOOO");
        listResult.add("P");
        listResult.add("Q");
    }

    private void initialView() {
        listView = (ListView) findViewById(R.id.list_view);
        View view = getLayoutInflater().inflate(R.layout.list_view_header, null);
        // Parameters for inflate is the layout file, and its group
        // Inflater finds the view
        LinearLayout listViewHeader = (LinearLayout) view.findViewById(R.id.list_view_header);

        viewPager = (ViewPager) view.findViewById(R.id.list_view_pager);
        fragmentList.add(new FirstFragment());
        fragmentList.add(new SecondFragment());
        fragmentList.add(new ThirdFragment());
        fragmentList.add(new FourthFragment());
        fragmentList.add(new FifthFragment());
        fragmentList.add(new SixthFragment());
        fragmentList.add(new SeventhFragment());
        fragmentList.add(new EighthFragment());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        // ViewPagerAdapter must accept a Fragment Manager as a parameter
        viewPagerAdapter.setContent(fragmentList);
        viewPager.setAdapter(viewPagerAdapter);

        ListViewAdapter listViewAdapter = new ListViewAdapter(this, listResult);
        // We use this which references the current class 'ListViewActivity' so the activity
        // is the context. The context contains less information than the activity.
        listView.addHeaderView(listViewHeader);

        TextView tv = new TextView(this);
        tv.setText("We have no more content.");
        tv.setTextSize(28);
        tv.setGravity(Gravity.CENTER);
        listView.addFooterView(tv);
        // This is a second way to implement a view.

        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(this);
    }
    // This method must have an override
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"ListView as clicked at position: "+ position, Toast.LENGTH_LONG).show();
        // 'this' refers to the list activity which contains the list_item.xml
        // Originally, the first object of the list was at position 0, in the ListViewAdapter,
        // Now if you add a header, then it becomes the first element giving it position 0. Then the
        // item list will display its first element as 1 now.
        Log.d("testListViewActivity",String.valueOf(position));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("message","ListViewActivity");
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}