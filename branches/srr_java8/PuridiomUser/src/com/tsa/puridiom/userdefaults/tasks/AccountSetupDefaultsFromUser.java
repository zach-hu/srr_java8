package com.tsa.puridiom.userdefaults.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

public class AccountSetupDefaultsFromUser extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String oid = (String) incomingRequest.get("organizationId");
			PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
			String uid = (String) incomingRequest.get("userId");
			UserProfile user = UserManager.getInstance().getUser(oid,uid);

			String accfld1 = "", accfld2 = "", accfld3 = "", accfld4 = "", accfld5 = "";
			String accfld6 = "", accfld7 = "", accfld8 = "", accfld9 = "", accfld10 = "";
			String accfld11 = "", accfld12 = "", accfld13 = "", accfld14 = "", accfld15 = "";

			String nameUdf1 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF1","");
			String nameUdf2 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF2","");
			String nameUdf3 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF3","");
			String nameUdf4 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF4","");
			String nameUdf5 = propertiesManager.getProperty("ACCT NAME UDFS", "UDF5","");
			String dept = propertiesManager.getProperty("ACCT NAME UDFS", "DEPT","");

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

		    if (oid.equalsIgnoreCase("bly07p"))
			{
				accfld1 = user.getDepartment();
				List accountCommodityList = (List) incomingRequest.get("accountCommodityList");
				if (accountCommodityList != null)
				{
					if (HiltonUtility.isEmpty(accfld2) && accountCommodityList.size() > 0)
					{
						Account accountCommodity = (Account) accountCommodityList.get(0);
						accfld2 = accountCommodity.getFld2();
					}
				}
			}
		    
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
			incomingRequest.put("Account_allocPercent", "0");
			incomingRequest.put("Account_allocAmount", "0");
			incomingRequest.put("Account_accountTitle", "");
			incomingRequest.put("Account_dateEntered", Dates.today("", ""));
			incomingRequest.put("Account_dateExpires", Dates.today("", ""));
			incomingRequest.put("Account_status", "");
			incomingRequest.put("Account_owner", "");
			incomingRequest.put("Account_allocMethod", "");
			incomingRequest.put("Account_allocQty", "0");
			incomingRequest.put("Account_recQty", "0");
			incomingRequest.put("Account_icLastRec", "0");
			incomingRequest.put("Account_source", "");

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			Log.error(this, "Error on AccountSetupFromUserDefault" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
