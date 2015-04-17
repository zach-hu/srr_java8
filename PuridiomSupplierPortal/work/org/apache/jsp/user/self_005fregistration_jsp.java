package org.apache.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.tsagate.foundation.utility.*;
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

public final class self_005fregistration_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n\r\n");
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

	RegisterUser registerUser = (RegisterUser) request.getAttribute("registerUser");
	String	errorMsg = (String) request.getAttribute("registrationErrorMsg");

	errorMsg = Utility.ckNull(errorMsg);
	if (!Utility.isEmpty(errorMsg)) {
		errorMsg = errorMsg + "<br>";
	}

	if (registerUser == null) {
		registerUser = new RegisterUser();
	}

	List organizationList = OrganizationManager.getInstance().getOrganizationList();
	if (organizationList == null) {
		organizationList = new ArrayList();
	}
	if (organizationList.size() <= 0) {
		Organization organization = new Organization();
		organization.setOrganizationId("HILTON");
		organization.setOrganizationName("Hilton");
		organizationList.add(organization);
	}

      out.write("\r\n\r\n<input type=hidden name=registrationPage value=\"user/self_registration.jsp\">\r\n\r\n<table width=680px cellpadding=0 cellspacing=0 border=0>\r\n<tr>\r\n\t<td vAlign=top width=135px height=30px>\r\n\t\t<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>\r\n\t\t<tr>\r\n\t\t\t<td height=1px class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" width=\"1\" height=\"1px\" /></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td nowrap class=hdr12 vAlign=middle>\r\n\t\t\t\t<div style=\"margin-left:10px; margin-right:10px\" class=hdr12>Vendor Registration</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t</td>\r\n\t<td vAlign=bottom><img class=hdr12 src=\"");
      out.print(contextPath);
      out.write("/images/angle.gif\" height=\"31\" /></td>\r\n\t<td vAlign=bottom align=right height=30px>\r\n\t\t<table cellpadding=\"0\" cellspacing=\"0\" border=0>\r\n\t\t<tr>\r\n\t\t\t<td width=1000px height=1px class=lightShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" width=\"1\" height=\"1\" /></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td height=2px class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" width=\"1\" height=\"2\" /></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t</td>\r\n</tr>\r\n</table>\r\n\r\n<br>\r\n\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px>\r\n<tr>\r\n\t<td width=100% valign=top align=center>\r\n\t\t<table border=0 cellSpacing=0 cellPadding=0 width=500px>\r\n\t\t<tr>\r\n\t\t\t<td colspan=2 class=error align=center>&nbsp;");
      out.print(errorMsg);
      out.write("</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td colspan=\"2\">\r\n\t\t\t\tTo register, please complete the information below and select\r\n\t\t\t\tthe &quot;Submit&quot; button. This information can be changed\r\n\t\t\t\tby you at any time after initial registration. <br>Note:\r\n\t\t\t\tAutomatic qualification is possible if your company is listed as\r\n\t\t\t\tan existing qualified supplier and a valid EIN Number is provided.\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr><td><br><br></td></tr>\r\n\t\t");
	if (organizationList.size() > 1) {
      out.write("\r\n\t\t<tr>\r\n\t\t\t<td>Organization Id<font color=\"#0000ff\">* </font> </td>\r\n\t\t\t<td>&nbsp;</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<select name=oidOptions tabindex=1>\r\n\t\t");
		for (int i = 0; i < organizationList.size(); i++) {
						Organization organization = (Organization) organizationList.get(i);
      out.write("\r\n\t\t\t\t\t<option value=\"");
      out.print(organization.getOrganizationId());
      out.write('"');
      out.write(' ');
 if (registerUser.getOrganizationId().equals(organization.getOrganizationId())) {
      out.write("selected");
}
      out.write('>');
      out.print(organization.getOrganizationName());
      out.write("</option>\r\n\t\t");
		}
      out.write("\r\n\t\t\t\t<input type=hidden name=\"RegisterUser_organizationId\" value=\"");
      out.print(registerUser.getOrganizationId());
      out.write("\" tabindex=1 size=20 maxLength=15 taborder=2 onfocus=\"setFocus('password');\" onBlur=\"setFocus('');\" onchange=\"this.value=this.value.toUpperCase();\"></td>\r\n\t\t\t<td>&nbsp;</td>\r\n\t\t</tr>\r\n\t\t");
	} else {
			Organization organization = (Organization) organizationList.get(0);
		
      out.write("\r\n\t\t\t<td colspan=2><input type=hidden name=\"RegisterUser_organizationId\" value=\"");
      out.print(organization.getOrganizationId());
      out.write("\"</td>\r\n\t\t");
	}
      out.write("\r\n\t\t<tr>\r\n\t\t\t<td>Company Name<font color=\"#0000ff\">* </font> </td>\r\n\t\t\t<td>EIN Number<font color=\"#0000ff\">* </font> </td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td><input type=text tabindex=4 name=\"RegisterUser_vendorName\" size=27 maxlength=40 value=\"");
      out.print(registerUser.getVendorName());
      out.write("\"></td>\r\n\t\t\t<td><input type=text tabindex=6 name=\"RegisterUser_vendorEin\" size=14 maxlength=10 value=\"");
      out.print(registerUser.getVendorEin());
      out.write("\" onchange=\"upperCase(this);\"></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<B>Email Address </B>\r\n\t\t\t\t<font color=\"#0000ff\">*</font>\r\n\t\t\t\t(This will be your Login Id)\r\n\t\t\t</td>\r\n\t\t\t<td>User's Last Name<font color=\"#0000ff\">* </font> </td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td vAlign=top><input type=text tabindex=8 name=\"RegisterUser_emailAddress\" size=27 maxlength=50 value=\"");
      out.print(registerUser.getEmailAddress());
      out.write("\"></td>\r\n\t\t\t<td><input type=text tabindex=10 name=\"RegisterUser_lastName\" size=27 maxlength=20 value=\"");
      out.print(registerUser.getLastName());
      out.write("\"></td>\r\n\t        </tr>\r\n\t\t<tr>\r\n\t\t\t<td>First Name<font color=\"#0000ff\">* </font></td>\r\n\t\t\t<td>MI</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td><input type=text tabindex=12 name=\"RegisterUser_firstName\" size=27 maxlength=20 value=\"");
      out.print(registerUser.getFirstName());
      out.write("\"></td>\r\n\t\t\t<td><input type=text tabindex=14 name=\"RegisterUser_middleInitial\" size=3 maxlength=2 value=\"");
      out.print(registerUser.getMiddleInitial());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Password <font color=\"#0000ff\">* </font></td>\r\n\t\t\t<td>Confirm Password <font color=\"#0000ff\">* </font></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td><input type=password tabindex=16 name=\"RegisterUser_contactPassword\" size=27 maxlength=12></td>\r\n\t\t\t<td><input type=password tabindex=18 name=\"confirm_password\" size=27 maxlength=12></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>Phone<font color=\"#0000ff\">* </font></td>\r\n\t\t\t<td>Fax</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td><input type=text tabindex=20 name=\"RegisterUser_phoneNumber\" size=20 maxlength=30 value=\"");
      out.print(registerUser.getPhoneNumber());
      out.write("\"></td>\r\n\t\t\t<td><input type=text tabindex=22 name=\"RegisterUser_faxNumber\" size=20 maxlength=30 value=\"");
      out.print(registerUser.getFaxNumber());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\r\n\t\t<table border=0 cellSpacing=0 cellPadding=0 width=500px>\r\n\t\t<tr><td colspan=2><br></td></tr>\r\n\t\t<tr align=middle>\r\n\t\t\t<td colspan=2>\r\n\t\t\t\t<center>\r\n\t\t\t\t\t<font color=\"#0000ff\">*</font>\r\n\t\t\t\t\t<b>REQUIRED INFORMATION.</b>\r\n\t\t\t\t</center>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t</td>\r\n</tr>\r\n</table>\r\n\r\n<br>\r\n\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px>\r\n<tr>\r\n\t<td width=50% align=center>\r\n\t\t<a href=\"javascript: register(); void(0);\"><img class=button src=\"");
      out.print(contextPath);
      out.write("/images/button_submit.gif\" tabindex=24 border=0 alt=Submit></a>\r\n        </TD>\r\n\t<td width=50% align=center>\r\n\t\t<a href=\"javascript: cancel();\"><img class=button src=\"");
      out.print(contextPath);
      out.write("/images/button_cancel.gif\" border=0 tabindex=26 alt=Cancel></a>\r\n\t</td>\r\n</tr>\r\n</table>\r\n\r\n<br>\r\n\r\n");
      out.write("<br><br>\r\n\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px vAlign=bottom>\r\n<hr size=0 color=black width=680px align=left>\r\n<tr>\r\n\t<td class=copyright width=33% align=left>&nbsp;Copyright &copy; ");
      out.print(HiltonUtility.getFormattedDate(new Date(), oid, "yyyy"));
      out.write("&nbsp;<a href=\"http://www.puridiom.com\" target=\"_blank\">Puridiom.com</a></td>\r\n\t<td class=copyright width=34% align=center><a href=\"http://www.puridiom.com/procurement/legal/index.html\" target=\"_blank\">Legal Notices</a></td>\r\n\t<td align=right class=copyright>&nbsp;Release 3.0.-datetime-</td>\r\n</tr>\r\n</table>\r\n\r\n</form>\r\n</body>\r\n</html>");
      out.write("\r\n\r\n<SCRIPT value=JavaScript>\r\n<!-- Hide script\r\n\r\n\tfrm = document.purchaseform;\r\n\r\n\tfrm.userId.value = \"\";\r\n\tfrm.organizationId.value = \"\";\r\n\r\n\tfunction register() {\r\n\t\tif (frm.oidOptions) {\r\n\t\t\t frm.RegisterUser_organizationId.value = frm.oidOptions[frm.oidOptions.selectedIndex].value.toUpperCase();\r\n\t\t}\r\n\t\tfrm.organizationId.value = frm.RegisterUser_organizationId.value;\r\n\t\tfrm.userId.value = frm.RegisterUser_emailAddress.value;\r\n\t\tdoSubmit('user/registration_confirmation.jsp', 'VendorRegistration');\r\n\t}\r\n\r\n\tfunction cancel() {\r\n\t\tdoSubmit('index.jsp', 'DoNothing');\r\n\t}\r\n\r\n function validateForm() {\r\n\tif (frm.handler.value.indexOf(\"VendorRegistration\") >= 0) {\r\n\t\t\tvar w = frm.RegisterUser_organizationId.value;\r\n\t\t\tvar alertMessage = \"\";\r\n\r\n\t\t\tif ( isEmpty( w ) )\r\n\t\t\t\talertMessage += 'The Organization Id is required.\\n';\r\n\r\n\t\t\tw = frm.RegisterUser_vendorName.value;\r\n\t\t\tif ( isEmpty( w ) )\r\n\t\t\t\talertMessage += 'Your Company Name is required.\\n';\r\n\r\n        \tw = frm.RegisterUser_vendorEin.value;\r\n\t\t\tif ( isEmpty( w ) )\r\n");
      out.write("\t\t\t\talertMessage += 'Your company\\'s EIN Number is required.\\n';\r\n\r\n\t\t\tw = frm.RegisterUser_emailAddress.value;\r\n\t\t\tif ( isEmpty( w ) ) {\r\n\t\t\t\talertMessage += 'Your Email Address (user-id) is required.\\n';\r\n\t\t\t} else {\r\n\t\t\t\tw =(frm.RegisterUser_emailAddress.value).toLowerCase();\r\n\t\t      \tif (w.indexOf(\"@\")<2)\r\n\t\t\t\t\talertMessage += 'The Email Address seems wrong. Please check the prefix and \\'@\\' sign.\\n';\r\n\r\n\t\t\t\tif ((w.indexOf(\".com\")<5)&&(w.indexOf(\".org\")<5)\r\n\t\t\t\t\t&&(w.indexOf(\".gov\")<5)&&(w.indexOf(\".net\")<5)\r\n\t\t\t\t\t&&(w.indexOf(\".ca\")<5)&&(w.indexOf(\".mil\")<5)&&(w.indexOf(\".edu\")<5)) {\r\n\t\t\t\t\talertMessage += 'The Email Address seems wrong. Please check the suffix for accuracy. (It should include a .com, .edu, .net, .org, .gov, .ca or .mil)\\n';\r\n\t\t\t\t}\r\n\t\t\t}\r\n\r\n\t        w = frm.RegisterUser_lastName.value;\r\n\t\t\tif ( isEmpty( w ) )\r\n\t\t\t\talertMessage += 'Your Last Name is required.\\n';\r\n\r\n\t\t\tw = frm.RegisterUser_firstName.value;\r\n\t\t\tif ( isEmpty( w ) )\r\n\t\t\t\talertMessage += 'Your First Name is required.\\n';\r\n\r\n\t\t\tw = frm.RegisterUser_contactPassword.value;\r\n");
      out.write("\t\t\tif ( isEmpty( w ) )\r\n\t\t\t\talertMessage += 'A Password is required.\\n';\r\n\r\n\t\t\tw = frm.confirm_password.value;\r\n\t\t\tif ( isEmpty( w ) )\r\n\t\t\t\talertMessage += 'Confirm Password is required.\\n';\r\n\r\n\t\t\tif ( w != frm.RegisterUser_contactPassword.value )\r\n\t\t\t\talertMessage += 'Confirm Password does not match Password.\\n';\r\n\r\n\t\t\tw = frm.RegisterUser_phoneNumber.value;\r\n\t\t\tif ( isEmpty( w ) )\r\n\t\t\t\talertMessage += 'Your Phone Number is required.\\n';\r\n\r\n\t\t\tif ( alertMessage.length > 0 ) {\r\n\t\t\t   \talert ( 'Please fix the following problems:\\n\\n'+alertMessage );\r\n\t\t\t   \treturn false;\r\n\t\t\t}\r\n\t\t}\r\n\r\n\t\treturn true;\r\n    }\r\n\r\n\tfunction thisLoad() {\r\n\t\treturn;\r\n\t}\r\n//-->\r\n</SCRIPT>\r\n\r\n");

	/*****	PREVENT CACHING	 *****/

	response.setHeader("Cache-Control","no-cache");	//HTTP 1.1
	response.setHeader("Pragma","no-cache");	//HTTP 1.0
	response.setDateHeader ("Expires", 0);		//prevents caching at the proxy server

	/*****************************/

      out.write('\r');
      out.write('\n');
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
