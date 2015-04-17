/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.labels.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class LabelsRetrieveBy extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{

	        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
	        StringBuffer queryString = new StringBuffer("from Labels as labels where 1 = 1 ");
	        List argumentsList = new ArrayList();
	        List typesList = new ArrayList();
	        if(incomingRequest.containsKey("Labels_labelCode"))
	        {
	        	queryString.append(" and labels.labelCode = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_labelCode"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_moduleAccess"))
	        {
	        	queryString.append(" and labels.moduleAccess = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_moduleAccess"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_module"))
	        {
	        	queryString.append(" and labels.module = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_module"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_moduleType"))
	        {
	        	queryString.append(" and labels.moduleType = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_moduleType"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_language"))
	        {
	        	queryString.append(" and labels.language = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_language"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_labelValue"))
	        {
	        	queryString.append(" and labels.labelValue = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_labelValue"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_abbreviation"))
	        {
	        	queryString.append(" and labels.abbreviation = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_abbreviation"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_hoverHelp"))
	        {
	        	queryString.append(" and labels.hoverHelp = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_hoverHelp"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_visible"))
	        {
	        	queryString.append(" and labels.visible = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_visible"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_required"))
	        {
	        	queryString.append(" and labels.required = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_required"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_validation"))
	        {
	        	queryString.append(" and labels.validation = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_validation"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_validationSeverity"))
	        {
	        	queryString.append(" and labels.validationSeverity = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_validationSeverity"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_validationMessage"))
	        {
	        	queryString.append(" and labels.validationMessage = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_validationMessage"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_lookupTable"))
	        {
	        	queryString.append(" and labels.lookupTable = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_lookupTable"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_udf1Code"))
	        {
	        	queryString.append(" and labels.udf1Code = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_udf1Code"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_udf2Code"))
	        {
	        	queryString.append(" and labels.udf2Code = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_udf2Code"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_udf3Code"))
	        {
	        	queryString.append(" and labels.udf3Code = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_udf3Code"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_udf4Code"))
	        {
	        	queryString.append(" and labels.udf4Code = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_udf4Code"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_udf5Code"))
	        {
	        	queryString.append(" and labels.udf5Code = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_udf5Code"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_status"))
	        {
	        	queryString.append(" and labels.status = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_status"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_owner"))
	        {
	        	queryString.append(" and labels.owner = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_owner"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_lastChangeBy"))
	        {
	        	queryString.append(" and labels.lastChangeBy = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_lastChangeBy"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_lastChangeDate"))
	        {
	        	queryString.append(" and labels.lastChangeDate = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_lastChangeDate"));
	        	typesList.add(Hibernate.DATE);
	        }
	        if(incomingRequest.containsKey("Labels_allowEdit"))
	        {
	        	queryString.append(" and labels.allowEdit = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_allowEdit"));
	        	typesList.add(Hibernate.STRING);
	        }
	        if(incomingRequest.containsKey("Labels_readOnly"))
	        {
	        	queryString.append(" and labels.readOnly = ? ");
	        	argumentsList.add(incomingRequest.get("Labels_readOnly"));
	        	typesList.add(Hibernate.STRING);
	        }
	        Type hiberTypes[] = (Type[])typesList.toArray(new Type[typesList.size()]);


	        List resultList = dbs.query(queryString.toString(), argumentsList.toArray() , hiberTypes) ;
	        if (resultList != null && resultList.size() > 0)
			{
				result = resultList;
			}

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			e.printStackTrace();
		}

		return result ;
	}
}
