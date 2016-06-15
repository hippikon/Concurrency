package digital.places.concurrency;

import java.util.ArrayList;

public abstract class AbstractReadFile
{
	public ArrayList<String[]> readInputIntoMemory()
	{
		initFiles();
		dataCheck();
		return loadFilesIntoMemory();
	}
	
	protected abstract void initFiles();
	
	protected abstract void dataCheck();
	
	protected abstract ArrayList<String[]> loadFilesIntoMemory();
}