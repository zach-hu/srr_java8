package iseries.wsbeans.updateembo;

public class UpdateEMBOPortTypeProxy implements iseries.wsbeans.updateembo.UpdateEMBOPortType {
  private String _endpoint = null;
  private iseries.wsbeans.updateembo.UpdateEMBOPortType updateEMBOPortType = null;
  
  public UpdateEMBOPortTypeProxy() {
    _initUpdateEMBOPortTypeProxy();
  }
  
  public UpdateEMBOPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initUpdateEMBOPortTypeProxy();
  }
  
  private void _initUpdateEMBOPortTypeProxy() {
    try {
      updateEMBOPortType = (new iseries.wsbeans.updateembo.UpdateEMBOLocator()).getUpdateEMBOSOAP11port_http();
      if (updateEMBOPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)updateEMBOPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)updateEMBOPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (updateEMBOPortType != null)
      ((javax.xml.rpc.Stub)updateEMBOPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public iseries.wsbeans.updateembo.UpdateEMBOPortType getUpdateEMBOPortType() {
    if (updateEMBOPortType == null)
      _initUpdateEMBOPortTypeProxy();
    return updateEMBOPortType;
  }
  
  public iseries.wsbeans.updateembo.xsd.POI960Result poi960(iseries.wsbeans.updateembo.xsd.POI960Input param0) throws java.rmi.RemoteException{
    if (updateEMBOPortType == null)
      _initUpdateEMBOPortTypeProxy();
    return updateEMBOPortType.poi960(param0);
  }
  
  public java.lang.String poi960_XML(iseries.wsbeans.updateembo.xsd.POI960Input param0) throws java.rmi.RemoteException{
    if (updateEMBOPortType == null)
      _initUpdateEMBOPortTypeProxy();
    return updateEMBOPortType.poi960_XML(param0);
  }
  
  
}