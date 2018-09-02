package com.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import com.model.MarketData;
import com.model.MarketDataItem;
import com.model.TradeType;

public class MarketDataReader {

	private static final String _SEPERATOR = ";";
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	/**
	 * Instead of using simple file reader, we can use OpenCSV jar to process CSV file.
	 * @param filePath
	 * @return
	 */
	public MarketData readFromCSV(String filePath) {
		MarketData marketData = new MarketData();
	    List<MarketDataItem> inputList = new ArrayList<MarketDataItem>();
	    
	    String line = "";
	    int iteration = 0;
	    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {
            	if(iteration == 0) { //skipping header
                    iteration++;  
                    continue;
                }
            	
            	try {
	                String[] arr = line.split(_SEPERATOR);
	                MarketDataItem item = new MarketDataItem();
	                
	                item.setEntity(arr[0]);
	                item.setType(TradeType.valueOf(arr[1]));
	                item.setAgreedFx(new BigDecimal(arr[2]));
	                item.setCurrency(Currency.getInstance(arr[3]));
	                
	                LocalDate localDate = LocalDate.parse(arr[4], formatter);
	                item.setInstructionDate(localDate);
	                
	                localDate = LocalDate.parse(arr[5], formatter);
	                item.setSettlementDate(localDate);
	                
	                item.setUnits(new Integer(arr[6]));
	                item.setPricePerUnit(new BigDecimal(arr[6]));
	                
	                System.out.println(item);
	                item.setAmount(calculateAmount(item));
	                
	                inputList.add(item);
            	}
                catch(Exception exp) {		//skip the wrong data
                	System.out.println("skiping the row due to error: " + exp.getMessage());
                }

            }
            
            marketData.setMarketDataList(inputList);
            
        } catch (IOException e) {
            e.printStackTrace();
            
        }
        
        return marketData;
	}
	
	
	private static BigDecimal calculateAmount(MarketDataItem item) {
        return item.getPricePerUnit()
                .multiply(BigDecimal.valueOf(item.getUnits()))
                .multiply(item.getAgreedFx()).setScale(2, BigDecimal.ROUND_FLOOR);
    }
}
