import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class convertToArff {

	private static ArrayList<String> POSreviews = new ArrayList<String>();
	private static ArrayList<String> NEGreviews = new ArrayList<String>();

	private static ArrayList<String> UselessWordsList = new ArrayList<String>();

	private static ArrayList<String[]> POSwords = new ArrayList<String[]>();
	private static ArrayList<String[]> NEGwords = new ArrayList<String[]>();

	private static void UselessWords() {

		// Articles
		UselessWordsList.add("\\b" + "a" + "\\b");
		UselessWordsList.add("\\b" + "an" + "\\b");
		UselessWordsList.add("\\b" + "the" + "\\b");

		// Adverbs of manner
		UselessWordsList.add("\\b" + "carefully" + "\\b");
		UselessWordsList.add("\\b" + "correctly" + "\\b");
		UselessWordsList.add("\\b" + "eagerly" + "\\b");
		UselessWordsList.add("\\b" + "easily" + "\\b");
		UselessWordsList.add("\\b" + "fast" + "\\b");
		UselessWordsList.add("\\b" + "loudly" + "\\b");
		UselessWordsList.add("\\b" + "patiently" + "\\b");
		UselessWordsList.add("\\b" + "quickly" + "\\b");
		UselessWordsList.add("\\b" + "quietly" + "\\b");
		UselessWordsList.add("\\b" + "well" + "\\b");

		// Adverbs of place
		UselessWordsList.add("\\b" + "abroad" + "\\b");
		UselessWordsList.add("\\b" + "anywhere" + "\\b");
		UselessWordsList.add("\\b" + "downstairs" + "\\b");
		UselessWordsList.add("\\b" + "here" + "\\b");
		UselessWordsList.add("\\b" + "home" + "\\b");
		UselessWordsList.add("\\b" + "in" + "\\b");
		UselessWordsList.add("\\b" + "nowhere" + "\\b");
		UselessWordsList.add("\\b" + "out" + "\\b");
		UselessWordsList.add("\\b" + "outside" + "\\b");
		UselessWordsList.add("\\b" + "somewhere" + "\\b");
		UselessWordsList.add("\\b" + "there" + "\\b");
		UselessWordsList.add("\\b" + "underground" + "\\b");
		UselessWordsList.add("\\b" + "upstairs" + "\\b");

		// Adverbs of purpose
		UselessWordsList.add("\\b" + "so" + "\\b");
		UselessWordsList.add("\\b" + "so that" + "\\b");
		UselessWordsList.add("\\b" + "to" + "\\b");
		UselessWordsList.add("\\b" + "in order to" + "\\b");
		UselessWordsList.add("\\b" + "because" + "\\b");
		UselessWordsList.add("\\b" + "since" + "\\b");
		UselessWordsList.add("\\b" + "accidentally" + "\\b");
		UselessWordsList.add("\\b" + "intentionally" + "\\b");
		UselessWordsList.add("\\b" + "purposely" + "\\b");

		// Adverbs of frequency
		UselessWordsList.add("\\b" + "always" + "\\b");
		UselessWordsList.add("\\b" + "every" + "\\b");
		UselessWordsList.add("\\b" + "never" + "\\b");
		UselessWordsList.add("\\b" + "often" + "\\b");
		UselessWordsList.add("\\b" + "rarely" + "\\b");
		UselessWordsList.add("\\b" + "seldom" + "\\b");
		UselessWordsList.add("\\b" + "sometimes" + "\\b");
		UselessWordsList.add("\\b" + "usually" + "\\b");

		// Adverbs of time
		UselessWordsList.add("\\b" + "after" + "\\b");
		UselessWordsList.add("\\b" + "already" + "\\b");
		UselessWordsList.add("\\b" + "during" + "\\b");
		UselessWordsList.add("\\b" + "finally" + "\\b");
		UselessWordsList.add("\\b" + "just" + "\\b");
		UselessWordsList.add("\\b" + "last" + "\\b");
		UselessWordsList.add("\\b" + "later" + "\\b");
		UselessWordsList.add("\\b" + "next" + "\\b");
		UselessWordsList.add("\\b" + "now" + "\\b");
		UselessWordsList.add("\\b" + "recently" + "\\b");
		UselessWordsList.add("\\b" + "soon" + "\\b");
		UselessWordsList.add("\\b" + "then" + "\\b");
		UselessWordsList.add("\\b" + "tomorrow" + "\\b");
		UselessWordsList.add("\\b" + "when" + "\\b");
		UselessWordsList.add("\\b" + "while" + "\\b");
		UselessWordsList.add("\\b" + "yesterday" + "\\b");

		// Adverbs of completeness
		UselessWordsList.add("\\b" + "everywhere" + "\\b");
		UselessWordsList.add("\\b" + "here" + "\\b");
		UselessWordsList.add("\\b" + "there" + "\\b");

		// Personal pronouns
		UselessWordsList.add("\\b" + "i" + "\\b");
		UselessWordsList.add("\\b" + "me" + "\\b");
		UselessWordsList.add("\\b" + "you" + "\\b");
		UselessWordsList.add("\\b" + "she" + "\\b");
		UselessWordsList.add("\\b" + "her" + "\\b");
		UselessWordsList.add("\\b" + "he" + "\\b");
		UselessWordsList.add("\\b" + "him" + "\\b");
		UselessWordsList.add("\\b" + "it" + "\\b");
		UselessWordsList.add("\\b" + "we" + "\\b");
		UselessWordsList.add("\\b" + "us" + "\\b");
		UselessWordsList.add("\\b" + "they" + "\\b");
		UselessWordsList.add("\\b" + "them" + "\\b");

		// Relative pronouns
		UselessWordsList.add("\\b" + "that" + "\\b");
		UselessWordsList.add("\\b" + "which" + "\\b");
		UselessWordsList.add("\\b" + "who" + "\\b");
		UselessWordsList.add("\\b" + "whom" + "\\b");
		UselessWordsList.add("\\b" + "whose" + "\\b");
		UselessWordsList.add("\\b" + "whichever" + "\\b");
		UselessWordsList.add("\\b" + "whoever" + "\\b");
		UselessWordsList.add("\\b" + "whomever" + "\\b");

		// Demonstrative pronouns
		UselessWordsList.add("\\b" + "this" + "\\b");
		UselessWordsList.add("\\b" + "these" + "\\b");
		UselessWordsList.add("\\b" + "that" + "\\b");
		UselessWordsList.add("\\b" + "those" + "\\b");

		// Indefinite pronouns (keeping those)

		// Reflexive pronouns
		UselessWordsList.add("\\b" + "myself" + "\\b");
		UselessWordsList.add("\\b" + "ourselves" + "\\b");
		UselessWordsList.add("\\b" + "yourself" + "\\b");
		UselessWordsList.add("\\b" + "yourselves" + "\\b");
		UselessWordsList.add("\\b" + "himself" + "\\b");
		UselessWordsList.add("\\b" + "herself" + "\\b");
		UselessWordsList.add("\\b" + "itself" + "\\b");
		UselessWordsList.add("\\b" + "themselves" + "\\b");

		// Interrogative pronouns
		UselessWordsList.add("\\b" + "what" + "\\b");
		UselessWordsList.add("\\b" + "who" + "\\b");
		UselessWordsList.add("\\b" + "which" + "\\b");
		UselessWordsList.add("\\b" + "whom" + "\\b");
		UselessWordsList.add("\\b" + "whose" + "\\b");
		UselessWordsList.add("\\b" + "where" + "\\b");
		UselessWordsList.add("\\b" + "when" + "\\b");
		UselessWordsList.add("\\b" + "why" + "\\b");

		// Possessive pronouns
		UselessWordsList.add("\\b" + "my" + "\\b");
		UselessWordsList.add("\\b" + "your" + "\\b");
		UselessWordsList.add("\\b" + "his" + "\\b");
		UselessWordsList.add("\\b" + "her" + "\\b");
		UselessWordsList.add("\\b" + "its" + "\\b");
		UselessWordsList.add("\\b" + "our" + "\\b");
		UselessWordsList.add("\\b" + "your" + "\\b");
		UselessWordsList.add("\\b" + "their" + "\\b");
		UselessWordsList.add("\\b" + "mine" + "\\b");
		UselessWordsList.add("\\b" + "yours" + "\\b");
		UselessWordsList.add("\\b" + "hers" + "\\b");
		UselessWordsList.add("\\b" + "ours" + "\\b");
		UselessWordsList.add("\\b" + "yours" + "\\b");
		UselessWordsList.add("\\b" + "theirs" + "\\b");

		// Prepositions
		UselessWordsList.add("\\b" + "aboard" + "\\b");
		UselessWordsList.add("\\b" + "about" + "\\b");
		UselessWordsList.add("\\b" + "above" + "\\b");
		UselessWordsList.add("\\b" + "across" + "\\b");
		UselessWordsList.add("\\b" + "after" + "\\b");
		UselessWordsList.add("\\b" + "against" + "\\b");
		UselessWordsList.add("\\b" + "along" + "\\b");
		UselessWordsList.add("\\b" + "amid" + "\\b");
		UselessWordsList.add("\\b" + "among" + "\\b");
		UselessWordsList.add("\\b" + "anti" + "\\b");
		UselessWordsList.add("\\b" + "around" + "\\b");
		UselessWordsList.add("\\b" + "as" + "\\b");
		UselessWordsList.add("\\b" + "at" + "\\b");
		UselessWordsList.add("\\b" + "before" + "\\b");
		UselessWordsList.add("\\b" + "behind" + "\\b");
		UselessWordsList.add("\\b" + "below" + "\\b");
		UselessWordsList.add("\\b" + "beneath" + "\\b");
		UselessWordsList.add("\\b" + "beside" + "\\b");
		UselessWordsList.add("\\b" + "besides" + "\\b");
		UselessWordsList.add("\\b" + "between" + "\\b");
		UselessWordsList.add("\\b" + "beyond" + "\\b");
		UselessWordsList.add("\\b" + "but" + "\\b");
		UselessWordsList.add("\\b" + "by" + "\\b");
		UselessWordsList.add("\\b" + "concerning" + "\\b");
		UselessWordsList.add("\\b" + "considering" + "\\b");
		UselessWordsList.add("\\b" + "despite" + "\\b");
		UselessWordsList.add("\\b" + "down" + "\\b");
		UselessWordsList.add("\\b" + "during" + "\\b");
		UselessWordsList.add("\\b" + "except" + "\\b");
		UselessWordsList.add("\\b" + "excepting" + "\\b");
		UselessWordsList.add("\\b" + "excluding" + "\\b");
		UselessWordsList.add("\\b" + "following" + "\\b");
		UselessWordsList.add("\\b" + "for" + "\\b");
		UselessWordsList.add("\\b" + "from" + "\\b");
		UselessWordsList.add("\\b" + "in" + "\\b");
		UselessWordsList.add("\\b" + "inside" + "\\b");
		UselessWordsList.add("\\b" + "into" + "\\b");
		UselessWordsList.add("\\b" + "like" + "\\b");
		UselessWordsList.add("\\b" + "minus" + "\\b");
		UselessWordsList.add("\\b" + "near" + "\\b");
		UselessWordsList.add("\\b" + "of" + "\\b");
		UselessWordsList.add("\\b" + "off" + "\\b");
		UselessWordsList.add("\\b" + "on" + "\\b");
		UselessWordsList.add("\\b" + "onto" + "\\b");
		UselessWordsList.add("\\b" + "opposite" + "\\b");
		UselessWordsList.add("\\b" + "outside" + "\\b");
		UselessWordsList.add("\\b" + "over" + "\\b");
		UselessWordsList.add("\\b" + "past" + "\\b");
		UselessWordsList.add("\\b" + "per" + "\\b");
		UselessWordsList.add("\\b" + "plus" + "\\b");
		UselessWordsList.add("\\b" + "regarding" + "\\b");
		UselessWordsList.add("\\b" + "round" + "\\b");
		UselessWordsList.add("\\b" + "save" + "\\b");
		UselessWordsList.add("\\b" + "since" + "\\b");
		UselessWordsList.add("\\b" + "than" + "\\b");
		UselessWordsList.add("\\b" + "through" + "\\b");
		UselessWordsList.add("\\b" + "to" + "\\b");
		UselessWordsList.add("\\b" + "toward" + "\\b");
		UselessWordsList.add("\\b" + "towards" + "\\b");
		UselessWordsList.add("\\b" + "under" + "\\b");
		UselessWordsList.add("\\b" + "underneath" + "\\b");
		UselessWordsList.add("\\b" + "unlike" + "\\b");
		UselessWordsList.add("\\b" + "until" + "\\b");
		UselessWordsList.add("\\b" + "up" + "\\b");
		UselessWordsList.add("\\b" + "upon" + "\\b");
		UselessWordsList.add("\\b" + "versus" + "\\b");
		UselessWordsList.add("\\b" + "via" + "\\b");
		UselessWordsList.add("\\b" + "with" + "\\b");
		UselessWordsList.add("\\b" + "within" + "\\b");
		UselessWordsList.add("\\b" + "without" + "\\b");

		// Verbs
		UselessWordsList.add("\\b" + "be" + "\\b");
		UselessWordsList.add("\\b" + "is" + "\\b");
		UselessWordsList.add("\\b" + "are" + "\\b");
		UselessWordsList.add("\\b" + "was" + "\\b");
		UselessWordsList.add("\\b" + "were" + "\\b");
		UselessWordsList.add("\\b" + "will" + "\\b");
		UselessWordsList.add("\\b" + "have" + "\\b");
		UselessWordsList.add("\\b" + "has" + "\\b");
		UselessWordsList.add("\\b" + "had" + "\\b");
		UselessWordsList.add("\\b" + "hadn" + "\\b");
		UselessWordsList.add("\\b" + "haven" + "\\b");
		UselessWordsList.add("\\b" + "hasn" + "\\b");
		UselessWordsList.add("\\b" + "do" + "\\b");
		UselessWordsList.add("\\b" + "don" + "\\b");
		UselessWordsList.add("\\b" + "does" + "\\b");
		UselessWordsList.add("\\b" + "doesn" + "\\b");
		UselessWordsList.add("\\b" + "did" + "\\b");

		UselessWordsList.add("\\b" + "come" + "\\b");
		UselessWordsList.add("\\b" + "came" + "\\b");
		UselessWordsList.add("\\b" + "make" + "\\b");
		UselessWordsList.add("\\b" + "made" + "\\b");
		UselessWordsList.add("\\b" + "say" + "\\b");
		UselessWordsList.add("\\b" + "said" + "\\b");
		UselessWordsList.add("\\b" + "go" + "\\b");
		UselessWordsList.add("\\b" + "went" + "\\b");
		UselessWordsList.add("\\b" + "gone" + "\\b");
		UselessWordsList.add("\\b" + "get" + "\\b");
		UselessWordsList.add("\\b" + "got" + "\\b");
		UselessWordsList.add("\\b" + "gotten" + "\\b");
		UselessWordsList.add("\\b" + "know" + "\\b");
		UselessWordsList.add("\\b" + "knew" + "\\b");
		UselessWordsList.add("\\b" + "known" + "\\b");
		UselessWordsList.add("\\b" + "see" + "\\b");
		UselessWordsList.add("\\b" + "saw" + "\\b");
		UselessWordsList.add("\\b" + "seen" + "\\b");
		UselessWordsList.add("\\b" + "think" + "\\b");
		UselessWordsList.add("\\b" + "thought" + "\\b");
		UselessWordsList.add("\\b" + "take" + "\\b");
		UselessWordsList.add("\\b" + "took" + "\\b");
		UselessWordsList.add("\\b" + "taken" + "\\b");
		UselessWordsList.add("\\b" + "use" + "\\b");
		UselessWordsList.add("\\b" + "used" + "\\b");
		UselessWordsList.add("\\b" + "watch" + "\\b");
		UselessWordsList.add("\\b" + "watched" + "\\b");
		UselessWordsList.add("\\b" + "want" + "\\b");
		UselessWordsList.add("\\b" + "wanted" + "\\b");

		// Modal verbs
		UselessWordsList.add("\\b" + "can" + "\\b");
		UselessWordsList.add("\\b" + "could" + "\\b");
		UselessWordsList.add("\\b" + "couldn" + "\\b");
		UselessWordsList.add("\\b" + "may" + "\\b");
		UselessWordsList.add("\\b" + "might" + "\\b");
		UselessWordsList.add("\\b" + "must" + "\\b");
		UselessWordsList.add("\\b" + "mustn" + "\\b");
		UselessWordsList.add("\\b" + "shall" + "\\b");
		UselessWordsList.add("\\b" + "should" + "\\b");
		UselessWordsList.add("\\b" + "shouldn" + "\\b");
		UselessWordsList.add("\\b" + "ought to" + "\\b");
		UselessWordsList.add("\\b" + "would" + "\\b");

		// Conjunctions
		UselessWordsList.add("\\b" + "and" + "\\b");
		UselessWordsList.add("\\b" + "or" + "\\b");
		UselessWordsList.add("\\b" + "for" + "\\b");
		UselessWordsList.add("\\b" + "yet" + "\\b");
		UselessWordsList.add("\\b" + "but" + "\\b");
		UselessWordsList.add("\\b" + "if" + "\\b");

		// Substantives
		UselessWordsList.add("\\b" + "film" + "\\b");
		UselessWordsList.add("\\b" + "films" + "\\b");
		UselessWordsList.add("\\b" + "movie" + "\\b");
		UselessWordsList.add("\\b" + "movies" + "\\b");
		UselessWordsList.add("\\b" + "story" + "\\b");
		UselessWordsList.add("\\b" + "people" + "\\b");
		UselessWordsList.add("\\b" + "time" + "\\b");

		// Others
		UselessWordsList.add("\\b" + "mr" + "\\b");
		UselessWordsList.add("\\b" + "sr" + "\\b");
		UselessWordsList.add("\\b" + "one" + "\\b");
		UselessWordsList.add("\\b" + "all" + "\\b");
		UselessWordsList.add("\\b" + "no" + "\\b");
		UselessWordsList.add("\\b" + "not" + "\\b");
		UselessWordsList.add("\\b" + "some" + "\\b");
		UselessWordsList.add("\\b" + "really" + "\\b");
		UselessWordsList.add("\\b" + "more" + "\\b");
		UselessWordsList.add("\\b" + "only" + "\\b");
		UselessWordsList.add("\\b" + "much" + "\\b");
		UselessWordsList.add("\\b" + "been" + "\\b");
		UselessWordsList.add("\\b" + "good" + "\\b");
		UselessWordsList.add("\\b" + "how" + "\\b");
		UselessWordsList.add("\\b" + "first" + "\\b");
		UselessWordsList.add("\\b" + "other" + "\\b");
		UselessWordsList.add("\\b" + "too" + "\\b");
		UselessWordsList.add("\\b" + "way" + "\\b");
		
		//Others
		UselessWordsList.add("\\b" + "s" + "\\b");
	}

	private static void loadReviews(String directory, boolean positive) throws IOException {

		File dir = new File(directory);
		File[] files = dir.listFiles();

		for (File f : files) {
			if (f.isFile()) {

				BufferedReader inputStream = null;
				String text = "";

				try {
					inputStream = new BufferedReader(new FileReader(f));
					String line;

					while ((line = inputStream.readLine()) != null) {

						text += line;
					}
				} finally {
					if (inputStream != null) {
						inputStream.close();
					}
				}
				if (positive == true) {
					POSreviews.add(text);
				}
				if (positive == false) {
					NEGreviews.add(text);
				}

			}
		}

	}

	private static void removeUseless() {

		System.out.println("-> Removing useless words from positive reviews");

		for (int i = 0; i < POSreviews.size(); i++) {
			
			// convert to lower case
			POSreviews.set(i, POSreviews.get(i).toLowerCase());

			// Remove numbers
			POSreviews.set(i, POSreviews.get(i).replaceAll("0", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("1", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("2", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("3", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("4", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("5", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("6", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("7", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("8", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("9", " "));

			// Remove Others
			POSreviews.set(i, POSreviews.get(i).replaceAll("<br />", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("\n", " "));
			// Remove special characters
			POSreviews.set(i, POSreviews.get(i).replaceAll("\"", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("'", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("[.]", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll(",", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll(":", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll(";", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("\\|", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("[(]", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("[)]", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("[\\[]", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("[\\]]", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("\\{", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("\\}", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("/", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("[?]", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("!", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("@", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("#", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("[$]", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("%", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("^", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("&", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("[*]", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("-", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("_", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("<", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll(">", " "));

			for (String s : UselessWordsList) {

				POSreviews.set(i, POSreviews.get(i).replaceAll(s, " "));
			}

		}

		System.out.println("-> Ok.");

		System.out.println("-> Removing useless words from negative reviews");

		for (int i = 0; i < NEGreviews.size(); i++) {

			// convert to lower case
			NEGreviews.set(i, NEGreviews.get(i).toLowerCase());

			// Remove numbers
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("0", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("1", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("2", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("3", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("4", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("5", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("6", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("7", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("8", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("9", " "));

			// Remove Others
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("<br />", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("\n", " "));

			// Remove special characters
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("\"", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("'", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("[.]", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll(",", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll(":", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll(";", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("\\|", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("[(]", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("[)]", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("[\\[]", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("[\\]]", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("\\{", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("\\}", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("/", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("[?]", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("!", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("@", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("#", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("$", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("%", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("^", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("&", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("[*]", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("-", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("_", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("<", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll(">", " "));

			for (String s : UselessWordsList) {

				NEGreviews.set(i, NEGreviews.get(i).replaceAll(s, " "));
			}
		}

		System.out.println("-> Ok.");
	}

	private static void convertToArray() {

		System.out.println("-> Converting positive reviews's words to vector");
		for (int i = 0; i < POSreviews.size(); i++) {
			
			POSreviews.set(i, POSreviews.get(i).replaceAll("( )+", " "));
			
			POSwords.add(POSreviews.get(i).split(" "));
			for (int j = 0; j < POSwords.get(i).length; j++) {
				POSwords.get(i)[j].replaceAll(" ", "");
				POSwords.get(i)[j].replaceAll("\n", "");
			}
		}
		System.out.println("-> Ok.");

		System.out.println("-> Converting negative reviews's words to vector");
		for (int i = 0; i < NEGreviews.size(); i++){
			
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("( )+", " "));
			
			NEGwords.add(NEGreviews.get(i).split(" "));
			for (int j = 0; j < NEGwords.get(i).length; j++) {
				NEGwords.get(i)[j].replaceAll(" ", "").trim();
				NEGwords.get(i)[j].replaceAll("\n", "");
			}
		}
		System.out.println("-> Ok.");
	}

	private static void separatingMostRelevant(){
		
		ArrayList<String> allPOSwords = new ArrayList<String>();
		ArrayList<String> allNEGwords = new ArrayList<String>();
		
		System.out.println("-> Joining all positive words on a list.");
		
		for(String[] s: POSwords){
			for(int i=0; i<s.length; i++){
				allPOSwords.add(s[i]);
			}
		}
		
		System.out.println("-> Joining all negative words on a list.");
		
		for(String[] s: NEGwords){
			for(int i=0; i<s.length; i++){
				allNEGwords.add(s[i]);
			}
		}
		/*
		for(int i=0; i<allPOSwords.size();i++){
			System.out.println(allPOSwords.get(i));
		}
		*/
		System.out.println("-> Calculating top 100 positive words");
		
		ArrayList<String> auxPOS = new ArrayList<String>();
		auxPOS = allPOSwords;

		int contPOS = 0;
		
		
		ArrayList<stringCont> wordsContPOS = new ArrayList<stringCont>();
		
		for(int j=0; j<allPOSwords.size(); j++){
			
			contPOS = 0;
			
			for(int i=0; i<auxPOS.size(); i++ ){
				if(allPOSwords.get(j).equals(auxPOS.get(i))){
					contPOS++;
				}
			}
			
			wordsContPOS.add(new stringCont(allPOSwords.get(j), contPOS/2));
			//System.out.println(wordsContPOS.get(j).string+" - "+wordsContPOS.get(j).cont);
		}
		
	
		/* Adiciona no vetor com as palavras que mais aparecem. */
		int top_n_words = 5;
		
		stringCont topStringsVector[] = new stringCont[top_n_words];
		
		for(int i = 0; i < top_n_words; i++){
			topStringsVector[i] = new stringCont("", 0);
		}
		
		int menor_do_vetor = 0;
		
		for(int i = 0; i < wordsContPOS.size(); i++) {
			
			if (wordsContPOS.get(i).cont > topStringsVector[menor_do_vetor].cont){
				
				int j;
				for(j = 0; j < top_n_words; j++) {
					
					if (wordsContPOS.get(i).string.equals(topStringsVector[j].string)) {
						 break;
					}
				}
				
				if ( j == top_n_words ){
					topStringsVector[menor_do_vetor] = wordsContPOS.get(i);
				}
				
				int menor = topStringsVector[0].cont;
				menor_do_vetor = 0;
				for(int w = 1; w < top_n_words; w++) {
					if (menor > topStringsVector[w].cont) {
						menor_do_vetor = w;
						menor = topStringsVector[w].cont;
					}
				}
			}
		}
		
		for(int i=0; i<topStringsVector.length; i++){
			System.out.println("String: "+topStringsVector[i].string+" - number: "+topStringsVector[i].cont);
		}
		
		//topStringsVector[0] = new stringCont("öi", 3);
		
		/*
		for(int i=0; i<wordsContPOS.size();i++){
			System.out.println("Palavra: "+wordsContPOS.get(i).string+" - Quantidade: "+wordsContPOS.get(i).cont);
		}
		*/
		
		
		System.out.println("-> Ok.");
		System.out.println("-> Calculating top 100 negative words");
		System.out.println("-> Ok.");
	}
	
	public static void main(String[] args) {

		System.out.println("Loading...\n");
		UselessWords();

		try {
			loadReviews("src/teste/part1/neg", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			loadReviews("src/teste/part2/neg", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			loadReviews("src/teste/part1/pos", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			loadReviews("src/teste/part2/pos", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Ok.\n");

		System.out.println("Test example:\n" + POSreviews.get(1) + "\n");

		System.out.println("Processing...\n");

		removeUseless();

		convertToArray();

		// System.out.println(POSreviews.get(10)+"\n");
		System.out.println("Test example result:\n");

		/*
		String x = "";
		for (int y = 0; y < POSwords.get(1).length; y++) {
			x = POSwords.get(1)[y];
			System.out.println(x);
		}
		 */
		System.out.println("\nOk.\n");
		
		System.out.println("Separating most relevant words...\n");
		
		separatingMostRelevant();
	
		System.out.println("Ok.\n");
		
		System.out.println("Creating .arff...\n");
		
		System.out.println(".arff created. Location: \nEnd.");
	}

}
