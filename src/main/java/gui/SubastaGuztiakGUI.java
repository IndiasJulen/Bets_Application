package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
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


public class SubastaGuztiakGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	/**
	 * This is the default constructor
	 */
	public SubastaGuztiakGUI() {
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
			
			JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SubastaGuztiakGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(6, 6, 483, 30);
			jContentPane.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SubastaGuztiakGUI.lblNewLabel_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblNewLabel_1.setBounds(42, 66, 61, 16);
			jContentPane.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SubastaGuztiakGUI.lblNewLabel_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblNewLabel_2.setBounds(269, 66, 88, 16);
			jContentPane.add(lblNewLabel_2);
			
			JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SubastaGuztiakGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton.setBounds(413, 48, 82, 29);
			jContentPane.add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MenuaBezeroGUI a = new MenuaBezeroGUI();
					a.setVisible(true);
				}
			});
		}
		return jContentPane;
	}
	
	/*private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}*/
} // @jve:decl-index=0:visual-constraint="0,0"

