package com.newrelic.apmplatform.exceptionhandling;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;


@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		return (
		          httpResponse.getStatusCode().series() == Series.CLIENT_ERROR 
		          || httpResponse.getStatusCode().series() == Series.SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		System.out.println("Error caught by handler, "+httpResponse.getStatusCode().toString());
		if (httpResponse.getStatusCode()
		          .series() == HttpStatus.Series.SERVER_ERROR) {
		            // handle SERVER_ERROR
					String errorMessage="NewRelic Server error. Cannot fetch data";
				//	if(httpResponse.getRawStatusCode()==500)
						//errorMessage = "NewRelic Server error. Cannot fetch data";
					throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, errorMessage);
		        } else if (httpResponse.getStatusCode()
		          .series() == HttpStatus.Series.CLIENT_ERROR) {
		            // handle CLIENT_ERROR
		        	
		        	String errorMessage="Generic error message";
		        	if(httpResponse.getRawStatusCode()==401)
		        		errorMessage = "API Key is invalid. Either wrong combination of REST API key and application ID or REST API key has been deleted from user's NewRelic Account";
		        	else if(httpResponse.getRawStatusCode()==403)
		        		errorMessage = "NewRelic API Access has not been enabled. User's REST API license may have expired.";
		        	throw new ResponseStatusException(httpResponse.getStatusCode(), errorMessage);
		        }
		            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
		            	throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Resource not found. NewRelic Error");
		            }
		            
		        

	}

}
