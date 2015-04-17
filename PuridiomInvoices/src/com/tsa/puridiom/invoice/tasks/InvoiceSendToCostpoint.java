package com.tsa.puridiom.invoice.tasks;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.ScheduledTask;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class InvoiceSendToCostpoint extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		
		String oid = (String) incomingRequest.get("organizationId");
		String newStatus = (String) incomingRequest.get("newStatus");
		InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
		DBSession dbs = (DBSession) incomingRequest.get("dbsession");

		PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	        
        if ("Y".equals(propertiesManager.getProperty("WEB_CLIENT", "COSTPOINT INVOICES", "N")) &&
        	(newStatus == null || DocumentStatus.IVC_APPROVED.equals(newStatus))) {
	        String		targetUrl = propertiesManager.getProperty("WEB_CLIENT","VOUCHERSERVICEURL","") ;
		    	
	    	HttpURLConnection connection = null;
	    	InputStream inputStream = null;
	    	URL 	url = null;
		
	    	url = new URL(targetUrl );
		
	   		connection = (HttpURLConnection)url.openConnection(Proxy.NO_PROXY);
		    		
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
	   		osw.write("INVOICE_IC_HEADER=" + invoiceHeader.getIcIvcHeader());
	   		osw.write("&PORT_SELECTION=apvoucherinsert_ivc");
	   		osw.write("&Type=AP_IVC");
	   		osw.flush();
	   		osw.close();
	   		Date startDate = new Date();
	
	   		inputStream = connection.getInputStream();
	   		BufferedReader iData = new BufferedReader(new InputStreamReader(inputStream));
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
	    	System.out.println("InvoiceSendToCostpoint Raw Response: " + s_input);

		    Date endDate = new Date();
		
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
	   		doc = db.parse(is) ;
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
	   		
	   		if (!"1".equals(severity)) { // severity other than 1 is an error
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
	   		scheduledTask.setTaskProcess(invoiceHeader.getInvoiceNumber());
	   		scheduledTask.setTaskStatus("" + getStatus()); // four digit task status
	   		dbs.add(scheduledTask);
	   		
	   		if (Status.FAILED == getStatus()) {
	   			Boolean batch = (Boolean) incomingRequest.get("batch");
	   			
	   			if (Boolean.TRUE.equals(batch)) {
	   				//send an email indicating a failed batch submission
	   				SendQueue sendQueue = new SendQueue();
	   				
	   				sendQueue.setAction(SendQueue.TEXT_EMAIL_ACTION);
	   				sendQueue.setDateadded(Dates.today("yyyy/MM/dd", ""));
	   				sendQueue.setDocic(invoiceHeader.getIcIvcHeader());
	   				sendQueue.setDoctype("IVC");
	   				sendQueue.setMessage("Failed Costpoint Submission: " + failedMsg);
	   				sendQueue.setSendfrom(PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("MAILEVENTS", "AdminEmailAddr", "support@puridiom.com"));
	   				sendQueue.setSendto(propertiesManager.getProperty("INVOICE", "APUSEREMAILADDRESS", ""));
	   				sendQueue.setStatus("00");
	   				sendQueue.setSubject("Failed Costpoint Submission for Order " + invoiceHeader.getPoNumber());
	   				sendQueue.setTimeadded(Dates.getNow(null, ""));
	   				
	   				dbs.add(sendQueue);
	   			}
	   			
	   			throw new Exception("Failed Costpoint Submission: " + failedMsg);
	   		} else {
	   			invoiceHeader.setDateExported(new Date());
		   		invoiceHeader.setApBatchId("" + System.currentTimeMillis());
		   		
		   		dbs.update(invoiceHeader);
	   		}
        }
	        
	    return null;
	}
}
