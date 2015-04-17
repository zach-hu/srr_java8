<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ page import="com.tsa.puridiom.entity.RequisitionHeader" %>
<%
	String	oid = (String) request.getAttribute("organizationId");
	String	language = (String)request.getAttribute("language");
	RequisitionHeader reqHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	pageContext.setAttribute("oid", oid);
	pageContext.setAttribute("language", language);
%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td width=680px align=center>
    <table border=0 cellpadding=0 cellspacing=0 width=665px>
    <tr>
      <td width=50% align=center valign=top>
        <table border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdrDk>
        <tr>
          <td class=browseRow>
            <table border=0 cellspacing=0 cellpadding=2 width=100%>
            <tsa:tr field="req-OWNERFULLNAME">
	          <td nowrap align=right><b>
	          <tsa:label labelName="req-OWNERFULLNAME" defaultString="FPE Name" checkRequired="false"/>:</b>&nbsp;</td>
	          <td nowrap width=100%><%=reqHeader.getOwner()%></td>
			</tsa:tr>
            <tsa:tr field="req-RQ05">
              <td nowrap align=right><b>
              <tsa:label labelName="req-RQ05" defaultString="Requisition UDF 5" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap width=100%><%=reqHeader.getUdf5Code()%></td>
			</tsa:tr>
			<tsa:tr field="req-priority">
              <td nowrap align=right><b>
              <tsa:label labelName="req-priority" defaultString="Requisition Priority" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap width=100%><%=reqHeader.getPriorityCode()%></td>
			</tsa:tr>
			<tsa:tr field="req-bidWaiver">
              <td nowrap align=right><b>
              <tsa:label labelName="req-bidWaiver" defaultString="Bid Waiver" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap width=100%><%=reqHeader.getBidWaiver()%></td>
			</tsa:tr>
			<tsa:tr field="req-RQ09">
              <td nowrap align=right><b>
              <tsa:label labelName="req-RQ09" defaultString="Requisition UDF 9" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap width=100%><%=reqHeader.getUdf9Code()%></td>
			</tsa:tr>
			<tsa:tr field="req-RQ11">
              <td nowrap align=right><b>
              <tsa:label labelName="req-RQ11" defaultString="Requisition UDF 11" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap width=100%><%=reqHeader.getUdf11Code()%></td>
			</tsa:tr>
			<tsa:tr field="req-RQ13">
              <td nowrap align=right><b>
              <tsa:label labelName="req-RQ13" defaultString="Requisition UDF 13" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap width=100%><%=reqHeader.getUdf13Code()%></td>
			</tsa:tr>
			<tsa:tr field="req-RQ15">
				<td nowrap align="right"><b>
				<tsa:label labelName="req-RQ15" defaultString="Requisition UDF 15" checkRequired="false"/>:</b>&nbsp;</td>
				<td nowrap width=100%><%=reqHeader.getUdf15Code()%></td>
			</tsa:tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
      <td width=50% align=center valign=top>
        <table border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdrDk>
        <tr>
          <td class=browseRow>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tsa:tr field="req-RQ01">
            	<td nowrap align=right><b>
            	<tsa:label labelName="req-RQ01" defaultString="Requisition UDF 1" checkRequired="false"/>:</b>&nbsp;</td>
            	<td nowrap width=100%><%=reqHeader.getUdf1Code()%></td>
            </tsa:tr>
            <tsa:tr field="req-RQ02">
              <td nowrap align=right><b>
              <tsa:label labelName="req-RQ02" defaultString="Requisition UDF 2" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap width=100%><%=reqHeader.getUdf2Code()%></td>
			</tsa:tr>
			<tsa:tr field="req-RQ03">
              <td nowrap align=right><b>
              <tsa:label labelName="req-RQ03" defaultString="Requisition UDF 3" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap width=100%><%=reqHeader.getUdf3Code()%></td>
			</tsa:tr>
			<tsa:tr field="req-gfpGfm">
              <td nowrap align=right><b>
              <tsa:label labelName="req-gfpGfm" defaultString="Requisition GFPGFM" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap width=100%><%=reqHeader.getGfpGfm()%></td>
			</tsa:tr>
			<tsa:tr field="req-RQ06">
              <td nowrap align=right><b>
              <tsa:label labelName="req-RQ06" defaultString="Requisition UDF 6" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap width=100%><%=reqHeader.getUdf6Code()%></td>
			</tsa:tr>
			<tsa:tr field="req-RQ07">
              <td nowrap align=right><b>
              <tsa:label labelName="req-RQ07" defaultString="Requisition UDF 7" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap width=100%><%=reqHeader.getUdf7Code()%></td>
			</tsa:tr>
			<tsa:tr field="req-RQ08">
              <td nowrap align=right><b>
              <tsa:label labelName="req-RQ08" defaultString="Requisition UDF 8" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap width=100%><%=reqHeader.getUdf8Code()%></td>
			</tsa:tr>
			<tsa:tr field="req-RQ14">
				<td nowrap align=right><b>
				<tsa:label labelName="req-RQ14" defaultString="Requisition UDF 14" checkRequired="false"/>:</b>&nbsp;</td>
				<td nowrap width=100%><%=reqHeader.getUdf14Code()%></td>
			</tsa:tr>

            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>

	<br>
	<br>