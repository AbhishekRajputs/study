package com.example.tokr.pullToRefreshAnimation

import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.Interpolator

class AnimationFactory {

    private val bounceInterpolator by lazy {
        BounceInterpolator()
    }

    private val accelerateInterpolator by lazy {
        AccelerateInterpolator()
    }

    private val decelerateInterpolator by lazy {
        AccelerateDecelerateInterpolator()
    }

    /**
     * @param animation
     * This method is responsible for configuring animation that animates the bounce,when the vegetables are dropped.
     * @return Animation
     */
    fun getBounce(animation: Animation): Animation {
        configureAnimation(
            animation, bounceInterpolator, ANIMATION_DURATION,
            0, 0, 0
        )
        return animation
    }

    /**
     * @param animation
     * This method is responsible for configuring animation that animates scaling of water.
     * @return Animation
     */
    fun getScale(animation: Animation): Animation {
        configureAnimation(
            animation, accelerateInterpolator, ANIMATION_SCALE_DURATION,
            0, 0, 0
        )
        return animation
    }

    /**
     * @param animation
     * This method is responsible for configuring animation that animates the flame appearance.
     * @return Animation
     */
    fun getFlameScale(animation: Animation): Animation {
        configureAnimation(
            animation, decelerateInterpolator, ANIMATION_FLAME_SCALE_DURATION,
            0, 0, 0
        )
        return animation
    }

    /**
     * @param animation
     * This method is responsible for configuring animation that animates the flame burn;
     * @return Animation
     */
    fun getFlameBurn(animation: Animation): Animation {
        configureAnimation(
            animation, decelerateInterpolator, ANIMATION_FLAME_BURN_DURATION,
            0, Animation.REVERSE, Animation.INFINITE
        )
        return animation
    }

    /**
     * @param animation Animation that needs to be configured.
     * @param offset    With which offset should be started.
     * This method is responsible for configuring animation that animates the bubble move.
     * Animation can be started with some offset.
     * @return Animation
     */
    fun getBubble(animation: Animation, offset: Int): Animation {
        configureAnimation(
            animation, decelerateInterpolator, ANIMATION_BUBBLE_DURATION,
            offset, Animation.RESTART, Animation.INFINITE
        )
        return animation
    }

    /**
     * @param animation
     * This method is responsible for configuring animation that animates the cover dropping.
     * @return Animation
     */
    fun getCover(animation: Animation): Animation {
        configureAnimation(
            animation, bounceInterpolator, ANIMATION_COVER_DURATION,
            ANIMATION_COVER_OFFSET, Animation.REVERSE, Animation.INFINITE
        )
        return animation
    }


    private fun configureAnimation(
        animation: Animation, interpolator: Interpolator, duration: Int,
        startOffset: Int, repeatMode: Int, repeatCount: Int
    ) {
        animation.interpolator = interpolator
        animation.duration = duration.toLong()
        animation.startOffset = startOffset.toLong()
        animation.repeatMode = repeatMode
        animation.repeatCount = repeatCount
    }

    companion object {
        private const val ANIMATION_DURATION = 700
        private const val ANIMATION_SCALE_DURATION = 500
        private const val ANIMATION_FLAME_SCALE_DURATION = 100
        private const val ANIMATION_FLAME_BURN_DURATION = 180
        private const val ANIMATION_BUBBLE_DURATION = 980
        private const val ANIMATION_COVER_DURATION = 580
        private const val ANIMATION_COVER_OFFSET = 20
    }

}