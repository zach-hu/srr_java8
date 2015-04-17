package com.tsa.puridiom.vendordocument.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VendorDocumentRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeaderString = (String) incomingRequest.get("VendorDocument_icRfqHeader");
			BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
			String vendorId = (String ) incomingRequest.get("VendorDocument_vendorId");
			String docIcString = (String) incomingRequest.get("VendorDocument_docIc");
			BigDecimal docIc = new BigDecimal ( docIcString );

			String queryString = "from VendorDocument as VendorDocument where VendorDocument.id.icRfqHeader = ? and VendorDocument.id.vendorId = ? and VendorDocument.id.docIc = ? ";
			List resultList = dbs.query(queryString, new Object[] {icRfqHeader, vendorId, docIc, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}