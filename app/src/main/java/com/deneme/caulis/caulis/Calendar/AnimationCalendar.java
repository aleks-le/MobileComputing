package com.deneme.caulis.caulis.Calendar;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;

import com.deneme.caulis.caulis.classes.CaulisCalendar;

class AnimationCalendar {

    private static final int HEIGHT_ANIM_DURATION_MILLIS = 650;
    private static final int INDICATOR_ANIM_DURATION_MILLIS = 600;
    private boolean isAnimating = false;
    private CaulisCalendar calendarContainer;
    private CalendarView calendarView;
    private CalendarView.CalendarAnimationListener calendarAnimationListener;

    AnimationCalendar(CaulisCalendar calendarContainer, CalendarView calendarView) {
        this.calendarContainer = calendarContainer;
        this.calendarView = calendarView;
    }

    void setCalendarAnimationListener(CalendarView.CalendarAnimationListener calendarAnimationListener){
        this.calendarAnimationListener = calendarAnimationListener;
    }

    void openCalendar() {
        if (isAnimating) {
            return;
        }
        isAnimating = true;
        Animation heightAnim = getCollapsingAnimation(true);
        heightAnim.setDuration(HEIGHT_ANIM_DURATION_MILLIS);
        heightAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        calendarContainer.setAnimationStatus(calendarContainer.EXPAND_COLLAPSE_CALENDAR);
        setUpAnimationLisForOpen(heightAnim);
        calendarView.getLayoutParams().height = 0;
        calendarView.requestLayout();
        calendarView.startAnimation(heightAnim);
    }

    void closeCalendar() {
        if (isAnimating) {
            return;
        }
        isAnimating = true;
        Animation heightAnim = getCollapsingAnimation(false);
        heightAnim.setDuration(HEIGHT_ANIM_DURATION_MILLIS);
        heightAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        setUpAnimationLisForClose(heightAnim);
        calendarContainer.setAnimationStatus(calendarContainer.EXPAND_COLLAPSE_CALENDAR);
        calendarView.getLayoutParams().height = calendarView.getHeight();
        calendarView.requestLayout();
        calendarView.startAnimation(heightAnim);
    }

    void openCalendarWithAnimation() {
        if (isAnimating) {
            return;
        }
        isAnimating = true;
        final Animator indicatorAnim = getIndicatorAnimator(1f, calendarContainer.getDayIndicatorRadius());
        final Animation heightAnim = getExposeCollapsingAnimation(true);
        calendarView.getLayoutParams().height = 0;
        calendarView.requestLayout();
        setUpAnimationLisForExposeOpen(indicatorAnim, heightAnim);
        calendarView.startAnimation(heightAnim);
    }

    void closeCalendarWithAnimation() {
        if (isAnimating) {
            return;
        }
        isAnimating = true;
        final Animator indicatorAnim = getIndicatorAnimator(calendarContainer.getDayIndicatorRadius(), 1f);
        final Animation heightAnim = getExposeCollapsingAnimation(false);
        calendarView.getLayoutParams().height = calendarView.getHeight();
        calendarView.requestLayout();
        setUpAnimationLisForExposeClose(indicatorAnim, heightAnim);
        calendarView.startAnimation(heightAnim);
    }

    private void setUpAnimationLisForExposeOpen(final Animator indicatorAnim, Animation heightAnim) {
        heightAnim.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                calendarContainer.setAnimationStatus(calendarContainer.EXPOSE_CALENDAR_ANIMATION);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                indicatorAnim.start();
            }
        });
        indicatorAnim.addListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                calendarContainer.setAnimationStatus(calendarContainer.ANIMATE_INDICATORS);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                calendarContainer.setAnimationStatus(calendarContainer.IDLE);
                onOpen();
                isAnimating = false;
            }
        });
    }

    private void setUpAnimationLisForExposeClose(final Animator indicatorAnim, Animation heightAnim) {
        heightAnim.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                calendarContainer.setAnimationStatus(calendarContainer.EXPOSE_CALENDAR_ANIMATION);
                indicatorAnim.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                calendarContainer.setAnimationStatus(calendarContainer.IDLE);
                onClose();
                isAnimating = false;
            }
        });
        indicatorAnim.addListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                calendarContainer.setAnimationStatus(calendarContainer.ANIMATE_INDICATORS);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });
    }

    @NonNull
    private Animation getExposeCollapsingAnimation(final boolean isCollapsing) {
        Animation heightAnim = getCollapsingAnimation(isCollapsing);
        heightAnim.setDuration(HEIGHT_ANIM_DURATION_MILLIS);
        heightAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        return heightAnim;
    }

    @NonNull
    private Animation getCollapsingAnimation(boolean isCollapsing) {
        return new CollapsingAnimation(calendarView, calendarContainer, calendarContainer.getTargetHeight(), getTargetGrowRadius(), isCollapsing);
    }

    @NonNull
    private Animator getIndicatorAnimator(float from, float to) {
        ValueAnimator animIndicator = ValueAnimator.ofFloat(from, to);
        animIndicator.setDuration(INDICATOR_ANIM_DURATION_MILLIS);
        animIndicator.setInterpolator(new OvershootInterpolator());
        animIndicator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                calendarContainer.setGrowFactorIndicator((Float) animation.getAnimatedValue());
                calendarView.invalidate();
            }
        });
        return animIndicator;
    }

    private int getTargetGrowRadius() {
        int heightSq = calendarContainer.getTargetHeight() * calendarContainer.getTargetHeight();
        int widthSq = calendarContainer.getWidth() * calendarContainer.getWidth();
        return (int) (0.5 * Math.sqrt(heightSq + widthSq));
    }

    private void onOpen() {
        if (calendarAnimationListener != null) {
            calendarAnimationListener.onOpened();
        }
    }

    private void onClose() {
        if (calendarAnimationListener != null) {
            calendarAnimationListener.onClosed();
        }
    }

    private void setUpAnimationLisForOpen(Animation openAnimation) {
        openAnimation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                super.onAnimationEnd(animation);
                onOpen();
                isAnimating = false;
            }
        });
    }

    private void setUpAnimationLisForClose(Animation openAnimation) {
        openAnimation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                super.onAnimationEnd(animation);
                onClose();
                isAnimating = false;
            }
        });
    }

    public boolean isAnimating() {
        return isAnimating;
    }
}
