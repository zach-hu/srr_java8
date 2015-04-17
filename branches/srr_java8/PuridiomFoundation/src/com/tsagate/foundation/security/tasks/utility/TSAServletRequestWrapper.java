package com.tsagate.foundation.security.tasks.utility;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import javax.servlet.ServletInputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class TSAServletRequestWrapper extends HttpServletRequestWrapper { 
  private class StringListEnumeration implements Enumeration {
    private int idx = 0;
    private List<String> names;
 
    public StringListEnumeration(List<String> names) {
      super();
      this.names = names;
    }
    
    public boolean hasMoreElements() {
      return (idx < names.size());
    }

    public String nextElement() {
      String rv = names.get(idx);
      idx++;
      return rv;
    }
  }

  private class BufferedServletInputStream extends ServletInputStream {
    ByteArrayInputStream bais;

    public BufferedServletInputStream(ByteArrayInputStream bais) {
      this.bais = bais;
    }

    public int available() {
      return bais.available();
    }

    public int read() {
      return bais.read();
    }

    public int read(byte[] buf,int off,int len) {
      return bais.read(buf,off,len);
    }
  }

  private HttpServletRequest rqst;
  private String type;
  private ServletInputStream is;
  private int contentLength = -1;
  private Map<Object, Object> parms = new HashMap<Object, Object>();
  private List<String> parmNames = new ArrayList<String>();

  public static HttpServletRequest buildWrapper(HttpServletRequest rqst) {
    String xml = get_content(rqst);
    TSAServletRequestWrapper wrapper = new TSAServletRequestWrapper(rqst);
    wrapper.setContent(xml.getBytes());
    return wrapper;
  }

  private TSAServletRequestWrapper(HttpServletRequest rqst) {
    super(rqst);
    this.rqst = rqst;
    Enumeration e = rqst.getParameterNames();
    Object key = null;
    while (e.hasMoreElements()) {
      key = e.nextElement();
      parms.put(key, rqst.getParameterMap().get(key));
    }
  }

  private static String get_content(HttpServletRequest rqst) {
    StringBuilder sb = new StringBuilder();
    try {
      InputStream is = rqst.getInputStream();
      is.available();
      int contentLength = rqst.getContentLength();

      int tchar = 0;

      for (int i = 0; i < contentLength; i++) {
        tchar = is.read();
        sb.append((char) tchar);
      }
    }
    catch (Throwable t) {
      t.printStackTrace();
    }
    return sb.toString();
  }

  public void setParameterMap(Map<String, String> parms) {
    for (Map.Entry<String, String> me : parms.entrySet()) {
      parms.put(me.getKey(), me.getValue()); 
    }
  }

  public java.util.Enumeration getParameterNames() {
    if (parmNames.size() == 0 || parmNames.size() != parms.size()) {
      for (Object name : parms.keySet()) {
        if (!parmNames.contains((String) name)) {
          parmNames.add((String) name);
        }
      }
    }
    return new StringListEnumeration(parmNames);
  }

  public String getParameter(String name) {
    return (String) getParameterMap().get(name);
  }

  public Map getParameterMap() {
    return parms;
  }

  public void setContentType(String type) {
    this.type = type;
  }

  public int getContentLength() {
    if (contentLength < 0) {
      return super.getContentLength();
    }
    return contentLength;   
  }

  public String getContentType() {
    return type;
  }

  public void setContent(byte[] content) {
    contentLength = content.length;
    BufferedServletInputStream is = new BufferedServletInputStream(new ByteArrayInputStream(content));

    this.is = is; 
  }

  public ServletInputStream getInputStream() throws IOException {
    if (is == null) {
      return super.getInputStream();
    }
    return is;
  }
}

