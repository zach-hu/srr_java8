<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	VendorRegister	vendorRegister = (VendorRegister) request.getAttribute("vendorRegister");
	String	message = HiltonUtility.ckNull((String) request.getAttribute("message"));

	if (vendorRegister == null) {
		vendorRegister = new VendorRegister();
		VendorRegisterPK vendorRegisterPk = new VendorRegisterPK();
		vendorRegister.setComp_id(vendorRegisterPk);
		vendorRegister.setVendorPrintFaxCode("M");
		vendorRegister.setDiversityProgram("N");
		vendorRegister.setSubcontract("N");
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
	int countVendorClass = 0;
	boolean changeVendor1099 = propertiesManager.getProperty("SUPPLIER PORTAL", "CHANGE VENDOR1099", "N").equalsIgnoreCase("Y");
	boolean uploadCertifiedDocuments = propertiesManager.getProperty("SUPPLIER PORTAL", "UPLOAD CERTIFIED DOCUMENTS", "N").equalsIgnoreCase("Y");
	String		poSendCodes = propertiesManager.getProperty("SUPPLIER PORTAL", "POSENDCODES", "PFEMX");
	String		vendorTermsOverride = propertiesManager.getProperty("SUPPLIER PORTAL", "VENDORTERMSOVERRIDE", "");
	String		vendorTerms = vendorRegister.getVendorVendTerms() ;
	if (! HiltonUtility.isEmpty(vendorTermsOverride)) {
		vendorTerms = vendorTermsOverride ;
	}
%>
<script language="JavaScript1.2" src="<%=contextPath%>/supplierportal/scripts/dynamicTables.js"></script>
<script language="JavaScript1.2" src="<%=contextPath%>/supplierportal/scripts/date_check.js"></script>
<script language="JavaScript1.2" src="<%=contextPath%>/supplierportal/scripts/calendar.js"></script>

<tsa:hidden name="VendorRegister_contactType" value="<%=vendorRegister.getContactType()%>"/>
<tsa:hidden name="VendorRegister_vendorId" value="<%=user.getVendorId()%>"/>
<tsa:hidden name="VendorRegCommRel_vendorId" value="<%=user.getVendorId()%>"/>
<tsa:hidden name="VendorDocument_icRfqHeader" value="0"/>
<tsa:hidden name="VendorDocument_vendorId" value="<%=user.getVendorId()%>"/>
<tsa:hidden name="temp_commodity_code" value="" onchange="setCommodityCode();"/>
<tsa:hidden name="temp_commodity_desc" value=""/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="vendorCommodityUpdate" value="Y"/>
<tsa:hidden name="fromSave" value=""/>
<tsa:hidden name="vendorQualification" value="N"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td vAlign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/supplierportal/images/none.gif" width=1px height=1px /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 vAlign=middle>
			    <% if (user.isQualified()) {  %>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Profile Information</div>
				<% } else { %>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Pre-Qualification Information</div>
				<% } %>
			</td>
			<td nowrap class=hdr12 vAlign=middle>
				<a href="javascript: uploadDocs(''); void(0);"><img name="img_attach" src="<%=contextPath%>/supplierportal/images/clip_lg.gif" border=0 valign=top alt="Click here to upload documents"></a>&nbsp;
			</td>
		</tr>
		</table>
	</td>
	<td width=30px vAlign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width=30px height=31px /></td>
	<td vAlign=bottom align=right height=30px>
		<table cellpadding=0 cellspacing=0 border=0>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width=1px height=1px /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width=1px height=2px /></td>
		</tr>
		</table>
	</td>
	<td valign=bottom align=middle width=300px>
		<table border=0 cellpadding=2 cellspacing=0 width=100%>
		<tr>
			 <td align=right width="50%"><a href="javascript: validatePreQualification(); void(0);"><img src="<%=contextPath%>/images/alert.gif" border=0></a></td>
			 <td align=left width="50%"><a href="javascript: validatePreQualification(); void(0);">Validate Information</a></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr><td align=center>&nbsp;<b><%=message%></b></td></tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=5px>&nbsp;</td>
	<td width=125px valign=top><b>General Information</b></td>
	<td width=5px>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" order=0 width=2px height=100% class=darkShadow></td>
	<td width=5px>&nbsp;</td>
	<td>
		<table border=0 cellspacing=0 cellpadding=2 <%=HtmlWriter.isVisible(oid, "bb-businessType")%>>
		<tr>
			<td nowrap class=label><%=DictionaryManager.getLabel(oid, "bb-businessType", "Business Type", true)%></td>
<%
	List vendorTypeList = (List) request.getAttribute("vendorTypeList");
	if (vendorTypeList != null && vendorTypeList.size() > 0) { %>
		</tr>
		</table>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-businessType")%>>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="VendorRegister_businessType" value="<%=vendorRegister.getBusinessType()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorTypeList.size(); il++) {
				StdTable stdTable = (StdTable) vendorTypeList.get(il);
				if (stdTable.getStatus().equalsIgnoreCase("02"))
				{
					StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_businessType" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendorRegister.getBusinessType().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%					fl = il;
					if (vendorTypeList.size()/(fl + 1) <= 2 && b_new_column) {
						b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
<%					}
				}
			}%>
					</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
<%	} else {%>
			<td><input type=text name="VendorRegister_businessType" value="<%=vendorRegister.getBusinessType()%>">
		</tr>
		</table>
<%	}%>
		<br>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr <%=HtmlWriter.isVisible(oid, "bb-supplierName")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-supplierName", "Company Name", true)%></td>
			<td colspan=2><input name="VendorRegister_vendorName" size=50 maxLength=40 value="<%=vendorRegister.getVendorName()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-addressLine2")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-addressLine2", "Address 2", true)%></td>
			<td colspan=2><input name="VendorRegister_addressLine2" size=35 maxLength=40 value="<%=vendorRegister.getAddressLine2()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-addressLine3")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-addressLine3", "Address 3", true)%></td>
			<td colspan=2><input name="VendorRegister_addressLine3" size=35 maxLength=40 value="<%=vendorRegister.getAddressLine3()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-addressLine4")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-addressLine4", "Address 4", true)%></td>
			<td colspan=2><input name="VendorRegister_addressLine4" size=35 maxLength=40 value="<%=vendorRegister.getAddressLine4()%>"></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-city")%> align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-city", "City", true)%></td>
			<td colspan=2>
				<table border=0 cellpadding=1 cellspacing=0 width=100%>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "bb-city")%>><input name="VendorRegister_addressCity" size=35 maxLength=30 value="<%=vendorRegister.getAddressCity()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-state")%> align=right nowrap><a href="javascript: browseState('VendorRegister_addressState'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-state", "State", true)%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-state")%>><input name="VendorRegister_addressState" size=5 maxLength=15 value="<%=vendorRegister.getAddressState()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-zip")%> align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-zip", "Postal Code", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-zip")%>><input name="VendorRegister_addressZipCode" size=12 maxLength=15 value="<%=vendorRegister.getAddressZipCode()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-country")%>>
			<td align=right nowrap><a href="javascript: browseLookup('VendorRegister_addressCountry', 'country'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-country", "Country", true)%></a></td>
			<td colspan=2><input name="VendorRegister_addressCountry" size=35 maxLength=25 value="<%=vendorRegister.getAddressCountry()%>" onchange="upperCase(this);"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-einNumber")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-einNumber", "EIN", true)%></td>
			<td colspan=2><input name="VendorRegister_vendorEin" maxLength=15 value="<%=vendorRegister.getVendorEin()%>" onchange="upperCase(this);setValidateEINNumber();"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-naicsCode")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-naicsCode", "NAICS Code(s)", true)%></td>
			<td colspan=2><input name="VendorRegister_vendorNaics" size=15 maxLength=11 value="<%=vendorRegister.getVendorNaics()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-sicCode")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-cnt-sicCode", "SIC Code", true)%></td>
			<td><input name="VendorRegister_vendorSic" size=15 maxLength=15 value="<%=vendorRegister.getVendorSic()%>"></td>
			<td><i>**<%=DictionaryManager.getLabel(oid, "bb-sicInstructions", "Standard Industrial Classification (SIC) codes are described at", false)%> <b><a  href="<%=DictionaryManager.getLabel(oid, "bb-sicLink", "http://www.osha.gov/cgi-bin/sic/sicser5", false)%>" id="A1" tabindex=-1 target="_blank"><%=DictionaryManager.getLabel(oid, "bb-sicLink", "http://www.osha.gov/cgi-bin/sic/sicser5", false)%></a></b>&nbsp;**</i></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-dunsNumber")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-cnt-dunsNumber", "DUNS Number", true)%></td>
			<td><input name="VendorRegister_vendorDuns" size=15 maxLength=11 value="<%=vendorRegister.getVendorDuns()%>"></td>
			<td><i>**  You can look this number up at <b><a  href="http://www.dnb.com/" id="A1" tabindex=-1 target="_blank">http://www.dnb.com/</a>&nbsp; **</i></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-fob")%>>
			<td align=right nowrap><a href="javascript: browseStd('VendorRegister_vendorFobId', 'FOB'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-fob", "FOB", true)%></a></td>
			<td colspan=2><input name="VendorRegister_vendorFobId" size=15 maxLength=15 value="<%=vendorRegister.getVendorFobId()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-shipVia")%>>
			<td align=right><a href="javascript: browseStd('VendorRegister_vendorShipVia', 'SHP'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-shipVia", "Ship Via", true)%>:</a></td>
			<td colspan=2><input type=text name="VendorRegister_vendorShipVia" tabindex=16 size=15 maxlength=15 value="<%=vendorRegister.getVendorShipVia()%>" onchange="upperCase(this);"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-orderLeadtime")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-orderLeadtime", "Order Leadtime", true)%></td>
			<td colspan=2><input type=text name="VendorRegister_vendorLeadDays" tabindex=17 size=15 maxlength=15 value="<%=vendorRegister.getVendorLeadDays()%>" onchange="nfilter(this);"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-poSendMethod")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-poSendMethod", "PO Send Method")%></td>
			<td colspan=2>
				<select name="VendorRegister_vendorPrintFaxCode" tabIndex=19>
				<% if (poSendCodes.indexOf("P") >= 0) {  %>
					<option value="P" <% if ((vendorRegister.getVendorPrintFaxCode()).indexOf("P")>= 0){ out.println("SELECTED"); }%>>Print PO</option>
				<% } %>
				<% if (poSendCodes.indexOf("F") >= 0) {  %>
					<option value="F" <% if ((vendorRegister.getVendorPrintFaxCode()).indexOf("F")>= 0){ out.println("SELECTED"); }%>>Fax PO</option>
				<% } %>
				<% if (poSendCodes.indexOf("E") >= 0 ) {  %>
					<option value="E" <% if ((vendorRegister.getVendorPrintFaxCode()).indexOf("E")>= 0){ out.println("SELECTED"); }%>>EDI PO</option>
				<% } %>
				<% if (poSendCodes.indexOf("M") >= 0) {  %>
					<option value="M" <% if ((vendorRegister.getVendorPrintFaxCode()).indexOf("M")>= 0){ out.println("SELECTED"); }%>>E-mail PO</option>
				<% } %>
				<% if (poSendCodes.indexOf("X") >= 0) {  %>
					<option value="X" <% if ((vendorRegister.getVendorPrintFaxCode()).indexOf("X")>= 0){ out.println("SELECTED"); }%>>XML Order</option>
				<% } %>
				</select>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-emailAddress")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-emailAddress", "Email Address", true)%></td>
			<td colspan=2><input type=text name="VendorRegister_vendorEmailAddr" tabindex=23 size=40 maxlength=40 value="<%=vendorRegister.getVendorEmailAddr()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-faxNumber")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-faxNumber", "Fax Number", true)%></td>
			<td colspan=2><input name="VendorRegister_vendorFaxNumber" size=16 maxLenth=25 width=85px value="<%=vendorRegister.getVendorFaxNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-paymentTerms")%>>
			<td align=right nowrap>
			<%	if (DictionaryManager.isLink(oid, "bb-paymentTerms")) { %>
				<a href="javascript: void(0);" onClick="browseLookup('VendorRegister_vendorVendTerms', 'payment-terms');"><%=DictionaryManager.getLabel(oid, "bb-paymentTerms", "Terms", true)%></a>
			<%	} else { %>
				<%=DictionaryManager.getLabel(oid, "bb-paymentTerms", "Terms", true)%>
			<%	} %>
			</td>
			<td colspan=2><input name="VendorRegister_vendorVendTerms" maxLength=10 value="<%=vendorTerms%>" <%=HtmlWriter.isReadOnly(oid, "bb-paymentTerms")%>></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-urlAddress")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-urlAddress", "Web Address", true)%></td>
			<td colspan=2><input name="VendorRegister_vendorWebAddress" maxLength=60 value="<%=vendorRegister.getVendorWebAddress()%>" size=50></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-commodity")%>>
			<td align=right nowrap valign=top><a href="javascript: void(0);" onClick="browseCommodities();"><%=DictionaryManager.getLabel(oid, "bb-commodity", "Commodity", true)%></a></td>
			<td colspan=2>
				<table border=0 cellpadding=2 cellspacing=0 id="commodityTable">
				</table>
			</td>
		</tr>
		</table>
		<br>
	</td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "bb-remitto")%>><td colspan=6 class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr <%=HtmlWriter.isVisible(oid, "bb-remitto")%>>
	<td width=5px>&nbsp;</td>
	<td width=125px valign=top><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-remitto", "Remit To Address", true)%></b></td>
	<td width=5px>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" order=0 width=2px height=100% class=darkShadow></td>
	<td width=5px>&nbsp;</td>
	<td>
		<br>
		<table border=0 cellspacing=0 cellpadding=2>
		<tr <%=HtmlWriter.isVisible(oid, "bb-rt-defaultGeneralInfo")%>>
			<td align=right nowrap width=175px><%=DictionaryManager.getLabel(oid, "bb-rt-defaultGeneralInfo", "Default General Information", true)%></td>
			<td colspan=2>
				<input type="checkbox" name="c_checkbox_defaultGeneralInfo" onclick="defaultGeneralInfo(this);">
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-rt-addressLine1")%>>
			<td align=right nowrap width=175px><%=DictionaryManager.getLabel(oid, "bb-rt-addressLine1", "Name / Address", true)%></td>
			<td colspan=2><input name="VendorRegister_rtAddressLine1" size=35 maxLength=40 value="<%=vendorRegister.getRtAddressLine1()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-rt-addressLine2")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-rt-addressLine2", "Address 2", true)%></td>
			<td colspan=2><input name="VendorRegister_rtAddressLine2" size=35 maxLength=40 value="<%=vendorRegister.getRtAddressLine2()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-rt-addressLine3")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-rt-addressLine3", "Address 3", true)%></td>
			<td colspan=2><input name="VendorRegister_rtAddressLine3" size=35 maxLength=40 value="<%=vendorRegister.getRtAddressLine3()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-rt-addressLine4")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-rt-addressLine4", "Address 4", true)%></td>
			<td colspan=2><input name="VendorRegister_rtAddressLine4" size=35 maxLength=40 value="<%=vendorRegister.getRtAddressLine4()%>"></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-rt-city")%> align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-rt-city", "City", true)%></td>
			<td colspan=2>
				<table border=0 cellpadding=1 cellspacing=0 width=100%>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "bb-rt-city")%>><input name="VendorRegister_rtAddressCity" size=35 maxLength=30 value="<%=vendorRegister.getRtAddressCity()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-rt-state")%> align=right nowrap><a href="javascript: browseState('VendorRegister_rtAddressState'); void(0);"><%=DictionaryManager.getLabel(oid, "bb-rt-state", "State", true)%></a></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-rt-state")%>><input name="VendorRegister_rtAddressState" size=5 maxLength=15 value="<%=vendorRegister.getRtAddressState()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-rt-zip")%> align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-rt-zip", "Postal Code", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-rt-zip")%>><input name="VendorRegister_rtAddressZip" size=12 maxLength=15 value="<%=vendorRegister.getRtAddressZip()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-rt-country")%>>
			<td align=right nowrap><a href="javascript: browseLookup('VendorRegister_rtAddressCountry', 'country'); void(0);"><%=DictionaryManager.getLabel(oid, "bb-rt-country", "Country", true)%></a></td>
			<td colspan=2><input name="VendorRegister_rtAddressCountry" size=35 maxLength=25 value="<%=vendorRegister.getRtAddressCountry()%>" onchange="upperCase(this);"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-rt-paymentType")%>>
			<td align=right nowrap><a href="javascript: browseStd('VendorRegister_vendPaymentType', 'PYTY'); void(0);"><%=DictionaryManager.getLabel(oid, "bb-rt-paymentType", "Payment Type", true)%></a></td>
			<td colspan=2><input name="VendorRegister_vendPaymentType" size=35 maxLength=25 value="<%=vendorRegister.getVendPaymentType()%>" onchange="upperCase(this);"></td>
		</tr>
		</table>
		<br>
	</td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "bb-eft")%>><td colspan=6 class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr <%=HtmlWriter.isVisible(oid, "bb-eft")%>>
	<td width=5px>&nbsp;</td>
	<td width=125px valign=top><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-eft", "EFT", true)%></b></td>
	<td width=5px>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" order=0 width=1px height=100% class=darkShadow></td>
	<td width=5px>&nbsp;</td>
	<td>
		<br>
		<table border=0 cellspacing=0 cellpadding=2>
		<tr <%=HtmlWriter.isVisible(oid, "bb-bankName")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-bankName", "Bank Name", true)%></td>
			<td colspan=2><input name="VendorRegister_eftBankName" size=30 maxLength=20 value="<%=vendorRegister.getEftBankName()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-routingnumber")%>>
			<td align=right nowrap width=175px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-routingnumber", "Routing Number", true)%></td>
			<td colspan=2><input name="VendorRegister_eftRoutingNumber" size=30 maxLength=20 value="<%=vendorRegister.getEftRoutingNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-accountNumber")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-accountNumber", "Account Number", true)%></td>
			<td colspan=2><input name="VendorRegister_eftAccountNumber" size=30 maxLength=20 value="<%=vendorRegister.getEftAccountNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-personName")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-personName", "Person Name", true)%></td>
			<td colspan=2><input name="VendorRegister_eftPersonName" size=30 maxLength=20 value="<%=vendorRegister.getEftPersonName()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-wireAccount")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-wireAccount", "Wire Account", true)%></td>
			<td colspan=2><input name="VendorRegister_eftWireAccount" size=30 maxLength=20 value="<%=vendorRegister.getEftWireAccount()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-accountType")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-accountType", "Account Type", true)%></td>
			<td colspan=2>
				<select name="VendorRegister_eftAccountType">
					<option value="C" <% if ((vendorRegister.getEftAccountType()).equals("C")){%> SELECTED <%}%>>Checking</option>
					<option value="S" <% if ((vendorRegister.getEftAccountType()).equals("S")){%> SELECTED <%}%>>Savings</option>
				</select>
			</td>
		</tr>
		</table>
		<br>
	</td>
</tr>
<tr><td colspan=6 class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr>
	<td>&nbsp;</td>
	<td valign=top><br><b>Contact Information</b></td>
	<td>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" order=0 width=1px height=100% class=darkShadow></td>
	<td>&nbsp;</td>
	<td>
		<br>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr <%=HtmlWriter.isVisible(oid, "bb-keycontact")%>>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-firstName", "Primary Contact Name", true)%></td>
			<td>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-salutation")%>>
					<%	if (propertiesManager.getProperty("SUPPLIER PORTAL", "SALUTATION TEXT", "N").equalsIgnoreCase("Y")) { %>
						<input name="VendorRegister_contactSalutation" size=4 maxLength=4 value="<%=vendorRegister.getContactSalutation()%>">
					<%	} else { %>
						<select name="VendorRegister_contactSalutation">
							<option value=""></option>
							<option value="Mr." <% if (vendorRegister.getContactSalutation().equals("Mr.")) {%>selected<%}%>>Mr.</option>
							<option value="Ms." <% if (vendorRegister.getContactSalutation().equals("Ms.")) {%>selected<%}%>>Ms.</option>
							<option value="Mrs." <% if (vendorRegister.getContactSalutation().equals("Mrs.")) {%>selected<%}%>>Mrs.</option>
							<option value="Miss" <% if (vendorRegister.getContactSalutation().equals("Miss")) {%>selected<%}%>>Miss</option>
						</select>
					<%	} %>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-firstName")%>><input name="VendorRegister_contactFirstName" size=27 maxLength=20 value="<%=vendorRegister.getContactFirstName()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-middleInitial")%>><input  name="VendorRegister_contactMidInit" size=3 maxLength=2 value="<%=vendorRegister.getContactMidInit()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-lastName")%>><input name="VendorRegister_contactLastName" size=27 maxLength=20 value="<%=vendorRegister.getContactLastName()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-title")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-cnt-title", "Title", true)%></td>
			<td><input name="VendorRegister_contactTitle" size=27 maxLength=30 value="<%=vendorRegister.getContactTitle()%>"></td>
		</tr>
		</table>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%> align=right nowrap width=150px><%=DictionaryManager.getLabel(oid, "bb-cnt-telephoneNumber", "Phone Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%>><input name="VendorRegister_contactPhoneNo" size=18 maxLength=30" value="<%=vendorRegister.getContactPhoneNo()%>"></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%> align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-cnt-faxNumber", "Fax Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%>><input name="vendorRegister.getContactFaxNumber()" size=18 maxLenth=25 width=85px value="<%=vendorRegister.getContactFaxNumber()%>"></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-cntemailAddress")%> align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-cnt-cntemailAddress", "Email Address", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-cntemailAddress")%>><input name="contactEmailAddr" size=45 maxLength=50 value="<%=vendorRegister.getComp_id().getContactEmailAddr()%>"> </td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-mobileNumber")%> align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-cnt-mobileNumber", "Mobile Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-mobileNumber")%>><input name="VendorRegister_contactMobileNumber" size=18 maxLenth=25 width=85px value="<%=vendorRegister.getContactMobileNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info1")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info1", "Info 1", true)%></td>
			<td><input name="VendorRegister_contactInfo1" size=45 maxLength=50 value="<%=vendorRegister.getContactInfo1()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info2")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info2", "Info 2", true)%></td>
			<td><input name="VendorRegister_contactInfo2" size=45 maxLength=50 value="<%=vendorRegister.getContactInfo2()%>"> </td>
		</tr>

		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info3")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info3", "Info 3", true)%></td>
			<td><input name="VendorRegister_contactInfo3" size=45 maxLength=50 value="<%=vendorRegister.getContactInfo3()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info4")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info4", "Info 4", true)%></td>
			<td><input name="VendorRegister_contactInfo4" size=45 maxLength=50 value="<%=vendorRegister.getContactInfo4()%>"> </td>
		</tr>
		</table>
		<br>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr <%=HtmlWriter.isVisible(oid, "bb-altcontact")%>>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-altcontact", "Alternate Contact Name", true)%></td>
			<td>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-salutation")%>>
					<%	if (propertiesManager.getProperty("SUPPLIER PORTAL", "SALUTATION TEXT", "N").equalsIgnoreCase("Y")) { %>
						<input name="VendorRegister_altSalutation" size=4 maxLength=4 value="<%=vendorRegister.getAltSalutation()%>">
					<%	} else { %>
						<select name="VendorRegister_altSalutation">
							<option value=""></option>
							<option value="Mr." <% if (vendorRegister.getAltSalutation().equals("Mr.")) {%>selected<%}%>>Mr.</option>
							<option value="Ms." <% if (vendorRegister.getAltSalutation().equals("Ms.")) {%>selected<%}%>>Ms.</option>
							<option value="Mrs." <% if (vendorRegister.getAltSalutation().equals("Mrs.")) {%>selected<%}%>>Mrs.</option>
							<option value="Miss" <% if (vendorRegister.getAltSalutation().equals("Miss")) {%>selected<%}%>>Miss</option>
						</select>
					<%	} %>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-firstName")%>><input name="VendorRegister_altFirstName" size=27 maxLength=20 value="<%=vendorRegister.getAltFirstName()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-middleInitial")%>><input  name="VendorRegister_altMidInit" size=3 maxLength=2 value="<%=vendorRegister.getAltMidInit()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-lastName")%>><input name="VendorRegister_altLastName" size=27 maxLength=20 value="<%=vendorRegister.getAltLastName()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-title")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-cnt-title", "Title", true)%></td>
			<td><input name="VendorRegister_altTitle" size=27 maxLength=30 value="<%=vendorRegister.getAltTitle()%>"></td>
		</tr>
		</table>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%> align=right nowrap width=150px><%=DictionaryManager.getLabel(oid, "bb-cnt-telephoneNumber", "Phone Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%>><input name="VendorRegister_altPhoneNo" size=18 maxLength=30" value="<%=vendorRegister.getAltPhoneNo()%>"></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%> align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-cnt-faxNumber", "Fax Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%>><input name="VendorRegister_altFaxNumber" size=18 maxLenth=25 width=85px value="<%=vendorRegister.getAltFaxNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-emailAddress")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-cnt-emailAddress", "Email Address", true)%></td>
			<td><input name="VendorRegister_altEmailAddr" size=45 maxLength=50 value="<%=vendorRegister.getAltEmailAddr()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info1")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info1", "Info 1", true)%></td>
			<td><input name="VendorRegister_altInfo1" size=45 maxLength=50 value="<%=vendorRegister.getAltInfo1()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info2")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info2", "Info 2", true)%></td>
			<td><input name="VendorRegister_altInfo2" size=45 maxLength=50 value="<%=vendorRegister.getAltInfo2()%>"> </td>
		</tr>

		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info3")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info3", "Info 3", true)%></td>
			<td><input name="VendorRegister_altInfo3" size=45 maxLength=50 value="<%=vendorRegister.getAltInfo3()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info4")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info4", "Info 4", true)%></td>
			<td><input name="VendorRegister_altInfo4" size=45 maxLength=50 value="<%=vendorRegister.getAltInfo4()%>"> </td>
		</tr>
		</table>
		<br>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr <%=HtmlWriter.isVisible(oid, "bb-altcontact2")%>>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-altcontact2", "Alternate Contact", true)%></td>
			<td>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-salutation")%>>
					<%	if (propertiesManager.getProperty("SUPPLIER PORTAL", "SALUTATION TEXT", "N").equalsIgnoreCase("Y")) { %>
						<input name="VendorRegister_arSalutation" size=4 maxLength=4 value="<%=vendorRegister.getArSalutation()%>">
					<%	} else { %>
						<select name="VendorRegister_arSalutation">
							<option value=""></option>
							<option value="Mr." <% if (vendorRegister.getArSalutation().equals("Mr.")) {%>selected<%}%>>Mr.</option>
							<option value="Ms." <% if (vendorRegister.getArSalutation().equals("Ms.")) {%>selected<%}%>>Ms.</option>
							<option value="Mrs." <% if (vendorRegister.getArSalutation().equals("Mrs.")) {%>selected<%}%>>Mrs.</option>
							<option value="Miss" <% if (vendorRegister.getArSalutation().equals("Miss")) {%>selected<%}%>>Miss</option>
						</select>
					<%	} %>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-firstName")%>><input name="VendorRegister_arFirstName" size=27 maxLength=20 value="<%=vendorRegister.getArFirstName()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-middleInitial")%>><input  name="VendorRegister_arMidInit" size=3 maxLength=2 value="<%=vendorRegister.getArMidInit()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-lastName")%>><input name="VendorRegister_arLastName" size=27 maxLength=20 value="<%=vendorRegister.getArLastName()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-title")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-cnt-title", "Title", true)%></td>
			<td><input name="VendorRegister_arTitle" size=27 maxLength=30 value="<%=vendorRegister.getArTitle()%>"></td>
		</tr>
		</table>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%> align=right nowrap width=150px><%=DictionaryManager.getLabel(oid, "bb-cnt-telephoneNumber", "Phone Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%>><input name="VendorRegister_arPhoneNo" size=18 maxLength=30" value="<%=vendorRegister.getArPhoneNo()%>"></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%> align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-cnt-faxNumber", "Fax Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%>><input name="VendorRegister_arFaxNumber" size=18 maxLenth=25 width=85px value="<%=vendorRegister.getArFaxNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-emailAddress")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-cnt-emailAddress", "Email Address", true)%></td>
			<td><input name="VendorRegister_arEmailAddr" size=45 maxLength=50 value="<%=vendorRegister.getArEmailAddr()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info1")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info1", "Info 1", true)%></td>
			<td><input name="VendorRegister_arInfo1" size=45 maxLength=50 value="<%=vendorRegister.getArInfo1()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info2")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info2", "Info 2", true)%></td>
			<td><input name="VendorRegister_arInfo2" size=45 maxLength=50 value="<%=vendorRegister.getArInfo2()%>"> </td>
		</tr>

		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info3")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info3", "Info 3", true)%></td>
			<td><input name="VendorRegister_arInfo3" size=45 maxLength=50 value="<%=vendorRegister.getArInfo3()%>"> </td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-info4")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-cnt-info4", "Info 4", true)%></td>
			<td><input name="VendorRegister_arInfo4" size=45 maxLength=50 value="<%=vendorRegister.getArInfo4()%>"> </td>
		</tr>
		</table>
	</td>
</tr>
<tr><td colspan=6 class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr>
	<td>&nbsp;</td>
	<td valign=top><br><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-additionalInformation", "Additional Information", true)%></b></td>
	<td>&nbsp;</td>
	<td width=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" order=0 width=1px height=100% class=darkShadow></td>
	<td>&nbsp;</td>
	<td>
<%
	List vendorUdf1List = (List) request.getAttribute("vendorUdf1List");
	if (vendorUdf1List != null && vendorUdf1List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-VN01")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabel(oid, "bb-VN01", "Supplier UDF 01", true)%> / Check all that apply</b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="VendorRegister_vendorUdf1" value="<%=vendorRegister.getVendorUdf1()%>">
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf1List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf1List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorUdf1" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendorRegister.getVendorUdf1().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (vendorUdf1List.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
<%			}
			}%>
					</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-VN01")%> width=100%>
		<tr>
			<td align=right nowrap width=150px><a href="javascript: browseStd('VendorRegister_vendorUdf1','VN01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN01", "Supplier UDF 01", true, true, "VendorRegister_vendorUdf1")%></a></td>
			<td><input type=text name="VendorRegister_vendorUdf1" value="<%=vendorRegister.getVendorUdf1()%>"></td>
		</tr>
		</table>
<%	}

	List vendorUdf2List = (List) request.getAttribute("vendorUdf2List");
	if (vendorUdf2List != null && vendorUdf2List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-VN02")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabel(oid, "bb-VN02", "Supplier UDF 02", true)%> / Check all that apply</b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="VendorRegister_vendorUdf2" value="<%=vendorRegister.getVendorUdf2()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf2List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf2List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorUdf2" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendorRegister.getVendorUdf2().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (vendorUdf2List.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
<%			}
			}%>
					</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-VN02")%>>
		<tr>
			<td align=right nowrap width=150px><a href="javascript: browseStd('VendorRegister_vendorUdf2','VN02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN02", "Supplier UDF 02", true, true, "VendorRegister_vendorUdf2")%></a></td>
			<td><input type=text name="VendorRegister_vendorUdf2" value="<%=vendorRegister.getVendorUdf2()%>"></td>
		</tr>
		</table>
<%	}

	if (!oid.equals("QRI06P")) {
		List vendorUdf3List = (List) request.getAttribute("vendorUdf3List");
		if (vendorUdf3List != null && vendorUdf3List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-VN03")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabel(oid, "bb-VN03", "Supplier UDF 03", true)%> / Check all that apply</b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="VendorRegister_vendorUdf3" value="<%=vendorRegister.getVendorUdf3()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf3List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf3List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorUdf3" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendorRegister.getVendorUdf3().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (vendorUdf3List.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
<%			}
			}%>
					</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
<%		} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-VN03")%>>
		<tr>
			<td align=right nowrap width=150px>
			<%	if (DictionaryManager.isLink(oid, "bb-VN03")) { %>
				<a href="javascript: browseStd('VendorRegister_vendorUdf3','VN03'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN03", "Supplier UDF 03", true, true, "VendorRegister_vendorUdf3")%></a>
			<%	} else { %>
				<%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN03", "Supplier UDF 03", true, true, "VendorRegister_vendorUdf3")%>
			<%	} %>
			</td>
			<td><input type=text name="VendorRegister_vendorUdf3" value="<%=vendorRegister.getVendorUdf3()%>"></td>
		</tr>
		</table>
<%		}
		}

	List vendorUdf4List = (List) request.getAttribute("vendorUdf4List");
	if (vendorUdf4List != null && vendorUdf4List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-VN04")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabel(oid, "bb-VN04", "Supplier UDF 04", true)%> / Check all that apply</b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="VendorRegister_vendorUdf4" value="<%=vendorRegister.getVendorUdf4()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf4List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf4List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorUdf4" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendorRegister.getVendorUdf4().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (vendorUdf4List.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
<%			}
			}%>
					</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-VN04")%>>
		<tr>
			<td align=right nowrap width=150px>
			<%	if (DictionaryManager.isLink(oid, "bb-VN04")) { %>
				<a href="javascript: browseStd('VendorRegister_vendorUdf4','VN04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN04", "Supplier UDF 04", true, true, "VendorRegister_vendorUdf4")%></a>
			<%	} else { %>
				<%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN04", "Supplier UDF 04", true, true, "VendorRegister_vendorUdf4")%>
			<%	} %>
			</td>
			<td><input type=text name="VendorRegister_vendorUdf4" value="<%=vendorRegister.getVendorUdf4()%>"></td>
		</tr>
		</table>
<%	}

	List vendorUdf5List = (List) request.getAttribute("vendorUdf5List");
	if (vendorUdf5List != null && vendorUdf5List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-VN05")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabel(oid, "bb-VN05", "Supplier UDF 05", true)%> / Check all that apply</b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="VendorRegister_vendorUdf5" value="<%=vendorRegister.getVendorUdf5()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf5List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf5List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorUdf5" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendorRegister.getVendorUdf5().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (vendorUdf5List.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
<%			}
			}%>
					</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-VN05")%>>
		<tr>
			<td align=right nowrap width=150px><a href="javascript: browseStd('VendorRegister_vendorUdf5','VN05'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN05", "Supplier UDF 05", true, true, "VendorRegister_vendorUdf5")%></a></td>
			<td><input type=text name="VendorRegister_vendorUdf5" value="<%=vendorRegister.getVendorUdf5()%>"></td>
		</tr>
		</table>
<%	}%>
		<br><br>
		<table border=0 cellspacing=1 cellpadding=1 width=100%>
		<tr <%=HtmlWriter.isVisible(oid, "bb-VN06subcategory")%>><td colspan=5 nowrap><%=DictionaryManager.getLabel(oid, "bb-VN06subcategory", "", true)%></td></tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN06")%> align=right nowrap width=150px><a href="javascript: browseStd('VendorRegister_vendorUdf6','VN06'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN06", "Supplier UDF 06", true, true, "VendorRegister_vendorUdf6")%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN06")%>><input name="VendorRegister_vendorUdf6" value="<%=vendorRegister.getVendorUdf6()%>" maxLength=15></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN07")%> align=right nowrap><a href="javascript: browseStd('VendorRegister_vendorUdf7','VN07'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN07", "Supplier UDF 07", true, true, "VendorRegister_vendorUdf7")%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN07")%> width=100px><input name="VendorRegister_vendorUdf7" value="<%=vendorRegister.getVendorUdf7()%>" maxLength=15></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN08")%> align=right nowrap width=150px><a href="javascript: browseStd('VendorRegister_vendorUdf8','VN08'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN08", "Supplier UDF 08", true, true, "VendorRegister_vendorUdf8")%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN08")%>><input name="VendorRegister_vendorUdf8" value="<%=vendorRegister.getVendorUdf8()%>" maxLength=15></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN09")%> align=right nowrap><a href="javascript: browseStd('VendorRegister_vendorUdf9','VN09'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN09", "Supplier UDF 09", true, true, "VendorRegister_vendorUdf9")%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN09")%> width=100px><input name="VendorRegister_vendorUdf9" value="<%=vendorRegister.getVendorUdf9()%>" maxLength=15></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN10")%> align=right nowrap width=150px><a href="javascript: browseStd('VendorRegister_vendorUdf10','VN10'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "bb-VN10", "Supplier UDF 10", true, true, "VendorRegister_vendorUdf10")%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN10")%>><input name="VendorRegister_vendorUdf10" value="<%=vendorRegister.getVendorUdf10()%>" maxLength=15></td>
		</tr>
		</table>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100%>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-yearsInBusiness")%> align=right nowrap width=150px><%=DictionaryManager.getLabel(oid, "bb-yearsInBusiness", "Years In Business", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-yearsInBusiness")%>><input name="VendorRegister_vendorYears" value="<%=vendorRegister.getVendorYears()%>" size = 10 maxLength=15 onchange="nfilter(this);" style="text-align: right"></td>
		</tr>
		</table>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-refInformation")%>>
		<tr <%=HtmlWriter.isVisible(oid, "bb-refInformation")%>><td colspan=2><b><%=DictionaryManager.getLabel(oid, "bb-refInformation", "Current / Previous Client Reference", false)%></b></td></tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-refCompanyName")%>>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabel(oid, "bb-refCompanyName", "Company Name", true)%></td>
			<td><input type=text name="VendorRegister_refCompanyName" size=75 maxLength=255 value="<%=vendorRegister.getRefCompanyName()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-refPhoneNumber")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-refPhoneNumber", "Phone Number", true)%></td>
			<td><input type=text name="VendorRegister_refPhoneNumber" size=75 maxLength=255 value="<%=vendorRegister.getRefPhoneNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-refContactName")%>>
			<td align=right nowrap><%=DictionaryManager.getLabel(oid, "bb-refContactName", "Contact Name", true)%></td>
			<td><input type=text name="VendorRegister_refContactName" size=75 maxLength=255 value="<%=vendorRegister.getRefContactName()%>"></td>
		</tr>
		</table>
		<br>
	</td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "bb-ownershipInfo")%>><td colspan=6 class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr <%=HtmlWriter.isVisible(oid, "bb-ownershipInfo")%>>
	<td>&nbsp;</td>
	<td valign=top><br><b>Business Ownership Information</b></td>
	<td>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" order=0 width=1px height=100% class=darkShadow></td>
	<td>&nbsp;</td>
	<td>
<%
	List ownershipTypesList = (List) request.getAttribute("ownershipTypeList");
	if (ownershipTypesList != null && ownershipTypesList.size() > 0){ %>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-ownershipType")%>>
		<tr><td><br></td></tr>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabel(oid, "bb-ownershipType", "Ownership Type", true)%> / Check all that apply</b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="VendorRegister_ownershipType" value="<%=vendorRegister.getOwnershipType()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%	boolean b_new_column = true;
		float fl = 0;
		for (int il = 0; il < ownershipTypesList.size(); il++) {
			StdTable stdTable = (StdTable) ownershipTypesList.get(il);
			if (stdTable.getStatus().equalsIgnoreCase("02"))
			{
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_ownershipType" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendorRegister.getOwnershipType().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%				fl = il;
				if (ownershipTypesList.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
					<table width=100% border=0 cellpadding=0 cellspacing=0>
<%				}
			}
		}%>
					</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-ownershipType")%>>
		<tr><td><br></td></tr>
		<tr>
			<td align=right><%=DictionaryManager.getLabel(oid, "bb-ownershipType", "Ownership Type", true)%></td>
			<td align=right><input type=text name="VendorRegister_ownershipType" value="<%=vendorRegister.getOwnershipType()%>"></td>
		</tr>
		</table>
<%	}

		List classificationTypeList = (List) request.getAttribute("classificationTypeList");
		if (classificationTypeList != null && classificationTypeList.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-vendorClass")%>>
		<tr>
			<td nowrap><b><%=DictionaryManager.getLabel(oid, "bb-vendorClass", "Vendor Class", true)%> / Check all that apply</b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="VendorRegister_vendorClass" value="<%=vendorRegister.getVendorClass()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < classificationTypeList.size(); il++) {
				StdTable stdTable = (StdTable) classificationTypeList.get(il);
				countVendorClass ++;
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorClass" type=checkbox value="<%=stdTablePK.getTableKey()%>" <%if (vendorRegister.getVendorClass().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%><%if(changeVendor1099){%> onclick="setCheckboxForVendor1099(this, <%=stdTablePK.getTableKey()%>);"<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (classificationTypeList.size()/(fl + 1) <= 2 && b_new_column){
					b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%			}
			}%>
						</table>
					</td>
				</tr>
				<%	if(changeVendor1099) { %>
				<tr>
					<td width="10%">&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "sup-form1099Required")%>>
						<input type="checkbox" name="c_checkbox_vendor1099" <%if ((vendorRegister.getVendor1099()).equals("Y")) { %>checked <% } %> value="Y" tabIndex=5 disabled>&nbsp;<%=DictionaryManager.getLabel(oid, "sup-form1099Required", "Form 1099 Required")%>
						<tsa:hidden name="VendorRegister_vendor1099" value="<%=vendorRegister.getVendor1099()%>"/>
					</td>
				</tr>
				<%	} %>
				</table>
			</td>
		</tr>
		</table>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-vendorClass")%>>
		<tr>
			<td align=right><%=DictionaryManager.getLabel(oid, "bb-vendorClass", "Vendor Class", true)%></td>
			<td align=right><input type=text name="VendorRegister_vendorClass" value="<%=vendorRegister.getVendorClass()%>"></td>
		</tr>
		</table>
<%	}

	List diverseCertifiedOrgList = (List) request.getAttribute("diverseCertifiedOrgList");
	if (diverseCertifiedOrgList != null && diverseCertifiedOrgList.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-diverseCertOrgs")%>>
		<tr><td><b><%=DictionaryManager.getLabel(oid, "bb-diverseCertOrgs", "Diverse Certified Organizations", true)%> Check all that apply.</b></td></tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="VendorRegister_diverseCertOrgs" value="<%=vendorRegister.getDiverseCertOrgs()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%	boolean b_new_column = true;
		float fl = 0;
		for (int il = 0; il < diverseCertifiedOrgList.size(); il++) {
			StdTable stdTable = (StdTable) diverseCertifiedOrgList.get(il);
			if (stdTable.getStatus().equalsIgnoreCase("02"))
			{
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_diverseCertOrg" type=checkbox value="<%=stdTablePK.getTableKey()%>" <%if(uploadCertifiedDocuments){%>onclick="loadAttachTypeC();"<%}%> <%if (vendorRegister.getDiverseCertOrgs().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%				fl = il;
				if (diverseCertifiedOrgList.size()/(fl + 1) <= 2 && b_new_column) {
					b_new_column = false;%>
						</table>
					</td>
					<td width=45% valign=top>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%				}
			}
		}%>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
<%	} else {%>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-diverseCertOrgs")%>>
		<tr>
			<td align=right><%=DictionaryManager.getLabel(oid, "bb-diverseCertOrgs", "Diverse Certified Organizations", true)%></td>
			<td align=right><input type=text name="VendorRegister_diverseCertOrgs" value="<%=vendorRegister.getDiverseCertOrgs()%>"></td>
		</tr>
		</table>
<%	}%>
		<br>
		<%	if (uploadCertifiedDocuments) { %>
		<table id="attachTypeC" border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-attachTypeC")%>>
		<tr>
			<td align=left width="50px">&nbsp;</td>
			<td align=left><%=DictionaryManager.getLabel(oid, "bb-attachTypeC", "attachTypeC", true)%>:</td>
			<td>
				<a href="javascript: uploadDocs('C'); void(0);" title="Upload Document"><img src="<%=contextPath%>/supplierportal/images/clip_lg.gif" border="0" alt="Upload Document"></a>
			</td>
		</tr>
		</table>
		<table id="attachTypeS" border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-attachTypeS")%>>
		<tr>
			<td align=left width="50px">&nbsp;</td>
			<td align=left><%=DictionaryManager.getLabel(oid, "bb-attachTypeS", "attachTypeS", true)%>:</td>
			<td>
				<a href="javascript: uploadDocs('S'); void(0);" title="Upload Document"><img src="<%=contextPath%>/supplierportal/images/clip_lg.gif" border="0" alt="Upload Document"></a>
			</td>
		</tr>
		</table>
		<table id="attachTypeW" border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-attachTypeW")%>>
		<tr>
			<td align=left width="50px">&nbsp;</td>
			<td align=left><%=DictionaryManager.getLabel(oid, "bb-attachTypeW", "attachTypeW", true)%>:</td>
			<td>
				<a href="javascript: uploadDocs('W'); void(0);" title="Upload Document"><img src="<%=contextPath%>/supplierportal/images/clip_lg.gif" border="0" alt="Upload Document"></a>
			</td>
		</tr>
		</table>
		<br>
		<%	} %>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-vendorDiversityProgram")%>>
		<tr <%=HtmlWriter.isVisible(oid, "bb-vendorDiversityProgram")%>>
			<td>Do you have a Supplier Diversity Program?</td>
			<td align=right width=25px><input type=radio name="VendorRegister_diversityProgram" value="Y" <% if (vendorRegister.getDiversityProgram().equalsIgnoreCase("Y")) {%>checked<%}%>></td>
			<td>Yes</td>
			<td align=right width=25px><input type=radio name="VendorRegister_diversityProgram" value="N" <% if (vendorRegister.getDiversityProgram().equalsIgnoreCase("N")) {%>checked<%}%>></td>
			<td>No</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-vendorSubcontract")%>>
			<td>Do you subcontract with diverse suppliers?</td>
			<td align=right width=25px><input type=radio name="VendorRegister_subcontract" value="Y" <% if (vendorRegister.getSubcontract().equalsIgnoreCase("Y")) {%>checked<%}%>></td>
			<td>Yes</td>
			<td align=right width=25px><input type=radio name="VendorRegister_subcontract" value="N" <% if (vendorRegister.getSubcontract().equalsIgnoreCase("N")) {%>checked<%}%>></td>
			<td>No</td>
		</tr>
		</table>
	</td>
</tr>
<tr  <%=HtmlWriter.isVisible(oid, "bb-certificationSection")%>><td colspan=6 class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr  <%=HtmlWriter.isVisible(oid, "bb-certificationSection")%>>
	<td>&nbsp;</td>
	<td valign=top><br><b>Certification Statement</b></td>
	<td>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" order=0 width=1px height=100% class=darkShadow></td>
	<td>&nbsp;</td>
	<td>
		<br>
		<table border=0 cellspacing=1 cellpadding=1  <%=HtmlWriter.isVisible(oid, "bb-certificationStatement")%>>
		<tr>
			<td><%=DictionaryManager.getLabel(oid, "bb-certificationStatement", "", true)%></td>
		</tr>
		</table>
<%	if (oid.equals("BSC04P")) {%>
		<br>
		<table border=0 cellspacing=0 cellpadding=2>
		<tr>
			<td valign=top align=right><input type=checkbox name="as_vendorUdf8" value="Y" <% if (vendorRegister.getVendorUdf8().equals("Y")) {%>checked<%}%>><tsa:hidden name="VendorRegister_vendorUdf8" value="<%=vendorRegister.getVendorUdf8()%>"/></td>
			<td>My company has not been excluded from federal procurement and non-procurement programs.</td>
		</tr>
		</table>
<%	}%>
		<br>
		<table border=0 cellspacing=0 cellpadding=2 class="requiredLabelHighlight"  <%=HtmlWriter.isVisible(oid, "bb-certificationCkboxStmt")%>>
		<tr>
			<td class="requiredLabelHighlight" valign=top align=right><input type=checkbox name="as_digitalSig" value="Y" <% if (vendorRegister.getDigitalSig().equals("Y")) {%>checked<%}%>><tsa:hidden name="VendorRegister_digitalSig" value="<%=vendorRegister.getDigitalSig()%>"/></td>
			<td class="requiredLabelHighlight"><%=DictionaryManager.getLabel(oid, "bb-certificationCkboxStmt", "I HEREBY CERTIFY ALL THE INFORMATION PROVIDED ON THIS FORM TO BE TRUE AND ACCURATE.", true)%></td>
		</tr>
		</tr>
		</table>
		<br>
	</td>
</tr>
<tr><td colspan=6 class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
</table>

<br>

<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<td align=center width=50%>
		<div id="forward_link"><a href="javascript: validateProfileInformation('VendorRegisterValidate'); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_submit.gif" tabindex=85 border=0></a></div>
	</td>
	<td align=center width=50%>
		<a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/supplierportal/images/button_cancel.gif" tabindex=86 border=0></a>
	</td>
</tr>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>

<script value=JavaScript>
<!--Hide Script
	frm = document.purchaseform;

	var currentRow = 0;
	var maxRows = 0;
	var certificationCheckRequired = <%=DictionaryManager.isVisible(oid, "bb-certificationCkboxStmt") && DictionaryManager.isVisible(oid, "bb-certificationSection")%>;

	setCommodities();

	<%	if (uploadCertifiedDocuments) { %>
	loadAttachTypeC();
	<%	} %>

	function setCommodities() {
<% for (int ic=0; ic < commodityList.size(); ic++) {
		String commodityCode = (String) commodityList.get(ic);
%>
		addCommodity("<%=commodityList.get(ic)%>","<%=CommodityManager.getInstance().getCommodityDescription(oid, commodityCode)%>");
<% }%>
	}

	function browseCommodities () {
		var selected = "";
		var selectCnt = 0;
		var args = "table=commodities";

		for (var i = 0; i < 10; i++) {
			if ( !isEmpty(frm.VendorRegCommRel_commodityCode[i].value) ) {
				if ( selected.length > 0 ) {
					selected = selected + "\u0008" + frm.VendorRegCommRel_commodityCode[i].value;
				}
				else {
					selected = frm.VendorRegCommRel_commodityCode[i].value;
				}
				selectCnt++;
			}
		}
		args = args + "&selected=" + selected + "&selectCnt=" + selectCnt;
<% if (propertiesManager.getProperty("MISC", "USE SUBCOMMODITY", "N").equalsIgnoreCase("Y")) {%>
		browseCommodity('temp_commodity_code','subcommodity','<%=propertiesManager.getProperty("MISC", "SubCommodityType", "")%>');
<% } else {%>
		browseCommodity('temp_commodity_code', 'commodity', '<%=propertiesManager.getProperty("MISC", "COMMODITYTYPE", "")%>');
<% }%>
	}

	function browseCommodity(formField, xml, commodityType) {
		if (xml == "subcommodity") {
			var currentCode = document.getElementById(formField).value;
			popupParameters =  popupParameters + "formField=" + formField +";browseName=" + xml + ";allowBrowse=" + frm.allowBrowse.value + ";selectedCode=" + currentCode + ";Commodity_commodity=" + currentCode;
			doSubmitToLookup('/supplierportal/browse/browse_subcommodity_tree.jsp', 'SubCommodityRetrieveTree', 'WIDTH=700px', 'HEIGHT=500px');
		} else if (!isEmpty(commodityType)) {
			var currentCode = document.getElementById(formField).value;
			popupParameters =  popupParameters + "formField=" + formField +";browseName=" + xml + ";allowBrowse=" + frm.allowBrowse.value + ";selectedCode=" + currentCode + ";Commodity_commodity=" + currentCode;
			doSubmitToLookup('/supplierportal/browse/browse_commodity_tree.jsp', 'CommodityRetrieveTree', 'WIDTH=700px', 'HEIGHT=500px');
		} else {
			browseLookup(formField, xml);
		}
	}

	function addCommodity(commodityCode, desc)
	{
		var myTable = document.getElementById("commodityTable");
		var myRow = createRow(myTable);

		currentRow = myTable.rows.length - 1;

		var myCell = createCell(myRow);
		myCell.innerHTML = "<input name=\"VendorRegCommRel_commodityCode\" size=15 value=\"" + commodityCode + "\" onfocus=\"this.blur();\" class=disabledTxtBox readOnly><input type=text name=\"as_commodityDesc\" value=\"" + desc + "\" size=50 onfocus=\"this.blur();\" disabled>";

		myCell = createCell(myRow);
		myCell.innerHTML = "<a href=\"javascript: deleteCommodity(" + currentRow + "); void(0);\"><img src=\"<%=contextPath%>/supplierportal/images/delete.gif\" border=0></a>";
	}

	function deleteCommodity(row) {
		var myTable = document.getElementById("commodityTable");
		var rows = myTable.rows.length;

		if (rows == 0) {
			return;
		}
		else if (rows > 1) {
			for (var i = row; i < rows; i++) {
				var commodityCode =  "";
				var commodityDesc =  "";

				if ((i+1) < rows) {
					commodityCode = frm.VendorRegCommRel_commodityCode[i + 1].value;
					commodityDesc = frm.as_commodityDesc[i + 1].value;
				}

				frm.VendorRegCommRel_commodityCode[i].value = commodityCode;
				frm.as_commodityDesc[i].value = commodityDesc;
			}
			frm.VendorRegCommRel_commodityCode[rows - 1].value = "";
			frm.as_commodityDesc[rows - 1].value = "";
		} else {
			frm.VendorRegCommRel_commodityCode.value = "";
			frm.as_commodityDesc.value = "";
		}
	}

	function setCommodityCode() {
		for (var i=0; i < 10; i++) {
			if (isEmpty(frm.VendorRegCommRel_commodityCode[i].value)) {
				frm.VendorRegCommRel_commodityCode[i].value = frm.temp_commodity_code.value;
				frm.as_commodityDesc[i].value = frm.temp_commodity_desc.value;
				frm.temp_commodity_code.value = "";
				frm.temp_commodity_desc.value = "";
				break;
			}
		}
		if (!isEmpty(frm.temp_commodity_code.value)) {
				frm.VendorRegCommRel_commodityCode[0].value = frm.temp_commodity_code.value;
				frm.as_commodityDesc[i].value = frm.temp_commodity_desc.value;
				frm.temp_commodity_code.value = "";
				frm.temp_commodity_desc.value = "";
		}
	}

	function setCommodityCodes() {
		var myTable = document.getElementById("commodityTable");
		var rows = myTable.rows.length;

		// make sure there are no duplicates
		if (rows > 1) {
			var codesEntered = "";
			for (var i=rows - 1; i >= 0; i--) {
				var tempCode = frm.VendorRegCommRel_commodityCode[i].value;

				if (isEmpty(tempCode) || codesEntered.indexOf("[" + tempCode + "]") >= 0) {
					deleteCommodity(i);
				} else {
					codesEntered = codesEntered + "[" + tempCode + "]";
				}
			}
		}
	}

	function setBusinessType() {
		if (frm.VendorRegister_businessType) {
			var businessTypes = frm.elements.item("as_businessType");
			if (businessTypes != undefined) {
				if (businessTypes.length > 1 ) {
					frm.VendorRegister_businessType.value = "";
					for (var i=0; i < frm.elements.item("as_businessType").length; i++){
						if (frm.as_businessType[i].checked) {
							frm.VendorRegister_businessType.value = frm.VendorRegister_businessType.value + frm.as_businessType[i].value;
						}
					}
				} else {
					if (frm.as_businessType.checked) {
						frm.VendorRegister_businessType.value = frm.as_businessType.value;
					}
				}
			}
		}
	}

	function setVendorClass() {
		if (frm.VendorRegister_vendorClass) {
			var vendorClasses = frm.elements.item("as_vendorClass");
			if (vendorClasses != undefined) {
				if (vendorClasses.length > 1 ) {
					frm.VendorRegister_vendorClass.value = "";
					for (var i=0; i < frm.elements.item("as_vendorClass").length; i++){
						if (frm.as_vendorClass[i].checked) {
							frm.VendorRegister_vendorClass.value = frm.VendorRegister_vendorClass.value + frm.as_vendorClass[i].value;
						}
					}
				} else {
					if (frm.as_vendorClass.checked) {
						frm.VendorRegister_vendorClass.value = frm.as_vendorClass.value;
					}
				}
			}
		}
	}

	function setOwnershipType() {
		if (frm.VendorRegister_ownershipType) {
			var ownershipTypes = frm.elements.item("as_ownershipType");
			if (ownershipTypes != undefined) {
				if (ownershipTypes.length > 1 ) {
					frm.VendorRegister_ownershipType.value = "";
					for (var i=0; i < frm.elements.item("as_ownershipType").length; i++){
						if (frm.as_ownershipType[i].checked) {
							frm.VendorRegister_ownershipType.value = frm.VendorRegister_ownershipType.value + frm.as_ownershipType[i].value;
						}
					}
				} else {
					if (frm.as_ownershipType.checked) {
						frm.VendorRegister_ownershipType.value = frm.as_ownershipType.value;
					}
				}
			}
		}
	}

	function setDiverseCertOrgs() {
		if (frm.VendorRegister_diverseCertOrgs) {
			var diverseCertOrgs = frm.elements.item("as_diverseCertOrg");
			if (diverseCertOrgs != undefined) {
				if (diverseCertOrgs.length > 1) {
					frm.VendorRegister_diverseCertOrgs.value = "";
					for (var i=0; i < frm.elements.item("as_diverseCertOrg").length; i++){
						if (frm.as_diverseCertOrg[i].checked) {
							frm.VendorRegister_diverseCertOrgs.value = frm.VendorRegister_diverseCertOrgs.value + frm.as_diverseCertOrg[i].value;
						}
					}
				} else {
					if (frm.as_diverseCertOrg.checked) {
						frm.VendorRegister_diverseCertOrgs.value = frm.as_diverseCertOrg.value;
					}
				}
			}
		}
	}

	function setVendorUdf1() {
		if (frm.VendorRegister_vendorUdf1) {
			var vendorUdfs = frm.elements.item("as_vendorUdf1");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.VendorRegister_vendorUdf1.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf1").length; i++){
						if (frm.as_vendorUdf1[i].checked) {
							frm.VendorRegister_vendorUdf1.value = frm.VendorRegister_vendorUdf1.value + frm.as_vendorUdf1[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf1.checked) {
						frm.VendorRegister_vendorUdf1.value = frm.as_vendorUdf1.value;
					}
				}
			}
		}
	}

	function setVendorUdf2() {
		if (frm.VendorRegister_vendorUdf2) {
			var vendorUdfs = frm.elements.item("as_vendorUdf2");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.VendorRegister_vendorUdf2.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf2").length; i++){
						if (frm.as_vendorUdf2[i].checked) {
							frm.VendorRegister_vendorUdf2.value = frm.VendorRegister_vendorUdf2.value + frm.as_vendorUdf2[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf2.checked) {
						frm.VendorRegister_vendorUdf2.value = frm.as_vendorUdf2.value;
					}
				}
			}
		}
	}

	function setVendorUdf3() {
		if (frm.VendorRegister_vendorUdf3) {
			var vendorUdfs = frm.elements.item("as_vendorUdf3");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.VendorRegister_vendorUdf3.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf3").length; i++){
						if (frm.as_vendorUdf3[i].checked) {
							frm.VendorRegister_vendorUdf3.value = frm.VendorRegister_vendorUdf3.value + frm.as_vendorUdf3[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf3.checked) {
						frm.VendorRegister_vendorUdf3.value = frm.as_vendorUdf3.value;
					}
				}
			}
		}
	}

	function setVendorUdf4() {
		if (frm.VendorRegister_vendorUdf4) {
			var vendorUdfs = frm.elements.item("as_vendorUdf4");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.VendorRegister_vendorUdf4.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf4").length; i++){
						if (frm.as_vendorUdf4[i].checked) {
							frm.VendorRegister_vendorUdf4.value = frm.VendorRegister_vendorUdf4.value + frm.as_vendorUdf4[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf4.checked) {
						frm.VendorRegister_vendorUdf4.value = frm.as_vendorUdf4.value;
					}
				}
			}
		}
	}

	function setVendorUdf5() {
		if (frm.VendorRegister_vendorUdf5) {
			var vendorUdfs = frm.elements.item("as_vendorUdf5");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.VendorRegister_vendorUdf5.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf5").length; i++){
						if (frm.as_vendorUdf5[i].checked) {
							frm.VendorRegister_vendorUdf5.value = frm.VendorRegister_vendorUdf5.value + frm.as_vendorUdf5[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf5.checked) {
						frm.VendorRegister_vendorUdf5.value = frm.as_vendorUdf5.value;
					}
				}
			}
		}
	}

	function setVendorUdf8() {
		if (frm.VendorRegister_vendorUdf8) {
			var vendorUdfs = frm.elements.item("as_vendorUdf8");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.VendorRegister_vendorUdf8.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf8").length; i++){
						if (frm.as_vendorUdf8[i].checked) {
							frm.VendorRegister_vendorUdf8.value = frm.VendorRegister_vendorUdf8.value + frm.as_vendorUdf8[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf8.checked) {
						frm.VendorRegister_vendorUdf8.value = frm.as_vendorUdf8.value;
					}
				}
			}
		}
	}

	function setDigitalSig() {
		if (frm.VendorRegister_digitalSig) {
			var digitalSigs = frm.elements.item("as_digitalSig");
			if (digitalSigs != undefined) {
				if (digitalSigs.length > 1 ) {
					frm.VendorRegister_digitalSig.value = "";
					for (var i=0; i < frm.elements.item("as_digitalSig").length; i++){
						if (frm.as_digitalSig[i].checked) {
							frm.VendorRegister_digitalSig.value = frm.VendorRegister_digitalSig.value + frm.as_digitalSig[i].value;
						}
					}
				} else {
					if (frm.as_digitalSig.checked) {
						frm.VendorRegister_digitalSig.value = frm.as_digitalSig.value;
					}
				}
			}
		}
	}

	function setValidateEINNumber()
	{
		var einNumber = frm.VendorRegister_vendorEin.value;
		var index_1 = 2;
		var index_2 = 3;
		var regExpSS = new RegExp('^\\d{3}-\\d{2}-\\d{4}$');
		var oid = '<%= oid %>';
		var errorMessage = '';
		var isValid = true;

		if(einNumber.indexOf("-") == index_1 || einNumber.indexOf("-") == index_2)
		{
			var leftNumber  = einNumber.substring(0,einNumber.indexOf("-"));
			var rightNumber = einNumber.substring(einNumber.indexOf("-") + 1, einNumber.length);

			for(var i=0; i<leftNumber.length; i++)
			{
				if(isNaN(parseInt(leftNumber.charAt(i))))
				{
					isValid = false;
					break;
				}
			}
			for(var i=0; i<rightNumber.length; i++)
			{
				if(isNaN(parseInt(rightNumber.charAt(i))))
				{
					isValid = false;
					break;
				}
			}

			if (!isValid) {
				errorMessage = "This is not a valid Tax ID Number. Must have numbers!";
			}
		}
		else
		{
			isValid = false;
			errorMessage = "This is not a valid Tax ID Number. Must have '-' in character " + (index_1 + 1) + " or " + (index_2 + 1) + " !";
		}

		if (!isValid) {
			if (!regExpSS.test(einNumber)) {
				errorMessage = "Please enter a valid Tax ID Number (format XX-XXXXXXX)\n";
				errorMessage += "or a valid SS# (format XXX-XX-XXXX).";
			} else {
				isValid = true;
			}
		}

		if (!isValid) {
			alert(errorMessage);
			frm.VendorRegister_vendorEin.value = "";
			frm.VendorRegister_vendorEin.focus();
		}

		return;
	}

	function uploadDocs(type) {
		popupParameters = "VendorDocument_icRfqHeader=0;VendorDocument_vendorId=<%=user.getVendorId()%>;VendorDocument_docType=" + type;
		doSubmitToLookup('/supplierportal/user/supplier_attachments.jsp', 'VendorDocumentRetrieveByVendor', 'width=700', 'height=350');
	}

	function submitProfileInformation() {
		doSubmit('/supplierportal/user/prequalification_complete.jsp', 'VendorRegisterUpdateByEmail');
	}

function validateProfileInformation(validationHandler) {
	var digitalSigs = frm.elements.item("as_digitalSig");
	var digitialSigSet = true;
	if (digitalSigs != undefined) {
		if (digitalSigs.length > 1 ) {
			for (var i=0; i < frm.elements.item("as_digitalSig").length; i++){
				if (!frm.as_digitalSig[i].checked) {
					digitialSigSet = false;
				}
			}
		} else {
			if (!frm.as_digitalSig.checked) {
				digitialSigSet = false;
			}
		}
	}

	if (!digitialSigSet && certificationCheckRequired) {
		alert("You must 'CERTIFY ALL THE INFORMATION PROVIDED ON THIS FORM TO BE TRUE AND ACCURATE' before submitting.");
		return false;
	}
	setBusinessType();
	setVendorClass();
	setOwnershipType();
	setDiverseCertOrgs();
	setVendorUdf1();
	setVendorUdf2();
	setVendorUdf3();
	setVendorUdf4();
	setVendorUdf5();
	setVendorUdf8();
	setDigitalSig();
//	setCommodityCodes();

	/*frm.failurePage.value = "/supplierportal/system/error_popup.jsp";
	if (doSubmit('user/prequalification_complete.jsp', 'VendorRegisterUpdateByEmail', 'lookup_window')) {
		hideAreaWithBlock('submit_link');
	}
	frm.failurePage.value = "/system/error.jsp";*/
	frm.fromSave.value = "Y";
    <% if (user.isQualified()) {  %>
    // Set flag to Y so password is not reset
    frm.vendorQualification.value="Y" ;
	doSubmit('user/prequalification_validation.jsp', 'VendorRegisterUpdateByEmail;VendorRegisterValidate;VendorRegisterUpdateVendorById');
	<% } else { %>
	doSubmit('user/prequalification_validation.jsp', 'VendorRegisterUpdateByEmail;VendorRegisterValidate');
	<% } %>
}

function doSubmitToNewTarget(page, handlerList, target) {
	setupHandlers(handlerList);

	frm.successPage.value = page;

	if (validateForm()) {
		var dummyFields = document.getElementById("dummyFields");
		var dummyFieldsHTML = "";
		if (dummyFields != null && dummyFields != undefined) {
			dummyFieldsHTML = dummyFields.innerHTML;
		}
//		resetDisabledFlds();
		resetDummyFields();

		frm.target = target;
		frm.submit();

		if (dummyFields != null && dummyFields != undefined) {
			dummyFields.innerHTML = dummyFieldsHTML;
		}
		return true;
	} else {
		return false;
	}
}

	function browseState(fld)
	{
		popupParameters = popupParameters + "colname=StdTable_id_tableType;operator==;filter_txt=STAT;logicalOperator=AND;originalFilter=Y;sort=N;"
		browseLookup(fld, "stdtable");
	}

	function validatePreQualification() {
		doSubmit('user/prequalification_validation.jsp', 'VendorRegisterUpdateByEmail;VendorRegisterValidate');
	}

	function setCheckboxForVendor1099(fld, x)
	{
	<%	if (countVendorClass > 1) { %>
		if (x == 0 || x == 2 || x == 3)
		{
			var fldCheck = frm.as_vendorClass[0].checked;

			<%	if (countVendorClass >= 3) { %>
			fldCheck = fldCheck || frm.as_vendorClass[2].checked;
			<%	} %>

			<%	if (countVendorClass >= 4) { %>
			fldCheck = fldCheck || frm.as_vendorClass[3].checked;
			<%	} %>

			if (fldCheck) {
				frm.c_checkbox_vendor1099.checked = true;
				frm.VendorRegister_vendor1099.value = "Y";
			} else {
				frm.c_checkbox_vendor1099.checked = false;
				frm.VendorRegister_vendor1099.value = "N";
			}
		}
	<%	} else { %>
		if (fld.checked) {
			frm.c_checkbox_vendor1099.checked = true;
			frm.VendorRegister_vendor1099.value = "Y";
		} else {
			frm.c_checkbox_vendor1099.checked = false;
			frm.VendorRegister_vendor1099.value = "N";
		}
	<%	} %>
	}

	function defaultGeneralInfo(fld)
	{
		if (fld.checked)
		{
			if (frm.VendorRegister_vendorName && frm.VendorRegister_rtAddressLine1)
				frm.VendorRegister_rtAddressLine1.value = frm.VendorRegister_vendorName.value;
			if (frm.VendorRegister_addressLine2 && frm.VendorRegister_rtAddressLine2)
				frm.VendorRegister_rtAddressLine2.value = frm.VendorRegister_addressLine2.value;
			if (frm.VendorRegister_addressLine3 && frm.VendorRegister_rtAddressLine3)
				frm.VendorRegister_rtAddressLine3.value = frm.VendorRegister_addressLine3.value;
			if (frm.VendorRegister_addressLine4 && frm.VendorRegister_rtAddressLine4)
				frm.VendorRegister_rtAddressLine4.value = frm.VendorRegister_addressLine4.value;
			if (frm.VendorRegister_addressCity && frm.VendorRegister_rtAddressCity)
				frm.VendorRegister_rtAddressCity.value = frm.VendorRegister_addressCity.value;
			if (frm.VendorRegister_addressState && frm.VendorRegister_rtAddressState)
				frm.VendorRegister_rtAddressState.value = frm.VendorRegister_addressState.value;
			if (frm.VendorRegister_addressZipCode && frm.VendorRegister_rtAddressZip)
				frm.VendorRegister_rtAddressZip.value = frm.VendorRegister_addressZipCode.value;
			if (frm.VendorRegister_addressCountry && frm.VendorRegister_rtAddressCountry)
				frm.VendorRegister_rtAddressCountry.value = frm.VendorRegister_addressCountry.value;
		}
		else
		{
			if (frm.VendorRegister_rtAddressLine1)
				frm.VendorRegister_rtAddressLine1.value = "";
			if (frm.VendorRegister_rtAddressLine2)
				frm.VendorRegister_rtAddressLine2.value = "";
			if (frm.VendorRegister_rtAddressLine3)
				frm.VendorRegister_rtAddressLine3.value = "";
			if (frm.VendorRegister_rtAddressLine4)
				frm.VendorRegister_rtAddressLine4.value = "";
			if (frm.VendorRegister_rtAddressCity)
				frm.VendorRegister_rtAddressCity.value = "";
			if (frm.VendorRegister_rtAddressState)
				frm.VendorRegister_rtAddressState.value = "";
			if (frm.VendorRegister_rtAddressZip)
				frm.VendorRegister_rtAddressZip.value = "";
			if (frm.VendorRegister_rtAddressCountry)
				frm.VendorRegister_rtAddressCountry.value = "";
		}
	}

	function loadAttachTypeC() {
		if (frm.as_diverseCertOrg) {
			var diverseCertOrgs = frm.elements.item("as_diverseCertOrg");
			if (diverseCertOrgs != undefined) {
				if (diverseCertOrgs.length > 1) {
					var checkVal = false;
					for (var i=0; i < frm.elements.item("as_diverseCertOrg").length; i++){
						if (frm.as_diverseCertOrg[i].checked) {
							checkVal = true;
							break;
						}
					}
					if (checkVal) {
						if (document.getElementById("attachTypeC")) {
							document.getElementById("attachTypeC").style.visibility = "visible";
							document.getElementById("attachTypeC").style.display = "block";
						}
					} else {
						if (document.getElementById("attachTypeC")) {
							document.getElementById("attachTypeC").style.visibility = "hidden";
							document.getElementById("attachTypeC").style.display = "none";
						}
					}
				} else {
					if (frm.as_diverseCertOrg.checked) {
						if (document.getElementById("attachTypeC")) {
							document.getElementById("attachTypeC").style.visibility = "visible";
							document.getElementById("attachTypeC").style.display = "block";
						}
					} else {
						if (document.getElementById("attachTypeC")) {
							document.getElementById("attachTypeC").style.visibility = "hidden";
							document.getElementById("attachTypeC").style.display = "none";
						}
					}
				}
			}
		}
	}

//-->
</script>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
