package com.tsa.puridiom.userprofile.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.StdTable;
import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class UserProfileSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UserProfile userProfile = (UserProfile) incomingRequest.get("userProfile");
			if (userProfile == null)
			{
				userProfile = new UserProfile();
			}

			if (incomingRequest.containsKey("UserProfile_userId"))
			{
				String uid = (String ) incomingRequest.get("UserProfile_userId");
				userProfile.setUserId(uid);
			}
			if (incomingRequest.containsKey("UserProfile_mailId"))
			{
				String mailId = (String) incomingRequest.get("UserProfile_mailId");
				userProfile.setMailId(Utility.ckNull(mailId).toLowerCase());
			}
			if (incomingRequest.containsKey("UserProfile_emailVersion"))
			{
				String emailVersion = (String) incomingRequest.get("UserProfile_emailVersion");
				userProfile.setEmailVersion(Utility.ckNull(emailVersion).toUpperCase());
			}
			if (incomingRequest.containsKey("UserProfile_organizationId"))
			{
				String organizationId = (String ) incomingRequest.get("UserProfile_organizationId");
				if (organizationId != null) {
					organizationId = organizationId.trim().toUpperCase();
				}
				userProfile.setOrganizationId(organizationId);
			}
			if (incomingRequest.containsKey("UserProfile_firstName"))
			{
				String firstName = (String) incomingRequest.get("UserProfile_firstName");
				userProfile.setFirstName(firstName);
			}
			if (incomingRequest.containsKey("UserProfile_middleInit"))
			{
				String middleInit = (String) incomingRequest.get("UserProfile_middleInit");
				userProfile.setMiddleInit(middleInit);
			}
			if (incomingRequest.containsKey("UserProfile_lastName"))
			{
				String lastName = (String) incomingRequest.get("UserProfile_lastName");
				userProfile.setLastName(lastName);
			}
			if (incomingRequest.containsKey("UserProfile_title"))
			{
				String title = (String) incomingRequest.get("UserProfile_title");
				userProfile.setTitle(title);
			}
			if (incomingRequest.containsKey("UserProfile_phoneNumber"))
			{
				String phoneNumber = (String) incomingRequest.get("UserProfile_phoneNumber");
				userProfile.setPhoneNumber(phoneNumber);
			}
			if (incomingRequest.containsKey("UserProfile_faxNumber"))
			{
				String faxNumber = (String) incomingRequest.get("UserProfile_faxNumber");
				userProfile.setFaxNumber(faxNumber);
			}
			if (incomingRequest.containsKey("UserProfile_phoneFormat"))
			{
				String phoneFormat = (String) incomingRequest.get("UserProfile_phoneFormat");
				userProfile.setPhoneFormat(phoneFormat);
			}
			if (incomingRequest.containsKey("UserProfile_faxFormat"))
			{
				String faxFormat = (String) incomingRequest.get("UserProfile_faxFormat");
				userProfile.setFaxFormat(faxFormat);
			}
			if (incomingRequest.containsKey("UserProfile_status"))
			{
				String status = (String) incomingRequest.get("UserProfile_status");
				userProfile.setStatus(status);
			}
			if (incomingRequest.containsKey("UserProfile_owner"))
			{
				String owner = (String) incomingRequest.get("UserProfile_owner");
				userProfile.setOwner(owner);
			}
			if (incomingRequest.containsKey("UserProfile_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("UserProfile_dateEntered");
				Date dateEntered = HiltonUtility.isEmpty(dateEnteredString) ? null : Dates.getDate(dateEnteredString);
				userProfile.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("UserProfile_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("UserProfile_dateExpires");
				Date dateExpires = HiltonUtility.isEmpty(dateExpiresString) ? null : Dates.getDate(dateExpiresString);
				userProfile.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("UserProfile_signature"))
			{
				String signature = (String) incomingRequest.get("UserProfile_signature");
				userProfile.setSignature(signature);
			}
			if (incomingRequest.containsKey("UserProfile_signaturePassword"))
			{
				String signaturePassword = (String) incomingRequest.get("UserProfile_signaturePassword");
				userProfile.setSignaturePassword(signaturePassword);
			}
			if (incomingRequest.containsKey("UserProfile_functionFlags"))
			{
				String functionFlags = (String) incomingRequest.get("UserProfile_functionFlags");
				userProfile.setFunctionFlags(functionFlags);
			}
			if (incomingRequest.containsKey("UserProfile_department"))
			{
				String department = (String) incomingRequest.get("UserProfile_department");
				userProfile.setDepartment(department);
			}
			if (incomingRequest.containsKey("UserProfile_userPassword"))
			{
				String userPassword = (String) incomingRequest.get("UserProfile_userPassword");
				userProfile.setUserPassword(userPassword);
			}
			if (incomingRequest.containsKey("UserProfile_adminBuyer"))
			{
				String adminBuyer = (String ) incomingRequest.get("UserProfile_adminBuyer");
				userProfile.setAdminBuyer(adminBuyer);
			}
			if (incomingRequest.containsKey("UserProfile_buyer"))
			{
				String buyer = (String) incomingRequest.get("UserProfile_buyer");
				userProfile.setBuyer(buyer);
			}
			if (incomingRequest.containsKey("UserProfile_requisitioner"))
			{
				String requisitioner = (String) incomingRequest.get("UserProfile_requisitioner");
				userProfile.setRequisitioner(requisitioner);
			}
			if (incomingRequest.containsKey("UserProfile_authorizedBy"))
			{
				String authorizedBy = (String) incomingRequest.get("UserProfile_authorizedBy");
				userProfile.setAuthorizedBy(authorizedBy);
			}
			if (incomingRequest.containsKey("UserProfile_receiver"))
			{
				String receiver = (String) incomingRequest.get("UserProfile_receiver");
				userProfile.setReceiver(receiver);
			}
			if (incomingRequest.containsKey("UserProfile_administeredBy"))
			{
				String administeredBy = (String) incomingRequest.get("UserProfile_administeredBy");
				userProfile.setAdministeredBy(administeredBy);
			}
			if (incomingRequest.containsKey("UserProfile_approver"))
			{
				String approver = (String) incomingRequest.get("UserProfile_approver");
				userProfile.setApprover(approver);
			}
			if (incomingRequest.containsKey("UserProfile_nameUdf1"))
			{
				String nameUdf1 = (String) incomingRequest.get("UserProfile_nameUdf1");
				userProfile.setNameUdf1(nameUdf1);
			}
			if (incomingRequest.containsKey("UserProfile_nameUdf2"))
			{
				String nameUdf2 = (String) incomingRequest.get("UserProfile_nameUdf2");
				userProfile.setNameUdf2(nameUdf2);
			}
			if (incomingRequest.containsKey("UserProfile_nameUdf3"))
			{
				String nameUdf3 = (String) incomingRequest.get("UserProfile_nameUdf3");
				userProfile.setNameUdf3(nameUdf3);
			}
			if (incomingRequest.containsKey("UserProfile_nameUdf4"))
			{
				String nameUdf4 = (String) incomingRequest.get("UserProfile_nameUdf4");
				userProfile.setNameUdf4(nameUdf4);
			}
			if (incomingRequest.containsKey("UserProfile_nameUdf5"))
			{
				String nameUdf5 = (String) incomingRequest.get("UserProfile_nameUdf5");
				userProfile.setNameUdf5(nameUdf5);
			}
			if (incomingRequest.containsKey("UserProfile_approvalAmount"))
			{
				String approvalAmountString = (String) incomingRequest.get("UserProfile_approvalAmount");
				if (Utility.isEmpty(approvalAmountString))
				{
					approvalAmountString = "0";
				}
				BigDecimal approvalAmount = new BigDecimal ( approvalAmountString );
				userProfile.setApprovalAmount(approvalAmount);
			}
			if (incomingRequest.containsKey("UserProfile_approveByLine"))
			{
				String approveByLine = (String) incomingRequest.get("UserProfile_approveByLine");
				userProfile.setApproveByLine(approveByLine);
			}
			if (incomingRequest.containsKey("UserProfile_mailPassword"))
			{
				String mailPassword = (String) incomingRequest.get("UserProfile_mailPassword");
				userProfile.setMailPassword(mailPassword);
			}
			if (incomingRequest.containsKey("UserProfile_callForward"))
			{
				String callForward = (String) incomingRequest.get("UserProfile_callForward");
				userProfile.setCallForward(callForward);
			}
			if (incomingRequest.containsKey("UserProfile_excludeLess"))
			{
				String excludeLessString = (String) incomingRequest.get("UserProfile_excludeLess");
				if (Utility.isEmpty(excludeLessString))
				{
					excludeLessString = "0";
				}
				BigDecimal excludeLess = new BigDecimal ( excludeLessString );
				userProfile.setExcludeLess(excludeLess);
			}
			if (incomingRequest.containsKey("UserProfile_warrantAmount"))
			{
				String warrantAmountString = (String) incomingRequest.get("UserProfile_warrantAmount");
				if (Utility.isEmpty(warrantAmountString))
				{
					warrantAmountString = "0";
				}
				BigDecimal warrantAmount = new BigDecimal ( warrantAmountString );
				userProfile.setWarrantAmount(warrantAmount);
			}
			if (incomingRequest.containsKey("UserProfile_mailStop"))
			{
				String mailStop = (String) incomingRequest.get("UserProfile_mailStop");
				userProfile.setMailStop(mailStop);
			}
			if (incomingRequest.containsKey("UserProfile_remoteUser"))
			{
				String remoteUser = (String) incomingRequest.get("UserProfile_remoteUser");
				userProfile.setRemoteUser(remoteUser);
			}
			if (incomingRequest.containsKey("UserProfile_passChanged"))
			{
				String passChanged = (String) incomingRequest.get("UserProfile_passChanged");
				userProfile.setPassChanged(passChanged);
			}
			if (incomingRequest.containsKey("UserProfile_lockLogin"))
			{
				String lockLogin = (String) incomingRequest.get("UserProfile_lockLogin");
				userProfile.setLockLogin(lockLogin);
			}
			if (incomingRequest.containsKey("UserProfile_empid"))
			{
				String empid = (String) incomingRequest.get("UserProfile_empid");
				userProfile.setEmpid(empid);
			}
			if (incomingRequest.containsKey("UserProfile_viewReqs"))
			{
				String viewReqs = (String) incomingRequest.get("UserProfile_viewReqs");
				userProfile.setViewReqs(viewReqs);
			}
			if (incomingRequest.containsKey("UserProfile_vchApp"))
			{
				String vchApp = (String) incomingRequest.get("UserProfile_vchApp");
				userProfile.setVchApp(vchApp);
			}
			if (incomingRequest.containsKey("UserProfile_reqClass"))
			{
				String reqClass = (String) incomingRequest.get("UserProfile_reqClass");
				userProfile.setReqClass(reqClass);
			}
			if (incomingRequest.containsKey("UserProfile_userType"))
			{
				String userType = (String) incomingRequest.get("UserProfile_userType");
				userProfile.setUserType(userType);
			}
			if (incomingRequest.containsKey("UserProfile_locale"))
			{
				String locale = (String) incomingRequest.get("UserProfile_locale");
				userProfile.setLocale(locale);
			}
			if (incomingRequest.containsKey("UserProfile_apBatchid"))
			{
				String apBatchid = (String) incomingRequest.get("UserProfile_apBatchid");
				userProfile.setApBatchid(apBatchid);
			}
			if (incomingRequest.containsKey("UserProfile_appointedFlag"))
			{
				String appointedFlag = (String) incomingRequest.get("UserProfile_appointedFlag");
				userProfile.setAppointedFlag(appointedFlag);
			}
			if (incomingRequest.containsKey("UserProfile_formValidate"))
			{
				String formValidate = (String) incomingRequest.get("UserProfile_formValidate");
				userProfile.setFormValidate(formValidate);
			}
			if (incomingRequest.containsKey("UserProfile_shipToCode"))
			{
				String shipToCode = (String) incomingRequest.get("UserProfile_shipToCode");
				userProfile.setShipToCode(shipToCode);
			}
			if (incomingRequest.containsKey("UserProfile_routing"))
			{
				String routing = (String) incomingRequest.get("UserProfile_routing");
				userProfile.setRouting(routing);
			}
			if (incomingRequest.containsKey("UserProfile_overrider"))
			{
				String overrider = (String) incomingRequest.get("UserProfile_overrider");
				userProfile.setOverrider(overrider);
			}
			if (incomingRequest.containsKey("UserProfile_securityQuestion"))
			{
				String securityQuestion = (String) incomingRequest.get("UserProfile_securityQuestion");
				userProfile.setSecurityQuestion(securityQuestion);
			}
			if (incomingRequest.containsKey("UserProfile_securityAnswer"))
			{
				String securityAnswer = (String) incomingRequest.get("UserProfile_securityAnswer");
				userProfile.setSecurityAnswer(securityAnswer);
			}
			if (incomingRequest.containsKey("UserProfile_costCenter"))
			{
				String costCenter = (String) incomingRequest.get("UserProfile_costCenter");
				userProfile.setCostCenter(costCenter);
			}
			if (incomingRequest.containsKey("UserProfile_reviewProfile"))
			{
				String reviewProfile = (String) incomingRequest.get("UserProfile_reviewProfile");
				userProfile.setReviewProfile(reviewProfile);
			}
			if (incomingRequest.containsKey("UserProfile_forwardOffDate"))
			{
				String forwardOffDateString = (String) incomingRequest.get("UserProfile_forwardOffDate");
				Date forwardOffDate = HiltonUtility.isEmpty(forwardOffDateString) ? null : Dates.getDate(forwardOffDateString);
				userProfile.setForwardOffDate(forwardOffDate);
			}
			if (incomingRequest.containsKey("UserProfile_adminBuyer"))
			{
				String adminBuyer = (String) incomingRequest.get("UserProfile_adminBuyer");
				userProfile.setAdminBuyer(adminBuyer);
			}
			if (incomingRequest.containsKey("UserProfile_contractAmount"))
			{
				String contractAmountString = (String) incomingRequest.get("UserProfile_contractAmount");
				if (Utility.isEmpty(contractAmountString))
				{
					contractAmountString = "0";
				}
				BigDecimal contractAmount = new BigDecimal ( contractAmountString );
				userProfile.setContractAmount(contractAmount);
			}
			if (incomingRequest.containsKey("UserProfile_priorPassword"))
			{
				String priorPassword = (String) incomingRequest.get("UserProfile_priorPassword");
				userProfile.setPriorPassword(priorPassword);
			}
			if (incomingRequest.containsKey("UserProfile_backupApprover"))
			{
				String backupApprover = (String) incomingRequest.get("UserProfile_backupApprover");
				if (backupApprover != null) {
					if (backupApprover.length() > 15) {
						backupApprover = backupApprover.substring(0,15) ;
					}
				}
				userProfile.setBackupApprover(backupApprover);
			}

			if (incomingRequest.containsKey("UserProfile_externalId"))
			{
				String externalId = (String) incomingRequest.get("UserProfile_externalId");
				userProfile.setExternalId(externalId);
			}

			if (incomingRequest.containsKey("userRoleList"))
			{
				List userRoleList = (List) incomingRequest.get("userRoleList");
				userProfile.setUserRoles(userRoleList);
			}
			result = userProfile;

			if (incomingRequest.containsKey("UserProfile_expandBrowse"))
			{
				String expandBrowse = (String) incomingRequest.get("UserProfile_expandBrowse");
				userProfile.setExpandBrowse(expandBrowse);
			}
			if (incomingRequest.containsKey("UserProfile_language"))
			{
				String language = (String) incomingRequest.get("UserProfile_language");
				userProfile.setLanguage(language);
			}
			if (incomingRequest.containsKey("UserProfile_currencyCode"))
			{
				String currencyCode = (String) incomingRequest.get("UserProfile_currencyCode");
				userProfile.setCurrencyCode(currencyCode);
			}
			if (incomingRequest.containsKey("UserProfile_timeZone"))
			{
				String timeZone = (String) incomingRequest.get("UserProfile_timeZone");
				userProfile.setTimeZone(timeZone);
			}
			if (incomingRequest.containsKey("UserProfile_dateFormat"))
			{
				String dateFormat = getDescriptionDateFormat(incomingRequest);
				userProfile.setDateFormat(dateFormat);
				String dateFormatKey = (String)incomingRequest.get("UserProfile_dateFormat");
				userProfile.setDateFormatKey(dateFormatKey);
			}
			if (incomingRequest.containsKey("UserProfile_lastChangeBy"))
			{
				String uid = (String ) incomingRequest.get("UserProfile_lastChangeBy");
				userProfile.setLastChangeBy(uid);
			}
			if (incomingRequest.containsKey("UserProfile_lastChangeDate"))
			{
				String changeDateString = (String) incomingRequest.get("UserProfile_lastChangeDate");
				Date changeDate = HiltonUtility.isEmpty(changeDateString) ? null : Dates.getDate(changeDateString);
				userProfile.setLastChangeDate(changeDate);
			}
			if (incomingRequest.containsKey("UserProfile_barChart"))
			{
				String bar = (String ) incomingRequest.get("UserProfile_barChart");
				userProfile.setBarChart(bar);
			}
			if (incomingRequest.containsKey("UserProfile_defaultContact"))
			{
				String defaultContact = (String ) incomingRequest.get("UserProfile_defaultContact");
				userProfile.setNameUdf5(defaultContact);
			}
			if (incomingRequest.containsKey("UserProfile_emailsActive"))
			{
				String emailsActive = (String) incomingRequest.get("UserProfile_emailsActive");
				userProfile.setEmailsActive(emailsActive);
			}
			if (incomingRequest.containsKey("UserProfile_termsAccepted"))
			{
				String termsAccepted = (String) incomingRequest.get("UserProfile_termsAccepted");
				userProfile.setTermsAccepted(termsAccepted);
			}
			if (incomingRequest.containsKey("UserProfile_pCardType")) {
				userProfile.setpCardType((String) incomingRequest.get("UserProfile_pCardType"));
			}
			if (incomingRequest.containsKey("UserProfile_pCardNumber")) {
				userProfile.setpCardNumber((String) incomingRequest.get("UserProfile_pCardNumber"));
			}
			if (incomingRequest.containsKey("UserProfile_pCardHolder")) {
				userProfile.setpCardHolder((String) incomingRequest.get("UserProfile_pCardHolder"));
			}
			if (incomingRequest.containsKey("UserProfile_pCardExpires")) {
				userProfile.setpCardExpires((String) incomingRequest.get("UserProfile_pCardExpires"));
			}
			if (incomingRequest.containsKey("UserProfile_inspector"))
			{
				String inspector = (String ) incomingRequest.get("UserProfile_inspector");
				userProfile.setInspector(inspector);
			}
			if (incomingRequest.containsKey("UserProfile_engineer"))
			{
				String engineer = (String ) incomingRequest.get("UserProfile_engineer");
				userProfile.setEngineer(engineer);
			}
			if (incomingRequest.containsKey("UserProfile_fpe"))
			{
				String fpe = (String ) incomingRequest.get("UserProfile_fpe");
				userProfile.setFpe(fpe);
			}
			if (incomingRequest.containsKey("UserProfile_marker"))
			{
				String marker = (String ) incomingRequest.get("UserProfile_marker");
				userProfile.setMarker(marker);
			}
			if (incomingRequest.containsKey("UserProfile_targetCenter"))
			{
				String targetCenter = (String) incomingRequest.get("UserProfile_targetCenter");
				userProfile.setTargetCenter(targetCenter);
			}
			result = userProfile;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}

	public String getDescriptionDateFormat(Map incomingRequest)throws Exception
	{
		String description = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String dateFormatKey = (String ) incomingRequest.get("UserProfile_dateFormat");
			StdTable stdTable = null;
			String queryString = "from StdTable as StdTable where StdTable.id.tableKey like ? ";
			List resultList = dbs.query(queryString, new Object[] {dateFormatKey, } , new Type[] { Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				stdTable = (StdTable)resultList.get(0);
				description = stdTable.getDescription();
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return description;
	}
}
