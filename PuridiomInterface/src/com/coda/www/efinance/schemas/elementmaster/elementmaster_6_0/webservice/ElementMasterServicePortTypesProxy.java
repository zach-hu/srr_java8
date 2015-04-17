package com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice;

public class ElementMasterServicePortTypesProxy implements com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMasterServicePortTypes {
  private String _endpoint = null;
  private com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMasterServicePortTypes elementMasterServicePortTypes = null;
  
  public ElementMasterServicePortTypesProxy() {
    _initElementMasterServicePortTypesProxy();
  }
  
  public ElementMasterServicePortTypesProxy(String endpoint) {
    _endpoint = endpoint;
    _initElementMasterServicePortTypesProxy();
  }
  
  private void _initElementMasterServicePortTypesProxy() {
    try {
      elementMasterServicePortTypes = (new com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMasterServiceLocator()).getElementMasterServicePort();
      if (elementMasterServicePortTypes != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)elementMasterServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)elementMasterServicePortTypes)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (elementMasterServicePortTypes != null)
      ((javax.xml.rpc.Stub)elementMasterServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ElementMasterServicePortTypes getElementMasterServicePortTypes() {
    if (elementMasterServicePortTypes == null)
      _initElementMasterServicePortTypesProxy();
    return elementMasterServicePortTypes;
  }
  
  public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetAddressesResponse getAddresses(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetAddressesRequest getAddressesRequest) throws java.rmi.RemoteException{
    if (elementMasterServicePortTypes == null)
      _initElementMasterServicePortTypesProxy();
    return elementMasterServicePortTypes.getAddresses(getAddressesRequest);
  }
  
  public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListPunchoutItemCodesResponse listPunchoutItemCodes(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListPunchoutItemCodesRequest listPunchoutItemCodesRequest) throws java.rmi.RemoteException{
    if (elementMasterServicePortTypes == null)
      _initElementMasterServicePortTypesProxy();
    return elementMasterServicePortTypes.listPunchoutItemCodes(listPunchoutItemCodesRequest);
  }
  
  public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListResponse list(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.ListRequest listRequest) throws java.rmi.RemoteException{
    if (elementMasterServicePortTypes == null)
      _initElementMasterServicePortTypesProxy();
    return elementMasterServicePortTypes.list(listRequest);
  }
  
  public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetBanksResponse getBanks(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetBanksRequest getBanksRequest) throws java.rmi.RemoteException{
    if (elementMasterServicePortTypes == null)
      _initElementMasterServicePortTypesProxy();
    return elementMasterServicePortTypes.getBanks(getBanksRequest);
  }
  
  public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddResponse add(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.AddRequest addRequest) throws java.rmi.RemoteException{
    if (elementMasterServicePortTypes == null)
      _initElementMasterServicePortTypesProxy();
    return elementMasterServicePortTypes.add(addRequest);
  }
  
  public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetResponse get(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.GetRequest getRequest) throws java.rmi.RemoteException{
    if (elementMasterServicePortTypes == null)
      _initElementMasterServicePortTypesProxy();
    return elementMasterServicePortTypes.get(getRequest);
  }
  
  public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateResponse update(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.UpdateRequest updateRequest) throws java.rmi.RemoteException{
    if (elementMasterServicePortTypes == null)
      _initElementMasterServicePortTypesProxy();
    return elementMasterServicePortTypes.update(updateRequest);
  }
  
  public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchResponse fetch(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.FetchRequest fetchRequest) throws java.rmi.RemoteException{
    if (elementMasterServicePortTypes == null)
      _initElementMasterServicePortTypesProxy();
    return elementMasterServicePortTypes.fetch(fetchRequest);
  }
  
  public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetResponse set(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.SetRequest setRequest) throws java.rmi.RemoteException{
    if (elementMasterServicePortTypes == null)
      _initElementMasterServicePortTypesProxy();
    return elementMasterServicePortTypes.set(setRequest);
  }
  
  public com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.DeleteResponse delete(com.coda.www.efinance.schemas.elementmaster.elementmaster_6_0.webservice.DeleteRequest deleteRequest) throws java.rmi.RemoteException{
    if (elementMasterServicePortTypes == null)
      _initElementMasterServicePortTypesProxy();
    return elementMasterServicePortTypes.delete(deleteRequest);
  }
  
  
}