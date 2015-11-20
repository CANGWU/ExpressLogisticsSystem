package dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataFactoryService extends Remote{
          
	   public CheckDataService getCheckData() ;
	   
	   public FinanceDataService getFinanceData();
	   
	   public SendDataService getSendData();
	   
	   public UserDataService getUserData();
	   
	   public AManagementDataService getAManagementData();
	   
	   public PManagementDataService getPManagementData() throws RemoteException;
	   
	   public DManagementDataService getDManagementData();
	   
	   public CManagementDataService getCManagementData();
	   
	     
	   
}