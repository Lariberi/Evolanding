package com.example.evolanding



object Monster {

    var nextMove: () -> Unit = {}

    fun startTheGame() {
        Thread(Runnable {
            while (true) {
                Thread.sleep(4)
                nextMove()
            }
        }).start()
    }
}