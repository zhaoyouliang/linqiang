package com.linqiang.warehouse.bean.response;

import java.util.List;

public class InOutBean {

    /**
     * data : {"admEnterOrOutSkuUnitinfo":{"message":"库存数量不足","warehouseAdmUnits":[{"skuCode":"K1N19001160","quantity":1000,"currentQuantity":720}]}}
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
         * admEnterOrOutSkuUnitinfo : {"message":"库存数量不足","warehouseAdmUnits":[{"skuCode":"K1N19001160","quantity":1000,"currentQuantity":720}]}
         */

        private AdmEnterOrOutSkuUnitinfoBean admEnterOrOutSkuUnitinfo;

        public AdmEnterOrOutSkuUnitinfoBean getAdmEnterOrOutSkuUnitinfo() {
            return admEnterOrOutSkuUnitinfo;
        }

        public void setAdmEnterOrOutSkuUnitinfo(AdmEnterOrOutSkuUnitinfoBean admEnterOrOutSkuUnitinfo) {
            this.admEnterOrOutSkuUnitinfo = admEnterOrOutSkuUnitinfo;
        }

        public static class AdmEnterOrOutSkuUnitinfoBean {
            /**
             * message : 库存数量不足
             * warehouseAdmUnits : [{"skuCode":"K1N19001160","quantity":1000,"currentQuantity":720}]
             */

            private String message;
            private List<WarehouseAdmUnitsBean> warehouseAdmUnits;

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public List<WarehouseAdmUnitsBean> getWarehouseAdmUnits() {
                return warehouseAdmUnits;
            }

            public void setWarehouseAdmUnits(List<WarehouseAdmUnitsBean> warehouseAdmUnits) {
                this.warehouseAdmUnits = warehouseAdmUnits;
            }

            public static class WarehouseAdmUnitsBean {
                /**
                 * skuCode : K1N19001160
                 * quantity : 1000
                 * currentQuantity : 720
                 */

                private String skuCode;
                private int quantity;
                private int currentQuantity;

                public String getSkuCode() {
                    return skuCode;
                }

                public void setSkuCode(String skuCode) {
                    this.skuCode = skuCode;
                }

                public int getQuantity() {
                    return quantity;
                }

                public void setQuantity(int quantity) {
                    this.quantity = quantity;
                }

                public int getCurrentQuantity() {
                    return currentQuantity;
                }

                public void setCurrentQuantity(int currentQuantity) {
                    this.currentQuantity = currentQuantity;
                }
            }
        }
    }
}
