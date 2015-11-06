<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	Map urlproperty = DictionaryManager.getInstance("oidurl", "PURIDIOM").getPropertyMap();
	Iterator it = urlproperty.keySet().iterator() ;
	String coid="PURIDIOM";
	while (it.hasNext()) {
		String ikey = (String) it.next();
		String itxt = (String) urlproperty.get(ikey);
		if (requestURLPath.indexOf(itxt)>0){
			coid = ikey;
		}
	}
	coid = coid.toUpperCase();
	String ckoid = (String) request.getAttribute("oid");
	if ((ckoid == null || ckoid.trim().equals("")) && coid != null && !coid.equals("PURIDIOM")) {
		request.setAttribute("organizationId", coid);
	}
%>
<%@ include file="/system/header.jsp" %>
<%
	//RR 17/07/2008 - link coming from another url
	String urlLoginId = (String)request.getParameter("urlLoginId");
	String srchcatalog  = (String)request.getParameter("srchcatalog");
	String srchcatalogoid  = (String)request.getParameter("oid");
	String shopping  = (String)request.getParameter("shopping");
	PropertiesManager propertiesManger = PropertiesManager.getInstance(coid);
	String selfRegister = propertiesManger.getProperty("LOGIN","SHOWSELFREGISTER","Y");
	String passwordLink = propertiesManger.getProperty("LOGIN", "SHOWFORGOTPASSWORDLINK", "Y");
	String rememberMe = propertiesManger.getProperty("LOGIN", "REMEMBERME", "Y");
	String edoc = (String) request.getParameter("edoc");
	String etype = (String) request.getParameter("etype");
	

	if (srchcatalog == null) srchcatalog = "" ;
	if (srchcatalog.equalsIgnoreCase("null")) srchcatalog = "" ;
	if (shopping == null) shopping = "" ;
	if (shopping.equalsIgnoreCase("null")) shopping = "" ;

	String	errorMsg = (String) request.getAttribute("errorMsg");
	String	loginId = (String) request.getAttribute("loginId");
	String	puridiomSessionId = (String) request.getAttribute("puridiomSessionId");
	String	puridiomLdapId = (String) request.getAttribute("verifyLDAPAuthentication");
	String  remoteUser = (String) request.getParameter("remoteUser") ;

	boolean autoSignOn = ! HiltonUtility.isEmpty(remoteUser) ;
	if (autoSignOn) {
		loginId = remoteUser ;
	}
//	System.out.println("Auto=" + autoSignOn) ;
//	System.out.println("remoteUser=" + remoteUser) ;

	errorMsg = HiltonUtility.ckNull(errorMsg);
	loginId = HiltonUtility.ckNull(loginId);
	puridiomSessionId = HiltonUtility.ckNull(puridiomSessionId);
	puridiomLdapId = HiltonUtility.ckNull(puridiomLdapId);

	if(contextPath.indexOf("servicios") > 0) {
		oid="B2B";
	}
	else if (isXpress) {
		oid = "PURIDIOMX";
	} else {
		oid = "PURIDIOM";
	}

	List organizationList = OrganizationManager.getInstance().getOrganizationList(true);
	String expURL = "(http|https)://my.*";

	String currentOid="PURIDIOM";

	if(requestURLPath.matches(expURL)){
		currentOid = requestURLPath.substring(7,10).toString();
	}
	if (organizationList == null) {
		organizationList = new ArrayList();
	}

	String organizationId="PURIDIOM";
	for (int i = 0; i < organizationList.size(); i++) {
		Organization organization = (Organization) organizationList.get(i);
		organizationId = organization.getOrganizationId();
		if( organizationId.indexOf(currentOid.toUpperCase()) == 0 ){
			currentOid=organizationId;
			break;
		}
	}

	if (currentOid.equals("PURIDIOM") && isXpress) {
		currentOid = "PURIDIOMX";
	}

java.util.Enumeration names = request.getParameterNames();
        while (names.hasMoreElements()) {
          String name = (String) names.nextElement();
          if (name.startsWith("SSO_")) {
%>
<tsa:hidden name="<%=name%>" value="<%=request.getParameter(name)%>"/>
<%
          }
        }
	String label = DictionaryManager.getLabelsInstance(currentOid, language).getLabel(currentOid, "loginId", "Login ID");
%>

<tsa:hidden name="puridiomSessionId" value="<%=puridiomSessionId%>"/>
<tsa:hidden name="epmc" value='<%= TokenProcessor.getInstance().generateToken(request, "")%>'/>
<tsa:hidden name="loginFailurePage" value="index.jsp"/>
<tsa:hidden name="finalAttemptPage" value="user/pswd_help_verification.jsp"/>
<tsa:hidden name="passwordResetPage" value="user/chg_pswd.jsp"/>
<tsa:hidden name="passwordSecurityResetPage" value="user/chg_security_pswd.jsp"/>
<tsa:hidden name="securityResetPage" value="user/chg_security_profile.jsp"/>
<tsa:hidden name="reviewProfilePage" value="user/user_profile.jsp"/>
<tsa:hidden name="srchcatalog" value="${srchcatalog}"/>
<tsa:hidden name="edoc" value="${edoc}"/>
<tsa:hidden name="etype" value="${etype}"/>

<div style="width: 650px; margin-left: 20px; margin-top: 10px"><b><%=DictionaryManager.getLabelsInstance(coid, language).getLabel(coid, "welcometitle", "Welcome to Puridiom 4.0")%></b><br><br><%=DictionaryManager.getLabelsInstance(coid, language).getLabel(coid, "welcomeMessage", "Experience the Power of Puridiom 4.0 On-Demand Purchasing.  Create and award purchase orders, capture, analyze, and track spend and purchasing activity.  Available only to registered users.")%></div>

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
<tr>
	<td width=100% valign=top>
		<table border=0 width=100% align=left>
		<tr>
			<td width=80px>&nbsp;</td>
			<td width=200px>&nbsp;</td>
			<td width=400px>&nbsp;</td>
		</tr>
		<tr>
			<td colspan=3 class=error align=center>&nbsp;<%=errorMsg%></td>
		</tr>
		<tr>
			<td align=right nowrap><B><%=DictionaryManager.getLabelsInstance(currentOid, language).getLabel(currentOid, "loginId", "Login ID")%>:<B></td>
			<td><input type="text" name="loginId" value="<%=headerEncoder.encodeForHTMLAttribute(loginId)%>" tabindex="1" size="35" onfocus="setFocus('loginId');" onblur="setFocus('');" onchange="lowerCase(this); trim(this); frm.mailId.value=this.value;"/></td>
			<td rowspan=2>
				<%=DictionaryManager.getLabelsInstance(currentOid, language).getLabel(currentOid, "logintxt1", "Enter your Login ID and Password to begin.")%><br>
				<%=DictionaryManager.getLabelsInstance(currentOid, language).getLabel(currentOid, "logintxt2", "")%>
			</td>
		</tr>
		<tr>
			<td align=right><B><%=DictionaryManager.getLabelsInstance(currentOid, language).getLabel(currentOid, "password", "Password")%>:</B></td>
			<td><input type=password name="password" autocomplete="off" value="" tabindex="2" size=35 maxLength=35 taborder=2 onfocus="setFocus('password');" onBlur="setFocus('');"></td>
		</tr>
		<% if ("Y".equals(rememberMe)) { %>
			<tr>
				<td>&nbsp;</td>
				<td valign=middle>
					<table border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td><input type=checkbox name="rememberMe">&nbsp;</td>
						<td><%=DictionaryManager.getLabelsInstance(currentOid, language).getLabel(currentOid, "rememberloginid", "Remember my Login ID")%>.</td>
					</tr>
					</table>
				</td>
				<td valign=middle><%=DictionaryManager.getLabelsInstance(currentOid, language).getLabel(currentOid, "storeid", "Selecting this will store your " + label + " on your computer")%>.</td>
			</tr>
		<% } %>
	<%	//String showforgotpasswordlink = DictionaryManager.getInstance("logon", DictionaryManager.getInstance("host", null).getProperty("instance-oid-installed", "")).getProperty("showforgotpasswordlink", "Y");
		if (passwordLink.equalsIgnoreCase("Y"))
		{
	%>
		<% if (HiltonUtility.isEmpty(puridiomLdapId) && !(currentOid.equalsIgnoreCase("wpc08p"))) { %>
			<tr>
				<td>&nbsp;</td>
				<td><a href="javascript: passwordReminder(); void(0);"><font style="background-color: yellow""><b><%=DictionaryManager.getLabelsInstance(currentOid, language).getLabel(currentOid, "forgotpassword", "Forgot your password")%>?&nbsp; <%=DictionaryManager.getLabelsInstance(currentOid, language).getLabel(currentOid, "click", "Click HERE")%>.</b></font></a></td>
				<td>&nbsp;</td>
			</tr>
		<%} %>
	<%	}
	%>
		<tr><td rowspan=3 valign=middle>&nbsp;</td></tr>
		<tr>
			<td>
				<div id="pxbutton">
	            	<a href="javascript: submitLogin(); void(0);" tabindex=3><%=DictionaryManager.getLabelsInstance(currentOid, language).getLabel(currentOid, "index-button-login", "login") %></a>
				</div>
			</td>
			<td>
				<span <%=HtmlWriter.isVisible(currentOid, "registeredusers")%>><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(currentOid, "registeredusers", "Registered Users")%>:</b></span>
				<span <%=HtmlWriter.isVisible(currentOid, "enterlogin")%>>
				<% if (!currentOid.equalsIgnoreCase("wpc08p")){%>
					<br><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(currentOid, "enterlogin", "Please enter your Login ID and Password, then click &quot;Login.&quot;")%><br>
				<%} else {%>
				<br><%=DictionaryManager.getLabelsInstance(currentOid, language).getLabel(currentOid, "enterlogin", "Please enter your " + label + " and Password, then click &quot;login.&quot;")%><br>
				<%} %>
				</span>
			</td>
		</tr>
	<% if (selfRegister.equalsIgnoreCase("Y"))
		{
			if (!(currentOid.equalsIgnoreCase("wpc08p")) && !(currentOid.equalsIgnoreCase("msg07p")) && HiltonUtility.isEmpty(puridiomLdapId)){%>
		<tr <%=HtmlWriter.isVisible(currentOid, "index-button-register")%>>
			<td>
				<div id="pxbutton">
	            	<a href="javascript: register(); void(0);" tabindex=4><%=DictionaryManager.getLabelsInstance(currentOid, language).getLabel(currentOid, "index-button-register", "register") %></a>
				</div>
			</td>
			<td>
				<b><%=DictionaryManager.getLabelsInstance(currentOid, language).getLabel(currentOid, "nonregisteredusers", "Not Registered?")%></b>
				<br><%=DictionaryManager.getLabelsInstance(currentOid, language).getLabel(DictionaryManager.getInstance("host", null).getProperty("instance-oid-installed", ""), "nonregistereduserstxt", "Please Self-Register to obtain your Login ID and Password. This option requires your Customer ID. ")%>
			</td>
		</tr>
		<%	}
		} %>
		</table>
	</td>
</tr>
</table>

<%// oid = DictionaryManager.getInstance("host", null).getProperty("instance-oid-installed", ""); %>
<%@ include file="/system/footer.jsp" %>
<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	hideArea("navTable");

	var displayWarning = false;
	var theFocus = "";
	var ruser = "${esapi:encodeForJavaScript(remoteUser)}" ;
	var autoSignOn = "<%=autoSignOn%>" ; <%-- autoSignOn is a boolean value, so not a problem for XSS --%>

	netscape  = "";
	ver	  = navigator.appVersion;
	len	  = ver.length;
	if (frm.userTokenId){
		frm.userTokenId.value = "" ;
	}
	frm.userId.value = "";
	frm.organizationId.value = "";
	frm.mailId.value = "${esapi:encodeForJavaScript(loginId)}";
	

	for (iln = 0; iln < len; iln++) {
		if (ver.charAt(iln) == "(") break;
	}

	netscape = (ver.charAt(iln+1).toUpperCase() != "C");

	document.onkeydown = keyDown; // work together to analyze keystrokes

	if (netscape) document.captureEvents(Event.KEYDOWN|Event.KEYUP);

	if (autoSignOn == "true") {
		submitLogin() ;
	}

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
		frm.loginId.value = '${esapi:encodeForJavaScript(urlLoginId)}';
		frm.mailId.value = '${esapi:encodeForJavaScript(urlLoginId)}';
		frm.password.value = '<%="hilton3x01"%>';

		var pg = "";
		var handlers = 'UserLogin';
		var catalog = '${esapi:encodeForJavaScript(shopping)}';
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
		frm.loginId.value = '${esapi:encodeForJavaScript(urlLoginId)}';
		frm.mailId.value = '${esapi:encodeForJavaScript(urlLoginId)}';

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
		var catalog = '${esapi:encodeForJavaScript(shopping)}';
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
			if ( autoSignOn == "false") {
				if ( isEmpty(frm.loginId.value) || isEmpty(frm.password.value) ) {
					alert("You must enter your Login ID and Password!");
					return false;
				}
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
<%	if (isXpress) {%>
		doSubmit('user/registration.jsp', 'DoNothing');
<%	} else {%>
		doSubmit('user/prompt_organization.jsp', 'DoNothing');
<%	}%>
	}

	function submitLogin() {
		var largeExpDate = new Date ();
		largeExpDate.setTime(largeExpDate.getTime() + (365 * 24 * 3600 * 1000));

		<% if ("Y".equals(rememberMe)) { %>
			if (frm.rememberMe.checked == true)
			{
				setCookie("loginid", frm.loginId.value, largeExpDate);
			}
			else
			{
				setCookie("loginid", "", largeExpDate);
			}
		<% } %>

		if (frm.UserLog_status)
			frm.UserLog_status.value = "<%=DocumentStatus.USERLOG_SUCCESSFUL_LOGIN%>";

		<% if (!HiltonUtility.isEmpty(srchcatalog))
		{%>
			autoNoUserCatalogSearch();
		<%}
		else if(!HiltonUtility.isEmpty(shopping))
		{%>
			autoNoUserShoppingSearch();
		<%}
		else if(HiltonUtility.ckNull(etype).equals("PO") && !HiltonUtility.isEmpty(edoc))
		{%>
			autoPoReview();
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
		<% if ("Y".equals(rememberMe)) { %>
			var storedLoginId = getCookie('loginid');
			if (!isEmpty(storedLoginId)) {
				frm.rememberMe.checked = true;
				frm.loginId.value = storedLoginId;
				frm.mailId.value = storedLoginId;
			}
		<% } %>
		return;
	}

	function setCookie(name, value, expires)
	{
		<% if (true == request.isSecure()) { %>
			var curCookie = name + "=" + escape(value) + "; secure=true" +
			((expires) ? "; expires=" + expires.toGMTString() : "");
		<% } else { %>
			var curCookie = name + "=" + escape(value) +
			((expires) ? "; expires=" + expires.toGMTString() : "");
		<% } %>
		document.cookie = curCookie;
	}

	function getCookie(name)
	{
		var result = null;
		var myCookie = " " + document.cookie + ";";
		var searchName = " " + name + "=";
		var startOfCookie = myCookie.indexOf(searchName);
		var endOfCookie;
		if (startOfCookie != -1)
		{
			startOfCookie += searchName.length;
			// skip past cookie name
			endOfCookie = myCookie.indexOf(";", startOfCookie);
			result = unescape(myCookie.substring(startOfCookie,endOfCookie));
		}
		return result;
	}

	function autoPoReview() {
		var newInputField = "<input type=hidden name='PoHeader_icPoHeader' value='${esapi:encodeForJavaScript(edoc)}'>";
		setHiddenFields(newInputField);

		doSubmit('orders/po_review.jsp', 'UserLogin;PoRetrieve');
	}
//-->
</SCRIPT>
