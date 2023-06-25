package com.example.kimkazandi

import android.content.Context
import androidx.room.Room
import com.example.kimkazandi.model.Detail
import com.example.kimkazandi.model.Draw
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class Results {



    fun getAllData(url: String): MutableList<Draw> {


        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(
                chain: Array<out X509Certificate>?,
                authType: String?
            ) {
            }

            override fun checkServerTrusted(
                chain: Array<out X509Certificate>?,
                authType: String?
            ) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        })
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.socketFactory)

        val doc: Document = Jsoup.connect(url).timeout(15000).get()

        val items =
            doc.select("div.item")// item class'ına sahip olan 8 div elementlerini seçer

        val draws = mutableListOf<Draw>()

        for (item in items) {
            val img = item.select("img").attr("src")
          // Başlığı seçer
            if (!img.isNullOrEmpty()) {
                val title = item.select("h4").text()
                val time = item.select("span.date:eq(0)").text() // Zamanı seçer
                val count = item.select("span.date:eq(1)").text() // Hediye miktarını seçer
                val price = item.select("span.date.kosul_fiyat").text() // Fiyatı seçer
                val img = item.select("img").attr("src") // Resmi seçer
                val link = item.select("a").attr("href") // Linki seçer

                val news = Draw(url,title, time, count, price, img, link)

                draws.add(news)
            } else {
                continue
            }
        }

        return draws
    }

    fun getDetail(link: String): Detail {
        val url = "https://www.kimkazandi.com/$link"

        val doc: Document = Jsoup.connect(url).timeout(15000).get()
        val baslik = doc.select("div.titleTeaser").text()
        val resim = doc.select("img.img-responsive").attr("src")
        val scrollbarDynamicText = doc.select("div.scrollbar-dynamic").text()
        val secondGalleryText = doc.select("div.secondGallery").text()
        val baslangicTarihi =
            doc.select("div.kalanSure h4:contains(Başlangıç Tarihi) label").first()?.parent()
                ?.ownText()
        val sonKatilimTarihi =
            doc.select("div.kalanSure h4:contains(Son Katılım Tarihi) label").first()?.parent()
                ?.ownText()
        val cekilisTarihi =
            doc.select("div.kalanSure h4:contains(Çekiliş Tarihi) label").first()?.parent()
                ?.ownText()
        val ilanTarihi =
            doc.select("div.kalanSure h4:contains(İlan Tarihi) label").first()?.parent()?.ownText()
        val minHarcamaTutari =
            doc.select("div.kalanSure h4:contains(Min. Harcama Tutarı) label").first()?.parent()
                ?.ownText()
        val toplamHediyeDegeri =
            doc.select("div.kalanSure h4:contains(Toplam Hediye Değeri) label").first()?.parent()
                ?.ownText()
        val toplamHediyeSayisi =
            doc.select("div.kalanSure h4:contains(Toplam Hediye Sayısı) label").first()?.parent()
                ?.ownText()

        return Detail(
            baslik,
            scrollbarDynamicText + "\n" + secondGalleryText,
            baslangicTarihi,
            sonKatilimTarihi,
            cekilisTarihi,
            ilanTarihi,
            minHarcamaTutari,
            toplamHediyeDegeri,
            toplamHediyeSayisi,
            resim
        )
    }
}
