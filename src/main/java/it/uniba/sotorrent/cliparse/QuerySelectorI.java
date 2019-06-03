package it.uniba.sotorrent.cliparse;

import it.uniba.sotorrent.soqueries.SOQueryI;

/**
 * Control
 * Interface to select the right query according to the parameters.
 * Author codd
 *
 */
public interface QuerySelectorI {

	/**
	 * Selects the right query.
	 * 
	 * @return SOQueryI The selected query.
	 * @throws IllegalArgumentException Wrong parameters passed.
	 */
	SOQueryI selectQuery() throws IllegalArgumentException;

}
