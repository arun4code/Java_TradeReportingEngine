package com.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

/**
 * @author bhard
 * @desc arabic calender
 */
public class WorkingCalenderTest {	
	
	@Test
	public void firstWorkingday() {
		final LocalDate testDate = LocalDate.of(2018, 9, 1); //sat
		final LocalDate predictDate = LocalDate.of(2018, 9, 3);
		
		LocalDate newDate = new WorkingCalender().firstWorkingday(testDate);
		
		assertEquals(newDate, predictDate);
		
	}
	

}
