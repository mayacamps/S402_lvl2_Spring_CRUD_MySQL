package cat.itacademy.barcelonactiva.camps.maya.s04.t02.n02.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.BindErrorUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handlingItemNotFound(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not available.");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handlingBadRequest(HttpMessageNotReadableException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Input syntax error. \n" + e.getCause());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handlingRepeatedNameError(DataIntegrityViolationException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error. Item with this name already exists. \n" + e.getCause());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handlingNullEntry(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Input error. " + BindErrorUtils.resolveAndJoin(e.getFieldErrors()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlingAllOtherExceptions(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error has occurred." + e.getMessage());
    }
}
