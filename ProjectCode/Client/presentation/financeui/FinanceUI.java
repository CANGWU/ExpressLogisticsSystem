package financeui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import manangerui.LogUI;
import manangerui.ReportUI;

public class FinanceUI extends JFrame{
	JPanel statusBar;
	JPanel informationBar;
	JLabel message;

	
	public FinanceUI(){
		super("�������ϵͳ");
		this.init();
	}
	
	public void init(){
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter(){
			   @Override
			public void windowClosing(WindowEvent e) {
			    super.windowClosing(e);
			    System.exit(0);
			   }});
			  layoutUI();
			  this.setVisible(true);
	}
	
	private void layoutUI(){
		JTabbedPane tab = new JTabbedPane(SwingConstants.LEFT);
		JSeparator sep1=new JSeparator();
		JSeparator sep2=new JSeparator();
		sep1.setOrientation(SwingConstants.HORIZONTAL);
		sep2.setOrientation(SwingConstants.HORIZONTAL);
		
		
		message=new JLabel("������Ա��Ϣ");
		statusBar = new JPanel();
		informationBar = new JPanel();
		
		
		Container container = this.getLayeredPane();
		
		JPanel accountmanagementui=new AccountManagementUI();
		JPanel balanceui=new BalanceUI();
		JPanel costui=new CostUI();
		JPanel accountinitui=new CompanyAccountInitUI();
		JPanel reportui=new ReportUI();
		JPanel logui=new LogUI();
		
		
		tab.add("�˻�����",accountmanagementui);
		tab.add("�������",balanceui);
		tab.add("�ɱ�����",costui);
		tab.add("��Ŀ��ʼ��",accountinitui);
		tab.add("ͳ�Ʊ���",reportui);
		tab.add("��־����",logui);
		

		
        informationBar.setLayout(new BorderLayout());
		informationBar.add(new JLabel("�������ϵͳELS"),BorderLayout.WEST);
		informationBar.add(message,BorderLayout.EAST);
		informationBar.add(sep1,BorderLayout.SOUTH);
		
		statusBar.setLayout(new BorderLayout());
		statusBar.add(new JLabel("״̬��(δʵ��)"),BorderLayout.SOUTH);
		statusBar.add(sep2,BorderLayout.NORTH);
		 
		container.setLayout(new BorderLayout());
		container.add(tab, BorderLayout.CENTER);
		container.add(statusBar, BorderLayout.SOUTH);
		container.add(informationBar, BorderLayout.NORTH);
	
		
	}
}