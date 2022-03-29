package acme.features.anonymous.userAccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AnonymousUserAccountRepository2 extends AbstractRepository {

	@Query("select u from UserAccount u where u.id = id")
	UserAccount findOneUserAccountById(int id);
	
	@Query("select u from UserAccount u")
	Collection<UserAccount> findAllUserAccounts();
}
