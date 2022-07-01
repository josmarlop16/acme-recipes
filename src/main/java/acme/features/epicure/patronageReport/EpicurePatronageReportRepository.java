package acme.features.epicure.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.patronages.PatronageReport;
import acme.framework.repositories.AbstractRepository;

public interface EpicurePatronageReportRepository extends AbstractRepository{
	
	@Query("select pr from PatronageReport pr where pr.id = :patronageReportId and pr.patronage.epicure.id = :epicureId")
	PatronageReport findPatronageReportById(int patronageReportId, int patronId);
	
	@Query("select pr from PatronageReport pr where pr.patronage.epicure.id = :id")
	Collection<PatronageReport> findPatronageReportsByEpicureId(int id);
	
}
