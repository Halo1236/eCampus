package com.ayhalo.ecampus.databases.bean;

/**
 * com.ayhalo.ecampus.databases.bean
 * create by wuyh 2018/4/22.
 */

public class Result {

    /**
     * error : true
     * results : failed
     */

    private String error;
    private String results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
