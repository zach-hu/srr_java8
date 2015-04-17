package com.tsa.puridiom.alerts.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.type.Type;

import com.tsa.puridiom.alerts.Alert;
import com.tsa.puridiom.alerts.AlertManager;
import com.tsa.puridiom.alerts.AlertWhereArgument;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class AlertsProcess extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		try
		{
			String organizationId = (String)incomingRequest.get("organizationId");
			List alertList = AlertManager.getInstance().getAlertList(organizationId);
			for (Iterator iter = alertList.iterator(); iter.hasNext();)
			{
				Alert alert = (Alert) iter.next();
				if(alert.isEnabled())
				{
					boolean runToday = true;
					if(alert.getLastRun() != null)
					{
						String lastRun = Utility.getDateFormat(alert.getLastRun().toString(), "yyyy/MM/dd");
						if(lastRun.compareTo(Dates.today("yyyy/MM/dd", "")) > -1)
						{
							runToday = false;
						}
					}
					if(runToday)
					{
						System.out.println("getting data for alert: " + alert.getName());
						Map alertRequest = new HashMap();
						alertRequest.put("dbsession", incomingRequest.get("dbsession"));
						alertRequest.put("select", alert.getWhere());
						List dataRows = this.retrieveAlertData(alertRequest, alert);
						System.out.println("found: " + dataRows.size());
						int countElement = 1;
						for (Iterator iterator = dataRows.iterator(); iterator.hasNext();)
						{
							//TODO if alert has a repeat argument - check for it and compare it against the filter value
							String lastElement = "";
							if(dataRows.size() == countElement)
							{
								lastElement = "Y";
							}
							countElement++;
							this.logAlertToSendAlerts(organizationId, alert, (Object) iterator.next(), lastElement);
							//this.getMsg(alert, alertData, organizationId);
						}
						UpdateLastRunAlert update = new UpdateLastRunAlert(organizationId);
						update.setLastRun(alert.getName());
					}
				}

			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Alerts failed to load!", e);
		}
		return ret;
	}

	public void logAlertToSendAlerts(String organizationId, Alert alert, Object alertData, String lastElement)
	{
		Map incomingRequest = new HashMap();
		incomingRequest.put("organizationId", organizationId);
		if (alertData instanceof BigDecimal)
		{
			incomingRequest.put("SendQueue_docic", alertData.toString());
			incomingRequest.put("SendQueue_messagetext", HiltonUtility.ckNull(alert.getMessage().toString()));
			incomingRequest.put("RequisitionHeader_icReqHeader", alertData.toString());
			incomingRequest.put("RequisitionLine_icReqHeader", alertData.toString());
			incomingRequest.put("PoHeader_icPoHeader", alertData.toString());
			incomingRequest.put("PoLine_icPoHeader", alertData.toString());
			incomingRequest.put("ApprovalLog_icHeader", alertData.toString());
		}
		else if (alertData instanceof String)
		{
			incomingRequest.put("VendorInsuranceDefault_vendorId", alertData.toString());
			incomingRequest.put("Vendor_vendorId", alertData.toString());
			incomingRequest.put("PoHeader_vendorId", alertData.toString());
		}
		else if (alertData instanceof Object[])
		{
			Object[] data = (Object[]) alertData;
			incomingRequest.put("SendQueue_docic", data[0].toString());
			String ics = "";
			for (int i = 1; i < data.length; i++)
			{
				if(ics.length() > 0)
				{
					ics = ics + "@@";
				}
				ics = ics + data[i].toString();
			}
			incomingRequest.put("SendQueue_messagetext", ics);
		}
		else
		{
			incomingRequest.put("SendQueue_messagetext", alertData.toString());
		}

        incomingRequest.put("SendQueue_doctype", alert.getType());
        incomingRequest.put("subject", HiltonUtility.ckNull(alert.getSubject()));
        incomingRequest.put("SendQueue_args", alert.getProcess());
        incomingRequest.put("everyDay", HiltonUtility.ckNull(alert.getEveryDay()));
        incomingRequest.put("lastElement", lastElement);
        incomingRequest.put("typeAlert", HiltonUtility.ckNull(alert.getName()));
        incomingRequest.put("descriptionAlert", HiltonUtility.ckNull(alert.getDescription()));
        String processFromAlert = alert.getRetrieveProcess();
        
        PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
        try
        {
        	PuridiomProcess process = processLoader.loadProcess(processFromAlert);
        	process.executeProcess(incomingRequest);
        }
        catch (Exception e)
        {
        	//TODO need to find what we need to log.
		}
	}

	public List  retrieveAlertData (Object object, Alert alert) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = new ArrayList();

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String queryString = (String ) incomingRequest.get("select");

			if(alert.getWhereArgList().size() > 0)
			{
				Object[] values = new Object[alert.getWhereArgList().size()];
				Type[] types = new Type[alert.getWhereArgList().size()];
				result =dbs.query(queryString.trim(), values);
			}
			else
			{
				result = dbs.query(queryString) ;
			}

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			Log.warn(this, "Alert data could not be retrieved");
		}
		return result;
	}

}
