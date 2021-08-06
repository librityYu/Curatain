package com.example.currtain

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.qw.curtain.lib.Curtain
import com.qw.curtain.lib.CurtainFlow
import com.qw.curtain.lib.GuideView
import com.qw.curtain.lib.flow.CurtainFlowInterface

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showInitGuide()
    }

    private fun showInitGuide() {
        CurtainFlow.Builder()
            .with(1, getStepOneGuide())
            .with(2, getStepOneGuide2())
            .create().start(object : CurtainFlow.CallBack {
                override fun onProcess(currentId: Int, curtainFlow: CurtainFlowInterface?) {
                    curtainFlow?.let {
                        var v = it.findTargetLightView<GuideView>()
                        v.setOnClickTargetView { curtainFlow.push() }
                    }
                }

                override fun onFinish() {
                }
            })
    }

    fun getStepOneGuide(): Curtain {
        return Curtain(this)
            .with(findViewById<View>(R.id.btn_shape_circle))
            .setTopView(R.layout.view_guide_flow1)
    }

    fun getStepOneGuide2(): Curtain {
        return Curtain(this)
            .with(findViewById<View>(R.id.btn_shape_circle2))
            .setTopView(R.layout.view_guide_flow1)

    }
}