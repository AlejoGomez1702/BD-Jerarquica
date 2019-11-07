/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Alejo
 */
public class Usuario 
{
    private String nombre;
    private String apellidos;
    private int grupo_id;

    public Usuario(String nombre, String apellidos, int grupo_id) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.grupo_id = grupo_id;
        if(nombre == null)
            this.nombre = "";
        if(apellidos == null)
            this.apellidos = "";
        
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getGrupo_id() {
        return grupo_id;
    }
    
    
    
}
