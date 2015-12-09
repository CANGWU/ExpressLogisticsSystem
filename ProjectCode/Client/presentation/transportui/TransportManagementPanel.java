	package transportui;
	
	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import java.awt.Insets;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.text.ParseException;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.Vector;
	
	import javax.sound.midi.VoiceStatus;
	import javax.swing.InputVerifier;
	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JComponent;
	import javax.swing.JFormattedTextField;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTextArea;
	import javax.swing.JTextField;
	import javax.swing.table.DefaultTableModel;
	import javax.swing.text.MaskFormatter;
	
	import com.sun.corba.se.spi.ior.Identifiable;
	import com.sun.jmx.remote.util.OrderClassLoaders;
	import com.sun.org.apache.xml.internal.security.Init;
	import com.sun.scenario.animation.shared.InfiniteClipEnvelope;
	import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import dataservice.DataFactoryService;
import dataserviceimpl.DataFactory;
import enums.Condition;
	import enums.DocumentCondition;
	import enums.Position;
	import enums.ResultMessage;
	import enums.Traffic;
	import enums.TransportType;
	import twaver.TWaverUtil;
	import twaver.base.A.A.A;
	import twaver.base.A.E.a;
	import twaver.base.A.E.c;
	import twaver.base.A.E.f;
	import vo.TransportVO;
	import free.BaseUI;
	import free.FreePagePane;
	import free.FreeReportPage;
	import free.FreeTabbedPane;
	import free.FreeToolBar;
	import free.FreeToolbarButton;
	import free.FreeToolbarRoverButton;
	import free.FreeUtil;
	
import main.StaffInfoPanel;
import strategysl.Constant;
import strategysl.ConstantController;
import sun.misc.ConditionLock;
import sun.swing.SwingUtilities2.Section;
import transportsl.Transport;
import transportsl.TransportController;
import transportslservice.TransportService;
	
	public class TransportManagementPanel extends FreePagePane{
		public static TransportService transportService;
		public static JPanel deleteTransportPanel,seekTransportPanel, addTransportPanel,fixTransportPanel;
		public static FreeReportPage addOrderPanel,fixOrderPanel;
		public static FreeTabbedPane tab;
//		public final static int ORDERNUMBERLENGTH=10;
//		public final static int IDNUMBERLENGTH=21;
//		public final static int TiMELENGTH=10;
//		public final static String IDERROR="������21λ���ݱ��";
//		public final static String ORDERNUMBERERROR="������10λ������";
//		public final static String TIMEERROR="��������ȷ���ڸ�ʽ��2010-09-01";
		public static MaskFormatter maskIDNumber;
		public static MaskFormatter maskTranfficNumber;
		public static MaskFormatter maskTranfficID;
		public static MaskFormatter maskTime;
		public static MaskFormatter maskOrderNumber;
		private static DataFactory dataFactory;
		private static ConstantController constantController;
		private static Constant constant;
		private static Transport transport;
	
	
		public static FreeReportPage  createTransportManagementPage(FreeTabbedPane tab){
			try {
				dataFactory=DataFactory.create();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			constant=new Constant(dataFactory);
			constantController=new ConstantController(constant);
			transport=new Transport(dataFactory,constantController);
			transportService=new TransportController(transport);
			
			
			
			try {
				
				initMaskFormatter();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TransportManagementPanel.tab=tab;		
			return createReportPage();
		}
	
		private static void initMaskFormatter() throws ParseException {
			// TODO Auto-generated method stub
			maskIDNumber=new MaskFormatter("#####################");
			maskIDNumber.setPlaceholderCharacter('0');
			//ʱ�仹��Ҫ����
			maskTime=new MaskFormatter("####*##*##");
			maskTime.setPlaceholderCharacter('0');
			maskOrderNumber=new MaskFormatter("##########");
			maskOrderNumber.setPlaceholderCharacter('0');
	
		}
	
		private static void createAddTransportPanel(){
			addTransportPanel=new FreePagePane();
			TransportVO vo = new TransportVO();
			ArrayList<String>order=new ArrayList<String>();
			ArrayList<Condition>condition=new ArrayList<Condition>();
			JLabel id=new JLabel("��ת|װ�˵����");
			JLabel sign=new JLabel("��������");
	
			JLabel tranfficId=new JLabel("�ؾ߱��");
			JLabel tranfficNumber=new JLabel("���˱��");
	
			JLabel nameOfWriter=new JLabel("��д��");
			JLabel departure=new JLabel("������");
			JLabel destination=new JLabel("�����");
			JLabel tranffic=new JLabel("���䷽ʽ");
			JLabel fare=new JLabel("����");
			JLabel time=new JLabel("��д����(��2010-01-03)");
			JLabel dCondition=new JLabel("�ύ״̬");
			JLabel member=new JLabel("Ѻ����Ա");
			JFormattedTextField idField = new JFormattedTextField(maskIDNumber);
			idField.setFocusLostBehavior(JFormattedTextField.COMMIT);
			idField.setInputVerifier(new FormattedTextFieldVerifier());
			JTextField nameOfWriterField=new JTextField(20);
			nameOfWriterField.setText(StaffInfoPanel.userVO.getName());
	
			//δ֪����
			JTextField tranfficIdField=new JTextField(10);
			JTextField tranfficNumberField=new JTextField(19);
			//
			JFormattedTextField timeField = new JFormattedTextField(maskTime);
			timeField.setFocusLostBehavior(JFormattedTextField.COMMIT);
			timeField.setInputVerifier(new FormattedTextFieldVerifier());
			JTextArea memberField=new JTextArea(2, 20);
			memberField.setLineWrap(true);
			JScrollPane scrollPane=new JScrollPane(memberField);
			scrollPane.setHorizontalScrollBarPolicy( 
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
			scrollPane.setVerticalScrollBarPolicy( 
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
			JComboBox signCombo=new JComboBox();
			signCombo.addItem(TransportType.Load);
			signCombo.addItem(TransportType.Reception);
			JComboBox departureCombo=new JComboBox();
			departureCombo.addItem(Position.Beijing);
			departureCombo.addItem(Position.Shanghai);
			departureCombo.addItem(Position.Nanjing);
			departureCombo.addItem(Position.Guangzhou);
			JComboBox destinationCombo=new JComboBox();
			destinationCombo.addItem(Position.Beijing);
			destinationCombo.addItem(Position.Shanghai);
			destinationCombo.addItem(Position.Nanjing);
			destinationCombo.addItem(Position.Guangzhou);
			JComboBox tranfficCombo=new JComboBox();
			tranfficCombo.addItem(Traffic.Air);
			tranfficCombo.addItem(Traffic.Train);
			tranfficCombo.addItem(Traffic.Car);
			JComboBox dConditionCombo=new JComboBox();
			dConditionCombo.addItem(DocumentCondition.draft);
			dConditionCombo.addItem(DocumentCondition.handing);
	
			GridBagLayout gb=new GridBagLayout(); 
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.insets=new Insets(5,0,2,0);
			JPanel bpanel = new JPanel();
			bpanel.setLayout(gb);
	
			BaseUI.myAdd(bpanel,id,constraints,0,0,1,1);
			BaseUI.myAdd(bpanel, sign, constraints, 0, 1, 1, 1);
			BaseUI.myAdd(bpanel,tranfficId,constraints,0,2,1,1);
			BaseUI.myAdd(bpanel,tranfficNumber,constraints,0,3,1,1);
			BaseUI.myAdd(bpanel,nameOfWriter,constraints,0,4,1,1);
			BaseUI.myAdd(bpanel,departure,constraints,0,5,1,1);
			BaseUI.myAdd(bpanel,destination,constraints,0,6,1,1);
			BaseUI.myAdd(bpanel,tranffic,constraints,0,7,1,1);
			BaseUI.myAdd(bpanel,fare,constraints,0,8,1,1);
			BaseUI.myAdd(bpanel,time,constraints,0,9,1,1);
			BaseUI.myAdd(bpanel,dCondition,constraints,0,10,1,1);
			BaseUI.myAdd(bpanel, member, constraints, 0, 11, 1, 1);
	
			BaseUI.myAdd(bpanel,idField,constraints,1,0,1,1);
			BaseUI.myAdd(bpanel, signCombo, constraints, 1, 1, 1, 1);
			BaseUI.myAdd(bpanel,tranfficIdField,constraints,1,2,1,1);
			BaseUI.myAdd(bpanel,tranfficNumberField,constraints,1,3,1,1);
			BaseUI.myAdd(bpanel,nameOfWriterField,constraints,1,4,1,1);
			BaseUI.myAdd(bpanel,departureCombo,constraints,1,5,1,1);
			BaseUI.myAdd(bpanel,destinationCombo,constraints,1,6,1,1);
			BaseUI.myAdd(bpanel,tranfficCombo,constraints,1,7,1,1);
			BaseUI.myAdd(bpanel,timeField,constraints,1,9,1,1);
			BaseUI.myAdd(bpanel,dConditionCombo,constraints,1,10,1,1);
			BaseUI.myAdd(bpanel, scrollPane, constraints, 1, 11, 1, 1);
	
			JButton calFare=new JButton("�����˷�");
			BaseUI.myAdd(bpanel, calFare, constraints, 1, 8, 1, 1);
			calFare.addMouseListener(new MouseListener() {
	
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
					vo.setDeparture((Position) departureCombo.getSelectedItem());
					vo.setDestination((Position) destinationCombo.getSelectedItem());
					vo.setTrafficType((Traffic) tranfficCombo.getSelectedItem());
					double k=transportService.addFare(vo);
					String fare=""+k;
					vo.setFare(k);
					calFare.setText(fare);
				}
			});
	
	
			JButton addOrder=new JButton("���Ӷ���");
			//addOrder.addActionListener(arg0);
			addOrder.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{   
					String title="���Ӷ���";                
					try{
						FreePagePane pagePane = FreeUtil.getPagePane(addOrderPanel);
						tab.setSelectedComponent(pagePane);
					}catch(Exception ex){
						createAddOrderPage(order,condition);
						tab.addTab(title, TransportUI.createPage(addOrderPanel));
						FreePagePane pagePane = FreeUtil.getPagePane(addOrderPanel);
						tab.setSelectedComponent(pagePane);
					}
	
	
				}
			});
	
	
			JButton submit=new JButton("�ύ");	
			submit.addMouseListener(new MouseListener() {
	
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
	
				}
	
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
	
				}
	
				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
	
				}
	
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
	
				}
	
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
	
					ResultMessage resultMessage;
					String[]s=memberField.getText().split("\n");
					ArrayList<String>member=new ArrayList<String>();
					for(String s1:s)
						member.add(s1);
					vo.setID(idField.getText());
					vo.setSign((TransportType) signCombo.getSelectedItem());
					vo.setTrafficID(tranfficIdField.getText());
					vo.setTransportID(tranfficNumberField.getText());
					vo.setWriter(nameOfWriterField.getText());
					vo.setTime(timeField.getText());
					vo.setMember(member);
					vo.setDocumentCondition((DocumentCondition)dConditionCombo.getSelectedItem());
					vo.setCondition(condition);
					vo.setOrder(order);
					try {
	
						resultMessage=transportService.saveTransport(vo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						resultMessage=ResultMessage.FAIL;
					}
					if(resultMessage==ResultMessage.SUCCESS){
						JOptionPane.showMessageDialog(null, "���Ӳ����ɹ�"); 
						tab.remove(FreeUtil.getPagePane(addTransportPanel));
					}else{
						JOptionPane.showMessageDialog(null, "���Ӳ���ʧ��"); 
					}
				}
	
				//TransportUI.setMockers(resultMessage);
			});
			BaseUI.myAdd(bpanel, addOrder, constraints, 0, 12, 2, 1);
			BaseUI.myAdd(bpanel,submit,constraints,0,13,2,1);
	
	
			addTransportPanel.add(bpanel);
		}
	
	
	
		private static void createDeleteTransportPanel(){
			deleteTransportPanel=new JPanel();
			GridBagLayout gb=new GridBagLayout(); 
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.insets=new Insets(15,0,5,0);
			JPanel bpanel = new JPanel();
			bpanel.setLayout(gb);
	
	
			JLabel id=new JLabel("��ת|װ�˵����");
			JFormattedTextField idField = new JFormattedTextField(maskIDNumber);
			idField.setFocusLostBehavior(JFormattedTextField.COMMIT);
			idField.setInputVerifier(new FormattedTextFieldVerifier());
	
			BaseUI.myAdd(bpanel,id,constraints,0,0,1,1);
			BaseUI.myAdd(bpanel,idField,constraints,1,0,1,1);
	
			JButton submit=new JButton("��ѯ��ɾ������ת|װ�˵����");
	
	
			JButton sure=new JButton("ȷ��ɾ��");
			BaseUI.myAdd(bpanel,submit,constraints,1,1,1,1);
			submit.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent arg0){ 
					TransportVO vo = null;
					ResultMessage resultMessage=null;
					try {
						vo=transportService.getTransport(idField.getText());
						if(vo!=null)
							resultMessage=ResultMessage.SUCCESS;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						resultMessage=ResultMessage.FAIL;
					}
	
					if(resultMessage!=ResultMessage.FAIL){
						bpanel.remove(submit);
						bpanel.remove(idField);
	
						JLabel id=new JLabel("��ת|װ�˵����:  " + vo.getID());
						JLabel tranfficId=new JLabel("�ؾ߱��:  "+vo.getTrafficID());
						JLabel tranfficNumber=new JLabel("���˱��:  "+vo.getTransportID());
						JLabel nameOfWriter=new JLabel("��д��:  "+vo.getWriter());
						JLabel departure=new JLabel("������:  "+vo.getDeparture());
						JLabel destination=new JLabel("�����:  "+vo.getDestination());
						JLabel tranffic=new JLabel("���䷽ʽ:  "+vo.getTrafficType());
						JLabel fare=new JLabel("����:  "+vo.getfare());
						JLabel time=new JLabel("��д����:  "+vo.getTime());
						JLabel dCondition=new JLabel("�ύ״̬:  "+vo.getDocumentCondition());
	
						BaseUI.myAdd(bpanel,id,constraints,0,0,1,1);
						BaseUI.myAdd(bpanel,tranfficId,constraints,0,1,1,1);
						BaseUI.myAdd(bpanel,tranfficNumber,constraints,0,2,1,1);
						BaseUI.myAdd(bpanel,nameOfWriter,constraints,0,3,1,1);
						BaseUI.myAdd(bpanel,departure,constraints,0,4,1,1);
						BaseUI.myAdd(bpanel,destination,constraints,0,5,1,1);
						BaseUI.myAdd(bpanel,tranffic,constraints,0,6,1,1);
						BaseUI.myAdd(bpanel,fare,constraints,0,7,1,1);
						BaseUI.myAdd(bpanel,time,constraints,0,8,1,1);
						BaseUI.myAdd(bpanel,dCondition,constraints,0,9,1,1);
						BaseUI.myAdd(bpanel, sure, constraints, 0, 10, 1, 1);
						String transportID=vo.getID();
						sure.addMouseListener(new MouseAdapter(){
							ResultMessage resultMessage=null;
							public void mouseClicked(MouseEvent arg0){
								try {
									resultMessage=transportService.deleteTransport(transportID);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									resultMessage=ResultMessage.FAIL;
								}
								if(resultMessage==ResultMessage.SUCCESS){
									JOptionPane.showMessageDialog(null, "ɾ�������ɹ�"); 
									tab.remove(FreeUtil.getPagePane(deleteTransportPanel));
								}else{
									JOptionPane.showMessageDialog(null, "ɾ������ʧ��"); 
								}
							}
						});
					}else{
						JOptionPane.showMessageDialog(null, "��ѯʧ��"); 
					}
				}
			});
	
			deleteTransportPanel.add(bpanel);
		}
	
		private static void createFixTransportPanel(){
			fixTransportPanel=new JPanel();
			GridBagLayout gb=new GridBagLayout(); 
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.insets=new Insets(5,0,2,0);
			JPanel bpanel = new JPanel();
			bpanel.setLayout(gb);
	
			JLabel id=new JLabel("��ת|װ�˵����");
			JFormattedTextField idField = new JFormattedTextField(maskIDNumber);
			idField.setFocusLostBehavior(JFormattedTextField.COMMIT);
			idField.setInputVerifier(new FormattedTextFieldVerifier());
			BaseUI.myAdd(bpanel,id,constraints,0,0,1,1);
			BaseUI.myAdd(bpanel,idField,constraints,1,0,1,1);
	
			JButton submit=new JButton("��ѯ���޸���ת|װ�˵���Ϣ");
			JButton sure=new JButton("ȷ���޸�");
			JButton fixOrder=new JButton("�޸Ķ���");
	
			BaseUI.myAdd(bpanel,submit,constraints,1,1,1,1);
	
			submit.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent arg0){
	
					TransportVO transportVO=null;
					ResultMessage resultMessage=null;
					try {
						transportVO=transportService.getTransport(idField.getText());
						if(transportVO!=null)
							resultMessage=ResultMessage.SUCCESS;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						resultMessage=ResultMessage.FAIL;
					}//
	
					if (resultMessage!=ResultMessage.FAIL){
	
						ArrayList<String>order=transportVO.getOrder();
						ArrayList<Condition>condition=transportVO.getCondition();
	
						bpanel.remove(submit);
						bpanel.remove(idField);
						id.setText("��ת|װ�˵����:  "+transportVO.getID());
						JLabel sign=new JLabel("��������");
						JLabel tranfficId=new JLabel("�ؾ߱��");
						JLabel tranfficNumber=new JLabel("���˱��");
						JLabel nameOfWriter=new JLabel("��д��");
						JLabel departure=new JLabel("������");
						JLabel destination=new JLabel("�����");
						JLabel tranffic=new JLabel("���䷽ʽ");
						JLabel fare=new JLabel("����");
						JLabel time=new JLabel("��д����(��2010-01-03)");
						JLabel dCondition=new JLabel("�ύ״̬");
						JLabel member=new JLabel("Ѻ����Ա");
						JTextField nameOfWriterField=new JTextField(transportVO.getWriter(),20);
						JTextField tranfficIdField=new JTextField(transportVO.getTrafficID(),10);
						JTextField tranfficNumberField=new JTextField(transportVO.getTransportID(),19);
						JFormattedTextField timeField = new JFormattedTextField(maskTime);		
						timeField.setFocusLostBehavior(JFormattedTextField.COMMIT);
						timeField.setInputVerifier(new FormattedTextFieldVerifier());
						timeField.setText(transportVO.getTime());
						JTextArea memberField=new JTextArea(2, 20);
						memberField.setLineWrap(true);
						JScrollPane scrollPane=new JScrollPane(memberField);
						scrollPane.setHorizontalScrollBarPolicy( 
								JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
						scrollPane.setVerticalScrollBarPolicy( 
								JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
						ArrayList<String>strings=transportVO.getMember();
						String string="";
						for(String s:strings)
							string=s+"\n"+string;
						memberField.setText(string);
						JComboBox signCombo=new JComboBox();
						signCombo.addItem(TransportType.Load);
						signCombo.addItem(TransportType.Reception);
						signCombo.setSelectedItem(transportVO.getSign());
						JComboBox departureCombo=new JComboBox();
						departureCombo.addItem(Position.Beijing);
						departureCombo.addItem(Position.Shanghai);
						departureCombo.addItem(Position.Nanjing);
						departureCombo.addItem(Position.Guangzhou);
						departureCombo.setSelectedItem(transportVO.getDeparture());
						JComboBox destinationCombo=new JComboBox();
						destinationCombo.addItem(Position.Beijing);
						destinationCombo.addItem(Position.Shanghai);
						destinationCombo.addItem(Position.Nanjing);
						destinationCombo.addItem(Position.Guangzhou);
						destinationCombo.setSelectedItem(transportVO.getDestination());
						JComboBox tranfficCombo=new JComboBox();
						tranfficCombo.addItem(Traffic.Air);
						tranfficCombo.addItem(Traffic.Train);
						tranfficCombo.addItem(Traffic.Car);
						tranfficCombo.setSelectedItem(transportVO.getTrafficType());
						JComboBox dConditionCombo=new JComboBox();
						dConditionCombo.addItem(DocumentCondition.draft);
						dConditionCombo.addItem(DocumentCondition.handing);
						dConditionCombo.setSelectedItem(transportVO.getDocumentCondition());
	
						BaseUI.myAdd(bpanel,id,constraints,0,0,1,1);
						BaseUI.myAdd(bpanel, sign, constraints, 0, 1, 1, 1);
						BaseUI.myAdd(bpanel,tranfficId,constraints,0,2,1,1);
						BaseUI.myAdd(bpanel,tranfficNumber,constraints,0,3,1,1);
						BaseUI.myAdd(bpanel,nameOfWriter,constraints,0,4,1,1);
						BaseUI.myAdd(bpanel,departure,constraints,0,5,1,1);
						BaseUI.myAdd(bpanel,destination,constraints,0,6,1,1);
						BaseUI.myAdd(bpanel,tranffic,constraints,0,7,1,1);
						BaseUI.myAdd(bpanel,fare,constraints,0,8,1,1);
						BaseUI.myAdd(bpanel,time,constraints,0,9,1,1);
						BaseUI.myAdd(bpanel,dCondition,constraints,0,10,1,1);
						BaseUI.myAdd(bpanel, member, constraints, 0, 11, 1, 1);
	
						BaseUI.myAdd(bpanel, signCombo, constraints, 1, 1, 1, 1);
						BaseUI.myAdd(bpanel,tranfficIdField,constraints,1,2,1,1);
						BaseUI.myAdd(bpanel,tranfficNumberField,constraints,1,3,1,1);
						BaseUI.myAdd(bpanel,nameOfWriterField,constraints,1,4,1,1);
						BaseUI.myAdd(bpanel,departureCombo,constraints,1,5,1,1);
						BaseUI.myAdd(bpanel,destinationCombo,constraints,1,6,1,1);
						BaseUI.myAdd(bpanel,tranfficCombo,constraints,1,7,1,1);
						BaseUI.myAdd(bpanel,timeField,constraints,1,9,1,1);
						BaseUI.myAdd(bpanel,dConditionCombo,constraints,1,10,1,1);
						BaseUI.myAdd(bpanel, scrollPane, constraints, 1, 11, 1, 1);
						BaseUI.myAdd(bpanel, fixOrder, constraints, 0, 12, 2, 1);
						BaseUI.myAdd(bpanel, sure, constraints,0, 13, 2, 1);
	
	
						TransportVO vo=new TransportVO();
						JButton calFare=new JButton("�����˷�");
						BaseUI.myAdd(bpanel, calFare, constraints, 1, 8, 1, 1);
						calFare.addMouseListener(new MouseListener() {
	
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
								vo.setDeparture((Position) departureCombo.getSelectedItem());
								vo.setDestination((Position) destinationCombo.getSelectedItem());
								vo.setTrafficType((Traffic) tranfficCombo.getSelectedItem());
								double k=transportService.addFare(vo);
								String fare=""+k;
								vo.setFare(k);
								calFare.setText(fare);
							}
						});
	
	
						fixOrder.addMouseListener(new MouseAdapter(){
							@Override
							public void mouseClicked(MouseEvent arg0) {   
								String title="�޸Ķ���";                
								try{
									FreePagePane pagePane = FreeUtil.getPagePane(fixOrderPanel);
									tab.setSelectedComponent(pagePane);
								}catch(Exception ex){
									createAddOrderPage(order, condition);
									tab.addTab(title, TransportUI.createPage(fixOrderPanel));
									FreePagePane pagePane = FreeUtil.getPagePane(fixOrderPanel);
									tab.setSelectedComponent(pagePane);
								}
	
	
							}
						});
	
						sure.addMouseListener(new MouseListener() {
	
							@Override
							public void mouseReleased(MouseEvent arg0) {
								// TODO Auto-generated method stub
	
							}
	
							@Override
							public void mousePressed(MouseEvent arg0) {
								// TODO Auto-generated method stub
	
							}
	
							@Override
							public void mouseExited(MouseEvent arg0) {
								// TODO Auto-generated method stub
	
							}
	
							@Override
							public void mouseEntered(MouseEvent arg0) {
								// TODO Auto-generated method stub
	
							}
	
							@Override
							public void mouseClicked(MouseEvent arg0) {
								// TODO Auto-generated method stub
	
								ResultMessage resultMessage;
								String[]s=memberField.getText().split("\n");
								ArrayList<String>member=new ArrayList<String>();
								for(String s1:s)
									member.add(s1);
	
								vo.setSign((TransportType) signCombo.getSelectedItem());
								vo.setTrafficID(tranfficIdField.getText());
								vo.setTransportID(tranfficNumberField.getText());
								vo.setWriter(nameOfWriterField.getText());
								vo.setTime(timeField.getText());
								vo.setMember(member);
								vo.setDocumentCondition((DocumentCondition) dConditionCombo.getSelectedItem());
								vo.setCondition(condition);
								vo.setOrder(order);
								try {
	
									resultMessage=transportService.updateTransport(vo);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									resultMessage=ResultMessage.FAIL;
						
								}
								if(resultMessage==ResultMessage.SUCCESS){
									JOptionPane.showMessageDialog(null, "�޸Ĳ����ɹ�"); 
									tab.remove(FreeUtil.getPagePane(fixTransportPanel));
								}else{
									JOptionPane.showMessageDialog(null, "�޸Ĳ���ʧ��"); 
								}
								//TransportUI.setMockers(resultMessage);
							}
					});
	
				}else{
					JOptionPane.showMessageDialog(null, "��ѯʧ��");
				}
	

			}
		});
	
	
			fixTransportPanel.add(bpanel);
	
	}
	
	private static void createSeekTransportPanel(){
		seekTransportPanel=new JPanel();
		GridBagLayout gb=new GridBagLayout(); 
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets=new Insets(15,0,5,0);
		JPanel bpanel = new JPanel();
		bpanel.setLayout(gb);
	
	
		JLabel id=new JLabel("��ת|װ�˵����");
		JFormattedTextField idField = new JFormattedTextField(maskIDNumber);
		idField.setFocusLostBehavior(JFormattedTextField.COMMIT);
		idField.setInputVerifier(new FormattedTextFieldVerifier());
		BaseUI.myAdd(bpanel,id,constraints,0,0,1,1);
		BaseUI.myAdd(bpanel,idField,constraints,1,0,1,1);
	
		JButton submit=new JButton("��ѯ��ת|װ�˵���Ϣ");
		JButton sure=new JButton("ȷ����Ϣ");
		BaseUI.myAdd(bpanel,submit,constraints,1,1,1,1);
		submit.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0){ 
				TransportVO vo = null;
				ResultMessage resultMessage=ResultMessage.SUCCESS;
				try {
					vo=transportService.getTransport(idField.getText());
					resultMessage=ResultMessage.SUCCESS;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultMessage=ResultMessage.FAIL;
				}
	
				if(resultMessage!=ResultMessage.FAIL){
					bpanel.remove(submit);
					bpanel.remove(idField);
	
					JLabel id=new JLabel("��ת|װ�˵����:  " + vo.getID());
					JLabel tranfficId=new JLabel("�ؾ߱��:  "+vo.getTrafficID());
					JLabel tranfficNumber=new JLabel("���˱��:  "+vo.getTransportID());
					JLabel nameOfWriter=new JLabel("��д��:  "+vo.getWriter());
					JLabel departure=new JLabel("������:  "+vo.getDeparture());
					JLabel destination=new JLabel("�����:  "+vo.getDestination());
					JLabel tranffic=new JLabel("���䷽ʽ:  "+vo.getTrafficType());
					JLabel fare=new JLabel("����:  "+vo.getfare());
					JLabel time=new JLabel("��д����:  "+vo.getTime());
					JLabel dCondition=new JLabel("�ύ״̬:  "+vo.getDocumentCondition());
	
					BaseUI.myAdd(bpanel,id,constraints,0,0,1,1);
					BaseUI.myAdd(bpanel,tranfficId,constraints,0,1,1,1);
					BaseUI.myAdd(bpanel,tranfficNumber,constraints,0,2,1,1);
					BaseUI.myAdd(bpanel,nameOfWriter,constraints,0,3,1,1);
					BaseUI.myAdd(bpanel,departure,constraints,0,4,1,1);
					BaseUI.myAdd(bpanel,destination,constraints,0,5,1,1);
					BaseUI.myAdd(bpanel,tranffic,constraints,0,6,1,1);
					BaseUI.myAdd(bpanel,fare,constraints,0,7,1,1);
					BaseUI.myAdd(bpanel,time,constraints,0,8,1,1);
					BaseUI.myAdd(bpanel,dCondition,constraints,0,9,1,1);
					BaseUI.myAdd(bpanel, sure, constraints, 0, 10, 1, 1);
	
					sure.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent arg0){
							//check the TransportPO
							tab.remove(FreeUtil.getPagePane(seekTransportPanel));
						}
					});
				}else{
					JOptionPane.showMessageDialog(null, "��ѯʧ��");
				}
			}
		});
	
		seekTransportPanel.add(bpanel);
	}
	
	
	//չʾ���ɾ������δ��ɣ�����
	private static FreeReportPage createReportPage() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("��ת��|װ�˵����");
		model.addColumn("��д��");
		model.addColumn("����״̬");
		model.addColumn("ʱ��");
		model.addColumn("���䷽ʽ");
		model.addColumn("��������");
		ArrayList<TransportVO>vos=null;
		ResultMessage resultMessage=null;
		
		
		//nameOfWriter û����
		try {
			vos=transportService.getTransportList(StaffInfoPanel.userVO.getName(), DocumentCondition.draft);
		    resultMessage=ResultMessage.SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMessage=ResultMessage.FAIL;
		}
	
		if(resultMessage==ResultMessage.SUCCESS)
			for (TransportVO vo:vos) {
				Vector row = new Vector();
				row.add(vo.getID());
				row.add(vo.getWriter());
				row.add(DocumentCondition.draft);
				row.add(vo.getTime());
				row.add(vo.getTrafficType());
				row.add(vo.getSign());
				model.addRow(row);
			}else {
				JOptionPane.showMessageDialog(null, "����ʧ��");
			}
	
		FreeReportPage page = new FreeReportPage();
		page.getTable().setModel(model);
		page.setDescription("All Work Order Items by Part Number. Created " + new Date().toString());
		setupPageToolbar(page);
	
		return page;
	}
	
	public static void createFixOrderPage(ArrayList<String>order,ArrayList<Condition>condition){
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("��ݵ���");
		model.addColumn("���״̬");
	
		for(int i=0;i<order.size();i++){
			Vector row = new Vector();
			row.add(order.get(i));
			row.add(condition.get(i));
			model.addRow(row);
		}
	
	
		fixOrderPanel=new FreeReportPage();
		FreeToolbarButton add=createButton("/free/test/add.png", "���Ӷ���", true);
		FreeToolbarButton delete=createButton("/free/test/update.png", "ɾ������", true);
		FreeToolbarButton finish=createButton("/free/test/print.png", "��������", true);
	
		JLabel orderNumber=new JLabel("������");
		JFormattedTextField orderNumberFiled = new JFormattedTextField(maskOrderNumber);
		orderNumberFiled.setFocusLostBehavior(JFormattedTextField.COMMIT);
		orderNumberFiled.setInputVerifier(new FormattedTextFieldVerifier());
		JLabel con=new JLabel("����״̬");
		JComboBox conditionCombo=new JComboBox();
		conditionCombo.addItem(Condition.perfect);
		conditionCombo.addItem(Condition.demage);
		conditionCombo.addItem(Condition.lost);
	
		fixOrderPanel.getRightToolBar().add(add);
		fixOrderPanel.getRightToolBar().add(delete);
		fixOrderPanel.getRightToolBar().add(finish);
		FreeToolBar leftToolBar=fixOrderPanel.getLeftToolBar();
		leftToolBar.add(orderNumber);
		leftToolBar.add(orderNumberFiled);
		leftToolBar.add(con);
		leftToolBar.add(conditionCombo);
	
		fixOrderPanel.getTable().setModel(model);
		fixOrderPanel.setDescription("All Work Order Items by Part Number. Created " + new Date().toString());
	
		add.addMouseListener(new MouseListener() {
	
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
				String  orderNumber;
				int i;
				for(i=0;i<model.getRowCount();i++){
					orderNumber = (String) model.getValueAt(i, 0);
					if(orderNumber.equals(orderNumberFiled.getText())){					
						JOptionPane.showMessageDialog(null, "�����Ѵ���");
						return;
					}
				}
				Vector row = new Vector();
				order.add(orderNumberFiled.getText());
				row.add(orderNumberFiled.getText());
				orderNumberFiled.setText("");
				condition.add((Condition) conditionCombo.getSelectedItem());
				row.add(conditionCombo.getSelectedItem());
				model.addRow(row);
			}
		});
		delete.addMouseListener(new MouseListener() {
	
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
				String  orderNumber;
				int i;
				int bRowCount=model.getRowCount();
				for(i=0;i<bRowCount;i++){
					orderNumber = (String) model.getValueAt(i, 0);
					if(orderNumber.equals(orderNumberFiled.getText())){
						model.removeRow(i);
						order.remove(i);
						condition.remove(i);
						orderNumberFiled.setText("");

						break;
					}
				}
				if(i==bRowCount){
					JOptionPane.showMessageDialog(null, "��ѯʧ��");
				}
			}
		});
	
		finish.addMouseListener(new MouseListener() {
	
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				tab.remove(FreeUtil.getPagePane(fixOrderPanel));
			}
		});
	
	
	}
	
	private static void createAddOrderPage(ArrayList<String> order, ArrayList<Condition> condition) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("��ݵ���");
		model.addColumn("���״̬");;
	
		for(int i=0;i<order.size();i++){
			Vector row = new Vector();
			row.add(order.get(i));
			row.add(condition.get(i));
			model.addRow(row);
		}
	
		addOrderPanel=new FreeReportPage();
		FreeToolbarButton add=createButton("/free/test/add.png", "���Ӷ���", true);
		FreeToolbarButton delete=createButton("/free/test/update.png", "ɾ������", true);
		FreeToolbarButton finish=createButton("/free/test/print.png", "��������", true);
	
		JLabel orderNumber=new JLabel("������");
		JFormattedTextField orderNumberFiled = new JFormattedTextField(maskOrderNumber);
		orderNumberFiled.setFocusLostBehavior(JFormattedTextField.COMMIT);
		orderNumberFiled.setInputVerifier(new FormattedTextFieldVerifier());
	
		JLabel conditionLabel=new JLabel("����״̬");
		JComboBox conditionCombo=new JComboBox();
		conditionCombo.addItem(Condition.perfect);
		conditionCombo.addItem(Condition.demage);
		conditionCombo.addItem(Condition.lost);
	
		addOrderPanel.getRightToolBar().add(add);
		addOrderPanel.getRightToolBar().add(delete);
		addOrderPanel.getRightToolBar().add(finish);
		FreeToolBar leftToolBar=addOrderPanel.getLeftToolBar();
		leftToolBar.add(orderNumber);
		leftToolBar.add(orderNumberFiled);
		leftToolBar.add(conditionLabel);
		leftToolBar.add(conditionCombo);
	
		addOrderPanel.getTable().setModel(model);
		addOrderPanel.setDescription("All Work Order Items by Part Number. Created " + new Date().toString());
	
		add.addMouseListener(new MouseListener() {
	
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//addOrderPanel
				String  orderNumber;
				int i;
				for(i=0;i<model.getRowCount();i++){
					orderNumber = (String) model.getValueAt(i, 0);
					if(orderNumber.equals(orderNumberFiled.getText())){					
						JOptionPane.showMessageDialog(null, "�����Ѵ���");
						return;
					}
				}
				Vector row = new Vector();
				order.add(orderNumberFiled.getText());
				row.add(orderNumberFiled.getText());
				orderNumberFiled.setText("");
				condition.add((Condition) conditionCombo.getSelectedItem());
				row.add(conditionCombo.getSelectedItem());
				model.addRow(row);
			}
		});
	
		delete.addMouseListener(new MouseListener() {
	
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String  orderNumber;
				int i;
				int bRowCount=model.getRowCount();
				for(i=0;i<bRowCount;i++){
					orderNumber = (String) model.getValueAt(i, 0);
					if(orderNumber.equals(orderNumberFiled.getText())){
						model.removeRow(i);
						order.remove(i);
						condition.remove(i);
						orderNumberFiled.setText("");

						break;
					}
				}
				if(i==bRowCount){
					JOptionPane.showMessageDialog(null, "��ѯʧ��");
				}
			}
		}); 
	
		finish.addMouseListener(new MouseListener() {
	
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
	
			}
	
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				///addOrderPanel.
				tab.remove(FreeUtil.getPagePane(addOrderPanel));			}
		});
	
	
	}
	
	public static void setupPageToolbar(FreePagePane page) {
		FreeToolbarButton addTransport,deleteTransport,fixTransport,seekTransport;
		addTransport=createButton("/free/test/add.png", "������ת|װ�˵�", true);
		deleteTransport=createButton("/free/test/update.png", "ɾ����ת|װ�˵�", true);
		fixTransport=createButton("/free/test/refresh.png", "�޸���ת|װ�˵�", true);
		seekTransport=createButton("/free/test/print.png", "������ת|װ�˵�", true);
		page.getRightToolBar().add(addTransport);
		page.getRightToolBar().add(deleteTransport);
		page.getRightToolBar().add(fixTransport);
		page.getRightToolBar().add(seekTransport);
	
		addTransport.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{   
				String title=addTransport.getToolTipText();                
				try{
					FreePagePane pagePane = FreeUtil.getPagePane(addTransportPanel);
					tab.setSelectedComponent(pagePane);
				}catch(Exception ex){
					createAddTransportPanel();
					tab.addTab(title, TransportUI.createPage(addTransportPanel));
					FreePagePane pagePane = FreeUtil.getPagePane(addTransportPanel);
					tab.setSelectedComponent(pagePane);
				}
	
	
			}
		});
	
	
		deleteTransport.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{   
				String title=deleteTransport.getToolTipText();                
				try{
					FreePagePane pagePane = FreeUtil.getPagePane(deleteTransportPanel);
					tab.setSelectedComponent(pagePane);
				}catch(Exception ex){
					createDeleteTransportPanel();
					tab.addTab(title, TransportUI.createPage(deleteTransportPanel));
					FreePagePane pagePane = FreeUtil.getPagePane(deleteTransportPanel);
					tab.setSelectedComponent(pagePane);
				}
	
	
			}
		});
	
	
		fixTransport.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{   
				String title=fixTransport.getToolTipText();                
				try{
					FreePagePane pagePane = FreeUtil.getPagePane(fixTransportPanel);
					tab.setSelectedComponent(pagePane);
				}catch(Exception ex){
					createFixTransportPanel();
					tab.addTab(title, TransportUI.createPage(fixTransportPanel));
					FreePagePane pagePane = FreeUtil.getPagePane(fixTransportPanel);
					tab.setSelectedComponent(pagePane);
	
				}
	
	
			}
		});
	
		seekTransport.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{   
				String title=seekTransport.getToolTipText();                
				try{
					FreePagePane pagePane = FreeUtil.getPagePane(seekTransportPanel);
					tab.setSelectedComponent(pagePane);
				}catch(Exception ex){
					createSeekTransportPanel();
					tab.addTab(title, TransportUI.createPage(seekTransportPanel));
					FreePagePane pagePane = FreeUtil.getPagePane(seekTransportPanel);
					tab.setSelectedComponent(pagePane);
				}
	
	
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