package com.tsa.puridiom.approvals;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
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

public class QueueEmailNotificationNotReceived extends Task
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
        	String everySevenDays = HiltonUtility.ckNull((String) propertiesManager.getProperty("ACTIVATE EMAIL NOTIFICATION", "DATE", ""));
        	String activateSevenDays = HiltonUtility.ckNull((String) propertiesManager.getProperty("ACTIVATE EMAIL NOTIFICATION", "ACTIVATE", "N"));
			String everySevenActivate = "N";
    		if (Utility.isEmpty(userDateFormat)) {
    			userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
    		}
    		SimpleDateFormat formatter = new SimpleDateFormat(userDateFormat);
        	Calendar fecha = Calendar.getInstance();
		    fecha.add( Calendar.DATE, -7 );
		    int queryMonth = fecha.get(Calendar.MONTH) + 1;
        	int queryYear  = fecha.get(Calendar.YEAR);
        	int queryDay = fecha.get(Calendar.DATE);
        	String valueDate = String.valueOf(queryYear)  + "/" + String.valueOf(queryMonth) + "/" + String.valueOf(queryDay);

        	Calendar today = Calendar.getInstance();
        	String sevenDaysDate = "";

        	String firstDateYear = String.valueOf(queryYear) + "/01/01";
        	PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");

        	StringBuffer queryString = new StringBuffer("from PoHeader as PoHeader where PoHeader.poType <> 'BO' AND PoHeader.poType <> 'SB' AND ");
            queryString.append("PoHeader.lastRevision = 'C' AND PoHeader.status >= '3030' AND PoHeader.status < '4005' AND PoHeader.poNumber <> 'N/A' ");
            queryString.append("AND PoHeader.promisedDate <= to_date( '" + valueDate + "' ,'yyyy-MM-dd') AND PoHeader.promisedDate >= to_date( '" + firstDateYear + "' ,'yyyy-MM-dd') ");

            List list = dbs.query(queryString.toString()) ;

            if(everySevenDays.equalsIgnoreCase("N") && activateSevenDays.equalsIgnoreCase("Y"))
            {
            	today.add( Calendar.DATE, +7 );
            	int todayMonth = today.get(Calendar.MONTH) + 1;
            	int todayYear  = today.get(Calendar.YEAR);
            	int todayDay = today.get(Calendar.DATE);
            	sevenDaysDate = String.valueOf(todayYear)  + "/" + String.valueOf(todayMonth) + "/" + String.valueOf(todayDay);
            	everySevenDays = sevenDaysDate;
            	everySevenActivate = "Y";
            }
            else if(activateSevenDays.equalsIgnoreCase("Y"))
            {
            	int todayMonth = today.get(Calendar.MONTH) + 1;
            	int todayYear  = today.get(Calendar.YEAR);
            	int todayDay = today.get(Calendar.DATE);
            	String todayDate = String.valueOf(todayYear)  + "/" + String.valueOf(todayMonth) + "/" + String.valueOf(todayDay);
            	if(everySevenDays.equalsIgnoreCase(todayDate))
            	{
            		today.add( Calendar.DATE, +7 );
                	int everySevenMonth = today.get(Calendar.MONTH) + 1;
                	int everySevenYear  = today.get(Calendar.YEAR);
                	int everySevenDay = today.get(Calendar.DATE);
                	sevenDaysDate = String.valueOf(everySevenYear) + "/" + String.valueOf(everySevenMonth) + "/" + String.valueOf(everySevenDay);
            		everySevenDays = sevenDaysDate;
                	everySevenActivate = "Y";
            	}
            }
            incomingRequest.put("Property_section","ACTIVATE EMAIL NOTIFICATION");
			incomingRequest.put("Property_property","DATE");
			incomingRequest.put("Property_value",everySevenDays);
			if(everySevenActivate.equalsIgnoreCase("Y"))
			{
				if (list != null && list.size() > 0)
				{
					for (int i = 0; i < list.size(); i++)
				    {
						 PoHeader poHeader = (PoHeader) list.get(i);
						 Map newIncomingRequest = (Map)object;
						 BigDecimal icPoHeader = poHeader.getIcPoHeader();
				    	 String  requisitionerCode = poHeader.getRequisitionerCode();
				    	 String  subject = "Puridiom Alert - Purchase " +  poHeader.getPoNumber() + " Delivery Due Notice]";
				    	 UserProfile user = UserManager.getInstance().getUser(organizationId,requisitionerCode);
				    	 String  sendTo = HiltonUtility.ckNull(user.getMailId());
				    	 newIncomingRequest.put("SendQueue_sendfrom", sendTo);
				    	 String  ccSendTo = HiltonUtility.ckNull((String) DictionaryManager.getInstance("emails", organizationId).getProperty("receiveactions.cc"));
						 if(!HiltonUtility.isEmpty(ccSendTo))
						 {
							 sendTo = sendTo + ";" + ccSendTo;
						 }
				    	 String	 message = "\nPurchase Order " + poHeader.getPoNumber() + " has not been FULLY received in Puridiom.";
				    	 message = message + "\nOrder Date: " + HiltonUtility.getFormattedDate(poHeader.getPoDate(), organizationId, userDateFormat);
				    	 message = message + "\nSupplier: " + poHeader.getVendorName();
				    	 message = message + "\nRequired By: " + formatter.format(poHeader.getRequiredDate());
				    	 message = message + "\nNotes: " + poHeader.getInternalComments();
				    	 String message2 = "";
				    	 String lineString = "from PoLine as PoLine where PoLine.icPoHeader = ?" ;
				    	 List lineList = dbs.query(lineString, icPoHeader, Hibernate.BIG_DECIMAL) ;
				    	 if(lineList != null && lineList.size() > 0)
				    	 {
				    		 message2 = "\nItem(s) as follows: ";
				    		 int numItem = 10;
				    		 if(lineList.size() < 11)
				    		 {
				    			 numItem = lineList.size();
				    		 }
			    	      	 for (int j = 0; j < numItem; j++)
			    	      	 {
			    				 PoLine poLine = (PoLine) lineList.get(j);
			    				 message2 = message2 + "\nLine " + poLine.getLineNumber().toString() + ": " + poLine.getQuantity().toString() + " " + poLine.getUmCode() + " " + poLine.getDescription();
			    			 }
			    	      	 if(lineList.size() > 10)
			    	      	 {
			    	      		message2 = message2 + "[ more items .....]";
			    	      	 }
				    	 }
				    	 newIncomingRequest.put("SendQueue_doctype", "RAE");
				    	 newIncomingRequest.put("SendQueue_docic", icPoHeader.toString());
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