package digital.places.concurrency;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
public class ReadFile extends AbstractReadFile
{
	private static String[] inputs;
	private static int[] lines;
	public static int inputSize;

	protected void initFiles()
	{
		inputs = new String[2];
		inputs[0] = "input1.txt";
		inputs[1] = "input2.txt";
		lines = new int[2];
		lines[0] = countLines(inputs[0]) + 1;
		lines[1] = countLines(inputs[1]) + 1;
		inputSize = lines[0];
	}
	
	protected void dataCheck()
	{
		if (lines[0] != lines[1]) throw new RuntimeException();
		
	}

	protected ArrayList<String[]> loadFilesIntoMemory()
	{
		ArrayList<String[]> inputdata = new ArrayList<String[]>();
		inputdata.add(read(inputs[0], lines[0]));
		inputdata.add(read(inputs[1], lines[1]));
		return inputdata;
	}
	
	private static String[] read(final String file, final int count)
	{
		String[] tempout = new String[count];
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null)
			{
				tempout[i++] = line;
			}
		}
		catch (IOException x)
		{
			System.err.format("IOException: %s%n", x);
		}
		finally
		{
			try
			{
				if (reader != null) reader.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return tempout;
	}

	private static int countLines(final String filename)
	{
		InputStream is = null;
		int count = 0;
		boolean empty = true;
		try
		{
			is = new BufferedInputStream(new FileInputStream(filename));
			byte[] c = new byte[1024];
			int readChars = 0;
			while ((readChars = is.read(c)) != -1)
			{
				empty = false;
				for (int i = 0; i < readChars; ++i)
				{
					if (c[i] == '\n')
					{
						++count;
					}
				}
			}
		}
		catch (IOException x)
		{
			System.err.format("IOException: %s%n", x);
		}
		finally
		{
			try
			{
				if (is != null) is.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (count == 0 && !empty) ? 1 : count;
	}
}