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


public class ErregistratuGui extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private final JPanel errorPanel = new JPanel();

	private JPanel jContentPane = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	private JTextField textFieldErabiltzailea;
	private JPasswordField passwordField;
	private JLabel lblErregistratu;
	private JButton btnErregistratu;
	private JButton btnErregistratuta;
	private JLabel lblErabiltzailea;
	
	/**
	 * This is the default constructor
	 */
	public ErregistratuGui() {
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
			appFacadeInterface = MainGUI.getBusinessLogic();
			this.setTitle("Erregistratu");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			
			lblErabiltzailea = new JLabel("erabiltzailea"); //$NON-NLS-1$ //$NON-NLS-2$
			lblErabiltzailea.setBounds(21, 65, 125, 33);
			jContentPane.add(lblErabiltzailea);
			
			JLabel lblPasahitza = new JLabel("pasahitza"); //$NON-NLS-1$ //$NON-NLS-2$
			lblPasahitza.setBounds(21, 110, 97, 33);
			jContentPane.add(lblPasahitza);
			
			textFieldErabiltzailea = new JTextField();
			textFieldErabiltzailea.setBounds(250, 65, 190, 26);
			jContentPane.add(textFieldErabiltzailea);
			textFieldErabiltzailea.setColumns(10);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(250, 113, 180, 26);
			jContentPane.add(passwordField);
			jContentPane.add(getLblErregistratu());
			jContentPane.add(getBtnErregistratu());
			jContentPane.add(getBtnErregistratuta());
		}
		return jContentPane;
	}
	
	
	private JLabel getLblErregistratu() {
		if (lblErregistratu == null) {
			lblErregistratu = new JLabel("ERREGISTRATU");
			lblErregistratu.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblErregistratu.setHorizontalAlignment(SwingConstants.CENTER);
			lblErregistratu.setBounds(6, 6, 483, 33);
		}
		return lblErregistratu;
	}
	private JButton getBtnErregistratu() {
		if (btnErregistratu == null) {
			btnErregistratu = new JButton("Erregistratu");
			btnErregistratu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String erabiltzailea = textFieldErabiltzailea.getText();
					String pasahitza = new String(passwordField.getPassword());
					if(appFacadeInterface.erregistratu(erabiltzailea, pasahitza)) {
						JOptionPane.showMessageDialog(errorPanel, "Erabiltzaile hori jadanik existitzen da.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					textFieldErabiltzailea.setText(null);
					passwordField.setText(null);
				}
			});
			btnErregistratu.setBounds(313, 183, 117, 40);
			
		}
		return btnErregistratu;
	}
	private JButton getBtnErregistratuta() {
		if (btnErregistratuta == null) {
			btnErregistratuta = new JButton("Erregistratuta nago"); //$NON-NLS-1$ //$NON-NLS-2$
			btnErregistratuta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainGUI a = new MainGUI();
					a.setVisible(true);
					setVisible(false);
				}
			});
			btnErregistratuta.setBounds(21, 183, 145, 40);
		}
		return btnErregistratuta;
	}
	
	/*private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}*/
} // @jve:decl-index=0:visual-constraint="0,0"

