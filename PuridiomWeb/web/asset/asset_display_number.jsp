<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%pageContext.setAttribute("oid", oid); %>
<!--In this part we get the itemnumber and description of asset-->
<table border="0" cellspacing="0" cellpadding="1" width="100%">
  		<tsa:tr>
      		
      		<tsa:td noWrap="nowrap" align="right"><b><tsa:label labelName="asset-itemnumber" defaultString="Item Number" />:</b></tsa:td> <tsa:td width="100px"><%=headerEncoder.encodeForHTML(s_item_number)%></tsa:td>
		</tsa:tr>
		<tsa:tr>
      		
      		<tsa:td noWrap="nowrap" align="right"><b><tsa:label labelName="asset-description" defaultString="Description" />:</b></tsa:td> <tsa:td><%=headerEncoder.encodeForHTML(s_description)%></tsa:td>
    	</tsa:tr>
 </table>
