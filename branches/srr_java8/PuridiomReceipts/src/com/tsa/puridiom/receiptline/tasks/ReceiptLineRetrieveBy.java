package com.tsa.puridiom.receiptline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class ReceiptLineRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null ;
		try {
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			StringBuffer queryString = new StringBuffer("from ReceiptLine as receiptline where 1=1 ");
			List args = new ArrayList();
			List<Type> types = new ArrayList<Type>();
			
			if(incomingRequest.containsKey("ReceiptLine_icRecLine"))
			{
				String icRecLine = (String) incomingRequest.get("ReceiptLine_icRecLine");
				args.add(icRecLine);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.id.icRecLine = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_icRecHeader"))
			{
				String icRecHeader = (String) incomingRequest.get("ReceiptLine_icRecHeader");
				args.add(icRecHeader);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.id.icRecHeader = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_receiptLine"))
			{
				String receiptLine = (String) incomingRequest.get("ReceiptLine_receiptLine");
				args.add(receiptLine);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.id.receiptLine = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_icPoLine"))
			{
				String icPoLine = (String) incomingRequest.get("ReceiptLine_icPoLine");
				args.add(icPoLine);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.icPoLine = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_receiptDate"))
			{
				String receiptDate = (String) incomingRequest.get("ReceiptLine_receiptDate");
				args.add(receiptDate);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.receiptDate = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_receiptNumber"))
			{
				String receiptNumber = (String) incomingRequest.get("ReceiptLine_receiptNumber");
				args.add(receiptNumber);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.receiptNumber = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_packingSlip"))
			{
				String packingSlip = (String) incomingRequest.get("ReceiptLine_packingSlip");
				args.add(packingSlip);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.packingSlip = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_lotNumber"))
			{
				String lotNumber = (String) incomingRequest.get("ReceiptLine_lotNumber");
				args.add(lotNumber);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.lotNumber = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_receivedBy"))
			{
				String receivedBy = (String) incomingRequest.get("ReceiptLine_receivedBy");
				args.add(receivedBy);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.receivedBy = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_qtyReceived"))
			{
				String qtyReceived = (String) incomingRequest.get("ReceiptLine_qtyReceived");
				args.add(qtyReceived);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.qtyReceived = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_qtyReturned"))
			{
				String qtyReturned = (String) incomingRequest.get("ReceiptLine_qtyReturned");
				args.add(qtyReturned);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.qtyReturned = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_inspectionCode"))
			{
				String inspectionCode = (String) incomingRequest.get("ReceiptLine_inspectionCode");
				args.add(inspectionCode);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.inspectionCode = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_status"))
			{
				String status = (String) incomingRequest.get("ReceiptLine_status");
				args.add(status);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.status = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_icPoHeader"))
			{
				String icPoHeader = (String) incomingRequest.get("ReceiptLine_icPoHeader");
				args.add(icPoHeader);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.icPoHeader = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_qtyRejected"))
			{
				String qtyRejected = (String) incomingRequest.get("ReceiptLine_qtyRejected");
				args.add(qtyRejected);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.qtyRejected = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_convFactor"))
			{
				String convFactor = (String) incomingRequest.get("ReceiptLine_convFactor");
				args.add(convFactor);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.convFactor = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_udf1Code"))
			{
				String udf1Code = (String) incomingRequest.get("ReceiptLine_udf1Code");
				args.add(udf1Code);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.udf1Code = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_udf2Code"))
			{
				String udf2Code = (String) incomingRequest.get("ReceiptLine_udf2Code");
				args.add(udf2Code);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.udf2Code = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_udf3Code"))
			{
				String udf3Code = (String) incomingRequest.get("ReceiptLine_udf3Code");
				args.add(udf3Code);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.udf3Code = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_udf4Code"))
			{
				String udf4Code = (String) incomingRequest.get("ReceiptLine_udf4Code");
				args.add(udf4Code);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.udf4Code = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_udf5Code"))
			{
				String udf5Code = (String) incomingRequest.get("ReceiptLine_udf5Code");
				args.add(udf5Code);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.udf5Code = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_carrierCode"))
			{
				String carrierCode = (String) incomingRequest.get("ReceiptLine_carrierCode");
				args.add(carrierCode);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.carrierCode = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_linComflag"))
			{
				String linComflag = (String) incomingRequest.get("ReceiptLine_linComflag");
				args.add(linComflag);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.linComflag = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_receiptType"))
			{
				String receiptType = (String) incomingRequest.get("ReceiptLine_receiptType");
				args.add(receiptType);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.receiptType = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_apBatchid"))
			{
				String apBatchid = (String) incomingRequest.get("ReceiptLine_apBatchid");
				args.add(apBatchid);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.apBatchid = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_receiptNotes"))
			{
				String receiptNotes = (String) incomingRequest.get("ReceiptLine_receiptNotes");
				args.add(receiptNotes);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.receiptNotes = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_releaseNumber"))
			{
				String releaseNumber = (String) incomingRequest.get("ReceiptLine_releaseNumber");
				args.add(releaseNumber);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.releaseNumber = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_rejectionCode"))
			{
				String rejectionCode = (String) incomingRequest.get("ReceiptLine_rejectionCode");
				args.add(rejectionCode);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.rejectionCode = ?");
			}
			if(incomingRequest.containsKey("ReceiptLine_dispositionCode"))
			{
				String dispositionCode = (String) incomingRequest.get("ReceiptLine_dispositionCode");
				args.add(dispositionCode);
				types.add(Hibernate.STRING);
				queryString.append(" AND receiptline.dispositionCode = ?");
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
