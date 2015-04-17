<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.utility.BlackBox" %>
<%
	String	userProfileUserId = (String) request.getAttribute("UserProfile_userId");
	String	userProfileOrganizationId = (String) request.getAttribute("UserProfile_organizationId");
	UserProfile	userProfile = UserManager.getInstance().getUser(userProfileOrganizationId, userProfileUserId);
	String s_dollar_decimals = PropertiesManager.getInstance(oid).getProperty("MISC", "DollarDecimals", "2");
	boolean	createNew = false;
	boolean newUserProfile = false;

	if (userProfile == null) {
		userProfile = new UserProfile();
		userProfile.setOrganizationId(oid);
		userProfile.setOwner(uid);
		userProfile.setDateEntered(d_today);
		newUserProfile = true;
	}
	UserProfile owner = UserManager.getInstance().getUser(oid, userProfile.getOwner());
	String	ownerName = owner.getDisplayName();
%>

<tsa:hidden name="currentPage" value="/admin/user/user_profile.jsp"/>

<%@ include file="/admin/user/user_profile_form.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>