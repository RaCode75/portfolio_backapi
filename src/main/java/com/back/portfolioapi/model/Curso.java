
package com.back.portfolioapi.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author RaCode75
 */

@Getter @Setter
public class Curso {
    
    private int id;
    private String titulo;
    private String imagen;
    private String carrera;
    private String codigo;
    private String inicio;
    private String fin;
    
    public Curso(){
    }
    
    public Curso(int id, 
                            String titulo,
                            String imagen,
                            String carrera,
                            String codigo,
                            String inicio,
                            String fin            
            ){
        
            this.titulo = titulo;
            this.imagen = imagen;
            this.carrera = carrera;
            this.codigo = codigo;
            this.inicio = inicio;
            this.fin = fin;   
    }
    
}
