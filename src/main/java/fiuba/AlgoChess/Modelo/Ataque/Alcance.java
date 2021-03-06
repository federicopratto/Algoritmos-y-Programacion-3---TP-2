package fiuba.AlgoChess.Modelo.Ataque;

import fiuba.AlgoChess.Modelo.Errores.DistanciaInvalidaException;

public class Alcance {

	private double distanciaMaxima;
	private double distanciaMinima;

	
	public Alcance(double minima, double maxima) {

		this.distanciaMaxima = maxima;
		this.distanciaMinima = minima;
	}

	
	public void estaDentroDelAlcance(double distancia) {
		
		if (distancia > this.distanciaMaxima || distancia < this.distanciaMinima) {
			
			throw new DistanciaInvalidaException();
		}
	}
}
