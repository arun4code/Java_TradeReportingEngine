package com;

import com.ReportGenerator.SettlementReport;
import com.data.DailyMarketData;
import com.model.MarketData;
import com.util.SettlementDateUtil;


/**
 * @author bhard
 * @desc main file to run this daily report batch
 * 
 * Data is defined in com.data.DailyMarketData
 * 
 * Additionally, data can be read from CSV 
 * This class accept CSV format data, and need to change the path accordingly. 
 * or pass the file path in arguments.
 * Assumption is that CSV file is in well format, if any row is having invalid data, the records will be ignored.
 * e.g. of CSV data, where CSV file is seperated by semi-colon (;)
 * ---------------------------------------------------------------------------------------------------
 * Entity	 Buy_Sell	AgreedFx	Currency	InstructionDate	SettlementDate	Units	PricePerUnit
 * ---------------------------------------------------------------------------------------------------
 * foo			B		0.5			USD			01/01/2016		02/01/2016		200		100.25
 * ---------------------------------------------------------------------------------------------------
 * 
 */

public class MainDailyReportBatch {
	
	//TODO:: change the file path, where CSV file is present. Though Data is already defined in Java file.
	private static final String FILE_PATH = "c:\\workspace\\DailyReport\\report_data.csv";
    
	public static void main( String[] args ) {
    	//read data from file
    	//MarketDataReader reader = new MarketDataReader();
    	//MarketData marketData = reader.readFromCSV(FILE_PATH);
    	
		MarketData marketData = DailyMarketData.getDailyMarketData();
    	
    	if(marketData == null || marketData.getMarketDataList() == null
    			|| marketData.getMarketDataList().size() <= 0) {
    		System.out.println("There is no data to process. Batch end");
    		
    		System.exit(1);
    	}
    	System.out.println("total number of valid records: " + marketData.getMarketDataList().size());
    	    	
			
		//process the data
    	SettlementDateUtil.updateSettlementDate(marketData);
		
    	//generate report
    	// report-1
    	System.out.println(SettlementReport.generateReportForIncomingAmount(marketData).toString());
    	
    	//report-2
    	System.out.println(SettlementReport.generateReportForOutgoingAmount(marketData).toString());
    	
		//report-3: apply ranking
    	System.out.println(SettlementReport.generateReportForIncomingOnRanking(marketData));
    	System.out.println(SettlementReport.generateReportForOutgoingOnRanking(marketData));
    	
    	
    	System.exit(0);
    }
}
