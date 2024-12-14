package co.com.seti.api.config;

import co.com.seti.usecase.user.common.MensajeDTO;
import co.com.seti.usecase.user.common.ValidacionDTO;
import co.com.seti.usecase.user.common.exception.ErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDTO<String>> generalException(Exception e){
        e.printStackTrace();
        return ResponseEntity.internalServerError().body( new MensajeDTO<>(e.getMessage())
        );
    }

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<MensajeDTO<String>> errorException(ErrorException e){

        return ResponseEntity.status(e.getCode()).body(new MensajeDTO<>(e.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeDTO<List<ValidacionDTO>>> validationException(
            MethodArgumentNotValidException ex ) {
        List<ValidacionDTO> errores = new ArrayList<>();
        BindingResult results = ex.getBindingResult();
        for (FieldError e: results.getFieldErrors()) {
            errores.add( new ValidacionDTO(e.getField(), e.getDefaultMessage()) );
        }
        return ResponseEntity.badRequest().body( new MensajeDTO<>(errores) );
    }
}