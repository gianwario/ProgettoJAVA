package eccezioni;

public class CapacitaInsufficienteException extends RuntimeException {
	
	public CapacitaInsufficienteException() {
		super();
	}

	public CapacitaInsufficienteException(String message) {
		super(message);
	}

}
