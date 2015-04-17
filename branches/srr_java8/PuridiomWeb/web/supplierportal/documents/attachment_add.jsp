<tsa:hidden name="VendorDocument_icRfqHeader" value="<%=icHeader%>"/>
<tsa:hidden name="VendorDocument_docIc" value=""/>
<tsa:hidden name="VendorDocument_vendorId" value="<%=vendorId%>"/>
<tsa:hidden name="VendorDocument_docTitle" value="<%=HiltonUtility.ckNull(docTitle)%>"/>
<tsa:hidden name="VendorDocument_docFilename" value="<%=HiltonUtility.ckNull(docFilename)%>"/>
<tsa:hidden name="VendorDocument_docType" value="<%=HiltonUtility.ckNull(docType)%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td></tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Adding <%=attachmentType%> Document</div>
			</td>
		</tr>
		</table>
	</td>
	<td width=30px valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width="30" height="31" /></td>
	<td valign=bottom align=right height=30px>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr><td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr><td width=100% align=center valign=top><br><b>Processing... Please wait.</b><br><br><br><img src="<%=contextPath%>/supplierportal/images/processing.gif" border=1 width=200px height=15px></td></tr>
</table>
<br>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	doSubmit("<%=returnPage%>", "VendorDocumentAdd;<%=returnHandler%>");

// end hiding contents -->
</SCRIPT>