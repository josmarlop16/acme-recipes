package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.patronages.PatronageReport;
import acme.framework.repositories.AbstractRepository;

public interface PatronPatronageReportRepository extends AbstractRepository{
	
	@Query("select pr from PatronageReport pr where pr.id = :patronageReportId and pr.patronage.patron.id = :patronId")
	PatronageReport findPatronageReportById(int patronageReportId, int patronId);
	
	@Query("select pr from PatronageReport pr where pr.patronage.patron.id = :id")
	Collection<PatronageReport> findPatronageReportsByPatronId(int id);
	
}
