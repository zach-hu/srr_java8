package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsa.puridiom.vendorregistration.VendorRegistrationUserManager;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.html.*;
import com.tsagate.properties.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tsa.puridiom.entity.Organization;
import com.tsa.puridiom.organization.OrganizationManager;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  static {
    _jspx_dependants = new java.util.Vector(6);
    _jspx_dependants.add("/system/prevent_caching.jsp");
    _jspx_dependants.add("/system/header.jsp");
    _jspx_dependants.add("/system/context_path.jsp");
    _jspx_dependants.add("/system/stylesheet_link.jsp");
    _jspx_dependants.add("/system/header_menu_options.jsp");
    _jspx_dependants.add("/system/footer.jsp");
  }

  public java.util.List getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"/system/error.jsp", true, 8192, true);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');

	/*****	PREVENT CACHING	 *****/

	response.setHeader("Cache-Control","no-cache");	//HTTP 1.1
	response.setHeader("Pragma","no-cache");	//HTTP 1.0
	response.setDateHeader ("Expires", 0);		//prevents caching at the proxy server

	/*****************************/

      out.write('\r');
      out.write('\n');
      out.write("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");

	String	contextPath = request.getContextPath(); 
	String	requestURLPath = request.getRequestURL().toString(); 
	
	if (com.tsa.puridiom.common.utility.HiltonUtility.isEmpty(contextPath)) {
		contextPath = "/puridiom/suppliers";
	}
	else {
		if (contextPath.endsWith("/")) {
			contextPath = contextPath.substring(0, contextPath.lastIndexOf("/"));
		}
		if (!com.tsa.puridiom.common.utility.HiltonUtility.isEmpty(requestURLPath)) {
			if (requestURLPath.indexOf(contextPath) > 0) {
				requestURLPath = requestURLPath.substring(0, requestURLPath.indexOf(contextPath) + contextPath.length());
			}
		}
	}
	if (com.tsa.puridiom.common.utility.HiltonUtility.isEmpty(requestURLPath)) {
		requestURLPath = contextPath;
	}

      out.write('\r');
      out.write('\n');

	String	userId = (String) request.getAttribute("userId");
	String	oid = (String) request.getAttribute("organizationId");
	RegisterUser	user = VendorRegistrationUserManager.getInstance().getUserInCache(oid, userId);

	if (oid == null) {
		oid = "";
	}
	if (userId == null) {
		userId = "";
	}
	if (user == null) {
		user = new RegisterUser();
	}

	String s_view = (String) request.getAttribute("viewType");
	if (HiltonUtility.isEmpty(s_view)) {
		s_view = "WIZARD";
	}

      out.write("\r\n\r\n<HTML>\r\n<HEAD>\r\n\t<meta http-equiv=\"content-type\" content=\"text/html;charset=iso-8859-1\">\r\n\t<META HTTP-EQUIV=\"Cache-Control\" content=\"no-cache\">\r\n\t<META HTTP-EQUIV=\"Expires\" CONTENT=\"Sat, 11 Sep 1971 12:00:00 GMT\">\r\n\t<META NAME=\"Description\" Content=\"");
      out.print(contextPath);
      out.write("/menu/menu_pg.jsp\">\r\n\t<SCRIPT LANGUAGE=\"JavaScript1.2\" SRC=\"");
      out.print(contextPath);
      out.write("/scripts/hilton.js\"></SCRIPT>\r\n\t<SCRIPT LANGUAGE=\"JavaScript1.2\" SRC=\"");
      out.print(contextPath);
      out.write("/scripts/filter.js\"></SCRIPT>\r\n\t<TITLE>Puridiom Supplier Portal</TITLE>\r\n\t");
      out.write("\t<LINK REL=STYLESHEET TYPE=\"text/css\" HREF=\"");
      out.print(contextPath);
      out.write("/system/hilton_default.css\">");
      out.write("\r\n</HEAD>\r\n<SCRIPT language=\"JavaScript1.2\">\r\n<!--  hide script from old browsers\r\n\r\n\tcontextPath = \"");
      out.print(contextPath);
      out.write("\";\r\n\r\n//-->\r\n</SCRIPT>\r\n\r\n<body marginwidth=0 marginheight=0 topmargin=0 leftmargin=0 width=680px onload=\"thisLoad();\" onunload=\"closeOpenWindows();\">\r\n<form name=\"purchaseform\" target=\"_parent\" action=\"");
      out.print(contextPath);
      out.write("/PuridiomSupplierPortalServletController\" method=\"POST\">\r\n<div id=\"filterFields\" style=\"display:none;\"></div>\r\n<div id=\"hiddenFields\" style=\"display:none;\"></div>\r\n\r\n<input type=HIDDEN name=handler value=\"\">\r\n<input type=HIDDEN name=successPage value=\"\">\r\n<input type=HIDDEN name=failurePage value=\"system/error.jsp\">\r\n<input type=HIDDEN name=userName value=\"");
      out.print(user.getDisplayName());
      out.write("\">\r\n<input type=HIDDEN name=userId value=\"");
      out.print(userId);
      out.write("\">\r\n<input type=HIDDEN name=organizationId value=\"");
      out.print(oid);
      out.write("\">\r\n<input type=HIDDEN name=browseName value=\"\">\r\n<input type=HIDDEN name=VendorRegister_contactEmailAddr value=\"");
      out.print(userId);
      out.write("\">\r\n<input type=HIDDEN name=Contact_emailAddr value=\"");
      out.print(userId);
      out.write("\">\r\n<input type=HIDDEN name=vendorId value=\"");
      out.print(user.getVendorId());
      out.write("\">\r\n\r\n");
      out.write("\r\n<table border=0 cellpadding=0 cellspacing=0 width=680px>\r\n<tr>\r\n\t<td><a href=\"http://www.puridiom.com\" target=\"_top\"><img src=\"");
      out.print(contextPath);
      out.write("/images/gcsclock.gif\"\talign=\"bottom\" alt=\"Purchasing Center Home\" border=0></a></td>\r\n\t<td valign=bottom>\r\n\t\t<table border=0 cellpadding=0 cellspacing=0 width=430px>\r\n\t\t\t<tr class=mrow>\r\n\t\t\t\t<th height=16px>&nbsp;</th>\r\n\t");
	if (user.isAuthenticated()) {
      out.write("\r\n\t\t\t\t<th align=center width=40px height=16px nowrap><a href=\"javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);\">Menu</a></th>\r\n\t\t\t\t<th align=center valign=middle width=1px height=16px class=separator>|</th>\r\n\t\t\t\t<!--th align=center width=40px height=16px nowrap><a href=\"javascript: helpMe(); void(0);\">Help</a>&nbsp;</th-->\r\n\t\t\t\t<!--th align=center valign=middle width=1px height=16px class=separator>|</th-->\r\n\t\t\t\t<th align=center width=60px height=16px nowrap><a href=\"javascript: logOff(); void(0);\">Log&nbsp;Off</a>&nbsp;</th>\r\n\t");
	}
      out.write("\r\n\t\t\t</tr>\r\n\t\t</table>\r\n\t</td>\r\n</tr>\r\n</table>\r\n\r\n<table border=0 cellpadding=0 cellspacing=0 width=680px>\r\n<tr><td class=mnav><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" border=0 height=1px width=100% class=mnav></td></tr>\r\n</table>\r\n\r\n");
	if (user.isAuthenticated()) {
      out.write("\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px height=22px>\r\n\t<tr class=mnav>\r\n\t\t<td nowrap align=center width=120px><a href=\"javascript: searchSales(); void(0);\" class=mnav>Surplus Items</a></td>\r\n\t\t<td nowrap align=center width=120px><a href=\"javascript: doSubmit('/browse/rfq_browse_filter_options.jsp', 'DoNothing'); void(0);\" class=mnav>Solicitations</a></td>\r\n\t");
	if (user.isQualified()) {
      out.write("\r\n\t\t<td nowrap align=center width=70px><a href=\"javascript: doSubmit('/browse/po_browse_filter_options.jsp', 'DoNothing'); void(0);\" class=mnav>Orders</a></td>\r\n\t\t<td nowrap align=center width=90px><a href=\"javascript: searchInvoices(); void(0);\" class=mnav>Invoices</a></td>\r\n\t\t<td nowrap align=center width=140px><a href=\"javascript: doSubmit('/user/profile.jsp', 'VendorContactRetrieveByEmail;VendorOptionsRetrieve');; void(0);\" class=mnav>Profile Information</a></td>\r\n\t");
	} else if (!user.isGuest()) {
      out.write("\r\n\t\t<td nowrap align=center width=150px><a href=\"javascript: doSubmit('/user/prequalification.jsp', 'VendorRegisterRetrieveByEmail;VendorOptionsRetrieve'); void(0);\" class=mnav>Pre-Qualification Process</a></td>\r\n\t");
	}
			if (!user.isGuest()) {
      out.write("\r\n\t\t<td nowrap align=center width=140px><a href=\"javascript: doSubmit('/documents/std_documents_menu.jsp', 'DoNothing'); void(0);\" class=mnav>Download Documents</a></td>\r\n\t");
	}
      out.write("\r\n\t</tr>\r\n</table>\r\n\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px>\r\n<tr valign=top>\r\n\t");
	if (!HiltonUtility.isEmpty(user.getDisplayName())) {
      out.write("\r\n\t<td nowrap align=left><font color=black size=1><b>&nbsp;Hello ");
      out.print(user.getDisplayName());
      out.write("!<br></b></font></td>\r\n\t");
	}
      out.write("\r\n\t<td nowrap align=right><font SIZE=-2><i>");
      out.print(HiltonUtility.getFormattedDate(new Date(), oid, "EEE, MMM d, yyyy hh:mm:ss z"));
      out.write("</i></font></td>\r\n</tr>\r\n</table>\r\n\r\n<hr size=0 color=black width=680px align=left>\r\n");
	} else {
      out.write("\r\n<br>\r\n");
	}
      out.write("\r\n\r\n<!--\r\nGuest - ");
      out.print(user.isGuest());
      out.write("<br>\r\nQualified - ");
      out.print(user.isQualified());
      out.write("<br>\r\nAuthenticated - ");
      out.print(user.isAuthenticated());
      out.write("\r\n-->");
      out.write('\r');
      out.write('\n');
      out.write("\r\n\r\n\r\n");

	if (userId.equalsIgnoreCase("BB-GUEST")) {
		userId = "";
	}
	String errorMsg = 	(String)request.getAttribute("errorMsg");

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
		organization.setOrganizationId("HILTON");
		organization.setOrganizationName("Hilton");
		organizationList.add(organization);
	}

      out.write("\r\n\r\n<input type=hidden name=\"loginFailurePage\" value=\"index.jsp\">\r\n<input type=hidden name=\"passwordResetPage\" value=\"/user/chg_pswd.jsp\">\r\n\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px>\r\n<tr>\r\n\t<td width=100% valign=top>\r\n\t\t<table border=0 width=680px align=left border=0>\r\n\t\t<tr>\r\n\t\t\t<td colspan=3 class=error align=center>&nbsp;");
      out.print(errorMsg);
      out.write("&nbsp;</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t");
	if (organizationList.size() > 1) {
      out.write("\r\n\t\t\t<td align=right><B>Organization:</B></td>\r\n\t\t\t<td>\r\n\t\t\t\t<select name=oidOptions tabindex=1>\r\n\t\t");
		for (int i = 0; i < organizationList.size(); i++) {
						Organization organization = (Organization) organizationList.get(i);
						String organizationName = organization.getOrganizationName();

						if (HiltonUtility.isEmpty(organizationName)) {
							organizationName = organization.getOrganizationId();
						}
						
      out.write("\r\n\t\t\t\t\t<option value=\"");
      out.print(organization.getOrganizationId());
      out.write('"');
      out.write(' ');
 if (oid.equals(organization.getOrganizationId())) {
      out.write("selected");
}
      out.write('>');
      out.print(organizationName);
      out.write("</option>\r\n\t\t");
		}
      out.write("\r\n\t\t\t\t<input type=hidden name=\"oid\" value=\"");
      out.print(oid);
      out.write("\" tabindex=1 size=20 maxLength=15 taborder=2 onfocus=\"setFocus('password');\" onBlur=\"setFocus('');\" onchange=\"this.value=this.value.toUpperCase();\"></td>\r\n\t\t\t</td>\r\n\t\t");
	} else {
			Organization organization = (Organization) organizationList.get(0);
		
      out.write("\r\n\t\t\t<td colspan=2><input type=hidden name=\"oid\" value=\"");
      out.print(organization.getOrganizationId());
      out.write("\"</td>\r\n\t\t");
	}
      out.write("\r\n\t\t\t<td width=380px rowspan=3>If your company is not registered, you may search and view solicitations as a \"Guest.\" However,\r\n\t\tall suppliers wishing to bid on any procurement opportunities must register their company. Registration also allows companies to\r\n\t\tview and download standard terms and conditions of purchase.</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td width=125px align=right><B>Login Id:<B></td>\r\n\t\t\t<td width=175px><input type=text name=\"uid\" value=\"");
      out.print(userId);
      out.write("\" tabindex=2 size=35 maxLength=100 onfocus=\"setFocus('userid');\" onBlur=\"setFocus('');\"></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td align=right><B>Password:</B></td>\r\n\t\t\t<td><input type=password name=\"password\" value=\"\" tabindex=3 size=20 maxLength=15 taborder=2 onfocus=\"setFocus('password');\" onBlur=\"setFocus('');\" ></td>\r\n\t\t</tr>\r\n\t\t<tr><td rowspan=4 valign=middle>&nbsp;</td></tr>\r\n\t\t<tr>\r\n\t\t\t<td><a href=\"javascript: submitLogin(); void(0);\" border=0 tabindex=4><img tabindex=4 class=button src=\"");
      out.print(contextPath);
      out.write("/images/button_login.gif\" align=bottom border=0 alt=\"Login\"></a></td>\r\n\t\t\t<td>\r\n\t\t\t\t<br><b>Registered Users:</b>\r\n\t\t\t\t<br>Please enter your Login Id and password, then click &quot;Login.&quot;\r\n\t\t\t\t<a href=\"javascript: passwordReminder(); void(0);\">Forgot your password?</a>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td><a href=\"javascript: register(); void(0);\"><img class=button src=\"");
      out.print(contextPath);
      out.write("/images/button_register.gif\" tabindex=5 alt=\"Self-Registration\" border=0></a></td>\r\n\t\t\t<td>\r\n\t\t\t\t<br><b>Non-Registered Users:</b>\r\n\t\t\t\t<br>Please Self-Register to obtain your Login Id and password.  This option requires your Customer Id.\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td><a href=\"javascript: loginAsGuest(); void(0);\"><img class=button src=\"");
      out.print(contextPath);
      out.write("/images/button_guest.gif\" tabindex=6 alt=\"Guest Login\" border=0></a></td>\r\n\t\t\t<td>\r\n\t\t\t\t<br><b>Guest Users:</b>\r\n\t\t\t\t<br>Click &quot;Guest&quot; to view solicitations as a guest user.\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t</td>\r\n</tr>\r\n</table>\r\n\r\n<table border=0 cellpadding=0 cellspacing=0 width=680px>\r\n<tr><td><br></td></tr>\r\n</table>\r\n\r\n");
      out.write("<br><br>\r\n\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px vAlign=bottom>\r\n<hr size=0 color=black width=680px align=left>\r\n<tr>\r\n\t<td class=copyright width=33% align=left>&nbsp;Copyright &copy; ");
      out.print(HiltonUtility.getFormattedDate(new Date(), oid, "yyyy"));
      out.write("&nbsp;<a href=\"http://www.puridiom.com\" target=\"_blank\">Puridiom.com</a></td>\r\n\t<td class=copyright width=34% align=center><a href=\"http://www.puridiom.com/procurement/legal/index.html\" target=\"_blank\">Legal Notices</a></td>\r\n\t<td align=right class=copyright>&nbsp;Release 3.0.-datetime-</td>\r\n</tr>\r\n</table>\r\n\r\n</form>\r\n</body>\r\n</html>");
      out.write("\r\n\r\n</FORM>\r\n\r\n</BODY>\r\n</HTML>\r\n<SCRIPT value=JavaScript>\r\n<!-- Hide script\r\n\r\n\tfrm = document.purchaseform;\r\n\r\n\tfrm.organizationId.value = \"\";\r\n\tif (frm.oidOptions) {\r\n\t\tfrm.oidOptions.focus();\r\n\t}\r\n\r\n\tvar displayWarning = false;\r\n\tvar theFocus = \"\";\r\n\tnetscape  = \"\";\r\n\tver\t  = navigator.appVersion;\r\n\tlen\t  = ver.length;\r\n\r\n\tfor (iln = 0; iln < len; iln++) {\r\n\t\tif (ver.charAt(iln) == \"(\") break;\r\n\t}\r\n\r\n\tnetscape = (ver.charAt(iln+1).toUpperCase() != \"C\");\r\n\r\n\tdocument.onkeydown = keyDown; // work together to analyze keystrokes\r\n\r\n\tif (netscape) document.captureEvents(Event.KEYDOWN|Event.KEYUP);\r\n\r\n\tfunction keyDown(DnEvents) { // handles keypress\r\n\t\t// determines whether Netscape or Internet Explorer\r\n\t\tk = (netscape) ? DnEvents.which : window.event.keyCode;\r\n\t\tif ( (k == 13) && ( (theFocus == 'password') || (theFocus == 'userid') ) ){\r\n\t\t\t// enter key pressed\r\n\t\t\t// check for focus so form isn't submitted twice\r\n\t\t\tsubmitLogin();\r\n\t\t}\r\n\t}\r\n\r\n\tfunction validateForm() {\r\n\t\tif (frm.handler.value.indexOf(\"VendorLogin\") >= 0) {\r\n");
      out.write("\t\t\tif (frm.oidOptions) {\r\n\t\t\t\t frm.oid.value = frm.oidOptions[frm.oidOptions.selectedIndex].value.toUpperCase();\r\n\t\t\t}\r\n\t\t\tif (isEmpty(frm.oid.value)) {\r\n\t\t\t\talert(\"You must enter an Organization Id.\");\r\n\t\t\t\tfrm.oid.focus();\r\n\t\t\t\treturn false;\r\n\t\t\t}\r\n\t\t\telse if ( (isEmpty(frm.uid.value) || isEmpty(frm.password.value)) && frm.userId.value != \"BB-GUEST\" ) {\r\n\t\t\t\talert(\"You must enter your Login Id and Password!\");\r\n\t\t\t\treturn false;\r\n\t\t\t}\r\n\r\n\t\t\tif (displayWarning) {\r\n\t\t\t\tvar msg = \"This version of Netscape is supported, but for optimum performance Netscape 6.0 is recommended.\";\r\n\t\t\t\tif (!confirm(msg)) {\r\n\t\t\t\t\treturn false;\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n//\t\tfrm.organizationId.value = frm.oid.value;\r\n\t\treturn true;\r\n\t}\r\n\r\n\tfunction setFocus(x) {\r\n\t\ttheFocus = x;\r\n\t}\r\n\r\n\tfunction browserSupport() {\r\n\t\tvar supported = false;\r\n\t\tvar bw = browserTest();\r\n \t\tvar agent = navigator.userAgent.toLowerCase();\r\n\t\tvar ie\t\t= (agent.indexOf(\"msie\") != -1);\r\n\t\tvar netscape\t= (agent.indexOf(\"netscape\") != -1);\r\n\t\tvar aol\t\t= (agent.indexOf(\"aol\") != -1);\r\n");
      out.write("\t\tvar opera\t= (agent.indexOf(\"opera\") != -1);\r\n\t\tvar webtv\t= (agent.indexOf(\"webtv\") != -1);\r\n\t\tvar version;\r\n\t\tvar ind1;\r\n\t\tvar ind2;\r\n\r\n\r\n\t\tif ( aol || opera || webtv ) {\r\n\t\t\tsupported = false;\r\n\t\t}\r\n\t\telse if ( ie ) {\r\n\t\t\tind1 = agent.indexOf(\"msie\") + 4;\r\n\t\t\tind2 = (agent.substring(ind1, agent.length)).indexOf(\";\");\r\n\t\t\tversion = agent.substring(ind1, ind1 + ind2);\r\n\t\t\tversion = parseFloat(version);\r\n\r\n\t\t\tif (version >= 4.6) {\r\n\t\t\t\tsupported = true;\r\n\t\t\t}\r\n\t\t}\r\n\t\telse if ( netscape ) {\r\n\t\t\tif (agent.indexOf(\"netscape6\") != -1) {\r\n\t\t\t\tind1 = agent.indexOf(\"netscape6/\") + 10;\r\n\t\t\t}\r\n\t\t\telse if (agent.indexOf(\"netscape/\") != -1) {\r\n\t\t\t\tind1 = agent.indexOf(\"netscape/\") + 9;\r\n\t\t\t}\r\n\r\n\t\t\tif (ind1 > 0) {\r\n\t\t\t\tind2 = agent.length;\r\n\r\n\t\t\t\tversion = agent.substring(ind1, ind2);\r\n\t\t\t}\r\n\t\t\telse {\r\n\t\t\t\tversion = \"0.0\";\r\n\t\t\t}\r\n\r\n\t\t\tversion = parseFloat(version);\r\n\r\n\t\t\tif (version >= 6.0) {\r\n\t\t\t\tsupported = true;\r\n\t\t\t}\r\n\t\t}\r\n\t\telse if ( bw.toLowerCase() == \"netscape\" ) {\r\n\t\t\tvar dom = document.getElementById?true:false;\r\n");
      out.write("\r\n\t\t\tversion = navigator.appVersion;\r\n\r\n\t\t\tif (dom) {\r\n\t\t\t\tnetscape = (parseFloat(version) >= 5.0) ?true:false;\r\n\t\t\t}\r\n\t\t\telse if (document.layers) {\r\n\t\t\t\tnetscape = (parseFloat(version) >= 4.7) ?true:false;\r\n\t\t\t\tdisplayWarning = (parseFloat(version) >= 4.7 && parseFloat(version) < 6.0) ?true:false;\r\n\t\t\t}\r\n\t\t\telse {\r\n\t\t\t\tnetscape = false;\r\n\t\t\t}\r\n\r\n\t\t\tif (netscape) {\r\n\t\t\t\tsupported = true;\r\n\t\t\t}\r\n\t\t}\r\n\r\n\t\tif ( !supported ) {\r\n\t\t\tfrm.action = \"");
      out.print(contextPath);
      out.write("/no_support.jsp\";\r\n\t\t\tfrm.submit();\r\n\t\t}\r\n\t}\r\n\r\n\tfunction register() {\r\n\t\tdoSubmit('user/self_registration.jsp', 'DoNothing');\r\n\t}\r\n\r\n\tfunction submitLogin() {\r\n\t\tfrm.userId.value = frm.uid.value;\r\n//\t\tfrm.organizationId.value = frm.oid.value;\r\n\t\tdoSubmit('menu/main_menu.jsp', 'VendorLogin');\r\n\t}\r\n\r\n\tfunction loginAsGuest() {\r\n\t\tfrm.userId.value = \"BB-GUEST\";\r\n\t\tfrm.organizationId.value = frm.oid.value;\r\n\t\tdoSubmit('menu/main_menu.jsp', 'VendorLogin');\r\n\t}\r\n\r\n\tfunction passwordReminder() {\r\n\t\tif (frm.oidOptions) {\r\n\t\t\t frm.oid.value = frm.oidOptions[frm.oidOptions.selectedIndex].value.toUpperCase();\r\n\t\t}\r\n\t\tdoSubmit('user/pswd_help.jsp', 'DoNothing');\r\n\t}\r\n\r\n\tfunction thisLoad() {\r\n\t\treturn;\r\n\t}\r\n//-->\r\n</SCRIPT>\r\n\r\n");

	/*****	PREVENT CACHING	 *****/

	response.setHeader("Cache-Control","no-cache");	//HTTP 1.1
	response.setHeader("Pragma","no-cache");	//HTTP 1.0
	response.setDateHeader ("Expires", 0);		//prevents caching at the proxy server

	/*****************************/

    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (pageContext != null) pageContext.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }
}
