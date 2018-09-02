package com.model;

import java.util.ArrayList;
import java.util.List;

public class MarketData {
	
	private List<MarketDataItem> marketDataList = new ArrayList<MarketDataItem>();

	public List<MarketDataItem> getMarketDataList() {
		return marketDataList;
	}

	public void setMarketDataList(List<MarketDataItem> marketDataList) {
		this.marketDataList = marketDataList;
	}
	
}
