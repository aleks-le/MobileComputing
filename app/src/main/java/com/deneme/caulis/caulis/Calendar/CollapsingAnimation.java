package com.deneme.caulis.caulis.Calendar;

import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.deneme.caulis.caulis.classes.CaulisCalendar;

public class CollapsingAnimation extends Animation {

    private final int targetHeight;
    private final CalendarView view;
    private int targetGrowRadius;
    private final boolean down;
    private CaulisCalendar calendarContainer;

    public CollapsingAnimation(CalendarView view, CaulisCalendar calendarContainer, int targetHeight, int targetGrowRadius, boolean down) {
        this.view = view;
        this.calendarContainer = calendarContainer;
        this.targetHeight = targetHeight;
        this.targetGrowRadius = targetGrowRadius;
        this.down = down;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float grow = 0;
        int newHeight;
        if (down) {
            newHeight = (int) (targetHeight * interpolatedTime);
            grow = (interpolatedTime * (targetGrowRadius * 2));
        } else {
            float progress = 1 - interpolatedTime;
            newHeight = (int) (targetHeight * progress);
            grow = (progress * (targetGrowRadius * 2));
        }
        calendarContainer.setGrowProgress(grow);
        view.getLayoutParams().height = newHeight;
        view.requestLayout();

    }

    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }

}
