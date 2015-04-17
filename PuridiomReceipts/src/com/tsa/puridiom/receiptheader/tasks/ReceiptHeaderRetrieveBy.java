package com.tsa.puridiom.receiptheader.tasks;

import com.tsagate.foundation.database.DBSession;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class ReceiptHeaderRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null ;
		try {
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			StringBuffer queryString = new StringBuffer("from ReceiptHeader as receiptheader where 1=1 ");
			List args = new ArrayList();
			List<Type> types = new ArrayList<Type>();
			
			if(incomingRequest.containsKey("ReceiptHeader_icRecHeader"))
			{
				String icRecHeader = (String) incomingRequest.get("ReceiptHeader_icRecHeader");
				args.add(icRecHeader);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.icRecHeader = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_icPoHeader"))
			{
				String icPoHeader = (String) incomingRequest.get("ReceiptHeader_icPoHeader");
				args.add(icPoHeader);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.icPoHeader = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_receiptDate"))
			{
				String receiptDate = (String) incomingRequest.get("ReceiptHeader_receiptDate");
				args.add(receiptDate);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.receiptDate = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_receiptType"))
			{
				String receiptType = (String) incomingRequest.get("ReceiptHeader_receiptType");
				args.add(receiptType);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.receiptType = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_receivedBy"))
			{
				String receivedBy = (String) incomingRequest.get("ReceiptHeader_receivedBy");
				args.add(receivedBy);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.receivedBy = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_carrierCode"))
			{
				String carrierCode = (String) incomingRequest.get("ReceiptHeader_carrierCode");
				args.add(carrierCode);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.carrierCode = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_packingSlip"))
			{
				String packingSlip = (String) incomingRequest.get("ReceiptHeader_packingSlip");
				args.add(packingSlip);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.packingSlip = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("ReceiptHeader_vendorId");
				args.add(vendorId);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.vendorId = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_owner"))
			{
				String owner = (String) incomingRequest.get("ReceiptHeader_owner");
				args.add(owner);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.owner = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_receiptStatus"))
			{
				String receiptStatus = (String) incomingRequest.get("ReceiptHeader_receiptStatus");
				args.add(receiptStatus);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.receiptStatus = ?");
			}			
			if(incomingRequest.containsKey("ReceiptHeader_refNumber"))
			{
				String refNumber = (String) incomingRequest.get("ReceiptHeader_refNumber");
				args.add(refNumber);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.refNumber = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_refRelease"))
			{
				String refRelease = (String) incomingRequest.get("ReceiptHeader_refRelease");
				args.add(refRelease);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.refRelease = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_refDate"))
			{
				String refDate = (String) incomingRequest.get("ReceiptHeader_refDate");
				args.add(refDate);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.refDate = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_refType"))
			{
				String refType = (String) incomingRequest.get("ReceiptHeader_refType");
				args.add(refType);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.refType = '" + refType + "'");
			}
			if(incomingRequest.containsKey("ReceiptHeader_vendorName"))
			{
				String vendorName = (String) incomingRequest.get("ReceiptHeader_vendorName");
				args.add(vendorName);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.vendorName = ?");
			}			
			if(incomingRequest.containsKey("ReceiptHeader_receiptNumber"))
			{
				String receiptNumber = (String) incomingRequest.get("ReceiptHeader_receiptNumber");
				args.add(receiptNumber);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.receiptNumber = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_fiscalYear"))
			{
				String fiscalYear = (String) incomingRequest.get("ReceiptHeader_fiscalYear");
				args.add(fiscalYear);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.fiscalYear = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_forwardTo"))
			{
				String forwardTo = (String) incomingRequest.get("ReceiptHeader_forwardTo");
				args.add(forwardTo);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.forwardTo = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_pkgsReceived"))
			{
				String pkgsReceived = (String) incomingRequest.get("ReceiptHeader_pkgsReceived");
				args.add(pkgsReceived);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.pkgsReceived = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_receiptNotes"))
			{
				String receiptNotes = (String) incomingRequest.get("ReceiptLine_receiptNotes");
				args.add(receiptNotes);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.receiptNotes = ?");
			}			
			if(incomingRequest.containsKey("ReceiptHeader_releaseNumber"))
			{
				String releaseNumber = (String) incomingRequest.get("ReceiptHeader_releaseNumber");
				args.add(releaseNumber);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.releaseNumber = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_returnDate"))
			{
				String returnDate = (String) incomingRequest.get("ReceiptHeader_returnDate");
				args.add(returnDate);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.returnDate = ?");
			}
			if(incomingRequest.containsKey("ReceiptHeader_tempIc"))
			{
				String tempIc = (String) incomingRequest.get("ReceiptHeader_tempIc");
				args.add(tempIc);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptheader.tempIc = ?");
			}
			result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()]));
			this.setStatus(dbs.getStatus()) ;
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result ;
	}
}
