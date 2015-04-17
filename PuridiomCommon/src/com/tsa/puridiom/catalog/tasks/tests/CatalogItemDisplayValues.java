/*
 * Created on Oct 10, 2003 
 */
package com.tsa.puridiom.catalog.tasks.tests;

import java.util.Iterator;
import java.util.List;

import com.tsa.puridiom.entity.CatalogItem;
import com.tsa.puridiom.entity.CatalogItemPK;

/**
 * @author renzo 
 */
public class CatalogItemDisplayValues
{
	public static void display(List qry)
	{
		StringBuffer data = new StringBuffer("") ;
		for (Iterator it = qry.iterator(); it.hasNext(); ) 
	    {
		   data.setLength(0) ;
    
		   CatalogItem ad = (CatalogItem) it.next() ;
		   CatalogItemPK pk = ad.getComp_id();
		   data.append(pk.getCatalogId() + "\u0009") ;
		   data.append(pk.getItemNumber() + "\u0009") ;
		   data.append(ad.getOwner() + "\u0009") ;
	
		   System.out.println(data.toString()) ;	            	
	   }
	}
}
