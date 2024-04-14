package spring.berrekate.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stadium_id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;
    private String country;
    private String address;

}
