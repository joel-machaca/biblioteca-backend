package pe.edu.idat.biblioteca.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre",length = 50,nullable = false)
    private String nombre;

    @Column(name = "apellido",length = 50,nullable = false)
    private String apellido;

    @Column(name = "telefono",length = 9,nullable = false)
    private String telefono;

    @Email
    @Column(name = "email",length = 100,nullable = false,unique = true)
    private String email;

    @Column(name = "password",length = 100,nullable = false)
    private String password;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol")
    private Rol rol;
}
