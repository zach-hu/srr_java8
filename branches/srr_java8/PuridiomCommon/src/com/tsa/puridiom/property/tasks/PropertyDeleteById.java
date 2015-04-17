/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.property.tasks;

import com.tsa.puridiom.entity.Property;
import com.tsa.puridiom.entity.PropertyPK;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.*;
/**
 * @author Administrator
 */
public class PropertyDeleteById extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		String section = (String) incomingRequest.get("section");
		String property = (String) incomingRequest.get("property");
		
		PropertyPK propPK = new PropertyPK() ;
	    propPK.setSection(section);
	    propPK.setProperty(property) ;
		Property prop = new Property(propPK) ;
	    
		dbs.delete(prop) ;
		this.setStatus(dbs.getStatus());
		return prop;
	}
}
