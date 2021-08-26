package Grupo11.ProyectoEgg.Entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import javax.validation.constraints.*;

@Entity
@Data
public class Clasificadores implements Serializable{
    
    @Id
    private Long dni;
    @NotEmpty
    @Size(min=4, message="Campo requerido")
    private String nombre;
    @NotEmpty
    private String apellido;
    @Temporal(TemporalType.DATE)
    @NotNull
    @Past
    private Date fechaNacimiento;
    @NotEmpty
    private String domicilio;
    @Column(unique = true)
    @Email
    private String email; 
    @NotEmpty
    private String instagram;
    @Temporal(TemporalType.DATE)
    @NotNull
    @FutureOrPresent
    private Date fechaCreacionUsuario;
    @NotNull
    private Long telefono;
    @NotEmpty
    @Size(min = 8, max = 12)
    private String clave;
    
    //setear automatico
    @ManyToOne
    private Rol rol;
    
    @OneToOne
    private Materiales materiales;
    
    @ManyToOne
    private Ruta Ruta;
    
//    @OneToOne
//    private Foto foto;
    
    
}
