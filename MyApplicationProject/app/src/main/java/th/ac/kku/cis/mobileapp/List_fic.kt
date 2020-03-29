package th.ac.kku.cis.mobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_list_fic.*

class List_fic : AppCompatActivity() {

    //หน้าแสดงชื่อนิยาย
    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_fic)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser
        text_name_login.text = user!!.email


        val listname_fic = resources.getStringArray(R.array.list_fic)
        val listname_listfic = mutableListOf<NameficModel>()

        for (i in 0..listname_fic.size - 1) {
            listname_listfic.add(NameficModel(listname_fic[i]))
        }

        val listView = findViewById<ListView>(R.id.listname_view)
        listView.adapter = MylistnameficAdapter(
            this, R.layout.layout_namelist, listname_listfic
        )

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as NameficModel
            //Toast.makeText(this,selectedItem,Toast.LENGTH_SHORT).show()
            val intent = Intent(this, EpActivity::class.java)

            intent.putExtra("name", selectedItem.namefic)

            startActivity(intent)
        }
    }

}
