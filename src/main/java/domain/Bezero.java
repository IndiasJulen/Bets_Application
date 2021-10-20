package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Bezero extends User {
	private Integer Dirua;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private ArrayList<Subasta> SubastaList;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private ArrayList<Puja> PujaList;
	
	public Bezero(String Izena, String Pass) {
		super(Izena, Pass);
		this.Dirua = 0;
		this.SubastaList = new ArrayList<Subasta>();
		this.PujaList = new ArrayList<Puja>();
	}

	public int getDirua() {
		return Dirua;
	}

	public void setDirua(Integer dirua) {
		Dirua = dirua;
	}

	public ArrayList<Subasta> getSubastaList() {
		return SubastaList;
	}

	public void setSubastaList(ArrayList<Subasta> subastaList) {
		SubastaList = subastaList;
	}

	public ArrayList<Puja> getPujaList() {
		return PujaList;
	}

	public void setPujaList(ArrayList<Puja> pujaList) {
		PujaList = pujaList;
	}
	
	public void gehituSubasta(Subasta subasta) {
		this.SubastaList.add(subasta);
	}
	
	public void gehituPuja(Puja puja) {
		this.PujaList.add(puja);
	}
	
	public void eguneratuBezeroa(Bezero bezero) {
		this.Dirua = bezero.getDirua();
		this.PujaList = bezero.getPujaList();
		this.SubastaList = bezero.getSubastaList();
	}
	 

}
