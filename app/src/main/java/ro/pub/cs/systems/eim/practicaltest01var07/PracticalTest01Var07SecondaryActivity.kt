package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

        val row1Left = intent.getStringExtra("row1Left") ?: ""
        val row1Right = intent.getStringExtra("row1Right") ?: ""
        val row2Left = intent.getStringExtra("row2Left") ?: ""
        val row2Right = intent.getStringExtra("row2Right") ?: ""

        row1LeftEditText.setText(row1Left)
        row1RightEditText.setText(row1Right)
        row2LeftEditText.setText(row2Left)
        row2RightEditText.setText(row2Right)

        val sumButton = findViewById<Button>(R.id.sum)
        val productButton = findViewById<Button>(R.id.product)

        sumButton.setOnClickListener {
            val num1 = row1LeftEditText.text.toString().toDoubleOrNull() ?: 0.0
            val num2 = row1RightEditText.text.toString().toDoubleOrNull() ?: 0.0
            val num3 = row2LeftEditText.text.toString().toDoubleOrNull() ?: 0.0
            val num4 = row2RightEditText.text.toString().toDoubleOrNull() ?: 0.0

            val sum = num1 + num2 + num3 + num4
            val message = "Sum: $sum"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            Log.d("PracticalTest01Var07", message)

            val resultIntent = Intent()
            resultIntent.putExtra("result", sum)
            resultIntent.putExtra("operation", "sum")
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        productButton.setOnClickListener {
            val num1 = row1LeftEditText.text.toString().toDoubleOrNull() ?: 0.0
            val num2 = row1RightEditText.text.toString().toDoubleOrNull() ?: 0.0
            val num3 = row2LeftEditText.text.toString().toDoubleOrNull() ?: 0.0
            val num4 = row2RightEditText.text.toString().toDoubleOrNull() ?: 0.0

            val product = num1 * num2 * num3 * num4
            val message = "Product: $product"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            Log.d("PracticalTest01Var07", message)

            val resultIntent = Intent()
            resultIntent.putExtra("result", product)
            resultIntent.putExtra("operation", "product")
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}