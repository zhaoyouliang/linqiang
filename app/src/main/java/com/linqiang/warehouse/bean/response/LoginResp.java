package com.linqiang.warehouse.bean.response;

public class LoginResp {

    /**
     * data : {"admAppLogin":{"name":"测试员","sessionToken":"E10ADC3949BA59ABBE56E057F20F883E"}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * admAppLogin : {"name":"测试员","sessionToken":"E10ADC3949BA59ABBE56E057F20F883E"}
         */

        private AdmAppLoginBean admAppLogin;

        public AdmAppLoginBean getAdmAppLogin() {
            return admAppLogin;
        }

        public void setAdmAppLogin(AdmAppLoginBean admAppLogin) {
            this.admAppLogin = admAppLogin;
        }

        public static class AdmAppLoginBean {
            /**
             * name : 测试员
             * sessionToken : E10ADC3949BA59ABBE56E057F20F883E
             */

            private String name;
            private String sessionToken;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSessionToken() {
                return sessionToken;
            }

            public void setSessionToken(String sessionToken) {
                this.sessionToken = sessionToken;
            }
        }
    }
}
