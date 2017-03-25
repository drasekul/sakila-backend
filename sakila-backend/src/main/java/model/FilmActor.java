package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the actor database table.
 * 
 */
@Entity
@Table(name="film_actor")
@NamedQueries({
	@NamedQuery(name="FilmActor.findAll", query="SELECT fa FROM FilmActor fa"),
	@NamedQuery(name="find all movies by actor by his id", query="SELECT f FROM Film f, FilmActor fa WHERE fa.actorId = :id AND f.filmId = fa.filmId"),
	@NamedQuery(name="find all actors by movie by his id", query="SELECT a FROM Actor a, FilmActor fa WHERE fa.filmId = :id AND a.actorId = fa.actorId")
})
public class FilmActor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="actor_id")
	private int actorId;

	@Id
	@Column(name="film_id")
	private int filmId;
	
	@Column(name="last_update")
	private Timestamp lastUpdate;
	
	public FilmActor(){
		
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
}