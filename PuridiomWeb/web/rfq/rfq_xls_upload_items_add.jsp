<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	String s_rfqStatus = (String) request.getAttribute("RfqHeader_status");
	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");
	String	docType = (String) request.getAttribute("DocAttachment_docType");
	String	docTitle = (String) request.getAttribute("DocAttachment_docTitle");
	String	docFilename = (String) request.getAttribute("DocAttachment_docFilename");
	String	docPost = (String) request.getAttribute("DocAttachment_docPost");
	String	docPrint = (String) request.getAttribute("DocAttachment_docPrint");
	String	docSource = (String) request.getAttribute("DocAttachment_docSource");
	String	returnPage = (String) request.getAttribute("returnPage");
	String	returnHandler = (String) request.getAttribute("returnHandler");
	//String formType = (String)request.getAttribute("formType");
	//String queueId = (String)request.getAttribute("queueId");

	if (HiltonUtility.isEmpty(icHeader)) {
		throw new Exception("The ic header was not found.");
	}
%>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="${RfqHeader_rfqType}"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=icHeader%>"/>
<tsa:hidden name="DocAttachment_docType" value="<%=HiltonUtility.ckNull(docType)%>"/>
<tsa:hidden name="DocAttachment_docTitle" value="<%=HiltonUtility.ckNull(docTitle)%>"/>
<tsa:hidden name="DocAttachment_docFilename" value="<%=HiltonUtility.ckNull(docFilename)%>"/>
<tsa:hidden name="DocAttachment_docPost" value="<%=HiltonUtility.ckNull(docPost)%>"/>
<tsa:hidden name="DocAttachment_docPrint" value="<%=HiltonUtility.ckNull(docPrint)%>"/>
<tsa:hidden name="DocAttachment_docSource" value="<%=HiltonUtility.ckNull(docSource)%>"/>
<!--<tsa:hidden name="formType" value="<%//=formType%>">-->
<!--<tsa:hidden name="queueId" value="<%//=queueId%>">-->

<%
String FilenameXls = HiltonUtility.ckNull(docFilename);
String	ext = FilenameXls.substring(FilenameXls.lastIndexOf("."));
int pos= FilenameXls.lastIndexOf(".");
String FnameXls = FilenameXls.substring(0,pos);
String Flag="V";
System.out.println("FilenameXls= "+ FilenameXls);
System.out.println("ext= "+ ext);
System.out.println("FnameXls= "+ FnameXls);
System.out.println("rfq_xls_upload_items_add := FilenameXLS= "+ FilenameXls);
%>

<tsa:hidden name="FilenameXls" value="<%=FnameXls%>"/>
<tsa:hidden name="Flag" value="<%=Flag%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Upload Items</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
		<table cellpadding="0" cellspacing="0" border=0>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>

<tr>
<td width=100% align=center valign=top><br><b>Processing... Please wait.</b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border=1 width=200px height=15px></td>
</tr>

<tr>
<td> </td>
</tr>
</table>


<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

   frm = document.purchaseform;

    doSubmit('/rfq/rfq_review.jsp', 'RfqRetrieve');

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>