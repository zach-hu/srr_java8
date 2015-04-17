package com.tsa.puridiom.vendorinsurancedefault.tasks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.entity.VendorInsuranceDefault;
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

public class QueueVendorInsuranceDefaultCoverage extends Task
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
        	String subjectAlert = (String)incomingRequest.get("subject");
        	String descriptionAlert = (String)incomingRequest.get("descriptionAlert");
        	List contractsList = (List) incomingRequest.get("contractsList");
        	if(contractsList == null)
        	{
        		contractsList = new ArrayList();
        	}
        	String typeAlert = (String)incomingRequest.get("typeAlert");
        	Map newIncomingRequest = (Map)object;
        	Vendor vendor = (Vendor) incomingRequest.get("vendor");
        	if(vendor == null)
        	{
        		vendor = new Vendor();
        	}
        	VendorInsuranceDefault vendorInsuranceDefault = (VendorInsuranceDefault) incomingRequest.get("vendorInsuranceDefaultRetrieveById");

        	PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
        	String everyDays = "";
        	if(typeAlert.equalsIgnoreCase("insurance_coverage_1"))
			{
        		everyDays = HiltonUtility.ckNull((String) propertiesManager.getProperty("INSURANCE EXPIRE1 DATE", "DATE", ""));
			}
			else if(typeAlert.equalsIgnoreCase("insurance_coverage_2"))
			{
				everyDays = HiltonUtility.ckNull((String) propertiesManager.getProperty("INSURANCE EXPIRE2 DATE", "DATE", ""));
			}
			else if(typeAlert.equalsIgnoreCase("insurance_coverage_3"))
			{
				everyDays = HiltonUtility.ckNull((String) propertiesManager.getProperty("INSURANCE EXPIRE3 DATE", "DATE", ""));
			}
			else if(typeAlert.equalsIgnoreCase("insurance_coverage_4"))
			{
				everyDays = HiltonUtility.ckNull((String) propertiesManager.getProperty("INSURANCE EXPIRE4 DATE", "DATE", ""));
			}
			else if(typeAlert.equalsIgnoreCase("insurance_coverage_5"))
			{
				everyDays = HiltonUtility.ckNull((String) propertiesManager.getProperty("INSURANCE EXPIRE5 DATE", "DATE", ""));
			}
			else if(typeAlert.equalsIgnoreCase("insurance_coverage_6"))
			{
				everyDays = HiltonUtility.ckNull((String) propertiesManager.getProperty("INSURANCE EXPIRE6 DATE", "DATE", ""));
			}
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
            if(typeAlert.equalsIgnoreCase("insurance_coverage_1"))
			{
            	incomingRequest.put("Property_section","INSURANCE EXPIRE1 DATE");
			}
			else if(typeAlert.equalsIgnoreCase("insurance_coverage_2"))
			{
				incomingRequest.put("Property_section","INSURANCE EXPIRE2 DATE");
			}
			else if(typeAlert.equalsIgnoreCase("insurance_coverage_3"))
			{
				incomingRequest.put("Property_section","INSURANCE EXPIRE3 DATE");
			}
			else if(typeAlert.equalsIgnoreCase("insurance_coverage_4"))
			{
				incomingRequest.put("Property_section","INSURANCE EXPIRE4 DATE");
			}
			else if(typeAlert.equalsIgnoreCase("insurance_coverage_5"))
			{
				incomingRequest.put("Property_section","INSURANCE EXPIRE5 DATE");
			}
			else if(typeAlert.equalsIgnoreCase("insurance_coverage_6"))
			{
				incomingRequest.put("Property_section","INSURANCE EXPIRE6 DATE");
			}
            incomingRequest.put("Property_property","DATE");
            incomingRequest.put("Property_value",everyDays);
            if(everyActivate.equalsIgnoreCase("Y") && contractsList != null)
			{
				String vendorOwner = HiltonUtility.ckNull(vendor.getOwner());
				if(HiltonUtility.isEmpty(vendorOwner))
				{
					vendorOwner = vendor.getVendorId();
				}
				UserProfile user = UserManager.getInstance().getUser(organizationId,vendorOwner);
				String  sendTo = HiltonUtility.ckNull(user.getMailId());

				String  subject = subjectAlert + " " + HiltonUtility.ckNull(vendor.getVendorName());

				String	message = "\n" + descriptionAlert + " expires on ";
				if(typeAlert.equalsIgnoreCase("insurance_coverage_1"))
				{
					message = message + vendorInsuranceDefault.getExpires1() +  ".";
				}
				else if(typeAlert.equalsIgnoreCase("insurance_coverage_2"))
				{
					message = message + vendorInsuranceDefault.getExpires2() +  ".";
				}
				else if(typeAlert.equalsIgnoreCase("insurance_coverage_3"))
				{
					message = message + vendorInsuranceDefault.getExpires3() +  ".";
				}
				else if(typeAlert.equalsIgnoreCase("insurance_coverage_4"))
				{
					message = message + vendorInsuranceDefault.getExpires4() +  ".";
				}
				else if(typeAlert.equalsIgnoreCase("insurance_coverage_5"))
				{
					message = message + vendorInsuranceDefault.getExpires5() +  ".";
				}
				else if(typeAlert.equalsIgnoreCase("insurance_coverage_6"))
				{
					message = message + vendorInsuranceDefault.getExpires6() +  ".";
				}
				message = message + "\nInsurance Contact: " + HiltonUtility.ckNull(vendorInsuranceDefault.getInsuranceContact());
		    	message = message + "\nInsurance Comments: " + HiltonUtility.ckNull(vendorInsuranceDefault.getInsuranceNotes());

		    	String message2 = "";
		    	if(contractsList != null && contractsList.size() > 0)
			    {
		    		int numPo = 10;
			    	if(contractsList.size() < 11)
			    	{
			    		numPo = contractsList.size();
			    	}
		    	    for (int i = 0; i < numPo; i++)
		    	    {
		    	    	PoHeader poHeader = (PoHeader) contractsList.get(i);
		    			message2 = message2 + "\nOrder #: " + poHeader.getPoNumber() + " Status: " + poHeader.getStatus();
		    			message2 = message2 + "\nEffective Date: " + HiltonUtility.getFormattedDate(poHeader.getEffectiveDate(), organizationId, userDateFormat) + " Expiration Date: " + HiltonUtility.getFormattedDate(poHeader.getExpirationDate(), organizationId, userDateFormat) + " Amount: " + HiltonUtility.getFormattedCurrency(poHeader.getTotal(), "USD", organizationId);
		    			message2 = message2 + "\nBuyer: " + poHeader.getBuyerCode();
		    			message2 = message2 + "\nSummary: " + poHeader.getInternalComments();

		    		}
		    	    if(contractsList.size() > 10)
		    	    {
		    	    	message2 = message2 + "[ more Orders .....]";
		    	    }
				}


				newIncomingRequest.put("SendQueue_sendfrom", sendTo);
				String  ccSendTo = HiltonUtility.ckNull((String) DictionaryManager.getInstance("emails", organizationId).getProperty("insuranceexpire.cc"));
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
				newIncomingRequest.put("SendQueue_owner", vendorOwner);
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