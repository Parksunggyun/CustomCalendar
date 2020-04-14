package thomas.park.customcalendar;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class CalendarPagerAdapter extends FragmentStateAdapter {

    private final String TAG = CalendarPagerAdapter.class.getSimpleName();

    public static final int FRAGMENT_COUNT = 5;

    public List<CalendarFragment> fragments;

    private RecyclerView recyclerView;


    public CalendarPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<CalendarFragment> fragments) {
        super(fragmentActivity);
        this.fragments = fragments;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addFragment(CalendarFragment fragment, int way, ViewPager2 calendarViewPager) {
        if (way == -1) {
            fragments.add(0, fragment);
            Log.e(TAG, "fragments.size() = " + fragments.size());
            for (int i = 0; i < fragments.size(); i++) {
                Log.e(TAG, "fragments.currentMonth = " + fragments.get(i).currentMonth);
            }
        } else if (way == 1) {
            fragments.add(fragment);
        }
        recyclerView.post(() -> {
            recyclerView.getAdapter().notifyDataSetChanged();
            if(way == -1) {
                calendarViewPager.setCurrentItem(1);
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

}
