package com.tsa.puridiom.requisition.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.DocComment;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Matthew
 */
public class RequisitionValidMarkTag extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			RequisitionHeader header = (RequisitionHeader) incomingRequest.get("header");
			List<RequisitionLine> lineItems = (List<RequisitionLine>) incomingRequest.get("lineitems");
			List<DocComment> commentList = (List<DocComment>) incomingRequest.get("comments");

			boolean validMaterialLocation = true;
			boolean validMarkComment = true;
			boolean validTagComment = true;
			boolean validHTagComment = true;
			boolean validLineAMS = true;
			boolean validHeaderAMS = true;
			boolean validAMS = true;
			boolean validationRequisitionLine = false;
			if(incomingRequest.containsKey("valType") && ((String)incomingRequest.get("valType")).equals("REQUISITIONLINE"))
			{
				validationRequisitionLine = true;
			}

			if ((header.getRequisitionType().equalsIgnoreCase("M") || header.getRequisitionType().equalsIgnoreCase("P")) && lineItems != null)
			{
				if(!validationRequisitionLine)
				{
				validHeaderAMS = this.ValidateAMS(header.getAccountList(), incomingRequest);
				}
				for (int i = 0; i < lineItems.size(); i++) {
					RequisitionLine requisitionLine = (RequisitionLine) lineItems.get(i);

					if(!"N".equals(requisitionLine.getAsset()))
					{
						if(Utility.isEmpty(requisitionLine.getUdf10Code()))
						{
							validMaterialLocation = false;
						}
					}

					if("M".equals(requisitionLine.getAsset()))
					{
						String commentTitle = "";
						List<DocComment> docCommentList = requisitionLine.getDocCommentList();
						if (docCommentList.isEmpty())
						{
							validMarkComment = false;
						}
						else
						{
							boolean commentThere = false;
							for (int j = 0; j < docCommentList.size(); j++)
							{
								commentTitle = docCommentList.get(j).getCommentId();
								if (commentTitle.indexOf("PMT_MARK") == 0)
								{
									commentThere = true;
								}
							}
							if (!commentThere)
								validMarkComment = false;
						}
					}

					if("T".equals(requisitionLine.getAsset()))
					{
						String commentTitle = "";
						List<DocComment> docCommentList = requisitionLine.getDocCommentList();
						List<Account> accountList = requisitionLine.getAccountList();
						if (docCommentList.isEmpty())
						{
							validTagComment = false;
						}
						else
						{
							boolean commentThere = false;
							for (int j = 0; j < docCommentList.size(); j++)
							{
								commentTitle = docCommentList.get(j).getCommentId();
								if (commentTitle.indexOf("RMT_TAG") == 0)
								{
									commentThere = true;
								}
							}
							if (!commentThere)
								validTagComment = false;
						}
						if (!accountList.isEmpty())
						{
							validLineAMS = this.ValidateAMS(accountList, incomingRequest);
						}
						else {
							if (!validHeaderAMS)
								validAMS = false;
						}
						if (!validLineAMS)
							validAMS = false;
					}

					if("H".equals(requisitionLine.getAsset()))
					{
						String commentTitle = "";
						List<DocComment> docCommentList = requisitionLine.getDocCommentList();
						List<Account> accountList = requisitionLine.getAccountList();
						if (docCommentList.isEmpty())
						{
							validHTagComment = false;
						}
						else
						{
							boolean commentThere = false;
							for (int j = 0; j < docCommentList.size(); j++)
							{
								commentTitle = docCommentList.get(j).getCommentId();
								if (commentTitle.indexOf("RMT_HIGHRISK") == 0)
								{
									commentThere = true;
								}
							}
							if (!commentThere)
								validHTagComment = false;
						}
						if (!accountList.isEmpty())
						{
							validLineAMS = this.ValidateAMS(accountList, incomingRequest);
						}
						else {
							if (!validHeaderAMS)
								validAMS = false;
						}
						if (!validLineAMS)
							validAMS = false;
					}

				}
			}

			incomingRequest.put("validMaterialLocation", String.valueOf(validMaterialLocation));
			incomingRequest.put("validMarkComment", String.valueOf(validMarkComment));
			incomingRequest.put("validTagComment", String.valueOf(validTagComment));
			incomingRequest.put("validHTagComment", String.valueOf(validHTagComment));
			incomingRequest.put("validAMS", String.valueOf(validAMS));
		}
		catch(Exception e)
		{
			Log.error(this, "RequisitionValidMarkTag error " + e.getMessage());

			throw e;
		}
		return result;
	}

	public boolean ValidateAMS(List<Account> accountList, Map incomingRequest)
	{
		boolean validAMS = true;

		if (!accountList.isEmpty())
		{
			for (int i = 0; i < accountList.size(); i++)
			{
				Account account = accountList.get(i);
				if (Utility.isEmpty(account.getFld4()) || Utility.isEmpty(account.getFld5()))
				{
					validAMS = false;
				}
			}
		}
		return validAMS;
	}
}