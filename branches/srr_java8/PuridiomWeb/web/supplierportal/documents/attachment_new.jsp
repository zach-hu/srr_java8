<tsa:hidden name="VendorDocument_icRfqHeader" value="<%=icHeader%>"/>
<tsa:hidden name="VendorDocument_docIc" value=""/>
<tsa:hidden name="VendorDocument_vendorId" value="<%=user.getVendorId()%>"/>
<tsa:hidden name="VendorDocument_docType" value="<%=docType%>"/>
<tsa:hidden name="returnPage" value="<%=returnPage%>"/>
<tsa:hidden name="returnHandler" value="VendorDocumentRetrieveByVendor"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Add <%=attachmentType%> Document</div>
			</td>
		</tr>
		</table>
	</td>
	<td width=30px valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width="30" height="31" /></td>
	<td valign=bottom align=right height=30px>
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

<br><br><br>

<table border=0 cellpadding=0 cellspacing=0 width=680px height=200px>
<tr>
	<td valign=top align=center width=100%>	
		<table border=0 cellpadding=2 cellspacing=0>
		<tr>
			<td align=right>Title:</td>
			<td><input type=text name="VendorDocument_docTitle" size=60 maxLength=60></td>
		</tr>
		<tr>
			<td align=right>File to Attach:</td>
			<td><input type=file name=file3 size=40></td>
		</tr>
		</table>
		
		<br><br>
		
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td align=center width=50%><a href="javascript: addDocument(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_add.gif" border=0 class=button></a></td>
			<td align=center width=50%><a href="javascript: doSubmit('<%=returnPage%>', 'VendorDocumentRetrieveByVendor'); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_return.gif" border=0 class=button></a></td>
		</tr>
		</table>
	</td>
</tr>
</table>
<br>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	function validateForm() {
		var handler = frm.handler.value;

		if (handler.indexOf("--") >= 0) {
			var doc = frm.file3.value;
			var title = frm.VendorDocument_docTitle.value;
			var alertMessage = "";
	
			if ( isEmpty(title) && isEmpty(doc) ) {
				alert("Please enter a Title and select a File to Attach.");
				return false;
			}
			else if (isEmpty(doc)) {
				alert('Please select a file to attach.');
				return false;
			}
			else if (isEmpty(title)) {
				alert('Please enter a Document Title.');
				return false;
			}
			else {
				frm.action =" <%=contextPath%>/PuridiomSupplierPortalDocumentUpload";
				frm.action = frm.action + "?" + frm.organizationId.value;
				frm.enctype = "multipart/form-data";
				frm.encoding = "multipart/form-data";
			}
		}
		return true;
	}

// end hiding contents -->
</SCRIPT>