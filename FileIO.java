import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FileIO {

	/*
	 * TODO -writer/reader/stream als attribute,die im konstruktor initiert
	 * werden -db.close schließt diese -von array auf hashmap
	 */
	static private FileIO singleton = null;
	private String path = null;

	static public FileIO getSingleton(String path) {
		if (singleton == null) {
			singleton = new FileIO(path);
		}
		return singleton;
	}

	private FileIO(String path) {
		this.path = path;
	}

	public void saveToFile(HashMap<String, String> store) {

		// System.out.println("writing file");
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			for (String key : store.keySet()) {

				bw.write(key + "|" + store.get(key));
				bw.newLine();

			}
			bw.close();

			// System.out.println("done");

		} catch (IOException e) {
			System.out.print("Error: ");
			e.printStackTrace();
		}
	}

	public void loadFromFile(HashMap<String, String> store) {

		System.out.println("reading file");
		try {
			File file = new File(path);
			if (!file.exists()) {
				System.out.println("no such file");
				return;
			}
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				String[] pair = line.split("\\|");
				String key = pair[0];
				String value = pair[1];
				// System.out.println("key:" + key + " value:" + value);
				store.put(key, value);
			}
		} catch (IOException e) {
			System.out.print("Error: ");
			e.printStackTrace();
		}
	}

}
