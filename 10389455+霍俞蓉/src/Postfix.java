/* 
 * @(#) Postfix.java    2012/10/25
 * 
 * Copyright(c) author Yurong Huo 
 * SYSU software engineering
 * All rights reserved
 * 
 * This program is switch infix to postfix
 * As input a string,we need find some way to check
 * the input is right or wrong
 */
import java.io.*;
import java.lang.StringBuilder;

/**
 * Ô¤²â·ÖÎöÀàparser
 * 
 * This class is to check the string(infix) is 
 * right or wrong that is a simple tanslator
 * of a expression
 * 
 * @version 0 2012/10/25
 * @author YurongHuo
 *
 */

class Parser {
	
	/**
	 * a sign that look ahead of expression
	 */
	static int lookahead;
	
	/**
	 * record the position of a character of expression
	 * initialize by 1
	 */
	static int pos;
	
	/**
	 * record the error position of expression
	 * and error message
	 */
	static StringBuilder errorbuffer ; 

	/**
	 * Constructor
	 * initialize the variable :
	 * lookahead errorbuffer index
	 */
	public Parser() throws IOException {
		errorbuffer  = new StringBuilder("");
		try {
			lookahead = System.in.read();//read a character
		} catch (IOException e) {
			e.printStackTrace();
		}
		if((char)lookahead == '\r'){
			errorbuffer.append("Error: Expression is empty.\n");
		}
		pos = 1;

	}

	/**
	 * Switch the infix to postfix
	 * As switching check the expression
	 * is right or false
	 */
	void expr() throws IOException {
		if( errorbuffer.length() == 0){
			/**
			 * check the first character is number or not
			 */
			if( ! term()){
				errorbuffer.append("Error: The first character is not number of expression.\n");
				//return ;
			}
			int flag = 0;

			/**
			 * recursive method
			 */
			while(true){
				/**
				 * if lookahead is "+" get into addition expression
				 */
				if ((char)lookahead == '+') {
					if( flag == 1){
						errorbuffer.append("Error: Absence of left operand-" +
								"There is no operand on the left of "+pos+"th ('+')character.\n");
						flag = 0;
					}
					if( ! match('+')){
						errorbuffer.append("Error: Absence of right operand-" +
								"The "+pos+"th character next to ('+')has no number.\n");
						break;
					}
					if( ! term()){
						errorbuffer.append("Error: Absence of right operand-" +
								"The "+pos+"th character next to('+')has no number.\n");
						break;
					}
					if(errorbuffer.length() == 0)
						System.out.print('+');
				}
				
				/**
				 * if lookahead is "-" get into subtraction expression
				 */ 
				else if ((char)lookahead == '-') {
					if( flag == 1){
						errorbuffer.append("Error: Absence of left operand-" +
								"There is no operand on the left of "+pos+"th ('-')character.\n");
						flag = 0;
					}
					if( ! match('-')){
						errorbuffer.append("Error: Absence of right operand-" +
								"The "+pos+"th character next to ('-')has no number.\n");
						break;
					}
					if( ! term()){
						errorbuffer.append("Error: Absence of right operand-" +
								":The "+pos+"th character next to('-')has no number.\n");
						break;
					}
					if(errorbuffer.length() == 0)
						System.out.print('-');
				} 
			
				else {
					/**
					 * if the operation is not "+" or "-"
					 * if it has reached end of expression,exit
					 */
					if((char)lookahead == '\r'){
						break ;
					}
					/**
					 * or£¬Error
					 */
					if(Character.isDigit((char)lookahead)){
						flag = 0;
						errorbuffer.append("Error: Absence of operator¡ª" +
								"The left side of "+pos+"th character has no operator '+' or '-'.\n");
					}
					else {
						int k = pos - 1;
						errorbuffer.append("Error: Absence of operator¡ª" +
								"The "+pos+" th character is not operator '+' or '-',that is " +
										"the right side of the "+k+"th character has " +
												"no correct oprator '+' or '-' .\n");
						flag = 1;
					}
				
					/**
					 * lookahead equal to next character
					 * to check more error
					 */
					try {
						lookahead = System.in.read();
					} catch (IOException e) {
						e.printStackTrace();
					}
					/**
					 * if it has reached end of expression,exit
					 */
					if((char)lookahead == '\r') {
						break ;
					}
					/**
					 * pos++ get next position
					 */
					pos ++ ;
				}
			}
		}
	}
	
	/**
	 * check the number in expression
	 */
	boolean term(){
		/**
		 * if the current character is number
		 * --call the match method and print the number
		 */
		while(true){
			if (Character.isDigit((char)lookahead)) {
				if(errorbuffer.length() == 0)
				   System.out.print((char)lookahead);
			    match(lookahead);
			    break;
			} 
			else {
				/**
				 * or check if reached to end of expression
				 * if it has reached,exit,return false
				 */
				if((char)lookahead == '\r'){
					return false;
				}
				else{
					int k = pos-1;
					/**
					 * if it has not reached,Error
					 */
					if(k == 0)
						return false;
					else{
						errorbuffer.append("Error: Absence of right operade-" +
								"There is no operand on the right of "+k+"th character.\n");
					
						/**
						 * lookahead equal to next character
						 */
						try {
							lookahead = System.in.read();
						}catch (IOException e) {
							e.printStackTrace();
						}
						/**
						 * if it has reached end of expression,exit
						 */
						if((char)lookahead == '\r'){
							return false;
						}
						else{
							/**
							 * or pos++
							 * keep analyzing the character of expression
							 */
							pos ++ ;
							continue;
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * match method
	 * the match method is to
	 * make lookahead equal to next character
	 * @param t lookahead
	 * @return true-if the next character is not empty
	 * or return false
	 */
	boolean match(int t) {
		if (lookahead == t){
			
			/**
			 * if matched get next character
			 */
			try {
				lookahead = System.in.read();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			
			/**
			 * if it has reached to end of expression,return false 
			 */
			if((char)lookahead == '\r'){
				return false;
			}
			else{
				/**
				 * or pos ++,get the next position 
				 */
				pos ++ ;
			}
		} 
		return true ;
	}
}

public class Postfix {
	/**
	 * main method(function)
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		/**
		 * message
		 */
		System.out.println("Input an infix expression (that only can add or subtract numbers 0-9)\n" +
				" and output its postfix notation:\n");
		/**
		 * declare a object of Parser parse
		 */
		Parser parser = new Parser() ;
		
		/**
		 * call expr() of parser
		 */
		parser.expr();
		System.out.println();
		/**
		 * print the error message
		 */
		System.out.println(parser.errorbuffer) ;
		/**
		 * process end 
		 */
		System.out.println("Process end.");
	}
}
