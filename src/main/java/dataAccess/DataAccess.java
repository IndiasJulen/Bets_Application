package dataAccess;

import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.Bezero;
import domain.Event;
import domain.Puja;
import domain.Question;
import domain.Subasta;
import domain.User;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

	public DataAccess(boolean initializeMode)  {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);

	}

	public DataAccess()  {	
		this(false);
	}


	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){

		db.getTransaction().begin();
		try {


			Calendar today = Calendar.getInstance();

			int month=today.get(Calendar.MONTH);
			int year=today.get(Calendar.YEAR);

			//Sortu user batzuk
			Bezero b = new Bezero("user", "user");
			Bezero b1 = new Bezero("user1", "user1");

			Admin admin = new Admin("admin", "admin");

			Subasta s1 = new Subasta("Surf tabla", 1, UtilDate.newDate(year, month, 1), UtilDate.newDate(year,month+1,1), 400, b1);
			Subasta s2 = new Subasta("Play Station 5", 2, UtilDate.newDate(year, month, 1), UtilDate.newDate(year,month+1,1), 450, b1);
			Subasta s3 = new Subasta("Mahaigaineko PC-a", 3, UtilDate.newDate(year, month, 1), UtilDate.newDate(year,month+1,1), 500, b);
			Subasta s4 = new Subasta("iPhone 12", 4, UtilDate.newDate(year, month, 1), UtilDate.newDate(year,month+1,1), 700, b);
			Subasta s5 = new Subasta("Rugby baloia", 5, UtilDate.newDate(year, month, 1), UtilDate.newDate(year,month+1,1), 600, b);
			Subasta s6 = new Subasta("Gitarra", 6, UtilDate.newDate(year, month, 1), UtilDate.newDate(year,month,5), 350, b1);
			
			Puja p4 = new Puja(4, 1000, s6, b);
			s6.bukatu();
			
			s6.gehituPuja(p4);

			Puja p1 = new Puja(1, 425, s1, b);
			Puja p2 = new Puja(2, 470, s2, b);
			Puja p3 = new Puja(3, 650, s5, b1);

			s1.gehituPuja(p1);
			s2.gehituPuja(p2);
			s5.gehituPuja(p3);
			
			b.gehituSubasta(s3);
			b.gehituSubasta(s4);
			b.gehituSubasta(s5);
			
			b1.gehituSubasta(s1);
			b1.gehituSubasta(s2);
			b1.gehituSubasta(s6);

			db.persist(p1);
			db.persist(p2);
			db.persist(p3);
			db.persist(p4);

			db.persist(s1);
			db.persist(s2);
			db.persist(s3);
			db.persist(s4);
			db.persist(s5);
			db.persist(s6);

			db.persist(b);
			db.persist(b1);
			db.persist(admin);

			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event

	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		//db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}*/

	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events

	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev:events){
			System.out.println(ev.toString());		 
			res.add(ev);
		}
		return res;
	}*/

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates

	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	

		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);


		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d:dates){
			System.out.println(d.toString());		 
			res.add(d);
		}
		return res;
	}*/


	public void open(boolean initializeMode){

		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			db = emf.createEntityManager();
		}

	}
	/**
	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);

	}*/

	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public User loginEgin(String userName, String pass) {
		db.getTransaction().begin();
		User user = db.find(User.class, userName); //klasea eta gakoa emanda objektua lortu
		db.getTransaction().commit();
		return user;
	}

	public boolean erregistratu(String userName, String pass) {
		db.getTransaction().begin();
		boolean error = false;
		Bezero bezero = new Bezero(userName, pass);
		Bezero bezero1 = db.find(Bezero.class, userName);
		if (bezero1 == null) db.persist(bezero);
		else error = true;
		db.getTransaction().commit();
		return error;
	}

	public void eguneratuDirua(String username, int dirua) {
		db.getTransaction().begin();
		Bezero bezero = db.find(Bezero.class, username);
		bezero.setDirua(dirua);
		db.getTransaction().commit();
	}

	public ArrayList<Subasta> subastaGuztiakLortu() {
		db.getTransaction().begin();
		Query query = db.createQuery("SELECT s FROM Subasta s");
		ArrayList<Subasta> lista = (ArrayList<Subasta>)query.getResultList();
		db.getTransaction().commit();
		return lista;
	}

	public void eguneratuBezeroa(Bezero bezero) {
		db.getTransaction().begin();
		Bezero bezero1 = db.find(Bezero.class, bezero.getIzena());
		bezero1.eguneratuBezeroa(bezero);
		db.persist(bezero1);
		db.getTransaction().commit();
	}

	public ArrayList<Puja> pujaGuztiakLortu() {
		db.getTransaction().begin();
		Query query = db.createQuery("SELECT p FROM Puja p");
		ArrayList<Puja> lista = (ArrayList<Puja>)query.getResultList();
		db.getTransaction().commit();
		return lista;
	}

	public void eguneratuSubasta(Subasta subasta) {
		db.getTransaction().begin();
		Subasta subasta1 = db.find(Subasta.class, subasta.getSubastaId());
		subasta1.eguneratu(subasta);
		db.persist(subasta1);
		System.out.println("AA SUBASTA");
		db.getTransaction().commit();
	}

	public ArrayList<String> lortuBezeroarenIrabazitakoak(String userName) {
		db.getTransaction().begin();
		ArrayList<String> lista = new ArrayList<String>();
		Query query = db.createQuery("SELECT s FROM Subasta s");
		for(Subasta s : (ArrayList<Subasta>)query.getResultList()) {
			if(s.getIrabaziDuenak() != (null)) {
				if(s.getBukatuta() && s.getIrabaziDuenak().equals(userName)) {
					lista.add(s.getDescription()+" "+s.getAzkenekoPuja()+"euro ordainduta.");
				}
			}
		}
		db.getTransaction().commit();
		return lista;
	}
	
	public void subastaEzabatu(Subasta subasta) {
		db.getTransaction().begin();
		Subasta subasta1 = db.find(Subasta.class, subasta.getSubastaId());
		db.remove(subasta1);
		db.getTransaction().commit();
	}
}
