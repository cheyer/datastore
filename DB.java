import java.util.HashMap;

public class DB {

	/*
	 * TODO -console thread --legt "aufgaben" in array -db thread --arbeitet
	 * aufgaben ab
	 */
	// HashMap for storing data
	private HashMap<String, String> store;

	private String path = "/home/chris/db/db.txt";
	private static boolean appRunning = true;

	FileIO io = null;

	public void openDB() {
		System.out.println("starting application");
		// System.out.println("creating hashmap");
		store = new HashMap<String, String>();
		// System.out.println("loading values into hashmap");
		// load from file
		io = FileIO.getSingleton(path);
		io.loadFromFile(store);
		System.out.println("database ready");
	}

	public void closeDB() {
		appRunning = false;
		io.saveToFile(store);
		System.out.println("database saved to file");
		System.out.println("see you");
		// close readers
		// etc
	}

	// GET
	public String get(String key) {
		String value = store.get(key);
		return value;
	}

	// SET
	public void set(String key, String value) {
		store.put(key, value);
	}

	// DELETE
	public void del(String key) {
		store.remove(key);
	}

	// PRINT
	public void print() {
		for (String key : store.keySet()) {
			System.out.println(key + "::" + store.get(key));
		}
	}

	public static void main(String[] args) {

		DB db = new DB();
		db.openDB();
		Console c = new Console(db);

		while (appRunning) {
			System.out.print("$");
			c.readConsole();
		}
	}

}