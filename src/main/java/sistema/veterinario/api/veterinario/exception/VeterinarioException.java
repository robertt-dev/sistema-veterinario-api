package sistema.veterinario.api.veterinario.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VeterinarioException extends RuntimeException {
    
    public VeterinarioException(String errorMessage) {
        super(errorMessage);
    }

}
