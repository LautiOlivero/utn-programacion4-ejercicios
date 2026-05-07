package com.utn.ejercicio6.security;


import com.utn.ejercicio6.model.Usuario;
import com.utn.ejercicio6.store.UsuarioStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioStore usuarioStore;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar en la lista en memoria el usuario con ese username
        Usuario usuario = usuarioStore.buscarPorUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " +
                    username);
        }
        return usuario;
    }
}