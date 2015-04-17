package com.tsa.puridiom.invformdata.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvFormData;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InvFormDataSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain invFormData */
			InvFormData	originalInvFormData = (InvFormData) incomingRequest.get("invFormData");
			InvFormData	invFormData = new InvFormData();
			
			String	newItemNumber = (String) incomingRequest.get("newInvFormData_itemNumber"); 

			invFormData.setItemNumber(newItemNumber);
			invFormData.setIcNotes(originalInvFormData.getIcNotes());
			invFormData.setStoItemNumber(originalInvFormData.getStoItemNumber());
			invFormData.setSto2ItemNumber(originalInvFormData.getSto2ItemNumber());
			invFormData.setSto3ItemNumber(originalInvFormData.getSto3ItemNumber());
			invFormData.setFormNumber(originalInvFormData.getFormNumber());
			invFormData.setFormRevision(originalInvFormData.getFormRevision());
			invFormData.setFormReplaces(originalInvFormData.getFormReplaces());
			invFormData.setPrimeUser(originalInvFormData.getPrimeUser());
			invFormData.setDepartment(originalInvFormData.getDepartment());
			invFormData.setOfficeLoc(originalInvFormData.getOfficeLoc());
			invFormData.setBudgetCode(originalInvFormData.getBudgetCode());
			invFormData.setLobCode(originalInvFormData.getLobCode());
			invFormData.setFormType(originalInvFormData.getFormType());
			invFormData.setFormClass(originalInvFormData.getFormClass());
			invFormData.setFormSize(originalInvFormData.getFormSize());
			invFormData.setCoverStock(originalInvFormData.getCoverStock());
			invFormData.setCoverInk(originalInvFormData.getCoverInk());
			invFormData.setPages(originalInvFormData.getPages());
			invFormData.setPageStock(originalInvFormData.getPageStock());
			invFormData.setPageInk(originalInvFormData.getPageInk());
			invFormData.setFormLogo1(originalInvFormData.getFormLogo1());
			invFormData.setFormLogo2(originalInvFormData.getFormLogo2());
			invFormData.setFormFolding(originalInvFormData.getFormFolding());
			invFormData.setFormBinding(originalInvFormData.getFormBinding());
			invFormData.setFormPadding(originalInvFormData.getFormPadding());
			invFormData.setFormPkging(originalInvFormData.getFormPkging());
			invFormData.setFormArt(originalInvFormData.getFormArt());
			invFormData.setFormSig1(originalInvFormData.getFormSig1());
			invFormData.setFormSig2(originalInvFormData.getFormSig2());
			invFormData.setLegalReview(originalInvFormData.getLegalReview());
			invFormData.setNumberFrom(originalInvFormData.getNumberFrom());
			invFormData.setNumberTo(originalInvFormData.getNumberTo());
			invFormData.setNoMissing(originalInvFormData.getNoMissing());
			invFormData.setListMissing(originalInvFormData.getListMissing());
			invFormData.setNumberPrefix(originalInvFormData.getNumberPrefix());
			invFormData.setNumberSuffix(originalInvFormData.getNumberSuffix());
			invFormData.setMicrCode(originalInvFormData.getMicrCode());
			invFormData.setMicrDesc(originalInvFormData.getMicrDesc());
			invFormData.setOcrCode(originalInvFormData.getOcrCode());
			invFormData.setOcrDesc(originalInvFormData.getOcrDesc());
			invFormData.setNumUdf1(originalInvFormData.getNumUdf1());
			invFormData.setNumUdf2(originalInvFormData.getNumUdf2());
			invFormData.setNumUdf3(originalInvFormData.getNumUdf3());
			invFormData.setNumUdf4(originalInvFormData.getNumUdf4());
			invFormData.setNumUdf5(originalInvFormData.getNumUdf5());
			invFormData.setFastenPos(originalInvFormData.getFastenPos());
			invFormData.setFastenDesc(originalInvFormData.getFastenDesc());
			invFormData.setFastenType(originalInvFormData.getFastenType());
			invFormData.setBinding(originalInvFormData.getBinding());
			invFormData.setBindingPer(originalInvFormData.getBindingPer());
			invFormData.setBindingCover(originalInvFormData.getBindingCover());
			invFormData.setBindingBack(originalInvFormData.getBindingBack());
			invFormData.setPadding(originalInvFormData.getPadding());
			invFormData.setPaddingPer(originalInvFormData.getPaddingPer());
			invFormData.setPaddingCover(originalInvFormData.getPaddingCover());
			invFormData.setPaddingBack(originalInvFormData.getPaddingBack());
			invFormData.setTypewriter(originalInvFormData.getTypewriter());
			invFormData.setTypewriterVsp(originalInvFormData.getTypewriterVsp());
			invFormData.setTypewriterHsp(originalInvFormData.getTypewriterHsp());
			invFormData.setPrinter(originalInvFormData.getPrinter());
			invFormData.setPrinterVsp(originalInvFormData.getPrinterVsp());
			invFormData.setPrinterHsp(originalInvFormData.getPrinterHsp());
			invFormData.setDecollator(originalInvFormData.getDecollator());
			invFormData.setBurster(originalInvFormData.getBurster());
			invFormData.setMailEquip(originalInvFormData.getMailEquip());
			invFormData.setEnvSize(originalInvFormData.getEnvSize());
			invFormData.setWinSize(originalInvFormData.getWinSize());
			invFormData.setPosFromleft(originalInvFormData.getPosFromleft());
			invFormData.setPosFrombot(originalInvFormData.getPosFrombot());
			invFormData.setFlapPos(originalInvFormData.getFlapPos());
			invFormData.setEnvType(originalInvFormData.getEnvType());
			invFormData.setGumType(originalInvFormData.getGumType());
			invFormData.setOtherType(originalInvFormData.getOtherType());
			invFormData.setFormColor(originalInvFormData.getFormColor());
			invFormData.setFormWeight(originalInvFormData.getFormWeight());
			invFormData.setFormStock(originalInvFormData.getFormStock());
			invFormData.setFormInk(originalInvFormData.getFormInk());
			invFormData.setLabelSize(originalInvFormData.getLabelSize());
			invFormData.setPerfLoc(originalInvFormData.getPerfLoc());
			invFormData.setAppSurface(originalInvFormData.getAppSurface());
			invFormData.setAdhesive(originalInvFormData.getAdhesive());
			invFormData.setQtyPerCtn(originalInvFormData.getQtyPerCtn());
			invFormData.setQtyPerPkg(originalInvFormData.getQtyPerPkg());
			invFormData.setPkgType(originalInvFormData.getPkgType());
			invFormData.setPkgOption(originalInvFormData.getPkgOption());
			invFormData.setCompCode(originalInvFormData.getCompCode());
			invFormData.setStockHbu(originalInvFormData.getStockHbu());
			invFormData.setSizeFlat(originalInvFormData.getSizeFlat());
			invFormData.setCoverPrints(originalInvFormData.getCoverPrints());
			invFormData.setBleeds(originalInvFormData.getBleeds());
			invFormData.setProofs(originalInvFormData.getProofs());
			invFormData.setFinishing(originalInvFormData.getFinishing());
			invFormData.setTurnaround(originalInvFormData.getTurnaround());
			invFormData.setFormUdf01(originalInvFormData.getFormUdf01());
			invFormData.setFormUdf02(originalInvFormData.getFormUdf02());
			invFormData.setSpecUdf01(originalInvFormData.getSpecUdf01());
			invFormData.setSpecUdf02(originalInvFormData.getSpecUdf02());
			invFormData.setSpecUdf03(originalInvFormData.getSpecUdf03());
			invFormData.setSpecNotes(originalInvFormData.getSpecNotes());
			invFormData.setFormAddress(originalInvFormData.getFormAddress());
			invFormData.setMaxReqQty(originalInvFormData.getMaxReqQty());
			invFormData.setAppointedFlag(originalInvFormData.getAppointedFlag());
			invFormData.setEquivalentStock(originalInvFormData.getEquivalentStock());
			invFormData.setAutoReprint(originalInvFormData.getAutoReprint());
			invFormData.setInprintableSpace(originalInvFormData.getInprintableSpace());
			invFormData.setFormTraps(originalInvFormData.getFormTraps());
			invFormData.setColorSeparation(originalInvFormData.getColorSeparation());
			invFormData.setSupInstruction(originalInvFormData.getSupInstruction());
			invFormData.setUseType(originalInvFormData.getUseType());
			invFormData.setMailDate(originalInvFormData.getMailDate());
			invFormData.setEnvLabel(originalInvFormData.getEnvLabel());
			invFormData.setEnvColor(originalInvFormData.getEnvColor());
			invFormData.setEnvWeight(originalInvFormData.getEnvWeight());
			invFormData.setEnvStock(originalInvFormData.getEnvStock());
			invFormData.setEnvInk(originalInvFormData.getEnvInk());
			invFormData.setEnvOther(originalInvFormData.getEnvOther());
			invFormData.setDateOut(originalInvFormData.getDateOut());
			invFormData.setDocPrtDate(originalInvFormData.getDocPrtDate());
			invFormData.setPuAppDate(originalInvFormData.getPuAppDate());
			invFormData.setFaAppDate(originalInvFormData.getFaAppDate());
			invFormData.setAutomaticReprint(originalInvFormData.getAutomaticReprint());

			incomingRequest.put("invFormData", invFormData);

			InvFormDataAdd addTask = new InvFormDataAdd();
			invFormData = (InvFormData) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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