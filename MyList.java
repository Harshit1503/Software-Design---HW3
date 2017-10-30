package hw3.maven_hw3;
import org.apache.commons.cli.*;
import java.util.Scanner;

public abstract class MyList implements Comparable<Object>
{
	@SuppressWarnings("unchecked")
	static int binSearch(Comparable[]arr1, Comparable key)
		{
			//Declare and Initialize the variables
				int start = 0;
				int end = arr1.length-1;
				int compVal;
				
				while(start<=end)
				{ 
					int mid = (start + end)/2;
					compVal = key.compareTo(arr1[mid]);
					//Check if the key is present at the middle
					if (compVal == 0)
					return 1;
					
					compVal = key.compareTo(arr1[mid]);
					//If key is greater than mid value, check the right half
					if (compVal > 0)
					start = mid + 1;
		
					//If key is smaller than mid value, check the left half
					else
					end = mid - 1;
		
				}
			//Element is not found 
			return 0;
		}

	public static void main(String[] args)
		{
			//Declare and Initialize the variable
				int result;
				
			//Check for the sorted list of integers as an input

					Options options = new Options();
					Comparable [] aList = null;
					
					Option typeOption = Option.builder("type")
							.longOpt("type")
							.hasArg()
							.numberOfArgs(1)
							.required(true)
							.build();
					options.addOption(typeOption);
			
			
					Option keyOption = Option.builder("key")
							.longOpt("key")
							.hasArg()
							.numberOfArgs(1)
							.required(true)
							.build();
					options.addOption(keyOption);

			
					Option listOption = Option.builder("list")
							.longOpt("list")
							.hasArgs()
							.valueSeparator(' ')
							.required(true)
							.build();
					options.addOption(listOption);
					
					CommandLineParser commandLineParser = new DefaultParser();
					CommandLine commandLine;

					try {
						commandLine = commandLineParser.parse(options, args);
					} catch (ParseException e) {
						e.printStackTrace();
						System.exit(1);
						return;
					}
					
					String type = commandLine.getOptionValue("type");
					String[] stringElements = commandLine.getOptionValues("list");
					if(type.equals("i"))
					{ //expect integer key and list
						int key = Integer.parseInt(commandLine.getOptionValue("key"));
						aList = new Integer[stringElements.length];
						for(int x = 0; x < stringElements.length; x++)
						aList[x] = Integer.parseInt(stringElements[x]);
						result = binSearch(aList, key);
						System.out.println(result);
					}
					else if (type.equals("s"))
					{ //expect String key and list
						String key = commandLine.getOptionValue("key");
						aList = commandLine.getOptionValues("list");
						result = binSearch(aList, key);
						System.out.println(result);
					}
		}
}	 
