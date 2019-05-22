package com.mygdx.jumpactiongame

import com.badlogic.gdx.graphics.Texture

class Enemy(type: Int, texture: Texture, srcX: Int, srcY: Int, srcWidth: Int, srcHeight: Int)
    : GameObject(texture, srcX, srcY, srcWidth, srcHeight) {

    companion object {
        // 横幅、高さ
        val Enemy_WIDTH = 2.0f
        val Enemy_HEIGHT = 0.9f

        // タイプ（通常と動くタイプ）
        val Enemy_TYPE_STATIC = 0
        val Enemy_TYPE_MOVING = 1

        // 状態（通常と消えた状態）
        val Enemy_STATE_NORMAL = 0
        val Enemy_STATE_VANISH = 1

        // 速度
        val Enemy_VELOCITY = 10.0f
        // 状態
        val Enemy_EXIST = 0
        val Enemy_NONE = 1
    }

    var mState: Int = 0
    var mType: Int

    init {
        setSize(Enemy_WIDTH, Enemy_HEIGHT)
        mType = Enemy_TYPE_MOVING
        if (mType == Enemy_TYPE_MOVING) {
            velocity.x = Enemy_VELOCITY
        }
    }

    // 座標を更新する
    fun update(deltaTime: Float) {
        if (mType == Enemy_TYPE_MOVING) {
            x += velocity.x * deltaTime

            if (x < Enemy_WIDTH / 2) {
                velocity.x = -velocity.x
                x = Enemy_WIDTH / 2
            }
            if (x > GameScreen.WORLD_WIDTH - Enemy_WIDTH / 2) {
                velocity.x = -velocity.x
                x = GameScreen.WORLD_WIDTH - Enemy_WIDTH / 2
            }
        }
    }

    // 消える
    fun vanish() {
        mState = Enemy_STATE_VANISH
        setAlpha(0f)
        velocity.x = 0f
    }
    fun get() {
        mState = Enemy_NONE
        setAlpha(0f)
    }
}