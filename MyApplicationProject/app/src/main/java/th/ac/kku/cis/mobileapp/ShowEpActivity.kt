package th.ac.kku.cis.mobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ep.*
import kotlinx.android.synthetic.main.activity_show_ep.*

class ShowEpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_ep)

        //var name = getIntent().getStringExtra("name")

        var EpName = getIntent().getStringExtra("epName")
        var Story = getIntent().getStringExtra("story")

        textView_Topname.text = EpName
        textView_body.text = Story


    }
}
