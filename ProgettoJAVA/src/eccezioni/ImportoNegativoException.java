package eccezioni;

public class ImportoNegativoException extends RuntimeException {

	public ImportoNegativoException() {
		super();
	}

	public ImportoNegativoException(String msg) {
		super(msg);
	}
}
