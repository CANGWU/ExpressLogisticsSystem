package dataserviceimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.util.ArrayList;

import dataservice.IoputDataService;
import enums.Condition;
import enums.Ioput;
import enums.ResultMessage;
import link.Helper;
import po.IoputPO;

public class IoputDataImpl extends UnicastRemoteObject implements IoputDataService {

	private IoputDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public IoputPO find(String id) {
		// TODO Auto-generated method stub
		String sql = "select *from ioputpo where id='"+id+",;";
		IoputPO po = null;
		ResultSet result = null;
		try{
			result = Helper.find(sql);
			if(result.next())
				if(result.getString("ioput").equals("in"))
					po = new IoputPO(result.getString("id"),result.getString("inputdate"),result.getString("time"),result.getString("destination"),result.getString("position"),Ioput.valueOf(result.getString("ioput")));
				else po = new IoputPO(result.getString("id"),result.getString("outputdate"),result.getString("time"),result.getString("destination"),result.getString("transport"),result.getString("receiptid"),Ioput.valueOf(result.getString("ioput")),Condition.valueOf(result.getString("condition")));
		}catch (Exception e){
			e.printStackTrace();
		}
		return po;
	}

	
	@Override
	public ResultMessage insert(IoputPO po) {
		String sql1 = "insert into ioputpo(id,inputdate,time,position,destination,ioput) values('"+po.getID()+"','"+po.getInputDate()+"','"+po.getTime()+"','"+po.getPosition()+"','"+po.getDestination()+"',"+po.getIoput()+");";
		String sql2 = "insert into ioputpo(id,outputdate,time,destination,receiptid,ioput,condition) "
				+ "values('"+po.getID()+"','"+po.getInputDate()+"','"+po.getTime()+"','"+po.getDestination()+"',"+po.getReceiptID()+"','"+po.getIoput()+","+po.getCondition()+");";
		// TODO Auto-generated method stub
		if(po.getIoput()==Ioput.in)
		return	Helper.insert(sql1);
        return Helper.insert(sql2); 
	}

	@Override
	public ResultMessage delete(IoputPO PO) {
		String sql = "delete from ioputpo where id='"+PO.getId()+"';";
		return Helper.delete(sql);
	}

	@Override
	public ResultMessage update(IoputPO PO) {
		// TODO Auto-generated method stub
	    ResultMessage result = delete(PO);
	    if(result==ResultMessage.FAIL)
	    	return result;
	    return insert(PO);

	}

	  public static IoputDataImpl create() throws RemoteException{
			if(ioput == null){
				synchronized(IoputDataImpl.class){
			
			if(ioput == null)
			ioput = new IoputDataImpl();
				}
			}
				
			return ioput;
		}
		
	   private volatile static IoputDataImpl ioput;

	@Override
	public ArrayList<IoputPO> finds(String[] ids) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<IoputPO>pos = new ArrayList<IoputPO>();
		for(String id:ids){
			pos.add(find(id));
		}
		return pos;
	}

	@Override
	public ArrayList<IoputPO> findDate(String date) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
		

	@Override
	public ArrayList<IoputPO> findDates(String[] date) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<IoputPO> findTimes(String[] time) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}   
	   
}
