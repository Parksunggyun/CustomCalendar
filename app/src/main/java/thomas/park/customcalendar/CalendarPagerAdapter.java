package thomas.park.customcalendar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.HashMap;

public class CalendarPagerAdapter extends FragmentStateAdapter {

    public CalendarPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return CalendarFragment.getNewInstance();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
