package ponkberry.hoandemo.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ponkberry.hoandemo.R;
import ponkberry.hoandemo.util.UtilDensity;

import static ponkberry.hoandemo.R.id.list_view_tv1;

/**
 * Created by Ponk on 1/25/2017.
 */

public class ListViewAdapter extends BaseAdapter {

    // This is a normal java. In order to use the findById function, we must use a layout inflater

    private Context mContext;
    private final LayoutInflater mInflater;
    private final ArrayList<String> listResult;

    public ListViewAdapter(Context context, ArrayList<String> listResult) {
        mContext = context;
        this.listResult = listResult;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listResult.size();
    }
    // This is the number of items the list can view
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
//        TextView view = new TextView(mContext);
//        view.setText(String.valueOf(position));
//        return view;
        // the convertView is the old view and the system will retain the old view stored as
        // convertView.
        if(convertView == null) { // Null means first time
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.textView1 = (TextView) convertView.findViewById(list_view_tv1);
            holder.textView2 = (TextView) convertView.findViewById(R.id.list_view_tv2);
            holder.textView3 = (TextView) convertView.findViewById(R.id.list_view_tv3);

            convertView.setTag(holder);
            // setTag has a private space inside whose parameter is an object.
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(String.valueOf(position));
        holder.textView3.setText(String.valueOf(position));
        holder.textView2.setText(listResult.get(position));
        if (position%2 == 0) {
            holder.textView1.setVisibility(View.VISIBLE);
            holder.textView3.setVisibility(View.INVISIBLE);
            // Don't use ViewHolder for layout parameter
            holder.llp.setMargins(UtilDensity.dip2px(mContext,50),0,0,0);
            //holder.lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            holder.llp.gravity = Gravity.LEFT;
            holder.textView2.setBackgroundResource(R.drawable.chatfrom_bg_focused);
            holder.textView2.setLayoutParams(holder.llp);
        } else {
            holder.textView1.setVisibility(View.INVISIBLE);
            holder.textView3.setVisibility(View.VISIBLE);
            holder.llp.gravity = Gravity.RIGHT;
            holder.llp.setMargins(0,0, UtilDensity.dip2px(mContext,50),0);
            //holder.lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            // Java must use pixels, not dots so you have to convert all dots to pixels
            holder.textView2.setBackgroundResource(R.drawable.chatto_bg_focused);
            holder.textView2.setLayoutParams(holder.llp);
        }

//        View rowView = mInflater.inflate(R.layout.list_item, parent, false);
//        TextView textView = (TextView)rowView.findViewById(R.id.list_view_tv);
//        textView.setText(String.valueOf(position));

//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        //return rowView;
        return convertView;
    }
    // For each item, you have to build a view. So for each position, it will call this method.

}

    // Everytime the program access the getView method, the TextView will always create a new
    // object. Every time, you'll be occupying memory space to generate new textview.

class ViewHolder {
    TextView textView1;
    TextView textView2;
    TextView textView3;
    //RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
}
