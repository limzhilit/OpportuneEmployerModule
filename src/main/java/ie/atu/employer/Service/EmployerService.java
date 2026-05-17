package ie.atu.employer.Service;

import ie.atu.employer.exception.EmployerNotFoundException;
import ie.atu.employer.model.Employer;
import ie.atu.employer.repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class EmployerService {

  private final EmployerRepository employerRepository;

  public Employer upsertEmployer(Employer incoming, String userId) {
    Long id = Long.parseLong(userId);
    incoming.setUserId(id);

    return employerRepository.findByUserId(id)
        .map(existing -> {
          existing.setName(incoming.getName());
          existing.setEmail(incoming.getEmail());
          existing.getPhone().setCountryCode(incoming.getPhone().getCountryCode());
          existing.getPhone().setNumber(incoming.getPhone().getNumber());
          existing.setAddress(incoming.getAddress());
          existing.setWebsite(incoming.getWebsite());
          existing.setDescription(incoming.getDescription());
          return employerRepository.save(existing); // UPDATE
        })
        .orElseGet(() -> employerRepository.save(incoming)); // INSERT
  }

  public Employer getEmployer(String userId) {
    Long id = Long.parseLong(userId);
    return employerRepository.findByUserId(id)
        .orElseThrow(() -> new EmployerNotFoundException("Employer profile not found for user: " + userId));
  }

  public String getEmployerName(Long id) {
    Employer employer = employerRepository.findByUserId(id)
        .orElseThrow(() -> new EmployerNotFoundException(
            "Employer profile not found for user: " + id
        ));

    return employer.getName();
  }

  public ArrayList<Employer> getAllEmployers() {
    return (ArrayList<Employer>) employerRepository.findAll();
  }


}

