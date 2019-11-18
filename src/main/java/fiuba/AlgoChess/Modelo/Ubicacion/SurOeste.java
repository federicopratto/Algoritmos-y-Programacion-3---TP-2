package fiuba.AlgoChess.Modelo.Ubicacion;

import fiuba.AlgoChess.Modelo.Tablero.Posicion;

public class SurOeste extends Direccion {

	@Override
	public Posicion decimeADondeMoverme(Posicion posicion) {

		return posicion.moveteHaciaElSurOeste();
	}
}
