package dataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import po.CarPO;

public interface CManagementDataService extends Remote{
	
	public CarPO find(String id);
	public ArrayList<CarPO> findAll();
	public void insert(CarPO po);
	public void delect(CarPO po);
	public void delect(String id);
	public void update(CarPO po);
	public void init();
	public void finish();

}