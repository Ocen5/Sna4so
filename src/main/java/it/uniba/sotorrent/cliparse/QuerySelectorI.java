package it.uniba.sotorrent.cliparse;

import it.uniba.sotorrent.soqueries.SOQueryI;

public interface QuerySelectorI {

	SOQueryI selectQuery() throws IllegalArgumentException;

}
