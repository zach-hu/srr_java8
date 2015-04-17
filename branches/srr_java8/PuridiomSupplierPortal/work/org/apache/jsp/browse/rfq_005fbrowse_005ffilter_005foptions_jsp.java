package org.apache.jsp.browse;

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
import com.tsa.puridiom.property.PropertiesManager;
import java.text.*;

public final class rfq_005fbrowse_005ffilter_005foptions_jsp extends org.apache.jasper.runtime.HttpJspBase
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

	PropertiesManager propertiesManager = PropertiesManager.getInstance(user.getOrganizationId());
	String	dateFormat = propertiesManager.getProperty("MISC", "DateFormat", "yyyy-MM-dd");
	String	browseName = "rfq-bidboard-posts";
	String	s_last2	 = new String();
	String	s_last5	 = new String();
	String	s_last10 = new String();
	String	s_last15 = new String();
	String	s_last20 = new String();
	String	s_last30 = new String();

	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
	Calendar rightNow = Calendar.getInstance();

	rightNow.add(rightNow.DAY_OF_MONTH, - 2);
	s_last2 = formatter.format(rightNow.getTime());

	rightNow = Calendar.getInstance();
	rightNow.add(rightNow.DAY_OF_MONTH, - 5);
	s_last5	= formatter.format(rightNow.getTime());

	rightNow = Calendar.getInstance();
	rightNow.add(rightNow.DAY_OF_MONTH, - 10);
	s_last10 = formatter.format(rightNow.getTime());

	rightNow = Calendar.getInstance();
	rightNow.add(rightNow.DAY_OF_MONTH, - 15);
	s_last15 = formatter.format(rightNow.getTime());

	rightNow = Calendar.getInstance();
	rightNow.add(rightNow.DAY_OF_MONTH, - 20);
	s_last20 = formatter.format(rightNow.getTime());

	rightNow = Calendar.getInstance();
	rightNow.add(rightNow.DAY_OF_MONTH, - 30);
	s_last30 = formatter.format(rightNow.getTime());


      out.write("\r\n<SCRIPT LANGUAGE=\"JavaScript1.2\" SRC=\"");
      out.print(contextPath);
      out.write("/scripts/date_check.js\"></SCRIPT>\r\n<SCRIPT LANGUAGE=\"JavaScript1.2\" SRC=\"");
      out.print(contextPath);
      out.write("/scripts/calendar.js\"></SCRIPT>\r\n\r\n<table width=680px cellpadding=0 cellspacing=0 border=0>\r\n<tr>\r\n\t<td valign=top width=150px height=30px>\r\n\t\t<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>\r\n\t\t<tr>\r\n\t\t\t<td height=1px class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" width=\"1\" height=\"1px\" /></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td nowrap class=hdr12 valign=middle>\r\n\t\t\t\t<div style=\"margin-left:10px; margin-right:10px\" class=hdr12>Solicitation Search Options</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t</td>\r\n\t<td width=30px valign=bottom><img class=hdr12 src=\"");
      out.print(contextPath);
      out.write("/images/angle.gif\" width=\"30\" height=\"31\" /></td>\r\n\t<td valign=bottom align=right>\r\n\t\t<table cellpadding=\"0\" cellspacing=\"0\" border=0>\r\n\t\t\t<tr>\r\n\t\t\t\t<td width=1000px height=1px class=lightShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" width=\"1\" height=\"1\" /></td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td height=2px class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" width=\"1\" height=\"2\" /></td>\r\n\t\t\t</tr>\r\n\t\t</table>\r\n\t</td>\r\n</tr>\r\n</table>\r\n\r\n<br>\r\n<br>\r\n\r\n<table border=0 cellpadding=2 cellspacing=0 width=680px>\r\n<tr>\r\n\t<td align=center>\r\n\t\t<table id=\"filterOptions\" border=0 cellpadding=2 cellspacing=0>\r\n\t\t<tr>\r\n\t\t\t<td align=right>Synopsis</td>\r\n\t\t\t<td colspan=4><input maxlength=250 name=\"synopsis\" size=75 type=text onchange=\"upperCase(this);\"></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td align=right>Solicitation #</td>\r\n\t\t\t<td><input maxlength=30 name=\"rfqNumber\" size=15 type=text onChange=\"correctRfqNumber();\"></td>\r\n\t\t\t<td width=50px>&nbsp;</td>\r\n\t\t\t<td align=right>Commodity</td>\r\n\t\t\t<td><input maxlength=30 name=\"commodity1\" size=20 type=text onchange=\"upperCase(this);\"></td>\r\n\t\t</tr>\r\n\t\t<tr>\t\r\n\t\t\t<td align=right>Amendment</td>\r\n\t\t\t<td><input maxlength=30 name=\"amendment\" size=15 type=text></td>\r\n\t\t\t<td width=50px>&nbsp;</td>\r\n\t\t\t<td align=right>Commodity</td>\r\n\t\t\t<td><input maxlength=30 name=\"commodity2\" size=20 type=text onchange=\"upperCase(this);\"></td>\r\n\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n\t\t\t<td align=right>Date Due</td>\r\n\t\t\t<td>\r\n\t\t\t    <input type=text name=\"dueDate\" value=\"\" onChange=\"checkDate( this );\" size=15 maxLength=30>\r\n\t\t\t\t<a href=\"javascript: show_calendar('dueDate', '");
      out.print(dateFormat);
      out.write("');\"><IMG src=\"");
      out.print(contextPath);
      out.write("/images/calendar.gif\" valign=bottom border=0></a>\r\n\t\t\t</td>\r\n\t\t\t<td width=50px>&nbsp;</td>\r\n\t\t\t<td align=right>Commodity</td>\r\n\t\t\t<td><input maxlength=30 name=\"commodity3\" size=20 type=text onchange=\"upperCase(this);\"></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t</td>\r\n</tr>\r\n<tr>\r\n\t<td align=center>\r\n\t\t<br><br>\r\n\t\t<table border=0 cellpadding=2 cellspacing=0>\r\n\t\t<tr>\r\n\t\t\t<td align=right nowrap>Show Solicitations Added in the Last:</td>\r\n\t\t\t<td>\r\n\t\t\t\t\t<SELECT NAME=\"as_posted\" TABINDEX=\"8\">\r\n\t\t\t\t\t\t<OPTION VALUE=\"\"></OPTION>\r\n\t\t\t\t\t\t<OPTION VALUE=\"");
      out.print(s_last2);
      out.write("\">2 days</OPTION>\r\n\t\t\t\t\t\t<OPTION VALUE=\"");
      out.print(s_last5);
      out.write("\">5 days</OPTION>\r\n\t\t\t\t\t\t<OPTION VALUE=\"");
      out.print(s_last10);
      out.write("\">10 days</OPTION>\r\n\t\t\t\t\t\t<OPTION VALUE=\"");
      out.print(s_last15);
      out.write("\">15 days</OPTION>\r\n\t\t\t\t\t\t<OPTION VALUE=\"");
      out.print(s_last20);
      out.write("\">20 days</OPTION>\r\n\t\t\t\t\t\t<OPTION VALUE=\"");
      out.print(s_last30);
      out.write("\">30 days</OPTION>\r\n\t\t\t\t\t</SELECT>\r\n\t\t\t</td>\r\n\t\t\t<td width=50px>&nbsp;</td>\r\n\t\t\t<td align=right nowrap>Sort By:</td>\r\n\t\t\t<td>\r\n\t\t\t\t\t<SELECT NAME=\"as_sort\" TABINDEX=\"9\">\r\n\t\t\t\t\t\t<OPTION VALUE=\"\"></OPTION>\r\n\t\t\t\t\t\t<OPTION VALUE=\"RfqHeader_appDate\" SELECTED>Posted Date</OPTION>\r\n\t\t\t\t\t\t<OPTION VALUE=\"RfqHeader_dueDate\">Due Date</OPTION>\r\n\t\t\t\t\t\t<OPTION VALUE=\"RfqHeader_rfqNumber\">Solicitation #</OPTION>\r\n\t\t\t\t\t</SELECT>\r\n\t\t\t\t\t<SELECT NAME=\"as_sort_opt\" TABINDEX=\"10\">\r\n\t\t\t\t\t\t<OPTION VALUE=\"\"></OPTION>\r\n\t\t\t\t\t\t<OPTION VALUE=\"ASC\" SELECTED>A</OPTION>\r\n\t\t\t\t\t\t<OPTION VALUE=\"DESC\">D</OPTION>\r\n\t\t\t\t\t</SELECT>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t</td>\r\n</tr>\r\n</table>\r\n<br>\r\n<br>\r\n<table border=0 width=680px>\r\n<tr><td id=\"hiddenFields\"></td></tr>\r\n<tr>\r\n\t<td align=center width=50%>\r\n\t\t<a href=\"javascript: search(); void(0);\">\r\n\t\t<img class=button src=\"");
      out.print(contextPath);
      out.write("/images/button_browse.gif\" border=0></a>\r\n\t</td>\r\n\t<td align=center width=50%>\r\n\t\t<a href=\"javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);\"><img class=button src=\"");
      out.print(contextPath);
      out.write("/images/button_cancel.gif\" tabindex=77 border=0></a>\r\n\t</td>\r\n</tr>\r\n</table>\r\n\r\n<script value=JavaScript>\r\n<!-- Hide script\r\n\r\n\tfrm = document.purchaseform;\r\n\tvar browseName = \"");
      out.print(browseName);
      out.write("\";\r\n\r\n\tfunction correctRfqNumber ( fld ) {\r\n/*\r\n\t\tvar frmFilter = fld.value;\r\n\t\tif (frmFilter.indexOf(\"%\") < 0) {\r\n\t\t\tnfilter( frmFilter );\r\n\t\t\tvar r = \"000000\";\r\n\r\n\t\t\tif ( (frmFilter.length < 6) && (frmFilter.length > 0)) {\r\n\t\t\t\tfld.value = r.substr(frmFilter.length) + frmFilter; \r\n\t\t\t}\r\n\t\t}\r\n*/\r\n\t}\r\n\r\n\tfunction search() {\r\n\t\tsetFilterOptions();\r\n\t\tbrowse(browseName);\r\n\t}\r\n\r\n\tfunction setFilterOptions() {\r\n\t\tif (!isEmpty(frm.synopsis.value)) {\r\n\t\t\tsetOriginalFilter(\"RfqHeader_rfqDescription\", \"LIKE\", \"%\" + frm.synopsis.value + \"%\");\r\n\t\t}\r\n\t\tif(isEmpty(frm.dueDate.value)) {\r\n\t\t\t// Only set dueDate >= today if due date filter is not specified\r\n\t\t\tsetOriginalFilter(\"RfqHeader_dueDate\", \">=\", \"");
      out.print(HiltonUtility.getFormattedDate(new Date(), oid, "yyyy-MM-dd"));
      out.write("\");\t\t\r\n\t\t}\r\n\t\tsetOriginalFilter(\"RfqHeader_rfqNumber\", \"=\", frm.rfqNumber.value);\r\n\t\tsetOriginalFilter(\"RfqHeader_rfqAmendment\", \"=\", frm.amendment.value);\r\n\t\tsetOriginalFilter(\"RfqHeader_dueDate\", \"=\", frm.dueDate.value);\r\n\t\tsetOriginalFilter(\"RfqHeader_appDate\", \">=\", frm.as_posted.options[frm.as_posted.selectedIndex].value);\r\n\t\t\r\n\t\tif (!isEmpty(frm.commodity1.value)) {\r\n\t\t\tbrowseName = \"rfq-bidboard-posts-by-commodity\";\r\n\t\t\t\r\n\t\t\tif (!isEmpty(frm.commodity2.value) || !isEmpty(frm.commodity3.value)) {\r\n\t\t\t\tsetOriginalFilterWithLogicalOper(\"RfqLine_commodity\", \"=\", frm.commodity1.value, \"OR\");\r\n\t\t\t\tsetOriginalFilterWithLogicalOper(\"RfqLine_commodity\", \"=\", frm.commodity2.value, \"OR\");\r\n\t\t\t\tsetOriginalFilterWithLogicalOper(\"RfqLine_commodity\", \"=\", frm.commodity3.value, \"OR\");\r\n\t\t\t} else {\r\n\t\t\t\tsetOriginalFilter(\"RfqLine_commodity\", \"=\", frm.commodity1.value);\t\t\t\r\n\t\t\t}\r\n\t\t}\r\n\t\tvar sortCol\t = frm.as_sort.options[frm.as_sort.selectedIndex].value;\r\n\t\tvar sortOrd\t = frm.as_sort_opt.options[frm.as_sort_opt.selectedIndex].value;\r\n");
      out.write("\t\tsetOriginalSort(sortCol, sortOrd);\r\n\t}\r\n\r\n\tfunction setFilterOptionsOld() {\r\n\t\tvar myTable = document.getElementById(\"filterOptions\");\r\n\t\tvar myHiddenFields = document.getElementById(\"hiddenFields\");\r\n\t\tvar filterFields = document.all(\"as_filter_txt\");\r\n\t\tvar previousLogicalOperator = \"AND\";\r\n\r\n\t\tfor (var i=0; i < filterFields.length; i++) {\r\n\t\t\tvar filterColumn = frm.as_colname[i].value;\r\n\t\t\tvar filterTxt = frm.as_filter_txt[i].value;\r\n//alert(\"filterColumn = \" + filterColumn);\r\n//alert(\"filterTxt = \" + filterTxt);\r\n\t\t\tif (!isEmpty(filterTxt)) {\r\n\t\t\t\tif (filterTxt.indexOf(\"%\") >= 0) {\r\n\t\t\t\t\tsetOriginalFilter(filterColumn, \"LIKE\", filterTxt);\r\n\t\t\t\t} else {\r\n\t\t\t\t\tsetOriginalFilter(filterColumn, \"=\", filterTxt);\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n\t\t\r\n\t\tvar posted\t= frm.as_posted.options[frm.as_posted.selectedIndex].value;\r\n\t\tif (!isEmpty(posted)) {\r\n\t\t\tsetOriginalFilter(\"RfqHeader_appDate\", \">=\", posted);\r\n\t\t}\r\n\t\t\r\n\t\tvar sortCol\t = frm.as_sort.options[frm.as_sort.selectedIndex].value;\r\n\t\tvar sortOrd\t = frm.as_sort_opt.options[frm.as_sort_opt.selectedIndex].value;\r\n");
      out.write("\t\tif (!isEmpty(sortCol)) {\r\n\t\t\tsetOriginalSort(sortCol, sortOrd);\r\n\t\t}\r\n\t}\r\n\r\n// End Hide script -->\r\n</script>\r\n\r\n");
      out.write("<br><br>\r\n\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px vAlign=bottom>\r\n<hr size=0 color=black width=680px align=left>\r\n<tr>\r\n\t<td class=copyright width=33% align=left>&nbsp;Copyright &copy; ");
      out.print(HiltonUtility.getFormattedDate(new Date(), oid, "yyyy"));
      out.write("&nbsp;<a href=\"http://www.puridiom.com\" target=\"_blank\">Puridiom.com</a></td>\r\n\t<td class=copyright width=34% align=center><a href=\"http://www.puridiom.com/procurement/legal/index.html\" target=\"_blank\">Legal Notices</a></td>\r\n\t<td align=right class=copyright>&nbsp;Release 3.0.-datetime-</td>\r\n</tr>\r\n</table>\r\n\r\n</form>\r\n</body>\r\n</html>");
      out.write('\r');
      out.write('\n');

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
