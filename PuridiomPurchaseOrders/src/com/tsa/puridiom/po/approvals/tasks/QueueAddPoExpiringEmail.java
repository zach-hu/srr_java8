package com.tsa.puridiom.po.approvals.tasks;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
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

public class QueueAddPoExpiringEmail extends Task
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
        	PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
        	PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
        	String nextRunDay = HiltonUtility.ckNull((String) propertiesManager.getProperty("ACTIVATE EMAIL POEXPIRE", "DATE", ""));
        	String everyDay = HiltonUtility.ckNull((String) incomingRequest.get("everyDay"));
        	int eachDay = 1;
        	if(!HiltonUtility.isEmpty(everyDay))
        	{
        		eachDay = Integer.parseInt(everyDay);
        	}
			String everySevenActivate = "N";
    		if (Utility.isEmpty(userDateFormat)) {
    			userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
    		}
    		Calendar today = Calendar.getInstance();
        	String sevenDaysDate = "";

        	PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");

            if(nextRunDay.equalsIgnoreCase("N"))
            {
            	today.add( Calendar.DATE, +eachDay );
            	int todayMonth = today.get(Calendar.MONTH) + 1;
            	int todayYear  = today.get(Calendar.YEAR);
            	int todayDay = today.get(Calendar.DATE);
            	sevenDaysDate = String.valueOf(todayYear)  + "/" + String.valueOf(todayMonth) + "/" + String.valueOf(todayDay);
            	nextRunDay = sevenDaysDate;
            	everySevenActivate = "Y";
            }
            else
            {
            	int todayMonth = today.get(Calendar.MONTH) + 1;
            	int todayYear  = today.get(Calendar.YEAR);
            	int todayDay = today.get(Calendar.DATE);
            	String todayDate = String.valueOf(todayYear)  + "/" + String.valueOf(todayMonth) + "/" + String.valueOf(todayDay);
            	if(nextRunDay.equalsIgnoreCase(todayDate))
            	{
            		today.add( Calendar.DATE, +eachDay );
                	int everySevenMonth = today.get(Calendar.MONTH) + 1;
                	int everySevenYear  = today.get(Calendar.YEAR);
                	int everySevenDay = today.get(Calendar.DATE);
                	sevenDaysDate = String.valueOf(everySevenYear) + "/" + String.valueOf(everySevenMonth) + "/" + String.valueOf(everySevenDay);
                	nextRunDay = sevenDaysDate;
                	everySevenActivate = "Y";
            	}
            }
            incomingRequest.put("Property_section","ACTIVATE EMAIL POEXPIRE");
			incomingRequest.put("Property_property","DATE");
			incomingRequest.put("Property_value",nextRunDay);
			if(everySevenActivate.equalsIgnoreCase("Y"))
			{
				Map newIncomingRequest = (Map)object;
				BigDecimal icPoHeader = poHeader.getIcPoHeader();
				String  requisitionerCode = poHeader.getRequisitionerCode();
				String  buyerCode = poHeader.getBuyerCode();
				String  subject = "System Alert, Purchase Order Expiring " +  poHeader.getPoNumber();
				UserProfile user = UserManager.getInstance().getUser(organizationId,buyerCode);
				String  sendTo = HiltonUtility.ckNull(user.getMailId());
				newIncomingRequest.put("SendQueue_sendfrom", sendTo);
				String  ccSendTo = HiltonUtility.ckNull((String) DictionaryManager.getInstance("emails", organizationId).getProperty("receiveactions.cc"));
				if(!HiltonUtility.isEmpty(ccSendTo))
				{
					sendTo = sendTo + ";" + ccSendTo;
				}		
			
				String	 message = "\nPurchase Order " + poHeader.getPoNumber() + " expires in 30 days.";
				message = message + "\nOrder Date: " + HiltonUtility.getFormattedDate(poHeader.getPoDate(), organizationId, userDateFormat);
				message = message + "\nEffective Date: " + HiltonUtility.getFormattedDate(poHeader.getEffectiveDate(), organizationId, userDateFormat) + " Expiration Date: " + HiltonUtility.getFormattedDate(poHeader.getExpirationDate(), organizationId, userDateFormat);
				message = message + "\nSupplier: " + poHeader.getVendorName();
				message = message + "\nRequired By: " + HiltonUtility.getFormattedDate(poHeader.getRequiredDate(), organizationId, userDateFormat);
				message = message + "\nRemarks: " + poHeader.getInternalComments();
				message = message + "\nPurchase Order: " + poHeader.getPoNumber() + " is pending approval for the past 90 days.";
				String message2 = "";
				
				List poLineList = (List) incomingRequest.get("poLineRetrieve");
				if(poLineList != null && poLineList.size() > 0)
		    	{
					message2 = "\nItem(s) as follows: ";
			    	int numItem = 10;
			    	if(poLineList.size() < 11)
			    	{
			    		numItem = poLineList.size();
			    	}
			    	 for (int i = 0; i < numItem; i++)
		    	    {
			    		PoLine poLine = (PoLine) poLineList.get(i);
		    			message2 = message2 + "\nLine " + poLine.getLineNumber().toString() + " Item #: " + poLine.getItemNumber() + ": " + poLine.getQuantity().toString();
		    		}
			    	if(poLineList.size() > 10)
		    	    {
		    	    	message2 = message2 + "[ more items .....]";
		    	    }
				}
				
				newIncomingRequest.put("SendQueue_subject",subject);
				newIncomingRequest.put("SendQueue_sendtotype", "E");
				newIncomingRequest.put("SendQueue_sendto", sendTo);
				newIncomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);
				newIncomingRequest.put("SendQueue_messagetext", message);
				newIncomingRequest.put("SendQueue_messagetext2", message2);
				newIncomingRequest.put("SendQueue_owner", poHeader.getOwner());
				process.executeProcess(newIncomingRequest);
			}
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
            throw new TsaException("Error processing HTML approvals.");
        }
        return result;
	}
}