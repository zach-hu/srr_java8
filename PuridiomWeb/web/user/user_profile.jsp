<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	commentProfile = propertiesManager.getProperty("USER", "COMMENTPROFILE", "Y");
	Address shipTo = (Address) user.getShipToAddress();
	String	errorMsg = (String) request.getAttribute("errorMsg");
	String	s_email_version = user.getEmailVersion() ;
	String	s_language = user.getLanguage();
	String	s_timeZone = user.getTimeZone();

	String dateFormatKey = user.getDateFormatKey();

	errorMsg = HiltonUtility.ckNull(errorMsg);
	if (errorMsg.length() > 0) {
		errorMsg = errorMsg + "<br>";
	}
	boolean allowApprovalEscalation = propertiesManager.getProperty("APPROVALS", "AllowApprovalEscalation", "N").equals("Y");
	String	browseLocale = propertiesManager.getProperty("USER PROFILE", "BROWSELOCALE", "");
	String userProfileCallForward = propertiesManager.getProperty("USER DEFAULTS", "CALLFORWARD", "60");
	String hideDepartment = propertiesManager.getProperty("LABELS","HIDEDEPT","N");
	List department = (List) request.getAttribute("department");
	List address    = (List) request.getAttribute("address");
	List poFormat = (List) request.getAttribute("poFormat");
	List baddress = null;
	if (oid.equalsIgnoreCase("vse06p"))
	{
		baddress   = (List) request.getAttribute("baddress") ;
		int bs = baddress.size() ;
	}

	String isDisabled="";
	if(oid.equalsIgnoreCase("HOY08P")){
		isDisabled = "readonly";
	}

	String s_audit_trail = propertiesManager.getProperty("AUDITTRAIL", "UserProfile", "N");

	String	s_pcardNumber = (String) HiltonUtility.ckNull(user.getpCardNumber());
	String	s_pcardHolder = (String) HiltonUtility.ckNull(user.getpCardHolder());
	String	s_subPcardNumber = "";
	if (s_pcardNumber.length() > 4) {
		s_subPcardNumber = s_pcardNumber.substring(s_pcardNumber.length() - 4, s_pcardNumber.length());
	} else {
		s_subPcardNumber = s_pcardNumber;
	}
	if (s_subPcardNumber.length() > 0) {
		s_subPcardNumber = "************" + s_subPcardNumber;
	}
	String	s_expYear = "";
	String	s_expMonth = "";
	if (user.getpCardExpires() != null && !user.getpCardExpires().equalsIgnoreCase("null") && user.getpCardExpires().length() >= 7) {
		s_expYear = user.getpCardExpires().substring(3,7);
		s_expMonth = user.getpCardExpires().substring(0,3);
	}
	System.out.println("TESTB");
%>
<tsa:hidden name="UserProfile_organizationId" value="<%=user.getOrganizationId()%>"/>
<tsa:hidden name="UserProfile_mailId" value="<%=user.getMailId()%>"/>
<tsa:hidden name="UserProfile_reviewProfile" value="N"/>
<tsa:hidden name="currentPage" value="/user/user_profile.jsp"/>
<tsa:hidden name="returnPage" value="/user/user_profile.jsp"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="auditSaveAdd" value="<%=s_audit_trail%>"/>
<tsa:hidden name="fromSave" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>My Profile</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
	<td valign=bottom align=middle width=*>
		<table border=0 cellpadding=2 cellspacing=0 width=100%>
		<tr>
			 <td align=right width="50%"><a href="javascript: validateMyProfile(); void(0);"><img src="<%=contextPath%>/images/alert.gif" border=0></a></td>
			 <td align=left width="50%"><a href="javascript: validateMyProfile(); void(0);">Validate Information</a></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>
<%@ include file="/user/user_info.jsp" %>
<br>

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
	<td class=error align=center colspan=3>
		<table border=0 cellpadding=1 cellspacing=0 width=90%>
		<tr><td><%=errorMsg%></td></tr>
		</table>
		<br>
	</td>
</tr>
<tr>
	<td colspan=2 width=100%>
		<table border=0 cellspacing=0 cellpadding=1>
		<tsa:tr field="user-userId">
			<tsa:td id="userIdLabelRow" field="user-userId" align="right" width="120px" noWrap="nowrap" >
				<tsa:label labelName="user-userId" defaultString="User ID" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="user-userId" >
				<tsa:input type="text" title="" name="UserProfile_userId" value="<%=headerEncoder.encodeForHTMLAttribute(user.getUserId())%>" tabIndex="0" size="25" maxLength="15" disabled="Y"/>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="user-name">
			<tsa:td id="nameLabelRow" field="user-name" align="right" width="120px" noWrap="nowrap" >
				<tsa:label labelName="user-name" defaultString="Name" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<td>
				<table border=0 cellpadding=0 cellspacing=0>
					<tr>
						<tsa:td field="user-firstName" > <tsa:input type="text" title="" name="UserProfile_firstName" value="<%=user.getFirstName()%>" tabIndex="2" size="20" maxLength="20"/></tsa:td>
						<tsa:td field="user-middleInitial" > <tsa:input type="text" title="" name="UserProfile_middleInit" value="<%=user.getMiddleInit()%>" tabIndex="3" size="3" maxLength="1"/></tsa:td>
						<tsa:td field="user-lastName" > <tsa:input type="text" title="" name="UserProfile_lastName" value="<%=user.getLastName()%>" tabIndex="4" size="30" maxLength="20"/></tsa:td>
					</tr>
				</table>
			</td>
		</tsa:tr>
		<tsa:tr field="user-mailId">
			<tsa:td id="mailIdLabelRow" field="user-mailId" align="right" height="18px" noWrap="nowrap" >
				<tsa:label labelName="user-mailId" defaultString="Email Address (Login ID)" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="user-mailId" >
				<tsa:input type="text" title="" name="as_mailId" value="<%=user.getMailId()%>" tabIndex="5" size="35" maxLength="65" onchange="lowerCase(this); trim(this);"/>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="user-emp">
			<tsa:td id="empLabelRow" field="user-emp" align="right" noWrap="nowrap" >
				<tsa:label labelName="user-emp" defaultString="Employee ID" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td id="empFieldRow" field="user-emp" >
				<tsa:input type="text" title="" name="UserProfile_emp" value="<%=user.getEmpid()%>" tabIndex="6" size="35" maxLength="15" onchange="upperCase(this)"/>
			</tsa:td>
		</tsa:tr>
		</table>
	</td>
	<td align=right valign=top>
<% if (!oid.equalsIgnoreCase("SRR10p")){%>
	<%	if (!user.getReviewProfile().equals("Y") || HiltonUtility.isEmpty(errorMsg)) {%>
			<table border=1 cellpadding=0 cellspacing=0>
			<tr><td class=browseHdr>&nbsp;My Profile Options</td></tr>
			<tr>
				<td>
					<table border=0 cellpadding=2 cellspacing=2 width=100%>
					<tr <%=HtmlWriter.isVisible(oid, "user-changePswd")%>><td nowrap><a href="javascript: doSubmit('/user/chg_pswd.jsp', 'DoNothing'); void(0);" title="Clicking here will allow you to change your Password."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-changePswd", "Change My Password")%></a></td></tr>
		<%	if (user.isAnApprover()) {%>
					<tr <%=HtmlWriter.isVisible(oid, "user-changeSignPswd")%>><td nowrap><a href="javascript: doSubmit('/user/chg_signature_pswd.jsp', 'DoNothing'); void(0);" title="Clicking here will allow you to change your Signature Password."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-changeSignPswd", "Change My SignaturePassword")%></a></td></tr>
		<%	}%>
	<%	if (propertiesManager.getProperty("LOGIN","SECURITYQUESTION","N").equalsIgnoreCase("Y")) {%>
					<tr <%=HtmlWriter.isVisible(oid, "user-changeSecurityQuestion")%>><td nowrap><a href="javascript:  doSubmit('/user/security_profile.jsp', 'DoNothing'); void(0);" title="Clicking here will allow you to change your Security Question.">Change My Security Question</a></td></tr>
	<%	}%>
					<tr><td nowrap><a href="javascript:  doSubmit('/user/change_color.jsp', 'DoNothing'); void(0);" title="Clicking here will allow you to change your Color Scheme.">Change My Color Scheme</a></td></tr>
					<!--tr <%=HtmlWriter.isVisible(oid, "user-changeRegionalSettings")%>><td nowrap><a href="javascript:  doSubmit('/user/regional_settings.jsp', 'DoNothing'); void(0);" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-changeRegionalSettings-instr", "Clicking here will allow you to change your Regional Settings.", false)%>"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-changeRegionalSettings", "Change My Regional Settings", true)%></a></td></tr-->
					</table>
				</td>
			</tr>
			</table>
	<%	}
	} %>
	</td>
</tr>
<tr>
	<td valign=top colspan=3 width=100%>
		<table border=0 cellspacing=0 cellpadding=1>
		<tsa:tr field="user-title">
			<tsa:td id="titleLabelRow" field="user-title" align="right" noWrap="nowrap" >
				<tsa:label labelName="user-title" defaultString="Title" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="user-title">
				<tsa:input type="text" title="" name="UserProfile_title" value="<%=user.getTitle()%>" tabIndex="7" size="35" maxLength="40"/>
			</tsa:td>
			<td width=*>&nbsp;</td>
		</tsa:tr>
<% if (!hideDepartment.equalsIgnoreCase("Y")) { %>
		<tsa:tr  field="user-department">
			<tsa:td id="departmentLabelRow" field="user-department" align="right" noWrap="nowrap" >
				<tsa:label labelName="user-department" defaultString="Department" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="user-department" >
				<tsa:input type="text" title="" name="UserProfile_department" value="<%=user.getDepartment()%>" tabIndex="8" size="35" maxLength="15" onchange="upperCase(this)"  />
			</tsa:td>
			<tsa:td field="user-department" cssClass="red">
			<%	if (commentProfile.equals("Y")) {	%>
				Click on the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-department", "Department", false)%> link to select a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-department", "Department", false)%>.
			<%	} %>
			</tsa:td>
		</tsa:tr>
<%  }  %>
		<tsa:tr field="user-costCenter">
			<tsa:td id="costCenterLabelRow" field="user-costCenter" align="right" noWrap="nowrap" >
				<tsa:label labelName="user-costCenter" defaultString="Cost Center" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="user-costCenter" >
				<tsa:input type="text" title="" name="UserProfile_costCenter" value="<%=user.getCostCenter()%>" tabIndex="9" size="35" maxLength="15" onchange="upperCase(this)"  >&nbsp;</tsa:input>
			</tsa:td>
			<tsa:td field="user-costCenter" cssClass="red">
			<%	if (commentProfile.equals("Y")) {	%>
				Click on the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-costCenter", "Cost Center", false)%> link to select a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-costCenter", "Cost Center", false)%>.
			<%	} %>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="user-locale">
			<tsa:td id="localeLabelRow" field="user-locale" align="right" noWrap="nowrap" >
				<tsa:label labelName="user-locale" defaultString="Location Code" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="user-locale" >
				<tsa:input type="text" title="" name="UserProfile_locale" value="<%=user.getLocale()%>" tabIndex="10" size="35" maxLength="15" onchange="upperCase(this)" />
			</tsa:td>
			<tsa:td field="user-locale" cssClass="red">
			<%	if (commentProfile.equals("Y")) {	%>
				Click on the link to select a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-locale", "Location Code", false)%>.
			<%	} %>
			</tsa:td>
		</tsa:tr>
		<!--tr <%=HtmlWriter.isVisible(oid, "language")%>>
			<td align=right <%=HtmlWriter.isVisible(oid, "user-language")%> nowrap><a href="javascript: browseStd('UserProfile_language', 'LANG'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-language", "Language", true)%></a>:</td>
			<td <%=HtmlWriter.isVisible(oid, "user-language")%>><input type=text name="UserProfile_language" value="<%=s_language%>" tabIndex=11>
			</td>
		</tr-->
		<tsa:tr field="user-currency">
			<tsa:td id="currencyLabelRow" field="user-currency" align="right" noWrap="nowrap" >
				<tsa:label labelName="user-currency" defaultString="Currency" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="user-currency" >
				<tsa:input type="text" title="" name="UserProfile_currencyCode" value="<%=user.getCurrencyCode()%>" tabIndex="12" size="35" maxLength="15" onchange="upperCase(this);" />
			</tsa:td>
			<tsa:td field="user-currency" cssClass="red">
			<%	if (commentProfile.equals("Y")) {	%>
				Click on the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-currency", "Currency", false)%> link to select a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-currency", "Currency", false)%>.
			<%	} %>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="user-timezone">
			<tsa:td id="timezoneLabelRow" field="user-timezone" align="right" noWrap="nowrap" >
				<tsa:label labelName="user-timezone" defaultString="Time Zone" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="user-timezone" >
				<tsa:input type="text" title="" name="UserProfile_timeZone" value="<%=user.getTimeZone()%>" tabIndex="13" size="35" maxLength="30" />
			</tsa:td>
			<tsa:td field="user-timezone" cssClass="red">
			<%	if (commentProfile.equals("Y")) {	%>
				Click on the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-timezone", "Time Zone", false)%> link to select a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-timezone", "Time Zone", false)%>.
			<%	} %>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="user-dateformat">
			<tsa:td id="dateformatLabelRow" field="user-dateformat" align="right" noWrap="nowrap" >
				<tsa:label labelName="user-dateformat" defaultString="Date Format" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="user-dateformat" >
				<tsa:input type="text" title="" name="UserProfile_dateFormat" value="<%=dateFormatKey%>" tabIndex="14" size="35" maxLength="15" />
			</tsa:td>
			<tsa:td field="user-dateformat" cssClass="red">
			<%	if (commentProfile.equals("Y")) {	%>
				Click on the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-dateformat", "Date Format", false)%> link to select a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-dateformat", "Date Format", false)%>.
			<%	} %>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="user-telephoneNumber">
			<tsa:td id="telephoneNumberLabelRow" field="user-telephoneNumber" align="right" noWrap="nowrap" >
				<tsa:label labelName="user-telephoneNumber" defaultString="Phone" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="user-telephoneNumber" >
				<tsa:input type="text" title="" name="UserProfile_phoneNumber" value="<%=user.getPhoneNumber()%>" tabIndex="15" size="35" maxLength="40" />
			</tsa:td>
			<tsa:td field="user-telephoneNumber" cssClass="red">
			<%	if (commentProfile.equals("Y")) {	%>
				<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-telephoneNumber", "Phone", true)%> Format: (XXX-XXX-XXXX)
			<%	} %>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="user-faxNumber">
			<tsa:td id="faxNumberLabelRow" field="user-faxNumber" align="right" noWrap="nowrap" >
				<tsa:label labelName="user-faxNumber" defaultString="Fax" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="user-faxNumber" >
				<tsa:input type="text" title="" name="UserProfile_faxNumber" value="<%=user.getFaxNumber()%>" tabIndex="16" size="35" maxLength="40"/>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="user-shipToCode">
			<tsa:td id="shipToCodeLabelRow" field="user-shipToCode" align="right" noWrap="nowrap" >
				<tsa:label labelName="user-shipToCode" defaultString="Ship To Code" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="user-shipToCode" >
				<tsa:input type="text" title="" name="UserProfile_shipToCode" value="<%=user.getShipToCode()%>" tabIndex="17" size="35" maxLength="15" onchange="upperCase(this)" />
			</tsa:td>
			<tsa:td field="user-shipToCode" cssClass="red">
			<%	if (commentProfile.equals("Y")) {	%>
				Click on the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-shipToCode", "Ship To Code", false)%> link to select a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-shipToCode", "Ship To Code", false)%>.
			<%	} %>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="user-routing">
			<tsa:td id="routingLabelRow" field="user-routing" align="right" noWrap="nowrap" >
				<tsa:label labelName="user-routing" defaultString="Routing" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="user-routing" >
				<tsa:input type="text" title="" name="UserProfile_routing" value="<%=user.getRouting()%>" tabIndex="18" size="35" maxLength="25" onchange="upperCase(this)"/>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="nameUdf1">
			<tsa:td id="nameUdf1LabelRow" field="nameUdf1" align="right" noWrap="nowrap" >
				<% 	if(oid.equalsIgnoreCase("QRI06P")) {%>
					<b><a href="javascript: browseLookup('UserProfile_nameUdf1', 'buyer'); void(0);"><tsa:label labelName="nameUdf1" defaultString="UDF 1" checkRequired="true" noinstance="yes"/>:&nbsp;</a></b>
				<% 	} else {%>
					<b><a href="javascript: browseStd('UserProfile_nameUdf1', 'AC02'); void(0);"><tsa:label labelName="nameUdf1" defaultString="UDF 1" checkRequired="true" noinstance="yes"/>:&nbsp;</a></b>
				<% 	} %>
			</tsa:td>
			<tsa:td field="nameUdf1" >
				<tsa:input type="text" title="" name="UserProfilenameUdf1" value="<%=user.getNameUdf1()%>" tabIndex="19" size="35" maxLength="15" onchange="upperCase(this)" disabled="Y"/>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="nameUdf2">
			<tsa:td id="nameUdf2LabelRow" field="nameUdf2" align="right" noWrap="nowrap" >
				<% 	if(oid.equalsIgnoreCase("QRI06P")) {%>
					<b><a href="javascript: browseLookup('UserProfile_nameUdf2', 'item_location'); void(0);"><tsa:label labelName="nameUdf2" defaultString="UDF 2" checkRequired="true" noinstance="yes"/>:&nbsp;</a></b>
				<% 	} else {%>
					<b><tsa:label labelName="nameUdf2" defaultString="UDF 2" checkRequired="true" noinstance="yes"/>:&nbsp;</b>
				<% 	} %>
			</tsa:td>
			<tsa:td field="nameUdf2" >
				<tsa:input type="text" title="" name="UserProfile_nameUdf2" value="<%=user.getNameUdf2()%>" tabIndex="20" size="35" maxLength="15" onchange="upperCase(this)"/>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="nameUdf3">
			<tsa:td id="nameUdf3LabelRow" field="nameUdf3" align="right" noWrap="nowrap" >
				<% 	if(oid.equalsIgnoreCase("QRI06P")) {%>
					<b><a href="javascript: browseStd('UserProfile_nameUdf3', 'AC01'); void(0);"><tsa:label labelName="nameUdf3" defaultString="UDF 3" checkRequired="true" noinstance="yes"/>:&nbsp;</a></b>
				<% 	} else {%>
					<b><a href="javascript: browseSetup('stdtable', 'RQ01', 'UserProfile_nameUdf3'); void(0);"><tsa:label labelName="nameUdf3" defaultString="UDF 3" checkRequired="true" noinstance="yes"/>:&nbsp;</a></b>
				<% 	} %>
			</tsa:td>
			<tsa:td field="nameUdf3" >
				<tsa:input type="text" title="" name="UserProfile_nameUdf3" value="<%=user.getNameUdf3()%>" tabIndex="21" size="35" maxLength="15" onchange="upperCase(this)"/>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="nameUdf4">
			<tsa:td id="nameUdf4LabelRow" field="nameUdf4" align="right" noWrap="nowrap" >
				<% 	if(oid.equalsIgnoreCase("QRI06P")) {%>
                    <b><a href="javascript: browseStd('UserProfile_nameUdf4', 'AC05'); void(0);"><tsa:label labelName="nameUdf4" defaultString="UDF 4" checkRequired="true" noinstance="yes"/>:&nbsp;</a></b>
				<% 	} else {%>
                    <b><a href="javascript: browseStd('UserProfile_nameUdf4', 'RQ07'); void(0);"><tsa:label labelName="nameUdf4" defaultString="UDF 4" checkRequired="true" noinstance="yes"/>:&nbsp;</a></b>
				<% 	} %>
			</tsa:td>
			<tsa:td field="nameUdf4" >
				<tsa:input type="text" title="" name="UserProfile_nameUdf4" value="<%=user.getNameUdf4()%>" tabIndex="23" size="35" maxLength="15" onchange="upperCase(this)"/>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="nameUdf5">
			<tsa:td id="nameUdf5LabelRow" field="nameUdf5" align="right" noWrap="nowrap" >
				<tsa:label labelName="nameUdf5" defaultString="UDF 5" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="nameUdf5" >
				<tsa:input type="text" title="" name="UserProfile_nameUdf5" value="<%=user.getNameUdf5()%>" tabIndex="24" size="35" maxLength="15" onchange="upperCase(this)"/>
			</tsa:td>
		</tsa:tr>
<%	if (user.isAnApprover()) {
	if (!oid.equalsIgnoreCase("SRR10p")){%>
        <tr>
          <td width=151px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "emailPreference", "Email Preference")%>:</td>
          <td><select name="UserProfile_emailVersion">
                <option <% if (s_email_version.equals("H")) {%> SELECTED <%}%> value="H" >HTML Version</option>
                <option <% if (s_email_version.equals("T")) {%> SELECTED <%}%> value="T" >Text Version</option>
                <option <% if (s_email_version.equals("N")) {%> SELECTED <%}%> value="N" >Notes Version</option>
            </select></td>
        </tr>
      <%} %>
		<tsa:tr field="user-callForward">
			<tsa:td id="callForwardLabelRow" field="user-callForward" align="right" noWrap="nowrap" >
				<a href="javascript: browseLookup('UserProfile_callForward','user-defined-approver'); void(0);"><tsa:label labelName="user-callForward" defaultString="Call Forward" checkRequired="true" noinstance="yes"/></a>:&nbsp;
			</tsa:td>
			<tsa:td field="user-callForward" colspan="2">
				<table border=0 cellpadding=0 cellspacing=0 >
				<tr>
					<tsa:input type="text" title="" name="UserProfile_callForward" value="<%=user.getCallForward()%>" tabIndex="41" size="35" disabled="true" onchange="setDateDefaultCallForward()"></tsa:input>
					<td><div id="callForwardTurnOff" style="visibility:hidden;"><a href="javascript: turnOff(); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Turn Off</a></div></td>
				</tr>
				</table>
			</tsa:td>
		</tsa:tr>
		<tsa:tr field="user-callForward" >

			<td align=right nowrap>&nbsp;</td>
			<td><input type=text name="as_approverName" value="<%=UserManager.getInstance().getUser(oid, user.getCallForward()).getDisplayName()%>" size=35 disabled></td>
		</tsa:tr>

		<tsa:tr field="user-callForward-endDate">
			<tsa:td id="callForward-endDateLabelRow" field="user-callForward-endDate" align="right" noWrap="nowrap" >
				<tsa:label labelName="user-callForward-endDate" defaultString="Call Forward End" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>

			<td nowrap>
				<input type=text name="UserProfile_forwardOffDate" value="<%=HiltonUtility.getFormattedDate(user.getForwardOffDate(), oid)%>" size=35>
				<a href="javascript: show_calendar('UserProfile_forwardOffDate', '<%=PropertiesManager.getInstance(oid).getProperty("MISC", "DateFormat", "MM-dd-yyyy")%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
			</td>
		</tsa:tr>

<%	if (allowApprovalEscalation) {
	//if (oid.equals("SRR")) { %>
				<tsa:tr field="user-backupApprover">
					<tsa:td id="backupApproverLabelRow" field="user-backupApprover" align="right" noWrap="nowrap" >
						<a href="javascript: browseLookup('UserProfile_backupApprover','user-defined-approver'); void(0);"><tsa:label labelName="user-backupApprover" defaultString="Backup Approver" checkRequired="true" noinstance="yes"/></a>:&nbsp;
					</tsa:td>
					<tsa:td field="user-backupApprover">
						<table border=0 cellpadding=0 cellspacing=0 >
						<tsa:tr field="user-backupApprover">
							<tsa:input type="text" title="" name="UserProfile_backupApprover" value="<%=user.getBackupApprover()%>" tabIndex="41" size="22" maxLength="15" disabled="true" onchange="upperCase(this); getNewInfo('backupApprover', this);"></tsa:input>
							<td><div id="backupApproverTurnOff"><a href="javascript: turnOffBackupApprover(); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Remove</a></div></td>
						</tsa:tr>
						</table>
					</tsa:td>
				</tsa:tr>
				<tsa:tr field="user-backupApprover">

					<td align=right nowrap>&nbsp;</td>
					<td><input type=text name="as_backupApproverName" value="<%=UserManager.getInstance().getUser(oid, user.getBackupApprover()).getDisplayName()%>" size=35 maxlength=100 disabled></td>
				</tsa:tr>
<%	} else {%>
				<tr>
					<td colspan=2>
						<tsa:hidden name="UserProfile_backupApprover" value="<%=user.getBackupApprover()%>"/>
						<tsa:hidden name="as_backupApproverName" value="<%=UserManager.getInstance().getUser(oid, user.getBackupApprover()).getDisplayName()%>"/>
					</td>
				</tr>
<%	}
	} else {%>
				<tr>
					<td colspan=2>
						<tsa:hidden name="UserProfile_backupApprover" value="<%=user.getBackupApprover()%>"/>
						<tsa:hidden name="as_backupApproverName" value="<%=UserManager.getInstance().getUser(oid, user.getBackupApprover()).getDisplayName()%>"/>
					</td>
				</tr>
<%	}%>
				<tr>
					<td align=right nowrap>Expand Browse Rows:</td>
					<td><input type="checkbox" name="c_checkbox" tabindex=8 value = "N" <% if (user.getExpandBrowse().equals("Y")) { %> CHECKED <% } %> ONCLICK="setFlagFromCkbox(this, frm.UserProfile_expandBrowse); void(0);"/></td>
					<tsa:hidden name="UserProfile_expandBrowse" value="<%=user.getExpandBrowse()%>"/>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "user-emailsActive")%>>
					<td align=right nowrap><%=DictionaryManager.getLabel(oid, "user-emailsActive", "Emails Active", true)%>:</td>
					<td><input type="checkbox" name="c_checkbox" tabindex=8 value = "Y" <% if (user.getEmailsActive().equals("Y")) { %> CHECKED <% } %> onclick="setFlagFromCkbox(this, frm.UserProfile_emailsActive); void(0);"/></td>
					<tsa:hidden name="UserProfile_emailsActive" value="<%=user.getEmailsActive()%>"/>
				</tr>
<%if (!oid.equalsIgnoreCase("SRR10p")){ %>
				<tr <%=HtmlWriter.isVisible(oid, "user-pcardName")%>>
					<td align=right nowrap><%=DictionaryManager.getLabel(oid, "user-pcardName", "Card Type", true)%>:</td>
					<td><SELECT NAME="UserProfile_pCardType">
							<OPTION value="" <%if ("".equalsIgnoreCase(user.getpCardType())) {%>SELECTED<%}%>>
<%
		Map paymentTypes = DictionaryManager.getInstance("payment-type", oid).getPropertyMap();
		Iterator typeIterator = paymentTypes.keySet().iterator();
		String pCardType = "";
		String pCardName = "";

		while (typeIterator.hasNext())
		{
			pCardType = (String) typeIterator.next();
			pCardName = (String) paymentTypes.get(pCardType);
%>
							<OPTION value="<%=pCardType%>" <%if (pCardType.equalsIgnoreCase(user.getpCardType())) {%>SELECTED<%}%>><%=pCardName%></VALUE>
<%	}	%>
						</SELECT></td>
				</tr>
				<TR <%=HtmlWriter.isVisible(oid, "user-pcardNumber")%>>
					<TD ALIGN="RIGHT" CLASS="basic"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-pcardNumber", "Card #", true)%>:&nbsp;</TD>
					<TD><INPUT TYPE="TEXT" NAME="as_pcardNumber" SIZE="22" MAXLENGTH="20" value="<%=s_subPcardNumber%>" ONCHANGE="upperCase(this); setPcardNumber(this);">
						<tsa:hidden name="UserProfile_pCardNumber" value="<%=s_pcardNumber%>"/></TD>
				</TR>
				<TR <%=HtmlWriter.isVisible(oid, "user-pcardHolder")%>>
					<TD ALIGN="RIGHT" CLASS="basic"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-pcardHolder", "Name", true)%>:&nbsp;</TD>
					<TD><INPUT TYPE="TEXT" NAME="UserProfile_pCardHolder" SIZE="22" MAXLENGTH="45" value="<%=s_pcardHolder%>" ONCHANGE="upperCase(this); ">
					</TD>
				</TR>
				<TR <%=HtmlWriter.isVisible(oid, "user-pcardExpirationDate")%>>
					<TD ALIGN="RIGHT" CLASS="basic"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-pcardExpirationDate", "Expiration Date")%>:&nbsp;</TD>
					<TD><SELECT NAME="as_expMonth" ONCHANGE="setExpirationDate();">
							<OPTION value="" <%if (s_expMonth.equalsIgnoreCase("")) {%>SELECTED<%}%>></OPTION>
							<OPTION value="JAN" <%if (s_expMonth.equalsIgnoreCase("JAN")) {%>SELECTED<%}%>>Jan</OPTION>
							<OPTION value="FEB" <%if (s_expMonth.equalsIgnoreCase("FEB")) {%>SELECTED<%}%>>Feb</OPTION>
							<OPTION value="MAR" <%if (s_expMonth.equalsIgnoreCase("MAR")) {%>SELECTED<%}%>>Mar</OPTION>
							<OPTION value="APR" <%if (s_expMonth.equalsIgnoreCase("APR")) {%>SELECTED<%}%>>Apr</OPTION>
							<OPTION value="MAY" <%if (s_expMonth.equalsIgnoreCase("MAY")) {%>SELECTED<%}%>>May</OPTION>
							<OPTION value="JUN" <%if (s_expMonth.equalsIgnoreCase("JUN")) {%>SELECTED<%}%>>Jun</OPTION>
							<OPTION value="JUL" <%if (s_expMonth.equalsIgnoreCase("JUL")) {%>SELECTED<%}%>>Jul</OPTION>
							<OPTION value="AUG" <%if (s_expMonth.equalsIgnoreCase("AUG")) {%>SELECTED<%}%>>Aug</OPTION>
							<OPTION value="SEP" <%if (s_expMonth.equalsIgnoreCase("SEP")) {%>SELECTED<%}%>>Sep</OPTION>
							<OPTION value="OCT" <%if (s_expMonth.equalsIgnoreCase("OCT")) {%>SELECTED<%}%>>Oct</OPTION>
							<OPTION value="NOV" <%if (s_expMonth.equalsIgnoreCase("NOV")) {%>SELECTED<%}%>>Nov</OPTION>
							<OPTION value="DEC" <%if (s_expMonth.equalsIgnoreCase("DEC")) {%>SELECTED<%}%>>Dec</OPTION>
						</SELECT>
						<SELECT NAME="as_expYear" ONCHANGE="setExpirationDate();">
							<OPTION value="" <%if (s_expYear.equalsIgnoreCase("")) {%>SELECTED<%}%>></OPTION>
							<OPTION value="2005" <%if (s_expYear.equalsIgnoreCase("2005")) {%>SELECTED<%}%>>2005</OPTION>
							<OPTION value="2006" <%if (s_expYear.equalsIgnoreCase("2006")) {%>SELECTED<%}%>>2006</OPTION>
							<OPTION value="2007" <%if (s_expYear.equalsIgnoreCase("2007")) {%>SELECTED<%}%>>2007</OPTION>
							<OPTION value="2008" <%if (s_expYear.equalsIgnoreCase("2008")) {%>SELECTED<%}%>>2008</OPTION>
							<OPTION value="2009" <%if (s_expYear.equalsIgnoreCase("2009")) {%>SELECTED<%}%>>2009</OPTION>
							<OPTION value="2010" <%if (s_expYear.equalsIgnoreCase("2010")) {%>SELECTED<%}%>>2010</OPTION>
							<OPTION value="2011" <%if (s_expYear.equalsIgnoreCase("2011")) {%>SELECTED<%}%>>2011</OPTION>
							<OPTION value="2012" <%if (s_expYear.equalsIgnoreCase("2012")) {%>SELECTED<%}%>>2012</OPTION>
							<OPTION value="2013" <%if (s_expYear.equalsIgnoreCase("2013")) {%>SELECTED<%}%>>2013</OPTION>
							<OPTION value="2014" <%if (s_expYear.equalsIgnoreCase("2014")) {%>SELECTED<%}%>>2014</OPTION>
							<OPTION value="2015" <%if (s_expYear.equalsIgnoreCase("2015")) {%>SELECTED<%}%>>2015</OPTION>
						</SELECT>
						<tsa:hidden name="UserProfile_pCardExpires" value="<%=user.getpCardExpires()%>"/>
					</TD>
				<tr>
					<td>&nbsp;</td>
					<td colspan=2>
						<br>
						<table border=0 cellpadding=1 cellspacing=0>
						<tr><td colspan=4 height=20px>&nbsp;&nbsp;<b>Bar Chart Requisition Display</b></td></tr>
						<tr>
							<td width=5% align=right><input type=radio name="UserProfile_barChart" value="3" <% if (user.getBarChart().equalsIgnoreCase("3")) {%> checked<%}%> ></td>
							<td width=20%>Last 3 Months</td>
							<td width=5% align=right><input type=radio name="UserProfile_barChart"  value="6"  <% if (user.getBarChart().equalsIgnoreCase("6")) {%> checked<%}%> ></td>
							<td width=20%>Last 6 Months</td>
							<td width=5% align=right><input type=radio name="UserProfile_barChart" value="12" <% if (user.getBarChart().equalsIgnoreCase("12")) {%> checked<%}%> ></td>
							<td width=20%>Last 12 Months</td>
							<td width=5% align=right><input type=radio name="UserProfile_barChart"  value="" <% if (HiltonUtility.isEmpty(user.getBarChart())) {%> checked<%}%> ></td>
							<td width=20%>Fiscal Year</td>
						</tr>
						</table>
					</td>
				</tr>
			<%  } //end of SRR if %>
		</table>
	</td>
	<!--td align=right valign=top colspan=2>
		<table border=0 cellpadding=1 cellspacing=0>
		<tr>
			<td width=110px align=right <%=HtmlWriter.isVisible(oid, "user-shipToCode")%> nowrap><a href="javascript: browseLookup('UserProfile_shipToCode', 'ship_to'); void(0);" title="Click here to choose a default <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-shipToCode", "Ship To Code", false)%> enter a <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-shipToCode", "Ship To Code", false)%> in the box on the right.""><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-shipToCode", "Ship To Code", true)%></a>:</td>
			<td <%=HtmlWriter.isVisible(oid, "user-shipToCode")%>><input type=text name="UserProfile_shipToCode" value="<%=user.getShipToCode()%>" tabindex=2 size=35 maxLength=15></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "user-shp-addressLine1")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-shp-addressLine1", "Address 1")%>:</td>
			<td><input type=text name="Address_addressLine1" size=35 maxLength=30 value="<%=shipTo.getAddressLine1()%>" onfocus="this.blur();" disabled></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "user-shp-addressLine2")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-shp-addressLine2", "Address 2")%>:</td>
			<td><input type=text name="Address_addressLine2" size=35 maxLength=30 value="<%=shipTo.getAddressLine2()%>" onfocus="this.blur();" disabled></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "user-shp-addressLine3")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-shp-addressLine3", "Address 3")%>:</td>
			<td><input type=text name="Address_addressLine3" size=35 maxLength=30 value="<%=shipTo.getAddressLine3()%>" onfocus="this.blur();" disabled></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "user-shp-addressLine4")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-shp-addressLine4", "Address 4")%>:</td>
			<td><input type=text name="Address_addressLine4" size=35 maxLength=30 value="<%=shipTo.getAddressLine4()%>" onfocus="this.blur();" disabled></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "user-shp-city")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-shp-city", "City")%>:</td>
			<td><input type=text name="Address_city" size=35 maxLength=30 value="<%=shipTo.getCity()%>" onfocus="this.blur();" disabled></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "user-shp-state")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-shp-state", "State")%>:</td>
			<td><input type=text name="Address_state" size=35 maxLength=30 value="<%=shipTo.getState()%>" onfocus="this.blur();" disabled></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "user-shp-zip")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-shp-zip", "Zip/Postal Code")%>:</td>
			<td><input type=text name="Address_postalCode" size=35 maxLength=30 value="<%=shipTo.getPostalCode()%>" onfocus="this.blur();" disabled></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "user-shp-country")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-shp-country", "Country")%>:</td>
			<td><input type=text name="Address_country" size=35 maxLength=25 value="<%=shipTo.getCountry()%>" onfocus="this.blur();" disabled></td>
		</tr>
		</table>
	</td-->
</tr>

<!-- This last row is used for spacing the columns.  Do not remove! -->
<tr>
	<td>&nbsp;</td>
	<td width=150px>&nbsp;</td>
	<td>&nbsp;</td>
</tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
<%	if (user.getReviewProfile().equals("Y") && !HiltonUtility.isEmpty(errorMsg)) {%>
	<td width=50% align=center><div id="pxbutton" style="align:center;"><a href="javascript: submitThis(); void(0);" tabindex=99>Save</a></div></td>
<%	} else {%>
	<td width=50% align=center>
		<table border=0><tr><td align=center><div id="pxbutton" style="align:center;"><a href="javascript: submitThis(); void(0);" tabindex=99>Save</a></div></td></tr></table>
	</td>
	<td width=50% align=center>
		<table border=0><tr><td align=center><div id="pxbutton" style="align:center;"><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);" tabindex=99>Cancel</a></div></td></tr></table>
	</td>
<%	}%>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script


	var accountFld1Browse = "<%=propertiesManager.getProperty("BROWSE", "Account_fld1", "systable")%>";
	var maxRows = 1;
	var tableType = "AC";

	frm = document.purchaseform;
	var dayCallForward = <%=userProfileCallForward%>;

	var uid = "<%=headerEncoder.encodeForJavaScript(user.getUserId())%>";
	var currentpage = "/user/user_profile.jsp";
	checkCallForwardOptions();
<%	if (allowApprovalEscalation) {%>
	checkBackupApproverOptions();
<%	}%>

	function validateForm() {
		if (frm.handler.value.indexOf("UserProfileUpdate") >= 0) {
			var alertMessage = "";
			var w;
			var m;

			w = frm.UserProfile_firstName.value;
			if ( isEmpty( w ) )
				alertMessage += 'First Name is required.\n';
			w = frm.UserProfile_lastName.value;
			if ( isEmpty( w ) )
				alertMessage += 'Last Name is required.\n';
			w = frm.as_mailId.value;
			if ( isEmpty( w ) )
				alertMessage += 'Email address is required for your login id.\n';
			if (frm.UserProfile_pCardNumber) {
				m = frm.UserProfile_pCardNumber.value;
			}
			if ( m < 4 && m > 0)
				alertMessage += 'Invalid Credit Card number.\n';

		if ( orgId == "BLY07P" )
		{
			w = frm.UserProfile_department.value;
			if ( isEmpty( w ) )
				alertMessage += '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-department", "Department")%> is required.\n';
			w = frm.UserProfile_nameUdf3.value;
			if ( isEmpty( w ) )
				alertMessage += '<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-nameUdf3", "nameUdf3")%> is required.\n';

		<%	if (user.isAnApprover()) { %>
			w = frm.UserProfile_backupApprover.value;
      	    if (isEmpty(w))
      		    alertMessage += '<%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-backupApprover", "Backup Approver") %> is required.\n';
    	<%	}%>
		}

			if ( alertMessage.length > 0 ) {
			    	alert ( 'Please fix the following problems:\n\n'+alertMessage );
			    	return false;
			}
			updateMailId();
		}

		return true;
	}

	function updateMailId() {
		var originalMailId = frm.UserProfile_mailId.value;
		var newMailId = frm.as_mailId.value;

		if (newMailId != originalMailId) {
			setHiddenFields("<input type=hidden name=" + '"' + "newUserProfile_mailId"+ '"' + " value=" + '"' + newMailId + '"' + ">");
		}
	}

	function browseSetup(browseName, browseType, fieldName) {
		if (browseType.length > 0){
			browseStd(fieldName, browseType);
		} else {
			browseLookup(fieldName, browseName);
		}
	}

	function turnOff()
	{
		frm.UserProfile_callForward.value = '';
		frm.UserProfile_forwardOffDate.value = '';
		frm.as_approverName.value = '';
		checkCallForwardOptions();
	}

	function checkCallForwardOptions() {
		if (frm.UserProfile_callForward) {
			var callForward = frm.UserProfile_callForward.value;
			var userProfileUserId = frm.UserProfile_userId.value;

			if (isEmpty(callForward) || (callForward == userProfileUserId)) {
				hideArea("callForwardTurnOff");
			} else {
				displayArea("callForwardTurnOff");
			}
		} else {
			hideArea("callForwardTurnOff");
		}
	}

	function turnOffBackupApprover()
	{
		frm.UserProfile_backupApprover.value = '';
		frm.as_backupApproverName.value = '';

		checkBackupApproverOptions();
	}

	function checkBackupApproverOptions() {
		var backupApprover = frm.UserProfile_backupApprover.value;
		var userProfileUserId = frm.UserProfile_userId.value;

		if (isEmpty(backupApprover) || (backupApprover == userProfileUserId)) {
			hideArea("backupApproverTurnOff");
		} else {
			displayArea("backupApproverTurnOff");
		}
	}

	function loadBackupApprovers() {
	<% if (!oid.equalsIgnoreCase("dtn07p")) {
		if (!oid.equalsIgnoreCase("bly07p")) { %>
		popupParameters += 'colname=UserProfile_department;operator==;filter_txt=<%= user.getDepartment() %>;logicalOperator=AND;originalFilter=Y;sort=N;';
	<% 	} %>
		popupParameters += 'colname=UserProfile_approvalAmount;operator=>=;filter_txt=<%= user.getApprovalAmount() %>;logicalOperator=AND;originalFilter=Y;sort=N;';
		popupParameters += 'originalRetrieve=Y;';
	<% } %>
	<%if (oid.equalsIgnoreCase("bly07p")) {%>
		browseLookup('UserProfile_backupApprover', 'approverprofile');
	<% } else {%>
		browseLookup('UserProfile_backupApprover', 'approver');
	<% } %>
	}

	function submitThis()
	{
	var msg = "";
	var chandler = "UserProfileUpdate";
	frm.fromSave.value = "Y";
	<% if (oid.equalsIgnoreCase("vse06p")) { %>
		 msg = "";

		if (frm.UserProfile_department.value == "")
			msg += "You must enter a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-department", "Department", false)%>\n";
		else
		{
			var valid = false;
			<% for (int i=0; i<department.size(); i++) { %>
				if (frm.UserProfile_department.value == "<%=department.get(i).toString()%>")
					valid = true;
			<% } %>
			if (!valid)
				msg += "You must enter a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-department", "Department", false)%>\n";
		}

		if (frm.UserProfile_nameUdf1.value == "")
			msg += "You must enter a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-nameUdf1", "Bill To Code", false)%>\n";
		else
		{
			var valid = false;
			<% for (int i=0; i<baddress.size(); i++) { %>
				if (frm.UserProfile_nameUdf1.value == "<%=baddress.get(i).toString()%>")
					valid = true;
			<% } %>
			if (!valid)
				msg += "You must enter a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-nameUdf1", "Bill To Code", false)%>\n";
		}

		if (frm.UserProfile_shipToCode.value == "")
			msg += "You must enter a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-shipToCode", "Ship To Code", false)%>\n";
		else
		{
			var valid = false;
			<% for (int i=0; i<address.size(); i++) { %>
				if (frm.UserProfile_shipToCode.value == "<%=address.get(i).toString()%>")
					valid = true;
			<% } %>
			if (!valid)
				msg += "You must enter a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-shipToCode", "Ship To Code", false)%>\n";
		}
	<%} else if (oid.equalsIgnoreCase("bly07p") &&  poFormat != null ) { %>
			var valid = false;
			<% for (int i=0; i<poFormat.size(); i++) { %>
				if (frm.UserProfile_nameUdf4.value == '<%=poFormat.get(i).toString()%>')
					valid = true;
			<% } %>
			if (!valid)
				msg += "You must enter a valid <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "nameUdf4", "Po Format", false)%>\n";
	<%} else { %>
		<% if (s_audit_trail.equalsIgnoreCase("Y")) { %> chandler = "UserProfileUpdate;AuditTrailUpdate"; <% } %>
		//doSubmit('menu/main_menu.jsp', chandler );
		doSubmit('/user/user_profile_validation.jsp', chandler + ';UserProfileValidate');
	<% } %>

		if(msg != "")
		{
			alert("Please fix the following problems:\n\n" + msg);
		}
		else
		{
			<% if (s_audit_trail.equalsIgnoreCase("Y")) { %> chandler = "UserProfileUpdate;AuditTrailUpdate"; <% } %>
			//doSubmit('menu/main_menu.jsp', chandler );
			doSubmit('/user/user_profile_validation.jsp', chandler + ';UserProfileValidate');
		}

	}

	function setAuditTables()
	{
		frm.auditTables.value = "UserProfile";
	}
	function getFields(el)
	{
		if ((el.type != "hidden" && el.name.indexOf("UserProfile_") > -1) ||
			(el.name == "UserProfile_expandBrowse") ||
			(el.name == "UserProfile_lockLogin") ||
			(el.name == "UserProfile_reviewProfile") ||
			(el.name == "UserProfile_overrider") ||
			(el.name == "UserProfile_adminBuyer") ||
			(el.name == "UserProfile_buyer") ||
			(el.name == "UserProfile_requisitioner") ||
			(el.name == "UserProfile_authorizedBy") ||
			(el.name == "UserProfile_receiver") ||
			(el.name == "UserProfile_approver") ||
			(el.name == "UserProfile_vchApp") ||
			(el.name == "UserProfile_appointedFlag") ||
			(el.name == "UserProfile_formValidate") ||
			(el.name == "UserProfile_administeredBy"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	function getFieldsJquery()
	{
		var jQuerySelector = ":input:not([type=hidden])[name^='UserProfile_']";
		jQuerySelector += ",:input:[name='UserProfile_expandBrowse']";
		jQuerySelector += ",:input:[name='UserProfile_lockLogin']";		
		jQuerySelector += ",:input:[name='UserProfile_reviewProfile']";		
		jQuerySelector += ",:input:[name='UserProfile_overrider']";
		jQuerySelector += ",:input:[name='UserProfile_adminBuyer']";
		jQuerySelector += ",:input:[name='UserProfile_buyer']";
		jQuerySelector += ",:input:[name='UserProfile_requisitioner']";
		jQuerySelector += ",:input:[name='UserProfile_authorizedBy']";
		jQuerySelector += ",:input:[name='UserProfile_receiver']";
		jQuerySelector += ",:input:[name='UserProfile_approver']";
		jQuerySelector += ",:input:[name='UserProfile_vchApp']";
		jQuerySelector += ",:input:[name='UserProfile_appointedFlag']";
		jQuerySelector += ",:input:[name='UserProfile_formValidate']";
		jQuerySelector += ",:input:[name='UserProfile_administeredBy']";
		jQuerySelector += ",:input:[name='UserProfile_barChart']";
		
		var fieldsToAuditJquery = $(jQuerySelector);
		return fieldsToAuditJquery;
    }
	function buildAuditIc()
	{
		return frm.UserProfile_userId.value;
	}
	function viewAuditTrail(userId)
	{
		popupParameters = popupParameters + "browseName=audittrail";
		popupParameters = popupParameters + ";colname=AuditRecord_entity1;operator==;filter_txt=" + userId + ";logicalOperator=AND;originalFilter=N;sort=N;"
		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}
	function setFlagFromCkbox(ckbox, fld) {
		if (ckbox.checked) {
		    fld.value = "Y";
		} else {
		    fld.value = "N";
		}
	}

	function viewBrowseLocale() {
		<% if(browseLocale.equals("")){ %>
			browseLookup('UserProfile_locale', 'country')
		<% }else{%>
			<%=browseLocale%>
		<% }%>
	}

	function validateMyProfile() {
		var chandler = "UserProfileUpdate";
		<% if (s_audit_trail.equalsIgnoreCase("Y")) { %> chandler = "UserProfileUpdate;AuditTrailUpdate"; <% } %>
		doSubmit('/user/user_profile_validation.jsp', chandler + ';UserProfileValidate');
	}

	function setPcardNumber(field) {
		frm.UserProfile_pCardNumber.value = field.value;
	}

	function setExpirationDate() {
		frm.UserProfile_pCardExpires.value = frm.as_expMonth.value + frm.as_expYear.value;
	}

	function setDateDefaultCallForward(){
		if(dayCallForward){
			if(frm.UserProfile_forwardOffDate.value == ""){
				var today = new Date();
				var dateCallForward = addToDate(today,	dayCallForward);
				var format = frm.userDateFormat.value;

				var gCal1 = new Calendar();
				gCal1.gYear = new String(dateCallForward.getFullYear());
				gCal1.gMonth = dateCallForward.getMonth();
				gCal1.gFormat = format.toUpperCase();
				var data = gCal1.format_data(dateCallForward.getDate());
				frm.UserProfile_forwardOffDate.value = data;
			}
		}
	}

	function addToDate ( date , day ) {
		var milisegundos = parseInt(day*24*60*60*1000);
		var tiempo = date.getTime();
		date.setTime(parseInt(tiempo+milisegundos));
		return date;
	}

//-->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>