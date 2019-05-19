package it.uniba.sotorrent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import it.uniba.sotorrent.soquery.*;

public class SOQuerySelector {

	String nameQuery;
	ISOQuery query;
		
	public SOQuerySelector(Map<String,Object>parameters) throws FileNotFoundException, IOException {
		
		System.out.println(parameters.toString());

		//Query yyyy mm dd limit
		if(parameters.containsKey("yyyy") && parameters.containsKey("mm") && parameters.containsKey("dd") && parameters.containsKey("limit")) {

			//qui implementare errori per il controllo dei valori dei 4 parametri

			Integer year = (Integer) parameters.get("yyyy");
			Integer month = (Integer) parameters.get("mm");
			Integer day = (Integer) parameters.get("dd");
			Integer limit = (Integer) parameters.get("limit");

			if (parameters.containsKey("type")) {	//Needed for chose type results of query
				switch ((String) parameters.get("type")) {

				case "question" :	//avaible edge and weight
					if (parameters.containsKey("edge") && parameters.get("edge").equals("yes")) {			//se con archi

						if (parameters.containsKey("weight" ) && parameters.get("weight").equals("yes")) {	//se archi pesati
							nameQuery = new String("Seleziona le prime "
									+ limit
									+ " coppie (from,to) relative a domande poste il "
									+ year + "/" + month + "/" + day );
							query = new SOQueryQuestionDayWeight(year, month, day, limit);
						}
						if (!parameters.containsKey("weight" ) || parameters.get("weight").equals("no")) {	//se archi semplici
							nameQuery = new String("Seleziona le prime "
									+ limit
									+ " coppie(from,to) di id utente che hanno fatto almeno una domanda il "
									+ year + "/" + month + "/" + day);
							query = new SOQueryQuestionDayEdge(year, month, day, limit);
						}
					}
					if (!parameters.containsKey("edge") || parameters.get("edge").equals("no")) {	//se senza archi
						nameQuery = new String("Seleziona i primi "
								+ limit
								+ " id utente che hanno fatto almeno una domanda il "
								+ year + "/" + month + "/" + day);
						query = new SOQueryQuestionDay(year, month, day, limit);
					}
					break;

				case "answer" ://not avaible edge and weight
					nameQuery = new String("Seleziona i primi "
							+ limit
							+ "id utente che hanno dato almeno una risposta il "
							+ year + "/" + month + "/" + day);
					query = new SOQueryAnswerDay(year, month, day, limit);
					break;

				case "post" ://not avaible edge and weight
					nameQuery = new String("Seleziona i primi "
							+ limit
							+ "id utente che hanno fatto almeno un post il "
							+ year + "/" + month + "/" + day);
					query = new SOQueryPostDay(year, month, day, limit);
					break;
				}

			}
		}



		//Query yyyy mm taglike limit
		if(parameters.containsKey("yyyy") && parameters.containsKey("mm") && parameters.containsKey("taglike") && parameters.containsKey("limit")) {
			//qui implementare errori per il controllo dei valori dei 4 parametri
			Integer year = (Integer) parameters.get("yyyy");
			Integer month = (Integer) parameters.get("mm");
			String taglike = (String) parameters.get("taglike");
			Integer limit = (Integer) parameters.get("limit");

			if (parameters.containsKey("type")) {	//Needed for chose type results of query
				switch ((String) parameters.get("type")) {

				case "question" :	//not avaible edge and weight
					nameQuery = new String("Seleziona i primi "
							+ limit
							+ " id utente che hanno fatto almeno una domanda sull' argomento "
							+ taglike + " il " + year + "/" + month);
					query = new SOQueryQuestionTags(year, month, taglike, limit);
					break;

				case "answer" ://not avaible edge and weight
					nameQuery = new String("Seleziona i primi "
							+ limit
							+ " id utente che hanno dato almeno una risposta sull' argomento "
							+ taglike + " il " + year + "/" + month);
					query = new SOQueryAnswerTags(year, month, taglike, limit);
					break;

				case "post" ://not avaible edge and weight
					nameQuery = new String("Seleziona i primi "
							+ limit
							+ " id utente che hanno fatto almeno un post sull' argomento "
							+ taglike + " il " + year + "/" + month);
					query = new SOQueryPostTags(year, month, taglike, limit);
					break;
				}
			}
		}


		//Query user limit
		if(parameters.containsKey("user") && parameters.containsKey("limit")) {
			//qui implementare errori per il controllo dei valori dei 4 parametri
			Integer user = (Integer) parameters.get("user");
			Integer limit = (Integer) parameters.get("limit");

			if (parameters.containsKey("type")) {	//Needed for chose type results of query
				switch ((String) parameters.get("type")) {

				case "question" :	//avaible edge and weight
					if (parameters.containsKey("edge") && parameters.get("edge").equals("yes")) {			//se con archi

						if (parameters.containsKey("weight" ) && parameters.get("weight").equals("yes")) {	//se archi pesati
							nameQuery = new String("Seleziona le prime "
									+ limit
									+ " coppie (from,to) con peso relative a domande poste dall'utente (to) "
									+ user);
							query = new SOQueryQuestionUsrWeight(user, limit);
						}
						if (!parameters.containsKey("weight" ) || parameters.get("weight").equals("no")) {	//se archi semplici
							nameQuery = new String("Seleziona le prime "
									+ limit
									+ " coppie (from,to) relative a domande poste dall'utente (to) "
									+ user);
							query = new SOQueryQuestionUsrEdge(user, limit);
						}
					}
					break;

				case "answer" ://not avaible edge and weight
					if (parameters.containsKey("edge") && parameters.get("edge").equals("yes")) {			//se con archi

						if (parameters.containsKey("weight" ) && parameters.get("weight").equals("yes")) {	//se archi pesati
							nameQuery = new String("Seleziona le prime "
									+ limit
									+ " coppie (from,to) con peso relative a risposte effetuate dall'utente (from) "
									+ user);
							query = new SOQueryAnswerUsrWeight(user, limit);
						}
						if (!parameters.containsKey("weight" ) || parameters.get("weight").equals("no")) {	//se archi semplici
							nameQuery = new String("Seleziona le prime "
									+ limit
									+ " coppie (from,to) relative a risposte effetuate dall'utente (from) "
									+ user);
							query = new SOQueryAnswerUsrEdge(user, limit);
						}
					}
					break;
				}
			}
		}


		//END CONSTRUCTOR
	}

	public ISOQuery getQuery() {
		return query;
	}
	
	public String getNameQuery() {
		return nameQuery;
	}

}