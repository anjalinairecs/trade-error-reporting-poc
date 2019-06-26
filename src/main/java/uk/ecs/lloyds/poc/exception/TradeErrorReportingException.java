package uk.ecs.lloyds.poc.exception;

public class TradeErrorReportingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TradeErrorReportingException() {
		super();
	}

	public TradeErrorReportingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TradeErrorReportingException(String message, Throwable cause) {
		super(message, cause);
	}

	public TradeErrorReportingException(String message) {
		super(message);
	}

	public TradeErrorReportingException(Throwable cause) {
		super(cause);
	}

}
