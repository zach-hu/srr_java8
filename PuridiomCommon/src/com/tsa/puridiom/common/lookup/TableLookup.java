package com.tsa.puridiom.common.lookup;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;
import java.lang.reflect.*;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class TableLookup
{
	private String 	organizationId;
	private String 	userId;
	private	int		lookupStatus ;
	private DBSession dbsession;

	public TableLookup(String organizationId, String userId)
	{
		this.organizationId = organizationId;
		this.userId = userId;
		this.lookupStatus = Status.SUCCEEDED ;
	}


	/**
	 * Not to be used by any other class.
	 * This session is used Only to get data for the graphs.
	 * @return session
	 */
	private DBSession getDbsession()
	{
		return dbsession;
	}

	private void setDbsession()
	{
		try
		{
			this.dbsession = new DBSession(this.organizationId);
			this.dbsession.noUpdate() ;
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String processLookup(Map incomingRequest)
	{
		StringBuffer	respData = new StringBuffer("") ;
		this.setDbsession();
		try
		{
			String pXml = (String)incomingRequest.get("process") ;
			String resultObj = (String)incomingRequest.get("resultObj") ;
			incomingRequest.put("dbsession", this.dbsession) ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.organizationId);
			PuridiomProcess process = processLoader.loadProcess(pXml);

			process.executeProcess(incomingRequest);

       		Object retObj = incomingRequest.get(resultObj) ;
       		String rType = retObj.getClass().getName() ;
       		Object result = null ;
       		if (rType.toLowerCase().indexOf("list") >= 0) {
       			// Is it a list
	       		List rList = (List) retObj ;
	       		if (rList.size() > 0) {
	       			result = rList.get(0) ;
	       		}
       		} else {
       			result = retObj ;
       		}
			if (process.getStatus() == Status.SUCCEEDED && result != null)
			{
				respData.append("<data>") ;
                Class c = result.getClass();
	       		Method m[] = c.getMethods() ;
	       		for (int mx = 0; mx < m.length; mx++ ) {
	       			String	methodName = m[mx].getName() ;
	       			if (methodName.startsWith("get")) {
	       				Object obj = m[mx].invoke(result, null) ;
	       				String	shortName = methodName.substring(3) ;
	       				respData.append("<" + shortName + ">") ;
	       				if (obj != null) {
	       					respData.append( obj.toString()) ;
//	       					respData.append("<![CDATA[" + obj.toString() + "]]>") ;
//	       					System.out.println(m[mx].getReturnType() + "  "  + m[mx].getName() + " = " + obj.toString() ) ;
	       				} else {
			       			String returnType = m[mx].getReturnType().getName() ;
	       					if (returnType.indexOf("BigDecimal") >= 0) {
	       						respData.append("0") ;
	       					} else {
	       						respData.append("") ;
	       					}
	       				}
	       				respData.append("</" + shortName + ">") ;
	       			}
	       		}
   				respData.append("</data>") ;
   				this.setLookupStatus(Status.SUCCEEDED) ;
			}
			else
			{
				this.setLookupStatus(Status.FAILED) ;
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace() ;
			this.setLookupStatus(Status.FAILED) ;
		}
		finally
	      {
			// force session close
			try {
				this.getDbsession().close() ;
			}
			catch (Exception ce) {
				ce.printStackTrace() ;
			}
	      }  // end finally

		return respData.toString() ;
	}


	/**
	 * @return the lookupStatus
	 */
	public int getLookupStatus() {
		return lookupStatus;
	}


	/**
	 * @param lookupStatus the lookupStatus to set
	 */
	public void setLookupStatus(int lookupStatus) {
		this.lookupStatus = lookupStatus;
	}
}
