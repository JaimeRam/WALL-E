package tp.pr5.cityLoader.cityLoaderExceptions;

/**
 * Exception thrown by the map loader when the file does not adhere to the file
 * format.
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class WrongCityFormatException extends java.io.IOException {

	private static final long serialVersionUID = 1L;

	public WrongCityFormatException() {
	}

	public WrongCityFormatException(java.lang.String msg) {
		super(msg);
	}

	public WrongCityFormatException(java.lang.String msg,
			java.lang.Throwable arg) {
		super(msg, arg);
	}

	public WrongCityFormatException(java.lang.Throwable arg) {
		super(arg);
	}

}
