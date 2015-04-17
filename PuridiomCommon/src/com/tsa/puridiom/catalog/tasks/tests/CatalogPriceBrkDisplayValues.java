/*
 * Created on Oct 13, 2003 
 */
package com.tsa.puridiom.catalog.tasks.tests;

import java.util.Iterator;
import java.util.List;

import com.tsa.puridiom.entity.CatalogPriceBrk;
import com.tsa.puridiom.entity.CatalogPriceBrkPK;

/**
 * @author renzo 
 */
public class CatalogPriceBrkDisplayValues
{
	public static void display(List qry)
	{
		StringBuffer data = new StringBuffer("") ;
		for (Iterator it = qry.iterator(); it.hasNext(); ) 
		{
		   data.setLength(0) ;

		   CatalogPriceBrk ad = (CatalogPriceBrk) it.next() ;
		   CatalogPriceBrkPK pk = ad.getComp_id();
		   data.append(pk.getCatalogId() + "\u0009") ;
		   data.append(pk.getItemNumber() + "\u0009") ;
		   data.append(pk.getSequence() + "\u0009") ;
		   data.append(ad.getStatus() + "\u0009") ;

		   System.out.println(data.toString()) ;	            	
	   }
	}
}
