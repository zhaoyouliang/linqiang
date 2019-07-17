package com.linqiang.warehouse.bean.request;

public class LoginReq {

    /**
     * variables : {"password":"123456","weChat":"test"}
     * query : query ($password: String, $weChat: String) {
     admAppLogin(password: $password, weChat: $weChat) {
     name
     sessionToken
     }
     }

     */

    private VariablesBean variables;
    private String query;

    public VariablesBean getVariables() {
        return variables;
    }

    public void setVariables(VariablesBean variables) {
        this.variables = variables;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public static class VariablesBean {
        /**
         * password : 123456
         * weChat : test
         */

        private String password;
        private String weChat;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getWeChat() {
            return weChat;
        }

        public void setWeChat(String weChat) {
            this.weChat = weChat;
        }
    }
}
