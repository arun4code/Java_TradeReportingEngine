package com.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import com.model.MarketData;
import com.model.MarketDataItem;
import com.model.TradeType;

public class DailyMarketData {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public static MarketData getDailyMarketData () {
		
		MarketData md = new MarketData();
		List<MarketDataItem> itemList = new ArrayList<>();
		
		MarketDataItem item = new MarketDataItem();
		item.setEntity("foo");
		item.setType(TradeType.B);
		item.setAgreedFx(new BigDecimal(0.5));
		item.setCurrency(Currency.getInstance("USD"));
		LocalDate localDate = LocalDate.parse("31/08/2018", formatter);
        item.setInstructionDate(localDate);        
        item.setSettlementDate(localDate.plusDays(1));
        item.setPricePerUnit(new BigDecimal(110.25));
        item.setUnits(100);
        item.setAmount(calculateAmount(item));
        
        itemList.add(item);
        
        
        item = new MarketDataItem();
		item.setEntity("bar");
		item.setType(TradeType.S);
		item.setAgreedFx(new BigDecimal(0.5));
		item.setCurrency(Currency.getInstance("AED"));
		localDate = LocalDate.parse("30/08/2018", formatter);
        item.setInstructionDate(localDate);        
        item.setSettlementDate(localDate.plusDays(1));
        item.setPricePerUnit(new BigDecimal(100.25));
        item.setUnits(110);
        item.setAmount(calculateAmount(item));
        
        itemList.add(item);
        
        
        
        item = new MarketDataItem();
		item.setEntity("ENTITY1");
		item.setType(TradeType.S);
		item.setAgreedFx(new BigDecimal(11.5));
		item.setCurrency(Currency.getInstance("AED"));
		localDate = LocalDate.parse("26/08/2018", formatter);
        item.setInstructionDate(localDate);        
        item.setSettlementDate(localDate.plusDays(1));
        item.setPricePerUnit(new BigDecimal(90.25));
        item.setUnits(200);
        item.setAmount(calculateAmount(item));
        
        itemList.add(item);
        
        item = new MarketDataItem();
		item.setEntity("ENTITY2");
		item.setType(TradeType.B);
		item.setAgreedFx(new BigDecimal(7.5));
		item.setCurrency(Currency.getInstance("SAR"));
		localDate = LocalDate.parse("25/08/2018", formatter);
        item.setInstructionDate(localDate);        
        item.setSettlementDate(localDate.plusDays(1));
        item.setPricePerUnit(new BigDecimal(110.25));
        item.setUnits(100);
        item.setAmount(calculateAmount(item));
        
        itemList.add(item);
        
        item = new MarketDataItem();
		item.setEntity("ENTITY3");
		item.setType(TradeType.S);
		item.setAgreedFx(new BigDecimal(10.5));
		item.setCurrency(Currency.getInstance("EUR"));
		localDate = LocalDate.parse("30/08/2018", formatter);
        item.setInstructionDate(localDate);        
        item.setSettlementDate(localDate.plusDays(1));
        item.setPricePerUnit(new BigDecimal(110.25));
        item.setUnits(100);
        item.setAmount(calculateAmount(item));
        
        itemList.add(item);
        
        
        item = new MarketDataItem();
		item.setEntity("ENTITY4");
		item.setType(TradeType.S);
		item.setAgreedFx(new BigDecimal(10.5));
		item.setCurrency(Currency.getInstance("AED"));
		localDate = LocalDate.parse("01/09/2018", formatter);
        item.setInstructionDate(localDate);        
        item.setSettlementDate(localDate.plusDays(1));
        item.setPricePerUnit(new BigDecimal(90.25));
        item.setUnits(130);
        item.setAmount(calculateAmount(item));
        
        itemList.add(item);
        
		md.setMarketDataList(itemList);
		
		return md;
	}
	
	private static BigDecimal calculateAmount(MarketDataItem item) {
        return item.getPricePerUnit()
                .multiply(BigDecimal.valueOf(item.getUnits()))
                .multiply(item.getAgreedFx()).setScale(2, BigDecimal.ROUND_FLOOR);
    }
}
