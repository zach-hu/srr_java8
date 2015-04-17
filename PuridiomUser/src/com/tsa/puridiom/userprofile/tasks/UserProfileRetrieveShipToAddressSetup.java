/**
 * 
 */
package com.tsa.puridiom.userprofile.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class UserProfileRetrieveShipToAddressSetup extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			UserProfile user = UserManager.getInstance().getUser(organizationId, userId);
			String shipToCode = this.getShipToCodeFromUser(user, organizationId);

			incomingRequest.put("Address_addressCode", shipToCode);
			incomingRequest.put("Address_addressType", "ADDR");

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "UserProfileRetrieveShipToAddressSetup error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

	private String getShipToCodeFromUser(UserProfile user, String organizationId)
	{
		String shipToCode = user.getShipToCode();
		PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
		String field1 = HiltonUtility.ckNull(propertiesManager.getProperty("Name Udfs", "Udf1", ""));
		String field2 = HiltonUtility.ckNull(propertiesManager.getProperty("Name Udfs", "Udf2", ""));
		String field3 = HiltonUtility.ckNull(propertiesManager.getProperty("Name Udfs", "Udf3", ""));
		String field4 = HiltonUtility.ckNull(propertiesManager.getProperty("Name Udfs", "Udf4", ""));
		String field5 = HiltonUtility.ckNull(propertiesManager.getProperty("Name Udfs", "Udf5", ""));
		String shipToCodeSuffix = "_shipToCode";

		if (HiltonUtility.isEmpty(shipToCode))
		{
			shipToCode = propertiesManager.getProperty("REQ DEFAULTS", "ShipToCode", "");
		}

		if (field1.endsWith(shipToCodeSuffix))
		{
			shipToCode = user.getNameUdf1();

		} else if (field2.endsWith(shipToCodeSuffix))
		{
			shipToCode = user.getNameUdf2();

		} else if (field3.endsWith(shipToCodeSuffix))
		{
			shipToCode = user.getNameUdf3();

		} else if (field4.endsWith(shipToCodeSuffix))
		{
			shipToCode = user.getNameUdf4();

		} else if (field5.endsWith(shipToCodeSuffix))
		{
			shipToCode = user.getNameUdf5();
		}

		return shipToCode;
	}

}
