package com.ReportGenerator;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toCollection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import com.model.MarketData;
import com.model.MarketDataItem;
import com.model.TradeType;

/**
 * @author bhard
 *
 */
public class SettlementReport {
    private final static Predicate<MarketDataItem> outgoingPredicate = 
    		item -> item.getType().equals(TradeType.B);

    private final static Predicate<MarketDataItem> incomingPredicate =
            item -> item.getType().equals(TradeType.S);
            
    /**
     * @param data
     * @return
     */
    private static Map<LocalDate, BigDecimal> dailyOutgoingAmount(MarketData data) {
        return dailyTotalAmount(data, outgoingPredicate);
    }

    /**
     * @param data
     * @return
     */
    private static Map<LocalDate, BigDecimal> dailyIncomingAmount(MarketData data) {
        return dailyTotalAmount(data, incomingPredicate);
    }

	private static Map<LocalDate, BigDecimal> dailyTotalAmount(MarketData data,
			Predicate<MarketDataItem> predicate) {
	
		List<MarketDataItem> itemList = data.getMarketDataList();
		if(itemList == null || itemList.isEmpty()) {
			return null;
		}
		return itemList.stream()
				.filter(predicate)
				.collect(groupingBy(MarketDataItem::getSettlementDate,
						mapping(MarketDataItem::getAmount,
	                        reducing(BigDecimal.ZERO, BigDecimal::add))));
		
	}
	
	
	public static StringBuilder generateReportForIncomingAmount(MarketData marketData) {
		final Map<LocalDate, BigDecimal> amount = dailyIncomingAmount(marketData);
		
		return generateReportOnConsole(amount);

	}	
    
	public static StringBuilder generateReportForOutgoingAmount(MarketData marketData) {
		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = dailyOutgoingAmount(marketData);
		
		return generateReportOnConsole(dailyOutgoingAmount);

	}
	
	private static StringBuilder generateReportOnConsole (Map<LocalDate, BigDecimal> dailyTradingData) {
		StringBuilder sb = new StringBuilder();
        sb.append("\nAmount in USD settled outgoing everyday \n")       
        .append("Date       	|          Amount      \n")
        .append("-------------------------------------\n");
        

        for (LocalDate date : dailyTradingData.keySet()) {
            sb.append(date).append("       |      ");
            sb.append(dailyTradingData.get(date)).append("\n");
        }
        
        return sb;
	}

	private static StringBuilder generateReportOnRanking(MarketData marketData, 
			Predicate<MarketDataItem> predicate,
			StringBuilder sb) {
	

		List<MarketDataItem> itemList = marketData.getMarketDataList();
		if(itemList == null || itemList.isEmpty()) {
			return null;
		}
		
		//map of settelement date and grouped Item on date.
		final Map<LocalDate, ArrayList<MarketDataItem>> ranking = new HashMap<>();

		itemList.stream()
				.filter(predicate)
				.collect(groupingBy(MarketDataItem::getSettlementDate))    //group by settlementdate	
				.forEach((date, itemSet) -> {
	                final AtomicInteger rank = new AtomicInteger();

	                final ArrayList<MarketDataItem> rankedItem = itemSet.stream()
	                    .sorted((item1, item2) -> item2.getAmount().compareTo(item1.getAmount()))		//compare with the group
	                    .map(item -> new MarketDataItem(item.getEntity(), date, rank.getAndIncrement()+1, item.getAmount()))
	                    .collect(toCollection(ArrayList::new));

	                ranking.put(date, rankedItem);
	            });

		
		//StringBuilder sb = new StringBuilder();  
        sb.append("Date		|	Entity	|	Amount		|	Ranking   \n")
        .append("---------------------------------------------------------------------------\n");

        for (LocalDate date : ranking.keySet()) {
            for (MarketDataItem item : ranking.get(date)) {
	        	sb
	            .append(date).append("	|	")
	            .append(item.getEntity()).append("	|	")
	            .append(item.getAmount()).append("	|		")
	            .append(item.getRank()).append("\n");
	        }
        }
        
        return sb;
	}

	public static StringBuilder generateReportForIncomingOnRanking(MarketData marketData) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n Ranking for incoming entities \n");
		
		return generateReportOnRanking(marketData, incomingPredicate, sb);
		
	}
	
	public static StringBuilder generateReportForOutgoingOnRanking(MarketData marketData) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n Ranking for outgoing entities \n");
		
		return generateReportOnRanking(marketData, outgoingPredicate, sb);
		
	}
	

            
}
