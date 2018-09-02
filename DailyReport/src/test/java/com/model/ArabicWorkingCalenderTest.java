package com.model;

import static org.junit.Assert.assertEquals;
import com.model.ArabicWorkingCalender;

import java.time.LocalDate;

import org.junit.Test;

/**
 * @author bhard
 * @desc arabic calender
 */
public class ArabicWorkingCalenderTest {	
	
	@Test
	public void firstWorkingday() {
		final LocalDate testDate = LocalDate.of(2018, 8, 31); //Friday
		final LocalDate predictDate = LocalDate.of(2018, 9, 2);
		
		LocalDate newDate = new ArabicWorkingCalender().firstWorkingday(testDate);
		
		assertEquals(newDate, predictDate);
		
	}
	

}
