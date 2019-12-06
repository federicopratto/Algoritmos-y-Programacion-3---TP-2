package fiuba.AlgoChess.Controlador.Handlers;

import java.io.File;

import fiuba.AlgoChess.Controlador.Alertas.*;
import fiuba.AlgoChess.Modelo.Errores.*;
import fiuba.AlgoChess.Modelo.Ubicacion.Posicion;
import fiuba.AlgoChess.Modelo.Unidad.Unidad;
import fiuba.AlgoChess.Vista.Tablero.VistaCasillero;
import fiuba.AlgoChess.Vista.Tablero.VistaDatosUnidad;
import fiuba.AlgoChess.Vista.Tablero.VistaTablero;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ClickParaSeleccionarUnidadAAtacar implements EventHandler<ActionEvent> {

	private VistaDatosUnidad unidadElegida;
	private Posicion posicionAtacante;
	private VistaTablero tablero;
	private VistaCasillero casillero;
	private Media sonido;
	private MediaPlayer reproductor;

	public ClickParaSeleccionarUnidadAAtacar(VistaDatosUnidad unidadElegida, Posicion posicion, VistaTablero tablero,
			VistaCasillero casillero) {

		this.unidadElegida = unidadElegida;
		this.posicionAtacante = posicion;
		this.tablero = tablero;
		this.casillero = casillero;
	}

	@Override
	public void handle(ActionEvent event) {

		boolean fallo = true;

		try {

			Unidad unidadAtacante = this.unidadElegida.getUnidad();
			unidadAtacante.interactuarCon(this.casillero.getCasillero());
			this.unidadElegida.agregarAtacante();
			this.reproducirSonido(unidadAtacante);
			fallo = false;

		} catch (MismaUnidadException e) {

			AlertaNoPoderUsarTuPoderConVos alertaNoPoderUsarTuPoderConVos = new AlertaNoPoderUsarTuPoderConVos();
			alertaNoPoderUsarTuPoderConVos.mostrarAlerta();

		} catch (MismoBandoException e) {

			AlertaAtaqueAMismoBando alertaAtaqueAAliado = new AlertaAtaqueAMismoBando();
			alertaAtaqueAAliado.mostrarAlerta();

		} catch (DistintoBandoException e) {

			AlertaCuracionABandoEnemigo alertaIntentoCurarAEnemigo = new AlertaCuracionABandoEnemigo();
			alertaIntentoCurarAEnemigo.mostrarAlerta();

		} catch (CasilleroLibreException e) {

			AlertaCasilleroVacio alertaCasilleroLibre = new AlertaCasilleroVacio();
			alertaCasilleroLibre.mostrarAlerta();

		} catch (DistanciaInvalidaException e) {

			AlertaDistanciaInvalida alertaDistanciaInvalida = new AlertaDistanciaInvalida();
			alertaDistanciaInvalida.mostrarAlerta();

		} catch (CatapultaNoPuedeSerCuradaException e) {
			
			AlertaCatapultaNoPuedeSerCurada alertaCatapultaNoPuedeSerCurada = new AlertaCatapultaNoPuedeSerCurada();
			alertaCatapultaNoPuedeSerCurada.mostrarAlerta();
			
		} finally {

			this.tablero.actualizarTablero();

			if (fallo) {
				this.tablero.comportamientoDeAtaque(this.posicionAtacante);
			} else {
				this.tablero.comportamientoSeleccionarUnidad();
			}
		}
	}

	private void reproducirSonido(Unidad unidadAtacante) {

		this.sonido = new Media(new File("./recursos/sonidos/" + unidadAtacante.getClass().getSimpleName() + ".wav")
				.toURI().toString());
		this.reproductor = new MediaPlayer(sonido);

		this.reproductor.stop();
		this.reproductor.play();
	}
}
