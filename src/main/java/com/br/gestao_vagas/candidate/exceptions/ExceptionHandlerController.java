package com.br.gestao_vagas.candidate.exceptions;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;


/*
    ControllerAdvice - Controla as exceções
    ExceptionHandle - Nele é passado o erro para o tratamento
 */
@ControllerAdvice
public class ExceptionHandlerController {
    private MessageSource messageSource;

        public ExceptionHandlerController(MessageSource message) {
            this.messageSource = message;
        }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        //Atribui um array a lista dto(message, field)
        List<ErrorMessageDTO> dto = new ArrayList<>();

            //getBinding - Usado para pegar os erros gerados
            //getField - Para pegar todos os campos
        e.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            //Cria um construtor com os parametros do dto
            ErrorMessageDTO error = new ErrorMessageDTO(err.getField(), message);
            dto.add(error);
        });
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
