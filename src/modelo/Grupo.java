package modelo;

/**
 *
 * @author Alejo
 */
public class Grupo 
{
    private String nombres;

    public Grupo(String nombres) {
        this.nombres = nombres;
        if(nombres == null)
            this.nombres = "";
    }

    public String getNombres() {
        return nombres;
    }
    
    
    
}
