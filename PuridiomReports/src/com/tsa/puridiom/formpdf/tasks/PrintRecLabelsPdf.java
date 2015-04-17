/*
 * Created on Nov 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formpdf.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.ReceiptType;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.InspectionHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.StdTable;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsa.puridiom.stdtable.tasks.StdTableRetrieveById;
import com.tsa.puridiom.userprofile.tasks.UserProfileRetrieveBy;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


/**
 * @author Jael
 */
@SuppressWarnings(value = { "unchecked" })
public class PrintRecLabelsPdf extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
        	Map incomingRequest = (Map)object;
        	String organizationId = (String)incomingRequest.get("organizationId");
            DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
            ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
            PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");

            List receiptLineList = receiptHeader.getReceiptLineList();
            for (int i = 0; i < receiptLineList.size(); i++) {
				ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);
				PoLine poLine = receiptLine.getPoLine();
				List inspectionList = poLine.getInspectionList();
				if(inspectionList.size() == 0){
					inspectionList.add(new InspectionHeader());
				}
			}
            
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
            
            // Account
            List accountList = (List)incomingRequest.get("accountList");
            Account account = null;
            if(!accountList.isEmpty()){
            	account = (Account)accountList.get(0);
            } else {
            	account = new Account();
            }
            
            // RT#
            StdTableRetrieveById task = new StdTableRetrieveById();
            Map newIncomingRequest = new HashMap();
            newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
            newIncomingRequest.put("StdTable_tableType", "RQ10");
            String udf10Code = "";
            if (requisitionHeader != null) {
            	udf10Code = requisitionHeader.getUdf10Code();
            }
            newIncomingRequest.put("StdTable_tableKey", udf10Code);
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

            parameters.put("formType", "rec-labels-pdf.jasper");
            parameters.put("receiptHeader", receiptHeader);
            parameters.put("poHeader", poHeader);
            parameters.put("requisitionHeader", requisitionHeader);
            parameters.put("account", account);
            parameters.put("routingNumber", routingNumber);
            parameters.put("deliverTo", deliverTo);

            String webreport = (String)incomingRequest.get("webreport");
            if(Utility.isEmpty(webreport)){		webreport = "Y";	}
            parameters.put("webreport", webreport);

			parameters.put("namePdf", ReceiptType.toString(receiptHeader.getReceiptType(), organizationId) + "[" + receiptHeader.getReceiptNumber() + "].pdf");

            ret = JasperReportsHelper.printPdf(parameters);
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
