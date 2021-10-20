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


public class MenuaBezeroGUI extends JFrame {
	
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
	public MenuaBezeroGUI() {
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
		this.setSize(512, 401);
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
			this.setTitle("Menu");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			
			JLabel lblSubastak = new JLabel("SUBASTAK"); //$NON-NLS-1$ //$NON-NLS-2$
			lblSubastak.setHorizontalAlignment(SwingConstants.CENTER);
			lblSubastak.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSubastak.setBounds(6, 6, 483, 28);
			jContentPane.add(lblSubastak);
			
			JButton btnSubastaSortu = new JButton("SUBASTA SORTU"); //$NON-NLS-1$ //$NON-NLS-2$
			btnSubastaSortu.setBounds(33, 80, 183, 76);
			jContentPane.add(btnSubastaSortu);
			btnSubastaSortu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					SubastaSortuGUI a = new SubastaSortuGUI();
					a.setVisible(true);
					setVisible(false);
				}
			});
			
			JButton btnSubastaGuztiak = new JButton("SUBASTA GUZTIAK IKUSI"); //$NON-NLS-1$ //$NON-NLS-2$
			btnSubastaGuztiak.setBounds(281, 80, 183, 76);
			jContentPane.add(btnSubastaGuztiak);
			btnSubastaGuztiak.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					BistaratuSubastakGUI a = new BistaratuSubastakGUI();
					a.setVisible(true);
					setVisible(false);
				}
			});
			
			JButton btnPujaEgin = new JButton("PUJA EGIN"); //$NON-NLS-1$ //$NON-NLS-2$
			btnPujaEgin.setBounds(33, 166, 183, 76);
			jContentPane.add(btnPujaEgin);
			btnPujaEgin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					PujaEginGUI a = new PujaEginGUI();
					a.setVisible(true);
					setVisible(false);
				}
			});
			
			JButton btnDiruaKudeatu = new JButton("DIRUA KUDEATU"); //$NON-NLS-1$ //$NON-NLS-2$
			btnDiruaKudeatu.setBounds(281, 166, 183, 76);
			jContentPane.add(btnDiruaKudeatu);
			
			JButton btnSesioaItxi = new JButton("SESIOA ITXI");
			btnSesioaItxi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainGUI a = new MainGUI();
					a.setVisible(true);
					setVisible(false);
				}
			});
			btnSesioaItxi.setBounds(33, 253, 183, 76);
			jContentPane.add(btnSesioaItxi);
			
			JButton btnNireKontua = new JButton("NIRE KONTUA");
			btnNireKontua.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NireKontuaGUI a = new NireKontuaGUI();
					a.setVisible(true);
					setVisible(false);
				}
			});
			btnNireKontua.setBounds(281, 253, 183, 76);
			jContentPane.add(btnNireKontua);
			btnDiruaKudeatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DiruKudeaketaGUI a = new DiruKudeaketaGUI();
					a.setVisible(true);
					setVisible(false);
				}
			});
		}
		return jContentPane;
	}
	
} 

