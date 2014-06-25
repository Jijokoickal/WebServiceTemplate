/**
 * 
 */
package com.example.wservice.io;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.example.wservice.io.exception.DataExchangerException;



/**
 * @author 321930
 * 
 */
public interface DataExchanger {

	public Data exchangeDataWithServer(String url, Data data, RequestType requestType) throws DataExchangerException,
			ClientProtocolException, IOException;

}
