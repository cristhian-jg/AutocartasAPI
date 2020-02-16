package com.crisgon.apirest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.crisgon.apirest.controller.CartaOperations;
import com.crisgon.apirest.controller.JugadorOperations;
import com.crisgon.apirest.controller.PartidasOperations;
import com.crisgon.apirest.model.Jugador;
import com.crisgon.apirest.model.Partida;
import com.google.gson.Gson;

/**
 * Created by @cristhian-jg on 13/02/2020
 *
 * @author Cristhian Gonz�lez.
 * 
 */

@Path("/initializegame")
public class InitializeGame {

	private static final String TAG = "IntializeGame";

	/**
	 * Verifica que el usuario existe en la base de datos y que la contrase�a
	 * introducida es correcta
	 * 
	 * @param Un jugador con su usuario y contrase�a.
	 * @return Response.OK con la informaci�n del jugador.
	 * @return Response LOGIN FAIL si el jugador no existe o la contrase�a no es
	 *         correcta
	 */
	@Path("login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Jugador jugador) {

		ArrayList<Jugador> jugadores;

		String nickname;
		String password;

		String json = "";

		jugadores = JugadorOperations.getAll();

		for (int i = 0; i < jugadores.size(); i++) {

			nickname = jugadores.get(i).getNickname();
			password = jugadores.get(i).getPassword();

			if (nickname.equals(jugador.getNickname()) && password.equals(jugador.getPassword())) {
				jugador = jugadores.get(i);
				json = new Gson().toJson(jugador);
				return Response.status(Response.Status.OK).entity(json).build();
			}
		}

		return Response.status(Response.Status.SEE_OTHER).entity(TAG + ": Login fail.").build();
	}

	// TODO Implementar una clase que registre usuarios.
	/**
	 * 
	 * @param Un jugador con usuario y contrase�a.
	 * @return Response REGISTER FAIL si algo sali� mal durante la operaci�n.
	 */
	@Path("register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(Jugador jugador) {

		return Response.status(Response.Status.SEE_OTHER).entity(TAG + ": Register fail.").build();
	}

	@Path("newgame")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response newGame(Jugador jugador) { //Podr�a ser sustituida por una clase Jugador.

		ArrayList<Partida> partidas;

		String nickname;
		boolean terminada;

		String json = "";

		partidas = PartidasOperations.getAll();

		for (int i = 0; i < partidas.size(); i++) {
			if (partidas.get(i).getJugador().equals(jugador.getNickname()) 
					&& partidas.get(i).isTerminada() ) {
				// Ya no hay partidas en marcha para el usuario y hay que devolver el id de la partida.
				return Response.status(Response.Status.OK).entity(json).build();
			}
		}

		// Debe devolver el id de la partida.
		return Response.status(Response.Status.OK).entity(json).build();
	}

	// Poner la partida pasa como terminada
	@Path("reset")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reset(Partida partida) {

		
		
		return null;
	}

	// @Path("raffle")
	public void raffle(String idSesion, int idGame) {

	}

	// @Path("playcard")
	public void playCard(String idSesion, int idGame, String idCard, String feature, String hand) {

	}

	// @Path("ready")
	public void ready(String idSesion, int idGame) {

	}
}
