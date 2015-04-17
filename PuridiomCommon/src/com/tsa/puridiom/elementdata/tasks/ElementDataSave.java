package com.tsa.puridiom.elementdata.tasks;

import com.tsa.puridiom.entity.ElementData;
import com.tsa.puridiom.entity.ElementDataPK;
import com.tsa.puridiom.entity.ElementForm;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ElementDataSave extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String formId = (String ) incomingRequest.get("ElementForm_formId");

			List					formDefList = (List) incomingRequest.get("formDefList") ;
			if (formDefList == null) formDefList = new ArrayList() ;
			List					formDataList = (List) incomingRequest.get("formDataList") ;
			if (formDataList == null) formDataList = new ArrayList() ;

			String icHeaderString = (String) incomingRequest.get("ElementData_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );

			String icLineString = (String) incomingRequest.get("ElementData_icLine");
			BigDecimal icLine = new BigDecimal ( icLineString );

			String icSequenceString = (String) incomingRequest.get("ElementData_icSequence") ;
			if (icSequenceString == null) icSequenceString = "0";
			BigDecimal icSequence = new BigDecimal ( icSequenceString );


			int defCount = formDefList.size() ;
			int dataCount = formDataList.size() ;
			for (int ix = 0; ix < defCount; ix++) {
				ElementForm formDef = (ElementForm) formDefList.get(ix) ;
				String eId = formDef.getElementId() ;
				if (eId== null || eId.equalsIgnoreCase("(blank")) continue ;
				String dataValue = (String) incomingRequest.get(eId) ;

				int eRow = findElement(formDataList, eId) ;

				if (eRow < 0) {
					// Add the record if not found
					ElementData formData = new ElementData() ;
					ElementDataPK formDataPk = new ElementDataPK() ;

					formDataPk.setElementId(eId) ;
					formDataPk.setFormId(formId) ;
					formDataPk.setIcHeader(icHeader) ;
					formDataPk.setIcLine(icLine) ;
					formDataPk.setIcSequence(icSequence);

					formData.setComp_id(formDataPk) ;
					formData.setElementValue(dataValue) ;
					dbs.add(formData) ;
					this.setStatus(dbs.getStatus()) ;

				} else {
					// Update the record
					ElementData formData = (ElementData) formDataList.get(eRow) ;
					formData.setElementValue(dataValue) ;
					dbs.update(formData) ;
					this.setStatus(dbs.getStatus()) ;
				}
			}

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;

	}

	private int findElement(List formDataList, String eId) {
		int  found = -1 ;
		int dataCount = formDataList.size() ;
		for (int ic = 0; ic < dataCount; ic++) {
			ElementData formData = (ElementData) formDataList.get(ic) ;
			if (eId.equalsIgnoreCase(formData.getComp_id().getElementId()) ) {
				found = ic ;
				break ;
			}
		}
		return found ;
	}
}