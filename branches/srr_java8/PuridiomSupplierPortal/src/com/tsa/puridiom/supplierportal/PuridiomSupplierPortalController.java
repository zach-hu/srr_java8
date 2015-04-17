/*
 * Created on March 11, 2004
 */
package com.tsa.puridiom.supplierportal;

import com.tsa.puridiom.supplierportal.handlers.*;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

/**
 * @author Kelli
 */
public class PuridiomSupplierPortalController {


	public Map handleRequest(Map requestParameters) throws Exception {
		Map outgoingMap = null;
		try {
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
				if (handler != null) {
					outgoingMap = handler.handleRequest(requestParameters);
				} else {
					if (outgoingMap == null) {
						outgoingMap = new HashMap();
					}
					String errorMsg = "The handler specified ('" +handlerName + "') does not exist.";
					outgoingMap.put("errorMsg", errorMsg);
					outgoingMap.put("viewPage", requestParameters.get("failurePage"));
					throw new Exception (errorMsg);
				}
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
			exception.printStackTrace();
			throw exception;
		}
		finally {
			return outgoingMap;
		}
	}
}
