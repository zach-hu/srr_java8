package com.tsagate.foundation.database;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;

public class AuditInterceptor extends EmptyInterceptor
{
	public Set entities = new HashSet();
	private String organizationId;
	private String userId;

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int[] findDirty(Object arg0, Serializable arg1, Object[] arg2,
			Object[] arg3, String[] arg4, Type[] arg5) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object instantiate(Class arg0, Serializable arg1)
			throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean isUnsaved(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onDelete(Object entity, Serializable arg1, Object[] arg2,
			String[] arg3, Type[] arg4) throws CallbackException {
		if (entity instanceof IEntity) {
			this.entities.add(entity);
		}
	}

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types)
	{
		if (entity instanceof IEntity) {
			this.entities.add(entity);
		}

		return false;
	}

	public boolean onLoad(Object arg0, Serializable arg1, Object[] arg2,
			String[] arg3, Type[] arg4) throws CallbackException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onSave(Object entity, Serializable arg1, Object[] arg2,
			String[] arg3, Type[] arg4) throws CallbackException {
		if(entity.getClass().getName().indexOf("AuditRecord") < 0)
		{
			if (entity instanceof IEntity) {
				this.entities.add(entity);
			}
		}
		return false;
	}

	public void postFlush(Iterator arg0) throws CallbackException
	{
		/*for(Iterator it = this.inserts.iterator(); it.hasNext();)
		{
			AuditLog.logEvent("create", it.next(), this.getUserId(), this.getOrganizationId());
		}
		for(Iterator it = this.updates.iterator(); it.hasNext();)
		{
			AuditLog.logEvent("create", it.next(), this.getUserId(), this.getOrganizationId());
		}*/

	}

	public void preFlush(Iterator arg0) throws CallbackException {

	}

}
