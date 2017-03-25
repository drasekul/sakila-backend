package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import facade.AbstractFacade;
import model.Film;
import facade.FilmFacade;

@Stateless
public class FilmFacadeEJB extends AbstractFacade<Film> implements FilmFacade {
	
	
	@PersistenceContext(unitName = "sakilaPU")
	private EntityManager em;
	
	public FilmFacadeEJB() {
		super(Film.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}