package com.example.dd_app.presentation.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PointF
import android.graphics.drawable.Drawable
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.dd_app.R
import com.example.dd_app.presentation.ui.components.DdAppBar
import com.example.dd_app.presentation.ui.components.DdExerciseModal
import com.example.dd_app.presentation.ui.components.DdTwoButtonsDialog
import com.example.dd_app.presentation.viewmodel.NewMyActivityViewModel
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polyline
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.PolylineMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


@Composable
fun NewMyActivityScreen(popBack: () -> Unit, viewModel: NewMyActivityViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var mapView = MapView(context)
    var beforeExercisePlacemark: PlacemarkMapObject? by remember { mutableStateOf(null) }
    var exercisePlacemark: PlacemarkMapObject? by remember { mutableStateOf(null) }
    var exercisePolyline: PolylineMapObject? by remember { mutableStateOf(null) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val location by viewModel.locationFlow.collectAsState()


    DisposableEffect(Unit) {
        viewModel.onStartMap()
        mapView.onStart()
        viewModel.viewModelScope.launch {
            val firstNonNullLocation = viewModel.locationFlow
                .filterNotNull()
                .first()
            val bitmap =
                if (!uiState.isExerciseStarted) viewToBitmap(context, R.drawable.ic_pin) else null
            bitmap?.let {
                beforeExercisePlacemark = mapView.mapWindow.map.mapObjects.addPlacemark().apply {
                    geometry = Point(firstNonNullLocation.latitude, firstNonNullLocation.longitude)
                }
                beforeExercisePlacemark?.useCompositeIcon()?.apply {
                    setIcon(
                        "ic_pin",
                        ImageProvider.fromBitmap(it),
                        IconStyle().apply {
                            anchor = PointF(0.5f, 1.0f)
                            scale = 0.7f
                        })
                }
            }
            mapView.mapWindow.map.move(
                CameraPosition(
                    Point(firstNonNullLocation.latitude, firstNonNullLocation.longitude),
                    18.0f,
                    0.0f,
                    0.0f,
                ),
                Animation(Animation.Type.SMOOTH, 2f),
                null
            )
        }
        onDispose {
            mapView.onStop()
            viewModel.onStopMap()
        }
    }

    LaunchedEffect(uiState.isExerciseStarted) {
        if (uiState.isExerciseStarted && beforeExercisePlacemark != null) {
            mapView.mapWindow.map.mapObjects.remove(beforeExercisePlacemark!!)
            val markData = beforeExercisePlacemark
            beforeExercisePlacemark = null
            val bitmap = viewToBitmap(context, R.drawable.pin)
            if (bitmap != null) {
                exercisePlacemark =
                    mapView.mapWindow.map.mapObjects.addPlacemark().apply {
                        geometry = markData!!.geometry
                    }
                exercisePlacemark?.useCompositeIcon()?.apply {
                    setIcon(
                        "pin",
                        ImageProvider.fromBitmap(bitmap),
                        IconStyle().apply { scale = 0.7f })
                }
                exercisePolyline = mapView.mapWindow.map.mapObjects.addPolyline(
                    Polyline(listOf(markData!!.geometry))
                )
                exercisePolyline?.apply {
                    strokeWidth = 5f
                    setStrokeColor(ContextCompat.getColor(context, R.color.purple))
                }
            }
        }
    }

    LaunchedEffect(location) {
        if (location != null) {
            beforeExercisePlacemark?.apply {
                geometry = Point(location!!.latitude, location!!.longitude)
            }
            if (uiState.isExerciseStarted) {
                if (exercisePlacemark == null) {
                    val bitmap = viewToBitmap(context, R.drawable.pin)
                    if (bitmap != null) {
                        exercisePlacemark =
                            mapView.mapWindow.map.mapObjects.addPlacemark().apply {
                                geometry =
                                    Point(location!!.latitude, location!!.longitude)
                            }
                        exercisePlacemark?.useCompositeIcon()?.apply {
                            setIcon(
                                "pin",
                                ImageProvider.fromBitmap(bitmap),
                                IconStyle().apply { scale = 0.7f })
                        }
                        exercisePolyline = mapView.mapWindow.map.mapObjects.addPolyline(
                            Polyline(
                                listOf(Point(location!!.latitude, location!!.longitude))
                            )
                        )
                        exercisePolyline?.apply {
                            strokeWidth = 5f
                            setStrokeColor(ContextCompat.getColor(context, R.color.purple))
                        }
                    }
                } else {
                    exercisePlacemark?.apply {
                        geometry = Point(location!!.latitude, location!!.longitude)
                    }
                    exercisePolyline?.apply {
                        geometry = Polyline(
                            geometry.points.plus(
                                Point(
                                    location!!.latitude,
                                    location!!.longitude
                                )
                            )
                        )
                    }
                }
            }
        }
    }

    BackHandler {
        if (!uiState.isExerciseStarted) popBack()
        else viewModel.onShowDialog()
    }

    Scaffold(
        topBar = {
            DdAppBar(containerColor = Color.Transparent, popBack = {
                if (!uiState.isExerciseStarted) popBack()
                else viewModel.onShowDialog()
            })
        },
    ) { padding ->
        Box(contentAlignment = Alignment.BottomCenter) {
            AndroidView(
                factory = { mapView },
                update = { mapView = it },
                modifier = Modifier.fillMaxSize()
            )
            DdExerciseModal(
                isExerciseStarted = uiState.isExerciseStarted,
                onExerciseTypeSelect = { type -> viewModel.onExerciseTypeSelect(type) },
                exerciseType = uiState.exerciseType,
                bottomPadding = padding.calculateBottomPadding(),
                onExerciseStart = { viewModel.onExerciseStart() },
                distanceMeters = uiState.distanceMeters.toInt(),
                onFinish = {
                    viewModel.viewModelScope.launch {
                        viewModel.insertMyActivity()
                        popBack()
                    }
                }
            )
            if (uiState.showDialog) DdTwoButtonsDialog(
                onCloseDialog = { viewModel.onCloseDialog() },
                onConfirm = {
                    viewModel.viewModelScope.launch {
                        viewModel.insertMyActivity()
                        popBack()
                    }
                },
                onDismiss = { popBack() },
            )
        }
    }
}

fun viewToBitmap(context: Context, @DrawableRes drawableRes: Int): Bitmap? {
    val drawable: Drawable? = context.getDrawable(drawableRes)
    val canvas = Canvas()
    if (drawable != null) {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return bitmap
    } else {
        return null
    }
}

