package ponkberry.hoandemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Ponk on 2/1/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentList;
    private static final String[] tabHeaders = new String[] { "History", "Content", "Login" };
    private static final String[] tabTyping = new String[] { "Cen", "CenCrop", "CenIn", "FitCen", "FitEnd", "FitStart", "FitXY", "Matrix" };

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setContent(ArrayList<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabHeaders[position % tabHeaders.length];
//        return tabTyping[position % tabTyping.length];
//        return fragmentList.get(position).getClass().getName();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        // This does not destroy from the memory.
    }
}
