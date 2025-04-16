package com.miller.roundedimageview

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import kotlin.math.roundToInt

class RoundedImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ShapeableImageView(context, attrs, defStyleAttr) {

    private var cornerFamily: Int = CornerFamily.ROUNDED
    private var mIsOval: Boolean = false
    private var cornerRadiusOverride: Float = 0f
    private var cornerRadiusTopLeftOverride: Float = 0f
    private var cornerRadiusTopRightOverride: Float = 0f
    private var cornerRadiusBottomRightOverride: Float = 0f
    private var cornerRadiusBottomLeftOverride: Float = 0f
    private var mBorderWidth: Float = 0f
    private var mBorderColor: ColorStateList? = null


    init {
        context.obtainStyledAttributes(attrs, R.styleable.RoundedImageView, defStyleAttr, 0).apply {
            mIsOval = getBoolean(R.styleable.RoundedImageView_riv_oval, false)
            cornerRadiusOverride =
                getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius, 0).toFloat()
            cornerRadiusTopLeftOverride = getDimensionPixelSize(
                R.styleable.RoundedImageView_riv_corner_radius_top_left,
                0
            ).toFloat()
            cornerRadiusTopRightOverride = getDimensionPixelSize(
                R.styleable.RoundedImageView_riv_corner_radius_top_right,
                0
            ).toFloat()
            cornerRadiusBottomRightOverride = getDimensionPixelSize(
                R.styleable.RoundedImageView_riv_corner_radius_bottom_right,
                0
            ).toFloat()
            cornerRadiusBottomLeftOverride = getDimensionPixelSize(
                R.styleable.RoundedImageView_riv_corner_radius_bottom_left,
                0
            ).toFloat()
            mBorderWidth =
                getDimensionPixelSize(R.styleable.RoundedImageView_riv_border_width, 0).toFloat()
            mBorderColor = getColorStateList(R.styleable.RoundedImageView_riv_border_color)
            recycle()
        }
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        setSelfAttr(width)
    }

    /**
     * 根据自定义属性设置圆角参数
     */
    private fun setSelfAttr(mWidth: Int) {
        shapeAppearanceModel = ShapeAppearanceModel.builder().apply {
            if (mIsOval) {
                setAllCorners(cornerFamily, mWidth.toFloat() * 0.49f)
            } else {
                if (cornerRadiusOverride != 0f) {
                    setAllCorners(cornerFamily, cornerRadiusOverride)
                } else {
                    if (cornerRadiusTopLeftOverride != 0f) setTopLeftCornerSize(
                        cornerRadiusTopLeftOverride
                    )
                    if (cornerRadiusTopRightOverride != 0f) setTopRightCornerSize(
                        cornerRadiusTopRightOverride
                    )
                    if (cornerRadiusBottomLeftOverride != 0f) setBottomLeftCornerSize(
                        cornerRadiusBottomLeftOverride
                    )
                    if (cornerRadiusBottomRightOverride != 0f) setBottomRightCornerSize(
                        cornerRadiusBottomRightOverride
                    )
                }
            }
            if (mBorderWidth != 0f) {
                strokeWidth = mBorderWidth
                val padWith = (strokeWidth / 2).roundToInt()
                setPadding(padWith, padWith, padWith, padWith)
            }
            if (mBorderColor != null) strokeColor = mBorderColor
        }.build()
    }

    fun setCornerRadius(
        cornerRadiusTopLeftOverride: Float,
        cornerRadiusTopRightOverride: Float,
        cornerRadiusBottomLeftOverride: Float,
        cornerRadiusBottomRightOverride: Float,
    ) {
        shapeAppearanceModel = ShapeAppearanceModel.builder().apply {
            if (cornerRadiusTopLeftOverride != 0f) setTopLeftCornerSize(
                cornerRadiusTopLeftOverride
            )
            if (cornerRadiusTopRightOverride != 0f) setTopRightCornerSize(
                cornerRadiusTopRightOverride
            )
            if (cornerRadiusBottomLeftOverride != 0f) setBottomLeftCornerSize(
                cornerRadiusBottomLeftOverride
            )
            if (cornerRadiusBottomRightOverride != 0f) setBottomRightCornerSize(
                cornerRadiusBottomRightOverride
            )
            if (mBorderWidth != 0f) {
                strokeWidth = mBorderWidth
                val padWith = (strokeWidth / 2).roundToInt()
                setPadding(padWith, padWith, padWith, padWith)
            }
            if (mBorderColor != null) strokeColor = mBorderColor
        }.build()
    }
}