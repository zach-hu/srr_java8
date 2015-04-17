
	<table border=3 cellpadding=2 cellspacing=0 width="650px" >
 	<%
	if (entityAttribute!=null)
	{
 	int e=0;
	for (int h=0 ;e<entityAttribute.length;h++)
	 {
		%><tr><%
	    for (int r=0 ;r<4 && e<entityAttribute.length;r++)
	    {%>
	 	    <td align=left><input type=checkbox name="ckboxEntityName" value="<%=entityAttribute[e].getName()%>"><%=entityAttribute[e].getName()%></td>
	    <%e++;}
		%><tr><%
	 }

	}
	%>
	</table>
