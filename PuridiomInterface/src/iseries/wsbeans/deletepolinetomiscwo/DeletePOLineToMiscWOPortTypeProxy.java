package iseries.wsbeans.deletepolinetomiscwo;

public class DeletePOLineToMiscWOPortTypeProxy implements iseries.wsbeans.deletepolinetomiscwo.DeletePOLineToMiscWOPortType {
  private String _endpoint = null;
  private iseries.wsbeans.deletepolinetomiscwo.DeletePOLineToMiscWOPortType deletePOLineToMiscWOPortType = null;
  
  public DeletePOLineToMiscWOPortTypeProxy() {
    _initDeletePOLineToMiscWOPortTypeProxy();
  }
  
  public DeletePOLineToMiscWOPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initDeletePOLineToMiscWOPortTypeProxy();
  }
  
  private void _initDeletePOLineToMiscWOPortTypeProxy() {
    try {
      deletePOLineToMiscWOPortType = (new iseries.wsbeans.deletepolinetomiscwo.DeletePOLineToMiscWOLocator()).getDeletePOLineToMiscWOSOAP11port_http();
      if (deletePOLineToMiscWOPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)deletePOLineToMiscWOPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)deletePOLineToMiscWOPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (deletePOLineToMiscWOPortType != null)
      ((javax.xml.rpc.Stub)deletePOLineToMiscWOPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public iseries.wsbeans.deletepolinetomiscwo.DeletePOLineToMiscWOPortType getDeletePOLineToMiscWOPortType() {
    if (deletePOLineToMiscWOPortType == null)
      _initDeletePOLineToMiscWOPortTypeProxy();
    return deletePOLineToMiscWOPortType;
  }
  
  public iseries.wsbeans.deletepolinetomiscwo.xsd.POI952Result poi952(iseries.wsbeans.deletepolinetomiscwo.xsd.POI952Input param0) throws java.rmi.RemoteException{
    if (deletePOLineToMiscWOPortType == null)
      _initDeletePOLineToMiscWOPortTypeProxy();
    return deletePOLineToMiscWOPortType.poi952(param0);
  }
  
  public java.lang.String poi952_XML(iseries.wsbeans.deletepolinetomiscwo.xsd.POI952Input param0) throws java.rmi.RemoteException{
    if (deletePOLineToMiscWOPortType == null)
      _initDeletePOLineToMiscWOPortTypeProxy();
    return deletePOLineToMiscWOPortType.poi952_XML(param0);
  }
  
  
}