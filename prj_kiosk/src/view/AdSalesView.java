package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import evt.AdSalesEvt;

@SuppressWarnings("serial")
public class AdSalesView extends JPanel {
	
	//Table
	private JTable salesInfo;
	private DefaultTableModel salesInfoTm ;
	
	//Field
	private JTextField startJtf=new JTextField();
	private JTextField endJtf=new JTextField();
	
	//Label
	private JLabel salesInfoJlb=new JLabel("판매내역");
	
	private JLabel startJlb=new JLabel("시작일");
	private JLabel endJlb=new JLabel("종료일");
	private JLabel periodJlb=new JLabel("~");

	private JLabel nameJlb=new JLabel("상품종류");
	private JLabel typeJlb=new JLabel("상품명");
	
	private JLabel totalJlb=new JLabel("총 매출 금액");
	
	//ComboBox
	private DefaultComboBoxModel<String> nameType=new DefaultComboBoxModel<String>();
	private JComboBox<String> jcbName;
	
	private DefaultComboBoxModel<String> productType=new DefaultComboBoxModel<String>();
	private JComboBox<String> jcbType=new JComboBox<String>( productType);
	
	//Button
	private JButton checkBtn=new JButton("조회") ; 
	private JButton monthCheckBtn=new JButton(
			"<HTML>월별매출<br>&nbsp;&nbsp;&nbsp;&nbsp;조회</HTML>"); 
	private JButton dayCheckBtn=new JButton(
			"<HTML>일일매출<br>&nbsp;&nbsp;&nbsp;&nbsp;조회</HTML>"); 
	
	//Area
	private JLabel totalLabel;
	
	//Panel
	private JPanel  salePeriodJp=new JPanel();
	private JPanel salesJp=new JPanel();
	
	public AdSalesView() {
		
		jcbName=new JComboBox<String>( nameType);
		totalLabel = new JLabel();
		JPanel panel = new JPanel();
		panel.add(totalLabel);
		panel.setBorder(new LineBorder(Color.black));
		
		//String, Scrollpane
		String[]  salesDetail = {"종류","상품","Ice/Hot","사이즈","옵션", "수량", "금액", "판매일시"};
		salesInfoTm = new DefaultTableModel(null, salesDetail);
		salesInfo = new JTable(salesInfoTm);
		JScrollPane salesInfoJsp = new JScrollPane(salesInfo);
		
		String defaultOption = "상품종류를 선택해주세요";
		productType.addElement(defaultOption);
		jcbType.setSelectedItem(defaultOption);
		
		//컴포넌트 위치설정, 추가
		setLayout(null);
		setVisible(true);
		
		//Event
		AdSalesEvt adSalesEvt = new AdSalesEvt(this);
		checkBtn.addActionListener(adSalesEvt);
		dayCheckBtn.addActionListener(adSalesEvt);
		monthCheckBtn.addActionListener(adSalesEvt);
		jcbName.addItemListener(adSalesEvt);
		
		//Table column 넓이 설정
		TableColumn col1 = salesInfo.getColumnModel().getColumn(0);
		TableColumn col2 = salesInfo.getColumnModel().getColumn(1);
		TableColumn col3 = salesInfo.getColumnModel().getColumn(2);
		TableColumn col4 = salesInfo.getColumnModel().getColumn(3);
		TableColumn col5 = salesInfo.getColumnModel().getColumn(4);
		TableColumn col6 = salesInfo.getColumnModel().getColumn(5);
		TableColumn col7 = salesInfo.getColumnModel().getColumn(6);
		col1.setPreferredWidth(20);
		col2.setPreferredWidth(50);
		col3.setPreferredWidth(20);
		col4.setPreferredWidth(20);
		col5.setPreferredWidth(50);
		col6.setPreferredWidth(15);
		col7.setPreferredWidth(30);

		
		salesJp.setBounds(0,0,900,1000);
		salePeriodJp.setBounds(480,30,210,100);
		
		//폰트 설정
		Font labelFont = new Font("맑은고딕",Font.BOLD,20);
		Font totalFont = new Font("맑은고딕",Font.BOLD,50);
		salesInfoJlb.setFont(labelFont);
		totalJlb.setFont(labelFont);
		totalLabel.setFont(totalFont);
		
		//라벨
		salesInfoJlb.setBounds(45,35,200,35);
		salesInfoJsp.setBounds(10,80,450,700);
		
		startJlb.setBounds(20,30,70,30);
		endJlb.setBounds(130,30,70,30);
		periodJlb.setBounds(105,60,20,20);
		startJtf.setBounds(20,60,70,30);
		endJtf.setBounds(130,60,70,30);
		
		nameJlb.setBounds(480,160,50,30);
		typeJlb.setBounds(480,220,50,30);
		
		jcbName.setBounds(480,190,210,30);
		jcbType.setBounds(480,250,210,30);
		
		checkBtn.setBounds(480, 300, 210, 50);
		
		monthCheckBtn.setBounds(490, 400, 90, 70);
		dayCheckBtn.setBounds(600, 400, 90, 70);
		
		totalJlb.setBounds(480,640,150,30);
		panel.setBounds(480,680,200,100);
		
		//테이블 폰트설정
		salesInfo.setFont(new Font("맑은고딕",Font.BOLD,12));
		salesJp.add(salesInfoJlb);
		salesJp.add(salesInfoJsp);
		
		salePeriodJp.setBorder(new TitledBorder("판매기간"));
		salePeriodJp.setLayout(null);
		
		salePeriodJp.add(startJtf);
		salePeriodJp.add(endJtf);
		salePeriodJp.add(startJlb);
		salePeriodJp.add(periodJlb);
		salePeriodJp.add(endJlb);
		
		salesJp.setBorder(new TitledBorder("매출관리"));
		salesJp.setLayout(null);
		
		salesJp.add(salePeriodJp);
		
		salesJp.add(nameJlb);
		salesJp.add(typeJlb);
		salesJp.add(jcbName);
		salesJp.add(jcbType);
		
		salesJp.add(checkBtn);
		salesJp.add(monthCheckBtn);
		salesJp.add(dayCheckBtn);
		
		salesJp.add(totalJlb);
		salesJp.add(panel);
		
		add(salesJp);
		
		
	}//AdSalesView

	public JLabel getSalesInfoJlb() {
		return salesInfoJlb;
	}


	public JTable getSalesInfo() {
		return salesInfo;
	}


	public DefaultTableModel getSalesInfoTm() {
		return salesInfoTm;
	}


	public JTextField getStartJtf() {
		return startJtf;
	}


	public JTextField getEndJtf() {
		return endJtf;
	}


	public JLabel getStartJlb() {
		return startJlb;
	}


	public JLabel getEndJlb() {
		return endJlb;
	}


	public JLabel getPeriodJlb() {
		return periodJlb;
	}


	public JLabel getNameJlb() {
		return nameJlb;
	}


	public JLabel getTypeJlb() {
		return typeJlb;
	}


	public DefaultComboBoxModel<String> getNameType() {
		return nameType;
	}


	public JComboBox<String> getJcbName() {
		return jcbName;
	}


	public DefaultComboBoxModel<String> getProductType() {
		return productType;
	}


	public JComboBox<String> getJcbType() {
		return jcbType;
	}


	public JButton getCheckBtn() {
		return checkBtn;
	}


	public JButton getMonthCheckBtn() {
		return monthCheckBtn;
	}


	public JButton getDayCheckBtn() {
		return dayCheckBtn;
	}


	public JLabel getTotalJlb() {
		return totalJlb;
	}


	public JLabel getTotalLabel() {
		return totalLabel;
	}

	public JPanel getSalePeriodJp() {
		return salePeriodJp;
	}


	public JPanel getSalesJp() {
		return salesJp;
	}


	public static void main(String[] args) {
		new AdSalesView();
	}//main
}//class
