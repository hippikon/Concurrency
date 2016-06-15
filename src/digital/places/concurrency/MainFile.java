package digital.places.concurrency;
import java.util.*;
public class MainFile
{

	public static void main (String[] args)
	{
		ReadFile rf = new ReadFile();
		ArrayList<String[]> inputs = rf.readInputIntoMemory();
		ProcessFile.touch();
		for (int i=0;i<rf.inputSize;i++)
		{
			new Thread(new ProcessFile(inputs.get(0)[i],inputs.get(1)[i])).start();
		}
	}

}