package pe.edu.upeu.bomerp.auth.seguridad.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USUARIOS", schema = "BOM_SEGURIDAD")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "PASSWORD_HASH", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "ROL", nullable = false, length = 20)
    private String rol; // 'ADMIN', 'PROFESOR', 'ALUMNO'

    @Column(name = "ESTADO", nullable = false, length = 20)
    private String estado; // 'ACTIVO', 'INACTIVO', 'BLOQUEADO'
}