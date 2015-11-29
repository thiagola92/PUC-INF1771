import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class convertToArff {


	private static ArrayList<String> POSreviews = new ArrayList<String>();
	private static ArrayList<String> NEGreviews = new ArrayList<String>();
	
	private static ArrayList<String> UselessWordsList = new ArrayList<String>();
	
	private static ArrayList<String> POSwords = new ArrayList<String>();
	private static ArrayList<String> NEGwords = new ArrayList<String>();
	
	private static void loadReviews(String directory, boolean positive) throws IOException{
		
		File dir = new File(directory);
		File[] files = dir.listFiles();
		
		for (File f: files){
			if(f.isFile()){
				
				BufferedReader inputStream = null;
				String text = "";
				
				try{
					inputStream = new BufferedReader(new FileReader(f));
					String line;
					
					while ((line = inputStream.readLine()) != null) {
	
						text += line;
                    }
				}
				finally{
					  if (inputStream != null) {
	                      inputStream.close();
	                    }
				}
				if(positive == true){
					POSreviews.add(text);
				}
				if(positive == false){
					NEGreviews.add(text);
				}
				
			}
		}
		
	}
	
	private static void removeUseless(){

		for(int i=0; i<POSreviews.size(); i++){
				
			//Remove numbers
			POSreviews.set(i, POSreviews.get(i).replaceAll("0", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("1", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("2", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("3", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("4", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("5", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("6", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("7", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("8", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("9", ""));
			
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("0", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("1", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("2", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("3", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("4", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("5", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("6", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("7", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("8", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("9", ""));
			
			//Remove Others
			POSreviews.set(i, POSreviews.get(i).replaceAll("<br />", " "));
			
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("<br />", " "));
			
			//Remove special characters
			POSreviews.set(i, POSreviews.get(i).replaceAll("\"", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("'", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("[.]", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll(",", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll(":", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll(";", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("|", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("[(]", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("[)]", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("[\\[]", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("[\\]]", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("\\{", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("\\}", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("/", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("[?]", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("!", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("@", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("#", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("$", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("%", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("^", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("&", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("[*]", ""));
			POSreviews.set(i, POSreviews.get(i).replaceAll("-", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("_", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll("<", " "));
			POSreviews.set(i, POSreviews.get(i).replaceAll(">", " "));
			
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("\"", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("'", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("[.]", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll(",", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll(":", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll(";", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("|", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("[(]", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("[)]", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("[\\[]", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("[\\]]", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("\\{", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("\\}", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("/", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("[?]", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("!", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("@", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("#", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("$", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("%", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("^", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("&", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("[*]", ""));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("-", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("_", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll("<", " "));
			NEGreviews.set(i, NEGreviews.get(i).replaceAll(">", " "));
			
			for(String s: UselessWordsList){
				
				POSreviews.set(i, POSreviews.get(i).replaceAll(s, " "));
				
				NEGreviews.set(i, NEGreviews.get(i).replaceAll(s, " "));
			}
			
		}

	}
	
	private static void UselessWords(){
		
		//Articles
		UselessWordsList.add("a");
		UselessWordsList.add("an");
		UselessWordsList.add("the");
		
		//Adverbs of manner
		UselessWordsList.add("carefully");
		UselessWordsList.add("correctly");
		UselessWordsList.add("eagerly");  
		UselessWordsList.add("easily");  
		UselessWordsList.add("fast");  
		UselessWordsList.add("loudly");
		UselessWordsList.add("patiently"); 
		UselessWordsList.add("quickly");  
		UselessWordsList.add("quietly");  
		UselessWordsList.add("well");  
		
		//Adverbs of place
		UselessWordsList.add("abroad");
		UselessWordsList.add("anywhere");
		UselessWordsList.add("downstairs");
		UselessWordsList.add("here");
		UselessWordsList.add("home");
		UselessWordsList.add("in");
		UselessWordsList.add("nowhere");
		UselessWordsList.add("out");
		UselessWordsList.add("outside");
		UselessWordsList.add("somewhere");
		UselessWordsList.add("there");
		UselessWordsList.add("underground");
		UselessWordsList.add("upstairs");
		  

		//Adverbs of purpose
		UselessWordsList.add("so"); 
		UselessWordsList.add("so that");  
		UselessWordsList.add("to");  
		UselessWordsList.add("in order to");  
		UselessWordsList.add("because");  
		UselessWordsList.add("since");  
		UselessWordsList.add("accidentally");  
		UselessWordsList.add("intentionally");  
		UselessWordsList.add("purposely");  

		//Adverbs of frequency
		UselessWordsList.add("always");  
		UselessWordsList.add("every");  
		UselessWordsList.add("never");  
		UselessWordsList.add("often");  
		UselessWordsList.add("rarely");  
		UselessWordsList.add("seldom");  
		UselessWordsList.add("sometimes");  
		UselessWordsList.add("usually");  

		//Adverbs of time
		UselessWordsList.add("after");  
		UselessWordsList.add("already");  
		UselessWordsList.add("during");  
		UselessWordsList.add("finally");  
		UselessWordsList.add("just");  
		UselessWordsList.add("last");  
		UselessWordsList.add("later");  
		UselessWordsList.add("next");  
		UselessWordsList.add("now");  
		UselessWordsList.add("recently");  
		UselessWordsList.add("soon");  
		UselessWordsList.add("then");  
		UselessWordsList.add("tomorrow");  
		UselessWordsList.add("when");  
		UselessWordsList.add("while");  
		UselessWordsList.add("yesterday");  

		//Adverbs of completeness
		UselessWordsList.add("everywhere");  
		UselessWordsList.add("here");  
		UselessWordsList.add("there");  

		//Personal pronouns
		UselessWordsList.add("i");  
		UselessWordsList.add("me");  
		UselessWordsList.add("you");  
		UselessWordsList.add("she");  
		UselessWordsList.add("her");  
		UselessWordsList.add("he");  
		UselessWordsList.add("him");  
		UselessWordsList.add("it");  
		UselessWordsList.add("we");  
		UselessWordsList.add("us");  
		UselessWordsList.add("they");  
		UselessWordsList.add("them");  

		 //Relative pronouns
		 UselessWordsList.add("that");  
		 UselessWordsList.add("which");  
		 UselessWordsList.add("who");  
		 UselessWordsList.add("whom");  
		 UselessWordsList.add("whose");  
		 UselessWordsList.add("whichever");  
		 UselessWordsList.add("whoever");  
		 UselessWordsList.add("whomever");  

		 //Demonstrative pronouns
		 UselessWordsList.add("this");  
		 UselessWordsList.add("these");  
		 UselessWordsList.add("that");  
		 UselessWordsList.add("those");  

		 //Indefinite pronouns (keeping those)

		 //Reflexive pronouns
		 UselessWordsList.add("myself");  
		 UselessWordsList.add("ourselves");  
		 UselessWordsList.add("yourself");  
		 UselessWordsList.add("yourselves");  
		 UselessWordsList.add("himself");  
		 UselessWordsList.add("herself");  
		 UselessWordsList.add("itself");  
		 UselessWordsList.add("themselves");  

		 //Interrogative pronouns
		 UselessWordsList.add("what");  
		 UselessWordsList.add("who");  
		 UselessWordsList.add("which");  
		 UselessWordsList.add("whom");  
		 UselessWordsList.add("whose");  
		 UselessWordsList.add("where");  
		 UselessWordsList.add("when");  
		 UselessWordsList.add("why");  

		 //Possessive pronouns
		 UselessWordsList.add("my");  
		 UselessWordsList.add("your");  
		 UselessWordsList.add("his");  
		 UselessWordsList.add("her");  
		 UselessWordsList.add("its"); 
		 UselessWordsList.add("our");  
		 UselessWordsList.add("your");  
		 UselessWordsList.add("their");  
		 UselessWordsList.add("mine");  
		 UselessWordsList.add("yours");  
		 UselessWordsList.add("hers");  
		 UselessWordsList.add("ours");  
		 UselessWordsList.add("yours");  
		 UselessWordsList.add("theirs");  

		 //Prepositions
		 UselessWordsList.add("aboard");  
		 UselessWordsList.add("about"); 
		 UselessWordsList.add("above");  
		 UselessWordsList.add("across");  
		 UselessWordsList.add("after");  
		 UselessWordsList.add("against");  
		 UselessWordsList.add("along");  
		 UselessWordsList.add("amid"); 
		 UselessWordsList.add("among");  
		 UselessWordsList.add("anti");  
		 UselessWordsList.add("around");  
		 UselessWordsList.add("as");  
		 UselessWordsList.add("at");  
		 UselessWordsList.add("before"); 
		 UselessWordsList.add("behind");  
		 UselessWordsList.add("below"); 
		 UselessWordsList.add("beneath");  
		 UselessWordsList.add("beside");  
		 UselessWordsList.add("besides");  
		 UselessWordsList.add("between");  
		 UselessWordsList.add("beyond");  
		 UselessWordsList.add("but");  
		 UselessWordsList.add("by");  
		 UselessWordsList.add("concerning");  
		 UselessWordsList.add("considering");  
		 UselessWordsList.add("despite"); 
		 UselessWordsList.add("down"); 
		 UselessWordsList.add("during");  
		 UselessWordsList.add("except");  
		 UselessWordsList.add("excepting");  
		 UselessWordsList.add("excluding");  
		 UselessWordsList.add("following");  
		 UselessWordsList.add("for");  
		 UselessWordsList.add("from");  
		 UselessWordsList.add("in");  
		 UselessWordsList.add("inside");  
		 UselessWordsList.add("into"); 
		 UselessWordsList.add("like"); 
		 UselessWordsList.add("minus"); 
		 UselessWordsList.add("near"); 
		 UselessWordsList.add("of"); 
		 UselessWordsList.add("off"); 
		 UselessWordsList.add("on"); 
		 UselessWordsList.add("onto"); 
		 UselessWordsList.add("opposite"); 
		 UselessWordsList.add("outside"); 
		 UselessWordsList.add("over");  
		 UselessWordsList.add("past");  
		 UselessWordsList.add("per"); 
		 UselessWordsList.add("plus");  
		 UselessWordsList.add("regarding");  
		 UselessWordsList.add("round");  
		 UselessWordsList.add("save");  
		 UselessWordsList.add("since");  
		 UselessWordsList.add("than");  
		 UselessWordsList.add("through");  
		 UselessWordsList.add("to");  
		 UselessWordsList.add("toward");  
		 UselessWordsList.add("towards");  
		 UselessWordsList.add("under");  
		 UselessWordsList.add("underneath");  
		 UselessWordsList.add("unlike");  
		 UselessWordsList.add("until");  
		 UselessWordsList.add("up"); 
		 UselessWordsList.add("upon"); 
		 UselessWordsList.add("versus");  
		 UselessWordsList.add("via");  
		 UselessWordsList.add("with");  
		 UselessWordsList.add("within");  
		 UselessWordsList.add("without");  

		 //Verbs
		 UselessWordsList.add("be"); 
		 UselessWordsList.add("is");  
		 UselessWordsList.add("are");  
		 UselessWordsList.add("was");  
		 UselessWordsList.add("were");  
		 UselessWordsList.add("will");  
		 UselessWordsList.add("have");  
		 UselessWordsList.add("has");  
		 UselessWordsList.add("had");  
		 UselessWordsList.add("hadn");  
		 UselessWordsList.add("haven");   
		 UselessWordsList.add("hasn");  
		 UselessWordsList.add("do");  
		 UselessWordsList.add("don"); 
		 UselessWordsList.add("does"); 
		 UselessWordsList.add("doesn");  
		 UselessWordsList.add("did");  

		 UselessWordsList.add("come");  
		 UselessWordsList.add("came");  
		 UselessWordsList.add("make");  
		 UselessWordsList.add("made");  
		 UselessWordsList.add("say");  
		 UselessWordsList.add("said");  
		 UselessWordsList.add("go"); 
		 UselessWordsList.add("went"); 
		 UselessWordsList.add("gone"); 
		 UselessWordsList.add("get"); 
		 UselessWordsList.add("got"); 
		 UselessWordsList.add("gotten"); 
		 UselessWordsList.add("know"); 
		 UselessWordsList.add("knew");  
		 UselessWordsList.add("known");  
		 UselessWordsList.add("see");  
		 UselessWordsList.add("saw");  
		 UselessWordsList.add("seen");  
		 UselessWordsList.add("think");  
		 UselessWordsList.add("thought");  
		 UselessWordsList.add("take");  
		 UselessWordsList.add("took");  
		 UselessWordsList.add("taken"); 
		 UselessWordsList.add("use");  
		 UselessWordsList.add("used"); 
		 UselessWordsList.add("watch"); 
		 UselessWordsList.add("watched"); 
		 UselessWordsList.add("want"); 
		 UselessWordsList.add("wanted"); 

		 //Modal verbs
		 UselessWordsList.add("can"); 
		 UselessWordsList.add("could"); 
		 UselessWordsList.add("couldn"); 
		 UselessWordsList.add("may");  
		 UselessWordsList.add("might");  
		 UselessWordsList.add("must");  
		 UselessWordsList.add("mustn");  
		 UselessWordsList.add("shall");  
		 UselessWordsList.add("should");  
		 UselessWordsList.add("shouldn");  
		 UselessWordsList.add("ought to");  
		 UselessWordsList.add("would");  

		 //Conjunctions
		 UselessWordsList.add("and");  
		 UselessWordsList.add("or");  
		 UselessWordsList.add("for");  
		 UselessWordsList.add("yet");  
		 UselessWordsList.add("but");   
		 UselessWordsList.add("if");  

		 //Substantives
		 UselessWordsList.add("film"); 
		 UselessWordsList.add("films");  
		 UselessWordsList.add("movie");  
		 UselessWordsList.add("movies"); 
		 UselessWordsList.add("story");   
		 UselessWordsList.add("people");   
		 UselessWordsList.add("time");   

		 //Others
		 UselessWordsList.add("mr");   
		 UselessWordsList.add("sr");   
		 UselessWordsList.add("one");   
		 UselessWordsList.add("all");  
		 UselessWordsList.add("no");   
		 UselessWordsList.add("not");   
		 UselessWordsList.add("some");   
		 UselessWordsList.add("really"); 
		 UselessWordsList.add("more");  
		 UselessWordsList.add("only");  
		 UselessWordsList.add("much");  
		 UselessWordsList.add("been");  
		 UselessWordsList.add("good");  
		 UselessWordsList.add("how");   
		 UselessWordsList.add("first");   
		 UselessWordsList.add("other");   
		 UselessWordsList.add("too");   
		 UselessWordsList.add("way");  
	}

	public static void main(String[] args) {

		System.out.println("Loading...\n");
		UselessWords();
		
		try {
			loadReviews("src/movie_review_dataset/part1/neg", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			loadReviews("src/movie_review_dataset/part2/neg", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			loadReviews("src/movie_review_dataset/part1/pos", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			loadReviews("src/movie_review_dataset/part2/pos", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		System.out.println("Ok.\n");
		
		System.out.println(POSreviews.get(10)+"\n");
		
		System.out.println("Processing...\n");
		
		removeUseless();
		
		System.out.println("Ok.\n");
		
		System.out.println(POSreviews.get(10)+"\n");
	}

}
