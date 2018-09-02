package com.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import com.model.ArabicWorkingCalender;
import com.model.IWorkingDays;
import com.model.MarketData;
import com.model.MarketDataItem;
import com.model.WorkingCalender;

/**
 * @author bhard
 * @desc util class to update settlement date based on locale
 *
 */
public class SettlementDateUtil {
	
	private static final Set<String> _ARABIC_CURR = new HashSet<>(Arrays.asList("AED", "SAR"));
	
    /**
     * @param marketData
     */
    public static void updateSettlementDate(MarketData marketData) {
    	marketData.getMarketDataList().forEach(SettlementDateUtil::updatedSettlementDate);        
    }
    
    /**
     * @param item
     */
    public static void updatedSettlementDate(MarketDataItem item) {        
        final IWorkingDays workingDays = findWorkingDay(item.getCurrency());

        final LocalDate settlement_date =
        		workingDays.firstWorkingday(item.getSettlementDate());

        System.out.println("new settlement date for " + item.getSettlementDate() + " is " + settlement_date);
        
        if (settlement_date != null) {
            item.setSettlementDate(settlement_date);
        }
    }
    
    /**
     * @param currency
     * @return
     */
    private static IWorkingDays findWorkingDay(Currency currency) {
        if (_ARABIC_CURR.contains(currency.getCurrencyCode())) {
            return new ArabicWorkingCalender();
        }
        return new WorkingCalender();
    }
}
