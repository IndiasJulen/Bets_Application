package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

@Entity @XmlAccessorType(XmlAccessType.FIELD)
public class Puja {
	
	@Id
	private int pujaZenbakia;
	private int Prezioa;
	@XmlIDREF
	private Subasta subasta;
	@XmlIDREF
	private Bezero bezero;
	
	public Puja(int pujaZenbakia, int prezioa, Subasta subasta, Bezero bezero) {
		this.pujaZenbakia = pujaZenbakia;
		this.Prezioa = prezioa;
		this.subasta = subasta;
		this.bezero = bezero;
	}
	
	public int getPujaZenbakia() {
		return this.pujaZenbakia;
	}
	
	public void setPujaZenbakia(int pujaZenbakia) {
		this.pujaZenbakia = pujaZenbakia;
	}

	public Subasta getSubasta() {
		return subasta;
	}

	public Bezero getBezero() {
		return bezero;
	}

	public int getPrezioa() {
		return Prezioa;
	}

	public void setPrezioa(int prezioa) {
		Prezioa = prezioa;
	}
	
	@Override
	public String toString() {
		return "Pujatutakoa: "+this.Prezioa+" - "+this.bezero.getIzena()+" bezeroak";
	}
	

}
