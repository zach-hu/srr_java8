/*
 * Created on Oct 27, 2005
 */
package com.tsa.puridiom.sungard.keyid.tasks;

import com.tsa.puridiom.entity.sungard.KeyId;
import com.tsa.puridiom.entity.sungard.KeyIdPK;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class GenerateInternalVendorId extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
			String	tableName = "VENDOR";
			String internalVendorId = null;
			List resultList = null;
			KeyId keyId = null;

			String queryString = "from KeyId as KeyId where KeyId.id.tableName = ? ";

			String sql = "update KEY_ID set LAST_CHECKED_TO = LAST_CHECKED_TO where TABLE_NAME = ?";
			Object [] args = new Object[1];
			Integer [] types = new Integer[1];
			args[0] = tableName;
			types[0] = Types.VARCHAR;
			int updstat = dbs.sqlUpdate(sql, args, types) ;
			dbs.getSession().flush() ;

				if (updstat == Status.SUCCEEDED) {
					resultList = dbs.query(queryString, new Object[] {tableName } , new Type[] { Hibernate.STRING }) ;

					if (resultList != null && resultList.size() > 0) {
					    keyId = (KeyId) resultList.get(0);
					}

					if (dbs.getStatus() == Status.SUCCEEDED && keyId != null) {
					    KeyIdPK pk = keyId.getComp_id();
					    pk.setKeyRangeLow(pk.getKeyRangeLow().add(new java.math.BigDecimal("1"))) ;
					    keyId.setComp_id(pk);
					    
					    internalVendorId = keyId.getComp_id().getKeyRangeLow().toString();
	
						if (dbs.getStatus() == Status.SUCCEEDED) {
							//dbs.update(keyId) ;
							sql = "update KEY_ID set KEY_RANGE_LOW = ? where TABLE_NAME = ?";
							args = new Object[2];
							types = new Integer[2];
							args[0] = internalVendorId;
							types[0] = Types.VARCHAR;
							args[1] = tableName;
							types[1] = Types.VARCHAR;
						    dbs.sqlUpdate(sql, args, types);
						}
					}

				result = internalVendorId;
			}

			if (result == null) {
				throw new Exception("No record exists for this table name (" + tableName + ").");
			}

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}
}
