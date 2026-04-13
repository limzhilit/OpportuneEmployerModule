package ie.atu.employer.controller;

import ie.atu.employer.Service.EmployerService;
import ie.atu.employer.model.Employer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employer")
@RequiredArgsConstructor
public class EmployerController {

  final EmployerService employerService;

  @PutMapping("/upsertEmployer")
  public ResponseEntity<Employer> upsertEmployer(
      @RequestBody Employer incoming,
      @RequestHeader("X-User-Id") String userId
  ) {
    System.out.println(userId);
    System.out.println(incoming.toString());
    Employer employer = employerService.upsertEmployer(incoming, userId);
    return ResponseEntity
        .created(URI.create("/employer/" + employer.getId()))
        .body(employer);  
  }
  
  @GetMapping
  public ResponseEntity<List<Employer>> getAllEmployers() {
    return ResponseEntity.ok(employerService.getAllEmployers());
  }

  @GetMapping("/getEmployer")
  public ResponseEntity<?> getEmployer(@RequestHeader("X-User-Id") String userId ) {
    return ResponseEntity.ok(employerService.getEmployer(userId));
  }

  @GetMapping("/name/{id}")
  public String getEmployerName(@PathVariable Long id ) {
    return employerService.getEmployerName(id);
  }
}
