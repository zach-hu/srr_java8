<%@ page language="java" errorPage="/xbidboard/error_pg.jsp" %>
<%@ page import="com.puridiom.general.InputObj" %>
<%@ page import="com.puridiom.general.OutputObj" %>

<%@ include file="/supplierportal/xbidboard/session/ck_session.jsp" %>
<%@ include file="/supplierportal/xbidboard/session/session_variables.jsp" %>
<HTML>
<HEAD>
	<META http-equiv="content-type" content="text/html;charset=iso-8859-1">
	<META HTTP-EQUIV="Cache-Control" content="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="Sat, 11 Sep 1971 12:00:00 GMT">
	<META NAME="Description" Content="/puridiom/xbidboard/bids/documents.jsp">
	<TITLE>Attached Documents</TITLE>	
	<SCRIPT LANGUAGE="JavaScript1.2" SRC="/puridiom/xbidboard/scripts/xchange.js"></SCRIPT>
	<LINK REL=STYLESHEET TYPE="text/css" HREF="/puridiom/xbidboard/xchange_style.css">
</HEAD>
<BODY>
<%@ include file="/supplierportal/xbidboard/menu/title.inc" %>
<%@ include file="/supplierportal/xbidboard/menu/menu.inc" %>
<%@ include file="/supplierportal/xbidboard/menu/browsemenu.inc" %>
<%
	InputObj iObj = new InputObj();

	iObj.setInputValue("header_ic",ss_hic) ;

	iObj.setComponentName("documents");
	iObj.setMethodName("retrieveDocuments") ;

	OutputObj oObj = BeanJag.retrieve(iObj);

	retval = oObj.getRetval();

	if ( retval == 1)
	{
		int rows = BeanJag.getRowCount("documents");
%>
<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER">
	<TR><TD CLASS="hdr12"><BR>Documents:</TD></TR>
	<TR><TD CLASS="hdr12"><BR></TD></TR>
	<TR>
		<TD ALIGN="CENTER">
			<TABLE CLASS="htable" WIDTH="380px" ALIGN="CENTER" BORDER="0" CELLSPACING="1" CELLPADDING="0">
				<TR CLASS="wlhdr">
					<TH ALIGN="CENTER">&nbsp;</TH>
					<TH CLASS="wlhdr" ALIGN="CENTER">Title</TH>
					<TH CLASS="wlhdr" ALIGN="CENTER">Description</TH>
				</TR>
				<FORM NAME="purchaseform" ACTION="/puridiom/xbidboard/base/update.jsp" METHOD="POST">
<%
		String stext = "";
		String s_class = "wlrowA";

		for (int i = 1; i < rows; i++) {

			if (BeanJag.getValue("documents", "doc_post", i).equals("Y")) {
%>
				<TR CLASS="<%=s_class%>">
					<TD CLASS="wlrow<%=s_class%>"><%=BeanJag.getDecimal("documents", "doc_order", i, 0)%></TD>
					<TD CLASS="wlrow<%=s_class%>">&nbsp;<A HREF="javascript: openDoc('<%=BeanJag.getValue("documents", "doc_filename", i)%>'); void(0);"><%=BeanJag.getValue("documents", "doc_title", i)%></A></TD>
					<TD CLASS="wlrow<%=s_class%>">
						&nbsp;<%stext = BeanJag.getValue("documents", "doc_desc", i); if(stext.length() >40){out.println(stext.substring(0,40));}else{out.println(stext);}%>
						<tsa:hidden name="doc_doc_post" value="<%=BeanJag.getValue("documents", "doc_post", i)%>"/>
						<tsa:hidden name="doc_doc_title" value="<%=BeanJag.getValue("documents", "doc_title", i)%>"/>
						<tsa:hidden name="doc_doc_order" value="<%=BeanJag.getDecimal("documents", "doc_order", i, 0)%>"/>
					</TD>
				</TR>
<%
				if (s_class.equals("wlrowA")) {
					s_class = "wlrowB";
				} else {
					s_class = "wlrowA";
				}
			}

		}%>
			</TABLE>
		</TD>
	</TR>
</TABLE>

<TABLE ALIGN="CENTER">
	<TR>
		<TD>
			<BR>
			<tsa:hidden name="ck_post" value=""/>
			<tsa:hidden name="doc_doc_post" value=""/>
			<tsa:hidden name="doc_doc_title" value=""/>
			<tsa:hidden name="doc_doc_order" value=""/>
			<tsa:hidden name="as_action" value="redirect"/>
			<tsa:hidden name="as_redirect" value="/puridiom/xbidboard/bids/documents.jsp"/>
			<tsa:hidden name="as_rows" value="<%=rows%>"/>
			<tsa:hidden name="doc_header_ic" value="<%=ss_hic%>"/>
		</TD>
	</TR>
	<TR>
		<TD ALIGN="CENTER" CLASS="basic">
			<A HREF="javascript: cancelthis(); void(0);"><IMG TABINDEX=40 SRC="/puridiom/xbidboard/images/button_close.gif" BORDER="0"></A>
		</TD>
	</TR>
	<TR>
		<TD ALIGN="CENTER" VALIGN="TOP"><%@ include file="/supplierportal/xbidboard/copyright.jsp" %></TD>
	</TR>
</TABLE>
</FORM>
<%
	}
	else
	{
		String	errorstr = oObj.getOutValue("errorMsg");			
		out.println("<br>Errorstr = " + errorstr.toString());
	}
%>

</BODY>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers
	frm = document.purchaseform;

	function cancelthis()
	{
		this.close();
	}

	function openDoc(fileName)
	{
		var winspecs = 'width=700' +","+ 'height=500' +",resizable=1,scrollbars=1,menubar=1,location=0,top=0,left=0";
		var detail_window = window.open("/puridiom/xbidboard/base/download.jsp?file=" + fileName + "&number=<%=ss_rfqno%>&type=", "detail_window", winspecs);

		detail_window.focus();
	}
/*
	function openDoc(doc_ord)
	{
		var winspecs = 'width=600' +","+ 'height=400' +",resizable=1,scrollbars=1,menubar=0,location=0,top=0,left=0";
		var detail_window = window.open('/puridiom/xbidboard/bids/get_doc_name.jsp?action=open&doc_ord=' + doc_ord, "detail_window", winspecs);

		detail_window.focus();
	}
*/

// end hiding contents -->
</SCRIPT>

</html>
