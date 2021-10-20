package gui;

import java.text.DateFormat;
import java.util.*;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Bezero;
import domain.Event;
import domain.Subasta;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class SubastaSortuGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();
	private JLabel jLabelSubastaData = new JLabel("Subastaren bukaera data");
	private JCalendar jCalendar = new JCalendar();


	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JTextField textFieldObjektua;
	private JTextField textFieldPrezioa;

	private static BLFacade facade;
	private static User user;

	private final JPanel errorPanel = new JPanel();

	public SubastaSortuGUI() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		user = MainGUI.getUser();
		facade = MainGUI.getBusinessLogic();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		this.setSize(604, 273);
		this.setTitle("Subasta sortu");
		
		Bezero bezero = (Bezero)user;

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		this.getContentPane().add(jCalendar, null);

		jLabelSubastaData.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelSubastaData.setBounds(40, 16, 225, 25);
		getContentPane().add(jLabelSubastaData);

		JLabel lblObjektua = new JLabel("Objektua"); //$NON-NLS-1$ //$NON-NLS-2$
		lblObjektua.setBounds(new Rectangle(40, 15, 140, 25));
		lblObjektua.setBounds(289, 50, 69, 25);
		getContentPane().add(lblObjektua);

		textFieldObjektua = new JTextField();
		textFieldObjektua.setBounds(433, 52, 110, 23);
		getContentPane().add(textFieldObjektua);
		textFieldObjektua.setColumns(10);

		JLabel lblPrezioa = new JLabel("Hasiera prezioa"); //$NON-NLS-1$ //$NON-NLS-2$
		lblPrezioa.setBounds(new Rectangle(40, 15, 140, 25));
		lblPrezioa.setBounds(289, 101, 94, 25);
		getContentPane().add(lblPrezioa);

		textFieldPrezioa = new JTextField();
		textFieldPrezioa.setBounds(433, 101, 110, 25);
		getContentPane().add(textFieldPrezioa);
		textFieldPrezioa.setColumns(10);

		JButton btnSortu = new JButton("Sortu"); 
		btnSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date bukaeraData = jCalendar.getDate();
					long millis=System.currentTimeMillis();  
					java.util.Date currentDate=new java.util.Date(millis); 

					String objektua = textFieldObjektua.getText();
					int prezioa = Integer.parseInt(textFieldPrezioa.getText());

					if(bukaeraData.compareTo(currentDate) <= 0) {
						JOptionPane.showMessageDialog(errorPanel, "Gaurko data edo lehenagokoa ezin duzu aukeratu.", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						int subastaId = facade.subastaGuztiakLortu().size() + 1;
						Subasta subasta = new Subasta(objektua, subastaId, currentDate, bukaeraData, prezioa, bezero);
						bezero.gehituSubasta(subasta);
						facade.eguneratuBezeroa(bezero);
						user = bezero;
						textFieldObjektua.setText(null);
						textFieldPrezioa.setText(null);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(errorPanel, "Prezioa zenbaki bat izan behar da.", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSortu.setBounds(433, 166, 110, 34);
		getContentPane().add(btnSortu);

		JButton btnAtzera = new JButton("Atzera"); //$NON-NLS-1$ //$NON-NLS-2$
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuaBezeroGUI a = new MenuaBezeroGUI();
				a.setVisible(true);
				setVisible(false);
			}
		});
		btnAtzera.setBounds(290, 166, 110, 34);
		getContentPane().add(btnAtzera);

	}
}