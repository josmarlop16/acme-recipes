package acme.features.anonymous.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AnonymousToolkitRepository extends AbstractRepository {

	@Query("select t from Toolkit t where t.id = id")
	Toolkit findOneToolkitById(int id);
	
	@Query("select t from Toolkit t")
	Collection<Toolkit> findAllToolkits();
}