/*
 * Created on Dec 6, 2005
 */
package com.tsa.puridiom.rfq.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.ResponseValue;
import com.tsa.puridiom.entity.ResponseValuePK;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;
import com.tsa.puridiom.entity.RfqQuestion;
import com.tsa.puridiom.entity.RfqVendor;
import com.tsa.puridiom.entity.VendorResponse;
import com.tsa.puridiom.entity.VendorResponsePK;
import com.tsa.puridiom.rfq.RfqBidWorksheet;
import com.tsa.puridiom.rfqline.tasks.RfqLineCopy;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Kelli
 */
public class RfqBidWorksheetDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String worksheetGroupsString = (String)incomingRequest.get("worksheetGroups");
			if(Utility.isEmpty(worksheetGroupsString)){		worksheetGroupsString = "4";		}
			BigDecimal worksheetGroups = new BigDecimal(worksheetGroupsString);
			RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader") ;
			if (rfqHeader == null) {		throw new Exception("The RfqHeader entity was not found.");		}

			rfqHeader.setShipToAddress((Address) incomingRequest.get("shipToAddress"));
			RfqBidWorksheet rfqBidWorksheet = new RfqBidWorksheet();

			List rfqVendorList = (List) incomingRequest.get("rfqVendorList");
			List rfqQuestionList = (List) incomingRequest.get("rfqQuestionList");
			List	vendors = new ArrayList();
			List	tempVendors = new ArrayList();
			int	groups = 0;

			for (int i = 0; i < rfqVendorList.size(); i++)
			{
			    tempVendors.add(rfqVendorList.get(i));
			    if ( ((i+1) % worksheetGroups.intValue()) == 0 || (i+1) >= rfqVendorList.size())
			    {
			        vendors.add(tempVendors);
			        tempVendors = new ArrayList();
			    }
			}

			groups = vendors.size();

			List	rfqLineList = (List) incomingRequest.get("rfqLineList");
			List rfqLineGroups = new ArrayList();
			RfqLineCopy copyTask = new RfqLineCopy();

			for (int ig = 0; ig < groups; ig++)
			{
			    List	rfqLines = new ArrayList();
				for (int il = 0; il < rfqLineList.size(); il++)
				{
				    RfqLine rfqLine = (RfqLine) rfqLineList.get(il);
				    //List rfqBidGroupList = rfqLine.getRfqBidGroup(ig, worksheetGroups.intValue());
				    List rfqBidGroupList = rfqLine.getRfqSelectedBidGroup(ig, worksheetGroups.intValue(), (List)vendors.get(ig));

				    HashMap copyParameters = new HashMap();
				    copyParameters.put("rfqLine", rfqLine);
				    RfqLine rfqLineTemp = (RfqLine) copyTask.executeTask(copyParameters);

				    rfqLineTemp.setRfqBidList(rfqBidGroupList);
				    rfqLines.add(rfqLineTemp);
				}
				rfqLineGroups.add(rfqLines);
			}

			List rfqQuestionGroups = new ArrayList();
			List vendorsListByGroup;
			List rfqQuestions;
			RfqQuestion rfqQuestion;
			RfqQuestion rfqQuestionTmp;
			List vendorResponseList;
			List responseValueList;
			RfqVendor rfqVendor;
			VendorResponse vendorResponse;
			ResponseValue responseValue;

			for (int ig = 0; ig < groups; ig++)
			{
				vendorsListByGroup = (List) vendors.get(ig);
				rfqQuestions = new ArrayList();

				for (int j = 0; j < rfqQuestionList.size(); j++)
				{
					rfqQuestion = (RfqQuestion) rfqQuestionList.get(j);

					rfqQuestionTmp = new RfqQuestion(rfqQuestion.getComp_id(), rfqQuestion.getSequence(), rfqQuestion.getQuestionText(), rfqQuestion.getResponseType(), rfqQuestion.getRatingMethod(), rfqQuestion.getWeight(), rfqQuestion.getMaxPoints());

					vendorResponseList = new ArrayList();
					responseValueList = new ArrayList();

					for (Iterator iter = vendorsListByGroup.iterator(); iter.hasNext();)
					{
						rfqVendor = (RfqVendor) iter.next();

						vendorResponse = null;

						if (rfqQuestion.getVendorResponseMap().containsKey(rfqVendor.getVendorId()))
						{
							vendorResponse = (VendorResponse) rfqQuestion.getVendorResponseMap().get(rfqVendor.getVendorId());

							vendorResponseList.add(vendorResponse);
						} else {

							vendorResponseList.add(new VendorResponse(new VendorResponsePK(rfqQuestion.getComp_id().getIcRfqHeader(), rfqQuestion.getComp_id().getIcQuestion(), rfqVendor.getVendorId())));
						}

						if ((vendorResponse != null) && (rfqQuestion.getResponseValueMap().containsKey(vendorResponse.getResponse())))
						{
							responseValue = (ResponseValue) rfqQuestion.getResponseValueMap().get(vendorResponse.getResponse());

							responseValueList.add(responseValue);
						} else {

							responseValueList.add(new ResponseValue(new ResponseValuePK(rfqQuestion.getComp_id().getIcQuestion(), "")));
						}
					}

					rfqQuestionTmp.setVendorResponseList(vendorResponseList);
					rfqQuestionTmp.setResponseValueList(responseValueList);

					rfqQuestions.add(rfqQuestionTmp);
				}

				rfqQuestionGroups.add(rfqQuestions);
			}

			rfqBidWorksheet.setRfqHeader(rfqHeader);
			rfqBidWorksheet.setRfqVendorGroupList(vendors);
			rfqBidWorksheet.setRfqLineGroupList(rfqLineGroups);
			rfqBidWorksheet.setRfqQuestionGroupList(rfqQuestionGroups);

			result = rfqBidWorksheet;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result  ;
	}
}
