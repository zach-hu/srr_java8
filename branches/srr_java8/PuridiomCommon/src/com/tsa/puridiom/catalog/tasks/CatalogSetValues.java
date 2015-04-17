package com.tsa.puridiom.catalog.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class CatalogSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

        String organizationId = (String) incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }

		try
		{
			Catalog catalog = (Catalog) incomingRequest.get("catalog");
			if (catalog == null)
			{
				catalog = new Catalog();
			}

			if (incomingRequest.containsKey("Catalog_catalogId"))
			{
				String catalogId = (String ) incomingRequest.get("Catalog_catalogId");
				catalog.setCatalogId(catalogId);
			}
			if (incomingRequest.containsKey("Catalog_title"))
			{
				String title = (String ) incomingRequest.get("Catalog_title");
				catalog.setTitle(title);
			}
			if (incomingRequest.containsKey("Catalog_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("Catalog_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString, userDateFormat);
				catalog.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("Catalog_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("Catalog_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString, userDateFormat);
				if (dateExpires != null && (catalog.getDateExpires() == null || !catalog.getDateExpires().equals(dateExpires))) {
				    incomingRequest.put("catalogStatusUpdate", "true");
				}
				catalog.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("Catalog_status"))
			{
				String status = (String ) incomingRequest.get("Catalog_status");
				if (!catalog.getStatus().equals(status)) {
				    incomingRequest.put("catalogStatusUpdate", "true");
				}
				catalog.setStatus(status);
			}
			if (incomingRequest.containsKey("Catalog_owner"))
			{
				String owner = (String ) incomingRequest.get("Catalog_owner");
				catalog.setOwner(owner);
			}
			if (incomingRequest.containsKey("Catalog_source"))
			{
				String source = (String ) incomingRequest.get("Catalog_source");
				catalog.setSource(source);
			}
			if (incomingRequest.containsKey("Catalog_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("Catalog_vendorId");
				catalog.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("Catalog_icPoHeader"))
			{
				String icPoHeaderString = (String) incomingRequest.get("Catalog_icPoHeader");
				if (Utility.isEmpty(icPoHeaderString))
				{
				    icPoHeaderString = "0";
				}
				BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
				catalog.setIcPoHeader(icPoHeader);
			}
			if (incomingRequest.containsKey("Catalog_poNumber"))
			{
				String poNumber = (String ) incomingRequest.get("Catalog_poNumber");
				catalog.setPoNumber(poNumber);
			}
			if (incomingRequest.containsKey("Catalog_revisionNumber"))
			{
				String revisionNumberString = (String) incomingRequest.get("Catalog_revisionNumber");
				if (Utility.isEmpty(revisionNumberString))
				{
					revisionNumberString = "1";
				}
				BigDecimal revisionNumber = new BigDecimal ( revisionNumberString );
				catalog.setRevisionNumber(revisionNumber);
			}
			if (incomingRequest.containsKey("Catalog_allowReleases"))
			{
				String allowReleases = (String ) incomingRequest.get("Catalog_allowReleases");
				if (Utility.isEmpty(allowReleases)) {
				    allowReleases = "N";
				}
				catalog.setAllowReleases(allowReleases);
			}
			if (incomingRequest.containsKey("Catalog_consolidateReleases"))
			{
				String consolidateReleases = (String ) incomingRequest.get("Catalog_consolidateReleases");
				if (Utility.isEmpty(consolidateReleases)) {
					consolidateReleases = "N";
				}
				catalog.setConsolidateReleases(consolidateReleases);
			}
			if (incomingRequest.containsKey("Catalog_webCatalog"))
			{
				String webCatalog = (String ) incomingRequest.get("Catalog_webCatalog");
				if (Utility.isEmpty(webCatalog)) {
				    webCatalog = "N";
				}
				catalog.setWebCatalog(webCatalog);
			}
			if (incomingRequest.containsKey("Catalog_externalCatalog"))
			{
				String externalCatalog = (String ) incomingRequest.get("Catalog_externalCatalog");
				if (Utility.isEmpty(externalCatalog)) {
				    externalCatalog = "N";
				}
				catalog.setExternalCatalog(externalCatalog);
			}
			if (incomingRequest.containsKey("Catalog_requestXml"))
			{
				String requestXml = (String ) incomingRequest.get("Catalog_requestXml");
				catalog.setRequestXml(requestXml);
			}
			if (incomingRequest.containsKey("Catalog_punchoutUrl"))
			{
				String punchoutUrl = (String ) incomingRequest.get("Catalog_punchoutUrl");
				catalog.setPunchoutUrl(punchoutUrl);
			}
			if (incomingRequest.containsKey("Catalog_icAccount"))
			{
				String icAccountString = (String) incomingRequest.get("Catalog_icAccount");
				if (Utility.isEmpty(icAccountString))
				{
					icAccountString = "0";
				}
				BigDecimal icAccount = new BigDecimal ( icAccountString );
				catalog.setIcAccount(icAccount);
			}
			if (incomingRequest.containsKey("Catalog_receiptRequired"))
			{
				String receiptRequired = (String ) incomingRequest.get("Catalog_receiptRequired");
				catalog.setReceiptRequired(receiptRequired);
			}
			if (incomingRequest.containsKey("Catalog_ordersOnly"))
			{
				String ordersOnly = (String ) incomingRequest.get("Catalog_ordersOnly");
				catalog.setOrdersOnly(ordersOnly);
			}
			if (incomingRequest.containsKey("Catalog_currencyCode"))
			{
				String currencyCode = (String) incomingRequest.get("Catalog_currencyCode");
				catalog.setCurrencyCode(currencyCode);
			}

			if (incomingRequest.containsKey("Catalog_icHeaderComment"))
			{
				String icHeaderCommentString = (String) incomingRequest.get("Catalog_icHeaderComment");
				if (HiltonUtility.isEmpty(icHeaderCommentString))
				{
					icHeaderCommentString = "0";
				}
				catalog.setIcHeaderComment(new BigDecimal(icHeaderCommentString));
			}

			if (incomingRequest.containsKey("Catalog_lastUpdatedDate"))
			{
				String lastUpdatedDateString = (String) incomingRequest.get("Catalog_lastUpdatedDate");
				Date lastUpdatedDate = Dates.getDate(lastUpdatedDateString);
				catalog.setLastUpdatedDate(lastUpdatedDate);
			}
			if (incomingRequest.containsKey("Catalog_lastUpdatedBy"))
			{
				String lastUpdatedBy = (String ) incomingRequest.get("Catalog_lastUpdatedBy");
				catalog.setLastUpdatedBy(lastUpdatedBy);
			}
			if (incomingRequest.containsKey("Catalog_icPunchout"))
			{
				String icPunchoutString = (String ) incomingRequest.get("Catalog_icPunchout");
				BigDecimal icPunchout = new BigDecimal ( icPunchoutString );
				catalog.setIcPunchout(icPunchout);
			}

			result = catalog;
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
