package az.elvinali.demo.model;

import az.elvinali.demo.enums.EmployeeGender;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.*;

import java.time.LocalDate;
import java.util.Date;

@Table(name = "employees")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@DynamicInsert
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    @Column(name = "father_name")
    String fatherName;
    Integer age;
    LocalDate dob;

    @Enumerated(EnumType.STRING)
    EmployeeGender gender;

    String city;

    @Column(name = "active", columnDefinition = "int(11) default 1")
    private Integer active;

    @CreationTimestamp
    @Column(name = "create_date",updatable = false)
    Date createDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    Date updateDate;

}
