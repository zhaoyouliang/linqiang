package com.linqiang.warehouse.bean.response;

import java.util.List;

public class SearchResultResp {

    /**
     * data : {"admFindAdmInfoByProductCode":{"productInfo":{"code":"K1N19001","gender":"N","title":"夏季短裤","picUrl":"https://cdn-1258157285.cos.ap-shanghai.myqcloud.com/abuqool/O1CN01FJETTv1Z4cY1PBoum_!!0-item_pic.jpg_460x460Q90.jpg"},"warehouseAdmUnits":[{"quantity":30,"skuCode":"K1N19001150","storageNo":"A01Z01C02"},{"quantity":20,"skuCode":"K1N19001150","storageNo":"A01Z01C01"},{"quantity":21,"skuCode":"K1N19001160","storageNo":"A01Z01C02"},{"quantity":820,"skuCode":"K1N19001160","storageNo":"A01Z01C01"}]}}
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
         * admFindAdmInfoByProductCode : {"productInfo":{"code":"K1N19001","gender":"N","title":"夏季短裤","picUrl":"https://cdn-1258157285.cos.ap-shanghai.myqcloud.com/abuqool/O1CN01FJETTv1Z4cY1PBoum_!!0-item_pic.jpg_460x460Q90.jpg"},"warehouseAdmUnits":[{"quantity":30,"skuCode":"K1N19001150","storageNo":"A01Z01C02"},{"quantity":20,"skuCode":"K1N19001150","storageNo":"A01Z01C01"},{"quantity":21,"skuCode":"K1N19001160","storageNo":"A01Z01C02"},{"quantity":820,"skuCode":"K1N19001160","storageNo":"A01Z01C01"}]}
         */

        private AdmFindAdmInfoByProductCodeBean admFindAdmInfoByProductCode;

        public AdmFindAdmInfoByProductCodeBean getAdmFindAdmInfoByProductCode() {
            return admFindAdmInfoByProductCode;
        }

        public void setAdmFindAdmInfoByProductCode(AdmFindAdmInfoByProductCodeBean admFindAdmInfoByProductCode) {
            this.admFindAdmInfoByProductCode = admFindAdmInfoByProductCode;
        }

        public static class AdmFindAdmInfoByProductCodeBean {
            /**
             * productInfo : {"code":"K1N19001","gender":"N","title":"夏季短裤","picUrl":"https://cdn-1258157285.cos.ap-shanghai.myqcloud.com/abuqool/O1CN01FJETTv1Z4cY1PBoum_!!0-item_pic.jpg_460x460Q90.jpg"}
             * warehouseAdmUnits : [{"quantity":30,"skuCode":"K1N19001150","storageNo":"A01Z01C02"},{"quantity":20,"skuCode":"K1N19001150","storageNo":"A01Z01C01"},{"quantity":21,"skuCode":"K1N19001160","storageNo":"A01Z01C02"},{"quantity":820,"skuCode":"K1N19001160","storageNo":"A01Z01C01"}]
             */

            private ProductInfoBean productInfo;
            private List<WarehouseAdmUnitsBean> warehouseAdmUnits;

            public ProductInfoBean getProductInfo() {
                return productInfo;
            }

            public void setProductInfo(ProductInfoBean productInfo) {
                this.productInfo = productInfo;
            }

            public List<WarehouseAdmUnitsBean> getWarehouseAdmUnits() {
                return warehouseAdmUnits;
            }

            public void setWarehouseAdmUnits(List<WarehouseAdmUnitsBean> warehouseAdmUnits) {
                this.warehouseAdmUnits = warehouseAdmUnits;
            }

            public static class ProductInfoBean {
                /**
                 * code : K1N19001
                 * gender : N
                 * title : 夏季短裤
                 * picUrl : https://cdn-1258157285.cos.ap-shanghai.myqcloud.com/abuqool/O1CN01FJETTv1Z4cY1PBoum_!!0-item_pic.jpg_460x460Q90.jpg
                 */

                private String code;
                private String gender;
                private String title;
                private String picUrl;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getGender() {
                    return gender;
                }

                public void setGender(String gender) {
                    this.gender = gender;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }
            }

            public static class WarehouseAdmUnitsBean {
                /**
                 * quantity : 30
                 * skuCode : K1N19001150
                 * storageNo : A01Z01C02
                 */

                private int quantity;
                private String skuCode;
                private String storageNo;

                @Override
                public String toString() {
                    return "WarehouseAdmUnitsBean{" +
                            "quantity=" + quantity +
                            ", skuCode='" + skuCode + '\'' +
                            ", storageNo='" + storageNo + '\'' +
                            '}';
                }

                public int getQuantity() {
                    return quantity;
                }

                public void setQuantity(int quantity) {
                    this.quantity = quantity;
                }

                public String getSkuCode() {
                    return skuCode;
                }

                public void setSkuCode(String skuCode) {
                    this.skuCode = skuCode;
                }

                public String getStorageNo() {
                    return storageNo;
                }

                public void setStorageNo(String storageNo) {
                    this.storageNo = storageNo;
                }
            }
        }
    }
}
