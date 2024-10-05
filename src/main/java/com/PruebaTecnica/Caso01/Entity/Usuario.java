package com.PruebaTecnica.Caso01.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
/**
 * Entidad que representa un usuario del sistema.
 * Implementa la interfaz UserDetails para la gestión de la seguridad.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario implements UserDetails {
  @Id
  private Long id; // ID del usuario
  private String username; // Nombre de usuario
  private String password; // Contraseña del usuario

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(); // Retorna las autoridades del usuario (vacío en este caso)
  }

  @Override
  public boolean isAccountNonExpired() {
    return true; // La cuenta no ha expirado
  }

  @Override
  public boolean isAccountNonLocked() {
    return true; // La cuenta no está bloqueada
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true; // Las credenciales no han expirado
  }

  @Override
  public boolean isEnabled() {
    return true; // La cuenta está habilitada
  }
}
