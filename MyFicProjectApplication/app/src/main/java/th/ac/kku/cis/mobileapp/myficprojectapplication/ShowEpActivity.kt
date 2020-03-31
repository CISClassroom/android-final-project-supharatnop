package th.ac.kku.cis.mobileapp.myficprojectapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_add_ep.*
import kotlinx.android.synthetic.main.activity_show_ep.*
import kotlinx.android.synthetic.main.activity_show_ep.imageView
import kotlinx.android.synthetic.main.activity_show_ep.text_name_login

class ShowEpActivity : AppCompatActivity() {

    var mAuth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_ep)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser
        text_name_login.text = user!!.email

        imageView.setOnClickListener {
            mAuth!!.signOut()
            Toast.makeText(this,"Signed Out", Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        //var name = getIntent().getStringExtra("name")

        var EpName = getIntent().getStringExtra("epName")
        var Story = getIntent().getStringExtra("story")

        textView_Topname.text = EpName
        textView_body.text = Story
    }
}
