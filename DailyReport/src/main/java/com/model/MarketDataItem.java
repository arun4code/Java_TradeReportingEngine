package com.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;


public class MarketDataItem {
	private static final long serialVersionUID = 1L;
	private String entity;
	private TradeType type;
	private BigDecimal agreedFx;
	private Currency currency;
	
	private LocalDate instructionDate;
	private LocalDate settlementDate;	
	private int units;
	private BigDecimal pricePerUnit;
	
	private BigDecimal amount;
	private int rank;
	
	public MarketDataItem() {
		
	}
	
	public MarketDataItem(String entity, LocalDate date, int rank, BigDecimal amount) {
		super();
		this.entity = entity;
		this.instructionDate = date;
		this.rank = rank;
		this.amount = amount;
	}
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public TradeType getType() {
		return type;
	}
	public void setType(TradeType type) {
		this.type = type;
	}
	public BigDecimal getAgreedFx() {
		return agreedFx;
	}
	public void setAgreedFx(BigDecimal agreedFx) {
		this.agreedFx = agreedFx;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public LocalDate getInstructionDate() {
		return instructionDate;
	}
	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}
	public LocalDate getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	
	@Override
	public String toString() {
		return "MarketDataItem [entity=" + entity + ", type=" + type + ", agreedFx=" + agreedFx + ", currency="
				+ currency + ", instructionDate=" + instructionDate + ", settlementDate=" + settlementDate + ", units="
				+ units + ", pricePerUnit=" + pricePerUnit + "]";
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Override
    public boolean equals(Object obj) {
        final MarketDataItem other = (MarketDataItem) obj;

        return other.getAmount() == this.getAmount() &&
            other.getEntity().equals(this.getEntity()) &&
            other.getSettlementDate().equals(this.getSettlementDate());
    }

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}
