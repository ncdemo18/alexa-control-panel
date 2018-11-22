package com.netcracker.alexa.controlpanel.vaadin.blocks;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Calendar;
import java.util.Locale;

public class TimeBlock extends VerticalLayout {

    public TimeBlock() {

        Calendar now = Calendar.getInstance();

        String monthName = now.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        String dayName = getTimeWithNullPrefix(now.get(Calendar.DAY_OF_MONTH)) + getDayOfMonthSuffix(now.get(Calendar.HOUR_OF_DAY));
        String fullDate = monthName + " " + dayName + ", " + now.get(Calendar.YEAR);
        String weekName = now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);

        String hours = getTimeWithNullPrefix(now.get(Calendar.HOUR));
        String minutes = getTimeWithNullPrefix(now.get(Calendar.MINUTE));
        String fullTime = hours + ":"  + minutes + " " + getTimeSuffix(now.get(Calendar.HOUR_OF_DAY));

        Span weekDayLine = new Span(weekName);
        weekDayLine.addClassName("font-gray");

        VerticalLayout dateBlock = new VerticalLayout(new Span(fullDate), weekDayLine);
        VerticalLayout timeBlock = new VerticalLayout(new Span(fullTime));
        timeBlock.setClassName("horizontal-border");

        HorizontalLayout layout = new HorizontalLayout(timeBlock, dateBlock);
        layout.setWidth("100%");
        layout.addClassName("font-white");
        layout.getStyle().set("font-size", "50px");

        addClassName("keep-inner-div-in-border");
        add(layout);
    }
    private String getDayOfMonthSuffix(int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }

    private String getTimeWithNullPrefix(Integer time){
        String strTime = time.toString();
        return time < 10 ? "0" + strTime : strTime;
    }

    private String getTimeSuffix(int hours) {
        return hours > 12 ? "pm" : "am";
    }
}
