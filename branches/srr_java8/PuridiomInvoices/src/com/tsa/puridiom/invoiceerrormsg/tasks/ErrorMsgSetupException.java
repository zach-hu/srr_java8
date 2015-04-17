package com.tsa.puridiom.invoiceerrormsg.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ErrorMsgSetupException extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String message = HiltonUtility.ckNull((String) incomingRequest.get("errorMsg"));
			List messageList = new ArrayList();			
			if(  message != null ){				 
				 String[] messageCadena = new String[10];
				 messageCadena = message.split("<BR>");
				 
				 for(int i=0; i<messageCadena.length; i++){
					 if(!messageCadena[i].equalsIgnoreCase(""))
					 messageList.add(messageCadena[i]);
				}
			}

			incomingRequest.put("errorMessageList", messageList);
			result = message;
			this.status = Status.SUCCEEDED;			
			incomingRequest.put("invoiceExceptionFailurePage", "/invoice/iv_error_message.jsp");
			incomingRequest.put("successPage", "/invoice/iv_error_message.jsp");
        	this.setPostAction("exitProcess");
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
            this.setStatus(Status.FAILED);
            e.printStackTrace();
            throw e;            
		}
		return result;
	}
}