package eccezioni;

public class ImportoNegativoException extends RuntimeException {

	public ImportoNegativoException() {
		super();
	}

	public ImportoNegativoException(String message) {
		super(message);
	}
}
