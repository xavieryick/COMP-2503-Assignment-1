import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
/**
 * This program performs basic analytics to count the number of Avengers mentioned 
 * in a given input.
 * @author Joseph, Xavier, BJ
 */
public class A1 {
	private Scanner input;
	private int topN = 4;
	private int totalwordCount;
	private ArrayList<Avenger> avengersList = new ArrayList<>();
	public String[][] avengerRoster = { { "captainamerica", "rogers", "evans" }, { "ironman", "stark", "downey" },
			{ "blackwidow", "romanoff", "johansson" }, { "hulk", "banner", "ruffalo" },
			{ "blackpanther", "tchalla", "boseman" }, { "thor", "odinson", "hemsworth" },
			{ "hawkeye", "barton", "renner" }, { "warmachine", "rhodes", "cheadle" },
			{ "spiderman", "parker", "holland" }, { "wintersoldier", "barnes", "stan" } };
	// Avenger objects
	private Avenger cptAmerica = new Avenger(avengerRoster[0][0], avengerRoster[0][1], avengerRoster[0][2]);
	private Avenger ironMan = new Avenger(avengerRoster[1][0], avengerRoster[1][1], avengerRoster[1][2]);
	private Avenger blackWidow = new Avenger(avengerRoster[2][0], avengerRoster[2][1], avengerRoster[2][2]);
	private Avenger hulk = new Avenger(avengerRoster[3][0], avengerRoster[3][1], avengerRoster[3][2]);
	private Avenger blackPan = new Avenger(avengerRoster[4][0], avengerRoster[4][1], avengerRoster[4][2]);
	private Avenger thor = new Avenger(avengerRoster[5][0], avengerRoster[5][1], avengerRoster[5][2]);
	private Avenger hawkEye = new Avenger(avengerRoster[6][0], avengerRoster[6][1], avengerRoster[6][2]);
	private Avenger warMachine = new Avenger(avengerRoster[7][0], avengerRoster[7][1], avengerRoster[7][2]);
	private Avenger spiderMan = new Avenger(avengerRoster[8][0], avengerRoster[8][1], avengerRoster[8][2]);
	private Avenger winterSoldier = new Avenger(avengerRoster[9][0], avengerRoster[9][1], avengerRoster[9][2]);
	private ArrayList<Avenger> possibleAvengers = new ArrayList<>();

	/**
	 * Initializes the program manager 
	 * @param args
	 */
	public static void main(String[] args) {
		A1 manager = new A1();
		manager.run();
	}
	
	/**
	 * Calls the readInput method to read from the input stream,
	 * then calls the printResults once every word is processed
	 */
	public void run() {
		readInput();
		printResults(avengersList);
	}

	/**
	 * Reads words from the input stream and processes them accordingly
	 */
	public void readInput() {
		input = new Scanner(System.in);
		while (input.hasNext()) {
			String word = input.next();
			word = word.trim().toLowerCase().split("'")[0].replaceAll("[^\\\\sa-zA-Z]", "");
			if (word.length() != 0) {
				totalwordCount++;
			}
			// Making a list of all possible Avengers
			possibleAvengers.add(cptAmerica);
			possibleAvengers.add(ironMan);
			possibleAvengers.add(blackWidow);
			possibleAvengers.add(hulk);
			possibleAvengers.add(blackPan);
			possibleAvengers.add(thor);
			possibleAvengers.add(hawkEye);
			possibleAvengers.add(warMachine);
			possibleAvengers.add(spiderMan);
			possibleAvengers.add(winterSoldier);
			matchIncrement(word, possibleAvengers); // Updating List of mentioned of Avengers and incrementing counters
		}
		input.close();
	}

	/*
	 * Matching and incrementing each Avenger's counts
	 */
	public void matchIncrement(String word, ArrayList<Avenger> avenger) {
		for (int i = 0; i < avenger.size(); i++) {
			if (word.equals(avenger.get(i).getAlias())) {
				avenger.get(i).incrementAliasCount();
				checkList(avenger.get(i));
				return;
			} else if (word.equals(avenger.get(i).getName())) {
				avenger.get(i).incrementNameCount();
				checkList(avenger.get(i));
				return;
			} else if (word.equals(avenger.get(i).getActor())) {
				avenger.get(i).incrementActorCount();
				checkList(avenger.get(i));
				return;
			}
		}
	}

	/* 
	 * Checks if an avenger mentioned is found in the avengersList
	 *  If not add the avenger to the avengerList
	 */
	public void checkList(Avenger a) {
		if (!(avengersList.contains(a))) {
			avengersList.add(a);
		}
	}
	/**
	 * Print TopN based on the size of the list of avengers mentioned
	 * @param av List of mentioned Avengers
	 */
	public void printingTopN(ArrayList<Avenger> av) {
		if (av.size() >= topN) {
			for (int i = 0; i < topN; i++) {
				System.out.println(av.get(i).toString());
			}
		} else {
			for (int i = 0; i < av.size(); i++) {
				System.out.println(av.get(i).toString());
			}
		}
	}

	/*
	 *  Print Results
	 */
	public void printResults(ArrayList<Avenger> av) {
		System.out.println("Total number of words: " + totalwordCount);
		System.out.println("Number of Avengers Mentioned: " + avengersList.size());
		System.out.println();
		
		// Print all Avengers ordered by appearance
		System.out.println("All avengers in the order they appeared in the input stream:");
		for (Avenger a : av) {
			System.out.println(a.toString());
		}
		System.out.println();
		
		System.out.println("Top " + topN + " most popular avengers:"); // Order by most popular Avengers
		Collections.sort(av, new Comparator<Avenger>() {
			@Override
			public int compare(Avenger a, Avenger b) {
				int totCountDiff = (b.getActorCount() + b.getAliasCount() + b.getNameCount()) // Desc. by total mentions
						- (a.getActorCount() + a.getAliasCount() + a.getNameCount());
				int nameDiff = a.getActor().compareTo(b.getActor()); // Asc. alphabetical order of Name
				// Breaking Ties
				if (totCountDiff == 0) {
					return nameDiff;
				} else {
					return totCountDiff;
				}
			}
		});
		printingTopN(av);
		System.out.println();

		
		System.out.println("Top " + topN + " most popular performers:");
		Collections.sort(av, new Comparator<Avenger>() { // Order by most popular performers/actors
			@Override
			public int compare(Avenger a, Avenger b) {
				int actCountdiff = b.getActorCount() - a.getActorCount(); // Descending Actor mentions
				int lengDiff = a.getName().length() - b.getName().length(); // Ascending of name's length
				int aliasDiff = a.getAlias().compareTo(b.getAlias()); // Ascending alphabetical order of Alias
				// Breaking Ties
				if (actCountdiff == 0) {
					if (lengDiff == 0) {
						return aliasDiff;
					} else {
						return lengDiff;
					}
				} else {
					return actCountdiff;
				}
			}
		});
		printingTopN(av);
		System.out.println();
		
		// Print all ordered by alias alphabetically
		System.out.println("All mentioned avengers in alphabetical order:"); 
		Collections.sort(av);
		for (Avenger a : av) {
			System.out.println(a.toString());
		}
		System.out.println();
	}
}