package com.example.tokr.pullToRefreshAnimation


import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.Transformation

import com.example.tokr.*
import com.example.tokr.PullToRefreshView
import com.example.tokr.R
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class SoupRefreshView(private val parent: PullToRefreshView?) : Drawable(), Animatable,
    Drawable.Callback {

    private val matrix1: Matrix = Matrix()
    private var bounceAnimation: Animation? = null
    private var scaleAnimation: Animation? = null
    private var flameScaleAnimation: Animation? = null
    private var flameBurnAnimation: Animation? = null
    private var bubble1Animation: Animation? = null
    private var bubble2Animation: Animation? = null
    private var bubble3Animation: Animation? = null
    private var bubble4Animation: Animation? = null
    private var bubble5Animation: Animation? = null
    private var bubble6Animation: Animation? = null
    private var coverAnimation: Animation? = null


    private var screenWidth: Float = 0.toFloat()
    private var screenHeight: Float = 0.toFloat()

    private var panTopOffset: Float = 0.toFloat()

    private var pan: Bitmap? = null
    private var patato: Bitmap? = null
    private var circle: Bitmap? = null
    private var carrot: Bitmap? = null
    private var rightPea: Bitmap? = null
    private var leftPea: Bitmap? = null
    private var cover: Bitmap? = null
    private var water: Bitmap? = null
    private var shadow: Bitmap? = null
    private var flame1: Bitmap? = null
    private var flame2: Bitmap? = null
    private var flame3: Bitmap? = null
    private var flame4: Bitmap? = null
    private var flame5: Bitmap? = null
    private var bubble: Bitmap? = null

    private var mPercent = 0.0f
    private var bounce = 0.0f

    private var isRefreshing = false

    private var coverOffset: Float = 0.toFloat()
    private var coverStartPointY: Float = 0.toFloat()
    private var coverFinalPointY: Float = 0.toFloat()

    private var isCoverDropped: Boolean = false
    private var isShadowDisplayed: Boolean = false
    private var scale: Float = 0.toFloat()
    private var flameScale: Float = 0.toFloat()
    private var flameBurn: Float = 0.toFloat()
    private var bubble1Move: Float = 0.toFloat()
    private var bubble2Move: Float = 0.toFloat()
    private var bubble3Move: Float = 0.toFloat()
    private var bubble4Move: Float = 0.toFloat()
    private var bubble5Move: Float = 0.toFloat()
    private var bubble6Move: Float = 0.toFloat()
    private var context = getContext()
    private var bubbleScaleOffset: Float = 0.toFloat()
    private var coverJump: Float = 0.toFloat()


    private var carrotFinalPointX: Float = 0.toFloat()
    private var carrotStartPointX: Float = 0.toFloat()

    private var carrotFinalPointY: Float = 0.toFloat()
    private var carrotStartPointY: Float = 0.toFloat()
    private var carrotOffset: Float = 0.toFloat()

    private var potatoFinalPointX: Float = 0.toFloat()
    private var potatoStartPointX: Float = 0.toFloat()

    private var potatoFinalPointY: Float = 0.toFloat()
    private var potatoStartPointY: Float = 0.toFloat()
    private var potatoOffset: Float = 0.toFloat()

    private var rightPeaFinalPointX: Float = 0.toFloat()
    private var rightPeaStartPointX: Float = 0.toFloat()

    private var rightPeaFinalPointY: Float = 0.toFloat()
    private var rightPeaStartPointY: Float = 0.toFloat()
    private var rightPeaOffset: Float = 0.toFloat()


    private var leftPeaFinalPointX: Float = 0.toFloat()
    private var leftPeaStartPointX: Float = 0.toFloat()

    private var leftPeaFinalPointY: Float = 0.toFloat()
    private var leftPeaStartPointY: Float = 0.toFloat()

    private var leftPeaOffset: Float = 0.toFloat()

    private var flame1TopOffset: Float = 0.toFloat()
    private var flame1LeftOffset: Float = 0.toFloat()

    private var flame2LeftOffset: Float = 0.toFloat()

    private var flame3TopOffset: Float = 0.toFloat()
    private var flame3LeftOffset: Float = 0.toFloat()

    private var flame4TopOffset: Float = 0.toFloat()
    private var flame4LeftOffset: Float = 0.toFloat()

    private var flame5TopOffset: Float = 0.toFloat()
    private var flame5LeftOffset: Float = 0.toFloat()

    private var bubble1TopOffset: Float = 0.toFloat()
    private var bubble1LeftOffset: Float = 0.toFloat()

    private var bubble2TopOffset: Float = 0.toFloat()
    private var bubble2LeftOffset: Float = 0.toFloat()

    private var bubble3TopOffset: Float = 0.toFloat()
    private var bubble3LeftOffset: Float = 0.toFloat()

    private var bubble4TopOffset: Float = 0.toFloat()
    private var bubble4LeftOffset: Float = 0.toFloat()

    private var bubble5TopOffset: Float = 0.toFloat()
    private var bubble5LeftOffset: Float = 0.toFloat()

    private var bubble6TopOffset: Float = 0.toFloat()
    private var bubble6LeftOffset: Float = 0.toFloat()


    private var circlePivotX: Float = 0.toFloat()
    private var circlePivotY: Float = 0.toFloat()
    private var bubble1PivotX: Float = 0.toFloat()
    private var bubble2PivotX: Float = 0.toFloat()
    private var bubble3PivotX: Float = 0.toFloat()
    private var bubble4PivotX: Float = 0.toFloat()
    private var bubble5PivotX: Float = 0.toFloat()
    private var bubble6PivotX: Float = 0.toFloat()


    init {
        context = parent!!.context
        setupAnimations()
        parent.post(Runnable { initiateDimens(parent.width) })
    }

    private fun initiateDimens(viewWidth: Int) {
        if (viewWidth <= 0 || viewWidth.toFloat() == screenWidth) return
        screenWidth = viewWidth.toFloat()
        screenHeight = context!!.resources.displayMetrics.heightPixels.toFloat()


        createBitmaps()

        panTopOffset = convertDpToFloatPixel(context!!, 60f)

        coverOffset = convertDpToFloatPixel(context!!, 90f)

        coverStartPointY = convertDpToFloatPixel(context!!, 10f)
        coverFinalPointY = convertDpToFloatPixel(context!!, 70f)


        carrotStartPointX = screenWidth / 100 * 79
        carrotFinalPointX = screenWidth / 100 * 30.0f


        carrotFinalPointY = convertDpToFloatPixel(context!!, 245f)
        carrotStartPointY = convertDpToFloatPixel(context!!, 161f)
        carrotOffset = convertDpToFloatPixel(context!!, 90f)

        potatoFinalPointX = screenWidth / 100 * -25
        potatoStartPointX = screenWidth / 100 * 14.5f


        potatoStartPointY = convertDpToFloatPixel(context!!, 150f)
        potatoFinalPointY = convertDpToFloatPixel(context!!, 237f)
        potatoOffset = convertDpToFloatPixel(context!!, 90f)


        rightPeaFinalPointX = screenWidth / 100 * 30.5f
        rightPeaStartPointX = screenWidth / 100 * 88

        rightPeaStartPointY = convertDpToFloatPixel(context!!, 150f)
        rightPeaFinalPointY = convertDpToFloatPixel(context!!, 242f)
        rightPeaOffset = convertDpToFloatPixel(context!!, 90f)

        leftPeaStartPointX = screenWidth / 100 * 7.5f
        leftPeaFinalPointX = screenWidth / 100 * -45

        leftPeaStartPointY = convertDpToFloatPixel(context!!, 150f)
        leftPeaFinalPointY = convertDpToFloatPixel(context!!, 242f)
        leftPeaOffset = convertDpToFloatPixel(context!!, 90f)

        flame1TopOffset = convertDpToFloatPixel(context!!, 134f)
        flame1LeftOffset = screenWidth / 100 * 42

        flame2LeftOffset = screenWidth / 100 * 45

        flame3TopOffset = convertDpToFloatPixel(context!!, 132f)
        flame3LeftOffset = screenWidth / 100 * 48.5f

        flame4TopOffset = convertDpToFloatPixel(context!!, 134f)
        flame4LeftOffset = screenWidth / 100 * 51.5f

        flame5TopOffset = convertDpToFloatPixel(context!!, 134f)
        flame5LeftOffset = screenWidth / 100 * 54f

        bubble1TopOffset = convertDpToFloatPixel(context!!, 144f)
        bubble1LeftOffset = screenWidth / 100 * 40

        bubble2TopOffset = convertDpToFloatPixel(context!!, 144f)
        bubble2LeftOffset = screenWidth / 100 * 42

        bubble3TopOffset = convertDpToFloatPixel(context!!, 144f)
        bubble3LeftOffset = screenWidth / 100 * 44

        bubble4TopOffset = convertDpToFloatPixel(context!!, 144f)
        bubble4LeftOffset = screenWidth / 100 * 46

        bubble5TopOffset = convertDpToFloatPixel(context!!, 144f)
        bubble5LeftOffset = screenWidth / 100 * 48

        bubble6TopOffset = convertDpToFloatPixel(context!!, 144f)
        bubble6LeftOffset = screenWidth / 100 * 50

        circlePivotX = convertDpToPixel(context!!, 100).toFloat()
        circlePivotY = convertDpToPixel(context!!, 40).toFloat()

        bubbleScaleOffset = convertDpToFloatPixel(context!!, 100f)

        setBubblesPivot(140f)

    }


    private fun createBitmaps() {

        pan = CreateBitmapFactory.getBitmapFromImage(R.drawable.pan, context!!)
        circle = CreateBitmapFactory.getBitmapFromImage(R.drawable.circle, context!!)
        patato = CreateBitmapFactory.getBitmapFromImage(R.drawable.potato, context!!)
        carrot = CreateBitmapFactory.getBitmapFromImage(R.drawable.carrot, context!!)
        rightPea = CreateBitmapFactory.getBitmapFromImage(R.drawable.pea, context!!)
        leftPea = CreateBitmapFactory.getBitmapFromImage(R.drawable.pea, context!!)
        cover = CreateBitmapFactory.getBitmapFromImage(R.drawable.pan_cover, context!!)
        water = CreateBitmapFactory.getBitmapFromImage(R.drawable.water, context!!)
        shadow = CreateBitmapFactory.getBitmapFromImage(R.drawable.shadow, context!!)
        flame1 = CreateBitmapFactory.getBitmapFromImage(R.drawable.flame1, context!!)
        flame2 = CreateBitmapFactory.getBitmapFromImage(R.drawable.flame2, context!!)
        flame3 = CreateBitmapFactory.getBitmapFromImage(R.drawable.flame3, context!!)
        flame4 = CreateBitmapFactory.getBitmapFromImage(R.drawable.flame4, context!!)
        flame5 = CreateBitmapFactory.getBitmapFromImage(R.drawable.flame5, context!!)
        bubble = CreateBitmapFactory.getBitmapFromDrawable(R.drawable.bubble, context!!)

    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, top)
    }

    override fun isRunning(): Boolean {
        return isRefreshing
    }

    override fun start() {
        bounceAnimation!!.reset()
        scaleAnimation!!.reset()
        flameScaleAnimation!!.reset()
        flameBurnAnimation!!.reset()
        bubble1Animation!!.reset()
        coverAnimation!!.reset()
        isRefreshing = true

        val animatorSet = AnimationSet(false)
        animatorSet.addAnimation(flameBurnAnimation)
        animatorSet.addAnimation(bubble1Animation)
        animatorSet.addAnimation(bubble2Animation)
        animatorSet.addAnimation(bubble3Animation)
        animatorSet.addAnimation(bubble4Animation)
        animatorSet.addAnimation(bubble5Animation)
        animatorSet.addAnimation(bubble6Animation)
        animatorSet.addAnimation(coverAnimation)
        parent!!.startAnimation(bounceAnimation)

        bounceAnimation!!.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                parent.startAnimation(scaleAnimation)
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        scaleAnimation!!.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                isShadowDisplayed = true
                parent.startAnimation(flameScaleAnimation)
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        flameScaleAnimation!!.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                parent.startAnimation(animatorSet)
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })

    }

    override fun stop() {
        parent!!.clearAnimation()
        isRefreshing = false
        isCoverDropped = false
        isShadowDisplayed = false
        resetOriginals()
    }


    fun setPercent(percent: Float, invalidate: Boolean) {
        setPercent(percent)
        if (invalidate) bounce = setVariable(percent)
    }


    fun offsetTopAndBottom(offset: Int) {
        invalidateSelf()
    }

    override fun draw(canvas: Canvas) {

        if (screenWidth <= 0) return

        val saveCount = canvas.save()

        canvas.translate(0f, 0f)
        canvas.clipRect(
            0f,
            (-parent!!.totalDragDistance).toFloat(),
            screenWidth,
            parent.totalDragDistance.toFloat()
        )
        drawCircle(canvas)
        drawShadow(canvas)
        drawPan(canvas)
        drawPotato(canvas)
        drawCarrot(canvas)
        drawRightPea(canvas)
        drawLeftPea(canvas)
        drawCover(canvas)
        drawWater(canvas)
        drawFlame(
            canvas, flame1, flame1LeftOffset, flame1TopOffset, flameScale,
            flame4LeftOffset - convertDpToFloatPixel(context!!, 15f),
            flame4TopOffset + 50
        )
        drawFlame(
            canvas, flame2, flame2LeftOffset, flame1TopOffset, flameBurn,
            flame2LeftOffset + convertDpToFloatPixel(context!!, 10f),
            flame1TopOffset + 50
        )
        drawFlame(
            canvas, flame3, flame3LeftOffset, flame3TopOffset, flameScale,
            flame3LeftOffset - convertDpToFloatPixel(context!!, 11f),
            flame3TopOffset + 50
        )
        drawFlame(
            canvas, flame4, flame4LeftOffset, flame4TopOffset,
            flameBurn, flame4LeftOffset, flame4TopOffset + 50
        )
        drawFlame(
            canvas, flame5, flame5LeftOffset, flame5TopOffset,
            flameScale, flame5LeftOffset, flame5TopOffset + 50
        )

        drawBubble(canvas, bubble1LeftOffset, bubble1TopOffset, bubble1Move, bubble1PivotX)
        drawBubble(canvas, bubble2LeftOffset, bubble2TopOffset, bubble2Move, bubble2PivotX)
        drawBubble(canvas, bubble3LeftOffset, bubble3TopOffset, bubble3Move, bubble3PivotX)
        drawBubble(canvas, bubble4LeftOffset, bubble4TopOffset, bubble4Move, bubble4PivotX)
        drawBubble(canvas, bubble5LeftOffset, bubble5TopOffset, bubble5Move, bubble5PivotX)
        drawBubble(canvas, bubble6LeftOffset, bubble6TopOffset, bubble6Move, bubble6PivotX)

        canvas.restoreToCount(saveCount)
    }

    private fun getContext(): Context? {
        return parent?.context

    }

    override fun invalidateDrawable(who: Drawable) {
        val callback = callback
        callback?.invalidateDrawable(this)
    }

    override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
        val callback = callback
        callback?.scheduleDrawable(this, what, `when`)
    }

    override fun unscheduleDrawable(who: Drawable, what: Runnable) {
        val callback = callback
        callback?.unscheduleDrawable(this, what)
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    //No need for implementation,we don't need this method.
    override fun setAlpha(alpha: Int) {}

    //No need for implementation,we don't need this method.
    override fun setColorFilter(cf: ColorFilter?) {}

    private fun drawCover(canvas: Canvas) {
        val matrix = matrix1
        matrix.reset()
        val offsetX: Float
        val offsetY: Float

        if (isRefreshing) {
            offsetX = screenWidth / 2 - cover!!.width / 2
            offsetY =
                (bounce * coverFinalPointY - coverStartPointY) * (bounce * coverFinalPointY - coverStartPointY) / coverOffset
            isCoverDropped = true
            if (isShadowDisplayed) {
                matrix.postRotate(-5f, 0f, 0f)
            }
            matrix.postRotate(coverJump * 5, 0f, 0f)
            matrix.postTranslate(offsetX, offsetY)
            val paint = Paint()
            val alpha = bounce / 2 * 500
            paint.alpha = alpha.toInt()
            canvas.drawBitmap(cover!!, matrix, paint)
        }
    }

    private fun drawPan(canvas: Canvas) {
        val matrix = matrix1
        matrix.reset()
        val dragPercent = min(1f, abs(mPercent))
        val offsetY: Float
        val offsetX = screenWidth / 2 - pan!!.width / 2
        offsetY = panTopOffset * dragPercent
        matrix.postTranslate(offsetX, offsetY)

        val paint = Paint()
        val alpha = dragPercent / 2 * 500
        paint.alpha = alpha.toInt()
        canvas.drawBitmap(pan!!, matrix, paint)
    }

    private fun drawCircle(canvas: Canvas) {
        val matrix = matrix1
        matrix.reset()

        val dragPercent = min(0.85f, abs(mPercent))

        val offsetX = screenWidth / 2 - circle!!.width / 2
        val offsetY = -(screenHeight / 100)

        matrix.postScale(dragPercent, dragPercent, circlePivotX, circlePivotY)
        matrix.postTranslate(offsetX, offsetY)

        val paint = Paint()
        val alpha = dragPercent / 2 * 500
        paint.alpha = alpha.toInt()

        canvas.drawBitmap(circle!!, matrix, paint)
    }

    private fun drawPotato(canvas: Canvas) {
        val matrix = matrix1
        matrix.reset()
        val dragPercent = min(1f, abs(mPercent))

        val offsetX: Float
        var offsetY: Float

        offsetX = -(dragPercent * potatoFinalPointX) + potatoStartPointX
        offsetY =
            (dragPercent * potatoFinalPointY - potatoStartPointY) * (dragPercent * potatoFinalPointY - potatoStartPointY) / potatoOffset
        if (isRefreshing) {
            val bouncePercent = min(1f, abs(bounce))
            offsetY += convertDpToFloatPixel(context!!, 25f) * bouncePercent
        }

        matrix.postTranslate(offsetX, offsetY)

        val paint = Paint()
        val alpha = dragPercent / 2 * 500
        paint.alpha = alpha.toInt()
        canvas.drawBitmap(patato!!, matrix, paint)
    }

    private fun drawCarrot(canvas: Canvas) {
        val matrix = matrix1
        matrix.reset()
        val dragPercent = min(1f, abs(mPercent))

        val offsetX: Float
        var offsetY: Float

        offsetX = -(dragPercent * carrotFinalPointX) + carrotStartPointX
        offsetY =
            (dragPercent * carrotFinalPointY - carrotStartPointY) * (dragPercent * carrotFinalPointY - carrotStartPointY) / carrotOffset

        if (isRefreshing) {
            val bouncePercent = min(1f, abs(bounce))
            offsetY += convertDpToFloatPixel(context!!, 25f) * bouncePercent
            matrix.postRotate(bouncePercent * -30)
        }


        matrix.postRotate(dragPercent * -330)
        matrix.postTranslate(offsetX, offsetY)
        val paint = Paint()
        val alpha = dragPercent / 2 * 500
        paint.alpha = alpha.toInt()
        canvas.drawBitmap(carrot!!, matrix, paint)
    }

    private fun drawRightPea(canvas: Canvas) {
        val matrix = matrix1
        matrix.reset()
        val dragPercent = min(1f, abs(mPercent))

        val offsetX: Float
        var offsetY: Float

        offsetX = -(dragPercent * rightPeaFinalPointX) + rightPeaStartPointX
        offsetY =
            (dragPercent * rightPeaFinalPointY - rightPeaStartPointY) * (dragPercent * rightPeaFinalPointY - rightPeaStartPointY) / rightPeaOffset

        if (isRefreshing) {
            val bouncePercent = min(1f, abs(bounce))
            offsetY += convertDpToFloatPixel(context!!, 25f) * bouncePercent
        }

        matrix.postTranslate(offsetX, offsetY)
        val paint = Paint()
        val alpha = dragPercent / 2 * 500
        paint.alpha = alpha.toInt()
        canvas.drawBitmap(rightPea!!, matrix, paint)
    }

    private fun drawLeftPea(canvas: Canvas) {
        val matrix = matrix1
        matrix.reset()
        val dragPercent = min(1f, abs(mPercent))

        val offsetX: Float
        var offsetY: Float

        offsetX = -(dragPercent * leftPeaFinalPointX) + leftPeaStartPointX
        offsetY =
            (dragPercent * leftPeaFinalPointY - leftPeaStartPointY) * (dragPercent * leftPeaFinalPointY - leftPeaStartPointY) / leftPeaOffset

        if (isRefreshing) {
            val bouncePercent = min(1f, abs(bounce))
            offsetY += convertDpToFloatPixel(context!!, 25f) * bouncePercent
        }


        matrix.postTranslate(offsetX, offsetY)

        val paint = Paint()
        val alpha = dragPercent / 2 * 500
        paint.alpha = alpha.toInt()
        canvas.drawBitmap(leftPea!!, matrix, paint)
    }

    private fun drawWater(canvas: Canvas) {
        val matrix = matrix1
        matrix.reset()

        val dragPercent = min(1f, abs(mPercent))
        val offsetX = screenWidth / 2 - water!!.width / 2
        val offsetY = panTopOffset * dragPercent + convertDpToFloatPixel(context!!, 10f)
        if (isCoverDropped) {
            matrix.postScale(
                1f,
                scale,
                convertDpToPixel(context!!, 48).toFloat(),
                convertDpToPixel(context!!, 60).toFloat()
            )
            matrix.postTranslate(offsetX, offsetY)
            val paint = Paint()
            canvas.drawBitmap(water!!, matrix, paint)
        }
    }

    private fun drawFlame(
        canvas: Canvas,
        bitmap: Bitmap?,
        flameOffsetX: Float,
        flameOffsetY: Float,
        scaleY: Float,
        pivotX: Float,
        pivotY: Float
    ) {
        val matrix = matrix1
        matrix.reset()

        if (isShadowDisplayed) {
            matrix.postTranslate(flameOffsetX, flameOffsetY)
            val flameMinScale = 0.9f
            matrix.postScale(
                max(flameMinScale, scaleY),
                max(flameMinScale, scaleY),
                pivotX,
                pivotY
            )
            val paint = Paint()
            val alpha = max(0.5f, flameScale) * 255
            paint.alpha = alpha.toInt()
            canvas.drawBitmap(bitmap!!, matrix, paint)
        }
    }

    private fun drawShadow(canvas: Canvas) {
        val matrix = matrix1
        matrix.reset()

        val dragPercent = min(1f, abs(mPercent))
        val offsetX = screenWidth / 2 - shadow!!.width / 2 + convertDpToFloatPixel(context!!, 17f)
        if (isCoverDropped) {
            val offsetY = panTopOffset * dragPercent
            matrix.postTranslate(offsetX, offsetY)
            val paint = Paint()
            val alpha = bounce / 2 * 500
            paint.alpha = alpha.toInt()
            canvas.drawBitmap(shadow!!, matrix, paint)
        }
    }

    private fun drawBubble(
        canvas: Canvas,
        bubbleOffsetX: Float,
        bubbleOffsetY: Float,
        move: Float,
        pivotX: Float
    ) {
        val matrix = matrix1
        matrix.reset()

        val offsetY = bubbleOffsetY - bubbleScaleOffset - bubbleScaleOffset * move
        if (isShadowDisplayed) {
            if (move < 0.48) {
                matrix.postScale(move, move, pivotX, bubbleOffsetY)
                matrix.postTranslate(bubbleOffsetX, offsetY)
                val paint = Paint()
                canvas.drawBitmap(bubble!!, matrix, paint)
            }
        }
    }

    private fun setPercent(percent: Float) {
        mPercent = percent
    }

    private fun setVariable(value: Float): Float {
        invalidateSelf()
        return value
    }

    /**
     * @param dp The offset of pivot to make bubbles move straight upward, while scaling.
     */
    private fun setBubblesPivot(dp: Float) {
        bubble1PivotX = bubble1LeftOffset - convertDpToFloatPixel(context!!, dp)
        bubble2PivotX = bubble2LeftOffset - convertDpToFloatPixel(context!!, dp)
        bubble3PivotX = bubble3LeftOffset - convertDpToFloatPixel(context!!, dp)
        bubble4PivotX = bubble4LeftOffset - convertDpToFloatPixel(context!!, dp)
        bubble5PivotX = bubble5LeftOffset - convertDpToFloatPixel(context!!, dp)
        bubble6PivotX = bubble6LeftOffset - convertDpToFloatPixel(context!!, dp)
    }

    private fun resetOriginals() {
        setPercent(0f)
        bounce = setVariable(0f)
        scale = setVariable(0f)
        bubble1Move = setVariable(0f)
        bubble2Move = setVariable(0f)
        bubble3Move = setVariable(0f)
        bubble4Move = setVariable(0f)
        bubble5Move = setVariable(0f)
        bubble6Move = setVariable(0f)
        flameScale = setVariable(0f)
        coverJump = setVariable(0f)
    }

    private fun setupAnimations() {
        val animationFactory = AnimationFactory()
        bounceAnimation = animationFactory.getBounce(object : Animation() {
            public override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                t.transformationType = Transformation.TYPE_BOTH
                bounce = setVariable(interpolatedTime)
            }

        })

        scaleAnimation = animationFactory.getScale(object : Animation() {

            public override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                scale = setVariable(interpolatedTime)
            }
        })


        flameScaleAnimation = animationFactory.getFlameScale(object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                flameScale = setVariable(interpolatedTime)
            }
        })


        flameBurnAnimation = animationFactory.getFlameBurn(object : Animation() {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                flameBurn = setVariable(1f - interpolatedTime)
                flameScale = setVariable(interpolatedTime)
            }
        })


        bubble1Animation = animationFactory.getBubble(object : Animation() {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                bubble1Move = setVariable(interpolatedTime)

            }
        }, 0)

        bubble2Animation = animationFactory.getBubble(object : Animation() {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                bubble2Move = setVariable(interpolatedTime)

            }
        }, ANIMATION_BUBBLE1_OFFSET)

        bubble3Animation = animationFactory.getBubble(object : Animation() {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                bubble3Move = setVariable(interpolatedTime)

            }
        }, ANIMATION_BUBBLE2_OFFSET)

        bubble4Animation = animationFactory.getBubble(object : Animation() {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                bubble4Move = setVariable(interpolatedTime)

            }
        }, ANIMATION_BUBBLE3_OFFSET)

        bubble5Animation = animationFactory.getBubble(object : Animation() {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                bubble5Move = setVariable(interpolatedTime)

            }
        }, ANIMATION_BUBBLE4_OFFSET)

        bubble6Animation = animationFactory.getBubble(object : Animation() {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                bubble6Move = setVariable(interpolatedTime)

            }
        }, ANIMATION_BUBBLE5_OFFSET)

        coverAnimation = animationFactory.getCover(object : Animation() {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                coverJump = setVariable(interpolatedTime)

            }
        })

    }

    companion object {

        private const val ANIMATION_BUBBLE1_OFFSET = 200
        private const val ANIMATION_BUBBLE2_OFFSET = 300
        private const val ANIMATION_BUBBLE3_OFFSET = 500
        private const val ANIMATION_BUBBLE4_OFFSET = 700
        private const val ANIMATION_BUBBLE5_OFFSET = 800
    }


}
