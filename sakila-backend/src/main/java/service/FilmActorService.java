package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.ActorFacade;
import facade.FilmActorFacade;
import facade.FilmFacade;
import model.Actor;
import model.Film;
import model.FilmActor;


@Path("/films")
public class FilmActorService {

	
	@EJB 
	ActorFacade actorFacadeEJB;
	@EJB
	FilmFacade filmFacadeEJB;
	@EJB
	FilmActorFacade filmActorFacadeEJB;
	
	Logger logger = Logger.getLogger(ActorService.class.getName());
	
	/*Metodo que busca todos los actores que han participado en una pelicula, por su id*/
	@GET
    @Path("{id}/actors")
    @Produces({"application/xml", "application/json"})
    public List<Actor> findFilmsByActor(@PathParam("id") Integer id) {
        List<Actor> resultado = filmActorFacadeEJB.findAllActorsMovie(id);
        return resultado;
        
    }
	
	
	//Metodo que asocia una pelicula a un actor
		//Se debe validar que exista la pelicula
		@POST
		@Path("{id_film}/actors/{id_actor}")
	    @Consumes({"application/xml", "application/json"})
	    public String createFilmActor(@PathParam("id_actor") Integer id_actor, @PathParam("id_film") Integer id_film ){
			String respuesta;
			if(actorFacadeEJB.find(id_actor)==null){
				respuesta="No existe dicho actor en la base de datos";
				return respuesta;
			}
			else{
				try{
					FilmActor fa = filmActorFacadeEJB.createFilmActor(id_actor, id_film);
					filmActorFacadeEJB.create(fa);
					respuesta="Actor asociado con exito.";
					return respuesta;
				}
				catch(Exception e){
					respuesta="El actor ya esta asociado a la pelicula.";
					return respuesta;
					}
				}
		}
}
