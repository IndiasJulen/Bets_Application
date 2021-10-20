package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

@Entity @XmlAccessorType(XmlAccessType.FIELD)
public class Subasta {
	private String Description;
	@Id
	int subastaId;
	private Date DataHas;
	private Date DataBuk;
	private int minPuja;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	private ArrayList<Puja> pujak;
	boolean bukatuta;
	@XmlIDREF
	private Bezero jabea;

	public Subasta(String description, int subastaId, Date dataHas, Date dataBuk, int minPuja, Bezero jabea) {
		this.Description = description;
		this.subastaId = subastaId;
		this.DataHas = dataHas;
		this.DataBuk = dataBuk;
		this.minPuja = minPuja;
		this.pujak = new ArrayList<Puja>();
		this.bukatuta = false;
		this.jabea = jabea;
	}

	public Date getDataHas() {
		return DataHas;
	}

	public void setDataHas(Date dataHas) {
		DataHas = dataHas;
	}

	public Date getDataBuk() {
		return DataBuk;
	}

	public void setDataBuk(Date dataBuk) {
		DataBuk = dataBuk;
	}

	public int getMinPuja() {
		return minPuja;
	}

	public void setMinPuja(int minPuja) {
		this.minPuja = minPuja;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public ArrayList<Puja> getPujak() {
		return pujak;
	}

	public void setPujak(ArrayList<Puja> pujak) {
		this.pujak = pujak;
	}

	public void setJabea(Bezero jabea) {
		this.jabea = jabea;
	}

	public Bezero getJabea() {
		return this.jabea;
	}

	@Override
	public String toString() {
		if(!this.bukatuta) {
			return this.Description+" - jabea: "+this.jabea.getIzena()+" - puja handiena: "+this.getAzkenekoPuja()+"E - bukaera: "+this.DataBuk.toString();
		} else {
			return this.Description+" - jabea: "+this.jabea.getIzena()+" - puja handiena: "+this.getAzkenekoPuja()+"E - bukaera: "+this.DataBuk.toString()+" - BUKATUTA";
		}
	}

	public void gehituPuja(Puja puja) {
		this.pujak.add(puja);
	}


	public void bukatu() {
		this.bukatuta = true;
	}

	public int getSubastaId() {
		return this.subastaId;
	}

	public void eguneratu(Subasta subasta) {
		this.pujak = subasta.getPujak();
	}

	public int getAzkenekoPuja() {
		if(!this.pujak.isEmpty()) {
			return this.pujak.get(this.pujak.size() - 1).getPrezioa();
		} else {
			return this.minPuja;
		}
	}

	public boolean getBukatuta() {
		return this.bukatuta;
	}

	public String getIrabaziDuenak() {
		if(!this.pujak.isEmpty()) {
			return this.pujak.get(this.pujak.size()-1).getBezero().getIzena();
		} else {
			return null;
		}
	}



}
