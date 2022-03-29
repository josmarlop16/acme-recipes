package acme.features.authenticated.chirp;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chirp.Chirp;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AuthenticatedChirpRepository extends AbstractRepository {

	@Query("select c from Chirp c where c.id = id")
	Chirp findOneChirpById(int id);
	
	@Query("select c from Chirp c")
	Collection<Chirp> findAllChirps();

	@Query("select c from Chirp c where c.creationMoment > :deadline")
	Collection<Chirp> findRecentChirps(Date deadline);
}