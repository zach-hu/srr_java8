package com.tsa.puridiom.formpdf.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import org.jfree.util.Log;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Richard Saraza
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class PoLineLabelPdfSetup extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		try{
			String queryString = "";

			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			String msrNumber = "";

			BigDecimal b_icReqLine = new BigDecimal(-1);
			BigDecimal b_icMsrLine = new BigDecimal(-1);
			String udf2Code = "";
			String markCode = "";
			String statusRI = "";
			String routeNumber = "";
			String markCodeExits = "N";
			
			if (poHeader != null && poHeader.getPoLineList() != null && poHeader.getPoLineList().size() > 0) {

				PoLine line = ((PoLine) poHeader.getPoLineList().get(0));

				b_icReqLine = line.getIcReqLine();
				b_icMsrLine = line.getIcLineHistory();
				udf2Code = line.getUdf2Code();
				markCode = line.getAsset();
				

				incomingRequest.put("poLineLabel", line);

				if ("VINIMAYA".equalsIgnoreCase(line.getCatalogId())) {
					incomingRequest.put("isCatalogVinimaya", "Y");


					List resultList = null;

					if (b_icReqLine != null) {
						queryString = "from RequisitionLine as b where b.icReqLine = ?";
						resultList = dbs.query(queryString,	new Object[] { b_icReqLine }, new Type[] { Hibernate.BIG_DECIMAL });

						if (resultList != null && resultList.size() > 0) {
							incomingRequest.put("reqLineLabel", resultList.get(0));
							msrNumber = ((RequisitionLine) resultList.get(0)).getMsrNumber();
						}
					}


					if (b_icMsrLine != null) {
						queryString = "from RequisitionLine as b where b.reqType = 'M' AND b.icLineHistory = ?";

						resultList = dbs.query(queryString,	new Object[] { b_icMsrLine }, new Type[] { Hibernate.BIG_DECIMAL });

						if (resultList != null && resultList.size() > 0) {
							incomingRequest.put("msrLine", resultList.get(0));
						}
						else
						{
							queryString = "from RequisitionLine as b where b.requisitionNumber = ?";

							resultList = dbs.query(queryString, new Object[] { msrNumber }, new Type[] { Hibernate.STRING });

							if (resultList != null && resultList.size() > 0) {
								incomingRequest.put("msrLine", resultList.get(0));
							}
						}
					}
					// setup MSR Header
					if(!HiltonUtility.isEmpty(msrNumber))
					{
						queryString = "from RequisitionHeader as h where h.requisitionNumber = ?";

						resultList = dbs.query(queryString,new Object[] { msrNumber },new Type[] { Hibernate.STRING });

						if (resultList != null && resultList.size() > 0) {
							incomingRequest.put("msrHeader", resultList.get(0));
						}
					}
					/// setup ruoteNumber and statusRI

					String kitMsr = "";
					String udf10codeMsr = "";
					String itemLocationMsr = "";

					queryString = "Select kit, udf10Code, itemLocation From RequisitionHeader where requisitionNumber = '" + msrNumber + "'";

					resultList = dbs.query(queryString);

					if(resultList != null && resultList.size() > 0)
					{
						Object[] arrayResult = (Object[])resultList.get(0);
						kitMsr = (String) arrayResult[0];
						udf10codeMsr = (String) arrayResult[1];
						itemLocationMsr = (String) arrayResult[2];
					}
					
					if(!HiltonUtility.isEmpty(markCode))
					{
						queryString = "from StdTable as stdtable where stdtable.id.tableType = 'ASSET' and stdtable.id.tableKey = '" + markCode + "'";

						resultList = dbs.query(queryString);
						
						if (resultList != null && resultList.size() > 0) {
							markCodeExits="Y";
						}
					}
					
					

					if(!udf2Code.equalsIgnoreCase("LEVEL_3")) {
						
						statusRI = "RI"; 
						
					} else if (markCodeExits.equalsIgnoreCase("Y")){
						
						queryString = "Select stdtable.description from StdTable as stdtable where stdtable.id.tableType = 'ASSET' and stdtable.id.tableKey = '" + markCode + "'";

						resultList = dbs.query(queryString);
						
						if (resultList != null && resultList.size() > 0) {
							statusRI = (String) resultList.get(0);
						}						
						
					} else if (kitMsr.equalsIgnoreCase("Y")){
						
						statusRI = "ACC"; 
						if(!HiltonUtility.isEmpty(itemLocationMsr)){
							queryString = "Select stdtable.udf1Code from StdTable as stdtable where stdtable.id.tableType = 'RQ10' and stdtable.id.tableKey = '" + itemLocationMsr + "'";
						} else {
							queryString  = "Select stdtable.udf1Code from StdTable as stdtable where stdtable.id.tableType = 'RQ10' and stdtable.id.tableKey = '" + udf10codeMsr + "'";
						}

						resultList = dbs.query(queryString);
						if(resultList != null && resultList.size() > 0)
						{
							routeNumber = (String) resultList.get(0);
						}
						
					} else {
						
						statusRI = "ACC"; 
						queryString  = "Select stdtable.udf1Code from StdTable as stdtable where stdtable.id.tableType = 'RQ10' and stdtable.id.tableKey = '" + udf10codeMsr + "'";

						resultList = dbs.query(queryString);
						if(resultList != null && resultList.size() > 0)
						{
							routeNumber = (String) resultList.get(0);
						}
						
					}

				} else {
					incomingRequest.put("isCatalogVinimaya", "N");
				}
			}

			incomingRequest.put("statusRI", statusRI);
			incomingRequest.put("routeNumber", routeNumber);

			this.setStatus(dbs.getStatus()) ;

		}
		catch (Exception e) 
		{
			Log.error(this, e);
			this.setStatus(Status.FAILED) ;
			throw new TsaException();
		}
		
		return result ;
	}
}
