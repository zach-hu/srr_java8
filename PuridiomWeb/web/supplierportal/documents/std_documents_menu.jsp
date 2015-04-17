<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.StdDocumentType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String ss_company_name = propertiesManager.getProperty("COMPANY", "NAME", "Puridiom");
%>
<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td vAlign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width=1px height=1px /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 vAlign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Standard Documents</div>
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
</tr>
</table>

<br>
		<table width=680px cellpadding=0 cellspacing=0 border=0>
		<TR>
			<td width=10px>&nbsp;</td>
			<TD>
				<%=ss_company_name%>'s standard Procurement Documents are available
				to download for your review. These documents cannot be altered. Any exceptions taken to
				the terms and conditions presented here must be approved in writing by the procurement
				representative. Only those terms and conditions provided via printed copy by the procurement
				representative in the Purchase Order/Subcontract shall be considered the contract documents.
			</TD>
		</TR>
<!-- include virtual="/xbidboard/pdf_notice.jsp" -->
		<TR><TD COLSPAN="2"><BR><BR></TD></TR>
		<TR>
			<td width=10px>&nbsp;</td>
			<TD>
				<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0">
				<TR>
					<TD WIDTH="2%" CLASS="browseHdrDk" height=15px>&nbsp;</TD>
					<TD WIDTH="48%" CLASS="browseHdrDk" height=15px>
						&nbsp;&nbsp;<A HREF="javascript: standardTerms(); void(0);" class=browseHdrDk><%=StdDocumentType.toString(StdDocumentType.STANDARD_DOCUMENT, oid)%></U></A>
					</TD>
					<TD WIDTH="45%">&nbsp;</TD>
				</TR>
				<tr>
					<td colspan="3"><img src='<%=contextPath%>/supplierportal/images/none.gif' height=1 width='100%' class=browseHdrDk></td>
				</tr>
				<TR>
					<TD HEIGHT="20"></TD>
					<TD HEIGHT="20" COLSPAN="2">
						These are the sample terms and conditions which apply to all Purchase Orders
						and Subcontracts issued by <%=ss_company_name%>. Each Request for Proposal will identify which
						terms and conditions apply to the individual procurement.
					</TD>
				</TR>
				<TR><TD COLSPAN="3"><BR><BR><BR></TD></TR>
				<TR>
					<TD WIDTH="2%" CLASS="browseHdrDk" height=15px>&nbsp;</TD>
					<TD WIDTH="48%" CLASS="browseHdrDk" height=15px>
						&nbsp;&nbsp;<A HREF="javascript: otherDocuments(); void(0);" class=browseHdrDk><%=StdDocumentType.toString(StdDocumentType.OTHER_DOCUMENT, oid)%></A>
					</TD>
					<TD WIDTH="45%">&nbsp;</TD>
				</TR>
				<tr>
					<td colspan="3"><img src='<%=contextPath%>/supplierportal/images/none.gif' height=1 width='100%' class=browseHdrDk></td>
				</tr>
				<TR>
					<TD>&nbsp;</TD>
					<TD HEIGHT="20" COLSPAN="2">
						These are the other documents associated with <%=ss_company_name%>'s procurements.
						Included in these documents are the ones required for pre-qualification.
						Applicable documents will be identified on the Request for Proposal.
					</TD>
				</TR>
				</TABLE>
			</TD>
		</TR>
		</TABLE>

		<br><br>
		<tsa:hidden name="docType" value=""/>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=100% align=center><a href="javascript: doSubmit('menu/main_menu.jsp', 'DoNothing'); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_cancel.gif" class=button border=0 tabIndex=110 alt="cancel"></a></td>
</tr>
</table>
<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function standardTerms() {
		frm.docType.value = "<%=StdDocumentType.STANDARD_DOCUMENT%>";
		browseStdDocuments();
	}

	function otherDocuments() {
		frm.docType.value = "<%=StdDocumentType.OTHER_DOCUMENT%>";
		browseStdDocuments();
	}

	function browseStdDocuments() {
		var newInputField = "<input type='hidden' name='StdDocument_docType' value='" + frm.docType.value + "'>";
		setHiddenFields(newInputField);

		doSubmit('/documents/std_documents.jsp', 'StdDocumentRetrieveBy');
	}

//-->
</SCRIPT>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>