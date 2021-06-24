package com.test.hellokotlin.activity.map

import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.esri.arcgisruntime.geometry.PointCollection
import com.esri.arcgisruntime.geometry.Polyline

import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.BasemapStyle
import com.esri.arcgisruntime.mapping.Viewpoint
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener
import com.esri.arcgisruntime.mapping.view.Graphic
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay
import com.esri.arcgisruntime.mapping.view.MapView
import com.esri.arcgisruntime.symbology.SimpleLineSymbol
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol
import com.test.hellokotlin.ARCGIS_APPKEY
import com.test.hellokotlin.databinding.ActivityMapBinding
import kotlin.math.roundToInt

/**
 *  created by pxy on 2021/4/25
 *
 * lateinit 和lazy是kotlin中的两种不同的延迟初始化的实现
 * lateinit只用于变量var ，lazy只用于常量val
 * lazy当且仅当变量被第一次调用的时候，委托方法才会执行
 *
 *lazy()是接受一个lambda并返回一个Lazy<T>实例的函数，
 * 返回的实例可以作为实现延迟属性的委托： 第一次调用 get() 会执行已传递给 lazy() 的 lambda 表达式并记录结果，
 * 后续调用 get() 只是返回记录的结果。
 *
 */
class MapActivity : AppCompatActivity() {

    private val binding: ActivityMapBinding by lazy {
        ActivityMapBinding.inflate(layoutInflater)
    }

    private val mMapView: MapView by lazy {
        binding.mapView
    }
    private val graphicsOverlay: GraphicsOverlay by lazy {
        GraphicsOverlay()
    }
    private val pointCollections: PointCollection by lazy {
        PointCollection(mMapView.spatialReference)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mMapView.isAttributionTextVisible = false
        setupMap()
        addGraphics()
    }


    private fun setupMap() {
        // license with a license key
        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud4449636536,none,NKMFA0PL4S0DRJE15166")
        ArcGISRuntimeEnvironment.setApiKey(ARCGIS_APPKEY)
        val map = ArcGISMap(BasemapStyle.ARCGIS_TOPOGRAPHIC)
        mMapView.apply {
            this.map = map
            setViewpoint(Viewpoint(29.712034, 115.992811, 10000000.0))
            onTouchListener = object : DefaultMapViewOnTouchListener(this@MapActivity, this) {

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    e?.let {
                        val screenPoint = Point(
                                it.x.roundToInt(),
                                it.y.roundToInt()
                        )
                        //逻辑写在这里
                        addPoint(screenPoint)

                    }
                    return true
                }
            }
        }
    }

    fun addGraphics() {
        //  val  graphicsOverlay =GraphicsOverlay()
        mMapView.graphicsOverlays.add(graphicsOverlay)
    }

    fun addPoint(point: Point) {
        val clickPoint = mMapView.screenToLocation(point)
        val simpleMarkerSymbol = SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, -0xa8cd, 10f)
        val blueOutLineSymbol = SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, -0xff9c0, 2f)
        simpleMarkerSymbol.outline = blueOutLineSymbol
        val pointGraphic = Graphic(clickPoint, simpleMarkerSymbol)
        graphicsOverlay.graphics.add(pointGraphic)

        Log.e("----point", "addPoint: =" + mMapView.spatialReference)
        pointCollections.apply {
            add(clickPoint)
            addLine(this)
        }
     /*       pointCollections.let {pointCollection ->
                pointCollection.add(clickPoint)
                addLine(pointCollection)
            }*/

    }

    fun addLine(pointCollection: PointCollection) {
        Log.e("----point", "addLine: ")
        val polyline = Polyline(pointCollection)
        val polylineSymbol = SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, -0xff9c01, 3f)

        val polylineGraphic = Graphic(polyline, polylineSymbol)
        graphicsOverlay.graphics.add(polylineGraphic)


    }


    override fun onResume() {
        super.onResume()
        mMapView.resume()
    }

    override fun onPause() {
        mMapView.pause()
        super.onPause()
    }

    override fun onDestroy() {
        mMapView.dispose()
        super.onDestroy()
    }


}