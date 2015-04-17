package com.tsa.puridiom.schedule.tasks;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.tsa.puridiom.entity.Schedule;
import com.tsa.puridiom.entity.SchedulePK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class ScheduleSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain schedule */
			SchedulePK comp_id = new SchedulePK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			Schedule	originalSchedule = (Schedule) incomingRequest.get("schedule");
			Schedule	schedule = new Schedule();
			String	icHeader = (String)incomingRequest.get("newSchedule_icHeader");
			String	documentType = "";
			
			if (incomingRequest.containsKey("newSchedule_documentType"))
			{
			    documentType = (String)incomingRequest.get("newSchedule_documentType");
			}
			else
			{
			    documentType = originalSchedule.getComp_id().getDocumentType();
			}
			
			comp_id.setScheduleType(originalSchedule.getComp_id().getScheduleType());
			comp_id.setDocumentType(documentType);
			comp_id.setIcHeader(new BigDecimal(icHeader));
			comp_id.setLineNumber(originalSchedule.getComp_id().getLineNumber());
			schedule.setDescription(originalSchedule.getDescription());
			schedule.setScheduleDate(originalSchedule.getScheduleDate());
			schedule.setCompletionDate(originalSchedule.getCompletionDate());
			schedule.setStatus(originalSchedule.getStatus());
			schedule.setRevisedDate(originalSchedule.getRevisedDate());
			schedule.setScheduleAmount(originalSchedule.getScheduleAmount());
			schedule.setComp_id(comp_id);
			
			String strFiscalYear = (String) incomingRequest.get("PoHeader_fiscalYear");
			String saveAsFiscalYear = (String) incomingRequest.get("PoHeader_applyFiscalYear");
	        Log.debug(this, "Fiscal Year: " + saveAsFiscalYear);
	        
	        if (strFiscalYear != null && saveAsFiscalYear != null) {
	        	setupNewFilcalYear(schedule, saveAsFiscalYear, strFiscalYear);
	        }

			incomingRequest.put("schedule", schedule);

			ScheduleAdd addTask = new ScheduleAdd();
			schedule = (Schedule) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = schedule;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
	
	/**
	 * 
	 * @param schedule
	 */
	private void setupNewFilcalYear(Schedule schedule, String saveAsFiscalYear, String strFiscalYear) {
		
		int fiscalYear = 0;
        try {
			fiscalYear = Integer.parseInt(strFiscalYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		  if (saveAsFiscalYear != null && saveAsFiscalYear.equals("Y")) {
	        	
			  Date scheduleDate = schedule.getScheduleDate();
	            if (scheduleDate != null && fiscalYear > 0) {
	            	Calendar calendar = Calendar.getInstance();
	            	calendar.setTime(scheduleDate);
	            	calendar.set(Calendar.YEAR, fiscalYear);
	            	scheduleDate = calendar.getTime();
	            	schedule.setScheduleDate(scheduleDate);
	            }
	            
	            Date revisedDate = schedule.getRevisedDate();
	            if (revisedDate != null && fiscalYear > 0) {
	            	Calendar calendar = Calendar.getInstance();
	            	calendar.setTime(revisedDate);
	            	calendar.set(Calendar.YEAR, fiscalYear);
	            	revisedDate = calendar.getTime();
	            	schedule.setRevisedDate(revisedDate);
	            }
	            
	            Date completionDate = schedule.getCompletionDate() ;
	            if (completionDate != null && fiscalYear > 0) {
	            	Calendar calendar = Calendar.getInstance();
	            	calendar.setTime(completionDate);
	            	calendar.set(Calendar.YEAR, fiscalYear);
	            	completionDate = calendar.getTime();
	            	schedule.setCompletionDate(completionDate);
	            }
	        }
	}
}