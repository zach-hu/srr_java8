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
import com.tsa.puridiom.commodity.CommodityManager;
import com.tsa.puridiom.entity.*;

public final class prequalification_jsp extends org.apache.jasper.runtime.HttpJspBase
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

	VendorRegister	vendorRegister = (VendorRegister) request.getAttribute("vendorRegister");
	String	message = HiltonUtility.ckNull((String) request.getAttribute("message"));

	if (vendorRegister == null) {
		vendorRegister = new VendorRegister();
	}
	List	vendorRegCommRelList = (List) request.getAttribute("vendorRegCommRelList");
	if (vendorRegCommRelList == null) {
		vendorRegCommRelList = new ArrayList();
	}
	List	commodityList = new ArrayList();
	for (int i=0; i < vendorRegCommRelList.size(); i++) {
		VendorRegCommRel vendorRegCommRel = (VendorRegCommRel) vendorRegCommRelList.get(i);
		commodityList.add(vendorRegCommRel.getComp_id().getCommodityCode());
	}
	if (commodityList.size() < 10) {
		for (int i=commodityList.size(); i < 10; i++) {
			commodityList.add("");
		}
	}

      out.write("\r\n<script language=\"JavaScript1.2\" src=\"");
      out.print(contextPath);
      out.write("/scripts/dynamicTables.js\"></script>\r\n\r\n<input type=hidden name=\"VendorRegister_contactType\" value=\"");
      out.print(vendorRegister.getContactType());
      out.write("\">\r\n<input type=hidden name=\"VendorRegister_vendorId\" value=\"");
      out.print(user.getVendorId());
      out.write("\">\r\n<input type=hidden name=\"VendorRegCommRel_vendorId\" value=\"");
      out.print(user.getVendorId());
      out.write("\">\r\n<input type=hidden name=allowBrowse value=\"true\">\r\n<input type=hidden name=vendorCommodityUpdate value=\"Y\">\r\n\r\n<table width=680px cellpadding=0 cellspacing=0 border=0>\r\n<tr>\r\n\t<td vAlign=top width=135px height=30px>\r\n\t\t<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>\r\n\t\t<tr>\r\n\t\t\t<td height=1px class=darkShadow colspan=2><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" width=1px height=1px /></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td nowrap class=hdr12 vAlign=middle>\r\n\t\t\t\t<div style=\"margin-left:10px; margin-right:10px\" class=hdr12>Pre-Qualification Information</div>\r\n\t\t\t</td>\r\n\t\t\t<td nowrap class=hdr12 vAlign=middle>\r\n\t\t\t\t<a href=\"javascript: uploadDocs(); void(0);\"><img name=\"img_attach\" src=\"");
      out.print(contextPath);
      out.write("/images/clip_lg.gif\" border=0 valign=top alt=\"Click here to upload documents\"></a>&nbsp;\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t</td>\r\n\t<td vAlign=bottom><img class=hdr12 src=\"");
      out.print(contextPath);
      out.write("/images/angle.gif\" height=31px /></td>\r\n\t<td vAlign=bottom align=right height=30px>\r\n\t\t<table cellpadding=0 cellspacing=0 border=0>\r\n\t\t<tr>\r\n\t\t\t<td width=1000px height=1px class=lightShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" width=1px height=1px /></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td height=2px class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" width=1px height=2px /></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t</td>\r\n</tr>\r\n</table>\r\n\r\n<br>\r\n\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px>\r\n<tr><td align=center>&nbsp;<b>");
      out.print(message);
      out.write("</b></td></tr>\r\n</table>\r\n\r\n<br>\r\n\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px>\r\n<tr>\r\n\t<td width=5px>&nbsp;</td>\r\n\t<td width=125px valign=top><b>General Information</b></td>\r\n\t<td width=5px>&nbsp;</td>\r\n\t<td width=2px class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" order=0 width=2px height=100% class=darkShadow></td>\r\n\t<td width=5px>&nbsp;</td>\r\n\t<td>\r\n\t\t<table border=0 cellspacing=0 cellpadding=2 ");
      out.print(HtmlWriter.isVisible(oid, "bb-businessType"));
      out.write(">\r\n\t\t<tr>\r\n\t\t\t<td nowrap class=label>");
      out.print(DictionaryManager.getLabel(oid, "bb-businessType", "Business Type", true));
      out.write("</td>\r\n");

	List vendorTypeList = (List) request.getAttribute("vendorTypeList");
	if (vendorTypeList != null && vendorTypeList.size() > 0) { 
      out.write("\r\n\t\t</tr>\r\n\t\t</table>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 width=100%>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td width=\"10%\">&nbsp;</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t\t<input type=hidden name=\"VendorRegister_businessType\" value=\"");
      out.print(vendorRegister.getBusinessType());
      out.write("\">\r\n\t\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorTypeList.size(); il++) {
				StdTable stdTable = (StdTable) vendorTypeList.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); 
      out.write("\r\n\t\t\t\t\t\t<tr><td><input name=\"as_businessType\" type=checkbox  value=\"");
      out.print(stdTablePK.getTableKey());
      out.write('"');
      out.write(' ');
if (vendorRegister.getBusinessType().indexOf(stdTablePK.getTableKey()) >= 0) {
      out.write("CHECKED");
}
      out.write(">&nbsp;");
      out.print(stdTable.getDescription());
      out.write("</td></tr>\r\n");
			fl = il;
				if (vendorTypeList.size()/(fl + 1) <= 2 && b_new_column) {
					b_new_column = false;
      out.write("\r\n\t\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
			}
			}
      out.write("\r\n\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	} else {
      out.write("\r\n\t\t\t<td><input type=text name=\"VendorRegister_businessType\" value=\"");
      out.print(vendorRegister.getBusinessType());
      out.write("\">\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	}
      out.write("\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-supplierName"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-supplierName", "Company Name", true));
      out.write("</td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_vendorName\" size=50 maxLength=40 value=\"");
      out.print(vendorRegister.getVendorName());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sicCode"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sicCode", "SIC Code", true));
      out.write("</td>\r\n\t\t\t<td><input name=\"VendorRegister_vendorSic\" size=15 maxLength=15 value=\"");
      out.print(vendorRegister.getVendorSic());
      out.write("\"></td>\r\n\t\t\t<td><i>**");
      out.print(DictionaryManager.getLabel(oid, "bb-sicInstructions", "Standard Industrial Classification (SIC) codes are described at", false));
      out.write(" <b><a  href=\"");
      out.print(DictionaryManager.getLabel(oid, "bb-sicLink", "http://www.osha.gov/cgi-bin/sic/sicser5", false));
      out.write("\" id=\"A1\" tabindex=-1 target=\"_blank\">");
      out.print(DictionaryManager.getLabel(oid, "bb-sicLink", "http://www.osha.gov/cgi-bin/sic/sicser5", false));
      out.write("</a></b>&nbsp;**</i></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-naicsCode"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-naicsCode", "NAICS Code(s)", true));
      out.write("</td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_vendorNaics\" size=15 maxLength=11 value=\"");
      out.print(vendorRegister.getVendorNaics());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-dunsNumber"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-dunsNumber", "DUNS Number", true));
      out.write("</td>\r\n\t\t\t<td><input name=\"VendorRegister_vendorDuns\" size=15 maxLength=11 value=\"");
      out.print(vendorRegister.getVendorDuns());
      out.write("\"></td>\r\n\t\t\t<td><i>**  You can look this number up at <b><a  href=\"http://www.dnb.com/\" id=\"A1\" tabindex=-1 target=\"_blank\">http://www.dnb.com/</a>&nbsp; **</i></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-addressLine2"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-addressLine2", "Address 2", true));
      out.write("</td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_addressLine2\" size=35 maxLength=40 value=\"");
      out.print(vendorRegister.getAddressLine2());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-addressLine3"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-addressLine3", "Address 3", true));
      out.write("</td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_addressLine3\" size=35 maxLength=40 value=\"");
      out.print(vendorRegister.getAddressLine3());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-addressLine4"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-addressLine4", "Address 4", true));
      out.write("</td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_addressLine4\" size=35 maxLength=40 value=\"");
      out.print(vendorRegister.getAddressLine4());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-city"));
      out.write(" align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-city", "City", true));
      out.write("</td>\r\n\t\t\t<td colspan=2>\r\n\t\t\t\t<table border=0 cellpadding=1 cellspacing=0 width=100%>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-city"));
      out.write("><input name=\"VendorRegister_addressCity\" size=35 maxLength=30 value=\"");
      out.print(vendorRegister.getAddressCity());
      out.write("\"></td>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-state"));
      out.write(" align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-state", "State", true));
      out.write("</td>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-state"));
      out.write("><input name=\"VendorRegister_addressState\" size=5 maxLength=15 value=\"");
      out.print(vendorRegister.getAddressState());
      out.write("\"></td>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-zip"));
      out.write(" align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-zip", "Postal Code", true));
      out.write("</td>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-zip"));
      out.write("><input name=\"VendorRegister_addressZipCode\" size=12 maxLength=15 value=\"");
      out.print(vendorRegister.getAddressZipCode());
      out.write("\"></td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-country"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-country", "Country", true));
      out.write("</td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_addressCountry\" size=35 maxLength=25 value=\"");
      out.print(vendorRegister.getAddressCountry());
      out.write("\" onchange=\"upperCase(this);\"></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-faxNumber"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-faxNumber", "Fax Number", true));
      out.write("</td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_vendorFaxNumber\" size=16 maxLenth=25 width=85px value=\"");
      out.print(vendorRegister.getVendorFaxNumber());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-einNumber"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-einNumber", "EIN", true));
      out.write("</td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_vendorEin\" maxLength=10 value=\"");
      out.print(vendorRegister.getVendorEin());
      out.write("\"></td>\r\n\t\t</tr>\r\n");
	if (oid.equals("QRI06P")) {
      out.write("\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN03"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN03", "UDF 03", true));
      out.write("</td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_vendorUdf3\" maxLength=10 value=\"");
      out.print(vendorRegister.getVendorUdf3());
      out.write("\"></td>\r\n\t\t</tr>\r\n");
	}
      out.write("\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-paymentTerms"));
      out.write(">\r\n\t\t\t<td align=right nowrap><a href=\"javascript: void(0);\" onClick=\"browseLookup('VendorRegister_vendorVendTerms', 'payment-terms');\">");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-paymentTerms", "Terms", true));
      out.write("</a></td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_vendorVendTerms\" maxLength=10 value=\"");
      out.print(vendorRegister.getVendorVendTerms());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-webAddress"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-webAddress", "Web Address", true));
      out.write("</td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_vendorWebAddress\" maxLength=60 value=\"");
      out.print(vendorRegister.getVendorWebAddress());
      out.write("\" size=50></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-commodity"));
      out.write(">\r\n\t\t\t<td align=right nowrap valign=top><a href=\"javascript: void(0);\" onClick=\"browseCommodities('VendorRegCommRel_commodityCode', 'commodity');\">");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-commodity", "Commodity", true));
      out.write("</a></td>\r\n\t\t\t<td colspan=2>\r\n\t\t\t\t<table border=0 cellpadding=2 cellspacing=0 id=\"commodityTable\">\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t\t<br>\r\n\t</td>\r\n</tr>\r\n<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-remitto"));
      out.write("><td colspan=6 class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" border=0 width=100% height=2px class=darkShadow></td></tr>\r\n<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-remitto"));
      out.write(">\r\n\t<td width=5px>&nbsp;</td>\r\n\t<td width=125px valign=top><b>Remit To Address</b></td>\r\n\t<td width=5px>&nbsp;</td>\r\n\t<td width=2px class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" order=0 width=2px height=100% class=darkShadow></td>\r\n\t<td width=5px>&nbsp;</td>\r\n\t<td>\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=0 cellpadding=2>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-rt-addressLine1"));
      out.write(">\r\n\t\t\t<td align=right nowrap width=175px>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-rt-nameAddress", "Name / Address", true));
      out.write("</td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_rtAddressLine1\" size=35 maxLength=40 value=\"");
      out.print(vendorRegister.getRtAddressLine1());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-rt-addressLine2"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-rt-addressLine2", "Address 2", true));
      out.write("</td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_rtAddressLine2\" size=35 maxLength=40 value=\"");
      out.print(vendorRegister.getRtAddressLine2());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-rt-addressLine3"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-rt-addressLine3", "Address 3", true));
      out.write("</td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_rtAddressLine3\" size=35 maxLength=40 value=\"");
      out.print(vendorRegister.getRtAddressLine3());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-rt-addressLine4"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-rt-addressLine4", "Address 4", true));
      out.write("</td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_rtAddressLine4\" size=35 maxLength=40 value=\"");
      out.print(vendorRegister.getRtAddressLine4());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-rt-city"));
      out.write(" align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-rt-city", "City", true));
      out.write("</td>\r\n\t\t\t<td colspan=2>\r\n\t\t\t\t<table border=0 cellpadding=1 cellspacing=0 width=100%>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-rt-city"));
      out.write("><input name=\"VendorRegister_rtAddressCity\" size=35 maxLength=30 value=\"");
      out.print(vendorRegister.getRtAddressCity());
      out.write("\"></td>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-rt-state"));
      out.write(" align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-rt-state", "State", true));
      out.write("</td>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-rt-state"));
      out.write("><input name=\"VendorRegister_rtAddressState\" size=5 maxLength=15 value=\"");
      out.print(vendorRegister.getRtAddressState());
      out.write("\"></td>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-rt-zip"));
      out.write(" align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-rt-zip", "Postal Code", true));
      out.write("</td>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-rt-zip"));
      out.write("><input name=\"VendorRegister_rtAddressZip\" size=12 maxLength=15 value=\"");
      out.print(vendorRegister.getRtAddressZip());
      out.write("\"></td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-sup-rt-country"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-sup-rt-country", "Country", true));
      out.write("</td>\r\n\t\t\t<td colspan=2><input name=\"VendorRegister_rtAddressCountry\" size=35 maxLength=25 value=\"");
      out.print(vendorRegister.getRtAddressCountry());
      out.write("\" onchange=\"upperCase(this);\"></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t\t<br>\r\n\t</td>\r\n</tr>\r\n<tr><td colspan=6 class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" border=0 width=100% height=2px class=darkShadow></td></tr>\r\n<tr>\r\n\t<td>&nbsp;</td>\r\n\t<td valign=top><br><b>Contact Information</b></td>\r\n\t<td>&nbsp;</td>\r\n\t<td width=2px class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" order=0 width=1px height=100% class=darkShadow></td>\r\n\t<td>&nbsp;</td>\r\n\t<td>\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-name"));
      out.write(">\r\n\t\t\t<td align=right nowrap width=150px>");
      out.print(DictionaryManager.getLabel(oid, "bb-cnt-key", "Key", true));
      out.write(' ');
      out.print(DictionaryManager.getLabel(oid, "bb-cnt-name", "Contact Name", true));
      out.write("</td>\r\n\t\t\t<td>\r\n\t\t\t\t<table border=0 cellpadding=1 cellspacing=0>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-salutation"));
      out.write(">\r\n\t\t\t\t\t\t<select name=\"VendorRegister_contactSalutation\">\r\n\t\t\t\t\t\t\t<option value=\"\"></option>\r\n\t\t\t\t\t\t\t<option value=\"Mr.\" ");
 if (vendorRegister.getContactSalutation().equals("Mr.")) {
      out.write("selected");
}
      out.write(">Mr.</option>\r\n\t\t\t\t\t\t\t<option value=\"Ms.\" ");
 if (vendorRegister.getContactSalutation().equals("Ms.")) {
      out.write("selected");
}
      out.write(">Ms.</option>\r\n\t\t\t\t\t\t\t<option value=\"Mrs.\" ");
 if (vendorRegister.getContactSalutation().equals("Mrs.")) {
      out.write("selected");
}
      out.write(">Mrs.</option>\r\n\t\t\t\t\t\t\t<option value=\"Miss\" ");
 if (vendorRegister.getContactSalutation().equals("Miss")) {
      out.write("selected");
}
      out.write(">Miss</option>\r\n\t\t\t\t\t\t</select>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-firstName"));
      out.write("><input name=\"VendorRegister_contactFirstName\" size=27 maxLength=20 value=\"");
      out.print(vendorRegister.getContactFirstName());
      out.write("\"></td>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-middleInitial"));
      out.write("><input  name=\"VendorRegister_contactMidInit\" size=3 maxLength=2 value=\"");
      out.print(vendorRegister.getContactMidInit());
      out.write("\"></td>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-lastName"));
      out.write("><input name=\"VendorRegister_contactLastName\" size=27 maxLength=20 value=\"");
      out.print(vendorRegister.getContactLastName());
      out.write("\"></td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-title"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-cnt-title", "Title", true));
      out.write("</td>\r\n\t\t\t<td><input name=\"VendorRegister_contactTitle\" size=27 maxLength=30 value=\"");
      out.print(vendorRegister.getContactTitle());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1>\r\n\t\t<tr>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber"));
      out.write(" align=right nowrap width=150px>");
      out.print(DictionaryManager.getLabel(oid, "bb-cnt-telephoneNumber", "Phone Number", true));
      out.write("</td>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber"));
      out.write("><input name=\"VendorRegister_contactPhoneNo\" size=18 maxLength=30\" value=\"");
      out.print(vendorRegister.getContactPhoneNo());
      out.write("\"></td>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-faxNumber"));
      out.write(" align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-cnt-faxNumber", "Fax Number", true));
      out.write("</td>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-faxNumber"));
      out.write("><input name=\"vendorRegister.getContactFaxNumber()\" size=18 maxLenth=25 width=85px value=\"");
      out.print(vendorRegister.getContactFaxNumber());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-emailAddress"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-cnt-emailAddress", "Email Address", true));
      out.write("</td>\r\n\t\t\t<td><input name=\"contactEmailAddr\" size=45 maxLength=50 value=\"");
      out.print(vendorRegister.getComp_id().getContactEmailAddr());
      out.write("\"> </td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-name"));
      out.write(">\r\n\t\t\t<td align=right nowrap width=150px>");
      out.print(DictionaryManager.getLabel(oid, "bb-cnt-alt", "Alternate", true));
      out.write(' ');
      out.print(DictionaryManager.getLabel(oid, "bb-cnt-name", "Contact Name", true));
      out.write("</td>\r\n\t\t\t<td>\r\n\t\t\t\t<table border=0 cellpadding=1 cellspacing=0>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-salutation"));
      out.write(">\r\n\t\t\t\t\t\t<select name=\"VendorRegister_altSalutation\">\r\n\t\t\t\t\t\t\t<option value=\"\"></option>\r\n\t\t\t\t\t\t\t<option value=\"Mr.\" ");
 if (vendorRegister.getAltSalutation().equals("Mr.")) {
      out.write("selected");
}
      out.write(">Mr.</option>\r\n\t\t\t\t\t\t\t<option value=\"Ms.\" ");
 if (vendorRegister.getAltSalutation().equals("Ms.")) {
      out.write("selected");
}
      out.write(">Ms.</option>\r\n\t\t\t\t\t\t\t<option value=\"Mrs.\" ");
 if (vendorRegister.getAltSalutation().equals("Mrs.")) {
      out.write("selected");
}
      out.write(">Mrs.</option>\r\n\t\t\t\t\t\t\t<option value=\"Miss\" ");
 if (vendorRegister.getAltSalutation().equals("Miss")) {
      out.write("selected");
}
      out.write(">Miss</option>\r\n\t\t\t\t\t\t</select>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-firstName"));
      out.write("><input name=\"VendorRegister_altFirstName\" size=27 maxLength=20 value=\"");
      out.print(vendorRegister.getAltFirstName());
      out.write("\"></td>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-middleInitial"));
      out.write("><input  name=\"VendorRegister_altMidInit\" size=3 maxLength=2 value=\"");
      out.print(vendorRegister.getAltMidInit());
      out.write("\"></td>\r\n\t\t\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-lastName"));
      out.write("><input name=\"VendorRegister_altLastName\" size=27 maxLength=20 value=\"");
      out.print(vendorRegister.getAltLastName());
      out.write("\"></td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-title"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-cnt-title", "Title", true));
      out.write("</td>\r\n\t\t\t<td><input name=\"VendorRegister_altTitle\" size=27 maxLength=30 value=\"");
      out.print(vendorRegister.getAltTitle());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1>\r\n\t\t<tr>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber"));
      out.write(" align=right nowrap width=150px>");
      out.print(DictionaryManager.getLabel(oid, "bb-cnt-telephoneNumber", "Phone Number", true));
      out.write("</td>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber"));
      out.write("><input name=\"VendorRegister_altPhoneNo\" size=18 maxLength=30\" value=\"");
      out.print(vendorRegister.getAltPhoneNo());
      out.write("\"></td>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-faxNumber"));
      out.write(" align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-cnt-faxNumber", "Fax Number", true));
      out.write("</td>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-faxNumber"));
      out.write("><input name=\"VendorRegister_altFaxNumber\" size=18 maxLenth=25 width=85px value=\"");
      out.print(vendorRegister.getAltFaxNumber());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-cnt-emailAddress"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-cnt-emailAddress", "Email Address", true));
      out.write("</td>\r\n\t\t\t<td><input name=\"VendorRegister_altEmailAddr\" size=45 maxLength=50 value=\"");
      out.print(vendorRegister.getAltEmailAddr());
      out.write("\"> </td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t\t<br>\r\n\t</td>\r\n</tr>\r\n<tr><td colspan=6 class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" border=0 width=100% height=2px class=darkShadow></td></tr>\r\n<tr>\r\n\t<td>&nbsp;</td>\r\n\t<td valign=top><br><b>Capacity Information</b></td>\r\n\t<td>&nbsp;</td>\r\n\t<td width=1px class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" order=0 width=1px height=100% class=darkShadow></td>\r\n\t<td>&nbsp;</td>\r\n\t<td>\r\n");

	List vendorUdf1List = (List) request.getAttribute("vendorUdf1List");
	if (vendorUdf1List != null && vendorUdf1List.size() > 0) { 
      out.write("\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 width=100% ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN01"));
      out.write(">\r\n\t\t<tr>\r\n\t\t\t<td nowrap ><b>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN01", "UDF 01", true));
      out.write(" / Check all that apply</b></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td width=\"10%\">&nbsp;</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t\t<input type=hidden name=\"VendorRegister_vendorUdf1\" value=\"");
      out.print(vendorRegister.getVendorUdf1());
      out.write("\">\r\n\t\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf1List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf1List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); 
      out.write("\r\n\t\t\t\t\t\t<tr><td><input name=\"as_vendorUdf1\" type=checkbox  value=\"");
      out.print(stdTablePK.getTableKey());
      out.write('"');
      out.write(' ');
if (vendorRegister.getVendorUdf1().indexOf(stdTablePK.getTableKey()) >= 0) {
      out.write("CHECKED");
}
      out.write(">&nbsp;");
      out.print(stdTable.getDescription());
      out.write("</td></tr>\r\n");
			fl = il;
				if (vendorUdf1List.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;
      out.write("\r\n\t\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
			}
			}
      out.write("\r\n\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	} else {
      out.write("\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN01"));
      out.write(">\r\n\t\t<tr><td><br></td></tr>\r\n\t\t<tr>\r\n\t\t\t<td align=right nowrap width=150px>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN01", "UDF 01", true));
      out.write("</td>\r\n\t\t\t<td><input type=text name=\"VendorRegister_vendorUdf1\" value=\"");
      out.print(vendorRegister.getVendorUdf1());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	}

	List vendorUdf2List = (List) request.getAttribute("vendorUdf2List");
	if (vendorUdf2List != null && vendorUdf2List.size() > 0) { 
      out.write("\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 width=100% ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN02"));
      out.write(">\r\n\t\t<tr>\r\n\t\t\t<td nowrap ><b>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN02", "UDF 02", true));
      out.write(" / Check all that apply</b></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td width=\"10%\">&nbsp;</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t\t<input type=hidden name=\"VendorRegister_vendorUdf2\" value=\"");
      out.print(vendorRegister.getVendorUdf2());
      out.write("\">\r\n\t\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf2List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf2List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); 
      out.write("\r\n\t\t\t\t\t\t<tr><td><input name=\"as_vendorUdf2\" type=checkbox  value=\"");
      out.print(stdTablePK.getTableKey());
      out.write('"');
      out.write(' ');
if (vendorRegister.getVendorUdf2().indexOf(stdTablePK.getTableKey()) >= 0) {
      out.write("CHECKED");
}
      out.write(">&nbsp;");
      out.print(stdTable.getDescription());
      out.write("</td></tr>\r\n");
			fl = il;
				if (vendorUdf2List.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;
      out.write("\r\n\t\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
			}
			}
      out.write("\r\n\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	} else {
      out.write("\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN02"));
      out.write(">\r\n\t\t<tr><td><br></td></tr>\r\n\t\t<tr>\r\n\t\t\t<td align=right nowrap width=150px>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN02", "UDF 02", true));
      out.write("</td>\r\n\t\t\t<td><input type=text name=\"VendorRegister_vendorUdf2\" value=\"");
      out.print(vendorRegister.getVendorUdf2());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	}

	if (!oid.equals("QRI06P")) {
		List vendorUdf3List = (List) request.getAttribute("vendorUdf3List");
		if (vendorUdf3List != null && vendorUdf3List.size() > 0) { 
      out.write("\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 width=100% ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN03"));
      out.write(">\r\n\t\t<tr>\r\n\t\t\t<td nowrap ><b>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN03", "UDF 03", true));
      out.write(" / Check all that apply</b></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td width=\"10%\">&nbsp;</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t\t<input type=hidden name=\"VendorRegister_vendorUdf3\" value=\"");
      out.print(vendorRegister.getVendorUdf3());
      out.write("\">\r\n\t\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf3List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf3List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); 
      out.write("\r\n\t\t\t\t\t\t<tr><td><input name=\"as_vendorUdf3\" type=checkbox  value=\"");
      out.print(stdTablePK.getTableKey());
      out.write('"');
      out.write(' ');
if (vendorRegister.getVendorUdf3().indexOf(stdTablePK.getTableKey()) >= 0) {
      out.write("CHECKED");
}
      out.write(">&nbsp;");
      out.print(stdTable.getDescription());
      out.write("</td></tr>\r\n");
			fl = il;
				if (vendorUdf3List.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;
      out.write("\r\n\t\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
			}
			}
      out.write("\r\n\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
		} else {
      out.write("\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN03"));
      out.write(">\r\n\t\t<tr><td><br></td></tr>\r\n\t\t<tr>\r\n\t\t\t<td align=right nowrap width=150px>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN03", "UDF 03", true));
      out.write("</td>\r\n\t\t\t<td><input type=text name=\"VendorRegister_vendorUdf3\" value=\"");
      out.print(vendorRegister.getVendorUdf3());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
		}
		}

	List vendorUdf4List = (List) request.getAttribute("vendorUdf4List");
	if (vendorUdf4List != null && vendorUdf4List.size() > 0) { 
      out.write("\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 width=100% ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN04"));
      out.write(">\r\n\t\t<tr>\r\n\t\t\t<td nowrap ><b>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN04", "UDF 04", true));
      out.write(" / Check all that apply</b></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td width=\"10%\">&nbsp;</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t\t<input type=hidden name=\"VendorRegister_vendorUdf4\" value=\"");
      out.print(vendorRegister.getVendorUdf4());
      out.write("\">\r\n\t\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf4List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf4List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); 
      out.write("\r\n\t\t\t\t\t\t<tr><td><input name=\"as_vendorUdf4\" type=checkbox  value=\"");
      out.print(stdTablePK.getTableKey());
      out.write('"');
      out.write(' ');
if (vendorRegister.getVendorUdf4().indexOf(stdTablePK.getTableKey()) >= 0) {
      out.write("CHECKED");
}
      out.write(">&nbsp;");
      out.print(stdTable.getDescription());
      out.write("</td></tr>\r\n");
			fl = il;
				if (vendorUdf4List.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;
      out.write("\r\n\t\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
			}
			}
      out.write("\r\n\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	} else {
      out.write("\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN04"));
      out.write(">\r\n\t\t<tr><td><br></td</tr>\r\n\t\t<tr>\r\n\t\t\t<td align=right nowrap width=150px>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN04", "UDF 04", true));
      out.write("</td>\r\n\t\t\t<td><input type=text name=\"VendorRegister_vendorUdf4\" value=\"");
      out.print(vendorRegister.getVendorUdf4());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	}

	List vendorUdf5List = (List) request.getAttribute("vendorUdf5List");
	if (vendorUdf5List != null && vendorUdf5List.size() > 0) { 
      out.write("\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 width=100% ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN05"));
      out.write(">\r\n\t\t<tr>\r\n\t\t\t<td nowrap ><b>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN05", "UDF 05", true));
      out.write(" / Check all that apply</b></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td width=\"10%\">&nbsp;</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t\t<input type=hidden name=\"VendorRegister_vendorUdf5\" value=\"");
      out.print(vendorRegister.getVendorUdf5());
      out.write("\">\r\n\t\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf5List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf5List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); 
      out.write("\r\n\t\t\t\t\t\t<tr><td><input name=\"as_vendorUdf5\" type=checkbox  value=\"");
      out.print(stdTablePK.getTableKey());
      out.write('"');
      out.write(' ');
if (vendorRegister.getVendorUdf5().indexOf(stdTablePK.getTableKey()) >= 0) {
      out.write("CHECKED");
}
      out.write(">&nbsp;");
      out.print(stdTable.getDescription());
      out.write("</td></tr>\r\n");
			fl = il;
				if (vendorUdf5List.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;
      out.write("\r\n\t\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
			}
			}
      out.write("\r\n\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	} else {
      out.write("\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN05"));
      out.write(">\r\n\t\t<tr><td><br></td></tr>\r\n\t\t<tr>\r\n\t\t\t<td align=right nowrap width=150px>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN05", "UDF 05", true));
      out.write("</td>\r\n\t\t\t<td><input type=text name=\"VendorRegister_vendorUdf5\" value=\"");
      out.print(vendorRegister.getVendorUdf5());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	}
      out.write("\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 width=100%>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN06subcategory"));
      out.write("<td colspan=5 nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN06subcategory", "", true));
      out.write("</td></tr>\r\n\t\t<tr>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN06"));
      out.write(" align=right nowrap width=150px>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN06", "UDF 06", true));
      out.write("</td>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN06"));
      out.write("><input name=\"VendorRegister_vendorUdf6\" value=\"");
      out.print(vendorRegister.getVendorUdf6());
      out.write("\" maxLength=15></td>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN07"));
      out.write(" align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN07", "UDF 07", true));
      out.write("</td>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN07"));
      out.write(" width=100px><input name=\"VendorRegister_vendorUdf7\" value=\"");
      out.print(vendorRegister.getVendorUdf7());
      out.write("\" maxLength=15></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n");
	if (!oid.equals("BSC04P")) {
      out.write("\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN08"));
      out.write(" align=right nowrap width=150px>>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN08", "UDF 08", true));
      out.write("</td>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN08"));
      out.write("><input name=\"VendorRegister_vendorUdf8\" value=\"");
      out.print(vendorRegister.getVendorUdf8());
      out.write("\" maxLength=15></td>\r\n");
	}
      out.write("\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN09"));
      out.write(" align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-VN09", "UDF 09", true));
      out.write("</td>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-VN09"));
      out.write(" width=100px><input name=\"VendorRegister_vendorUdf9\" value=\"");
      out.print(vendorRegister.getVendorUdf9());
      out.write("\" maxLength=15></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 width=100%>\r\n\t\t<tr>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-yearsInBusiness"));
      out.write(" align=right nowrap width=150px>");
      out.print(DictionaryManager.getLabel(oid, "bb-yearsInBusiness", "Years In Business", true));
      out.write("</td>\r\n\t\t\t<td ");
      out.print(HtmlWriter.isVisible(oid, "bb-yearsInBusiness"));
      out.write("><input name=\"VendorRegister_vendorYears\" value=\"");
      out.print(vendorRegister.getVendorYears());
      out.write("\" size = 10 maxLength=15 onchange=\"nfilter(this);\" style=\"text-align: right\"></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 ");
      out.print(HtmlWriter.isVisible(oid, "bb-refInformation"));
      out.write("\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-refInformation"));
      out.write("<td colspan=2><b>");
      out.print(DictionaryManager.getLabel(oid, "bb-refInformation", "Current / Previous Client Reference", false));
      out.write("</b></td></tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-refCompanyName"));
      out.write(">\r\n\t\t\t<td align=right nowrap width=150px>");
      out.print(DictionaryManager.getLabel(oid, "bb-refCompanyName", "Company Name", true));
      out.write("</td>\r\n\t\t\t<td><input type=text name=\"VendorRegister_refCompanyName\" size=75 maxLength=255 value=\"");
      out.print(vendorRegister.getRefCompanyName());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-refPhoneNumber"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-refPhoneNumber", "Phone Number", true));
      out.write("</td>\r\n\t\t\t<td><input type=text name=\"VendorRegister_refPhoneNumber\" size=75 maxLength=255 value=\"");
      out.print(vendorRegister.getRefPhoneNumber());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-refContactName"));
      out.write(">\r\n\t\t\t<td align=right nowrap>");
      out.print(DictionaryManager.getLabel(oid, "bb-refContactName", "Contact Name", true));
      out.write("</td>\r\n\t\t\t<td><input type=text name=\"VendorRegister_refContactName\" size=75 maxLength=255 value=\"");
      out.print(vendorRegister.getRefContactName());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t\t<br>\r\n\t</td>\r\n</tr>\r\n<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-ownershipType"));
      out.write("><td colspan=6 class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" border=0 width=100% height=2px class=darkShadow></td></tr>\r\n<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-ownershipType"));
      out.write(">\r\n\t<td>&nbsp;</td>\r\n\t<td valign=top><br><b>Business Ownership Information</b></td>\r\n\t<td>&nbsp;</td>\r\n\t<td width=2px class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" order=0 width=1px height=100% class=darkShadow></td>\r\n\t<td>&nbsp;</td>\r\n\t<td>\r\n");

	List ownershipTypesList = (List) request.getAttribute("ownershipTypeList");
	if (ownershipTypesList != null && ownershipTypesList.size() > 0){ 
      out.write("\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 width=100% ");
      out.print(HtmlWriter.isVisible(oid, "bb-ownershipType"));
      out.write(">\r\n\t\t<tr><td><br></td></tr>\r\n\t\t<tr>\r\n\t\t\t<td nowrap ><b>");
      out.print(DictionaryManager.getLabel(oid, "bb-ownershipType", "Ownership Type", true));
      out.write(" / Check all that apply</b></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td width=\"10%\">&nbsp;</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t\t<input type=hidden name=\"VendorRegister_ownershipType\" value=\"");
      out.print(vendorRegister.getOwnershipType());
      out.write("\">\r\n\t\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
	boolean b_new_column = true;
		float fl = 0;
		for (int il = 0; il < ownershipTypesList.size(); il++) {
			StdTable stdTable = (StdTable) ownershipTypesList.get(il);
			StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); 
      out.write("\r\n\t\t\t\t\t\t<tr><td><input name=\"as_ownershipType\" type=checkbox  value=\"");
      out.print(stdTablePK.getTableKey());
      out.write('"');
      out.write(' ');
if (vendorRegister.getOwnershipType().indexOf(stdTablePK.getTableKey()) >= 0) {
      out.write("CHECKED");
}
      out.write(">&nbsp;");
      out.print(stdTable.getDescription());
      out.write("</td></tr>\r\n");
			fl = il;
			if (ownershipTypesList.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;
      out.write("\r\n\t\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
			}
		}
      out.write("\r\n\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	} else {
      out.write("\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 ");
      out.print(HtmlWriter.isVisible(oid, "bb-ownershipType"));
      out.write(">\r\n\t\t<tr><td><br></td></tr>\r\n\t\t<tr>\r\n\t\t\t<td align=right>");
      out.print(DictionaryManager.getLabel(oid, "bb-ownershipType", "Ownership Type", true));
      out.write("</td>\r\n\t\t\t<td align=right><input type=text name=\"VendorRegister_ownershipType\" value=\"");
      out.print(vendorRegister.getOwnershipType());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	}

		List classificationTypeList = (List) request.getAttribute("classificationTypeList");
		if (classificationTypeList != null && classificationTypeList.size() > 0) { 
      out.write("\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 width=100% ");
      out.print(HtmlWriter.isVisible(oid, "bb-vendorClass"));
      out.write(">\r\n\t\t<tr>\r\n\t\t\t<td nowrap><b>");
      out.print(DictionaryManager.getLabel(oid, "bb-vendorClass", "Vendor Class", true));
      out.write(" / Check all that apply</b></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td width=\"10%\">&nbsp;</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t\t<input type=hidden name=\"VendorRegister_vendorClass\" value=\"");
      out.print(vendorRegister.getVendorClass());
      out.write("\">\r\n\t\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < classificationTypeList.size(); il++) {
				StdTable stdTable = (StdTable) classificationTypeList.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); 
      out.write("\r\n\t\t\t\t\t\t<tr><td><input name=\"as_vendorClass\" type=checkbox value=\"");
      out.print(stdTablePK.getTableKey());
      out.write('"');
      out.write(' ');
if (vendorRegister.getVendorClass().indexOf(stdTablePK.getTableKey()) >= 0) {
      out.write("CHECKED");
}
      out.write(">&nbsp;");
      out.print(stdTable.getDescription());
      out.write("</td></tr>\r\n");
			fl = il;
				if (classificationTypeList.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;
      out.write("\r\n\t\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
			}
			}
      out.write("\r\n\t\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	} else {
      out.write("\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 ");
      out.print(HtmlWriter.isVisible(oid, "bb-vendorClass"));
      out.write(">\r\n\t\t<tr>\r\n\t\t\t<td align=right>");
      out.print(DictionaryManager.getLabel(oid, "bb-vendorClass", "Vendor Class", true));
      out.write("</td>\r\n\t\t\t<td align=right><input type=text name=\"VendorRegister_vendorClass\" value=\"");
      out.print(vendorRegister.getVendorClass());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	}

	List diverseCertifiedOrgList = (List) request.getAttribute("diverseCertifiedOrgList");
	if (diverseCertifiedOrgList != null && diverseCertifiedOrgList.size() > 0) { 
      out.write("\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 width=100% ");
      out.print(HtmlWriter.isVisible(oid, "bb-diverseCertOrgs"));
      out.write(">\r\n\t\t<tr><td>");
      out.print(DictionaryManager.getLabel(oid, "bb-diverseCertOrgs", "Diverse Certified Organizations", true));
      out.write(" Check all that apply.</td></tr>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td width=\"10%\">&nbsp;</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t\t<input type=hidden name=\"VendorRegister_diverseCertOrgs\" value=\"");
      out.print(vendorRegister.getDiverseCertOrgs());
      out.write("\">\r\n\t\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
	boolean b_new_column = true;
		float fl = 0;
		for (int il = 0; il < diverseCertifiedOrgList.size(); il++) {
			StdTable stdTable = (StdTable) diverseCertifiedOrgList.get(il);
			StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); 
      out.write("\r\n\t\t\t\t\t\t<tr><td><input name=\"as_diverseCertOrg\" type=checkbox value=\"");
      out.print(stdTablePK.getTableKey());
      out.write('"');
      out.write(' ');
if (vendorRegister.getDiverseCertOrgs().indexOf(stdTablePK.getTableKey()) >= 0) {
      out.write("CHECKED");
}
      out.write(">&nbsp;");
      out.print(stdTable.getDescription());
      out.write("</td></tr>\r\n");
			fl = il;
			if (diverseCertifiedOrgList.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;
      out.write("\r\n\t\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t\t<td width=45% valign=top>\r\n\t\t\t\t\t\t<table width=100% border=0 cellpadding=0 cellspacing=0>\r\n");
			}
		}
      out.write("\r\n\t\t\t\t\t\t</table>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	} else {
      out.write("\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 ");
      out.print(HtmlWriter.isVisible(oid, "bb-diverseCertOrgs"));
      out.write(">\r\n\t\t<tr>\r\n\t\t\t<td align=right>");
      out.print(DictionaryManager.getLabel(oid, "bb-diverseCertOrgs", "Diverse Certified Organizations", true));
      out.write("</td>\r\n\t\t\t<td align=right><input type=text name=\"VendorRegister_diverseCertOrgs\" value=\"");
      out.print(vendorRegister.getDiverseCertOrgs());
      out.write("\"></td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	}
      out.write("\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1 ");
      out.print(HtmlWriter.isVisible(oid, "bb-vendorDiversityProgram"));
      out.write(">\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-vendorDiversityProgram"));
      out.write(">\r\n\t\t\t<td>Do you have a Supplier Diversity Program?</td>\r\n\t\t\t<td align=right width=25px><input type=radio name=\"VendorRegister_diversityProgram\" value=\"Y\" ");
 if (vendorRegister.getDiversityProgram().equalsIgnoreCase("Y")) {
      out.write("checked");
}
      out.write("></td>\r\n\t\t\t<td>Yes</td>\r\n\t\t\t<td align=right width=25px><input type=radio name=\"VendorRegister_diversityProgram\" value=\"N\" ");
 if (vendorRegister.getDiversityProgram().equalsIgnoreCase("N")) {
      out.write("checked");
}
      out.write("></td>\r\n\t\t\t<td>No</td>\r\n\t\t</tr>\r\n\t\t<tr ");
      out.print(HtmlWriter.isVisible(oid, "bb-vendorSubcontract"));
      out.write(">\r\n\t\t\t<td>Do you subcontract with diverse suppliers?</td>\r\n\t\t\t<td align=right width=25px><input type=radio name=\"VendorRegister_subcontract\" value=\"Y\" ");
 if (vendorRegister.getSubcontract().equalsIgnoreCase("Y")) {
      out.write("checked");
}
      out.write("></td>\r\n\t\t\t<td>Yes</td>\r\n\t\t\t<td align=right width=25px><input type=radio name=\"VendorRegister_subcontract\" value=\"N\" ");
 if (vendorRegister.getSubcontract().equalsIgnoreCase("N")) {
      out.write("checked");
}
      out.write("></td>\r\n\t\t\t<td>No</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t</td>\r\n</tr>\r\n<tr  ");
      out.print(HtmlWriter.isVisible(oid, "bb-certificationSection"));
      out.write("><td colspan=6 class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" border=0 width=100% height=2px class=darkShadow></td></tr>\r\n<tr  ");
      out.print(HtmlWriter.isVisible(oid, "bb-certificationSection"));
      out.write(">\r\n\t<td>&nbsp;</td>\r\n\t<td valign=top><br><b>Certification Statement</b></td>\r\n\t<td>&nbsp;</td>\r\n\t<td width=2px class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" order=0 width=1px height=100% class=darkShadow></td>\r\n\t<td>&nbsp;</td>\r\n\t<td>\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=1 cellpadding=1  ");
      out.print(HtmlWriter.isVisible(oid, "bb-certificationStatement"));
      out.write(">\r\n\t\t<tr>\r\n\t\t\t<td>");
      out.print(DictionaryManager.getLabel(oid, "bb-certificationStatement", "", true));
      out.write("</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	if (oid.equals("BSC04P")) {
      out.write("\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=0 cellpadding=2>\r\n\t\t<tr>\r\n\t\t\t<td valign=top align=right><input type=checkbox name=\"as_vendorUdf8\" value=\"Y\" ");
 if (vendorRegister.getVendorUdf8().equals("Y")) {
      out.write("checked");
}
      out.write("><input type=hidden name=\"VendorRegister_vendorUdf8\" value=\"");
      out.print(vendorRegister.getVendorUdf8());
      out.write("\"></td>\r\n\t\t\t<td>My company has not been excluded from federal procurement and non-procurement programs.</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n");
	}
      out.write("\r\n\t\t<br>\r\n\t\t<table border=0 cellspacing=0 cellpadding=2 class=\"requiredLabelHighlight\"  ");
      out.print(HtmlWriter.isVisible(oid, "bb-certificationCkboxStmt"));
      out.write(">\r\n\t\t<tr>\r\n\t\t\t<td class=\"requiredLabelHighlight\" valign=top align=right><input type=checkbox name=\"as_digitalSig\" value=\"Y\" ");
 if (vendorRegister.getDigitalSig().equals("Y")) {
      out.write("checked");
}
      out.write("><input type=hidden name=\"VendorRegister_digitalSig\" value=\"");
      out.print(vendorRegister.getDigitalSig());
      out.write("\"></td>\r\n\t\t\t<td class=\"requiredLabelHighlight\">");
      out.print(DictionaryManager.getLabel(oid, "bb-certificationCkboxStmt", "I HEREBY CERTIFY ALL THE INFORMATION PROVIDED ON THIS FORM TO BE TRUE AND ACCURATE.", true));
      out.write("</td>\r\n\t\t</tr>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t\t<br>\r\n\t</td>\r\n</tr>\r\n<tr><td colspan=6 class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" border=0 width=100% height=2px class=darkShadow></td></tr>\r\n</table>\r\n\r\n<br>\r\n\r\n<table border=0 cellpadding=2 cellspacing=0 width=680px>\r\n<tr>\r\n\t<td align=center width=50%>\r\n\t\t<div id=\"forward_link\"><a href=\"javascript: validateProfileInformation('VendorRegisterValidate'); void(0);\"><img class=button src=\"");
      out.print(contextPath);
      out.write("/images/button_submit.gif\" tabindex=85 border=0></a></div>\r\n\t</td>\r\n\t<td align=center width=50%>\r\n\t\t<a href=\"javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);\"><img class=button src=\"");
      out.print(contextPath);
      out.write("/images/button_cancel.gif\" tabindex=86 border=0></a>\r\n\t</td>\r\n</tr>\r\n</table>\r\n\r\n");
      out.write("<br><br>\r\n\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px vAlign=bottom>\r\n<hr size=0 color=black width=680px align=left>\r\n<tr>\r\n\t<td class=copyright width=33% align=left>&nbsp;Copyright &copy; ");
      out.print(HiltonUtility.getFormattedDate(new Date(), oid, "yyyy"));
      out.write("&nbsp;<a href=\"http://www.puridiom.com\" target=\"_blank\">Puridiom.com</a></td>\r\n\t<td class=copyright width=34% align=center><a href=\"http://www.puridiom.com/procurement/legal/index.html\" target=\"_blank\">Legal Notices</a></td>\r\n\t<td align=right class=copyright>&nbsp;Release 3.0.-datetime-</td>\r\n</tr>\r\n</table>\r\n\r\n</form>\r\n</body>\r\n</html>");
      out.write("\r\n\r\n<script value=JavaScript>\r\n<!--Hide Script\r\n\tfrm = document.purchaseform;\r\n\r\n\tvar currentRow = 0;\r\n\tvar maxRows = 0;\r\n\tvar certificationCheckRequired = ");
      out.print(DictionaryManager.isVisible(oid, "bb-certificationCkboxStmt") && DictionaryManager.isVisible(oid, "bb-certificationSection"));
      out.write(";\r\n\r\n\tsetCommodities();\r\n\r\n\tfunction setCommodities() {\r\n");
 for (int ic=0; ic < commodityList.size(); ic++) {
		String commodityCode = (String) commodityList.get(ic);

      out.write("\r\n\t\taddCommodity(\"");
      out.print(commodityList.get(ic));
      out.write('"');
      out.write(',');
      out.write('"');
      out.print(CommodityManager.getInstance().getCommodityDescription(oid, commodityCode));
      out.write("\");\r\n");
 }
      out.write("\r\n\t}\r\n\r\n\tfunction browseCommodities(fld, xml) {\r\n\t\tvar commodities = frm.elements.item(\"VendorRegCommRel_commodityCode\");\r\n\t\tvar rowSet = false;\r\n\t\tvar commodityCnt = 0;\r\n\r\n\t\tif (commodities != undefined) {\r\n\t\t\tcommodityCnt = commodities.length;\r\n\t\t\tif (commodities.length > 1 ) {\r\n\t\t\t\tfor (var i=0; i < commodities.length; i++) {\r\n\t\t\t\t\tvar commodity = frm.VendorRegCommRel_commodityCode[i].value;\r\n\t\t\t\t\tif (isEmpty(commodity)) {\r\n\t\t\t\t\t\tcurrentRow = i;\r\n\t\t\t\t\t\trowSet  = true;\r\n\t\t\t\t\t\tbreak;\r\n\t\t\t\t\t}\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n\t\tif (!rowSet) {\r\n\t\t\taddCommodity(\"\",\"\");\r\n\t\t\tcurrentRow = commodityCnt;\r\n\t\t\tcommodityCnt++;\r\n\t\t}\r\n\t\tmaxRows = commodityCnt;\r\n\t\tbrowseSchedule(fld, xml);\r\n\t}\r\n\r\n\tfunction addCommodity(commodityCode, desc)\r\n\t{\r\n\t\tvar myTable = document.getElementById(\"commodityTable\");\r\n\t\tvar myRow = createRow(myTable);\r\n\r\n\t\tcurrentRow = myTable.rows.length - 1;\r\n\r\n\t\tvar myCell = createCell(myRow);\r\n\t\tmyCell.innerHTML = \"<input name=\\\"VendorRegCommRel_commodityCode\\\" size=15 value=\\\"\" + commodityCode + \"\\\" onfocus=\\\"this.blur();\\\" class=disabledTxtBox readOnly><input type=text name=\\\"as_commodityDesc\\\" value=\\\"\" + desc + \"\\\" size=50 onfocus=\\\"this.blur();\\\" disabled>\";\r\n");
      out.write("\r\n\t\tmyCell = createCell(myRow);\r\n\t\tmyCell.innerHTML = \"<a href=\\\"javascript: deleteCommodity(\" + currentRow + \"); void(0);\\\"><img src=\\\"");
      out.print(contextPath);
      out.write("\\\\images\\\\delete.gif\\\" border=0></a>\";\r\n\t}\r\n\r\n\tfunction deleteCommodity(row) {\r\n\t\tvar myTable = document.getElementById(\"commodityTable\");\r\n\t\tvar rows = myTable.rows.length;\r\n\r\n\t\tif (rows == 0) {\r\n\t\t\treturn;\r\n\t\t}\r\n\t\telse if (rows > 1) {\r\n\t\t\tfor (var i = row; i < rows; i++) {\r\n\t\t\t\tvar commodityCode =  \"\";\r\n\t\t\t\tvar commodityDesc =  \"\";\r\n\r\n\t\t\t\tif ((i+1) < rows) {\r\n\t\t\t\t\tcommodityCode = frm.VendorRegCommRel_commodityCode[i + 1].value;\r\n\t\t\t\t\tcommodityDesc = frm.as_commodityDesc[i + 1].value;\r\n\t\t\t\t}\r\n\r\n\t\t\t\tfrm.VendorRegCommRel_commodityCode[i].value = commodityCode;\r\n\t\t\t\tfrm.as_commodityDesc[i].value = commodityDesc;\r\n\t\t\t}\r\n\t\t\tfrm.VendorRegCommRel_commodityCode[rows - 1].value = \"\";\r\n\t\t\tfrm.as_commodityDesc[rows - 1].value = \"\";\r\n\t\t} else {\r\n\t\t\tfrm.VendorRegCommRel_commodityCode.value = \"\";\r\n\t\t\tfrm.as_commodityDesc.value = \"\";\r\n\t\t}\r\n\t}\r\n\r\n/*\tfunction browseCommodities () {\r\n\t\tvar selected = \"\";\r\n\t\tvar selectCnt = 0;\r\n\t\tvar args = \"table=commodities\";\r\n\r\n\t\tfor (var i = 0; i < 5; i++) {\r\n\t\t\tif ( !isEmpty(frm.VendorCommRel_commodityCode[i].value) ) {\r\n");
      out.write("\t\t\t\tif ( selected.length > 0 ) {\r\n\t\t\t\t\tselected = selected + \"\\u0008\" + frm.VendorCommRel_commodityCode[i].value;\r\n\t\t\t\t}\r\n\t\t\t\telse {\r\n\t\t\t\t\tselected = frm.VendorCommRel_commodityCode[i].value;\r\n\t\t\t\t}\r\n\t\t\t\tselectCnt++;\r\n\t\t\t}\r\n\t\t}\r\n\t\targs = args + \"&selected=\" + selected + \"&selectCnt=\" + selectCnt;\r\n\t\tbrowseLookup('temp_commodity_code', 'commodity');\r\n\t}\r\n*/\r\n\tfunction setCommodityCodes() {\r\n\t\tvar myTable = document.getElementById(\"commodityTable\");\r\n\t\tvar rows = myTable.rows.length;\r\n\r\n\t\t// make sure there are no duplicates\r\n\t\tif (rows > 1) {\r\n\t\t\tvar codesEntered = \"\";\r\n\t\t\tfor (var i=rows - 1; i >= 0; i--) {\r\n\t\t\t\tvar tempCode = frm.VendorRegCommRel_commodityCode[i].value;\r\n\r\n\t\t\t\tif (isEmpty(tempCode) || codesEntered.indexOf(\"[\" + tempCode + \"]\") >= 0) {\r\n\t\t\t\t\tdeleteCommodity(i);\r\n\t\t\t\t} else {\r\n\t\t\t\t\tcodesEntered = codesEntered + \"[\" + tempCode + \"]\";\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n\t}\r\n\r\n\tfunction setBusinessType() {\r\n\t\tif (frm.VendorRegister_businessType) {\r\n\t\t\tvar businessTypes = frm.elements.item(\"as_businessType\");\r\n\t\t\tif (businessTypes != undefined) {\r\n");
      out.write("\t\t\t\tif (businessTypes.length > 1 ) {\r\n\t\t\t\t\tfrm.VendorRegister_businessType.value = \"\";\r\n\t\t\t\t\tfor (var i=0; i < frm.elements.item(\"as_businessType\").length; i++){\r\n\t\t\t\t\t\tif (frm.as_businessType[i].checked) {\r\n\t\t\t\t\t\t\tfrm.VendorRegister_businessType.value = frm.VendorRegister_businessType.value + frm.as_businessType[i].value;\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t}\r\n\t\t\t\t} else {\r\n\t\t\t\t\tif (frm.as_businessType.checked) {\r\n\t\t\t\t\t\tfrm.VendorRegister_businessType.value = frm.as_businessType.value;\r\n\t\t\t\t\t}\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n\t}\r\n\r\n\tfunction setVendorClass() {\r\n\t\tif (frm.VendorRegister_vendorClass) {\r\n\t\t\tvar vendorClasses = frm.elements.item(\"as_vendorClass\");\r\n\t\t\tif (vendorClasses != undefined) {\r\n\t\t\t\tif (vendorClasses.length > 1 ) {\r\n\t\t\t\t\tfrm.VendorRegister_vendorClass.value = \"\";\r\n\t\t\t\t\tfor (var i=0; i < frm.elements.item(\"as_vendorClass\").length; i++){\r\n\t\t\t\t\t\tif (frm.as_vendorClass[i].checked) {\r\n\t\t\t\t\t\t\tfrm.VendorRegister_vendorClass.value = frm.VendorRegister_vendorClass.value + frm.as_vendorClass[i].value;\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t}\r\n\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\tif (frm.as_vendorClass.checked) {\r\n\t\t\t\t\t\tfrm.VendorRegister_vendorClass.value = frm.as_vendorClass.value;\r\n\t\t\t\t\t}\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n\t}\r\n\r\n\tfunction setOwnershipType() {\r\n\t\tif (frm.VendorRegister_ownershipType) {\r\n\t\t\tvar ownershipTypes = frm.elements.item(\"as_ownershipType\");\r\n\t\t\tif (ownershipTypes != undefined) {\r\n\t\t\t\tif (ownershipTypes.length > 1 ) {\r\n\t\t\t\t\tfrm.VendorRegister_ownershipType.value = \"\";\r\n\t\t\t\t\tfor (var i=0; i < frm.elements.item(\"as_ownershipType\").length; i++){\r\n\t\t\t\t\t\tif (frm.as_ownershipType[i].checked) {\r\n\t\t\t\t\t\t\tfrm.VendorRegister_ownershipType.value = frm.VendorRegister_ownershipType.value + frm.as_ownershipType[i].value;\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t}\r\n\t\t\t\t} else {\r\n\t\t\t\t\tif (frm.as_ownershipType.checked) {\r\n\t\t\t\t\t\tfrm.VendorRegister_ownershipType.value = frm.as_ownershipType.value;\r\n\t\t\t\t\t}\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n\t}\r\n\r\n\tfunction setDiverseCertOrgs() {\r\n\t\tif (frm.VendorRegister_diverseCertOrgs) {\r\n\t\t\tvar diverseCertOrgs = frm.elements.item(\"as_diverseCertOrg\");\r\n\t\t\tif (diverseCertOrgs != undefined) {\r\n\t\t\t\tif (diverseCertOrgs.length > 1) {\r\n");
      out.write("\t\t\t\t\tfrm.VendorRegister_diverseCertOrgs.value = \"\";\r\n\t\t\t\t\tfor (var i=0; i < frm.elements.item(\"as_diverseCertOrg\").length; i++){\r\n\t\t\t\t\t\tif (frm.as_diverseCertOrg[i].checked) {\r\n\t\t\t\t\t\t\tfrm.VendorRegister_diverseCertOrgs.value = frm.VendorRegister_diverseCertOrgs.value + frm.as_diverseCertOrg[i].value;\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t}\r\n\t\t\t\t} else {\r\n\t\t\t\t\tif (frm.as_diverseCertOrg.checked) {\r\n\t\t\t\t\t\tfrm.VendorRegister_diverseCertOrgs.value = frm.as_diverseCertOrg.value;\r\n\t\t\t\t\t}\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n\t}\r\n\r\n\tfunction setVendorUdf1() {\r\n\t\tif (frm.VendorRegister_vendorUdf1) {\r\n\t\t\tvar vendorUdfs = frm.elements.item(\"as_vendorUdf1\");\r\n\t\t\tif (vendorUdfs != undefined) {\r\n\t\t\t\tif (vendorUdfs.length > 1 ) {\r\n\t\t\t\t\tfrm.VendorRegister_vendorUdf1.value = \"\";\r\n\t\t\t\t\tfor (var i=0; i < frm.elements.item(\"as_vendorUdf1\").length; i++){\r\n\t\t\t\t\t\tif (frm.as_vendorUdf1[i].checked) {\r\n\t\t\t\t\t\t\tfrm.VendorRegister_vendorUdf1.value = frm.VendorRegister_vendorUdf1.value + frm.as_vendorUdf1[i].value;\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t}\r\n\t\t\t\t} else {\r\n\t\t\t\t\tif (frm.as_vendorUdf1.checked) {\r\n");
      out.write("\t\t\t\t\t\tfrm.VendorRegister_vendorUdf1.value = frm.as_vendorUdf1.value;\r\n\t\t\t\t\t}\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n\t}\r\n\r\n\tfunction setVendorUdf2() {\r\n\t\tif (frm.VendorRegister_vendorUdf2) {\r\n\t\t\tvar vendorUdfs = frm.elements.item(\"as_vendorUdf2\");\r\n\t\t\tif (vendorUdfs != undefined) {\r\n\t\t\t\tif (vendorUdfs.length > 1 ) {\r\n\t\t\t\t\tfrm.VendorRegister_vendorUdf2.value = \"\";\r\n\t\t\t\t\tfor (var i=0; i < frm.elements.item(\"as_vendorUdf2\").length; i++){\r\n\t\t\t\t\t\tif (frm.as_vendorUdf2[i].checked) {\r\n\t\t\t\t\t\t\tfrm.VendorRegister_vendorUdf2.value = frm.VendorRegister_vendorUdf2.value + frm.as_vendorUdf2[i].value;\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t}\r\n\t\t\t\t} else {\r\n\t\t\t\t\tif (frm.as_vendorUdf2.checked) {\r\n\t\t\t\t\t\tfrm.VendorRegister_vendorUdf2.value = frm.as_vendorUdf2.value;\r\n\t\t\t\t\t}\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n\t}\r\n\r\n\tfunction setVendorUdf3() {\r\n\t\tif (frm.VendorRegister_vendorUdf3) {\r\n\t\t\tvar vendorUdfs = frm.elements.item(\"as_vendorUdf3\");\r\n\t\t\tif (vendorUdfs != undefined) {\r\n\t\t\t\tif (vendorUdfs.length > 1 ) {\r\n\t\t\t\t\tfrm.VendorRegister_vendorUdf3.value = \"\";\r\n\t\t\t\t\tfor (var i=0; i < frm.elements.item(\"as_vendorUdf3\").length; i++){\r\n");
      out.write("\t\t\t\t\t\tif (frm.as_vendorUdf3[i].checked) {\r\n\t\t\t\t\t\t\tfrm.VendorRegister_vendorUdf3.value = frm.VendorRegister_vendorUdf3.value + frm.as_vendorUdf3[i].value;\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t}\r\n\t\t\t\t} else {\r\n\t\t\t\t\tif (frm.as_vendorUdf3.checked) {\r\n\t\t\t\t\t\tfrm.VendorRegister_vendorUdf3.value = frm.as_vendorUdf3.value;\r\n\t\t\t\t\t}\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n\t}\r\n\r\n\tfunction setVendorUdf4() {\r\n\t\tif (frm.VendorRegister_vendorUdf4) {\r\n\t\t\tvar vendorUdfs = frm.elements.item(\"as_vendorUdf4\");\r\n\t\t\tif (vendorUdfs != undefined) {\r\n\t\t\t\tif (vendorUdfs.length > 1 ) {\r\n\t\t\t\t\tfrm.VendorRegister_vendorUdf4.value = \"\";\r\n\t\t\t\t\tfor (var i=0; i < frm.elements.item(\"as_vendorUdf4\").length; i++){\r\n\t\t\t\t\t\tif (frm.as_vendorUdf4[i].checked) {\r\n\t\t\t\t\t\t\tfrm.VendorRegister_vendorUdf4.value = frm.VendorRegister_vendorUdf4.value + frm.as_vendorUdf4[i].value;\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t}\r\n\t\t\t\t} else {\r\n\t\t\t\t\tif (frm.as_vendorUdf4.checked) {\r\n\t\t\t\t\t\tfrm.VendorRegister_vendorUdf4.value = frm.as_vendorUdf4.value;\r\n\t\t\t\t\t}\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n\t}\r\n\r\n\tfunction setVendorUdf5() {\r\n\t\tif (frm.VendorRegister_vendorUdf5) {\r\n");
      out.write("\t\t\tvar vendorUdfs = frm.elements.item(\"as_vendorUdf5\");\r\n\t\t\tif (vendorUdfs != undefined) {\r\n\t\t\t\tif (vendorUdfs.length > 1 ) {\r\n\t\t\t\t\tfrm.VendorRegister_vendorUdf5.value = \"\";\r\n\t\t\t\t\tfor (var i=0; i < frm.elements.item(\"as_vendorUdf5\").length; i++){\r\n\t\t\t\t\t\tif (frm.as_vendorUdf5[i].checked) {\r\n\t\t\t\t\t\t\tfrm.VendorRegister_vendorUdf5.value = frm.VendorRegister_vendorUdf5.value + frm.as_vendorUdf5[i].value;\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t}\r\n\t\t\t\t} else {\r\n\t\t\t\t\tif (frm.as_vendorUdf5.checked) {\r\n\t\t\t\t\t\tfrm.VendorRegister_vendorUdf5.value = frm.as_vendorUdf5.value;\r\n\t\t\t\t\t}\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n\t}\r\n\r\n\tfunction setVendorUdf8() {\r\n\t\tif (frm.VendorRegister_vendorUdf8) {\r\n\t\t\tvar vendorUdfs = frm.elements.item(\"as_vendorUdf8\");\r\n\t\t\tif (vendorUdfs != undefined) {\r\n\t\t\t\tif (vendorUdfs.length > 1 ) {\r\n\t\t\t\t\tfrm.VendorRegister_vendorUdf8.value = \"\";\r\n\t\t\t\t\tfor (var i=0; i < frm.elements.item(\"as_vendorUdf8\").length; i++){\r\n\t\t\t\t\t\tif (frm.as_vendorUdf8[i].checked) {\r\n\t\t\t\t\t\t\tfrm.VendorRegister_vendorUdf8.value = frm.Vendor_vendUdf8.value + frm.as_vendorUdf8[i].value;\r\n");
      out.write("\t\t\t\t\t\t}\r\n\t\t\t\t\t}\r\n\t\t\t\t} else {\r\n\t\t\t\t\tif (frm.as_vendorUdf8.checked) {\r\n\t\t\t\t\t\tfrm.VendorRegister_vendorUdf8.value = frm.as_vendorUdf8.value;\r\n\t\t\t\t\t}\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n\t}\r\n\r\n\tfunction setDigitalSig() {\r\n\t\tif (frm.VendorRegister_digitalSig) {\r\n\t\t\tvar digitalSigs = frm.elements.item(\"as_digitalSig\");\r\n\t\t\tif (digitalSigs != undefined) {\r\n\t\t\t\tif (digitalSigs.length > 1 ) {\r\n\t\t\t\t\tfrm.VendorRegister_digitalSig.value = \"\";\r\n\t\t\t\t\tfor (var i=0; i < frm.elements.item(\"as_digitalSig\").length; i++){\r\n\t\t\t\t\t\tif (frm.as_digitalSig[i].checked) {\r\n\t\t\t\t\t\t\tfrm.VendorRegister_digitalSig.value = frm.VendorRegister_digitalSig.value + frm.as_digitalSig[i].value;\r\n\t\t\t\t\t\t}\r\n\t\t\t\t\t}\r\n\t\t\t\t} else {\r\n\t\t\t\t\tif (frm.as_digitalSig.checked) {\r\n\t\t\t\t\t\tfrm.VendorRegister_digitalSig.value = frm.as_digitalSig.value;\r\n\t\t\t\t\t}\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n\t}\r\n\r\n\tfunction uploadDocs() {\r\n\t\tpopupParameters = \"VendorDocument_icRfqHeader=0;VendorDocument_vendorId=");
      out.print(user.getVendorId());
      out.write("\";\r\n\t\tdoSubmitToLookup('/user/supplier_attachments.jsp', 'VendorDocumentRetrieveByVendor', 'width=700', 'height=350');\r\n\t}\r\n\r\n\tfunction submitProfileInformation() {\r\n\t\tdoSubmit('/user/prequalification_complete.jsp', 'VendorRegisterUpdateByEmail');\r\n\t}\r\n\r\n//-->\r\n</script>\r\n\r\n");

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
