package iseries.wsbeans.addpolinetomiscwo;

public class AddPOLineToMiscWOPortTypeProxy implements iseries.wsbeans.addpolinetomiscwo.AddPOLineToMiscWOPortType {
  private String _endpoint = null;
  private iseries.wsbeans.addpolinetomiscwo.AddPOLineToMiscWOPortType addPOLineToMiscWOPortType = null;
  
  public AddPOLineToMiscWOPortTypeProxy() {
    _initAddPOLineToMiscWOPortTypeProxy();
  }
  
  public AddPOLineToMiscWOPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initAddPOLineToMiscWOPortTypeProxy();
  }
  
  private void _initAddPOLineToMiscWOPortTypeProxy() {
    try {
      addPOLineToMiscWOPortType = (new iseries.wsbeans.addpolinetomiscwo.AddPOLineToMiscWOLocator()).getAddPOLineToMiscWOSOAP11port_http();
      if (addPOLineToMiscWOPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)addPOLineToMiscWOPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)addPOLineToMiscWOPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (addPOLineToMiscWOPortType != null)
      ((javax.xml.rpc.Stub)addPOLineToMiscWOPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public iseries.wsbeans.addpolinetomiscwo.AddPOLineToMiscWOPortType getAddPOLineToMiscWOPortType() {
    if (addPOLineToMiscWOPortType == null)
      _initAddPOLineToMiscWOPortTypeProxy();
    return addPOLineToMiscWOPortType;
  }
  
  public java.lang.String poi950_XML(iseries.wsbeans.addpolinetomiscwo.xsd.POI950Input param0) throws java.rmi.RemoteException{
    if (addPOLineToMiscWOPortType == null)
      _initAddPOLineToMiscWOPortTypeProxy();
    return addPOLineToMiscWOPortType.poi950_XML(param0);
  }
  
  public iseries.wsbeans.addpolinetomiscwo.xsd.POI950Result poi950(iseries.wsbeans.addpolinetomiscwo.xsd.POI950Input param0) throws java.rmi.RemoteException{
    if (addPOLineToMiscWOPortType == null)
      _initAddPOLineToMiscWOPortTypeProxy();
    return addPOLineToMiscWOPortType.poi950(param0);
  }
  
  
}