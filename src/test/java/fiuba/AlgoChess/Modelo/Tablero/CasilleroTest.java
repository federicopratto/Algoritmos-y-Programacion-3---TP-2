package fiuba.AlgoChess.Modelo.Tablero;

import fiuba.AlgoChess.Modelo.Errores.CasilleroOcupadoException;
import fiuba.AlgoChess.Modelo.Errores.DistintoBandoException;
import fiuba.AlgoChess.Modelo.Jugador.Bando;
import fiuba.AlgoChess.Modelo.Tablero.Casillero.Casillero;
import fiuba.AlgoChess.Modelo.Unidad.Jinete;

import org.junit.Assert;
import org.junit.Test;

public class CasilleroTest {

	@Test
	public void Test00SeColocaEntidadEnCasilleroDeMismoBandoQueEstaLibre() {

		Bando bandoJose = new Bando();
		Casillero casillero = new Casillero(1, 1, bandoJose);

		Jinete jinete = new Jinete(bandoJose);
		casillero.agregarNuevaUnidad(jinete);

		Assert.assertEquals(jinete, casillero.getUnidad());
	}

	@Test(expected = CasilleroOcupadoException.class)
	public void Test01NoSeColocaEntidadEnCasilleroOcupado() {

		Bando bandoJose = new Bando();
		Casillero casillero = new Casillero(1, 1, bandoJose);

		Jinete jinete = new Jinete(bandoJose);
		casillero.agregarNuevaUnidad(jinete);

		Jinete otroJinete = new Jinete(bandoJose);
		casillero.agregarUnidad(otroJinete);


	}

	@Test(expected = DistintoBandoException.class)
	public void Test02NoSeColocaEntidadAliadaEnCasilleroEnemigoDesocupadoEnElComienzoDelJuego() {

		Bando bandoJose = new Bando();
		Bando bandoJuan = new Bando();

		Casillero casillero = new Casillero(1, 1, bandoJose);
		Jinete jinete = new Jinete(bandoJuan);

		casillero.agregarNuevaUnidad(jinete);
	}

	@Test
	public void Test03SeAgregaVecinoACasillero() {

		Bando bandoJose = new Bando();
		Bando bandoJuan = new Bando();

		Casillero casillero1 = new Casillero(0, 0, bandoJose);
		Casillero casillero2 = new Casillero(1, 1, bandoJuan);
		casillero1.agregarVecino(casillero2);

		Jinete jinete1 = new Jinete(bandoJose);
		Jinete jinete2 = new Jinete(bandoJuan);
				
		casillero1.agregarNuevaUnidad(jinete1);
		casillero2.agregarNuevaUnidad(jinete2);
		
		Assert.assertEquals(true, casillero1.hayEnemigosCerca(bandoJose));
	}
	@Test
	public void Test04SeQuitaUnidadMuertaDeSuCasillero() {


		Bando bandoJose = new Bando();
		Bando bandoJuan = new Bando();

		Jinete jinete1 = new Jinete(bandoJose);
		Jinete jinete2 = new Jinete(bandoJuan);

		Casillero casillero1 = new Casillero(0, 0, bandoJose);
		Casillero casillero2 = new Casillero(0, 4, bandoJuan);

		casillero1.agregarNuevaUnidad(jinete1);
		casillero2.agregarNuevaUnidad(jinete2);

		for(int i= 0; i < 10; i++){ jinete1.interactuarCon(casillero2);}


		Assert.assertEquals(true, casillero2.quitarUnidadMuerta());
	}
	@Test
	public void Test05NoSeQuitaUnidadQueNoEstanMuertasDeSuCasillero() {


		Bando bandoJose = new Bando();
		Bando bandoJuan = new Bando();

		Jinete jinete1 = new Jinete(bandoJose);
		Jinete jinete2 = new Jinete(bandoJuan);

		Casillero casillero1 = new Casillero(0, 0, bandoJose);
		Casillero casillero2 = new Casillero(0, 4, bandoJuan);

		casillero1.agregarNuevaUnidad(jinete1);
		casillero2.agregarNuevaUnidad(jinete2);

		 jinete1.interactuarCon(casillero2);


		Assert.assertEquals(false, casillero2.quitarUnidadMuerta());
	}

	@Test
	public void Test06SeColocaEntidadEnCasilleroDeDistintoBandoLuegoDeComenzadoElJuego() {

		Bando bandoJose = new Bando();
		Bando bandoJuan = new Bando();

		Jinete jinete1 = new Jinete(bandoJose);
		Jinete jinete2 = new Jinete(bandoJuan);

		Casillero casillero1 = new Casillero(0, 0, bandoJose);
		Casillero casillero2 = new Casillero(0, 4, bandoJuan);

		casillero1.agregarNuevaUnidad(jinete1);
		casillero2.agregarNuevaUnidad(jinete2);

		for(int i= 0; i < 10; i++){ jinete1.interactuarCon(casillero2);}

		casillero2.quitarUnidadMuerta();
		casillero2.agregarUnidad(jinete1);
		Assert.assertEquals(jinete1, casillero1.getUnidad());

	}






}