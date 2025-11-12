package ro.pub.cs.systems.eim.practicaltest01var07

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var07SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var07_secondary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val row1LeftEditText = findViewById<EditText>(R.id.row_1_left_edit_text)
        val row1RightEditText = findViewById<EditText>(R.id.row_1_right_edit_text)
        val row2LeftEditText = findViewById<EditText>(R.id.row_2_left_edit_text)
        val row2RightEditText = findViewById<EditText>(R.id.row_2_right_edit_text)

        // Get the values from the intent
        val row1Left = intent.getStringExtra("row1Left") ?: ""
        val row1Right = intent.getStringExtra("row1Right") ?: ""
        val row2Left = intent.getStringExtra("row2Left") ?: ""
        val row2Right = intent.getStringExtra("row2Right") ?: ""

        // Set the values to the EditTexts
        row1LeftEditText.setText(row1Left)
        row1RightEditText.setText(row1Right)
        row2LeftEditText.setText(row2Left)
        row2RightEditText.setText(row2Right)
    }
}