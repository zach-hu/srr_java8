package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class PoValidateSupplierSpend extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

			BigDecimal totalSupplierSpend = new BigDecimal(0);

			String vendorId = "";
			String poNumber = "";
			if (poHeader != null)
			{
				vendorId = poHeader.getVendorId();
				poNumber = poHeader.getPoNumber();
			}

			int year = new Date().getYear() + 1900;

			String sql = "select sum(poHeader.total) from PoHeader as poHeader " +
				"where poHeader.vendorId = ? and poHeader.lastRevision = 'C' " +
				"and poHeader.status > ? and poHeader.status < ? " +
				"and (poHeader.poType = 'PO' or poHeader.poType = 'SO') " +
				"and poHeader.poNumber <> ? " +
				"and poHeader.poDate >= to_date('01-01-" + year + "','MM-dd-yyyy') " +
				"and poHeader.poDate <= to_date('12-31-" + year + "','MM-dd-yyyy') ";
			List resultList = dbs.query(sql, new Object[] {vendorId, DocumentStatus.PO_APPROVING, DocumentStatus.CANCELLED, poNumber}, new Type[] {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING});
			if (resultList != null && resultList.size() > 0)
			{
				BigDecimal sumTotal = (BigDecimal)resultList.get(0);
				if (sumTotal != null)
					totalSupplierSpend = sumTotal;
			}

			totalSupplierSpend = totalSupplierSpend.add(poHeader.getTotal());
			incomingRequest.put("totalSupplierSpend", totalSupplierSpend);
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}
