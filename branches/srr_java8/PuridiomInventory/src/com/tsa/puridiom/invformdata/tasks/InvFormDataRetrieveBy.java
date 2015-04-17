package com.tsa.puridiom.invformdata.tasks;

import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
public class InvFormDataRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvFormData as invformdata where 1=1 ");
		if(incomingRequest.containsKey("InvFormData_itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("InvFormData_itemNumber");
			queryString.append(" AND invformdata.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("InvFormData_icNotes"))
		{			
			String icNotes = (String) incomingRequest.get("InvFormData_icNotes");
			queryString.append(" AND invformdata.icNotes = '" + icNotes + "'");
		}
		if(incomingRequest.containsKey("InvFormData_stoItemNumber"))
		{			
			String stoItemNumber = (String) incomingRequest.get("InvFormData_stoItemNumber");
			queryString.append(" AND invformdata.stoItemNumber = '" + stoItemNumber + "'");
		}
		if(incomingRequest.containsKey("InvFormData_sto2ItemNumber"))
		{			
			String sto2ItemNumber = (String) incomingRequest.get("InvFormData_sto2ItemNumber");
			queryString.append(" AND invformdata.sto2ItemNumber = '" + sto2ItemNumber + "'");
		}
		if(incomingRequest.containsKey("InvFormData_sto3ItemNumber"))
		{			
			String sto3ItemNumber = (String) incomingRequest.get("InvFormData_sto3ItemNumber");
			queryString.append(" AND invformdata.sto3ItemNumber = '" + sto3ItemNumber + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formNumber"))
		{			
			String formNumber = (String) incomingRequest.get("InvFormData_formNumber");
			queryString.append(" AND invformdata.formNumber = '" + formNumber + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formRevision"))
		{			
			String formRevision = (String) incomingRequest.get("InvFormData_formRevision");
			queryString.append(" AND invformdata.formRevision = '" + formRevision + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formReplaces"))
		{			
			String formReplaces = (String) incomingRequest.get("InvFormData_formReplaces");
			queryString.append(" AND invformdata.formReplaces = '" + formReplaces + "'");
		}
		if(incomingRequest.containsKey("InvFormData_primeUser"))
		{			
			String primeUser = (String) incomingRequest.get("InvFormData_primeUser");
			queryString.append(" AND invformdata.primeUser = '" + primeUser + "'");
		}
		if(incomingRequest.containsKey("InvFormData_department"))
		{			
			String department = (String) incomingRequest.get("InvFormData_department");
			queryString.append(" AND invformdata.department = '" + department + "'");
		}
		if(incomingRequest.containsKey("InvFormData_officeLoc"))
		{			
			String officeLoc = (String) incomingRequest.get("InvFormData_officeLoc");
			queryString.append(" AND invformdata.officeLoc = '" + officeLoc + "'");
		}
		if(incomingRequest.containsKey("InvFormData_budgetCode"))
		{			
			String budgetCode = (String) incomingRequest.get("InvFormData_budgetCode");
			queryString.append(" AND invformdata.budgetCode = '" + budgetCode + "'");
		}
		if(incomingRequest.containsKey("InvFormData_lobCode"))
		{			
			String lobCode = (String) incomingRequest.get("InvFormData_lobCode");
			queryString.append(" AND invformdata.lobCode = '" + lobCode + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formType"))
		{			
			String formType = (String) incomingRequest.get("InvFormData_formType");
			queryString.append(" AND invformdata.formType = '" + formType + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formClass"))
		{			
			String formClass = (String) incomingRequest.get("InvFormData_formClass");
			queryString.append(" AND invformdata.formClass = '" + formClass + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formSize"))
		{			
			String formSize = (String) incomingRequest.get("InvFormData_formSize");
			queryString.append(" AND invformdata.formSize = '" + formSize + "'");
		}
		if(incomingRequest.containsKey("InvFormData_coverStock"))
		{			
			String coverStock = (String) incomingRequest.get("InvFormData_coverStock");
			queryString.append(" AND invformdata.coverStock = '" + coverStock + "'");
		}
		if(incomingRequest.containsKey("InvFormData_coverInk"))
		{			
			String coverInk = (String) incomingRequest.get("InvFormData_coverInk");
			queryString.append(" AND invformdata.coverInk = '" + coverInk + "'");
		}
		if(incomingRequest.containsKey("InvFormData_pages"))
		{			
			String pages = (String) incomingRequest.get("InvFormData_pages");
			queryString.append(" AND invformdata.pages = '" + pages + "'");
		}
		if(incomingRequest.containsKey("InvFormData_pageStock"))
		{			
			String pageStock = (String) incomingRequest.get("InvFormData_pageStock");
			queryString.append(" AND invformdata.pageStock = '" + pageStock + "'");
		}
		if(incomingRequest.containsKey("InvFormData_pageInk"))
		{			
			String pageInk = (String) incomingRequest.get("InvFormData_pageInk");
			queryString.append(" AND invformdata.pageInk = '" + pageInk + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formLogo1"))
		{			
			String formLogo1 = (String) incomingRequest.get("InvFormData_formLogo1");
			queryString.append(" AND invformdata.formLogo1 = '" + formLogo1 + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formLogo2"))
		{			
			String formLogo2 = (String) incomingRequest.get("InvFormData_formLogo2");
			queryString.append(" AND invformdata.formLogo2 = '" + formLogo2 + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formFolding"))
		{			
			String formFolding = (String) incomingRequest.get("InvFormData_formFolding");
			queryString.append(" AND invformdata.formFolding = '" + formFolding + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formBinding"))
		{			
			String formBinding = (String) incomingRequest.get("InvFormData_formBinding");
			queryString.append(" AND invformdata.formBinding = '" + formBinding + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formPadding"))
		{			
			String formPadding = (String) incomingRequest.get("InvFormData_formPadding");
			queryString.append(" AND invformdata.formPadding = '" + formPadding + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formPkging"))
		{			
			String formPkging = (String) incomingRequest.get("InvFormData_formPkging");
			queryString.append(" AND invformdata.formPkging = '" + formPkging + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formArt"))
		{			
			String formArt = (String) incomingRequest.get("InvFormData_formArt");
			queryString.append(" AND invformdata.formArt = '" + formArt + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formSig1"))
		{			
			String formSig1 = (String) incomingRequest.get("InvFormData_formSig1");
			queryString.append(" AND invformdata.formSig1 = '" + formSig1 + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formSig2"))
		{			
			String formSig2 = (String) incomingRequest.get("InvFormData_formSig2");
			queryString.append(" AND invformdata.formSig2 = '" + formSig2 + "'");
		}
		if(incomingRequest.containsKey("InvFormData_legalReview"))
		{			
			String legalReview = (String) incomingRequest.get("InvFormData_legalReview");
			queryString.append(" AND invformdata.legalReview = '" + legalReview + "'");
		}
		if(incomingRequest.containsKey("InvFormData_numberFrom"))
		{			
			String numberFrom = (String) incomingRequest.get("InvFormData_numberFrom");
			queryString.append(" AND invformdata.numberFrom = '" + numberFrom + "'");
		}
		if(incomingRequest.containsKey("InvFormData_numberTo"))
		{			
			String numberTo = (String) incomingRequest.get("InvFormData_numberTo");
			queryString.append(" AND invformdata.numberTo = '" + numberTo + "'");
		}
		if(incomingRequest.containsKey("InvFormData_noMissing"))
		{			
			String noMissing = (String) incomingRequest.get("InvFormData_noMissing");
			queryString.append(" AND invformdata.noMissing = '" + noMissing + "'");
		}
		if(incomingRequest.containsKey("InvFormData_listMissing"))
		{			
			String listMissing = (String) incomingRequest.get("InvFormData_listMissing");
			queryString.append(" AND invformdata.listMissing = '" + listMissing + "'");
		}
		if(incomingRequest.containsKey("InvFormData_numberPrefix"))
		{			
			String numberPrefix = (String) incomingRequest.get("InvFormData_numberPrefix");
			queryString.append(" AND invformdata.numberPrefix = '" + numberPrefix + "'");
		}
		if(incomingRequest.containsKey("InvFormData_numberSuffix"))
		{			
			String numberSuffix = (String) incomingRequest.get("InvFormData_numberSuffix");
			queryString.append(" AND invformdata.numberSuffix = '" + numberSuffix + "'");
		}
		if(incomingRequest.containsKey("InvFormData_micrCode"))
		{			
			String micrCode = (String) incomingRequest.get("InvFormData_micrCode");
			queryString.append(" AND invformdata.micrCode = '" + micrCode + "'");
		}
		if(incomingRequest.containsKey("InvFormData_micrDesc"))
		{			
			String micrDesc = (String) incomingRequest.get("InvFormData_micrDesc");
			queryString.append(" AND invformdata.micrDesc = '" + micrDesc + "'");
		}
		if(incomingRequest.containsKey("InvFormData_ocrCode"))
		{			
			String ocrCode = (String) incomingRequest.get("InvFormData_ocrCode");
			queryString.append(" AND invformdata.ocrCode = '" + ocrCode + "'");
		}
		if(incomingRequest.containsKey("InvFormData_ocrDesc"))
		{			
			String ocrDesc = (String) incomingRequest.get("InvFormData_ocrDesc");
			queryString.append(" AND invformdata.ocrDesc = '" + ocrDesc + "'");
		}
		if(incomingRequest.containsKey("InvFormData_numUdf1"))
		{			
			String numUdf1 = (String) incomingRequest.get("InvFormData_numUdf1");
			queryString.append(" AND invformdata.numUdf1 = '" + numUdf1 + "'");
		}
		if(incomingRequest.containsKey("InvFormData_numUdf2"))
		{			
			String numUdf2 = (String) incomingRequest.get("InvFormData_numUdf2");
			queryString.append(" AND invformdata.numUdf2 = '" + numUdf2 + "'");
		}
		if(incomingRequest.containsKey("InvFormData_numUdf3"))
		{			
			String numUdf3 = (String) incomingRequest.get("InvFormData_numUdf3");
			queryString.append(" AND invformdata.numUdf3 = '" + numUdf3 + "'");
		}
		if(incomingRequest.containsKey("InvFormData_numUdf4"))
		{			
			String numUdf4 = (String) incomingRequest.get("InvFormData_numUdf4");
			queryString.append(" AND invformdata.numUdf4 = '" + numUdf4 + "'");
		}
		if(incomingRequest.containsKey("InvFormData_numUdf5"))
		{			
			String numUdf5 = (String) incomingRequest.get("InvFormData_numUdf5");
			queryString.append(" AND invformdata.numUdf5 = '" + numUdf5 + "'");
		}
		if(incomingRequest.containsKey("InvFormData_fastenPos"))
		{			
			String fastenPos = (String) incomingRequest.get("InvFormData_fastenPos");
			queryString.append(" AND invformdata.fastenPos = '" + fastenPos + "'");
		}
		if(incomingRequest.containsKey("InvFormData_fastenDesc"))
		{			
			String fastenDesc = (String) incomingRequest.get("InvFormData_fastenDesc");
			queryString.append(" AND invformdata.fastenDesc = '" + fastenDesc + "'");
		}
		if(incomingRequest.containsKey("InvFormData_fastenType"))
		{			
			String fastenType = (String) incomingRequest.get("InvFormData_fastenType");
			queryString.append(" AND invformdata.fastenType = '" + fastenType + "'");
		}
		if(incomingRequest.containsKey("InvFormData_binding"))
		{			
			String binding = (String) incomingRequest.get("InvFormData_binding");
			queryString.append(" AND invformdata.binding = '" + binding + "'");
		}
		if(incomingRequest.containsKey("InvFormData_bindingPer"))
		{			
			String bindingPer = (String) incomingRequest.get("InvFormData_bindingPer");
			queryString.append(" AND invformdata.bindingPer = '" + bindingPer + "'");
		}
		if(incomingRequest.containsKey("InvFormData_bindingCover"))
		{			
			String bindingCover = (String) incomingRequest.get("InvFormData_bindingCover");
			queryString.append(" AND invformdata.bindingCover = '" + bindingCover + "'");
		}
		if(incomingRequest.containsKey("InvFormData_bindingBack"))
		{			
			String bindingBack = (String) incomingRequest.get("InvFormData_bindingBack");
			queryString.append(" AND invformdata.bindingBack = '" + bindingBack + "'");
		}
		if(incomingRequest.containsKey("InvFormData_padding"))
		{			
			String padding = (String) incomingRequest.get("InvFormData_padding");
			queryString.append(" AND invformdata.padding = '" + padding + "'");
		}
		if(incomingRequest.containsKey("InvFormData_paddingPer"))
		{			
			String paddingPer = (String) incomingRequest.get("InvFormData_paddingPer");
			queryString.append(" AND invformdata.paddingPer = '" + paddingPer + "'");
		}
		if(incomingRequest.containsKey("InvFormData_paddingCover"))
		{			
			String paddingCover = (String) incomingRequest.get("InvFormData_paddingCover");
			queryString.append(" AND invformdata.paddingCover = '" + paddingCover + "'");
		}
		if(incomingRequest.containsKey("InvFormData_paddingBack"))
		{			
			String paddingBack = (String) incomingRequest.get("InvFormData_paddingBack");
			queryString.append(" AND invformdata.paddingBack = '" + paddingBack + "'");
		}
		if(incomingRequest.containsKey("InvFormData_typewriter"))
		{			
			String typewriter = (String) incomingRequest.get("InvFormData_typewriter");
			queryString.append(" AND invformdata.typewriter = '" + typewriter + "'");
		}
		if(incomingRequest.containsKey("InvFormData_typewriterVsp"))
		{			
			String typewriterVsp = (String) incomingRequest.get("InvFormData_typewriterVsp");
			queryString.append(" AND invformdata.typewriterVsp = '" + typewriterVsp + "'");
		}
		if(incomingRequest.containsKey("InvFormData_typewriterHsp"))
		{			
			String typewriterHsp = (String) incomingRequest.get("InvFormData_typewriterHsp");
			queryString.append(" AND invformdata.typewriterHsp = '" + typewriterHsp + "'");
		}
		if(incomingRequest.containsKey("InvFormData_printer"))
		{			
			String printer = (String) incomingRequest.get("InvFormData_printer");
			queryString.append(" AND invformdata.printer = '" + printer + "'");
		}
		if(incomingRequest.containsKey("InvFormData_printerVsp"))
		{			
			String printerVsp = (String) incomingRequest.get("InvFormData_printerVsp");
			queryString.append(" AND invformdata.printerVsp = '" + printerVsp + "'");
		}
		if(incomingRequest.containsKey("InvFormData_printerHsp"))
		{			
			String printerHsp = (String) incomingRequest.get("InvFormData_printerHsp");
			queryString.append(" AND invformdata.printerHsp = '" + printerHsp + "'");
		}
		if(incomingRequest.containsKey("InvFormData_decollator"))
		{			
			String decollator = (String) incomingRequest.get("InvFormData_decollator");
			queryString.append(" AND invformdata.decollator = '" + decollator + "'");
		}
		if(incomingRequest.containsKey("InvFormData_burster"))
		{			
			String burster = (String) incomingRequest.get("InvFormData_burster");
			queryString.append(" AND invformdata.burster = '" + burster + "'");
		}
		if(incomingRequest.containsKey("InvFormData_mailEquip"))
		{			
			String mailEquip = (String) incomingRequest.get("InvFormData_mailEquip");
			queryString.append(" AND invformdata.mailEquip = '" + mailEquip + "'");
		}
		if(incomingRequest.containsKey("InvFormData_envSize"))
		{			
			String envSize = (String) incomingRequest.get("InvFormData_envSize");
			queryString.append(" AND invformdata.envSize = '" + envSize + "'");
		}
		if(incomingRequest.containsKey("InvFormData_winSize"))
		{			
			String winSize = (String) incomingRequest.get("InvFormData_winSize");
			queryString.append(" AND invformdata.winSize = '" + winSize + "'");
		}
		if(incomingRequest.containsKey("InvFormData_posFromleft"))
		{			
			String posFromleft = (String) incomingRequest.get("InvFormData_posFromleft");
			queryString.append(" AND invformdata.posFromleft = '" + posFromleft + "'");
		}
		if(incomingRequest.containsKey("InvFormData_posFrombot"))
		{			
			String posFrombot = (String) incomingRequest.get("InvFormData_posFrombot");
			queryString.append(" AND invformdata.posFrombot = '" + posFrombot + "'");
		}
		if(incomingRequest.containsKey("InvFormData_flapPos"))
		{			
			String flapPos = (String) incomingRequest.get("InvFormData_flapPos");
			queryString.append(" AND invformdata.flapPos = '" + flapPos + "'");
		}
		if(incomingRequest.containsKey("InvFormData_envType"))
		{			
			String envType = (String) incomingRequest.get("InvFormData_envType");
			queryString.append(" AND invformdata.envType = '" + envType + "'");
		}
		if(incomingRequest.containsKey("InvFormData_gumType"))
		{			
			String gumType = (String) incomingRequest.get("InvFormData_gumType");
			queryString.append(" AND invformdata.gumType = '" + gumType + "'");
		}
		if(incomingRequest.containsKey("InvFormData_otherType"))
		{			
			String otherType = (String) incomingRequest.get("InvFormData_otherType");
			queryString.append(" AND invformdata.otherType = '" + otherType + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formColor"))
		{			
			String formColor = (String) incomingRequest.get("InvFormData_formColor");
			queryString.append(" AND invformdata.formColor = '" + formColor + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formWeight"))
		{			
			String formWeight = (String) incomingRequest.get("InvFormData_formWeight");
			queryString.append(" AND invformdata.formWeight = '" + formWeight + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formStock"))
		{			
			String formStock = (String) incomingRequest.get("InvFormData_formStock");
			queryString.append(" AND invformdata.formStock = '" + formStock + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formInk"))
		{			
			String formInk = (String) incomingRequest.get("InvFormData_formInk");
			queryString.append(" AND invformdata.formInk = '" + formInk + "'");
		}
		if(incomingRequest.containsKey("InvFormData_labelSize"))
		{			
			String labelSize = (String) incomingRequest.get("InvFormData_labelSize");
			queryString.append(" AND invformdata.labelSize = '" + labelSize + "'");
		}
		if(incomingRequest.containsKey("InvFormData_perfLoc"))
		{			
			String perfLoc = (String) incomingRequest.get("InvFormData_perfLoc");
			queryString.append(" AND invformdata.perfLoc = '" + perfLoc + "'");
		}
		if(incomingRequest.containsKey("InvFormData_appSurface"))
		{			
			String appSurface = (String) incomingRequest.get("InvFormData_appSurface");
			queryString.append(" AND invformdata.appSurface = '" + appSurface + "'");
		}
		if(incomingRequest.containsKey("InvFormData_adhesive"))
		{			
			String adhesive = (String) incomingRequest.get("InvFormData_adhesive");
			queryString.append(" AND invformdata.adhesive = '" + adhesive + "'");
		}
		if(incomingRequest.containsKey("InvFormData_qtyPerCtn"))
		{			
			String qtyPerCtn = (String) incomingRequest.get("InvFormData_qtyPerCtn");
			queryString.append(" AND invformdata.qtyPerCtn = '" + qtyPerCtn + "'");
		}
		if(incomingRequest.containsKey("InvFormData_qtyPerPkg"))
		{			
			String qtyPerPkg = (String) incomingRequest.get("InvFormData_qtyPerPkg");
			queryString.append(" AND invformdata.qtyPerPkg = '" + qtyPerPkg + "'");
		}
		if(incomingRequest.containsKey("InvFormData_pkgType"))
		{			
			String pkgType = (String) incomingRequest.get("InvFormData_pkgType");
			queryString.append(" AND invformdata.pkgType = '" + pkgType + "'");
		}
		if(incomingRequest.containsKey("InvFormData_pkgOption"))
		{			
			String pkgOption = (String) incomingRequest.get("InvFormData_pkgOption");
			queryString.append(" AND invformdata.pkgOption = '" + pkgOption + "'");
		}
		if(incomingRequest.containsKey("InvFormData_compCode"))
		{			
			String compCode = (String) incomingRequest.get("InvFormData_compCode");
			queryString.append(" AND invformdata.compCode = '" + compCode + "'");
		}
		if(incomingRequest.containsKey("InvFormData_stockHbu"))
		{			
			String stockHbu = (String) incomingRequest.get("InvFormData_stockHbu");
			queryString.append(" AND invformdata.stockHbu = '" + stockHbu + "'");
		}
		if(incomingRequest.containsKey("InvFormData_sizeFlat"))
		{			
			String sizeFlat = (String) incomingRequest.get("InvFormData_sizeFlat");
			queryString.append(" AND invformdata.sizeFlat = '" + sizeFlat + "'");
		}
		if(incomingRequest.containsKey("InvFormData_coverPrints"))
		{			
			String coverPrints = (String) incomingRequest.get("InvFormData_coverPrints");
			queryString.append(" AND invformdata.coverPrints = '" + coverPrints + "'");
		}
		if(incomingRequest.containsKey("InvFormData_bleeds"))
		{			
			String bleeds = (String) incomingRequest.get("InvFormData_bleeds");
			queryString.append(" AND invformdata.bleeds = '" + bleeds + "'");
		}
		if(incomingRequest.containsKey("InvFormData_proofs"))
		{			
			String proofs = (String) incomingRequest.get("InvFormData_proofs");
			queryString.append(" AND invformdata.proofs = '" + proofs + "'");
		}
		if(incomingRequest.containsKey("InvFormData_finishing"))
		{			
			String finishing = (String) incomingRequest.get("InvFormData_finishing");
			queryString.append(" AND invformdata.finishing = '" + finishing + "'");
		}
		if(incomingRequest.containsKey("InvFormData_turnaround"))
		{			
			String turnaround = (String) incomingRequest.get("InvFormData_turnaround");
			queryString.append(" AND invformdata.turnaround = '" + turnaround + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formUdf01"))
		{			
			String formUdf01 = (String) incomingRequest.get("InvFormData_formUdf01");
			queryString.append(" AND invformdata.formUdf01 = '" + formUdf01 + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formUdf02"))
		{			
			String formUdf02 = (String) incomingRequest.get("InvFormData_formUdf02");
			queryString.append(" AND invformdata.formUdf02 = '" + formUdf02 + "'");
		}
		if(incomingRequest.containsKey("InvFormData_specUdf01"))
		{			
			String specUdf01 = (String) incomingRequest.get("InvFormData_specUdf01");
			queryString.append(" AND invformdata.specUdf01 = '" + specUdf01 + "'");
		}
		if(incomingRequest.containsKey("InvFormData_specUdf02"))
		{			
			String specUdf02 = (String) incomingRequest.get("InvFormData_specUdf02");
			queryString.append(" AND invformdata.specUdf02 = '" + specUdf02 + "'");
		}
		if(incomingRequest.containsKey("InvFormData_specUdf03"))
		{			
			String specUdf03 = (String) incomingRequest.get("InvFormData_specUdf03");
			queryString.append(" AND invformdata.specUdf03 = '" + specUdf03 + "'");
		}
		if(incomingRequest.containsKey("InvFormData_specNotes"))
		{			
			String specNotes = (String) incomingRequest.get("InvFormData_specNotes");
			queryString.append(" AND invformdata.specNotes = '" + specNotes + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formAddress"))
		{			
			String formAddress = (String) incomingRequest.get("InvFormData_formAddress");
			queryString.append(" AND invformdata.formAddress = '" + formAddress + "'");
		}
		if(incomingRequest.containsKey("InvFormData_maxReqQty"))
		{			
			String maxReqQty = (String) incomingRequest.get("InvFormData_maxReqQty");
			queryString.append(" AND invformdata.maxReqQty = '" + maxReqQty + "'");
		}
		if(incomingRequest.containsKey("InvFormData_appointedFlag"))
		{			
			String appointedFlag = (String) incomingRequest.get("InvFormData_appointedFlag");
			queryString.append(" AND invformdata.appointedFlag = '" + appointedFlag + "'");
		}
		if(incomingRequest.containsKey("InvFormData_equivalentStock"))
		{			
			String equivalentStock = (String) incomingRequest.get("InvFormData_equivalentStock");
			queryString.append(" AND invformdata.equivalentStock = '" + equivalentStock + "'");
		}
		if(incomingRequest.containsKey("InvFormData_autoReprint"))
		{			
			String autoReprint = (String) incomingRequest.get("InvFormData_autoReprint");
			queryString.append(" AND invformdata.autoReprint = '" + autoReprint + "'");
		}
		if(incomingRequest.containsKey("InvFormData_inprintableSpace"))
		{			
			String inprintableSpace = (String) incomingRequest.get("InvFormData_inprintableSpace");
			queryString.append(" AND invformdata.inprintableSpace = '" + inprintableSpace + "'");
		}
		if(incomingRequest.containsKey("InvFormData_formTraps"))
		{			
			String formTraps = (String) incomingRequest.get("InvFormData_formTraps");
			queryString.append(" AND invformdata.formTraps = '" + formTraps + "'");
		}
		if(incomingRequest.containsKey("InvFormData_colorSeparation"))
		{			
			String colorSeparation = (String) incomingRequest.get("InvFormData_colorSeparation");
			queryString.append(" AND invformdata.colorSeparation = '" + colorSeparation + "'");
		}
		if(incomingRequest.containsKey("InvFormData_supInstruction"))
		{			
			String supInstruction = (String) incomingRequest.get("InvFormData_supInstruction");
			queryString.append(" AND invformdata.supInstruction = '" + supInstruction + "'");
		}
		if(incomingRequest.containsKey("InvFormData_useType"))
		{			
			String useType = (String) incomingRequest.get("InvFormData_useType");
			queryString.append(" AND invformdata.useType = '" + useType + "'");
		}
		if(incomingRequest.containsKey("InvFormData_mailDate"))
		{			
			String mailDate = (String) incomingRequest.get("InvFormData_mailDate");
			queryString.append(" AND invformdata.mailDate = '" + mailDate + "'");
		}
		if(incomingRequest.containsKey("InvFormData_envLabel"))
		{			
			String envLabel = (String) incomingRequest.get("InvFormData_envLabel");
			queryString.append(" AND invformdata.envLabel = '" + envLabel + "'");
		}
		if(incomingRequest.containsKey("InvFormData_envColor"))
		{			
			String envColor = (String) incomingRequest.get("InvFormData_envColor");
			queryString.append(" AND invformdata.envColor = '" + envColor + "'");
		}
		if(incomingRequest.containsKey("InvFormData_envWeight"))
		{			
			String envWeight = (String) incomingRequest.get("InvFormData_envWeight");
			queryString.append(" AND invformdata.envWeight = '" + envWeight + "'");
		}
		if(incomingRequest.containsKey("InvFormData_envStock"))
		{			
			String envStock = (String) incomingRequest.get("InvFormData_envStock");
			queryString.append(" AND invformdata.envStock = '" + envStock + "'");
		}
		if(incomingRequest.containsKey("InvFormData_envInk"))
		{			
			String envInk = (String) incomingRequest.get("InvFormData_envInk");
			queryString.append(" AND invformdata.envInk = '" + envInk + "'");
		}
		if(incomingRequest.containsKey("InvFormData_envOther"))
		{			
			String envOther = (String) incomingRequest.get("InvFormData_envOther");
			queryString.append(" AND invformdata.envOther = '" + envOther + "'");
		}
		if(incomingRequest.containsKey("InvFormData_dateOut"))
		{			
			String dateOut = (String) incomingRequest.get("InvFormData_dateOut");
			queryString.append(" AND invformdata.dateOut = '" + dateOut + "'");
		}
		if(incomingRequest.containsKey("InvFormData_docPrtDate"))
		{			
			String docPrtDate = (String) incomingRequest.get("InvFormData_docPrtDate");
			queryString.append(" AND invformdata.docPrtDate = '" + docPrtDate + "'");
		}
		if(incomingRequest.containsKey("InvFormData_puAppDate"))
		{			
			String puAppDate = (String) incomingRequest.get("InvFormData_puAppDate");
			queryString.append(" AND invformdata.puAppDate = '" + puAppDate + "'");
		}
		if(incomingRequest.containsKey("InvFormData_faAppDate"))
		{			
			String faAppDate = (String) incomingRequest.get("InvFormData_faAppDate");
			queryString.append(" AND invformdata.faAppDate = '" + faAppDate + "'");
		}
		if(incomingRequest.containsKey("InvFormData_automaticReprint"))
		{			
			String automaticReprint = (String) incomingRequest.get("InvFormData_automaticReprint");
			queryString.append(" AND invformdata.automaticReprint = '" + automaticReprint + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}