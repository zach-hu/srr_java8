<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	//In this part we get information of asset and DocAttachment entities using the icHeader attribute -->
	String	assetNumber = (String) request.getAttribute("Asset_tagNumber");
	String	icHeader = (String) request.getAttribute("DocAttachment_icHeader");

	String	assetTrackingNumber = (String) request.getAttribute("Asset_trackingNumber");
	String	assetItemNumber = (String) request.getAttribute("Asset_itemNumber");

	String	docType = (String) request.getAttribute("DocAttachment_docType");
	String	docTitle = (String) request.getAttribute("DocAttachment_docTitle");
	String	docFilename = (String) request.getAttribute("DocAttachment_docFilename");
	String	docPost = (String) request.getAttribute("DocAttachment_docPost");
	String	docPrint = (String) request.getAttribute("DocAttachment_docPrint");
	String	docSource = (String) request.getAttribute("DocAttachment_docSource");
	String	returnPage = (String) request.getAttribute("returnPage");
	String	returnHandler = (String) request.getAttribute("returnHandler");

	if (HiltonUtility.isEmpty(icHeader)) {
		throw new Exception("The ic header was not found.");
	}
%>
<tsa:hidden name="Asset_tagNumber" value="<%=assetNumber%>"/>
<tsa:hidden name="Asset_trackingNumber" value="<%=assetTrackingNumber%>"/>
<tsa:hidden name="Asset_itemNumber" value="<%=assetItemNumber%>"/>

<tsa:hidden name="DocAttachment_icHeader" value="<%=icHeader%>"/>
<tsa:hidden name="DocAttachment_docType" value="<%=HiltonUtility.ckNull(docType)%>"/>
<tsa:hidden name="DocAttachment_docTitle" value="<%=HiltonUtility.ckNull(docTitle)%>"/>
<tsa:hidden name="DocAttachment_docFilename" value="<%=HiltonUtility.ckNull(docFilename)%>"/>
<tsa:hidden name="DocAttachment_docPost" value="<%=HiltonUtility.ckNull(docPost)%>"/>
<tsa:hidden name="DocAttachment_docPrint" value="<%=HiltonUtility.ckNull(docPrint)%>"/>
<tsa:hidden name="DocAttachment_docSource" value="<%=HiltonUtility.ckNull(docSource)%>"/>
<%
	if (HiltonUtility.isEmpty(assetNumber)) {
		assetNumber = "N/A";
	}
%>
<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr><tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td></tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="attachments" defaultString="Attachments" /></div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<!--In this part we show the ItemNumber and TrackingNumber attributes of asset-->
		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="itemnumber" defaultString="ItemNumber #" />:</b></tsa:td>
			<tsa:td width="125px"><%=assetItemNumber%></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right"><b><tsa:label labelName="trackingnumber" defaultString="TrackingNumber #" />:</b></tsa:td>
			<tsa:td width="125px"><%=assetTrackingNumber%></tsa:td>
		</tsa:tr>

				</table>
		<table cellpadding="0" cellspacing="0" border="0">
		<tsa:tr><tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td></tsa:tr>
		<tsa:tr><tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td></tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr><tsa:td width="100%" align="center" valign="top"><br><b><tsa:label labelName="processing" defaultString="Processing... Please wait." /></b><br><br><br><img src="<%=contextPath%>/images/processing.gif" border="1" width="200px" height="15px"></tsa:td></tsa:tr>
</table>
<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;

	doSubmit("<%=headerEncoder.encodeForJavaScript(returnPage)%>", "DocAttachmentAdd;<%=returnHandler%>");

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>