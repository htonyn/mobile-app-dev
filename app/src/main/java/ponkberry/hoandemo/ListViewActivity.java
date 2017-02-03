package ponkberry.hoandemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import ponkberry.hoandemo.adapter.ListViewAdapter;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // In order to use on click for the list view, you need to implement the listener for the
    // on click

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
        listView.setOnItemClickListener(this);
    }
    // This method must have an override
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"ListView as clicked at position: "+ position, Toast.LENGTH_LONG).show();
        // 'this' refers to the list activity which contains the list_item.xml
    }
}