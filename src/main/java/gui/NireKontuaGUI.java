package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Bezero;
import domain.Event;
import domain.Puja;
import domain.Subasta;
import domain.User;
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
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


public class NireKontuaGUI extends JFrame {

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
	private DefaultListModel<String> listIrabazitakoakModel = new DefaultListModel<String>();

	private JList<String> listSubastaAktiboak;
	private JList<String> listIrabazitakoSubastak;

	private static User user;


	/**
	 * This is the default constructor
	 */
	public NireKontuaGUI() {
		super();
		setTitle("Nire kontua"); //$NON-NLS-1$ //$NON-NLS-2$

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
		this.setSize(721, 585);
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

			jContentPane = new JPanel();
			jContentPane.setLayout(null);

			JLabel lblSubastaGuztiak = new JLabel("SUBASTA AKTIBOAK"); //$NON-NLS-1$ //$NON-NLS-2$
			lblSubastaGuztiak.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSubastaGuztiak.setHorizontalAlignment(SwingConstants.CENTER);
			lblSubastaGuztiak.setBounds(34, 107, 306, 26);
			jContentPane.add(lblSubastaGuztiak);

			JLabel lblSubastarenPujak = new JLabel("IRABAZITAKO SUBASTAK"); //$NON-NLS-1$ //$NON-NLS-2$
			lblSubastarenPujak.setHorizontalAlignment(SwingConstants.CENTER);
			lblSubastarenPujak.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSubastarenPujak.setBounds(364, 107, 306, 26);
			jContentPane.add(lblSubastarenPujak);

			Bezero bezero = (Bezero)user;

			int i = 1;
			String subasta = null;
			
			System.out.println(bezero.getSubastaList());
			if(!bezero.getSubastaList().isEmpty()) {
				for(Subasta s : bezero.getSubastaList()) {
					System.out.println(s.toString());
					if(!s.getBukatuta()) {
						subasta = s.getDescription()+" - azkeneko puja: "+s.getAzkenekoPuja()+" euro";
						listSubastakModel.addElement(i+" : "+subasta);
						i++;
					}

				}
			} else {
				listSubastakModel.addElement("MOMENTUZ EZ DUZU SUBASTA AKTIBORIK");
			}

			listSubastaAktiboak = new JList<String>(listSubastakModel);
			listSubastaAktiboak.setBounds(34, 144, 306, 309);
			listSubastaAktiboak.setBorder(BorderFactory.createLineBorder(Color.black));
			jContentPane.add(listSubastaAktiboak);

			int j = 1;

			if(!appFacadeInterface.lortuBezeroarenIrabazitakoak(bezero.getIzena()).isEmpty()) {
				for(String s : appFacadeInterface.lortuBezeroarenIrabazitakoak(bezero.getIzena())) {
					listIrabazitakoakModel.addElement(j+": "+s);
					j++;
				}
			} else {
				listIrabazitakoakModel.addElement("MOMENTUZ EZ DUZU EZER IRABAZI");
			}

			listIrabazitakoSubastak = new JList<String>(listIrabazitakoakModel);
			listIrabazitakoSubastak.setBounds(364, 144, 306, 309);
			listIrabazitakoSubastak.setBorder(BorderFactory.createLineBorder(Color.black));
			jContentPane.add(listIrabazitakoSubastak);


			JButton btnAtzera = new JButton("ATZERA"); //$NON-NLS-1$ //$NON-NLS-2$
			btnAtzera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MenuaBezeroGUI a = new MenuaBezeroGUI();
					a.setVisible(true);
					setVisible(false);
				}
			});
			btnAtzera.setBounds(277, 481, 153, 35);
			jContentPane.add(btnAtzera);

			JLabel lblNireKontua = new JLabel("NIRE KONTUA");
			lblNireKontua.setHorizontalAlignment(SwingConstants.CENTER);
			lblNireKontua.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNireKontua.setBounds(10, 11, 685, 26);
			jContentPane.add(lblNireKontua);

			JLabel lblUserName = new JLabel(bezero.getIzena());
			lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
			lblUserName.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblUserName.setBounds(12, 57, 342, 26);
			jContentPane.add(lblUserName);

			JLabel lblKreditua = new JLabel("KREDITUA:");
			lblKreditua.setHorizontalAlignment(SwingConstants.CENTER);
			lblKreditua.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblKreditua.setBounds(286, 57, 284, 26);
			jContentPane.add(lblKreditua);

			JLabel lblDirua = new JLabel(String.valueOf(bezero.getDirua()));
			lblDirua.setHorizontalAlignment(SwingConstants.CENTER);
			lblDirua.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblDirua.setBounds(390, 57, 284, 26);
			jContentPane.add(lblDirua);


		}
		return jContentPane;
	}
}

