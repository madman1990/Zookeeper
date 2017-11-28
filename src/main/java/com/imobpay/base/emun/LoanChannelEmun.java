package com.imobpay.base.emun;

public class LoanChannelEmun {
    public enum CodeMesg {
        /**
         * 贷款状态0审核中
         */
        LOAN_STATUS_0("0"),
        /**
         * 贷款状态1审核拒绝
         */
        LOAN_STATUS_1("1");
        private String value;

        private CodeMesg(String value) {
            this.value = value;
        }

        /**
         * 描述：获取属性值.<br/>
         * 创建人：madman <br/>
         * 返回类型：@return value .<br/>
         */
        public String getValue() {
            return value;
        }

        /**
        * 创建人：madman <br/>
        * 创建时间：2017年10月24日 下午2:52:52 <br/>
        * 参数: @param  value 设置值.  <br/>
        */
        public void setValue(String value) {
            this.value = value;
        }

    }
}
