<%
  PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
  String	userProfileMailId = (String) request.getAttribute("UserProfile_mailId");
  String	browseLocale = propertiesManager.getProperty("USER PROFILE", "BROWSELOCALE", "");

  if (HiltonUtility.isEmpty(userProfileMailId)) {
    userProfileMailId = userProfile.getMailId();
  }

  String errorMsg = 	(String)request.getAttribute("errorMsg");
  errorMsg = HiltonUtility.ckNull(errorMsg);
  if (errorMsg.length() > 0) {
    UserProfile temp = (UserProfile) request.getAttribute("userProfile");
    if (temp != null) {
      // Use userProfile from request if error message was sent so previously entered information is available
      userProfile = temp;
    }
    errorMsg = errorMsg + "<br>";
  }

  String userProfileCallForward = (String) request.getAttribute("UserProfile_callForward");
  if (HiltonUtility.isEmpty(userProfileCallForward)) {
	  userProfileCallForward = propertiesManager.getProperty("USER", "CALLFORWARD", "60");
  }
//  if (!HiltonUtility.isEmpty(userProfileCallForward)) {
//	  userProfile.setCallForward(userProfileCallForward);
//  }


  String   dateFormatKey = userProfile.getDateFormatKey();
  String timeZone = PropertiesManager.getInstance(oid).getProperty("USER DEFAULTS", "TIMEZONE", "");
  boolean allowApprovalEscalation =  PropertiesManager.getInstance(oid).getProperty("APPROVALS", "AllowApprovalEscalation", "N").equals("Y");
  boolean autoGenerateUserId =  false;
  boolean hideSignatureFields = false;

  if (PropertiesManager.getInstance(oid).getProperty("USER", "AUTOGENERATEID", "N").equals("Y") || isXpress) {
  	autoGenerateUserId = true;
  }

  if (PropertiesManager.getInstance(oid).getProperty("USER", "HIDESIGFIELDS", "N").equals("Y")){
	  hideSignatureFields = true;
  }

  if (timeZone == null){
	  timeZone = "";
  }
  String emailVersion = userProfile.getEmailVersion();

  List department = (List) request.getAttribute("department");
  List address    = (List) request.getAttribute("address");
  List stdtable    = (List) request.getAttribute("stdtable");

	String s_audit_trail =  PropertiesManager.getInstance(oid).getProperty("AUDITTRAIL", "UserProfile", "N");
%>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/accounts.js"></SCRIPT>

<tsa:hidden name="UserProfile_mailId" value="<%=userProfileMailId%>"/>
<tsa:hidden name="UserProfile_userId" value="<%=userProfile.getUserId()%>"/>
<tsa:hidden name="UserProfile_organizationId" value="<%=userProfile.getOrganizationId()%>"/>
<tsa:hidden name="UserProfile_owner" value="<%=userProfile.getOwner()%>"/>

<tsa:hidden name="User_security05" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="deleteAppRuleByUser" value="N"/>
<%	if (!newUserProfile) { %>
<tsa:hidden name="auditSaveAdd" value="<%=s_audit_trail%>"/>
<%	}
	if (createNew) {%>
<tsa:hidden name="UserProfile_lockLogin" value="N"/>
<tsa:hidden name="UserProfile_reviewProfile" value="Y"/>

<%	}%>
<tsa:hidden name="UserProfile_userId_Validate" value="<%=userProfile.getUserId()%>"/>
<tsa:hidden name="UserProfile_organizationId_Validate" value="<%=userProfile.getOrganizationId()%>"/>
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
        <div style="margin-left:10px; margin-right:10px" class=hdr12>User Profile</td>
      </td>
    </tr>
    </table>
  </td>
  <td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
  <td valign=bottom width=300px align=left height=30px>
	<table border=0 cellpadding=2 cellspacing=0 width=100%>
		<tr>
			<td align=right width="50%"><a href="javascript: validateMyProfile(); void(0);"><img src="<%=contextPath%>/images/alert.gif" border=0></a></td>
			<td align=left width="50%"><a href="javascript: validateMyProfile(); void(0);">Validate Information</a></td>
		</tr>
	</table>
    <table cellpadding="0" cellspacing="0" border=0 width=100%>
	<tr>
      <td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
    </tr>
    <tr>
      <td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
    </tr>
    </table>
  </td>
  <td valign=bottom align=right height=30px>
	<div id="userLicenseDisplay"><%@ include file="/admin/user/user_available_licenses.jsp" %></div>
    <table cellpadding="0" cellspacing="0" border=0 width=100%>
	<tr>
      <td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
    </tr>
    <tr>
      <td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
    </tr>
    </table>
  </td>
</tr>
</table>

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
  <td width=100%>
    <table border=0 cellspacing=0 cellpadding=0 width=100%>
    <tr><td colspan=2 class=error align=center><br><%=errorMsg%></td></tr>
<%	if (!createNew) {%>
    <tr>
      <td colspan=2>
        <table border=0 cellpadding=1 cellspacing=0>
        <tr>
			<tsa:td id="user-userIdLabelRow" field="user-userId" align="right" width="148px" noWrap="nowrap" >
				<tsa:label labelName="user-userId" defaultString="User ID" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="user-userId" width="145px">
					<tsa:input type="text" title="" name="as_userId" value="<%=userProfile.getUserId()%>" tabIndex="2" size="25" maxLength="15" disabled="Y" onchange="alphaNumericCheck(this); frm.UserProfile_userId.value = this.value;"></tsa:input>
			</tsa:td>
          <td width=90px align=right><b>Locked:<b></td>
          <td><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_lockLogin); void(0);" tabIndex=4 <% if (userProfile.getLockLogin().equalsIgnoreCase("Y")) {%>checked<%}%>><tsa:hidden name="UserProfile_lockLogin" value="<%=userProfile.getLockLogin()%>"/></td>
          <td width=120px align=right><b>Review Profile:<b></td>
          <td><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_reviewProfile); void(0);" tabIndex=5 <% if (userProfile.getReviewProfile().equalsIgnoreCase("Y")) {%>checked<%}%>><tsa:hidden name="UserProfile_reviewProfile" value="<%=userProfile.getReviewProfile()%>"/></td>
        </tr>
        </table>
      </td>
    </tr>
<%	} else if (!autoGenerateUserId) {%>
    <tr>
      <td colspan=2>
        <table border=0 cellpadding=1 cellspacing=0>
        <tr>
          <tsa:td id="user-userIdLabelRow" field="user-userId" align="right" width="148px" noWrap="nowrap" >
				<tsa:label labelName="user-userId" defaultString="User ID" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<tsa:td field="user-userId" width="145px">
					<tsa:input type="text" title="" name="as_userId" value="<%=userProfile.getUserId()%>" tabIndex="2" size="25" maxLength="15" onchange="alphaNumericCheck(this); frm.UserProfile_userId.value = this.value;"></tsa:input>
		   </tsa:td>
        </tr>
        </table>
      </td>
    </tr>
<%	}%>
    <tr>
      <td colspan=2>
        <table border=0 cellspacing=0 cellpadding=1>
        <tsa:tr field="user-name" >
			<tsa:td id="user-nameLabelRow" field="user-name" align="right" width="148px" noWrap="nowrap" >
				<tsa:label labelName="user-name" defaultString="Name" checkRequired="true" noinstance="yes"/>:&nbsp;
			</tsa:td>
			<td>
				<table border=0 cellpadding=0 cellspacing=0>
					<tr>
						<tsa:td field="user-firstName" > <tsa:input type="text" title="" name="UserProfile_firstName" value="<%=userProfile.getFirstName()%>" tabIndex="6" size="20" maxLength="20"/></tsa:td>
						<tsa:td field="user-middleInitial" > <tsa:input type="text" title="" name="UserProfile_middleInit" value="<%=userProfile.getMiddleInit()%>" tabIndex="8" size="3" maxLength="1"/></tsa:td>
						<tsa:td field="user-lastName" > <tsa:input type="text" title="" name="UserProfile_lastName" value="<%=userProfile.getLastName()%>" tabIndex="10" size="30" maxLength="20"/></tsa:td>
					</tr>
				</table>
			</td>
		</tsa:tr>
        </table>
      </td>
    </tr>
    <tr>
      <td width=50% align=center>
        <table border=0 cellspacing=0 cellpadding=1 width=100%>
        <tr>
          <td <%=HtmlWriter.isVisible(oid, "mailid")%> width=151px align=right nowrap>
          	<b><%=DictionaryManager.getLabel(oid, "mailid", "Email Address (Login ID)", true)%>:</b>
          </td>
          <td><input type=text name="as_mailId" value="<%=HiltonUtility.ckNull(userProfile.getMailId())%>" tabindex=12 size=35 maxlength=65 onchange="lowerCase(this); trim(this);"></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "user-expandBrowse")%>>
			<td align=right nowrap><b><%=DictionaryManager.getLabel(oid, "user-expandBrowse", "Expand Browse", true)%>:</b></td>
			<td><input type="checkbox" name="c_checkbox" tabindex=62 value = "Y" <% if (userProfile.getExpandBrowse().equals("Y")) { %> CHECKED <% } %> onclick="setFlagFromCkbox(this, frm.UserProfile_expandBrowse); void(0);"/></td>
			<tsa:hidden name="UserProfile_expandBrowse" value="<%=userProfile.getExpandBrowse()%>"/>
		</tr>
        <tr <%=HtmlWriter.isVisible(oid, "user-emailsActive")%>>
			<td align=right nowrap><b><%=DictionaryManager.getLabel(oid, "user-emailsActive", "Emails Active", true)%>:</b></td>
			<td><input type="checkbox" name="c_checkbox" tabindex=62 value = "Y" <% if (userProfile.getEmailsActive().equals("Y")) { %> CHECKED <% } %> onclick="setFlagFromCkbox(this, frm.UserProfile_emailsActive); void(0);"/></td>
			<tsa:hidden name="UserProfile_emailsActive" value="<%=userProfile.getEmailsActive()%>"/>
		</tr>
        <tr>
          <td width=151px align=right nowrap><b>Emails Version:</b></td>
          <td><select name="UserProfile_emailVersion">
                <option value="H" <% if(emailVersion != null && emailVersion.equals("H")) {%>selected="selected" <%} %>>HTML Version</option>
                <option value="T" <% if(emailVersion != null && emailVersion.equals("T")) {%>selected="selected" <%} %>>Text Version</option>
                <option value="N" <% if(emailVersion != null && emailVersion.equals("N")) {%>selected="selected" <%} %>>Notes Version</option>
            </select></td>
        </tr>
        <tr>
          <td width=150px align=right <%=HtmlWriter.isVisible(oid, "user-empid")%> nowrap><b><%=DictionaryManager.getLabel(oid, "user-empid", "Employee ID", true)%>:</b></td>
          <td <%=HtmlWriter.isVisible(oid, "user-empid")%>><input type=text name="UserProfile_empid" value="<%=userProfile.getEmpid()%>" tabindex=20 size=35 maxlength=15 onchange="upperCase(this);"></td>
        </tr>
        <tr>
          <td <%=HtmlWriter.isVisible(oid, "user-title")%> align=right nowrap><b><%=DictionaryManager.getLabel(oid, "user-title", "Title", true)%>:</b></td>
          <td <%=HtmlWriter.isVisible(oid, "user-title")%>><input type=text name="UserProfile_title" value="<%=userProfile.getTitle()%>" tabindex=30 size=35 maxlength=40></td>
        </tr>
        <tr>
          <td <%=HtmlWriter.isVisible(oid, "user-department")%> align=right nowrap><b><a href="javascript: browseLookup('UserProfile_department', 'department'); void(0);"><%=DictionaryManager.getLabel(oid, "user-department", "Department", true)%>:</a></b></td>
          <td <%=HtmlWriter.isVisible(oid, "user-department")%>><input type=text name="UserProfile_department" value="<%=userProfile.getDepartment()%>" tabindex=40 size=35 maxlength=15 readonly></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "user-costCenter")%>>
        <%	if (oid.equalsIgnoreCase("ttr09p")) {	%>
        	<td align=right <%=HtmlWriter.isVisible(oid, "user-costCenter")%> nowrap><b><a href="javascript: browseStd('UserProfile_costCenter', 'AC03'); void(0);"><%=DictionaryManager.getLabel(oid, "user-costCenter", "Cost Center", true)%></a>:</b></td>
<%			} else {	%>
        	<td align=right <%=HtmlWriter.isVisible(oid, "user-costCenter")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-costCenter", "Cost Center", false, true, "UserProfile_costCenter")%>:</td>
<%			}	%>
			<td <%=HtmlWriter.isVisible(oid, "user-costCenter")%>><input type=text name="UserProfile_costCenter" value="<%=userProfile.getCostCenter()%>" tabindex=400 size=35 maxLength=15 onchange="upperCase(this);"></td>
        </tr>
        <tr>
          <td align=right <%=HtmlWriter.isVisible(oid, "shipToCode")%> nowrap><b><a href="javascript: browseLookup('UserProfile_shipToCode', 'ship_to'); void(0);"><%=DictionaryManager.getLabel(oid, "shipToCode", "Ship To Code", true)%></a>:</b></td>
          <td <%=HtmlWriter.isVisible(oid, "shipToCode")%>><input type=text name="UserProfile_shipToCode" value="<%=userProfile.getShipToCode()%>" tabindex=410 size=35 maxLength=15 onchange="upperCase(this);"></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "user-routing")%>>
          <td align=right <%=HtmlWriter.isVisible(oid, "user-routing")%> nowrap><b><%=DictionaryManager.getLabel(oid, "user-routing", "Routing", true)%>:</b></td>
          <td <%=HtmlWriter.isVisible(oid, "user-routing")%>><input type=text name="UserProfile_routing" value="<%=userProfile.getRouting()%>" tabindex=420 size=35 maxLength=25 onchange="upperCase(this);"></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "nameUdf1")%>>
          <td align=right <%=HtmlWriter.isVisible(oid, "nameUdf1")%> nowrap>
			<%	if (DictionaryManager.isLink(oid, "nameUdf1")) {	%>
				<% 	if (oid.equalsIgnoreCase("QRI06P")) { %>
					<b><a href="javascript: browseLookup('UserProfile_nameUdf1', 'buyer'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "nameUdf1", "UDF 1", true, false, "UserProfile_nameUdf1")%>:</b></a>
				<% 	} else {%>
            		<b><a href="javascript: browseStd('UserProfile_nameUdf1', 'AC02'); void(0);"><%=DictionaryManager.getLabel(oid, "nameUdf1", "UDF 1", true, true, "UserProfile_nameUdf1")%>:</b></a>
            	<% 	} %>
			<%	} else {	%>
            	<b><%=DictionaryManager.getLabel(oid, "nameUdf1", "UDF 1", true, true, "UserProfile_nameUdf1")%>:</b>
			<%	}	%>
          </td>
          <td <%=HtmlWriter.isVisible(oid, "nameUdf1")%>><input type=text name="UserProfile_nameUdf1" value="<%=userProfile.getNameUdf1()%>" tabindex=430 size=35 maxlength=15 onchange="upperCase(this);"></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "nameUdf2")%>>
          <td align=right <%=HtmlWriter.isVisible(oid, "nameUdf2")%> nowrap>
<%	if (oid.equalsIgnoreCase("akdoc")) {	%>
            <b><a href="javascript: browseStd('UserProfile_nameUdf2', 'AC03'); void(0);"><%=DictionaryManager.getLabel(oid, "nameUdf2", "UDF 2", true, true, "UserProfile_nameUdf2")%>:</b></a>
<%	} else {	%>
		<% 	if (oid.equalsIgnoreCase("QRI06P")) { %>
			<b><a href="javascript: browseLookup('UserProfile_nameUdf2', 'item_location'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "nameUdf2", "UDF 2", true, false, "UserProfile_nameUdf2")%>:</b></a>
		<% 	} else {%>
            <b><%=DictionaryManager.getLabel(oid, "nameUdf2", "UDF 2", true, true, "UserProfile_nameUdf2")%>:</b>
        <% 	} %>
<%	}	%>
          </td>
          <td <%=HtmlWriter.isVisible(oid, "nameUdf2")%>><input type=text name="UserProfile_nameUdf2" value="<%=userProfile.getNameUdf2()%>" tabindex=440 size=35 maxlength=15 onchange="upperCase(this);"></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "nameUdf3")%>>
          <td align=right <%=HtmlWriter.isVisible(oid, "nameUdf3")%> nowrap>
<%	if (oid.equalsIgnoreCase("akdoc")) {	%>
            <b><a href="javascript: browseStd('UserProfile_nameUdf3', 'AC04'); void(0);"><%=DictionaryManager.getLabel(oid, "nameUdf3", "UDF3", true, true, "UserProfile_nameUdf3")%>:</b></a>
<%	} else {	%>
		<% 	if (oid.equalsIgnoreCase("QRI06P")) { %>
			<b><a href="javascript: browseStd('UserProfile_nameUdf3', 'AC01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "nameUdf3", "UDF 3", true, false, "UserProfile_nameUdf3")%>:</b></a>
		<% 	} else {%>
            <b><%=DictionaryManager.getLabel(oid, "nameUdf3", "UDF3", true, true, "UserProfile_nameUdf3")%>:</b>
        <% 	} %>
<%	}	%>
          </td>
          <td <%=HtmlWriter.isVisible(oid, "nameUdf3")%>><input type=text name="UserProfile_nameUdf3" value="<%=userProfile.getNameUdf3()%>" tabindex=450 size=35 maxlength=15 onchange="upperCase(this);"></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "nameUdf4")%>>
	      <td align=right <%=HtmlWriter.isVisible(oid, "nameUdf4")%> nowrap>
<%	if (oid.equalsIgnoreCase("bly07p")) {	%>
             <b><a href="javascript:  browseStd('UserProfile_nameUdf4', 'RQ07'); void(0);"><%=DictionaryManager.getLabel(oid, "nameUdf4", "UDF 4", true, true, "UserProfile_nameUdf4")%>:</b></a>
<%	} else {	%>
		<% 	if (oid.equalsIgnoreCase("QRI06P")) { %>
            <b><a href="javascript: browseStd('UserProfile_nameUdf4', 'AC05'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "nameUdf4", "UDF 4", true, false, "UserProfile_nameUdf4")%>:</b></a>
		<% 	} else {%>
        	<b><%=DictionaryManager.getLabel(oid, "nameUdf4", "UDF 4", true, true, "UserProfile_nameUdf4")%>:</b>
        <% 	} %>
<%	}	%>
       	   </td>
	    	<td <%=HtmlWriter.isVisible(oid, "nameUdf4")%>><input type=text name="UserProfile_nameUdf4" value="<%=userProfile.getNameUdf4()%>" tabindex=460 size=35 maxLength=15 onchange="upperCase(this);"></td>
		</tr>
        <tr <%=HtmlWriter.isVisible(oid, "nameUdf5")%>>
          <td align=right <%=HtmlWriter.isVisible(oid, "nameUdf5")%> nowrap><b><%=DictionaryManager.getLabel(oid, "nameUdf5", "UDF 5", true, true, "UserProfile_nameUdf5")%>:</b></td>
          <td <%=HtmlWriter.isVisible(oid, "nameUdf5")%>><input type=text name="UserProfile_nameUdf5" value="<%=userProfile.getNameUdf5()%>" tabindex=470 size=35 maxlength=15 onchange="upperCase(this);"></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "ownerName")%>>
          <td align=right <%=HtmlWriter.isVisible(oid, "ownerName")%> nowrap><b><%=DictionaryManager.getLabel(oid, "ownerName", "Owner Name", true)%>:</b></td>
          <td <%=HtmlWriter.isVisible(oid, "ownerName")%>><input type=text name="as_ownerName" value="<%=UserManager.getInstance().getUser(oid, userProfile.getNameUdf5()).getDisplayName()%>" tabindex=180 size=35 maxlength=100 disabled></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "user-locale")%>>
          <td <%=HtmlWriter.isVisible(oid, "user-locale")%> align=right nowrap>
<%	if (DictionaryManager.isLink(oid, "user-locale")) { %>
            <b><a href="javascript: viewBrowseLocale(); void(0);"><%=DictionaryManager.getLabel(oid, "user-locale", "Location Code", true)%>:</a></b>
<%	} else { %>
            <b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-locale", "Location Code", true)%>:</b>
<%	} %>
          </td>
          <td <%=HtmlWriter.isVisible(oid, "user-locale")%>><input type=text name="UserProfile_locale" value="<%=userProfile.getLocale()%>" tabindex=50 size=35 maxlength=15 onchange="upperCase(this);"></td>
        </tr>
        <!--tr <%=HtmlWriter.isVisible(oid, "user-language")%>>
			<td <%=HtmlWriter.isVisible(oid, "user-language")%> align=right nowrap>
				<b><a href="javascript: browseStd('UserProfile_language', 'LANG'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-language", "Language", true)%>:</a></b>
			</td>
          <td <%=HtmlWriter.isVisible(oid, "user-language")%>><input type=text name="UserProfile_language" value="<%=userProfile.getLanguage()%>" tabindex=51 size=35 maxlength=15 onchange="upperCase(this);"></td>
        </tr-->
		<tr <%=HtmlWriter.isVisible(oid, "user-currency")%>>
			<td align=right <%=HtmlWriter.isVisible(oid, "user-currency")%> nowrap><a href="javascript: browseLookup('UserProfile_currencyCode', 'curr_code'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-currency", "Currency", true)%></a>:</td>
			<td <%=HtmlWriter.isVisible(oid, "user-currency")%>><input type=text name="UserProfile_currencyCode" value="<%=userProfile.getCurrencyCode()%>" tabindex=12 size=35 maxlength=15 onchange="upperCase(this);"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "user-timezone")%>>
			<td align=right <%=HtmlWriter.isVisible(oid, "user-timezone")%> nowrap><a href="javascript: browseLookup('UserProfile_timeZone', 'timezone'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-timezone", "Time Zone", true, true, "UserProfile_timeZone")%></a>:</td>
			<td <%=HtmlWriter.isVisible(oid, "user-timezone")%>><input type=text name="UserProfile_timeZone" <%if(createNew){%>value="<%=timeZone%>"<%}else{%> value="<%=userProfile.getTimeZone()%>"<%} %> tabindex=13 size=35 maxlength=30></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "user-dateformat")%>>
			<td align=right <%=HtmlWriter.isVisible(oid, "user-dateformat")%> nowrap><a href="javascript: browseStd('UserProfile_dateFormat', 'DFMT'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-dateformat", "Date Format", true, true, "UserProfile_dateFormat")%></a>:</td>
			<td <%=HtmlWriter.isVisible(oid, "user-dateformat")%>><input type=text name="UserProfile_dateFormat" value="<%=dateFormatKey%>" tabindex=14 size=35 maxlength=15></td>
		</tr>

        <tr>
          <td <%=HtmlWriter.isVisible(oid, "user-telephoneNumber")%> align=right nowrap><b><%=DictionaryManager.getLabel(oid, "user-telephoneNumber", "Phone", true)%>:</b></td>
          <td <%=HtmlWriter.isVisible(oid, "user-telephoneNumber")%>><input type=text name="UserProfile_phoneNumber" value="<%=userProfile.getPhoneNumber()%>" tabindex=65 size=35 maxlength=40></td>
        </tr>
        <tr>
          <td <%=HtmlWriter.isVisible(oid, "user-faxNumber")%> align=right nowrap><b><%=DictionaryManager.getLabel(oid, "user-faxNumber", "Fax", true)%>:</b></td>
          <td <%=HtmlWriter.isVisible(oid, "user-faxNumber")%>><input type=text name="UserProfile_faxNumber" value="<%=userProfile.getFaxNumber()%>" tabindex=70 size=35 maxlength=40></td>
        </tr>

        <tr>
          <td align=right nowrap><b>Password:</b></td>
          <td><input type=password name="UserProfile_userPassword" autocomplete="off" <%if(createNew){%>value=""<%}else{%>value="******" <%}%>tabindex=80 size=35 maxlength=12></td>
        </tr>
        <tr>
          <td align=right nowrap><b>Confirm Password:</b></td>
          <td><input type=password name="confirmUserPassword" autocomplete="off" <%if(createNew){%>value=""<%}else{%>value="******" <%}%>tabindex=85 size=35 maxlength=12></td>
        </tr>
        <tr>
          <td align=right nowrap><b>Password Changed:</b></td>
          <td><input type=text name="UserProfile_passChanged" value="<%=userProfile.getPassChanged()%>" tabindex=120 size=35 maxlength=10 READONLY disabled="disabled"></td>
        </tr>
         <tr>
          <td align=right nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
          <td>
            <select name="UserProfile_status" onchange="checkDateExpiresDisplay();" tabIndex=480>
              <option value="02" <% if (userProfile.getStatus().equals("02")) {%>selected<%}%>>Permanent</option>
              <option value="03" <% if (userProfile.getStatus().equals("03")) {%>selected<%}%>>Inactive</option>
              <option value="01" <% if (userProfile.getStatus().equals("01")) {%>selected<%}%>>Temporary</option>
            </select>
          </td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "user-userType")%>>
		  <td align=right <%=HtmlWriter.isVisible(oid, "user-userType")%> nowrap><b><%=DictionaryManager.getLabel(oid, "userType", "User Type", true, true, "UserProfile_userType")%>:</b></td>
          <td <%=HtmlWriter.isVisible(oid, "user-userType")%>><input type=text name="UserProfile_userType" value="<%=userProfile.getUserType()%>" tabindex=180 size=35 maxlength=100 ></td>
        </tr>
        <tr>
          <td align=right nowrap><b>Date Entered:</b></td>
          <td><input type=text name="UserProfile_dateEntered" value="<%=HiltonUtility.getFormattedDate(userProfile.getDateEntered(), oid, userDateFormat)%>" size=15 maxlength=10 disabled></td>
        </tr>
        <tr id="dateExpires">
          <td align=right nowrap><b>Date Expires:</b></td>
          <td><input type=text name="UserProfile_dateExpires" value="<%=HiltonUtility.getFormattedDate(userProfile.getDateExpires(), oid, userDateFormat)%>" tabindex=500 size=15 maxlength=10 onchange="checkDate(this);">
            <a href="javascript: show_calendar('UserProfile_dateExpires', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
          </td>
        </tr>
        <tr>
          <td align=right nowrap><b>Date Last Logged On:</b></td>
          <td>&nbsp;<%=HiltonUtility.getFormattedDate(userProfile.getLastLoggedOn(), oid, userDateFormat + " h:mm:ss a")%></td>
        </tr>  
        <tr <%=HtmlWriter.isVisible(oid, "TARGETCENTER")%>>
          <td align=right <%=HtmlWriter.isVisible(oid, "TARGETCENTER")%> nowrap>							
            <b><a href="javascript: browseLookup('UserProfile_targetCenter', 'purchasecard'); void(0);"><%=DictionaryManager.getLabel(oid, "TARGETCENTER", "Credit card", true)%></a>:</b>
          </td>
          <td <%=HtmlWriter.isVisible(oid, "TARGETCENTER")%>><input type=text name="UserProfile_targetCenter" value="<%=userProfile.getTargetCenter()%>" tabindex=430 size=35 maxlength=15 onchange="upperCase(this);"></td>
        </tr>
        </table>
      </td>
      <td width="50%" align="center" valign="top">
        <table border=0 cellspacing=0 cellpadding=1>
        <tr><td colspan="2"><br></td></tr>
        <tr>
          <td align=center colspan=2>
        <table border=0 cellspacing=0 cellpadding=1>
        <tr><td colspan="2"><br></td></tr>
        <tr>
          <td align=center colspan=2>
            <table border=0 cellspacing=0 cellpadding=0 width=100%>
            <tr>
              <td align=center width=50%>
                <table border=0 cellspacing=0 cellpadding=1>
                <tr <%=HtmlWriter.isVisible(oid, "user-approver-overrider")%>>
                  <td align=right><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_overrider); void(0);" tabIndex=260 <% if (userProfile.isAnOverrider()) {%>checked<%}%>></td>
                  <td nowrap><b><%=DictionaryManager.getLabel(oid, "user-overrider", "Admin Approver")%></b><tsa:hidden name="UserProfile_overrider" value="<%=userProfile.getOverrider()%>"/></td>
                </tr>
                <tr <%=HtmlWriter.isVisible(oid, "user-adminBuyer")%>>
                  <td align=right><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_adminBuyer); void(0);" tabIndex=210 <% if (userProfile.isAnAdminBuyer()) {%>checked<%}%>></td>
                  <td nowrap><b><%=DictionaryManager.getLabel(oid, "user-adminBuyer", "Buyer Administrator")%></b><tsa:hidden name="UserProfile_adminBuyer" value="<%=userProfile.getAdminBuyer()%>"/></td>
                </tr>
                <tr <%=HtmlWriter.isVisible(oid, "user-buyer")%>>
                  <td align=right><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_buyer); void(0);" tabIndex=220 <% if (userProfile.isABuyer()) {%>checked<%}%>></td>
                  <td nowrap><b><%=DictionaryManager.getLabel(oid, "user-buyer", "Buyer")%></b><tsa:hidden name="UserProfile_buyer" value="<%=userProfile.getBuyer()%>"/></td>
                </tr>
                <tr <%=HtmlWriter.isVisible(oid, "user-requisitioner")%>>
                  <td align=right><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_requisitioner); void(0);" tabIndex=230  <% if (userProfile.isARequisitioner()) {%>checked<%}%>></td>
                  <td nowrap><b><%=DictionaryManager.getLabel(oid, "user-requisitioner", "Requisitioner")%></b><tsa:hidden name="UserProfile_requisitioner" value="<%=userProfile.getRequisitioner()%>"/></td>
                </tr>
                <tr <%=HtmlWriter.isVisible(oid, "user-authorizedBy")%>>
                  <td align=right><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_authorizedBy); void(0);" tabIndex=240 <% if (userProfile.isAnAuthorizedBy()) {%>checked<%}%>></td>
                  <td nowrap><b><%=DictionaryManager.getLabel(oid, "user-authorizedBy", "Authorized By")%></b><tsa:hidden name="UserProfile_authorizedBy" value="<%=userProfile.getAuthorizedBy()%>"/></td>
                </tr>
                <tr <%=HtmlWriter.isVisible(oid, "user-receiver")%>>
                  <td align=right><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_receiver); void(0);" tabIndex=250 <% if (userProfile.isAReceiverValue()) {%>checked<%}%>></td>
                  <td nowrap><b><%=DictionaryManager.getLabel(oid, "user-receiver", "Receiver")%></b><tsa:hidden name="UserProfile_receiver" value="<%=userProfile.getReceiver()%>"/></td>
                </tr>
                <tr>
                  <td align=right>&nbsp;</td>
                  <td nowrap>&nbsp;</td>
                </tr>
                </table>
              </td>
              <td align=center width=50% valign=top>
                <table border=0 cellspacing=0 cellpadding=1>
                <tr <%=HtmlWriter.isVisible(oid, "user-approver")%>>
                  <td align=right><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_approver); void(0);" tabIndex=260 <% if (userProfile.isAnApprover()) {%>checked<%}%>></td>
                  <td nowrap><b><%=DictionaryManager.getLabel(oid, "user-approver", "Approver")%></b><tsa:hidden name="UserProfile_approver" value="<%=userProfile.getApprover()%>"/></td>
                </tr>
                <tr <%=HtmlWriter.isVisible(oid, "user-vchApp")%>>
                  <td align=right><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_vchApp); void(0);" tabIndex=270 <% if (userProfile.isVchApprover()) {%>checked<%}%>></td>
                  <td nowrap><b><%=DictionaryManager.getLabel(oid, "user-vchApp", "Invoice Approver")%></b><tsa:hidden name="UserProfile_vchApp" value="<%=userProfile.getVchApp()%>"/></td>
                </tr>
                <tr <%=HtmlWriter.isVisible(oid, "user-appointedFlag")%>>
                  <td align=right><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_appointedFlag); void(0);" tabIndex=280 <% if (userProfile.isAppointed()) {%>checked<%}%>></td>
                  <td nowrap><b><%=DictionaryManager.getLabel(oid, "user-appointedFlag", "Appointed")%></b><tsa:hidden name="UserProfile_appointedFlag" value="<%=userProfile.getAppointedFlag()%>"/></td>
                </tr>
                <tr <%=HtmlWriter.isVisible(oid, "user-formValidate")%>>
                  <td align=right><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_formValidate); void(0);" tabIndex=290 <% if (userProfile.isAFormValidate()) {%>checked<%}%>></td>
                  <td nowrap><b><%=DictionaryManager.getLabel(oid, "user-formValidate", "Form Validation")%></b><tsa:hidden name="UserProfile_formValidate" value="<%=userProfile.getFormValidate()%>"/></td>
                </tr>
                <tr <%=HtmlWriter.isVisible(oid, "user-administeredBy")%>>
                  <td align=right><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_administeredBy); void(0);" tabIndex=300 <% if (userProfile.isAnAdministeredBy()) {%>checked<%}%>></td>
                  <td nowrap><b><%=DictionaryManager.getLabel(oid, "user-administeredBy", "Administered By")%></b><tsa:hidden name="UserProfile_administeredBy" value="<%=userProfile.getAdministeredBy()%>"/></td>
                </tr>
                <tr <%=HtmlWriter.isVisible(oid, "user-inspector")%>>
                  <td align=right><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_inspector); void(0);" tabIndex=300 <% if (userProfile.isAInspector()) {%>checked<%}%>></td>
                  <td nowrap><b><%=DictionaryManager.getLabel(oid, "user-inspector", "Inspector")%></b><tsa:hidden name="UserProfile_inspector" value="<%=userProfile.getInspector()%>"/></td>
                </tr>
                <tr <%=HtmlWriter.isVisible(oid, "user-engineer")%>>
                  <td align=right><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_engineer); void(0);" tabIndex=300 <% if (userProfile.isAEngineer()) {%>checked<%}%>></td>
                  <td nowrap><b><%=DictionaryManager.getLabel(oid, "user-engineer", "Engineer")%></b><tsa:hidden name="UserProfile_engineer" value="<%=userProfile.getEngineer()%>"/></td>
                </tr>
                <tr <%=HtmlWriter.isVisible(oid, "user-fpe")%>>
                  <td align=right><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_fpe); void(0);" tabIndex=300 <% if (userProfile.isAFpe()) {%>checked<%}%>></td>
                  <td nowrap><b><%=DictionaryManager.getLabel(oid, "user-fpe", "FPE")%></b><tsa:hidden name="UserProfile_fpe" value="<%=userProfile.getFpe()%>"/></td>
                </tr>
                <tr <%=HtmlWriter.isVisible(oid, "user-marker")%>>
                  <td align=right><input type=checkbox name=ckbox value=Y onclick="setFlagFromCkbox(this, frm.UserProfile_marker); void(0);" tabIndex=300 <% if (userProfile.isAMarker()) {%>checked<%}%>></td>
                  <td nowrap><b><%=DictionaryManager.getLabel(oid, "user-marker", "Marker")%></b><tsa:hidden name="UserProfile_marker" value="<%=userProfile.getMarker()%>"/></td>
                </tr>
                </table>
              </td>
            </tr>
            </table>
          </td>
        </tr>
        <tr><td colspan="2"><br></td></tr>
    <% if (!hideSignatureFields) {%>
        <tr>
          <td align=right nowrap><b>Signature:</b></td>
          <td>
            <input type=text name="UserProfile_signature" value="<%=userProfile.getSignature()%>" tabindex=90 size=35 maxlength=50>
          </td>
        </tr>
        <tr>
          <td align=right nowrap><b>Signature Password:</b></td>
          <td><input type=password name="UserProfile_signaturePassword" autocomplete="off" value="<%=BlackBox.getDecrypt(userProfile.getSignaturePassword())%>" tabindex=100 size=35 maxlength=12></td>
        </tr>
        <tr>
          <td align=right nowrap><b>Confirm Password:</b></td>
          <td><input type=password name="confirmSignaturePassword" autocomplete="off" value="<%=BlackBox.getDecrypt(userProfile.getSignaturePassword())%>" tabindex=110 size=35 maxlength=12></td>
        </tr>
    <% } %>
        <tr>
          <td align=right nowrap><b>Approval Authorization:</b></td>
          <td><input type=text name="UserProfile_approvalAmount" value="<%=HiltonUtility.getFormattedDollar(userProfile.getApprovalAmount(), oid)%>" tabindex="130" size="35" maxlength="10" onchange="formatMe(this);"></td>
        </tr>
        <tr>
          <td align=right nowrap><b>Buyer Warrant Amount:</b></td>
          <td><input type=text name="UserProfile_warrantAmount" value="<%=HiltonUtility.getFormattedDollar(userProfile.getWarrantAmount(), oid)%>" tabindex="140" size="35" maxlength="10" onchange="formatMe(this);"></td>
        </tr>
        <tr>
          <td align=right nowrap><b>Contract Warrant Amount:</b></td>
          <td><input type=text name="UserProfile_contractAmount" value="<%=HiltonUtility.getFormattedDollar(userProfile.getContractAmount(), oid)%>" tabindex="140" size="35" maxlength="10" onchange="formatMe(this);"></td>
        </tr>
        <tr>
          <td <%=HtmlWriter.isVisible(oid, "user-callForward")%> align=right nowrap><b><a href="javascript: browseLookup('UserProfile_callForward', 'approver'); void(0);">Call Forward:</a></b></td>
          <td <%=HtmlWriter.isVisible(oid, "user-callForward")%>>
            <table border=0 cellpadding=0 cellspacing=0>
            <tr>
              <td><input type=text name="UserProfile_callForward" value="<%=userProfile.getCallForward()%>" tabindex=170 size=22 maxlength=15 disabled onchange="upperCase(this); getNewInfo('approver', this); setDateDefaultCallForward();"></td>
              <td><div id="callForwardTurnOff"><a href="javascript: turnOff(); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Turn Off</a></td></div>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td align=right nowrap>&nbsp;</td>
          <td><input type=text name="as_approverName" value="<%=UserManager.getInstance().getUser(oid, userProfile.getCallForward()).getDisplayName()%>" tabindex=180 size=35 maxlength=100 disabled></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "callForward-endDate")%>>
          <td align=right nowrap><b></>Call Forward End:</b></td>
          <td nowrap>
<%		if (!HiltonUtility.isEmpty(userProfile.getCallForward()) && HiltonUtility.ckNull(userProfile.getForwardOffDate()).after(d_today)) {%>
            <input type=text name="UserProfile_forwardOffDate" value="<%=HiltonUtility.getFormattedDate(userProfile.getForwardOffDate(), oid, userDateFormat)%>" tabindex=190 size=20>
<%		} else { %>
            <input type=text name="UserProfile_forwardOffDate" value="" tabindex=200 size=20>
<%		} %>
            <a href="javascript: show_calendar('UserProfile_forwardOffDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
          </td>
        </tr>
<%	if (allowApprovalEscalation) {%>
        <tr>
          <td <%=HtmlWriter.isVisible(oid, "user-backupApprover")%> align=right nowrap><b><a href="javascript: loadBackupApprovers(); void(0);"><%=DictionaryManager.getLabel(oid, "user-backupApprover", "Backup Approver", true)%>:</a></b></td>
          <td <%=HtmlWriter.isVisible(oid, "user-backupApprover")%>>
            <table border=0 cellpadding=0 cellspacing=0>
            <tr>
              <td><input type=text name="UserProfile_backupApprover" value="<%=userProfile.getBackupApprover()%>" tabindex=210 size=22 maxlength=15 disabled onchange="upperCase(this); getNewInfo('backupApprover', this);"></td>
              <td><div id="backupApproverTurnOff"><a href="javascript: turnOffBackupApprover(); void(0);"><img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Remove</a></td></div>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td align=right nowrap>&nbsp;</td>
          <td><input type=text name="as_backupApproverName" value="<%=UserManager.getInstance().getUser(oid, userProfile.getBackupApprover()).getDisplayName()%>" size=35 maxlength=100 disabled></td>
        </tr>
<%	}%>
		<tr <%=HtmlWriter.isVisible(oid, "user-lastChangeBy")%>>
          <td align=right <%=HtmlWriter.isVisible(oid, "user-lastChangeBy")%> nowrap><b><%=DictionaryManager.getLabel(oid, "lastChangeBy", "Last Changed By", true)%>:</b></td>
          <td <%=HtmlWriter.isVisible(oid, "user-lastChangeBy")%>><input type=text name="UserProfile_lastChangedBy" value="<%=userProfile.getLastChangeBy()%>" tabindex="480" size="35" maxlength="15" onchange="upperCase(this);" disabled></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "user-lastChangedDate")%>>
          <td align=right <%=HtmlWriter.isVisible(oid, "user-lastChangedDate")%> nowrap><b><%=DictionaryManager.getLabel(oid, "lastChangedDate", "Last Changed Date", true)%>:</b></td>
          <td <%=HtmlWriter.isVisible(oid, "user-lastChangedDate")%>><input type=text name="UserProfile_lastChangedDate" value="<%=HiltonUtility.getFormattedDate(userProfile.getLastChangeDate(), oid, userDateFormat)%>" size=15 maxlength=10 disabled></td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
<%	if (role.getAccessRights("USRROLE") > 0) { %>
	<div style="visible:visible">
<%	} else {%>
	<div style="visible:hidden;display:none;">
<%	}%>
    <br>

    <table border=0 cellspacing=0 cellpadding=2 width=680px>
    <tr>
      <td width=100% align=center>
        <table border=0 cellspacing=0 cellpadding=2 width=450px class=browseHdrDk>
        <tr>
          <td width=115px class=browseHdrDk>&nbsp;Group ID</td>
          <td width=285px class=browseHdrDk>Group Description</td>
          <td width=50px class=browseHdrDk>Remove</td>
        </tr>
        </table>
        <div id="scrollbox" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 450px; align:center; overflow-y:auto">
        <table id="groupProfiles" border=0 cellpadding=2 cellspacing=0 width=415px align=left>

        </table>
        </div>

        <a href="javascript: browseGroups(); void(0);">Select Group</a>
      </td>
    </tr>
    </table>
	</div>
  </td>
</tr>
</table>

<br>
<% if (!newUserProfile && s_audit_trail.equalsIgnoreCase("Y")) { %>
<table border=0 cellpadding=2 cellspacing=0 width=680px>
	<tr>
		<td align=center>
			<a href="javascript: viewAuditTrail('<%=userProfile.getUserId()%>');" title="Audit Trail"><img src="<%=contextPath%>/images/asset3.gif" border="0" alt="Audit Trail"></a>
			<a href="javascript: viewAuditTrail('<%=userProfile.getUserId()%>');" title="Audit Trail">Audit Trail</a>
		</td>
	</tr>
</table>
<% } %>
<br>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
<%	if (createNew) {%>
  <td width=50% align=center>
	<div id="pxbutton"><a href="javascript: submitThis('UserProfileAdminAdd'); void(0);">Add</a></div>
  </td>
<%	} else {%>
  <td width=50% align=center>
    <div id="pxbutton"><a href="javascript: submitThis('UserProfileAdminUpdate'); void(0);">Save</a></div>
  </td>
<%	}%>
  <td width=50% align=center>
	<div id="pxbutton"><a href="javascript: doSubmit('admin/admin_menu.jsp', 'DoNothing'); void(0);">Return</a></div>
  </td>
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
  var uid = "<%=userProfile.getUserId()%>";
  var currentpage = "/admin/user/user_profile.jsp";
  var originalApprover = <%=userProfile.isAnApprover()%>;
  var userRoleAccess = <%=role.getAccessRights("USRROLE")%>;
<%	if (createNew) {	%>
  setNavCookie("/admin/user/user_profile_add.jsp", "DoNothing", "Add New User");
<%	} else {	%>
  setNavCookie("/admin/user/user_profile.jsp", "DoNothing", "<%=userProfile.getDisplayName()%>");
<%	}	%>

<%    if (!hideSignatureFields) {
		if (HiltonUtility.isEmpty(userProfile.getSignaturePassword())) {%>
  frm.UserProfile_signaturePassword.value = "";
  frm.confirmSignaturePassword.value = "";
<%	}
}%>

<%	List groupList = userProfile.getUserRoles();
    if (groupList == null) {
    }
    if (groupList != null) {
      for (int ig = 0; ig < groupList.size(); ig++) {
        String	gid = (String) groupList.get(ig);
        String	desc = GroupManager.getInstance().getSecurityGroupDescription(oid, gid);%>
  addRole("<%=gid%>", "<%=desc%>");
<%		}
    }%>
  checkDateExpiresDisplay();
  checkCallForwardOptions();
<%	if (allowApprovalEscalation) {%>
  checkBackupApproverOptions();
<%	}%>

	function setCheckbox(fld)
	{
		fld.value = 'N';
		if ( frm.c_checkbox.checked )
		{
			fld.value = 'Y';
		}
		return true;
	}


  function validateForm() {
    var handler = frm.handler.value;
    var valid = false;
    var createNew = <%=createNew%>;

    if (handler.indexOf("UserProfileAdminUpdate") >= 0 || handler.indexOf("UserProfileAdminAdd") >= 0) {
      var alertMessage = "";
      var w;

<%	if (!autoGenerateUserId) {%>
      w = frm.UserProfile_userId.value;
      if ( isEmpty( w ) )
        alertMessage += 'UserId is required.\n';
<%	}%>
	  w = frm.UserProfile_firstName.value;
      if ( isEmpty( w ) )
        alertMessage += 'First Name is required.\n';
      w = frm.UserProfile_lastName.value;
      if ( isEmpty( w ) )
        alertMessage += 'Last Name is required.\n';
      w = frm.as_mailId.value;
      if ( isEmpty( w ) )
        alertMessage += 'Email address is required for the login id.\n';
 <% if (!hideSignatureFields) {%>
	  w = frm.confirmSignaturePassword.value;
      if ( w != frm.UserProfile_signaturePassword.value )
        alertMessage += 'Password Confirmation does not match Signature Password.\n';
<% } %>
      if (frm.UserProfile_department.value == "")
    	  alertMessage += 'You must enter a valid <%=DictionaryManager.getLabel(oid, "user-department", "Department")%>\n';

	  if (frm.UserProfile_shipToCode.value == "")
		  alertMessage += 'You must enter a valid <%=DictionaryManager.getLabel(oid, "user-shipToCode", "Ship To Code")%>\n';

      if(frm.UserProfile_status.value == '01'){
    	  var dateEntered = frm.UserProfile_dateEntered;
          var dateExpires = frm.UserProfile_dateExpires;

          if(frm.UserProfile_dateExpires.value != ''){
          	if(!compareDateGreaterThan(dateExpires,dateEntered)){
          		alertMessage += 'Date Expires must be greater than Date Entered.\n';
            }
          }
      }

      if ( alertMessage.length > 0 ) {
            alert('Please fix the following problems:\n\n' + alertMessage);
            return false;
      }

		if (userRoleAccess > 0) {
			var groupUserIds = document.all("UserGroupRel_userId");

			if (groupUserIds == null) {
				if (!confirm("No user group roles have been assigned to this user.  Continue?")) {
					return false;
				}
			}
			if (groupUserIds != null) {
				if (groupUserIds.length > 0) {
					for (var i=0; i < groupUserIds.length; i++) {
						frm.UserGroupRel_userId[i].value = frm.UserProfile_userId.value;
					}
				} else {
					frm.UserGroupRel_userId.value = frm.UserProfile_userId.value;
				}
			}
			else {
				//may need to add functionality to delete all roles
			}
		} else {
			setUserGroupRoles();
		}
		updateMailId();
    }

    if (frm.UserProfile_userPassword.value == "******") {
      frm.UserProfile_userPassword.name = "temp";
      frm.confirmUserPassword.name = "temp";
    }

    frm.deleteAppRuleByUser.value = "N";

    if (originalApprover && (handler.indexOf("UserProfileAdminUpdate") >= 0)) {
      if (frm.UserProfile_approver.value != "Y") {
        if (confirm("Click OK to delete all approval rules for this user.  Click Cancel to continue.")) {
          frm.deleteAppRuleByUser.value = "Y";
        }
      }
    }
    return true;
  }

  function setFlagFromCkbox(ckbox, fld) {
    if (ckbox.checked) {
      fld.value = "Y";
    } else {
      fld.value = "N";
    }
  }

  function addRole(rid, desc) {
    var myTable = document.getElementById("groupProfiles");
    var myRow = createRow(myTable);
    var myCell = createCell(myRow);
    var rowCount = myTable.rows.length;

    myRow.id = rid;
    myCell.style.width = "115px";
    myCell.innerHTML = rid + "<input type=hidden name=" + '"' + "UserGroupRel_userId" + '"' + " value=" + '"' + uid + '"' + "><input type=hidden name=" + '"' + "UserGroupRel_groupId" + '"' + " value=" + '"' + rid + '"' + ">";

    myCell = createCell(myRow);
    myCell.style.width = "285px";
    myCell.innerHTML = desc;

    myCell = createCell(myRow);
    myCell.style.width = "35px";
    myCell.align = "center";
    myCell.innerHTML = "<a href=" + '"' + "javascript: removeRole('" + rid + "'); void(0);" + '"' + "><img src=" + '"' + "<%=contextPath%>/images/delete.gif" + '"' + "border=0></a>";
  }

  function removeRole(rid) {
    var myTable = document.getElementById("groupProfiles");
    var rows = myTable.rows;

    for (var i=0; i < rows.length; i++) {
      if (rows[i].id == rid) {
        myTable.deleteRow(i);
      }
    }
  }

  function removeAllRoles() {
    var myTable = document.getElementById("groupProfiles");
	if (myTable != undefined) {
	    var rows = myTable.rows;
		if (rows != undefined && rows != null) {
	    	for (var i=0; i < rows.length; i++) {
   		    	myTable.deleteRow(i);
			}
		}
	}
  }
  function browseGroups() {
    popupParameters =  popupParameters + "browseName=securitygroup;allowBrowse=true";

    doSubmitToPopup('browse/browse_groups_popup.jsp', 'BrowseRetrieve', 'WIDTH=705px', 'HEIGHT=505px');
  }

  function checkDateExpiresDisplay() {
    if (frm.UserProfile_status[frm.UserProfile_status.selectedIndex].value == "01") {
      displayArea("dateExpires");
    } else {
      hideArea("dateExpires");
    }
  }

  function deleteUser() {
    if (confirm("Delete user '<%=userProfile.getUserId()%>'?")) {
      doSubmit('/admin/admin_menu.jsp', 'UserProfileDelete');
    } else {
      return false;
    }
  }

  function updateMailId() {
    var newMailId = frm.as_mailId.value;
<%	if (createNew) {%>
    frm.UserProfile_mailId.value = newMailId;
<%	} else {%>
    var originalMailId = frm.UserProfile_mailId.value;
    if (newMailId != "<%=userProfileMailId%>") {
      setHiddenFields("<input type=hidden name=" + '"' + "newUserProfile_mailId"+ '"' + " value=" + '"' + newMailId + '"' + ">");
    }
<%	}%>
  }

  function browseSetup(browseName, browseType, fieldName) {
    if (browseType.length > 0){
      browseStd(fieldName, browseType);
    } else {
      browseLookup(fieldName, browseName);
    }
  }

  function formatMe(x)
  {
    var dollar_dec = <%=Integer.valueOf(s_dollar_decimals).intValue()%>;
    x.value = nformat(eval(nfilter(x)),dollar_dec);
  }

  function turnOff()
  {
    frm.UserProfile_callForward.value = '';
    frm.as_approverName.value = '';

    if (frm.UserProfile_forwardOffDate)
    {
      frm.UserProfile_forwardOffDate.value = '';
    }

    checkCallForwardOptions();
  }

  function checkCallForwardOptions() {
    var callForward = frm.UserProfile_callForward.value;
    var userProfileUserId = frm.UserProfile_userId.value;

    if (isEmpty(callForward) || (callForward == userProfileUserId)) {
      hideArea("callForwardTurnOff");
    } else {
      displayArea("callForwardTurnOff");
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

	<%	String s_department = userProfile.getDepartment();
		if (!oid.equalsIgnoreCase("dtn07p")) {
			if (oid.equalsIgnoreCase("bly07p")) {
				s_department = s_department.substring(0,3) + "%";
			} %>

	    popupParameters += 'colname=UserProfile_department;operator==;filter_txt=<%= s_department %>;logicalOperator=AND;originalFilter=Y;sort=N;';
	    popupParameters += 'colname=UserProfile_approvalAmount;operator=>=;filter_txt=<%= userProfile.getApprovalAmount() %>;logicalOperator=AND;originalFilter=Y;sort=N;';
	    popupParameters += 'originalRetrieve=Y;';
	<% } %>
	<%if (oid.equalsIgnoreCase("bly07p")) {%>
		browseLookup('UserProfile_backupApprover', 'approverprofile');
	<% } else {%>
		browseLookup('UserProfile_backupApprover', 'approver');
	<% } %>
	}

	function submitThis(handler)
	{
		var msg = "";
		var valid = false;
		frm.fromSave.value = "Y";
		<% if (oid.equalsIgnoreCase("vse06p")) { %>
		if (frm.UserProfile_department.value == "")
			msg += "You must enter a valid <%=DictionaryManager.getLabel(oid, "user-department", "Department", false)%>\n";
		else
		{
			valid = false;
			<% for (int i=0; i<department.size(); i++) { %>
				if (frm.UserProfile_department.value == "<%=department.get(i).toString()%>")
					valid = true;
			<% } %>
			if (!valid)
				msg += "You must enter a valid <%=DictionaryManager.getLabel(oid, "user-department", "Department", false)%>\n";
		}

		if (frm.UserProfile_shipToCode.value == "")
			msg += "You must enter a valid <%=DictionaryManager.getLabel(oid, "user-shipToCode", "Ship To Code", false)%>\n";
		else
		{
			var valid = false;
			<% for (int i=0; i<address.size(); i++) { %>
				if (frm.UserProfile_shipToCode.value == "<%=address.get(i).toString()%>")
					valid = true;
			<% } %>
			if (!valid)
				msg += "You must enter a valid <%=DictionaryManager.getLabel(oid, "user-shipToCode", "Ship To Code", false)%>\n";
		}

		if(msg != "")
			alert("Please fix the following problems:\n\n" + msg);
		else
			<% if (!newUserProfile && s_audit_trail.equalsIgnoreCase("Y")) { %> handler = handler + ";AuditTrailUpdate";<% } %>
			//doSubmit('admin/admin_menu.jsp', handler);
			doSubmit('admin/user/user_profile_validation.jsp', handler + ';UserProfileValidate');
	<% } else { %>
		<% if (!newUserProfile && s_audit_trail.equalsIgnoreCase("Y")) { %> handler = handler + ";AuditTrailUpdate";<% } %>
		//doSubmit('admin/admin_menu.jsp', handler);
		doSubmit('admin/user/user_profile_validation.jsp', handler + ';UserProfileValidate');
	<% } %>
	}

	function setAuditTables()
	{
		frm.auditTables.value = "UserProfile";
	}
	function getFields(el)
	{
		if ((el.type != "hidden" && el.name.indexOf("UserProfile_") > -1) ||
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
		var jQuerySelector = ":input:not([type=hidden][name='UserProfile_userPassword'])[name^='UserProfile_']";
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
	function setUserGroupRoles() {
		var userId = frm.UserProfile_userId.value;
		if (userId == "SYSADM") {
			addRole("PX-ADMIN", "<%=GroupManager.getInstance().getSecurityGroupDescription(oid, "PX-ADMIN")%>");
		} else if (frm.UserProfile_userType.value == "ADMIN") {
			addRole("ADMIN", "<%=GroupManager.getInstance().getSecurityGroupDescription(oid, "ADMIN")%>");
		} else {
			var requisitioner = frm.UserProfile_requisitioner.value;
			var buyer = frm.UserProfile_buyer.value;
			var approver = frm.UserProfile_approver.value;x
			var overrider = frm.UserProfile_overrider.value;
			var userStatus = frm.UserProfile_status.value;
			var dateExpires = frm.UserProfile_dateExpires.value;

			removeAllRoles();

			if (userStatus == "02" || (compareDate(dateExpires, frm.todayDate))) {
				if (requisitioner == "Y") {
					addRole("REQUISITIONER", "<%=GroupManager.getInstance().getSecurityGroupDescription(oid, "REQUISITIONER")%>");
				}
				if (buyer == "Y") {
					addRole("BUYER", "<%=GroupManager.getInstance().getSecurityGroupDescription(oid, "BUYER")%>");
				}
				if (approver == "Y") {
					addRole("APPROVER", "<%=GroupManager.getInstance().getSecurityGroupDescription(oid, "APPROVER")%>");
				}
				if (overrider == "Y") {
					addRole("ADMINAPPROVER", "<%=GroupManager.getInstance().getSecurityGroupDescription(oid, "ADMINAPPROVER")%>");
				}
			}
		}
	}

	function viewBrowseLocale() {
		<% if(browseLocale.equals("")){ %>
			browseLookup('UserProfile_locale', 'country');
		<% }else{%>
			<%=browseLocale%>
		<% }%>
	}

	function validateMyProfile() {
		var chandler = "UserProfileAdminUpdate";
		<% if (!newUserProfile && s_audit_trail.equalsIgnoreCase("Y")) { %> chandler = "UserProfileAdminUpdate;AuditTrailUpdate"; <% } %>
		doSubmit('/admin/user/user_profile_validation.jsp', chandler + ';UserProfileValidate');
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