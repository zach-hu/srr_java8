<%
com.tsagate.foundation.security.tasks.utility.TSAServletRequestWrapper
   rqstWrapper =
    new com.tsagate.foundation.security.tasks.utility.TSAServletRequestWrapper(request);

  String xml = com.tsagate.foundation.security.tasks.utility.SSOWebUtil.read_content(rqstWrapper);

  try {
    com.tsagate.foundation.security.tasks.utility.SSOWebUtil.pluck_out_sso_parameters(rqstWrapper.getParameterMap(), xml, "SSO_");
  }
  catch (Throwable t) {
    System.out.println("Error parsing XML: " + xml);
    throw t;
  }

  if (rqstWrapper.getParameter("SSO_Code") == null ||
      rqstWrapper.getParameter("SSO_Code").equals("0")) {
    rqstWrapper.setAttribute("organizationId", rqstWrapper.getParameter("SSO_Organization"));
    rqstWrapper.setAttribute("loginId", rqstWrapper.getParameter("SSO_LoginID"));
    rqstWrapper.setAttribute("loginPwd", "ZXIW6Q");
    rqstWrapper.setAttribute("mailId", rqstWrapper.getParameter("SSO_Email"));
    rqstWrapper.setAttribute("verifyLDAPAuthentication", "true");
    getServletContext().getRequestDispatcher("/index.jsp").forward(rqstWrapper, response);
  }
  else {
    if (rqstWrapper.getParameter("SSO_ErrorURL") == null) {
%>
<HTML>
    The supplied credentials were not valid. [code: <%=rqstWrapper.getParameter("SSO_Code")%>]
</HTML>
<%
    }
    else {
      response.sendRedirect((String) rqstWrapper.getParameter("SSO_ErrorURL"));
    }
  }
%>

