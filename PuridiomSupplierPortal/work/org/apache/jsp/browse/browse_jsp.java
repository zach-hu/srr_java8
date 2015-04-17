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
import com.tsa.puridiom.browse.*;
import com.tsa.puridiom.entity.RfqHeader;

public final class browse_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  static {
    _jspx_dependants = new java.util.Vector(9);
    _jspx_dependants.add("/system/prevent_caching.jsp");
    _jspx_dependants.add("/system/header.jsp");
    _jspx_dependants.add("/system/context_path.jsp");
    _jspx_dependants.add("/system/stylesheet_link.jsp");
    _jspx_dependants.add("/system/header_menu_options.jsp");
    _jspx_dependants.add("/browse/browse_form.jsp");
    _jspx_dependants.add("/browse/browse_pageform.jsp");
    _jspx_dependants.add("/browse/browse_page_nav.jsp");
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
      out.write('\r');
      out.write('\n');
      out.write("\r\n\r\n\r\n<script language='Javascript1.2' src=\"");
      out.print(contextPath);
      out.write("/scripts/browse.js\"></script>\r\n<script language='Javascript1.2' src=\"");
      out.print(contextPath);
      out.write("/scripts/dynamicTables.js\"></script>\r\n");

		String browseName = (String) request.getAttribute("browseName");
		BrowseObject browseObject = (BrowseObject) request.getAttribute("browseObject");
		BrowseColumn[] labels = browseObject.getLabels();
		List list = (List) request.getAttribute("browseList");
		int	ipageSize = browseObject.getPageSize();
		int	ipageCount = 1;
		int	ibegin = 0;
		int	iend = 0;
		int	irows = 0;

		if (browseName.indexOf("rfq-bidboard-posts") >= 0) {
			//Remove duplicate Rfq records
			int totalRows = list.size();
			Map existingRows = new HashMap();

			for (int il = totalRows - 1; il >= 0; il--) {
				Object object = list.get(il);
				if (object instanceof Object[]) {
					Object objArray[] = (Object[]) object;
					for (int ioa=0; ioa < objArray.length; ioa++) {
						if (objArray[ioa] instanceof RfqHeader) {
							RfqHeader rfqHeader = (RfqHeader) objArray[ioa];
							if (existingRows.containsKey(rfqHeader.getRfqNumber())) {
								list.remove(il);
							} else {
								existingRows.put(rfqHeader.getRfqNumber(), String.valueOf(il));
							}
							break;
						}
					}
				}

/*				for (int i=0; i < labels.length; i++) {
					BrowseColumn column = (BrowseColumn) displayList.get(i);

					if (column.getColumnName().equals("RfqHeader_rfqNumber")) {
						if (existingRows.containsKey(column.getValue())) {
							list.remove(il);
						} else {
							existingRows.put(column.getValue(), String.valueOf(il));
						}
						break;
					}
				}
*/
			}
		}

		if (ipageSize == 0) {
			ipageSize = 10;
		}
		if (list != null) {
			irows = list.size();
		}
		if (irows > 0) {
			ibegin = 1;
			iend = irows;
		}
		if (irows > ipageSize) {
			iend = ipageSize;
		}
		if (ipageSize > 0) {
			ipageCount = ((irows - 1) / browseObject.getPageSize()) + 1;
		}

		StringBuffer sbHiddenFields = new StringBuffer();
		StringBuffer sbOriginalFilterFields = new StringBuffer();
		StringBuffer sbFilterFields = new StringBuffer();
		StringBuffer sbOriginalFilterText = new StringBuffer();
		StringBuffer sbFilterText = new StringBuffer();
		StringBuffer sbSortOptions = new StringBuffer();
		StringBuffer sbFilterOptions = new StringBuffer();
		String	sortedColumn = "";
		List filters = browseObject.getBrowseFilters();
		Map labelsMap = new HashMap();
		BrowseColumn browseColumns[] = browseObject.getBrowseColumns();
		boolean	lbFirst = true;

		for (int i=0; i < labels.length; i++) {
			BrowseColumn column = labels[i];

			if (column.getLabel() != null && column.getLabel().length() > 0 && !column.getType().equals("Input") && !column.getClassName().equals("Input") && !column.getType().equals("Checkbox")) {
				labelsMap.put(column.getColumnName(), column.getLabel());

				if (column.getColumnName().equals(sortedColumn)) {
					sbSortOptions.append("<option value=" + '"' + column.getColumnName() + '"' + " selected" + ">" + column.getLabel() + "</option>");
				} else {
					sbSortOptions.append("<option value=" + '"' + column.getColumnName() + '"' + ">" + column.getLabel() + "</option>");
				}
				if (lbFirst) {
					lbFirst = false ;
					sbFilterOptions.append("<option value=" + '"' + column.getColumnName() + '"' + " selected" + ">" + column.getLabel() + "</option>");
				} else {
					sbFilterOptions.append("<option value=" + '"' + column.getColumnName() + '"' + ">" + column.getLabel() + "</option>");
				}
			}
		}

		if (filters != null) {
			for (int ix=0; ix < filters.size(); ix++) {
				BrowseFilter filter = (BrowseFilter) filters.get(ix);
				String	colname = filter.getColumnName().replace('.', '_');
				String	sort = filter.getSort();
				StringBuffer sb = new StringBuffer("");

				sb.append("<input type=hidden name=" + '"' + "colname" + '"' + " value=" + '"' + colname + '"' + ">");
				sb.append("<input type=hidden name=" + '"' + "filter_txt" + '"' + " value=" + '"' + filter.getValue() + '"' + ">");
				sb.append("<input type=hidden name=" + '"' + "operator" + '"' + " value=" + '"' + filter.getOperator() + '"' + ">");
				sb.append("<input type=hidden name=" + '"' + "logicalOperator" + '"' + " value=" + '"' + filter.getLogicalOperator() + '"' + ">");
				sb.append("<input type=hidden name=" + '"' + "sort" + '"' + " value=" + '"' + filter.getSort() + '"' + ">");

				if (sort != null && !sort.equalsIgnoreCase("N") && sortedColumn.length() == 0) {
					sortedColumn = colname;
				}
				if (filter.isOriginalFilter()) {
					sb.append("<input type=hidden name=" + '"' + "originalFilter" + '"' + " value=" + '"' + "Y" + '"' + ">");

					sbOriginalFilterFields.append(sb);

					if (filter.getValue() != null && filter.getValue().length() > 0) {
						if (labelsMap.containsKey(colname) && !HiltonUtility.isEmpty((String) labelsMap.get(colname))) {
							if (sbOriginalFilterText.length() > 0) {
								sbOriginalFilterText.append(filter.getLogicalOperator() + " ");
							}
							sbOriginalFilterText.append(labelsMap.get(colname) + " " + filter.getOperator() + " '" + filter.getValue() + "' ");
						} else if (browseName.indexOf("rfq-bidboard-posts") >= 0 && colname.equals("RfqVendor_id_vendorId") && filter.getValue().equalsIgnoreCase(user.getVendorId())) {
							if (sbFilterText.length() > 0) {
								sbFilterText.append(filter.getLogicalOperator() + " ");
							}
							sbOriginalFilterText.append("Solicitations I have bid on.");
						}
					}
				} else {
					sb.append("<input type=hidden name=" + '"' + "originalFilter" + '"' + " value=" + '"' + "N" + '"' + ">");

					sbFilterFields.append(sb);

					if (filter.getValue() != null && filter.getValue().length() > 0) {
						if (labelsMap.containsKey(colname) && !HiltonUtility.isEmpty((String) labelsMap.get(colname))) {
							if (sbFilterText.length() > 0) {
								sbFilterText.append(filter.getLogicalOperator() + " ");
							}
							sbFilterText.append(labelsMap.get(colname) + " " + filter.getOperator() + " '" + filter.getValue() + "' ");
						}
					}
				}
			}
		}

		String	allowBrowse = (String) request.getAttribute("allowBrowse");
		if (allowBrowse == null) {
			allowBrowse = "true";
		}

		String	s_req_type = (String) request.getAttribute("RequisitionHeader_requisitionType");
		String	s_po_type = (String) request.getAttribute("PoHeader_poType");
		if (s_req_type == null)	{	s_req_type = "";	}
		if (s_po_type == null)	{	s_po_type = "";	}
		String	s_today = HiltonUtility.getFormattedDate(new Date(), oid,  "MM-dd-yyyy  HH:mm:ss");

      out.write("\r\n\r\n<input type=HIDDEN name=allowBrowse value=");
      out.print(allowBrowse);
      out.write(">\r\n<input type=HIDDEN name=fromBrowse value=");
      out.print(browseName);
      out.write(">\r\n\r\n<table width=680px cellpadding=0 cellspacing=0 border=0>\r\n<tr>\r\n\t<td valign=top width=150px height=30px>\r\n\t\t<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>\r\n\t\t<tr><td height=1px class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" width=\"1\" height=\"1px\" /></td></tr>\r\n\t\t<tr><td nowrap class=hdr12 valign=middle><div style=\"margin-left:10px; margin-right:10px\" class=hdr12 id=\"browseTitle\">");
      out.print(browseObject.getTitle());
      out.write("</div></td></tr>\r\n\t\t</table>\r\n\t</td>\r\n\t<td width=30px valign=bottom><img class=hdr12 src=\"");
      out.print(contextPath);
      out.write("/images/angle.gif\" width=\"30\" height=\"31\" /></td>\r\n\t<td valign=bottom align=right>\r\n\t\t<div id=\"filterTextDisplay\">\r\n\t\t<table border=0 cellspacing=0 cellpadding=0 border=0>\r\n\t\t<tr><td>&nbsp;<b>Original Filter:</b> ");
      out.print(sbOriginalFilterText);
      out.write("</td></tr>\r\n\t\t<tr><td>&nbsp;<b>Current Filter:</b>  ");
      out.print(sbFilterText);
      out.write("</td></tr>\r\n\t\t</table>\r\n\t\t</div>\r\n\t\t<table cellpadding=\"0\" cellspacing=\"0\" border=0>\r\n\t\t\t<tr><td width=1000px height=1px class=lightShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" width=\"1\" height=\"1\" /></td></tr>\r\n\t\t\t<tr><td height=2px class=darkShadow><img src=\"");
      out.print(contextPath);
      out.write("/images/none.gif\" width=\"1\" height=\"2\" /></td></tr>\r\n\t\t</table>\r\n\t</td>\r\n</tr>\r\n</table>\r\n\r\n<br>\r\n\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px>\r\n<tr><td nowrap>&nbsp;&nbsp;<b>Showing Records <span id=\"beginRecord\">");
      out.print(ibegin);
      out.write("</span> - <span id=\"endRecord\">");
      out.print(iend);
      out.write("</span> of ");
      out.print(list.size());
      out.write("</b></td></tr>\r\n</table>\r\n\r\n<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=680px>\r\n<tr>\r\n\t<td width=5px>&nbsp;</td>\r\n\t<td width=670px class=browseHdrDk align=center valign=top>\r\n\t\t<table border=0 cellspacing=0 cellpadding=2 width=665px>\r\n\t\t<tr>\r\n");
	for (int i=0; i < labels.length; i++) {
			int	iwidth = 10;
			if (labels[i].getSize() > 0) {	iwidth = labels[i].getSize();	}

      out.write("\r\n\t\t\t<td nowrap height=18px class=browseHdrDk width=");
      out.print(iwidth);
      out.write("%><a href=\"javascript: sortMe('");
      out.print(labels[i].getColumnName());
      out.write("'); void(0);\" class=browseHdrDk>");
      out.print(labels[i].getLabel());
      out.write("</a></td>\r\n");
	}
      out.write("\r\n\t\t</tr>\r\n\t\t</table>\r\n\t\t<div id=\"browseBorder\" class=browseHdrDk style=\"border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; height: ");
      out.print(((ipageSize) * 18) + 20);
      out.write("px; align:center; overflow-y:visible; overflow-x:auto;\">\r\n\t\t<div id=\"noRecords\" style=\"visibility: hidden; display: none;\">\r\n\t\t<table border=0 cellspacing=0 cellpadding=2 width=665px class=browseRow>\r\n\t\t<tr><td><b>There were no records found in the database matching your criteria.</b></td></tr>\r\n\t\t</table>\r\n\t\t</div>\r\n");

		int icurrentRow = 0;
		int icurrentPage = 1;

		while (icurrentPage <= ipageCount) {
			int inextEnd = icurrentRow + ipageSize;
			if (inextEnd > irows) {
				inextEnd = irows;
			}

      out.write("\r\n\t\t<div id=\"page");
      out.print(icurrentPage);
      out.write("\" style=\"visibility: hidden; display: none;\">\r\n\t\t\t<table id=browseRows border=0 cellspacing=0 cellpadding=1 width=665px class=browseRow>\r\n");
		String rowClass = "browseRow";
			for (int il = icurrentRow; il < inextEnd; il++) {
				Object object = list.get(il);
				List	detailList = new ArrayList();
				int	ipageRow = ipageSize-((icurrentPage*ipageSize) - il);
				int	itd = 0;

      out.write("\r\n\t\t\t<tr>\r\n\t\t\t\t<td class=");
      out.print(rowClass);
      out.write(">\r\n\t\t\t\t\t<table border=0 cellspacing=0 cellpadding=1 width=665px class=");
      out.print(rowClass);
      out.write(">\r\n\t\t\t\t\t<tr>\r\n\r\n");
			for (int i = 0; i < browseColumns.length; i++) {
					BrowseColumn column = browseColumns[i];
					Object entity = BrowseUtility.getEntityObject(object, column);
					Object originalValue = null;
					Object result = null;
					String alignment = "left";
					int	iwidth = 10;

					if (entity != null) {
						result = BrowseUtility.getColumnValue(column, entity);
					} else {
						result = "";
					}

					originalValue = result;		//set originalValue before formatting result

					if (column.getType().length() >= 0 && !column.getType().equals("String")) {
						result = BrowseUtility.formatBrowseColumnValue(result, column, oid);
					}

					if (column.getType().indexOf("Decimal") >= 0) {	alignment = "right";	}
					if (column.getSize() > 0) {	iwidth = column.getSize();	}

					if (column.isHiddenInput()) {
						sbHiddenFields.append( "<input type=hidden name=\\" + '"' + column.getColumnName() + "\\\" value=\\\"" + result + "\\\">");
					}
					if (column.isDetail()) {
						BrowseColumn detailColumn = BrowseUtility.getBrowseColumnCopy(column);
						detailColumn.setValue(result);
						detailList.add(detailColumn);
					}
					if (!column.isHidden() && !column.isDetail()) {

      out.write("\r\n\t\t\t\t\t\t<td height=18px class=");
      out.print(rowClass);
      out.write(" align=");
      out.print(alignment);
      out.write(" width=");
      out.print(iwidth);
      out.write("% vAlign=top>\r\n");
					if (column.getType().equals("Checkbox")) {
      out.write("\r\n\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"c_checkbox\">&nbsp;\r\n");
					}
						if (column.getLink() != null && column.getLink().length() > 0 && allowBrowse.equalsIgnoreCase("true")) {
      out.write("\r\n\t\t\t\t\t\t\t<a href=\"");
      out.print(BrowseUtility.populateLinkParameters(column, entity));
      out.write("\"  onclick=\"javascript: rowSelect='");
      out.print(il);
      out.write("';\" onMouseOver=\"showDetails(");
      out.print(ipageRow);
      out.write(");highlightRow(");
      out.print(ipageRow);
      out.write(");\" onMouseOut=\"removeHighlight(");
      out.print(ipageRow);
      out.write(");hideDetails(");
      out.print(ipageRow);
      out.write(");\">\r\n");
						if (column.getColumnName().equals(sortedColumn)) {
      out.write("\r\n\t\t\t\t\t\t\t<span id=\"sortedValue");
      out.print(icurrentPage);
      out.write('-');
      out.print(ipageRow);
      out.write('"');
      out.write('>');
      out.print(result);
      out.write("</span>\r\n");
						} else {
      out.write("\r\n\t\t\t\t\t\t\t");
      out.print(result);
      out.write('\r');
      out.write('\n');
						}
      out.write("\r\n\t\t\t\t\t\t\t</a>\r\n");
					} else {
							if (column.getColumnName().equals(sortedColumn)) {
      out.write("\r\n\t\t\t\t\t\t\t<span id=\"sortedValue");
      out.print(icurrentPage);
      out.write('-');
      out.print(ipageRow);
      out.write('"');
      out.write('>');
      out.print(result);
      out.write("</span>\r\n");
						} else {
      out.write("\r\n\t\t\t\t\t\t\t");
      out.print(result);
      out.write('\r');
      out.write('\n');
						}
						}
						if ((browseName.indexOf("rfq-bidboard-posts") >= 0 && column.getColumnName().equals("RfqHeader_dueDate")) || (browseName.equals("sale-auctions")&& column.getColumnName().equals("SaleHeader_auctionEndDate"))) {
      out.write("\r\n\t\t\t\t\t\t\t<input type=hidden name=\"due_date\" value=\"");
      out.print(HiltonUtility.getFormattedDate(originalValue, oid, "MM-dd-yyyy"));
      out.write("\">\r\n");
					}
      out.write("\r\n\t\t\t\t\t\t</td>\r\n");
				}
					if ((browseName.indexOf("rfq-bidboard-posts") >= 0 && column.getColumnName().equals("RfqHeader_bidDueTime")) || (browseName.equals("sale-auctions")&& column.getColumnName().equals("SaleHeader_auctionEndTime"))) {
      out.write("\r\n\t\t\t\t\t\t\t<input type=hidden name=\"due_time\" value=\"");
      out.print(originalValue);
      out.write("\">\r\n");
				}
				}
      out.write("\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t</table>\r\n\t\t\t\t\t<div  id=\"details");
      out.print(icurrentRow);
      out.write("\" style=\"visibility:hidden;display:none;\" class=");
      out.print(rowClass);
      out.write(">\r\n\t\t\t\t\t<table  id=\"detailRows\" border=0 cellspacing=0 cellpadding=0 class=");
      out.print(rowClass);
      out.write(" width=95% align=right>\r\n\t\t\t\t\t<tr class=");
      out.print(rowClass);
      out.write('>');
      out.write('\r');
      out.write('\n');
			int itotalWidth = 0;
				for (int i=0; i < detailList.size(); i++) {
					BrowseColumn column = (BrowseColumn) detailList.get(i);
					int	iwidth = 10;
					if (column.getSize() > 0) {	iwidth = column.getSize();	}
					itotalWidth = itotalWidth + iwidth;

					if (iwidth >= 100 || itotalWidth > 100) {
						if (i > 0) {

      out.write("\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr class=");
      out.print(rowClass);
      out.write('>');
      out.write('\r');
      out.write('\n');
					}
      out.write("\r\n\t\t\t\t\t\t<td colspan=");
      out.print(detailList.size());
      out.write(" height=18px width=");
      out.print(iwidth);
      out.write("% valign=top class=");
      out.print(rowClass);
      out.write(">\r\n\t\t\t\t\t\t<b>");
      out.print(column.getLabel());
      out.write(":</b>");
      out.print(column.getValue());
      out.write("\r\n\t\t\t\t\t\t</td>\r\n");
				} else {
      out.write("\r\n\t\t\t\t\t\t<td height=18px  class=");
      out.print(rowClass);
      out.write(" width=");
      out.print(iwidth);
      out.write("% valign=top>\r\n");
					if (column.getLink() != null && column.getLink().length() > 0) {
      out.write("\r\n\t\t\t\t\t\t\t<a href=\"");
      out.print(column.getLink());
      out.write('"');
      out.write('>');
      out.print(column.getLabel());
      out.write("</a>\r\n");
					} else {
      out.write("\r\n\t\t\t\t\t\t\t<b>");
      out.print(column.getLabel());
      out.write(":</b>&nbsp;");
      out.print(column.getValue());
      out.write('\r');
      out.write('\n');
					}
      out.write("\r\n\t\t\t\t\t\t</td>\r\n");
				}
					if (itotalWidth >= 100 && (i+1) < detailList.size()) {
						itotalWidth = 0;

      out.write("\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr class=");
      out.print(rowClass);
      out.write('>');
      out.write('\r');
      out.write('\n');
				}
				}
      out.write("\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n");

				if (rowClass.equals("browseRow"))
				{
					rowClass = "summary";
				} else {
					rowClass = "browseRow";
				}

				icurrentRow++;
			}
			inextEnd = icurrentRow + ipageSize;
			if (inextEnd > irows) {
				inextEnd = irows;
			}
			icurrentPage++;

      out.write("\r\n\t\t</table>\r\n\t\t</div>\r\n");
	}
      out.write("\r\n\t\t</div>\r\n\t</td>\r\n\t<td width=5px>&nbsp;</td>\r\n</tr>\r\n<tr>\r\n\t<td id=\"originalFilterFields\">");
      out.print(sbOriginalFilterFields);
      out.write("</td>\r\n\t<td id=\"currentFilterFields\">");
      out.print(sbFilterFields);
      out.write("</td>\r\n\t<td id=\"hiddenFields\"></td>\r\n\t<input type=hidden name=\"today\" value=\"");
      out.print(s_today);
      out.write("\">\r\n</tr>\r\n</table>\r\n\r\n");

/**************************************************************/
/*																							*/
/*	Include File for Browse Page Navigation						*/
/*																							*/
/*	int ipageCount	-- Page Count										*/
/*																							*/
/*	The following javascript functions may be called:			*/
/*	isEmpty(x)																*/
/*	nfilter(x)																			*/
/*	previousPage()																*/
/*	nextPage()																		*/
/*	pageMe(x)																		*/
/*	moveMe(x)																		*/
/*																							*/
/**************************************************************/

      out.write("\r\n\t\t<div id=\"pageNavigation\" style=\"border:solid 0px; background: #FFFFFF; padding: 2px; width: 680px; height: 0px; align:center; display:none; visibility:hidden;\">\r\n\t\t<table border=0 cellpadding=0 cellspacing=0 width=680px height=50px>\r\n\t\t<tr>\r\n\t\t\t<td align=center colspan=2 valign=middle>\r\n\t\t\t<table border=0 cellpadding=0 cellspacing=0>\r\n\t\t\t<tr>\r\n\t\t\t\t<td colspan=3 align=center nowrap>\r\n\t\t\t\t\t<table border=0 cellpadding=2 cellspacing=1 align=center>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td align=right><b>Go To Page: </b><input type=text name=\"go_to_pg\" size=5 value=\"\"></td>\r\n\t\t\t\t\t\t<td><a href=\"javascript: if (!isEmpty(nfilter(frm.go_to_pg))) { pageMe(nfilter(frm.go_to_pg)); } void(0);\"><img class=button src=\"");
      out.print(contextPath);
      out.write("/images/button_go_sm.gif\" border=0 valign=bottom></a></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t</table>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr><td colspan=3><br></td></tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td align=right><span id=\"previousPage\" style=\"visibility: hidden; display: block;\"><a href=\"javascript: previousPage(); void(0);\"><< Previous</a></span></td>\r\n\t\t\t\t<td align=center>&nbsp;\r\n\t\t\t\t\t<span id=\"pageLink1\"><b>1</b></span>&nbsp;");
	for (int ipg = 2; ipg <= ipageCount; ipg++) {
      out.write("<span id=\"pageLink");
      out.print(ipg);
      out.write("\"><a href=\"javascript: pageMe(");
      out.print(ipg);
      out.write(");\">");
      out.print(ipg);
      out.write("</a></span>&nbsp;");
}
      out.write("\r\n\t\t\t\t</td>\r\n\t\t\t\t<td><span id=\"nextPage\" style=\"visibility: hidden; display: block;\">&nbsp;<a href=\"javascript: nextPage(); void(0);\">Next >></a></span></td>\r\n\t\t\t</tr>\r\n\t\t\t<tr><td colspan=3><br></td></tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td colspan=3>\r\n\t\t\t\t\t<div id=\"groupNavigation\" style=\"visibility:hidden; display:none;\">\r\n\t\t\t\t\t<table border=0 cellspacing=0 cellpadding=0 width=100%>\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t<td id=\"previousGroup\"></td>\r\n\t\t\t\t\t\t<td id=\"nextGroup\" align=right></td>\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr><td colspan=2><br></td></tr>\r\n\t\t\t\t\t</table>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t</table>\r\n\t\t</div>");
      out.write("\r\n\t\t<table border=0 cellpadding=0 cellspacing=0 width=680px> \r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t<div id=\"findPageOptions\" style=\"visibility: hidden; display=none;\">\r\n\t\t\t\t<table align=center border=0 cellpadding=2 cellspacing=1>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td align=right>Find Page that contains:<br> (within ");
      out.print(labelsMap.get(sortedColumn));
      out.write(") </td>\r\n\t\t\t\t\t<td nowrap>\r\n\t\t\t\t\t\t<input type=text name=\"finder\" maxlength=\"30\" value=\"\" size=30 onChange=\"upperCase(this);\">&nbsp;\r\n\t\t\t\t\t\t<input type=button value=\"Find\" name=\"request\" onClick=\"findMe('");
      out.print(sortedColumn);
      out.write("');\">\r\n\t\t\t\t\t</td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</div>\r\n\t\t\t<div id=\"filterOptions\" style=\"visibility: hidden; display=none;\">\r\n\t\t\t\t<table align=center border=0 cellpadding=2 cellspacing=1>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td nowrap>Filter: \r\n\t\t\t\t\t\t<select name=\"dbcolumn\" size=\"1\">");
      out.print(sbFilterOptions);
      out.write("</select>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t\t<td nowrap>\r\n\t\t\t\t\t\t<select name=\"filtor\" SIZE=\"1\"><option value=\"=\">=\r\n\t\t\t\t\t\t\t<option value=\"&gt;\">&gt;<option value=\"&lt;\">&lt;<option value=\"&gt;=\">&gt;=<option value=\"&lt;=\">&lt;=<option value=\"<>\">&lt;&gt;\r\n\t\t\t\t\t\t</select>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t\t<td nowrap>\r\n\t\t\t\t\t\t<input type=text maxlength=30 name=\"filter\" value=\"\" size=30>&nbsp;\r\n\t\t\t\t\t\t<input type=button name=\"request\" value=\"Filter\" onClick=\"filterMe();\">&nbsp;\r\n\t\t\t\t\t\t<span id=\"resetFilterOptions\" style=\"visibility: visible; display=inline;\">\r\n\t\t\t\t\t\t\t<input id=\"resetButton\" type=button name=\"reset\" value=\"Reset\" onClick=\"resetMe();\">&nbsp;\r\n\t\t\t\t\t\t</span>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</table>\r\n\t\t\t</div>\r\n\t\t\t<div id=\"resetOption\" style=\"visibility: hidden; display=none;\">\r\n\t\t\t<table align=center border=0 cellpadding=2 cellspacing=1>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td nowrap><input id=\"resetButton\" type=button name=\"reset\" value=\"Reset\" onClick=\"resetMe();\">&nbsp;</td>\r\n\t\t\t\t</tr>\r\n\t\t\t</table>\r\n\t\t\t</div>\r\n\t\t\t<div id=\"resetOriginalOption\" style=\"visibility: hidden; display=none;\">\r\n");
      out.write("\t\t\t<table align=center border=0 cellpadding=2 cellspacing=1>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td nowrap><input id=\"resetOriginalButton\" type=button name=\"reset\" value=\"Reset Original\" onClick=\"resetOriginal();\">&nbsp;</td>\r\n\t\t\t\t</tr>\r\n\t\t\t</table>\r\n\t\t\t</div>\r\n\t\t</tr>\r\n\t</table>\r\n\t<table>\r\n\t\t<tr>\r\n\t\t\t<td id=\"catalogFields\"></td>\r\n\t\t</tr>\r\n\t</table>\r\n");
      out.write("\r\n\r\n<SCRIPT value=JavaScript>\r\n<!-- Hide script\r\n\r\n\tfrm = document.purchaseform;\r\n\r\n\tvar rowSelect = 0;\r\n\tvar browser = browserCheck();\r\n\tvar browseName = \"");
      out.print(browseName);
      out.write("\";\r\n\tvar totalRows = ");
      out.print(irows);
      out.write(";\r\n\tvar pageSize = ");
      out.print(ipageSize);
      out.write(";\r\n\tvar pageCount = ");
      out.print(ipageCount);
      out.write(";\r\n\tvar sortedColumn = \"");
      out.print(sortedColumn);
      out.write("\";\r\n");
	if (sbFilterFields != null && sbFilterFields.length() > 0) {
      out.write("\r\n\tvar filterSet = true;\r\n");
	} else {
      out.write("\r\n\tvar filterSet = false;\r\n");
	}
	if (sbOriginalFilterFields != null && sbOriginalFilterFields.length() > 0) {
      out.write("\r\n\tvar originalFilterSet = true;\r\n");
	} else {
      out.write("\r\n\tvar originalFilterSet = false;\r\n");
	}
      out.write("\r\n\r\n\tsetHiddenFields(\"");
      out.print(sbHiddenFields);
      out.write("\");\r\n");
	if (browseName.equals("poheader") || browseName.equals("invoiceheader") || browseName.equals("sale-auctions")) {
      out.write("\r\n\t\t\thideArea('resetOriginalButton');\r\n");
	} 
      out.write("\r\n\r\n\tif (document.getElementById(\"filterFields\") != null && document.getElementById(\"filterFields\") != undefined && document.getElementById(\"filterFields\").length > 0) {\r\n\t\tdocument.getElementById(\"filterFields\").innerHTML = document.getElementById(\"currentFilterFields\").innerHTML;\r\n\t\tdocument.getElementById(\"currentFilterFields\").innerHTML = \"\";\r\n\t} else {\r\n\t\tdocument.getElementById(\"currentFilterFields\").id = \"filterFields\";\r\n\t}\r\n\r\n\tbrowseDisplaySetup();\r\n");
	if (browseName.indexOf("rfq-bidboard-posts") >= 0 || browseName.equals("sale-auctions")) {
      out.write("\r\n\tstart();\r\n");
	}
      out.write("\r\n\r\n\tfunction showDetails(row) {\r\n\t\tdisplayArea(\"details\" + (row+(pageSize*currentPage)-pageSize));\r\n\t}\r\n\tfunction hideDetails(row) {\r\n\t\thideArea(\"details\" + (row+(pageSize*currentPage)-pageSize));\r\n\t}\r\n\r\n\tfunction timeRemainingSetup() {\r\n\t\tif (totalRows > 1) {\r\n\t\t\tfor (var i=0; i < totalRows; i++) {\r\n\t\t\t\tfrm.Input_time[i].style.textAlign = \"right\";\r\n\r\n\t\t\t\tvar dueDate = frm.due_date[i].value;\r\n\t\t\t\tvar dueTime = frm.due_time[i].value;\r\n\r\n\t\t\t\tif ( dueTime.length > 0 ) {\r\n\t\t\t\t\tif (dueTime == \"24:00\") {\r\n\t\t\t\t\t\tdueDate = dueDate + \"  00:00\";\r\n\t\t\t\t\t}\r\n\t\t\t\t\telse {\r\n\t\t\t\t\t\tdueDate = dueDate + \"  \" + dueTime;\r\n\t\t\t\t\t}\r\n\t\t\t\t} else {\r\n\t\t\t\t\tdueDate = dueDate + \"  00:00\";\r\n\t\t\t\t}\r\n\t\t\t\tfrm.due_date[i].value = dueDate;\r\n\t\t\t}\r\n\t\t} else if (totalRows == 1) {\r\n\t\t\tfrm.Input_time.style.textAlign = \"right\";\r\n\r\n\t\t\tvar dueDate = frm.due_date.value;\r\n\t\t\tvar dueTime = frm.due_time.value\r\n\r\n\t\t\tif ( dueTime.length > 0 ) {\r\n\t\t\t\tif (dueTime == \"24:00\") {\r\n\t\t\t\t\tdueDate = dueDate + \"  00:00\";\r\n\t\t\t\t}\r\n\t\t\t\telse {\r\n\t\t\t\t\tdueDate = dueDate + \"  \" + dueTime;\r\n");
      out.write("\t\t\t\t}\r\n\t\t\t} else {\r\n\t\t\t\tdueDate = dueDate + \"  00:00\";\r\n\t\t\t}\r\n\t\t\tfrm.due_date.value = dueDate;\r\n\t\t}\r\n\t}\r\n\r\n\tfunction start()\r\n\t{\r\n\r\n\t\ttimeRemainingSetup();\r\n\r\n\t\tif (totalRows > 1) {\r\n\t\t\tfor(var i = 0; i < totalRows; i++) {\r\n\t\t\t\tsetup(frm.due_date[i].value, i);\r\n\t\t\t\trepeat(i)\r\n\t\t\t}\r\n\t\t} else if (totalRows == 1) {\r\n\t\t\tsetup(frm.due_date.value, i);\r\n\t\t\trepeat(i)\r\n\t\t}\r\n\t}\r\n\r\n\tfunction repeat(row)\r\n\t{\r\n\t\t\tdown(row);\r\n\t\t\tsetTimeout(\"repeat(\" + row + \")\",1000);\r\n\t}\r\n\r\n\tfunction setup(day, row)\r\n\t{\r\n\t\tif ( (browserCheck() == \"NS6\") || (browserCheck() == \"NS\") ) {\r\n\t\t\ttoday\t= (getDateNS(frm.today.value)).getTime();\r\n\t\t\tthe_day = (getDateNS(day)).getTime();\r\n\t\t}\r\n\t\telse {\r\n\t\t\ttoday\t= (new Date(frm.today.value)).getTime();\r\n\t\t\tthe_day = (new Date(day)).getTime();\r\n\t\t}\r\n\r\n\t\ttime = (the_day - today);\r\n\t\tif (totalRows > 1) {\r\n\t\t\tfrm.due_date[row].value=time;\r\n\t\t} else if (totalRows == 1) {\r\n\t\t\tfrm.due_date.value=time;\r\n\t\t}\r\n\t}\r\n\r\n\tfunction down(row)\r\n\t{\r\n\t\tif (totalRows > 1) {\r\n\t\t\tif (frm.Input_time[row].value != \"Closed\") {\r\n");
      out.write("\t\t\t\tfrm.due_date[row].value = frm.due_date[row].value - 1000;\r\n\t\t\t\ttime = frm.due_date[row].value;\r\n\t\t\t\tdays = (time - (time % 86400000)) / 86400000;\r\n\t\t\t\ttime = time - (days * 86400000);\r\n\t\t\t\thours = (time - (time % 3600000)) / 3600000;\r\n\t\t\t\ttime = time - (hours * 3600000);\r\n\t\t\t\tmins = (time - (time % 60000)) / 60000;\r\n\t\t\t\ttime = time - (mins * 60000);\r\n\t\t\t\tsecs = (time - (time % 1000)) / 1000;\r\n\t\t\t\tif(days==1) out = \"1 day, \";\r\n\t\t\t\telse out = days+\" days, \";\r\n\t\t\t\tif(hours < 10) out = out+\"0\";\r\n\t\t\t\tout = out+hours+\":\";\r\n\t\t\t\tif(mins < 10) out = out+\"0\";\r\n\t\t\t\tout = out+mins+\":\";\r\n\t\t\t\tif(secs < 10) out = out+\"0\";\r\n\t\t\t\tout = out+secs;\r\n\t\t\t\tif(days+hours+mins+secs> 0)\r\n\t\t\t\t{\r\n\t\t\t\t\tfrm.Input_time[row].value = out;\r\n\t\t\t\t}\r\n\t\t\t\telse\r\n\t\t\t\t{\r\n\t\t\t\t\tfrm.Input_time[row].value = \"Closed\";\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t} else if (totalRows == 1) {\r\n\t\t\tif (frm.Input_time.value != \"Closed\") {\r\n\t\t\t\tfrm.due_date.value = frm.due_date.value - 1000;\r\n\t\t\t\ttime = frm.due_date.value;\r\n\t\t\t\tdays = (time - (time % 86400000)) / 86400000;\r\n\t\t\t\ttime = time - (days * 86400000);\r\n");
      out.write("\t\t\t\thours = (time - (time % 3600000)) / 3600000;\r\n\t\t\t\ttime = time - (hours * 3600000);\r\n\t\t\t\tmins = (time - (time % 60000)) / 60000;\r\n\t\t\t\ttime = time - (mins * 60000);\r\n\t\t\t\tsecs = (time - (time % 1000)) / 1000;\r\n\t\t\t\tif(days==1) out = \"1 day, \";\r\n\t\t\t\telse out = days+\" days, \";\r\n\t\t\t\tif(hours < 10) out = out+\"0\";\r\n\t\t\t\tout = out+hours+\":\";\r\n\t\t\t\tif(mins < 10) out = out+\"0\";\r\n\t\t\t\tout = out+mins+\":\";\r\n\t\t\t\tif(secs < 10) out = out+\"0\";\r\n\t\t\t\tout = out+secs;\r\n\t\t\t\tif(days+hours+mins+secs> 0)\r\n\t\t\t\t{\r\n\t\t\t\t\tfrm.Input_time.value = out;\r\n\t\t\t\t}\r\n\t\t\t\telse\r\n\t\t\t\t{\r\n\t\t\t\t\tfrm.Input_time.value = \"Closed\";\r\n\t\t\t\t}\r\n\t\t\t}\r\n\t\t}\r\n\t}\r\n\r\n\tfunction viewRfq(ic) {\r\n\t\tvar closed = false;\r\n\r\n");
	if (HiltonUtility.ckNull(browseName).indexOf("rfq-bidboard-posts") >= 0) {
      out.write("\r\n\t\tif (totalRows > 1) {\r\n\t\t\tif (frm.Input_time[rowSelect].value == \"Closed\") {\r\n\t\t\t\tclosed = true;\r\n\t\t\t}\r\n\t\t} else if (totalRows == 1) {\r\n\t\t\tif (frm.Input_time.value == \"Closed\") {\r\n\t\t\t\tclosed = true;\r\n\t\t\t}\r\n\t\t}\r\n\r\n\t\tvar myCell = document.getElementById(\"hiddenFields\");\r\n\t\tvar newInputField = \"<input type='hidden' name='RfqHeader_icRfqHeader' value='\" + ic + \"'>\";\r\n\t\tnewInputField = newInputField +  \"<input type='hidden' name='VendorQuestion_icRfqHeader' value='\" + ic + \"'>\";\r\n\t\tnewInputField = newInputField +  \"<input type='hidden' name='RfqBid_vendorId' value='");
      out.print(user.getVendorId());
      out.write("'>\";\r\n\r\n\t\tmyCell.innerHTML = newInputField;\r\n\r\n\t\tif (closed) {\r\n\t\t\tdoSubmit('/rfq/rfq_summary_postauction.jsp','RfqRetrieve');\r\n\t\t} else {\r\n\t\t\tdoSubmit('','RfqRetrieve;SetEventPage');\r\n\t\t}\r\n");
	}
      out.write("\r\n\t}\r\n\r\n\tfunction viewSaleItem(icHeader, icLine) {\r\n\t\tvar myCell = document.getElementById(\"hiddenFields\");\r\n\t\tvar newInputField = \"<input type='hidden' name='SaleHeader_icSaleHeader' value='\" + icHeader + \"'>\";\r\n\r\n\t\tnewInputField = newInputField + \"<input type='hidden' name='SaleLine_icSaleHeader' value='\" + icHeader + \"'>\";\r\n\t\tnewInputField = newInputField + \"<input type='hidden' name='SaleLine_icSaleLine' value='\" + icLine + \"'>\";\r\n\r\n\t\tvar pg = \"/sales/sale_item_summary.jsp\";\r\n\t\tif (totalRows > 1) {\r\n\t\t\tif (frm.Input_time[rowSelect].value == \"Closed\") {\r\n\t\t\t\tpg = \"/sales/sale_item_postauction.jsp\";\r\n\t\t\t}\r\n\t\t} else if (totalRows == 1) {\r\n\t\t\tif (frm.Input_time.value == \"Closed\") {\r\n\t\t\t\tpg = \"/sales/sale_item_postauction.jsp\";\r\n\t\t\t}\r\n\t\t}\r\n\r\n\t\tmyCell.innerHTML = newInputField;\r\n\t\tdoSubmit(pg,'RfqRetrieve;RfqLineRetrieveById');\r\n\t}\r\n\r\n\tfunction createInvoiceFromOrder(ic) {\r\n\t\tvar myCell = document.getElementById(\"hiddenFields\");\r\n\t\tmyCell.innerHTML = \"\";\r\n\t\tvar newInputField = \"<input type='hidden' name='PoHeader_icPoHeader' value='\" + ic + \"'>\";\r\n");
      out.write("\r\n\t\tmyCell.innerHTML = newInputField;\r\n\t\tdoSubmit('/invoice/iv_general_info.jsp', 'InvoiceCreateFromOrder');\r\n\t}\r\n\r\n\tfunction viewInvoice(ic) {\r\n\t\t//frm.viewType.value = \"WIZARD\";\r\n\t\tvar myCell = document.getElementById(\"hiddenFields\");\r\n\t\tmyCell.innerHTML = \"\";\r\n\t\tvar newInputField = \"<input type='hidden' name='InvoiceHeader_icIvcHeader' value='\" + ic + \"'>\";\r\n\r\n\t\tmyCell.innerHTML = newInputField;\r\n\t\tdoSubmit('/invoice/iv_review.jsp', 'InvoiceRetrieve');\r\n\t}\r\n\r\n// End Hide script -->\r\n</SCRIPT>");
      out.write("\r\n\r\n");
 if (browseName.indexOf("rfq-bidboard-posts") >= 0) {
      out.write("\r\n<table border=0 width=680px>\r\n\t<tr>\r\n\t<td align=center>\r\n\t\t<a href=\"javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing');  void(0);\"><img class=button src=\"");
      out.print(contextPath);
      out.write("/images/button_cancel.gif\" border=0></a>\r\n\t</td>\r\n\t</tr>\r\n</table>\r\n");
	}
      out.write("\r\n\r\n");
      out.write("<br><br>\r\n\r\n<table border=0 cellspacing=0 cellpadding=0 width=680px vAlign=bottom>\r\n<hr size=0 color=black width=680px align=left>\r\n<tr>\r\n\t<td class=copyright width=33% align=left>&nbsp;Copyright &copy; ");
      out.print(HiltonUtility.getFormattedDate(new Date(), oid, "yyyy"));
      out.write("&nbsp;<a href=\"http://www.puridiom.com\" target=\"_blank\">Puridiom.com</a></td>\r\n\t<td class=copyright width=34% align=center><a href=\"http://www.puridiom.com/procurement/legal/index.html\" target=\"_blank\">Legal Notices</a></td>\r\n\t<td align=right class=copyright>&nbsp;Release 3.0.-datetime-</td>\r\n</tr>\r\n</table>\r\n\r\n</form>\r\n</body>\r\n</html>");
      out.write("\r\n\r\n<SCRIPT value=JavaScript>\r\n<!-- Hide script\r\n\r\n\tfunction returnMe(code){\r\n\t\tvar title = '");
      out.print(browseObject.getTitle());
      out.write("';\r\n\t\tvar myCell = document.getElementById(\"hiddenFields\");\r\n\r\n\t\tif ((title.indexOf(\"Currency\")) >= 0) {\r\n\t\t\tmyCell.innerHTML = \"\";\r\n\t\t\tvar newInputField =  \"<INPUT TYPE=\\\"HIDDEN\\\" NAME=\\\"CurrCode_currencyCode\\\" VALUE=\\\"\" + code + \"\\\" >\";\r\n\t\t\tmyCell.innerHTML = newInputField;\r\n\t\t\tdoSubmit('/admin/systemtables/currcode_details.jsp', 'CurrCodeRetrieveById');\r\n\t\t}\r\n\t\telse {\r\n\t\t\tmyCell.innerHTML = \"\";\r\n\t\t\tvar newInputField =  \"<INPUT TYPE=\\\"HIDDEN\\\" NAME=\\\"StdTable_tableType\\\" VALUE=\\\"\" + tableType + \"\\\" ><INPUT TYPE=\\\"HIDDEN\\\" NAME=\\\"StdTable_tableKey\\\" VALUE=\\\"\" + code + \"\\\" >\";\r\n\t\t\tmyCell.innerHTML = newInputField;\r\n\t\t\tdoSubmit('/admin/systemtables/systable_record_details.jsp', 'StdTableRetrieveBy');\r\n\t\t}\r\n\t}\r\n\r\n\tfunction viewPo(ic, type) {\r\n\t\tvar myCell = document.getElementById(\"hiddenFields\");\r\n\t\tmyCell.innerHTML = \"\";\r\n\t\tvar newInputField = \"<input type='hidden' name='PoHeader_icPoHeader' value='\" + ic + \"'>\";\r\n\r\n\t\tmyCell.innerHTML = newInputField;\r\n\t\tdoSubmit('/orders/po_review.jsp','PoRetrieve');\r\n\t}\r\n\r\n// End Hide script -->\r\n");
      out.write("</SCRIPT>\r\n\r\n\r\n");
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
