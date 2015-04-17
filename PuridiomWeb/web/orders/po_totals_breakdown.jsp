<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>

<iframe id="getInfoFrame" name="getInfoFrame" src="<%=contextPath%>/system/processing.jsp" frameborder="0" marginheight="0" marginwidth="0" style="position: absolute; border: 1px solid #C0C0C0; display: none; visibility:hidden;"></iframe>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/dynamicTables.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/po_totals.js"></SCRIPT>

<%
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	BigDecimal	bd_ic_po_header = poHeader.getIcPoHeader();
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	String	s_po_status = poHeader.getStatus();
	String	s_refresh = (String) request.getAttribute("refreshOpener");
	String	s_reason = (String) request.getAttribute("reason");
	String	s_title = "";

	if (Utility.isEmpty(s_po_status))	{	s_po_status = "3000";	}
	if (Utility.isEmpty(s_refresh))		{	s_refresh = "N";			}
	if (Utility.isEmpty(s_reason))		{	s_reason = "";				}

	boolean bAllowEdit = true;
	if ( role.getAccessRights("PO") < 2 || (!bAdminChanges && s_po_status.compareTo(DocumentStatus.PO_REJECTED) > 0)  || (s_po_status.compareTo(DocumentStatus.CANCELLED) >= 0) || s_po_status.compareTo(DocumentStatus.PO_AMENDED) == 0)
	{
		bAllowEdit = false;
	}
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer())
	{
		bAllowEdit = false;
	}

	if (s_reason.equalsIgnoreCase("discount"))
	{
		s_title = "Discount Amounts";
	}
	else if (s_reason.equalsIgnoreCase("tax"))
	{
		s_title = "Tax Amounts";
	}
	else if (s_reason.equalsIgnoreCase("shipping"))
	{
		s_title = "Shipping Charges";
	}
	else if (s_reason.equalsIgnoreCase("other"))
	{
		s_title = "Other Charges";
	}
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="refreshOpener" value="<%=s_refresh%>"/>
<tsa:hidden name="allowBrowse" value="true"/>

<table cellpadding=0 cellspacing=0 border=0>
<tr><td><br></td></tr>
<tr>
	<td width=100%>
		<table cellpadding=0 cellspacing=0 border=0 width=100%>
		<tr>
			<td valign=top width=50px height=30px>
				<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
				<tr>
					<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
				</tr>
				<tr>
					<td nowrap class=hdr12 valign=middle>
						<div style="margin-left:10px; margin-right:10px" class=hdr12><%=s_title%></div>
					</td>
				</tr>
				</table>
			</td>
			<td valign=bottom align=left><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign=bottom align=right height=30px width=100%>
				<table cellpadding="0" cellspacing="0" border=0 width=100%>
				<tr>
					<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
				</tr>
				<tr>
					<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<td>
		<TABLE border=0 cellpadding=1 cellspacing=2 width=400px>
		<TR>
			<TD colspan=2>&nbsp;</TD>
<% 	if (s_reason.equals("discount")) { %>
			<tsa:td field="po-discountPercent" cssClass="mnav" height="18px" noWrap="nowrap" align="center"><tsa:label labelName="po-discountPercent" defaultString="Discount %" /></tsa:td>
			<tsa:td field="po-discountAmount" cssClass="mnav" height="18px" noWrap="nowrap" align="center"><tsa:label labelName="po-discountAmount" defaultString="Discount" /></tsa:td>
<%	}
		if (s_reason.equals("tax")) { %>
			<TD <%=HtmlWriter.isVisible(oid, "po-taxCode")%> class=mnav height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-taxCode", "Tax Code")%></TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-taxPercent")%> class=mnav height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-taxPercent", "Tax %")%></TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-taxAmount")%> class=mnav height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-taxAmount", "Tax")%></TD>
<%	}
		if (s_reason.equals("shipping")) { %>
			<TD <%=HtmlWriter.isVisible(oid, "po-shippingCharges")%> class=mnav height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shippingCharges", "Shipping")%></TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-shippingTaxAmount")%> class=mnav height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shippingTaxAmount", "Shipping Tax")%></TD>
<%	}
		if (s_reason.equals("other")) { %>
			<TD <%=HtmlWriter.isVisible(oid, "po-otherCharges")%> class=mnav height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-otherCharges", "Other")%></TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-otherTaxAmount")%> class=mnav height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-otherTaxAmount", "Other Tax")%></TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-otherDescription")%> class=mnav height=18px nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-otherDescription", "Other Description")%></TD>
<%	} %>
		</TR>
		<TR>
			<TD COLSPAN="2">
				<TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0>
<%	if (s_reason.equals("shipping")) { %>
				<TR <%=HtmlWriter.isVisible(oid, "po-shippingTaxable")%>>
					<TD nowrap CLASS="otab">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-shippingTaxable", "Shipping Taxable")%>:
						<tsa:hidden name="PoHeader_shippingTaxable" value="<%=HiltonUtility.ckNull(poHeader.getShippingTaxable())%>"/>
						<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if (HiltonUtility.ckNull(poHeader.getShippingTaxable()).equals("Y")) { %>CHECKED value="Y"<% } %> ONCLICK="setCheckbox(PoHeader_shippingTaxable); calculateShipTax();">
					</TD>
				</TR>
<%	}
		else
		{ %>
				<tsa:hidden name="PoHeader_shippingTaxable" value="<%=HiltonUtility.ckNull(poHeader.getShippingTaxable())%>"/>
<%	}
		if (s_reason.equals("other")) {%>
				<TR <%=HtmlWriter.isVisible(oid, "po-otherTaxable")%>>
					<TD nowrap CLASS="otab">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-otherTaxable", "Other Taxable")%>:
						<tsa:hidden name="PoHeader_otherTaxable" value="<%=HiltonUtility.ckNull(poHeader.getOtherTaxable())%>"/>
						<INPUT TYPE="CHECKBOX" NAME="c_checkbox" <% if (HiltonUtility.ckNull(poHeader.getOtherTaxable()).equals("Y")) { %>CHECKED value="Y"<% } %> ONCLICK="setCheckbox(PoHeader_otherTaxable); calculateOtherTax();">
					</TD>
				</TR>
<%	}
		else
		{ %>
				<tsa:hidden name="PoHeader_otherTaxable" value="<%=HiltonUtility.ckNull(poHeader.getOtherTaxable())%>"/>
<%	} %>
				<TR <%=HtmlWriter.isVisible(oid, "po-subtotal")%> CLASS="otab" HEIGHT="18px">
					<TD CLASS="otab">
						<B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-subtotal", "Subtotal")%>: </B><%=HiltonUtility.getFormattedDollar(poHeader.getSubtotal(), oid)%>
						<tsa:hidden name="PoHeader_subtotal" value="<%=HiltonUtility.getFormattedDollar(poHeader.getSubtotal(), oid)%>"/>
					</TD>
				</TR>
				</TABLE>
			</TD>
<%	if (s_reason.equals("discount")) { %>
			<TD <%=HtmlWriter.isVisible(oid, "po-discountPercent")%> VALIGN="CENTER" ALIGN="CENTER">
				<INPUT TYPE="TEXT" NAME="PoHeader_discountPercent" value="<%=Utility.getBigDecimalFormatted(poHeader.getDiscountPercent(), 2)%>" STYLE="text-align:right" SIZE="6" ONCHANGE="changeHeaderAmount(PoHeader_discountPercent);">%
			</TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-discountAmount")%> VALIGN="CENTER" ALIGN="CENTER">
				<INPUT TYPE="TEXT" NAME="PoHeader_discountAmount" value="<%=HiltonUtility.getFormattedDollar(poHeader.getDiscountAmount(), oid)%>" STYLE="text-align:right" SIZE="10"  ONCHANGE="distributeDiscount();">
			</TD>
<%	} else {%>
			<tsa:hidden name="PoHeader_discountPercent" value="<%=Utility.getBigDecimalFormatted(poHeader.getDiscountPercent(), 2)%>"/>
			<tsa:hidden name="PoHeader_discountAmount" value="<%=HiltonUtility.getFormattedDollar(poHeader.getDiscountAmount(), oid)%>"/>
<% 	}
		if (s_reason.equals("tax")) { %>
			<TD <%=HtmlWriter.isVisible(oid, "po-taxCode")%> VALIGN="CENTER" ALIGN="CENTER" NOWRAP>
				<A HREF="javascript: browseLookup('PoHeader_taxCode', 'taxcode'); void(0);"><IMG SRC="<%=contextPath%>/images/browser.gif" BORDER="0" ALT="Tax Codes"></A>
				<INPUT TYPE="TEXT" NAME="PoHeader_taxCode" value="<%=HiltonUtility.ckNull(poHeader.getTaxCode())%>" SIZE="10" ONCHANGE="upperCase(this); browseMeTax(frm.PoHeader_taxCode,'TAX', '0');">
			</TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-taxPercent")%> VALIGN="CENTER" ALIGN="CENTER"><INPUT TYPE="TEXT" NAME="PoHeader_taxPercent" value="<%=Utility.getBigDecimalFormatted(poHeader.getTaxPercent(), 5)%>" STYLE="text-align:right" SIZE="9" ONCHANGE="changeHeaderAmount(PoHeader_taxPercent);">%</TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-taxAmount")%> VALIGN="CENTER" ALIGN="CENTER"><INPUT TYPE="TEXT" NAME="PoHeader_taxAmount" value="<%=HiltonUtility.getFormattedDollar(poHeader.getTaxAmount(), oid)%>" STYLE="text-align:right" SIZE="10" ONCHANGE="distributeTax();" ONFOCUS="setHeaderFocus(this);"></TD>
<%	} else {%>
			<tsa:hidden name="PoHeader_taxCode" value="<%=HiltonUtility.ckNull(poHeader.getTaxCode())%>"/>
			<tsa:hidden name="PoHeader_taxPercent" value="<%=Utility.getBigDecimalFormatted(poHeader.getTaxPercent(), 5)%>"/>
			<tsa:hidden name="PoHeader_taxAmount" value="<%=HiltonUtility.getFormattedDollar(poHeader.getTaxAmount(), oid)%>"/>
<%	}
		if (s_reason.equals("shipping")) { %>
			<TD <%=HtmlWriter.isVisible(oid, "po-shippingCharges")%> VALIGN="CENTER" ALIGN="CENTER"><INPUT TYPE="TEXT" NAME="PoHeader_shippingCharges" value="<%=HiltonUtility.getFormattedDollar(poHeader.getShippingCharges(), oid)%>" STYLE="text-align:right" SIZE="10" ONCHANGE="distributeShipping();"></TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-shippingTaxAmount")%> VALIGN="CENTER" ALIGN="CENTER"><INPUT TYPE="TEXT" NAME="PoHeader_shippingTax" value="<%=HiltonUtility.getFormattedDollar(poHeader.getShippingTax(), oid)%>" STYLE="text-align:right" SIZE="10" ONCHANGE="distributeShipTax();" ONFOCUS="setHeaderFocus(this);"></TD>
<%	} else {%>
			<tsa:hidden name="PoHeader_shippingCharges" value="<%=HiltonUtility.getFormattedDollar(poHeader.getShippingCharges(), oid)%>"/>
			<tsa:hidden name="PoHeader_shippingTax" value="<%=HiltonUtility.getFormattedDollar(poHeader.getShippingTax(), oid)%>"/>
<%	}
		if (s_reason.equals("other")) { %>
			<TD <%=HtmlWriter.isVisible(oid, "po-otherCharges")%> VALIGN="CENTER" ALIGN="CENTER"><INPUT TYPE="TEXT" NAME="PoHeader_otherCharges" value="<%=HiltonUtility.getFormattedDollar(poHeader.getOtherCharges(), oid)%>" STYLE="text-align:right" SIZE="10" ONCHANGE="distributeOther();"></TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-otherTaxAmount")%> VALIGN="CENTER" ALIGN="CENTER"><INPUT TYPE="TEXT" NAME="PoHeader_otherTax" value="<%=HiltonUtility.getFormattedDollar(poHeader.getOtherTax(), oid)%>" STYLE="text-align:right" SIZE="10" ONCHANGE="distributeOtherTax();" ONFOCUS="setHeaderFocus(this);"></TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-otherDescription")%> VALIGN="CENTER" ALIGN="CENTER"><INPUT TYPE="TEXT" NAME="PoHeader_otherDescription" value="<%=HiltonUtility.ckNull(poHeader.getOtherDescription())%>" SIZE="25" ONCHANGE="copyOtherDesc();" maxLength=30></TD>
<%	} else { %>
			<tsa:hidden name="PoHeader_otherCharges" value="<%=HiltonUtility.getFormattedDollar(poHeader.getOtherCharges(), oid)%>"/>
			<tsa:hidden name="PoHeader_otherTax" value="<%=HiltonUtility.getFormattedDollar(poHeader.getOtherTax(), oid)%>"/>
			<tsa:hidden name="PoHeader_otherDescription" value="<%=HiltonUtility.ckNull(poHeader.getOtherDescription())%>"/>
<%	} %>
			<tsa:hidden name="PoHeader_total" value="<%=HiltonUtility.getFormattedDollar(poHeader.getTotal(), oid)%>"/>
		</TR>
<%	if (s_reason.equals("tax") || s_reason.equals("other"))
		{ %>
		<TR CLASS="htable"><TD COLSPAN="5"><IMG SRC="<%=contextPath%>/images/none.GIF"  HEIGHT="5" ALIGN="TOP" BORDER="0"></TD></TR>
<%	}
		else
		{ %>
		<TR CLASS="htable"><TD COLSPAN="4"><IMG SRC="<%=contextPath%>/images/none.GIF" HEIGHT="5" ALIGN="TOP" BORDER="0"></TD></TR>

<%	}

	int i_rowcount = 0;

	BigDecimal	bd_line_quantity;
	BigDecimal	bd_line_unit_price;
	BigDecimal	bd_line_subtotal;
	BigDecimal	bd_line_total;

	BigDecimal	bd_line_um_factor;

	String	s_line_status = "";
	String	s_line_taxable = "";

	List lineList = (List) poHeader.getPoLineList();
	if (lineList != null)
	{
		for (int i = 0; i < lineList.size(); i++)
		{
			PoLine poLine = (PoLine) lineList.get(i);

			s_line_status = poLine.getStatus();
			s_line_taxable = poLine.getTaxable();

			bd_line_quantity = HiltonUtility.getFormattedDollar(poLine.getQuantity(), oid);
			bd_line_unit_price = HiltonUtility.getFormattedPrice(poLine.getUnitPrice(), oid);
			bd_line_subtotal = HiltonUtility.getFormattedDollar(bd_line_quantity.multiply(bd_line_unit_price), oid);

			if (s_line_status == null)	{	s_line_status = "";	}
			if (s_line_taxable == null)	{	s_line_taxable = "";	}
%>
		<TR CLASS="otab">
			<TD CLASS="otab" valign=top>
				<FONT FACE="Arial Black"><%=Utility.getBigDecimalFormatted(poLine.getLineNumber(), 0)%></FONT>
				<tsa:hidden name="PoLine_icPoLine" value="<%=Utility.getBigDecimalFormatted(poLine.getIcPoLine(), 0)%>"/>
			</TD>
			<TD>
				<TABLE CELLPADDING=0 CELLSPACING=0>
				<TR>
					<TD CLASS="otab"><b><%=HiltonUtility.ckNull(poLine.getItemNumber())%></b></TD>
				</TR>
				<TR>
					<TD width=150px CLASS="otab" nowrap><%=HiltonUtility.ckNull(poLine.getDescription())%></TD>
				</TR>
				<TR>
					<TD CLASS="otab">
<%		if (s_line_status.equals(DocumentStatus.CANCELLED))
			{ %>
						<FONT COLOR="red">Cancelled</FONT>
<%		}
			else
			{
				if (s_line_taxable.equals("Y"))
				{ %>
						Taxable
<%			}
				else
				{ %>
						Non-Taxable
<%			} %>
						&nbsp;&nbsp;&nbsp;&nbsp;<%=bd_line_subtotal%>
<%		} %>
						<tsa:hidden name="PoLine_taxable" value="<%=s_line_taxable%>"/>
						<tsa:hidden name="computed_subtotal" value="<%=bd_line_subtotal%>"/>
						<tsa:hidden name="PoLine_taxOvr" value="<%=HiltonUtility.ckNull(poLine.getTaxOvr())%>"/>
						<tsa:hidden name="PoLine_discOvr" value="<%=HiltonUtility.ckNull(poLine.getDiscOvr())%>"/>
						<tsa:hidden name="PoLine_shipOvr" value="<%=HiltonUtility.ckNull(poLine.getShipOvr())%>"/>
						<tsa:hidden name="PoLine_otherOvr" value="<%=HiltonUtility.ckNull(poLine.getOtherOvr())%>"/>
						<tsa:hidden name="PoLine_status" value="<%=HiltonUtility.ckNull(poLine.getStatus())%>"/>
					</TD>
				</TR>
				</TABLE>
			</TD>
<%	if (s_reason.equals("discount")) { %>
			<TD <%=HtmlWriter.isVisible(oid, "po-discountPercent")%> VALIGN="CENTER" ALIGN="CENTER"><INPUT TYPE="TEXT" NAME="PoLine_discountPercent" value="<%=HiltonUtility.getFormattedDollar(poLine.getDiscountPercent(), oid)%>" STYLE="text-align:right" SIZE="6" ONCHANGE="changeLineAmount(PoLine_discountPercent,<%=i_rowcount%>);" ONFOCUS="setLineFocus(PoLine_discountPercent,<%=i_rowcount%>);">%</TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-discountAmount")%> VALIGN="CENTER" ALIGN="CENTER"><INPUT TYPE="TEXT" NAME="PoLine_discountAmount" value="<%=HiltonUtility.getFormattedDollar(poLine.getDiscountAmount(), oid)%>" STYLE="text-align:right" SIZE="10" ONCHANGE="changeLineAmount(PoLine_discountAmount,<%=i_rowcount%>);" ONFOCUS="setLineFocus(PoLine_discountAmount,<%=i_rowcount%>);"></TD>
<%	} else { %>
			<tsa:hidden name="PoLine_discountPercent" value="<%=HiltonUtility.getFormattedDollar(poLine.getDiscountPercent(), oid)%>"/>
			<tsa:hidden name="PoLine_discountAmount" value="<%=HiltonUtility.getFormattedDollar(poLine.getDiscountAmount(), oid)%>"/>
<%	}
		if (s_reason.equals("tax"))
		{ %>
			<TD <%=HtmlWriter.isVisible(oid, "po-taxCode")%> VALIGN="CENTER" ALIGN="CENTER" NOWRAP>
<%
			int	i_ind = 0;

			if (s_line_taxable.equalsIgnoreCase("Y"))
			{ %>
					<A HREF="javascript: void(0);" ONCLICK="browseLookup('PoLine_taxCode[<%=i_rowcount%>]', 'taxcode'); setRow(<%=i_rowcount%>);"><IMG SRC="<%=contextPath%>/images/browser.gif" BORDER="0" ALT="Tax Codes"></A>
<%		} else { %>
				&nbsp;&nbsp;
<%		} %>
				<INPUT TYPE="TEXT" NAME="PoLine_taxCode" value="<%=HiltonUtility.ckNull(poLine.getTaxCode())%>" SIZE="10" ONCHANGE="upperCase(this); browseMeTax(frm.PoLine_taxCode[<%=i_rowcount%>],'TAX', '<%=i_rowcount%>');" <%if (!s_line_taxable.equals("Y")){%>ONFOCUS="this.blur();" disabled<%}%>>
			</TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-taxPercent")%> VALIGN="CENTER" ALIGN="CENTER" NOWRAP><INPUT TYPE="TEXT" NAME="PoLine_taxPercent" value="<%=Utility.getBigDecimalFormatted(poLine.getTaxPercent(), 5)%>" STYLE="text-align:right" SIZE="9" ONCHANGE="changeLineAmount(PoLine_taxPercent,<%=i_rowcount%>);" <%if (!s_line_taxable.equals("Y")) {%>ONFOCUS="this.blur();" disabled<%}else{%>ONFOCUS="setLineFocus(PoLine_taxPercent,<%=i_rowcount%>);"<%}%>>%</TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-taxAmount")%> VALIGN="CENTER" ALIGN="CENTER" NOWRAP><INPUT TYPE="TEXT" NAME="PoLine_taxAmount" value="<%=HiltonUtility.getFormattedDollar(poLine.getTaxAmount(), oid)%>" STYLE="text-align:right" SIZE="10" ONCHANGE="changeLineAmount(PoLine_taxAmount,<%=i_rowcount%>);" <%if (!s_line_taxable.equals("Y")) {%>ONFOCUS="this.blur();" disabled<%}else{%>ONFOCUS="setLineFocus(PoLine_taxAmount,<%=i_rowcount%>);"<%}%>></TD>
<%	} else {%>
			<tsa:hidden name="PoLine_taxCode" value="<%=HiltonUtility.ckNull(poLine.getTaxCode())%>"/>
			<tsa:hidden name="PoLine_taxPercent" value="<%=Utility.getBigDecimalFormatted(poLine.getTaxPercent(), 5)%>"/>
			<tsa:hidden name="PoLine_taxAmount" value="<%=HiltonUtility.getFormattedDollar(poLine.getTaxAmount(), oid)%>"/>
<%	}
		if (s_reason.equals("shipping")) { %>
			<TD <%=HtmlWriter.isVisible(oid, "po-shippingCharges")%> VALIGN="CENTER" ALIGN="CENTER"><INPUT TYPE="TEXT" NAME="PoLine_shippingCharges" value="<%=HiltonUtility.getFormattedDollar(poLine.getShippingCharges(), oid)%>" STYLE="text-align:right" SIZE="10" ONCHANGE="changeLineAmount(PoLine_shippingCharges,<%=i_rowcount%>);" ONFOCUS="setLineFocus(PoLine_shippingCharges,<%=i_rowcount%>);"></TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-shippingTaxAmount")%> VALIGN="CENTER" ALIGN="CENTER"><INPUT TYPE="TEXT" NAME="PoLine_shippingTax" value="<%=HiltonUtility.getFormattedDollar(poLine.getShippingTax(), oid)%>" STYLE="text-align:right" SIZE="10" ONCHANGE="changeLineAmount(PoLine_shippingTax,<%=i_rowcount%>);" ONFOCUS="setLineFocus(PoLine_shippingTaxt,<%=i_rowcount%>);" > </TD>
<%	} else { %>
			<tsa:hidden name="PoLine_shippingCharges" value="<%=HiltonUtility.getFormattedDollar(poLine.getShippingCharges(), oid)%>"/>
			<tsa:hidden name="PoLine_shippingTax" value="<%=HiltonUtility.getFormattedDollar(poLine.getShippingTax(), oid)%>"/>
<%	}
		if (s_reason.equals("other")) { %>
			<TD <%=HtmlWriter.isVisible(oid, "po-otherCharges")%> VALIGN="CENTER" ALIGN="CENTER"><INPUT TYPE="TEXT" NAME="PoLine_otherCharges" value="<%=HiltonUtility.getFormattedDollar(poLine.getOtherCharges(), oid)%>" STYLE="text-align:right" SIZE="10" ONCHANGE="changeLineAmount(PoLine_otherCharges,<%=i_rowcount%>);" ONFOCUS="setLineFocus(PoLine_otherCharges,<%=i_rowcount%>);"></TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-otherTaxAmount")%> VALIGN="CENTER" ALIGN="CENTER"><INPUT TYPE="TEXT" NAME="PoLine_otherTax" value="<%=HiltonUtility.getFormattedDollar(poLine.getOtherTax(), oid)%>" STYLE="text-align:right" SIZE="10" ONCHANGE="changeLineAmount(PoLine_otherTax,<%=i_rowcount%>);" ONFOCUS="setLineFocus(PoLine_otherTax,<%=i_rowcount%>);"></TD>
			<TD <%=HtmlWriter.isVisible(oid, "po-otherDescription")%> VALIGN="CENTER" ALIGN="CENTER"><INPUT TYPE="TEXT" NAME="PoLine_otherDescription" value="<%=HiltonUtility.ckNull(poLine.getOtherDescription())%>" SIZE="25" ONFOCUS="setLineFocus(PoLine_otherDescription,<%=i_rowcount%>);" maxLength=30></TD>
<%	} else { %>
			<tsa:hidden name="PoLine_otherCharges" value="<%=HiltonUtility.getFormattedDollar(poLine.getOtherCharges(), oid)%>"/>
			<tsa:hidden name="PoLine_otherTax" value="<%=HiltonUtility.getFormattedDollar(poLine.getOtherTax(), oid)%>"/>
			<tsa:hidden name="PoLine_otherDescription" value="<%=HiltonUtility.ckNull(poLine.getOtherDescription())%>"/>
<% }%>
			<tsa:hidden name="PoLine_lineTotal" value="<%=HiltonUtility.getFormattedDollar(poLine.getLineTotal(), oid)%>"/>
		</TR>
<%		i_rowcount++;
		}

	}
	if ( bAllowEdit )  { %>
		<TR >
			<TD COLSPAN="5" ALIGN="RIGHT">
				<TABLE BORDER="0" >
				<TR>
					<TD ALIGN="RIGHT" >
						<A HREF="javascript: if (verifyAction('Clear all fields on this form?')) { resetMe(); } void(0);"><FONT CLASS="reset"><B>clear</B></FONT></A>&nbsp;
					</TD>
				</TR>
				</TABLE>
			</TD>
		</TR>
		</table>
	</td>
</TR>
<%	} %>
<TR><TD><BR></TD></TR>
<TR>
	<TD COLSPAN="5">
		<TABLE BORDER="0" WIDTH="100%">
		<TR>
<%	if ( bAllowEdit )  { %>
			<TD WIDTH="50%" ALIGN="CENTER">
				<div id="pxbutton"><A HREF="javascript: submitThis(); void(0);" BORDER="0">Save</A></div>
			</TD>
			<TD WIDTH="50%" ALIGN="CENTER">
<%	} else { %>
			<TD WIDTH="100%" ALIGN="CENTER">
<%	} %>
				<div id="pxbutton"><A HREF="javascript: closePopup(); void(0);">Close</A></div>
			</TD>
		</TR>
		</TABLE>
	</TD>
</TR>
</TABLE>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	var rowcount = <%=i_rowcount%>;
	var opencount = 0;
	var disc_ovr = "N";
	var tax_ovr = "N";
	var ship_ovr = "N";
	var other_ovr = "N";
	var currentRow = 0;

	frm = document.purchaseform;

	dollarDecimals = <%=s_dollar_decimals%>;

	setOverrideFlags();

	if (frm.refreshOpener.value == "Y")
	{
		//set cursor to hourglass while the system is processing
		window.parent.document.body.style.cursor = "wait";
		window.parent.doSubmit('/orders/po_totals.jsp', 'PoHeaderRetrieveById');
		closePopup();
	}

	function submitThis()
	{
		frm.refreshOpener.value = "Y";
		doSubmit('/orders/po_totals_breakdown.jsp', 'PoUpdateAll');
	}

	function setAlerts()
	{
	<% if (s_reason.equalsIgnoreCase("discount")) {%>
		window.parent.document.purchaseform.discount.value="N";
	<% } else if (s_reason.equalsIgnoreCase("tax")) {%>
		window.parent.document.purchaseform.tax.value="N";
	<% } else if (s_reason.equalsIgnoreCase("shipping")) {%>
		window.parent.document.purchaseform.shipping.value="N";
	<% } else if (s_reason.equalsIgnoreCase("other")) {%>
		window.parent.document.purchaseform.other.value="N";
	<% }%>
	}

	function setRow(row)
	{
		currentRow = row;
	}

	function setHeaderFocus(formField)
	{
		/*blocks header shipping/other tax fields*/
		if (formField == frm.PoHeader_shippingTax)
		{
			if ((frm.PoHeader_shippingTaxable.value == "N") || (frm.PoHeader_shippingCharges.value == "0.00") || ((eval(frm.PoHeader_taxPercent.value) > 0) && (tax_ovr == "N")))
			{
				formField.blur();
			}
		}
		if (formField == frm.PoHeader_otherTax)
		{
			if ((frm.PoHeader_otherTaxable.value == "N") || (frm.PoHeader_otherCharges.value == "0.00") || ((eval(frm.PoHeader_taxPercent.value) > 0) && (tax_ovr == "N")))
			{
				formField.blur();
			}
		}
		if (formField == frm.PoHeader_taxAmount)
		{
			if (rowcount < 1)
			{
				return;
			}
			else if (rowcount > 1)
			{
				for (var i = 0; i < rowcount; i++)
				{
					if (frm.PoLine_taxable[i].value == "Y")
					{
						return;
					}
				}
			}
			else if (rowcount == 1)
			{
				if (frm.PoLine_taxable.value == "Y")
				{
					return;
				}
			}
		}
	}

	function setLineFocus(formField,x)
	{
		if (rowcount > 1)
		{
			/*blocks line shipping/other tax fields or all fields if the status is canceled*/
			if (frm.PoLine_status[x].value == "<%=DocumentStatus.CANCELLED%>")
			{
				formField[x].blur();
				return;
			}
			if (formField == frm.PoLine_shippingTax)
			{
				if ((frm.PoHeader_shippingTaxable.value == "N") || (frm.PoLine_shippingCharges[x].value == "0.00") || ((eval(frm.PoLine_taxPercent[x].value) > 0) && (frm.PoLine_taxOvr[x].value == "N")))
				{
					formField[x].blur();
				}
			}
			if (formField == frm.PoLine_otherTax)
			{
				if ((frm.PoHeader_otherTaxable.value == "N") || (frm.PoLine_otherCharges[x].value == "0.00") || ((eval(frm.PoLine_taxPercent[x].value) > 0) && (frm.PoLine_taxOvr[x].value == "N")))
				{
					formField[x].blur();
				}
			}
		}
		else
		{
			if (frm.PoLine_status.value == "<%=DocumentStatus.CANCELLED%>")
			{
				formField.blur();
				return;
			}
			if (formField == frm.PoLine_shippingTax)
			{
				if ((frm.PoHeader_shippingTaxable.value == "N") || (frm.PoLine_shippingCharges.value == "0.00") || ((eval(frm.PoLine_taxPercent.value) > 0) && (frm.PoLine_taxOvr.value == "N")))
				{
					formField.blur();
				}
			}
			if (formField == frm.PoLine_otherTax)
			{
				if ((frm.PoHeader_otherTaxable.value == "N") || (frm.PoLine_otherCharges.value == "0.00") || ((eval(frm.PoLine_taxPercent.value) > 0) && (frm.PoLine_taxOvr.value == "N")))
				{
					formField.blur();
				}
			}
		}
	}

	function setCheckbox(fld) {
		fld.value = 'N';
		if ( frm.c_checkbox.checked ) {
			fld.value = 'Y';
		}
	}

	function resetCheckbox(fld) {
		frm.c_checkbox.checked = false;
		if ( fld.value == 'Y')
		{
			frm.c_checkbox.checked = true;
		}
		return false;
	}

	function resetMe()
	{
<%	/* Po Header */
		BigDecimal bd_subtotal = HiltonUtility.getFormattedDollar(poHeader.getSubtotal(), oid);
		BigDecimal bd_total = HiltonUtility.getFormattedDollar(poHeader.getTotal(), oid);
%>
		frm.PoHeader_subtotal.value	= "<%=bd_subtotal%>";
		frm.PoHeader_total.value	= "<%=bd_total%>";

<%	if (s_reason.equals("shipping")) {%>
			frm.PoHeader_shippingTaxable.value = "N";
			frm.c_checkbox.checked = false;
			frm.PoHeader_shippingCharges.value = "0.00";
			frm.PoHeader_shippingTax.value = "0.00";
<%	}
		else if (s_reason.equals("other")) {%>
			frm.PoHeader_otherTaxable.value = "N";
			frm.c_checkbox.checked = false;
			frm.PoHeader_otherCharges.value = "0.00";
			frm.PoHeader_otherTax.value = "0.00";
			frm.PoHeader_otherDescription.value	= "";
<%	}
		else if (s_reason.equals("discount")) {%>
			frm.PoHeader_discountPercent.value = "0.00";
			frm.PoHeader_discountAmount.value = "0.00";
<%	}
		else if (s_reason.equals("tax")) {%>
			frm.PoHeader_taxCode.value = "";
			frm.PoHeader_taxPercent.value = "0.00";
			frm.PoHeader_taxAmount.value = "0.00";
<%	}
		/* Po Line */
		String	s_fldArray = "";
		if (lineList != null)
		{
			for (int i = 0; i < lineList.size(); i++)
			{
				PoLine poLine = (PoLine) lineList.get(i);

				bd_line_unit_price = poLine.getUnitPrice();
				bd_line_quantity = poLine.getQuantity();
				bd_line_um_factor = poLine.getUmFactor();

				if (bd_line_unit_price == null)	{	bd_line_unit_price = new BigDecimal(0);	}
				if (bd_line_quantity == null)		{	bd_line_quantity = new BigDecimal(0);		}
				if (bd_line_um_factor == null)	{	bd_line_um_factor = new BigDecimal(1);	}

				bd_line_subtotal = bd_line_unit_price.multiply(bd_line_quantity);
				bd_line_subtotal = bd_line_subtotal.multiply(bd_line_um_factor);
				bd_line_subtotal = bd_line_subtotal.setScale(2, BigDecimal.ROUND_HALF_UP);

				bd_line_total = poLine.getLineTotal();

				if (lineList.size() > 1) {
					s_fldArray = "[" + String.valueOf(i) + "]";
				}
%>
				frm.computed_subtotal<%=s_fldArray%>.value = "<%=bd_line_subtotal%>";
				frm.PoLine_lineTotal<%=s_fldArray%>.value = "<%=bd_line_total%>";

<%			if (s_reason.equals("tax")) {%>
					frm.PoLine_taxOvr<%=s_fldArray%>.value = "N";
					frm.PoLine_taxAmount<%=s_fldArray%>.value = "0.00";
					frm.PoLine_taxCode<%=s_fldArray%>.value = "";
					frm.PoLine_taxPercent<%=s_fldArray%>.value = "0.00";

<%				if (lineList.size() > 1)
					{
						s_fldArray = "[" + String.valueOf(i) + "]";
					}
					else
					{
						s_fldArray = "";
					}
				}
				else if (s_reason.equals("discount")) {%>
					frm.PoLine_discOvr<%=s_fldArray%>.value = "N";
					frm.PoLine_discountPercent<%=s_fldArray%>.value = "0.00";
					frm.PoLine_discountAmount<%=s_fldArray%>.value = "0.00";
<%			}
				else if (s_reason.equals("shipping")) {%>
					frm.PoLine_shipOvr<%=s_fldArray%>.value = "N";
					frm.PoLine_shippingCharges<%=s_fldArray%>.value = "0.00";
					frm.PoLine_shippingTax<%=s_fldArray%>.value = "0.00";
<%			}
				else if (s_reason.equals("other")) {%>
					frm.PoLine_otherOvr<%=s_fldArray%>.value = "N";
					frm.PoLine_otherCharges<%=s_fldArray%>.value = "0.00";
					frm.PoLine_otherTax<%=s_fldArray%>.value = "0.00";
					frm.PoLine_otherDescription<%=s_fldArray%>.value = "";
<%			}
				i_rowcount++;
			}
 		} %>

		if (rowcount > 0)
		{
			lineTotal();
		}
	}

	function thisLoadPopup()
	{
<%	if (!bAllowEdit) { %>
			checkInputSettings();
<%	} %>
	}


	function browseLookup(formField, xml)
	{
		popupParameters =  "fromPage=Totals;formField=" + formField +";browseName=" + xml + ";allowBrowse=" + frm.allowBrowse.value;

		doSubmitToPopup('/browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=300px');
	}

	function openWindow (url, w, h) {
		if (browser == "Netscape") {
			if (w == undefined) { w = 'WIDTH=500'; }
			if (h == undefined) { h = 'HEIGHT=300'; }
			if (theFocus == undefined) { theFocus = 'detail'; }
		}
		else {
			if (w == null) { w = 'WIDTH=500'; }
			if (h == null) { h = 'HEIGHT=300'; }
			if (theFocus == null) { theFocus = 'detail'; }
		}
		var winspecs = w +","+ h +",resizable=1,scrollbars=1,menubar=0,location=0,top=0,left=0";

		detail_window = window.open("<%=contextPath%>/system/popup_html.jsp", "detail_window", winspecs);

		if (theFocus == 'main') {
			self.focus();
		}
		else {
			detail_window.focus();
		}

		if (detail_window.opener == null) detail_window.opener = window;
		self.name = "main";
	}

	function browseMeTax (formField, tableType, row )
	{
		//set cursor to hourglass while the system is processing
		document.body.style.cursor = "wait";

		fldObject = formField;
		fldFromObject = null;

		var taxCode = formField.value;

		popupParameters = "TaxCode_taxCode=" + taxCode + ";formField=" + formField.name + ";row=" + row;

		setLookupParameters('/base/get_tax_info.jsp', 'TaxCodeRetrieveById');
		displayArea('getInfoFrame');
		document.getElementById('getInfoFrame').src = 'system/iframe_html.jsp';

	}

// End Hide script -->
</SCRIPT>