/**
 * 
 */
package com.brap.util;

/**
 * @author prajwbhat
 *
 */
public enum DayOfWeekEnum {

	MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

	private final Integer dayOfWeekInteger;

	DayOfWeekEnum(Integer dayOfWeekInteger) {
		this.dayOfWeekInteger = dayOfWeekInteger;
	}

	public Integer getDayOfWeekInteger() {
		return dayOfWeekInteger;
	}
}
