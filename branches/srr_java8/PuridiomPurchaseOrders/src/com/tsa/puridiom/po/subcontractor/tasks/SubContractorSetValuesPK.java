package com.tsa.puridiom.po.subcontractor.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.SubContractor;
import com.tsa.puridiom.entity.SubContractorPK;

public class SubContractorSetValuesPK
{
	public void setValues(Map incomingRequest, SubContractor subContractor ) throws Exception
	{
		try
		{
			String poNumber = (String ) incomingRequest.get("SubContractor_poNumber");
			String releaseNumberString = (String ) incomingRequest.get("SubContractor_releaseNumber");
			BigDecimal releaseNumber = new BigDecimal ( releaseNumberString );
			String subName = (String) incomingRequest.get("SubContractor_subName");

			SubContractorPK comp_id = new SubContractorPK();
			comp_id.setPoNumber(poNumber);
			comp_id.setReleaseNumber(releaseNumber);
			comp_id.setSubName(subName);
			subContractor.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}