package com.tsa.puridiom.labels.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.Labels;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class LabelsSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		String oid = (String)incomingRequest.get("organizationId") ;
		String userId = (String)incomingRequest.get("userId") ;
		String dateFormat = PropertiesManager.getInstance(oid).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
		String userTimeZone = (String)incomingRequest.get("userTimeZone") ;

		try
		{
			Labels labels = (Labels) incomingRequest.get("labels");
			if (labels == null)
			{
				labels = new Labels();
			}

			if (incomingRequest.containsKey("Labels_icLabel"))
			{
				String icLabelString = (String) incomingRequest.get("Labels_icLabel");
				if (Utility.isEmpty(icLabelString))
				{
					icLabelString = "0";
				}
				BigDecimal icLabel = new BigDecimal ( icLabelString );
				labels.setIcLabel(icLabel);
			}
			if (incomingRequest.containsKey("Labels_labelCode"))
			{
				String labelCode = (String) incomingRequest.get("Labels_labelCode");
				labels.setLabelCode(labelCode);
			}
			if (incomingRequest.containsKey("Labels_moduleAccess"))
			{
				String moduleAccess = (String) incomingRequest.get("Labels_moduleAccess");
				labels.setModuleAccess(moduleAccess);
			}
			if (incomingRequest.containsKey("Labels_module"))
			{
				String module = (String) incomingRequest.get("Labels_module");
				labels.setModule(module);
			}
			if (incomingRequest.containsKey("Labels_moduleType"))
			{
				String moduleType = (String) incomingRequest.get("Labels_moduleType");
				labels.setModuleType(moduleType);
			}
			if (incomingRequest.containsKey("Labels_language"))
			{
				String language = (String) incomingRequest.get("Labels_language");
				labels.setLanguage(language);
			}
			if (incomingRequest.containsKey("Labels_labelValue"))
			{
				String labelValue = (String) incomingRequest.get("Labels_labelValue");
				labels.setLabelValue(labelValue);
			}
			if (incomingRequest.containsKey("Labels_abbreviation"))
			{
				String abbreviation = (String) incomingRequest.get("Labels_abbreviation");
				labels.setAbbreviation(abbreviation);
			}
			if (incomingRequest.containsKey("Labels_hoverHelp"))
			{
				String hoverHelp = (String) incomingRequest.get("Labels_hoverHelp");
				labels.setHoverHelp(hoverHelp);
			}
			if (incomingRequest.containsKey("Labels_visible"))
			{
				String visible = (String) incomingRequest.get("Labels_visible");
				labels.setVisible(visible);
			}
			if (incomingRequest.containsKey("Labels_required"))
			{
				String required = (String) incomingRequest.get("Labels_required");
				labels.setRequired(required);
			}
			if (incomingRequest.containsKey("Labels_validation"))
			{
				String validation = (String) incomingRequest.get("Labels_validation");
				labels.setValidation(validation);
			}
			if (incomingRequest.containsKey("Labels_validationSeverity"))
			{
				String validationSeverity = (String) incomingRequest.get("Labels_validationSeverity");
				labels.setValidationSeverity(validationSeverity);
			}
			if (incomingRequest.containsKey("Labels_validationMessage"))
			{
				String validationMessage = (String) incomingRequest.get("Labels_validationMessage");
				labels.setValidationMessage(validationMessage);
			}
			if (incomingRequest.containsKey("Labels_validationLink"))
			{
				String validationLink = (String) incomingRequest.get("Labels_validationLink");
				//BDC@1 value was created to sckip the crossFilter Filter 'cause we need to save javascript code in the DB
				validationLink = validationLink.replaceAll("BDC@1", "javascript: doSubmit(");
				labels.setValidationLink(validationLink);
			}
			if (incomingRequest.containsKey("Labels_fieldname"))
			{
				String fieldname = (String) incomingRequest.get("Labels_fieldname");
				labels.setFieldname(fieldname);
			}
			if (incomingRequest.containsKey("Labels_browseName"))
			{
				String browseName = (String) incomingRequest.get("Labels_browseName");
				labels.setBrowseName(browseName);
			}
			if (incomingRequest.containsKey("Labels_udf1Code"))
			{
				String udf1Code = (String) incomingRequest.get("Labels_udf1Code");
				labels.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("Labels_udf2Code"))
			{
				String udf2Code = (String) incomingRequest.get("Labels_udf2Code");
				labels.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("Labels_udf3Code"))
			{
				String udf3Code = (String) incomingRequest.get("Labels_udf3Code");
				labels.setUdf3Code(udf3Code);
			}
			if (incomingRequest.containsKey("Labels_udf4Code"))
			{
				String udf4Code = (String) incomingRequest.get("Labels_udf4Code");
				labels.setUdf4Code(udf4Code);
			}
			if (incomingRequest.containsKey("Labels_udf5Code"))
			{
				String udf5Code = (String) incomingRequest.get("Labels_udf5Code");
				labels.setUdf5Code(udf5Code);
			}
			if (incomingRequest.containsKey("Labels_status"))
			{
				String status = (String) incomingRequest.get("Labels_status");
				labels.setStatus(status);
			}
			if (incomingRequest.containsKey("Labels_owner"))
			{
				String owner = (String) incomingRequest.get("Labels_owner");
				labels.setOwner(owner);
			}
			if (incomingRequest.containsKey("Labels_lastChangeBy"))
			{
				String lastChangeBy = (String) incomingRequest.get("Labels_lastChangeBy");
				labels.setLastChangeBy(lastChangeBy);
			}
			else {
				labels.setLastChangeBy(userId);
			}
			if (incomingRequest.containsKey("Labels_lastChangeDate"))
			{
				String lastChangeDateString = (String) incomingRequest.get("Labels_lastChangeDate");
				Date lastChangeDate = Dates.getSqlDate(lastChangeDateString, dateFormat);
				labels.setLastChangeDate(lastChangeDate);
			}
			else {
				labels.setLastChangeDate(Dates.getCurrentDate(userTimeZone));
			}
			if (incomingRequest.containsKey("Labels_allowEdit"))
			{
				String allowEdit = (String) incomingRequest.get("Labels_allowEdit");
				labels.setAllowEdit(allowEdit);
			}
			if (incomingRequest.containsKey("Labels_readOnly"))
			{
				String readOnly = (String) incomingRequest.get("Labels_readOnly");
				labels.setReadOnly(readOnly);
			}
			if (incomingRequest.containsKey("Labels_allowLink"))
			{
				String allowLink = (String) incomingRequest.get("Labels_allowLink");
				labels.setAllowLink(allowLink);
			}
			if (incomingRequest.containsKey("Labels_fieldLength"))
			{
				String fieldLength = (String) incomingRequest.get("Labels_fieldLength");
				labels.setFieldLength(fieldLength);
			}

			result = labels;
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
