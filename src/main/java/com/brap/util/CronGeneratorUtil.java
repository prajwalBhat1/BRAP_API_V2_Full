package com.brap.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

/**
 * Class converts the given UI inputs to cron expressions form
 * 
 * @author prajwbhat
 *
 */
public class CronGeneratorUtil {

	private static final String COLON = ":";

	private static final String DOUBLE_COLON = "::";

	private static final String COMMA = ",";

	private static final String SEMI_COLON = ";";
	
	private static String cronFormat = "0 mm hh ? * dow";

	/**
	 * Input would be something like this:
	 * 
	 * === Mon;Tue;Wed::13:30;14:30,Thu;Fri::21:45;22:00 ===
	 * 
	 * @param cronInputString
	 * @return
	 */
	public static List<String> generateCronExpressionFromInputs(String cronInputString) {
		// split whole expressions
		List<String> cronExpressions = null;
		if (cronInputString.contains(COMMA)) {
			cronExpressions = CollectionUtils.arrayToList(cronInputString.split(COMMA));
		} else {
			cronExpressions = Collections.singletonList(cronInputString);
		}
		Map<String, String> scheduleMap = fetchScheduleMap(cronExpressions);

		List<String> scheduleList = new ArrayList<>();
		for (Map.Entry<String, String> entry : scheduleMap.entrySet()) {
			// "0 mm hh ? * dow"
			String[] timeArr = entry.getKey().split(COLON);
			String scheduleString = new String(cronFormat).replace("mm", timeArr[1]).replace("hh", timeArr[0])
					.replace("dow", entry.getValue()).trim();
			scheduleList.add(scheduleString);
		}
		return scheduleList;
	}

	/**
	 * @param cronExpressions
	 * @return
	 */
	private static Map<String, String> fetchScheduleMap(List<String> cronExpressions) {
		Map<String, String> scheduleMap = new HashMap<>();
		cronExpressions.stream().forEach(schedule -> {

			List<String> scheduleList = new LinkedList<>(CollectionUtils.arrayToList(schedule.split(DOUBLE_COLON)));
			if (scheduleList.get(0).contains(SEMI_COLON)) {
				String daysString = scheduleList.get(0);
				scheduleList.remove(0);
				scheduleList.add(0, daysString.replace(SEMI_COLON, COMMA));
			}
			if (scheduleList.get(1).contains(SEMI_COLON)) {
				List<String> timeList = new ArrayList<>(
						CollectionUtils.arrayToList(scheduleList.get(1).split(SEMI_COLON)));

				timeList.stream().forEach(time -> {
					scheduleMap.put(time, scheduleList.get(0).toUpperCase());

				});
			} else {
				scheduleMap.put(scheduleList.get(1), scheduleList.get(0).toUpperCase());
			}

		});
		return scheduleMap;
	}

	public static void main(String[] args) {
		CronGeneratorUtil.generateCronExpressionFromInputs("Mon;Tue;Wed::13:30;14:30,Thu;Fri::21:45;22:00");
	}
}
