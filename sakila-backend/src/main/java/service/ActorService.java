package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.ActorFacade;
import facade.FilmActorFacade;
import facade.FilmFacade;
import model.Actor;
import model.Film;
import model.FilmActor;

@Path("/actors")
public class ActorService {
	
	@EJB 
	ActorFacade actorFacadeEJB;
	@EJB
	FilmFacade filmFacadeEJB;
	@EJB
	FilmActorFacade filmActorFacadeEJB;
	
	Logger logger = Logger.getLogger(ActorService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Actor> findAll(){
		return actorFacadeEJB.findAll();
	}
	
	@GET
	@Path("/films")
	@Produces({"application/xml", "application/json"})
	public List<Film> findAllMovies(){
		return filmFacadeEJB.findAll();
	}
	
	@GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Actor find(@PathParam("id") Integer id) {
        return actorFacadeEJB.find(id);
    }
	
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Actor entity) {
        actorFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Actor entity) {
    	entity.setActorId(id.intValue());
        actorFacadeEJB.edit(entity);
    }
	
    
    /*Metodo que busca todas las peliculas en las que ha participado un actor, por su id*/
	@GET
    @Path("{id}/films")
    @Produces({"application/xml", "application/json"})
    public List<Film> findFilmsByActor(@PathParam("id") Integer id) {
        List<Film> resultado = filmActorFacadeEJB.findAllMoviesActor(id);
        return resultado;
        
    }
	
	//Metodo que asocia una pelicula a un actor
	//Se debe validar que exista la pelicula
	@POST
	@Path("{id_actor}/films/{id_film}")
    @Consumes({"application/xml", "application/json"})
    public String createFilmActor(@PathParam("id_actor") Integer id_actor, @PathParam("id_film") Integer id_film ){
		String respuesta;
		if(filmFacadeEJB.find(id_film)==null){
			respuesta="No existe dicha pelicula en la base de datos";
			return respuesta;
		}
		else{
			try{
				FilmActor fa = filmActorFacadeEJB.createFilmActor(id_actor, id_film);
				filmActorFacadeEJB.create(fa);
				respuesta="Pelicula asociada con exito.";
				return respuesta;
			}
			catch(Exception e){
				respuesta="La pelicula ya esta asociada al actor.";
				return respuesta;
				}
			}
	}

}
