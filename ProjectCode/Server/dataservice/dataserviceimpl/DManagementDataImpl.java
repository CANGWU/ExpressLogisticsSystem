package dataserviceimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataservice.DManagementDataService;
import enums.ResultMessage;
import enums.Sex;
import link.Helper;
import po.DriverPO;
import po.StaffPO;

public class DManagementDataImpl extends UnicastRemoteObject implements DManagementDataService {

	private DManagementDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public DriverPO find(String id){
		// TODO Auto-generated method stub
		ResultSet result = null;
		String sql = "SELECT*FROM staffpo WHERE workNumber= '" + id + "';";
		try {
	     result = Helper.run(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Object result = null;
		
		
		DriverPO driver = null;
		try {
			driver = new DriverPO(result.getString("name"),result.getString("position"),result.getString("workNumber"),
					result.getString("workPlaceNumber"),result.getString("birthDate"),result.getString("idNumber"),result.getString("phoneNumber"),
					result.getString("address"),Sex.values()[result.getInt("sex")],result.getInt("driverYear"),result.getInt("page"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}

	@Override
	public ArrayList<DriverPO> findAll() {
		// TODO Auto-generated method stub
		ArrayList<DriverPO>drivers = new ArrayList<DriverPO>();
		DriverPO driver = null;
		ResultSet result = null;
		String sql = "SELECT*FROM staffpo;";
		try {
	     result = Helper.run(sql);
			while(result.next()){
				driver = new DriverPO(result.getString("name"),result.getString("position"),result.getString("workNumber"),
								result.getString("workPlaceNumber"),result.getString("birthDate"),result.getString("idNumber"),result.getString("phoneNumber"),
								result.getString("address"),Sex.values()[result.getInt("sex")],result.getInt("driverYear"),result.getInt("page"));
				drivers.add(driver);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return drivers;
	}

	@Override
	public ResultMessage insert(DriverPO po) {
		// TODO Auto-generated method stub
       String sql = "INSERT INTO staffpo(name,position,workNumber,workPlaceNumber,birthDate,idNumber,phoneNumber,address,sex,driverYear,page)VALUES('"
		+po.getName()+"','"+po.getPosition()+"','"+po.getWorkNumber()+"','"+po.getWorkPlaceNumber()+"','"+po.getBrithDate()+"','"+po.getIDNumber()+"','"+po.getPhoneNumber()+
		"','"+po.getAddress()+"','"+po.getSex()+"',"+po.getDriverYear()+","+po.getPage()+");";
       try {
		Helper.run(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       return null;
	}

	@Override
	public ResultMessage delect(DriverPO po) {
		// TODO Auto-generated method stub	
		String sql = "DELETE FROM staffpo WHERE idNumber='"+po.getIDNumber()+"';";
		try {
			Helper.run(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage delect(String id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM staffpo WHERE workNumber='"+id+"';";
		try {
			Helper.run(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage update(DriverPO po) {
		// TODO Auto-generated method stub
		//UPDATE student SET sage = 20 WHERE Snum = '09032401' ; 
    delect(po.getWorkNumber());
    insert(po);
    return null;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

	public static DManagementDataImpl create() throws RemoteException{
		if(dm == null){
			synchronized(DManagementDataImpl.class){
		
		if(dm == null)
		dm = new DManagementDataImpl();
			}
		}
			
		return dm;
	}
	
   private volatile static DManagementDataImpl dm;
}
