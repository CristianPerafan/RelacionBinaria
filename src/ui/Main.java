package ui;

//Cristian Felipe Perafan Chilito - A00378035
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class Main {

	public Main() {

	}

	static Scanner sc = new Scanner(System.in);

	static Main source = new Main();

	public static void main(String[] args) {
		System.out.println("*** MENU ***\n" + "1) Binary relation\n" + "0) Exit");

		int optionMenu = sc.nextInt();
		sc.nextLine();

		switch (optionMenu) {
		case 0:
			System.out.println("Bye...");
			break;
		case 1:
			source.binaryRelation();
			break;
		default:
			System.out.println("No valid option!!");
			break;

		}
	}

	public void binaryRelation() {
		// Declaration of the first list for the domain
		List<String> domain = new ArrayList<String>();

		// Declaration of the first list for the codomain
		List<String> codomain = new ArrayList<String>();

		System.out.println("*** DOMAIN ***");
		System.out.println("How many elements does your domain have?");
		int numDomain = sc.nextInt();
		sc.nextLine();

		String auxElement = "";

		for (int i = 0; i < numDomain; i++) {
			System.out.println("Please enter the element " + (i + 1) + " of the domain: ");
			auxElement = sc.nextLine();
			domain.add(auxElement);
		}

		System.out.println("*** CODOMAIN ***");

		System.out.println("How many elements does your codomain have?");
		int numCodomain = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < numCodomain; i++) {
			System.out.println("Please enter the element " + (i + 1) + " of the codomain: ");
			auxElement = sc.nextLine();
			codomain.add(auxElement);
		}

		System.out.println("How many ordered pairs do you want to enter?");
		int numOrderedPairs = sc.nextInt();
		sc.nextLine();

		// HashMap statement to store ordered pairs
		HashMap<String, String> orderedPairs = new HashMap<String, String>();

		String x = "";
		String y = "";

		for (int i = 0; i < numOrderedPairs; i++) {
			System.out.println("Enter the element X of the ordered pair " + (i + 1));
			x = sc.nextLine();
			System.out.println("Enter the element Y of the ordered pair " + (i + 1));
			y = sc.nextLine();
			orderedPairs.put(x, y);
		}

		if (source.checkElementsOfOrderedPairsDomain(orderedPairs, numDomain, domain).size() != 0) {
			System.out.println("");
			System.out.print("Message:\n");
			System.out.println("The binary relation does not correspond to a \n"
					+ "function, because there are elements  \n" + "of the ordered pairs that are not in the domain:");
			System.out.println(source.checkElementsOfOrderedPairsDomain(orderedPairs, numDomain, domain));
		} else {
			if (source.checkElementsOfOrderedPairsCodomain(orderedPairs, numCodomain, codomain).size() != 0) {
				System.out.println("");
				System.out.print("Message:\n");
				System.out.println(
						"The binary relation does not correspond to a \n" + "function, because there are elements  \n"
								+ "of the ordered pairs that are not in the codomain:");

				System.out.println(source.checkElementsOfOrderedPairsCodomain(orderedPairs, numCodomain, codomain));
			}

			else {
				System.out.println("");
				System.out.println("*** RESULTS ***");
				System.out.println("Domain " + domain.toString());
				System.out.println("Codomain: " + codomain.toString());
				System.out.println("");
				System.out.println("== Ordered pairs ==");
				System.out.println("");
				System.out.println("| x | y |");
				for (Entry<String, String> m : orderedPairs.entrySet()) {
					System.out.println("| " + m.getKey() + " | " + m.getValue() + " |");
				}

				System.out.println("");

				if (source.checkIfTheFunctionIsInjective(orderedPairs)) {
					System.out.println("The function is INVERSE");
				} else {
					System.out.println("The function is not INVERSE");
				}

				if (!source.checkIfTheFunctionIsNotSurjective(orderedPairs, codomain)) {
					System.out.println("The function is SURJECTIVE");
				} else {
					System.out.println("The function is not SURJECTIVE");

				}
				
				if(checkIfTheFunctionIsBijective(orderedPairs,codomain)) {
					System.out.println("The function is BIJECTIVE");
				}
				else {
					System.out.println("The function is not BIJECTIVE");
				}

			}

		}

	}

	public List<String> checkElementsOfOrderedPairsDomain(HashMap<String, String> orderedPairs, int numDomain,
			List<String> domain) {

		// List to store the elements that do not correspond to the domain
		List<String> numbersNoDomain = new ArrayList<String>();

		// Check if the relation corresponds to a function
		boolean condition = false;
		boolean stop = false;
		for (String m : orderedPairs.keySet()) {

			for (int i = 0; i < numDomain && !stop; i++) {
				if (m.equals(domain.get(i))) {
					condition = true;
					stop = true;
				}
			}

			if (condition == false) {
				numbersNoDomain.add(m);
			}
			condition = false;
			stop = false;

		}

		return numbersNoDomain;
	}

	public List<String> checkElementsOfOrderedPairsCodomain(HashMap<String, String> orderedPairs, int numCodomain,
			List<String> coDomain) {

		// List to store the elements that do not correspond to the codomain
		List<String> numbersNoCodomain = new ArrayList<String>();

		// Check if the relation corresponds to a function
		boolean condition = false;
		boolean stop = false;
		for (Entry<String, String> m : orderedPairs.entrySet()) {

			for (int i = 0; i < numCodomain && !stop; i++) {
				if (m.getValue().equals(coDomain.get(i))) {
					condition = true;
					stop = true;
				}
			}

			if (condition == false) {
				numbersNoCodomain.add(m.getValue());
			}
			condition = false;
			stop = false;

		}

		return numbersNoCodomain;

	}

	public boolean checkIfTheFunctionIsInjective(HashMap<String, String> orderedPairs) {

		boolean out = true;
		boolean stop = false;

		// List to store the elements that do not correspond to the codomain
		List<String> imagesOrderedPairs = new ArrayList<String>();

		for (Entry<String, String> m : orderedPairs.entrySet()) {
			imagesOrderedPairs.add(m.getValue());
		}

		if (imagesOrderedPairs.size() == 1) {
			out = false;
		} else {
			for (int i = 0; i < imagesOrderedPairs.size() && !stop; i++) {
				for (int e = imagesOrderedPairs.size() - 1; e > -1 && !stop; e--) {
					if (i != e) {
						if (imagesOrderedPairs.get(i).equals(imagesOrderedPairs.get(e))) {
							out = false;
							stop = true;

						}
					}
				}
			}
		}

		return out;
	}

	public boolean checkIfTheFunctionIsNotSurjective(HashMap<String, String> orderedPairs, List<String> codomain) {
		boolean out = true;
		boolean auxOut;
		boolean stop = false;

		// List to store the elements that do not correspond to the codomain
		List<String> imagesOrderedPairs = new ArrayList<String>();

		for (Entry<String, String> m : orderedPairs.entrySet()) {
			imagesOrderedPairs.add(m.getValue());
		}

		for (int i = 0; i < codomain.size() && !stop; i++) {

			auxOut = true;

			for (int e = 0; e < imagesOrderedPairs.size(); e++) {
				if (codomain.get(i).equals(imagesOrderedPairs.get(e))) {
					auxOut = false;
				}
				if (e == imagesOrderedPairs.size() - 1) {
					if (auxOut == true) {
						out = auxOut;
						stop = true;
					}
				}
			}

			if (i == codomain.size() - 1) {
				out = auxOut;
			}
		}
		
		//Out = False - IS SURJECTIVE
		//Out = True - IS NOT SURJECTIVE
		
		return out;
	}
	
	public boolean checkIfTheFunctionIsBijective(HashMap<String, String> orderedPairs, List<String> codomain) {
		boolean out = false;
		
		boolean c1 = source.checkIfTheFunctionIsNotSurjective(orderedPairs,codomain); 
		boolean c2 = source.checkIfTheFunctionIsInjective(orderedPairs);

		
		if(c1 == false && c2 == true) {
			out = true;
		}
		return out;
		
	}
}
