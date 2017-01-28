package ponkberry.hoandemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import ponkberry.hoandemo.adapter.ListViewAdapter;


public class ListViewActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initialView();

    }

    private void initialView() {
        listView = (ListView) findViewById(R.id.list_view);
        ListViewAdapter listViewAdapter = new ListViewAdapter(this);
        // We use this which references the current class 'ListViewActivity' so the activity
        // is the context. The context contains less information than the activity.
        listView.setAdapter(listViewAdapter);
    }
}
