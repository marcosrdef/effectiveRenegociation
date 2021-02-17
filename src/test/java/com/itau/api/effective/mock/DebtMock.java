package com.itau.api.effective.mock;

import com.itau.api.effective.model.DebtModel;

import java.util.ArrayList;
import java.util.List;

public class DebtMock {
    public static DebtModel getDebtModel() {
        return DebtModel.builder()
                .id("6c0dad3a-f288-48cf-a802-b1a06b7bd695")
                .date("01/10/2018")
                .currentValue("1300,10")
                .documentId("89852935089")
                .status("1")
                .debitValueOriginal("700,00")
                .negotiable("true")
                .build();
    }

    public static List<DebtModel> lstDebts() {
        List<DebtModel> lstDebts = new ArrayList<>();
        lstDebts.add(getDebtModel());
        return lstDebts;
    }
}
