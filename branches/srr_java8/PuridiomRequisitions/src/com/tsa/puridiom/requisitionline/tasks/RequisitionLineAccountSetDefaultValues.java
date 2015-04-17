package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.AccountPK;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * Used to set default values in the account, based on the values set
 * in the My Profile
 *
 *
 * @author Alexander
 *
 */
public class RequisitionLineAccountSetDefaultValues extends Task
{

	@SuppressWarnings("unchecked")
	public Object executeTask(Object object) throws Exception
	{
		Object resp = null;
		Map incomingRequest = (Map) object;
		String oid = (String) incomingRequest.get("organizationId");
		String	uid = (String) incomingRequest.get("userId");
		String	commodityUpdateAccount = HiltonUtility.ckNull((String) incomingRequest.get("commodityUpdateAccount"));

		UserProfile	user = UserManager.getInstance().getUser(oid, uid);
		PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

		List accountCommodityList = (List) incomingRequest.get("accountCommodityList");
		List accountList = (List) incomingRequest.get("accountList");
		if (accountCommodityList == null || accountCommodityList.size() == 0) {
			accountCommodityList = (List) incomingRequest.get("accountList");
		}
		
		String defaultByCommodity = propertiesManager.getProperty("ACCOUNTS", "DEFAULTBYCOMMODITY", "N");
		String defaultAllItem = propertiesManager.getProperty("ACCOUNTS", "DEFAULTACCOUNTALLITEM", "N");
		String replaceFldsFromAccountCommodity = propertiesManager.getProperty("ACCOUNTS", "REPLACE FDLS FROM ACCOUNT COMMODITY", "N");

		String accfld1 = "", accfld2 = "", accfld3 = "", accfld4 = "", accfld5 = "";
		String accfld6 = "", accfld7 = "", accfld8 = "", accfld9 = "", accfld10 = "";
		String accfld11 = "", accfld12 = "", accfld13 = "", accfld14 = "", accfld15 = "";

		String nameUdf1 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF1","");
		String nameUdf2 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF2","");
		String nameUdf3 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF3","");
		String nameUdf4 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF4","");
		String nameUdf5 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF5","");
		String dept = propertiesManager.getProperty("ACCT NAME UDFS", "DEPT","");
		String costCenter = propertiesManager.getProperty("ACCT NAME UDFS", "COST CENTER","");
		String shipTo = propertiesManager.getProperty("ACCT NAME UDFS","SHIP TO","");
		String locale = propertiesManager.getProperty("ACCT NAME UDFS","LOCALE","");


		if ( !HiltonUtility.isEmpty(nameUdf1) )
		{
			if (nameUdf1.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getNameUdf1();	}
			if (nameUdf1.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getNameUdf1();	}
		}
		if ( !HiltonUtility.isEmpty(nameUdf2) )
		{
			if (nameUdf2.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getNameUdf2();	}
			if (nameUdf2.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getNameUdf2();	}
		}
		if ( !HiltonUtility.isEmpty(nameUdf3) )
		{
			if (nameUdf3.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getNameUdf3();	}
			if (nameUdf3.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getNameUdf3();	}
		}
		if ( !HiltonUtility.isEmpty(nameUdf4) )
		{
			if (nameUdf4.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getNameUdf4();	}
			if (nameUdf4.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getNameUdf4();	}
		}
		if ( !HiltonUtility.isEmpty(nameUdf5) )
		{
			if (nameUdf5.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getNameUdf5();	}
			if (nameUdf5.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getNameUdf5();	}
		}
		if ( !HiltonUtility.isEmpty(dept) )
		{
			if (dept.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getDepartment();	}
			if (dept.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getDepartment();	}
		}
		if ( !HiltonUtility.isEmpty(costCenter) )
		{
			if (costCenter.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getCostCenter();	}
			if (costCenter.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getCostCenter();	}
			if (costCenter.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getCostCenter();	}
			if (costCenter.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getCostCenter();	}
			if (costCenter.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getCostCenter();	}
			if (costCenter.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getCostCenter();	}
			if (costCenter.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getCostCenter();	}
			if (costCenter.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getCostCenter();	}
			if (costCenter.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getCostCenter();	}
			if (costCenter.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getCostCenter();	}
			if (costCenter.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getCostCenter();	}
			if (costCenter.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getCostCenter();	}
			if (costCenter.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getCostCenter();	}
			if (costCenter.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getCostCenter();	}
			if (costCenter.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getCostCenter();	}
		}
		if ( !HiltonUtility.isEmpty(shipTo) )
		{
			if (shipTo.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getShipToCode();	}
			if (shipTo.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getShipToCode();	}
		}
		if ( !HiltonUtility.isEmpty(locale) )
		{
			if (locale.equalsIgnoreCase("Account_fld1"))	{	accfld1 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld2"))	{	accfld2 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld3"))	{	accfld3 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld4"))	{	accfld4 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld5"))	{	accfld5 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld6"))	{	accfld6 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld7"))	{	accfld7 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld8"))	{	accfld8 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld9"))	{	accfld9 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld10"))	{	accfld10= user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld11"))	{	accfld11 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld12"))	{	accfld12 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld13"))	{	accfld13 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld14"))	{	accfld14 = user.getLocale();	}
			if (locale.equalsIgnoreCase("Account_fld15"))	{	accfld15 = user.getLocale();	}
		}

		if (defaultByCommodity.equals("Y") && accountCommodityList != null && accountCommodityList.size() > 0) {
			Account accountCommodity = null;
			Account account = null;
			if (propertiesManager.getProperty("ACCOUNTS", "SELECT ACCOUNT BY FLD 1", "N").equalsIgnoreCase("Y"))
			{
				for (int ind = 0; ind < accountCommodityList.size(); ind++)
				{
					Account accountItem = (Account) accountCommodityList.get(ind);
					if (accfld1.equalsIgnoreCase(accountItem.getFld1()))
					{
						accountCommodity = accountItem;
						break;
					}
				}
				if (accountCommodity != null) {
					if (HiltonUtility.isEmpty(accfld1)) {
						accfld1 = accountCommodity.getFld1();
					}
					if (HiltonUtility.isEmpty(accfld2)) {
						accfld2 = accountCommodity.getFld2();
					}
					if (HiltonUtility.isEmpty(accfld3)) {
						accfld3 = accountCommodity.getFld3();
					}
					if (HiltonUtility.isEmpty(accfld4)) {
						accfld4 = accountCommodity.getFld4();
					}
					if (HiltonUtility.isEmpty(accfld5)) {
						accfld5 = accountCommodity.getFld5();
					}
					if (HiltonUtility.isEmpty(accfld6)) {
						accfld6 = accountCommodity.getFld6();
					}
					if (HiltonUtility.isEmpty(accfld7)) {
						accfld7 = accountCommodity.getFld7();
					}
					if (HiltonUtility.isEmpty(accfld8)) {
						accfld8 = accountCommodity.getFld8();
					}
					if (HiltonUtility.isEmpty(accfld9)) {
						accfld9 = accountCommodity.getFld9();
					}
					if (HiltonUtility.isEmpty(accfld10)) {
						accfld10 = accountCommodity.getFld10();
					}
					if (HiltonUtility.isEmpty(accfld11)) {
						accfld11 = accountCommodity.getFld11();
					}
					if (HiltonUtility.isEmpty(accfld12)) {
						accfld12 = accountCommodity.getFld12();
					}
					if (HiltonUtility.isEmpty(accfld13)) {
						accfld13 = accountCommodity.getFld13();
					}
					if (HiltonUtility.isEmpty(accfld14)) {
						accfld14 = accountCommodity.getFld14();
					}
					if (HiltonUtility.isEmpty(accfld15)) {
						accfld15 = accountCommodity.getFld15();
					}
		
					accountCommodityList = new ArrayList();
					accountCommodityList.add(accountCommodity);
				}
			}
			else if(commodityUpdateAccount.equalsIgnoreCase("Y") && accountList != null && accountList.size() > 0 )
			{
				for (int ind = 0; ind < accountCommodityList.size(); ind++)
				{
					Account accountItem = (Account) accountCommodityList.get(ind);
					accountCommodity = accountItem;
					break;
				}
				for (int ind = 0; ind < accountList.size(); ind++)
				{
					account = (Account) accountList.get(ind);
					break;
				}
				if (accountCommodity != null) {
					if (HiltonUtility.isEmpty(accfld1) || (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(accountCommodity.getFld1()))) {
						accfld1 = accountCommodity.getFld1();
						account.setFld1(accfld1);
					}
					if (HiltonUtility.isEmpty(accfld2) || (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(accountCommodity.getFld2()))) {
						accfld2 = accountCommodity.getFld2();
						account.setFld2(accfld2);
					}
					if (HiltonUtility.isEmpty(accfld3) || (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(accountCommodity.getFld3()))) {
						accfld3 = accountCommodity.getFld3();
						account.setFld3(accfld3);
					}
					if (HiltonUtility.isEmpty(accfld4) || (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(accountCommodity.getFld4()))) {
						accfld4 = accountCommodity.getFld4();
						account.setFld4(accfld4);
					}
					if (HiltonUtility.isEmpty(accfld5) || (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(accountCommodity.getFld5()))) {
						accfld5 = accountCommodity.getFld5();
						account.setFld5(accfld5);
					}
					if (HiltonUtility.isEmpty(accfld6) || (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(accountCommodity.getFld6()))) {
						accfld6 = accountCommodity.getFld6();
						account.setFld6(accfld6);
					}
					if (HiltonUtility.isEmpty(accfld7) || (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(accountCommodity.getFld7()))) {
						accfld7 = accountCommodity.getFld7();
						account.setFld7(accfld7);
					}
					if (HiltonUtility.isEmpty(accfld8) || (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(accountCommodity.getFld8()))) {
						accfld8 = accountCommodity.getFld8();
						account.setFld8(accfld8);
					}
					if (HiltonUtility.isEmpty(accfld9) || (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(accountCommodity.getFld9()))) {
						accfld9 = accountCommodity.getFld9();
						account.setFld9(accfld9);
					}
					if (HiltonUtility.isEmpty(accfld10) || (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(accountCommodity.getFld10()))) {
						accfld10 = accountCommodity.getFld10();
						account.setFld10(accfld10);
					}
					if (HiltonUtility.isEmpty(accfld11) || (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(accountCommodity.getFld11()))) {
						accfld11 = accountCommodity.getFld11();
						account.setFld11(accfld11);
					}
					if (HiltonUtility.isEmpty(accfld12) || (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(accountCommodity.getFld12()))) {
						accfld12 = accountCommodity.getFld12();
						account.setFld12(accfld12);
					}
					if (HiltonUtility.isEmpty(accfld13) || (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(accountCommodity.getFld13()))) {
						accfld13 = accountCommodity.getFld13();
						account.setFld13(accfld13);
					}
					if (HiltonUtility.isEmpty(accfld14) || (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(accountCommodity.getFld14()))) {
						accfld14 = accountCommodity.getFld14();
						account.setFld14(accfld14);
					}
					if (HiltonUtility.isEmpty(accfld15) || (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && !HiltonUtility.isEmpty(accountCommodity.getFld15()))) {
						accfld15 = accountCommodity.getFld15();
						account.setFld15(accfld15);
					}
		
					accountCommodityList = new ArrayList();
					accountCommodityList.add(accountCommodity);
				}
			}
			else if(!commodityUpdateAccount.equalsIgnoreCase("Y") && accountCommodityList != null && accountCommodityList.size() > 0 )
			{
				for (int ind = 0; ind < accountCommodityList.size(); ind++)
				{
					Account accountItem = (Account) accountCommodityList.get(ind);
					accountCommodity = accountItem;
					break;
				}
				if (accountCommodity != null) {
					if (HiltonUtility.isEmpty(accountCommodity.getFld1()) && !HiltonUtility.isEmpty(accfld1)) {
						accountCommodity.setFld1(accfld1);
					}
					if (HiltonUtility.isEmpty(accountCommodity.getFld2()) && !HiltonUtility.isEmpty(accfld2)) {
						accountCommodity.setFld2(accfld2);
					}
					if (HiltonUtility.isEmpty(accountCommodity.getFld3()) && !HiltonUtility.isEmpty(accfld3)) {
						accountCommodity.setFld3(accfld3);
					}
					if (HiltonUtility.isEmpty(accountCommodity.getFld4()) && !HiltonUtility.isEmpty(accfld4)) {
						accountCommodity.setFld4(accfld4);
					}
					if (HiltonUtility.isEmpty(accountCommodity.getFld5()) && !HiltonUtility.isEmpty(accfld5)) {
						accountCommodity.setFld5(accfld5);
					}
					if (HiltonUtility.isEmpty(accountCommodity.getFld6()) && !HiltonUtility.isEmpty(accfld6)) {
						accountCommodity.setFld6(accfld6);
					}
					if (HiltonUtility.isEmpty(accountCommodity.getFld7()) && !HiltonUtility.isEmpty(accfld7)) {
						accountCommodity.setFld7(accfld7);
					}
					if (HiltonUtility.isEmpty(accountCommodity.getFld8()) && !HiltonUtility.isEmpty(accfld8)) {
						accountCommodity.setFld8(accfld8);
					}
					if (HiltonUtility.isEmpty(accountCommodity.getFld9()) && !HiltonUtility.isEmpty(accfld9)) {
						accountCommodity.setFld9(accfld9);
					}
					if (HiltonUtility.isEmpty(accountCommodity.getFld10()) && !HiltonUtility.isEmpty(accfld10)) {
						accountCommodity.setFld10(accfld10);
					}
					if (HiltonUtility.isEmpty(accountCommodity.getFld11()) && !HiltonUtility.isEmpty(accfld11)) {
						accountCommodity.setFld11(accfld11);
					}
					if (HiltonUtility.isEmpty(accountCommodity.getFld12()) && !HiltonUtility.isEmpty(accfld12)) {
						accountCommodity.setFld12(accfld12);
					}
					if (HiltonUtility.isEmpty(accountCommodity.getFld13()) && !HiltonUtility.isEmpty(accfld13)) {
						accountCommodity.setFld13(accfld13);
					}
					if (HiltonUtility.isEmpty(accountCommodity.getFld14()) && !HiltonUtility.isEmpty(accfld14)) {
						accountCommodity.setFld14(accfld14);
					}
					if (HiltonUtility.isEmpty(accountCommodity.getFld15()) && !HiltonUtility.isEmpty(accfld15)) {
						accountCommodity.setFld15(accfld15);
					}
		
					accountCommodityList = new ArrayList();
					accountCommodityList.add(accountCommodity);
				}
			}

			if (replaceFldsFromAccountCommodity.equalsIgnoreCase("Y") && accountCommodityList != null && accountCommodityList.size() > 0)
			{
				Account accountComm = (Account) accountCommodityList.get(0);
				if (accountComm != null)
				{
					if (!HiltonUtility.isEmpty(accountComm.getFld1())) { accfld1 = accountComm.getFld1(); }
					if (!HiltonUtility.isEmpty(accountComm.getFld2())) { accfld2 = accountComm.getFld2(); }
					if (!HiltonUtility.isEmpty(accountComm.getFld3())) { accfld3 = accountComm.getFld3(); }
					if (!HiltonUtility.isEmpty(accountComm.getFld4())) { accfld4 = accountComm.getFld4(); }
					if (!HiltonUtility.isEmpty(accountComm.getFld5())) { accfld5 = accountComm.getFld5(); }
					if (!HiltonUtility.isEmpty(accountComm.getFld6())) { accfld6 = accountComm.getFld6(); }
					if (!HiltonUtility.isEmpty(accountComm.getFld7())) { accfld7 = accountComm.getFld7(); }
					if (!HiltonUtility.isEmpty(accountComm.getFld8())) { accfld8 = accountComm.getFld8(); }
					if (!HiltonUtility.isEmpty(accountComm.getFld9())) { accfld9 = accountComm.getFld9(); }
					if (!HiltonUtility.isEmpty(accountComm.getFld10())) { accfld10 = accountComm.getFld10(); }
					if (!HiltonUtility.isEmpty(accountComm.getFld11())) { accfld11 = accountComm.getFld11(); }
					if (!HiltonUtility.isEmpty(accountComm.getFld12())) { accfld12 = accountComm.getFld12(); }
					if (!HiltonUtility.isEmpty(accountComm.getFld13())) { accfld13 = accountComm.getFld13(); }
					if (!HiltonUtility.isEmpty(accountComm.getFld14())) { accfld14 = accountComm.getFld14(); }
					if (!HiltonUtility.isEmpty(accountComm.getFld15())) { accfld15 = accountComm.getFld15(); }
				}
			}
		}

		RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
		if (propertiesManager.getProperty("ACCOUNTS", "FLD3 FROM DEPARTMENTCODE", "N").equalsIgnoreCase("Y") && requisitionHeader != null && !HiltonUtility.isEmpty(requisitionHeader.getDepartmentCode()))
		{
			accfld3 = requisitionHeader.getDepartmentCode();
		}

		incomingRequest.put("userNameUdf1", accfld1);
		incomingRequest.put("userNameUdf2", accfld2);
		incomingRequest.put("userNameUdf3", accfld3);
		incomingRequest.put("userNameUdf4", accfld4);
		incomingRequest.put("userNameUdf5", accfld5);
		incomingRequest.put("userNameUdf6", accfld6);
		incomingRequest.put("userNameUdf7", accfld7);
		incomingRequest.put("userNameUdf8", accfld8);
		incomingRequest.put("userNameUdf9", accfld9);
		incomingRequest.put("userNameUdf10", accfld10);
		incomingRequest.put("userNameUdf11", accfld11);
		incomingRequest.put("userNameUdf12", accfld12);
		incomingRequest.put("userNameUdf13", accfld13);
		incomingRequest.put("userNameUdf14", accfld14);
		incomingRequest.put("userNameUdf15", accfld15);

		incomingRequest.put("Account_fld1", accfld1);
		incomingRequest.put("Account_fld2", accfld2);
		incomingRequest.put("Account_fld3", accfld3);
		incomingRequest.put("Account_fld4", accfld4);
		incomingRequest.put("Account_fld5", accfld5);
		incomingRequest.put("Account_fld6", accfld6);
		incomingRequest.put("Account_fld7", accfld7);
		incomingRequest.put("Account_fld8", accfld8);
		incomingRequest.put("Account_fld9", accfld9);
		incomingRequest.put("Account_fld10", accfld10);
		incomingRequest.put("Account_fld11", accfld11);
		incomingRequest.put("Account_fld12", accfld12);
		incomingRequest.put("Account_fld13", accfld13);
		incomingRequest.put("Account_fld14", accfld14);
		incomingRequest.put("Account_fld15", accfld15);
		
		if(defaultAllItem.equalsIgnoreCase("Y") && (accountCommodityList.size() < 1 || accountCommodityList == null))
		{
			Account accountDefault = new Account();
			AccountPK comp_id = new AccountPK();
			String icHeader = (String)incomingRequest.get("Account_icHeader");
			String icLine = (String)incomingRequest.get("Account_icLine") ;
			BigDecimal bdHeader = new BigDecimal(icHeader);
			BigDecimal bdLine = new BigDecimal(icLine);
			comp_id.setSequence(new BigDecimal(0));
			comp_id.setIcHeader(bdHeader);
			comp_id.setIcLine(bdLine);
			accountDefault.setComp_id(comp_id);
			if (!HiltonUtility.isEmpty(accfld1)) {
				accountDefault.setFld1(accfld1);
			}
			if (!HiltonUtility.isEmpty(accfld2)) {
				accountDefault.setFld2(accfld2);
			}
			if (!HiltonUtility.isEmpty(accfld3)) {
				accountDefault.setFld3(accfld3);
			}
			if (!HiltonUtility.isEmpty(accfld4)) {
				accountDefault.setFld4(accfld4);
			}
			if (!HiltonUtility.isEmpty(accfld5)) {
				accountDefault.setFld5(accfld5);
			}
			if (!HiltonUtility.isEmpty(accfld6)) {
				accountDefault.setFld6(accfld6);
			}
			if (!HiltonUtility.isEmpty(accfld7)) {
				accountDefault.setFld7(accfld7);
			}
			if (!HiltonUtility.isEmpty(accfld8)) {
				accountDefault.setFld8(accfld8);
			}
			if (!HiltonUtility.isEmpty(accfld9)) {
				accountDefault.setFld9(accfld9);
			}
			if (!HiltonUtility.isEmpty(accfld10)) {
				accountDefault.setFld10(accfld10);
			}
			if (!HiltonUtility.isEmpty(accfld11)) {
				accountDefault.setFld11(accfld11);
			}
			if (!HiltonUtility.isEmpty(accfld12)) {
				accountDefault.setFld12(accfld12);
			}
			if (!HiltonUtility.isEmpty(accfld13)) {
				accountDefault.setFld13(accfld13);
			}
			if (!HiltonUtility.isEmpty(accfld14)) {
				accountDefault.setFld14(accfld14);
			}
			if (!HiltonUtility.isEmpty(accfld15)) {
				accountDefault.setFld15(accfld15);
			}

			accountCommodityList = new ArrayList();
			accountCommodityList.add(accountDefault);
		}
		resp = accountCommodityList;

		this.status = Status.SUCCEEDED;

		return resp;
	}
}
