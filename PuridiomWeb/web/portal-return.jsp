<%

String  returnUrl = (String) request.getAttribute("ReturnURL") ;
System.out.println("Redirecting to " + returnUrl) ;
response.sendRedirect(returnUrl);

%>
