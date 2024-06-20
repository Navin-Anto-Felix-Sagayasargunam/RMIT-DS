import java.io.*;

// needed for interface
import java.util.List;
// needed for multiset container
import java.util.HashMap;
import java.util.Map;
// needed for printing purposes
import java.util.StringJoiner;

// DO NOT USE any OR ADD other java.util.* packages and classes
// UNLESS it is specified in the official assignment 1 FAQ

import implementation.*;

public class MultisetTester
{
	protected static final String progName = "MultisetTester";
	public static void usage(String progName) {
		printErrorMsg(progName + ": <implementation> [(optional) fileName to output results to]");
		printErrorMsg("<implementation> = <array | orderedlinkedlist | bst | duallinkedlist>");
		System.exit(1);
	} 

	public static void printErrorMsg(String msg) {
		System.err.println("> " + msg);
	} 

	public static void printErrorMsg(PrintWriter outWriter, String msg) {
		outWriter.println("> " + msg);
	} 

	public static void processOperations(BufferedReader inReader, PrintWriter outWriter, MultisetImplCreator creator, Map<String, RmitMultiset> multisets)
		throws IOException
	{
		String line;
		boolean bQuit = false;
		while (!bQuit && (line = inReader.readLine()) != null) {
	
			String[] tokens = line.split("\\s+");

			
			if (tokens.length < 1) {
				printErrorMsg("not enough tokens.");
				continue;
			}

			String command = tokens[0];
			
			switch (command) {
			
				case "list":
					outWriter.println("# " + line);
					for (String multisetId : multisets.keySet()) {
						outWriter.println(multisetId);
					}
					break;
		
				case "create":
					if (tokens.length == 2) {
						String id = tokens[1];
				
						if (multisets.containsKey(id)) {
							printErrorMsg("multiset identifier " + id + " is used currently.");
						}
						else {
							RmitMultiset newMultiset = creator.createImplementation();
							multisets.put(id, newMultiset);
						}
					}
					else {
						printErrorMsg("not enough tokens.");
					}
					break;
				
				case "add":
					if (tokens.length == 3) {
						RmitMultiset multiset = multisets.get(tokens[1]);
						if (multiset != null) {
							multiset.add(tokens[2]);
						}
						else {
							printErrorMsg("operation failed, id not found.");
						}
					}
					else {
						printErrorMsg("not enough tokens.");
					}
					break;
			
				case "search":
					outWriter.println("# " + line);
					if (tokens.length == 3) {
						RmitMultiset multiset = multisets.get(tokens[1]);
						if (multiset != null) {
							int foundNumber = multiset.search(tokens[2]);
							if (foundNumber == RmitMultiset.searchFailed) {
								outWriter.println(tokens[2] + " not found");
							}
							else {
								outWriter.println(tokens[2] + " " + foundNumber);
							}
						}
						else {
						
							outWriter.println(RmitMultiset.searchFailed);
							printErrorMsg(outWriter, "operation failed, id not found.");
						}
					}
					else {
					
						outWriter.println(RmitMultiset.searchFailed);
						printErrorMsg(outWriter, "not enough tokens.");
					}
					break;
				
				case "searchByInstance":
					outWriter.println("# " + line);
					if (tokens.length == 3) {
						RmitMultiset multiset = multisets.get(tokens[1]);
						if (multiset != null) {
							int instanceToSearch = Integer.valueOf(tokens[2]);
							if (instanceToSearch > 0) {
								List<String> lItems = multiset.searchByInstance(instanceToSearch);

								if (lItems != null) {
						
									StringJoiner joiner = new StringJoiner(",");
									for (String item : lItems) {
										joiner.add(item);
									}
									outWriter.println(joiner.toString());
								}
								else {
									outWriter.println(RmitMultiset.searchFailed);
									printErrorMsg(outWriter, "operation failed, null returned.");
								}
							}
							else {
							
								outWriter.println(RmitMultiset.searchFailed);
								printErrorMsg(outWriter, "operation failed, instance number must be greater than 0.");
							}
						}
						else {
							
							outWriter.println(RmitMultiset.searchFailed);
							printErrorMsg(outWriter, "operation failed, id not found.");
						}
					}
					else {
						
						outWriter.println(RmitMultiset.searchFailed);
						printErrorMsg(outWriter, "not enough tokens.");
					}
					break;
				case "contains":
					outWriter.println("# " + line);
					if (tokens.length == 3) {
						RmitMultiset multiset = multisets.get(tokens[1]);
						if (multiset != null) {
							boolean bFound = multiset.contains(tokens[2]);
							if (bFound) {
								outWriter.println(tokens[2] + " is in multiset");
							}
							else {
								outWriter.println(tokens[2] + " is not in multiset");
							}
						}
						else {
					
							outWriter.println(RmitMultiset.searchFailed);
							printErrorMsg(outWriter, "operation failed, id not found.");
						}
					}
					else {
						
						outWriter.println(RmitMultiset.searchFailed);
						printErrorMsg(outWriter, "not enough tokens.");
					}
					break;
				
				case "removeOne":
					if (tokens.length == 3) {
						RmitMultiset multiset = multisets.get(tokens[1]);
						if (multiset != null) {
							multiset.removeOne(tokens[2]);
						}
						else {
							printErrorMsg("operation failed, id not found.");
						}
					}
					else {
						printErrorMsg("not enough tokens.");
					}
					break;
				
				case "print":
					outWriter.println("# " + line);
					if (tokens.length == 2) {
						RmitMultiset multiset = multisets.get(tokens[1]);
						if (multiset != null) {
							String sOut = multiset.print();
							outWriter.print(sOut);
							outWriter.flush();
						}
						else {
							printErrorMsg("operation failed, id not found.");
						}
					}
					else {
						printErrorMsg("not enough tokens.");
					}
					break;
			
				case "printRange":
					outWriter.println("# " + line);
					if (tokens.length == 4) {
						RmitMultiset multiset = multisets.get(tokens[1]);
						if (multiset != null) {
							String sOut = multiset.printRange(tokens[2], tokens[3]);
							outWriter.print(sOut);
							outWriter.flush();
						}
						else {
							printErrorMsg("operation failed, id not found.");
						}
					}
					else {
						printErrorMsg("not enough tokens.");
					}
					break;
				
				case "intersect":
					if (tokens.length == 4) {
						RmitMultiset multiset1 = multisets.get(tokens[1]);
						RmitMultiset multiset2 = multisets.get(tokens[2]);
						if (multiset1 != null && multiset2 != null && !multisets.containsKey(tokens[3])) {
							RmitMultiset newMultiset = multiset1.intersect(multiset2);
							if (newMultiset != null) {
								multisets.put(tokens[3], newMultiset);
							}
						}
						else {
							printErrorMsg("operation failed, id not found or duplicate id for new multiset.");
						}
					}
					else {
						printErrorMsg("not enough tokens.");
					}
					break;
			
				case "union":
					if (tokens.length == 4) {
						RmitMultiset multiset1 = multisets.get(tokens[1]);
						RmitMultiset multiset2 = multisets.get(tokens[2]);
						if (multiset1 != null && multiset2 != null && !multisets.containsKey(tokens[3])) {
							RmitMultiset newMultiset = multiset1.union(multiset2);
							if (newMultiset != null) {
								multisets.put(tokens[3], newMultiset);
							}
						}
						else {
							printErrorMsg("operation failed, id not found or duplicate id for new multiset.");
						}
					}
					else {
						printErrorMsg("not enough tokens.");
					}
					break;
				
				case "difference":
					if (tokens.length == 4) {
						RmitMultiset multiset1 = multisets.get(tokens[1]);
						RmitMultiset multiset2 = multisets.get(tokens[2]);
						if (multiset1 != null && multiset2 != null && !multisets.containsKey(tokens[3])) {
							RmitMultiset newMultiset = multiset1.difference(multiset2);
							if (newMultiset != null) {
								multisets.put(tokens[3], newMultiset);
							}
						}
						else {
							printErrorMsg("operation failed, id not found or duplicate id for new multiset.");
						}
					}
					else {
						printErrorMsg("not enough tokens.");
					}
					break;
		
				case "quit":
					bQuit = true;
					break;
				default:
					printErrorMsg("Unknown command.");
			}

		} 

	} 


	
	public static void main(String[] args) {

	
		if (args.length > 2 || args.length < 1) {
			printErrorMsg("Incorrect number of arguments.");
			usage(progName);
		}

		String implementationType = args[0];

		String outFilename = null;
		if (args.length == 2) {
			outFilename = args[1];
		}


		
		Map<String, RmitMultiset> hMultisets =  new HashMap<String, RmitMultiset>();
	
		try {
			MultisetImplCreator creator = new MultisetImplCreator(implementationType);

			
			BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter outWriter = new PrintWriter(System.out, true);

			if (outFilename != null) {
				outWriter = new PrintWriter(new FileWriter(outFilename), true);
			}

			processOperations(inReader, outWriter, creator, hMultisets);
		} catch (IllegalArgumentException e) {
				printErrorMsg(e.getMessage());
				usage(progName);
		}
		catch (IOException e) {
			printErrorMsg(e.getMessage());
		}

	} 

} 
