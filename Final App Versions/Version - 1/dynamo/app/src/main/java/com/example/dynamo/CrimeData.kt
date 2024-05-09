package com.example.dynamo

import android.content.res.Resources

class CrimeData(private val resources: Resources) {

    data class CrimeValues(
        val video_id: Int,
        val natureCrime: String,
        val Risklevel: Double,
        val latitude: Double,
        val longitude: Double,
        val video_url: String,
        var approveStatus: String = "Pending", // Updated field
        var subclass: String = ""
    )  {
        // Methods to update approval status and subclass
        fun approve() {
            approveStatus = "Approved"
        }

        fun disapprove(subclass: String) {
            approveStatus = "Disapproved"
            this.subclass = subclass
        }
    }

    val dataCrimeList = listOf(
        CrimeValues(
            1,
            "Assault",
            99.8837,
            16.5424,
            73.4567,
            "https://envision0177.s3.amazonaws.com/videos/2_Assault018_x264.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=LRAh0UbyGvFO48wIRdHH%2Fb7WsyE%3D&Expires=1712811719",
        ),
        CrimeValues(
            2,
            "Arson",
            71.4778,
            16.5424,
            73.4567,
            "https://envision0177.s3.amazonaws.com/videos/1_Arson049_x264.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=PAIEIeddhj3Mjx2sLjVqI7T04Oo%3D&Expires=1712811719"
        ),
        CrimeValues(
            3,
            "Burglary",
            37.8447,
            16.5424,
            73.4567,
            "https://envision0177.s3.amazonaws.com/videos/3_Burglary081_x264.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=b%2BaMNe7ySdmmZ5ro7MIAjJHG6aU%3D&Expires=1712811721"
        ),
        CrimeValues(
            4,
            "Arson",
            60.2807,
            16.5424,
            73.4567,
            "https://envision0177.s3.amazonaws.com/videos/1_Arson049_x264.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=PAIEIeddhj3Mjx2sLjVqI7T04Oo%3D&Expires=1712811719"
        ),
        CrimeValues(
            5,
            "Normal",
            96.5424,
            16.5424,
            73.4567,
            "https://envision0177.s3.amazonaws.com/videos/5_Arson049_x264.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=cDXsKcd3DHGyGnq6sp5EBiu1jDU%3D&Expires=1712811721"
        ),
        CrimeValues(
            6,
            "Shooting",
            74.7474,
            74.7474,
            74.7474,
            "https://envision0177.s3.amazonaws.com/videos/4_Abuse029_x264.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=Pgzd85uidit%2FNKL9y2%2FJouXp4BM%3D&Expires=1712811721"
        ),
        CrimeValues(
            7,
            "Burglary",
            49.4949,
            49.4949,
            49.4949,
            "https://envision0177.s3.amazonaws.com/videos/3_Burglary081_x264.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=b%2BaMNe7ySdmmZ5ro7MIAjJHG6aU%3D&Expires=1712811721"
        ),
        CrimeValues(
            8,
            "Arson",
            63.0,
            49.4949,
            49.4949,
            "https://envision0177.s3.amazonaws.com/videos/1_Arson049_x264.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=PAIEIeddhj3Mjx2sLjVqI7T04Oo%3D&Expires=1712811719"
        ),
        CrimeValues(
            9,
            "Assault",
            25.2525,
            25.2525,
            25.2525,
            "https://envision0177.s3.amazonaws.com/videos/2_Assault018_x264.mp4?AWSAccessKeyId=AKIA5FTZFJUJ6OFOAGUS&Signature=LRAh0UbyGvFO48wIRdHH%2Fb7WsyE%3D&Expires=1712811719"
        )
    )
}


