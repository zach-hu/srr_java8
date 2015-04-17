package com.tsa.puridiom.invformdata.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.InvFormData;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class InvFormDataSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvFormData invFormData = (InvFormData) incomingRequest.get("invFormData");
			if (invFormData == null)
			{
				invFormData = new InvFormData();
			}

			if (incomingRequest.containsKey("InvFormData_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("InvFormData_itemNumber");
				invFormData.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("InvFormData_icNotes"))
			{
				String icNotesString = (String) incomingRequest.get("InvFormData_icNotes");
				if (Utility.isEmpty(icNotesString))
				{
					icNotesString = "0";
				}
				BigDecimal icNotes = new BigDecimal ( icNotesString );
				invFormData.setIcNotes(icNotes);
			}
			if (incomingRequest.containsKey("InvFormData_stoItemNumber"))
			{
				String stoItemNumber = (String ) incomingRequest.get("InvFormData_stoItemNumber");
				invFormData.setStoItemNumber(stoItemNumber);
			}
			if (incomingRequest.containsKey("InvFormData_sto2ItemNumber"))
			{
				String sto2ItemNumber = (String ) incomingRequest.get("InvFormData_sto2ItemNumber");
				invFormData.setSto2ItemNumber(sto2ItemNumber);
			}
			if (incomingRequest.containsKey("InvFormData_sto3ItemNumber"))
			{
				String sto3ItemNumber = (String ) incomingRequest.get("InvFormData_sto3ItemNumber");
				invFormData.setSto3ItemNumber(sto3ItemNumber);
			}
			if (incomingRequest.containsKey("InvFormData_formNumber"))
			{
				String formNumber = (String ) incomingRequest.get("InvFormData_formNumber");
				invFormData.setFormNumber(formNumber);
			}
			if (incomingRequest.containsKey("InvFormData_formRevision"))
			{
				String formRevision = (String ) incomingRequest.get("InvFormData_formRevision");
				invFormData.setFormRevision(formRevision);
			}
			if (incomingRequest.containsKey("InvFormData_formReplaces"))
			{
				String formReplaces = (String ) incomingRequest.get("InvFormData_formReplaces");
				invFormData.setFormReplaces(formReplaces);
			}
			if (incomingRequest.containsKey("InvFormData_primeUser"))
			{
				String primeUser = (String ) incomingRequest.get("InvFormData_primeUser");
				invFormData.setPrimeUser(primeUser);
			}
			if (incomingRequest.containsKey("InvFormData_department"))
			{
				String department = (String ) incomingRequest.get("InvFormData_department");
				invFormData.setDepartment(department);
			}
			if (incomingRequest.containsKey("InvFormData_officeLoc"))
			{
				String officeLoc = (String ) incomingRequest.get("InvFormData_officeLoc");
				invFormData.setOfficeLoc(officeLoc);
			}
			if (incomingRequest.containsKey("InvFormData_budgetCode"))
			{
				String budgetCode = (String ) incomingRequest.get("InvFormData_budgetCode");
				invFormData.setBudgetCode(budgetCode);
			}
			if (incomingRequest.containsKey("InvFormData_lobCode"))
			{
				String lobCode = (String ) incomingRequest.get("InvFormData_lobCode");
				invFormData.setLobCode(lobCode);
			}
			if (incomingRequest.containsKey("InvFormData_formType"))
			{
				String formType = (String ) incomingRequest.get("InvFormData_formType");
				invFormData.setFormType(formType);
			}
			if (incomingRequest.containsKey("InvFormData_formClass"))
			{
				String formClass = (String ) incomingRequest.get("InvFormData_formClass");
				invFormData.setFormClass(formClass);
			}
			if (incomingRequest.containsKey("InvFormData_formSize"))
			{
				String formSize = (String ) incomingRequest.get("InvFormData_formSize");
				invFormData.setFormSize(formSize);
			}
			if (incomingRequest.containsKey("InvFormData_coverStock"))
			{
				String coverStock = (String ) incomingRequest.get("InvFormData_coverStock");
				invFormData.setCoverStock(coverStock);
			}
			if (incomingRequest.containsKey("InvFormData_coverInk"))
			{
				String coverInk = (String ) incomingRequest.get("InvFormData_coverInk");
				invFormData.setCoverInk(coverInk);
			}
			if (incomingRequest.containsKey("InvFormData_pages"))
			{
				String pagesString = (String) incomingRequest.get("InvFormData_pages");
				if (Utility.isEmpty(pagesString))
				{
					pagesString = "0";
				}
				BigDecimal pages = new BigDecimal ( pagesString );
				invFormData.setPages(pages);
			}
			if (incomingRequest.containsKey("InvFormData_pageStock"))
			{
				String pageStock = (String ) incomingRequest.get("InvFormData_pageStock");
				invFormData.setPageStock(pageStock);
			}
			if (incomingRequest.containsKey("InvFormData_pageInk"))
			{
				String pageInk = (String ) incomingRequest.get("InvFormData_pageInk");
				invFormData.setPageInk(pageInk);
			}
			if (incomingRequest.containsKey("InvFormData_formLogo1"))
			{
				String formLogo1 = (String ) incomingRequest.get("InvFormData_formLogo1");
				invFormData.setFormLogo1(formLogo1);
			}
			if (incomingRequest.containsKey("InvFormData_formLogo2"))
			{
				String formLogo2 = (String ) incomingRequest.get("InvFormData_formLogo2");
				invFormData.setFormLogo2(formLogo2);
			}
			if (incomingRequest.containsKey("InvFormData_formFolding"))
			{
				String formFolding = (String ) incomingRequest.get("InvFormData_formFolding");
				invFormData.setFormFolding(formFolding);
			}
			if (incomingRequest.containsKey("InvFormData_formBinding"))
			{
				String formBinding = (String ) incomingRequest.get("InvFormData_formBinding");
				invFormData.setFormBinding(formBinding);
			}
			if (incomingRequest.containsKey("InvFormData_formPadding"))
			{
				String formPadding = (String ) incomingRequest.get("InvFormData_formPadding");
				invFormData.setFormPadding(formPadding);
			}
			if (incomingRequest.containsKey("InvFormData_formPkging"))
			{
				String formPkging = (String ) incomingRequest.get("InvFormData_formPkging");
				invFormData.setFormPkging(formPkging);
			}
			if (incomingRequest.containsKey("InvFormData_formArt"))
			{
				String formArt = (String ) incomingRequest.get("InvFormData_formArt");
				invFormData.setFormArt(formArt);
			}
			if (incomingRequest.containsKey("InvFormData_formSig1"))
			{
				String formSig1 = (String ) incomingRequest.get("InvFormData_formSig1");
				invFormData.setFormSig1(formSig1);
			}
			if (incomingRequest.containsKey("InvFormData_formSig2"))
			{
				String formSig2 = (String ) incomingRequest.get("InvFormData_formSig2");
				invFormData.setFormSig2(formSig2);
			}
			if (incomingRequest.containsKey("InvFormData_legalReview"))
			{
				String legalReview = (String ) incomingRequest.get("InvFormData_legalReview");
				invFormData.setLegalReview(legalReview);
			}
			if (incomingRequest.containsKey("InvFormData_numberFrom"))
			{
				String numberFrom = (String ) incomingRequest.get("InvFormData_numberFrom");
				invFormData.setNumberFrom(numberFrom);
			}
			if (incomingRequest.containsKey("InvFormData_numberTo"))
			{
				String numberTo = (String ) incomingRequest.get("InvFormData_numberTo");
				invFormData.setNumberTo(numberTo);
			}
			if (incomingRequest.containsKey("InvFormData_noMissing"))
			{
				String noMissing = (String ) incomingRequest.get("InvFormData_noMissing");
				invFormData.setNoMissing(noMissing);
			}
			if (incomingRequest.containsKey("InvFormData_listMissing"))
			{
				String listMissing = (String ) incomingRequest.get("InvFormData_listMissing");
				invFormData.setListMissing(listMissing);
			}
			if (incomingRequest.containsKey("InvFormData_numberPrefix"))
			{
				String numberPrefix = (String ) incomingRequest.get("InvFormData_numberPrefix");
				invFormData.setNumberPrefix(numberPrefix);
			}
			if (incomingRequest.containsKey("InvFormData_numberSuffix"))
			{
				String numberSuffix = (String ) incomingRequest.get("InvFormData_numberSuffix");
				invFormData.setNumberSuffix(numberSuffix);
			}
			if (incomingRequest.containsKey("InvFormData_micrCode"))
			{
				String micrCode = (String ) incomingRequest.get("InvFormData_micrCode");
				invFormData.setMicrCode(micrCode);
			}
			if (incomingRequest.containsKey("InvFormData_micrDesc"))
			{
				String micrDesc = (String ) incomingRequest.get("InvFormData_micrDesc");
				invFormData.setMicrDesc(micrDesc);
			}
			if (incomingRequest.containsKey("InvFormData_ocrCode"))
			{
				String ocrCode = (String ) incomingRequest.get("InvFormData_ocrCode");
				invFormData.setOcrCode(ocrCode);
			}
			if (incomingRequest.containsKey("InvFormData_ocrDesc"))
			{
				String ocrDesc = (String ) incomingRequest.get("InvFormData_ocrDesc");
				invFormData.setOcrDesc(ocrDesc);
			}
			if (incomingRequest.containsKey("InvFormData_numUdf1"))
			{
				String numUdf1 = (String ) incomingRequest.get("InvFormData_numUdf1");
				invFormData.setNumUdf1(numUdf1);
			}
			if (incomingRequest.containsKey("InvFormData_numUdf2"))
			{
				String numUdf2 = (String ) incomingRequest.get("InvFormData_numUdf2");
				invFormData.setNumUdf2(numUdf2);
			}
			if (incomingRequest.containsKey("InvFormData_numUdf3"))
			{
				String numUdf3 = (String ) incomingRequest.get("InvFormData_numUdf3");
				invFormData.setNumUdf3(numUdf3);
			}
			if (incomingRequest.containsKey("InvFormData_numUdf4"))
			{
				String numUdf4 = (String ) incomingRequest.get("InvFormData_numUdf4");
				invFormData.setNumUdf4(numUdf4);
			}
			if (incomingRequest.containsKey("InvFormData_numUdf5"))
			{
				String numUdf5 = (String ) incomingRequest.get("InvFormData_numUdf5");
				invFormData.setNumUdf5(numUdf5);
			}
			if (incomingRequest.containsKey("InvFormData_fastenPos"))
			{
				String fastenPos = (String ) incomingRequest.get("InvFormData_fastenPos");
				invFormData.setFastenPos(fastenPos);
			}
			if (incomingRequest.containsKey("InvFormData_fastenDesc"))
			{
				String fastenDesc = (String ) incomingRequest.get("InvFormData_fastenDesc");
				invFormData.setFastenDesc(fastenDesc);
			}
			if (incomingRequest.containsKey("InvFormData_fastenType"))
			{
				String fastenType = (String ) incomingRequest.get("InvFormData_fastenType");
				invFormData.setFastenType(fastenType);
			}
			if (incomingRequest.containsKey("InvFormData_binding"))
			{
				String binding = (String ) incomingRequest.get("InvFormData_binding");
				invFormData.setBinding(binding);
			}
			if (incomingRequest.containsKey("InvFormData_bindingPer"))
			{
				String bindingPerString = (String) incomingRequest.get("InvFormData_bindingPer");
				if (Utility.isEmpty(bindingPerString))
				{
					bindingPerString = "0";
				}
				BigDecimal bindingPer = new BigDecimal ( bindingPerString );
				invFormData.setBindingPer(bindingPer);
			}
			if (incomingRequest.containsKey("InvFormData_bindingCover"))
			{
				String bindingCover = (String ) incomingRequest.get("InvFormData_bindingCover");
				invFormData.setBindingCover(bindingCover);
			}
			if (incomingRequest.containsKey("InvFormData_bindingBack"))
			{
				String bindingBack = (String ) incomingRequest.get("InvFormData_bindingBack");
				invFormData.setBindingBack(bindingBack);
			}
			if (incomingRequest.containsKey("InvFormData_padding"))
			{
				String padding = (String ) incomingRequest.get("InvFormData_padding");
				invFormData.setPadding(padding);
			}
			if (incomingRequest.containsKey("InvFormData_paddingPer"))
			{
				String paddingPerString = (String) incomingRequest.get("InvFormData_paddingPer");
				if (Utility.isEmpty(paddingPerString))
				{
					paddingPerString = "0";
				}
				BigDecimal paddingPer = new BigDecimal ( paddingPerString );
				invFormData.setPaddingPer(paddingPer);
			}
			if (incomingRequest.containsKey("InvFormData_paddingCover"))
			{
				String paddingCover = (String ) incomingRequest.get("InvFormData_paddingCover");
				invFormData.setPaddingCover(paddingCover);
			}
			if (incomingRequest.containsKey("InvFormData_paddingBack"))
			{
				String paddingBack = (String ) incomingRequest.get("InvFormData_paddingBack");
				invFormData.setPaddingBack(paddingBack);
			}
			if (incomingRequest.containsKey("InvFormData_typewriter"))
			{
				String typewriter = (String ) incomingRequest.get("InvFormData_typewriter");
				invFormData.setTypewriter(typewriter);
			}
			if (incomingRequest.containsKey("InvFormData_typewriterVsp"))
			{
				String typewriterVspString = (String) incomingRequest.get("InvFormData_typewriterVsp");
				if (Utility.isEmpty(typewriterVspString))
				{
					typewriterVspString = "0";
				}
				BigDecimal typewriterVsp = new BigDecimal ( typewriterVspString );
				invFormData.setTypewriterVsp(typewriterVsp);
			}
			if (incomingRequest.containsKey("InvFormData_typewriterHsp"))
			{
				String typewriterHspString = (String) incomingRequest.get("InvFormData_typewriterHsp");
				if (Utility.isEmpty(typewriterHspString))
				{
					typewriterHspString = "0";
				}
				BigDecimal typewriterHsp = new BigDecimal ( typewriterHspString );
				invFormData.setTypewriterHsp(typewriterHsp);
			}
			if (incomingRequest.containsKey("InvFormData_printer"))
			{
				String printer = (String ) incomingRequest.get("InvFormData_printer");
				invFormData.setPrinter(printer);
			}
			if (incomingRequest.containsKey("InvFormData_printerVsp"))
			{
				String printerVspString = (String) incomingRequest.get("InvFormData_printerVsp");
				if (Utility.isEmpty(printerVspString))
				{
					printerVspString = "0";
				}
				BigDecimal printerVsp = new BigDecimal ( printerVspString );
				invFormData.setPrinterVsp(printerVsp);
			}
			if (incomingRequest.containsKey("InvFormData_printerHsp"))
			{
				String printerHspString = (String) incomingRequest.get("InvFormData_printerHsp");
				if (Utility.isEmpty(printerHspString))
				{
					printerHspString = "0";
				}
				BigDecimal printerHsp = new BigDecimal ( printerHspString );
				invFormData.setPrinterHsp(printerHsp);
			}
			if (incomingRequest.containsKey("InvFormData_decollator"))
			{
				String decollator = (String ) incomingRequest.get("InvFormData_decollator");
				invFormData.setDecollator(decollator);
			}
			if (incomingRequest.containsKey("InvFormData_burster"))
			{
				String burster = (String ) incomingRequest.get("InvFormData_burster");
				invFormData.setBurster(burster);
			}
			if (incomingRequest.containsKey("InvFormData_mailEquip"))
			{
				String mailEquip = (String ) incomingRequest.get("InvFormData_mailEquip");
				invFormData.setMailEquip(mailEquip);
			}
			if (incomingRequest.containsKey("InvFormData_envSize"))
			{
				String envSize = (String ) incomingRequest.get("InvFormData_envSize");
				invFormData.setEnvSize(envSize);
			}
			if (incomingRequest.containsKey("InvFormData_winSize"))
			{
				String winSize = (String ) incomingRequest.get("InvFormData_winSize");
				invFormData.setWinSize(winSize);
			}
			if (incomingRequest.containsKey("InvFormData_posFromleft"))
			{
				String posFromleftString = (String) incomingRequest.get("InvFormData_posFromleft");
				if (Utility.isEmpty(posFromleftString))
				{
					posFromleftString = "0";
				}
				BigDecimal posFromleft = new BigDecimal ( posFromleftString );
				invFormData.setPosFromleft(posFromleft);
			}
			if (incomingRequest.containsKey("InvFormData_posFrombot"))
			{
				String posFrombotString = (String) incomingRequest.get("InvFormData_posFrombot");
				if (Utility.isEmpty(posFrombotString))
				{
					posFrombotString = "0";
				}
				BigDecimal posFrombot = new BigDecimal ( posFrombotString );
				invFormData.setPosFrombot(posFrombot);
			}
			if (incomingRequest.containsKey("InvFormData_flapPos"))
			{
				String flapPos = (String ) incomingRequest.get("InvFormData_flapPos");
				invFormData.setFlapPos(flapPos);
			}
			if (incomingRequest.containsKey("InvFormData_envType"))
			{
				String envType = (String ) incomingRequest.get("InvFormData_envType");
				invFormData.setEnvType(envType);
			}
			if (incomingRequest.containsKey("InvFormData_gumType"))
			{
				String gumType = (String ) incomingRequest.get("InvFormData_gumType");
				invFormData.setGumType(gumType);
			}
			if (incomingRequest.containsKey("InvFormData_otherType"))
			{
				String otherType = (String ) incomingRequest.get("InvFormData_otherType");
				invFormData.setOtherType(otherType);
			}
			if (incomingRequest.containsKey("InvFormData_formColor"))
			{
				String formColor = (String ) incomingRequest.get("InvFormData_formColor");
				invFormData.setFormColor(formColor);
			}
			if (incomingRequest.containsKey("InvFormData_formWeight"))
			{
				String formWeight = (String ) incomingRequest.get("InvFormData_formWeight");
				invFormData.setFormWeight(formWeight);
			}
			if (incomingRequest.containsKey("InvFormData_formStock"))
			{
				String formStock = (String ) incomingRequest.get("InvFormData_formStock");
				invFormData.setFormStock(formStock);
			}
			if (incomingRequest.containsKey("InvFormData_formInk"))
			{
				String formInk = (String ) incomingRequest.get("InvFormData_formInk");
				invFormData.setFormInk(formInk);
			}
			if (incomingRequest.containsKey("InvFormData_labelSize"))
			{
				String labelSize = (String ) incomingRequest.get("InvFormData_labelSize");
				invFormData.setLabelSize(labelSize);
			}
			if (incomingRequest.containsKey("InvFormData_perfLoc"))
			{
				String perfLocString = (String) incomingRequest.get("InvFormData_perfLoc");
				if (Utility.isEmpty(perfLocString))
				{
					perfLocString = "0";
				}
				BigDecimal perfLoc = new BigDecimal ( perfLocString );
				invFormData.setPerfLoc(perfLoc);
			}
			if (incomingRequest.containsKey("InvFormData_appSurface"))
			{
				String appSurface = (String ) incomingRequest.get("InvFormData_appSurface");
				invFormData.setAppSurface(appSurface);
			}
			if (incomingRequest.containsKey("InvFormData_adhesive"))
			{
				String adhesive = (String ) incomingRequest.get("InvFormData_adhesive");
				invFormData.setAdhesive(adhesive);
			}
			if (incomingRequest.containsKey("InvFormData_qtyPerCtn"))
			{
				String qtyPerCtnString = (String) incomingRequest.get("InvFormData_qtyPerCtn");
				if (Utility.isEmpty(qtyPerCtnString))
				{
					qtyPerCtnString = "0";
				}
				BigDecimal qtyPerCtn = new BigDecimal ( qtyPerCtnString );
				invFormData.setQtyPerCtn(qtyPerCtn);
			}
			if (incomingRequest.containsKey("InvFormData_qtyPerPkg"))
			{
				String qtyPerPkgString = (String) incomingRequest.get("InvFormData_qtyPerPkg");
				if (Utility.isEmpty(qtyPerPkgString))
				{
					qtyPerPkgString = "0";
				}
				BigDecimal qtyPerPkg = new BigDecimal ( qtyPerPkgString );
				invFormData.setQtyPerPkg(qtyPerPkg);
			}
			if (incomingRequest.containsKey("InvFormData_pkgType"))
			{
				String pkgType = (String ) incomingRequest.get("InvFormData_pkgType");
				invFormData.setPkgType(pkgType);
			}
			if (incomingRequest.containsKey("InvFormData_pkgOption"))
			{
				String pkgOption = (String ) incomingRequest.get("InvFormData_pkgOption");
				invFormData.setPkgOption(pkgOption);
			}
			if (incomingRequest.containsKey("InvFormData_compCode"))
			{
				String compCode = (String ) incomingRequest.get("InvFormData_compCode");
				invFormData.setCompCode(compCode);
			}
			if (incomingRequest.containsKey("InvFormData_stockHbu"))
			{
				String stockHbu = (String ) incomingRequest.get("InvFormData_stockHbu");
				invFormData.setStockHbu(stockHbu);
			}
			if (incomingRequest.containsKey("InvFormData_sizeFlat"))
			{
				String sizeFlat = (String ) incomingRequest.get("InvFormData_sizeFlat");
				invFormData.setSizeFlat(sizeFlat);
			}
			if (incomingRequest.containsKey("InvFormData_coverPrints"))
			{
				String coverPrints = (String ) incomingRequest.get("InvFormData_coverPrints");
				invFormData.setCoverPrints(coverPrints);
			}
			if (incomingRequest.containsKey("InvFormData_bleeds"))
			{
				String bleeds = (String ) incomingRequest.get("InvFormData_bleeds");
				invFormData.setBleeds(bleeds);
			}
			if (incomingRequest.containsKey("InvFormData_proofs"))
			{
				String proofs = (String ) incomingRequest.get("InvFormData_proofs");
				invFormData.setProofs(proofs);
			}
			if (incomingRequest.containsKey("InvFormData_finishing"))
			{
				String finishing = (String ) incomingRequest.get("InvFormData_finishing");
				invFormData.setFinishing(finishing);
			}
			if (incomingRequest.containsKey("InvFormData_turnaround"))
			{
				String turnaround = (String ) incomingRequest.get("InvFormData_turnaround");
				invFormData.setTurnaround(turnaround);
			}
			if (incomingRequest.containsKey("InvFormData_formUdf01"))
			{
				String formUdf01 = (String ) incomingRequest.get("InvFormData_formUdf01");
				invFormData.setFormUdf01(formUdf01);
			}
			if (incomingRequest.containsKey("InvFormData_formUdf02"))
			{
				String formUdf02 = (String ) incomingRequest.get("InvFormData_formUdf02");
				invFormData.setFormUdf02(formUdf02);
			}
			if (incomingRequest.containsKey("InvFormData_specUdf01"))
			{
				String specUdf01 = (String ) incomingRequest.get("InvFormData_specUdf01");
				invFormData.setSpecUdf01(specUdf01);
			}
			if (incomingRequest.containsKey("InvFormData_specUdf02"))
			{
				String specUdf02 = (String ) incomingRequest.get("InvFormData_specUdf02");
				invFormData.setSpecUdf02(specUdf02);
			}
			if (incomingRequest.containsKey("InvFormData_specUdf03"))
			{
				String specUdf03 = (String ) incomingRequest.get("InvFormData_specUdf03");
				invFormData.setSpecUdf03(specUdf03);
			}
			if (incomingRequest.containsKey("InvFormData_specNotes"))
			{
				String specNotes = (String ) incomingRequest.get("InvFormData_specNotes");
				invFormData.setSpecNotes(specNotes);
			}
			if (incomingRequest.containsKey("InvFormData_formAddress"))
			{
				String formAddress = (String ) incomingRequest.get("InvFormData_formAddress");
				invFormData.setFormAddress(formAddress);
			}
			if (incomingRequest.containsKey("InvFormData_maxReqQty"))
			{
				String maxReqQtyString = (String) incomingRequest.get("InvFormData_maxReqQty");
				if (Utility.isEmpty(maxReqQtyString))
				{
					maxReqQtyString = "0";
				}
				BigDecimal maxReqQty = new BigDecimal ( maxReqQtyString );
				invFormData.setMaxReqQty(maxReqQty);
			}
			if (incomingRequest.containsKey("InvFormData_appointedFlag"))
			{
				String appointedFlag = (String ) incomingRequest.get("InvFormData_appointedFlag");
				invFormData.setAppointedFlag(appointedFlag);
			}
			if (incomingRequest.containsKey("InvFormData_equivalentStock"))
			{
				String equivalentStock = (String ) incomingRequest.get("InvFormData_equivalentStock");
				invFormData.setEquivalentStock(equivalentStock);
			}
			if (incomingRequest.containsKey("InvFormData_autoReprint"))
			{
				String autoReprint = (String ) incomingRequest.get("InvFormData_autoReprint");
				invFormData.setAutoReprint(autoReprint);
			}
			if (incomingRequest.containsKey("InvFormData_inprintableSpace"))
			{
				String inprintableSpace = (String ) incomingRequest.get("InvFormData_inprintableSpace");
				invFormData.setInprintableSpace(inprintableSpace);
			}
			if (incomingRequest.containsKey("InvFormData_formTraps"))
			{
				String formTraps = (String ) incomingRequest.get("InvFormData_formTraps");
				invFormData.setFormTraps(formTraps);
			}
			if (incomingRequest.containsKey("InvFormData_colorSeparation"))
			{
				String colorSeparation = (String ) incomingRequest.get("InvFormData_colorSeparation");
				invFormData.setColorSeparation(colorSeparation);
			}
			if (incomingRequest.containsKey("InvFormData_supInstruction"))
			{
				String supInstruction = (String ) incomingRequest.get("InvFormData_supInstruction");
				invFormData.setSupInstruction(supInstruction);
			}
			if (incomingRequest.containsKey("InvFormData_useType"))
			{
				String useType = (String ) incomingRequest.get("InvFormData_useType");
				invFormData.setUseType(useType);
			}
			if (incomingRequest.containsKey("InvFormData_mailDate"))
			{
				String mailDateString = (String) incomingRequest.get("InvFormData_mailDate");
				Date mailDate = Dates.getDate(mailDateString);
				invFormData.setMailDate(mailDate);
			}
			if (incomingRequest.containsKey("InvFormData_envLabel"))
			{
				String envLabel = (String ) incomingRequest.get("InvFormData_envLabel");
				invFormData.setEnvLabel(envLabel);
			}
			if (incomingRequest.containsKey("InvFormData_envColor"))
			{
				String envColor = (String ) incomingRequest.get("InvFormData_envColor");
				invFormData.setEnvColor(envColor);
			}
			if (incomingRequest.containsKey("InvFormData_envWeight"))
			{
				String envWeight = (String ) incomingRequest.get("InvFormData_envWeight");
				invFormData.setEnvWeight(envWeight);
			}
			if (incomingRequest.containsKey("InvFormData_envStock"))
			{
				String envStock = (String ) incomingRequest.get("InvFormData_envStock");
				invFormData.setEnvStock(envStock);
			}
			if (incomingRequest.containsKey("InvFormData_envInk"))
			{
				String envInk = (String ) incomingRequest.get("InvFormData_envInk");
				invFormData.setEnvInk(envInk);
			}
			if (incomingRequest.containsKey("InvFormData_envOther"))
			{
				String envOther = (String ) incomingRequest.get("InvFormData_envOther");
				invFormData.setEnvOther(envOther);
			}
			if (incomingRequest.containsKey("InvFormData_dateOut"))
			{
				String dateOutString = (String) incomingRequest.get("InvFormData_dateOut");
				Date dateOut = Dates.getDate(dateOutString);
				invFormData.setDateOut(dateOut);
			}
			if (incomingRequest.containsKey("InvFormData_docPrtDate"))
			{
				String docPrtDateString = (String) incomingRequest.get("InvFormData_docPrtDate");
				Date docPrtDate = Dates.getDate(docPrtDateString);
				invFormData.setDocPrtDate(docPrtDate);
			}
			if (incomingRequest.containsKey("InvFormData_puAppDate"))
			{
				String puAppDateString = (String) incomingRequest.get("InvFormData_puAppDate");
				Date puAppDate = Dates.getDate(puAppDateString);
				invFormData.setPuAppDate(puAppDate);
			}
			if (incomingRequest.containsKey("InvFormData_faAppDate"))
			{
				String faAppDateString = (String) incomingRequest.get("InvFormData_faAppDate");
				Date faAppDate = Dates.getDate(faAppDateString);
				invFormData.setFaAppDate(faAppDate);
			}
			if (incomingRequest.containsKey("InvFormData_automaticReprint"))
			{
				String automaticReprint = (String ) incomingRequest.get("InvFormData_automaticReprint");
				invFormData.setAutomaticReprint(automaticReprint);
			}

			result = invFormData;
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