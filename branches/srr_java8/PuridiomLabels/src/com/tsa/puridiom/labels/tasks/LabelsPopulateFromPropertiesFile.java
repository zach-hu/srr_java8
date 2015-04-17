/*
 * Created on May 29, 2009
 */
package com.tsa.puridiom.labels.tasks;


import java.math.BigDecimal;
import java.util.*;
import com.tsa.puridiom.common.documents.GeneralStatus;
import com.tsa.puridiom.entity.Labels;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;
import com.tsagate.properties.LabelsDictionary;

/**
 * @author Kelli
 */
public class LabelsPopulateFromPropertiesFile extends Task {
	List mainLabelProperties = new ArrayList();

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			String userId = "AUTO";
            String language = Utility.ckNull((String) incomingRequest.get("Labels_language"));
            String organizationId = (String) incomingRequest.get("organizationId");
            List labelsList = new ArrayList();

            if (Utility.isEmpty(language)) {
            	language = Utility.ckNull((String) incomingRequest.get("language"));
            }
            if (Utility.isEmpty(language)) {
            	language = "EN";
            }

            LabelsDictionary labelsDictionary = DictionaryManager.getLabelsInstance(organizationId, language);
            PropertyResourceBundle bundle = (PropertyResourceBundle) DictionaryManager.getLabelsInstance(organizationId, language).bundle;
            this.mainLabelProperties = this.stripProperties(bundle);

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
            PuridiomProcess process = processLoader.loadProcess("labels-add.xml");

            Map updateParameters = new HashMap();
            updateParameters.put("organizationId", organizationId);
            updateParameters.put("userId", userId);
            updateParameters.put("dbsession", incomingRequest.get("dbsession"));

            for (int i = 0; i < mainLabelProperties.size(); i++) {
                String labelCode = (String) mainLabelProperties.get(i);
                String labelValue = labelsDictionary.getLabel(organizationId, labelCode, "");
                if (Utility.isEmpty(labelCode) || Utility.isEmpty(labelValue)) {
                	continue;
                }
                String abbreviation = labelsDictionary.getLabel(organizationId, "hdg-" + labelCode, "");
                String browseName = labelsDictionary.getLabel(organizationId, labelCode + "-browseName", "");
                String browseType = labelsDictionary.getLabel(organizationId, labelCode + "-browseType", "");
                String validationMessage = labelsDictionary.getLabel(organizationId, "val" + labelCode, "");
                String moduleAccess = this.getModuleAccessValue(labelCode);
                String module = this.getModuleValue(labelCode);
                String moduleType = this.getModuleTypeValue(labelCode);
                String visible = "Y";
                String readOnly = "N";
                String required = "N";
                String link = "Y";
                String hoverHelp = labelsDictionary.getLabel(organizationId, labelCode + "-alt", "Click here to selec");
                boolean isVisible = labelsDictionary.isVisible(organizationId, labelCode);
                boolean isReadOnly = labelsDictionary.isReadOnly(organizationId, labelCode);
                boolean isRequired = labelsDictionary.isRequired(organizationId, labelCode);
                boolean isLink = labelsDictionary.isLink(organizationId, labelCode);

                if (Utility.isEmpty(abbreviation)) {
                	abbreviation = labelsDictionary.getLabel(organizationId, "brw-" + labelCode, "");
                }
                if (Utility.isEmpty(hoverHelp)) {
                	hoverHelp = "Click here to select a valid " + labelValue;
                }
                if (Utility.isEmpty(validationMessage)) {
                	validationMessage = "You must enter/select a valid " + labelValue + ".";
                }
                if (!isVisible) {
                	visible = "N";
                }
                if (!isRequired) {
                	required = "N";
                }
                if (!isLink) {
                	link  = "N";
                }
                if (labelCode.indexOf("-") > 0) {
                	module = labelCode.substring(0, labelCode.indexOf("-"));
                	labelCode = labelCode.substring(labelCode.indexOf("-") + 1);
                }

                Labels labels = new Labels();

                labels.setIcLabel(new BigDecimal(i + 1));
                labels.setAbbreviation(abbreviation);
                labels.setAllowEdit("Y");
                labels.setAllowLink(link);
                labels.setBrowseName(browseName);
                labels.setHoverHelp(hoverHelp);
                labels.setLabelCode(labelCode);
                labels.setLabelValue(labelValue);
                labels.setLanguage(language);
                labels.setLastChangeBy(userId);
                labels.setLastChangeDate(Dates.getCurrentDate(""));
                labels.setModuleAccess(moduleAccess);
                labels.setModule(module);
                labels.setModuleType(moduleType);
                labels.setOwner(userId);
                labels.setRequired(required);
                labels.setStatus(GeneralStatus.STATUS_TEMPORARY);
                labels.setUdf1Code(browseType);
                labels.setUdf2Code("");
                labels.setUdf3Code("");
                labels.setUdf4Code("");
                labels.setUdf5Code("");
                labels.setValidation("N");
                labels.setValidationSeverity("E");
                labels.setValidationMessage(validationMessage);
                labels.setVisible(visible);

                updateParameters.put("labels", labels);

                process.executeProcess(updateParameters);

                labelsList.add(labels);
            }

            result = labelsList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

	List stripProperties(PropertyResourceBundle bundle) {
		List propertyList = new ArrayList();

		Enumeration enumeration = bundle.getKeys();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			if (key.endsWith("-visible")) {
				continue;
			} else if (key.endsWith("-required")) {
				continue;
			} else if (key.endsWith("-browseName")) {
				continue;
			} else if (key.endsWith("-browseType")) {
				continue;
			} else if (key.endsWith("-alt")) {
				continue;
			} else if (key.startsWith("brw-")) {
				continue;
			} else if (key.startsWith("hdg-")) {
				continue;
			} else if (key.startsWith("val")) {
				System.out.println("skipping validation: " + key);
				continue;
			} else if (key.indexOf("-") > 0 && (key.substring(key.indexOf("-") + 1)).indexOf("-") > 0) {
				// check for main property
				continue;
			} else {
				propertyList.add(key);
			}
		}
		return propertyList;
	}

	private String getModuleValue(String labelCode) {
		Map moduleValuesMap = this.getModuleValuesMap(labelCode);
		String module = (String) moduleValuesMap.get("MODULE");
		return Utility.ckNull(module);
	}

	private String getModuleAccessValue(String labelCode) {
		Map moduleValuesMap = this.getModuleValuesMap(labelCode);
		String moduleAccess = (String) moduleValuesMap.get("MODULE_ACCESS");
		return Utility.ckNull(moduleAccess);
	}

	private String getModuleTypeValue(String labelCode) {
		Map moduleValuesMap = this.getModuleValuesMap(labelCode);
		String moduleType = (String) moduleValuesMap.get("MODULE_TYPE");
		return Utility.ckNull(moduleType);
	}
	private Map getModuleValuesMap(String labelCode) {
		String moduleAccess = "";
		String moduleType = "";
		String module = "";

		labelCode = labelCode.toUpperCase();
		if (labelCode.startsWith("ALERT")) {
			moduleAccess = "ALERTS";
			module = "";
			moduleType = "";
		} else if (labelCode.startsWith("APP")) {
			moduleAccess = "APPROVALS";
			module = "";
			moduleType = "";
		} else if (labelCode.startsWith("ASSET")) {
			moduleAccess = "ASSETS";
			module = "";
			moduleType = "";
		} else if (labelCode.startsWith("BUDGET")) {
			moduleAccess = "ACCOUNT BUDGET";
			module = "";
			moduleType = "";
		} else if (labelCode.startsWith("CALLFORWARD")) {
			moduleAccess = "APPROVALS";
			module = "";
			moduleType = "";
		} else if (labelCode.startsWith("DOC")) {
			moduleAccess = "DOCUMENTS";
			module = "";
			moduleType = "";
		} else if (labelCode.startsWith("FORM")) {
			moduleAccess = "FORMS";
			module = "";
			moduleType = "";
		} else if (labelCode.startsWith("INVOICE")) {
			moduleAccess = "INVOICE";
			module = "";
			moduleType = "";
		} else if (labelCode.startsWith("INV")) {
			moduleAccess = "STANDARD INVENTORY";
			module = "";
			moduleType = "";
		} else if (labelCode.startsWith("PDF")) {
			moduleAccess = "DOCUMENTS";
			module = "";
			moduleType = "";
		} else if (labelCode.startsWith("ROUTING")) {
			moduleAccess = "APPROVALS";
			module = "routing";
			moduleType = "";
		} else if (labelCode.startsWith("PO")) {
			moduleAccess = "PURCHASE ORDERS";
			module = "po";
			moduleType = "";
		} else if (labelCode.startsWith("REC")) {
			moduleAccess = "RECEIVING";
			module = "rec";
			moduleType = "";
		} else if (labelCode.startsWith("REQ")) {
			moduleAccess = "REQUISITIONS";
			module = "req";
			moduleType = "";
		} else if (labelCode.startsWith("RFQ")) {
			moduleAccess = "REQUEST FOR QUOTES";
			module = "rfq";
			moduleType = "";
		} else if (labelCode.startsWith("RQ")) {
			moduleAccess = "REQUEST FOR QUOTES";
			module = "rfq";
			moduleType = "";
		} else if (labelCode.startsWith("SALE")) {
			moduleAccess = "SALES";
			module = "sales";
			moduleType = "";
		} else if (labelCode.startsWith("SOLICITATION")) {
			moduleAccess = "REQUEST FOR QUOTES";
			module = "rfq";
			moduleType = "";
		} else if (labelCode.startsWith("SUP")) {
			moduleAccess = "SUPPLIER";
			module = "sup";
			moduleType = "";
		} else if (labelCode.startsWith("VAL")) {
			moduleAccess = "VALIDATIONS";
			module = "";
			moduleType = "";
		} else if ((labelCode.indexOf("DISC") >= 0 || labelCode.indexOf("AMT") >= 0 || labelCode.indexOf("AMOUNT") >= 0 || labelCode.indexOf("CHARGES") >= 0 || labelCode.indexOf("TAX") >= 0) &&
					(labelCode.indexOf("DEPT") < 0 && labelCode.indexOf("WARRANT") > 0 && labelCode.indexOf("ALLOC") < 0 && labelCode.indexOf("DAYS") < 0)) {
			moduleAccess = "TOTALS";
			module = "";
			moduleType = "";
		} else if (labelCode.startsWith("VCH")) {
			moduleAccess = "VOUCHERS";
			module = "ivc";
			moduleType = "";
		} else if (labelCode.startsWith("VN")) {
			moduleAccess = "SUPPLIER";
			module = "sup";
			moduleType = "";
		} else if (labelCode.startsWith("NAMEUDF")) {
			moduleAccess = "USER";
			module = "user";
			moduleType = "";
		} else if (labelCode.length() == 4 || labelCode.startsWith("DEPT-") || labelCode.startsWith("DEPTBUYER-") || labelCode.startsWith("TIMEZONE-")) {
			moduleAccess = "ADMIN";
		} else if (labelCode.indexOf("-") > 0 && !labelCode.endsWith("-instr") && !labelCode.startsWith("HIGHLIGHT") && !labelCode.startsWith("CLICK")) {
			moduleAccess = (labelCode.substring(0, labelCode.indexOf("-"))).toUpperCase();
			labelCode = labelCode.substring(labelCode.indexOf("-"));
			if (labelCode.indexOf("-") > 0 && !labelCode.endsWith("-instr") && !labelCode.startsWith("HIGHLIGHT") && !labelCode.startsWith("CLICK")) {
				moduleAccess = (labelCode.substring(0, labelCode.indexOf("-"))).toUpperCase();
				labelCode = labelCode.substring(labelCode.indexOf("-"));
			}
		} else if (labelCode.indexOf("SCHEDULE") > 0) {
			moduleAccess = "SCHEDULES";
			module = "";
			moduleType = "";
		} else {
			moduleAccess = "GENERAL";
			module = "";
			moduleType = "";
		}

		HashMap moduleValuesMap = new HashMap();
		moduleValuesMap.put("MODULE_ACCESS", moduleAccess);
		moduleValuesMap.put("MODULE", module);
		moduleValuesMap.put("MODULE_TYPE", moduleType);
		return moduleValuesMap;
	}
}
