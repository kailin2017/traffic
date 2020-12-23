package com.kailin.traffic.data.bus.route

import android.os.Bundle
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kailin.traffic.data.bus.NameType
import java.io.Serializable
import java.lang.Exception

/**
BusStopOfRoute {
RouteUID (String): 路線唯一識別代碼，規則為 {業管機關簡碼} + {RouteID}，其中 {業管機關簡碼} 可於Authority API中的AuthorityCode欄位查詢 ,
RouteID (String): 地區既用中之路線代碼(為原資料內碼) ,
RouteName (NameType): 路線名稱 ,
Operators (Array[Operators], optional): 營運業者 ,
SubRouteUID (String): 附屬路線唯一識別代碼，規則為 {業管機關簡碼} + {SubRouteID}，其中 {業管機關簡碼} 可於Authority API中的AuthorityCode欄位查詢 ,
SubRouteID (String): 地區既用中之附屬路線代碼(為原資料內碼) ,
SubRouteName (NameType): 附屬路線名稱 ,
Direction (integer, optional): 去返程 : [0:'去程',1:'返程',2:'迴圈',255:'未知'] ,
City (String, optional): 站牌權管所屬縣市(相當於市區公車API的City參數)[若為公路/國道客運路線則為空值] ,
CityCode (String, optional): 站牌權管所屬縣市之代碼(國際ISO 3166-2 三碼城市代碼)[若為公路/國道客運路線則為空值] ,
Stops (Array[BusStop]): 所有經過站牌 ,
UpdateTime (DateTime): 資料更新日期時間(ISO8601格式:yyyy-MM-ddTHH:mm:sszzz) ,
VersionID (Int32): 資料版本編號
}
NameType {
Zh_tw (String, optional): 中文繁體名稱 ,
En (String, optional): 英文名稱
}
RouteOperator {
OperatorID (String): 營運業者代碼 ,
OperatorName (NameType): 營運業者名稱 ,
OperatorCode (String): 營運業者簡碼 ,
OperatorNo (String): 營運業者編號
}
Stop {
StopUID (String): 站牌唯一識別代碼，規則為 {業管機關簡碼} + {StopID}，其中 {業管機關簡碼} 可於Authority API中的AuthorityCode欄位查詢 ,
StopID (String): 地區既用中之站牌代碼(為原資料內碼) ,
StopName (NameType): 站牌名稱 ,
StopBoarding (integer, optional): 上下車站別 : [-1:'可下車',0:'可上下車',1:'可上車'] ,
StopSequence (Int32): 路線經過站牌之順序 ,
StopPosition (PointType): 站牌位置 ,
StationID (String, optional): 站牌所屬的站位ID ,
StationGroupID (String): 站牌所屬的組站位ID ,
LocationCityCode (String, optional): 站牌位置縣市之代碼(國際ISO 3166-2 三碼城市代碼)[若為公路/國道客運路線則為空值]
}
PointType {
PositionLat (number, optional): 位置緯度(WGS84) ,
PositionLon (number, optional): 位置經度(WGS84) ,
GeoHash (String, optional): 地理空間編碼
}
 */
@Entity(tableName = "BusRouteData")
data class BusRouteData(
    val AuthorityID: String,
    val BusRouteType: Int,
    val City: String,
    val CityCode: String,
    val DepartureStopNameEn: String?,
    val DepartureStopNameZh: String,
    val DestinationStopNameEn: String?,
    val DestinationStopNameZh: String,
    val FareBufferZoneDescriptionEn: String?,
    val FareBufferZoneDescriptionZh: String,
    val HasSubRoutes: Boolean,
    var Operators: MutableList<Operator>,
    val ProviderID: String,
    val RouteID: String,
    val RouteMapImageUrl: String,
    val RouteName: NameType,
    @PrimaryKey
    val RouteUID: String,
    var SubRoutes: MutableList<SubRoute>,
    val TicketPriceDescriptionEn: String?,
    val TicketPriceDescriptionZh: String,
    val UpdateTime: String,
    val VersionID: Int
) : Serializable{

    fun toBundle() = Bundle().apply {
        putSerializable(TAG, this@BusRouteData)
    }

    companion object {
        private const val TAG = "BusStopOfRoute"

        fun fromBundle(bundle: Bundle?): BusRouteData? {
            return try {
                bundle?.getSerializable(TAG) as BusRouteData
            } catch (e: Exception) {
                null
            }
        }
    }
}