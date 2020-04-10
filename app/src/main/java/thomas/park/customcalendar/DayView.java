package thomas.park.customcalendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class DayView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG); //
    Paint mPaintTextWhite = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaintBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaintBackgroundToday = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaintBackgroundEvent = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int dayOfWeek = -1;
    private boolean isStaticText = false;
    private long millis;
    private Rect rect;
    private boolean isTouchMode;
    private int dp12;
    private int dp16;
    private boolean hasEvent = false;
    private int[] mColorEvents;
    private final float RADIUS = 100f;

    public DayView(Context context) {
        super(context);
        init();
    }

    public DayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        dp12 = dpToPx(getContext(), 12);
        dp16 = dpToPx(getContext(), 16);

        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(dp12);
        mPaint.setTextAlign(Paint.Align.CENTER);

        mPaintTextWhite.setColor(Color.WHITE);
        mPaintTextWhite.setTextSize(dp12);
        mPaintTextWhite.setTextAlign(Paint.Align.CENTER);

        mPaintBackground.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        mPaintBackgroundToday.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        mPaintBackgroundEvent.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        setClickable(true);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        isTouchMode = true;
                        break;

                    case MotionEvent.ACTION_UP:
                        if(isTouchMode) {

                        }
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        isTouchMode = false;
                        break;
                }


                return false;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private int dpToPx(Context context, int dp) {

        return (int) TypedValue.applyDimension
                (TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

}
