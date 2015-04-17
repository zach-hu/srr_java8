package com.tsa.puridiom.userprofile.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class UserProfileRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from UserProfile as userprofile where 1=1 ");
		if(incomingRequest.containsKey("UserProfile_mailId"))
		{
			String mailId = (String) incomingRequest.get("UserProfile_mailId");
			queryString.append(" AND userprofile.mailId = '" + mailId + "'");
		}
		if(incomingRequest.containsKey("UserProfile_organizationId"))
		{
			String organizationId = (String) incomingRequest.get("UserProfile_organizationId");
			queryString.append(" AND userprofile.organizationId = '" + organizationId + "'");
		}
		if(incomingRequest.containsKey("UserProfile_userId"))
		{
			String userId = (String) incomingRequest.get("UserProfile_userId");
			queryString.append(" AND userprofile.id.userId = '" + userId + "'");
		}
		if(incomingRequest.containsKey("UserProfile_firstName"))
		{
			String firstName = (String) incomingRequest.get("UserProfile_firstName");
			queryString.append(" AND userprofile.firstName = '" + firstName + "'");
		}
		if(incomingRequest.containsKey("UserProfile_middleInit"))
		{
			String middleInit = (String) incomingRequest.get("UserProfile_middleInit");
			queryString.append(" AND userprofile.middleInit = '" + middleInit + "'");
		}
		if(incomingRequest.containsKey("UserProfile_lastName"))
		{
			String lastName = (String) incomingRequest.get("UserProfile_lastName");
			queryString.append(" AND userprofile.lastName = '" + lastName + "'");
		}
		if(incomingRequest.containsKey("UserProfile_title"))
		{
			String title = (String) incomingRequest.get("UserProfile_title");
			queryString.append(" AND userprofile.title = '" + title + "'");
		}
		if(incomingRequest.containsKey("UserProfile_phoneNumber"))
		{
			String phoneNumber = (String) incomingRequest.get("UserProfile_phoneNumber");
			queryString.append(" AND userprofile.phoneNumber = '" + phoneNumber + "'");
		}
		if(incomingRequest.containsKey("UserProfile_faxNumber"))
		{
			String faxNumber = (String) incomingRequest.get("UserProfile_faxNumber");
			queryString.append(" AND userprofile.faxNumber = '" + faxNumber + "'");
		}
		if(incomingRequest.containsKey("UserProfile_phoneFormat"))
		{
			String phoneFormat = (String) incomingRequest.get("UserProfile_phoneFormat");
			queryString.append(" AND userprofile.phoneFormat = '" + phoneFormat + "'");
		}
		if(incomingRequest.containsKey("UserProfile_faxFormat"))
		{
			String faxFormat = (String) incomingRequest.get("UserProfile_faxFormat");
			queryString.append(" AND userprofile.faxFormat = '" + faxFormat + "'");
		}
		if(incomingRequest.containsKey("UserProfile_status"))
		{
			String status = (String) incomingRequest.get("UserProfile_status");
			queryString.append(" AND userprofile.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("UserProfile_owner"))
		{
			String owner = (String) incomingRequest.get("UserProfile_owner");
			queryString.append(" AND userprofile.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("UserProfile_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("UserProfile_dateEntered");
			queryString.append(" AND userprofile.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("UserProfile_dateExpires"))
		{
			String dateExpires = (String) incomingRequest.get("UserProfile_dateExpires");
			queryString.append(" AND userprofile.dateExpires = '" + dateExpires + "'");
		}
		if(incomingRequest.containsKey("UserProfile_signature"))
		{
			String signature = (String) incomingRequest.get("UserProfile_signature");
			queryString.append(" AND userprofile.signature = '" + signature + "'");
		}
		if(incomingRequest.containsKey("UserProfile_signaturePassword"))
		{
			String signaturePassword = (String) incomingRequest.get("UserProfile_signaturePassword");
			queryString.append(" AND userprofile.signaturePassword = '" + signaturePassword + "'");
		}
		if(incomingRequest.containsKey("UserProfile_functionFlags"))
		{
			String functionFlags = (String) incomingRequest.get("UserProfile_functionFlags");
			queryString.append(" AND userprofile.functionFlags = '" + functionFlags + "'");
		}
		if(incomingRequest.containsKey("UserProfile_department"))
		{
			String department = (String) incomingRequest.get("UserProfile_department");
			queryString.append(" AND userprofile.department = '" + department + "'");
		}
		if(incomingRequest.containsKey("UserProfile_userPassword"))
		{
			String userPassword = (String) incomingRequest.get("UserProfile_userPassword");
			queryString.append(" AND userprofile.userPassword = '" + userPassword + "'");
		}
		if(incomingRequest.containsKey("UserProfile_buyer"))
		{
			String buyer = (String) incomingRequest.get("UserProfile_buyer");
			queryString.append(" AND userprofile.buyer = '" + buyer + "'");
		}
		if(incomingRequest.containsKey("UserProfile_requisitioner"))
		{
			String requisitioner = (String) incomingRequest.get("UserProfile_requisitioner");
			queryString.append(" AND userprofile.requisitioner = '" + requisitioner + "'");
		}
		if(incomingRequest.containsKey("UserProfile_authorizedBy"))
		{
			String authorizedBy = (String) incomingRequest.get("UserProfile_authorizedBy");
			queryString.append(" AND userprofile.authorizedBy = '" + authorizedBy + "'");
		}
		if(incomingRequest.containsKey("UserProfile_receiver"))
		{
			String receiver = (String) incomingRequest.get("UserProfile_receiver");
			queryString.append(" AND userprofile.receiver = '" + receiver + "'");
		}
		if(incomingRequest.containsKey("UserProfile_administeredBy"))
		{
			String administeredBy = (String) incomingRequest.get("UserProfile_administeredBy");
			queryString.append(" AND userprofile.administeredBy = '" + administeredBy + "'");
		}
		if(incomingRequest.containsKey("UserProfile_approver"))
		{
			String approver = (String) incomingRequest.get("UserProfile_approver");
			queryString.append(" AND userprofile.approver = '" + approver + "'");
		}
		if(incomingRequest.containsKey("UserProfile_nameUdf1"))
		{
			String nameUdf1 = (String) incomingRequest.get("UserProfile_nameUdf1");
			queryString.append(" AND userprofile.nameUdf1 = '" + nameUdf1 + "'");
		}
		if(incomingRequest.containsKey("UserProfile_nameUdf2"))
		{
			String nameUdf2 = (String) incomingRequest.get("UserProfile_nameUdf2");
			queryString.append(" AND userprofile.nameUdf2 = '" + nameUdf2 + "'");
		}
		if(incomingRequest.containsKey("UserProfile_nameUdf3"))
		{
			String nameUdf3 = (String) incomingRequest.get("UserProfile_nameUdf3");
			queryString.append(" AND userprofile.nameUdf3 = '" + nameUdf3 + "'");
		}
		if(incomingRequest.containsKey("UserProfile_nameUdf4"))
		{
			String nameUdf4 = (String) incomingRequest.get("UserProfile_nameUdf4");
			queryString.append(" AND userprofile.nameUdf4 = '" + nameUdf4 + "'");
		}
		if(incomingRequest.containsKey("UserProfile_nameUdf5"))
		{
			String nameUdf5 = (String) incomingRequest.get("UserProfile_nameUdf5");
			queryString.append(" AND userprofile.nameUdf5 = '" + nameUdf5 + "'");
		}
		if(incomingRequest.containsKey("UserProfile_approvalAmount"))
		{
			String approvalAmount = (String) incomingRequest.get("UserProfile_approvalAmount");
			queryString.append(" AND userprofile.approvalAmount = '" + approvalAmount + "'");
		}
		if(incomingRequest.containsKey("UserProfile_approveByLine"))
		{
			String approveByLine = (String) incomingRequest.get("UserProfile_approveByLine");
			queryString.append(" AND userprofile.approveByLine = '" + approveByLine + "'");
		}
		if(incomingRequest.containsKey("UserProfile_mailPassword"))
		{
			String mailPassword = (String) incomingRequest.get("UserProfile_mailPassword");
			queryString.append(" AND userprofile.mailPassword = '" + mailPassword + "'");
		}
		if(incomingRequest.containsKey("UserProfile_callForward"))
		{
			String callForward = (String) incomingRequest.get("UserProfile_callForward");
			queryString.append(" AND userprofile.callForward = '" + callForward + "'");
		}
		if(incomingRequest.containsKey("UserProfile_excludeLess"))
		{
			String excludeLess = (String) incomingRequest.get("UserProfile_excludeLess");
			queryString.append(" AND userprofile.excludeLess = '" + excludeLess + "'");
		}
		if(incomingRequest.containsKey("UserProfile_warrantAmount"))
		{
			String warrantAmount = (String) incomingRequest.get("UserProfile_warrantAmount");
			queryString.append(" AND userprofile.warrantAmount = '" + warrantAmount + "'");
		}
		if(incomingRequest.containsKey("UserProfile_mailStop"))
		{
			String mailStop = (String) incomingRequest.get("UserProfile_mailStop");
			queryString.append(" AND userprofile.mailStop = '" + mailStop + "'");
		}
		if(incomingRequest.containsKey("UserProfile_remoteUser"))
		{
			String remoteUser = (String) incomingRequest.get("UserProfile_remoteUser");
			queryString.append(" AND userprofile.remoteUser = '" + remoteUser + "'");
		}
		if(incomingRequest.containsKey("UserProfile_passChanged"))
		{
			String passChanged = (String) incomingRequest.get("UserProfile_passChanged");
			queryString.append(" AND userprofile.passChanged = '" + passChanged + "'");
		}
		if(incomingRequest.containsKey("UserProfile_lockLogin"))
		{
			String lockLogin = (String) incomingRequest.get("UserProfile_lockLogin");
			queryString.append(" AND userprofile.lockLogin = '" + lockLogin + "'");
		}
		if(incomingRequest.containsKey("UserProfile_empid"))
		{
			String empid = (String) incomingRequest.get("UserProfile_empid");
			queryString.append(" AND userprofile.empid = '" + empid + "'");
		}
		if(incomingRequest.containsKey("UserProfile_viewReqs"))
		{
			String viewReqs = (String) incomingRequest.get("UserProfile_viewReqs");
			queryString.append(" AND userprofile.viewReqs = '" + viewReqs + "'");
		}
		if(incomingRequest.containsKey("UserProfile_vchApp"))
		{
			String vchApp = (String) incomingRequest.get("UserProfile_vchApp");
			queryString.append(" AND userprofile.vchApp = '" + vchApp + "'");
		}
		if(incomingRequest.containsKey("UserProfile_reqClass"))
		{
			String reqClass = (String) incomingRequest.get("UserProfile_reqClass");
			queryString.append(" AND userprofile.reqClass = '" + reqClass + "'");
		}
		if(incomingRequest.containsKey("UserProfile_userType"))
		{
			String userType = (String) incomingRequest.get("UserProfile_userType");
			queryString.append(" AND userprofile.userType = '" + userType + "'");
		}
		if(incomingRequest.containsKey("UserProfile_locale"))
		{
			String locale = (String) incomingRequest.get("UserProfile_locale");
			queryString.append(" AND userprofile.locale = '" + locale + "'");
		}
		if(incomingRequest.containsKey("UserProfile_apBatchid"))
		{
			String apBatchid = (String) incomingRequest.get("UserProfile_apBatchid");
			queryString.append(" AND userprofile.apBatchid = '" + apBatchid + "'");
		}
		if(incomingRequest.containsKey("UserProfile_appointedFlag"))
		{
			String appointedFlag = (String) incomingRequest.get("UserProfile_appointedFlag");
			queryString.append(" AND userprofile.appointedFlag = '" + appointedFlag + "'");
		}
		if(incomingRequest.containsKey("UserProfile_formValidate"))
		{
			String formValidate = (String) incomingRequest.get("UserProfile_formValidate");
			queryString.append(" AND userprofile.formValidate = '" + formValidate + "'");
		}
		if(incomingRequest.containsKey("UserProfile_shipToCode"))
		{
			String shipToCode = (String) incomingRequest.get("UserProfile_shipToCode");
			queryString.append(" AND userprofile.shipToCode = '" + shipToCode + "'");
		}
		if(incomingRequest.containsKey("UserProfile_routing"))
		{
			String routing = (String) incomingRequest.get("UserProfile_routing");
			queryString.append(" AND userprofile.routing = '" + routing + "'");
		}
		if(incomingRequest.containsKey("UserProfile_overrider"))
		{
			String overrider = (String) incomingRequest.get("UserProfile_overrider");
			queryString.append(" AND userprofile.overrider = '" + overrider + "'");
		}
		if(incomingRequest.containsKey("UserProfile_securityQuestion"))
		{
			String securityQuestion = (String) incomingRequest.get("UserProfile_securityQuestion");
			queryString.append(" AND userprofile.securityQuestion = '" + securityQuestion + "'");
		}
		if(incomingRequest.containsKey("UserProfile_securityAnswer"))
		{
			String securityAnswer = (String) incomingRequest.get("UserProfile_securityAnswer");
			queryString.append(" AND userprofile.securityAnswer = '" + securityAnswer + "'");
		}
		if(incomingRequest.containsKey("UserProfile_costCenter"))
		{
			String costCenter = (String) incomingRequest.get("UserProfile_costCenter");
			queryString.append(" AND userprofile.costCenter = '" + costCenter + "'");
		}
		if(incomingRequest.containsKey("UserProfile_reviewProfile"))
		{
			String reviewProfile = (String) incomingRequest.get("UserProfile_reviewProfile");
			queryString.append(" AND userprofile.reviewProfile = '" + reviewProfile + "'");
		}
		if(incomingRequest.containsKey("UserProfile_forwardOffDate"))
		{
			String forwardOffDate = (String) incomingRequest.get("UserProfile_forwardOffDate");
			queryString.append(" AND userprofile.forwardOffDate = '" + forwardOffDate + "'");
		}
		if(incomingRequest.containsKey("UserProfile_adminBuyer"))
		{
			String adminBuyer = (String) incomingRequest.get("UserProfile_adminBuyer");
			queryString.append(" AND userprofile.adminBuyer = '" + adminBuyer + "'");
		}
		if(incomingRequest.containsKey("UserProfile_contractAmount"))
		{
			String contractAmount = (String) incomingRequest.get("UserProfile_contractAmount");
			queryString.append(" AND userprofile.contractAmount = '" + contractAmount + "'");
		}
		if(incomingRequest.containsKey("UserProfile_priorPassword"))
		{
			String priorPassword = (String) incomingRequest.get("UserProfile_priorPassword");
			queryString.append(" AND userprofile.priorPassword = '" + priorPassword + "'");
		}
		if(incomingRequest.containsKey("UserProfile_backupApprover"))
		{
			String backupApprover = (String) incomingRequest.get("UserProfile_backupApprover");
			queryString.append(" AND userprofile.backupApprover = '" + backupApprover + "'");
		}
		if(incomingRequest.containsKey("UserProfile_emailVersion"))
		{
			String emailVersion = (String) incomingRequest.get("UserProfile_emailVersion");
			queryString.append(" AND userprofile.emailVersion = '" + emailVersion + "'");
		}
		if(incomingRequest.containsKey("UserProfile_externalId"))
		{
			String externalId = (String) incomingRequest.get("UserProfile_externalId");
			queryString.append(" AND userprofile.externalId = '" + externalId + "'");
		}
		if(incomingRequest.containsKey("UserProfile_expandBrowse"))
		{
			String expandBrowse = (String) incomingRequest.get("UserProfile_expandBrowse");
			queryString.append(" AND userprofile.expandBrowse = '" + expandBrowse + "'");
		}
		if(incomingRequest.containsKey("UserProfile_language"))
		{
			String language = (String) incomingRequest.get("UserProfile_language");
			queryString.append(" AND userprofile.language = '" + language + "'");
		}
		if(incomingRequest.containsKey("UserProfile_currencyCode"))
		{
			String currencyCode = (String) incomingRequest.get("UserProfile_currencyCode");
			queryString.append(" AND userprofile.currencyCode = '" + currencyCode + "'");
		}
		if(incomingRequest.containsKey("UserProfile_timeZone"))
		{
			String timeZone = (String) incomingRequest.get("UserProfile_timeZone");
			queryString.append(" AND userprofile.timeZone = '" + timeZone + "'");
		}
		if(incomingRequest.containsKey("UserProfile_dateFormat"))
		{
			String dateFormat = (String) incomingRequest.get("UserProfile_dateFormat");
			queryString.append(" AND userprofile.dateFormat = '" + dateFormat + "'");
		}
		if(incomingRequest.containsKey("UserProfile_inspector"))
		{
			String inspector = (String) incomingRequest.get("UserProfile_inspector");
			queryString.append(" AND userprofile.inspector = '" + inspector + "'");
		}
		if(incomingRequest.containsKey("UserProfile_engineer"))
		{
			String engineer = (String) incomingRequest.get("UserProfile_engineer");
			queryString.append(" AND userprofile.engineer = '" + engineer + "'");
		}
		if(incomingRequest.containsKey("UserProfile_fpe"))
		{
			String fpe = (String) incomingRequest.get("UserProfile_fpe");
			queryString.append(" AND userprofile.fpe = '" + fpe + "'");
		}

		queryString.append(" ORDER BY userprofile.firstName, userprofile.lastName");
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
