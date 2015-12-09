package dataserviceimpl;

import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataservice.ReceiveDataService;
import enums.DocumentCondition;
import enums.ResultMessage;
import link.Helper;
import po.BillPO;
import po.DeliverPO;
import po.GoodsPO;
import po.OrderPO;
import po.ReceiverPO;
import po.SenderPO;

public class ReceiveDataImpl extends UnicastRemoteObject implements ReceiveDataService {

	private ReceiveDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public OrderPO find(String id) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select*from orderpo where ordernumber='"+id+"';";
		ResultSet result = null;
		OrderPO po = null;
		ReceiverPO receiver = null;
		SenderPO sender = null;
		BillPO bill = null;
		GoodsPO goods = null;

		try{
			result = Helper.find(sql);
			if(result.next()){

				//ObjectInputStream oips = new ObjectInputStream(result.getBinaryStream("receiver"));  
				receiver = (ReceiverPO)IOObject.getArray(result.getBytes("receiver"));
				//oips = new ObjectInputStream(result.getBinaryStream("sender"));  
				sender = (SenderPO)IOObject.getArray(result.getBytes("sender"));
				//oips = new ObjectInputStream(result.getBinaryStream("bill"));  
				bill = (BillPO)IOObject.getArray(result.getBytes("bill"));
				//oips = new ObjectInputStream(result.getBinaryStream("goods"));  
				goods = (GoodsPO)IOObject.getArray(result.getBytes("goods"));
				po = new OrderPO(receiver,sender,bill,goods,result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getString(8),DocumentCondition.valueOf(result.getString(9)));
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return po;
	}

	@Override
	public ArrayList<OrderPO> findsO(String field, String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage insertO(OrderPO order) throws Exception {
		String sql = "insert into orderpo values(?,?,?,?,"+order.getTimeOfSend()+"','"+order.getDueOfReceive()+"','"+order.getOrdernumber()+"','"+order.getNameOfCourier()+"','"+order.getReceiver()+"','"+order.getdCondition()+"');";
		try{
			Helper.pStatement = Helper.conn.prepareStatement(sql);
			Helper.pStatement.setObject(1,IOObject.toByteArray(order.getReceiver()));
			Helper.pStatement.setObject(2,IOObject.toByteArray(order.getSender()));
			Helper.pStatement.setObject(3,IOObject.toByteArray(order.getBill()));
			Helper.pStatement.setObject(4,IOObject.toByteArray(order.getGoods()));
			Helper.pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAIL;
		}

		return ResultMessage.SUCCESS;

	}

	@Override
	public ResultMessage deleteO(OrderPO po) throws Exception {
		// TODO Auto-generated method stub
		String sql = "delete from orderpo where ordernumber='"+po.getOrdernumber()+"';";
		return Helper.delete(sql);
	}

	@Override
	public ResultMessage updateO(OrderPO po) throws Exception {
		// TODO Auto-generated method stub
		ResultMessage result = deleteO(po);
		if(result==ResultMessage.FAIL)
			return result;
		return insertO(po);

	}

	@Override
	public DeliverPO findD(String id) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select*from deliverpo where id='"+id+"'";
		DeliverPO po = null;
		ResultSet result = null;
		ArrayList<String>member;
		ArrayList<String>order;

		try{
			result = Helper.find(sql);
			if(result.next()){
				//ObjectInputStream oips = new ObjectInputStream(result.getBinaryStream("member"));  
				member = (ArrayList<String>)IOObject.getArray(result.getBytes("member"));
				//oips = new ObjectInputStream(IOObjectresult.getBinaryStream("order"));  
				order = (ArrayList<String>)IOObject.getArray(result.getBytes("order"));
				po = new DeliverPO(result.getString(0),result.getString(1),result.getString(2),member,order,DocumentCondition.valueOf(result.getString(5)));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return po;
	}

	@Override
	public ArrayList<DeliverPO> findsD(String field, String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage insertD(DeliverPO po) throws Exception {
		String sql = "insert into deliverpo values('"+po.getID()+"','"+po.getWriter()+"','"+po.getTime()+"',?,?,'"+"','"+po.getDocumentCondition()+"');";
		try{
			Helper.pStatement = Helper.conn.prepareStatement(sql);
			Helper.pStatement.setObject(1,IOObject.toByteArray(po.getMember()));
			Helper.pStatement.setObject(2,IOObject.toByteArray(po.getOrder()));
			Helper.pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAIL;
		}
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}


	@Override
	public ArrayList<DeliverPO> findDWithdCondition(String nameOfWriter, DocumentCondition dCondition) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select*from deliverpo where documentcondition='"+dCondition+"' and nameOfWriter='"+nameOfWriter+"';";
		DeliverPO po = null;
		ResultSet result = null;
		ArrayList<String>member;
		ArrayList<String>order;
		ArrayList<DeliverPO>pos = new ArrayList<DeliverPO>();

		try{
			result = Helper.find(sql);
			while(result.next()){
				//ObjectInputStream oips = new ObjectInputStream(result.getBinaryStream("member"));  
				member = (ArrayList<String>)IOObject.getArray(result.getBytes("member"));
				//oips = new ObjectInputStream(result.getBinaryStream("order"));  
				order = (ArrayList<String>)IOObject.getArray(result.getBytes("order"));
				po = new DeliverPO(result.getString(0),result.getString(1),result.getString(2),member,order,DocumentCondition.valueOf(result.getString(5)));
				pos.add(po);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return pos;
	}

	@Override
	public ResultMessage deleteD(DeliverPO po) throws Exception {
		String sql = "delete from deliverpo where id='"+po.getID()+"';";
		// TODO Auto-generated method stub
		return Helper.delete(sql);
	}

	@Override
	public ResultMessage updateD(DeliverPO po) throws Exception {
		ResultMessage result = deleteD(po);
		if(result == ResultMessage.FAIL)
			return result;
		// TODO Auto-generated method stub
		return insertD(po);
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void finish() throws Exception {
		// TODO Auto-generated method stub

	}
	public static ReceiveDataImpl create() throws RemoteException{
		if(receive == null){
			synchronized(ReceiveDataImpl.class){

				if(receive == null)
					receive = new ReceiveDataImpl();
			}
		}

		return receive;
	}

	private volatile static ReceiveDataImpl receive;  

}
