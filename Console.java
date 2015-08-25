import java.util.Scanner;

public class Console {

	static String wrongcmd = "Wrong command. For a listing of all available commands type help.";
	static String appVersion = "0.0.1";
	DB db = null;

	/*
	 * Commands
	 */
	// get key
	// set key
	// value del key
	// print
	// help
	// exit
	public Console(DB db) {
		this.db = db;
	}

	public void readConsole() {
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		// System.out.println(line);
		String[] parts = line.split(" ");
		if (parts.length == 1) {
			// System.out.println("alles mit einem part");
			/*
			 * print
			 */
			if (parts[0].equals("print")) {
				// System.out.println("print out whole db");
				db.print();
			}
			/*
			 * help
			 */
			else if (parts[0].equals("help")) {
				System.out.println("DB - version " + appVersion);
				System.out.println("List of commands:");
				System.out.println("get 'key'");
				System.out.println("set 'key' 'value'");
				System.out.println("del 'key'");
				System.out.println("print");
				System.out.println("help");
				System.out.println("exit");
			}
			/*
			 * exit
			 */
			else if (parts[0].equals("exit")) {
				// System.out.println("Exiting program");
				db.closeDB();
			}
			/*
			 * false command
			 */
			else {
				System.out.println(wrongcmd);
			}
		} else if (parts.length == 2) {
			// System.out.println("alles mit zwei parts");
			/*
			 * get
			 */
			if (parts[0].equals("get")) {
				String key = parts[1];
				db.get(key);
				System.out.println(db.get(key));
			}
			/*
			 * del
			 */
			else if (parts[0].equals("del")) {
				String key = parts[1];
				// System.out.println("deleting: " + key);
				db.del(key);
			}
			/*
			 * false command
			 */
			else {
				System.out.println(wrongcmd);
			}
		} else if (parts.length == 3) {
			// System.out.println("alles mit drei parts");
			/*
			 * set
			 */
			if (parts[0].equals("set")) {
				String key = parts[1];
				String value = parts[2];
				// System.out.println("setting: " + key + ":" + value);
				db.set(key, value);
			}
			/*
			 * false command
			 */
			else {
				System.out.println(wrongcmd);
			}
		} else {
			System.out.println(wrongcmd);
		}
	}
}