package utils;

public class ConsoleValidator {

	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean isValidPortInput(String portInput) {
		if(portInput.isEmpty()) return true;
		else if (isInteger(portInput)){
			int port = Integer.parseInt(portInput);
			if (port > 1024 && port < 65535)
				return true;
		}
		return false;
	}

	public static boolean isValidYesNoInput(String input) {
		return (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n"));
	}

}
