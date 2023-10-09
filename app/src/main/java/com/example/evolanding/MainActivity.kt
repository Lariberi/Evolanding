package com.example.evolanding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.evolanding.Monster.startTheGame

const val CELL_SIZE = 100
const val VERTICAL_CELL_AMOUNT = 10
const val HORIZONTAL_CELL_AMOUNT = 15
const val VERTICAL_MAX_SIZE = CELL_SIZE * VERTICAL_CELL_AMOUNT
const val HORIZONTAL_MAX_SIZE = CELL_SIZE * HORIZONTAL_CELL_AMOUNT

class MainActivity : AppCompatActivity() {
       private val lin by lazy {
        Lines(this)}
    private lateinit var container: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // container.layoutParams = FrameLayout.LayoutParams(VERTICAL_MAX_SIZE, HORIZONTAL_MAX_SIZE)

        container = findViewById(R.id.container)

        val gg = View(this)
        gg.layoutParams = FrameLayout.LayoutParams(100, 100)
        gg.background = ContextCompat.getDrawable(this, R.drawable.ggs1)

        startTheGame()
        move(Directions.DOWN, gg)

        // Удали { Monster.nextMove = } если нужно движение змейки превратить в танчик
        // showButton.setOnClickListener { Monster.nextMove = { move(Directions.UP, gg) } }
        // showButton.setOnClickListener { move(Directions.UP, gg) }

        val showButton = findViewById<ImageView>(R.id.ButUp)
        showButton.setOnClickListener { Monster.nextMove = { move(Directions.UP, gg) } }
        val showButton1 = findViewById<ImageView>(R.id.ButDown)
        showButton1.setOnClickListener { Monster.nextMove = { move(Directions.DOWN, gg) } }
        val showButton2 = findViewById<ImageView>(R.id.ButLeft)
        showButton2.setOnClickListener { Monster.nextMove = { move(Directions.LEFT, gg) } }
        val showButton3 = findViewById<ImageView>(R.id.ButRight)
        showButton3.setOnClickListener { Monster.nextMove = { move(Directions.RIGHT, gg) } }

    }
    fun move(directions: Directions, gg: View) {
        val layoutParams = gg.layoutParams as FrameLayout.LayoutParams
        when (directions) {
            Directions.UP ->
            {if (layoutParams.topMargin>0)
                (gg.layoutParams as FrameLayout.LayoutParams).topMargin -=1 }
            Directions.DOWN ->
            {if (layoutParams.topMargin + gg.height < HORIZONTAL_MAX_SIZE)
                (gg.layoutParams as FrameLayout.LayoutParams).topMargin += 1}
            Directions.LEFT ->
            {if (layoutParams.leftMargin>0)
                (gg.layoutParams as FrameLayout.LayoutParams).leftMargin -= 1}
            Directions.RIGHT ->
            {if (layoutParams.leftMargin + gg.width < VERTICAL_MAX_SIZE)
                (gg.layoutParams as FrameLayout.LayoutParams).leftMargin += 1}
        }

        runOnUiThread {
            container.removeView(gg)
            container.addView(gg)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.lab, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
           return when (item.itemId){
                R.id.menu_lab -> {
                    lin.line()
                    //show settings(lab)
                    return true
                }
               else -> super.onOptionsItemSelected(item)
            }
    }
}
enum class Directions{
    UP,
    DOWN,
    LEFT,
    RIGHT
}