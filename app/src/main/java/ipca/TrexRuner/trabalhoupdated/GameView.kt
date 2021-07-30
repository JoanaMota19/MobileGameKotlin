package ipca.TrexRuner.trabalhoupdated


import android.content.Context
import android.content.Intent
import android.graphics.*
import android.media.MediaPlayer
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView


class GameView: SurfaceView, Runnable {

    var playing : Boolean = false
    var gameThread : Thread? = null
    var backSound : MediaPlayer
    var player : Player
    var backGround : BackGround
    var pause : Pause
    var deadView : DeadView
    var paint : Paint
    var canvas : Canvas
    var surfaceHolder : SurfaceHolder
    var score : Int = 0
    var pauseActivity : PauseActivity
    var ClickPause : Boolean = false
    var killImage = false

    var enemies : MutableList<Enemy> = ArrayList<Enemy>()

    var viewWidth = 0
    var viewHeight = 0

    constructor(context: Context?,
                viewWidth : Int,
                viewHeight : Int ) : super(context) {
        player = Player(context!!, viewWidth, viewHeight)
        pauseActivity = PauseActivity()
        backGround = BackGround(context!!,viewWidth,viewHeight)
        pause = Pause(context!!,viewWidth,viewHeight)
        deadView = DeadView(context!!,viewWidth,viewHeight)
        paint = Paint()
        paint.textSize = 100.0f
        canvas = Canvas()
        surfaceHolder = holder
        backSound = MediaPlayer.create(context, R.raw.sound)
        this.viewWidth = viewWidth
        this.viewHeight = viewHeight


        for (i in 0 until 1) {
            enemies.add(Enemy(context, viewWidth,viewHeight))
        }
    }

    override fun run() {
        while (playing) {
            draw()
            update()
            control()
        }
    }

    fun update(){

        player.update(viewWidth,viewHeight)

        for(e in enemies){
            e.update(player.speed,context)



            backSound.start()
            if (e.collisionDetection.intersect(player.collisionDetection)){
                killImage = true

            }
            if (ClickPause){
                    startActivity()
            }
        }
    }


    fun draw(){
        if (surfaceHolder.surface.isValid){
            canvas = surfaceHolder.lockCanvas()
            canvas.drawColor(Color.GREEN)

            canvas.drawBitmap(backGround.bitmap,backGround.x.toFloat(),backGround.y.toFloat(), paint)


            canvas.drawBitmap(pause.bitmap,pause.x.toFloat(),pause.y.toFloat(), paint)


            canvas.drawBitmap(player.bitmap!!, player.x.toFloat(), player.y.toFloat(), paint)



            for (e in enemies) {
                canvas.drawBitmap(e.bitmap!!, e.x.toFloat(), e.y.toFloat(), paint)
            }


            paint.color = Color.GREEN

            for (e in enemies) {
                canvas.drawText("Score :"+ e.Score, viewWidth / 2.0f, 100.0f,paint )
                score = e.Score
            }

            if (killImage) canvas.drawBitmap(deadView.bitmap, deadView.x.toFloat(), deadView.y.toFloat(), paint)


            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }



    fun control(){
        Thread.sleep(30)
    }

    fun pause(){
        playing = false
        gameThread!!.join()
    }

    fun resume(){
        playing = true
        gameThread = Thread(this)
        gameThread!!.start()
    }



    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let{ev->
            when(ev.action.and(MotionEvent.ACTION_MASK)){
                MotionEvent.ACTION_DOWN -> {
                    val rect = Rect(ev.x.toInt(),ev.y.toInt(),ev.x.toInt()+50,ev.y.toInt() + 50)
                    if (rect.intersect(pause.collisionDetection)) {
                        ClickPause = true
                    }else player.jump = true
                }
            }

        }

        return true
    }


    private fun startActivity() { // TODO Auto-generated method stub

        val intent= Intent(getContext(), PauseActivity::class.java)
        context.startActivity(intent)
        //val myIntent = Intent("android.intent.action.DetectBlock")
        //myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //context.startActivity(myIntent)

    }
}