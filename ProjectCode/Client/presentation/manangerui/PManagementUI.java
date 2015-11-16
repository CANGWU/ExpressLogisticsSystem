package manangerui;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.BaseUI;

public class PManagementUI extends JPanel {
	public PManagementUI(){
		init();
	}
	
	//初始化
	public void init(){
        add = new JButton("添加机构");
		del = new JButton("删除机构");
		update = new JButton("修改机构");
		seek = new JButton("查找机构");
		name=new JLabel("机构名称");
		worknumber=new JLabel("机构编号");
		workplace=new JLabel("机构地点");
	    position=new JLabel("机构类型");
		this.setLayout(new BorderLayout());
		//容器

	
		GridBagLayout gb=new GridBagLayout(); 
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets=new Insets(25,0,25,0);
		JPanel bpanel = new JPanel();
		bpanel.setLayout(gb);
		BaseUI.myAdd(bpanel,add,constraints,0,0,1,1);
		BaseUI.myAdd(bpanel,del,constraints,0,1,1,1);
		BaseUI.myAdd(bpanel,update,constraints,0,2,1,1);
		BaseUI.myAdd(bpanel,seek,constraints,0,3,1,1);
		
		
		JPanel lpanel=new JPanel();
		lpanel.setLayout(gb);
		constraints.insets=new Insets(0,0,0,70);
		
		BaseUI.myAdd(lpanel,name,constraints,0,0,1,1);
		BaseUI.myAdd(lpanel,worknumber,constraints,1,0,1,1);
		BaseUI.myAdd(lpanel,workplace,constraints,2,0,1,1);
		BaseUI.myAdd(lpanel,position,constraints,3,0,1,1);
	
		
		String nation[] ={"1","2","3","4"};
		list=new JList(nation);
		this.add(new JScrollPane(list));
		
		
		this.add(lpanel,BorderLayout.NORTH);
		this.add(list,BorderLayout.CENTER);
		
        this.add(bpanel,BorderLayout.WEST);

	}
	//这个方法可以抽象出来！！！
	
	JButton add;
	JButton del;
	JButton update;
	JButton seek;
	JList list;
	JLabel name;
	JLabel worknumber;
	JLabel workplace;
	JLabel position;

}
