package com.coda.www.efinance.schemas.input.input_11_2.webservice;

public class InputServicePortTypesProxy implements com.coda.www.efinance.schemas.input.input_11_2.webservice.InputServicePortTypes {
  private String _endpoint = null;
  private com.coda.www.efinance.schemas.input.input_11_2.webservice.InputServicePortTypes inputServicePortTypes = null;
  
  public InputServicePortTypesProxy() {
    _initInputServicePortTypesProxy();
  }
  
  public InputServicePortTypesProxy(String endpoint) {
    _endpoint = endpoint;
    _initInputServicePortTypesProxy();
  }
  
  private void _initInputServicePortTypesProxy() {
    try {
      inputServicePortTypes = (new com.coda.www.efinance.schemas.input.input_11_2.webservice.InputServiceLocator()).getInputServicePort();
      if (inputServicePortTypes != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)inputServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)inputServicePortTypes)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (inputServicePortTypes != null)
      ((javax.xml.rpc.Stub)inputServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.coda.www.efinance.schemas.input.input_11_2.webservice.InputServicePortTypes getInputServicePortTypes() {
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes;
  }
  
  public com.coda.www.efinance.schemas.input.input_11_2.webservice.PostToIntrayResponse postToIntray(com.coda.www.efinance.schemas.input.input_11_2.webservice.PostToIntrayRequest postToIntrayRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.postToIntray(postToIntrayRequest);
  }
  
  public com.coda.www.efinance.schemas.input.input_11_2.webservice.PostResponse post(com.coda.www.efinance.schemas.input.input_11_2.webservice.PostRequest postRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.post(postRequest);
  }
  
  public com.coda.www.efinance.schemas.input.input_11_2.webservice.PostToBooksResponse postToBooks(com.coda.www.efinance.schemas.input.input_11_2.webservice.PostToBooksRequest postToBooksRequest) throws java.rmi.RemoteException{
    if (inputServicePortTypes == null)
      _initInputServicePortTypesProxy();
    return inputServicePortTypes.postToBooks(postToBooksRequest);
  }
  
  
}