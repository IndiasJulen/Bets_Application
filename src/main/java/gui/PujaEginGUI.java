package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;


import domain.Bezero;

import domain.Puja;
import domain.Subasta;
import domain.User;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class PujaEginGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}

	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}

	private JLabel lblPujaEgin;
	private JLabel lblSubastaAktiboak;
	private JTextField textFieldKant;

	private JList listSubastak;
	private DefaultListModel<String> listSubastakModel = new DefaultListModel<String>();

	private static User user;

	private final JPanel errorPanel = new JPanel();

	/**
	 * This is the default constructor
	 */
	public PujaEginGUI() {
		super();
		setTitle("Puja egin"); //$NON-NLS-1$ //$NON-NLS-2$

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
		this.setSize(532, 463);
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
			user = MainGUI.getUser();

			Bezero bezero = (Bezero)user;
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblPujaEgin());
			jContentPane.add(getLblSubastaAktiboak());

			JButton btnAtzera = new JButton("Atzera"); //$NON-NLS-1$ //$NON-NLS-2$
			btnAtzera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MenuaBezeroGUI a = new MenuaBezeroGUI();
					a.setVisible(true);
					setVisible(false);
				}
			});
			btnAtzera.setBounds(30, 371, 91, 29);
			jContentPane.add(btnAtzera);

			JLabel lblPujaKant = new JLabel("Puja kantitatea"); //$NON-NLS-1$ //$NON-NLS-2$
			lblPujaKant.setBounds(151, 377, 122, 16);
			jContentPane.add(lblPujaKant);

			textFieldKant = new JTextField();
			textFieldKant.setBounds(247, 372, 68, 26);
			jContentPane.add(textFieldKant);
			textFieldKant.setColumns(10);

			ArrayList<Subasta> lista = new ArrayList<Subasta>();

			JButton btnPujaEgin = new JButton("Puja egin"); //$NON-NLS-1$ //$NON-NLS-2$
			btnPujaEgin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(!textFieldKant.getText().equals(null)) {
							int pujatutakoKop = Integer.parseInt(textFieldKant.getText());

							String[] selected = String.valueOf(listSubastak.getSelectedValue()).split(":");
							String index = selected[0].trim();
							Subasta subasta = lista.get(Integer.parseInt(index) - 1);

							if(bezero.getDirua() < pujatutakoKop) {
								JOptionPane.showMessageDialog(errorPanel, "Ez daukazu nahiko dirurik puja egiteko.", "Error", JOptionPane.ERROR_MESSAGE);
							} else {
								if(subasta.getAzkenekoPuja() > pujatutakoKop) {
									JOptionPane.showMessageDialog(errorPanel, "Egindako azkeneko puja zurea baino handiagoa da.", "Error", JOptionPane.ERROR_MESSAGE);
								} else {

									int pujaZenb = appFacadeInterface.pujaGuztiakLortu().size() + 1;;
									Puja puja = new Puja(pujaZenb, pujatutakoKop, subasta, bezero);

									subasta.gehituPuja(puja);
									bezero.gehituPuja(puja);

									appFacadeInterface.eguneratuSubasta(subasta);
									appFacadeInterface.eguneratuBezeroa(bezero);

									user = bezero;
								}
							}

						} else {
							JOptionPane.showMessageDialog(errorPanel, "Puja egiteko kantitate bat sartu behar duzu.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(errorPanel, "Puja zenbaki bat izan behar da.", "Error", JOptionPane.ERROR_MESSAGE);
					} finally {
						textFieldKant.setText(null);
					}
				}
			});
			btnPujaEgin.setBounds(372, 371, 117, 29);
			jContentPane.add(btnPujaEgin);

			int i = 1;

			for(Subasta s : appFacadeInterface.subastaGuztiakLortu()) {
				if(!s.getJabea().getIzena().equals(bezero.getIzena()) && !s.getBukatuta()) { // listan bakarrik erakusteko guk egin ez ditugun subastak, bestela guk egindakoetan baita puja egin genezake eta gaizki egongo litzake
					lista.add(s);
					listSubastakModel.addElement(i+" : "+s.toString());
					i++;
				}
			}

			listSubastak = new JList<String>(listSubastakModel);
			listSubastak.setBounds(30, 74, 457, 276);
			listSubastak.setBorder(BorderFactory.createLineBorder(Color.black));
			jContentPane.add(listSubastak);
		}
		return jContentPane;
	}


	private JLabel getLblPujaEgin() {
		if (lblPujaEgin == null) {
			lblPujaEgin = new JLabel("PUJA EGIN"); //$NON-NLS-1$ //$NON-NLS-2$
			lblPujaEgin.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblPujaEgin.setHorizontalAlignment(SwingConstants.CENTER);
			lblPujaEgin.setBounds(6, 6, 483, 31);
		}
		return lblPujaEgin;
	}
	private JLabel getLblSubastaAktiboak() {
		if (lblSubastaAktiboak == null) {
			lblSubastaAktiboak = new JLabel("Subasta aktiboak"); //$NON-NLS-1$ //$NON-NLS-2$
			lblSubastaAktiboak.setBounds(206, 48, 108, 16);
		}
		return lblSubastaAktiboak;
	}

}

