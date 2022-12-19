
package com.back.portfolioapi.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author RaCode75
 */

@Getter @Setter
public class Persona {
    
    private int id;
    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    private String nacionalidad;
    private String ocupacion;
    private String email;
    private String sobre_mi;
    private String image_perfil;
    private String reside_en;
    
    public Persona(){
    }
    
    public Persona(int id, String nombre, String apellido ){
    
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
    }
    
}
