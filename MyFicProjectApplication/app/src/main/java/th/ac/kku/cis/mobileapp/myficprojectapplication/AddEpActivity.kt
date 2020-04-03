package th.ac.kku.cis.mobileapp.myficprojectapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_ep.*

class AddEpActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference

    var mAuth : FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ep)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser
        text_name_login.text = user!!.email

        imageView.setOnClickListener {
            mAuth!!.signOut()
            Toast.makeText(this,"Signed Out", Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        mDatabase = FirebaseDatabase.getInstance().reference

        button_SaveEp.setOnClickListener {
            AddData("String")

        }
    }

    fun AddData(data: String) {
        var name = getIntent().getStringExtra("name")

        var newData: AddFicModel = AddFicModel.create()
        val obj = mDatabase.child("EpFic").push()

        newData.TopicName = name.toString()
        newData.EpName = editText_EpName.text.toString()
        newData.Story = editText_Story.text.toString()
        newData.Id = obj.key

//        newData.EvenId = obj.key
        obj.setValue(newData)

        val inputvalue1 : String = editText_EpName.toString()
        if (inputvalue1 == null || inputvalue1.trim() == " ") {
            Toast.makeText(this,"please input data, edit text cannot be blank",Toast.LENGTH_LONG).show()
        }
        val inputvalue2 : String = editText_Story.toString()
        if (inputvalue2 == null || inputvalue1.trim() == " ") {
            Toast.makeText(this,"please input data, edit text cannot be blank",Toast.LENGTH_LONG).show()
        }


        Toast.makeText(applicationContext, "Note save successfully", Toast.LENGTH_LONG).show()
        var i = Intent(this, EpActivity::class.java)
        i.putExtra("name",name)
        //startActivity(i)
        finish()
    }
}
