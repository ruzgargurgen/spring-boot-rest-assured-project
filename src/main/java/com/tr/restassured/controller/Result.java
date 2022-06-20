package com.tr.restassured.controller;

class Result {
    String user;
    double result;

    public Result(String user, double result) {
        super();
        this.user = user;
        this.result = result;
    }

    public Result(String user) {
        super();
        this.user = user;
    }

    public Result() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

}
