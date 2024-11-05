package com.example.lab6_kotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab6_kotlin.models.InputModel
import java.lang.Math.pow
import kotlin.math.sqrt

class HomeViewModel : ViewModel() {
    private val inputDataGrindingMachine = MutableLiveData(InputModel())
    val inputGrindingMachine = inputDataGrindingMachine

    private val inputDataGroutingMachine = MutableLiveData(InputModel())
    val inputGroutingMachine = inputDataGroutingMachine

    private val inputDataCircularSaw = MutableLiveData(InputModel())
    val inputCircularSaw = inputDataCircularSaw

    private val inputDataPress = MutableLiveData(InputModel())
    val inputPress = inputDataPress

    private val inputDataPolishingMachine = MutableLiveData(InputModel())
    val inputPolishingMachine = inputDataPolishingMachine

    private val inputDataShaper = MutableLiveData(InputModel())
    val inputShaper = inputDataShaper

    private val inputDataFan = MutableLiveData(InputModel())
    val inputFan = inputDataFan


    fun calculate() {
        val nPnGrinding = countNPn(inputGrindingMachine.value!!.n, inputGrindingMachine.value!!.Pn)
        val IpGrinding = calculateGrindingMachine(nPnGrinding)

        val nPnGrouting = countNPn(inputGroutingMachine.value!!.n, inputGroutingMachine.value!!.Pn)
        val IpGrouting = calculateGroutingMachine(nPnGrouting)

        val nPnCircleSaw = countNPn(inputCircularSaw.value!!.n, inputCircularSaw.value!!.Pn)
        val IpCircularSaw = calculateCircularSaw(nPnCircleSaw)

        val nPnPress = countNPn(inputPress.value!!.n, inputPress.value!!.Pn)
        val IpPress = calculatePress(nPnPress)

        val nPnPolishing =
            countNPn(inputPolishingMachine.value!!.n, inputPolishingMachine.value!!.Pn)
        val IpPolishing = calculatePolishingMachine(nPnPolishing)

        val nPnShaper = countNPn(inputShaper.value!!.n, inputShaper.value!!.Pn)
        val IpShaper = calculateShaper(nPnShaper)

        val nPnFan = countNPn(inputFan.value!!.n, inputFan.value!!.Pn)
        val IpFan = calculateFan(nPnFan)

        val sum = nPnGrinding + nPnGrouting + nPnCircleSaw + nPnPress + nPnPolishing + nPnShaper + nPnFan
        val Kv =
            (nPnGrinding * inputGrindingMachine.value!!.Kv + nPnGrouting * inputGroutingMachine.value!!.Kv
                    + nPnCircleSaw * inputCircularSaw.value!!.Kv + nPnPress * inputPress.value!!.Kv
                    + nPnPolishing * inputPolishingMachine.value!!.Kv + nPnShaper * inputShaper.value!!.Kv
                    + nPnFan * inputFan.value!!.Kv) / sum

        val ne = pow(sum, 2.0) / ()
    }


    private fun calculateGrindingMachine(nPn: Double): Double {
        return countEstimatedCurrent(
            nPn,
            inputGrindingMachine.value!!.Un,
            inputGrindingMachine.value!!.cos,
            inputGrindingMachine.value!!.eta
        )
    }

    private fun calculateGroutingMachine(nPn: Double): Double {
        return countEstimatedCurrent(
            nPn,
            inputGroutingMachine.value!!.Un,
            inputGroutingMachine.value!!.cos,
            inputGroutingMachine.value!!.eta
        )
    }

    private fun calculateCircularSaw(nPn: Double): Double {
        return countEstimatedCurrent(
            nPn,
            inputCircularSaw.value!!.Un,
            inputCircularSaw.value!!.cos,
            inputCircularSaw.value!!.eta
        )
    }

    private fun calculatePress(nPn: Double): Double {
        return countEstimatedCurrent(
            nPn,
            inputPress.value!!.Un,
            inputPress.value!!.cos,
            inputPress.value!!.eta
        )
    }

    private fun calculatePolishingMachine(nPn: Double): Double {
        return countEstimatedCurrent(
            nPn,
            inputPolishingMachine.value!!.Un,
            inputPolishingMachine.value!!.cos,
            inputPolishingMachine.value!!.eta
        )
    }

    private fun calculateShaper(nPn: Double): Double {
        return countEstimatedCurrent(
            nPn,
            inputShaper.value!!.Un,
            inputShaper.value!!.cos,
            inputShaper.value!!.eta
        )
    }

    private fun calculateFan(nPn: Double): Double {
        return countEstimatedCurrent(
            nPn,
            inputFan.value!!.Un,
            inputFan.value!!.cos,
            inputFan.value!!.eta
        )
    }


    private fun countNPn(n: Int, Pn: Double): Double {
        return n * Pn
    }

    private fun countNPnPow2(n: Int, Pn: Double): Double {
        return n * Pn * Pn
    }

    private fun countEstimatedCurrent(nPn: Double, Un: Double, cos: Double, eta: Double): Double {
        return nPn / (sqrt(3.0) * Un * cos * eta)
    }

}