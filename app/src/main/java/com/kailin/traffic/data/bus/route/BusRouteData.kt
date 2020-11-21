package com.kailin.traffic.data.bus.route

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.kailin.traffic.data.bus.NameType
import com.kailin.traffic.data.bus.NameTypeConverters

/**
BusRoute {
RouteUID (String): 路線唯一識別代碼，規則為 {業管機關簡碼} + {RouteID}，其中 {業管機關簡碼} 可於Authority API中的AuthorityCode欄位查詢 ,
RouteID (String): 地區既用中之路線代碼(為原資料內碼) ,
HasSubRoutes (Boolean): 實際上是否有多條附屬路線。(此欄位值與SubRoutes結構並無強烈的絕對關聯。詳細說明請參閱swagger上方的【資料服務使用注意事項】) ,
Operators (Array[RouteOperator]): 營運業者 ,
AuthorityID (String): 業管機關代碼 ,
ProviderID (String): 資料提供平台代碼 ,
SubRoutes (Array[BusSubRoute], optional): 附屬路線資料(如果原始資料並無提供附屬路線ID，而本平台基於跨來源資料之一致性，會以SubRouteID=RouteID產製一份相對應的附屬路線資料(若有去返程，則會有兩筆)) ,
BusRouteType (integer): 公車路線類別 : [11:'市區公車',12:'公路客運',13:'國道客運',14:'接駁車'] ,
RouteName (NameType): 路線名稱 ,
DepartureStopNameZh (String, optional): 起站中文名稱 ,
DepartureStopNameEn (String, optional): 起站英文名稱 ,
DestinationStopNameZh (String, optional): 終點站中文名稱 ,
DestinationStopNameEn (String, optional): 終點站英文名稱 ,
TicketPriceDescriptionZh (String, optional): 票價中文敘述 ,
TicketPriceDescriptionEn (String, optional): 票價英文敘述 ,
FareBufferZoneDescriptionZh (String, optional): 收費緩衝區中文敘述 ,
FareBufferZoneDescriptionEn (String, optional): 收費緩衝區英文敘述 ,
RouteMapImageUrl (String, optional): 路線簡圖網址 ,
City (String, optional): 路線權管所屬縣市(相當於市區公車API的City參數)[若為公路/國道客運路線則為空值] ,
CityCode (String, optional): 路線權管所屬縣市之代碼(國際ISO 3166-2 三碼城市代碼)[若為公路/國道客運路線則為空值] ,
UpdateTime (DateTime): 資料更新日期時間(ISO8601格式:yyyy-MM-ddTHH:mm:sszzz) ,
VersionID (Int32): 資料版本編號
}
RouteOperator {
OperatorID (String): 營運業者代碼 ,
OperatorName (NameType): 營運業者名稱 ,
OperatorCode (String): 營運業者簡碼 ,
OperatorNo (String): 營運業者編號[交通部票證資料系統定義]
}
BusSubRoute {
SubRouteUID (String): 附屬路線唯一識別代碼，規則為 {業管機關簡碼} + {SubRouteID}，其中 {業管機關簡碼} 可於Authority API中的AuthorityCode欄位查詢 ,
SubRouteID (String): 地區既用中之附屬路線代碼(為原資料內碼) ,
OperatorIDs (Array[string]): 營運業者代碼 ,
SubRouteName (NameType): 附屬路線名稱 ,
Headsign (String, optional): 車頭描述 ,
HeadsignEn (String, optional): 車頭英文描述 ,
Direction (integer): 去返程 : [0:'去程',1:'返程',2:'迴圈',255:'未知'] ,
FirstBusTime (String, optional): 平日第一班發車時間 ,
LastBusTime (String, optional): 平日返程第一班發車時間 ,
HolidayFirstBusTime (String, optional): 假日去程第一班發車時間 ,
HolidayLastBusTime (String, optional): 假日返程第一班發車時間
}
NameType {
Zh_tw (String, optional): 中文繁體名稱 ,
En (String, optional): 英文名稱
}
 */
@Entity(tableName = "BusRouteData")
data class BusRouteData(
    val AuthorityID: String,
    val BusRouteType: Int,
    val City: String,
    val CityCode: String,
    val DepartureStopNameEn: String,
    val DepartureStopNameZh: String,
    val DestinationStopNameEn: String,
    val DestinationStopNameZh: String,
    val FareBufferZoneDescriptionEn: String,
    val FareBufferZoneDescriptionZh: String,
    val HasSubRoutes: Boolean,
    val Operators: List<Operator>,
    val ProviderID: String,
    val RouteID: String,
    val RouteMapImageUrl: String,
    @TypeConverters(NameTypeConverters::class)
    val RouteName: NameType,
    @PrimaryKey
    val RouteUID: String,
    val SubRoutes: List<SubRoute>,
    val TicketPriceDescriptionEn: String,
    val TicketPriceDescriptionZh: String,
    val UpdateTime: String,
    val VersionID: Int
)