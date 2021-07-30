package ipca.TrexRuner.trabalhoupdated

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Point
import android.os.Bundle
import android.view.Display
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_pause.*


class PauseActivity: AppCompatActivity() {

    var gameActivity: GameActivity? = GameActivity()


    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE// roda a tela
            setContentView(R.layout.activity_pause)

            //regressar para a activity do jogo
            voltarButton.setOnClickListener {
                gameActivity!!.gameView!!.resume()
                finish()
            }
            //regressar ao menu principal
            menuButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }

            //sair do jogo
            sairButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }


    }

}