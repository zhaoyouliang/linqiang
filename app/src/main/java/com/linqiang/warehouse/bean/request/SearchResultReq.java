package com.linqiang.warehouse.bean.request;

public class SearchResultReq  {

    /**
     * variables : {"token":"97A0FC12B70423A202602D986E5296CD","skuCode":"K1N19001150"}
     * query : query ($skuCode: String, $token: String) {
     admFindAdmInfoByProductCode(skuCode: $skuCode, token: $token) {
     productInfo {
     code
     gender
     supplier
     }
     warehouseAdmUnits {
     quantity
     skuCode
     storageNo
     }
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
         * token : 97A0FC12B70423A202602D986E5296CD
         * skuCode : K1N19001150
         */

        private String token;
        private String skuCode;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getSkuCode() {
            return skuCode;
        }

        public void setSkuCode(String skuCode) {
            this.skuCode = skuCode;
        }
    }
}
