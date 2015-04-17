package com.tsagate.foundation.security.tasks.utility;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.ServletRequest;

public class SSOWebUtil {
  public static String read_content(ServletRequest request) throws Exception {
    int contentLength = request.getContentLength();
    java.io.InputStream is = request.getInputStream();
    StringBuilder sb = new StringBuilder();

    int tchar = 0;
    for (int i = 0; i < contentLength; i++) {
      tchar = is.read();
      sb.append((char) tchar);
    }
    return sb.toString();
  }

  private static String remove_value_enclosed_in(String xml, String startDelimiter, String endDelimiter) {
     if (xml == null) {
        return xml;
     }
     
     String rv = xml;
     int indexOfHeader = xml.indexOf(startDelimiter);
     if (indexOfHeader >= 0) {
        int indexOfHeaderTrailer = xml.indexOf(endDelimiter, indexOfHeader);
        if (indexOfHeaderTrailer >= 0) {
           if (xml.length() + 1 > indexOfHeaderTrailer + 1) {
              rv = xml.substring(indexOfHeaderTrailer + 1);
           }
        }
     }
     
     if (rv.indexOf(startDelimiter) >= 0) {
        rv = remove_value_enclosed_in(rv, startDelimiter, endDelimiter);
     }
     
     return rv;
  }
  
  private static String remove_xml_header(String xml) {
     return remove_value_enclosed_in(xml, "<?", "?>");
  }
  
  private static String remove_xml_comments(String xml) {
     return remove_value_enclosed_in(xml, "<!--", "-->");
  }
  
  public static Map<String, String> parse_xml(String nodeNamePrefix, String xml) throws Exception {
     Map<String, String> rv = new HashMap<String, String>();
     
     // remove any xml header and comments
     xml = remove_xml_header(xml);
     xml = remove_xml_comments(xml);
     
     // Now spin through and look for simple elements
     String[] fields = xml.split("[<>]");
     List<String> consolidatedFields = new ArrayList<String>();
     for (int i = 0; i < fields.length; i++) {
       if (fields[i] != null && fields[i].trim().length() != 0) {
         consolidatedFields.add(fields[i]);
       }
     }
     fields = consolidatedFields.toArray(new String[consolidatedFields.size()]);

     Stack<String> stack = new Stack<String>();
     for (int i = 0; i < fields.length; i++) {
       if (fields[i].endsWith("/")) {
          if (nodeNamePrefix == null) {
             rv.put(fields[i].substring(0, fields[i].length() - 1), null);
          }
          else {
             rv.put(nodeNamePrefix + fields[i].substring(0, fields[i].length() - 1), null);
          }
          continue;
       }
       else if (fields[i].startsWith("/")) {
         String val = stack.pop();
         if (!val.startsWith("/") && ! fields[i - 1].startsWith("/")) {
            if (nodeNamePrefix == null) {
               rv.put(fields[i].substring(1, fields[i].length()), fields[i - 1]);
            }
            else {
               rv.put(nodeNamePrefix + fields[i].substring(1, fields[i].length()), fields[i - 1]);
            }
         }
       }
       else {
         stack.push(fields[i]);
       }
     }  
     
     return rv;
  }
  
  public static Map<String, String> pluck_out_sso_parameters(Map map, String xml, String nodePrefix) throws Exception {
     Map<String, String> ps = parse_xml(nodePrefix, xml);
     for (Map.Entry<String, String> me : ps.entrySet()) {
       map.put(me.getKey(), me.getValue());
     }
     // Kind of a hack - but put this in to work with LDAP process(es)
     map.put("verifyLDAPAuthentication", "true");
     map.put("loginId", map.get(nodePrefix + "Email"));
     return ps;
  }
  
  public static void main(String[] args) {
     String xml = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?> " +
                  "<UserInfo> " +
                  "  <Status>" +
                  "    <Code>0</Code>" +
                  "    <Desc>Success</Desc>" +
                  "    <ErrorURL>0</ErrorURL>" +
                  "  </Status>" +
                  "  <Organization>QRI06P</Organization>" +
                  "  <ReturnURL>default.aspx</ReturnURL>" +
                  "  <UserType>EMPLOYEE</UserType>" +
                  "  <LoginID>srowland</LoginID>" +
                  "  <FirstName>Shane</FirstName>" +
                  "  <LastName>Rowland</LastName>" +
                  "  <Email>srowland@qrinc.com</Email>" +
                  "  <Title>Programmer Analyst IV</Title>" +
                  "  <CPhone></CPhone>" +
                  "  <BPhone>817-665-5586</BPhone>" +
                  "  <Location>801 Cherry Street</Location>" +
                  "  <City>Fort Worth</City>" +
                  "  <State>Texas</State>" +
                  "  <Zip>76102</Zip>" +
                  "  <Country>US</Country>" +
                  "</UserInfo>";
     Map<String, Object> parms = new HashMap<String, Object>();
     try {
        Map<String, String> vals = pluck_out_sso_parameters(parms, xml, "SSO_");
        System.out.println("....>" + vals);
     }
     catch (Throwable t) {
        t.printStackTrace();
     }
  }
}
