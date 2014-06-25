/**
 * 
 */
package com.example.wservice.io;

import com.example.wservice.io.impl.IOHandlerImpl;

/**
 * Factory class which is supposed to provide various implementations
 * of {@link IOHandler}
 * 
 * @author 321930
 *
 */
public class IOHandlerFactory {

	/**
	 * 
	 */
	private IOHandlerFactory() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * factory method to get the current {@link IOHandler} implementation.
	 * @return the {@link IOHandler} implementation.
	 */
	
	public static IOHandler getIOHandler(){
		return new IOHandlerImpl();
		//return new IOHandlerDummyImpl();
	}

}
