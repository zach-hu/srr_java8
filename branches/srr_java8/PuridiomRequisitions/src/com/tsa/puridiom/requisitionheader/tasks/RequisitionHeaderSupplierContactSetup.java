/*
 * Created on Feb 24, 2005
 */
package com.tsa.puridiom.requisitionheader.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.ContactPK;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class RequisitionHeaderSupplierContactSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String vendorId = (String) incomingRequest.get("RequisitionHeader_vendorId");
			String contactType = "DEFAULT";
			if (!HiltonUtility.isEmpty(vendorId))
			{
				String queryString = "from Contact as Contact where Contact.id.contactType = ? AND Contact.id.vendorId = ? ";
				List resultList = dbs.query(queryString, new Object[] {contactType, vendorId } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;

				if (resultList != null && resultList.size() > 0)
				{
					Contact contact = (Contact) resultList.get(0);
					ContactPK contactPK = contact.getComp_id();
					incomingRequest.put("RequisitionHeader_vendContactCode", contactPK.getContactCode());
					incomingRequest.put("RequisitionHeader_vendorAttn", contact.getDisplayName());
					incomingRequest.put("RequisitionHeader_contactPhone", contact.getPhoneNumber());
					incomingRequest.put("RequisitionHeader_contactMobilePhone", contact.getMobileNumber());
				}
			}
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}
}
