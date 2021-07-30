package ipca.TrexRuner.trabalhoupdated

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import java.util.*


class Enemy {

    var bitmap : Bitmap

    var x : Int = 0
    var y : Int = 0
    var speed : Int = 0
    var typeEnemy : Int = 0
    public var Score : Int = 0

    private var maxY = 0
    private var minY = 0
    private var maxX = 0
    private var minX = 0

    val generator = Random()

    val collisionDetection : Rect

    constructor(context: Context,
                borderWidth : Int,
                borderHeight : Int ){

        typeEnemy =enemysArray()[generator.nextInt(3)]

        bitmap = BitmapFactory.decodeResource(context.resources, typeEnemy)
        if(typeEnemy == enemysArray()[2])bitmap = Bitmap.createScaledBitmap(bitmap, 150, 50, true)
        if(typeEnemy == enemysArray()[1])bitmap = Bitmap.createScaledBitmap(bitmap, 50, 150, true)
        if(typeEnemy == enemysArray()[0])bitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true)


        maxY = borderHeight
        minY = 0
        maxX = borderWidth
        minX = 0

        speed = generator.nextInt(1)+20
        x = borderWidth
        if (typeEnemy == enemysArray()[2]) y = maxY - bitmap.height - 500
        else y = maxY - bitmap.height - 300


        collisionDetection = Rect(x,y,bitmap.width,bitmap.height)

    }

    fun update(playerSpeed : Int,context: Context){

        if (Score < 500) x -= speed
        else if (Score < 1000) x -= speed * 2
        else if (Score < 3000) x -= speed * 3
        else if (Score < 6000) x -= speed * 4
        else if (Score < 9000) x -= speed * 5
        else x -= speed * 10


        if (x < minX - bitmap.width) {

            Score += 100
            speed = generator.nextInt(1) + 20

            typeEnemy = enemysArray()[(generator.nextInt(3))]

            bitmap = BitmapFactory.decodeResource(context.resources, typeEnemy)
            if(typeEnemy == enemysArray()[2])bitmap = Bitmap.createScaledBitmap(bitmap, 150, 50, true)
            if(typeEnemy == enemysArray()[1])bitmap = Bitmap.createScaledBitmap(bitmap, 50, 150, true)
            if(typeEnemy == enemysArray()[0])bitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true)


            x = maxX

            if (typeEnemy == enemysArray()[2]) y = maxY - bitmap.height - 500
            else y = maxY - bitmap.height - 300

            val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrator.vibrate(200)
            }


        }

        collisionDetection.set(x,y,x + bitmap.width, y + bitmap.height)
    }
}