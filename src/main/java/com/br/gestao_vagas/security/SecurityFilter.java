package com.br.gestao_vagas.security;

import com.br.gestao_vagas.providers.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/*O filtro é resposável por toda a requisição antes de chegar na controller.
    Authenticação -> Jwt
    Autorização -> Rotas de usuário
    Registrar requisições
    Proteção contra ataques -> xss, csrf

     Interceptar e validar tokens JWT antes que a requisição chegue ao controlador.
     Bloquear requisições não autorizadas antes mesmo de serem processadas.
     Modificar requisições ou respostas (ex.: adicionar cabeçalhos de segurança.
*/

@Component
//OncePerRequestFilter -> Garante que o filtro sera executado uma vez por requisição
public class SecurityFilter extends OncePerRequestFilter {
    @Value("${security.token.secret}")
    private String secretToken;

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        //SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");

        if (request.getRequestURI().startsWith("/company")) {

            if (header != null) {
                var token = this.jwtProvider.validateToken(header);
                if (token == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }

                var roles = token.getClaim("roles")
                        .asList(Object.class);

                var grants = roles.stream()
                                .map( role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
                        .toList();

                request.setAttribute("company_id", token.getSubject());
                UsernamePasswordAuthenticationToken auth
                        = new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}

