/**
 *  <pre>	
 *  Project Name:Zookeeper .</br>
 *  File: Prepo.java .</br>
 *  Package Name:com.imobpay.base.test .</br>
 *  Date      Author       Changes .</br>
 *  2017年3月25日   madman     Create  .</br>
 *  Description: .</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.test;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * <pre>
 * 【类型】: Prepo <br/> 
 * 【作用】: TODO ADD FUNCTION. <br/>  
 * 【时间】：2017年3月25日 下午5:45:49 <br/> 
 * 【作者】：madman <br/>
 * </pre>
 */
public class Prepo {
    static JSONObject json            = null;
    static {
        json = new JSONObject();
        json.put("phone", "13122198813");
        json.put("version", "2.9.0");
        json.put("osType", "android2.3.5");
        json.put("mobileSerialNum", "3598360434842560000000000000000000000000");
        json.put("userIP", "127.0.0.1");
        json.put("appUser", "qtpay");
        json.put("phone", "13122198813");
        json.put("token", "0000");
        json.put("longitude", "121.515330");
        json.put("latitude", "31.232783");
        json.put("sign", "412fadsfoinhuc450f8jcnalzq08mfja");
        json.put("transLogNo", "532362");
        json.put("transDate", "20151201");
        json.put("transTime", "165337");
        json.put("mobileNo", "13122198813");
    }

    static String     deletePrepParam = "delete from prep_param  t  where  t.application=''{0}'';";
    static String     deleteTxReqKey  = "delete from tx_req_key  t  where  t.reqkey=''{0}'';";
    static String     deletePrepVail  = "delete from prep_param_vali  t  where t.application=''{0}'';";
    static String     deleteTrade     = "delete from prepo_trade_template  t  where  t.application=''{0}'';";

    // delete from prep_param t where t.application='GetLoginAppUserType.Req';
    // delete from tx_req_key t where t.reqkey='GetLoginAppUserType.Req';
    // delete from prep_param_vali t where t.application='GetLoginAppUserType.Req';

    // select * from prep_param t where t.application='GetLoginAppUserType.Req';
    // select * from tx_req_key t where t.reqkey='GetLoginAppUserType.Req';
    // select * from prep_param_vali t where t.application='GetLoginAppUserType.Req';
    static String     prepTrade       = "insert into prepo_trade_template values (prepo.nextval, ''{0}'', ''{1}'', ''{2}'', ''http://192.168.1.22:8001/unifiedAction.json?'', ''00'', ''20170306112436'', ''20170306112436'');";
    static String     sql             = "insert into tx_req_key (REQKEY, REQVALUE, STATUS)values (''{0}'', ''com.qtpay.preposing.reqhandle.PrepoAgent'', ''0'');";

    static String     paramVail       = "insert into prep_param_vali values (prepo.nextval, ''{0}'', ''0'', ''0'', ''0'', ''6'', ''0'', ''1'', ''1'', ''DUBBOUSER'', ''10'', ''1'', ''0'', ''1'', ''FrServer'');";

    static String     paramV0         = "insert into prep_param values (prepo.nextval, ''{0}'', ''0'', ''{1}'', null, ''0'', ''1'', null, ''1'');";
    static String     paramV1         = "insert into prep_param values (prepo.nextval, ''{0}'', ''1'', ''{1}'', ''{2}'', null, ''1'', null, ''1'');";
    static String     paramV1_JYM     = "insert into prep_param values (prepo.nextval, ''{0}'', ''1'', null, ''JYM'', null, ''0'', ''{1}'', ''1'');";

    static String     paramV2_0       = "insert into prep_param values (prepo.nextval, ''{0}'', ''2'', ''respDesc'', ''MSG_TEXT'', null, ''1'', null, ''1'');";
    static String     paramV2_1       = "insert into prep_param values (prepo.nextval, ''{0}'', ''2'', ''respCode'', ''MSG_CODE'', null, ''1'', null, ''1'');";
    static String     paramV2_2       = "insert into prep_param values (prepo.nextval, ''{0}'', ''2'', ''data'', ''data'', null, ''1'', null, ''1'');";
    static String     paramV2_3       = "insert into prep_param values (prepo.nextval, ''{0}'', ''2'', ''dataType''', null, ''0'', ''0'', ''json'', ''1'');";

    /**
     * 【方法名】 : (这里用一句话描述这个方法的作用). <br/>
     * 【注意】: (这里描述这个方法的注意事项 – 可选).<br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2017年3月25日 下午5:45:49 .<br/>
     * 【参数】： .<br/>
     * 
     * @param args
     *            .<br/>
     *            <p>
     *            修改记录.<br/>
     *            修改人: madman 修改描述：创建新新件 .<br/>
     *            <p/>
     */
    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<String, String>();
        inserPrepo("GetSalesPromotioList.Req", "金豆跑马灯列", map);
    }

    /**
     * 
     * 【方法名】 : 保存用户的收货地址 <br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2017年6月24日 下午6:13:04 .<br/>
     * 【参数】： .<br/>
     * .<br/>
     * <p>
     * 修改记录.<br/>
     * 修改人: madman 修改描述：创建新新件 .<br/>
     * <p/>
     */
    @Test
    public void SaveUserDeliveryAddress() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("appUser", "");
        map.put("customerId", "");
        map.put("mobileNo", "");
        map.put("userName", "");
        map.put("address", "");
        map.put("businessType", "");
        inserPrepo("SaveUserDeliveryAddress.Req", "保存用户的收货记录", map);
    }

    /**
     * 
     * 【方法名】 : (这里用一句话描述这个方法的作用). <br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2017年5月4日 下午7:01:47 .<br/>
     * 【参数】： .<br/>
     * .<br/>
     * <p>
     * 修改记录.<br/>
     * 修改人: Administrator 修改描述：创建新新件 .<br/>
     * <p/>
     */
    @Test
    public void GetMktProdectList() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("customerId", "");
        inserPrepo("customerId.Req", "终端下发记录列表", map);
    }

    /**
     * 
     * 【方法名】 : 终端下发记录列表 【时间】： 2017年3月26日 下午1:46:31 .<br/>
     * 【参数】： .<br/>
     * .<br/>
     * <p>
     * 修改记录.<br/>
     * 修改人: madman 修改描述：创建新新件 .<br/>
     * <p/>
     */
    @Test
    public void GetTermDownRecord() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("loginAppUserType", "");
        map.put("agencyId", "");
        map.put("currPage", "");
        map.put("type", "");
        inserPrepo("GetTermDownRecord.Req", "终端下发记录列表", map);
    }

    /**
     * 
     * 【方法名】 : 设置成本 【作者】: madman .<br/>
     * 【时间】： 2017年3月26日 下午1:46:31 .<br/>
     * 【参数】： .<br/>
     * .<br/>
     * <p>
     * 修改记录.<br/>
     * 修改人: madman 修改描述：创建新新件 .<br/>
     * <p/>
     */
    @Test
    public void SetProfitCost() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("loginAppUserType", "");
        map.put("agencyId", "");
        map.put("requestList", "");
        map.put("downAgencyId", "");
        inserPrepo("SetProfitCost.Req", "设置成本", map);
    }

    /**
     * 
     * 【方法名】 : 分润模板列表查询<br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2017年3月26日 下午1:46:31 .<br/>
     * 【参数】： .<br/>
     * .<br/>
     * <p>
     * 修改记录.<br/>
     * 修改人: madman 修改描述：创建新新件 .<br/>
     * <p/>
     */
    @Test
    public void GetCostDefaultAndMax() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("loginAppUserType", "");
        map.put("agencyId", "");
        inserPrepo("GetCostDefaultAndMax.Req", "分润模板列表查询", map);
    }

    /**
     * 
     * 【方法名】 : 分润模板列表查询<br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2017年3月26日 下午1:46:31 .<br/>
     * 【参数】： .<br/>
     * .<br/>
     * <p>
     * 修改记录.<br/>
     * 修改人: madman 修改描述：创建新新件 .<br/>
     * <p/>
     */
    @Test
    public void GetOldFrTemplate() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("loginAppUserType", "");
        map.put("agencyId", "");
        map.put("currPage", "");
        inserPrepo("GetOldFrTemplate.Req", "分润模板列表查询", map);
    }

    /**
     * 
     * 【方法名】 : 完善个人信息<br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2017年3月26日 下午1:46:31 .<br/>
     * 【参数】： .<br/>
     * .<br/>
     * <p>
     * 修改记录.<br/>
     * 修改人: madman 修改描述：创建新新件 .<br/>
     * <p/>
     */
    @Test
    public void UpdateAgencyInfo() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("loginAppUserType", "");
        map.put("customerId", "");
        map.put("userName", "");
        map.put("mobileNo", "");
        map.put("certPid", "");
        map.put("email", "");
        inserPrepo("UpdateAgencyInfo.Req", "完善个人信息", map);
    }

    /**
     * 
     * 【方法名】 : 网页注册<br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2017年3月26日 下午1:46:31 .<br/>
     * 【参数】： .<br/>
     * .<br/>
     * <p>
     * 修改记录.<br/>
     * 修改人: madman 修改描述：创建新新件 .<br/>
     * <p/>
     */
    @Test
    public void NetWorkUserRegister() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("loginAppUserType", "");
        map.put("mobileNo", "");
        map.put("mobileMac", "");
        map.put("agencyId", "");
        map.put("agencyMac", "");
        map.put("password", "");
        inserPrepo("NetWorkUserRegister.Req", "网页注册", map);
    }

    /**
     * 
     * 【方法名】 : 获取开设的未使用下级机构列表<br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2017年3月26日 下午1:46:31 .<br/>
     * 【参数】： .<br/>
     * .<br/>
     * <p>
     * 修改记录.<br/>
     * 修改人: madman 修改描述：创建新新件 .<br/>
     * <p/>
     */
    @Test
    public void GetOpenUnUserAgencyList() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("loginAppUserType", "");
        map.put("agencyId", "");
        map.put("agencyName", "");
        map.put("currPage", "1");
        inserPrepo("GetOpenUnUserAgencyList.Req", "获取开设的未使用下级机构列表", map);
    }

    /**
     * 
     * 【方法名】 : 校验验证码正确性 <br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2017年3月26日 下午1:42:03 .<br/>
     * 【参数】： .<br/>
     * .<br/>
     * <p>
     * 修改记录.<br/>
     * 修改人: madman 修改描述：创建新新件 .<br/>
     * <p/>
     */
    @Test
    public void CheckLoginMobileMac() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("loginAppUserType", "");
        map.put("mobileMac", "");
        map.put("appUser", "");
        map.put("mobileNo", "");
        map.put("customerId", "");
        inserPrepo("CheckLoginMobileMac.Req", "校验验证码正确性 ", map);
    }

    /**
     * 
     * 【方法名】 :开设下级机构 <br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2017年3月26日 下午1:29:21 .<br/>
     * 【参数】： .<br/>
     * .<br/>
     * <p>
     * 修改记录.<br/>
     * 修改人: madman 修改描述：创建新新件 .<br/>
     * <p/>
     */
    @Test
    public void OpenDownAgency() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("agencyName", "");
        map.put("agencyId", "");
        map.put("loginAppUserType", "");

        inserPrepo("OpenDownAgency.Req", "开设下级机构 ", map);
    }

    /**
     * 
     * 【方法名】 : 获取登录应用类型 【作者】: madman .<br/>
     * 【时间】： 2017年3月26日 下午1:25:29 .<br/>
     * 【参数】： .<br/>
     * .<br/>
     * <p>
     * 修改记录.<br/>
     * 修改人: madman 修改描述：创建新新件 .<br/>
     * <p/>
     */
    @Test
    public void GetLoginAppUserType() {
        HashMap<String, String> map = new HashMap<String, String>();
        inserPrepo("GetLoginAppUserType.Req", "获取登录应用类型", map);
    }

    /**
     * 【方法名】 : 开设下级机构 【作者】: madman .<br/>
     * 【时间】： 2017年3月26日 下午1:28:15 .<br/>
     * 【参数】： .<br/>
     * 
     * @param app
     * @param map
     *            .<br/>
     *            <p>
     *            修改记录.<br/>
     *            修改人: madman 修改描述：创建新新件 .<br/>
     *            <p/>
     */
    private static void inserPrepo(String app, String desc, HashMap<String, String> map) {
        System.out.println(MessageFormat.format(deletePrepParam, app));
        System.out.println(MessageFormat.format(deleteTxReqKey, app));
        System.out.println(MessageFormat.format(deletePrepVail, app));
        System.out.println(MessageFormat.format(deleteTrade, app));
        System.out.println(MessageFormat.format(prepTrade, app, desc, json));

        System.out.println(MessageFormat.format(sql, app));
        System.out.println(MessageFormat.format(paramVail, app));
        Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> next = iterator.next();
            System.out.println(MessageFormat.format(paramV0, app, next.getKey()));
            System.out.println(MessageFormat.format(paramV1, app, next.getKey(), next.getKey()));
            json.put(next.getKey(), next.getValue() == null ? next.getKey() : next.getValue());
        }
        json.put("application", app);
        String str = app.substring(0, app.length() - 4);
        System.out.println(MessageFormat.format(paramV1_JYM, app, str));
        System.out.println(MessageFormat.format(paramV2_0, app));
        System.out.println(MessageFormat.format(paramV2_1, app));
        System.out.println(MessageFormat.format(paramV2_2, app));
        System.out.println(MessageFormat.format(paramV2_3, app));
    }
}
