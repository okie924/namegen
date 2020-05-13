
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class FantasyWriteFile
{
	
	void fileManip(String name) throws IOException
	{
		try(FileWriter out = new FileWriter("GeneratedNames.txt",true);
		BufferedWriter b = new BufferedWriter(out);
		PrintWriter p = new PrintWriter(b);)
		{		
			p.println(name);	
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	void sortFile() throws IOException
	{
		try(BufferedReader read = new BufferedReader(new FileReader("C:/Users/D108478/workspace/Name Generator/GeneratedNames.txt")))
		{
			ArrayList<String> names = new ArrayList<String>();
			
			String currline=read.readLine();
			
			while(currline != null)
			{
				names.add(currline);
				currline = read.readLine();
			}
			
			Collections.sort(names);
			BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/D108478/workspace/Name Generator/GeneratedNames.txt"));
			
			for(String line : names)
			{
				writer.write(line);
				writer.newLine();
			}
			
			if(read != null)
			{
				read.close();
			}
			if(writer != null)
			{
				writer.close();
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	void clearOldData() throws NoSuchFileException
	{
		try
		{
			Files.deleteIfExists(Paths.get("C:/Users/D108478/workspace/Name Generator/GeneratedNames.txt"));	
		}
		catch(NoSuchFileException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
