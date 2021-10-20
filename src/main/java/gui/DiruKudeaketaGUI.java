package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

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


public class DiruKudeaketaGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}

	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldDirua;
	private JLabel lblDiruaKudeatu;
	private JButton btnSartu;
	private JButton btnAtera;
	private JButton btnAtzera;

	private JLabel lblDirua;

	private User user;

	private final JPanel errorPanel = new JPanel();

	/**
	 * This is the default constructor
	 */
	public DiruKudeaketaGUI() {
		super();
		setTitle("Dirua kudeatu");

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
		this.setSize(525, 303);
		this.appFacadeInterface = MainGUI.getBusinessLogic();
		this.user = MainGUI.getUser();
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

			JLabel lblDiruKant = new JLabel("Diru kantitatea"); //$NON-NLS-1$ //$NON-NLS-2$
			lblDiruKant.setBounds(68, 89, 97, 33);
			jContentPane.add(lblDiruKant);

			textFieldDirua = new JTextField();
			textFieldDirua.setBounds(250, 92, 183, 26);
			jContentPane.add(textFieldDirua);
			textFieldDirua.setColumns(10);
			jContentPane.add(getLblDiruaKudeatu());
			jContentPane.add(getBtnSartu());
			jContentPane.add(getBtnAtera());
			jContentPane.add(getBtnAtzera());
			

			JLabel lblSaldoa = new JLabel("Saldoa:"); 
			lblSaldoa.setBounds(68, 141, 61, 16);
			jContentPane.add(lblSaldoa);

			Bezero bezero = (Bezero)user;
			lblDirua = new JLabel();
			lblDirua.setText(String.valueOf(bezero.getDirua()));
			lblDirua.setBounds(156, 141, 61, 16);
			jContentPane.add(lblDirua);

		}
		return jContentPane;
	}


	private JLabel getLblDiruaKudeatu() {
		if (lblDiruaKudeatu == null) {
			lblDiruaKudeatu = new JLabel("DIRUA KUDEATU");
			lblDiruaKudeatu.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblDiruaKudeatu.setHorizontalAlignment(SwingConstants.CENTER);
			lblDiruaKudeatu.setBounds(6, 6, 483, 33);
		}
		return lblDiruaKudeatu;
	}
	private JButton getBtnSartu() {
		if (btnSartu == null) {
			btnSartu = new JButton("Sartu"); 
			btnSartu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(!textFieldDirua.getText().equals(null)) {
							int dirua = Integer.parseInt(textFieldDirua.getText());
							Bezero bezero = (Bezero)user;
							bezero.setDirua(bezero.getDirua() + dirua);
							appFacadeInterface.eguneratuDirua(user.getIzena(), bezero.getDirua());
							user = bezero;
							lblDirua.setText(String.valueOf(bezero.getDirua()));
						} else {
							JOptionPane.showMessageDialog(errorPanel, "Sartu nahi duzun diru kantitatea jarri mesedez.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(errorPanel, "Zenbaki bat sartu mesedez.", "Error", JOptionPane.ERROR_MESSAGE);
					} finally {
						textFieldDirua.setText(null);
					}
				}
			});
			btnSartu.setBounds(316, 194, 117, 33);
		}
		return btnSartu;
	}

	private JButton getBtnAtera() {
		if (btnAtera == null) {
			btnAtera = new JButton("Atera"); //$NON-NLS-1$ //$NON-NLS-2$
			btnAtera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(!textFieldDirua.getText().equals(null)) {
							int dirua = Integer.parseInt(textFieldDirua.getText());
							Bezero bezero = (Bezero)user;
							if(bezero.getDirua() < dirua) {
								JOptionPane.showMessageDialog(errorPanel, "Atera nahi duzuna baino diru gutxiago duzu.", "Error", JOptionPane.ERROR_MESSAGE);
							} else {
								bezero.setDirua(bezero.getDirua() - dirua);
								appFacadeInterface.eguneratuDirua(user.getIzena(), bezero.getDirua());
								user = bezero;
								lblDirua.setText(String.valueOf(bezero.getDirua()));
							}
						} else {
							JOptionPane.showMessageDialog(errorPanel, "Atera nahi duzun diru kantitatea jarri mesedez.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(errorPanel, "Zenbaki bat sartu mesedez.", "Error", JOptionPane.ERROR_MESSAGE);
					} finally {
						textFieldDirua.setText(null);
					}
				}
			});
			btnAtera.setBounds(187, 194, 117, 33);
		}
		return btnAtera;
	}

	private JButton getBtnAtzera() {
		if (btnAtzera == null) {
			btnAtzera = new JButton("Atzera joan"); //$NON-NLS-1$ //$NON-NLS-2$
			btnAtzera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MenuaBezeroGUI a = new MenuaBezeroGUI();
					a.setVisible(true);
					setVisible(false);
				}
			});
			btnAtzera.setBounds(68, 195, 108, 31);
		}
		return btnAtzera;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

