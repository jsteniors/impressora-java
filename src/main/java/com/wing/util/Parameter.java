package com.wing.util;

public class Parameter<K> {
	
	private String key;
    private K value;
    private boolean stringConcat = false;
    
    public Parameter(String key, K value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public K getValue() {
        return value;
    }

    public void setValue(K value) {
        this.value = value;
    }

	public boolean isStringConcat() {
		return stringConcat;
	}

	public void setStringConcat(boolean stringConcat) {
		this.stringConcat = stringConcat;
	}

}
