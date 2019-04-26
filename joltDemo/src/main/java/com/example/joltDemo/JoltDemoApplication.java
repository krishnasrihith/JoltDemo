package com.example.joltDemo;

import java.io.InputStream;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class JoltDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoltDemoApplication.class, args);
			ObjectMapper mapper = new ObjectMapper();
			InputStream stream = JoltDemoApplication.class.getResourceAsStream("/spec.json");
			Map<String, Object> chainrSpecJSON = null;
			try {
				chainrSpecJSON = mapper.readValue(stream,Map.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        Chainr chainr = Chainr.fromSpec( chainrSpecJSON.get("mandapalli") );

	        Object inputJSON = JsonUtils.classpathToObject( "/input.json" );

	        Object transformedOutput = chainr.transform(inputJSON);
	        System.out.println( JsonUtils.toJsonString( transformedOutput ) );
	        
	         chainr = Chainr.fromSpec( chainrSpecJSON.get("arpit") );

	         inputJSON = JsonUtils.classpathToObject( "/input2.json" );

	        transformedOutput = chainr.transform(inputJSON);
	        System.out.println( JsonUtils.toJsonString( transformedOutput ) );
	        
	}

}
