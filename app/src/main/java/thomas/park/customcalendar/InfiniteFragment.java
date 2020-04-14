package thomas.park.customcalendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InfiniteFragment extends Fragment {


    public static Fragment InitiateFragment(
            int firstDayNumOfMonth, // 해당 달의 1일이 어떤 요일인지 일 = 1, 토 = 7
            int lastDayNumOfMonth, // 해당 달의 마지막이 어떤 요일인지 일 = 1, 토 = 7
            int lastDayOfMonth, // 해당 달의 마지막 날, 2월 = 28 or 29, 1월 = 31, 4월 = 30
            int lastDayOfPrevMonth, // 이전 달의 마지막 날
            int currentYear, // 현재 표시되는 달력의 연도
            int currentMonth // 현재 표시되는 달력의 월
    ) {
        InfiniteFragment fragment = new InfiniteFragment();
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
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
