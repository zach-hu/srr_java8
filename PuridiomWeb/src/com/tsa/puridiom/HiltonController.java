/*
 * Created on Jul 15, 2003 
 */
package com.tsa.puridiom;

import com.tsa.puridiom.handlers.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

/**
 * @author Administrator 
 */
public class HiltonController {
	
	
	public Map handleRequest(Map requestParameters) throws Exception {
		Map outgoingMap = null;
		try {
		    requestParameters.put("handlerNotFound", false) ;
			String	handlerList = (String) requestParameters.get("handler");
			int lastIndex = handlerList.lastIndexOf(";");
			int len = handlerList.length() - 1;
			if (lastIndex != len) {
				handlerList = handlerList + ";";
			}
				
			while (handlerList.length() > 0) {
				String	handlerName = handlerList.substring(0, handlerList.indexOf(";"));
				handlerList = handlerList.substring(handlerList.indexOf(";") + 1);
				
				requestParameters.put("handler", handlerName);
				IHandler handler = HandlerFactory.getInstance().getHandler(requestParameters);
				if (handler == null) {
				    Log.error(this, "The handler [" + handlerName + "] could not be found.");
				    requestParameters.put("handler", "DoNothingHandler");
				    requestParameters.put("handlerNotFound", true) ;
					handler = HandlerFactory.getInstance().getHandler(requestParameters);
				}
				outgoingMap = handler.handleRequest(requestParameters);
				String continueHandlers = "Y";
				if (outgoingMap.containsKey("continueHandlers")) {
					continueHandlers = Utility.ckNull((String) outgoingMap.get("continueHandlers"));
				}
				if (continueHandlers.equals("N")) {
					break;
				}
			}
		}
		catch(Exception exception) {
			requestParameters.put("exception", exception);
			exception.printStackTrace();
		}
		return outgoingMap;
	}
}
