/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.ReceiptType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.StdTable;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.stdtable.tasks.StdTableRetrieveById;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsa.puridiom.userprofile.tasks.UserProfileRetrieveBy;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


/**
 * @author Jael
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
@SuppressWarnings(value = { "unchecked" })
public class PrintRecLineLabelPdf extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            String organizationId = (String)incomingRequest.get("organizationId");
            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
            String status = (String)incomingRequest.get("status") ;
            ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
            ReceiptLine receiptLine = (ReceiptLine)incomingRequest.get("receiptLine");
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            InspectionHeader inspectionHeader = (InspectionHeader)incomingRequest.get("inspectionHeader");
            RequisitionLine msrLine = (RequisitionLine)incomingRequest.get("msrLine");
            
            //Status
            
            if(status.equalsIgnoreCase(DocumentStatus.RCV_INPROGRESS) || status.equalsIgnoreCase(DocumentStatus.RCV_REJECTED)){
            	status = "";
            } else if(status.equalsIgnoreCase(DocumentStatus.RCV_STEP_2)){
            	status = "M&T";
            } else if(status.compareTo(DocumentStatus.RCV_STEP_3) >= 0 && (!status.equalsIgnoreCase("ACC") && !status.equalsIgnoreCase("UNN")) && !status.equalsIgnoreCase(DocumentStatus.RCV_REJECTED)){
            	status = "ACC";
            }
            
            // Users
            UserProfile userProfile = UserManager.getInstance().getUser(organizationId, receiptLine.getInspectorAssigned());
            
            UserProfileRetrieveBy userRetrieve = new UserProfileRetrieveBy();
            Map userIncomingRequest = new HashMap();
            String[] userName = poHeader.getShipToContact().split(" ");
            String firstName = "";
            String lastName = "";
            if (userName != null && userName.length > 0) {
            	firstName = userName[0];
            }
            if (userName != null && userName.length > 1) {
            	lastName = userName[1];
            }
            userIncomingRequest.put("dbsession", dbs);
            userIncomingRequest.put("UserProfile_organizationId", organizationId);
            userIncomingRequest.put("UserProfile_firstName", firstName);
            userIncomingRequest.put("UserProfile_lastName", lastName);
            
            UserProfile deliverTo = null;
            List userList = (List) userRetrieve.executeTask(userIncomingRequest);
            if(!userList.isEmpty()){
            	deliverTo = (UserProfile)userList.get(0);
            } else {
            	deliverTo = new UserProfile();
            }
            
            UserProfile received = UserManager.getInstance().getUser(organizationId, receiptHeader.getReceivedBy());
            
            // Account
            List accountList = (List)incomingRequest.get("accountList");
            Account account = null;
            if(!accountList.isEmpty()){
            	account = (Account)accountList.get(0);
            } else {
            	account = new Account();
            }
            
            //Number RI/GI
            String numberStatic = "05";
            Calendar calendar = Calendar.getInstance();
            if (inspectionHeader != null)
            	calendar.setTime(inspectionHeader.getDateEntered());
            String year = (new Integer(calendar.get(Calendar.YEAR))).toString();
            
            // RT#
            StdTableRetrieveById task = new StdTableRetrieveById();
            Map newIncomingRequest = new HashMap();
            newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
            newIncomingRequest.put("StdTable_tableType", "RQ10");
            String tableKey = "";
            if (requisitionHeader != null) {
	            if(requisitionHeader.getKit().equalsIgnoreCase("Y"))
	            {
		            tableKey = requisitionHeader.getItemLocation();
	            }
	            
	            if(HiltonUtility.isEmpty(tableKey))
	            {
	            	tableKey = requisitionHeader.getUdf10Code();
	            }
            }
            newIncomingRequest.put("StdTable_tableKey", tableKey);
            StdTable stdTable = (StdTable) task.executeTask(newIncomingRequest);
            String routingNumber = "";
            if(stdTable != null){
            	routingNumber = stdTable.getUdf1Code()+"/"+stdTable.getUdf2Code();
            }
            
            
            Map parameters = new HashMap();
            EntityDataSource ds = new EntityDataSource(receiptHeader);
            parameters.put("datasource", ds);
            parameters.put("organizationId", organizationId);
            parameters.put("oid", organizationId);
            parameters.put("entity", receiptHeader);

            parameters.put("formType", "rec-line-label-pdf.jasper");
            parameters.put("receiptHeader", receiptHeader);
            parameters.put("receiptLine", receiptLine);
            parameters.put("poHeader", poHeader);
            parameters.put("requisitionHeader", requisitionHeader);
            parameters.put("msrLine", msrLine);
            parameters.put("account", account);
            parameters.put("userProfile", userProfile);
            parameters.put("inspectionHeader", inspectionHeader);
            parameters.put("year", year);
            parameters.put("numberStatic", numberStatic);
            parameters.put("routingNumber", routingNumber);
            parameters.put("deliverTo", deliverTo);
            parameters.put("received", received);
            parameters.put("status", status);

            String webreport = (String)incomingRequest.get("webreport");
            if(Utility.isEmpty(webreport)){		webreport = "Y";	}
            parameters.put("webreport", webreport);

			parameters.put("namePdf", ReceiptType.toString(receiptHeader.getReceiptType(), organizationId) + "[" + receiptHeader.getReceiptNumber() + "].pdf");

            ret = JasperReportsHelper.printPdf(parameters);
            incomingRequest.put("returnFile", Boolean.TRUE);
            incomingRequest.put("returnfilename", ret);
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
    }
}
