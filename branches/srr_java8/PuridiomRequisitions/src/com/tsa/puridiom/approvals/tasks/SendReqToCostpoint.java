/*
 * Created on Dec 7, 2004
 */
package com.tsa.puridiom.approvals.tasks;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.ScheduledTask;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class SendReqToCostpoint extends Task
{
	public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        
        String oid = (String) incomingRequest.get("organizationId");
        String newStatus = (String) incomingRequest.get("newStatus");
        RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
        
        PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
        
        if (newStatus.equals("1035") && "K".equals(reqHeader.getRequisitionType()) &&
        	"Y".equals(propertiesManager.getProperty("REQ OPTIONS", "COSTPOINT ADMINCHECKREQUESTS", "N"))) {
	        if (reqHeader == null) System.out.println("reqHeader is null");
	    	String s_header_ic = reqHeader.getIcReqHeader().toString() ;
	    	
	    	
	
	    	String		targetUrl = propertiesManager.getProperty("WEB_CLIENT","VOUCHERSERVICEURL","") ;
	    	
	    	HttpURLConnection connection = null;
	    	InputStream inputStream = null;
	    	URL 	url = null;
	
	    	url = new URL(targetUrl );
	
	   		connection = (HttpURLConnection)url.openConnection(Proxy.NO_PROXY);
	    		
	   		if (connection == null) System.out.println("connection is null");
	
	   		connection.setDoOutput(true);
	   		connection.setDoInput(true);
	   		connection.setRequestMethod("POST") ;
	   		connection.setUseCaches(false);
	   		connection.setAllowUserInteraction(false);
	   		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	
	   		OutputStream os = connection.getOutputStream();
	   		if (os == null) System.out.println("os is null");
	
	   		OutputStreamWriter osw = new OutputStreamWriter(os);
	   		if (osw == null) System.out.println("osw is null");
	   		//write name value pairs
	   		osw.write("REQUISITION_NUMBER=" + reqHeader.getRequisitionNumber());
	   		osw.write("&PORT_SELECTION=apvoucherinsert_req");
	   		osw.write("&Type=AP_REQ");
	   		osw.flush();
	   		osw.close();
	   		
	   		Date startDate = new Date();
	   		inputStream = connection.getInputStream();
	   		DataInputStream iData = new DataInputStream(inputStream);
	    	String iLine = null;
	    	StringBuffer buf = new StringBuffer("");
	
	    	do {
	    		iLine = iData.readLine();
	    			
	    		if (iLine == null) {
	    			break;
	    		}
	    		if (iLine.trim().length() > 0) {
	    			buf.append(iLine);
	    		}
	    	} while(true);
	
	        String s_input = buf.toString();
	    	inputStream.close() ;
	
	   		Document doc ;
	
	  		String severity = "1";
	  		String msgText = "";
	  		
	  		//filter s_input down to just XML Response
			s_input = s_input.substring(s_input.indexOf("<Response>"));
			s_input = s_input.substring(0, s_input.indexOf("</Response>") + 11);
	
	   		InputStream is = new ByteArrayInputStream(s_input.getBytes("UTF-8"));
	   		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	   		DocumentBuilder db=dbf.newDocumentBuilder();
	//    		doc=db.parse(xmlfile);
	   		try {
	   			doc = db.parse(is) ;
	   		} catch (Exception e) {
	   			if (buf == null || buf.length() == 0) {
	   				System.out.println("SendReqToCostpoint: Empty Response");
	   			} else {
	   				System.out.println("SendReqToCostpoint response: " + buf);
	   			}
	   			
	   			throw e;
	   		}
	   		
	   		Date endDate = new Date();
	   		
	   		NodeList nlist = doc.getFirstChild().getChildNodes() ;
	   		int nlength = nlist.getLength() ;
	   		String failedMsg = "";
	   		for (int ix = 0; ix < nlength; ix++) {
	   		  	Node nnode = nlist.item(ix) ;
	   		   	String nname = nnode.getNodeName() ;
	   		   	if (nname.equalsIgnoreCase("severity")) {
	   		   		severity = nnode.getLastChild().getNodeValue() ;
	   		   	}
	   		   	
	   		   	if (nname.equalsIgnoreCase("message")) {
	   		  		NodeList mlist = nnode.getChildNodes() ;
	   		   		int mlength = mlist.getLength() ;
	   		   		
		   		   	for (int im = 0; im < mlength; im++) {
	   		   	    	Node mnode = mlist.item(im) ;
	   		   	    	String mname = mnode.getNodeName() ;
	   		    		if ("msgtext".equalsIgnoreCase(mname)) {
	   		    			msgText = mnode.getLastChild().getNodeValue();
	   		    		}
	   		   		}
	   		   	}
	   	   }
	   		
	   	   if (!"1".equals(severity)) {
   		     this.setStatus(Status.FAILED);
   		   	 failedMsg = msgText;
	   	   }
	   		
	   	   //insert record into scheduled_task table
	   	   ScheduledTask scheduledTask = new ScheduledTask();
	   	   UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
	   	   scheduledTask.setIcSchedule(new BigDecimal(ukg.getUniqueKey()));
	   		
	   	   DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmm:ss");
	   	   scheduledTask.setTaskEndDate(dateFormat.format(endDate));
	   	   scheduledTask.setTaskStartDate(dateFormat.format(startDate));
	   	   scheduledTask.setTaskName("InvoiceSendToCostpoint");
	   	   scheduledTask.setTaskProcess(reqHeader.getRequisitionNumber());
	   	   scheduledTask.setTaskStatus("" + getStatus()); // four digit task status
	   	   dbs.add(scheduledTask);
	   	   
	   	   if (Status.FAILED == getStatus()) {
	   		   throw new Exception("Failed Costpoint Submission: " + failedMsg);
	   	   }
        }
        
        return null;
    }
}
