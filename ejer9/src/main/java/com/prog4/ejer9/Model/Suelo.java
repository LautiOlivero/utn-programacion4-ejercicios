package com.prog4.ejer9.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "suelos")
public class Suelo {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Double ph;
    
    @OneToMany(mappedBy = "suelo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lote> lotes = new ArrayList<>();

    public void addLote(Lote lote) {
        if (lote == null) return;
        this.lotes.add(lote);
        lote.setSuelo(this);
    }

    public void removeLote(Lote lote) {
        if (lote == null) return;
        this.lotes.remove(lote);
        lote.setSuelo(null);
    }

}
