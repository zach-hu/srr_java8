<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	reqReceiptOption = propertiesManager.getProperty("REQ DEFAULTS", "ReceiptRequired", "R");
	String	poReceiptOption = propertiesManager.getProperty("PO DEFAULTS", "ReceiptRequired", "R");
%>
<tsa:hidden name="allowBrowse" value="true"/>
<table width=<%=formWidth%> cellpadding="0" cellspacing="0" border="0">
<tr>
	<td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height=100% width=100%>
		<tr>
			<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class="hdr12" class="darkShadow">
				<div style="margin-left:10px; margin-right:10px" class="hdr12"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "systemdefaults", "System Defaults")%></td>
			</td>
		</tr>
		</table>
	</td>
	<td valign="bottom" width=3px><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign="bottom" align="right" height="30px" width=100%>
		<table cellpadding="0" cellspacing="0" border="0" width=100%>
		<tr>
			<td width="100%" height="1px" class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height="2px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table id="mainTable" border="0" cellspacing="0" cellpadding="0" width=<%=formEntryWidth%>>
<tr>
	<td align="center" valign="top" width="100%" id="systemSetupFrame">
		<div id="SystemProcessing" style="visibility: visible; position:absolute; top:120px; left:25px; height:440px;">
			<table border="0" cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td width="100%" align="center" valign="top"><b>Loading system defaults... Please wait.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td>
			</tr>
			</table>
		</div>

		<div id="Supplier" style="visibility: hidden; display:none; position:absolute; left:5px; height:340px;">
			<table border="0" cellspacing="0" cellpadding="1" width=100%>
			<tr><td colspan="2" height="30px">&nbsp;&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplierdefaults", "Supplier Defaults")%></b></td></tr>
			<tr>
				<td <%=HtmlWriter.isVisible(oid, "sup-shipVia")%> align="right" nowrap><a href="javascript: setCurrentRow('VENDOR DEFAULTS', 'ShipVia', 'shipvia'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-shipVia", "Ship Via")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "sup-shipVia")%>>
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="ShipVia"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","ShipVia","")%>" onchange="upperCase(this);" size="15">
				</td>
				<td <%=HtmlWriter.isVisible(oid, "sup-currency")%> align="right"nowrap><a href="javascript: setCurrentRow('VENDOR DEFAULTS', 'currencyCode', 'curr_code'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-currency", "Currency")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "sup-currency")%>>
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="currencyCode"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","currencyCode","")%>" onchange="upperCase(this);" size="15">
				</td>
			</tr>
			<tr>
				<td <%=HtmlWriter.isVisible(oid, "sup-fob")%> align="right"nowrap><a href="javascript: setCurrentRow('VENDOR DEFAULTS', 'FobId', '', 'FOB'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-fob", "FOB")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "sup-fob")%>>
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="FobId"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","FobId","")%>" onchange="upperCase(this);" size="15">
				</td>
				<td <%=HtmlWriter.isVisible(oid, "sup-paymentTerms")%> align="right"nowrap><a href="javascript: setCurrentRow('VENDOR DEFAULTS', 'VendTerms', 'payment-terms'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-paymentTerms", "Terms")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "sup-paymentTerms")%>>
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="VendTerms"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","VendTerms","")%>" onchange="upperCase(this);" size="15">
				</td>
			</tr>
			<tr>
				<td <%=HtmlWriter.isVisible(oid, "sup-poSendMethod")%> align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-poSendMethod", "PO Send Method")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "sup-poSendMethod")%>>
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="PrintFaxCode"/>
					<select name="Property_value" size="1">
        	            <option  <% if (propertiesManager.getProperty("VENDOR DEFAULTS","PrintFaxCode","N").equalsIgnoreCase("P")) {%> selected<%}%>  value="P">Print PO</option>
            	        <option  <% if (propertiesManager.getProperty("VENDOR DEFAULTS","PrintFaxCode","N").equalsIgnoreCase("F")) {%> selected<%}%> value="F">Fax PO</option>
                	    <option  <% if (propertiesManager.getProperty("VENDOR DEFAULTS","PrintFaxCode","N").equalsIgnoreCase("E")) {%> selected<%}%> value="E">EDI PO</option>
                    	<option  <% if (propertiesManager.getProperty("VENDOR DEFAULTS","PrintFaxCode","N").equalsIgnoreCase("M")) {%> selected<%}%> value="M">Email PO</option>
	                    <option  <% if (propertiesManager.getProperty("VENDOR DEFAULTS","PrintFaxCode","N").equalsIgnoreCase("X")) {%> selected<%}%> value="X">XML Order</option>
    	            </select>
				</td>
			</tr>
			<tr><td colspan="4"><br></td></tr>
			<tr>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN01")%> align="right"nowrap><a href="javascript: setCurrentRow('VENDOR DEFAULTS', 'VendUdf1', '', 'VN01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-VN01", "Udf 1")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN01")%>>
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="VendUdf1"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","VendUdf1","")%>" onchange="upperCase(this);" size="15"></td>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN02")%> align="right"><a href="javascript: setCurrentRow('VENDOR DEFAULTS', 'VendUdf2', '', 'VN02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-VN02", "Udf 2")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN02")%>>
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="VendUdf2"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","VendUdf2","")%>" onchange="upperCase(this);" size="15">
				</td>
			</tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "sup-VN03")%> align="right"nowrap><a href="javascript: setCurrentRow('VENDOR DEFAULTS', 'VendUdf3', '', 'VN03'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-VN03", "Udf 3")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN03")%>>
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="VendUdf3"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","VendUdf3","")%>" onchange="upperCase(this);" size="15">
				</td>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN04")%> align="right" nowrap><a href="javascript: setCurrentRow('VENDOR DEFAULTS', 'VendUdf4', '', 'VN04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-VN04", "Udf 4")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN04")%>>
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="VendUdf4"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","VendUdf4","")%>" onchange="upperCase(this);" size="15">
				</td>
			</tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "sup-VN05")%> align="right" nowrap><a href="javascript: setCurrentRow('VENDOR DEFAULTS', 'VendUdf5', '', 'VN05'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-VN05", "Udf 5")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN05")%>>
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="VendUdf5"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","VendUdf5","")%>" onchange="upperCase(this);" size="15">
				</td>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN06")%> align="right" nowrap><a href="javascript: setCurrentRow('VENDOR DEFAULTS', 'VendUdf6', '', 'VN06'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-VN06", "Udf 6")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN06")%>>
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="VendUdf6"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","VendUdf6","")%>" onchange="upperCase(this);" size="15">
				</td>
			</tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "sup-VN07")%> align="right" nowrap><a href="javascript: setCurrentRow('VENDOR DEFAULTS', 'VendUdf7', '', 'VN07'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-VN07", "Udf 7")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN07")%>>
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="VendUdf7"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","VendUdf7","")%>" onchange="upperCase(this);" size="15">
				</td>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN08")%> align="right"nowrap><a href="javascript: setCurrentRow('VENDOR DEFAULTS', 'VendUdf8', '', 'VN08'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-VN08", "Udf 8")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN08")%>>
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="VendUdf8"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","VendUdf8","")%>" onchange="upperCase(this);" size="15">
				</td>
				</tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "sup-VN09")%> align="right"nowrap><a href="javascript: setCurrentRow('VENDOR DEFAULTS', 'VendUdf9', '', 'VN09'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-VN09", "Udf 9")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN09")%>>
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="VendUdf9"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","VendUdf9","")%>" onchange="upperCase(this);" size="15">
				</td>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN10")%> align="right"nowrap><a href="javascript: setCurrentRow('VENDOR DEFAULTS', 'VendUdf10', '', 'VN10'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-VN10", "Udf 10")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "sup-VN10")%>>
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="VendUdf10"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("VENDOR DEFAULTS","VendUdf10","")%>" onchange="upperCase(this);" size="15">
				</td>
			</tr>
			<tr><td colspan="4"><br></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "sup-inspectionRequired")%> align="right" colspan=2><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-inspectionRequired", "Inspection Required")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "sup-inspectionRequired")%> colspan="2">
					<tsa:hidden name="Property_section" value="VENDOR DEFAULTS"/>
					<tsa:hidden name="Property_property" value="InspectionReqd"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"VENDOR DEFAULTS\",\"InspectionReqd\",\"\")%>"/>
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("VENDOR DEFAULTS","InspectionReqd","N").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'VENDOR DEFAULTS', 'InspectionReqd');">
				</td>
			</tr>
			</table>
		</div>

		<div id="Requisition" style="visibility: hidden; display:none; position:absolute; left:15px; height:340px;">
			<table border="0" cellspacing="1" cellpadding="1" width=100%>
			<tr><td colspan="2" height=30px>&nbsp;&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitiondefaults", "Requisition Defaults")%></b></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "req-shipToCode")%> align="right"><a href="javascript: setCurrentRow('REQ DEFAULTS', 'ShipToCode', 'ship_to'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-shipToCode", "Ship To")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-shipToCode")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="ShipToCode"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "ShipToCode", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "req-dateOffset")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-dateOffset", "Date Offset")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "req-dateOffset")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="DateOffset"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "DateOffset", "")%>"></td></tr>
			<tr>
				<td <%=HtmlWriter.isVisible(oid, "req-shp-attention")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-shp-attention", "Attention")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "req-shp-attention")%> nowrap>
					<input type="checkbox" name="check_shipAttn" <%if (propertiesManager.getProperty("REQ DEFAULTS", "ShipAttn", "").equals("Y")) { %>checked<% } %> onchange="setCkBoxValue(this, 'REQ DEFAULTS', 'ShipAttn');">Use Requisitioner Name
					<tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
					<tsa:hidden name="Property_property" value="ShipAttn"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"REQ DEFAULTS\", \"ShipAttn\", \"\")%>"/>
				</td>
				<td <%=HtmlWriter.isVisible(oid, "req-language")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-language", "Language")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "req-language")%>>
					<tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
					<tsa:hidden name="Property_property" value="Language"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "Language", "(Default)")%>">
				</td>
			</tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "req-billToCode")%> align="right"><a href="javascript: setCurrentRow('REQ DEFAULTS', 'BillToCode', 'bill_to'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-billToCode", "Bill To")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-billToCode")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="BillToCode"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "BillToCode", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "req-receiptOptions")%> align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-receiptOptions", "Receipt Option")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "req-receiptOptions")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="ReceiptRequired"/>
				<select name="Property_value" size=1>
				<%	if (propertiesManager.getProperty("REC SELECTIONS", "X", "Y").equals("Y")) {%>
					<%	if (!oid.equals("MSG07P")) {%>
						<option <% if (reqReceiptOption.equals("X")) {%> SELECTED <%}%> value=""></option>
					<%	}%>
				<%	}
					if (propertiesManager.getProperty("REC SELECTIONS", "R", "Y").equals("Y")) {%>
						<option <% if (reqReceiptOption.equals("R")) {%> SELECTED <%}%> value="R"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiptrequired", "Receipt Required", false)%></option>
				<%	}
					if (propertiesManager.getProperty("REC SELECTIONS", "P", "Y").equals("Y")) {%>
						<option <% if (reqReceiptOption.equals("P")) {%> SELECTED <%}%> value="P"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "previouslyreceived", "Previously Received", false)%></option>
				<%	}
					if (propertiesManager.getProperty("REC SELECTIONS", "N", "Y").equals("Y")) {%>
					<%	if (!oid.equals("MSG07P")) {%>
						<option <% if (reqReceiptOption.equals("N")) {%> SELECTED <%}%> value="N"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noreceiptrequired", "No Receipt Required", false)%></option>
					<%	}%>
				<%	}
					if (propertiesManager.getProperty("REC SELECTIONS", "E", "Y").equals("Y")) {%>
						<option <% if (reqReceiptOption.equals("E")) {%> SELECTED <%}%> value="E"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enduserreceipt", "End User Receipt", false)%></option>
				<%	}%>
				</select></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "req-bil-attention")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-bil-attention", "Attention")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "req-bil-attention")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="BillToContact"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "BillToContact", "")%>"></td>
				<td <%=HtmlWriter.isVisible(oid, "req-currency")%> align="right"><a href="javascript: setCurrentRow('REQ DEFAULTS', 'CurrencyCode', 'curr_code'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-currency", "Currency")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-currency")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="CurrencyCode"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "CurrencyCode", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "req-shipVia")%> align="right"><a href="javascript: setCurrentRow('REQ DEFAULTS', 'ShippingCode', 'shipvia'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-shipVia", "Ship Via")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-shipVia")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="ShippingCode"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "ShippingCode", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "req-taxCode")%> align="right"><a href="javascript: setCurrentRow('REQ DEFAULTS', 'TaxCode', 'taxcode'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-taxCode", "Tax Code")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-taxCode")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="TaxCode"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "TaxCode", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "req-priority")%> align="right"><a href="javascript: setCurrentRow('REQ DEFAULTS', 'PriorityCode', 'priority'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-priority", "Priority")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-priority")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="PriorityCode"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "PriorityCode", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "req-buyer")%> align="right"><a href="javascript: setCurrentRow('REQ DEFAULTS', 'Buyer', 'buyer'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-buyer", "Buyer")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-buyer")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Buyer"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "Buyer", "")%>" onchange="upperCase(this);"></td></tr>
			<tr>
				<td <%=HtmlWriter.isVisible(oid, "req-inventoryLocation")%> align="right" nowrap><a href="javascript: setCurrentRow('REQ DEFAULTS', 'InventoryLocation', 'item_location'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-inventoryLocation", "Inventory Location")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-inventoryLocation")%>>
					<tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
					<tsa:hidden name="Property_property" value="InventoryLocation"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "InventoryLocation", "")%>" onchange="upperCase(this);">
				</td>
				<td <%=HtmlWriter.isVisible(oid, "req-taxCodeFromShipTo")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-taxCodeFromShipTo", "Tax Code from ShipTo")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-taxCodeFromShipTo")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
					<tsa:hidden name="Property_property" value="TaxCodeFromShipTo"/>
					<input type="checkbox" name="check_taxCodeFromShipTo" <%if(propertiesManager.getProperty("REQ DEFAULTS", "TaxCodeFromShipTo", "Y").equals("Y")){%>checked<%}%> onchange="setCkBoxValue(this, 'REQ DEFAULTS', 'TaxCodeFromShipTo');">
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"REQ DEFAULTS\", \"TaxCodeFromShipTo\", \"Y\")%>"/>
				</td>
			</tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "req-defaultUseTaxPercentCanadian")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-defaultUseTaxPercentCanadian", "Canadian Use Tax Percent")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "req-defaultUseTaxPercentCanadian")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="UseTaxPercentCanadian"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "UseTaxPercentCanadian", "0")%>"></td>
				<td colspan="2"></td>
			</tr>
			<tr><td colspan="4"><br></td></tr>
			<tr>
				<td align="right" <%=HtmlWriter.isVisible(oid, "req-RQ01")%> align="right" nowrap><a href="javascript: setCurrentRow('REQ DEFAULTS', 'Udf1Code', '', 'RQ01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ01", "Udf 1")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-RQ01")%>>
					<tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
					<tsa:hidden name="Property_property" value="Udf1Code"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "Udf1Code", "")%>" onchange="upperCase(this);">
				</td>
				<td <%=HtmlWriter.isVisible(oid, "req-RQ02")%> align="right" nowrap><a href="javascript: setCurrentRow('REQ DEFAULTS', 'Udf2Code', '', 'RQ02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ02", "Udf 2")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-RQ02")%>>
					<tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
					<tsa:hidden name="Property_property" value="Udf2Code"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "Udf2Code", "")%>" onchange="upperCase(this);">
				</td>
			</tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "req-RQ03")%> align="right"><a href="javascript: setCurrentRow('REQ DEFAULTS', 'Udf3Code', '', 'RQ03'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ03", "Udf 3")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-RQ03")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf3Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "Udf3Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "req-RQ04")%> align="right"><a href="javascript: setCurrentRow('REQ DEFAULTS', 'Udf4Code', '', 'RQ04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ04", "Udf 4")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-RQ04")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf4Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "Udf4Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "req-RQ05")%> align="right"><a href="javascript: setCurrentRow('REQ DEFAULTS', 'Udf5Code', '', 'RQ05'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ05", "Udf 5")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-RQ05")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf5Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "Udf5Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "req-RQ06")%> align="right"><a href="javascript: setCurrentRow('REQ DEFAULTS', 'Udf6Code', '', 'RQ06'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ06", "Udf 6")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-RQ06")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf6Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "Udf6Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "req-RQ07")%> align="right"><a href="javascript: setCurrentRow('REQ DEFAULTS', 'Udf7Code', '', 'RQ07'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ07", "Udf 7")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-RQ07")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf7Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "Udf7Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "req-RQ08")%> align="right"><a href="javascript: setCurrentRow('REQ DEFAULTS', 'Udf8Code', '', 'RQ08'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ08", "Udf 8")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-RQ08")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf8Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "Udf8Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "req-RQ09")%> align="right" nowrap><a href="javascript: setCurrentRow('REQ DEFAULTS', 'Udf9Code', '', 'RQ09'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ09", "Udf 9")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-RQ09")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf9Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "Udf9Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "req-RQ10")%> align="right"><a href="javascript: setCurrentRow('REQ DEFAULTS', 'Udf10Code', '', 'RQ10'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-RQ10", "Udf 10")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-RQ10")%>><tsa:hidden name="Property_section" value="REQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf10Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("REQ DEFAULTS", "Udf10Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td colspan="2" height=30px>&nbsp;&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionlinedefaults", "Requisition Line Item Defaults")%></b></td></tr>
			<tr><td align="right" <%=HtmlWriter.isVisible(oid, "req-LN01")%>><a href="javascript: setCurrentRow('RQL DEFAULTS', 'Udf1Code', '', 'LN01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-LN01", "Udf 1")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-LN01")%>><tsa:hidden name="Property_section" value="RQL DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf1Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RQL DEFAULTS", "Udf1Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "req-LN02")%> align="right"><a href="javascript: setCurrentRow('RQL DEFAULTS', 'Udf2Code', '', 'LN02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-LN02", "Udf 2")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-LN02")%>><tsa:hidden name="Property_section" value="RQL DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf2Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RQL DEFAULTS", "Udf2Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "req-LN03")%> align="right"><a href="javascript: setCurrentRow('RQL DEFAULTS', 'Udf3Code', '', 'LN03'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-LN03", "Udf 3")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-LN03")%>><tsa:hidden name="Property_section" value="RQL DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf3Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RQL DEFAULTS", "Udf3Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "req-LN04")%> align="right"><a href="javascript: setCurrentRow('RQL DEFAULTS', 'Udf4Code', '', 'LN04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-LN04", "Udf 4")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-LN04")%>><tsa:hidden name="Property_section" value="RQL DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf4Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RQL DEFAULTS", "Udf4Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "req-LN05")%> align="right"><a href="javascript: setCurrentRow('RQL DEFAULTS', 'Udf5Code', '', 'LN05'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "req-LN05", "Udf 5")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "req-LN05")%>><tsa:hidden name="Property_section" value="RQL DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf5Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RQL DEFAULTS", "Udf5Code", "")%>" onchange="upperCase(this);"></td>
			</tr>
			</table>
		</div>

		<div id="Solicitation" style="visibility: hidden; display:none; position:absolute; left:25px; height:440px;">
			<table border="0" cellspacing="1" cellpadding="1" width=100%>
			<tr><td colspan="2" height=30px>&nbsp;&nbsp;<b>Solicitation Defaults</b></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "rfq-bidResponse")%> align="right"><a href="javascript: setCurrentRow('RFQ DEFAULTS', 'ResponseCode', '', 'RESP'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-bidResponse", "Bid Response")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-bidResponse")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="ResponseCode"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "ResponseCode", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-dueDateOffset")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-dueDateOffset", "Due Date Offset")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-dueDateOffset")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="DueDateOffset"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "DueDateOffset", "")%>"></td></tr>
			<tr><td colspan="4"><br></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "rfq-webpost")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-webpost", "Webpost")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-webpost")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Webpost"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "Webpost", "")%>"></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-bidAccess")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-bidAccess", "Bid Access")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-bidAccess")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="BidAccess"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "BidAccess", "")%>"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "rfq-auctionType")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-auctionType", "Auction Type")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-auctionType")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="AuctionType"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "AuctionType", "")%>"></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-bidDueTime")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-bidDueTime", "Bid Due Time")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-bidDueTime")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="BidDueTime"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "BidDueTime", "")%>"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "rfq-timeZone")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-timeZone", "Time Zone")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-timeZone")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="TimeZone"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "TimeZone", "")%>"></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-bidItemOptions")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-BidItemOptions", "Bid Item Options")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-bidItemOptions")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="BidItemOptions"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "BidItemOptions", "")%>"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "rfq-lowestBidSource")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-lowestBidSource", "Lowest Bid Source")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-lowestBidSource")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="LowestBidSource"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "LowestBidSource", "")%>"></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-lowestBidRequired")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-lowestBidRequired", "Lowest Bid Req")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-lowestBidRequired")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="LowestBidReq"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "LowestBidReq", "")%>"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "rfq-lowestDisplay")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-lowestDisplay", "Lowest Display")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-lowestDisplay")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="LowestBidDisplay"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "LowestBidDisplay", "")%>"></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-currency")%> align="right"><a href="javascript: setCurrentRow('RFQ DEFAULTS', 'CURRENCY', 'curr_code'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-currency", "Currency")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-currency")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="CURRENCY"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS","Currency"," ")%>" onchange="upperCase(this);"></tr>
			<tr><td colspan="4"><br></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "rfq-RF01")%> align="right"><a href="javascript: setCurrentRow('RFQ DEFAULTS', 'Udf1Code', '', 'RF01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-RF01", "Udf 1")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-RF01")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf1Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "Udf1Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-RF02")%> align="right"><a href="javascript: setCurrentRow('RFQ DEFAULTS', 'Udf2Code', '', 'RF02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-RF02", "Udf 2")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-RF02")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf2Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "Udf2Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "rfq-RF03")%> align="right"><a href="javascript: setCurrentRow('RFQ DEFAULTS', 'Udf3Code', '', 'RF03'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-RF03", "Udf 3")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-RF03")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf3Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "Udf3Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-RF04")%> align="right"><a href="javascript: setCurrentRow('RFQ DEFAULTS', 'Udf4Code', '', 'RF04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-RF04", "Udf 4")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-RF04")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf4Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "Udf4Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "rfq-RF05")%> align="right"><a href="javascript: setCurrentRow('RFQ DEFAULTS', 'Udf5Code', '', 'RF05'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-RF05", "Udf 5")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-RF05")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf5Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "Udf5Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-RF06")%> align="right"><a href="javascript: setCurrentRow('RFQ DEFAULTS', 'Udf6Code', '', 'RF06'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-RF06", "Udf 6")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-RF06")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf6Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "Udf6Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "rfq-RF07")%> align="right"><a href="javascript: setCurrentRow('RFQ DEFAULTS', 'Udf7Code', '', 'RF07'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-RF07", "Udf 7")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-RF07")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf7Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "Udf7Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-RF08")%> align="right"><a href="javascript: setCurrentRow('RFQ DEFAULTS', 'Udf8Code', '', 'RF08'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-RF08", "Udf 8")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-RF08")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf8Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "Udf8Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "rfq-RF09")%> align="right"><a href="javascript: setCurrentRow('RFQ DEFAULTS', 'Udf9Code', '', 'RF09'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-RF09", "Udf 9")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-RF09")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf9Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "Udf9Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-RF10")%> align="right"><a href="javascript: setCurrentRow('RFQ DEFAULTS', 'Udf10Code', '', 'RF10'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-RF10", "Udf 10")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "rfq-RF10")%>><tsa:hidden name="Property_section" value="RFQ DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf10Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("RFQ DEFAULTS", "Udf10Code", "")%>" onchange="upperCase(this);"></td></tr>
			</table>
		</div>

		<div id="PurchaseOrder" style="visibility: hidden; display:none; left:25px; ">
			<table border="0" cellspacing="1" cellpadding="1" width=100%>
			<tr><td colspan="2" height="30px">&nbsp;&nbsp;<b>Purchase Order Defaults</b></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "po-shipToCode")%> align="right"><a href="javascript: setCurrentRow('PO DEFAULTS', 'ShipToCode', 'ship_to'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shipToCode", "Ship To")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "po-shipToCode")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="ShipToCode"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "ShipToCode", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "po-dateOffset")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-dateOffset", "Date Offset")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "po-dateOffset")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="DateOffset"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "DateOffset", "")%>"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "po-shp-attention")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shp-attention", "Attention")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "po-shp-attention")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="ShipToContact"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "ShipToContact", "")%>"></td>
				<td <%=HtmlWriter.isVisible(oid, "po-language")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-language", "Language")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "po-language")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Language"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "Language", "(Default)")%>"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "po-billToCode")%> align="right"><a href="javascript: setCurrentRow('PO DEFAULTS', 'BillToCode', 'bill_to'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-billToCode", "Bill To")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "po-billToCode")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="BillToCode"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "BillToCode", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "po-receiptOptions")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-receiptOptions", "Receipt Option")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "po-receiptOptions")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="ReceiptRequired"/>
				<select name="Property_value" size=1>
				<%	if (propertiesManager.getProperty("REC SELECTIONS", "R", "Y").equals("Y")) {%>
						<option <% if (reqReceiptOption.equals("R")) {%> SELECTED <%}%> value="R"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receiptrequired", "Receipt Required", false)%></option>
				<%	}
					if (propertiesManager.getProperty("REC SELECTIONS", "P", "Y").equals("Y")) {%>
						<option <% if (reqReceiptOption.equals("P")) {%> SELECTED <%}%> value="P"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "previouslyreceived", "Previously Received", false)%></option>
				<%	}
					if (propertiesManager.getProperty("REC SELECTIONS", "N", "Y").equals("Y")) {%>
						<option <% if (reqReceiptOption.equals("N")) {%> SELECTED <%}%> value="N"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noreceiptrequired", "No Receipt Required", false)%></option>
				<%	}
					if (propertiesManager.getProperty("REC SELECTIONS", "E", "Y").equals("Y")) {%>
						<option <% if (reqReceiptOption.equals("E")) {%> SELECTED <%}%> value="E"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "enduserreceipt", "End User Receipt", false)%></option>
				<%	}%>
				</select></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "po-bil-attention")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-bil-attention", "Attention")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "po-bil-attention")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="BillToContact"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "BillToContact", "")%>"></td>
				<td <%=HtmlWriter.isVisible(oid, "po-taxCode")%> align="right"><a href="javascript: setCurrentRow('PO DEFAULTS', 'TaxCode', 'taxcode'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-taxCode", "Tax Code")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "po-taxCode")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="TaxCode"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "TaxCode", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "po-priority")%> align="right"><a href="javascript: setCurrentRow('PO DEFAULTS', 'PriorityCode', 'priority'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-priority", "Priority")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "po-priority")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="PriorityCode"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "PriorityCode", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "po-taxCodeFromShipTo")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-taxCodeFromShipTo", "Tax Code from ShipTo")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "po-taxCodeFromShipTo")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
					<tsa:hidden name="Property_property" value="TaxCodeFromShipTo"/>
					<input type="checkbox" name="check_taxCodeFromShipTo" <%if(propertiesManager.getProperty("PO DEFAULTS", "TaxCodeFromShipTo", "N").equals("Y")){%>checked<%}%> onchange="setCkBoxValue(this, 'PO DEFAULTS', 'TaxCodeFromShipTo');">
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"PO DEFAULTS\", \"TaxCodeFromShipTo\", \"N\")%>"/>
				</td>
			</tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "po-defaultUseTaxPercentCanadian")%> align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-defaultUseTaxPercentCanadian", "Canadian Use Tax Percent")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "po-defaultUseTaxPercentCanadian")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="UseTaxPercentCanadian"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "UseTaxPercentCanadian", "0")%>"></td>
				<td colspan="2"></td>
			</tr>
			<tr><td colspan="4"><br></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "po-PO01")%> align="right"><a href="javascript: setCurrentRow('PO DEFAULTS', 'Udf1Code', '', 'PO01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO01", "Udf 1")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "po-PO01")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf1Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "Udf1Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "po-PO02")%> align="right"><a href="javascript: setCurrentRow('PO DEFAULTS', 'Udf2Code', '', 'PO02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO02", "Udf 2")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "po-PO02")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf2Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "Udf2Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "po-PO03")%> align="right"><a href="javascript: setCurrentRow('PO DEFAULTS', 'Udf3Code', '', 'PO03'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO03", "Udf 3")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "po-PO03")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf3Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "Udf3Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "po-PO04")%> align="right"><a href="javascript: setCurrentRow('PO DEFAULTS', 'Udf4Code', '', 'PO04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO04", "Udf 4")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "po-PO04")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf4Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "Udf4Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "po-PO05")%> align="right"><a href="javascript: setCurrentRow('PO DEFAULTS', 'Udf5Code', '', 'PO05'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO05", "Udf 5")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "po-PO05")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf5Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "Udf5Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "po-PO06")%> align="right"><a href="javascript: setCurrentRow('PO DEFAULTS', 'Udf6Code', '', 'PO06'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO06", "Udf 6")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "po-PO06")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf6Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "Udf6Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "po-PO07")%> align="right"><a href="javascript: setCurrentRow('PO DEFAULTS', 'Udf7Code', '', 'PO07'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO07", "Udf 7")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "po-PO07")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf7Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "Udf7Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "po-PO08")%> align="right"><a href="javascript: setCurrentRow('PO DEFAULTS', 'Udf8Code', '', 'PO08'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO08", "Udf 8")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "po-PO08")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf8Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "Udf8Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "po-PO09")%> align="right" nowrap><a href="javascript: setCurrentRow('PO DEFAULTS', 'Udf9Code', '', 'PO09'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO09", "Udf 9")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "po-PO09")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf9Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "Udf9Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "po-PO10")%> align="right"><a href="javascript: setCurrentRow('PO DEFAULTS', 'Udf10Code', '', 'PO10'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-PO10", "Udf 10")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "po-PO10")%>><tsa:hidden name="Property_section" value="PO DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf10Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("PO DEFAULTS", "Udf10Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td colspan="4"><br></td></tr>
			<tr>
				<td colspan="4" width="100%">
				<table border="0" cellspacing="1" cellpadding="1" width="100%">
					<tr><td colspan="4" height="30px">&nbsp;&nbsp;<b>Print Order Defaults</b></td></tr>
					<tr>
						<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "PrintEffectiveDates", "Print Effective Dates")%>:</td>
						<td align="left"><tsa:hidden name="Property_section" value="Print Orders"/>
							<tsa:hidden name="Property_property" value="PrintEffectiveDates"/>
							<input type="checkbox" name="check_printEffectiveText" <%if(propertiesManager.getProperty("Print Orders", "PrintEffectiveDates", "N").equals("Y")){%>checked<%}%> onchange="setCkBoxValue(this, 'Print Orders', 'PrintEffectiveDates');">
							<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"Print Orders\", \"PrintEffectiveDates\", \"N\")%>"/>
						</td>
						<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "PrintEffectiveText", "Print Effective Text")%>:</td>
						<td><tsa:hidden name="Property_section" value="Print Orders"/>
							<tsa:hidden name="Property_property" value="PrintEffectiveText"/>
							<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("Print Orders", "PrintEffectiveText", "THIS ORDER IS EFFECTIVE FROM")%>" size="50">
						</td>
					</tr>
					<tr>
					    <td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "PrintReplaceOrder", "Print Replace Order")%>:</td>
						<td><tsa:hidden name="Property_section" value="Print Orders"/>
							<tsa:hidden name="Property_property" value="PrintReplaceOrder"/>
							<input type="checkbox" name="check_printReplaceOrder" <%if(propertiesManager.getProperty("Print Orders", "PrintReplaceOrder", "N").equals("Y")){%>checked<%}%> onchange="setCkBoxValue(this, 'Print Orders', 'PrintReplaceOrder');">
							<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"Print Orders\", \"PrintReplaceOrder\", \"N\")%>"/>
						</td>
						<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "PrintReplaceText", "Print Replace Text")%>:</td>
						<td><tsa:hidden name="Property_section" value="Print Orders"/>
							<tsa:hidden name="Property_property" value="PrintReplaceText"/>
							<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("Print Orders", "PrintReplaceText", "THIS REPLACES ORDER NO. E")%>" size="50">
						</td>
					</tr>
					<tr>
						<td align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "PrintRevisionWarning", "Print Revision Warning")%>:</td>
						<td><tsa:hidden name="Property_section" value="Print Orders"/>
							<tsa:hidden name="Property_property" value="PrintRevisionWarning"/>
							<input type="checkbox" name="check_printRevisionWarning" <%if(propertiesManager.getProperty("Print Orders", "PrintRevisionWarning", "N").equals("Y")){%>checked<%}%> onchange="setCkBoxValue(this, 'Print Orders', 'PrintRevisionWarning');">
							<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"Print Orders\", \"PrintRevisionWarning\", \"N\")%>"/>
						</td>
						<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "PrintRevWarnText", "Print Revision Warning Text")%>:</td>
						<td><tsa:hidden name="Property_section" value="Print Orders"/>
							<tsa:hidden name="Property_property" value="PrintRevWarnText"/>
							<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("Print Orders", "PrintRevWarnText", "*** THIS IS REVISION [REV] ***")%>" size="50">
						</td>
					</tr>
					<tr>
						<td align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "PrintServiceTCs", "Print Service T&Cs")%>:</td>
						<td><tsa:hidden name="Property_section" value="Print Orders"/>
							<tsa:hidden name="Property_property" value="PrintServiceTCs"/>
							<input type="checkbox" name="check_printServiceTCs" <%if(propertiesManager.getProperty("Print Orders", "PrintServiceTCs", "N").equals("Y")){%>checked<%}%> onchange="setCkBoxValue(this, 'Print Orders', 'PrintServiceTCs');">
							<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"Print Orders\", \"PrintServiceTCs\", \"N\")%>"/>
						</td>
					</tr>
					</table>
					</td>
				</tr>
				<!--tr>
					<td colspan="4" width="100%">
						<table border="0" cellspacing="1" cellpadding="1" width="100%" align="left">
							<tr>
								<td colspan="2" height="30px">&nbsp;&nbsp;<b>Review</b></td>
							</tr>
							<tr>
								<td align="right" width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "review", "Review")%>:</td>
								<td align="left"><tsa:hidden name="Property_section" value="REVIEWFINALIZE"/>
								<tsa:hidden name="Property_property" value="ENABLED"/>
								<input type="checkbox" name="check_reviewfinalize" <%if(propertiesManager.getProperty("REVIEWFINALIZE", "ENABLED", "N").equals("Y")){%>checked<%}%> onchange="setCkBoxValue(this, 'REVIEWFINALIZE', 'ENABLED');">
								<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"REVIEWFINALIZE\", \"ENABLED\", \"N\")%>"/></td>
							</tr>
						</table>
					</td>
				</tr-->
			</table>
		</div>
		<div id="CatalogItem" style="visibility: hidden; display:none; position:absolute; left:15px; height:440px;">
			<table border="0" cellspacing="1" cellpadding="1" width="100%">
			<tr><td colspan="2" height=30px>&nbsp;&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalogitemdefaults", "Catalog Item Defaults")%></b></td></tr>
			<tr><td align="right" <%=HtmlWriter.isVisible(oid, "cat-LN01")%>><a href="javascript: setCurrentRow('CATALOG ITEM DEFAULTS', 'Udf1Code', '', 'LN01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN01", "Udf 1")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "cat-LN01")%>><tsa:hidden name="Property_section" value="CATALOG ITEM DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf1Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("CATALOG ITEM DEFAULTS", "Udf1Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "cat-LN02")%> align="right"><a href="javascript: setCurrentRow('CATALOG ITEM DEFAULTS', 'Udf2Code', '', 'LN02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN02", "Udf 2")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "cat-LN02")%>><tsa:hidden name="Property_section" value="CATALOG ITEM DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf2Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("CATALOG ITEM DEFAULTS", "Udf2Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "cat-LN03")%> align="right"><a href="javascript: setCurrentRow('CATALOG ITEM DEFAULTS', 'Udf3Code', '', 'LN03'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN03", "Udf 3")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "cat-LN03")%>><tsa:hidden name="Property_section" value="CATALOG ITEM DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf3Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("CATALOG ITEM DEFAULTS", "Udf3Code", "")%>" onchange="upperCase(this);"></td>
				<td <%=HtmlWriter.isVisible(oid, "cat-LN04")%> align="right"><a href="javascript: setCurrentRow('CATALOG ITEM DEFAULTS', 'Udf4Code', '', 'LN04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN04", "Udf 4")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "cat-LN04")%>><tsa:hidden name="Property_section" value="CATALOG ITEM DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf4Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("CATALOG ITEM DEFAULTS", "Udf4Code", "")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "cat-LN05")%> align="right"><a href="javascript: setCurrentRow('CATALOG ITEM DEFAULTS', 'Udf5Code', '', 'LN05'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "cat-LN05", "Udf 5")%>:</a></td>
				<td <%=HtmlWriter.isVisible(oid, "cat-LN05")%>><tsa:hidden name="Property_section" value="CATALOG ITEM DEFAULTS"/>
				<tsa:hidden name="Property_property" value="Udf5Code"/>
				<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("CATALOG ITEM DEFAULTS", "Udf5Code", "")%>" onchange="upperCase(this);"></td>
			</tr>
			</table>
		</div>

		<div id="NonStandardItem" style="visibility: hidden; display:none; position:absolute; left:15px; height:440px;">
			<table border="0" cellspacing="1" cellpadding="1">
			<tsa:tr><tsa:td colspan="2" height="30px" id="nonStandardItemDefaults">&nbsp;&nbsp;<b><tsa:label labelName="nonStandardItemDefaults" defaultString="Non Standard Item Defaults"></tsa:label></b></tsa:td></tsa:tr>
			<tsa:tr>
				<tsa:td align="right" id="uom" width="180px"><a href="javascript: setCurrentRow('NON-STANDARD ITEM DEFAULTS', 'UMCODE', 'unitofmeasure'); void(0);"><tsa:label labelName="uom" defaultString="UOM" checkRequired="false" /></a>:</tsa:td>
				<tsa:td>
					<tsa:hidden name="Property_section" value="NON-STANDARD ITEM DEFAULTS"/>
					<tsa:hidden name="Property_property" value="UMCODE"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("NON-STANDARD ITEM DEFAULTS", "UMCODE", "EA")%>" onChange="upperCase(this);">
				</tsa:td>
			</tsa:tr>
			<tsa:tr>
				<tsa:td align="right" id="commodityCode"><a href="javascript: setCurrentRow('NON-STANDARD ITEM DEFAULTS', 'COMMODITYCODE', 'commodity'); void(0);"><tsa:label labelName="commodityCode" defaultString="Commodity Code" checkRequired="false" /></a>:</tsa:td>
				<tsa:td>
					<tsa:hidden name="Property_section" value="NON-STANDARD ITEM DEFAULTS"/>
					<tsa:hidden name="Property_property" value="COMMODITYCODE"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("NON-STANDARD ITEM DEFAULTS", "COMMODITYCODE", "")%>" onChange="upperCase(this);">
				</tsa:td>
			</tsa:tr>
			<tsa:tr>
				<tsa:td align="right" id="taxable"><tsa:label labelName="taxable" defaultString="Taxable"/>:</tsa:td>
				<tsa:td>
					<tsa:hidden name="Property_section" value="NON-STANDARD ITEM DEFAULTS"/>
					<tsa:hidden name="Property_property" value="TAXABLE"/>
					<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"NON-STANDARD ITEM DEFAULTS\", \"TAXABLE\", \"Y\")%>" />
					<input type=checkbox name="ckbox" value='Y' <% if (propertiesManager.getProperty("NON-STANDARD ITEM DEFAULTS","TAXABLE","Y").equalsIgnoreCase("Y")) {%> checked<%}%> onchange="setCkBoxValue(this, 'NON-STANDARD ITEM DEFAULTS', 'TAXABLE');">
				</tsa:td>
			</tsa:tr>
			</table>
		</div>
		<div id="Budget" style="visibility: hidden; display:none; position:absolute; left:15px; height:440px;">
			<table border="0" cellspacing="1" cellpadding="1" width="100%">
			<tr>
				<td colspan="4" width="100%">
					<table border="0" cellspacing="1" cellpadding="1" width="100%" align="left">
						<tr>
							<td colspan="4" height="30px">&nbsp;&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetdefaults", "Budget Defaults")%></b></td>
						</tr>
						<tr>
							<td colspan="2" align="right" width="38%"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetserviceactive", "Enable Budget Service ")%> :</td>
							<td colspan="2" align="left"><tsa:hidden name="Property_section" value="BUDGET"/>
							<tsa:hidden name="Property_property" value="BUDGETSERVICEACTIVE"/>
							<input type="checkbox" name="check_budgetservice" <%if(propertiesManager.getProperty("BUDGET", "BUDGETSERVICEACTIVE", "N").equals("Y")){%>checked<%}%> onchange="setCkBoxValue(this, 'BUDGET', 'BUDGETSERVICEACTIVE');">
							<tsa:hidden name="Property_value" value="<%=propertiesManager.getProperty(\"BUDGET\", \"BUDGETSERVICEACTIVE\", \"N\")%>"/></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</div>

		<div id="User" style="visibility: hidden; display:none; position:absolute; left:15px; height:440px;">
			<table border="0" cellspacing="1" cellpadding="1" width="100%">
			<tr><td colspan="2" height=30px>&nbsp;&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "userdefaults", "User Defaults")%></b></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "country")%> align=right><a href="javascript: browseLookup('as_countryCode', 'country'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "country", "Country")%></a>:</td>
				<td <%=HtmlWriter.isVisible(oid, "country")%>><tsa:hidden name="as_countryCode" value="<%=propertiesManager.getProperty(\"USER DEFAULTS\",\"COUNTRY\",\"USA\")%>"/>
					<tsa:hidden name="Property_section" value="USER DEFAULTS"/>
					<tsa:hidden name="Property_property" value="COUNTRY"/>
					<input type=text name="Property_value" value="<%=propertiesManager.getProperty("USER DEFAULTS","COUNTRY","USA")%>"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "language")%> align=right><a href="javascript: browseStd('as_language', 'LANG'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "language", "Language")%></a>:</td>
				<td <%=HtmlWriter.isVisible(oid, "language")%>><tsa:hidden name="as_language" value="<%=propertiesManager.getProperty(\"USER DEFAULTS\",\"LANGUAGE\",\"EN\")%>" />
					<tsa:hidden name="Property_section" value="USER DEFAULTS"/>
					<tsa:hidden name="Property_property" value="LANGUAGE"/>
					<input type=text name="Property_value" value="<%=propertiesManager.getProperty("USER DEFAULTS","LANGUAGE","EN")%>"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "currency")%> align=right><a href="javascript: setCurrentRow('USER DEFAULTS', 'CURRENCYCODE', 'curr_code'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "currency", "Currency")%></a>:</td>
				<td <%=HtmlWriter.isVisible(oid, "currency")%>><tsa:hidden name="as_currencyCode" value="<%=propertiesManager.getProperty(\"MISC\",\"BASECURRENCY\",\"USD\")%>" />
					<tsa:hidden name="Property_section" value="MISC"/>
					<tsa:hidden name="Property_property" value="BASECURRENCY"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("MISC","CURRENCYCODE","USD")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "timezone")%> align=right><a href="javascript: browseStd('as_timeZone', 'TMZN'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "timezone", "Time Zone")%></a>:</td>
				<td <%=HtmlWriter.isVisible(oid, "timezone")%>><tsa:hidden name="as_timeZone" value="<%=propertiesManager.getProperty(\"USER DEFAULTS\",\"TIMEZONE\",\"GMT\")%>" />
					<tsa:hidden name="Property_section" value="USER DEFAULTS"/>
					<tsa:hidden name="Property_property" value="TIMEZONE"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("USER DEFAULTS","TIMEZONE","GMT")%>" onchange="upperCase(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "dateformat")%> align=right><a href="javascript: browseStd('as_dateFormat', 'DFMT'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateformat", "Date Format")%></a>:</td>
				<td <%=HtmlWriter.isVisible(oid, "dateformat")%>><tsa:hidden name="as_dateFormat" value="<%=propertiesManager.getProperty(\"MISC\",\"DATEFORMAT\",\"MM-DD-YYYY\")%>" />
					<tsa:hidden name="Property_section" value="MISC"/>
					<tsa:hidden name="Property_property" value="DATEFORMAT"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("MISC","DATEFORMAT","MM-DD-YYYY")%>" onchange="correctDateFormat(this);"></td></tr>
			<tr><td <%=HtmlWriter.isVisible(oid, "callforward")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "callforward", "Call Forward")%>:</td>
				<td <%=HtmlWriter.isVisible(oid, "callforward")%>><tsa:hidden name="as_callForward" value="<%=propertiesManager.getProperty(\"USER DEFAULTS\",\"CALLFORWARD\",\"60\")%>" />
					<tsa:hidden name="Property_section" value="USER DEFAULTS"/>
					<tsa:hidden name="Property_property" value="CALLFORWARD"/>
					<input type="text" name="Property_value" value="<%=propertiesManager.getProperty("USER DEFAULTS","CALLFORWARD","60")%>"></td></tr>
			</table>
		</div>


	</td>
	<td width="180px" align="right" valign="top">
		<hr size="0" width="170px">
<%	int istep = 1;%>
		<table border="0" width="170px" valign="top" cellpadding="2" cellspacing="0" id="processSteps">
		<tr height="25px">
			<td><img id="SupplierProcessImg" src="<%=contextPath%>/images/step<%=istep%>on.gif" class="processOnImg" border="0"></td>
			<td nowrap>
				<a href="javascript: setDefaultOptions('Supplier'); void(0);" id="SupplierProcessLink" class=processOn><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplierDefaults", "Supplier Defaults")%></a>
			</td>
		</tr>
<%	if (reqsActive) {
		istep++;%>
		<tr height="25px">
			<td><img id="RequisitionProcessImg" src="<%=contextPath%>/images/step<%=istep%>off.gif" class="processOffImg" border="0"></td>
			<td nowrap>
				<a href="javascript: setDefaultOptions('Requisition'); void(0);" id="RequisitionProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionDefaults", "Requisition Defaults")%></a>
			</td>
		</tr>
<%	}
	if (rfqsActive) {
		istep++;%>
		<tr height="25px">
			<td><img id="SolicitationProcessImg" src="<%=contextPath%>/images/step<%=istep%>off.gif" class="processOffImg" border="0"></td>
			<td nowrap>
				<a href="javascript: setDefaultOptions('Solicitation');  void(0);" id="SolicitationProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "solicitationDefaults", "Solicitation Defaults")%></a>
			</td>
		</tr>
<%	}
	if (posActive) {
		istep++;%>
		<tr height="25px">
			<td><img id="PurchaseOrderProcessImg" src="<%=contextPath%>/images/step<%=istep%>off.gif" class="processOffImg" border="0"></td>
			<td nowrap>
				<a href="javascript: setDefaultOptions('PurchaseOrder');  void(0);" id="PurchaseOrderProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "purchaseOrderDefaults", "Purchase Order Defaults")%></a>
			</td>
		</tr>
<%	}
	if (DictionaryManager.isVisible(oid, "cat-LN01") || DictionaryManager.isVisible(oid, "cat-LN02") || DictionaryManager.isVisible(oid, "cat-LN03") || DictionaryManager.isVisible(oid, "cat-LN04") || DictionaryManager.isVisible(oid, "cat-LN05")) {
		istep++;%>
		<tr height="25px">
			<td><img id="CatalogItemProcessImg" src="<%=contextPath%>/images/step<%=istep%>off.gif" class="processOffImg" border="0"></td>
			<td nowrap>
				<a href="javascript: setDefaultOptions('CatalogItem');  void(0);" id="CatalogItemProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "catalogItemDefaults", "Catalog Item Defaults")%></a>
			</td>
		</tr>
<%	}
	if (DictionaryManager.isVisible(oid, "taxable") && DictionaryManager.isVisible(oid, "nonStandardItemDefaults")) {
		istep++;%>
		<tr height="25px">
			<td><img id="NonStandardItemProcessImg" src="<%=contextPath%>/images/step<%=istep%>off.gif" class="processOffImg" border="0"></td>
			<td nowrap>
				<a href="javascript: setDefaultOptions('NonStandardItem');  void(0);" id="NonStandardItemProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "nonStandardItemDefaults", "Non Standard Item Defaults")%></a>
			</td>
		</tr>
<%	}
	if (budgetActive) {
		istep++;%>
		<tr height="25px">
			<td><img id="BudgetProcessImg" src="<%=contextPath%>/images/step<%=istep%>off.gif" class="processOffImg" border="0"></td>
			<td nowrap>
				<a href="javascript: setDefaultOptions('Budget');  void(0);" id="BudgetProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetDefaults", "Budget Defaults")%></a>
			</td>
		</tr>
<%	}
	istep++;%>
		<tr height="25px">
			<td><img id="UserProcessImg" src="<%=contextPath%>/images/step<%=istep%>off.gif" class="processOffImg" border="0"></td>
			<td nowrap>
				<a href="javascript: setDefaultOptions('User');  void(0);" id="UserProcessLink" class=processOff><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "Userdefaults", "User Defaults")%></a>
			</td>
		</tr>
		</table>

		<hr size="0" width="170px">
	</td>
</tr>
</table>
<br>
<br>
<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('admin/admin_menu.jsp', 'PropertyUpdate'); void(0);">Save</a></div></td>
			<td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('admin/admin_menu.jsp', 'DoNothing'); void(0);">Return</a></div></td>
		</tr>

		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	setNavCookie("/admin/system_defaults.jsp", "DoNothing", "System Defaults");

	var selectedType = "";

	function setCurrentRow(section, field, browse, tableType) {
		var i = 0;
		if (navigator.appName == "Microsoft Internet Explorer")
			var propertyValue = frm.elements.item("Property_value");
		else
			var propertyValue = document.getElementsByName("Property_value");
		for (i=0; i < propertyValue.length; i++) {
			if (section == frm.Property_section[i].value && field == frm.Property_property[i].value) {
				currentRow = i;
				if (tableType != undefined) {
					browseStd("Property_value", tableType);
				} else {
					browseSchedule("Property_value", browse);
				}
				i = frm.elements.item("Property_value").length;
			}
		}
	}

	function thisLoad() {
		hideArea("SystemProcessing");
		setDefaultOptions("Supplier");

		f_StartIt();
	}

	function populateHTML() {
		var myFrame = document.getElementById("systemSetupFrame");
		var myHtml = myFrame.innerHTML + getHtml();

		myFrame.innerHTML = myHtml;
	}

	function setDefaultOptions(type) {
		if (type != selectedType) {
			var myText = document.getElementById(type + "ProcessLink");
			var myImg = document.getElementById(type + "ProcessImg");
			var mySrc = myImg.src;

			var i = mySrc.indexOf("off.gif");

			if (i > 0) {
				mySrc = mySrc.substring(0, i) + "on.gif";
			}

			myImg.src = mySrc;
			myImg.className = "processOnImg";
			myText.className = "processOn";

			if (selectedType.length > 0) {
				myText = document.getElementById(selectedType + "ProcessLink");
				myImg = document.getElementById(selectedType + "ProcessImg");
				mySrc = myImg.src;

				i = mySrc.indexOf("on.gif");

				if (i > 0) {
					mySrc = mySrc.substring(0, i) + "off.gif";
				}

				myImg.src = mySrc;
				myImg.className = "processOffImg" ;
				myText.className = "processOff";

				hideArea(selectedType);
			}

			displayArea(type);

			selectedType = type;

			var mainTable = document.getElementById("mainTable");
			mainTable.style.height = "300px";
		}
	}

	function setCkBoxValue(ckbox, s, p) {
		var value = "";

		if (ckbox.checked == true) {
			value = "Y";
		}
		else {
			value = "N";
		}

		setPropertyValue(s, p, value);
	}

	function setPropertyValue(s, p, v) {
		var count = document.all.Property_property.length;
		var section = "";
		var property = "";

		for (var i=0; i < count; i++) {
			section = frm.Property_section[i].value;
			property = frm.Property_property[i].value;

			if (section == s && property == p)
			{
				frm.Property_value[i].value = v;
			}
		}
	}

	function correctDateFormat(fld) {
		var dateFormat = fld.value;
		if (!isEmpty(dateFormat) && (dateFormat.length == 10 || dateFormat.length == 8)) {
			dateFormat = replaceChars(dateFormat, "Y", "y");
			dateFormat = replaceChars(dateFormat, "D", "d");
			dateFormat = replaceChars(dateFormat, "m", "M");
		}
		fld.value = dateFormat;
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>
