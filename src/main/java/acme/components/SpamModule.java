package acme.components;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpamModule {

	/**
	 * Metodo para validar si una cadena de texto contiene una cantidad de spam por encima o por debajo del umbral permitido
	 * @param text Cadena de texto que queremos validar
	 * @param weakSpamWords Lista con las palabras weak-spam
	 * @param strongSpamWords Lista con las palabras strong-spam
	 * @return Si el spam que contiene el texto está por debajo o no del umbral de spam permitido
	 */
	public static Boolean spamValidator(final String text, final List<String>weakSpamWords, final List<String>strongSpamWords){
		final Boolean res;
		final Boolean weakSpamCondition;
		final Boolean strongSpamCondition;
	
		final String myRegexStrong = SpamModule.regexBuilder(strongSpamWords);
		final String myRegexWeak = SpamModule.regexBuilder(weakSpamWords);
		
		final Double strongPercentage = SpamModule.percentageStrongSpamWords(myRegexStrong, text);
		final Double weakPercentage = SpamModule.percentageStrongSpamWords(myRegexWeak, text);
		
//		Comprobamos si supera o no el umbral de palabras strong-spam
		if(strongPercentage >= 10.0 ) {
			strongSpamCondition = false;
		}else {
			strongSpamCondition = true;
		}
		
//		Comprobamos si supera o no el umbral de palabras weak-spam
		if(weakPercentage >= 25.0 ) {
			weakSpamCondition = false;
		}else {
			weakSpamCondition = true;
		}
		
//		Si cumple ambas condiciones de no superar el 25% en strong y el 10% en weak, damos por válido el texto
		res = weakSpamCondition && strongSpamCondition;
		System.out.println("Strong regex : "+myRegexStrong);
		System.out.println("Weak regex : "+myRegexWeak);
		
		System.out.println("Strong percentage : "+strongPercentage);
		System.out.println("Weak percentage : "+weakPercentage);

		System.out.println("Text : "+ text);
		System.out.println("Is valid text? : "+res);

		return res;
	}
	
	
//	Devuelve el porcentaje de strog-spam-words dentro de un texto
	private static double percentageStrongSpamWords(final String myRegex, final String text) {
	    final Pattern pattern = Pattern.compile(myRegex, Pattern.CASE_INSENSITIVE);
	    final Matcher matchResult = pattern.matcher(text);
	    
	    final int totalNumberOfWords = SpamModule.wordsInText(text);
	    final int totalMatches = SpamModule.countMatches(matchResult);
	    System.out.println("Total Words= "+totalNumberOfWords);
	    System.out.println("Matches= "+totalMatches);
	    
	    return ((double)totalMatches/(double)totalNumberOfWords)*100.0;
	}
	
//	Cuenta las veces que una expresion regular hace match 
	private static int countMatches(final Matcher matchResult) {
		int count = 0;
	    while (matchResult.find()) {
	      count++;
	    }
	    return count;
	}
	
//	Numero de palabras que contiene un texto
	private static int wordsInText(final String text) {
		final String[] parts = text.split(" ");
		return parts.length;
		
	}
	

	/**
	 * Este metodo genera una expresion regular a partir de una listad e palabras de spam que obtenemos de la base de datos del sistema
	 * @param spamWords : palabras consideradas spam 
	 * @return expresion regular
	 */
	private static String regexBuilder(final List<String>spamWords) {
		final StringBuilder regexBuilder = new StringBuilder();
		
//		Iteramos sobre la lista de palabras de spam
		for(int i=0; i<spamWords.size(); i++) {
			final String word = spamWords.get(i);
			final String[] parts = word.split(" ");
			
//			Caso de que sea palabra compuesta (Ej "one million dollar")
			if(parts.length > 1) { 
				final StringBuilder auxBuilder = new StringBuilder();
				for(int j=0; j < parts.length; j++) {
					
//					Hemos llegado a la ultima palabra de la palabra compuesta ( Ej nuesta palabra es "one million dollar" y vamos por "dollar" )
					if(j == parts.length-1) {
						auxBuilder.append(parts[j]);
//					Aun no hemos llegado a la ultima palabra de la palabra compuesta ( Ej nuesta palabra es "one million dollar" y vamos por "million"
					}else {
						auxBuilder.append(parts[j]+"[^\\w]*");
					}
				}
				
//				Si ya no quedan palabras en la lista de spamWords (Ej Lista = ["buy bitcoin", "sex", "hardcore"] y vamos por "hardcore")
				if(i == spamWords.size()-1) {
					regexBuilder.append(auxBuilder.toString());
					
//				Si aun quedan palabras en la lista de spamWords (Ej Lista = ["buy bitcoin", "sex", "hardcore"] y vamos por sex)
				}else {
					regexBuilder.append(auxBuilder.toString()+"|");
				}
					
//			Caso de que sea palabra simple ( Ej "sex" )
			}else {		
				if(i == spamWords.size()-1) {
					regexBuilder.append(word);
				}else {
					regexBuilder.append(word+"|");
				}
			}
			
		}
		
		return regexBuilder.toString();
	}
}
