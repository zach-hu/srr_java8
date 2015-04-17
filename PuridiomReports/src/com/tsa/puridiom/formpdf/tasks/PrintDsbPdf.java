package com.tsa.puridiom.formpdf.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperReport;

import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.common.documents.DisbursementType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.jasperreports.JasperReportsHelper;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PrintDsbPdf extends Task
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			DisbHeader disbHeader = (DisbHeader) incomingRequest.get("disbHeader");
			String organizationId = (String) incomingRequest.get("organizationId");
			
			Map parameters = new HashMap();

			EntityDataSource ds = new EntityDataSource(disbHeader);
			EntityDataSource shiptoDS = new EntityDataSource(disbHeader.getShipToAddress());
			parameters.put("shipToDS", shiptoDS);
			
			EntityDataSource itemLocationDS = new EntityDataSource(disbHeader.getInventoryAddress());
			parameters.put("itemLocationDS", itemLocationDS);
								
			// EntityDataSource itemsDS = new EntityDataSource(poHeader.getl);
			// List itemsList = (List)incomingRequest.get("poLineList");
			// HibernateQueryResultDataSource itemDS = new
			// HibernateQueryResultDataSource(itemsList);
			// parameters.put("linesDS", itemDS);
			// JasperReportsHelper.fill("serviceBlanket1.jasper", parameters,
			// organizationId, ds);
			// JasperReportsHelper.exportToPdf("serviceBlanket1.jasper",
			// organizationId, parameters, ds, "serviceBlanket1.pdf");
			parameters.put("datasource", ds);
			parameters.put("organizationId", organizationId);
			parameters.put("oid", organizationId);
			parameters.put("entity", disbHeader);

			parameters.put("formType", "disbursement.jasper");
			HibernateQueryResultDataSource itemDS = new HibernateQueryResultDataSource(disbHeader.getDisbLineList());
			parameters.put("linesDS", itemDS);
			parameters.put("lines", disbHeader.getDisbLineList());
									
			//parameters.put("reqLines", lineItems);
			// EntityDataSource shiptoDS = new
			// EntityDataSource(disbHeader.getAccountList());
			// parameters.put("shipToDS", shiptoDS);
			// EntityDataSource vendorDS = new
			// EntityDataSource(reqHeader.getVendorAddress());
			// parameters.put("vendorDS", vendorDS);
			HibernateQueryResultDataSource accountsDS = new HibernateQueryResultDataSource(disbHeader.getAccountList());
			parameters.put("accountsDS", accountsDS);
			List routingList = (List) incomingRequest.get("routingList");
			if (routingList == null)
			{
				routingList = new ArrayList();
			}
			HibernateQueryResultDataSource routingListDS = new HibernateQueryResultDataSource(routingList);
			parameters.put("routingListDS", routingListDS);

			String webreport = (String) incomingRequest.get("webreport");
			if (Utility.isEmpty(webreport))
			{
				webreport = "Y";
			}
			parameters.put("webreport", webreport);

			List docCommentList = disbHeader.getDocCommentList();
			List commentsBeforeList = new ArrayList();
			List commentsAfterList = new ArrayList();
			for (int i = 0; i < docCommentList.size(); i++)
			{
				DocComment docComment = (DocComment) docCommentList.get(i);
				if (docComment.getCommentPlace().equals("B"))
				{
					commentsBeforeList.add(docComment);
				} else
				{
					commentsAfterList.add(docComment);
				}
			}
			HibernateQueryResultDataSource commentsBeforeDS = new HibernateQueryResultDataSource(commentsBeforeList);
			parameters.put("commentsBeforeDS", commentsBeforeDS);
			HibernateQueryResultDataSource commentsAfterDS = new HibernateQueryResultDataSource(commentsAfterList);
			parameters.put("commentsAfterDS", commentsAfterDS);
			parameters.put("namePdf", DisbursementType.toString(disbHeader.getDisbType(), organizationId) + "[" + disbHeader.getRequisitionNumber() + "].pdf");

			ret = JasperReportsHelper.printPdf(parameters);

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}
}
