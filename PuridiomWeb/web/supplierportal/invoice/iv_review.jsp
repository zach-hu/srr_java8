<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.text.NumberFormat" %>
<%
	String	menuContextPath = request.getContextPath();
	String	menuequestURLPath = request.getRequestURL().toString();

	if (com.tsa.puridiom.common.utility.HiltonUtility.isEmpty(menuContextPath)) {
		menuContextPath = "/demo/hilton-bidboard";
	}
	else {
		if (menuContextPath.endsWith("/")) {
			menuContextPath = menuContextPath.substring(0, menuContextPath.lastIndexOf("/"));
		}
	}
%>
<script language='Javascript1.2' src="<%=menuContextPath%>/supplierportal/menu/ivOptionArrays.js"></script>
<script language='Javascript1.2' type="text/javascript">
<!--
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

	if(IsMenu) {
		document.write("<scr" + "ipt language='Javascript1.2' src='<%=menuContextPath%>/menu/hierMainMenu.js' type='text/javascript'><\/scr" + "ipt>");
	}
//-->
</SCRIPT>
<%@ include file="/supplierportal/system/header.jsp" %>
<%
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
 	String	s_current_process = "REVIEW";
	String	s_current_page = "/invoice/iv_review.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";
%>
<script language='Javascript1.2' type="text/javascript">
<!--

	invoiceNumber = "<%=invoiceHeader.getInvoiceNumber()%>";
	invoiceStatus = "<%=invoiceHeader.getStatus()%>";
	ivcInProgress = "<%=DocumentStatus.IVC_INPROGRESS%>";
	invoiceExtractActive = <%=propertiesManager.getProperty("SUN", "InvoiceExtract", "N").equalsIgnoreCase("Y")%>;

	Array91= createInvoiceOptionsMenu(Array91[0]);

//-->
</SCRIPT>

<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="formType" value="IVH"/>
<tsa:hidden name="invoiceaction" value=""/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
  <td valign=top width=135px height=30px>
    <table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
    <tr>
      <td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
      <td nowrap class=hdr12 valign=middle>
        <div style="margin-left:10px; margin-right:10px" class=hdr12>Invoice Information</div>
      </td>
    </tr>
    </table>
  </td>
  <td valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" height="31" /></td>
  <td valign=bottom align=right height=30px>
    <table border=0 cellspacing=0 cellpadding=1 width=100%>
    <tr>
      <td align=right><b>Invoice #:</b></td>
      <td width=150px><%=invoiceHeader.getInvoiceNumber()%></td>
    </tr>
    <tr>
      <td align=right><b>Status:</b></td>
      <td width=150px><%=DocumentStatus.toString(invoiceHeader.getStatus())%></td>
    </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border=0>
    <tr>
      <td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
    </tr>
    <tr>
      <td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>

<%	if (invoiceHeader.getStatus() == DocumentStatus.IVC_INPROGRESS) { %>
<table border="0" cellpadding="2" cellspacing="0" width="680px">
<tr>
  <td width="630px" align="right" nowrap><a href="javascript: void(0);" onMouseOut="popDown('Menu91')" onMouseOver="popUp('Menu91',event)">More Options</a></td>
  <td width="50px">&nbsp;</td>
</tr>
</table>
<%	} %>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td width=680px align=center>
    <table border=0 cellpadding=0 cellspacing=0 width=680px>
    <tr>
      <td width=300px align=center valign=top>
        <table border=0 cellspacing=0 cellpadding=0 width=250px>
        <tr>
          <td align=center>
            <table border=1 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
            <tr>
              <td class=browseHdr height=18px nowrap>&nbsp;General Information</td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class=browseRow>
            <table border=0 cellspacing=0 cellpadding=2 width=100%>
            <tr <%=HtmlWriter.isVisible(oid, "invoiceDate")%>>
              <td nowrap align=right valign=top><b><%=DictionaryManager.getLabel(oid, "invoiceDate", "Invoice Date")%>:</b></td>
              <td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getInvoiceDate(), oid)%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "paymentDueDate")%>>
              <td nowrap align=right valign=top><b><%=DictionaryManager.getLabel(oid, "paymentDueDate", "Payment Due")%>:</b></td>
              <td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getPaymentDueDate(), oid)%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-fiscalYear")%>>
              <td nowrap align=right valign=top><b><%=DictionaryManager.getLabel(oid, "ivc-orderNumber", "Order Number")%>:</b></td>
              <td nowrap valign=top>
                <%=invoiceHeader.getPoNumber()%><% if (invoiceHeader.getPoRelease().compareTo(new BigDecimal(0)) > 0) {%>
                &nbsp;Release <%=invoiceHeader.getPoRelease()%><%}%>
              </td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-requisitionerName")%>>
              <td nowrap align=right valign=top width=40%><b><%=DictionaryManager.getLabel(oid, "ivc-invoiceTotal", "Invoice Total")%>:</b></td>
              <td valign=top><%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceTotal(), oid)%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-department")%>>
              <td nowrap align=right valign=top width=40%><b><%=DictionaryManager.getLabel(oid, "ivc-orderedByName", "Ordered By")%>:</b></td>
              <td nowrap valign=top><%=invoiceHeader.getOrderByName()%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-buyerName")%>>
              <td nowrap align=right valign=top><b><%=DictionaryManager.getLabel(oid, "ivc-orderedByEmail", "Email")%>:</b></td>
              <td valign=top><%=invoiceHeader.getOrderByEmail()%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-buyerName")%>>
              <td nowrap align=right valign=top><b><%=DictionaryManager.getLabel(oid, "ivc-orderedByPhone", "Phone")%>:</b></td>
              <td valign=top><%=invoiceHeader.getOrderByPhone()%></td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
<%
	Address shipTo = (Address) invoiceHeader.getShipToAddress();
	if (shipTo == null)
	{
		shipTo = new Address();
	}
%>
      <td width=300px align=center valign=top>
        <table border=0 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
        <tr>
          <td>
            <table border=1 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
            <tr>
              <td class=browseHdr height=18px nowrap>&nbsp;Shipping Information</td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class=browseRow>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-shipToCode")%>><td class=browseRow height=12px nowrap><b><%=invoiceHeader.getShipToCode()%></b></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine1")%>><td class=browseRow height=12px nowrap><%=shipTo.getAddressLine1()%></td></tr>
            </table>

            <table id=supplierRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(shipTo.getAddressLine2()))
		{ %>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine2")%>><td class=browseRow height=12px nowrap><%=shipTo.getAddressLine2()%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(shipTo.getAddressLine3()))
		{ %>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine3")%>><td class=browseRow height=12px nowrap><%=shipTo.getAddressLine3()%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(shipTo.getAddressLine4()))
		{ %>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine4")%>><td class=browseRow height=12px nowrap><%=shipTo.getAddressLine4()%></td></tr>
<%	}%>
            <tr><td class=browseRow height=12px nowrap><%=shipTo.getCity()%>&nbsp;<%=shipTo.getState()%>&nbsp;<%=shipTo.getPostalCode()%></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-shp-country")%>><td class=browseRow height=12px nowrap><%=shipTo.getCountry()%></td></tr>
            </table>

<%	if (!HiltonUtility.isEmpty(invoiceHeader.getShipToContact())) { %>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-shp-attention")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabel(oid, "ivc-shp-attention", "Attn")%>:&nbsp;<%=invoiceHeader.getShipToContact()%></td></tr>
            </table>
<%	} %>
          </td>
        </tr>
        </table>
      </td>
      <td rowspan=3 align=right valign=top><%@ include file="/supplierportal/invoice/iv_sidebar.jsp" %></td>
</tr>
    <tr><td colspan=2>&nbsp;</td></tr>
<%	if (processMap.containsKey("PAYMENT_INFO")) {
			InvoiceVendor vendor= (InvoiceVendor) request.getAttribute("invoiceVendor");
			InvoiceAddress vendAddr = (InvoiceAddress) request.getAttribute("invoiceAddress");
			if (vendor == null) {
				vendor = new InvoiceVendor();
			}
			if (vendAddr == null) {
				vendAddr = new InvoiceAddress();
			}
%>
    <tr>
      <td width=300px align=center valign=top>
        <table id=supplierTable border=0 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
        <tr>
          <td>
            <table border=1 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
            <tr>
              <td class=browseHdr height=18px nowrap>&nbsp;Payment Information</td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class=browseRow id=supplierRows>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-supplier")%>><td class=browseRow height=12px nowrap><b><%=invoiceHeader.getVendorId()%></b></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine1")%>><td class=browseRow height=12px nowrap><%=vendAddr.getAddressLine1()%></td></tr>
            </table>

            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
	<%	if (!HiltonUtility.isEmpty(vendAddr.getAddressLine2())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine2")%>><td class=browseRow height=12px nowrap><%=vendAddr.getAddressLine2()%></td></tr>
	<%	}
			if (!HiltonUtility.isEmpty(vendAddr.getAddressLine3())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine3")%>><td class=browseRow height=12px nowrap><%=vendAddr.getAddressLine3()%></td></tr>
	<%	}
			if (!HiltonUtility.isEmpty(vendAddr.getAddressLine4())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine4")%>><td class=browseRow height=12px nowrap><%=vendAddr.getAddressLine4()%></td></tr>
	<%	}%>
            <tr><td class=browseRow height=12px nowrap><%=vendAddr.getCityStatePostal()%></td></tr>
	<%if (!HiltonUtility.isEmpty(vendAddr.getCountry())) {%>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-sup-country")%>><td class=browseRow height=12px nowrap><%=vendAddr.getCountry()%></td></tr>
	<%	}
			if (!HiltonUtility.isEmpty(invoiceHeader.getContactName())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-sup-attention")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabel(oid, "ivc-sup-attention", "Attn")%>: <%=invoiceHeader.getContactName()%></td></tr>
	<%	}
			if (!HiltonUtility.isEmpty(invoiceHeader.getApReference())) { %>
			<tr <%=HtmlWriter.isVisible(oid, "ivc-apreference")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabel(oid, "ivc-apreference", "AP Reference")%>: <%=invoiceHeader.getApReference()%></td></tr>
	<%	} %>
            </table>

          </td>
        </tr>
        </table>
      </td>
<%	}
		if (processMap.containsKey("PAYMENT_INFO")) {
			Address billTo = (Address) invoiceHeader.getBillToAddress();
			if (billTo == null) {
				billTo = new Address();
			}
%>
      <td width=300px align=center valign=top>
        <table id=paymentTable border=0 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
        <tr>
          <td>
            <table border=1 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
            <tr>
              <td class=browseHdr height=18px nowrap>&nbsp;Billing Information</td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class=browseRow id=billingRows>
            <table border=0 cellspacing=0 cellpadding=2 width=210px class=browseRow>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-billToCode")%>><td class=browseRow height=12px nowrap><b><%=invoiceHeader.getBillToCode()%></b></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-bil-addressLine1")%>><td class=browseRow height=12px nowrap><%=billTo.getAddressLine1()%></td></tr>
            </table>

            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
	<%	if (!HiltonUtility.isEmpty(billTo.getAddressLine2())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-bil-addressLine2")%>><td class=browseRow height=12px nowrap><%=billTo.getAddressLine2()%></td></tr>
	<%	}
			if (!HiltonUtility.isEmpty(billTo.getAddressLine3())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-bil-addressLine3")%>><td class=browseRow height=12px nowrap><%=billTo.getAddressLine3()%></td></tr>
	<%	}
			if (!HiltonUtility.isEmpty(billTo.getAddressLine4())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-bil-addressLine4")%>><td class=browseRow height=12px nowrap><%=billTo.getAddressLine4()%></td></tr>
	<%	} %>
            <tr><td class=browseRow height=12px nowrap><%=billTo.getCityStatePostal()%></td></tr>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-bil-country")%>><td class=browseRow height=12px nowrap><%=billTo.getCountry()%></td></tr>
            </table>

            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
	<%	if (!HiltonUtility.isEmpty(invoiceHeader.getBillToContact())) { %>
            <tr <%=HtmlWriter.isVisible(oid, "ivc-bil-attention")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabel(oid, "ivc-bil-attention", "Attn")%>:&nbsp;<%=invoiceHeader.getBillToContact()%></td></tr>
	<%	} %>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
<%	} %>
    </table>
  </td>
</tr>
</table>

<br>

<%//	if (processMap.containsKey("ITEMS")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td align=center width=680px>
    <table id=itemTable border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
    <tr>
      <td>
        <table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
        <tr>
          <td width=100% align=center valign=top>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tr>
              <td width=3% class=browseHdr nowrap>&nbsp;</td>
              <td <%=HtmlWriter.isVisible(oid, "ivc-itemNumber")%> width=13% class=browseHdr nowrap><%=DictionaryManager.getLabel(oid, "ivc-hdg-itemNumber", "Item/Part #")%></td>
              <td <%=HtmlWriter.isVisible(oid, "ivc-quantity")%> width=8% class=browseHdr align=right nowrap align=center><%=DictionaryManager.getLabel(oid, "ivc-hdg-quantity", "Quantity")%></td>
              <td <%=HtmlWriter.isVisible(oid, "ivc-uom")%> width=8% class=browseHdr align=center nowrap><%=DictionaryManager.getLabel(oid, "ivc-hdg-uom", "UOM")%></td>
              <td <%=HtmlWriter.isVisible(oid, "ivc-unitCost")%> width=13% class=browseHdr align=right nowrap><%=DictionaryManager.getLabel(oid, "ivc-hdg-unitCost", "Unit Cost")%></td>
              <td <%=HtmlWriter.isVisible(oid, "ivc-extendedCost")%> width=13% class=browseHdr align=right nowrap align=right><%=DictionaryManager.getLabel(oid, "ivc-hdg-extendedCost", "Ext Cost")%></td>
              <td width=3% class=browseHdr>&nbsp;</td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td width=100% align=center valign=top>
        <table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
        <tr>
          <td>
            <table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
<%
		List lineitems = (List) request.getAttribute("invoiceLineList");
		if (lineitems != null && lineitems.size() > 0)
		{
			for (int i = 0; i < lineitems.size(); i++)
			{
				InvoiceLine invoiceLine = (InvoiceLine) lineitems.get(i);
				BigDecimal bdQuantity = invoiceLine.getQuantity();
				BigDecimal bdUnitPrice = invoiceLine.getUnitPrice();
				BigDecimal bdUmFactor = invoiceLine.getUmFactor();
				if (bdUmFactor.compareTo(new BigDecimal(0)) == 0)
				{
					bdUmFactor = new BigDecimal(1);
				}
				BigDecimal bdExtCost = HiltonUtility.getFormattedDollar(bdQuantity.multiply(bdUnitPrice).multiply(bdUmFactor), oid);
%>
            <tr id=itemRows>
              <td width=3% class=browseRow nowrap align=right><%=i+1%></td>
              <td <%=HtmlWriter.isVisible(oid, "ivc-itemNumber")%> width=13% class=browseRow nowrap>
                <%=invoiceLine.getItemNumber()%>
                <tsa:hidden id="icReqLine_<%//=i%>" value="<%//=reqLine.getIcReqLine()%>"/>
              </td>
              <td <%=HtmlWriter.isVisible(oid, "ivc-quantity")%> width=8% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(invoiceLine.getQuantity(), oid)%></td>
              <td <%=HtmlWriter.isVisible(oid, "ivc-uom")%> width=8% class=browseRow align=center nowrap><%=invoiceLine.getUmCode()%></td>
              <td <%=HtmlWriter.isVisible(oid, "ivc-unitCost")%> width=13% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(invoiceLine.getUnitPrice(), oid)%></td>
              <td <%=HtmlWriter.isVisible(oid, "ivc-extendedCost")%> width=13% class=browseRow nowrap align=right><%=bdExtCost%></td>
              <td width=3% class=browseRow>&nbsp;</td>
            </tr>
            <tr id=itemRows>
              <td class=browseRow nowrap>&nbsp;</td>
              <td colspan=6 class=browseRow><%=invoiceLine.getDescription()%></td>
            </tr>
            <tr><td colspan=7><hr></td></tr>
<%		}
		} %>
            </table>
          </td>
        </tr>
        </table>

<%
	//			if (processMap.containsKey("HEADER_TOTALS")) {
	BigDecimal  bdSubtotal = invoiceHeader.getInvoiceSubtotal();
	BigDecimal  bdDiscount = invoiceHeader.getInvoiceDiscount();
	BigDecimal  bdTax = invoiceHeader.getInvoiceTax();
	BigDecimal  bdShipping = invoiceHeader.getInvoiceShipping();
	BigDecimal  bdOther = invoiceHeader.getInvoiceOther();
	BigDecimal  bdRejecting = invoiceHeader.getInvoiceRejecting();

	BigDecimal bdTotal = bdSubtotal.subtract(bdDiscount);
	bdTotal = bdTotal.add(bdTax).add(bdShipping).add(bdOther);
	bdTotal = bdTotal.subtract(bdRejecting);
%>
        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
        <tr <%=HtmlWriter.isVisible(oid, "ivc-subtotal")%>>
          <td colspan="3" width=82% class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabel(oid, "ivc-subtotal", "Subtotal")%>:</td>
          <td width=13% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceSubtotal(), oid)%></td>
          <td width=5% class=browseRow nowrap align=right>&nbsp;</td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "ivc-discountAmount")%>>
          <td colspan="3" class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabel(oid, "ivc-discountAmount", "Discount")%>:</td>
          <td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceDiscount(), oid)%></td>
          <td class=browseRow nowrap align=right>&nbsp;</td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "ivc-taxAmount")%>>
          <td colspan="3" class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabel(oid, "ivc-taxAmount", "Tax")%>:</td>
          <td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceTax(), oid)%></td>
          <td class=browseRow nowrap align=right>&nbsp;</td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "ivc-shippingCharges")%>>
          <td colspan="3" class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabel(oid, "ivc-shippingCharges", "Shipping")%>:</td>
          <td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceShipping(), oid)%></td>
          <td class=browseRow nowrap align=right>&nbsp;</td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "ivc-otherCharges")%>>
          <td colspan="3" class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabel(oid, "ivc-otherCharges", "Other")%>:</td>
          <td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceOther(), oid)%></td>
          <td class=browseRow nowrap align=right>&nbsp;</td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "ivc-rejectedAmount")%>>
          <td width="50%" class=browseRow nowrap align=right>
          	<%	if (!HiltonUtility.isEmpty(invoiceHeader.getReasonCode())) {%>
          	&nbsp;<%=DictionaryManager.getLabel(oid, "ivc-rejectedReason", "Reason")%>:
          	<%	} %>
          </td>
          <td class=browseRow nowrap align=left><%=invoiceHeader.getReasonCode()%></td>
          <td class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabel(oid, "ivc-rejectedAmount", "Rejecting")%>:</td>
          <td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceRejecting(), oid)%></td>
          <td class=browseRow nowrap align=right>&nbsp;</td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "ivc-total")%>>
			<td class=browseRow nowrap align=right><%=DictionaryManager.getLabel(oid, "ivc-invoiceTotal", "Invoice Total")%>:</td>
			<td class=browseRow nowrap align=left><%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceTotal(), oid)%></td>
			<td class=processOn nowrap align=right><b><%=DictionaryManager.getLabel(oid, "ivc-total", "Total")%>:</b></td>
			<td class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(bdTotal, oid)%></td>
			<td class=browseRow nowrap align=right>&nbsp;</td>
        </tr>
        </table>
<%//			} */%>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
<%//	} %>

<br>

<%//	if (processMap.containsKey("HEADER_ACCOUNTS")) { %>

<%
      BigDecimal bd_total_perc = new BigDecimal(0.00);
      BigDecimal bd_total_amt = new BigDecimal(0.00);
      int	acci = 0;
%>




<%//	} %>

<br>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	function invoiceValidate(x)
	{
		frm.invoiceaction.value = x;
		doSubmit("/invoice/iv_validate.jsp", "InvoiceValidate");
	}

	function invoiceForward()
	{
		doSubmit("/menu/main_menu.jsp", "InvoiceForward");
	}

	function thisLoad()
	{
		f_StartIt();
	}
// End Hide script -->
</SCRIPT>