<!-- RFQ HEADER -->
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%
	/********************************************************/
	/*	String	s_subheading -- must be declared			*/
	/*	String	s_rfq_number -- must be declared			*/
	/*	String	s_rfq_amendment -- must be declared		*/
	/*	String	s_rfq_status -- must be declared				*/
	/********************************************************/
%>
<TR><TD COLSPAN="2"><BR></TD></TR>
<TR>
	<TD VALIGN="MIDDLE" rowspan=2" class="hdr16"><%=s_subheading%></TD>
	<TD ALIGN="RIGHT" VALIGN="MIDDLE" rowspan="2" WIDTH="40%">
		<TABLE BORDER="0" CELLPADDING="1" CELLSPACING="2">
		<TR>
			<TD WIDTH="100" NOWRAP ALIGN="RIGHT" CLASS="dkbox">Solicitation:</TD>
			<TD WIDTH="135" NOWRAP CLASS="ltbox">&nbsp;<%=s_rfq_number%><BR></TD>
		</TR>
<%	if (!HiltonUtility.isEmpty(s_rfq_amendment) && !s_rfq_amendment.equals("0")) {%>
		<TR>
			<TD WIDTH="100" NOWRAP ALIGN="RIGHT" CLASS="dkbox">Amendment:</TD>
			<TD WIDTH="135" NOWRAP CLASS="ltbox">&nbsp;<%=s_rfq_amendment%><BR></TD>
		</TR>
<%	}%>
		<TR>
			<TD WIDTH="100" NOWRAP ALIGN="RIGHT" CLASS="dkbox">Status:</TD>
			<TD WIDTH="135" NOWRAP CLASS="ltbox">&nbsp;<%=DocumentStatus.toString(s_rfq_status, oid)%><BR></TD>
		</TR>
		</TABLE>
	</TD>
</TR>
			
<!-- RFQ HEADER END -->