package com.tsa.puridiom.invformpart.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvFormPart;
import com.tsa.puridiom.entity.InvFormPartPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InvFormPartSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain invFormPart */
			InvFormPartPK comp_id = new InvFormPartPK();
			InvFormPart	originalInvFormPart = (InvFormPart) incomingRequest.get("invFormPart");
			InvFormPart	invFormPart = new InvFormPart();
			
			String	newItemNumber = (String) incomingRequest.get("newInvFormPart_itemNumber");

			comp_id.setItemNumber(newItemNumber);
			comp_id.setFormPart(originalInvFormPart.getComp_id().getFormPart());
			invFormPart.setMeasureBy(originalInvFormPart.getMeasureBy());
			invFormPart.setOverallWidth(originalInvFormPart.getOverallWidth());
			invFormPart.setOverallLen(originalInvFormPart.getOverallLen());
			invFormPart.setDetachWidth(originalInvFormPart.getDetachWidth());
			invFormPart.setDetachLen(originalInvFormPart.getDetachLen());
			invFormPart.setSizeUdf(originalInvFormPart.getSizeUdf());
			invFormPart.setPrintSides(originalInvFormPart.getPrintSides());
			invFormPart.setPrintCopies(originalInvFormPart.getPrintCopies());
			invFormPart.setMarginWords(originalInvFormPart.getMarginWords());
			invFormPart.setAddchgdel(originalInvFormPart.getAddchgdel());
			invFormPart.setBlockout(originalInvFormPart.getBlockout());
			invFormPart.setCopiesUdf(originalInvFormPart.getCopiesUdf());
			invFormPart.setPaperColor(originalInvFormPart.getPaperColor());
			invFormPart.setPaperWeight(originalInvFormPart.getPaperWeight());
			invFormPart.setPaperGrade(originalInvFormPart.getPaperGrade());
			invFormPart.setInkFront(originalInvFormPart.getInkFront());
			invFormPart.setInkBack(originalInvFormPart.getInkBack());
			invFormPart.setPaperUdf(originalInvFormPart.getPaperUdf());
			invFormPart.setInkUdf(originalInvFormPart.getInkUdf());
			invFormPart.setCarbonWeight(originalInvFormPart.getCarbonWeight());
			invFormPart.setCarbonGrade(originalInvFormPart.getCarbonGrade());
			invFormPart.setCarbonColor(originalInvFormPart.getCarbonColor());
			invFormPart.setCarbonSize(originalInvFormPart.getCarbonSize());
			invFormPart.setCarbonSpot(originalInvFormPart.getCarbonSpot());
			invFormPart.setCarbonStrip(originalInvFormPart.getCarbonStrip());
			invFormPart.setCarbonUdf(originalInvFormPart.getCarbonUdf());
			invFormPart.setPunchHoles(originalInvFormPart.getPunchHoles());
			invFormPart.setPunchSize(originalInvFormPart.getPunchSize());
			invFormPart.setPunchCenter(originalInvFormPart.getPunchCenter());
			invFormPart.setPunchPos(originalInvFormPart.getPunchPos());
			invFormPart.setMperfLeft(originalInvFormPart.getMperfLeft());
			invFormPart.setMperfRight(originalInvFormPart.getMperfRight());
			invFormPart.setOperfLeft(originalInvFormPart.getOperfLeft());
			invFormPart.setOperfRight(originalInvFormPart.getOperfRight());
			invFormPart.setPerfType(originalInvFormPart.getPerfType());
			invFormPart.setTurnType(originalInvFormPart.getTurnType());
			invFormPart.setComp_id(comp_id);

			incomingRequest.put("invFormPart", invFormPart);

			InvFormPartAdd addTask = new InvFormPartAdd();
			invFormPart = (InvFormPart) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = invFormPart;
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