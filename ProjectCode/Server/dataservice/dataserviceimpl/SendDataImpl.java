package dataserviceimpl;

import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataservice.SendDataService;
import enums.DocumentCondition;
import enums.ResultMessage;
import link.Helper;
import po.BillPO;
import po.GoodsPO;
import po.OrderPO;
import po.ReceiptsPO;
import po.ReceiverPO;
import po.SenderPO;

public class SendDataImpl extends UnicastRemoteObject implements SendDataService {

	@Override
	public ResultMessage insertOrderPO(OrderPO order) {
		// TODO Auto-generated method stub
		String sql = "insert into orderpo values(?,?,?,?"+order.getTimeOfSend()+"','"+order.getDueOfReceive()+"','"+order.getOrdernumber()+"','"+order.getNameOfCourier()+"','"+order.getReceiver()+"','"+order.getdCondition()+"');";
		try{
			Helper.pStatement = Helper.conn.prepareStatement(sql);
			Helper.pStatement.setObject(0,order.getReceiver());
			Helper.pStatement.setObject(1,order.getSender());
			Helper.pStatement.setObject(2,order.getSender());
			Helper.pStatement.setObject(3,order.getGoods());
			Helper.pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAIL;
		}

		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage insertReceiptsPO(ReceiptsPO receipts) {
		// TODO Auto-generated method stub
		String sql = "insert into receiptspo values('"+receipts.getData()+"',"+receipts.getFee()+",'"+receipts.getCourier()+"',?);";
		try{
			Helper.pStatement = Helper.conn.prepareStatement(sql);
			Helper.pStatement.setObject(3,receipts.getOrdernumbers());
			Helper.pStatement.executeUpdate();
	}catch (Exception e){
		e.printStackTrace();
		return ResultMessage.FAIL;
	}
		return ResultMessage.SUCCESS;
	}
	
	private SendDataImpl() throws RemoteException{
		super();
	}

	
	public static SendDataImpl create() throws RemoteException{
			if(send == null){
				synchronized(SendDataImpl.class){
			
			if(send == null)
			send = new SendDataImpl();
				}
			}
				
			return send;
		}
		
	   private volatile static SendDataImpl send;

	@Override
	public ArrayList<OrderPO> findWithdCondition(String nameOfCourier, DocumentCondition dCondition) throws RemoteException {
		// TODO Auto-generated method stub
		String sql = "select* from orderpo where documentcondition='"+dCondition+"' and name='"+nameOfCourier+"';";
		ResultSet result = null;
		OrderPO po =null;
		ArrayList<OrderPO>pos = new ArrayList<OrderPO>();
		ReceiverPO receiver = null;
		SenderPO sender = null;
		BillPO bill = null;
		GoodsPO goods = null;

		try{
			result = Helper.find(sql);
			while(result.next()){

				ObjectInputStream oips = new ObjectInputStream(result.getBinaryStream("receiver"));  
				receiver = (ReceiverPO)oips.readObject();
				oips = new ObjectInputStream(result.getBinaryStream("sender"));  
				sender = (SenderPO)oips.readObject();
				oips = new ObjectInputStream(result.getBinaryStream("bill"));  
				bill = (BillPO)oips.readObject();
				oips = new ObjectInputStream(result.getBinaryStream("goods"));  
				goods = (GoodsPO)oips.readObject();
				po = new OrderPO(receiver,sender,bill,goods,result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getString(8),DocumentCondition.valueOf(result.getString(9)));
			    pos.add(po);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return pos;
	}
}
