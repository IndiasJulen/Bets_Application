package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Admin;
import domain.Bezero;
import domain.Event;
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


public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final JPanel errorPanel = new JPanel();

	private JPanel jContentPane = null;
	private JButton jButtonErregistratu = null;
	private JButton jButtonLogin = null;

	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}

	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JTextField textFieldErabiltzailea;
	private JPasswordField passwordField;

	//GUi guztietan user atributu estatikoa sortu; beti ze user den jakiteko
	private static User user;
	public static void setUser(User user1){
		user=user1;
	}
	public static User getUser(){
		return user;
	}

	/**
	 * This is the default constructor
	 */
	public MainGUI() {
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
		this.setSize(495, 311);
		this.setContentPane(getJContentPane());
		this.setTitle("Login");
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
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton2());

			JLabel lblErabiltzailea = new JLabel("Erabiltzailea"); //$NON-NLS-1$ //$NON-NLS-2$
			lblErabiltzailea.setBounds(31, 66, 97, 33);
			jContentPane.add(lblErabiltzailea);

			JLabel lblPasahitza = new JLabel("Pasahitza"); //$NON-NLS-1$ //$NON-NLS-2$
			lblPasahitza.setBounds(31, 110, 97, 33);
			jContentPane.add(lblPasahitza);

			textFieldErabiltzailea = new JTextField();
			textFieldErabiltzailea.setBounds(250, 65, 190, 26);
			jContentPane.add(textFieldErabiltzailea);
			textFieldErabiltzailea.setColumns(10);

			passwordField = new JPasswordField();
			passwordField.setBounds(250, 113, 190, 26);
			jContentPane.add(passwordField);
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonErregistratu == null) {
			jButtonErregistratu = new JButton(); 
			jButtonErregistratu.setBounds(32, 181, 125, 53);
			jButtonErregistratu.setText("Erregistratu");
			jButtonErregistratu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new ErregistratuGui(); //a q ventana va ir
					a.setVisible(true);
					setVisible(false);
				}
			});
		}
		return jButtonErregistratu;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonLogin == null) {
			jButtonLogin = new JButton();
			jButtonLogin.setBounds(315, 181, 125, 53);
			jButtonLogin.setText("Login");
			jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					User user1 = appFacadeInterface.loginEgin(textFieldErabiltzailea.getText(), new String(passwordField.getPassword()));
					String password = new String(passwordField.getPassword());
					if(password.equals(user1.getPass())) {
						if(user1 instanceof Bezero) {
							JFrame a = new MenuaBezeroGUI(); //a q ventana va ir
							a.setVisible(true);
						} else if(user1 instanceof Admin) {
							JFrame a = new LangileGUI(); //a q ventana va ir
							a.setVisible(true);
						}
						user = user1;
						setVisible(false);
					} else {
						JOptionPane.showMessageDialog(errorPanel, "Daturen bat gaizki sartu duzu.", "Error", JOptionPane.ERROR_MESSAGE);
						passwordField.setText(null);
					}
				}
			});
		}
		return jButtonLogin;
	}


	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel("Login egin");
			jLabelSelectOption.setBounds(0, 0, 495, 53);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}

	/*private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}*/
} // @jve:decl-index=0:visual-constraint="0,0"

