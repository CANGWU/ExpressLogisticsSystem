package managerui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sun.glass.ui.TouchInputSupport;

import dataserviceimpl.DataFactory;
import free.FreePagePane;
import free.FreeReportPage;
import free.FreeTabbedPane;
import free.FreeToolBar;
import free.FreeToolbarButton;
import free.FreeToolbarRoverButton;
import po.LogPO;
import twaver.TWaverUtil;
import usersl.LogManagement;
import userslservice.LogService;
import vo.LogVO;

public class LogPanel {
	public static FreeTabbedPane tab;
	public static LogService logService;


	public static FreePagePane createLogPage(FreeTabbedPane tab) {
		try {
			logService=LogManagement.creatCheck(DataFactory.create());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LogPanel.tab=tab;
		// TODO Auto-generated method stub
		return createReportPage();
	}

	private static FreeReportPage createReportPage() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("操作时间");
		model.addColumn("操作地址");
		model.addColumn("操作账户");
		model.addColumn("操作内容");


//		for (int i = 0; i < 100; i++) {
//			Vector row = new Vector();
//			row.add("2015/02/28");
//			row.add("我家");
//			row.add("110119001");
//			row.add("getTime");
//			model.addRow(row);
//		}

		FreeReportPage page = new FreeReportPage();
		page.getTable().setModel(model);
		page.setDescription("All Work Order Items by Part Number. Created " + new Date().toString());
		FreeToolbarButton checkLog;
		checkLog=createButton("/free/test/print.png", "查询日志", true);
		page.getRightToolBar().add(checkLog);
		JLabel time=new JLabel("时间");
		JTextField timeField=new JTextField(10);
		JLabel office=new JLabel("操作地址");
		JTextField officeFiledFiled=new JTextField(9);

		FreeToolBar leftToolBar=page.getLeftToolBar();
		leftToolBar.add(time);
		leftToolBar.add(timeField);
		leftToolBar.add(office);
		leftToolBar.add(officeFiledFiled);
		checkLog.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
					}
            ArrayList<LogVO>logs=logService.logmessage(officeFiledFiled.getText(), time.getText());
            for(LogVO vo:logs){
            	Vector row=new Vector();
            	row.add(vo.getTime());
            	row.add(vo.getOffice());
            	row.add(vo.getUseuId());
            	row.add(vo.getLogmessage());
            	model.addRow(row);
            }
			}
		});


		return page;
	}

	public static void setupPageToolbar(FreePagePane page) {
		FreeToolbarButton checkLog;
		checkLog=createButton("/free/test/print.png", "查询日志", true);
		page.getRightToolBar().add(checkLog);
		JLabel time=new JLabel("时间");
		JTextField timeField=new JTextField(10);
		JLabel office=new JLabel("操作地址");
		JTextField officeFiledFiled=new JTextField(9);

		FreeToolBar leftToolBar=page.getLeftToolBar();
		leftToolBar.add(time);
		leftToolBar.add(timeField);
		leftToolBar.add(office);
		leftToolBar.add(officeFiledFiled);
		checkLog.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	public static FreeToolbarButton createButton(String icon, String tooltip, boolean rover) {
		FreeToolbarButton button = null;
		if (rover) {
			button = new FreeToolbarRoverButton();
		} else {
			button = new FreeToolbarButton();
		}
		button.setIcon(TWaverUtil.getIcon(icon));
		button.setToolTipText(tooltip);

		return button;
	}
}
