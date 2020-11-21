package com.kailin.traffic.data.bus.estimatedTime

import com.kailin.traffic.data.bus.NameType

/**
BusN1EstimateTime {
PlateNumb (String, optional): 車牌號碼 [値為値為-1時，表示目前該站位無車輛行駛] ,
StopUID (String, optional): 站牌唯一識別代碼，規則為 {業管機關簡碼} + {StopID}，其中 {業管機關簡碼} 可於Authority API中的AuthorityCode欄位查詢 ,
StopID (String, optional): 地區既用中之站牌代碼(為原資料內碼) ,
StopName (NameType, optional): 站牌名 ,
RouteUID (String, optional): 路線唯一識別代碼，規則為 {業管機關代碼} + {RouteID}，其中 {業管機關代碼} 可於Authority API中的AuthorityCode欄位查詢 ,
RouteID (String, optional): 地區既用中之路線代碼(為原資料內碼) ,
RouteName (NameType, optional): 路線名稱 ,
SubRouteUID (String, optional): 子路線唯一識別代碼，規則為 {業管機關簡碼} + {SubRouteID}，其中 {業管機關簡碼} 可於Authority API中的AuthorityCode欄位查詢 ,
SubRouteID (String, optional): 地區既用中之子路線代碼(為原資料內碼) ,
SubRouteName (NameType, optional): 子路線名稱 ,
Direction (integer): 去返程(該方向指的是此車牌車輛目前所在路線的去返程方向，非指站站牌所在路線的去返程方向，使用時請加值業者多加注意) : [0:'去程',1:'返程',2:'迴圈',255:'未知'] ,
EstimateTime (integer, optional): 到站時間預估(秒) [當StopStatus値為2~4或PlateNumb値為-1時，EstimateTime値為null; 當StopStatus値為1時， EstimateTime値多數為null，僅部分路線因有固定發車時間，故EstimateTime有値; 當StopStatus値為0時，EstimateTime有値。] ,
StopCountDown (integer, optional): 車輛距離本站站數 ,
CurrentStop (String, optional): 車輛目前所在站牌代碼 ,
DestinationStop (String, optional): 車輛目的站牌代碼 ,
StopSequence (integer, optional): 路線經過站牌之順序 ,
StopStatus (integer, optional): 車輛狀態備註 : [0:'正常',1:'尚未發車',2:'交管不停靠',3:'末班車已過',4:'今日未營運'] ,
MessageType (integer, optional): 資料型態種類 : [0:'未知',1:'定期',2:'非定期'] ,
NextBusTime (DateTime, optional): 下一班公車到達時間(ISO8601格式:yyyy-MM-ddTHH:mm:sszzz) ,
IsLastBus (boolean, optional): 是否為末班車 ,
Estimates (Array[Estimate], optional): 到站時間預估  ,
DataTime (DateTime, optional): 系統演算該筆預估到站資料的時間(ISO8601格式:yyyy-MM-ddTHH:mm:sszzz)[目前僅公總提供此欄位資訊] ,
TransTime (DateTime, optional): 車機資料傳輸時間(ISO8601格式:yyyy-MM-ddTHH:mm:sszzz))[該欄位在N1資料中無意義] ,
SrcRecTime (DateTime, optional): 來源端平台接收時間(ISO8601格式:yyyy-MM-ddTHH:mm:sszzz))[該欄位在N1資料中無意義] ,
SrcTransTime (DateTime, optional): 來源端平台資料傳出時間(ISO8601格式:yyyy-MM-ddTHH:mm:sszzz)[公總使用動態即時推播故有提供此欄位, 而非公總系統因使用整包資料更新, 故沒有提供此欄位] ,
SrcUpdateTime (DateTime, optional): 來源端平台資料更新時間(ISO8601格式:yyyy-MM-ddTHH:mm:sszzz)[公總使用動態即時推播故沒有提供此欄位, 而非公總系統因提供整包資料更新, 故有提供此欄] ,
UpdateTime (DateTime): 本平台資料更新時間(ISO8601格式:yyyy-MM-ddTHH:mm:sszzz)
}
NameType {
Zh_tw (String, optional): 中文繁體名稱 ,
En (String, optional): 英文名稱
}
Estimate {
PlateNumb (String, optional): 車輛車牌號碼 ,
EstimateTime (integer, optional): 車輛之到站時間預估(秒) ,
IsLastBus (boolean, optional): 是否為末班車 ,
VehicleStopStatus (integer, optional): 車輛於該站之進離站狀態 : [0:'離站',1:'進站']
}
 */

data class EstimateTimeData(
    val Direction: Int,
    val EstimateTime: Int,
    val MessageType: Int,
    val RouteID: String,
    val RouteName: NameType,
    val RouteUID: String,
    val SrcUpdateTime: String,
    val StopID: String,
    val StopName: NameType,
    val StopStatus: Int,
    val StopUID: String,
    val UpdateTime: String
)