/**
 * 
 */
package com.samsoft.aws.lambda.snss3;

import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsoft.aws.lambda.snss3.entity.PatientCheckoutEvent;

/**
 * @author SAMARESH
 *
 */
public class BuildManagementLambda {
	private final ObjectMapper mapper = new ObjectMapper();

	public void handler(SNSEvent event) {
		event.getRecords().forEach(item -> {
			try {
				PatientCheckoutEvent patientObj = mapper.readValue(item.getSNS().getMessage(),
						PatientCheckoutEvent.class);
				System.out.println(patientObj);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}
