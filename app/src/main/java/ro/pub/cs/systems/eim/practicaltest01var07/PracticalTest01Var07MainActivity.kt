package ro.pub.cs.systems.eim.practicaltest01var07

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class PracticalTest01Var07MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var07_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val setButton = findViewById<Button>(R.id.set)
        val randomButton = findViewById<Button>(R.id.random)

        val row1LeftEditText = findViewById<EditText>(R.id.row_1_left_edit_text)
        val row1RightEditText = findViewById<EditText>(R.id.row_1_right_edit_text)
        val row2LeftEditText = findViewById<EditText>(R.id.row_2_left_edit_text)
        val row2RightEditText = findViewById<EditText>(R.id.row_2_right_edit_text)

        randomButton.setOnClickListener {
            if (row1LeftEditText.text.toString().isEmpty()) {
                row1LeftEditText.setText(String.format("%.2f", Random.nextFloat() * 10))
            }
            if (row1RightEditText.text.toString().isEmpty()) {
                row1RightEditText.setText(String.format("%.2f", Random.nextFloat() * 10))
            }
            if (row2LeftEditText.text.toString().isEmpty()) {
                row2LeftEditText.setText(String.format("%.2f", Random.nextFloat() * 10))
            }
            if (row2RightEditText.text.toString().isEmpty()) {
                row2RightEditText.setText(String.format("%.2f", Random.nextFloat() * 10))
            }

            // Launch SecondaryActivity with the values
            val intent = Intent(this, PracticalTest01Var07SecondaryActivity::class.java)
            intent.putExtra("row1Left", row1LeftEditText.text.toString())
            intent.putExtra("row1Right", row1RightEditText.text.toString())
            intent.putExtra("row2Left", row2LeftEditText.text.toString())
            intent.putExtra("row2Right", row2RightEditText.text.toString())
            startActivity(intent)
        }
    }
}