package thomas.park.customcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class DayItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private Drawable divider;


    public DayItemDecoration(@NotNull Context context) {
        final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);

        divider = styledAttributes.getDrawable(0);
        styledAttributes.recycle();
    }

    public DayItemDecoration(Context context, int resId) {
        divider = ContextCompat.getDrawable(context, resId);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {


        int childPosition = parent.getChildAdapterPosition(view);
        if(childPosition % 7 != 6) {
            outRect.right = 2;
        }
        if (childPosition / 7 != 5) {
            outRect.bottom = 2;
        }

    }

}
