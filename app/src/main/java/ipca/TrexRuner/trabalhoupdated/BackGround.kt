package ipca.TrexRuner.trabalhoupdated


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory


class BackGround {
    var bitmap : Bitmap
    var x : Int = 0
    var y : Int = 0



    constructor(context: Context,
                borderWidth : Int,
                borderHeight : Int ){
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.fundojogo)
    }

}