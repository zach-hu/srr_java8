package iseries.wsbeans.updatepolinetomiscwo;

public class UpdatePOLineToMiscWOPortTypeProxy implements iseries.wsbeans.updatepolinetomiscwo.UpdatePOLineToMiscWOPortType {
  private String _endpoint = null;
  private iseries.wsbeans.updatepolinetomiscwo.UpdatePOLineToMiscWOPortType updatePOLineToMiscWOPortType = null;
  
  public UpdatePOLineToMiscWOPortTypeProxy() {
    _initUpdatePOLineToMiscWOPortTypeProxy();
  }
  
  public UpdatePOLineToMiscWOPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initUpdatePOLineToMiscWOPortTypeProxy();
  }
  
  private void _initUpdatePOLineToMiscWOPortTypeProxy() {
    try {
      updatePOLineToMiscWOPortType = (new iseries.wsbeans.updatepolinetomiscwo.UpdatePOLineToMiscWOLocator()).getUpdatePOLineToMiscWOSOAP11port_http();
      if (updatePOLineToMiscWOPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)updatePOLineToMiscWOPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)updatePOLineToMiscWOPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (updatePOLineToMiscWOPortType != null)
      ((javax.xml.rpc.Stub)updatePOLineToMiscWOPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public iseries.wsbeans.updatepolinetomiscwo.UpdatePOLineToMiscWOPortType getUpdatePOLineToMiscWOPortType() {
    if (updatePOLineToMiscWOPortType == null)
      _initUpdatePOLineToMiscWOPortTypeProxy();
    return updatePOLineToMiscWOPortType;
  }
  
  public java.lang.String poi951_XML(iseries.wsbeans.updatepolinetomiscwo.xsd.POI951Input param0) throws java.rmi.RemoteException{
    if (updatePOLineToMiscWOPortType == null)
      _initUpdatePOLineToMiscWOPortTypeProxy();
    return updatePOLineToMiscWOPortType.poi951_XML(param0);
  }
  
  public iseries.wsbeans.updatepolinetomiscwo.xsd.POI951Result poi951(iseries.wsbeans.updatepolinetomiscwo.xsd.POI951Input param0) throws java.rmi.RemoteException{
    if (updatePOLineToMiscWOPortType == null)
      _initUpdatePOLineToMiscWOPortTypeProxy();
    return updatePOLineToMiscWOPortType.poi951(param0);
  }
  
  
}