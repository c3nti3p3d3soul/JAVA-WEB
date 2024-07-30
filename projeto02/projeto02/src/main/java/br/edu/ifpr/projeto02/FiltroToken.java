package br.edu.ifpr.projeto02;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.ifpr.projeto02.model.Usuario;
import br.edu.ifpr.projeto02.repositorio.UsuarioRepositorio;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FiltroToken extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token;
        var authenticationHeader = request.getHeader("Authorization");

        if (authenticationHeader != null) {
            Optional<Usuario> usuario =
             this.usuarioRepositorio.findByChave(authenticationHeader);

             if (usuario.isPresent()) {
                var authentication = new UsernamePasswordAuthenticationToken(usuario.get(), null,null);
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
             } else{
                response.setStatus(HttpStatus.FORBIDDEN.value());
             }
        }else{
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }
    
}
