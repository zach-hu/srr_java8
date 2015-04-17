<%@page import="java.math.BigDecimal"%>
<%@page import="com.tsa.puridiom.common.documents.DocumentStatus"%>
<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%

RequisitionLine msrLine = (RequisitionLine)request.getAttribute("msrLine");
boolean reqempty=false;
boolean poempty=false;
boolean msrLineNotInInv=false;
if(msrLine==null){
	msrLine = new RequisitionLine();
}
RequisitionHeader reqHeaderSourced = (RequisitionHeader)request.getAttribute("reqHeaderSourced");
if(reqHeaderSourced==null){
	reqempty=true;
	reqHeaderSourced = new RequisitionHeader();
}

PoHeader poHeader = (PoHeader)request.getAttribute("poHeader");
if(poHeader==null){
	poempty=true;
	poHeader = new PoHeader();
}

List receiptLineList = (List)request.getAttribute("receiptLineList");
List invoiceHeaderList = (List)request.getAttribute("invoiceHeaderList");

InvItem invItem =(InvItem)request.getAttribute("invItem");
if(invItem==null){
	invItem = new InvItem();
	if(!reqHeaderSourced.getKit().equals("Y"))
	msrLineNotInInv = true;
}


List invBinLocationList = (List)request.getAttribute("invBinLocationList");


String closeMe = (String) request.getAttribute("closeMe");
String lineDisplay = (String) request.getAttribute("lineDisplay");



if (closeMe == null) {	closeMe = "N";	}
%>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>
<div id="piOptions" style="
border:none;position: absolute;
width: 100%; align:center; valign:center; overflow-y:hidden; overflow-x:hidden; height: 100%">
<table border="0" align="center" id="msrData" width="100%"
style="border-collapse: collapse; padding:none;">
	<tr class="mnav">
		<td colspan="2">&nbsp;MSR #&nbsp;<%=msrLine.getRequisitionNumber()%>&nbsp;</td>
		<td>&nbsp;&nbsp;</td>
		<td>&nbsp;&nbsp;</td>
		<td>&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td nowrap id="line">Line:&nbsp;<%=msrLine.getLineNumber()%>&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td nowrap id="item">Item:&nbsp;<%=msrLine.getItemNumber()%>&nbsp;&nbsp;</td>
	</tr>
	<%if(!reqempty) {%>
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td nowrap id="requisitionNumberSourced"><b>REQ #:</b>&nbsp;<%=reqHeaderSourced.getRequisitionNumber()%>&nbsp;</td>
		<td nowrap id="requisitionStatusSourced"><b>STATUS:</b>&nbsp;<%=DocumentStatus.toString(reqHeaderSourced.getStatus())%>&nbsp;</td>
	</tr>
	<%if(!poempty){%>
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td nowrap id="poNumber"><b>PO #:</b>&nbsp;<%=poHeader.getPoNumber()%>&nbsp;</td>
		<td nowrap id="releaseNumber"><b>REL #:</b>&nbsp;<%=poHeader.getReleaseNumber()%>&nbsp;</td>
		<td nowrap id="poStatus"><b>STATUS:</b>&nbsp;<%=DocumentStatus.toString(poHeader.getStatus())%>&nbsp;</td>
	</tr>
	<%} %>
	
	<%if(receiptLineList!=null && receiptLineList.size()>0){ 
		for(int rll = 0; rll < receiptLineList.size() ;rll ++){
			ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(rll);
			BigDecimal qtyReceived = HiltonUtility.ckNull(receiptLine.getQtyReceived());
			BigDecimal qtyAccepted = HiltonUtility.ckNull(receiptLine.getQtyAccepted());
	%>
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td nowrap id="receiptNumber"><b>RECEIPT #:</b>&nbsp;<%=receiptLine.getReceiptNumber()%>&nbsp;</td>
		<td nowrap id="receiptStatus"><b>STATUS:</b>&nbsp;<%=DocumentStatus.toString(receiptLine.getStatus())%>&nbsp;</td>
		<td nowrap id="receiptStatus"><b>QTY:</b>&nbsp;<%=qtyAccepted%>&nbsp;</td>
	</tr>
	<%	}
	} %>
	<%if(!poempty){%>
	<%if(invoiceHeaderList!=null && invoiceHeaderList.size()>0){ 
			for(int ihl = 0; ihl < invoiceHeaderList.size() ;ihl ++){
				InvoiceHeader invoiceHeader = (InvoiceHeader)invoiceHeaderList.get(ihl);
				BigDecimal qtyInvoiced = new BigDecimal(0);
				if(invoiceHeader.getInvoiceLineList()!=null &&invoiceHeader.getInvoiceLineList().size() > 0){
					for(int ill = 0; ill < invoiceHeader.getInvoiceLineList().size(); ill++){
						InvoiceLine invoiceLine = (InvoiceLine)invoiceHeader.getInvoiceLineList().get(ill);
						qtyInvoiced = qtyInvoiced.add(invoiceLine.getQuantity());
	%>
					<tr>
						<td>&nbsp;&nbsp;</td>
						<td nowrap id="receiptNumber"><b>INVOICE #:</b>&nbsp;<%=invoiceHeader.getInvoiceNumber()%>&nbsp;</td>
						<td nowrap id="receiptStatus"><b>STATUS:</b>&nbsp;<%=DocumentStatus.toString(invoiceHeader.getStatus())%>&nbsp;</td>
						<td nowrap id="receiptStatus"><b>QTY:</b>&nbsp;<%=invoiceLine.getQuantity()%>&nbsp;</td>
						<td nowrap id="receiptStatus"><b>LINE TOTAL:</b>&nbsp;<%=invoiceLine.getLineTotal() %>&nbsp;</td>
					</tr>
	<%
					}
				}
			}
		}
	} %>
	<%if(!msrLineNotInInv){%>
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td nowrap id="receiptNumberlbl"><b>INVENTORY NUMBER #:</b></td>
		<td nowrap id="receiptNumber">&nbsp;<%=invItem.getItemNumber()%>&nbsp;</td>
	</tr>
	<tr>
	<div>
	<table id="invData" width="100%">
		<tr>
			<td>Location</td>
			<td>Aisle/Area</td>
			<td>Row</td>
			<td>Column</td>
			<td>Bin</td>
			<td>UOM</td>
			<td>On Hand</td>
		</tr>
		<%if(invBinLocationList != null && invBinLocationList.size()>0){ 
			for(int ibl = 0; ibl < invBinLocationList.size() ; ibl++){
				InvBinLocation invBinLocation = (InvBinLocation)invBinLocationList.get(ibl);
		%>
		<tr>
			<td><%=invBinLocation.getItemLocation() %></td>
			<td><%=invBinLocation.getAisle()%></td>
			<td><%=invBinLocation.getLocrow()%></td>
			<td><%=invBinLocation.getTier()%></td>
			<td><%=invBinLocation.getBin()%></td>
			<td><%=invItem.getUnitOfOrder()%></td>
			<td><%=invBinLocation.getQtyOnHand()%></td>
		</tr>
		<%
			} 
		}%>
	</table>
	</div>
	</tr>
	<%} %>

	<%} %>
	</table>
	
	
</div>
<input type="hidden" name="viewNow" value="Y" />
<input type="hidden" name="emailTo" value="N" />
<input type="hidden" name="closeMe" value="N" />
<%@ include file="/system/footer_popup.jsp" %>
<SCRIPT value=JavaScript>
<!-- Hide script
	frm = document.purchaseform;

	function closeiframe()
	{
		parent.document.getElementById('recframe').style.display="none";
	}
	function thisLoadPopup()
	{
  		addHandle(document.getElementsByTagName('body').item(0), window);
  		frame = parent.document.getElementById('recframe_'+'<%=lineDisplay%>');
		//alert(document.getElementById('msrData').offsetHeight + 'px');
		fixHeight = document.getElementById('msrData').offsetHeight;
		if(document.getElementById('invData')){
			fixHeight = fixHeight + document.getElementById('invData').offsetHeight;
		}
		frame.style.height = fixHeight + 'px';
		//alert(document.getElementById('msrData').offsetWidth + 'px');
		fixWidth = document.getElementById('msrData').offsetWidth;
		if(document.getElementById('invData')){
			if(document.getElementById('msrData').offsetWidth > fixWidth )
			{
				fixWidth = document.getElementById('msrData').offsetWidth;
			}
		}
		frame.style.width = fixWidth + 'px';
	}
	
	function closeMe()
	{
		parent.document.getElementById('recframe').style.display="none";
	}
	// End Hide script -->
</SCRIPT>
