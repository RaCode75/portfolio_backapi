
package com.back.portfolioapi.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author RaCode75
 */

@Getter @Setter
@Entity
@Table(name="Education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String id_per;
    private String titulo;
    private String imagen;
    private String carrera;
    private String codigo;
    private String duracion;
    private String inicio;
    private String fin;
    

    
    public Education(){
    }
    
    public Education(Long id, 
                            String id_per,
                            String titulo,
                            String imagen,
                            String carrera,
                            String codigo,
                            String duracion,
                            String inicio,
                            String fin            
            ){
            this.id_per = id_per;
            this.titulo = titulo;
            this.imagen = imagen;
            this.carrera = carrera;
            this.codigo = codigo;
            this.duracion = duracion;
            this.inicio = inicio;
            this.fin = fin;   
    }
    
}
