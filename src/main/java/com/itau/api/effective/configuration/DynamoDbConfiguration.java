package com.itau.api.effective.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.api.effective.model.DebtModel;
import com.itau.api.effective.model.EffectiveRenegociationModel;
import com.itau.api.effective.model.SimulationModel;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.itau.api.effective")
public class DynamoDbConfiguration {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.dynamodb.region}")
    private String amazonAWSRegion;

    @Value("${amazon.dynamodb.key}")
    private String amazonKey;

    @Value("${amazon.dynamodb.secret}")
    private String amazonSecret;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() throws IOException {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials()))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, amazonAWSRegion))
                .build();

        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        createTableRenegociation(amazonDynamoDB, dynamoDBMapper);
        createTableSimulation(amazonDynamoDB, dynamoDBMapper);
        createTableDebtModel(amazonDynamoDB, dynamoDBMapper);

        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
                amazonKey, amazonSecret);
    }

    private void createTableRenegociation(AmazonDynamoDB amazonDynamoDB
            ,DynamoDBMapper dynamoDBMapper) {
        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(EffectiveRenegociationModel.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
    }

    private void createTableSimulation(AmazonDynamoDB amazonDynamoDB
            ,DynamoDBMapper dynamoDBMapper) {
        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(SimulationModel.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
    }

    private void createTableDebtModel(AmazonDynamoDB amazonDynamoDB
            ,DynamoDBMapper dynamoDBMapper) throws IOException {
        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(DebtModel.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        if(TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest)) {
            ClassLoader classLoader = getClass().getClassLoader();
            byte[] dataArr = FileCopyUtils.copyToByteArray(classLoader.getResourceAsStream("debts.json"));
            String content = new String(dataArr, StandardCharsets.UTF_8);
            List<DebtModel> listDebts= new ObjectMapper().readValue(content, new TypeReference<List<DebtModel>>(){});
            if (!listDebts.isEmpty()) {
                dynamoDBMapper.batchSave(listDebts);
            }

        }
    }
}
