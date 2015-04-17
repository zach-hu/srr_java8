/*
 * Created on Dec 28, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisbLineAllQtyDisbursed extends Task
{
    /**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			String sql = "SELECT sum(disbLine.quantity) FROM DisbLine disbLine WHERE disbLine.icReqLine = ? AND  disbLine.status = '" +
					DocumentStatus.INV_DISBURSED + "'";
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String ic = (String)incomingRequest.get("RequisitionLine_icReqLine");
			BigDecimal icReqLine = new BigDecimal(ic);
			List all = dbs.query(sql, new Object[] {icReqLine} , new Type[] { Hibernate.BIG_DECIMAL}) ;
			
			if(all != null)
			{
			    ret = all.get(0);
			}
			
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
