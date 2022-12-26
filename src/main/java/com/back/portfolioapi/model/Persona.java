
package com.back.portfolioapi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author RaCode75
 */

@Getter @Setter
@Entity
@Table(name="persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    private String nacionalidad;
    private String ocupacion;
    private String email;
    private String sobre_mi;
    private String image_perfil;
    private String reside_en;
    private String pass;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name="persona_id")
    private List<Education> cursos = new ArrayList<>();    
    public Persona(){
    }
    
    public Persona(Long id,
                            String nombre,
                            String apellido,
                            String fecha_nacimiento,
                            String nacionalidad,
                            String ocupacion,
                            String email,
                            String sobre_mi,
                            String image_perfil,
                            String reside_en,
                            String pass
                            ){
    
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
            this.fecha_nacimiento =  fecha_nacimiento;
            this.nacionalidad = nacionalidad;
            this.ocupacion = ocupacion;
            this. email = email;
            this.sobre_mi = sobre_mi;
            this.image_perfil = image_perfil;
            this.reside_en = reside_en;
            this.pass = pass;
    }
    
}
