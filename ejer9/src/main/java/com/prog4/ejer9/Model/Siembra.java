package com.prog4.ejer9.Model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "siembras")
public class Siembra {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lote_id", nullable = false)
    private Lote lote;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cultivo_id", nullable = false)
    private Cultivo cultivo;


    @ManyToMany
    @JoinTable(
        name = "siembra_quimicos",
        joinColumns =  @JoinColumn(name = "siembra_id"),
        inverseJoinColumns = @JoinColumn (name = "agroquimico_id")
    )
    private Set<Agroquimico> agroquimicos = new HashSet<>();

    public void addAgroquimico(Agroquimico agro) {
        if (agro == null) return;
        this.agroquimicos.add(agro);
        agro.getSiembras().add(this);
    }

    public void removeAgroquimico(Agroquimico agro) {
        if (agro == null) return;
        this.agroquimicos.remove(agro);
        agro.getSiembras().remove(this);
    }

    

}
