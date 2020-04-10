package thomas.park.customcalendar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarFragment extends Fragment {

    public static CalendarFragment getNewInstance() {
        return new CalendarFragment();
    }

    private RecyclerView calendarView;
    private CalendarAdapter calendarAdapter;



    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = view.findViewById(R.id.calendarView);

        calendarView.setLayoutManager(new GridLayoutManager(getContext(), 7));

        calendarAdapter = new CalendarAdapter();
        calendarView.setAdapter(calendarAdapter);
        ArrayList<CalendarItem> items = new ArrayList<>();
        for(int i = 0; i< 31; i++) {
            items.add(new CalendarItem(String.valueOf(i+1), (i % 5 == 0), false));
        }
        calendarAdapter.update(items);
        return view;
    }
}
