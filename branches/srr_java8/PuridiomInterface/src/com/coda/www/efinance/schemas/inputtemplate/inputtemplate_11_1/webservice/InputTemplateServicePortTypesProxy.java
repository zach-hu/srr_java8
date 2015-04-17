package com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice;

public class InputTemplateServicePortTypesProxy implements com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.InputTemplateServicePortTypes {
  private String _endpoint = null;
  private com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.InputTemplateServicePortTypes inputTemplateServicePortTypes = null;
  
  public InputTemplateServicePortTypesProxy() {
    _initInputTemplateServicePortTypesProxy();
  }
  
  public InputTemplateServicePortTypesProxy(String endpoint) {
    _endpoint = endpoint;
    _initInputTemplateServicePortTypesProxy();
  }
  
  private void _initInputTemplateServicePortTypesProxy() {
    try {
      inputTemplateServicePortTypes = (new com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.InputTemplateServiceLocator()).getInputTemplateServicePort();
      if (inputTemplateServicePortTypes != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)inputTemplateServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)inputTemplateServicePortTypes)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (inputTemplateServicePortTypes != null)
      ((javax.xml.rpc.Stub)inputTemplateServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.InputTemplateServicePortTypes getInputTemplateServicePortTypes() {
    if (inputTemplateServicePortTypes == null)
      _initInputTemplateServicePortTypesProxy();
    return inputTemplateServicePortTypes;
  }
  
  public com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.ListResponse list(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.ListRequest listRequest) throws java.rmi.RemoteException{
    if (inputTemplateServicePortTypes == null)
      _initInputTemplateServicePortTypesProxy();
    return inputTemplateServicePortTypes.list(listRequest);
  }
  
  public com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddResponse add(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddRequest addRequest) throws java.rmi.RemoteException{
    if (inputTemplateServicePortTypes == null)
      _initInputTemplateServicePortTypesProxy();
    return inputTemplateServicePortTypes.add(addRequest);
  }
  
  public com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.UpdateResponse update(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.UpdateRequest updateRequest) throws java.rmi.RemoteException{
    if (inputTemplateServicePortTypes == null)
      _initInputTemplateServicePortTypesProxy();
    return inputTemplateServicePortTypes.update(updateRequest);
  }
  
  public com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.GetResponse get(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.GetRequest getRequest) throws java.rmi.RemoteException{
    if (inputTemplateServicePortTypes == null)
      _initInputTemplateServicePortTypesProxy();
    return inputTemplateServicePortTypes.get(getRequest);
  }
  
  public com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.DeleteResponse delete(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.DeleteRequest deleteRequest) throws java.rmi.RemoteException{
    if (inputTemplateServicePortTypes == null)
      _initInputTemplateServicePortTypesProxy();
    return inputTemplateServicePortTypes.delete(deleteRequest);
  }
  
  
}