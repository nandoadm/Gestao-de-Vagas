package com.br.gestao_vagas.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
            String token = request.getHeader("Authorization");
        System.out.println(token);

        filterChain.doFilter(request, response);
//        throw new UnsupportedOperationException("Not supported yet.");

    }
}
