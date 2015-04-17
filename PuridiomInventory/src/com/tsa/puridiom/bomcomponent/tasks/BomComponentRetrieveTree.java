package com.tsa.puridiom.bomcomponent.tasks;

import com.tsa.puridiom.entity.BomComponent;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Hashtable;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class BomComponentRetrieveTree extends Task
{
	DBSession	dbs = null ;
	List				treeList = new ArrayList() ;
	int				level = 0;

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		dbs = (DBSession)incomingRequest.get("dbsession") ;

		String itemNumber = (String) incomingRequest.get("InvItem_itemNumber");
		this.retrieve(itemNumber) ;

		this.setStatus(dbs.getStatus()) ;
		return treeList ;
	}

	private int retrieve(String item) {
		int 	i_error = 1 ;
		StringBuffer queryString = new StringBuffer("from BomComponent as BomComponent where ");
		queryString.append("BomComponent.parentItem = ? order by BomComponent.icComponent" );
		Object [] args = {item};
		Type [] types = {Hibernate.STRING};
		try {
			List result = dbs.query(queryString.toString(), args, types) ;
			for (int li_x = 0;li_x < result.size(); li_x++) {
				BomComponent bc = (BomComponent) result.get(li_x) ;
				Hashtable ht = new Hashtable() ;
				ht.put("level", Integer.toString(level)) ;
				ht.put("bomComponent", bc) ;
				treeList.add(ht) ;

				String itemNumber = bc.getComponentItem() ;
				if (itemNumber.compareTo(item) != 0) {
					level = level + 1 ;
					retrieve(itemNumber) ;
					level = level - 1 ;
				}
			}
		}
		catch (Exception e) {
			i_error = -1 ;
			e.printStackTrace() ;
		}

		return i_error ;
	}
}
