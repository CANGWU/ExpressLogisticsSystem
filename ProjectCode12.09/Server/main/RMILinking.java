import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;

import dataservice.AManagementDataService;
import dataservice.CManagementDataService;
import dataservice.CheckDataService;
import dataservice.ConstantDataService;
import dataservice.DManagementDataService;
import dataservice.DataFactoryService;
import dataservice.FinanceDataService;
import dataservice.IoputDataService;
import dataservice.PManagementDataService;
import dataservice.ReceiveDataService;
import dataservice.SalaryStrategyDataService;
import dataservice.SendDataService;
import dataservice.StockDataService;
import dataservice.TransportDataService;
import dataservice.UserDataService;
import dataserviceimpl.DManagementDataImpl;
import dataserviceimpl.DataFactory;
import dataserviceimpl.PManagementDataImpl;

public class RMILinking {
	DataFactoryService dataFactory;
	PManagementDataService pm;
	DManagementDataService dm;
	CManagementDataService cm;
	AManagementDataService am;
	CheckDataService check;
	ConstantDataService constant;
	FinanceDataService finance;
	IoputDataService ioput;
	ReceiveDataService receive;
	SalaryStrategyDataService salary;
	SendDataService send;
	StockDataService stock;
	TransportDataService transport;
	UserDataService user;

	public RMILinking() throws RemoteException{
		dataFactory = new DataFactory();
		pm = dataFactory.getPManagementData();
		dm = dataFactory.getDManagementData();
		cm = dataFactory.getCManagementData();
		am = dataFactory.getAManagementData();
		check = dataFactory.getCheckData();
		constant = dataFactory.getConstantData();
		finance = dataFactory.getFinanceData();
		ioput = dataFactory.getIoputData();
		receive = dataFactory.getReceiveData();
		salary = dataFactory.getSalaryStrategyData();
		send = dataFactory.getSendData();
		stock = dataFactory.getStockDate();
		transport = dataFactory.getTransportDate();
		user = dataFactory.getUserData();

	}


	public void setRMI(){
		String URL = "localhost";

		try{
			RMISocketFactory.setSocketFactory(new SMRMISocket());
			LocateRegistry.createRegistry(1099); 
			Naming.bind("//"+URL+":1099/getPManagementDataService",pm);
			Naming.bind("//"+URL+":1099/getCManagementDataService",cm);
			Naming.bind("//"+URL+":1099/getAManagementDataService",am);
			Naming.bind("//"+URL+":1099/getDManagementDataService",dm);
			Naming.bind("//"+URL+":1099/getCheckDataService",check);
			Naming.bind("//"+URL+":1099/getConstantDataService",constant);
			Naming.bind("//"+URL+":1099/getFinanceDataService",finance);
			Naming.bind("//"+URL+":1099/getIoputDataService",ioput);
			Naming.bind("//"+URL+":1099/getTransportDataService",transport);
			Naming.bind("//"+URL+":1099/getReceiveDataService",receive);
			Naming.bind("//"+URL+":1099/getSalaryStrategyDataService",salary);
			Naming.bind("//"+URL+":1099/getSendDataService",send);
			Naming.bind("//"+URL+":1099/getStockDataService",stock);
			Naming.bind("//"+URL+":1099/getUserDataService",user);
			
			
			System.out.println("OK to bound the RMI Service");

		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}



	}


}
