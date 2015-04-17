package com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice;

public class DocumentMasterServicePortTypesProxy implements com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.DocumentMasterServicePortTypes {
  private String _endpoint = null;
  private com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.DocumentMasterServicePortTypes documentMasterServicePortTypes = null;
  
  public DocumentMasterServicePortTypesProxy() {
    _initDocumentMasterServicePortTypesProxy();
  }
  
  public DocumentMasterServicePortTypesProxy(String endpoint) {
    _endpoint = endpoint;
    _initDocumentMasterServicePortTypesProxy();
  }
  
  private void _initDocumentMasterServicePortTypesProxy() {
    try {
      documentMasterServicePortTypes = (new com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.DocumentMasterServiceLocator()).getDocumentMasterServicePort();
      if (documentMasterServicePortTypes != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)documentMasterServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)documentMasterServicePortTypes)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (documentMasterServicePortTypes != null)
      ((javax.xml.rpc.Stub)documentMasterServicePortTypes)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.DocumentMasterServicePortTypes getDocumentMasterServicePortTypes() {
    if (documentMasterServicePortTypes == null)
      _initDocumentMasterServicePortTypesProxy();
    return documentMasterServicePortTypes;
  }
  
  public com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.GetNoListsResponse getNoLists(com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.GetNoListsRequest getNoListsRequest) throws java.rmi.RemoteException{
    if (documentMasterServicePortTypes == null)
      _initDocumentMasterServicePortTypesProxy();
    return documentMasterServicePortTypes.getNoLists(getNoListsRequest);
  }
  
  public com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.ListResponse list(com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.ListRequest listRequest) throws java.rmi.RemoteException{
    if (documentMasterServicePortTypes == null)
      _initDocumentMasterServicePortTypesProxy();
    return documentMasterServicePortTypes.list(listRequest);
  }
  
  public com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.AddResponse add(com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.AddRequest addRequest) throws java.rmi.RemoteException{
    if (documentMasterServicePortTypes == null)
      _initDocumentMasterServicePortTypesProxy();
    return documentMasterServicePortTypes.add(addRequest);
  }
  
  public com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.GetResponse get(com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.GetRequest getRequest) throws java.rmi.RemoteException{
    if (documentMasterServicePortTypes == null)
      _initDocumentMasterServicePortTypesProxy();
    return documentMasterServicePortTypes.get(getRequest);
  }
  
  public com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.UpdateResponse update(com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.UpdateRequest updateRequest) throws java.rmi.RemoteException{
    if (documentMasterServicePortTypes == null)
      _initDocumentMasterServicePortTypesProxy();
    return documentMasterServicePortTypes.update(updateRequest);
  }
  
  public com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.ReleaseReservationResponse releaseReservation(com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.ReleaseReservationRequest releaseReservationRequest) throws java.rmi.RemoteException{
    if (documentMasterServicePortTypes == null)
      _initDocumentMasterServicePortTypesProxy();
    return documentMasterServicePortTypes.releaseReservation(releaseReservationRequest);
  }
  
  public com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.ReserveResponse reserve(com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.ReserveRequest reserveRequest) throws java.rmi.RemoteException{
    if (documentMasterServicePortTypes == null)
      _initDocumentMasterServicePortTypesProxy();
    return documentMasterServicePortTypes.reserve(reserveRequest);
  }
  
  public com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.DeleteResponse delete(com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice.DeleteRequest deleteRequest) throws java.rmi.RemoteException{
    if (documentMasterServicePortTypes == null)
      _initDocumentMasterServicePortTypesProxy();
    return documentMasterServicePortTypes.delete(deleteRequest);
  }
  
  
}