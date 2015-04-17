<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>\

<%@ page import="java.util.*"%>
<%@ page import="com.tsagate.foundation.utility.PdfUtil"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PDF Submit Data</title>
</head>
<body>
<%
// HttpSession sessionId = request.getSession() ;
String oid = request.getParameter("oid") ;

Map mp = new Hashtable() ;
Enumeration en = request.getParameterNames() ;
while (en.hasMoreElements())
{
	String paramName = (String) en.nextElement();
	String paramValue = request.getParameter(paramName) ;
	mp.put(paramName, paramValue) ;
};


String fileName = request.getHeader("Referer") ;
int pos = fileName.lastIndexOf("/") + 1 ;
fileName = fileName.substring(pos) ;

PdfUtil fdf = new PdfUtil(oid) ;
String fdfname = fdf.fdfSave(fileName, mp) ;

%>
</body>
</html>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

window.open('','_self','');

window.close() ;

// end hiding contents -->
</SCRIPT>


