package com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice;

public class ElementFinderServicePortTypesProxy implements com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.ElementFinderServicePortTypes {
  private String _endpoint = null;
  private com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.ElementFinderServicePortTypes elementFinderServicePortTypes = null;
  
  public ElementFinderServicePortTypesProxy() {
    _initElementFinderServicePortTypesProxy();
  }
  
  public ElementFinderServicePortTypesProxy(String endpoint) {
    _endpoint = endpoint;
    _initElementFinderServicePortTypesProxy();
  }
  
  private void _initElementFinderServicePortTypesProxy() {
    try {
      elementFinderServicePortTypes = (new com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.ElementFinderServiceLocator()).getElementFinderServicePort();
      if (elementFinderServicePortTypes != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)elementFinderServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)elementFinderServicePortTypes)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (elementFinderServicePortTypes != null)
      ((javax.xml.rpc.Stub)elementFinderServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.ElementFinderServicePortTypes getElementFinderServicePortTypes() {
    if (elementFinderServicePortTypes == null)
      _initElementFinderServicePortTypesProxy();
    return elementFinderServicePortTypes;
  }
  
  public com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.FilterResponse filter(com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.FilterRequest filterRequest) throws java.rmi.RemoteException{
    if (elementFinderServicePortTypes == null)
      _initElementFinderServicePortTypesProxy();
    return elementFinderServicePortTypes.filter(filterRequest);
  }
  
  public com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.FindResponse find(com.coda.www.efinance.schemas.elementmaster.elementfinder_11_2.webservice.FindRequest findRequest) throws java.rmi.RemoteException{
    if (elementFinderServicePortTypes == null)
      _initElementFinderServicePortTypesProxy();
    return elementFinderServicePortTypes.find(findRequest);
  }
  
  
}