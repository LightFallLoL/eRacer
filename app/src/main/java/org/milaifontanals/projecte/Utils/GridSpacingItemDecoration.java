package org.milaifontanals.projecte.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private final int space;
    private final Paint paint;

    public GridSpacingItemDecoration(Context context, int color, int space) {
        this.space = space;
        this.paint = new Paint();
        this.paint.setColor(color);
        this.paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            // Dibujar el margen derecho
            int left = child.getRight() + params.rightMargin;
            int right = left + space;
            int top = child.getTop() - params.topMargin;
            int bottom = child.getBottom() + params.bottomMargin + space;
            canvas.drawRect(left, top, right, bottom, paint);

            // Dibujar el margen inferior
            left = child.getLeft() - params.leftMargin;
            right = child.getRight() + params.rightMargin + space;
            top = child.getBottom() + params.bottomMargin;
            bottom = top + space;
            canvas.drawRect(left, top, right, bottom, paint);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.set(0, 0, space, space);
    }
}
