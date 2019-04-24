package com.github.zhgxun.learn.common.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class LogisticsTraceBean {
    // 标识属于快刀云回调产生的信息
    @SerializedName("iskuaidaoyun")
    private int isKuaidaoyun = 1;

    // 快递公司编码, go已经做过一次映射, 需要同步, 未做映射的保留原样, 需要补充到go代码中
    @SerializedName("shippercode")
    private String shipperCode;

    @SerializedName("ebussinessid")
    private String ebussinessId = "";

    // 格式为 callback := fmt.Sprintf("%v-%v-%v", deliveryId, pid, express)
    // ExpressSn 快递单号
    // DeliveryId 需要查询ShopDelivery表主键
    // pid 查看go代码, 查询 kuaidi100 的逻辑其实并没有处理 pid, 故 pid=0
    @SerializedName("ordercode")
    private String orderCode;

    // 快递单号
    @SerializedName("logisticcode")
    private String logisticCode;

    private boolean success = false;

    private String reason;

    // 快递状态
    // "在途中", "已发货", "疑难件", "已签收", "退签", "派件中", "退回"
    private String state;

    // 同 orderCode
    private String callback;

    // 回调操作时间, 当前时间戳
    @SerializedName("callback_time")
    private Long callbackTime = System.currentTimeMillis() / 1000;

    @SerializedName("subscribe_time")
    private int subscribeTime = 0;

    // 快递跟踪信息
    private List<TraceBeanV2> traces;
}
