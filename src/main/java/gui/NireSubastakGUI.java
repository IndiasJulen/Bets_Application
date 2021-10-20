package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Bezero;
import domain.Event;
import domain.Subasta;
import domain.User;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;


public class NireSubastakGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	private User user;
	
	/**
	 * This is the default constructor
	 */
	public NireSubastakGUI() {
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
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 290);
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
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			user = MainGUI.getUser();
			
			JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("NireSubastakGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(6, 6, 483, 25);
			jContentPane.add(lblNewLabel);
			
			JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("NireSubastakGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton.setBounds(407, 43, 82, 29);
			jContentPane.add(btnNewButton);

			DefaultListModel<String> subastakListModel = new DefaultListModel<String>();
	
			Bezero bezero = (Bezero)user;
			
			for(Subasta s: bezero.getSubastaList()) {
				subastakListModel.addElement(s.toString());//ir añadiendo las subastas en su forma de string, para que sea más fácil de ver
			}
			
			JList subastakList = new JList(subastakListModel);
			subastakList.setBounds(45, 43, 347, 170);
			JScrollPane subastakScrollpane = new JScrollPane(subastakList);
			jContentPane.add(subastakScrollpane);
			
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MenuaBezeroGUI a = new MenuaBezeroGUI();
					a.setVisible(true);
				}
			});
		}
		return jContentPane;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

