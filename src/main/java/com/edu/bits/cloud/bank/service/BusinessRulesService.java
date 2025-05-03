package com.edu.bits.cloud.bank.service;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;
import org.springframework.stereotype.Service;

@Service
public class BusinessRulesService {

    private final LambdaClient lambdaClient;

    public BusinessRulesService() {
        // Set the appropriate AWS region
        lambdaClient = LambdaClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }

    /**
     * Calls the AWS Lambda function to evaluate business rules.
     * 
     * @param payload JSON input containing customer & account data.
     * @return String returned by the Lambda function.
     */
    public String applyRules(String payload) {
        InvokeRequest request = InvokeRequest.builder()
                .functionName("BusinessRulesHandler")  // Lambda function name
                .payload(SdkBytes.fromUtf8String(payload))
                .build();
        InvokeResponse response = lambdaClient.invoke(request);
        return response.payload().asUtf8String();
    }
}
