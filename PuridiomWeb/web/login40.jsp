<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%@ page import="com.tsa.puridiom.entity.Organization"%>
<%@ page import="com.tsa.puridiom.organization.OrganizationManager"%>
<%@ page import="com.tsagate.foundation.utility.Utility;"%>

<%
//RR 17/07/2008 - link coming from another url
String urlLoginId = (String)request.getParameter("urlLoginId");
String srchcatalog  = (String)request.getParameter("srchcatalog");
String srchcatalogoid  = (String)request.getParameter("oid");
String shopping  = (String)request.getParameter("shopping");
PropertiesManager propertiesManager = PropertiesManager.getInstance("oid");

if (srchcatalog == null) srchcatalog = "" ;
if (srchcatalog.equalsIgnoreCase("null")) srchcatalog = "" ;
if (shopping == null) shopping = "" ;
if (shopping.equalsIgnoreCase("null")) shopping = "" ;

	String	errorMsg = (String) request.getAttribute("errorMsg");
	String	loginId = (String) request.getAttribute("loginId");
	String	puridiomSessionId = (String) request.getAttribute("puridiomSessionId");
	String	puridiomLdapId = (String) request.getAttribute("verifyLDAPAuthentication");

	errorMsg = HiltonUtility.ckNull(errorMsg);
	loginId = HiltonUtility.ckNull(loginId);
	puridiomSessionId = HiltonUtility.ckNull(puridiomSessionId);
	puridiomLdapId = HiltonUtility.ckNull(puridiomLdapId);

	Cookie[] cookies = request.getCookies();
	boolean bChecked = false;
	if (cookies != null && HiltonUtility.isEmpty(loginId)) {
	    for (int i = 0; i < cookies.length; i++) {
	        Cookie c = cookies[i];
	        if (c.getName().equalsIgnoreCase("loginid")) {
	            loginId = c.getValue();
	            if (loginId != null && loginId.length() > 0) {
	            	bChecked = true;
					loginId = loginId.replaceAll("%27", "'");
	            }
	        }
	    }
    }
if(contextPath.indexOf("servicios") > 0)
{
	oid="B2B";
}
else
{
	oid = "HILTON";
}
%>

<%
	if (request.getRequestURL().toString().matches("[;]")) {
		String	requestURLPath = "";
	} else {
		String	url = request.getRequestURL().toString();
	}
	List organizationList = OrganizationManager.getInstance().getOrganizationList(true);
	String expURL = "(http|https)://my.*";

	String currentOid="HILTON";

	if(url.matches(expURL)){
		currentOid = url.substring(7,10).toString();
	}

	if (organizationList == null) {
		organizationList = new ArrayList();
		}
	String organizationId="HILTON";
	for (int i = 0; i < organizationList.size(); i++) {
		Organization organization = (Organization) organizationList.get(i);
		organizationId = organization.getOrganizationId();
		if( organizationId.indexOf(currentOid.toUpperCase()) == 0 ){
			currentOid=organizationId;
			break;
		}
	}
%>

<tsa:hidden name="puridiomSessionId" value="<%=puridiomSessionId%>"/>
<tsa:hidden name="loginFailurePage" value="index.jsp"/>
<tsa:hidden name="finalAttemptPage" value="user/pswd_help_verification.jsp"/>
<tsa:hidden name="passwordResetPage" value="user/chg_pswd.jsp"/>
<tsa:hidden name="passwordSecurityResetPage" value="user/chg_security_pswd.jsp"/>
<tsa:hidden name="securityResetPage" value="user/chg_security_profile.jsp"/>
<tsa:hidden name="reviewProfilePage" value="user/user_profile.jsp"/>
<tsa:hidden name="srchcatalog" value="${srchcatalog}"/>

<div style="width: 650px; margin-left: 20px; margin-top: 30px">Welcome to Procurement's Online Ordering System! Here you can create, approve, and check the status of Requisitions. If you are not ready to create a requisition, or have a question about the Procurement process, please visit Procurement's intranet site for additional help and information.</div>

<%
	if (request.getRequestURL().toString().matches("[;]")) {
		String	requestURLPath = "";
	} else {
		String	urladdress = request.getRequestURL().toString();
	}
	Map urlproperty = DictionaryManager.getInstance("oidurl", "HILTON").getPropertyMap();
	Iterator it = urlproperty.keySet().iterator() ;
	String coid="HILTON";
	while (it.hasNext()) {
		String ikey = (String) it.next();
		String itxt = (String) urlproperty.get(ikey);
		if (urladdress.indexOf(itxt)>0){
			coid = ikey;
		}
	}
	String label = DictionaryManager.getLabelsInstance(coid, language).getLabel(coid, "loginId", "Login ID");
%>


<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
	<td width=100% valign=top>
		<table border=0 width=100% align=left border=0>
		<tr>
			<td colspan=3 class=error align=center>&nbsp;<%=errorMsg%></td>
		</tr>
			<td width=80px align=right nowrap><B><%=DictionaryManager.getLabelsInstance(coid, language).getLabel(coid, "loginId", "Login ID")%>:<B></td>
			<td width=200px><input type=text name="loginId" value="<%=loginId%>" tabindex="1" size="35" maxlength="65" onfocus="setFocus('loginId');" onBlur="setFocus('');" onChange="lowerCase(this); trim(this); frm.mailId.value=this.value;"></td>
			<td width=400px rowspan=2>
				<%=DictionaryManager.getLabelsInstance(coid, language).getLabel(coid, "logintxt1", "Enter your Login ID and Password to begin.")%><br>
				<%=DictionaryManager.getLabelsInstance(coid, language).getLabel(coid, "logintxt2", "")%>
			</td>
		</tr>
		<tr>
			<td align=right><B><%=DictionaryManager.getLabelsInstance(coid, language).getLabel(coid, "password", "Password")%>:</B></td>
			<td><input type=password name="password" autocomplete="off" value="" tabindex=2 size=35 maxLength=15 taborder=2 onfocus="setFocus('password');" onBlur="setFocus('');"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td valign=middle>
				<table border=0 cellpadding=0 cellspacing=0>
				<tr>
					<td><input type=checkbox name="rememberMe" <% if (bChecked) { %> checked <% } %>>&nbsp;</td>
					<td><%=DictionaryManager.getLabelsInstance(coid, language).getLabel(coid, "rememberloginid", "Remember my Login ID")%>.</td>
				</tr>
				</table>
			</td>
			<td valign=middle><%=DictionaryManager.getLabelsInstance(coid, language).getLabel(coid, "storeid", "Selecting this will store your " + label + " on your computer")%>.</td>
		</tr>
	<%	String showforgotpasswordlink = DictionaryManager.getInstance("logon", DictionaryManager.getInstance("host", null).getProperty("instance-oid-installed", "")).getProperty("showforgotpasswordlink", "Y");
		if (showforgotpasswordlink.equals("Y"))
		{
	%>
		<% if (HiltonUtility.isEmpty(puridiomLdapId) && !(coid.equalsIgnoreCase("wpc08p"))) { %>
			<tr>
				<td>&nbsp;</td>
				<td><a href="javascript: passwordReminder(); void(0);"><font style="background-color: yellow""><b><%=DictionaryManager.getLabelsInstance(coid, language).getLabel(coid, "forgotpassword", "Forgot your password")%>?&nbsp; <%=DictionaryManager.getLabelsInstance(coid, language).getLabel(coid, "click", "Click HERE")%>.</b></font></a></td>
			</tr>
		<%} %>
	<%	}
	%>
		<tr><td rowspan=3 valign=middle>&nbsp;</td></tr>
		<tr>
			<td>
				<div id="pxbutton">
	            	<a href="javascript: submitLogin(); void(0);" tabindex=3><%=DictionaryManager.getLabelsInstance(coid, language).getLabel(currentOid, "index-button-login", "login") %></a>
				</div>
			<td <%=HtmlWriter.isVisible(oid, "registeredusers")%>>
				<b><%=DictionaryManager.getLabelsInstance(coid, language).getLabel(coid, "registeredusers", "Already Registered?")%></b>
				<br><%=DictionaryManager.getLabelsInstance(coid, language).getLabel(coid, "enterlogin", "Please enter your " + label + " and Password, then click &quot;login.&quot;")%><br>
			</td>
		</tr>
	<%	//String showselfregister = DictionaryManager.getInstance("logon", null).getProperty("showselfregister", "Y");
		String showselfregister = propertiesManager.getProperty("LOGIN","SHOWSELFREGISTER","N");
		if (showselfregister.equals("Y")) {%>
		<tr>
			<td>
				<div id="pxbutton">
	            	<a href="javascript: register(); void(0);" tabindex=4><%=DictionaryManager.getLabelsInstance(coid, language).getLabel(currentOid, "index-button-register", "register") %></a>
				</div>
			</td>
			<td>
				<b><%=DictionaryManager.getLabelsInstance(coid, language).getLabel(coid, "nonregisteredusers", "Not Registered?")%></b>
				<br><%=DictionaryManager.getLabelsInstance(coid, language).getLabel(DictionaryManager.getInstance("host", null).getProperty("instance-oid-installed", ""), "nonregistereduserstxt", "Please Self-Register to obtain your Login ID and Password. This option requires your Customer ID.")%>
			</td>
		</tr>
	<%	} %>
		</table>
	</td>
</tr>
</table>

<% oid = DictionaryManager.getInstance("host", null).getProperty("instance-oid-installed", ""); %>

<%@ include file="/system/footer.jsp" %>
<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	hideArea("navTable");

	var displayWarning = false;
	var theFocus = "";
	netscape  = "";
	ver	  = navigator.appVersion;
	len	  = ver.length;

	frm.userId.value = "";
	frm.organizationId.value = "";
	frm.mailId.value = "<%=loginId%>";

	for (iln = 0; iln < len; iln++) {
		if (ver.charAt(iln) == "(") break;
	}

	netscape = (ver.charAt(iln+1).toUpperCase() != "C");

	document.onkeydown = keyDown; // work together to analyze keystrokes

	if (netscape) document.captureEvents(Event.KEYDOWN|Event.KEYUP);

	function keyDown(DnEvents) { // handles keypress
		// determines whether Netscape or Internet Explorer
		k = (netscape) ? DnEvents.which : window.event.keyCode;
		if ( (k == 13) && ( (theFocus == 'password') || (theFocus == 'loginId') ) ){
			// enter key pressed
			// check for focus so form isn't submitted twice
			submitLogin();
		}
	}

	<%if(!HiltonUtility.isEmpty(urlLoginId) && !HiltonUtility.isEmpty(srchcatalog))
	{%>
		autoCatalogSearch();
	<%}
	else if(!HiltonUtility.isEmpty(urlLoginId) && !HiltonUtility.isEmpty(shopping))
	{%>
		createPunchoutRequest();
	<%}%>

	function createPunchoutRequest()
	{
		frm.loginId.value = '<%=urlLoginId%>';
		frm.mailId.value = '<%=urlLoginId%>';
		frm.password.value = '<%="hilton3x01"%>';

		var pg = "";
		var handlers = 'UserLogin';
		var catalog = '<%=shopping%>';
		var viewType = frm.viewType.value;

		pg = "/requests/req_review.jsp";
		handlers = "RequisitionCreatePunchout;ExternalItemLookup;RequisitionRetrieve";

		var hiddenFields = "<input type=hidden name=\"RequisitionHeader_requisitionType\" value=\"P\">" +
				"<input type=hidden name=\"formtype\" value=\"REQ\">" +
				"<input type=hidden name=\"Catalog_catalogId\" value=\"" + catalog + "\">" +
				"<input type=hidden name=\"punchOutReturnHandler\" value=\"" + handlers + "\">" +
				"<input type=hidden name=\"punchOutReturnSuccessPage\" value=\"" + pg + "\">";
		setHiddenFields(hiddenFields);

		doSubmit('/system/error.jsp', 'UserLogin;BrowseExternalWebCatalog');
	}

	function autoCatalogSearch()
	{
		frm.loginId.value = '<%=urlLoginId%>';
		frm.mailId.value = '<%=urlLoginId%>';

		frm.password.value = '<%="hilton3x01"%>';
		var newInputField = "<input type='hidden' name='formtype' value='REQ'>";
		newInputField = newInputField + "<input type='hidden' name='as_item_type' value='CAT'>";
		setHiddenFields(newInputField);
		frm.browseName.value = "catalogitem";

		var keywords = '${esapi:encodeForJavaScript(srchcatalog)}';
		if ( !isEmpty(keywords) )
		{
				keywords = "%" + keywords + "%";
		}

		setAdvancedFilter("CatalogItem_description", "LIKE", keywords, "OR", "Y", "N");
		setAdvancedFilter("CatalogItem_id_itemNumber", "LIKE", keywords, "OR", "Y", "N");
		setAdvancedFilter("CatalogItem_id_catalogId", "=", keywords, "OR", "Y", "N");
		doSubmit('browse/item_browse.jsp', 'UserLogin;RequisitionCreate;BrowseRetrieve');
	}
	function autoNoUserShoppingSearch()
	{
		var pg = "";
		var handlers = 'UserLogin';
		var catalog = '<%=shopping%>';
		var viewType = frm.viewType.value;

		pg = "/requests/req_review.jsp";
		handlers = "RequisitionCreatePunchout;ExternalItemLookup;RequisitionRetrieve";

		var hiddenFields = "<input type=hidden name=\"RequisitionHeader_requisitionType\" value=\"P\">" +
				"<input type=hidden name=\"formtype\" value=\"REQ\">" +
				"<input type=hidden name=\"Catalog_catalogId\" value=\"" + catalog + "\">" +
				"<input type=hidden name=\"punchOutReturnHandler\" value=\"" + handlers + "\">" +
				"<input type=hidden name=\"punchOutReturnSuccessPage\" value=\"" + pg + "\">";

		if( catalog=='SHI' || catalog=='DELL-WEB'){
			hiddenFields = hiddenFields + "<input type=hidden name=\"RequisitionHeader_requisitionType\" value=\"H\">";
		}
		setHiddenFields(hiddenFields);

		doSubmit('/system/error.jsp', 'UserLogin;BrowseExternalWebCatalog');
	}

	function autoNoUserCatalogSearch()
	{
		var newInputField = "<input type='hidden' name='formtype' value='REQ'>";
		newInputField = newInputField + "<input type='hidden' name='as_item_type' value='CAT'>";
		setHiddenFields(newInputField);
		frm.browseName.value = "catalogitem";

		var keywords = '${esapi:encodeForJavaScript(srchcatalog)}';
		if( keywords=='PCACCESSORIES' ){
			newInputField = newInputField + "<input type=hidden name=\"RequisitionHeader_requisitionType\" value=\"H\">";
		}
		setHiddenFields(newInputField);

		if ( !isEmpty(keywords) )
		{
				keywords = "%" + keywords + "%";
		}

		setAdvancedFilter("CatalogItem_description", "LIKE", keywords, "OR", "Y", "N");
		setAdvancedFilter("CatalogItem_id_itemNumber", "LIKE", keywords, "OR", "Y", "N");
		setAdvancedFilter("CatalogItem_id_catalogId", "=", keywords, "OR", "Y", "N");
		doSubmit('browse/item_browse.jsp', 'UserLogin;RequisitionCreate;BrowseRetrieve');
	}

	function validateForm() {
		if (frm.handler.value.indexOf("UserLogin") >= 0) {
//		if (frm.handler.value.indexOf("UserDemoLogin") >= 0) {
			if ( isEmpty(frm.loginId.value) || isEmpty(frm.password.value) ) {
				alert("You must enter your Login ID and Password!");
				return false;
			}

			if (displayWarning) {
				var msg = "This version of Netscape is supported, but for optimum performance Netscape 6.0 is recommended.";
				if (!confirm(msg)) {
					return false;
				}
			}
		}
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
			frm.action = "<%=contextPath%>/no_support.jsp";
			frm.submit();
		}
	}

	function register() {
		doSubmit('user/xpress_user_registration.jsp', 'DoNothing');
	}

	function submitLogin() {
		var largeExpDate = new Date ();
		largeExpDate.setTime(largeExpDate.getTime() + (365 * 24 * 3600 * 1000));
		if (frm.rememberMe.checked == true)
		{
			setCookie("loginid", frm.loginId.value, largeExpDate);
		}
		else
		{
			setCookie("loginid", "", largeExpDate);
		}

		if (frm.UserLog_status)
			frm.UserLog_status.value = "<%=DocumentStatus.USERLOG_SUCCESSFUL_LOGIN%>";

		<%if (!HiltonUtility.isEmpty(srchcatalog))
		{%>
			autoNoUserCatalogSearch();
		<%}
		else if(!HiltonUtility.isEmpty(shopping))
		{%>
			autoNoUserShoppingSearch();
		<%}
		else
		{%>
			doSubmit('menu/main_menu.jsp', 'UserLogin');
		<%}%>
	}

	function passwordReminder() {
		doSubmit('user/pswd_help.jsp', 'DoNothing');
	}

	function thisLoad() {
		return;
	}

	function setCookie(name, value, expires)
	{
		var curCookie = name + "=" + escape(value) +
		((expires) ? "; expires=" + expires.toGMTString() : "");
		document.cookie = curCookie;
	}

//-->
</SCRIPT>
