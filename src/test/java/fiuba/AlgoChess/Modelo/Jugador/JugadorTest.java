package fiuba.AlgoChess.Modelo.Jugador;

import fiuba.AlgoChess.Modelo.Errores.DistintoBandoException;
import fiuba.AlgoChess.Modelo.Tablero.Tablero;
import fiuba.AlgoChess.Modelo.Ubicacion.Posicion;
import fiuba.AlgoChess.Modelo.Errores.PuntosInsuficientesException;
import fiuba.AlgoChess.Modelo.Unidad.Jinete;

import org.junit.Assert;
import org.junit.Test;


public class JugadorTest {

    @Test
    public void Test01JugadorPuedeColocarUnaUnidadSiTienePuntos(){
    	
    	Bando bandoJose = new Bando();
        Jugador jose = new Jugador("Jose", bandoJose);
        Jinete jinete = new Jinete(bandoJose);

        jose.agregarUnidad(jinete);
        
        Assert.assertEquals(true, jose.sigueJugando());
    }


    @Test (expected = PuntosInsuficientesException.class)
    public void Test02JugadorNoPuedeColocarUnaUnidadSiNoTienePuntos(){
    	
    	Bando bandoJose = new Bando();
        Jugador jose = new Jugador("Jose", bandoJose);

        for (int i = 0; i < 20; i++) {
			
        	Jinete unJinete = new Jinete(bandoJose);
        	jose.agregarUnidad(unJinete);
		}
    }
    
    
    @Test
    public void Test03JugadorPierdeSiNoTieneUnidades(){

    	Bando bandoJose = new Bando();
        Jugador jose = new Jugador("Jose", bandoJose);

        Assert.assertEquals(false, jose.sigueJugando());
    }
    
    
    @Test
    public void Test04JugadorSigueJugandoSiTieneEntidades(){

    	Bando bandoJose = new Bando();
        Jugador jose = new Jugador("Jose", bandoJose);
        Jinete jinete = new Jinete(bandoJose);
        
        jose.agregarUnidad(jinete);

        Assert.assertEquals(true, jose.sigueJugando());
    }
    
    
    /*
     * Agregar un test de que un jugador no puede seleccionar la ficha de otro.
     */

    @Test (expected = DistintoBandoException.class)
    public void Test05JugadorNoPuedeSeleccionarUnidadDeDistintoBando(){


        Bando bandoJose = new Bando();
        Bando bandoJuan = new Bando();
        Tablero tablero = new Tablero(bandoJose, bandoJuan);

        Jugador jose = new Jugador("Jose", bandoJose);
        Jinete jinete = new Jinete(bandoJuan);

        Posicion posicion = new Posicion(1,1);
        tablero.agregarNuevaUnidad(jinete, posicion);

        // El tablero le da su unidad a el jugador.
        jose.elegirUnidad(tablero.seleccionarUnidad(posicion));
    }
    @Test
    public void Test06JugadorSeleccionaUnidadYSeAgregaCorrectamente(){
        Bando bandoJuan = new Bando();
        Jugador juan = new Jugador("Juan", bandoJuan);

        Jinete jinete = new Jinete(bandoJuan);

        juan.agregarUnidad(jinete);

        Assert.assertTrue(juan.getUnidades().contains(jinete));
    }

}