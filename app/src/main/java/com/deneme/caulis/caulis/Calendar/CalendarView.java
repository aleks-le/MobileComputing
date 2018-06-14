package com.deneme.caulis.caulis.Calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.deneme.caulis.caulis.classes.CaulisCalendar;
import com.deneme.caulis.caulis.classes.CaulisEvent;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarView extends View {

    public static final int FILL_LARGE_INDICATOR = 1;
    public static final int NO_FILL_LARGE_INDICATOR = 2;
    public static final int SMALL_INDICATOR = 3;

    private final AnimationCalendar animationCalendar;
    private CaulisCalendar calendarContainer;
    private GestureDetectorCompat gestureDetector;
    private boolean horizontalScrollEnabled = true;

    public interface CalendarViewListener {
        public void onDayClick(Date dateClicked);
        public void onMonthScroll(Date firstDayOfNewMonth);
    }

    public interface CalendarAnimationListener {
        public void onOpened();
        public void onClosed();
    }

    private final GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            calendarContainer.onSingleTapUp(e);
            invalidate();
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if(horizontalScrollEnabled) {
                if (Math.abs(distanceX) > 0) {
                    getParent().requestDisallowInterceptTouchEvent(true);

                    calendarContainer.onScroll(e1, e2, distanceX, distanceY);
                    invalidate();
                    return true;
                }
            }

            return false;
        }
    };

    public CalendarView(Context context) {this(context, null);}

    public CalendarView(Context context, AttributeSet attrs) {this(context, attrs, 0);}

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        calendarContainer = new CaulisCalendar(attrs, getContext(), new CaulisEvent(Calendar.getInstance()),Locale.getDefault(), TimeZone.getDefault());
        animationCalendar = new AnimationCalendar(calendarContainer, this);
    }

    public void setAnimationListener(CalendarAnimationListener calendarAnimationListener){
        animationCalendar.setCalendarAnimationListener(calendarAnimationListener);
    }

    public void setLocale(TimeZone timeZone, Locale locale){
        calendarContainer.setLocale(timeZone, locale);
        invalidate();
    }

    /*
    Compact calendar will use the locale to determine the abbreviation to use as the day column names.
    The default is to use the default locale and to abbreviate the day names to one character.
    Setting this to true will displace the short weekday string provided by java.
     */
    public void setUseThreeLetterAbbreviation(boolean useThreeLetterAbbreviation){
        calendarContainer.setUseWeekDayAbbreviation(useThreeLetterAbbreviation);
        invalidate();
    }

    public void setCalendarBackgroundColor(final int calenderBackgroundColor) {
        calendarContainer.setCalendarBackgroundColor(calenderBackgroundColor);
        invalidate();
    }

    /*
    Sets the name for each day of the week. No attempt is made to adjust width or text size based on the length of each day name.
    Works best with 3-4 characters for each day.
     */
    public void setDayColumnNames(String[] dayColumnNames){
        calendarContainer.setDayColumnNames(dayColumnNames);
    }

    public void setFirstDayOfWeek(int dayOfWeek){
        calendarContainer.setFirstDayOfWeek(dayOfWeek);
        invalidate();
    }

    public void setCurrentSelectedDayBackgroundColor(int currentSelectedDayBackgroundColor) {
        calendarContainer.setCurrentSelectedDayBackgroundColor(currentSelectedDayBackgroundColor);
        invalidate();
    }

    public void setCurrentDayBackgroundColor(int currentDayBackgroundColor) {
        calendarContainer.setCurrentDayBackgroundColor(currentDayBackgroundColor);
        invalidate();
    }

    public int getHeightPerDay(){
        return calendarContainer.getHeightPerDay();
    }

    public void setListener(CalendarViewListener listener){
        calendarContainer.setListener(listener);
    }

    public Date getFirstDayOfCurrentMonth(){
        return calendarContainer.getFirstDayOfCurrentMonth();
    }

    public void shouldDrawIndicatorsBelowSelectedDays(boolean shouldDrawIndicatorsBelowSelectedDays){
        calendarContainer.shouldDrawIndicatorsBelowSelectedDays(shouldDrawIndicatorsBelowSelectedDays);
    }

    public void setCurrentDate(Date dateTimeMonth){
        calendarContainer.setCurrentDate(dateTimeMonth);
        invalidate();
    }

    public int getWeekNumberForCurrentMonth(){
        return calendarContainer.getWeekNumberForCurrentMonth();
    }

    public void setShouldDrawDaysHeader(boolean shouldDrawDaysHeader){
        calendarContainer.setShouldDrawDaysHeader(shouldDrawDaysHeader);
    }

    public void setCurrentSelectedDayTextColor(int currentSelectedDayTextColor) {
        calendarContainer.setCurrentSelectedDayTextColor(currentSelectedDayTextColor);
    }

    public void setCurrentDayTextColor(int currentDayTextColor) {
        calendarContainer.setCurrentDayTextColor(currentDayTextColor);
    }

    public void addEvent(Event event){
        addEvent(event, true);
    }

    public void addEvent(Event event, boolean shouldInvalidate){
        calendarContainer.addEvent(event);
        if(shouldInvalidate){
            invalidate();
        }
    }
    public void addEvents(List<Event> events){
        calendarContainer.addEvents(events);
        invalidate();
    }

    public List<Event> getEvents(Date date){return calendarContainer.getCalendarEventsFor(date.getTime());}

    public List<Event> getEvents(long epochMillis){return calendarContainer.getCalendarEventsFor(epochMillis);}

    public List<Event> getEventsForMonth(long epochMillis){return calendarContainer.getCalendarEventsForMonth(epochMillis);}

    public List<Event> getEventsForMonth(Date date){return calendarContainer.getCalendarEventsForMonth(date.getTime());}

    public void shouldSelectFirstDayOfMonthOnScroll(boolean shouldSelectFirstDayOfMonthOnScroll){
        calendarContainer.setShouldSelectFirstDayOfMonthOnScroll(shouldSelectFirstDayOfMonthOnScroll);
    }

    public void setCurrentSelectedDayIndicatorStyle(final int currentSelectedDayIndicatorStyle){
        calendarContainer.setCurrentSelectedDayIndicatorStyle(currentSelectedDayIndicatorStyle);
        invalidate();
    }

    public void setCurrentDayIndicatorStyle(final int currentDayIndicatorStyle){
        calendarContainer.setCurrentDayIndicatorStyle(currentDayIndicatorStyle);
        invalidate();
    }

    public void setEventIndicatorStyle(final int eventIndicatorStyle){
        calendarContainer.setEventIndicatorStyle(eventIndicatorStyle);
        invalidate();
    }

    private void checkTargetHeight() {
        if (calendarContainer.getTargetHeight() <= 0) {
            throw new IllegalStateException("Target height must be set in xml properties in order to expand/collapse CompactCalendar.");
        }
    }

    public void displayOtherMonthDays(boolean displayOtherMonthDays){
        calendarContainer.setDisplayOtherMonthDays(displayOtherMonthDays);
        invalidate();
    }

    public void setTargetHeight(int targetHeight){
        calendarContainer.setTargetHeight(targetHeight);
        checkTargetHeight();
    }

    public void showCalendar(){
        checkTargetHeight();
        animationCalendar.openCalendar();
    }

    public void hideCalendar(){
        checkTargetHeight();
        animationCalendar.closeCalendar();
    }

    public void showCalendarWithAnimation(){
        checkTargetHeight();
        animationCalendar.openCalendarWithAnimation();
    }

    public void hideCalendarWithAnimation(){
        checkTargetHeight();
        animationCalendar.closeCalendarWithAnimation();
    }

    public void showNextMonth(){
        calendarContainer.showNextMonth();
        invalidate();
    }

    public void showPreviousMonth(){
        calendarContainer.showPreviousMonth();
        invalidate();
    }

    public boolean isAnimating(){
        return animationCalendar.isAnimating();
    }

    @Override
    protected void onMeasure(int parentWidth, int parentHeight) {
        super.onMeasure(parentWidth, parentHeight);
        int width = MeasureSpec.getSize(parentWidth);
        int height = MeasureSpec.getSize(parentHeight);
        if(width > 0 && height > 0) {
            calendarContainer.onMeasure(width, height, getPaddingRight(), getPaddingLeft());
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        calendarContainer.onDraw(canvas);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(calendarContainer.computeScroll()){
            invalidate();
        }
    }

    public void shouldScrollMonth(boolean enableHorizontalScroll){
        this.horizontalScrollEnabled = enableHorizontalScroll;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (horizontalScrollEnabled) {
            calendarContainer.onTouch(event);
            invalidate();
        }

        // on touch action finished (CANCEL or UP), we re-allow the parent container to intercept touch events (scroll inside ViewPager + RecyclerView issue #82)
        if((event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP) && horizontalScrollEnabled) {
            getParent().requestDisallowInterceptTouchEvent(false);
        }

        // always allow gestureDetector to detect onSingleTap and scroll events
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        if (this.getVisibility() == View.GONE) {
            return false;
        }
        // Prevents ViewPager from scrolling horizontally by announcing that (issue #82)
        return this.horizontalScrollEnabled;
    }

}

