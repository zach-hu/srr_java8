package com.tsa.puridiom.vendorinsurancedefault;

import com.tsa.puridiom.entity.VendorInsurance;
import com.tsa.puridiom.entity.VendorInsuranceDefault;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;

import java.util.HashMap;
import java.util.Map;

public class VendorInsuranceDefaultManager
{
	private static VendorInsuranceDefaultManager INSTANCE = new VendorInsuranceDefaultManager();

	private HashMap organizations = new HashMap();

	private VendorInsuranceDefaultManager()
	{
		super();
	}

	public static VendorInsuranceDefaultManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new VendorInsuranceDefaultManager();
		}
		return INSTANCE;
	}

	public VendorInsuranceDefault getVendorInsuranceDefault(String organizationId, String vendorId) throws java.lang.Exception
    {
		VendorInsuranceDefault vendorInsuranceDefault = null;

        try
        {
            if (!HiltonUtility.isEmpty(organizationId))
            {
                Map vendorInsuranceDefaultMap = new HashMap();
                if (this.organizations.containsKey(organizationId))
                {
                	vendorInsuranceDefaultMap = (Map) this.organizations.get(organizationId);
                }
                if (vendorInsuranceDefaultMap.containsKey(vendorId))
                {
                	vendorInsuranceDefault = (VendorInsuranceDefault) vendorInsuranceDefaultMap.get(vendorId);
                }
                if (vendorInsuranceDefault == null)
                {
                    Map processParameters = new HashMap();
                    processParameters.put("organizationId", organizationId);
                    processParameters.put("Vendor_vendorId", vendorId);
                    processParameters.put("VendorInsuranceDefault_vendorId", vendorId);

                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
                    PuridiomProcess process = processLoader.loadProcess("vendorinsurancedefault-retrieve-by-id.xml");

                    process.executeProcess(processParameters);

                    vendorInsuranceDefault = (VendorInsuranceDefault) processParameters.get("vendorInsuranceDefault");
                    if (vendorInsuranceDefault != null)
                    {
                    	vendorInsuranceDefaultMap.put(vendorInsuranceDefault.getVendorId(), vendorInsuranceDefault);
                    }
                    this.organizations.put(organizationId, vendorInsuranceDefaultMap);
                }
            }

            return vendorInsuranceDefault;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

	public void setVendorInsuranceDefaultInCache(String organizationId, VendorInsuranceDefault vendorInsuranceDefault) throws Exception
	{
		String	vendorId = vendorInsuranceDefault.getVendorId();
		Map vendorInsuranceDefaultMap = new HashMap();

		if (HiltonUtility.isEmpty(vendorId) || HiltonUtility.isEmpty(organizationId))
		{
			throw new Exception ("VendorInsuranceDefault cannot be updated without a vendor Id and an organization id!");
		}

		if (this.organizations.containsKey(organizationId))
		{
			vendorInsuranceDefaultMap = (Map) this.organizations.get(organizationId);
		}

		vendorInsuranceDefaultMap.put(vendorId, vendorInsuranceDefault);
		this.organizations.put(organizationId, vendorInsuranceDefaultMap);
	}

	public static void refresh() {
        INSTANCE = new VendorInsuranceDefaultManager();
    }
	
	public VendorInsurance getVendorInsuranceFromDefault(String organizationId, String vendorId) throws java.lang.Exception
	{
		VendorInsuranceDefault vendorInsuranceDefault = null;
		VendorInsurance vendorInsurance = new VendorInsurance();
		try {
			vendorInsuranceDefault = getVendorInsuranceDefault(organizationId, vendorId);
			
			if(vendorInsuranceDefault == null){
				return vendorInsurance;
			}
			
			
			vendorInsurance.setVendorId(vendorInsuranceDefault.getVendorId());
			vendorInsurance.setInsuranceContact(vendorInsuranceDefault.getInsuranceContact());
			vendorInsurance.setInsuranceNotes(vendorInsuranceDefault.getInsuranceNotes());
//			vendorInsurance.setInsuranceStatus(vendorInsuranceDefault.getInsuranceStatus());
//			vendorInsurance.setExpiredLetter(vendorInsuranceDefault.getExpiredLetter());
			vendorInsurance.setCoverage1(vendorInsuranceDefault.getCoverage1());
			vendorInsurance.setExpires1(vendorInsuranceDefault.getExpires1());
			vendorInsurance.setNamed1(vendorInsuranceDefault.getNamed1());
			vendorInsurance.setWaiver1(vendorInsuranceDefault.getWaiver1());
			vendorInsurance.setLimit1(vendorInsuranceDefault.getLimit1());
			vendorInsurance.setCoverage2(vendorInsuranceDefault.getCoverage2());
			vendorInsurance.setExpires2(vendorInsuranceDefault.getExpires2());
			vendorInsurance.setNamed2(vendorInsuranceDefault.getNamed2());
			vendorInsurance.setWaiver2(vendorInsuranceDefault.getWaiver2());
			vendorInsurance.setLimit2(vendorInsuranceDefault.getLimit2());
			vendorInsurance.setCoverage3(vendorInsuranceDefault.getCoverage3());
			vendorInsurance.setExpires3(vendorInsuranceDefault.getExpires3());
			vendorInsurance.setNamed3(vendorInsuranceDefault.getNamed3());
			vendorInsurance.setWaiver3(vendorInsuranceDefault.getWaiver3());
			vendorInsurance.setLimit3(vendorInsuranceDefault.getLimit3());
			vendorInsurance.setCoverage4(vendorInsuranceDefault.getCoverage4());
			vendorInsurance.setExpires4(vendorInsuranceDefault.getExpires4());
			vendorInsurance.setNamed4(vendorInsuranceDefault.getNamed4());
			vendorInsurance.setWaiver4(vendorInsuranceDefault.getWaiver4());
			vendorInsurance.setLimit4(vendorInsuranceDefault.getLimit4());
			vendorInsurance.setCoverage5(vendorInsuranceDefault.getCoverage5());
			vendorInsurance.setExpires5(vendorInsuranceDefault.getExpires5());
			vendorInsurance.setNamed5(vendorInsuranceDefault.getNamed5());
			vendorInsurance.setWaiver5(vendorInsuranceDefault.getWaiver5());
			vendorInsurance.setLimit5(vendorInsuranceDefault.getLimit5());
			vendorInsurance.setCoverage6(vendorInsuranceDefault.getCoverage6());
			vendorInsurance.setExpires6(vendorInsuranceDefault.getExpires6());
			vendorInsurance.setNamed6(vendorInsuranceDefault.getNamed6());
			vendorInsurance.setWaiver6(vendorInsuranceDefault.getWaiver6());
			vendorInsurance.setLimit6(vendorInsuranceDefault.getLimit6());
			/*vendorInsurance.setCertifiedDate1(vendorInsuranceDefault.getCertifiedDate1());
			vendorInsurance.setCertStatus1(vendorInsuranceDefault.getCertStatus1());
			vendorInsurance.setCertifiedDate2(vendorInsuranceDefault.getCertifiedDate2());
			vendorInsurance.setCertStatus2(vendorInsuranceDefault.getCertStatus2());
			vendorInsurance.setCertifiedDate3(vendorInsuranceDefault.getCertifiedDate3());
			vendorInsurance.setCertStatus3(vendorInsuranceDefault.getCertStatus3());
			vendorInsurance.setCertifiedDate4(vendorInsuranceDefault.getCertifiedDate4());
			vendorInsurance.setCertStatus4(vendorInsuranceDefault.getCertStatus4());
			vendorInsurance.setCertifiedDate5(vendorInsuranceDefault.getCertifiedDate5());
			vendorInsurance.setCertStatus5(vendorInsuranceDefault.getCertStatus5());
			vendorInsurance.setCertifiedDate6(vendorInsuranceDefault.getCertifiedDate6());
			vendorInsurance.setCertStatus6(vendorInsuranceDefault.getCertStatus6());
			vendorInsurance.setCertUdf1(vendorInsuranceDefault.getCertUdf1());
			vendorInsurance.setCertUdf2(vendorInsuranceDefault.getCertUdf2());
			vendorInsurance.setCertUdf3(vendorInsuranceDefault.getCertUdf3());
			vendorInsurance.setCertUdf4(vendorInsuranceDefault.getCertUdf4());
			vendorInsurance.setCertUdf5(vendorInsuranceDefault.getCertUdf5());
			vendorInsurance.setCertUdf6(vendorInsuranceDefault.getCertUdf6());
			vendorInsurance.setComplianceNotes(vendorInsuranceDefault.getComplianceNotes());
			vendorInsurance.setNotifiedDate(vendorInsuranceDefault.getNotifiedDate());*/
			
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(this, e);
            throw e;
		}
		return vendorInsurance;
	}
}
