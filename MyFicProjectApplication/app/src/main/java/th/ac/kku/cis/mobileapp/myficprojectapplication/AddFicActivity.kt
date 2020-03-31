package th.ac.kku.cis.mobileapp.myficprojectapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_ep.*
import kotlinx.android.synthetic.main.activity_add_fic.*
import kotlinx.android.synthetic.main.activity_add_fic.imageView
import kotlinx.android.synthetic.main.activity_add_fic.text_name_login


class AddFicActivity : AppCompatActivity() {


    lateinit var activityList: MutableList<AddModelNameFic>
    lateinit var mDB: DatabaseReference

    var mAuth : FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_fic)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser
        text_name_login.text = user!!.email

        imageView.setOnClickListener {
            mAuth!!.signOut()
            Toast.makeText(this,"Signed Out", Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        activityList = mutableListOf()
        mDB = FirebaseDatabase.getInstance().reference

        button_saveaddnamefic.setOnClickListener {
            AddData("String")
        }
    }
    fun AddData(data: String) {
        var newData: AddModelNameFic = AddModelNameFic.create()
        val obj = mDB.child("AddNameFic").push()

        newData.AddNameFic = editText_addname.text.toString()

        obj.setValue(newData)


        Toast.makeText(applicationContext,"Activity save successfully", Toast.LENGTH_LONG).show()
        finish()
    }

}
