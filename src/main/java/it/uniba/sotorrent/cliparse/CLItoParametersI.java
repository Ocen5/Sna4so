package it.uniba.sotorrent.cliparse;

/**
 * Control
 * Interface for receiving parameters from CLI and parse them.
 * Author codd
 */
public interface CLItoParametersI {

	/**
	 * This method parses the parameter got from CLI.
	 * 
	 * @param args The command line inputs.
	 * @return paramSet ParameterSet which contains the input parameters to the query.
	 * @throws IllegalArgumentException Wrong parameter passed from CLI.
	 */
	ParameterSet parseCLI(String[] args) throws IllegalArgumentException;

}
