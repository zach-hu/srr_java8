/**
 *
 */
package com.tsa.puridiom.taxcode;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.TaxCode;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;

/**
 * @author Johnny Zapana
 */
public class TaxCodeManager
{
	private static TaxCodeManager INSTANCE = new TaxCodeManager();

	private Map organizations = new HashMap();

	/**
	 * TaxCodeManager constructor comment.
	 */
	private TaxCodeManager()
	{
		super();
	}

	/**
	 * @return com.tsa.puridiom.taxcode.TaxCodeManager
	 */
	public static TaxCodeManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new TaxCodeManager();
		}
		return INSTANCE;
	}

	/**
	 * @return com.tsa.puridiom.entity.TaxCode
	 * @param organizationId
	 *            java.lang.String
	 * @param taxCode
	 *            java.lang.String
	 * @exception java.lang.Exception
	 *                The exception description.
	 */
	public TaxCode getTaxCode(String organizationId, String taxCode) throws java.lang.Exception
	{
		TaxCode taxCodeObject = null;
		Map taxCodes = new HashMap();
		PuridiomProcessLoader processLoader;
		PuridiomProcess process;
		Map incomingRequest = new HashMap();

		try
		{
			if (!HiltonUtility.isEmpty(organizationId))
			{
				if (this.organizations.containsKey(organizationId))
				{
					taxCodes = (Map) this.organizations.get(organizationId);
				}

				if (taxCodes.containsKey(taxCode))
				{
					taxCodeObject = (TaxCode) taxCodes.get(taxCode);
				} else
				{
					incomingRequest.put("organizationId", organizationId);
					incomingRequest.put("TaxCode_taxCode", taxCode);

					processLoader = new PuridiomProcessLoader(organizationId);
					process = processLoader.loadProcess("taxcode-retrieve-by-id.xml");

					process.executeProcess(incomingRequest);

					taxCodeObject = (TaxCode) incomingRequest.get("taxCode");

					if (taxCodeObject != null)
					{
						taxCodes.put(taxCodeObject.getTaxCode(), taxCodeObject);
					}

					this.organizations.put(organizationId, taxCodes);
				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}

		return taxCodeObject;
	}

	/**
	 * Returns a String that represents the value of this object.
	 *
	 * @return a string representation of the receiver
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();

		sb.append("[ClassName=com.tsa.puridiom.taxcode.TaxCodeManager]");

		return sb.toString();
	}
}
