/**
 * Created on Feb 17, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoErrors.java
 *
 */
package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.math.BigDecimal;
import java.util.Date;

public class PoErrors
{	
	/* Could not locate blanket info! */
	public static String NOBLANKET = "Could not locate blanket info!";
	/* Blanket Limit and Release Limit must be greater than zero */
	public static String NORELEASELIMIT = "Blanket Limit and Release Limit must be greater than zero!";
	/* "Inventory Items must be received normally!  All Inventory Items have been changed to Receipt Required." */
	public static String INVITEMSRECEIPT = "Inventory Items must be received normally!  All Inventory Items have been changed to Receipt Required.";
	public static int WARNING = -21;
	public static int NOBLANKETCODE = -5;
	public static int AMTOVERORDERCODE = -4;
	public static int AMTOVERRELEASECODE = -3;
	public static int BLANKETEXPIRED = -2;
	/* Critical Error */
	public static int CRITICAL = 0;
	/*User input required before Forward */
	public static int USERINPUT = 1;
	/*Forwaded sending a user notice */
	public static int NOTICE = 2;
	
	/**
	 * Total Release amount.... exceeds Blanket order limit ....
	 * @param releaseAmount
	 * @param blanketLimit
	 * @return
	 */
	public static String getAmtOverOrder(BigDecimal releaseAmount, BigDecimal blanketLimit, String oid)
	{
		String temp = "Total Release amount ( " + HiltonUtility.getFormattedDollar(releaseAmount, oid) + " ) exceeds Blanket Order limit ( " + HiltonUtility.getFormattedDollar(blanketLimit, oid) + " )!";
		return temp;
	}	
	
	public static String getAmtOverRelease(BigDecimal releaseAmount, BigDecimal blanketLimit, String oid)
	{
	    String temp = "Total Release amount ( " + HiltonUtility.getFormattedDollar(releaseAmount, oid) + " ) exceeds Blanket Release limit ( " + HiltonUtility.getFormattedDollar(blanketLimit, oid) + " )!";
		return temp;
	}
	
	public static String blanketOrderExpired(String poNumber, Date expdate, String oid)
	{
		String temp = "Blanket Order " + poNumber + " expired on " + HiltonUtility.getFormattedDate(expdate, oid) + "!";
		return temp;
	}
}
