package com.itau.api.effective.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.List;

@DynamoDBTable(tableName = "SimulationRenegociation")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimulationModel {
    private String id;
    private String date;
    private String originalValue;
    private String valueWithDiscount;
    private String discountedValue;
    private String discountPercent;
    private String plots;
    private String installmentValue;
    private String documentId;
    private List<String> idDebts;
    private String status;
    private String groupSimulationId;
    private String currency;

    @DynamoDBAttribute
    public String getGroupSimulationId() {
        return groupSimulationId;
    }

    public void setGroupSimulationId(String transactionId) {
        this.groupSimulationId = transactionId;
    }

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @CreatedDate
    @DynamoDBAttribute
    @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.CREATE)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @DynamoDBAttribute
    public String getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(String originalValue) {
        this.originalValue = originalValue;
    }

    @DynamoDBAttribute
    public String getValueWithDiscount() {
        return valueWithDiscount;
    }

    public void setValueWithDiscount(String valueWithDiscount) {
        this.valueWithDiscount = valueWithDiscount;
    }

    @DynamoDBAttribute
    public String getDiscountedValue() {
        return discountedValue;
    }

    public void setDiscountedValue(String discountedValue) {
        this.discountedValue = discountedValue;
    }

    @DynamoDBAttribute
    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    @DynamoDBAttribute
    public String getPlots() {
        return plots;
    }

    public void setPlots(String plots) {
        this.plots = plots;
    }

    @DynamoDBAttribute
    public String getInstallmentValue() {
        return installmentValue;
    }

    public void setInstallmentValue(String installmentValue) {
        this.installmentValue = installmentValue;
    }

    @DynamoDBAttribute
    public List<String> getIdDebts() {
        return idDebts;
    }

    public void setIdDebts(List<String> idDebts) {
        this.idDebts = idDebts;
    }


    @DynamoDBAttribute
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    @DynamoDBAttribute
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @DynamoDBAttribute
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
