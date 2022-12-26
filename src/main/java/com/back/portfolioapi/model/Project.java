
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
@Table(name="Proyecto")
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String imagen;
    private String description;
    private String technologies;
    private String repo;
    private String site;
    
    public Project(){
    }
    
    public Project(Long id,
                              String titulo,
                              String imagen,
                              String description,
                              String technologies,
                              String repo,
                              String site
    ){
        this.titulo = titulo;
        this.imagen = imagen;
        this.description = description;
        this.technologies = technologies;
        this.repo = repo;
        this.site = site;
    }
}
