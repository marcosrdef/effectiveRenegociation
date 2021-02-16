package com.itau.api.effective.utils;

import com.itau.api.effective.model.DebtModel;
import org.springframework.util.ObjectUtils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Utils {
    public static String sumDebits(List<DebtModel> lstDebt) throws ParseException {
        float totalValue = 0;
        lstDebt = lstDebt.stream().filter(x-> Boolean.TRUE.equals(Boolean.parseBoolean(x.getNegotiable()))).collect(Collectors.toList());
        for (DebtModel debtModel : lstDebt) {
            totalValue += convertStringToFloat(debtModel.getCurrentValue());
        }
        return String.format("%.2f", totalValue);
    }

    public static float convertStringToFloat(String value) throws ParseException {
        float convertValue = 0;
        if (ObjectUtils.isEmpty(value)) {
            return convertValue;
        }
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        return nf.parse(value).floatValue();
    }

    public static String calculateValueWithDiscount(String value, String percent) throws ParseException {
        float originalValue = convertStringToFloat(value);
        float discountValue = (originalValue * Integer.parseInt(percent))/100;

        return String.format("%.2f", (originalValue - discountValue));
    }

    public static String calculateinstallmentValue(String value, String plots) throws ParseException {
        float totalValue = convertStringToFloat(value);
        totalValue = (totalValue / Integer.parseInt(plots));
        return String.format("%.2f", totalValue);
    }

    public static String calculateDiscountedValue(String originalValue, String valueWithDiscount) throws ParseException {
        float valorTotal = convertStringToFloat(originalValue) - convertStringToFloat(valueWithDiscount);
        return String.format("%.2f", valorTotal);
    }
}
