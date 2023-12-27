package practica;

public class Cartas {

	private String numero;
	private String palo;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getPalo() {
		return palo;
	}
	public void setPalo(String palo) {
		this.palo = palo;
	}
	
	public Cartas(String numero, String palo) {
		super();
		this.numero = numero;
		this.palo = palo;
	}
	public Cartas() {
		super();
		this.numero = "";
		this.palo = "";
	}
	@Override
	public String toString() {
		return "Cartas [numero=" + numero + ", palo=" + palo + "]";
	}
}
