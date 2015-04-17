package com.tsa.puridiom.invoiceheader.tasks;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class InvoiceHeaderHistoryLogIp extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession");
		List invoices = (List)incomingRequest.get("invoiceHeaderList");
		List result = new ArrayList();
		SimpleDateFormat formatTex = new SimpleDateFormat("yyyy/MM/dd");
		if(invoices != null){
			for(int x = 0; x<invoices.size(); x++){
				InvoiceHeader invoice = (InvoiceHeader)invoices.get(x);
				String queryString = "from HistoryLog as hst where hst.icHeader = ? and hst.doctype = 'IVH' order by hst.icHistory ASC";
				BigDecimal icHeader = invoice.getIcIvcHeader();
				List historiesLog  = dbs.query(queryString, new Object[] {icHeader} , new Type[] {Hibernate.BIG_DECIMAL}) ;
	
				for(int y=0; y < historiesLog.size(); y++){
					HistoryLog historyLog = (HistoryLog)historiesLog.get(y);
					Date fecLog = formatTex.parse(historyLog.getLogDate());
					InvoiceHeader newInvoice = new InvoiceHeader();
					newInvoice.setIcIvcHeader(historyLog.getIcHeader());
					newInvoice.setDateEntered(fecLog);
					newInvoice.setEnteredBy(historyLog.getUserid());
					newInvoice.setInvoiceNumber(invoice.getInvoiceNumber());
					newInvoice.setInvoiceTotal(invoice.getInvoiceTotal());
					newInvoice.setStatus(historyLog.getStatus());
					newInvoice.setIp(historyLog.getIpAddress());
					result.add(newInvoice);
				}
			}
		}
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
