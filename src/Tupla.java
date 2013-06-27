
public class Tupla implements Comparable<Tupla> {
	
	private Passageiro classificado;
	private Passageiro aClassificar;
	private double distancia;
	
	public Tupla(Passageiro classificado, Passageiro aClassificar) {
		this.classificado = classificado;
		this.aClassificar = aClassificar;
		this.distancia = classificado.distanciaPara(aClassificar);
	}
	public Passageiro getClassificado() {
		return classificado;
	}
	public void setClassificado(Passageiro classificado) {
		this.classificado = classificado;
	}
	public Passageiro getaClassificar() {
		return aClassificar;
	}
	public void setaClassificar(Passageiro aClassificar) {
		this.aClassificar = aClassificar;
	}
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	@Override
	public int compareTo(Tupla o) {
		return Double.compare(o.distancia , distancia);
	}
	
	
	
}
