package com.tsa.puridiom.invitem.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InvItemRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvItem as invitem where 1=1 ");
		if(incomingRequest.containsKey("InvItem_itemNumber"))
		{
			String itemNumber = (String) incomingRequest.get("InvItem_itemNumber");
			queryString.append(" AND invitem.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("InvItem_commodity"))
		{
			String commodity = (String) incomingRequest.get("InvItem_commodity");
			queryString.append(" AND invitem.commodity = '" + commodity + "'");
		}
		if(incomingRequest.containsKey("InvItem_icText"))
		{
			String icText = (String) incomingRequest.get("InvItem_icText");
			queryString.append(" AND invitem.icText = '" + icText + "'");
		}
		if(incomingRequest.containsKey("InvItem_unitOfOrder"))
		{
			String unitOfOrder = (String) incomingRequest.get("InvItem_unitOfOrder");
			queryString.append(" AND invitem.unitOfOrder = '" + unitOfOrder + "'");
		}
		if(incomingRequest.containsKey("InvItem_cost"))
		{
			String cost = (String) incomingRequest.get("InvItem_cost");
			queryString.append(" AND invitem.cost = '" + cost + "'");
		}
		if(incomingRequest.containsKey("InvItem_taxable"))
		{
			String taxable = (String) incomingRequest.get("InvItem_taxable");
			queryString.append(" AND invitem.taxable = '" + taxable + "'");
		}
		if(incomingRequest.containsKey("InvItem_abcCode"))
		{
			String abcCode = (String) incomingRequest.get("InvItem_abcCode");
			queryString.append(" AND invitem.abcCode = '" + abcCode + "'");
		}
		if(incomingRequest.containsKey("InvItem_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("InvItem_dateEntered");
			queryString.append(" AND invitem.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("InvItem_dateExpires"))
		{
			String dateExpires = (String) incomingRequest.get("InvItem_dateExpires");
			queryString.append(" AND invitem.dateExpires = '" + dateExpires + "'");
		}
		if(incomingRequest.containsKey("InvItem_status"))
		{
			String status = (String) incomingRequest.get("InvItem_status");
			queryString.append(" AND invitem.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("InvItem_owner"))
		{
			String owner = (String) incomingRequest.get("InvItem_owner");
			queryString.append(" AND invitem.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("InvItem_source"))
		{
			String source = (String) incomingRequest.get("InvItem_source");
			queryString.append(" AND invitem.source = '" + source + "'");
		}
		if(incomingRequest.containsKey("InvItem_unitOfIssue"))
		{
			String unitOfIssue = (String) incomingRequest.get("InvItem_unitOfIssue");
			queryString.append(" AND invitem.unitOfIssue = '" + unitOfIssue + "'");
		}
		if(incomingRequest.containsKey("InvItem_factor"))
		{
			String factor = (String) incomingRequest.get("InvItem_factor");
			queryString.append(" AND invitem.factor = '" + factor + "'");
		}
		if(incomingRequest.containsKey("InvItem_averageCost"))
		{
			String averageCost = (String) incomingRequest.get("InvItem_averageCost");
			queryString.append(" AND invitem.averageCost = '" + averageCost + "'");
		}
		if(incomingRequest.containsKey("InvItem_udf1Code"))
		{
			String udf1Code = (String) incomingRequest.get("InvItem_udf1Code");
			queryString.append(" AND invitem.udf1Code = '" + udf1Code + "'");
		}
		if(incomingRequest.containsKey("InvItem_udf2Code"))
		{
			String udf2Code = (String) incomingRequest.get("InvItem_udf2Code");
			queryString.append(" AND invitem.udf2Code = '" + udf2Code + "'");
		}
		if(incomingRequest.containsKey("InvItem_udf3Code"))
		{
			String udf3Code = (String) incomingRequest.get("InvItem_udf3Code");
			queryString.append(" AND invitem.udf3Code = '" + udf3Code + "'");
		}
		if(incomingRequest.containsKey("InvItem_udf4Code"))
		{
			String udf4Code = (String) incomingRequest.get("InvItem_udf4Code");
			queryString.append(" AND invitem.udf4Code = '" + udf4Code + "'");
		}
		if(incomingRequest.containsKey("InvItem_udf5Code"))
		{
			String udf5Code = (String) incomingRequest.get("InvItem_udf5Code");
			queryString.append(" AND invitem.udf5Code = '" + udf5Code + "'");
		}
		if(incomingRequest.containsKey("InvItem_udf6Code"))
		{
			String udf6Code = (String) incomingRequest.get("InvItem_udf6Code");
			queryString.append(" AND invitem.udf6Code = '" + udf6Code + "'");
		}
		if(incomingRequest.containsKey("InvItem_udf7Code"))
		{
			String udf7Code = (String) incomingRequest.get("InvItem_udf7Code");
			queryString.append(" AND invitem.udf7Code = '" + udf7Code + "'");
		}
		if(incomingRequest.containsKey("InvItem_udf8Code"))
		{
			String udf8Code = (String) incomingRequest.get("InvItem_udf8Code");
			queryString.append(" AND invitem.udf8Code = '" + udf8Code + "'");
		}
		if(incomingRequest.containsKey("InvItem_udf9Code"))
		{
			String udf9Code = (String) incomingRequest.get("InvItem_udf9Code");
			queryString.append(" AND invitem.udf9Code = '" + udf9Code + "'");
		}
		if(incomingRequest.containsKey("InvItem_udf10Code"))
		{
			String udf10Code = (String) incomingRequest.get("InvItem_udf10Code");
			queryString.append(" AND invitem.udf10Code = '" + udf10Code + "'");
		}
		if(incomingRequest.containsKey("InvItem_udf11Code"))
		{
			String udf11Code = (String) incomingRequest.get("InvItem_udf11Code");
			queryString.append(" AND invitem.udf11Code = '" + udf11Code + "'");
		}
		if(incomingRequest.containsKey("InvItem_udf12Code"))
		{
			String udf12Code = (String) incomingRequest.get("InvItem_udf12Code");
			queryString.append(" AND invitem.udf12Code = '" + udf12Code + "'");
		}
		if(incomingRequest.containsKey("InvItem_udf13Code"))
		{
			String udf13Code = (String) incomingRequest.get("InvItem_udf13Code");
			queryString.append(" AND invitem.udf13Code = '" + udf13Code + "'");
		}
		if(incomingRequest.containsKey("InvItem_udf14Code"))
		{
			String udf14Code = (String) incomingRequest.get("InvItem_udf14Code");
			queryString.append(" AND invitem.udf14Code = '" + udf14Code + "'");
		}
		if(incomingRequest.containsKey("InvItem_udf15Code"))
		{
			String udf15Code = (String) incomingRequest.get("InvItem_udf15Code");
			queryString.append(" AND invitem.udf15Code = '" + udf15Code + "'");
		}
		if(incomingRequest.containsKey("InvItem_variance"))
		{
			String variance = (String) incomingRequest.get("InvItem_variance");
			queryString.append(" AND invitem.variance = '" + variance + "'");
		}
		if(incomingRequest.containsKey("InvItem_imageFile"))
		{
			String imageFile = (String) incomingRequest.get("InvItem_imageFile");
			queryString.append(" AND invitem.imageFile = '" + imageFile + "'");
		}
		if(incomingRequest.containsKey("InvItem_issueCost"))
		{
			String issueCost = (String) incomingRequest.get("InvItem_issueCost");
			queryString.append(" AND invitem.issueCost = '" + issueCost + "'");
		}
		if(incomingRequest.containsKey("InvItem_lastCost"))
		{
			String lastCost = (String) incomingRequest.get("InvItem_lastCost");
			queryString.append(" AND invitem.lastCost = '" + lastCost + "'");
		}
		if(incomingRequest.containsKey("InvItem_icNotes"))
		{
			String icNotes = (String) incomingRequest.get("InvItem_icNotes");
			queryString.append(" AND invitem.icNotes = '" + icNotes + "'");
		}
		if(incomingRequest.containsKey("InvItem_poNumber"))
		{
			String poNumber = (String) incomingRequest.get("InvItem_poNumber");
			queryString.append(" AND invitem.poNumber = '" + poNumber + "'");
		}
		if(incomingRequest.containsKey("InvItem_kit"))
		{
			String kit = (String) incomingRequest.get("InvItem_kit");
			queryString.append(" AND invitem.kit = '" + kit + "'");
		}
		if(incomingRequest.containsKey("InvItem_mohMonths"))
		{
			String mohMonths = (String) incomingRequest.get("InvItem_mohMonths");
			queryString.append(" AND invitem.mohMonths = '" + mohMonths + "'");
		}
		if(incomingRequest.containsKey("InvItem_eoqMonths"))
		{
			String eoqMonths = (String) incomingRequest.get("InvItem_eoqMonths");
			queryString.append(" AND invitem.eoqMonths = '" + eoqMonths + "'");
		}
		if(incomingRequest.containsKey("InvItem_mohTot"))
		{
			String mohTot = (String) incomingRequest.get("InvItem_mohTot");
			queryString.append(" AND invitem.mohTot = '" + mohTot + "'");
		}
		if(incomingRequest.containsKey("InvItem_eoqTot"))
		{
			String eoqTot = (String) incomingRequest.get("InvItem_eoqTot");
			queryString.append(" AND invitem.eoqTot = '" + eoqTot + "'");
		}
		if(incomingRequest.containsKey("InvItem_chargable"))
		{
			String chargable = (String) incomingRequest.get("InvItem_chargable");
			queryString.append(" AND invitem.chargable = '" + chargable + "'");
		}
		if(incomingRequest.containsKey("InvItem_maxReqQty"))
		{
			String maxReqQty = (String) incomingRequest.get("InvItem_maxReqQty");
			queryString.append(" AND invitem.maxReqQty = '" + maxReqQty + "'");
		}
		if(incomingRequest.containsKey("InvItem_actionCode"))
		{
			String actionCode = (String) incomingRequest.get("InvItem_actionCode");
			queryString.append(" AND invitem.actionCode = '" + actionCode + "'");
		}
		if(incomingRequest.containsKey("InvItem_restrictedItem"))
		{
			String restrictedItem = (String) incomingRequest.get("InvItem_restrictedItem");
			queryString.append(" AND invitem.restrictedItem = '" + restrictedItem + "'");
		}
		if(incomingRequest.containsKey("InvItem_sumQtyOh"))
		{
			String sumQtyOh = (String) incomingRequest.get("InvItem_sumQtyOh");
			queryString.append(" AND invitem.sumQtyOh = '" + sumQtyOh + "'");
		}
		if(incomingRequest.containsKey("InvItem_sumBackorder"))
		{
			String sumBackorder = (String) incomingRequest.get("InvItem_sumBackorder");
			queryString.append(" AND invitem.sumBackorder = '" + sumBackorder + "'");
		}
		if(incomingRequest.containsKey("InvItem_sumUsage"))
		{
			String sumUsage = (String) incomingRequest.get("InvItem_sumUsage");
			queryString.append(" AND invitem.sumUsage = '" + sumUsage + "'");
		}
		if(incomingRequest.containsKey("InvItem_sumEoq"))
		{
			String sumEoq = (String) incomingRequest.get("InvItem_sumEoq");
			queryString.append(" AND invitem.sumEoq = '" + sumEoq + "'");
		}
		if(incomingRequest.containsKey("InvItem_sumMinOh"))
		{
			String sumMinOh = (String) incomingRequest.get("InvItem_sumMinOh");
			queryString.append(" AND invitem.sumMinOh = '" + sumMinOh + "'");
		}
		if(incomingRequest.containsKey("InvItem_sumQtyOrder"))
		{
			String sumQtyOrder = (String) incomingRequest.get("InvItem_sumQtyOrder");
			queryString.append(" AND invitem.sumQtyOrder = '" + sumQtyOrder + "'");
		}
		if(incomingRequest.containsKey("InvItem_lastPo"))
		{
			String lastPo = (String) incomingRequest.get("InvItem_lastPo");
			queryString.append(" AND invitem.lastPo = '" + lastPo + "'");
		}
		if(incomingRequest.containsKey("InvItem_lastPoDate"))
		{
			String lastPoDate = (String) incomingRequest.get("InvItem_lastPoDate");
			queryString.append(" AND invitem.lastPoDate = '" + lastPoDate + "'");
		}
		if(incomingRequest.containsKey("InvItem_assetCode"))
		{
			String assetCode = (String) incomingRequest.get("InvItem_assetCode");
			queryString.append(" AND invitem.assetCode = '" + assetCode + "'");
		}
		if(incomingRequest.containsKey("InvItem_minReqQty"))
		{
			String minReqQty = (String) incomingRequest.get("InvItem_minReqQty");
			queryString.append(" AND invitem.minReqQty = '" + minReqQty + "'");
		}
		if(incomingRequest.containsKey("InvItem_receiptRequired"))
		{
			String receiptRequired = (String) incomingRequest.get("InvItem_receiptRequired");
			queryString.append(" AND invitem.receiptRequired = '" + receiptRequired + "'");
		}
		if(incomingRequest.containsKey("InvItem_pullIncrement"))
		{
			String pullIncrement = (String) incomingRequest.get("InvItem_pullIncrement");
			queryString.append(" AND invitem.pullIncrement = '" + pullIncrement + "'");
		}
		if(incomingRequest.containsKey("InvItem_usageRecalc"))
		{
			String usageRecalc = (String) incomingRequest.get("InvItem_usageRecalc");
			queryString.append(" AND invitem.usageRecalc = '" + usageRecalc + "'");
		}
		if(incomingRequest.containsKey("InvItem_buyerCode"))
		{
			String buyerCode = (String) incomingRequest.get("InvItem_buyerCode");
			queryString.append(" AND invitem.buyerCode = '" + buyerCode + "'");
		}
		if(incomingRequest.containsKey("InvItem_aribaExport"))
		{
			String aribaExport = (String) incomingRequest.get("InvItem_aribaExport");
			queryString.append(" AND invitem.aribaExport = '" + aribaExport + "'");
		}
		if(incomingRequest.containsKey("InvItem_appointedFlag"))
		{
			String appointedFlag = (String) incomingRequest.get("InvItem_appointedFlag");
			queryString.append(" AND invitem.appointedFlag = '" + appointedFlag + "'");
		}
		if(incomingRequest.containsKey("InvItem_ssInterface"))
		{
			String ssInterface = (String) incomingRequest.get("InvItem_ssInterface");
			queryString.append(" AND invitem.ssInterface = '" + ssInterface + "'");
		}
		if(incomingRequest.containsKey("InvItem_managedBy"))
		{
			String managedBy = (String) incomingRequest.get("InvItem_managedBy");
			queryString.append(" AND invitem.managedBy = '" + managedBy + "'");
		}
		if(incomingRequest.containsKey("InvItem_description"))
		{
			String description = (String) incomingRequest.get("InvItem_description");
			queryString.append(" AND invitem.description like '%" + description + "%'");
		}

		if (incomingRequest.containsKey("InvItem_modelNumber"))
		{
			String modelNumber = (String ) incomingRequest.get("InvItem_modelNumber");
			queryString.append(" AND invitem.modelNumber = '" + modelNumber + "'");
		}

		if (incomingRequest.containsKey("InvItem_mfgName"))
		{
			String mfgName = (String ) incomingRequest.get("InvItem_mfgName");
			queryString.append(" AND invitem.mfgName = '" + mfgName + "'");
		}

		if (incomingRequest.containsKey("InvItem_nsnNumber"))
		{
			String nsnNumber = (String ) incomingRequest.get("InvItem_nsnNumber");
			queryString.append(" AND invitem.nsnNumber = '" + nsnNumber + "'");
		}

		if (incomingRequest.containsKey("InvItem_gfpProperty"))
		{
			String gfpProperty = (String ) incomingRequest.get("InvItem_gfpProperty");
			queryString.append(" AND invitem.gfpProperty = '" + gfpProperty + "'");
		}

		if (incomingRequest.containsKey("InvItem_fgpProperty"))
		{
			String fgpProperty = (String ) incomingRequest.get("InvItem_fgpProperty");
			queryString.append(" AND invitem.fgpProperty = '" + fgpProperty + "'");
		}

		if (incomingRequest.containsKey("InvItem_capProperty"))
		{
			String capProperty = (String ) incomingRequest.get("InvItem_capProperty");
			queryString.append(" AND invitem.capProperty = '" + capProperty + "'");
		}

		if (incomingRequest.containsKey("InvItem_itemType"))
		{
			String itemType = (String ) incomingRequest.get("InvItem_itemType");
			queryString.append(" AND invitem.itemType = '" + itemType + "'");
		}
		if (incomingRequest.containsKey("InvItem_barcodeData"))
		{
			String barcodeData = (String ) incomingRequest.get("InvItem_barcodeData");
			queryString.append(" AND invitem.barcodeData = '" + barcodeData + "'");
		}
		if (incomingRequest.containsKey("InvItem_unitOfUsage"))
		{
			String unitOfUsage = (String ) incomingRequest.get("InvItem_unitOfUsage");
			queryString.append(" AND invitem.unitOfUsage = '" + unitOfUsage + "'");
		}
		if (incomingRequest.containsKey("InvItem_dfltMethod"))
		{
			String dfltMethod = (String ) incomingRequest.get("InvItem_dfltMethod");
			queryString.append(" AND invitem.dfltMethod = '" + dfltMethod + "'");
		}
		if (incomingRequest.containsKey("InvItem_drawingNo"))
		{
			String drawingNo = (String ) incomingRequest.get("InvItem_drawingNo");
			queryString.append(" AND invitem.drawingNo = '" + drawingNo + "'");
		}
		if (incomingRequest.containsKey("InvItem_makeToOrder"))
		{
			String makeToOrder = (String ) incomingRequest.get("InvItem_makeToOrder");
			queryString.append(" AND invitem.makeToOrder = '" + makeToOrder + "'");
		}
		if(incomingRequest.containsKey("InvItem_reorderLevel"))
		{
			String reorderLevel = (String) incomingRequest.get("InvItem_reorderLevel");
			queryString.append(" AND invitem.reorderLevel = '" + reorderLevel + "'");
		}
		if(incomingRequest.containsKey("InvItem_icHeader"))
		{
			String icHeader = (String) incomingRequest.get("InvItem_icHeader");
			queryString.append(" AND invitem.icHeader = '" + icHeader + "'");
		}
		if(incomingRequest.containsKey("InvItem_critSparePart"))
		{
			String critSparePart = (String) incomingRequest.get("InvItem_critSparePart");
			queryString.append(" AND invitem.critSparePart ='" + critSparePart + "'");
		}


		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}