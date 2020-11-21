package com.kailin.traffic.data.basic.news

/**
NewsList {
UpdateTime (DateTime): 更新日期時間 ,
UpdateInterval (Int32): 資料更新週期(秒) ,
Newses (Array[News]): 資料(陣列) ,
Count (integer, optional)
}
News {
NewsID (Guid): 最新消息原單位發布代碼 ,
Language (String): 語系 ,
Department (String, optional): 發布單位 ,
Title (String, optional): 消息標題 ,
NewsCategory (integer, optional): 消息類別 : [1:'最新消息',2:'新聞稿',3:'營運資訊',4:'轉乘資訊',5:'活動訊息',6:'系統公告',7:'新服務上架',8:'API修正',9:'來源異常',99:'其他'] ,
Description (String): 內容描述 ,
NewsURL (String, optional): 報導網站連結 ,
AttachmentURL (String, optional): 相關網站連結 ,
PublishTime (DateTime, optional): 消息公告日期時間 ,
StartTime (DateTime, optional): 開始時間 ,
EndTime (DateTime, optional): 結束時間 ,
UpdateTime (DateTime, optional): 消息更新時間
}
 */

data class PTXNewsData(
    val Newses: List<PTXNewse>,
    val UpdateInterval: Int,
    val UpdateTime: String
)