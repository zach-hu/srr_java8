package org.apache.jsp.user;

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

public final class registration_005fconfirmation_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  static {
    _jspx_dependants = new java.util.Vector(5);
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
      out.write('\r');
      out.write('\n');

	String	errorMsg = (String) request.getAttribute("errorMsg");
	String	password = (String) request.getAttribute("confirm_password");
	String	supplierType = "registered";
	
	if (user.isQualified()) {
		supplierType = "qualified";
	}

      out.write("\r\n\r\n<input type=HIDDEN name=oid value=\"");
      out.print(oid);
      out.write("\">\r\n\r\n<table width=680px cellpadding=0 cellspacing=0 border=0>\r\n<tr>\r\n\t<td vAlign=top width=135px height=30px>\r\n\t\t<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>\r\n\t\t<tr>\r\n\t\t\t<td height=1px class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" width=\"1\" height=\"1px\" /></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td nowrap class=hdr12 vAlign=middle>\r\n\t\t\t\t<div style=\"margin-left:10px; margin-right:10px\" class=hdr12>Self Registration</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t</td>\r\n\t<td width=30px vAlign=bottom><img class=hdr12 src=\"");
      out.print(contextPath);
      out.write("/images/angle.gif\" width=\"30\" height=\"31\" /></td>\r\n\t<td vAlign=bottom align=right height=30px>\r\n\t\t<table cellpadding=\"0\" cellspacing=\"0\" border=0>\r\n\t\t<tr>\r\n\t\t\t<td width=1000px height=1px class=lightShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" width=\"1\" height=\"1\" /></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td height=2px class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" width=\"1\" height=\"2\" /></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t</td>\r\n</tr>\r\n</table>\r\n\r\n<br><br>\r\n\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px>\r\n<tr>\r\n\t<td width=100% valign=top align=center>\r\n\t\t<table border=0 cellSpacing=0 cellPadding=0 width=80%>\r\n\t\t<tr><td align=center><b>Welcome to the Solicitation System!</td></tr>\r\n\t\t<tr><td><br></td></tr>\r\n\t\t<tr><td align=center>Your registration information has been submitted.&nbsp;</td></tr>\r\n\t\t<tr><td align=center>You are now a ");
      out.print(supplierType);
      out.write(" supplier. You can search solicitations, submit bids, view standard terms and conditions, and change your profile information.<br>&nbsp;</td></tr>\r\n\t\t<tr><td><br><br></td></tr>\r\n\t\t<tr>\r\n\t\t\t<td align=center>\r\n\t\t\t\tClick the continue button below to continue to the menu page.\r\n\t\t\t\t<input type=hidden name=\"password\" value=\"");
      out.print(password);
      out.write("\">\r\n\t\t\t</td> \r\n\t\t</tr>\r\n\t\t<tr><td><br><br></td></tr>\r\n\t\t</table>\r\n\t</td>\r\n</tr>\r\n</table>\r\n\r\n<br>\r\n\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px>\r\n<tr>\r\n\t<td align=center>\r\n\t\t<a href=\"javascript: doSubmit('menu/main_menu.jsp', 'VendorLogin');\"><img class=button src=\"");
      out.print(contextPath);
      out.write("/images/button_continue.gif\" border=0 tabindex=26 alt=Continue to the Menu Page></a>\r\n\t</td>\r\n</tr>\r\n</table>\r\n\r\n<br>\r\n\r\n");
      out.write("<br><br>\r\n\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px vAlign=bottom>\r\n<hr size=0 color=black width=680px align=left>\r\n<tr>\r\n\t<td class=copyright width=33% align=left>&nbsp;Copyright &copy; ");
      out.print(HiltonUtility.getFormattedDate(new Date(), oid, "yyyy"));
      out.write("&nbsp;<a href=\"http://www.puridiom.com\" target=\"_blank\">Puridiom.com</a></td>\r\n\t<td class=copyright width=34% align=center><a href=\"http://www.puridiom.com/procurement/legal/index.html\" target=\"_blank\">Legal Notices</a></td>\r\n\t<td align=right class=copyright>&nbsp;Release 3.0.-datetime-</td>\r\n</tr>\r\n</table>\r\n\r\n</form>\r\n</body>\r\n</html>");
      out.write("\r\n\r\n</FORM>\r\n\r\n</BODY>\r\n</HTML>\r\n<SCRIPT value=JavaScript>\r\n<!-- Hide script\r\n\r\n\tfrm = document.purchaseform;\r\n\r\n\tfunction register() {\r\n\t\tfrm.organizationId.value = frm.RegisterUser_organizationId.value;\r\n\t\tdoSubmit('user/registration_confirmation.jsp', 'VendorRegistration');\r\n\t}\r\n\r\n\tfunction cancel() {\r\n\t\tdoSubmit('index.jsp', 'DoNothing');\r\n\t}\r\n\r\n    function confirmPswd() {\r\n\t\tvar inputedData = frm.confirm_password.value.toLowerCase();\r\n\t\tvar strPassword = frm.sup_contact_password.value.toLowerCase();\r\n\t\tif (inputedData != (strPassword)) {\r\n\t\t\talert(\"You did not enter the same password.  Please re-enter your password.\");\r\n\t\t\tfrm.confirm_password.value = \"\"\r\n\t\t\tfrm.sup_contact_password.focus();\r\n\t\t\tfrm.sup_contact_password.select();\r\n\t\t\treturn false;\r\n\t\t}\r\n\t\treturn true;\r\n\t}\r\n    \r\n function validateForm() {\r\n\tif (frm.handler.value.indexOf(\"VendorRegistration\") >= 0) {\r\n\t\t\tvar strOrganizationId = frm.RegisterUser_organizationId.value;\r\n\t        var strCompany = frm.RegisterUser_vendorName.value;\r\n        \tvar strEin = frm.RegisterUser_vendorEin.value;\r\n");
      out.write("\t        var strLast = frm.RegisterUser_lastName.value;\r\n        \tvar strFirst = frm.RegisterUser_firstName.value;\r\n\t        var strEmail = frm.RegisterUser_emailAddress.value;\r\n        \tvar strPswd = frm.RegisterUser_password.value;\r\n\t        var strVerifyPswd = frm.confirm_password.value;\r\n\t        var strPhone = frm.RegisterUser_phoneNumber.value;\r\n\t        var strFax = frm.RegisterUser_faxNumber.value;\r\n\r\n\t        if ( isEmpty(strOrganizationId) ) { \r\n\t\t\t\tvar msg = \"The Organization Id is required!\"; alert(msg);\r\n\t\t\t\tfrm.RegisterUser_organizationId.focus();\r\n\t\t\t\treturn false; \r\n\t\t\t}\r\n\t\t\t\r\n\t        if ( isEmpty(strCompany) ) { \r\n\t\t\t\tvar msg = \"The company name is required!\"; alert(msg);\r\n\t\t\t\tfrm.RegisterUser_vendorName.focus();\r\n//\t\t\t\treturn false; \r\n\t\t\t}\r\n\r\n\t        if ( isEmpty(strEin) ) { \r\n        \t\tvar msg = \"EIN Number is required!\"; alert(msg);\r\n\t\t\t\tfrm.RegisterUser_vendorEin.focus();\r\n\t\t\t\treturn false; \r\n\t\t\t}\r\n\r\n\t\t\tfrm.RegisterUser_emailAddress.focus();\r\n\t\t\tif ( isEmpty(strEmail) ) { \r\n\t\t\t\tvar msg = \"Your Email Address (user-id) is required\"; alert(msg);\r\n");
      out.write("\t\t\t\tfrm.RegisterUser_emailAddress.focus();\r\n\t\t\t\treturn false; \r\n\t\t\t}\r\n\t\t\t\r\n\t\t\tfrm.RegisterUser_lastName.focus();\r\n\t\t\tif ( isEmpty(strLast) ) { \r\n\t\t\t\tvar msg = \"Your User's Last Name is required\"; alert(msg);\r\n\t\t\t\tfrm.RegisterUser_lastName.focus();\r\n\t\t\t\treturn false; \r\n\t\t\t}\r\n\t\r\n\t\t\tfrm.RegisterUser_firstName.focus();\r\n\t\t\tif ( isEmpty(strFirst) ) { \r\n\t\t\t\tvar msg = \"Your First Name is required\"; alert(msg);\r\n\t\t\t\tfrm.RegisterUser_firstName.focus();\r\n\t\t\t\treturn false; \r\n\t\t\t}\r\n\t\r\n\t\t\tfrm.RegisterUser_password.focus();\r\n\t\t\tif ( isEmpty(strPswd) ) { \r\n\t\t\t\tvar msg = \"A password is required\"; alert(msg);\r\n\t\t\t\tfrm.RegisterUser_password.focus();\r\n\t\t\t\treturn false; \r\n\t\t\t}\r\n\r\n\t\t\tfrm.confirm_password.focus();\r\n\t        if ( isEmpty(strVerifyPswd) ) { \r\n\t\t\t\tvar msg = \"Please confirm your password\"; alert(msg);\r\n\t\t\t\tfrm.confirm_password.focus();\r\n\t\t\t\treturn false; \r\n\t\t\t}\r\n\t\r\n\t\t\tfrm.RegisterUser_phoneNumber.focus();  \r\n\t\t\tif ( isEmpty(strPhone) ) { \r\n\t\t\t\tvar msg = \"Your Phone is required\"; alert(msg);\r\n\t\t\t\tfrm.RegisterUser_contact_phone_number.focus();\r\n");
      out.write("\t\t\t\treturn false; \r\n\t\t\t}\r\n\t\r\n\t\t\ttxt=(frm.RegisterUser_emailAddress.value).toLowerCase();\r\n\t      \tif (txt.indexOf(\"@\")<2) {\r\n\t\t\t\talert(\"This email address seems wrong. Please check the prefix and '@' sign.\");\r\n\t\t\t\tfrm.RegisterUser_emailAddress.focus();\r\n\t\t\t\tfrm.RegisterUser_emailAddress.select();\r\n\t\t\t\treturn false;\r\n\t\t\t}\r\n\t\t\tif ((txt.indexOf(\".com\")<5)&&(txt.indexOf(\".org\")<5)\r\n\t\t\t\t&&(txt.indexOf(\".gov\")<5)&&(txt.indexOf(\".net\")<5)\r\n\t\t\t\t&&(txt.indexOf(\".mil\")<5)&&(txt.indexOf(\".edu\")<5)) {\r\n\t\t\t\talert(\"This email address seems wrong. Please check the suffix for accuracy. (It should include a .com, .edu, .net, .org, .gov or .mil)\");\r\n\t\t\t\tfrm.RegisterUser_emailAddress.focus();\r\n\t\t\t\tfrm.RegisterUser_emailAddress.select();\r\n\t\t\t\treturn false;\r\n\t\t\t}\r\n\t\t}\r\n\r\n\t\treturn true;\t\r\n    }\r\n\r\n\tfunction thisLoad() {\r\n\t\treturn;\r\n\t}\r\n//-->\r\n</SCRIPT>");
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
