package com.kailin.traffic.data.bus.route

import com.kailin.traffic.data.bus.NameType
import java.io.Serializable

data class Operator(
    val OperatorCode: String,
    val OperatorID: String,
    val OperatorName: NameType,
    val OperatorNo: String
) : Serializable