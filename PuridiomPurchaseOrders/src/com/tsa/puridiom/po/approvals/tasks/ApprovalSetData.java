package com.tsa.puridiom.po.approvals.tasks;

import com.tsa.puridiom.entity.*;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ApprovalSetData extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

        boolean listOk = true ;
        boolean hasLineRules  = false ;
        String 		appUdfSection[] = (String[]) incomingRequest.get("appUdfSection") ;
        String		appUdfColumn[] = (String[]) incomingRequest.get("appUdfColumn") ;
        String		appUdfType[] = (String[]) incomingRequest.get("appUdfType") ;
        String		appUdfName[] = (String[]) incomingRequest.get("appUdfName") ;
        String		value = null ;
        PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
        List	lineItemList = (List)incomingRequest.get("poLineList") ;
        BigDecimal	icHeader = poHeader.getIcPoHeader();
        BigDecimal	total = poHeader.getTotal();
		List accountItemList = (List)incomingRequest.get("accountList") ;
    	List headerList = new ArrayList() ;
    	List lineList = new ArrayList();
    	List accountList = new ArrayList();
    	List dataList = new ArrayList() ;
    	Map accountAllocationAmts = new HashMap();
    	
		try
		{
			List hRowList = new ArrayList();
	        for (int ix = 0;ix < appUdfSection.length ; ix++) {
	            if (appUdfSection[ix] == null) {
	                continue ;
	            }
	            String section = appUdfSection[ix] ;
	            if (section.equalsIgnoreCase("header") ) {
	            	// Header
	        		Class cls = poHeader.getClass() ;
	            	Method mth = cls.getMethod("get" + appUdfColumn[ix],null);
	            	Hashtable column = new Hashtable() ;
	            	column.put("section",appUdfSection[ix]) ;
	            	column.put("rulecolumn","fld" + (ix +1)) ;
					column.put("sectioncode","HDR") ;
	            	column.put("name",appUdfColumn[ix]);
					value = (String) mth.invoke(poHeader,null);
	            	column.put("value",Utility.ckNull(value).trim()) ;
	            	column.put("type",appUdfType[ix]);
	            	column.put("icheader",icHeader);
	            	column.put("icaccount",icHeader);
	            	column.put("label",appUdfName[ix]);
	            	column.put("amount", total);
	            	hRowList.add(column);
	            }
	        }

	        if (hRowList.size() > 0) {
		        headerList.add(hRowList);
	        }

	//        dataList.add(hRowList) ;

			for (int i = 0; i < lineItemList.size(); i++)
			{
				PoLine poLine = (PoLine) lineItemList.get(i);
				List lRowList = new ArrayList();
		        for (int ix = 0;ix < appUdfSection.length ; ix++) {
		            if (appUdfSection[ix] == null) {
		                continue ;
		            }
		            if (appUdfSection[ix].equalsIgnoreCase("line")) {
		        		Class cls = poLine.getClass() ;
		        		String st_Method = "";
		        		String nameUdf = "";
		        		if(appUdfColumn[ix].equalsIgnoreCase("CommodityCode"))
		        		{
		        			st_Method = "getCommodity";
		        			nameUdf = "Commodity";
		        		}
		        		else
		        		{
		        			st_Method = "get" + appUdfColumn[ix];
		        			nameUdf = appUdfColumn[ix];
		        		}
		        		Method mth = cls.getMethod(st_Method,null);
		        		
		            	Hashtable column = new Hashtable() ;
		            	column.put("section",appUdfSection[ix]) ;
		            	column.put("rulecolumn","fld" + (ix +1));
						column.put("sectioncode","LIN") ;

		            	column.put("name",nameUdf);
						value = (String) mth.invoke(poLine,null);
		            	column.put("value",Utility.ckNull(value).trim()) ;
		            	column.put("type",appUdfType[ix]);
		            	column.put("icheader",poLine.getIcPoHeader());
		            	column.put("icline",poLine.getIcPoLine()) ;
		            	column.put("icaccount",poLine.getIcAccount()) ;
		            	column.put("label",appUdfName[ix]);
		            	column.put("amount", poLine.getLineTotal());

		            	lRowList.add(column);
		            }
				}
		        if (lRowList.size() > 0) {
			        lineList.add(lRowList);
		        }
			}

			for (int i = 0; i < accountItemList.size(); i++)
			{
				Account rqa = (Account) accountItemList.get(i);
				List aRowList = new ArrayList();
				BigDecimal	icaccount = rqa.getComp_id().getIcLine();
				BigDecimal allocationAmt = new BigDecimal(0);
				if (accountAllocationAmts.containsKey(icaccount)) {
				    allocationAmt = (BigDecimal) accountAllocationAmts.get(icaccount);
				}
				allocationAmt  = allocationAmt.add(rqa.getAllocAmount());
				accountAllocationAmts.put(icaccount, allocationAmt);
				
		        for (int ix = 0;ix < appUdfSection.length ; ix++) {
		            if (appUdfSection[ix] == null) {
		                continue ;
		            }
		            if (appUdfSection[ix].equalsIgnoreCase("account")) {
		        		Class cls = rqa.getClass() ;
		            	Method mth = cls.getMethod("get" + appUdfColumn[ix],null);
		            	Hashtable column = new Hashtable() ;
		            	column.put("section",appUdfSection[ix]) ;
		            	column.put("rulecolumn","fld" + (ix +1));
						column.put("sectioncode","ACC") ;

		            	column.put("name",appUdfColumn[ix]);
						value = (String) mth.invoke(rqa,null);
		            	column.put("value",Utility.ckNull(value).trim()) ;
		            	column.put("type",appUdfType[ix]);
		            	column.put("icheader",rqa.getComp_id().getIcHeader()) ;
		            	column.put("icline",rqa.getComp_id().getIcLine());
		            	column.put("icaccount",icaccount);
		            	column.put("label",appUdfName[ix]);
		            	column.put("amount", rqa.getAllocAmount());
		            	column.put("allocPercent", rqa.getAllocPercent());
		            	aRowList.add(column);
		            }
				}
		        if (aRowList.size() > 0) {
		        	accountList.add(aRowList);
		        }
			}

			// Must be done in sequence
			if (headerList.size() > 0) {
				// Has Header
				dataList = headerList ;
			}
			if (lineList.size() > 0) {
				// Has Line Rules
				dataList = mergeToHeader(dataList,lineList) ;
				if (accountList.size() > 0) {
					dataList = mergeToLine(dataList,accountList) ;
				}
				if(dataList.size() < 1)
				{
					dataList = mergeToHeader(dataList,accountList) ;
				}
			} else {
				if (accountList.size() > 0) {
					dataList = mergeToHeader(dataList,accountList) ;
				}
			}

	        incomingRequest.put("dataList",dataList) ;
	        incomingRequest.put("accountAllocationAmts", accountAllocationAmts);

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return null ;
	}

	public List mergeToHeader(List l2, List l1) {
		List newList = new ArrayList() ;
		if (l2.size() > 0 ) {
			for (int ix = 0; ix < l1.size(); ix++) {
				List row1List = (List) l1.get(ix) ;
				for (int mx = 0; mx < l2.size(); mx++) {
					List row2List = (List) l2.get(mx) ;
					for (int cx = 0;cx < row2List.size(); cx++) {
						row1List.add(row2List.get(cx)) ;
					}
				}
			    newList.add(row1List) ;
			}
		} else {
			newList = l1 ;
		}

		return newList ;
	}

	public List mergeToLine(List l1, List l2) {

		ArrayList newList = new ArrayList() ;

		for (int lx = 0; lx < l1.size(); lx++) {
			List row1List = (List) l1.get(lx) ;
			Hashtable column = (Hashtable) row1List.get(0);
			BigDecimal	lineIc = (BigDecimal) column.get("icline") ;
			BigDecimal	icAccount = (BigDecimal) column.get("icaccount") ;

			for (int mx = 0; mx < l2.size(); mx++) {
				List row2List = (List) l2.get(mx) ;
				List newLineList = new ArrayList();
				boolean lineListAdded = false;
				for (int cx = 0;cx < row2List.size(); cx++) {
					Hashtable acolumn = (Hashtable) row2List.get(cx) ;
					if (acolumn != null) {
						BigDecimal icALine = (BigDecimal) acolumn.get("icline") ;
						BigDecimal icAAccount = (BigDecimal) acolumn.get("icaccount") ;
						if (icAccount.compareTo(icAAccount) == 0) {
						    if (!lineListAdded) {
						        newLineList.addAll(row1List);
						        lineListAdded = true;
						    }
						    newLineList.add(row2List.get(cx)) ;
						}
					}
				}
				if (newLineList.size() > 0) {
				    newList.add(newLineList) ;
				}
			}
		}

		return newList ;
	}
}
