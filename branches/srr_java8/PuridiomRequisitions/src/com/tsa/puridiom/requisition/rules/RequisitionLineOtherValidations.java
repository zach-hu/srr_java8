package com.tsa.puridiom.requisition.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.DocAttachment;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Matthew
 */
public class RequisitionLineOtherValidations extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			RequisitionHeader header = (RequisitionHeader) incomingRequest.get("header");
			List lineItems = (List) incomingRequest.get("lineitems");
			List<DocAttachment> attachmentList = (List<DocAttachment>) incomingRequest.get("attachments");

			boolean validTraceabilityComment = true;
			boolean validShelfLifeComment = true;
			boolean validProcurementLevelAfter530 = true;
			boolean validFunctionalClass = true;
			boolean validSecurityClearance = true;

			if (header.getRequisitionType().equalsIgnoreCase("M") && lineItems != null)
			{
				if(!Utility.isEmpty(header.getUdf5Code()) && "yes".equalsIgnoreCase(header.getUdf5Code()))
				{
					if (attachmentList.isEmpty())
					{
						validSecurityClearance = false;
					}
					else
					{
						boolean commentThere = false;
						for (int j = 0; j < attachmentList.size(); j++)
						{
							if ("DOE_F470.1".equals(attachmentList.get(j).getDocTitle()))
							{
								commentThere = true;
							}
						}
						if (!commentThere)
							validSecurityClearance = false;
					}
				}

				for (int i = 0; i < lineItems.size(); i++) {
					RequisitionLine requisitionLine = (RequisitionLine) lineItems.get(i);

					if ("A".equalsIgnoreCase(requisitionLine.getUdf5Code()) || "B".equalsIgnoreCase(requisitionLine.getUdf5Code()))
					{
						String commentTitle = "";
						List<DocComment> docCommentList = requisitionLine.getDocCommentList();
						if (docCommentList.isEmpty())
						{
							validTraceabilityComment = false;
						}
						else
						{
							boolean commentThere = false;
							for (int j = 0; j < docCommentList.size(); j++)
							{
								commentTitle = docCommentList.get(j).getCommentId();
								if (commentTitle.indexOf("R_T") == 0)
								{
									commentThere = true;
								}
							}
							if (!commentThere)
								validTraceabilityComment = false;
						}
					}

					if ("Y".equals(requisitionLine.getShelfLifeRqd()))
					{
						String commentTitle = "";
						List<DocComment> docCommentList = requisitionLine.getDocCommentList();
						if (docCommentList.isEmpty())
						{
							validShelfLifeComment = false;
						}
						else
						{
							boolean commentThere = false;
							for (int j = 0; j < docCommentList.size(); j++)
							{
								commentTitle = docCommentList.get(j).getCommentId();
								if (commentTitle.indexOf("RSL_") == 0)
								{
									commentThere = true;
								}
							}
							if (!commentThere)
								validShelfLifeComment = false;
						}
					}

					int status = 0;
					try {
						status = Integer.parseInt(header.getStatus());
					}
					catch (Exception e)
					{
						Log.debug(this, "Status was not parsed into int error " + e.getMessage());
					}

					if (Utility.isEmpty(header.getBuyer()))
					{
						if("ER".equalsIgnoreCase(requisitionLine.getUdf2Code()) || "ER".equalsIgnoreCase(header.getPriorityCode()))
						{
							validProcurementLevelAfter530 = false;
						}

						if("ER".equalsIgnoreCase(requisitionLine.getUdf3Code()) || "ER".equalsIgnoreCase(header.getUdf7Code()))
						{
							validFunctionalClass = false;
						}
					}

					if(!Utility.isEmpty(header.getRequisitionerCode()) && !Utility.isEmpty(header.getOwner()))
					{
						if (header.getRequisitionerCode().equalsIgnoreCase(header.getOwner()))
						{
							if (status > 530)
							{
								if("ER".equalsIgnoreCase(requisitionLine.getUdf2Code()) || "ER".equalsIgnoreCase(header.getPriorityCode()))
								{
									validProcurementLevelAfter530 = false;
								}

								if("ER".equalsIgnoreCase(requisitionLine.getUdf3Code()) || "ER".equalsIgnoreCase(header.getUdf7Code()))
								{
									validFunctionalClass = false;
								}
							}
						}
					}
				}
			}

			incomingRequest.put("validTraceabilityComment", String.valueOf(validTraceabilityComment));
			incomingRequest.put("validShelfLifeComment", String.valueOf(validShelfLifeComment));
			incomingRequest.put("validProcurementLevelAfter530", String.valueOf(validProcurementLevelAfter530));
			incomingRequest.put("validFunctionalClass", String.valueOf(validFunctionalClass));
			incomingRequest.put("validSecurityClearance", String.valueOf(validSecurityClearance));
		}
		catch(Exception e)
		{
			Log.error(this, "RequisitionLineOtherValidations error " + e.getMessage());

			throw e;
		}
		return result;
	}
}