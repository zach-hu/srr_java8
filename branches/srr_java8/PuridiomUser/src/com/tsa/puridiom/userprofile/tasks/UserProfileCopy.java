package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class UserProfileCopy extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			UserProfile userProfile = (UserProfile) incomingRequest.get("userProfile");
			UserProfile userProfileCopy = new UserProfile();

			if (userProfile != null) {
				userProfileCopy.setAdminBuyer(userProfile.getAdminBuyer());
				userProfileCopy.setAdministeredBy(userProfile.getAdministeredBy());
				userProfileCopy.setApBatchid(userProfile.getApBatchid());
				userProfileCopy.setAppointedFlag(userProfile.getAppointedFlag());
				userProfileCopy.setApprovalAmount(userProfile.getApprovalAmount());
				userProfileCopy.setApproveByLine(userProfile.getApproveByLine());
				userProfileCopy.setApprover(userProfile.getApprover());
				userProfileCopy.setAuthorizedBy(userProfile.getAuthorizedBy());
				userProfileCopy.setBarChart(userProfile.getBarChart());
				userProfileCopy.setBuyer(userProfile.getBuyer());
				userProfileCopy.setCallForward(userProfile.getCallForward());
				userProfileCopy.setContractAmount(userProfile.getContractAmount());
				userProfileCopy.setCostCenter(userProfile.getCostCenter());
				userProfileCopy.setCurrencyCode(userProfile.getCurrencyCode());
				userProfileCopy.setDateEntered(userProfile.getDateEntered());
				userProfileCopy.setDateExpires(userProfile.getDateExpires());
				userProfileCopy.setDateFormat(userProfile.getDateFormat());
				userProfileCopy.setDepartment(userProfile.getDepartment());
				userProfileCopy.setEmailsActive(userProfile.getEmailsActive());
				userProfileCopy.setEmailVersion(userProfile.getEmailVersion());
				userProfileCopy.setEmpid(userProfile.getEmpid());
				userProfileCopy.setEngineer(userProfile.getEngineer());
				userProfileCopy.setExcludeLess(userProfile.getExcludeLess());
				userProfileCopy.setExternalId(userProfile.getExternalId());
				userProfileCopy.setFaxFormat(userProfile.getFaxFormat());
				userProfileCopy.setFaxNumber(userProfile.getFaxNumber());
				userProfileCopy.setFirstName(userProfile.getFirstName());
				userProfileCopy.setFormValidate(userProfile.getFormValidate());
				userProfileCopy.setFpe(userProfile.getFpe());
				userProfileCopy.setFunctionFlags(userProfile.getFunctionFlags());
				userProfileCopy.setInspector(userProfile.getInspector());
				userProfileCopy.setLanguage(userProfile.getLanguage());
				userProfileCopy.setLastLoggedOn(userProfile.getLastLoggedOn());
				userProfileCopy.setLastName(userProfile.getLastName());
				userProfileCopy.setLocale(userProfile.getLocale());
				userProfileCopy.setLockLogin(userProfile.getLockLogin());
				userProfileCopy.setLoggedOn(userProfile.getLoggedOn());
				userProfileCopy.setLoginAttempts(userProfile.getLoginAttempts());
				userProfileCopy.setPriorPassword(userProfile.getPriorPassword());
				userProfileCopy.setMailId(userProfile.getMailId());
				userProfileCopy.setMailPassword(userProfile.getMailPassword());
				userProfileCopy.setMailStop(userProfile.getMailStop());
				userProfileCopy.setMarker(userProfile.getMarker());
				userProfileCopy.setMiddleInit(userProfile.getMiddleInit());
				userProfileCopy.setNameUdf1(userProfile.getNameUdf1());
				userProfileCopy.setNameUdf2(userProfile.getNameUdf2());
				userProfileCopy.setNameUdf3(userProfile.getNameUdf3());
				userProfileCopy.setNameUdf4(userProfile.getNameUdf4());
				userProfileCopy.setNameUdf5(userProfile.getNameUdf5());
				userProfileCopy.setOrganizationId(userProfile.getOrganizationId());
				userProfileCopy.setOverrider(userProfile.getOverrider());
				userProfileCopy.setOwner(userProfile.getOwner());
				userProfileCopy.setPassChanged(userProfile.getPassChanged());
				userProfileCopy.setPhoneFormat(userProfile.getPhoneFormat());
				userProfileCopy.setPhoneNumber(userProfile.getPhoneNumber());
				userProfileCopy.setReceiver(userProfile.getReceiver());
				userProfileCopy.setRegistered(userProfile.isRegistered());
				userProfileCopy.setRemoteUser(userProfile.getRemoteUser());
				userProfileCopy.setReqClass(userProfile.getReqClass());
				userProfileCopy.setReviewProfile(userProfile.getReviewProfile());
				userProfileCopy.setRequisitioner(userProfile.getRequisitioner());
				userProfileCopy.setRouting(userProfile.getRouting());
				userProfileCopy.setSecurityAnswer(userProfile.getSecurityAnswer());
				userProfileCopy.setSecurityQuestion(userProfile.getSecurityQuestion());
				userProfileCopy.setShipToCode(userProfile.getShipToCode());
				userProfileCopy.setSignature(userProfile.getSignature());
				userProfileCopy.setSignaturePassword(userProfile.getSignaturePassword());
				userProfileCopy.setStatus(userProfile.getStatus());
				userProfileCopy.setTermsAccepted(userProfile.getTermsAccepted());
				userProfileCopy.setTimeZone(userProfile.getTimeZone());
				userProfileCopy.setTitle(userProfile.getTitle());
				userProfileCopy.setUserId(userProfile.getUserId());
				userProfileCopy.setUserPassword(userProfile.getUserPassword());
				userProfileCopy.setUserRoles(userProfile.getUserRoles());
				userProfileCopy.setUserType(userProfile.getUserType());
				userProfileCopy.setVchApp(userProfile.getVchApp());
				userProfileCopy.setViewReqs(userProfile.getViewReqs());
				userProfileCopy.setWarrantAmount(userProfile.getWarrantAmount());
			}


			result = userProfileCopy;
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