package th.ac.kku.cis.mobileapp

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
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ep)

        //2
        auth = FirebaseAuth.getInstance()
        // Id = auth.currentUser!!.uid
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

        Toast.makeText(applicationContext, "Note save successfully", Toast.LENGTH_LONG).show()
        var i = Intent(this, EpActivity::class.java)
        i.putExtra("name",name)
        //startActivity(i)
        finish()
    }
}


