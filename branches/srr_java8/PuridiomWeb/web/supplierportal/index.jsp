<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.Organization" %>
<%@ page import="com.tsa.puridiom.organization.OrganizationManager" %>
<%@ page import="com.tsa.puridiom.common.documents.StdDocumentType" %>
<%
	if (userId.equalsIgnoreCase("BB-GUEST")) {
		userId = "";
	}
	String errorMsg = 	(String)request.getAttribute("errorMsg");
	String attempts = 	(String) request.getAttribute("attempts");
	String priorId	=	(String) request.getAttribute("priorId");

	errorMsg = HiltonUtility.ckNull(errorMsg);

	if (HiltonUtility.isEmpty(oid)) {
		oid = HiltonUtility.ckNull((String) request.getAttribute("oid"));
	}

	List organizationList = OrganizationManager.getInstance().getOrganizationList(true);
	if (organizationList == null) {
		organizationList = new ArrayList();
	}
	if (organizationList.size() <= 0) {
		Organization organization = new Organization();
		organization.setOrganizationId("PURIDIOM");
		organization.setOrganizationName("Puridiom");
		organizationList.add(organization);
	}
%>

<tsa:hidden name="loginFailurePage" value="index.jsp"/>
<tsa:hidden name="passwordResetPage" value="/user/chg_pswd.jsp"/>
<tsa:hidden name="lockedPage" value="/user/locked_out.jsp"/>
<tsa:hidden name="attempts" value="<%=attempts%>"/>
<tsa:hidden name="priorId" value="<%=priorId%>"/>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=100% valign=top>
		<table border=0 width=680px align=left border=0>
		<tr>
			<td colspan=3 class=error align=center>&nbsp;<%=errorMsg%>&nbsp;</td>
		</tr>
		<tr>
		<%	if (organizationList.size() > 1) {%>
			<td align=right><B>Organization:</B></td>
			<td>
				<select name=oidOptions tabindex=1>
		<%		for (int i = 0; i < organizationList.size(); i++) {
						Organization organization = (Organization) organizationList.get(i);
						String organizationName = organization.getOrganizationName();

						if (HiltonUtility.isEmpty(organizationName)) {
							organizationName = organization.getOrganizationId();
						}
						%>
					<option value="<%=organization.getOrganizationId()%>" <% if (oid.equals(organization.getOrganizationId())) {%>selected<%}%>><%=organizationName%></option>
		<%		}%>
				<tsa:hidden name="oid" value="<%=oid%>"/></td>
			</td>
		<%	} else {
			Organization organization = (Organization) organizationList.get(0);
		%>
			<td colspan=2><tsa:hidden name="oid" value="<%=organization.getOrganizationId()%>"/></td>
		<%	}%>
			<td width=380px rowspan=3>If your company is not registered, you may search and view solicitations as a "Guest." However,
		all suppliers wishing to bid on any procurement opportunities must register their company. Registration also allows companies to
		view and download standard terms and conditions of purchase.</td>
		</tr>
		<tr>
			<td width=125px align=right><B>Login Id:<B></td>
			<td width=175px><input type=text name="uid" value="<%=userId%>" tabindex=2 size=35 maxLength=100 onfocus="setFocus('userid');" onBlur="setFocus('');"></td>
		</tr>
		<tr>
			<td align=right><B>Password:</B></td>
			<td><input type=password name="password" autocomplete="off" value="" tabindex=3 size=20 maxLength=15 taborder=2 onfocus="setFocus('password');" onBlur="setFocus('');" ></td>
		</tr>
		<tr><td rowspan=4 valign=middle>&nbsp;</td></tr>
		<tr>
			<td><a href="javascript: submitLogin(); void(0);" border=0 tabindex=4><img tabindex=4 class=button src="<%=contextPath%>/supplierportal/images/button_login.gif" align=bottom border=0 alt="Login"></a></td>
			<td>
				<br><b>Registered Users:</b>
				<br>Please enter your Login Id and password, then click &quot;Login.&quot;
				<a href="javascript: passwordReminder(); void(0);">Forgot your password?</a>
			</td>
		</tr>
		<tr>
			<td><a href="javascript: register(); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_register.gif" tabindex=5 alt="Self-Registration" border=0></a></td>
			<td>
				<br><b>Non-Registered Users:</b>
				<br>Please Self-Register to obtain your Login Id and password.  This option requires your Customer Id.
			</td>
		</tr>
		<%	String showselfguestportal = DictionaryManager.getInstance("logon", DictionaryManager.getInstance("host", null).getProperty("instance-oid-installed", "")).getProperty("showselfguestportal", "Y");
		if (showselfguestportal.equals("Y"))
		{ %>
		<tr>
			<td><a href="javascript: loginAsGuest(); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_guest.gif" tabindex=6 alt="Guest Login" border=0></a></td>
			<td>
				<br><b>Guest Users:</b>
				<br>Click &quot;Guest&quot; to view solicitations as a guest user.
			</td>
		</tr>
		<% }%>
		</table>
	</td>
</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr><td><br></td></tr>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>

</FORM>

</BODY>
</HTML>
<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	frm.organizationId.value = "";
	if (frm.oidOptions) {
		frm.oidOptions.focus();
	}

	var displayWarning = false;
	var theFocus = "";
	netscape  = "";
	ver	  = navigator.appVersion;
	len	  = ver.length;

	for (iln = 0; iln < len; iln++) {
		if (ver.charAt(iln) == "(") break;
	}

	netscape = (ver.charAt(iln+1).toUpperCase() != "C");

	document.onkeydown = keyDown; // work together to analyze keystrokes

	if (netscape) document.captureEvents(Event.KEYDOWN|Event.KEYUP);

	function keyDown(DnEvents) { // handles keypress
		// determines whether Netscape or Internet Explorer
		k = (netscape) ? DnEvents.which : window.event.keyCode;
		if ( (k == 13) && ( (theFocus == 'password') || (theFocus == 'userid') ) ){
			// enter key pressed
			// check for focus so form isn't submitted twice
			submitLogin();
		}
	}

	function validateForm() {
		if (frm.handler.value.indexOf("VendorLogin") >= 0) {
			if (frm.oidOptions) {
				 frm.oid.value = frm.oidOptions[frm.oidOptions.selectedIndex].value.toUpperCase();
			}
			if (isEmpty(frm.oid.value)) {
				alert("You must enter an Organization Id.");
				frm.oid.focus();
				return false;
			}
			else if ( (isEmpty(frm.uid.value) || isEmpty(frm.password.value)) && frm.userId.value != "BB-GUEST" ) {
				alert("You must enter your Login Id and Password!");
				return false;
			}

			if (displayWarning) {
				var msg = "This version of Netscape is supported, but for optimum performance Netscape 6.0 is recommended.";
				if (!confirm(msg)) {
					return false;
				}
			}
		}
//		frm.organizationId.value = frm.oid.value;
		return true;
	}

	function setFocus(x) {
		theFocus = x;
	}

	function browserSupport() {
		var supported = false;
		var bw = browserTest();
 		var agent = navigator.userAgent.toLowerCase();
		var ie		= (agent.indexOf("msie") != -1);
		var netscape	= (agent.indexOf("netscape") != -1);
		var aol		= (agent.indexOf("aol") != -1);
		var opera	= (agent.indexOf("opera") != -1);
		var webtv	= (agent.indexOf("webtv") != -1);
		var version;
		var ind1;
		var ind2;


		if ( aol || opera || webtv ) {
			supported = false;
		}
		else if ( ie ) {
			ind1 = agent.indexOf("msie") + 4;
			ind2 = (agent.substring(ind1, agent.length)).indexOf(";");
			version = agent.substring(ind1, ind1 + ind2);
			version = parseFloat(version);

			if (version >= 4.6) {
				supported = true;
			}
		}
		else if ( netscape ) {
			if (agent.indexOf("netscape6") != -1) {
				ind1 = agent.indexOf("netscape6/") + 10;
			}
			else if (agent.indexOf("netscape/") != -1) {
				ind1 = agent.indexOf("netscape/") + 9;
			}

			if (ind1 > 0) {
				ind2 = agent.length;

				version = agent.substring(ind1, ind2);
			}
			else {
				version = "0.0";
			}

			version = parseFloat(version);

			if (version >= 6.0) {
				supported = true;
			}
		}
		else if ( bw.toLowerCase() == "netscape" ) {
			var dom = document.getElementById?true:false;

			version = navigator.appVersion;

			if (dom) {
				netscape = (parseFloat(version) >= 5.0) ?true:false;
			}
			else if (document.layers) {
				netscape = (parseFloat(version) >= 4.7) ?true:false;
				displayWarning = (parseFloat(version) >= 4.7 && parseFloat(version) < 6.0) ?true:false;
			}
			else {
				netscape = false;
			}

			if (netscape) {
				supported = true;
			}
		}

		if ( !supported ) {
			frm.action = "<%=contextPath%>/supplierportal/no_support.jsp";
			frm.submit();
		}
	}

	function register() {
		doSubmit('user/self_registration.jsp', 'DoNothing');
	}

	function submitLogin() {
		frm.userId.value = frm.uid.value;
//		frm.organizationId.value = frm.oid.value;
		doSubmit('menu/main_menu.jsp', 'VendorLogin');
	}

	function loginAsGuest() {
		if (frm.oidOptions) {
			 frm.oid.value = frm.oidOptions[frm.oidOptions.selectedIndex].value.toUpperCase();
		}
		frm.userId.value = "BB-GUEST";
		frm.organizationId.value = frm.oid.value;

		var newInputField = "<input type='hidden' name='StdDocument_docType' value='<%=StdDocumentType.STANDARD_DOCUMENT%>'>";
		setHiddenFields(newInputField);

		doSubmit('user/terms_acceptance.jsp', 'StdDocumentRetrieveBy');
	}

	function passwordReminder() {
		if (frm.oidOptions) {
			 frm.oid.value = frm.oidOptions[frm.oidOptions.selectedIndex].value.toUpperCase();
		}
		doSubmit('user/pswd_help.jsp', 'DoNothing');
	}

	function thisLoad() {
		return;
	}
//-->
</SCRIPT>

<%@ include file="/supplierportal/system/prevent_caching.jsp" %>