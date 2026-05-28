package com.prog4.ejer9.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "lotes")
public class Lote {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer numero;
    
    @Column(nullable = false)
    private Double superficie;

    @OneToMany(mappedBy = "lote", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Siembra> siembras = new ArrayList<>();

    public void addSiembra(Siembra siembra) {
        if (siembra == null) return;
        this.siembras.add(siembra);
        siembra.setLote(this);
    }

    public void removeSiembra(Siembra siembra) {
        if (siembra == null) return;
        this.siembras.remove(siembra);
        siembra.setLote(null);
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "campo_id", nullable = false)
    private Campo campo ;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "suelo_id", nullable = false)
    private Suelo suelo;
    
    
}
