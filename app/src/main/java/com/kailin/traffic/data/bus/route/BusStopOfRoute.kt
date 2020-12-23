package com.kailin.traffic.data.bus.route

import android.os.Bundle
import com.kailin.traffic.data.bus.NameType
import java.io.Serializable

/**
BusDisplayStopOfRoute {
RouteUID (String): 路線唯一識別代碼，規則為 {業管機關代碼} + {RouteID}，其中 {業管機關代碼} 可於Authority API中的AuthorityCode欄位查詢 ,
RouteID (String): 地區既用中之路線代碼(為原資料內碼) ,
RouteName (NameType): 路線名稱 ,
Direction (integer, optional): 去返程 : [0:'去程',1:'返程',2:'迴圈',255:'未知'] ,
Stops (Array[BusStop]): 所有經過站牌 ,
UpdateTime (DateTime): 資料更新日期時間(ISO8601格式:yyyy-MM-ddTHH:mm:sszzz) ,
VersionID (Int32): 資料版本編號
}
NameType {
Zh_tw (String, optional): 中文繁體名稱 ,
En (String, optional): 英文名稱
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
data class BusStopOfRoute(
    val City: String,
    val CityCode: String,
    val Direction: Int,
    val Operators: List<Operator>,
    val RouteID: String,
    val RouteName: NameType,
    val RouteUID: String,
    val Stops: MutableList<BusStop>,
    val SubRouteID: String,
    val SubRouteName: NameType,
    val SubRouteUID: String,
    val UpdateTime: String,
    val VersionID: Int
) : Serializable {

    fun toBundle() = Bundle().apply {
        putSerializable(TAG, this@BusStopOfRoute)
    }

    companion object {
        private const val TAG = "BusStopOfRoute"

        fun fromBundle(bundle: Bundle?): BusStopOfRoute? {
            return try {
                bundle?.getSerializable(TAG) as BusStopOfRoute
            } catch (e: Exception) {
                null
            }
        }
    }
}