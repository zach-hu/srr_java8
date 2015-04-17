<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	List list = (List)request.getAttribute("queryResult");
%>

<br>
<%if(list != null)
{%>
    <table >
    	<%for(int i = 0; i < list.size(); i++)
		{
			Object obj[] = (Object[])list.get(i);%>
			<tr >
				<%for(int j = 0; j < obj.length; j++)
				{%>
					<td ><%=obj[j]%></td>
				<%}%>
			</tr>
		<%}%>
    </table>
<%}
else
{
	out.println("list is null");
}%>
<br>
<br>
<a href="javascript: doSubmit('temp/servlet_test.jsp','DoNothing'); void(0);">RETURN TO SERVLET TEST</a>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	
// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>
