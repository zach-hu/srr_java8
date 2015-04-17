<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.ReceiptType" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.entity.StdTable" %>
<%@ page import="com.tsa.puridiom.stdtable.tasks.StdTableManager" %>
<%@ page import="java.math.BigDecimal" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	quantityDecimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	formId = HiltonUtility.ckNull((String) request.getAttribute("ElementForm_formId"));
	boolean readOnly = HiltonUtility.ckNull((String) request.getAttribute("readOnly")).equalsIgnoreCase("true");
	String  documentText = HiltonUtility.ckNull((String) request.getAttribute("documentText"));
	String  documentNumber = HiltonUtility.ckNull((String) request.getAttribute("documentNumber"));
	String  formTitle = HiltonUtility.ckNull((String) request.getAttribute("formTitle"));
	BigDecimal	icHeader = new BigDecimal(HiltonUtility.ckNull((String) request.getAttribute("ElementData_icHeader")));
	BigDecimal	icLine = new BigDecimal(HiltonUtility.ckNull((String) request.getAttribute("ElementData_icLine")));
	List					formDefList = (List) request.getAttribute("formDefList") ;
	if (formDefList == null) formDefList = new ArrayList() ;
	List					formDataList = (List) request.getAttribute("formDataList") ;
	if (formDataList == null) formDataList = new ArrayList() ;
%>
<input type=hidden name="ElementData_icHeader" value="<%=icHeader%>">
<input type=hidden name="ElementData_icLine" value="<%=icLine%>">
<input type=hidden name="ElementData_formId" value="<%=formId%>">
<input type=hidden name="ElementForm_formId" value="<%=formId%>">
<input type=hidden name="formTitle" value="<%=formTitle%>">


<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=formTitle%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
		<% if (! HiltonUtility.isEmpty(documentText)) { %>
			<td nowrap align=right><b><%=documentText %>:</b></td>
			<td width=100px><%=documentNumber%></td>
		<% } %>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br><br>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td valign="top" align="center">
		<table border="0" cellspacing="0" cellpadding="0" width="90%">
		<tr>
		<%
		int defCount = formDefList.size() ;
		int dataCount = formDataList.size() ;

		BigDecimal currentTb = new BigDecimal(0 );
		BigDecimal currentTc = new BigDecimal(0) ;
		for (int ix = 0; ix < defCount; ix++) {
			ElementForm formDef = (ElementForm) formDefList.get(ix) ;
			// Check for table change
			if (formDef.getElementTb().compareTo(currentTb) != 0)  {
				if (currentTb.compareTo(new BigDecimal(0)) != 0) { %>
					</tr>
					</table>
					<br>
		<%  } 	%>
		 		<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
	<% }  else if ( formDef.getElementTc().compareTo(currentTc) <= 0) { %>
				<tr>
	<%	}
			currentTb = formDef.getElementTb() ;
			currentTc = formDef.getElementTc() ;

			String iD = formDef.getElementId() ;
			String len = formDef.getElementLength().toString() ;
			String dType = formDef.getElementType() ;
			String justify = "LEFT" ;
			String labelDflt = formDef.getElementLabel() ;
			if (dType.startsWith("NUM")) justify = "RIGHT" ;

			// Get Data value
			String dataValue = "" ;
			for (int ic = 0; ic < dataCount; ic++) {
				ElementData formData = (ElementData) formDataList.get(ic) ;
				if (iD != null && iD.equalsIgnoreCase(formData.getComp_id().getElementId()) ) {
					dataValue = formData.getElementValue() ;
					break ;
				}
			}

			if (iD == null || iD.equalsIgnoreCase("(blank)") || iD.equalsIgnoreCase("idpNo")) { %>
					<td align=right nowrap>&nbsp;</td>
	<% } else { %>
					<td align=right nowrap><%=labelDflt %>&nbsp;</td>
					<td><input type=text name="<%=iD%>" size=<%=len%> maxlength=<%=len%> tabindex=<%=ix%> value="<%=dataValue%>" onchange="upperCase(this);" <% if ( readOnly ) {%>  READONLY  <%}%></td>
					<% }  %>
<%		} 	%>
					</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=500px>
<tr>
	<% if (readOnly) { %>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: closeReturn(); ">Return</a></div></td>
	<% } else { %>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: saveReturn(); ">Save</a></div></td>
			<td width=50% align=center><div id="pxbutton"><a href="javascript: closeReturn(); ">Return</a></div></td>
	<% } %>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
    readOnly = <%=readOnly%> ;
	self.focus();


	function saveReturn() {
		doSubmit('/system/close_window.jsp', 'ElementDataSave');
	}

	function closeReturn() {

		doSubmit('/system/close_window.jsp', 'DoNothing');

	}


// end hiding contents -->
</SCRIPT>