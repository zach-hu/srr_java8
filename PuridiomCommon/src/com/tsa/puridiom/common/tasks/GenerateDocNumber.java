/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.common.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.AutoGen;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Administrator
 */
public class GenerateDocNumber extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		BigDecimal result = null;

		try {
			DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
			String documentType = (String)incomingRequest.get("AutoGen_documentType");
			String type = HiltonUtility.ckNull((String)incomingRequest.get("AutoGen_Type"));
			String genYear = (String)incomingRequest.get("AutoGen_genYear") ;
			String	o = (String) incomingRequest.get("organizationId");
			PropertiesManager properties = PropertiesManager.getInstance(o);
			String	enableFormatType = properties.getProperty("AUTONUMBER", "AUTOREQ" + type, "N").toUpperCase();
			List resultList = null ;
			AutoGen autoGen = null;

			String queryString = "from AutoGen as AutoGen where AutoGen.id.documentType = ? and AutoGen.id.genYear = ? ";
			type = documentType + type;
			resultList = dbs.query(queryString, new Object[] {type, genYear } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
			if (resultList != null && resultList.size() > 0 && enableFormatType.equalsIgnoreCase("Y")) {
				autoGen = (AutoGen) resultList.get(0);
				documentType = documentType + type;
			}
			
			if(autoGen == null)
			{
				resultList = dbs.query(queryString, new Object[] {documentType, genYear } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
				
				if (resultList != null && resultList.size() > 0) {
					autoGen = (AutoGen) resultList.get(0);
				}
			}
			
			dbs.lock(autoGen);
			dbs.refresh(autoGen);
				
			result = autoGen.getNextNumber().add(BigDecimal.ONE);
			
			autoGen.setNextNumber(result);
			
			dbs.update(autoGen);
			
			dbs.getSession().flush();
			
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
    		Log.error(this, "Error Message: " + e.getMessage());
    		throw e;
		}

		return result.toString();
	}
}
