<%
  StringBuilder sb = new StringBuilder();
  sb.append("<UserInfo>");
    sb.append("<Organization>").append(request.getParameter("Organization")).append("</Organization>");
    sb.append("<ReturnURL>").append(request.getParameter("ReturnURL")).append("</ReturnURL>");
    sb.append("<ErrorURL>").append(request.getParameter("ErrorURL")).append("</ErrorURL>");
    sb.append("<LoginID>").append(request.getParameter("LoginID")).append("</LoginID>");
    sb.append("<FirstName>").append(request.getParameter("FirstName")).append("</FirstName>");
    sb.append("<LastName>").append(request.getParameter("LastName")).append("</LastName>");
    sb.append("<Email>").append(request.getParameter("Email")).append("</Email>");
    sb.append("<Title>").append(request.getParameter("Title")).append("</Title>");
    sb.append("<DivisionCode>").append(request.getParameter("DivisionCode")).append("</DivisionCode>");
    sb.append("<Location>").append(request.getParameter("Location")).append("</Location>");
    sb.append("<Status>");
    sb.append("  <Code>").append(request.getParameter("LoginStatusCode")).append("</Code>");
    sb.append("  <Desc>").append(request.getParameter("LoginStatusDesc")).append("</Desc>");
    sb.append("</Status>");
  sb.append("</UserInfo>");

  com.tsagate.foundation.security.tasks.utility.TSAServletRequestWrapper wrapper =
    new com.tsagate.foundation.security.tasks.utility.TSAServletRequestWrapper(request);
  wrapper.setContentType("text/xml");
  wrapper.setContent(sb.toString().getBytes());


  getServletContext().getRequestDispatcher("/sso_login.jsp").forward(wrapper, response);
%>
