package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
import domain.Puja;
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
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


public class BistaratuSubastakGUI extends JFrame {

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
	private DefaultListModel<String> listPujakModel = new DefaultListModel<String>();
	
	private JList<String> listSubastak;
	private JList<String> listPujak;


	/**
	 * This is the default constructor
	 */
	public BistaratuSubastakGUI() {
		super();
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("BistaratuSubastakGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$

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
		this.setSize(721, 663);
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
			this.setLocationRelativeTo(null);
			appFacadeInterface = MainGUI.getBusinessLogic();
			jContentPane = new JPanel();
			jContentPane.setLayout(null);

			JLabel lblSubastaGuztiak = new JLabel("SUBASTA GUZTIAK"); //$NON-NLS-1$ //$NON-NLS-2$
			lblSubastaGuztiak.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSubastaGuztiak.setHorizontalAlignment(SwingConstants.CENTER);
			lblSubastaGuztiak.setBounds(10, 19, 685, 26);
			jContentPane.add(lblSubastaGuztiak);

			JLabel lblSubastarenPujak = new JLabel("SUBASTAN EGINDAKO PUJAK"); //$NON-NLS-1$ //$NON-NLS-2$
			lblSubastarenPujak.setHorizontalAlignment(SwingConstants.CENTER);
			lblSubastarenPujak.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSubastarenPujak.setBounds(10, 293, 685, 26);
			jContentPane.add(lblSubastarenPujak);

			ArrayList<Subasta> listaSubastak = new ArrayList<Subasta>();

			int i = 1;

			for(Subasta s : appFacadeInterface.subastaGuztiakLortu()) {
				listaSubastak.add(s);
				listSubastakModel.addElement(i+" : "+s.toString());
				i++;

			}

			ArrayList<Puja> listaPujak = new ArrayList<Puja>();

			listSubastak = new JList<String>(listSubastakModel);
			listSubastak.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					listPujakModel.clear();
					String[] selected = String.valueOf(listSubastak.getSelectedValue()).split(":");
					String index = selected[0].trim();
					Subasta subasta = listaSubastak.get(Integer.parseInt(index) - 1);

					int j = 1;

					for(Puja p : subasta.getPujak()) {
						listaPujak.add(p);
						listPujakModel.addElement(j+" : "+p.toString());
						j++;

					}
				}
			});


			listSubastak.setBounds(34, 59, 633, 207);
			listSubastak.setBorder(BorderFactory.createLineBorder(Color.black));
			jContentPane.add(listSubastak);
			
			listPujak = new JList<String>(listPujakModel);
			listPujak.setBounds(34, 330, 633, 209);
			listPujak.setBorder(BorderFactory.createLineBorder(Color.black));
			jContentPane.add(listPujak);


			JButton btnAtzera = new JButton("ATZERA"); //$NON-NLS-1$ //$NON-NLS-2$
			btnAtzera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MenuaBezeroGUI a = new MenuaBezeroGUI();
					a.setVisible(true);
					setVisible(false);
				}
			});
			btnAtzera.setBounds(278, 561, 153, 35);
			jContentPane.add(btnAtzera);


		}
		return jContentPane;
	}
}

