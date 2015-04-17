<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%
	String vendorName = (String) request.getAttribute("Vendor_vendorName");
	String vendor_buyer = HiltonUtility.ckNull((String) request.getAttribute("Vendor_buyer"));
	Vendor vendor = (Vendor) request.getAttribute("vendor");

	String	s_current_process = "NOTES_UDFS";
	String	s_current_page = "/admin/supplier/supplier_notes.jsp";
	String	s_current_method = "VendorUpdate";
	String	s_current_process_method = "";
%>

<tsa:hidden name="VendorAccount_vendorId" value="${Vendor_vendorId}"/>
<tsa:hidden name="VendorAffiliate_vendorId" value="${Vendor_vendorId}"/>
<tsa:hidden name="VendorInsurance_vendorId" value="${Vendor_vendorId}"/>
<tsa:hidden name="tableType" value=""/>

<%@ include file="/admin/supplier/supplier_hidden_fields.jsp" %>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=vendor.getVendorName()%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center>
		<table border=0 width=100%>
		<tr <%=HtmlWriter.isVisible(oid, "supplierNotes")%>>
			<td align="right" valign="top" width="150px"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplierNotes", "Notes", true)%>:</td>
			<td colspan="3"><textarea name="Vendor_notes" cols=61 rows=6 tabIndex=1 onKeyDown="textCounter(this, 255);" onKeyUp="textCounter(this, 255);"><%=HiltonUtility.ckNull(vendor.getNotes())%></textarea></td>
		</tr>
<%
	List vendorUdf1List = (List) request.getAttribute("vendorUdf1List");
	if (vendorUdf1List != null && vendorUdf1List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "sup-VN01")%>>
		<tr>
			<td nowrap><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN01", "Supplier UDF 01", true)%> - <%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN01CheckAll", "Check all that apply", true)%></b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="Vendor_vendUdf1" value="<%=vendor.getVendUdf1()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf1List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf1List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorUdf1" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getVendUdf1().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
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
		<br>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "sup-VN01")%> width=100%>
		<tr>
			<td align=right nowrap width=150px><a href="javascript: browseStd('Vendor_vendUdf1','VN01'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN01", "Supplier UDF 01", true, true, "Vendor_vendUdf1")%></a></td>
			<td><input type=text name="Vendor_vendUdf1" value="<%=vendor.getVendUdf1()%>"></td>
		</tr>
		</table>
<%	}

	List vendorUdf2List = (List) request.getAttribute("vendorUdf2List");
	if (vendorUdf2List != null && vendorUdf2List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "sup-VN02")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN02", "Supplier UDF 02", true)%> - <%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN02CheckAll", "Check all that apply", true)%></b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="Vendor_vendUdf2" value="<%=vendor.getVendUdf2()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf2List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf2List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorUdf2" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getVendUdf2().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
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
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "sup-VN02")%> width=100%>
		<tr>
			<td align=right nowrap width=150px><a href="javascript: browseStd('Vendor_vendUdf2','VN02'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN02", "Supplier UDF 02", true, true, "Vendor_vendUdf2")%></a></td>
			<td><input type=text name="Vendor_vendUdf2" value="<%=vendor.getVendUdf2()%>"></td>
		</tr>
		</table>
<%	}
	List vendorUdf3List = (List) request.getAttribute("vendorUdf3List");
	if (vendorUdf3List != null && vendorUdf3List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "sup-VN03")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN03", "Supplier UDF 03", true)%> - <%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN03CheckAll", "Check all that apply", true)%></b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="Vendor_vendUdf3" value="<%=vendor.getVendUdf3()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf3List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf3List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorUdf3" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getVendUdf3().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
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
		<br>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "sup-VN03")%> width=100%>
		<tr>
			<td align=right nowrap width=150px><a href="javascript: browseStd('Vendor_vendUdf3','VN03'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN03", "Supplier UDF 03", true, true, "Vendor_vendUdf3")%></a></td>
			<td><input type=text name="Vendor_vendUdf3" value="<%=vendor.getVendUdf3()%>"></td>
		</tr>
		</table>
<%	}
	List vendorUdf4List = (List) request.getAttribute("vendorUdf4List");
	if (vendorUdf4List != null && vendorUdf4List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "sup-VN04")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN04", "Supplier UDF 04", true)%> - <%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN04CheckAll", "Check all that apply", true)%></b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="Vendor_vendUdf4" value="<%=vendor.getVendUdf4()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf4List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf4List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorUdf4" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getVendUdf4().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
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
		<br>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "sup-VN04")%> width=100%>
		<tr>
			<td align=right nowrap width=150px><a href="javascript: browseStd('Vendor_vendUdf4','VN04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN04", "Supplier UDF 04", true, true, "Vendor_vendUdf4")%></a></td>
			<td><input type=text name="Vendor_vendUdf4" value="<%=vendor.getVendUdf4()%>"></td>
		</tr>
		</table>
<%	}

	List vendorUdf5List = (List) request.getAttribute("vendorUdf5List");
	if (vendorUdf5List != null && vendorUdf5List.size() > 0) { %>
		<br>
		<table border=0 cellspacing=1 cellpadding=1 width=100% <%=HtmlWriter.isVisible(oid, "sup-VN05")%>>
		<tr>
			<td nowrap ><b><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN05", "Supplier UDF 05", true)%> - <%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN05CheckAll", "Check all that apply", true)%></b></td>
		</tr>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0 align=center>
				<tr>
					<td width="10%">&nbsp;</td>
					<td width=45% valign=top>
						<tsa:hidden name="Vendor_vendUdf5" value="<%=vendor.getVendUdf5()%>"/>
						<table width=100% border=0 cellpadding=0 cellspacing=0>
<%		boolean b_new_column = true;
			float fl = 0;
			for (int il = 0; il < vendorUdf5List.size(); il++) {
				StdTable stdTable = (StdTable) vendorUdf5List.get(il);
				StdTablePK stdTablePK = (StdTablePK) stdTable.getComp_id(); %>
						<tr><td><input name="as_vendorUdf5" type=checkbox  value="<%=stdTablePK.getTableKey()%>" <%if (vendor.getVendUdf5().indexOf(stdTablePK.getTableKey()) >= 0) {%>CHECKED<%}%>>&nbsp;<%=stdTable.getDescription()%></td></tr>
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
		<br>
<%	} else {%>
		<table border=0 cellspacing=1 cellpadding=1 <%=HtmlWriter.isVisible(oid, "sup-VN05")%> width=100%>
		<tr>
			<td align=right nowrap width=150px><a href="javascript: browseStd('Vendor_vendUdf5','VN05'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, "").getLabel(oid, "sup-VN05", "Supplier UDF 05", true, true, "Vendor_vendUdf5")%></a></td>
			<td><input type=text name="Vendor_vendUdf5" value="<%=vendor.getVendUdf5()%>"></td>
		</tr>
		</table>
<%	}%>
<br><br>
		<table border=0 cellspacing=1 cellpadding=1 width=100%>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "sup-VN06")%> align=right width=150px><a href="javascript: browseStd('Vendor_vendUdf6','VN06'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-VN06", "Supplier UDF 06", true, true, "Vendor_vendUdf6")%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-VN06")%>><input type=text name="Vendor_vendUdf6" tabindex=7 maxlength=15 value="<%=HiltonUtility.ckNull(vendor.getVendUdf6())%>"></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-VN07")%> align=right width=150px><a href="javascript: browseStd('Vendor_vendUdf7','VN07'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-VN07", "Supplier UDF 07", true, true, "Vendor_vendUdf7")%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-VN07")%>><input type=text name="Vendor_vendUdf7" tabindex=8 maxlength=15 value="<%=HiltonUtility.ckNull(vendor.getVendUdf7())%>"></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "sup-VN08")%> align=right width=150px><a href="javascript: browseStd('Vendor_vendUdf8','VN08'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-VN08", "Supplier UDF 08", true, true, "Vendor_vendUdf8")%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-VN08")%>><input type=text name="Vendor_vendUdf8" tabindex=9 maxlength=15 value="<%=HiltonUtility.ckNull(vendor.getVendUdf8())%>"></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-VN09")%> align=right width=150px><a href="javascript: browseStd('Vendor_vendUdf9','VN09'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-VN09", "Supplier UDF 09", true, true, "Vendor_vendUdf9")%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-VN09")%>><input type=text name="Vendor_vendUdf9" tabindex=10 maxlength=15 value="<%=HiltonUtility.ckNull(vendor.getVendUdf9())%>"></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "sup-VN10")%> align=right width=150px><a href="javascript: browseStd('Vendor_vendUdf10','VN10'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sup-VN10", "Supplier UDF 10", true, true, "Vendor_vendUdf10")%>:</a></td>
			<td <%=HtmlWriter.isVisible(oid, "sup-VN10")%>><input type=text name="Vendor_vendUdf10" tabindex=11 maxlength=15 value="<%=HiltonUtility.ckNull(vendor.getVendUdf10())%>"></td>
		</tr>
		</table>
	</td>
	<td valign=top align="right"><br><%@ include file="/admin/supplier/supplier_sidebar.jsp" %></td>
</tr>
</table>

<br>
<br>


<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	var currentRow = 0;

	function thisLoad()
	{
		f_StartIt();
<%	if (role.getAccessRights("VEND") < 2) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function validateForm()
	{
		//  if user clicks on Purchase History step
		if (frm.handler.value.indexOf("BrowseRetrieve") > 0)
		{
			setOriginalFilter("PoHeader_vendorId", "=", '${esapi:encodeForJavaScript(Vendor_vendorId)}');
			var newInputField = "<input type=hidden name='PurchaseHistory_vendorId' value=${esapi:encodeForJavaScript(Vendor_vendorId)}>";
			setHiddenFields(newInputField);
			frm.browseName.value = "supplierorders";
		}
		if (frm.handler.value.indexOf("VendorUpdate") >= 0)
		{
			setVendorUdf1();
			setVendorUdf2();
			setVendorUdf3();
			setVendorUdf4();
			setVendorUdf5();
		}
		return true;
	}

	function browseStdTable(code)
	{
		frm.tableType.value = code;
		setOriginalFilter("StdTable_id_tableType", "=", code);
		browse('stdtable-admin');
	}

	function setVendorUdf1() {
		if (frm.Vendor_vendUdf1) {
			var vendorUdfs = frm.elements.item("as_vendorUdf1");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.Vendor_vendUdf1.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf1").length; i++){
						if (frm.as_vendorUdf1[i].checked) {
							frm.Vendor_vendUdf1.value + frm.as_vendorUdf1[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf1 && frm.as_vendorUdf1.checked) {
						frm.Vendor_vendUdf1.value = frm.as_vendorUdf1.value;
					}
				}
			}
		}
	}

	function setVendorUdf2() {
		if (frm.Vendor_vendUdf2) {
			var vendorUdfs = frm.elements.item("as_vendorUdf2");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.Vendor_vendUdf2.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf2").length; i++){
						if (frm.as_vendorUdf2[i].checked) {
							frm.Vendor_vendUdf2.value = frm.Vendor_vendUdf2.value + frm.as_vendorUdf2[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf2 && frm.as_vendorUdf2.checked) {
						frm.Vendor_vendUdf2.value = frm.as_vendorUdf2.value;
					}
				}
			}
		}
	}

	function setVendorUdf3() {
		if (frm.Vendor_vendUdf3) {
			var vendorUdfs = frm.elements.item("as_vendorUdf3");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.Vendor_vendUdf3.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf3").length; i++){
						if (frm.as_vendorUdf3[i].checked) {
							frm.Vendor_vendUdf3.value = frm.Vendor_vendUdf3.value + frm.as_vendorUdf3[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf3 && frm.as_vendorUdf3.checked) {
						frm.Vendor_vendUdf3.value = frm.as_vendorUdf3.value;
					}
				}
			}
		}
	}

	function setVendorUdf4() {
		if (frm.Vendor_vendUdf4) {
			var vendorUdfs = frm.elements.item("as_vendorUdf4");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.Vendor_vendUdf4.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf4").length; i++){
						if (frm.as_vendorUdf4[i].checked) {
							frm.Vendor_vendUdf4.value = frm.Vendor_vendUdf4.value + frm.as_vendorUdf4[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf4 && frm.as_vendorUdf4.checked) {
						frm.Vendor_vendUdf4.value = frm.as_vendorUdf4.value;
					}
				}
			}
		}
	}

	function setVendorUdf5() {
		if (frm.Vendor_vendUdf5) {
			var vendorUdfs = frm.elements.item("as_vendorUdf5");
			if (vendorUdfs != undefined) {
				if (vendorUdfs.length > 1 ) {
					frm.Vendor_vendUdf5.value = "";
					for (var i=0; i < frm.elements.item("as_vendorUdf5").length; i++){
						if (frm.as_vendorUdf5[i].checked) {
							frm.Vendor_vendUdf5.value = frm.Vendor_vendUdf5.value + frm.as_vendorUdf5[i].value;
						}
					}
				} else {
					if (frm.as_vendorUdf5 && frm.as_vendorUdf5.checked) {
						frm.Vendor_vendUdf5.value = frm.as_vendorUdf5.value;
					}
				}
			}
		}
	}


// End Hide script -->
</SCRIPT>