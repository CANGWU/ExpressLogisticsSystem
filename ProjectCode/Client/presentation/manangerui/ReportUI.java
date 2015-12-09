package manangerui;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.BaseUI;

public class ReportUI extends JPanel {
	public ReportUI(){
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		cReport = new JButton("成本收益表");
		jReport = new JButton("经营情况表");
		this.setLayout(new BorderLayout());
		//容器

	
		GridBagLayout gb=new GridBagLayout(); 
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets=new Insets(25,0,25,0);
		JPanel bpanel = new JPanel();
		bpanel.setLayout(gb);
		BaseUI.myAdd(bpanel,cReport,constraints,0,0,1,1);
		BaseUI.myAdd(bpanel,jReport,constraints,0,1,1,1);
        this.add(bpanel,BorderLayout.WEST);

	}
	
	JButton cReport;
	JButton jReport;
	

}
