package com.prog4.ejer9.Model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@Entity
@Table(name = "agroquimicos")
public class Agroquimico {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String composicion;

    @ManyToMany(mappedBy = "agroquimicos")
    private Set<Siembra> siembras = new HashSet<>();

    public void addSiembra(Siembra siembra) {
        if (siembra == null) return;
        this.siembras.add(siembra);
        siembra.getAgroquimicos().add(this);
    }

    public void removeSiembra(Siembra siembra) {
        if (siembra == null) return;
        this.siembras.remove(siembra);
        siembra.getAgroquimicos().remove(this);
    }
}
