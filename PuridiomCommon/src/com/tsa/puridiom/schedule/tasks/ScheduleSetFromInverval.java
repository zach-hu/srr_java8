package com.tsa.puridiom.schedule.tasks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

/**
 * 
 * @author Alexander Angulo
 *
 */
public class ScheduleSetFromInverval  extends Task 
{

	@SuppressWarnings("unchecked")
	public Object executeTask (Object object) throws Exception
	{
		
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession");
		String organizationId = (String)incomingRequest.get("organizationId");
		
		String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (Utility.isEmpty(userDateFormat)) 
        {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }
		
		String icPoHeader = (String) incomingRequest.get("PoHeader_icPoHeader");
		String poHeadernterval = (String) incomingRequest.get("PoHeader_interval");
		String effectiveDateString = (String) incomingRequest.get("PoHeader_effectiveDate");
		String expirationDateString = (String) incomingRequest.get("PoHeader_expirationDate");
		Date effectiveDate = Dates.getSqlDate(effectiveDateString, userDateFormat);
		Date expirationDate = Dates.getSqlDate(expirationDateString, userDateFormat);
		
		if (expirationDate.after(effectiveDate)) {
			List<Date> intervals = generateInvervals(poHeadernterval, effectiveDate, expirationDate);
			Iterator<Date> iterator = intervals.iterator();
			
			while (iterator.hasNext()) 
			{
				Date interval = iterator.next();
				
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("dbsession", dbs);
				newIncomingRequest.put("organizationId", organizationId);
				newIncomingRequest.put("Schedule_scheduleType", "P");
				newIncomingRequest.put("Schedule_documentType", "POH");
				newIncomingRequest.put("Schedule_icHeader", icPoHeader);
				newIncomingRequest.put("Schedule_description", "Payment Schedule");
				newIncomingRequest.put("Schedule_scheduleDate", new java.sql.Date(interval.getTime()));
				
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
	            PuridiomProcess process = processLoader.loadProcess("schedule-add.xml");

	            process.executeProcess(newIncomingRequest);
	            this.setStatus(process.getStatus());
			}
		}
		
		return null;
	}

	/**
	 * 
	 * @param poHeadernterval
	 * @return
	 */
	private List<Date> generateInvervals(String poHeadernterval, Date effectiveDate, Date expirationDate) throws Exception
	{
		List<Date> dates = new ArrayList<Date>();
		
		int calendarField = 0;
		int amount = 0;
		if (poHeadernterval.equals("Weekly")) 
		{
			calendarField = Calendar.DAY_OF_YEAR; 
			amount = 7;
		}
		else if (poHeadernterval.equals("Bi-Weekly")) 
		{
			calendarField = Calendar.DAY_OF_YEAR;
			amount = 14;
		}
		else if (poHeadernterval.equals("Semi-Monthly")) 
		{
			//calendarField = Calendar.DAY_OF_YEAR;
			//amount = 15;
			
			int firstDay = 0;
			
			Date date01 = getDate(01);
			Date date16 = getDate(16);
			
			if (effectiveDate.compareTo(date01) == 0) {
				firstDay = 01;
			} else if (effectiveDate.compareTo(date01) > 0 
					&& effectiveDate.compareTo(date16) < 0) { 
				firstDay = 16;
			} else {
				firstDay = 31;
			}
			
			boolean obtainNewInterval = true;
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(effectiveDate);
			
			int day = 0;
			if (firstDay == 01) {
				calendar.set(Calendar.DAY_OF_MONTH, 01);
				day = 01;
			} else if (firstDay == 16) { 
				calendar.set(Calendar.DAY_OF_MONTH, 16);
				day = 16;
			} else if (firstDay == 31) {
				calendar.add(Calendar.MONTH, 1);
				calendar.set(Calendar.DAY_OF_MONTH, 01);
				day = 01;
			}
			
			int i = 0; // 1 is first day, 2 is second day
			while (obtainNewInterval) {
				if (i == 0) {
					i++;
				} else if (i == 1) {
					if (day == 01) {
						calendar.set(Calendar.DAY_OF_MONTH, 16);
						day = 16;
					} else if (day == 16) {
						calendar.add(Calendar.MONTH, 1);
						calendar.set(Calendar.DAY_OF_MONTH, 01);
						day = 01;
					}
				} 
				
				Date date = calendar.getTime();
				if (date.after(expirationDate)) {
					obtainNewInterval = false;
				} else {
					dates.add(date);
				}
			}
			
			return dates;
			
		}
		else if (poHeadernterval.equals("Monthly")) 
		{
			calendarField = Calendar.MONTH;
			amount = 1;
		} 
		else if (poHeadernterval.equals("Quarterly")) 
		{
			calendarField = Calendar.MONTH;
			amount = 3;
		}
		else  
		{
			throw new Exception("Inverval selected is not valid");
		}
		
		boolean obtainNewInterval = true;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(effectiveDate);
		
		while (obtainNewInterval) {
			calendar.add(calendarField, amount);
			Date date = calendar.getTime();
			if (date.after(expirationDate)) {
				obtainNewInterval = false;
			} else {
				dates.add(date);
			}
		}
		
		return dates;
	}
	
	/**
	 * 
	 * @param numberDay
	 * @return
	 */
	private Date getDate(int numberDay) {
		Calendar calendarDay = Calendar.getInstance();
		calendarDay.setTime(new Date());
		calendarDay.set(Calendar.HOUR_OF_DAY, 0);
		calendarDay.set(Calendar.MINUTE, 0);
		calendarDay.set(Calendar.SECOND, 0);
		calendarDay.set(Calendar.MILLISECOND, 0);
		calendarDay.set(Calendar.DAY_OF_MONTH, numberDay);
		Date date = calendarDay.getTime();
		return date;
	}
}
