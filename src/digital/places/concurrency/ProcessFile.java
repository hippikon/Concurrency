package digital.places.concurrency;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProcessFile implements Runnable
{
	private static String ofilename = "output.txt";

	private String record1;
	private String record2;

	public ProcessFile(String r1, String r2)
	{
		record1 = r1;
		record2 = r2;
	}

	public void run()
	{
		String line = "";
		if (record1.substring(0,3).equals(record2.substring(0,3)))
		{
			line = record1 + record2.substring(3);
		}
		else
		{
			line = record1;
		}
		writeln(ofilename, line);
	}

	public static void touch()
	{
		BufferedWriter writer = null;
		FileWriter fw = null;
		try
		{
			File file = new File(ofilename);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			fw = new FileWriter(file.getAbsoluteFile(),false);
			writer = new BufferedWriter(fw);
			writer.write("");
		} 
		catch (IOException x) 
		{
		    System.err.format("IOException: %s%n", x);
		}
		finally
		{
			try
			{
				if (writer != null) writer.close();
				if (fw != null) fw.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	public synchronized void writeln(final String ofile, final String outtext)
	{
		BufferedWriter writer = null;
		FileWriter fw = null;
		try
		{
			File file = new File(ofile);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			fw = new FileWriter(file.getAbsoluteFile(),true);
			writer = new BufferedWriter(fw);
			writer.write(outtext);
			writer.newLine();
		} 
		catch (IOException x) 
		{
		    System.err.format("IOException: %s%n", x);
		}
		finally
		{
			try
			{
				if (writer != null) writer.close();
				if (fw != null) fw.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}