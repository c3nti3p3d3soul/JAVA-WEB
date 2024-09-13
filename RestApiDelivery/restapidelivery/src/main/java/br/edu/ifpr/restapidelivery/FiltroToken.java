package br.edu.ifpr.restapidelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import br.edu.ifpr.restapidelivery.model.Usuario;
import br.edu.ifpr.restapidelivery.repositorio.UsuarioRepositorio;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@Component
public class FiltroToken extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Captura do cabeçalho Authorization (contendo a chave do usuário)
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && !authorizationHeader.isEmpty()) {
            // Usar o valor do header diretamente como a chave do usuário
            String chaveUsuario = authorizationHeader;

            // Buscar o usuário no banco de dados pela chave
            Optional<Usuario> usuarioOptional = usuarioRepositorio.findByChave(chaveUsuario);

            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                // Autenticar o usuário
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        usuario,
                        null,
                        null // Permissões (caso existam)
                );

                // Setar a autenticação no contexto do Spring Security
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                System.out.println("Chave não encontrada no banco de dados.");
                response.setStatus(HttpStatus.UNAUTHORIZED.value()); // Chave não encontrada
                return;
            }

        } else if ("/usuario/cadastro".equals(request.getRequestURI()) || "/usuario/login".equals(request.getRequestURI())) {
            // Permitir acesso livre para login e cadastro
            filterChain.doFilter(request, response);
            return;
        } else {
            System.out.println("Cabeçalho de autorização ausente ou inválido.");
            response.setStatus(HttpStatus.UNAUTHORIZED.value()); // Cabeçalho de autorização ausente
            return;
        }

        filterChain.doFilter(request, response);
    }
}
