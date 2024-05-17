package com.example.protapptest.config.common

import android.util.Log
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts

object JWTDecoder {
    fun decodeJWT(jwtToken: String): String? {
//        val r = Jwts.parser().parseClaimsJws(jwtToken).body.get("uid").toString()
        Jwts.header()
        val r = Jwts.parserBuilder()
            .setSigningKey("C3y3k31UFc5PHOJp1fQgQfuh204KDVZY03FrdpgWdfCheJ556sx0Z7IsxyXlMHHE5R2qASZ0SMqbRaAJMSHbKA")
            .build().parse(jwtToken).toString()


        Log.d("tusmuertos", "r = ${r}")
        return try {
            val claimsJws: Jws<Claims> = Jwts.parser().parseClaimsJws(jwtToken)
            val body: Claims = claimsJws.body
            Log.d("tusmuertos", "body = $body")
            body.get("uid", String::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
