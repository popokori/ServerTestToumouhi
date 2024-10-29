package mr.gov.masef.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mr.gov.masef.entites.Employer;
import mr.gov.masef.entites.Hopital;
import mr.gov.masef.repository.HopitalRepository;

@Service
public class HopitalService {
@Autowired
private HopitalRepository hop;




public Hopital save(Hopital h) {
	return hop.save(h);
}


public List<Hopital> getListHopital() {
	return hop.findAll();
}


public List<Hopital> getListHopitalForWilaya(String wilaya) {
	return hop.findByWilaya(wilaya);
}
public Optional<Hopital> getHopital(Long id) {
	
	return hop.findById(id);
}

public List<Employer> getListEmpForHopital(Long id) {
	
	Optional<Hopital>h= hop.findById(id);
	if(h.isPresent())
		return h.get().getEmployers();
	else
		return null;
}
}
