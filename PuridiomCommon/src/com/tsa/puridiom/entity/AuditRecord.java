package com.tsa.puridiom.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.tsa.puridiom.common.utility.HiltonUtility;

public class AuditRecord implements Serializable
{
	private BigDecimal transaction;
	private BigDecimal ic;
	private String entityClass;
	private String userId;
	private String mailId;
	private String ipAddress;
	private Date created;
	private String entity1;
	private String entity2;
	private String entity3;
	private String entity4;
	private String entity5;

	public AuditRecord(){}
	public AuditRecord(BigDecimal ic, BigDecimal transaction, String entityClass, Date created, String userId, String mailId, String ipAddress, String entity1, String entity2, String entity3, String entity4, String entity5)
	{
		this.ic = ic;
		this.transaction = transaction;
		this.entityClass = entityClass;
		this.created = created;
		this.userId = userId;
		this.mailId = mailId;
		this.ipAddress = ipAddress;
		this.entity1 = entity1;
		this.entity2 = entity2;
		this.entity3 = entity3;
		this.entity4 = entity4;
		this.entity5 = entity5;
	}

	public BigDecimal getIc() {
		return ic;
	}
	public void setIc(BigDecimal ic) {
		this.ic = ic;
	}
	public String getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

	public String getEntity1() {
		return (java.lang.String)HiltonUtility.ckNull(this.entity1).trim();
	}

	public void setEntity1(String _entity1) {
		if (!HiltonUtility.isEmpty(_entity1) && _entity1.length() > 2000) {
			_entity1 = _entity1.substring(0, 2000);
		}
		this.entity1 = _entity1;
	}

	public String getEntity2() {
		return (java.lang.String)HiltonUtility.ckNull(this.entity2).trim();
	}

	public void setEntity2(String _entity2) {
		if (!HiltonUtility.isEmpty(_entity2) && _entity2.length() > 2000) {
			_entity2 = _entity2.substring(0, 2000);
		}
		this.entity2 = _entity2;
	}
	public String getEntity3() {
		return (java.lang.String)HiltonUtility.ckNull(this.entity3).trim();
	}

	public void setEntity3(String _entity) {
		if (!HiltonUtility.isEmpty(_entity) && _entity.length() > 2000) {
			_entity = _entity.substring(0, 2000);
		}
		this.entity3 = _entity;
	}
	public String getEntity4() {
		return (java.lang.String)HiltonUtility.ckNull(this.entity4).trim();
	}

	public void setEntity4(String _entity) {
		if (!HiltonUtility.isEmpty(_entity) && _entity.length() > 2000) {
			_entity = _entity.substring(0, 2000);
		}
		this.entity4 = _entity;
	}
	public String getEntity5() {
		return (java.lang.String)HiltonUtility.ckNull(this.entity5).trim();
	}

	public void setEntity5(String _entity) {
		if (!HiltonUtility.isEmpty(_entity) && _entity.length() > 2000) {
			_entity = _entity.substring(0, 2000);
		}
		this.entity5 = _entity;
	}

	public void setEntity(String entity)
	{
		if(!HiltonUtility.isEmpty(entity))
		{
			if (entity.length() > 2000)
			{
				this.entity1 = entity.substring(0, 2000);
				if(entity.length() > 4000)
				{
					this.entity2 = entity.substring(2000, 4000);
				}
				else
				{
					this.entity2 = entity.substring(2000);
				}
			}
			else
			{
				this.entity1 = entity.substring(0);
			}
		}
	}

	public String getEntity()
	{
		StringBuffer msgSB = new StringBuffer(this.getEntity1());
		msgSB.append(this.getEntity2());
		msgSB.append(this.getEntity3());
		msgSB.append(this.getEntity4());
		msgSB.append(this.getEntity5());

		return msgSB.toString().trim();
	}

	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public BigDecimal getTransaction() {
		return transaction;
	}
	public void setTransaction(BigDecimal transaction) {
		this.transaction = transaction;
	}
}
