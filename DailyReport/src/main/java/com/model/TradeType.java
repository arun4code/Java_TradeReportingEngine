package com.model;

public enum TradeType {
	B("B"),
    S("S");

    private String type;

    TradeType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
    
}
