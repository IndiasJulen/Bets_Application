package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
import domain.Subasta;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;


public class LangileGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	private DefaultListModel<String> listSubastakModel = new DefaultListModel<String>();
	private JList<String> listSubastak;
	
	private final JPanel errorPanel = new JPanel();
	
	/**
	 * This is the default constructor
	 */
	public LangileGUI() {
		super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(664, 435);
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			appFacadeInterface = MainGUI.getBusinessLogic();
			this.setTitle("Langile menu"); 
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			
			JLabel lblSubastak = new JLabel("SUBASTA AKTIBOAK"); 
			lblSubastak.setHorizontalAlignment(SwingConstants.CENTER);
			lblSubastak.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSubastak.setBounds(23, 15, 601, 28);
			jContentPane.add(lblSubastak);
			
			ArrayList<Subasta> lista = new ArrayList<Subasta>();
			
			listakBete(lista);
			
			listSubastak = new JList<String>(listSubastakModel);
			listSubastak.setBorder(BorderFactory.createLineBorder(Color.black));
			listSubastak.setBounds(23, 56, 601, 235);
			jContentPane.add(listSubastak);
			
			JButton btnEzabatu = new JButton("EZABATU");
			btnEzabatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String[] selected = String.valueOf(listSubastak.getSelectedValue()).split(":");
					String index = selected[0].trim();
					Subasta subasta = lista.get(Integer.parseInt(index) - 1);
					
					appFacadeInterface.subastaEzabatu(subasta);
					
					listSubastakModel.clear();
					listakBete(lista);
					
					JOptionPane.showMessageDialog(errorPanel, "\""+subasta.getSubastaId()+"\""+" id-a duen subasta ezabatuta: "+subasta.getDescription(), "Ezabatuta", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			btnEzabatu.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnEzabatu.setBounds(344, 326, 149, 36);
			jContentPane.add(btnEzabatu);
			
			JButton btnSesioaItxi = new JButton("SESIOA ITXI"); 
			btnSesioaItxi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainGUI a = new MainGUI();
					a.setVisible(true);
					setVisible(false);
				}
			});
			btnSesioaItxi.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnSesioaItxi.setBounds(136, 326, 149, 36);
			jContentPane.add(btnSesioaItxi);
		}
		return jContentPane;
	}
	
	private void listakBete(ArrayList<Subasta> lista) {
		int i = 1;
		
		for(Subasta s : appFacadeInterface.subastaGuztiakLortu()) {
			if(!s.getBukatuta()) {
				lista.add(s);
				listSubastakModel.addElement(i+" : "+s.toString());
				i++;
			}
		}
	}
}

