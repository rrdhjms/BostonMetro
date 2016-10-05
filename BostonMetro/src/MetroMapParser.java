import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MetroMapParser {
   
	private BufferedReader fileInput;
   
   public MetroMapParser(String filename) throws IOException 
   {   
		//a buffered reader reads line by line, returning null when file is done
	   	filename= "bostonmetro.txt";
		fileInput = new BufferedReader(new FileReader(filename));		
   }
   
   public static void usage() 
   {
	 //prints a usage message to System.out
		System.out.println("java ex3.MetroMapParser <bostonmetro.txt>");
   }
   
   public  void  generateGraphFromFile()
			throws IOException, BadFileException
		    {

	   
	   		MultigraphADT mGraph= new Multigraph();
	   
			String line = fileInput.readLine();
			StringTokenizer st;
			String stationID;
			String stationName;
			String lineName;
			String outboundID, inboundID;
			
			while(line != null)
			{

			    st = new StringTokenizer(line);

			    //We want to handle empty lines effectively, we just ignore them!
			    if(!st.hasMoreTokens())
			    {
				line = fileInput.readLine();
				continue;
			    }
			    
			    //from the grammar, we know that the Station ID is the first token on the line
			    stationID = st.nextToken();
			    
			    if(!st.hasMoreTokens())
			    {
				throw new BadFileException("no station name");
			    }

			    //from the grammar, we know that the Station Name is the second token on the line.
			    stationName = st.nextToken();
			    
			    if(!st.hasMoreTokens())
			    {
				throw new BadFileException("station is on no lines");
			    }
			    NodeIn metroStation = new Node(stationID,stationName);
			    mGraph.addNode(stationID,stationName);

			    while(st.hasMoreTokens())
			    {
				lineName = st.nextToken();
				
				if(!st.hasMoreTokens())
				{
				    throw new BadFileException("poorly formatted line info");
				}

				outboundID = st.nextToken();
				
				if(!st.hasMoreTokens())
				{
				    throw new BadFileException("poorly formatted adjacent stations");
				}

				inboundID = st.nextToken();			
				EdgeIn metroLine= new Edge(lineName,outboundID,inboundID);
				mGraph.addEdge(lineName,outboundID, inboundID);
				mGraph.addEdge(lineName,inboundID, outboundID);
			    }
			    		
			    line = fileInput.readLine();
			}
			
		       
		    }

   

   
   public static void main(String[] args) {
	   //wtf is args??
	   if(args.length != 1)
		{
		    usage();
		    System.exit(0);
		}
	   
   }
   
   }
