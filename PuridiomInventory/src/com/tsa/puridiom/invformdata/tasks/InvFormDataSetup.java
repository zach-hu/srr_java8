package com.tsa.puridiom.invformdata.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

public class InvFormDataSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String itemNumber = (String) incomingRequest.get("InvItem_itemNumber") ;
			if (itemNumber == null)
			{
				this.setStatus(Status.FAILED);
			}
			incomingRequest.put("InvFormData_itemNumber", itemNumber);
			incomingRequest.put("InvFormData_icNotes", "0");

			incomingRequest.put("InvFormData_stoItemNumber", "");
			incomingRequest.put("InvFormData_sto2ItemNumber", "");
			incomingRequest.put("InvFormData_sto3ItemNumber", "");
			incomingRequest.put("InvFormData_formNumber", "");
			incomingRequest.put("InvFormData_formRevision", "");
			incomingRequest.put("InvFormData_formReplaces", "");
			incomingRequest.put("InvFormData_primeUser", "");
			incomingRequest.put("InvFormData_department", "");
			incomingRequest.put("InvFormData_officeLoc", "");
			incomingRequest.put("InvFormData_budgetCode", "");
			incomingRequest.put("InvFormData_lobCode", "");
			incomingRequest.put("InvFormData_formType", "");
			incomingRequest.put("InvFormData_formClass", "");
			incomingRequest.put("InvFormData_formSize", "");
			incomingRequest.put("InvFormData_coverStock", "");
			incomingRequest.put("InvFormData_coverInk", "");
			incomingRequest.put("InvFormData_pages", "0");
			incomingRequest.put("InvFormData_pageStock", "");
			incomingRequest.put("InvFormData_pageInk", "");
			incomingRequest.put("InvFormData_formLogo1", "");
			incomingRequest.put("InvFormData_formLogo2", "");
			incomingRequest.put("InvFormData_formFolding", "");
			incomingRequest.put("InvFormData_formBinding", "");
			incomingRequest.put("InvFormData_formPadding", "");
			incomingRequest.put("InvFormData_formPkging", "");
			incomingRequest.put("InvFormData_formArt", "");
			incomingRequest.put("InvFormData_formSig1", "");
			incomingRequest.put("InvFormData_formSig2", "");
			incomingRequest.put("InvFormData_legalReview", "");
			incomingRequest.put("InvFormData_numberFrom", "");
			incomingRequest.put("InvFormData_numberTo", "");
			incomingRequest.put("InvFormData_noMissing", "");
			incomingRequest.put("InvFormData_listMissing", "");
			incomingRequest.put("InvFormData_numberPrefix", "");
			incomingRequest.put("InvFormData_numberSuffix", "");
			incomingRequest.put("InvFormData_micrCode", "");
			incomingRequest.put("InvFormData_micrDesc", "");
			incomingRequest.put("InvFormData_ocrCode", "");
			incomingRequest.put("InvFormData_ocrDesc", "");
			incomingRequest.put("InvFormData_numUdf1", "");
			incomingRequest.put("InvFormData_numUdf2", "");
			incomingRequest.put("InvFormData_numUdf3", "");
			incomingRequest.put("InvFormData_numUdf4", "");
			incomingRequest.put("InvFormData_numUdf5", "");
			incomingRequest.put("InvFormData_fastenPos", "");
			incomingRequest.put("InvFormData_fastenDesc", "");
			incomingRequest.put("InvFormData_fastenType", "");
			incomingRequest.put("InvFormData_binding", "");
			incomingRequest.put("InvFormData_bindingPer", "0");
			incomingRequest.put("InvFormData_bindingCover", "");
			incomingRequest.put("InvFormData_bindingBack", "");
			incomingRequest.put("InvFormData_padding", "");
			incomingRequest.put("InvFormData_paddingPer", "0");
			incomingRequest.put("InvFormData_paddingCover", "");
			incomingRequest.put("InvFormData_paddingBack", "");
			incomingRequest.put("InvFormData_typewriter", "");
			incomingRequest.put("InvFormData_typewriterVsp", "0");
			incomingRequest.put("InvFormData_typewriterHsp", "0");
			incomingRequest.put("InvFormData_printer", "");
			incomingRequest.put("InvFormData_printerVsp", "0");
			incomingRequest.put("InvFormData_printerHsp", "0");
			incomingRequest.put("InvFormData_decollator", "");
			incomingRequest.put("InvFormData_burster", "");
			incomingRequest.put("InvFormData_mailEquip", "");
			incomingRequest.put("InvFormData_envSize", "");
			incomingRequest.put("InvFormData_winSize", "");
			incomingRequest.put("InvFormData_posFromleft", "0");
			incomingRequest.put("InvFormData_posFrombot", "0");
			incomingRequest.put("InvFormData_flapPos", "");
			incomingRequest.put("InvFormData_envType", "");
			incomingRequest.put("InvFormData_gumType", "");
			incomingRequest.put("InvFormData_otherType", "");
			incomingRequest.put("InvFormData_formColor", "");
			incomingRequest.put("InvFormData_formWeight", "");
			incomingRequest.put("InvFormData_formStock", "");
			incomingRequest.put("InvFormData_formInk", "");
			incomingRequest.put("InvFormData_labelSize", "");
			incomingRequest.put("InvFormData_perfLoc", "0");
			incomingRequest.put("InvFormData_appSurface", "");
			incomingRequest.put("InvFormData_adhesive", "");
			incomingRequest.put("InvFormData_qtyPerCtn", "0");
			incomingRequest.put("InvFormData_qtyPerPkg", "0");
			incomingRequest.put("InvFormData_pkgType", "");
			incomingRequest.put("InvFormData_pkgOption", "");
			incomingRequest.put("InvFormData_compCode", "");
			incomingRequest.put("InvFormData_stockHbu", "");
			incomingRequest.put("InvFormData_sizeFlat", "");
			incomingRequest.put("InvFormData_coverPrints", "");
			incomingRequest.put("InvFormData_bleeds", "");
			incomingRequest.put("InvFormData_proofs", "");
			incomingRequest.put("InvFormData_finishing", "");
			incomingRequest.put("InvFormData_turnaround", "");
			incomingRequest.put("InvFormData_formUdf01", "");
			incomingRequest.put("InvFormData_formUdf02", "");
			incomingRequest.put("InvFormData_specUdf01", "");
			incomingRequest.put("InvFormData_specUdf02", "");
			incomingRequest.put("InvFormData_specUdf03", "");
			incomingRequest.put("InvFormData_specNotes", "");
			incomingRequest.put("InvFormData_formAddress", "");
			incomingRequest.put("InvFormData_maxReqQty", "0");
			incomingRequest.put("InvFormData_appointedFlag", "");
			incomingRequest.put("InvFormData_equivalentStock", "");
			incomingRequest.put("InvFormData_autoReprint", "");
			incomingRequest.put("InvFormData_inprintableSpace", "");
			incomingRequest.put("InvFormData_formTraps", "");
			incomingRequest.put("InvFormData_colorSeparation", "");
			incomingRequest.put("InvFormData_supInstruction", "");
			incomingRequest.put("InvFormData_useType", "");
			incomingRequest.put("InvFormData_mailDate", Dates.today("", ""));
			incomingRequest.put("InvFormData_envLabel", "");
			incomingRequest.put("InvFormData_envColor", "");
			incomingRequest.put("InvFormData_envWeight", "");
			incomingRequest.put("InvFormData_envStock", "");
			incomingRequest.put("InvFormData_envInk", "");
			incomingRequest.put("InvFormData_envOther", "");
			incomingRequest.put("InvFormData_dateOut", Dates.today("", ""));
			incomingRequest.put("InvFormData_docPrtDate", Dates.today("", ""));
			incomingRequest.put("InvFormData_puAppDate", Dates.today("", ""));
			incomingRequest.put("InvFormData_faAppDate", Dates.today("", ""));
			incomingRequest.put("InvFormData_automaticReprint", "");

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