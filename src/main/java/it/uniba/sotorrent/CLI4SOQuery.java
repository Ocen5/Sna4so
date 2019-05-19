package it.uniba.sotorrent;

import java.util.HashMap;
import java.util.Map;

/**
 * <<boundary>>
 * This class bounds valid parameters and their values from CLI args.
 *
 */
public class CLI4SOQuery {

	private Map<String,Object>parameters = new HashMap<String,Object>();

	public CLI4SOQuery(final String[] args) throws Exception {


		for (int i = 0; i < args.length; i++) {

			String nameParam;
			String valueParam;
			int index = args[i].indexOf("=");

			if (index > 0 && index < args[i].length()) {	//Se "=" esiste != -1, se si strova all'inizio o alla fine non è valido
				nameParam = args[i].substring(0, index);
				valueParam = args[i].substring(index+1);
			} else {
				Exception exception = new Exception(
						"Parametro di input non valido.");
				throw exception;
			}


			//Check here valid name of parameter
			switch (nameParam) {

			case "type" :
				parameters.put(nameParam, valueParam);
				break;


			case "yyyy" :
				parameters.put(nameParam, Integer.valueOf(valueParam));
				break;


			case "mm" :
				parameters.put(nameParam, Integer.valueOf(valueParam));
				break;


			case "dd" :
				parameters.put(nameParam, Integer.valueOf(valueParam));
				break;


			case "taglike" :
				parameters.put(nameParam, valueParam);
				break;


			case "user" :
				parameters.put(nameParam, Integer.valueOf(valueParam));
				break;


			case "limit" :
				parameters.put(nameParam, Integer.valueOf(valueParam));
				break;


			case "edge" :
				parameters.put(nameParam, valueParam);
				break;


			case "weight" :
				parameters.put(nameParam, valueParam);
				break;


			}
		}
	}


	public Map<String,Object> getParameters() {
		return parameters;
	}
}
