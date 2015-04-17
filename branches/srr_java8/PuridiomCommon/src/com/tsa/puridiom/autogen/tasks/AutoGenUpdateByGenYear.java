package com.tsa.puridiom.autogen.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class AutoGenUpdateByGenYear extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		try
        {
			Map incomingRequest = (Map)object;

			incomingRequest.put("AutoGen_documentType", "PO");
			if(!HiltonUtility.isEmpty(HiltonUtility.ckNull((String)incomingRequest.get("AutoGen_genYearPo"))))
			{
				incomingRequest.put("AutoGen_genYear"     , (String)incomingRequest.get("AutoGen_genYearPo"));
				incomingRequest.put("AutoGen_nextNumber"  , (String)incomingRequest.get("AutoGen_nextNumberPo"));
				PuridiomProcessLoader processLoaderPo = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess processPo = processLoaderPo.loadProcess("autogen-update.xml");
				processPo.executeProcess(incomingRequest);
				if (processPo.getStatus() < Status.SUCCEEDED)
					throw new Exception("Auto Number process for Po failed.");
			}
			
			if(!HiltonUtility.isEmpty(HiltonUtility.ckNull((String)incomingRequest.get("AutoGen_genYearReq"))))
			{
				incomingRequest.put("AutoGen_documentType", "REQ");
				incomingRequest.put("AutoGen_genYear"     , (String)incomingRequest.get("AutoGen_genYearReq"));
				incomingRequest.put("AutoGen_nextNumber"  , (String)incomingRequest.get("AutoGen_nextNumberReq"));
				PuridiomProcessLoader processLoaderReq = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess processReq = processLoaderReq.loadProcess("autogen-update.xml");
				processReq.executeProcess(incomingRequest);
				if (processReq.getStatus() < Status.SUCCEEDED)
					throw new Exception("Auto Number process for Req failed.");
			}
			
			String genYearReqM = (String)incomingRequest.get("AutoGen_genYearReqM");
			
			if(!HiltonUtility.isEmpty(HiltonUtility.ckNull((String)incomingRequest.get("AutoGen_genYearReqM"))))
			{
				incomingRequest.put("AutoGen_documentType", "REQM");
				incomingRequest.put("AutoGen_genYear"     , (String)incomingRequest.get("AutoGen_genYearReqM"));
				incomingRequest.put("AutoGen_nextNumber"  , (String)incomingRequest.get("AutoGen_nextNumberReqM"));
				PuridiomProcessLoader processLoaderReq = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess processReq = processLoaderReq.loadProcess("autogen-update.xml");
				processReq.executeProcess(incomingRequest);
				if (processReq.getStatus() < Status.SUCCEEDED)
					throw new Exception("Auto Number process for ReqM failed.");
			}

			if(!HiltonUtility.isEmpty(HiltonUtility.ckNull((String)incomingRequest.get("AutoGen_genYearRfq"))))
			{
				incomingRequest.put("AutoGen_documentType", "RFQ");
				incomingRequest.put("AutoGen_genYear"     , (String)incomingRequest.get("AutoGen_genYearRfq"));
				incomingRequest.put("AutoGen_nextNumber"  , (String)incomingRequest.get("AutoGen_nextNumberRfq"));
				PuridiomProcessLoader processLoaderRfq = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess processRfq = processLoaderRfq.loadProcess("autogen-update.xml");
				processRfq.executeProcess(incomingRequest);
				if (processRfq.getStatus() < Status.SUCCEEDED)
					throw new Exception("Auto Number process for Rfq failed.");
			}

			if(!HiltonUtility.isEmpty(HiltonUtility.ckNull((String)incomingRequest.get("AutoGen_genYearDsb"))))
			{
				incomingRequest.put("AutoGen_documentType", "DSB");
				incomingRequest.put("AutoGen_genYear"     , (String)incomingRequest.get("AutoGen_genYearDsb"));
				incomingRequest.put("AutoGen_nextNumber"  , (String)incomingRequest.get("AutoGen_nextNumberDsb"));
				PuridiomProcessLoader processLoaderDsb = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess processDsb = processLoaderDsb.loadProcess("autogen-update.xml");
				processDsb.executeProcess(incomingRequest);
				if (processDsb.getStatus() < Status.SUCCEEDED)
					throw new Exception("Auto Number process for Dsb failed.");
			}

			this.setStatus(Status.SUCCEEDED);
        }
		catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
		return null;
	}
}