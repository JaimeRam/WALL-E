package tp.pr5.instructions.exceptions;

/**
 * Exception thrown when a instruction execution fails. The exception has a
 * user-friendly message with an explanation about the error. This class has
 * many different constructors, one for every constructor of the base class.
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class InstructionExecutionException extends java.lang.Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor without parameters (no message is given)
	 */

	public InstructionExecutionException() {
	}

	/**
	 * The exception thrown is created with a problem message.
	 * 
	 * @param arg0
	 *            - User-friendly string that explains the error.
	 */

	public InstructionExecutionException(java.lang.String arg0) {
		super(arg0);
	}

	/**
	 * Constructor to create the exception with a nested cause.
	 * 
	 * @param arg0
	 *            - Nested exception.
	 */

	public InstructionExecutionException(java.lang.Throwable arg0) {
		super(arg0);
	}

	/**
	 * Constructor to create the exception with a nested cause and an error
	 * message.
	 * 
	 * @param arg0
	 *            - User-friendly string that explains the error.
	 * @param arg1
	 *            - Nested exception.
	 */

	public InstructionExecutionException(java.lang.String arg0,
			java.lang.Throwable arg1) {
		super(arg0, arg1);
	}
}
