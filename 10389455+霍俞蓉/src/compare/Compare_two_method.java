package compare;

import java.io.*;
import java.lang.System;

/**
 * loop method
 * @author Yurong Huo
 *
 */
class Parser_Loop {
	/**
	 *  a sign that look ahead of expression
	 */
	static int lookahead;
	
	/**
	 * get a character from a expression
	 */
	static Reader r_char = null;
	
	/**
	 * constructor
	 * initialize the reader and  lookahead
	 * @param rc
	 * @throws IOException
	 */
	public Parser_Loop(Reader rc) throws IOException{
		r_char = rc;
		lookahead = r_char.read();//read character stream
	}

	/**
	 * analysis expression
	 * use loop method
	 * @throws IOException
	 */
	void expr() throws IOException {
		term();
		//rest()
		while(true){
			/**
			 * if lookahead ='+',get into addition expression
			 */
			if (lookahead == '+') {
				match('+');
				term();
			/**
			 * if lookahead ='-',get into subtraction expression
			 */
			} 
			else if (lookahead == '-') {
				match('-');
				term();
			/**
			 *or exit
			 */
			} 
			else
				return ;
		}
	}

	/**
	 * analysis number
	 * @throws IOException
	 */
	void term() throws IOException {
		if (Character.isDigit((char)lookahead)) {
			match(lookahead);
		} 
		else
			throw new Error("syntax error");
	}

	/**
	 * match method
	 * if matched lookahead equal to next character
	 * @param t
	 */
	void match(int t) throws IOException {
		if (lookahead == t){
			lookahead = r_char.read();
		}
		/**
		 * or print error ,exit
		 */
		else
			throw new Error("syntax error");
	}
}

/**
 * tail_recursive
 * @author Yurong Huo
 */
class Parser_TailRecursion {
	/**
	 *  a sign that look ahead of expression
	 */
	static int lookahead;
	
	/**
	 * get a character from a expression
	 */
	static Reader r_char = null;
	
	/**
	 * constructor
	 * initialize the reader and  lookahead
	 * @param rc
	 * @throws IOException
	 */
	public Parser_TailRecursion(Reader rc) throws IOException{
		r_char = rc;
	    lookahead = r_char.read();
	}

	/**
	 * analysis expression
	 * @throws IOException
	 */
	void expr() throws IOException {
		term();
		rest();
	}

	/**
	 * create expression
	 * use tail_recursive method
	 * @throws IOException
	 */
	void rest() throws IOException {
		/**
		 * if lookahead ='+',get into addition expression
		 */
		if (lookahead == '+') {
			match('+');
			term();
			rest();
			}
			
		/**
		 * if lookahead ='-',get into subtraction expression
		 */ 
		else if (lookahead == '-') {
			match('-');
			term();
			rest();
		}
		/**
		 * or do nothing
		 */
		else {return; }
	}

	/**
	 * analysis number
	 * @throws IOException
	 */
	void term() throws IOException {
		if (Character.isDigit((char)lookahead)) {
			match(lookahead);
		} 
		else
			throw new Error("syntax error");
	}

	/**
	 * match method
	 * if matched lookahead equal to next character
	 * @param t
	 */
	void match(int t) throws IOException {
		if (lookahead == t){
			lookahead = r_char.read();
		}
		else
			throw new Error("syntax error");
	}
}

/**
 * This class is to
 * prodece amount of file
 * that include lots of infix expression
 * @author Yurong Huo
 *
 */
class Produceinfix {
	/**
	 * set the number of files
	 */
	static int f_num = 10;
	
    /**
     * initialize the length of expression in 
     * first file
     * as the number of files increased
     * the length is increased as well
     */
	int lens = 501 ;
	/**
	 * record operator£¨ + or - £©
	 */
	String ch = "+";
	
	/**
	 * constructor
	 * @throws IOException
	 */
	public Produceinfix() throws IOException{
		/**
		 * create files
		 */
		for(int i = 0;i < f_num ; i++){
			/**
			 * file name format is "test+i.infix" 
			 */
			String filename = "compare_time/test00"+i+".infix";
			try {
				/**
				 * create a file output stream and put
				 * it in buffer
				 */
				BufferedWriter wr = new BufferedWriter( new FileWriter(filename));
				String str ;
				/**
				 * create expression
				 */
				for(int k = 0;k < lens ; k++){
					str = "";
					/**
					 * start at 0 position,produce number(0-9)
					 * at even position(ex:0,2,4..)
					 */
					if(k % 2 == 0){
						/**
						 *produce one of 0~9 
						 */
						str = str + getRandom();
					}else{
						/**
						 * at odd position is operator '+' or '-'
						 */
						int index = getrandomope();
						if( index == 0)
							ch = "-";
						else if(index == 1)
							ch = "+";
						str = str + ch;
					}
					/**
					 * close stream
					 */
					wr.write(str);
				}
				wr.close();
			} catch (IOException e) {
				/**
				 * exception
				 */
					e.printStackTrace();
			}
			/**
			 * after create a file
			 * add the length of expression
			 */
			lens = lens + 500;
		}
	}
	
	/**
	 * create the random number
	 * that between 0 and 9
	 * @return ran
	 */
	public int getRandom(){
		int ran = (int)(Math.random()*10);
		return ran;
	}
	
	/**
	 * create the random number
	 * that between 0 and 1
	 * @return ran
	 */
	public int getrandomope(){
		int ranope = (int)(Math.random()*2);
		return ranope;
	}
}


/**
 * comparable class:Compare¡ª¡ªtwo¡ª¡ªmethod
 * 
 * This class is to compare two method
 * about switching the infix
 *  to postfix which are :loop and Tail Recursion 
 * 
 * @version 0 2012/10/27
 * @version 1 2012/10/29
 * @author YurongHuo
 *
 */
public class Compare_two_method {
	/**
	 * main method
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		/**
		 * declare a file named"result"to store the result
		 * and calculate the running time of two method
		 * and print the result in "result"file
		 */
		String resultfile = "result.txt";
		
		/**
		 * produce amount of infix expression
		 */
		Produceinfix pi = new Produceinfix();
		/**
		 * get file's amount
		 */
		int fnums = pi.f_num;
		try{
			/**
			 * create a file output stream and put
			 * it in buffer
			 */
			BufferedWriter wr = new BufferedWriter(new FileWriter(resultfile));
			wr.write("Loop\t");
			wr.write("Tail_Recursion\t");
			wr.newLine();
			
			/**
			 * each time,open a file,use two different method 
			 * to switch infix to postfix
			 * and calculate the time
			 */
			for(int i = 0; i< fnums; i++){
				String filename = "compare_time/test00"+i+".infix";
				File file = new File(filename);
				try{
					/**
					 * call taul_recursice method and loop method
					 * put result in "result"file
					 */
					
					wr.write(Loop(file)+"\t");
					wr.write(TailRecursion(file)+"\t");
					wr.newLine();
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			/**
			 * close
			 */
			wr.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
	}
	
	/**
	 * This function is used to call tail_recursive
	 * method
	 * for each file,call 20 times and calculate the cost time
	 * and return the result
	 * @param file
	 * @return cost time
	 */
	public static double TailRecursion(File file){
		int t = 20 ;
		int temp = t;
		double sumtime = 0.0;
		double starttime , endtime , costtime = 0.0 ;
		try{
			/**
			 * get start time
			 */
			starttime = System.currentTimeMillis();
			while(temp != 0){
				Reader reader = new InputStreamReader(new FileInputStream(file));
				new Parser_TailRecursion(reader).expr();
				reader.close();
				temp -- ;
			}
			
			/**
			 * record end time
			 */
			endtime = System.currentTimeMillis();
			
			/**
			 * calculate cost time
			 */
			sumtime = endtime - starttime;
			costtime  = sumtime / t ;
			
		}catch(IOException e){
			e.printStackTrace();
		}
		return costtime;
	}
	
	/**
	 * This function is used to call loop
	 * method
	 * for each file,call 20 times and calculate the cost time
	 * and return the result
	 * @param file
	 * @return cost time
	 */
	public static double Loop(File file){
		int t = 20 ;
		int temp = t;
		double sumtime = 0.0;
		double starttime , endtime , costtime = 0.0 ;
		try{
			/**
			 * get start time
			 */
			starttime = System.currentTimeMillis();
			
			while( temp != 0){
				Reader reader = new InputStreamReader(new FileInputStream(file));
				new Parser_Loop(reader).expr();
				reader.close();
				temp -- ;
			}
			
			/**
			 * record end time
			 */
			endtime = System.currentTimeMillis();
			
			/**
			 * calculate cost time
			 */
			sumtime = endtime - starttime;
			costtime = sumtime / t;
			
		}catch(IOException e){
			e.printStackTrace();
		}
		return costtime;
	}
}

