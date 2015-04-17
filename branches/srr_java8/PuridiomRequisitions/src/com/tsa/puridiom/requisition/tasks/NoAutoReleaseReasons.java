package com.tsa.puridiom.requisition.tasks;

import com.tsagate.properties.DictionaryManager;

public class NoAutoReleaseReasons
{
	public static String CATALOG_NO_RELEASES = "0";
	public static String ORDER_EXPIRED = "-1";
	public static String ORDER_NOT_AWARDED = "-2";
	public static String EX_RELEASE_LIMIT = "-3";
	public static String EX_BLANKET_LIMIT = "-4";
	public static String INCOMPLETE_REVISION = "-100";

	/**
	 * Gets the actual text of why the item was not autoreleased.
	 * @param code
	 * @param oranizationId
	 * @return
	 */
	public static String getMessage(String code, String oranizationId)
	{
		String message = "";
		if(code.equals(NoAutoReleaseReasons.CATALOG_NO_RELEASES))
		{
			//message = DictionaryManager.getLabel(oranizationId, "catalogNoRelase", " Does Not Allow Releases.");
			message = DictionaryManager.getLabel(oranizationId, "catalogNoRelease", " ");	//leave blank until source is fixed as requested by JR
		}
		if(code.equals(NoAutoReleaseReasons.ORDER_EXPIRED))
		{
			message = DictionaryManager.getLabel(oranizationId, "orderexpired", "Blanket Order has Expired or is not yet Effective.");
		}
		if(code.equals(NoAutoReleaseReasons.ORDER_NOT_AWARDED))
		{
			message = DictionaryManager.getLabel(oranizationId, "ordernoaward", "Blanket Order has not been AWARDED.");
		}
		if(code.equals(NoAutoReleaseReasons.EX_RELEASE_LIMIT))
		{
			message = DictionaryManager.getLabel(oranizationId, "exreleaselimit", "Total of Items Exceeds Release Limit.");
		}
		if(code.equals(NoAutoReleaseReasons.EX_BLANKET_LIMIT))
		{
			message = DictionaryManager.getLabel(oranizationId, "exblanketlimit", "Total of Items Exceeds Blanket Limit.");
		}
		if(code.equals(NoAutoReleaseReasons.INCOMPLETE_REVISION))
		{
			message = DictionaryManager.getLabel(oranizationId, "incompleterevision", "Blanket has an Incomplete Revision.");
		}

		return message;
	}
}
