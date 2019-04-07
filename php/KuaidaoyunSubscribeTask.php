<?php

/**
 * Class KuaidaoyunSubscribeTask
 * 通过快刀云批量订阅三方商家历史快递单
 */
class KuaidaoyunSubscribeTask
{
    private $account;
    private $host;
    private $secret;
    private $dbFileName;
    private $logFileName;

    function __construct()
    {
        $this->account = "test";
        $this->host = "http://www.kuaidaoyun.com/posttest.aspx";
        $this->secret = "kd8cctestsecret";
        $this->dbFileName = "/Users/zhgxun/Desktop/temp.txt";
        $this->logFileName = "/Users/zhgxun/Desktop/log.txt";
    }

    function batchSubscribe()
    {
        // 按行读取文件
        // 5000097412	0	 47120930	shunfeng
        // delivery_id pid express_sn bizcode
        $file = fopen($this->dbFileName, "r");
        while (!feof($file)) {
            $line = fgets($file);
            $delivery = explode("\t", $line);
            if (!trim($delivery[0])) {
                continue;
            }
            $deliveryId = trim($delivery[0]);
            $pid = trim($delivery[1]);
            $expressSn = trim($delivery[2]);
            $bizCode = trim($delivery[3]);

            // 快递单号为空不订阅
            if (!$expressSn) {
                continue;
            }

            $newBizCode = $this->parseBizCode($bizCode);
            $state = $deliveryId . "-" . $pid . "-" . $expressSn;

            $this->post($newBizCode . "," . $expressSn . "," . $state);
        }
    }

    function parseBizCode($bizCode)
    {
        $pos = strpos($bizCode, "-");
        if ($pos != false) {
            return substr($bizCode, 0, $pos);
        }
        $pos = strpos($bizCode, "(");
        if ($pos != false) {
            return substr($bizCode, 0, $pos);
        }
        return $bizCode;
    }

    function post($data)
    {
        if (trim($data)) {
            exit("订阅数据为空");
        }

        print_r("正在订阅: " . $data);

        $sign = $this->secret;
        $sign .= $data;
        $sign .= $this->secret;
        $sign = strtoupper(md5($sign));

        $postData = array(
            'account' => $this->account,
            'data' => $data,
            'sign' => $sign
        );

        $postData = http_build_query($postData);
        $options = array(
            'http' => array(
                'method' => 'POST',
                'header' => 'Content-type:application/x-www-form-urlencoded',
                'content' => $postData,
                'timeout' => 60 // 超时时间（单位:s）
            ));
        $context = stream_context_create($options);
        $result = file_get_contents($this->host, false, $context);
        print_r("订阅结果存入文件");
        file_put_contents($this->logFileName, $result, FILE_APPEND);
        print_r($data . "订阅完毕");
    }
}

$task = new KuaidaoyunSubscribeTask();
// 批量订阅
//$task->batchSubscribe();
// 单个订阅
//$task->post("");
print_r("\n\nDone");
