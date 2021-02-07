/**
 * 
 */
package com.samsoft.aws.lambda.snss3;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsoft.aws.lambda.snss3.entity.PatientCheckoutEvent;

/**
 * @author SAMARESH
 *
 */
public class PatientCheckoutLambda {
	private static final String PATIENT_CHECKOUT_TOPIC = System.getenv("PATIENT_CHECKOUT_TOPIC");
	private final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
	private final AmazonSNS sns = AmazonSNSClientBuilder.defaultClient();
	private final ObjectMapper mapper = new ObjectMapper();

	public void handler(S3Event event) {
		event.getRecords().forEach(record -> {
			S3ObjectInputStream s3InputStream = s3
					.getObject(record.getS3().getBucket().getName(), record.getS3().getObject().getKey())
					.getObjectContent();
			try {
				List<PatientCheckoutEvent> patientCheckoutEvent = Arrays
						.asList(mapper.readValue(s3InputStream, PatientCheckoutEvent[].class));
				s3InputStream.close();
				System.out.println(patientCheckoutEvent);
				publishMessageToSNS(patientCheckoutEvent);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	private void publishMessageToSNS(List<PatientCheckoutEvent> patientCheckoutEvent) {
		patientCheckoutEvent.forEach(item -> {
			try {
				sns.publish(PATIENT_CHECKOUT_TOPIC, mapper.writeValueAsString(item));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		});
	}

}
