package ejb;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import facade.AbstractFacade;
import model.Actor;
import model.Film;
import model.FilmActor;
import facade.FilmActorFacade;

@Stateless
public class FilmActorFacadeEJB extends AbstractFacade<FilmActor> implements FilmActorFacade  {
	
	
	@PersistenceContext(unitName = "sakilaPU")
	private EntityManager em;
	
	public FilmActorFacadeEJB() {
		super(FilmActor.class);
	}

	
	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
	
	@Override
	//Metodo que ejecuta la named query (definida en el modelo) que representa la consulta
	//que busca todas las peliculas en las que ha participado un actor por el id del actor.
	 public List<Film> findAllMoviesActor(Object id){
	    	EntityManager em = getEntityManager();
	    	Query q = em.createNamedQuery("find all movies by actor by his id");
	    	q.setParameter("id", id);
	    	return q.getResultList();
	  }
	
	@Override
	public FilmActor createFilmActor(Object id_actor, Object id_film){
    	FilmActor fa = new FilmActor();
    	fa.setActorId((int)id_actor);
    	fa.setFilmId((int)id_film);
    	Date date= new Date();
    	long miliseg= date.getTime();
    	Timestamp ts = new Timestamp(miliseg);
    	fa.setLastUpdate(ts);
    	return fa;
    	
    }
	
	
	//Metodo que ejecuta la named query (definida en el modelo) que representa la consulta
	//que busca todos los actores de una pelicula por el id de la pelicula
	public List<Actor> findAllActorsMovie(Object id){
		EntityManager em = getEntityManager();
    	Query q = em.createNamedQuery("find all actors by movie by his id");
    	q.setParameter("id", id);
    	return q.getResultList();
	}

}