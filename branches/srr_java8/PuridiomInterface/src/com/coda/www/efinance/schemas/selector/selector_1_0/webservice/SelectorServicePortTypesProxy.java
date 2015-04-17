package com.coda.www.efinance.schemas.selector.selector_1_0.webservice;

public class SelectorServicePortTypesProxy implements com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectorServicePortTypes {
  private String _endpoint = null;
  private com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectorServicePortTypes selectorServicePortTypes = null;
  
  public SelectorServicePortTypesProxy() {
    _initSelectorServicePortTypesProxy();
  }
  
  public SelectorServicePortTypesProxy(String endpoint) {
    _endpoint = endpoint;
    _initSelectorServicePortTypesProxy();
  }
  
  private void _initSelectorServicePortTypesProxy() {
    try {
      selectorServicePortTypes = (new com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectorServiceLocator()).getSelectorServicePort();
      if (selectorServicePortTypes != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)selectorServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)selectorServicePortTypes)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (selectorServicePortTypes != null)
      ((javax.xml.rpc.Stub)selectorServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectorServicePortTypes getSelectorServicePortTypes() {
    if (selectorServicePortTypes == null)
      _initSelectorServicePortTypesProxy();
    return selectorServicePortTypes;
  }
  
  public com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectResponse select(com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectRequest selectRequest) throws java.rmi.RemoteException{
    if (selectorServicePortTypes == null)
      _initSelectorServicePortTypesProxy();
    return selectorServicePortTypes.select(selectRequest);
  }
  
  public com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectDetailsResponse selectDetails(com.coda.www.efinance.schemas.selector.selector_1_0.webservice.SelectDetailsRequest selectDetailsRequest) throws java.rmi.RemoteException{
    if (selectorServicePortTypes == null)
      _initSelectorServicePortTypesProxy();
    return selectorServicePortTypes.selectDetails(selectDetailsRequest);
  }
  
  
}