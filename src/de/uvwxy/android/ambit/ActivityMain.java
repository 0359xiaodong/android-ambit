package de.uvwxy.android.ambit;

import android.support.v4.app.Fragment;
import de.uvwxy.android.ambit.fragments.FragmentMain;
import de.uvwxy.cardpager.ActivityCardPager;
import de.uvwxy.melogsta.Log;

public class ActivityMain extends ActivityCardPager {

    @Override
    public Fragment getFragment(int position) {
        Log.setContext(getApplicationContext());
        Log.setAllLogToHistory(true);
        Log.setLogAnything(true);
        Log.setAllLogToLogCat(true);
        return new FragmentMain();
    }

    @Override
    public CharSequence getFragmentTitle(int position) {
        // TODO Auto-generated method stub
        return "Test";
    }

    @Override
    public int getFragmentCount() {
        // TODO Auto-generated method stub
        return 1;
    }

}
