package fiap.scj40.storectrl.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntityNotFound extends RuntimeException {

  public EntityNotFound(String product) {
    super("Entity not found:" + product);
  }

}
