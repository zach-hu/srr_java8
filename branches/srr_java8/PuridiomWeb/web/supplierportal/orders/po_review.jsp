<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.po.tasks.PoBlanketInfo" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	PoBlanketInfo blanketInfo = (PoBlanketInfo) request.getAttribute("blanketInfo");
	BigDecimal	bd_ic_po_header = poHeader.getIcPoHeader();
	BigDecimal	bd_ic_header_history = poHeader.getIcHeaderHistory();
	BigDecimal	bd_release_number = poHeader.getReleaseNumber();
	BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
	BigDecimal	bd_zero = new BigDecimal(0);
	String	s_po_number = poHeader.getPoNumber();
	String	s_po_status = poHeader.getStatus();
	String	s_po_type = poHeader.getPoType();
	String	s_receipt_required = poHeader.getReceiptRequired();
	String fromBrowse = (String) request.getAttribute("fromBrowse");

 	if (s_receipt_required.equalsIgnoreCase("R"))	{	s_receipt_required = "Receipt Required";		}
	if (s_receipt_required.equalsIgnoreCase("P"))	{	s_receipt_required = "Previously Received";	}
	if (s_receipt_required.equalsIgnoreCase("N"))	{	s_receipt_required = "No Receipt Required";	}
	if (s_receipt_required.equalsIgnoreCase("E"))	{	s_receipt_required = "End User Receipt";		}

	if (HiltonUtility.isEmpty(s_po_number))	{	s_po_number = "N/A";									}
	if (bd_release_number == null)		{	bd_release_number = new BigDecimal(0000);	}
	if (bd_revision_number == null)		{	bd_revision_number = new BigDecimal(0000);	}
	if (HiltonUtility.isEmpty(s_po_status))		{	s_po_status = DocumentStatus.PO_INPROGRESS;	}

	List lineList = (List) poHeader.getPoLineList();
	int	i_line_count = 0;

	if (lineList != null)
	{
		i_line_count = lineList.size();
	}
%>
<script language='Javascript1.2' src="<%=contextPath%>/supplierportal/menu/poOptionArrays.js"></script>
<!--  header.jsp  -->
<%@ page import="com.tsa.puridiom.supplierportal.*" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.html.*" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
	String	userId = (String) request.getAttribute("userId");
	String	oid = (String) request.getAttribute("organizationId");
	BidBoardUser	user = BidBoardUserManager.getInstance().getUserInCache(oid, userId);

	if (oid == null) {
		oid = "";
	}
	if (userId == null) {
		userId = "";
	}
	if (user == null) {
		user = new BidBoardUser();
	}

	String s_view = (String) request.getAttribute("viewType");
	if (HiltonUtility.isEmpty(s_view)) {
		s_view = "WIZARD";
	}
%>

<HTML>
<HEAD>
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
	<META HTTP-EQUIV="Cache-Control" content="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="Sat, 11 Sep 1971 12:00:00 GMT">
	<META NAME="Description" Content="<%=contextPath%>/supplierportal/menu/menu_pg.jsp">
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/hilton.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/filter.js"></SCRIPT>
	<TITLE>Puridiom Supplier Portal</TITLE>
	<%@ include file="/supplierportal/system/stylesheet_link.jsp" %>
</HEAD>
<script language='Javascript1.2' type="text/javascript">
<!--
		contextPath = "<%=contextPath%>/supplierportal/";
	   DOM = (document.getElementById) ? true : false;
	   NS4 = (document.layers) ? true : false;
	NS4old = (NS4 && (parseFloat(navigator.appVersion) < 4.02));
	    IE = (document.all) ? true : false;
	   IE4 = IE && !DOM;
	   Mac = (navigator.appVersion.indexOf("Mac") != -1);
	  IE4M = IE4 && Mac;
	IsMenu = (DOM || (NS4 && !NS4old) || (IE && !IE4M));

	if(window.event + "" == "undefined") event = null;
	function f_PopUp(){return};
	function f_PopDown(){return};
	popUp = f_PopUp;
	popDown = f_PopDown;
	var browseTxt = "<%=DictionaryManager.getLabel(oid, "browse", "Buscar", false)%>";
	var newTxt = "<%=DictionaryManager.getLabel(oid, "createnew", "Nueva", false)%>";
	var menuLocation = <%=DictionaryManager.getLabel(oid, "menulocation", "72", false)%>;
	var printPdfLabel = "<%=DictionaryManager.getLabel(oid, "printPdf", "Issue Document", false)%>";

	if(IsMenu) {
		document.write("<scr" + "ipt language='Javascript1.2' src='<%=contextPath%>/supplierportal/menu/menubar_arrays.js' type='text/javascript'><\/scr" + "ipt>");
		document.write("<scr" + "ipt language='Javascript1.2' src='<%=contextPath%>/supplierportal/menu/hierMainMenu.js' type='text/javascript'><\/scr" + "ipt>");
	}
//-->
</SCRIPT>

<body marginwidth=0 marginheight=0 topmargin=0 leftmargin=0 width=680px onload="thisLoad();" onunload="closeOpenWindows();">
<form name="purchaseform" target="_parent" action="<%=contextPath%>/supplier" method="POST">
<div id="filterFields" style="display:none;"></div>
<div id="hiddenFields" style="display:none;"></div>

<tsa:hidden name="handler" value=""/>
<tsa:hidden name="successPage" value=""/>
<tsa:hidden name="failurePage" value="system/error.jsp"/>
<tsa:hidden name="userName" value="<%=user.getDisplayName()%>"/>
<tsa:hidden name="userId" value="<%=userId%>"/>
<tsa:hidden name="organizationId" value="<%=oid%>"/>
<tsa:hidden name="browseName" value=""/>
<tsa:hidden name="VendorRegister_contactEmailAddr" value="<%=userId%>"/>
<tsa:hidden name="Contact_emailAddr" value="<%=userId%>"/>
<tsa:hidden name="vendorId" value="<%=user.getVendorId()%>"/>

<%@ include file="/supplierportal/system/header_menu_options.jsp" %>
<!--  end header.jsp  -->
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");

	UserProfile buyer = com.tsa.puridiom.usermanager.UserManager.getInstance().getUser(oid, poHeader.getBuyerCode());
	UserProfile requisitioner = com.tsa.puridiom.usermanager.UserManager.getInstance().getUser(oid, poHeader.getRequisitionerCode());
%>

<script language='Javascript1.2' type="text/javascript">
<!--

	poType = "<%=s_po_type%>";
	Array91= createPoOptionsMenu(Array91[0]);

//-->
</SCRIPT>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="allowBrowse" value="false"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=OrderType.toString(s_po_type, oid)%> Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px>
    	<table border="0" cellspacing="0" cellpadding="1" width="100%">
<%	int i_colspan = 1;%>
  		<tr>
	      <td nowrap align="right"><b><%=DictionaryManager.getLabel(oid, "order", "Order", false)%> #:</b></td>
	      <td width=100px><%=s_po_number%></td>
			<%	if (bd_release_number.compareTo(bd_zero) > 0)
		    {
		      i_colspan = i_colspan + 2; %>
		      <td nowrap align="right"><b><%=DictionaryManager.getLabel(oid, "release", "Release", false)%> #:</b></td>
		      <td width=100px><%=bd_release_number%></td>
			<%	}
		    if (bd_revision_number.compareTo(bd_zero) > 0)
		    {
		      i_colspan = i_colspan + 2; %>
		      <td nowrap align="right"><b><%=DictionaryManager.getLabel(oid, "revision", "Revision", false)%> #:</b></td>
		      <td width=100px><%=bd_revision_number%></td>
			<%	} %>
  		</tr>
		<tr>
	      <td colspan=<%=i_colspan%> nowrap align="right"><b><%=DictionaryManager.getLabel(oid, "status", "Status", false)%>:</b></td>
	      <td width=100px><%=DocumentStatus.toString(s_po_status, oid)%></td>
	    </tr>
	 	</table>
		<table cellpadding="0" cellspacing="0" border="0">
	    <tr>
	      <td width=1000px height="1px" class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
	    </tr>
	    <tr>
	      <td height=2px class="darkShadow"><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
	    </tr>
	   </table>
	</td>
</tr>
</table>

<br>

<table border="0" cellpadding="2" cellspacing="0" width="680px">
<tr>
	<td align="right" width="90%"><a href="javascript: void(0);" onMouseOut="popDown('Menu91')" onMouseOver="popUp('Menu91',event)">More Options</a></td>
	<td width="10%">&nbsp;</td>
</tr>
</table>

<br>


<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="680px">
    <tr>
      <td width="300px" align="center" valign="top">
        <table border="0" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
        <tr>
          <td>
            <table border="1" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
            <tr>
              <td class="browseHdr" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabel(oid, "po-general_information", "General Information")%></td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class="browseRow">
            <table border="0" cellspacing="0" cellpadding="2" width="100%">
            <tr <%=HtmlWriter.isVisible(oid, "po-buyerName")%>>
              <td nowrap align="right" width="40%"><b><%=DictionaryManager.getLabel(oid, "po-buyerName", "Buyer")%>:</b>&nbsp;</td>
              <td nowrap><%=buyer.getDisplayName()%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "orderDate")%>>
              <td nowrap align="right" width="40%"><b><%=DictionaryManager.getLabel(oid, "orderDate", "Order Date")%>:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getPoDate(), oid)%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-fiscalYear")%>>
              <td nowrap align="right" width="40%"><b><%=DictionaryManager.getLabel(oid, "po-fiscalYear", "Fiscal Year")%>:</b>&nbsp;</td>
              <td nowrap>
                <%=poHeader.getFiscalYear()%>
                <tsa:hidden name="PoHeader_fiscalYear" value="<%=poHeader.getFiscalYear()%>"/>
              </td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "contract")%>>
              <td nowrap align="right" width="40%"><b><%=DictionaryManager.getLabel(oid, "contract", "Contract")%>:</b>&nbsp;</td>
              <td nowrap><%=poHeader.getContractNo()%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "effectiveDate")%>>
              <td nowrap align="right" width="40%"><b><%=DictionaryManager.getLabel(oid, "effectiveDate", "Effective Date")%>:&nbsp;</b></td>
              <td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getEffectiveDate(), oid)%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "promisedDate")%>>
              <td nowrap align="right" width="40%"><b><%=DictionaryManager.getLabel(oid, "promisedDate", "Promised Date")%>:&nbsp;</b></td>
              <td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getPromisedDate(), oid)%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-requiredDate")%>>
              <td nowrap align="right" width="40%"><b><%=DictionaryManager.getLabel(oid, "po-requiredDate", "Required Date")%>:&nbsp;</b></td>
              <td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getRequiredDate(), oid)%></td>
            </tr>
<%	if (!HiltonUtility.isEmpty(poHeader.getRequisitionNumber())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-requisitionNumber")%>>
              <td nowrap align="right" width="40%"><b><%=DictionaryManager.getLabel(oid, "po-requisitionNumber", "Requisition Number")%>:&nbsp;</b></td>
              <td nowrap><%=poHeader.getRequisitionNumber()%></td>
            </tr>
<%	}
	if (!HiltonUtility.isEmpty(poHeader.getRequisitionerCode())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-requisitionerName")%>>
              <td nowrap align="right" width="40%"><b><%=DictionaryManager.getLabel(oid, "po-requisitionerName", "Requisitioner Name")%>:&nbsp;</b></td>
              <td nowrap><%=requisitioner.getDisplayName()%></td>
            </tr>
<%	}%>
            </table>
          </td>
        </tr>
        </table>
      </td>
<%
  String	s_ship_address_line_1 = "";
  String	s_ship_address_line_2 = "";
  String	s_ship_address_line_3 = "";
  String	s_ship_address_line_4 = "";
  String	s_ship_city = "";
  String	s_ship_state = "";
  String	s_ship_postal_code = "";
  String	s_ship_country = "";

  Address shipTo = (Address) poHeader.getShipToAddress();
  if (shipTo != null)
  {
    s_ship_address_line_1 = shipTo.getAddressLine1();
    s_ship_address_line_2 = shipTo.getAddressLine2();
    s_ship_address_line_3 = shipTo.getAddressLine3();
    s_ship_address_line_4 = shipTo.getAddressLine4();
    s_ship_city = shipTo.getCity();
    s_ship_state = shipTo.getState();
    s_ship_postal_code = shipTo.getPostalCode();
    s_ship_country = shipTo.getCountry();
  }
%>
      <td width="300px" align="center" valign="top">
        <table border="0" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
        <tr>
          <td>
            <table border="1" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
            <tr>
              <td class="browseHdr" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabel(oid, "po-shipping_information", "Shipping Information")%></td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class="browseRow">
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
            <tr <%=HtmlWriter.isVisible(oid, "po-shipToCode")%>><td class="browseRow" height="12px" nowrap><b><%=poHeader.getShipToCode()%></b></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine1")%>><td class="browseRow" height="12px" nowrap><%=s_ship_address_line_1%></td></tr>
            </table>

            <table id="supplierRows" border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
<%	if (!HiltonUtility.isEmpty(s_ship_address_line_2))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine2")%>><td class="browseRow" height="12px" nowrap><%=s_ship_address_line_2%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(s_ship_address_line_3))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine3")%>><td class="browseRow" height="12px" nowrap><%=s_ship_address_line_3%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(s_ship_address_line_4))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-shp-addressLine4")%>><td class="browseRow" height="12px" nowrap><%=s_ship_address_line_4%></td></tr>
<%	} %>
            <tr><td class="browseRow" height="12px" nowrap><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-shp-country")%>><td class="browseRow" height="12px" nowrap><%=s_ship_country%></td></tr>
            </table>

<%	if (!HiltonUtility.isEmpty(poHeader.getShipToContact())) { %>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
            <tr <%=HtmlWriter.isVisible(oid, "po-shp-attention")%>><td class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabel(oid, "po-shp-attention", "Attn")%>:&nbsp;<%=poHeader.getShipToContact()%></td></tr>
            </table>
<%	} %>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
<%	if (!HiltonUtility.isEmpty(poHeader.getShipViaCode())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-shipVia")%>><td class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabel(oid, "po-shipVia", "Ship Via")%>:&nbsp;<%=poHeader.getShipViaCode()%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(poHeader.getPriorityCode())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-priority")%>><td class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabel(oid, "po-priority", "Priority")%>:&nbsp;<%=poHeader.getPriorityCode()%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(poHeader.getRouting())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-routing")%>><td class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabel(oid, "po-routing", "Routing")%>:&nbsp;<%=poHeader.getRouting()%></td></tr>
<%	} %>
            </table>
          </td>
        </tr>
        </table>
      </td>
      <td rowspan="5" align="right" valign="top"><%//@ include file="/orders/po_sidebar.jsp" %></td>
    </tr>
    <tr><td colspan="2">&nbsp;</td></tr>
<%
  String	s_vend_address_line_1 = "";
  String	s_vend_address_line_2 = "";
  String	s_vend_address_line_3 = "";
  String	s_vend_address_line_4 = "";
  String	s_vend_city = "";
  String	s_vend_state = "";
  String	s_vend_postal_code = "";
  String	s_vend_country = "";

  Address vendor = (Address) poHeader.getVendorAddress();
  if (vendor != null)
  {
    s_vend_address_line_1 = vendor.getAddressLine1();
    s_vend_address_line_2 = vendor.getAddressLine2();
    s_vend_address_line_3 = vendor.getAddressLine3();
    s_vend_address_line_4 = vendor.getAddressLine4();
    s_vend_city = vendor.getCity();
    s_vend_state = vendor.getState();
    s_vend_postal_code = vendor.getPostalCode();
    s_vend_country = vendor.getCountry();
  }
%>
    <tr>
      <td width="300px" align="center" valign="top">
        <table id="supplierTable" border="0" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
        <tr>
          <td>
            <table border="1" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
            <tr>
              <td class="browseHdr" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabel(oid, "po-supplier_information", "Supplier Information")%></td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class="browseRow" id="supplierRows">
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
            <tr <%=HtmlWriter.isVisible(oid, "po-supplier")%>><td class="browseRow" height="12px" nowrap><b><%=poHeader.getVendorId()%></b></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine1")%>><td class="browseRow" height="12px" nowrap><%=s_vend_address_line_1%></td></tr>

<%	if (!HiltonUtility.isEmpty(s_vend_address_line_2))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine2")%>><td class="browseRow" height="12px" nowrap><%=s_vend_address_line_2%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(s_vend_address_line_3))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine3")%>><td class="browseRow" height="12px" nowrap><%=s_vend_address_line_3%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(s_vend_address_line_4))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-sup-addressLine4")%>><td class="browseRow" height="12px" nowrap><%=s_vend_address_line_4%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(s_vend_city) || !HiltonUtility.isEmpty(s_vend_state) || !HiltonUtility.isEmpty(s_vend_postal_code)) { %>
            <tr>
              <td class="browseRow" height="12px" nowrap>
<%		if (!HiltonUtility.isEmpty(s_vend_city)) { %>
                <%=s_vend_city%>&nbsp;
<%		}
      if (!HiltonUtility.isEmpty(s_vend_state)) { %>
                <%=s_vend_state%>&nbsp;
<%		}
      if (!HiltonUtility.isEmpty(s_vend_postal_code)) { %>
                <%=s_vend_postal_code%>
<%		} %>
              </td>
            </tr>
<%	} %>
<%	if (!HiltonUtility.isEmpty(s_vend_country)) { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-sup-country")%>><td class="browseRow" height="12px" nowrap><%=s_vend_country%></td></tr>
<%	} %>

<%	if (!HiltonUtility.isEmpty(poHeader.getContactName()))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-sup-attention")%>><td class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabel(oid, "po-sup-attention", "Attn")%>:&nbsp;<%=poHeader.getContactName()%></td></tr>
<%	} %>
            </table>
          </td>
        </tr>
        </table>
      </td>
<%
  String	s_bill_address_line_1 = "";
  String	s_bill_address_line_2 = "";
  String	s_bill_address_line_3 = "";
  String	s_bill_address_line_4 = "";
  String	s_bill_city = "";
  String	s_bill_state = "";
  String	s_bill_postal_code = "";
  String	s_bill_country = "";

  Address billTo = (Address) poHeader.getBillToAddress();
  if (billTo != null)
  {
    s_bill_address_line_1 = billTo.getAddressLine1();
    s_bill_address_line_2 = billTo.getAddressLine2();
    s_bill_address_line_3 = billTo.getAddressLine3();
    s_bill_address_line_4 = billTo.getAddressLine4();
    s_bill_city = billTo.getCity();
    s_bill_state = billTo.getState();
    s_bill_postal_code = billTo.getPostalCode();
    s_bill_country = billTo.getCountry();
  }
%>
      <td width="300px" align="center" valign="top">
        <table id="billingTable" border="0" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
        <tr>
          <td>
            <table border="1" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
            <tr>
              <td class="browseHdr" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabel(oid, "po-billing_information", "Billing Information")%></td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class="browseRow" id="billingRows">
            <table border="0" cellspacing="0" cellpadding="2" width="210px" class="browseRow">
            <tr <%=HtmlWriter.isVisible(oid, "po-billToCode")%>><td class="browseRow" height="12px" nowrap><b><%=poHeader.getBillToCode()%></b></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine1")%>><td class="browseRow" height="12px" nowrap><%=s_bill_address_line_1%></td></tr>

<%	if (!HiltonUtility.isEmpty(s_bill_address_line_2))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine2")%>><td class="browseRow" height="12px" nowrap><%=s_bill_address_line_2%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(s_bill_address_line_3))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine3")%>><td class="browseRow" height="12px" nowrap><%=s_bill_address_line_3%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(s_bill_address_line_4))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-bil-addressLine4")%>><td class="browseRow" height="12px" nowrap><%=s_bill_address_line_4%></td></tr>
<%	} %>
            <tr><td class="browseRow" height="12px" nowrap><%=s_bill_city%>&nbsp;<%=s_bill_state%>&nbsp;<%=s_bill_postal_code%></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-bil-country")%>><td class="browseRow" height="12px" nowrap><%=s_bill_country%></td></tr>

<%	if (!HiltonUtility.isEmpty(poHeader.getBillToContact()))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-bil-attention")%>><td class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabel(oid, "po-bil-attention", "Attn")%>:&nbsp;<%=poHeader.getBillToContact()%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(poHeader.getTermsCode()))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-paymentTerms")%>><td class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabel(oid, "po-paymentTerms", "Terms")%>:&nbsp;<%=poHeader.getTermsCode()%></td></tr>
<%	}
    if (!HiltonUtility.isEmpty(poHeader.getFobCode()))
    { %>
            <tr <%=HtmlWriter.isVisible(oid, "po-fob")%>><td class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabel(oid, "po-fob", "FOB")%>:&nbsp;<%=poHeader.getFobCode()%></td></tr>
<%	} %>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    <tr><td colspan="2">&nbsp;</td></tr>
    <tr>
      <td width="300px" align="center" valign="top">
<%	if (poHeader.getPoType().equals("BO")) { %>
        <table border="0" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
        <tr>
          <td>
            <table border="1" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
            <tr>
              <td class="browseHdr" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabel(oid, "blanket_information", "Blanket Information")%></td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class="browseRow">
            <table border="0" cellspacing="0" cellpadding="2" width="100%">
            <tr <%=HtmlWriter.isVisible(oid, "effectiveDate")%>>
              <td nowrap align="right" width="50%"><b><%=DictionaryManager.getLabel(oid, "effectiveDate", "Effective Date")%>:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getFormattedDate(blanketInfo.getPoEffective(), oid)%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "expirationDate")%>>
              <td nowrap align="right" width="50%"><b><%=DictionaryManager.getLabel(oid, "expirationDate", "Expiration Date")%>:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getFormattedDate(blanketInfo.getPoExpires(), oid)%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "releaseLimit")%>>
              <td nowrap align="right" width="50%"><b><%=DictionaryManager.getLabel(oid, "releaseLimit", "Release Limit")%>:</b>&nbsp;</td>
              <td nowrap>$<%=HiltonUtility.getFormattedDollar(blanketInfo.getPoReleaseLimit(), oid)%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "blanketLimit")%>>
              <td nowrap align="right" width="50%"><b><%=DictionaryManager.getLabel(oid, "blanketLimit", "Blanket Limit")%>:</b>&nbsp;</td>
              <td nowrap>$<%=HiltonUtility.getFormattedDollar(blanketInfo.getPoBlanket(), oid)%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "numberOfReleases")%>>
              <td nowrap align="right" width="50%"><b><%=DictionaryManager.getLabel(oid, "numberOfReleases", "Number of Releases")%>:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getBigDecimalFormatted(blanketInfo.getReleaseCount(), 0)%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "releaseTotal")%>>
              <td nowrap align="right" width="50%"><b><%=DictionaryManager.getLabel(oid, "releaseTotal", "Release Total")%>:</b>&nbsp;</td>
              <td nowrap>$<%=HiltonUtility.getFormattedDollar(blanketInfo.getReleaseTotal(), oid)%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "availableBlanket")%>>
              <td nowrap align="right" width="50%"><b><%=DictionaryManager.getLabel(oid, "availableBlanket", "Available Blanket")%>:</b>&nbsp;</td>
              <td nowrap>$<%=HiltonUtility.getFormattedDollar(blanketInfo.getAvailableBlanket(), oid)%></td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
<%	} %>
      </td>
      <td width="300px" align="center" valign="top">

      </td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td align="center" width="680px">
    <table id="itemTable" border="0" cellspacing="0" cellpadding="0" width="665px" class="browseHdr">
    <tr>
      <td>
        <table border="1" cellspacing="0" cellpadding="0" width="665px" class="browseHdr">
        <tr>
          <td width="100%" align="center" valign="top">
            <table border="0" cellspacing="0" cellpadding=4 width="100%" class="browseRow">
            <tr>
              <td width="2%" class="browseHdr" align=center nowrap>&nbsp;</td>
              <td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> width="13%" class="browseHdr" align="center" nowrap><%=DictionaryManager.getLabel(oid, "po-hdg-itemNumber", "Item/Part #")%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-commodity")%> width="18%" class="browseHdr" align="center" nowrap><%=DictionaryManager.getLabel(oid, "po-hdg-commodity", "Commodity")%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-quantity")%> width="10%" class="browseHdr" nowrap align="right"><%=DictionaryManager.getLabel(oid, "po-hdg-quantity", "Quantity")%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-UOM")%> width="6%" class="browseHdr" align="center" nowrap><%=DictionaryManager.getLabel(oid, "po-hdg-uom", "UOM")%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-unitCost")%> width="10%" class="browseHdr" nowrap align="right"><%=DictionaryManager.getLabel(oid, "po-hdg-unitCost", "Unit Cost")%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-extendedCost")%> width="13%" class="browseHdr" nowrap align="right"><%=DictionaryManager.getLabel(oid, "po-hdg-extendedCost", "Ext Cost")%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-discountAmount")%> width="11%" class="browseHdr" nowrap align="right"><%=DictionaryManager.getLabel(oid, "po-hdg-discountAmount", "Discount")%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-lineStatus")%> width="17%" class="browseHdr" align="center" nowrap><%=DictionaryManager.getLabel(oid, "po-hdg-lineStatus", "Line Status")%></td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td width="100%" align="center" valign="top">
      <tsa:hidden name="PoLine_icPoLine" value=""/>
        <table id=itemRow border="0" cellspacing="0" cellpadding="0" width="100%" class="browseRow">
        <tr>
          <td>
<%
    for (int i = 0; i < i_line_count; i++)
    {
      PoLine poLine = (PoLine) lineList.get(i);

      BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid);
      BigDecimal bd_unit_price = HiltonUtility.getFormattedDollar(poLine.getUnitPrice(), oid);
      BigDecimal b_um_factor = HiltonUtility.getFormattedDollar(poLine.getUmFactor(), oid);
      BigDecimal bd_extended_cost = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price).multiply(b_um_factor), oid);
%>
            <table border="0" cellspacing="0" cellpadding="4" width="100%" class="browseRow">
            <tr>
              <td width="2%" class="browseRow" nowrap align="right"><%=i+1%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> width="13%" class="browseRow" nowrap>
                <%=poLine.getItemNumber()%>
                <tsa:hidden id="icPoLine_<%=i%>" name="icPoLine_<%=i%>" value="<%=poLine.getIcPoLine()%>"/>
              </td>
              <td <%=HtmlWriter.isVisible(oid, "po-commodity")%> width="18%" class="browseRow" nowrap><%=poLine.getCommodity()%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-quantity")%> width="10%" class="browseRow" nowrap align="center"><%=bd_quantity%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-uom")%> width="6%" class="browseRow" nowrap align="center"><%=poLine.getUmCode()%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-unitCost")%> width="10%" class="browseRow" nowrap align="center"><%=bd_unit_price%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-extendedCost")%> width="13%" class="browseRow" nowrap align="center"><%=bd_extended_cost%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-discountAmount")%> width="11%" class="browseRow" nowrap align="center"><%=HiltonUtility.getFormattedDollar(poLine.getDiscountAmount(), oid)%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-lineStatus")%> width="17%" class="browseRow" nowrap align="center"><%=DocumentStatus.toString(poLine.getStatus())%></td>
            </tr>
            <tr>
              <td class="browseRow" nowrap>&nbsp;</td>
              <td colspan="8" class="browseRow"><%=poLine.getDescription()%></td>
            </tr>
<%		if (!(poLine.getRequisitionNumber().equalsIgnoreCase(poHeader.getRequisitionNumber())) || !(poLine.getRequisitionerCode().equalsIgnoreCase(poHeader.getRequisitionerCode()))) {%>
            <tr>
              <td class=browseRow nowrap>&nbsp;</td>
<%			if (!poLine.getRequisitionNumber().equals(poHeader.getRequisitionNumber())) {%>
              <td <%=HtmlWriter.isVisible(oid, "ln-po-requisitionNumber")%> colspan=4 class=browseRow><b><%=DictionaryManager.getLabel(oid, "ln-po-requisitionNumber", "Requisition #")%>:</b>&nbsp;<%=poLine.getRequisitionNumber()%></td>
<%			}
			if ( !HiltonUtility.isEmpty(poLine.getRequisitionerCode()) && !poLine.getRequisitionerCode().equals(poHeader.getRequisitionerCode()) ) {%>
              <td <%=HtmlWriter.isVisible(oid, "ln-po-requisitionerName")%> colspan=4 class=browseRow><b><%=DictionaryManager.getLabel(oid, "ln-po-requisitionerName", "Requisitioner Name")%>:</b>&nbsp;
              <%=com.tsa.puridiom.usermanager.UserManager.getInstance().getUser(oid, poLine.getRequisitionerCode()).getDisplayName()%></td>
<%			}%>
            </tr>
<%		}%>
            </table>
<%		} %>
          </td>
        </tr>
<%	if (i_line_count > 0) {%>
        <tr><td class="browseRow" width="100%"><hr size="0"></td></tr>
<%	}%>
        </table>

        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr>
          <td>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
            <tr <%=HtmlWriter.isVisible(oid, "po-subtotal")%>>
              <td width="65%" class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabel(oid, "po-subtotal", "Subtotal")%>:</td>
              <td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getSubtotal(), oid)%></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-discountAmount")%>>
              <td width="65%" class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabel(oid, "po-discountAmount", "Discount")%>:</td>
              <td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getDiscountAmount(), oid)%></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-taxAmount")%>>
              <td width="65%" class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabel(oid, "po-taxAmount", "Tax")%>:</td>
              <td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getTaxAmount(), oid)%></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-shippingCharges")%>>
              <td width="65%" class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabel(oid, "po-shippingCharges", "Shipping")%>:</td>
              <td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getShippingCharges(), oid)%></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-shippingTaxAmount")%>>
              <td width="65%" class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabel(oid, "po-shippingTaxAmount", "Shipping Tax")%>:</td>
              <td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getShippingTax(), oid)%></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-otherCharges")%>>
              <td width="65%" class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabel(oid, "po-otherCharges", "Other")%>:</td>
              <td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getOtherCharges(), oid)%></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-otherTaxAmount")%>>
              <td width="65%" class="browseRow" nowrap align="right">&nbsp;<%=DictionaryManager.getLabel(oid, "po-otherTaxAmount", "Other Tax")%>:</td>
              <td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getFormattedDollar(poHeader.getOtherTax(), oid)%></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tr>
            </table>
          </td>
        </tr>
        </table>

        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="browseRow">
        <tr>
          <td>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
            <tr <%=HtmlWriter.isVisible(oid, "po-total")%>>
              <td width="65%" class="browseRow" nowrap align="right"><b><%=DictionaryManager.getLabel(oid, "po-total", "Total")%>:</b></td>
              <td width="10%" class="browseRow" nowrap align="right"><b>$<%=HiltonUtility.getFormattedDollar(poHeader.getTotal(), oid)%></b></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td align="center" width="680px">
    <table border="0" cellspacing="0" cellpadding="0" width="665px" class="browseHdr">
    <tr>
      <td>
        <table border="1" cellspacing="0" cellpadding="0" width="665px" class="browseHdr">
        <tr>
          <td>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
            <tr>
              <td width="75%" height="18px" class="browseHdr">&nbsp;<b><%=DictionaryManager.getLabel(oid, "po-comments", "Comment")%></b></td>
              <td width="8%" height="18px" class="browseHdr" align="center" nowrap="nowrap">&nbsp;<b><%=DictionaryManager.getLabel(oid, "po-print", "Print")%></b></td>
              <td width="7%" height="18px" class="browseHdr" align="center" nowrap="nowrap">&nbsp;<b><%=DictionaryManager.getLabel(oid, "po-bold", "Bold")%></b></td>
              <td width="10%" height="18px" class="browseHdr" align="center" nowrap="nowrap">&nbsp;<b><%=DictionaryManager.getLabel(oid, "po-placement", "Placement")%></b></td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>
<%
      List cmtList = (List) poHeader.getDocCommentList();
      int ci = 0;
      if (cmtList != null)
      {
        for(int i = 0; i < cmtList.size(); i++)
        {
          DocComment docComment = (DocComment) cmtList.get(i);
          if (docComment == null) {
            continue;
          }
          DocText docText = docComment.getDocText();
          if (docText == null) {
            docText = new DocText();
          }
          String s_cmt_title = docComment.getCommentTitle();
          String s_cmt_print = docComment.getCommentPrint();
          String s_cmt_bold = docComment.getCommentBold();
          String s_cmt_place = docComment.getCommentPlace();
          String s_cmt_text = docText.getStdText();

          if (s_cmt_place.equals("A"))
          {
            s_cmt_place = "After";
          }
          else if (s_cmt_place.equals("B"))
          {
            s_cmt_place = "Before";
          }
          ci++;
%>
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr>
          <td width="75%" class="browseRow"><%=s_cmt_title%></td>
          <td width="8%" class="browseRow" align="center" valign="top"><%=s_cmt_print%></td>
          <td width="7%" class="browseRow" align="center" valign="top"><%=s_cmt_bold%></td>
          <td width="10%" class="browseRow" align="center" valign="top"><%=s_cmt_place%></td>
        </tr>
        </table>

        <table class="browseRow">
        <tr>
          <td width="50px">&nbsp;</td>
          <td width="100%"><%=s_cmt_text%>	</td>
        </tr>
        </table>
<% 			}
      }
      if (ci == 0) {%>
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr><td class="browseRow"><%=DictionaryManager.getLabel(oid, "po-nocomments", "There are no comments associated with this order")%>.</td></tr>
        </table>
<%		}%>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>

<%	if (propertiesManager.isModuleActive("DOCUMENTS")) {%>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td align="center" width="680px">
    <table border="0" cellspacing="0" cellpadding="0" width="665px" class="browseHdr">
    <tr>
      <td>
        <table border="1" cellspacing="0" cellpadding="0" width="665px" class="browseHdr">
        <tr>
          <td>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
            <tr>
              <td width="80%" height="18px" class="browseHdr">&nbsp;<b><%=DictionaryManager.getLabel(oid, "po-attachment", "Attachment")%></b></td>
              <td width="10%" height="18px" class="browseHdr" align="center">&nbsp;<b><%=DictionaryManager.getLabel(oid, "po-print", "Print")%></b></td>
              <td width="10%" height="18px" class="browseHdr" align="center">&nbsp;</td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td>
<%
    List attachmentList = (List) poHeader.getDocAttachmentList();
      int ai = 0;
      if (attachmentList != null) {
        for(int i = 0; i < attachmentList.size(); i++) {
          DocAttachment docAttachment = (DocAttachment) attachmentList.get(i);
          if (docAttachment == null) {
            continue;
          }
          String	filename = docAttachment.getDocFilename();
          String	ext = filename.substring(filename.lastIndexOf(".") + 1);
          ai++;
%>
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr>
          <td width="80%" class="browseRow">
            <table border="0" cellpadding="0" cellspacing="0" width="100%" class="browseRow">
            <tr>
              <td width="25px" align="center" valign="middle">
<%		if (ext.equalsIgnoreCase("DOC")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/ms_word.gif" border="0" alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/adobe_pdf.gif" border="0" alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/ms_excel.gif" border="0" alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/ms_project.gif" border="0" alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/ms_powerpoint.gif" border="0" alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/picture_sm.gif" border="0" alt="Open Attached Image"></a>
<%		} else {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/supplierportal/images/doc_attached.gif" border="0" alt="Open Attached Document"></a>
<%		}%>
              </td>
              <td>
                <a href="javascript: openDocument(<%=i%>); void(0);" title="Click here to open the <%=docAttachment.getDocTitle()%> attachment."><%=docAttachment.getDocTitle()%></a>
                <tsa:hidden name="docFilename" value="<%=filename%>"/>
              </td>
            </tr>
            </table>
          </td>
          <td width="10%" class="browseRow" align="center" valign="top"><%=docAttachment.getDocPrint()%></td>
          <td width="10%" class="browseRow" align="center" valign="top"></td>
        </tr>
        </table>

  <% 		}
      }
      if (ai == 0) {%>
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr><td><%=DictionaryManager.getLabel(oid, "po-noattachments", "There are no attachments associated with this order")%>.</td></tr>
        </table>
<%		}%>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
<%	}%>

<br><br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=33% align=center><a href="javascript: printMe(); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_print.gif" border=0 alt="Print"></a></td>
<%	if (poHeader.getStatus().equals(DocumentStatus.PO_AWARDED) && poHeader.getDateAcknowledged() == null) {%>
	<td width=33% align=center><a href="javascript: acknowledgeMe(); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_acknowledge.gif" border=0 alt="Acknowledge"></a></td>
<%	}%>
	<td width=33% align=center><a href="javascript: returnMe(); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>

<br><br>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var fromBrowse = "<%=HiltonUtility.ckNull(fromBrowse)%>";

	function printMe()
 	{
 		doSubmit('/orders/po_print_pdf.jsp', 'DoNothing');
 	}

	function acknowledgeMe()
 	{
		clearFilters();
		frm.browseName.value = "po-pending-acknowledgement";
		frm.allowBrowse.value = "true";
 		doSubmit('/orders/po_acknowledged.jsp', 'PoSupplierAcknowledgement;BrowseRetrieve');
 	}

	function openDocument(row) {
		var filename = "";

		if (document.all("docFilename").length > 1) {
			filename = frm.docFilename[row].value;
		}
		else {
			filename = frm.docFilename.value;
		}

		popupUrl = '/system/popupDocAttachment.jsp';
		popupHandler = "StdDocumentDownloadFile";
		popupUserId = frm.userId.value;
		popupOrganizationId = frm.organizationId.value;
		popupParameters = "filename=" + filename;

		if (theFocus == null) { theFocus = 'document_window'; }

		var winspecs = "WIDTH=680,HEIGHT=680,resizable=1,scrollbars=1,menubar=0,location=0,top=0,left=0";
		document_window = window.open("<%=contextPath%>/supplierportal/system/popup.html", "document_window", winspecs);

		if (theFocus == 'main') {
			self.focus();
		}
		else {
			document_window.focus();
		}

		if (document_window.opener == null) document_window.opener = window;
		self.name = "main";
	}

	function viewPayments()
	{
		var releaseNumber = frm.PoHeader_releaseNumber.value;

		popupParameters = popupParameters + "colname=PaymentAccount_poNumber;operator==;filter_txt=" + frm.PoHeader_poNumber.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
		popupParameters = popupParameters + "colname=PaymentAccount_releaseNumber;operator=;filter_txt=" + releaseNumber +  ";logicalOperator=AND;originalFilter=Y;sort=N;";
		browseLookup('', 'paymentaccount'); void(0);
	}

	function viewReleasePayments()
	{
		popupParameters = popupParameters + "colname=PaymentAccount_poNumber;operator==;filter_txt=" + frm.PoHeader_poNumber.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
		browseLookup('', 'paymentaccount'); void(0);
	}

	function returnMe()
	{
		if (fromBrowse == "po-pending-acknowledgement") {
			searchOrdersPendingAck();
		} else {
			searchOrders();
		}
	}

	function createInvoice()
 	{
		if (frm.PoHeader_poNumber.value.length > 0)
		{
	 		doSubmit('/invoice/iv_general_info.jsp', 'InvoiceLookupPoNumber');
		}
 	}

// End Hide script -->
</SCRIPT>

