package com.tsa.puridiom.invitem.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvItemSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvItem invItem = (InvItem) incomingRequest.get("invItem");
			String	itemAction = HiltonUtility.ckNull((String) incomingRequest.get("historyStatus"));
			if (invItem == null)
			{
				invItem = new InvItem();
			}

			if (incomingRequest.containsKey("InvItem_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("InvItem_itemNumber");
				invItem.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("InvItem_itemNumberSuperceded"))
			{
				String itemNumberSuperceded = (String ) incomingRequest.get("InvItem_itemNumberSuperceded");
				invItem.setItemNumberSuperceded(itemNumberSuperceded);
			}
			if (incomingRequest.containsKey("InvItem_commodity"))
			{
				String commodity = (String ) incomingRequest.get("InvItem_commodity");
				invItem.setCommodity(commodity);
			}
			if (incomingRequest.containsKey("InvItem_icText"))
			{
				String icTextString = (String) incomingRequest.get("InvItem_icText");
				if (Utility.isEmpty(icTextString))
				{
					icTextString = "0";
				}
				BigDecimal icText = new BigDecimal ( icTextString );
				invItem.setIcText(icText);
			}
			if (incomingRequest.containsKey("InvItem_unitOfOrder"))
			{
				String unitOfOrder = (String ) incomingRequest.get("InvItem_unitOfOrder");
				invItem.setUnitOfOrder(unitOfOrder);
			}
			if (incomingRequest.containsKey("InvItem_cost"))
			{
				String costString = (String) incomingRequest.get("InvItem_cost");
				if (Utility.isEmpty(costString))
				{
					costString = "0";
				}
				BigDecimal cost = new BigDecimal ( costString );
				invItem.setCost(cost);
			}
			if (incomingRequest.containsKey("InvItem_taxable"))
			{
				String taxable = (String ) incomingRequest.get("InvItem_taxable");
				invItem.setTaxable(taxable);
			}
			if (incomingRequest.containsKey("InvItem_abcCode"))
			{
				String abcCode = (String ) incomingRequest.get("InvItem_abcCode");
				invItem.setAbcCode(abcCode);
			}
			if (incomingRequest.containsKey("InvItem_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("InvItem_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				invItem.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("InvItem_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("InvItem_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				invItem.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("InvItem_status"))
			{
				String status = (String ) incomingRequest.get("InvItem_status");
				invItem.setStatus(status);
			}
			if (incomingRequest.containsKey("InvItem_owner"))
			{
				String owner = (String ) incomingRequest.get("InvItem_owner");
				invItem.setOwner(owner);
			}
			if (incomingRequest.containsKey("InvItem_source"))
			{
				String source = (String ) incomingRequest.get("InvItem_source");
				invItem.setSource(source);
			}
			if (incomingRequest.containsKey("InvItem_unitOfIssue"))
			{
				String unitOfIssue = (String ) incomingRequest.get("InvItem_unitOfIssue");
				invItem.setUnitOfIssue(unitOfIssue);
			}
			if (incomingRequest.containsKey("InvItem_factor"))
			{
				String factorString = (String) incomingRequest.get("InvItem_factor");
				if (Utility.isEmpty(factorString))
				{
					factorString = "0";
				}
				BigDecimal factor = new BigDecimal ( factorString );
				invItem.setFactor(factor);
			}
			if (incomingRequest.containsKey("InvItem_averageCost"))
			{
				String averageCostString = (String) incomingRequest.get("InvItem_averageCost");
				if (Utility.isEmpty(averageCostString))
				{
					averageCostString = "0";
				}
				BigDecimal averageCost = new BigDecimal ( averageCostString );
				invItem.setAverageCost(averageCost);
			}
			if (incomingRequest.containsKey("InvItem_udf1Code"))
			{
				String udf1Code = (String ) incomingRequest.get("InvItem_udf1Code");
				invItem.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("InvItem_udf2Code"))
			{
				String udf2Code = (String ) incomingRequest.get("InvItem_udf2Code");
				invItem.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("InvItem_udf3Code"))
			{
				String udf3Code = (String ) incomingRequest.get("InvItem_udf3Code");
				invItem.setUdf3Code(udf3Code);
			}
			if (incomingRequest.containsKey("InvItem_udf4Code"))
			{
				String udf4Code = (String ) incomingRequest.get("InvItem_udf4Code");
				invItem.setUdf4Code(udf4Code);
			}
			if (incomingRequest.containsKey("InvItem_udf5Code"))
			{
				String udf5Code = (String ) incomingRequest.get("InvItem_udf5Code");
				invItem.setUdf5Code(udf5Code);
			}
			if (incomingRequest.containsKey("InvItem_udf6Code"))
			{
				String udf6Code = (String ) incomingRequest.get("InvItem_udf6Code");
				invItem.setUdf6Code(udf6Code);
			}
			if (incomingRequest.containsKey("InvItem_udf7Code"))
			{
				String udf7Code = (String ) incomingRequest.get("InvItem_udf7Code");
				invItem.setUdf7Code(udf7Code);
			}
			if (incomingRequest.containsKey("InvItem_udf8Code"))
			{
				String udf8Code = (String ) incomingRequest.get("InvItem_udf8Code");
				invItem.setUdf8Code(udf8Code);
			}
			if (incomingRequest.containsKey("InvItem_udf9Code"))
			{
				String udf9Code = (String ) incomingRequest.get("InvItem_udf9Code");
				invItem.setUdf9Code(udf9Code);
			}
			if (incomingRequest.containsKey("InvItem_udf10Code"))
			{
				String udf10Code = (String ) incomingRequest.get("InvItem_udf10Code");
				invItem.setUdf10Code(udf10Code);
			}
			if (incomingRequest.containsKey("InvItem_udf11Code"))
			{
				String udf11Code = (String ) incomingRequest.get("InvItem_udf11Code");
				invItem.setUdf11Code(udf11Code);
			}
			if (incomingRequest.containsKey("InvItem_udf12Code"))
			{
				String udf12Code = (String ) incomingRequest.get("InvItem_udf12Code");
				invItem.setUdf12Code(udf12Code);
			}
			if (incomingRequest.containsKey("InvItem_udf13Code"))
			{
				String udf13Code = (String ) incomingRequest.get("InvItem_udf13Code");
				invItem.setUdf13Code(udf13Code);
			}
			if (incomingRequest.containsKey("InvItem_udf14Code"))
			{
				String udf14Code = (String ) incomingRequest.get("InvItem_udf14Code");
				invItem.setUdf14Code(udf14Code);
			}
			if (incomingRequest.containsKey("InvItem_udf15Code"))
			{
				String udf15Code = (String ) incomingRequest.get("InvItem_udf15Code");
				invItem.setUdf15Code(udf15Code);
			}
			if (incomingRequest.containsKey("InvItem_variance"))
			{
				String varianceString = (String) incomingRequest.get("InvItem_variance");
				if (Utility.isEmpty(varianceString))
				{
					varianceString = "0";
				}
				BigDecimal variance = new BigDecimal ( varianceString );
				invItem.setVariance(variance);
			}
			if (incomingRequest.containsKey("InvItem_imageFile"))
			{
				String imageFile = (String ) incomingRequest.get("InvItem_imageFile");
				invItem.setImageFile(imageFile);
			}
			if (incomingRequest.containsKey("InvItem_issueCost"))
			{
				String issueCostString = (String) incomingRequest.get("InvItem_issueCost");
				if (Utility.isEmpty(issueCostString))
				{
					issueCostString = "0";
				}
				BigDecimal issueCost = new BigDecimal ( issueCostString );
				invItem.setIssueCost(issueCost);
			}
			if (incomingRequest.containsKey("InvItem_lastCost"))
			{
				String lastCostString = (String) incomingRequest.get("InvItem_lastCost");
				if (Utility.isEmpty(lastCostString))
				{
					lastCostString = "0";
				}
				BigDecimal lastCost = new BigDecimal ( lastCostString );
				invItem.setLastCost(lastCost);
			}
			if (incomingRequest.containsKey("InvItem_icNotes"))
			{
				String icNotesString = (String) incomingRequest.get("InvItem_icNotes");
				if (Utility.isEmpty(icNotesString))
				{
					icNotesString = "0";
				}
				BigDecimal icNotes = new BigDecimal ( icNotesString );
				invItem.setIcNotes(icNotes);
			}
			if (incomingRequest.containsKey("InvItem_poNumber"))
			{
				String poNumber = (String ) incomingRequest.get("InvItem_poNumber");
				invItem.setPoNumber(poNumber);
			}
			if (incomingRequest.containsKey("InvItem_kit"))
			{
				String kit = (String ) incomingRequest.get("InvItem_kit");
				invItem.setKit(kit);
			}
			if (incomingRequest.containsKey("InvItem_mohMonths"))
			{
				String mohMonthsString = (String) incomingRequest.get("InvItem_mohMonths");
				if (Utility.isEmpty(mohMonthsString))
				{
					mohMonthsString = "0";
				}
				BigDecimal mohMonths = new BigDecimal ( mohMonthsString );
				invItem.setMohMonths(mohMonths);
			}
			if (incomingRequest.containsKey("InvItem_eoqMonths"))
			{
				String eoqMonthsString = (String) incomingRequest.get("InvItem_eoqMonths");
				if (Utility.isEmpty(eoqMonthsString))
				{
					eoqMonthsString = "0";
				}
				BigDecimal eoqMonths = new BigDecimal ( eoqMonthsString );
				invItem.setEoqMonths(eoqMonths);
			}
			if (incomingRequest.containsKey("InvItem_mohTot"))
			{
				String mohTotString = (String) incomingRequest.get("InvItem_mohTot");
				if (Utility.isEmpty(mohTotString))
				{
					mohTotString = "0";
				}
				BigDecimal mohTot = new BigDecimal ( mohTotString );
				invItem.setMohTot(mohTot);
			}
			if (incomingRequest.containsKey("InvItem_eoqTot"))
			{
				String eoqTotString = (String) incomingRequest.get("InvItem_eoqTot");
				if (Utility.isEmpty(eoqTotString))
				{
					eoqTotString = "0";
				}
				BigDecimal eoqTot = new BigDecimal ( eoqTotString );
				invItem.setEoqTot(eoqTot);
			}
			if (incomingRequest.containsKey("InvItem_chargable"))
			{
				String chargable = (String ) incomingRequest.get("InvItem_chargable");
				invItem.setChargable(chargable);
			}
			if (incomingRequest.containsKey("InvItem_maxReqQty"))
			{
				String maxReqQtyString = (String) incomingRequest.get("InvItem_maxReqQty");
				if (Utility.isEmpty(maxReqQtyString))
				{
					maxReqQtyString = "0";
				}
				BigDecimal maxReqQty = new BigDecimal ( maxReqQtyString );
				invItem.setMaxReqQty(maxReqQty);
			}
			if (incomingRequest.containsKey("InvItem_actionCode"))
			{
				String actionCode = (String ) incomingRequest.get("InvItem_actionCode");
				invItem.setActionCode(actionCode);
			}
			if (incomingRequest.containsKey("InvItem_restrictedItem"))
			{
				String restrictedItem = (String ) incomingRequest.get("InvItem_restrictedItem");
				invItem.setRestrictedItem(restrictedItem);
			}
			if (incomingRequest.containsKey("InvItem_sumQtyOh"))
			{
				String sumQtyOhString = (String) incomingRequest.get("InvItem_sumQtyOh");
				if (Utility.isEmpty(sumQtyOhString))
				{
					sumQtyOhString = "0";
				}
				BigDecimal sumQtyOh = new BigDecimal ( sumQtyOhString );
				invItem.setSumQtyOh(sumQtyOh);
			}
			if (incomingRequest.containsKey("InvItem_sumBackorder"))
			{
				String sumBackorderString = (String) incomingRequest.get("InvItem_sumBackorder");
				if (Utility.isEmpty(sumBackorderString))
				{
					sumBackorderString = "0";
				}
				BigDecimal sumBackorder = new BigDecimal ( sumBackorderString );
				invItem.setSumBackorder(sumBackorder);
			}
			if (incomingRequest.containsKey("InvItem_sumUsage"))
			{
				String sumUsageString = (String) incomingRequest.get("InvItem_sumUsage");
				if (Utility.isEmpty(sumUsageString))
				{
					sumUsageString = "0";
				}
				BigDecimal sumUsage = new BigDecimal ( sumUsageString );
				invItem.setSumUsage(sumUsage);
			}
			if (incomingRequest.containsKey("InvItem_sumEoq"))
			{
				String sumEoqString = (String) incomingRequest.get("InvItem_sumEoq");
				if (Utility.isEmpty(sumEoqString))
				{
					sumEoqString = "0";
				}
				BigDecimal sumEoq = new BigDecimal ( sumEoqString );
				invItem.setSumEoq(sumEoq);
			}
			if (incomingRequest.containsKey("InvItem_sumMinOh"))
			{
				String sumMinOhString = (String) incomingRequest.get("InvItem_sumMinOh");
				if (Utility.isEmpty(sumMinOhString))
				{
					sumMinOhString = "0";
				}
				BigDecimal sumMinOh = new BigDecimal ( sumMinOhString );
				invItem.setSumMinOh(sumMinOh);
			}
			if (incomingRequest.containsKey("InvItem_sumQtyOrder"))
			{
				String sumQtyOrderString = (String) incomingRequest.get("InvItem_sumQtyOrder");
				if (Utility.isEmpty(sumQtyOrderString))
				{
					sumQtyOrderString = "0";
				}
				BigDecimal sumQtyOrder = new BigDecimal ( sumQtyOrderString );
				invItem.setSumQtyOrder(sumQtyOrder);
			}
			if (incomingRequest.containsKey("InvItem_lastPo"))
			{
				String lastPo = (String ) incomingRequest.get("InvItem_lastPo");
				invItem.setLastPo(lastPo);
			}
			if (incomingRequest.containsKey("InvItem_lastPoDate"))
			{
				String lastPoDateString = (String) incomingRequest.get("InvItem_lastPoDate");
				Date lastPoDate = Dates.getDate(lastPoDateString);
				invItem.setLastPoDate(lastPoDate);
			}
			if (incomingRequest.containsKey("InvItem_assetCode"))
			{
				String assetCode = (String ) incomingRequest.get("InvItem_assetCode");
				invItem.setAssetCode(assetCode);
			}
			if (incomingRequest.containsKey("InvItem_minReqQty"))
			{
				String minReqQtyString = (String) incomingRequest.get("InvItem_minReqQty");
				if (Utility.isEmpty(minReqQtyString))
				{
					minReqQtyString = "0";
				}
				BigDecimal minReqQty = new BigDecimal ( minReqQtyString );
				invItem.setMinReqQty(minReqQty);
			}
			if (incomingRequest.containsKey("InvItem_receiptRequired"))
			{
				String receiptRequired = (String ) incomingRequest.get("InvItem_receiptRequired");
				invItem.setReceiptRequired(receiptRequired);
			}
			if (incomingRequest.containsKey("InvItem_pullIncrement"))
			{
				String pullIncrementString = (String) incomingRequest.get("InvItem_pullIncrement");
				if (Utility.isEmpty(pullIncrementString))
				{
					pullIncrementString = "0";
				}
				BigDecimal pullIncrement = new BigDecimal ( pullIncrementString );
				invItem.setPullIncrement(pullIncrement);
			}
			if (incomingRequest.containsKey("InvItem_usageRecalc"))
			{
				String usageRecalc = (String ) incomingRequest.get("InvItem_usageRecalc");
				invItem.setUsageRecalc(usageRecalc);
			}
			if (incomingRequest.containsKey("InvItem_buyerCode"))
			{
				String buyerCode = (String ) incomingRequest.get("InvItem_buyerCode");
				invItem.setBuyerCode(buyerCode);
			}
			if (incomingRequest.containsKey("InvItem_aribaExport"))
			{
				String aribaExport = (String ) incomingRequest.get("InvItem_aribaExport");
				invItem.setAribaExport(aribaExport);
			}
			if (incomingRequest.containsKey("InvItem_appointedFlag"))
			{
				String appointedFlag = (String ) incomingRequest.get("InvItem_appointedFlag");
				invItem.setAppointedFlag(appointedFlag);
			}
			if (incomingRequest.containsKey("InvItem_ssInterface"))
			{
				String ssInterface = (String ) incomingRequest.get("InvItem_ssInterface");
				invItem.setSsInterface(ssInterface);
			}
			if (incomingRequest.containsKey("InvItem_managedBy"))
			{
				String managedBy = (String ) incomingRequest.get("InvItem_managedBy");
				invItem.setManagedBy(managedBy);
			}
			if (incomingRequest.containsKey("InvItem_description"))
			{
				String description = (String ) incomingRequest.get("InvItem_description");
				invItem.setDescription(description);
			}
			if (incomingRequest.containsKey("InvItem_modelNumber"))
			{
				String modelNumber = (String ) incomingRequest.get("InvItem_modelNumber");
				invItem.setModelNumber(modelNumber);
			}

			if (incomingRequest.containsKey("InvItem_mfgName"))
			{
				String mfgName = (String ) incomingRequest.get("InvItem_mfgName");
				invItem.setMfgName(mfgName);
			}

			if (incomingRequest.containsKey("InvItem_nsnNumber"))
			{
				String nsnNumber = (String ) incomingRequest.get("InvItem_nsnNumber");
				invItem.setNsnNumber(nsnNumber);
			}

			if (incomingRequest.containsKey("InvItem_gfpProperty"))
			{
				String gfpProperty = (String ) incomingRequest.get("InvItem_gfpProperty");
				invItem.setGfpProperty(gfpProperty);
			}

			if (incomingRequest.containsKey("InvItem_fgpProperty"))
			{
				String fgpProperty = (String ) incomingRequest.get("InvItem_fgpProperty");
				invItem.setFgpProperty(fgpProperty);
			}

			if (incomingRequest.containsKey("InvItem_capProperty"))
			{
				String capProperty = (String ) incomingRequest.get("InvItem_capProperty");
				invItem.setCapProperty(capProperty);
			}

			if (incomingRequest.containsKey("InvItem_itemType"))
			{
				String itemType = (String ) incomingRequest.get("InvItem_itemType");
				invItem.setItemType(itemType);
			}

			if (incomingRequest.containsKey("InvItem_barcodeData"))
			{
				String barcodeData = (String ) incomingRequest.get("InvItem_barcodeData");
				invItem.setBarcodeData(barcodeData);
			}

			if (incomingRequest.containsKey("InvItem_drawingNo"))
			{
				String drawingNo = (String ) incomingRequest.get("InvItem_drawingNo");
				invItem.setDrawingNo(drawingNo);
			}
			if (incomingRequest.containsKey("InvItem_duomUmCode"))
			{
				String duomUmCode = (String ) incomingRequest.get("InvItem_duomUmCode");
				invItem.setDuomUmCode(duomUmCode);
			}
			if (incomingRequest.containsKey("InvItem_critSparePart"))
			{
				String critSparePart = (String) incomingRequest.get("InvItem_critSparePart");
				invItem.setCritSparePart(critSparePart);
			}
			if (incomingRequest.containsKey("InvItem_critSparePart"))
			{
				String critSparePart = (String) incomingRequest.get("InvItem_critSparePart");
				invItem.setCritSparePart(critSparePart);
			}
			if (incomingRequest.containsKey("InvItem_approveStatus"))
			{
				String approveStatus = (String) incomingRequest.get("InvItem_approveStatus");
				invItem.setApproveStatus(approveStatus);
			}
			if (incomingRequest.containsKey("InvItem_shelfLifeRqd"))
			{
				String shelfLifeRqd = (String) incomingRequest.get("InvItem_shelfLifeRqd");
				invItem.setShelfLifeRqd(shelfLifeRqd);
			}
			if (itemAction.equalsIgnoreCase("CREATE"))
			{
				UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
				invItem.setIcHeaderHistory(new BigDecimal(ukg.getUniqueKey().toString()));
			}

			result = invItem;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}