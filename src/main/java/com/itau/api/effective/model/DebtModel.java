package com.itau.api.effective.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = "Debt")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DebtModel {
    private String id;
    private String documentId;
    private String currentValue;
    private String debitValueOriginal;
    private String date;
    private String negotiable;
    private String status;

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    @DynamoDBAttribute
    public String getDocumentId() {
        return documentId;
    }

    @DynamoDBAttribute
    public String getCurrentValue() {
        return currentValue;
    }

    @DynamoDBAttribute
    public String getDebitValueOriginal() {
        return debitValueOriginal;
    }

    @DynamoDBAttribute
    public String getDate() {
        return date;
    }


    @DynamoDBAttribute
    public String getNegotiable() {
        return negotiable;
    }

    @DynamoDBAttribute
    public String getStatus() {
        return status;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    public void setDebitValueOriginal(String debitValueOriginal) {
        this.debitValueOriginal = debitValueOriginal;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNegotiable(String negotiable) {
        this.negotiable = negotiable;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(String id) {
        this.id = id;
    }
}