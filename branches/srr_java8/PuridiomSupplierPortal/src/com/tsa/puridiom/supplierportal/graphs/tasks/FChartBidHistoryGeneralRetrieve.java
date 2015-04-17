package com.tsa.puridiom.supplierportal.graphs.tasks;

import java.util.List;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.awt.image.* ;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;


import java.awt.Frame;
import javax.swing.filechooser.FileFilter;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.database.DBSession;

public class FChartBidHistoryGeneralRetrieve extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("RfqBidHistory_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
 	    	String queryString = "from RfqBidHistory RfqBidHistory where RfqBidHistory.id.icRfqHeader = ?";
 	    	result = dbs.query(queryString, new Object[] {icHeader} , new Type[] { Hibernate.BIG_DECIMAL});
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
	return result;
}
}