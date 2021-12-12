import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

public class convertToArff {
	
	// DEBUGS, bote como true para ver os detalhes
	public static boolean DEBUG_lendo_arquivos = false;
	public static boolean DEBUG_separando_em_palavras = false;
	public static boolean DEBUG_definindo_palavras_feias = false;
	public static boolean DEBUG_organizando_palavras = false;
	public static boolean DEBUG_top = false;
	public static boolean DEBUG_ambiguidade = false;
	public static boolean DEBUG_top2 = false;
	public static boolean DEBUG_escrevendo = false;
	
	// Local das reviews
	public static String local_das_negativas = "src/movie_review_dataset/part1/neg";
	public static String local_das_positivas = "src/movie_review_dataset/part1/pos";
	
	// Quantas palavras positivas e negativas
	public static int quantidade_de_palavras = 100;
	
	// Nome do arquivo onde vai ser salvo (terminar com .arff)
	public static String nome_arquivo = "src/arquivo100.arff";
	
	// Reviews
	public static ArrayList<String> review_negativa = new ArrayList<String>();
	public static ArrayList<String> review_positiva = new ArrayList<String>();
	
	// Palavras
	public static ArrayList<String> palavra_negativa = new ArrayList<String>();
	public static ArrayList<String> palavra_positiva = new ArrayList<String>();
	
	// Palavras que vão atrapalhar
	public static ArrayList<String> palavras_ruims = new ArrayList<String>();
	
	// Palavras que vão atrapalhar 2 (vamos evitar fazer muita alteração agora no codigo)
	public static ArrayList<String> palavras_ruims2 = new ArrayList<String>();
	
	// Palavras top
	public static ArrayList<TopWord> palavra_negativa_top = new ArrayList<TopWord>();
	public static ArrayList<TopWord> palavra_positiva_top = new ArrayList<TopWord>();
	
	// Palavras top e seus contadores
	public static class TopWord {
		public String palavra;
		public int contador;
	}
	
	public static void main(String[] args) {

	    long startTime = System.currentTimeMillis();

	    System.out.println(">> Lendo Arquivos");
	    
		lendoArquivos();
		local_das_negativas = "src/movie_review_dataset/part2/neg";
		local_das_positivas = "src/movie_review_dataset/part2/pos";
		lendoArquivos();

	    System.out.println("<< Finalizado");
	    System.out.println(">> Definindo Palavras Ruims");
	    
		definindoPalavrasFeias();

	    System.out.println("<< Finalizado");
	    System.out.println(">> Separando Em Palavras");
		
		separandoEmPalavras();

	    System.out.println("<< Finalizado");
	    System.out.println(">> Organizando Palavras");
		
		organizandoPalavras();

	    System.out.println("<< Finalizado");
	    System.out.println(">> Definindo palavras que mais aparecem");
		
		topPalavras();

	    System.out.println("<< Finalizado");
	    System.out.println("<< Removendo ambiguidade");
	    
	    removerAmbiguas();

	    System.out.println(">> Finalizado");
	    System.out.println("<< Organizando novamente");
	    
	    organizandoPalavrasNovamente();

	    System.out.println(">> Finalizado");
	    System.out.println(">> Escrevendo .arff");
		
		escrevendoArff();
		
	    System.out.println("<< Finalizado");
		
		long stopTime = System.currentTimeMillis();
	    System.out.println(stopTime - startTime + "ms");
	}
	
	// Função responsavel por ler as reviews positivas e negativas e salvar na Lista delas
	public static void lendoArquivos() {
		
		File pasta = new File(local_das_negativas);
		File[] lista_dos_arquivos = pasta.listFiles();

		if (DEBUG_lendo_arquivos) {
			
			System.out.println("\tVendo as reviews negativas encontrados na pasta '" + local_das_negativas + "'");
			System.out.println("------------------------------------");
		
			for (int i=0; i < lista_dos_arquivos.length; i++)
				System.out.println(lista_dos_arquivos[i]);

			System.out.println("------------------------------------");
		}
		
		// Percorrer toda a lista com os nomes das reviews negativas
		for(File i : lista_dos_arquivos) {
			if (i.isFile()) {

				// Tentativa de ler os arquivos
				try {
					BufferedReader entrada = new BufferedReader(new FileReader(i));

					// Salvando na lista de reviews
					review_negativa.add(entrada.readLine());
					
					if (DEBUG_lendo_arquivos)
						System.out.println(review_negativa.get(review_negativa.size()-1));
					
					entrada.close();
					
				} catch (IOException x) {
					System.err.format("IOException: %s%n", x);
				}
				
				
			}
		}
		
		
		
		// Agora fazendo o mesmo só que para as reviews positivas
		
		
		pasta = new File(local_das_positivas);
		lista_dos_arquivos = pasta.listFiles();
		
		if (DEBUG_lendo_arquivos) {

			System.out.println("------------------------------------");
			System.out.println("\tVendo as reviews positivas encontrados na pasta '" + local_das_positivas + "'");
			System.out.println("------------------------------------");
			
			for (int i=0; i < lista_dos_arquivos.length; i++)
				System.out.println(lista_dos_arquivos[i]);

			System.out.println("------------------------------------");
		}

		// Percorrer toda a lista com os nomes das reviews positivas
		for(File i : lista_dos_arquivos) {
			if (i.isFile()) {

				// Tentativa de ler os arquivos
				try {
					BufferedReader entrada = new BufferedReader(new FileReader(i));

					// Salvando na lista de reviews
					review_positiva.add(entrada.readLine());
					
					if (DEBUG_lendo_arquivos)
						System.out.println(review_positiva.get(review_positiva.size()-1));
					
					entrada.close();
					
				} catch (IOException x) {
					System.err.format("IOException: %s%n", x);
				}
				
				
			}
		}
		
		if (DEBUG_lendo_arquivos)
			System.out.println("------------------------------------");;
		
	}

	// Palavras que não nos ajudao no trabalho
	public static void definindoPalavrasFeias() {
		
		if (DEBUG_definindo_palavras_feias) {
			System.out.println("\t Palavras que não ajudam o programa");
			System.out.println("------------------------------------");
		}
		
		// DEFINA TODAS AS PALAVRAS FEIAS POR AQUI
		
		
		// Not words
		palavras_ruims.add(".");
		palavras_ruims.add("?");
		palavras_ruims.add("!");
		palavras_ruims.add(",");
		palavras_ruims.add("-");
		palavras_ruims.add("(");
		palavras_ruims.add(")");
		palavras_ruims.add("/");
		palavras_ruims.add(">");
		palavras_ruims.add("<");
		palavras_ruims.add("&");
		palavras_ruims.add("$");
		palavras_ruims.add("#");
		palavras_ruims.add("@");
		palavras_ruims.add("*");
		palavras_ruims.add("\"");
		palavras_ruims.add(" br ");
		
		// Numbers
		palavras_ruims.add("0");
		palavras_ruims.add("1");
		palavras_ruims.add("2");
		palavras_ruims.add("3");
		palavras_ruims.add("4");
		palavras_ruims.add("5");
		palavras_ruims.add("6");
		palavras_ruims.add("7");
		palavras_ruims.add("8");
		palavras_ruims.add("9");
		
		// Letras sozinhas não querem dizer nada (se a review começar com uma letra só então essa letra vai escapar essa condição)
		palavras_ruims2.add("a");
		palavras_ruims2.add("b");
		palavras_ruims2.add("c");
		palavras_ruims2.add("d");
		palavras_ruims2.add("e");
		palavras_ruims2.add("f");
		palavras_ruims2.add("g");
		palavras_ruims2.add("h");
		palavras_ruims2.add("i");
		palavras_ruims2.add("j");
		palavras_ruims2.add("k");
		palavras_ruims2.add("l");
		palavras_ruims2.add("m");
		palavras_ruims2.add("n");
		palavras_ruims2.add("o");
		palavras_ruims2.add("p");
		palavras_ruims2.add("q");
		palavras_ruims2.add("r");
		palavras_ruims2.add("s");
		palavras_ruims2.add("t");
		palavras_ruims2.add("u");
		palavras_ruims2.add("v");
		palavras_ruims2.add("x");
		palavras_ruims2.add("z");
		palavras_ruims2.add("k");
		palavras_ruims2.add("w");
		palavras_ruims2.add("y");
		
		// Articles
		palavras_ruims2.add("an");
		palavras_ruims2.add("the");
		
		// Adverbs of Manner
		palavras_ruims2.add("carefully");
		palavras_ruims2.add("correctly");
		palavras_ruims2.add("eagerly");
		palavras_ruims2.add("easily");
		palavras_ruims2.add("fast");
		palavras_ruims2.add("loudly");
		palavras_ruims2.add("patiently");
		palavras_ruims2.add("quickly");
		palavras_ruims2.add("well");
		
		// Adverbs of place
		palavras_ruims2.add("abroad");
		palavras_ruims2.add("anywhere");
		palavras_ruims2.add("downstairs");
		palavras_ruims2.add("here");
		palavras_ruims2.add("home");
		palavras_ruims2.add("in");
		palavras_ruims2.add("nowhere");
		palavras_ruims2.add("out");
		palavras_ruims2.add("outside");
		palavras_ruims2.add("somewhere");
		palavras_ruims2.add("there");
		palavras_ruims2.add("underground");
		palavras_ruims2.add("upstairs");
		
		// Adverbs of purpose
		palavras_ruims2.add("so");
		palavras_ruims2.add("so that");
		palavras_ruims2.add("to");
		palavras_ruims2.add("in order to");
		palavras_ruims2.add("because");
		palavras_ruims2.add("since");
		palavras_ruims2.add("accidentally");
		palavras_ruims2.add("intentionally");
		palavras_ruims2.add("purposely");
		
		// Adverbs of frequency
		palavras_ruims2.add("always");
		palavras_ruims2.add("every");
		palavras_ruims2.add("never");
		palavras_ruims2.add("often");
		palavras_ruims2.add("rarely");
		palavras_ruims2.add("seldom");
		palavras_ruims2.add("sometimes");
		palavras_ruims2.add("usually");
		
		// Adverbs of time
		palavras_ruims2.add("after");
		palavras_ruims2.add("already");
		palavras_ruims2.add("during");
		palavras_ruims2.add("finally");
		palavras_ruims2.add("just");
		palavras_ruims2.add("last");
		palavras_ruims2.add("later");
		palavras_ruims2.add("next");
		palavras_ruims2.add("now");
		palavras_ruims2.add("recently");
		palavras_ruims2.add("soon");
		palavras_ruims2.add("then");
		palavras_ruims2.add("tomorrow");
		palavras_ruims2.add("when");
		palavras_ruims2.add("while");
		palavras_ruims2.add("yesterday");
		
		// Adverbs of completeness
		palavras_ruims2.add("everywhere");
		palavras_ruims2.add("here");
		palavras_ruims2.add("there");
		
		// Personal pron
		palavras_ruims2.add("me");
		palavras_ruims2.add("you");
		palavras_ruims2.add("he");
		palavras_ruims2.add("him");
		palavras_ruims2.add("she");
		palavras_ruims2.add("her");
		palavras_ruims2.add("it");
		palavras_ruims2.add("we");
		palavras_ruims2.add("us");
		palavras_ruims2.add("they");
		palavras_ruims2.add("them");
		
		// Relative pron
		palavras_ruims2.add("which");
		palavras_ruims2.add("who");
		palavras_ruims2.add("whom");
		palavras_ruims2.add("whose");
		palavras_ruims2.add("whichever");
		palavras_ruims2.add("whoever");
		palavras_ruims2.add("whomever");
		
		// Demonstrative pron
		palavras_ruims2.add("this");
		palavras_ruims2.add("that");
		palavras_ruims2.add("these");
		palavras_ruims2.add("those");
		
		// Reflexive pron
		palavras_ruims2.add("myself");
		palavras_ruims2.add("yourself");
		palavras_ruims2.add("himself");
		palavras_ruims2.add("herself");
		palavras_ruims2.add("oneself");
		palavras_ruims2.add("itself");
		palavras_ruims2.add("ourselves");
		palavras_ruims2.add("yourselves");
		palavras_ruims2.add("themselves");
		
		// Interrogative pron
		palavras_ruims2.add("what");
		palavras_ruims2.add("where");
		palavras_ruims2.add("when");
		palavras_ruims2.add("why");
		
		// Possessive adj
		palavras_ruims2.add("my");
		palavras_ruims2.add("his");
		palavras_ruims2.add("her");
		palavras_ruims2.add("its");
		palavras_ruims2.add("our");
		palavras_ruims2.add("their");
		palavras_ruims2.add("your");
		
		// Possessive pron
		palavras_ruims2.add("mine");
		palavras_ruims2.add("hers");
		palavras_ruims2.add("ours");
		palavras_ruims2.add("theirs");
		palavras_ruims2.add("yours");
		
		// Prepositions
		palavras_ruims2.add("aboard");
		palavras_ruims2.add("about");
		palavras_ruims2.add("above");
		palavras_ruims2.add("across");
		palavras_ruims2.add("after");
		palavras_ruims2.add("against");
		palavras_ruims2.add("along");
		palavras_ruims2.add("amid");
		palavras_ruims2.add("among");
		palavras_ruims2.add("anti");
		palavras_ruims2.add("around");
		palavras_ruims2.add("as");
		palavras_ruims2.add("at");
		palavras_ruims2.add("before");
		palavras_ruims2.add("behind");
		palavras_ruims2.add("below");
		palavras_ruims2.add("beneath");
		palavras_ruims2.add("beside");
		palavras_ruims2.add("besides");
		palavras_ruims2.add("between");
		palavras_ruims2.add("beyond");
		palavras_ruims2.add("by");
		palavras_ruims2.add("concerning");
		palavras_ruims2.add("considering");
		palavras_ruims2.add("depsite");
		palavras_ruims2.add("down");					//
		palavras_ruims2.add("during");
		palavras_ruims2.add("except");
		palavras_ruims2.add("excepting");
		palavras_ruims2.add("excluding");
		palavras_ruims2.add("following");
		palavras_ruims2.add("for");
		palavras_ruims2.add("from");
		palavras_ruims2.add("in");
		palavras_ruims2.add("inside");
		palavras_ruims2.add("into");
		palavras_ruims2.add("like");
		palavras_ruims2.add("minus");
		palavras_ruims2.add("near");
		palavras_ruims2.add("of");
		palavras_ruims2.add("off");
		palavras_ruims2.add("on");
		palavras_ruims2.add("onto");
		palavras_ruims2.add("opposite");
		palavras_ruims2.add("outside");
		palavras_ruims2.add("over");
		palavras_ruims2.add("past");
		palavras_ruims2.add("per");
		palavras_ruims2.add("plus");
		palavras_ruims2.add("regarding");
		palavras_ruims2.add("round");
		palavras_ruims2.add("save");
		palavras_ruims2.add("since");
		palavras_ruims2.add("than");
		palavras_ruims2.add("through");
		palavras_ruims2.add("to");
		palavras_ruims2.add("toward");
		palavras_ruims2.add("towards");
		palavras_ruims2.add("under");
		palavras_ruims2.add("underneath");
		palavras_ruims2.add("unlike");
		palavras_ruims2.add("until");
		palavras_ruims2.add("up");
		palavras_ruims2.add("upon");
		palavras_ruims2.add("versus");
		palavras_ruims2.add("via");
		palavras_ruims2.add("with");
		palavras_ruims2.add("within");
		palavras_ruims2.add("without");
		
		// Verbs
		palavras_ruims2.add("be");
		palavras_ruims2.add("is");
		palavras_ruims2.add("are");
		palavras_ruims2.add("was");
		palavras_ruims2.add("were");
		palavras_ruims2.add("will");
		palavras_ruims2.add("have");
		palavras_ruims2.add("has");
		palavras_ruims2.add("had");
		palavras_ruims2.add("hadn");
		palavras_ruims2.add("haven");
		palavras_ruims2.add("hasn");
		palavras_ruims2.add("do");
		palavras_ruims2.add("don");
		palavras_ruims2.add("does");
		palavras_ruims2.add("doesn");
		palavras_ruims2.add("did");
		palavras_ruims2.add("come");
		palavras_ruims2.add("came");
		palavras_ruims2.add("make");
		palavras_ruims2.add("made");
		palavras_ruims2.add("say");
		palavras_ruims2.add("said");
		palavras_ruims2.add("go");
		palavras_ruims2.add("went");
		palavras_ruims2.add("gone");
		palavras_ruims2.add("get");
		palavras_ruims2.add("got");
		palavras_ruims2.add("gotten");
		palavras_ruims2.add("know");
		palavras_ruims2.add("knew");
		palavras_ruims2.add("known");
		palavras_ruims2.add("see");
		palavras_ruims2.add("saw");
		palavras_ruims2.add("seen");
		palavras_ruims2.add("think");
		palavras_ruims2.add("thought");
		palavras_ruims2.add("take");
		palavras_ruims2.add("took");
		palavras_ruims2.add("taken");
		palavras_ruims2.add("use");
		palavras_ruims2.add("used");
		palavras_ruims2.add("watch");
		palavras_ruims2.add("watched");
		palavras_ruims2.add("want");
		palavras_ruims2.add("wanted");
		
		// Moda verbs
		palavras_ruims2.add("can");
		palavras_ruims2.add("could");
		palavras_ruims2.add("may");
		palavras_ruims2.add("might");
		palavras_ruims2.add("must");
		palavras_ruims2.add("mustn");
		palavras_ruims2.add("shall");
		palavras_ruims2.add("should");
		palavras_ruims2.add("shouldn");
		palavras_ruims2.add("ought to");
		palavras_ruims2.add("would");
		
		// Conjunctions
		palavras_ruims2.add("and");
		palavras_ruims2.add("but");
		palavras_ruims2.add("or");
		palavras_ruims2.add("yet");
		palavras_ruims2.add("for");
		palavras_ruims2.add("nor");
		palavras_ruims2.add("so");
		palavras_ruims2.add("if");
		
		// others
		palavras_ruims2.add("movie");
		palavras_ruims2.add("film");
		palavras_ruims2.add("guy");
		palavras_ruims2.add("couldnt");
		palavras_ruims2.add("house");
		palavras_ruims2.add("american");
		palavras_ruims2.add("theres");
		palavras_ruims2.add("dont");
		palavras_ruims2.add("wasnt");
		palavras_ruims2.add("else");
		palavras_ruims2.add("either");
		palavras_ruims2.add("very");
		palavras_ruims2.add("minutes");
		palavras_ruims2.add("script");
		palavras_ruims2.add("trying");
		palavras_ruims2.add("maybe");
		palavras_ruims2.add("put");
		palavras_ruims2.add("comes");
		palavras_ruims2.add("one");
		palavras_ruims2.add("two");
		palavras_ruims2.add("three");
		palavras_ruims2.add("four");
		palavras_ruims2.add("five");
		palavras_ruims2.add("six");
		palavras_ruims2.add("seven");
		palavras_ruims2.add("eight");
		palavras_ruims2.add("nine");
		palavras_ruims2.add("mother");
		palavras_ruims2.add("dad");
		palavras_ruims2.add("son");
		palavras_ruims2.add("sister");
		palavras_ruims2.add("brother");
		palavras_ruims2.add("kids");
		palavras_ruims2.add("years");
		palavras_ruims2.add("men");
		palavras_ruims2.add("john");
		palavras_ruims2.add("dvd");
		palavras_ruims2.add("name");
		palavras_ruims2.add("takes");
		palavras_ruims2.add("im");
		
		
		
		
		// 5 vezes apenas para ter certeza (leia-se gambiarra)
		palavras_ruims.add("  "); // Não queremos que tenha espaços em brancos como palavra
		palavras_ruims.add("  "); // Não queremos que tenha espaços em brancos como palavra
		palavras_ruims.add("  "); // Não queremos que tenha espaços em brancos como palavra
		palavras_ruims.add("  "); // Não queremos que tenha espaços em brancos como palavra
		palavras_ruims.add("  "); // Não queremos que tenha espaços em brancos como palavra
		palavras_ruims.add("  "); // Não queremos que tenha espaços em brancos como palavra
		
		if (DEBUG_definindo_palavras_feias) {
			System.out.println("Numero de palavras ruims: " + (palavras_ruims.size() + palavras_ruims2.size()));
			System.out.println("------------------------------------");
		}
		
	}
	
	// Separa as reviews em palavras e guarda em um array
	// Apenas pega cada palavra de uma string e bota em uma lista
	public static void separandoEmPalavras() {
		
		if (DEBUG_separando_em_palavras) {
			System.out.println("\t Tirando as palavras/sinais ruims das reviews");
			System.out.println("------------------------------------");
		}
			
		// Para cada review negativa pegar as palavras dela
		for (int i = 0; i < review_negativa.size(); i++) {
				
			// Trocar essa parte da string por espaço
			for(int j = 0; j < palavras_ruims.size(); j++) {
				review_negativa.set(i, review_negativa.get(i).replace(palavras_ruims.get(j), " "));
				
				if (DEBUG_separando_em_palavras) 
					System.out.println(review_negativa.get(i));
			}
			review_negativa.set(i, review_negativa.get(i).replace("'", ""));

			if (DEBUG_separando_em_palavras) {
				System.out.println("------------------------------------");
				System.out.println("\t Palavras dessa review");
				System.out.println("------------------------------------");
			}
			
			
			// Pega uma review e corta em varias palavras
			String[] palavras_de_uma_review = review_negativa.get(i).split(" ");
			
			// Bota cada palavra na lista
			for (int w = 0; w < palavras_de_uma_review.length; w++) {
				if (!palavras_de_uma_review[w].matches("^\\s*$")){
				
					palavras_de_uma_review[w] = palavras_de_uma_review[w].toLowerCase();
					
					for(int x = 0; x < palavras_ruims2.size(); x++) {
						if (palavras_de_uma_review[w].compareTo(palavras_ruims2.get(x)) == 0)
							x = palavras_ruims2.size();
						
						if (x == palavras_ruims2.size() - 1) {
							palavra_negativa.add(palavras_de_uma_review[w]);
							
							if (DEBUG_separando_em_palavras)
								System.out.println(palavra_negativa.get(palavra_negativa.size()-1));
						}
					}
					
					if (palavras_ruims2.size() == 0) {
						palavra_negativa.add(palavras_de_uma_review[w]);
						
						if (DEBUG_separando_em_palavras)
							System.out.println(palavra_negativa.get(palavra_negativa.size()-1));
					}
				}
			}
			
			if (DEBUG_separando_em_palavras)
				System.out.println("------------------------------------");
		}
		
		// Para cada review positiva pegar as palavras dela
		for (int i = 0; i < review_positiva.size(); i++) {
						
			// Trocar essa parte da string por espaço
			for(int j = 0; j < palavras_ruims.size(); j++) {
				review_positiva.set(i, review_positiva.get(i).replace(palavras_ruims.get(j), " "));
				
				if (DEBUG_separando_em_palavras) 
					System.out.println(review_positiva.get(i));
			}
			review_positiva.set(i, review_positiva.get(i).replace("'", ""));

			if (DEBUG_separando_em_palavras) {
				System.out.println("------------------------------------");
				System.out.println("\t Palavras dessa review");
				System.out.println("------------------------------------");
			}
			
			// Pega uma review e corta em varias palavras
			String[] palavras_de_uma_review = review_positiva.get(i).split(" ");
			
			// Bota cada palavra na lista
			for (int w = 0; w < palavras_de_uma_review.length; w++) {
				if (!palavras_de_uma_review[w].matches("^\\s*$")) {
				
					palavras_de_uma_review[w] = palavras_de_uma_review[w].toLowerCase();
					
					for(int x = 0; x < palavras_ruims2.size(); x++) {
						if (palavras_de_uma_review[w].compareTo(palavras_ruims2.get(x)) == 0)
							x = palavras_ruims2.size();
						
						if (x == palavras_ruims2.size() - 1) {
							palavra_positiva.add(palavras_de_uma_review[w]);
							
							if (DEBUG_separando_em_palavras)
								System.out.println(palavra_positiva.get(palavra_positiva.size()-1));
						}
					}
					
					if (palavras_ruims2.size() == 0) {
						palavra_positiva.add(palavras_de_uma_review[w]);
						
						if (DEBUG_separando_em_palavras)
							System.out.println(palavra_positiva.get(palavra_positiva.size()-1));
					}
				}
			}
			
			if (DEBUG_separando_em_palavras)
				System.out.println("------------------------------------");
		}
		
	}
	
	// Organiza a lista de palavras em ordem
	public static void organizandoPalavras() {

		// Vai organizar em ordem alfabetica
		Collections.sort(palavra_negativa);
		Collections.sort(palavra_positiva);
		
		if (DEBUG_organizando_palavras){
			System.out.println("------------------------------------");
			System.out.println("\t Organizando as palavras negativas em ordem alfabetica");
			System.out.println("------------------------------------");
			for(int i = 0; i < palavra_negativa.size(); i++)
				System.out.println(palavra_negativa.get(i));
		}
		
		if (DEBUG_organizando_palavras){
			System.out.println("------------------------------------");
			System.out.println("\t Organizando as palavras positivas em ordem alfabetica");
			System.out.println("------------------------------------");
			for(int i = 0; i < palavra_positiva.size(); i++)
				System.out.println(palavra_positiva.get(i));
		}

	}

	// Organiza as palavras em ordem de quem mais aparece
	public static void topPalavras() {
		
		TopWord top = new TopWord();	// Sempre usar ele para encher a lista
		
		String atual;	// Guarda na memoria a string que estamos contando
		String nova;	// A string que pode subistituir a antiga
		int index;		// Como o vetor vai estar o tempo todo tentando se organizar, nós precisamos saber onde esta a palavra que queremos aumentar o contador
		
		int contadorTemporario;
		String stringTemporaria;
		
		if(DEBUG_top) {
			System.out.println("------------------------------------");
			System.out.println("\t Pegando as top palavras negativas que mais aparecem e botando em ordem");
			System.out.println("------------------------------------");
		}
		
		atual = palavra_negativa.get(0);
		
		top.contador = 1;
		top.palavra = atual;
		
		palavra_negativa_top.add(top);
		
		index = 0;
		
		// Contando o numero de vezes que uma mesma palavra aparece
		for(int i = 1; i < palavra_negativa.size(); i++) {
			nova = palavra_negativa.get(i);
			
			if (atual.compareTo(nova) != 0) {
				
				// Se a string for diferente começar um contador novo
				atual = nova;
				
				top = new TopWord();
				top.contador = 1;
				top.palavra = atual;
				
				palavra_negativa_top.add(top);
				
				index = palavra_negativa_top.size()-1;
				
			} else {
				palavra_negativa_top.get(index).contador++;
				
				// Ordem do maior pro menor contador
				for(int j = palavra_negativa_top.size()-1; j > 0; j--){
					
					if (palavra_negativa_top.get(j).contador > palavra_negativa_top.get(j-1).contador) {
						
						contadorTemporario = palavra_negativa_top.get(j-1).contador;
						stringTemporaria = palavra_negativa_top.get(j-1).palavra;
						
						palavra_negativa_top.get(j-1).contador = palavra_negativa_top.get(j).contador;
						palavra_negativa_top.get(j-1).palavra = palavra_negativa_top.get(j).palavra;

						palavra_negativa_top.get(j).contador = contadorTemporario;
						palavra_negativa_top.get(j).palavra = stringTemporaria;
						
						index = index-1;
						
					}
				}
			}
		}

		
		if(DEBUG_top) {
			for(int i1 = 0; i1 < palavra_negativa_top.size() && i1 < quantidade_de_palavras; i1++)
				System.out.printf("%s ==== %d\n", palavra_negativa_top.get(i1).palavra, palavra_negativa_top.get(i1).contador);
			System.out.println("------------------------------------");
		}
		
		// Mesma coisa só que agora para as positivas
			
		top = new TopWord();	// Sempre usar ele para encher a lista
		
		if(DEBUG_top) {
			System.out.println("\t Pegando as top palavras positivas que mais aparecem e botando em ordem");
			System.out.println("------------------------------------");
		}
		
		atual = palavra_positiva.get(0);
		
		top.contador = 1;
		top.palavra = atual;
		
		palavra_positiva_top.add(top);
		
		index = 0;
		
		// Contando o numero de vezes que uma mesma palavra aparece
		for(int i1 = 1; i1 < palavra_positiva.size(); i1++) {
			nova = palavra_positiva.get(i1);
			
			if (atual.compareTo(nova) != 0) {
				
				// Se a string for diferente começar um contador novo
				atual = nova;
				
				top = new TopWord();
				top.contador = 1;
				top.palavra = atual;
				
				palavra_positiva_top.add(top);
				
				index = palavra_positiva_top.size()-1;
				
			} else {
				palavra_positiva_top.get(index).contador++;
				
				// Ordem do maior pro menor contador
				for(int j = palavra_positiva_top.size()-1; j > 0; j--){
					
					if (palavra_positiva_top.get(j).contador > palavra_positiva_top.get(j-1).contador) {
						
						contadorTemporario = palavra_positiva_top.get(j-1).contador;
						stringTemporaria = palavra_positiva_top.get(j-1).palavra;
						
						palavra_positiva_top.get(j-1).contador = palavra_positiva_top.get(j).contador;
						palavra_positiva_top.get(j-1).palavra = palavra_positiva_top.get(j).palavra;
						
						palavra_positiva_top.get(j).contador = contadorTemporario;
						palavra_positiva_top.get(j).palavra = stringTemporaria;
						
						index = index-1;
						
					}
				}
			}
			
		}
		
		if(DEBUG_top) {
			for(int i1 = 0; i1 < palavra_positiva_top.size() && i1 < quantidade_de_palavras; i1++)
				System.out.printf("%s ==== %d\n", palavra_positiva_top.get(i1).palavra, palavra_positiva_top.get(i1).contador);
			System.out.println("------------------------------------");
		}
		
	}

	// Se uma palavra existe na lista de positivas E de negativas, ela tem que ser analisada e tratada de alguma maneira, essa função é como eu trataria
	public static void removerAmbiguas() {
		
		
		if(DEBUG_ambiguidade) {
			System.out.println("------------------------------------");
			System.out.println("\t Removendo ambiguidade das positivas");
			System.out.println("------------------------------------");
		}
		
		if (DEBUG_ambiguidade)
			System.out.println("\t Numero de palavras dentro das top " +  (palavra_negativa_top.size() + palavra_positiva_top.size()));
		
		// Pegar cada uma das palavras das negativas...
		for (int i = 0; i < palavra_negativa_top.size(); i++) {
			
			// ...e comparar com cada uma das positivas
			for (int j = 0; j < palavra_positiva_top.size(); j++) {
				
				// Se forem iguais
				if (palavra_negativa_top.get(i).palavra.compareTo(palavra_positiva_top.get(j).palavra) == 0) {
					
					if(DEBUG_ambiguidade) {
						System.out.println("!!! Foi encontrada uma palavra ambigua !!!");
						System.out.println(palavra_negativa_top.get(i).palavra + " <=> " + palavra_positiva_top.get(j).palavra);
					}
						
					
					if (palavra_negativa_top.get(i).contador > palavra_positiva_top.get(j).contador)  {
						palavra_negativa_top.get(i).contador -= palavra_positiva_top.get(j).contador;
						palavra_positiva_top.remove(j);
						j--;							// Como a posicao j vai virar outra, vai precisar passar de novo aqui
					} else if (palavra_negativa_top.get(i).contador < palavra_positiva_top.get(j).contador) {
						palavra_positiva_top.get(j).contador -= palavra_negativa_top.get(i).contador;
						palavra_negativa_top.remove(i);
						j = 0;							// Como o i vai irar outra palavra, precisamos resetar esse loop
					} else {
						palavra_positiva_top.remove(j);
						palavra_negativa_top.remove(i);
						j = 0;							// Como essa palavra é inutil para nosso aprendizado, jogar fora
					}
					
				}
				
			}
			
		}

		if(DEBUG_ambiguidade) {
			System.out.println("------------------------------------");
			for(int i1 = 0; i1 < palavra_negativa_top.size() && i1 < quantidade_de_palavras; i1++)
				System.out.printf("%s ==== %d\n", palavra_negativa_top.get(i1).palavra, palavra_negativa_top.get(i1).contador);
			System.out.println("------------------------------------");
			System.out.println("\t Removendo ambiguidade das negativas");
			System.out.println("------------------------------------");
			for(int i1 = 0; i1 < palavra_positiva_top.size() && i1 < quantidade_de_palavras; i1++)
				System.out.printf("%s ==== %d\n", palavra_positiva_top.get(i1).palavra, palavra_positiva_top.get(i1).contador);
			System.out.println("------------------------------------");
		}
		
	}
	
	// Equilibrando novamente
	public static void organizandoPalavrasNovamente() {
		
		TopWord temporario = new TopWord();
		
		if(DEBUG_top2) {
			System.out.println("------------------------------------");
			System.out.println("\t Organizando pela segunda vez as negativas (bubble pode demorar um pouco)");
			System.out.println("------------------------------------");
		}
		
		// Organizando negativas
		for (int j = palavra_negativa_top.size()-1; j >= 1; j--) {
		
			for (int i = 0; i < j; i++) {
			
				if (palavra_negativa_top.get(i).contador < palavra_negativa_top.get(i+1).contador) {

					temporario.palavra = palavra_negativa_top.get(i+1).palavra;
					temporario.contador = palavra_negativa_top.get(i+1).contador;
					
					palavra_negativa_top.get(i+1).contador = palavra_negativa_top.get(i).contador;
					palavra_negativa_top.get(i+1).palavra = palavra_negativa_top.get(i).palavra;

					palavra_negativa_top.get(i).contador = temporario.contador;
					palavra_negativa_top.get(i).palavra = temporario.palavra;
					
				
				}
			
			}
		
		}
		

		// Organizando positivas
		for (int j = palavra_positiva_top.size()-1; j >= 1; j--) {
			
			for (int i = 0; i < j; i++) {
			
				if (palavra_positiva_top.get(i).contador < palavra_positiva_top.get(i+1).contador) {

					temporario.palavra = palavra_positiva_top.get(i+1).palavra;
					temporario.contador = palavra_positiva_top.get(i+1).contador;
					
					palavra_positiva_top.get(i+1).contador = palavra_positiva_top.get(i).contador;
					palavra_positiva_top.get(i+1).palavra = palavra_positiva_top.get(i).palavra;

					palavra_positiva_top.get(i).contador = temporario.contador;
					palavra_positiva_top.get(i).palavra = temporario.palavra;
					
				
				}
			
			}
		
		}

		if(DEBUG_top2) {
			for(int i1 = 0; i1 < palavra_negativa_top.size() && i1 < quantidade_de_palavras; i1++)
				System.out.printf("%s ==== %d\n", palavra_negativa_top.get(i1).palavra, palavra_negativa_top.get(i1).contador);
			System.out.println("------------------------------------");
			System.out.println("\t Organizando pela segunda vez as positivas (bubble pode demorar um pouco)");
			System.out.println("------------------------------------");
			for(int i1 = 0; i1 < palavra_positiva_top.size() && i1 < quantidade_de_palavras; i1++)
				System.out.printf("%s ==== %d\n", palavra_positiva_top.get(i1).palavra, palavra_positiva_top.get(i1).contador);
			System.out.println("------------------------------------");
		}
		
	}
	
	// Função que vai escrever um arquivo no estilo arff
	public static void escrevendoArff() {
		
		if(DEBUG_escrevendo) {
			System.out.println("------------------------------------");
			System.out.println("\t Escrevendo o arquivo .arff");
			System.out.println("------------------------------------");
		}
		
		try {
			PrintWriter arquivo = new PrintWriter(nome_arquivo, "UTF-8");
			
			arquivo.println("@relation reviews");
			arquivo.println("");
			
			int contadorTemp = 0; // Apenas para esses dois loops embaixo
			
			/*
			// Escreve as palavras negativas
			for (int i = 0; i < palavra_negativa_top.size() && i < quantidade_de_palavras; i++, contadorTemp = i)
				arquivo.println("@attribute contador" + i + " real");

			// Escreve as palavras positivas
			for (int j = 0; j < palavra_positiva_top.size() && j < quantidade_de_palavras; j++)
				arquivo.println("@attribute contador" + (j + contadorTemp + 1) + " real");
			*/
			
			// Escreve as palavras negativas
			for (int i = 0; i < palavra_negativa_top.size() && i < quantidade_de_palavras; i++, contadorTemp = i)
				arquivo.println("@attribute '" + palavra_negativa_top.get(i).palavra + "' real");

			// Escreve as palavras positivas
			for (int j = 0; j < palavra_positiva_top.size() && j < quantidade_de_palavras; j++)
				arquivo.println("@attribute '" + palavra_positiva_top.get(j).palavra + "' real");

			arquivo.println("@attribute opiniao {pos, neg}");
			arquivo.println("");
			arquivo.println("@data");
			

			if(DEBUG_escrevendo) {
				System.out.println("------------------------------------");
				System.out.println("\t Contando palavras nas reviews negativas");
				System.out.println("------------------------------------");
			}
			
			// Percorre todas as reviews negativas
			for(int j = 0; j < review_negativa.size(); j++) {
				
				// Salva todas as palavras dessa review em um vetor
				String[] palavra = review_negativa.get(j).split(" ");
			
				// Percorre todas as palavras importantes negativas
				for (int i = 0; i < palavra_negativa_top.size() && i < quantidade_de_palavras; i++) {
					
					// Percorre todas palavras da review procurando uma igual a palavra top que procuramos
					int contador = 0;
					for (int k = 0; k < palavra.length; k++) {
						
						if (palavra[k].toLowerCase().compareTo(palavra_negativa_top.get(i).palavra) == 0){
							if (DEBUG_escrevendo)
								System.out.println("Na review negativa " + j + " foi encontrada a palavra " + palavra[k]);
							contador++;
						}
						
					}
				
					arquivo.print(contador + ",");
				}
				
				// Percorre todas as palavras importantes positivas
				for (int i = 0; i < palavra_positiva_top.size() && i < quantidade_de_palavras; i++) {
								
					// Percorre todas palavras da review procurando uma igual a palavra top que procuramos
					int contador = 0;
					for (int k = 0; k < palavra.length; k++) {
									
						if (palavra[k].toLowerCase().compareTo(palavra_positiva_top.get(i).palavra) == 0) {
							if (DEBUG_escrevendo)
								System.out.println("Na review negativa " + j + " foi encontrada a palavra " + palavra[k]);
							contador++;
						}
									
					}
							
					arquivo.print(contador + ",");
				}
				
				arquivo.print("neg" + "\n");
			}
			

			if(DEBUG_escrevendo) {
				System.out.println("------------------------------------");
				System.out.println("\t Contando palavras nas reviews positivas");
				System.out.println("------------------------------------");
			}
			
			// Percorre todas as reviews positivas
			for(int j = 0; j < review_positiva.size(); j++) {
				
				// Salva todas as palavras dessa review em um vetor
				String[] palavra = review_positiva.get(j).split(" ");
				
				// Percorre todas as palavras importantes negativas
				for (int i = 0; i < palavra_negativa_top.size() && i < quantidade_de_palavras; i++) {
					
					// Percorre todas palavras da review procurando uma igual a palavra top que procuramos
					int contador = 0;
					for (int k = 0; k < palavra.length; k++) {
						
						if (palavra[k].toLowerCase().compareTo(palavra_negativa_top.get(i).palavra) == 0){
							if (DEBUG_escrevendo)
								System.out.println("Na review positiva " + j + " foi encontrada a palavra " + palavra[k]);
							contador++;
						}
						
					}
				
					arquivo.print(contador + ",");
				}
						
				// Percorre todas as palavras importantes positivas
				for (int i = 0; i < palavra_positiva_top.size() && i < quantidade_de_palavras; i++) {
								
					// Percorre todas palavras da review procurando uma igual a palavra top que procuramos
					int contador = 0;
					for (int k = 0; k < palavra.length; k++) {
									
						if (palavra[k].toLowerCase().compareTo(palavra_positiva_top.get(i).palavra) == 0) {
							if (DEBUG_escrevendo)
								System.out.println("Na review positiva " + j + " foi encontrada a palavra " + palavra[k]);
							contador++;
						}
									
					}
							
					arquivo.print(contador + ",");
				}
							
				arquivo.print("pos" + "\n");
			}
			
			
			// Fechar depois de usar
			arquivo.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
	}
}
