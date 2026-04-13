package ie.atu.employer.repository;

import ie.atu.employer.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployerRepository extends JpaRepository<Employer, Long> {
  boolean existsByUserId(Long userId);
  Optional<Employer> findByUserId(Long userId);

}
