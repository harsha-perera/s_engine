/**
 * 
 */
package irdm.project.pagerank;

import java.util.HashMap;
import java.io.*;

/**
 * @author Harsha Perera
 *
 *         This class saves page rank scores in a format that can be used by the
 *         SimpleStaticScoreModifier class in the Terrier search engine.
 */
public class TerrierFeatureScoreWriter {

	String indexFileFolder;

	public TerrierFeatureScoreWriter(String indexFileFolder) {
		this.indexFileFolder = indexFileFolder;
	}

	public void PersistPageRankScores(HashMap<String, Double> pageRankScores, boolean overwrite) throws IOException {
		String indexFileName = indexFileFolder + File.separator + "PageRankScores.dat";

		File file = new File(indexFileName);

		if (file.exists()) {
			file.delete();
		}

		file.createNewFile();

		FileWriter fw = new FileWriter(file);

		try (BufferedWriter bw = new BufferedWriter(fw)) {
			pageRankScores.forEach((url, rank) -> {
				try {
					bw.write(url);
					bw.write(" ");
					bw.write(rank.toString());
					bw.newLine();
				} catch (IOException e) {
					throw new UncheckedIOException(e);
				}
			});
		}

	}
}
