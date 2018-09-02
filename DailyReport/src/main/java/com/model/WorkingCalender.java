package com.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bhard
 * @ desc: default calender
 */
public class WorkingCalender implements IWorkingDays {

	private final Map<DayOfWeek, Boolean> workingDayMap = new HashMap<DayOfWeek, Boolean>();
	
	public WorkingCalender() {        
        this.workingDayMap.put(DayOfWeek.MONDAY, true);
        this.workingDayMap.put(DayOfWeek.TUESDAY, true);
        this.workingDayMap.put(DayOfWeek.WEDNESDAY, true);
        this.workingDayMap.put(DayOfWeek.THURSDAY, true);
        this.workingDayMap.put(DayOfWeek.FRIDAY, true);
        this.workingDayMap.put(DayOfWeek.SATURDAY, false);
        this.workingDayMap.put(DayOfWeek.SUNDAY, false);
	}
	
	@Override
	public LocalDate firstWorkingday(final LocalDate date) {
		final DayOfWeek day = date.getDayOfWeek();

        if (workingDayMap.get(day)) {
            return date;
        } else {
            return firstWorkingday(date.plusDays(1));
        }
	}
	

}
