package ipca.TrexRuner.trabalhoupdated


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class Pause {
    var bitmap : Bitmap
    var x : Int = 0
    var y : Int = 0


    val collisionDetection : Rect



    constructor(context: Context,
                borderWidth : Int,
                borderHeight : Int ){
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.pausebutton)
        bitmap = Bitmap.createScaledBitmap(bitmap,150, 150, true);

        collisionDetection = Rect(x,y,bitmap.width,bitmap.height)
    }

}