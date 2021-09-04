package com.jaewoo.cloud.util

import com.jaewoo.cloud.api.error.exception.UnkownHostException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.net.InetAddress

@Component
class ServiceUtil {

    @Value("\${server.port}")
    var port : Int = 0

    var address : String? = null

    fun getServiceAddress() : String {
        val hostName = findMyHostName()
        val ipAddress = findMyIpAddress()
        return address ?: "$hostName/$ipAddress:$port"
    }

    fun findMyHostName() : String {
        try {
            return InetAddress.getLocalHost().hostName
        } catch (e : UnkownHostException) {
            return "Unkown host name"
        }
    }

    fun findMyIpAddress() : String {
        try {
            return InetAddress.getLocalHost().hostAddress
        } catch (e : UnkownHostException) {
            return "Unkown Ip address"
        }
    }
}