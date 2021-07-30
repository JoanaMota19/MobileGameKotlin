package ipca.TrexRuner.trabalhoupdated

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class DeadView {

    var bitmap : Bitmap
    var x : Int = 0
    var y : Int = 0



    constructor(context: Context,
                borderWidth : Int,
                borderHeight : Int ){
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.gameover)
        bitmap = Bitmap.createScaledBitmap(bitmap,3000, 1500, true)

    }
}