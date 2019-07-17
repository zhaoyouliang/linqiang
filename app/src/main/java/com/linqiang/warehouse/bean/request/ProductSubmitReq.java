package com.linqiang.warehouse.bean.request;

import java.util.List;

public class ProductSubmitReq {

    /**
     * variables : {"token":"97A0FC12B70423A202602D986E5296CD","storageNo":"A01Z01C01","enterType":0,"operationMan":"测试员","outType":1,"productStockUnitInfos":[{"code":"K1N19001160","quantity":1000,"status":1}]}
     * query : mutation ($storageNo: String, $enterType: Int, $operationMan: String, $outType: Int, $productStockUnitInfos: [ProductStockUnitInfoInput], $token: String) {
     admEnterOrOutSkuUnitinfo(storageNo: $storageNo, enterType: $enterType, operationMan: $operationMan, outType: $outType, productStockUnitInfos: $productStockUnitInfos, token: $token) {message warehouseAdmUnits {skuCode  quantity    currentQuantity   }  }}
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
         * storageNo : A01Z01C01
         * enterType : 0
         * operationMan : 测试员
         * outType : 1
         * productStockUnitInfos : [{"code":"K1N19001160","quantity":1000,"status":1}]
         */

        private String token;
        private String storageNo;
        private int enterType;
        private String operationMan;
        private int outType;
        private List<ProductStockUnitInfosBean> productStockUnitInfos;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getStorageNo() {
            return storageNo;
        }

        public void setStorageNo(String storageNo) {
            this.storageNo = storageNo;
        }

        public int getEnterType() {
            return enterType;
        }

        public void setEnterType(int enterType) {
            this.enterType = enterType;
        }

        public String getOperationMan() {
            return operationMan;
        }

        public void setOperationMan(String operationMan) {
            this.operationMan = operationMan;
        }

        public int getOutType() {
            return outType;
        }

        public void setOutType(int outType) {
            this.outType = outType;
        }

        public List<ProductStockUnitInfosBean> getProductStockUnitInfos() {
            return productStockUnitInfos;
        }

        public void setProductStockUnitInfos(List<ProductStockUnitInfosBean> productStockUnitInfos) {
            this.productStockUnitInfos = productStockUnitInfos;
        }

        public static class ProductStockUnitInfosBean {
            /**
             * code : K1N19001160
             * quantity : 1000
             * status : 1
             */

            private String code;
            private int quantity;
            private int status;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        @Override
        public String toString() {
            return "VariablesBean{" +
                    "token='" + token + '\'' +
                    ", storageNo='" + storageNo + '\'' +
                    ", enterType=" + enterType +
                    ", operationMan='" + operationMan + '\'' +
                    ", outType=" + outType +
                    ", productStockUnitInfos=" + productStockUnitInfos +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ProductSubmitReq{" +
                "variables=" + variables +
                ", query='" + query + '\'' +
                '}';
    }
}
