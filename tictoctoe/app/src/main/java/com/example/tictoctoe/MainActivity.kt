package com.example.tictoctoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.tictoctoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    enum class Turn
    {
        NOUGHT,
        CROSS
    }

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private var crossesScore = 0
    private var noughtsScore = 0
    private var drawScore = 0

    private var boardList = mutableListOf<Button>()
    private var tvList = mutableListOf<Any>()

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()

        binding.btnReset.setOnClickListener {
            resetBoard()
        }
    }

    private fun initBoard()
    {
        tvList.add(binding.tvDraw)
        tvList.add(binding.tvPlayerX)
        tvList.add(binding.tvPlayerO)
        tvList.add(binding.btnReset)
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
    }

    fun boardTapped(view: View)
    {
        if(view !is Button)
            return
        addToBoard(view)

        if(checkForVictory(NOUGHT))
        {
            noughtsScore++
            result("Noughts Win!")
        }
        else if(checkForVictory(CROSS))
        {
            crossesScore++
            result("Crosses Win!")
        }

        if(fullBoard() && !checkForVictory(CROSS) && !checkForVictory(NOUGHT))
        {
            drawScore++
            result("Draw")
        }

    }

    private fun checkForVictory(s: String): Boolean
    {
        //Horizontal
        if(match(binding.a1,s) && match(binding.a2,s) && match(binding.a3,s)){
            binding.a1.setTextColor(Color.RED)
            binding.a2.setTextColor(Color.RED)
            binding.a3.setTextColor(Color.RED)
            return true
        }

        if(match(binding.b1,s) && match(binding.b2,s) && match(binding.b3,s)){
            binding.b1.setTextColor(Color.RED)
            binding.b2.setTextColor(Color.RED)
            binding.b3.setTextColor(Color.RED)
            return true
        }

        if(match(binding.c1,s) && match(binding.c2,s) && match(binding.c3,s)){
            binding.c1.setTextColor(Color.RED)
            binding.c2.setTextColor(Color.RED)
            binding.c3.setTextColor(Color.RED)
            return true
        }
        //Vertical
        if(match(binding.a1,s) && match(binding.b1,s) && match(binding.c1,s)){
            binding.a1.setTextColor(Color.RED)
            binding.b1.setTextColor(Color.RED)
            binding.c1.setTextColor(Color.RED)
            return true
        }
        if(match(binding.a2,s) && match(binding.b2,s) && match(binding.c2,s)){
            binding.a2.setTextColor(Color.RED)
            binding.b2.setTextColor(Color.RED)
            binding.c2.setTextColor(Color.RED)
            return true
        }
        if(match(binding.a3,s) && match(binding.b3,s) && match(binding.c3,s)){
            binding.a3.setTextColor(Color.RED)
            binding.b3.setTextColor(Color.RED)
            binding.c3.setTextColor(Color.RED)
            return true
        }

        //Diagonal
        if(match(binding.a1,s) && match(binding.b2,s) && match(binding.c3,s)){
            binding.a1.setTextColor(Color.RED)
            binding.b2.setTextColor(Color.RED)
            binding.c3.setTextColor(Color.RED)
            return true
        }
        if(match(binding.a3,s) && match(binding.b2,s) && match(binding.c1,s)){
            binding.a3.setTextColor(Color.RED)
            binding.b2.setTextColor(Color.RED)
            binding.c1.setTextColor(Color.RED)
            return true
        }

        return false
    }


    private fun match(button: Button, symbol : String): Boolean = button.text == symbol

    private fun result(title: String)
    {
        binding.tvDraw.text = "Draw : ${drawScore}"
        binding.tvPlayerX.text = "Player X : ${crossesScore}"
        binding.tvPlayerO.text = "Player O : ${noughtsScore}"

        val message = "\nNoughts $noughtsScore\n\nCrosses $crossesScore"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Reset")
            { _,_ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }

    private fun resetBoard()
    {
        for(button in boardList)
        {
            button.text = ""
            button.setTextColor(Color.BLACK)
        }

        if(firstTurn == Turn.NOUGHT)
            firstTurn = Turn.CROSS
        else if(firstTurn == Turn.CROSS)
            firstTurn = Turn.NOUGHT

        currentTurn = firstTurn
        setTurnLabel()
    }

    private fun fullBoard(): Boolean
    {
        for(button in boardList)
        {
            if(button.text == "")
                return false
        }
        return true
    }

    private fun addToBoard(button: Button)
    {
        if(button.text != "")
            return

        if(currentTurn == Turn.NOUGHT)
        {
            button.text = NOUGHT
            currentTurn = Turn.CROSS
        }
        else if(currentTurn == Turn.CROSS)
        {
            button.text = CROSS
            currentTurn = Turn.NOUGHT
        }
        setTurnLabel()
    }

    private fun setTurnLabel()
    {
        var turnText = ""
        if(currentTurn == Turn.CROSS)
            turnText = "Turn $CROSS"
        else if(currentTurn == Turn.NOUGHT)
            turnText = "Turn $NOUGHT"

        binding.turnTV.text = turnText
    }

    companion object
    {
        const val NOUGHT = "O"
        const val CROSS = "X"
    }

}