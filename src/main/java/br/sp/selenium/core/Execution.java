package br.sp.selenium.core;

public class Execution {
	
	public static boolean CLOSE_BROWSER = true;

//	public static TypeExecution Type_Execution = TypeExecution.GRID;
	public static TypeExecution Type_Execution = TypeExecution.LOCAL;
	
	public enum TypeExecution {
		LOCAL,
		GRID
	}

}
