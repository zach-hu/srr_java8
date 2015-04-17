<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>

<%

String str = "<b>BLAH</b><script>alert('BLAH');</script>";

pageContext.setAttribute("str", str);

%>
<html>
<body>
<tsa:test input="<%=str %>"/>
<p>
NULL TEST: ${thisisnull}
<p>
NULL BEAN TEST: ${thisisnull.nullprop}
<p>
<tsa:hidden name="blah" value="<%= str%>"/>
</body>
</html>