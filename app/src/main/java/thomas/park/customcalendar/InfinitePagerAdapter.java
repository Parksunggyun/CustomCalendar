package thomas.park.customcalendar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class InfinitePagerAdapter extends FragmentStateAdapter {

    private final int FRAGMENT_COUNT = 5;

    public InfinitePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return null;
    }

    @Override
    public int getItemCount() {
        return FRAGMENT_COUNT;
    }

}
