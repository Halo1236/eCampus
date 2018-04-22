package com.ayhalo.ecampus.databases.bean;

/**
 * com.ayhalo.ecampus.databases.bean
 * create by wuyh 2018/4/22.
 */

public class User {

    /**
     * error : false
     * results : {"belong":"信息工程学院","id":1,"telephone":"13228705297","username":"吴义黄","xueid":"20140303213"}
     */

    private String error;
    private ResultsBean results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * belong : 信息工程学院
         * id : 1
         * telephone : 13228705297
         * username : 吴义黄
         * xueid : 20140303213
         */

        private String belong;
        private int id;
        private String telephone;
        private String username;
        private String xueid;

        public String getBelong() {
            return belong;
        }

        public void setBelong(String belong) {
            this.belong = belong;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getXueid() {
            return xueid;
        }

        public void setXueid(String xueid) {
            this.xueid = xueid;
        }
    }
}
