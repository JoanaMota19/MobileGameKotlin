package ipca.TrexRuner.trabalhoupdated

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import androidx.core.graphics.scale
import kotlin.math.roundToInt
import kotlin.system.exitProcess

class Player {
    var bitmap : Bitmap
    var x : Int = 0
    var y : Int = 0
    var ground : Boolean = true
    var jump : Boolean = false
    var ascending : Boolean = false
    var faling : Boolean = false




    var speed : Int = 0

    private val GRAVITY : Int = -10
    private var maxY = 0
    private var minY = 0
    private var Saltos : Int = 0

    val collisionDetection : Rect

    constructor(context: Context,
                borderWidth : Int,
                borderHeight : Int ){
        x = 75
        y = 50
        speed = 1
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player)
        bitmap = Bitmap.createScaledBitmap(bitmap,150, 150, true);

        maxY = borderHeight - bitmap.height
        minY = 0


        collisionDetection = Rect(x,y,bitmap.width,bitmap.height)
    }

    fun update(borderWidth : Int,
               borderHeight : Int){



        if(ground){
            y = maxY - 300
        }
        if(ascending) {
            if (Saltos < 5) speed += 15
            else if(Saltos < 10) speed += 30
            else speed += 60
        }
        if(faling) {
            if (Saltos < 5) speed -= 20
            else if(Saltos < 10) speed -= 30
            else speed -= 60
        }
        y -= speed + GRAVITY

        if (jump){
            Saltos ++
            ascending = true
            ground = false
            jump = false
        }
        if (y <borderHeight / 2 + bitmap.height - 300 ) {
            faling = true
            ascending = false
        }
        if (y > maxY - 300 ) {
            ground = true
            faling = false

        }
        if ( y < borderHeight/2 - 300) y = borderHeight / 2 + bitmap.height - 300
        if ( y - bitmap.height - 300 > borderHeight- 300) y = borderHeight - bitmap.height- 300



        collisionDetection.set(x,y,x + bitmap.width, y + bitmap.height)
    }
}