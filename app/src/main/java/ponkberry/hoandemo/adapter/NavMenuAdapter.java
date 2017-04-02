package ponkberry.hoandemo.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ponkberry.hoandemo.R;

/**
 * Created by Ponk on 3/20/2017.
 */

public class NavMenuAdapter extends BaseAdapter {


    private Context mContext;
    private final LayoutInflater mInflater;
    private final ArrayList<String> listResult;
    private TextView tv;
    private Typeface myriadPro;

    public NavMenuAdapter(Context context, ArrayList<String> listResult) {
        mContext = context;
        this.listResult = listResult;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myriadPro = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/Myriad Pro Regular.ttf");
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
        NavViewHolder holder;
        if(convertView == null) { // Null means first time
            convertView = mInflater.inflate(R.layout.nav_list, parent, false);
            holder = new NavViewHolder();
            holder.className = (TextView) convertView.findViewById(R.id.nav_list_text);

            convertView.setTag(holder);
            // setTag has a private space inside whose parameter is an object.
        } else {
            holder = (NavViewHolder) convertView.getTag();
        }
        holder.className.setText(listResult.get(position));
        holder.className.setTypeface(myriadPro);
        return convertView;
    }
}

class NavViewHolder {
    TextView className;
    //RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    //LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
}
