package it.uniba.sotorrent.cliparse;

public interface CLItoParametersI {

	ParameterSet parseCLI(String[] args) throws IllegalArgumentException;

}
