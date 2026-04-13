package ie.atu.employer.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long userId;
  private String name;
  private String email;

  @Valid
  @Embedded
  private Phone phone;
  private String address;
  private String website;
  private String description;

}
