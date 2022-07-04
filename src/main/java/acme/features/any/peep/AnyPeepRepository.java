package acme.features.any.peep;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.peep.Peep;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AnyPeepRepository extends AbstractRepository {

	@Query("select p from Peep p where p.id = :id")
	Peep findOnePeepById(int id);
	
	@Query("select p from Peep p")
	Collection<Peep> findAllPeeps();

	@Query("select p from Peep p where p.instantiationMoment > :deadline")
	Collection<Peep> findRecentPeeps(Date deadline);
}