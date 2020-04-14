package thomas.park.customcalendar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
    private static final String TAG = CalendarFragment.class.getSimpleName();

    private int firstDayNumOfMonth, lastDayNumOfMonth, lastDayOfMonth, lastDayOfPrevMonth;
    int currentYear, currentMonth;
    private MainActivity activity;

    private RecyclerView calendarView;
    private CalendarAdapter calendarAdapter;

    private CalendarFragment(MainActivity activity) {
        this.activity = activity;
    }

    public static CalendarFragment InitiateFragment(
            MainActivity activity,
            int firstDayNumOfMonth, // 해당 달의 1일이 어떤 요일인지 일 = 1, 토 = 7
            int lastDayOfMonth, // 해당 달의 마지막 날, 2월 = 28 or 29, 1월 = 31, 4월 = 30
            int lastDayNumOfMonth, // 해당 달의 마지막이 어떤 요일인지 일 = 1, 토 = 7
            int lastDayOfPrevMonth, // 이전 달의 마지막 날
            int currentYear, // 현재 표시되는 달력의 연도
            int currentMonth // 현재 표시되는 달력의 월
    ) {
        CalendarFragment fragment = new CalendarFragment(activity);
        Bundle args = new Bundle();
        args.putInt("firstDayNumOfMonth", firstDayNumOfMonth);
        args.putInt("lastDayNumOfMonth", lastDayNumOfMonth);
        args.putInt("lastDayOfMonth", lastDayOfMonth);
        args.putInt("lastDayOfPrevMonth", lastDayOfPrevMonth);
        args.putInt("currentYear", currentYear);
        args.putInt("currentMonth", currentMonth);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle args = getArguments();
            firstDayNumOfMonth = args.getInt("firstDayNumOfMonth");
            lastDayOfMonth = args.getInt("lastDayOfMonth");
            lastDayNumOfMonth = args.getInt("lastDayNumOfMonth");
            lastDayOfPrevMonth = args.getInt("lastDayOfPrevMonth");
            currentYear = args.getInt("currentYear");
            currentMonth = args.getInt("currentMonth");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendarView.setLayoutManager(new GridLayoutManager(getContext(), 7));

        calendarAdapter = new CalendarAdapter();
        calendarView.setAdapter(calendarAdapter);
        DayItemDecoration decoration = new DayItemDecoration(activity, R.drawable.day_divider);
        calendarView.addItemDecoration(decoration);
        ArrayList<CalendarItem> items = new ArrayList<>();
        int addDays = 0;

        if((firstDayNumOfMonth - 1) + lastDayOfMonth + (7 - lastDayNumOfMonth) <= 35) {
            addDays = 7;
        }
        for (int i = -(firstDayNumOfMonth - 1); i < lastDayOfMonth + (7 - lastDayNumOfMonth) + addDays; i++) {
            boolean isThisMonth = (i + 1 >= 1) && (i + 1 <= lastDayOfMonth);
            int dayNum = i + 1;
            if(!isThisMonth) {
                if(i + 1 > lastDayOfMonth) {
                    dayNum = (i + 1) - lastDayOfMonth;
                } else if(i + 1 < 1) {
                    dayNum = lastDayOfPrevMonth + i + 1;
                }
            }
            items.add(new CalendarItem(String.valueOf(dayNum), (i % 5 == 0), false, isThisMonth, false));
        }
        calendarAdapter.update(items);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = view.findViewById(R.id.calendarView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTodayText();
    }

    void setTodayText() {
        activity.setToday(currentYear + " " + currentMonth + "월");
    }
}
