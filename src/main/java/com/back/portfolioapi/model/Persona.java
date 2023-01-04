
package com.back.portfolioapi.model;

import com.back.portfolioapi.model.Roles.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    @Column
    private String nombre;
    @Column
    private String email;
    @Column
    @JsonIgnore
    private String pass;
    @Column
    private String apellido;
    @Column
    private String fecha_nacimiento;
    @Column
    private String nacionalidad;
    @Column
    private String ocupacion;
   @Column
    private String sobre_mi;
    @Column
    private String image_perfil;
    @Column
    private String reside_en;
    
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name="persona_id")
    private List<Education> cursos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "persona_roles", 
	joinColumns = @JoinColumn(name = "persona_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
private Set<Role> roles = new HashSet<>();
        
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

  	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
