package com.tsa.puridiom.approvals;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class QueueAddReqEscalateApprovalEmail extends Task
{
	public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        	String organizationId = (String)incomingRequest.get("organizationId");
        	String userDateFormat = (String) incomingRequest.get("userDateFormat");
        	Map newIncomingRequest = (Map)object;
        	RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
        	ApprovalLog approvalLog = (ApprovalLog) incomingRequest.get("approvalLog");
        	PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
        	String everyDays = HiltonUtility.ckNull((String) propertiesManager.getProperty("REQ ESCALATE BACKUP", "DATE", ""));
        	String everyDay = HiltonUtility.ckNull((String) incomingRequest.get("everyDay"));
        	int eachDay = 1;
        	if(!HiltonUtility.isEmpty(everyDay))
        	{
        		eachDay = Integer.parseInt(everyDay);
        	}
			String everyActivate = "N";
    		if (Utility.isEmpty(userDateFormat)) {
    			userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "yyyy-MM-dd") ;
    		}
        	Calendar today = Calendar.getInstance();
        	String sevenDaysDate = "";

        	PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");

            if(everyDays.equalsIgnoreCase("N"))
            {
            	today.add( Calendar.DATE, +eachDay );
            	int todayMonth = today.get(Calendar.MONTH) + 1;
            	int todayYear  = today.get(Calendar.YEAR);
            	int todayDay = today.get(Calendar.DATE);
            	sevenDaysDate = String.valueOf(todayYear)  + "/" + String.valueOf(todayMonth) + "/" + String.valueOf(todayDay);
            	everyDays = sevenDaysDate;
            	everyActivate = "Y";
            }
            else
            {
            	int todayMonth = today.get(Calendar.MONTH) + 1;
            	int todayYear  = today.get(Calendar.YEAR);
            	int todayDay = today.get(Calendar.DATE);
            	String todayDate = String.valueOf(todayYear)  + "/" + String.valueOf(todayMonth) + "/" + String.valueOf(todayDay);
            	if(everyDays.equalsIgnoreCase(todayDate))
            	{
            		today.add( Calendar.DATE, +eachDay );
                	int everySevenMonth = today.get(Calendar.MONTH) + 1;
                	int everySevenYear  = today.get(Calendar.YEAR);
                	int everySevenDay = today.get(Calendar.DATE);
                	sevenDaysDate = String.valueOf(everySevenYear) + "/" + String.valueOf(everySevenMonth) + "/" + String.valueOf(everySevenDay);
                	everyDays = sevenDaysDate;
                	everyActivate = "Y";
            	}
            }
            incomingRequest.put("Property_section","REQ ESCALATE BACKUP");
			incomingRequest.put("Property_property","DATE");
			incomingRequest.put("Property_value",everyDays);
			if(everyActivate.equalsIgnoreCase("Y"))
			{
				String currentApprover = approvalLog.getComp_id().getUserId();
				String backupApprover = HiltonUtility.ckNull(approvalLog.getBackupApprover());
				String currentApproverName = "";
				String backupApproverName = "";
				UserProfile userProfile = new UserProfile(); 
				
				if(HiltonUtility.isEmpty(backupApprover))
				{
					backupApprover = approvalLog.getComp_id().getUserId();
				}
				currentApproverName = UserManager.getInstance().getUser(organizationId, currentApprover).getDisplayName();
				backupApproverName = UserManager.getInstance().getUser(organizationId, backupApprover).getDisplayName();
				
				String s_approved_date = HiltonUtility.getFormattedDate(approvalLog.getDateAssigned(), organizationId, userDateFormat);
				String  requisitionerCode = requisitionHeader.getRequisitionerCode();
				
				String  subject = "Puridiom System Alert - Requisition Number: " +  requisitionHeader.getRequisitionNumber() + " has been escalated to " + currentApproverName;
				
				String	message = "\nRequisition Number " + requisitionHeader.getRequisitionNumber() + " was assigned to " + currentApproverName +  " on " + s_approved_date + " , but has now been escalated to " + backupApproverName; 
				message = message + "\nRequisition Date: " + HiltonUtility.getFormattedDate(requisitionHeader.getRequisitionDate(), organizationId, userDateFormat);
		    	message = message + "\nRemarks " + HiltonUtility.ckNull(requisitionHeader.getInternalComments());
		    	String message2 = "";
		    	List reqLine = (List) incomingRequest.get("requisitionLineRetrieve");
		    	if(reqLine != null && reqLine.size() > 0)
			    {
		    		message2 = "\nItem(s) as follows: ";
			    	int numItem = 10;
			    	if(reqLine.size() < 11)
			    	{
			    		numItem = reqLine.size();
			    	}
		    	    for (int i = 0; i < numItem; i++)
		    	    {
		    	    	RequisitionLine requisitionLine = (RequisitionLine) reqLine.get(i);
		    			message2 = message2 + "\nLine " + requisitionLine.getLineNumber().toString() + ": " + requisitionLine.getQuantity().toString() + " " + requisitionLine.getUmCode() + " " + requisitionLine.getDescription();
		    		}
		    	    if(reqLine.size() > 10)
		    	    {
		    	    	message2 = message2 + "[ more items .....]";
		    	    }
				}
		    		 
		    	UserProfile user = UserManager.getInstance().getUser(organizationId,requisitionerCode);
				String  sendTo = HiltonUtility.ckNull(user.getMailId());
				newIncomingRequest.put("SendQueue_sendfrom", sendTo);
				String  ccSendTo = HiltonUtility.ckNull((String) DictionaryManager.getInstance("emails", organizationId).getProperty("reqescalate.cc"));
				if(!HiltonUtility.isEmpty(ccSendTo))
				{
					sendTo = sendTo + ";" + ccSendTo;
				}
				
				newIncomingRequest.put("SendQueue_subject",subject);
				newIncomingRequest.put("SendQueue_sendtotype", "E");
				newIncomingRequest.put("SendQueue_sendto", sendTo);
				newIncomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);
				newIncomingRequest.put("SendQueue_messagetext", message);
				newIncomingRequest.put("SendQueue_messagetext2", message2);
				newIncomingRequest.put("SendQueue_owner", requisitionerCode);
				process.executeProcess(newIncomingRequest);
			}
			this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
            throw new TsaException("Error processing HTML approvals.");
        }		
		
        return result;
	}
}