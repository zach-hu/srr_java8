<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.commodity.CommodityManager" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%
	VendorRegister	vendorRegister = (VendorRegister) request.getAttribute("vendorRegister");
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
%>
<script language="JavaScript1.2" src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<tsa:hidden name="VendorRegister_contactType" value="<%=vendorRegister.getContactType()%>"/>
<tsa:hidden name="VendorRegister_vendorId" value="<%=vendorRegister.getComp_id().getVendorId()%>"/>
<tsa:hidden name="VendorRegCommRel_vendorId" value="<%=vendorRegister.getComp_id().getVendorId()%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="vendorCommodityUpdate" value="Y"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td vAlign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width=1px height=1px /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 vAlign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "pre-qualificationInformation", "Pre-Qualification Information")%></div>
			</td>
			<td nowrap class=hdr12 vAlign=middle>
				<a href="javascript: viewDocs(); void(0);"><img name="img_attach" src="<%=contextPath%>/images/clip.gif" border=0 valign=top alt="Click here to upload documents"></a>&nbsp;
			</td>
		</tr>
		</table>
	</td>
	<td vAlign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height=31px /></td>
	<td vAlign=bottom align=right height=30px>
		<table cellpadding=0 cellspacing=0 border=0>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width=1px height=1px /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width=1px height=2px /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br><br>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=5px>&nbsp;</td>
	<td width=125px valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "general_information", "General Information")%></b></td>
	<td width=5px>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" order=0 width=2px height=100% class=darkShadow></td>
	<td width=5px>&nbsp;</td>
	<td>
		<table border=0 cellspacing=0 cellpadding=2 <%=HtmlWriter.isVisible(oid, "bb-businessType")%>>
		<tr>
			<td nowrap class=label><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-businessType", "Business Type", true)%></td>
<%
	List vendorTypeList = (List) request.getAttribute("vendorTypeList");
	if (vendorTypeList != null && vendorTypeList.size() > 0) { %>
		</tr>
		</table>
		<table border=0 cellspacing=1 cellpadding=1 width=100%>
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
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_businessType" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendorRegister.getBusinessType().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
				if (vendorTypeList.size()/(fl + 1) <= 2 && b_new_column) {
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
			<td><input type=text name="VendorRegister_businessType" value="<%=vendorRegister.getBusinessType()%>">
		</tr>
		</table>
<%	}%>
		<br>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr <%=HtmlWriter.isVisible(oid, "bb-supplierName")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-supplierName", "Company Name", true)%></td>
			<td colspan=2><input name="VendorRegister_vendorName" size=50 maxLength=40 value="<%=vendorRegister.getVendorName()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-sicCode")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sicCode", "SIC Code", true)%></td>
			<td><input name="VendorRegister_vendorSic" size=15 maxLength=15 value="<%=vendorRegister.getVendorSic()%>"></td>
			<td><i>**<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sicInstructions", "Standard Industrial Classification (SIC) codes are described at", false)%> <b><a  href="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sicLink", "http://www.osha.gov/cgi-bin/sic/sicser5", false)%>" id="A1" tabindex=-1 target="_blank"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sicLink", "http://www.osha.gov/cgi-bin/sic/sicser5", false)%></a></b>&nbsp;**</i></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-naicsCode")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-naicsCode", "NAICS Code(s)", true)%></td>
			<td colspan=2><input name="VendorRegister_vendorNaics" size=15 maxLength=11 value="<%=vendorRegister.getVendorNaics()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-dunsNumber")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-dunsNumber", "DUNS Number", true)%></td>
			<td><input name="VendorRegister_vendorDuns" size=15 maxLength=11 value="<%=vendorRegister.getVendorDuns()%>"></td>
			<td><i>**  <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "canLookNumberAt", "You can look this number up at ")%><b><a  href="http://www.dnb.com/" id="A1" tabindex=-1 target="_blank">http://www.dnb.com/</a>&nbsp; **</i></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-sup-addressLine2")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-addressLine2", "Address 2", true)%></td>
			<td colspan=2><input name="VendorRegister_addressLine2" size=35 maxLength=40 value="<%=vendorRegister.getAddressLine2()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-sup-addressLine3")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-addressLine3", "Address 3", true)%></td>
			<td colspan=2><input name="VendorRegister_addressLine3" size=35 maxLength=40 value="<%=vendorRegister.getAddressLine3()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-sup-addressLine4")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-addressLine4", "Address 4", true)%></td>
			<td colspan=2><input name="VendorRegister_addressLine4" size=35 maxLength=40 value="<%=vendorRegister.getAddressLine4()%>"></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-sup-city")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-city", "City", true)%></td>
			<td colspan=2>
				<table border=0 cellpadding=1 cellspacing=0 width=100%>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "bb-sup-city")%>><input name="VendorRegister_addressCity" size=35 maxLength=30 value="<%=vendorRegister.getAddressCity()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-sup-state")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-state", "State", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-sup-state")%>><input name="VendorRegister_addressState" size=5 maxLength=15 value="<%=vendorRegister.getAddressState()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-sup-zip")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-zip", "Postal Code", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-sup-zip")%>><input name="VendorRegister_addressZipCode" size=12 maxLength=15 value="<%=vendorRegister.getAddressZipCode()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-sup-country")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-country", "Country", true)%></td>
			<td colspan=2><input name="VendorRegister_addressCountry" size=35 maxLength=25 value="<%=vendorRegister.getAddressCountry()%>" onchange="upperCase(this);"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-sup-faxNumber")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-faxNumber", "Fax Number", true)%></td>
			<td colspan=2><input name="VendorRegister_vendorFaxNumber" size=16 maxLenth=25 width=85px value="<%=vendorRegister.getVendorFaxNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-einNumber")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-einNumber", "EIN", true)%></td>
			<td colspan=2><input name="VendorRegister_vendorEin" maxLength=10 value="<%=vendorRegister.getVendorEin()%>"></td>
		</tr>
<%	if (oid.equals("QRI06P")) {%>
		<tr <%=HtmlWriter.isVisible(oid, "bb-VN03")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN03", "UDF 03", true)%></td>
			<td colspan=2><input name="VendorRegister_vendorUdf3" maxLength=10 value="<%=vendorRegister.getVendorUdf3()%>"></td>
		</tr>
<%	}%>
		<tr <%=HtmlWriter.isVisible(oid, "bb-sup-paymentTerms")%>>
			<td align=right nowrap><a href="javascript: void(0);" onClick="browseLookup('VendorRegister_vendorVendTerms', 'payment-terms');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-paymentTerms", "Terms", true)%></a></td>
			<td colspan=2><input name="VendorRegister_vendorVendTerms" maxLength=10 value="<%=vendorRegister.getVendorVendTerms()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-sup-webAddress")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-webAddress", "Web Address", true)%></td>
			<td colspan=2><input name="VendorRegister_vendorWebAddress" maxLength=60 value="<%=vendorRegister.getVendorWebAddress()%>" size=50></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-sup-commodity")%>>
			<td align=right nowrap valign=top><a href="javascript: void(0);" onClick="browseCommodities('VendorRegCommRel_commodityCode', 'commodity');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-commodity", "Commodity", true)%></a></td>
			<td colspan=2>
				<table border=0 cellpadding=2 cellspacing=0 id="commodityTable">
				</table>
			</td>
		</tr>
		</table>
		<br>
	</td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "bb-sup-remitto")%>><td colspan=6 class=darkShadow><img src="<%=contextPath%>/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr <%=HtmlWriter.isVisible(oid, "bb-sup-remitto")%>>
	<td width=5px>&nbsp;</td>
	<td width=125px valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "remitToAddress", "Remit To Address")%></b></td>
	<td width=5px>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" order=0 width=2px height=100% class=darkShadow></td>
	<td width=5px>&nbsp;</td>
	<td>
		<br>
		<table border=0 cellspacing=0 cellpadding=2>
		<tr <%=HtmlWriter.isVisible(oid, "bb-sup-rt-addressLine1")%>>
			<td align=right nowrap width=175px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-rt-nameAddress", "Name / Address", true)%></td>
			<td colspan=2><input name="VendorRegister_rtAddressLine1" size=35 maxLength=40 value="<%=vendorRegister.getRtAddressLine1()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-sup-rt-addressLine2")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-rt-addressLine2", "Address 2", true)%></td>
			<td colspan=2><input name="VendorRegister_rtAddressLine2" size=35 maxLength=40 value="<%=vendorRegister.getRtAddressLine2()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-sup-rt-addressLine3")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-rt-addressLine3", "Address 3", true)%></td>
			<td colspan=2><input name="VendorRegister_rtAddressLine3" size=35 maxLength=40 value="<%=vendorRegister.getRtAddressLine3()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-sup-rt-addressLine4")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-rt-addressLine4", "Address 4", true)%></td>
			<td colspan=2><input name="VendorRegister_rtAddressLine4" size=35 maxLength=40 value="<%=vendorRegister.getRtAddressLine4()%>"></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-sup-rt-city")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-rt-city", "City", true)%></td>
			<td colspan=2>
				<table border=0 cellpadding=1 cellspacing=0 width=100%>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "bb-sup-rt-city")%>><input name="VendorRegister_rtAddressCity" size=35 maxLength=30 value="<%=vendorRegister.getRtAddressCity()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-sup-rt-state")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-rt-state", "State", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-sup-rt-state")%>><input name="VendorRegister_rtAddressState" size=5 maxLength=15 value="<%=vendorRegister.getRtAddressState()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-sup-rt-zip")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-rt-zip", "Postal Code", true)%></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-sup-rt-zip")%>><input name="VendorRegister_rtAddressZip" size=12 maxLength=15 value="<%=vendorRegister.getRtAddressZip()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-sup-rt-country")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-sup-rt-country", "Country", true)%></td>
			<td colspan=2><input name="VendorRegister_rtAddressCountry" size=35 maxLength=25 value="<%=vendorRegister.getRtAddressCountry()%>" onchange="upperCase(this);"></td>
		</tr>
		</table>
		<br>
	</td>
</tr>
<tr><td colspan=6 class=darkShadow><img src="<%=contextPath%>/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr>
	<td>&nbsp;</td>
	<td valign=top><br><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "contactInformation", "Contact Information")%></b></td>
	<td>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" order=0 width=1px height=100% class=darkShadow></td>
	<td>&nbsp;</td>
	<td>
		<br>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-name")%>>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-cnt-key", "Key", true)%> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-cnt-name", "Contact Name", true)%></td>
			<td>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-salutation")%>>
						<select name="VendorRegister_contactSalutation">
							<option value=""></option>
							<option value="Mr." <% if (vendorRegister.getContactSalutation().equals("Mr.")) {%>selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "mr", "Mr")%>.</option>
							<option value="Ms." <% if (vendorRegister.getContactSalutation().equals("Ms.")) {%>selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ms", "Ms")%>.</option>
							<option value="Mrs." <% if (vendorRegister.getContactSalutation().equals("Mrs.")) {%>selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "mrs", "Mrs")%>.</option>
							<option value="Miss" <% if (vendorRegister.getContactSalutation().equals("Miss")) {%>selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "miss", "Miss")%></option>
						</select>
					</td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-firstName")%>><input name="VendorRegister_contactFirstName" size=27 maxLength=20 value="<%=vendorRegister.getContactFirstName()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-middleInitial")%>><input  name="VendorRegister_contactMidInit" size=3 maxLength=2 value="<%=vendorRegister.getContactMidInit()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-lastName")%>><input name="VendorRegister_contactLastName" size=27 maxLength=20 value="<%=vendorRegister.getContactLastName()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-title")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-cnt-title", "Title", true)%></td>
			<td><input name="VendorRegister_contactTitle" size=27 maxLength=30 value="<%=vendorRegister.getContactTitle()%>"></td>
		</tr>
		</table>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%> align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-cnt-telephoneNumber", "Phone Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%>><input name="VendorRegister_contactPhoneNo" size=18 maxLength=30" value="<%=vendorRegister.getContactPhoneNo()%>"></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-cnt-faxNumber", "Fax Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%>><input name="vendorRegister.getContactFaxNumber()" size=18 maxLenth=25 width=85px value="<%=vendorRegister.getContactFaxNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-emailAddress")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-cnt-emailAddress", "Email Address", true)%></td>
			<td><input name="contactEmailAddr" size=45 maxLength=50 value="<%=vendorRegister.getComp_id().getContactEmailAddr()%>"> </td>
		</tr>
		</table>
		<br>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-name")%>>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-cnt-alt", "Alternate", true)%> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-cnt-name", "Contact Name", true)%></td>
			<td>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-salutation")%>><select name="VendorRegister_altSalutation">
                      <option value=""></option>
                      <option value="Mr." <% if (vendorRegister.getAltSalutation().equals("Mr.")) {%>selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "mr", "Mr")%>.</option>
                      <option value="Ms." <% if (vendorRegister.getAltSalutation().equals("Ms.")) {%>selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ms", "Ms")%>Ms.</option>
                      <option value="Mrs." <% if (vendorRegister.getAltSalutation().equals("Mrs.")) {%>selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "mrs", "Mrs")%>.</option>
                      <option value="Miss" <% if (vendorRegister.getAltSalutation().equals("Miss")) {%>selected<%}%>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "miss", "Miss")%></option>
                    </select></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-firstName")%>><input name="VendorRegister_altFirstName" size=27 maxLength=20 value="<%=vendorRegister.getAltFirstName()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-middleInitial")%>><input  name="VendorRegister_altMidInit" size=3 maxLength=2 value="<%=vendorRegister.getAltMidInit()%>"></td>
					<td <%=HtmlWriter.isVisible(oid, "bb-cnt-lastName")%>><input name="VendorRegister_altLastName" size=27 maxLength=20 value="<%=vendorRegister.getAltLastName()%>"></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-title")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-cnt-title", "Title", true)%></td>
			<td><input name="VendorRegister_altTitle" size=27 maxLength=30 value="<%=vendorRegister.getAltTitle()%>"></td>
		</tr>
		</table>
		<table border=0 cellspacing=1 cellpadding=1>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%> align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-cnt-telephoneNumber", "Phone Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-telephoneNumber")%>><input name="VendorRegister_altPhoneNo" size=18 maxLength=30" value="<%=vendorRegister.getAltPhoneNo()%>"></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-cnt-faxNumber", "Fax Number", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-cnt-faxNumber")%>><input name="VendorRegister_altFaxNumber" size=18 maxLenth=25 width=85px value="<%=vendorRegister.getAltFaxNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-cnt-emailAddress")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-cnt-emailAddress", "Email Address", true)%></td>
			<td><input name="VendorRegister_altEmailAddr" size=45 maxLength=50 value="<%=vendorRegister.getAltEmailAddr()%>"> </td>
		</tr>
		</table>
		<br>
	</td>
</tr>
<tr><td colspan=6 class=darkShadow><img src="<%=contextPath%>/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr>
	<td>&nbsp;</td>
	<td valign=top><br><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "capacityInformation", "Capacity Information")%></b></td>
	<td>&nbsp;</td>
	<td width=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" order=0 width=1px height=100% class=darkShadow></td>
	<td>&nbsp;</td>
	<td>
<%
	List vendorUdf1List = (List) request.getAttribute("vendorUdf1List");
	if (vendorUdf1List != null && vendorUdf1List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-VN01")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN01", "UDF 01", true)%> / <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "checkAllApply", "Check all that apply")%></b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="VendorRegister_vendorUdf1" value="<%=vendorRegister.getVendorUdf1()%>"/>
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
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-VN01")%>>
		<tr><td><br></td></tr>
		<tr>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN01", "UDF 01", true)%></td>
			<td><input type=text name="VendorRegister_vendorUdf1" value="<%=vendorRegister.getVendorUdf1()%>"></td>
		</tr>
		</table>
<%	}

	List vendorUdf2List = (List) request.getAttribute("vendorUdf2List");
	if (vendorUdf2List != null && vendorUdf2List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-VN02")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN02", "UDF 02", true)%> / <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "checkAllApply", "Check all that apply")%></b></td>
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
		<tr><td><br></td></tr>
		<tr>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN02", "UDF 02", true)%></td>
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
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN03", "UDF 03", true)%> / <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "checkAllApply", "Check all that apply")%></b></td>
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
		<tr><td><br></td></tr>
		<tr>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN03", "UDF 03", true)%></td>
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
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN04", "UDF 04", true)%> / <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "checkAllApply", "Check all that apply")%></b></td>
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
		<tr><td><br>></td></tr>
		<tr>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN04", "UDF 04", true)%></td>
			<td><input type=text name="VendorRegister_vendorUdf4" value="<%=vendorRegister.getVendorUdf4()%>"></td>
		</tr>
		</table>
<%	}

	List vendorUdf5List = (List) request.getAttribute("vendorUdf5List");
	if (vendorUdf5List != null && vendorUdf5List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-VN05")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN05", "UDF 05", true)%> / <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "checkAllApply", "Check all that apply")%></b></td>
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
		<tr><td><br></td></tr>
		<tr>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN05", "UDF 05", true)%></td>
			<td><input type=text name="VendorRegister_vendorUdf5" value="<%=vendorRegister.getVendorUdf5()%>"></td>
		</tr>
		</table>
<%	}%>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100%>
		<tr <%=HtmlWriter.isVisible(oid, "bb-VN06subcategory")%>><td colspan=5 nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN06subcategory", "", true)%></td></tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN06")%> align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN06", "UDF 06", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN06")%>><input name="VendorRegister_vendorUdf6" value="<%=vendorRegister.getVendorUdf6()%>" maxLength=15></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN07")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN07", "UDF 07", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN07")%> width=100px><input name="VendorRegister_vendorUdf7" value="<%=vendorRegister.getVendorUdf7()%>" maxLength=15></td>
		</tr>
		<tr>
<%	if (!oid.equals("BSC04P")) {%>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN08")%> align=right nowrap width=150px>><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN08", "UDF 08", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN08")%>><input name="VendorRegister_vendorUdf8" value="<%=vendorRegister.getVendorUdf8()%>" maxLength=15></td>
<%	}%>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN09")%> align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-VN09", "UDF 09", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-VN09")%> width=100px><input name="VendorRegister_vendorUdf9" value="<%=vendorRegister.getVendorUdf9()%>" maxLength=15></td>
		</tr>
		</table>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100%>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "bb-yearsInBusiness")%> align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-yearsInBusiness", "Years In Business", true)%></td>
			<td <%=HtmlWriter.isVisible(oid, "bb-yearsInBusiness")%>><input name="VendorRegister_vendorYears" value="<%=vendorRegister.getVendorYears()%>" size = 10 maxLength=15 onchange="nfilter(this);" style="text-align: right"></td>
		</tr>
		</table>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-refInformation")%>>
		<tr <%=HtmlWriter.isVisible(oid, "bb-refInformation")%>><td colspan=2><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-refInformation", "Current / Previous Client Reference", false)%></b></td></tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-refCompanyName")%>>
			<td align=right nowrap width=150px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-refCompanyName", "Company Name", true)%></td>
			<td><input type=text name="VendorRegister_refCompanyName" size=75 maxLength=255 value="<%=vendorRegister.getRefCompanyName()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-refPhoneNumber")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-refPhoneNumber", "Phone Number", true)%></td>
			<td><input type=text name="VendorRegister_refPhoneNumber" size=75 maxLength=255 value="<%=vendorRegister.getRefPhoneNumber()%>"></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-refContactName")%>>
			<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-refContactName", "Contact Name", true)%></td>
			<td><input type=text name="VendorRegister_refContactName" size=75 maxLength=255 value="<%=vendorRegister.getRefContactName()%>"></td>
		</tr>
		</table>
		<br>
	</td>
</tr>
<tr <%=HtmlWriter.isVisible(oid, "bb-ownershipType")%>><td colspan=6 class=darkShadow><img src="<%=contextPath%>/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr <%=HtmlWriter.isVisible(oid, "bb-ownershipType")%>>
	<td>&nbsp;</td>
  <td valign=top><br><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "businessOwnershipInformation", "Business Ownership Information")%></b></td>
	<td>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" order=0 width=1px height=100% class=darkShadow></td>
	<td>&nbsp;</td>
	<td>
<%
	List ownershipTypesList = (List) request.getAttribute("ownershipTypeList");
	if (ownershipTypesList != null && ownershipTypesList.size() > 0){ %>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-ownershipType")%>>
		<tr><td><br></td></tr>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-ownershipType", "Ownership Type", true)%> / <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "checkAllApply", "Check all that apply")%></b></td>
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
			StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_ownershipType" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendorRegister.getOwnershipType().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
			if (ownershipTypesList.size()/(fl + 1) <= 2 && b_new_column){
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
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-ownershipType")%>>
		<tr><td><br></td></tr>
		<tr>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-ownershipType", "Ownership Type", true)%></td>
			<td align=right><input type=text name="VendorRegister_ownershipType" value="<%=vendorRegister.getOwnershipType()%>"></td>
		</tr>
		</table>
<%	}

		List classificationTypeList = (List) request.getAttribute("classificationTypeList");
		if (classificationTypeList != null && classificationTypeList.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-vendorClass")%>>
		<tr>
			<td nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-vendorClass", "Vendor Class", true)%> / <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "checkAllApply", "Check all that apply")%></b></td>
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
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorClass" type=checkbox value="<%=stdTablePK.getTableKey()%>" <%if (vendorRegister.getVendorClass().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
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
				</table>
			</td>
		</tr>
		</table>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-vendorClass")%>>
		<tr>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-vendorClass", "Vendor Class", true)%></td>
			<td align=right><input type=text name="VendorRegister_vendorClass" value="<%=vendorRegister.getVendorClass()%>"></td>
		</tr>
		</table>
<%	}

	List diverseCertifiedOrgList = (List) request.getAttribute("diverseCertifiedOrgList");
	if (diverseCertifiedOrgList != null && diverseCertifiedOrgList.size() > 0) { %>
		<br>
	  <table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "bb-diverseCertOrgs")%>>
		<tr><td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-diverseCertOrgs", "Diverse Certified Organizations", true)%> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "checkAllApply", "Check all that apply")%>.</td></tr>
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
			StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_diverseCertOrg" type=checkbox value="<%=stdTablePK.getTableKey()%>" <%if (vendorRegister.getDiverseCertOrgs().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
<%			fl = il;
			if (diverseCertifiedOrgList.size()/(fl + 1) <= 2 && b_new_column){
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
		<br>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-diverseCertOrgs")%>>
		<tr>
			<td align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-diverseCertOrgs", "Diverse Certified Organizations", true)%></td>
			<td align=right><input type=text name="VendorRegister_diverseCertOrgs" value="<%=vendorRegister.getDiverseCertOrgs()%>"></td>
		</tr>
		</table>
<%	}%>
		<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "", "")%><br>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "bb-vendorDiversityProgram")%>>
		<tr <%=HtmlWriter.isVisible(oid, "bb-vendorDiversityProgram")%>>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "haveSupplierDiversityProgram", "Do you have a Supplier Diversity Program")%>?</td>
			<td align=right width=25px><input type=radio name="VendorRegister_diversityProgram" value="Y" <% if (vendorRegister.getDiversityProgram().equalsIgnoreCase("Y")) {%>checked<%}%>></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "yes", "Yes")%></td>
			<td align=right width=25px><input type=radio name="VendorRegister_diversityProgram" value="N" <% if (vendorRegister.getDiversityProgram().equalsIgnoreCase("N")) {%>checked<%}%>></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "no", "No")%></td>
		</tr>
		<tr <%=HtmlWriter.isVisible(oid, "bb-vendorSubcontract")%>>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "subcontractDiverseSuppliers", "Do you subcontract with diverse suppliers")%>?</td>
			<td align=right width=25px><input type=radio name="VendorRegister_subcontract" value="Y" <% if (vendorRegister.getSubcontract().equalsIgnoreCase("Y")) {%>checked<%}%>></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "yes", "Yes")%></td>
			<td align=right width=25px><input type=radio name="VendorRegister_subcontract" value="N" <% if (vendorRegister.getSubcontract().equalsIgnoreCase("N")) {%>checked<%}%>></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "no", "No")%></td>
		</tr>
		</table>
	</td>
</tr>
<tr  <%=HtmlWriter.isVisible(oid, "bb-certificationSection")%>><td colspan=6 class=darkShadow><img src="<%=contextPath%>/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
<tr  <%=HtmlWriter.isVisible(oid, "bb-certificationSection")%>>
	<td>&nbsp;</td>
	<td valign=top><br><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "certificationStatement", "Certification Statement")%></b></td>
	<td>&nbsp;</td>
	<td width=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" order=0 width=1px height=100% class=darkShadow></td>
	<td>&nbsp;</td>
	<td>
		<br>
		<table border=0 cellspacing=1 cellpadding=1  <%=HtmlWriter.isVisible(oid, "bb-certificationStatement")%>>
		<tr>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-certificationStatement", "", true)%></td>
		</tr>
		</table>
<%	if (oid.equals("BSC04P")) {%>
		<br>
		<table border=0 cellspacing=0 cellpadding=2>
		<tr>
			<td valign=top align=right><input type=checkbox name="as_vendorUdf8" value="Y" <% if (vendorRegister.getVendorUdf8().equals("Y")) {%>checked<%}%>><tsa:hidden name="VendorRegister_vendorUdf8" value="<%=vendorRegister.getVendorUdf8()%>"/></td>
			<td><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "excludedFromFederalProcurement", "My company has not been excluded from federal procurement and non-procurement programs")%>.</td>
		</tr>
		</table>
<%	}%>
		<br>
		<table border=0 cellspacing=0 cellpadding=2 class="requiredLabelHighlight"  <%=HtmlWriter.isVisible(oid, "bb-certificationCkboxStmt")%>>
		<tr>
			<td class="requiredLabelHighlight" valign=top align=right><input type=checkbox name="as_digitalSig" value="Y" <% if (vendorRegister.getDigitalSig().equals("Y")) {%>checked<%}%>><tsa:hidden name="VendorRegister_digitalSig" value="<%=vendorRegister.getDigitalSig()%>"/></td>
			<td class="requiredLabelHighlight"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "bb-certificationCkboxStmt", "I HEREBY CERTIFY ALL THE INFORMATION PROVIDED ON THIS FORM TO BE TRUE AND ACCURATE.", true)%></td>
		</tr>
		</tr>
		</table>
		<br>
	</td>
</tr>
<tr><td colspan=6 class=darkShadow><img src="<%=contextPath%>/images/none.gif" border=0 width=100% height=2px class=darkShadow></td></tr>
</table>

<br>

<table border=0 cellpadding=2 cellspacing=0 width=680px>
<tr>
	<td align=center width=100%>
		<a href="javascript: returnMe(); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" tabindex=86 border=0></a>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<script value=JavaScript>
<!--Hide Script
	frm = document.purchaseform;

	var currentRow = 0;
	var maxRows = 0;

	setCommodities();
	checkInputSettings();

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menubarSpacer");
	}

	function setCommodities() {
<% for (int ic=0; ic < commodityList.size(); ic++) {
		String commodityCode = (String) commodityList.get(ic);
%>
		addCommodity("<%=commodityList.get(ic)%>","<%=CommodityManager.getInstance().getCommodityDescription(oid, commodityCode)%>");
<% }%>
	}

	function addCommodity(commodityCode, desc)
	{
		myTable = document.getElementById("commodityTable");
		myRow = createRow(myTable);

		myCell = createCell(myRow);
		myCell.innerHTML = "<input name=\"VendorRegCommRel_commodityCode\" size=15 value=\"" + commodityCode + "\" onfocus=\"this.blur();\" class=disabledTxtBox readOnly><input type=text name=\"as_commodityDesc\" value=\"" + desc + "\" size=50 onfocus=\"this.blur();\" disabled>";

		currentRow = myTable.rows.length - 1;
	}

	function returnMe() {
		frm.allowBrowse.value = "true";
		frm.browseName.value = "vendor-register";
		doSubmit("admin/supplier/prequalify_suppliers.jsp", "BrowseRetrieve");
	}

	function viewDocs() {
	    var newInputField = "<input type=hidden name=VendorDocument_icRfqHeader value=0>" +
	    	"<input type=hidden name=VendorRegister_contactEmailAddr value=<%=vendorRegister.getComp_id().getContactEmailAddr()%>>" +
	    	"<input type=hidden name=VendorDocument_vendorId value=<%=vendorRegister.getComp_id().getVendorId()%>>" +
	    	"<input type=hidden name=allowEdit value=N>" +
	    	"<input type=hidden name=returnPage value='/admin/supplier/prequalification.jsp'>" +
	    	"<input type=hidden name=returnHandler value='VendorRegisterRetrieveByEmail;VendorOptionsRetrieve'>";
	    setHiddenFields(newInputField);
		doSubmit('/admin/supplier/supplier_attachments.jsp', 'VendorUpdate;VendorDocumentRetrieveByVendor', 'width=700', 'height=350');
	}
//-->
</script>

<%@ include file="/system/prevent_caching.jsp" %>